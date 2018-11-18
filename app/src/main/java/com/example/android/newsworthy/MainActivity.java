package com.example.android.newsworthy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //Temporary toast for testing clicks
    Toast mTestToast = null;

    //View member variables
    LinearLayout mSearchLayout;
    EditText mSearchText;
    Button mExecuteSearch;
    RecyclerView mResultsRecycler;
    ProgressBar mProgressIndicator;
    TextView mErrorText;


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
                //Search EditText and button should be invisible until this button is clicked
                mSearchLayout.setVisibility(View.VISIBLE);
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
}
