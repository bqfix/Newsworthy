package com.example.android.newsworthy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //Temporary toast for testing clicks
    Toast mTestToast = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


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
                //TODO Add proper logic for buttons

                if (mTestToast != null) {
                    mTestToast.cancel();
                }
                mTestToast = Toast.makeText(this, "Search", Toast.LENGTH_SHORT);
                mTestToast.show();
                return true;
            case (R.id.action_preferences):
                Intent prefIntent = new Intent(MainActivity.this, PreferencesActivity.class);
                startActivity(prefIntent);
                return true;

        }

        //If no match, let the system handle the click with default behavior
        return super.onOptionsItemSelected(item);
    }
}
