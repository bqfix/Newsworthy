package com.example.android.newsworthy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.view.View;
import android.widget.TextView;

public class PreferencesActivity extends AppCompatActivity {

    //Member Variables
    TextView mAboutText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);

        //Set views
        mAboutText = (TextView) findViewById(R.id.about_text_view);

        //Set logic to go to About page
        mAboutText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent aboutIntent = new Intent(PreferencesActivity.this, AboutActivity.class);
                startActivity(aboutIntent);
            }
        });

    }


}
