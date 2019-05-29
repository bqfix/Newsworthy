package com.example.android.newsworthy;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;

import java.net.URL;
import java.util.List;

public class NewsStoriesLoader extends AsyncTaskLoader<List<NewsStory>> {

    private String mUrlString;
    private OnStartLoadingCallback mOnStartLoadingCallback;

    public NewsStoriesLoader(@NonNull Context context, String urlString, OnStartLoadingCallback onStartLoadingCallback) {
        super(context);
        mUrlString = urlString;
        mOnStartLoadingCallback = onStartLoadingCallback;
    }

    @Override
    protected void onStartLoading() {
        mOnStartLoadingCallback.onStartLoading();
        forceLoad();
    }

    @Nullable
    @Override
    public List<NewsStory> loadInBackground() {
        if (mUrlString == null || mUrlString.length() < 1) {
            return null;
        }
        URL url = NetworkUtils.buildURL(mUrlString);
        String jsonResponse = NetworkUtils.makeHttpRequest(url);
        List<NewsStory> newsStories = NetworkUtils.extractStories(jsonResponse);

        return newsStories;
    }

    @Override
    public void deliverResult(@Nullable List<NewsStory> data) {

        super.deliverResult(data);
    }

    public interface OnStartLoadingCallback{
        void onStartLoading();
    }
}