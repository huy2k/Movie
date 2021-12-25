package com.example.appp;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


public abstract class ReviewParser extends AsyncTask<String,Void,ArrayList<ReviewData>> {
    public ArrayList<ReviewData> reviewsList = new ArrayList<>();

    @Override
    protected ArrayList<ReviewData> doInBackground(String... params) {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String param_type = params[0];
        final String BASE_URL = "http://api.themoviedb.org/3/movie/"+param_type+"/reviews"+"?api_key=f0d7c86f6eeb4a09849c830fb0d27a46";
        try {
            URL url = new URL(BASE_URL);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.connect();

            InputStreamReader stream = new InputStreamReader(urlConnection.getInputStream());
            reader = new BufferedReader(stream);


            final StringBuilder textBuilder = new StringBuilder();
            String line;
            String finalResult;
            while ((line = reader.readLine()) != null) {
                textBuilder.append(line);
            }
            finalResult = textBuilder.toString();

            JSONObject jsonObject = new JSONObject(finalResult);
            JSONArray jsonArray = jsonObject.getJSONArray("results");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject currentMovie = jsonArray.getJSONObject(i);
                String reviewAuthor = currentMovie.getString("author");
                String reviewContent = currentMovie.getString("content");

                ReviewData reviewData = new ReviewData(reviewAuthor,reviewContent);
                reviewsList.add(reviewData);
            }

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return reviewsList;
    }
    @Override
    protected abstract void onPostExecute(ArrayList<ReviewData> reviewsList);
}
