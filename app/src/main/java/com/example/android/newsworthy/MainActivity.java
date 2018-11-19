package com.example.android.newsworthy;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NewsStoriesAdapter.NewsStoriesClickHandler {

    //Temporary test variables
    Toast mTestToast = null;
    private static final String testUrlString = "https://content.guardianapis.com/search?q=debate&tag=politics/politics&from-date=2014-01-01&api-key=test";

    //View member variables
    private LinearLayout mSearchLayout;
    private EditText mSearchText;
    private Button mExecuteSearch;
    private RecyclerView mResultsRecycler;
    private ProgressBar mProgressIndicator;
    private TextView mErrorText;
    private NewsStoriesAdapter mNewsStoriesAdapter;

    private ArrayList<NewsStory> mTestArray;


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


        mTestArray = new ArrayList<>();
        mTestArray.add(new NewsStory("Female peers condemn 'misogynistic' attitudes in Lord Lester debate", "2018-11-16T18:50:43Z", "https://www.theguardian.com/politics/2018/nov/16/female-peers-condemn-misogynistic-attitudes-in-lord-lester-debate"));
        mTestArray.add(new NewsStory("Female peers condemn 'misogynistic' attitudes in Lord Lester debate", "2018-11-16T18:50:43Z", "https://www.theguardian.com/politics/2018/nov/16/female-peers-condemn-misogynistic-attitudes-in-lord-lester-debate"));
        mTestArray.add(new NewsStory("Female peers condemn 'misogynistic' attitudes in Lord Lester debate", "2018-11-16T18:50:43Z", "https://www.theguardian.com/politics/2018/nov/16/female-peers-condemn-misogynistic-attitudes-in-lord-lester-debate"));
        mTestArray.add(new NewsStory("Female peers condemn 'misogynistic' attitudes in Lord Lester debate", "2018-11-16T18:50:43Z", "https://www.theguardian.com/politics/2018/nov/16/female-peers-condemn-misogynistic-attitudes-in-lord-lester-debate"));
        mTestArray.add(new NewsStory("Female peers condemn 'misogynistic' attitudes in Lord Lester debate", "2018-11-16T18:50:43Z", "https://www.theguardian.com/politics/2018/nov/16/female-peers-condemn-misogynistic-attitudes-in-lord-lester-debate"));
        mTestArray.add(new NewsStory("Female peers condemn 'misogynistic' attitudes in Lord Lester debate", "2018-11-16T18:50:43Z", "https://www.theguardian.com/politics/2018/nov/16/female-peers-condemn-misogynistic-attitudes-in-lord-lester-debate"));
        mTestArray.add(new NewsStory("Female peers condemn 'misogynistic' attitudes in Lord Lester debate", "2018-11-16T18:50:43Z", "https://www.theguardian.com/politics/2018/nov/16/female-peers-condemn-misogynistic-attitudes-in-lord-lester-debate"));
        mTestArray.add(new NewsStory("Female peers condemn 'misogynistic' attitudes in Lord Lester debate", "2018-11-16T18:50:43Z", "https://www.theguardian.com/politics/2018/nov/16/female-peers-condemn-misogynistic-attitudes-in-lord-lester-debate"));
        mTestArray.add(new NewsStory("Female peers condemn 'misogynistic' attitudes in Lord Lester debate", "2018-11-16T18:50:43Z", "https://www.theguardian.com/politics/2018/nov/16/female-peers-condemn-misogynistic-attitudes-in-lord-lester-debate"));
        mTestArray.add(new NewsStory("Female peers condemn 'misogynistic' attitudes in Lord Lester debate", "2018-11-16T18:50:43Z", "https://www.theguardian.com/politics/2018/nov/16/female-peers-condemn-misogynistic-attitudes-in-lord-lester-debate"));
        mTestArray.add(new NewsStory("Female peers condemn 'misogynistic' attitudes in Lord Lester debate", "2018-11-16T18:50:43Z", "https://www.theguardian.com/politics/2018/nov/16/female-peers-condemn-misogynistic-attitudes-in-lord-lester-debate"));
        mTestArray.add(new NewsStory("Female peers condemn 'misogynistic' attitudes in Lord Lester debate", "2018-11-16T18:50:43Z", "https://www.theguardian.com/politics/2018/nov/16/female-peers-condemn-misogynistic-attitudes-in-lord-lester-debate"));
        mTestArray.add(new NewsStory("Female peers condemn 'misogynistic' attitudes in Lord Lester debate", "2018-11-16T18:50:43Z", "https://www.theguardian.com/politics/2018/nov/16/female-peers-condemn-misogynistic-attitudes-in-lord-lester-debate"));
        mTestArray.add(new NewsStory("Female peers condemn 'misogynistic' attitudes in Lord Lester debate", "2018-11-16T18:50:43Z", "https://www.theguardian.com/politics/2018/nov/16/female-peers-condemn-misogynistic-attitudes-in-lord-lester-debate"));
        mTestArray.add(new NewsStory("Female peers condemn 'misogynistic' attitudes in Lord Lester debate", "2018-11-16T18:50:43Z", "https://www.theguardian.com/politics/2018/nov/16/female-peers-condemn-misogynistic-attitudes-in-lord-lester-debate"));
        mTestArray.add(new NewsStory("Female peers condemn 'misogynistic' attitudes in Lord Lester debate", "2018-11-16T18:50:43Z", "https://www.theguardian.com/politics/2018/nov/16/female-peers-condemn-misogynistic-attitudes-in-lord-lester-debate"));
        mTestArray.add(new NewsStory("Female peers condemn 'misogynistic' attitudes in Lord Lester debate", "2018-11-16T18:50:43Z", "https://www.theguardian.com/politics/2018/nov/16/female-peers-condemn-misogynistic-attitudes-in-lord-lester-debate"));

        //Logic to Setup RecyclerView
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mResultsRecycler.setLayoutManager(layoutManager);

        mNewsStoriesAdapter = new NewsStoriesAdapter(this);

        mResultsRecycler.setAdapter(mNewsStoriesAdapter);

        mNewsStoriesAdapter.setNewsStories(mTestArray);


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
                Intent prefIntent = new Intent(MainActivity.this, PreferencesActivity.class);
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
        Intent uriIntent = new Intent(Intent.ACTION_VIEW,uri);
        if (uriIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(uriIntent);
        }
    }
}
