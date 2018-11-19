package com.example.android.newsworthy;


import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

/**
 * Class to hold methods for querying the API and parsing the JSON
 */
public final class NetworkUtils {

    private static final String LOG_TAG = NetworkUtils.class.getSimpleName();


    private NetworkUtils() {

    }

    /**
     * Builds the URL
     *
     * @param stringUrl the url to be input
     * @return The URL to query
     */
    public static URL buildURL(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, e.getMessage());
        }

        return url;
    }

    /**
     * Method to make the initial Http request
     *
     * @param url the url to be used (likely generated from buildURL method)
     * @return a string with the full JSON response to be parsed by extractStories method
     */
    public static String makeHttpRequest(URL url) {
        String jsonResponse = "";
        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            //Read data if valid connection was made
            int responseCode = urlConnection.getResponseCode();
            if (responseCode == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + responseCode);
            }
        } catch (IOException exception) {
            Log.e(LOG_TAG, "Could not connect");
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException exception) {
                Log.e(LOG_TAG, "Failed to close input stream");
            }

        }
        return jsonResponse;
    }


    /**
     * Helper method to perform the reading from the input stream
     *
     * @param inputStream provided from the makeHttpRequest method
     * @return a built JSONResponse to be returned by the makeHttpRequest method
     * @throws IOException that is handled by the makeHttpRequest method
     */
    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }

        return output.toString();
    }

    /**
     * Method to parse the given JSON and return a list of NewsStories to be displayed
     * @param stringJSON A string containing all the JSON (generated from makeHttpRequest method)
     * @return A list of all the news stories pulled from the JSON
     */
    public static ArrayList<NewsStory> extractStories(String stringJSON) {
        ArrayList<NewsStory> newsStories = new ArrayList<>();

        try {
            //Get to the actual array of stories
            JSONObject newsStoriesJSON = new JSONObject(stringJSON);
            JSONObject response = newsStoriesJSON.getJSONObject("response");
            JSONArray results = response.getJSONArray("results");

            //Iterate through the array, convert each item to a NewsStory, and add each item to the previous initialized ArrayList
            for (int position = 0; position < results.length(); position++) {
                //Get story
                JSONObject currentNewsStory = results.getJSONObject(position);

                //Get aspects of the story
                String title = currentNewsStory.getString("webTitle");
                String date = currentNewsStory.getString("webPublicationDate");
                String url = currentNewsStory.getString("webUrl");

                //Add a NewsStory using the above parameters
                newsStories.add(new NewsStory(title,date,url));
            }

        } catch (JSONException e) {
            Log.e(LOG_TAG, e.getMessage());
        }
        return newsStories;
    }

}
