package com.example.eddie.starwarsapp;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by eddie on 2/17/18.
 */

public class Movie {


    // instance variables or fields
    public String title;
    public String imageUrl;
    public ArrayList<String> main_characters;
    public String description;
    public String instructionUrl;
    public int episode_number;
    public String alreadyseen;

    // constructor
    // default

    // method
    // static methods that read the json file in and load into Recipe

    // static method that loads our recipes.json using the helper method
    // this method will return an array list of recipes constructed from the JSON
    // file
    public static ArrayList<Movie> getMoviesFromFile(String filename, Context context) {
        ArrayList<Movie> MovieList = new ArrayList<Movie>();


        // try to read from JSON file
        // get information by using the tags
        // construct a Recipe Object for each recipe in JSON
        // add the object to arraylist
        // return arraylist
        try {
            String jsonString = loadJsonFromAsset("Movies.json", context);
            JSONObject json = new JSONObject(jsonString);
            JSONArray Movies = json.getJSONArray("Movies");


            // for loop to go through each recipe in your movie array

            for (int i = 0; i < Movies.length(); i++) {
                JSONObject movieJson = Movies.getJSONObject(i);

                Movie movie = new Movie();
                movie.title = movieJson.getString("title");
                movie.description = movieJson.getString("description");
                movie.imageUrl = movieJson.getString("poster");
                JSONArray peeps = movieJson.getJSONArray("main_characters");
                ArrayList<String>Characters = new ArrayList<>();
                for (int j = 0; j < peeps.length(); j++) {
                    Characters.add(peeps.getString(j));
                }
                movie.main_characters = Characters;
                movie.instructionUrl = movieJson.getString("url");
                movie.episode_number = movieJson.getInt("episode_number");
                // add to arraylist
                MovieList.add(movie);

            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return MovieList;
    }


    // helper method that loads from any Json file
    private static String loadJsonFromAsset(String filename, Context context) {
        String json = null;


        try {
            InputStream is = context.getAssets().open(filename);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (java.io.IOException ex) {
            ex.printStackTrace();
            return null;
        }

        return json;
    }
}


