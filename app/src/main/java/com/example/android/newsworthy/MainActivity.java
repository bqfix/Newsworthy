package com.example.android.newsworthy;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements NewsStoriesAdapter.NewsStoriesClickHandler, LoaderManager.LoaderCallbacks<List<NewsStory>>, NewsStoriesLoader.OnStartLoadingCallback {

    //View member variables
    private LinearLayout mSearchLayout;
    private EditText mSearchText;
    private Button mExecuteSearch;
    private RecyclerView mResultsRecycler;
    private ProgressBar mProgressIndicator;
    private TextView mErrorText;
    private NewsStoriesAdapter mNewsStoriesAdapter;

    private final int LOADER_ID = 27;

    private final String BASE_URL = "https://content.guardianapis.com/search";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Assign variables
        mSearchLayout = (LinearLayout) findViewById(R.id.search_layout);
        mSearchText = (EditText) findViewById(R.id.search_edit_text);
        mExecuteSearch = (Button) findViewById(R.id.execute_search_button);
        mResultsRecycler = (RecyclerView) findViewById(R.id.results_recycler_view);
        mProgressIndicator = (ProgressBar) findViewById(R.id.progress_indicator);
        mErrorText = (TextView) findViewById(R.id.error_text);


        //Logic to Setup RecyclerView
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mResultsRecycler.setLayoutManager(layoutManager);

        mNewsStoriesAdapter = new NewsStoriesAdapter(this);
        mResultsRecycler.setAdapter(mNewsStoriesAdapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mResultsRecycler.getContext(), layoutManager.getOrientation());
        mResultsRecycler.addItemDecoration(dividerItemDecoration);

        getSupportLoaderManager().initLoader(LOADER_ID, null, this);

        mExecuteSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportLoaderManager().restartLoader(LOADER_ID, null, MainActivity.this);
                hideKeyboard();
                mSearchLayout.setVisibility(View.GONE);
            }
        });

    }

    //Setup Menu bar actions for main activity

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    //Click logic for menu

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //Compare clicked item to the ID for existing buttons and execute custom logic for each and return true(action handled)
        switch (item.getItemId()) {
            case (R.id.action_search):
                //Search EditText and button toggle
                if (mSearchLayout.getVisibility() == View.VISIBLE) {
                    mSearchLayout.setVisibility(View.GONE);
                    hideKeyboard();
                } else {
                    mSearchLayout.setVisibility(View.VISIBLE);
                }
                return true;
            case (R.id.action_preferences):
                Intent prefIntent = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(prefIntent);
                return true;
        }

        //If no match, let the system handle the click with default behavior
        return super.onOptionsItemSelected(item);
    }

    //Helper methods for showing results recycler view, progress bar, and error text
     void showResults() {
        mErrorText.setVisibility(View.INVISIBLE);
        mProgressIndicator.setVisibility(View.INVISIBLE);
        mResultsRecycler.setVisibility(View.VISIBLE);
    }

     void showProgress() {
        mErrorText.setVisibility(View.INVISIBLE);
        mResultsRecycler.setVisibility(View.INVISIBLE);
        mProgressIndicator.setVisibility(View.VISIBLE);
    }

    void showErrorText() {
        mProgressIndicator.setVisibility(View.INVISIBLE);
        mResultsRecycler.setVisibility(View.INVISIBLE);
        mErrorText.setVisibility(View.VISIBLE);
    }

    //OnStartLoadingCallback override
    @Override
    public void onStartLoading() {
        showProgress();
    }

    //Helper method to hide soft keyboard
    private void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }

    }

    //Logic to handle clicks

    @Override
    public void onItemClick(NewsStory newsStory) {
        Uri uri = Uri.parse(newsStory.getUrlString());
        Intent uriIntent = new Intent(Intent.ACTION_VIEW, uri);
        if (uriIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(uriIntent);
        }
    }

    //Overrides of LoaderCallback methods
    @NonNull
    @Override
    public Loader onCreateLoader(int i, @Nullable Bundle bundle) {
        //Get Max Results from preferences
        String query = mSearchText.getText().toString();

        Uri baseUri = Uri.parse(BASE_URL);

        Uri.Builder uriBuilder = baseUri.buildUpon();

        if (query != null && query != "") {
            uriBuilder.appendQueryParameter("q", query);
        }
        //Necessary param to access the api
        uriBuilder.appendQueryParameter("api-key", "test");

        //Necessary param to also get the contributor tag, which holds the author data (for later parsing)
        uriBuilder.appendQueryParameter("show-tags", "contributor");

        Uri builtUri = uriBuilder.build();


        return new NewsStoriesLoader(this, builtUri.toString(), this);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<NewsStory>> loader, List<NewsStory> newsStories) {
        //Set earthquakes to RecyclerView
        mNewsStoriesAdapter.setNewsStories(newsStories);

        //Show Error or Results
        if (newsStories != null && !newsStories.isEmpty()) {
            showResults();
        } else {
            showErrorText();
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader loader) {

    }

}
