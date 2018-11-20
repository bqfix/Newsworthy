package com.example.android.newsworthy;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NewsStory {
    private final String LOG_TAG = getClass().getSimpleName();

    private String mTitle;
    private String mDate;
    private String mFormattedDate;
    private String mUrlString;
    private String mSection;
    private String mAuthor;

    public NewsStory(String title, String date, String urlString, String section, String author) {
        this.mTitle = title;
        this.mDate = date;
        this.mUrlString = urlString;
        this.mSection = section;
        this.mAuthor = author;

        //Automatically format date
        try {
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
            SimpleDateFormat outputFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm a");

            Date inputDate = inputFormat.parse(mDate);
            mFormattedDate = outputFormat.format(inputDate);
        } catch (ParseException exception) {
            mFormattedDate = null;
            Log.e(LOG_TAG, exception.getMessage());
        }
    }

    public String getTitle() {
        return mTitle;
    }

    public String getFormattedDate() {
        return mFormattedDate;
    }

    public String getUrlString() {
        return mUrlString;
    }

    public String getSection() {
        return mSection;
    }

    public String getAuthor() {
        return mAuthor;
    }
}
