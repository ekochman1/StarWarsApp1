package com.example.eddie.starwarsapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final int RESULT_ID = 1;

    private ListView mListView;
    private Context mContext;

    MovieAdapter adapter;
    Movie selectedMovie;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;
        // data to display
        final ArrayList<Movie> MovieList = Movie.getMoviesFromFile("Movies.json", this);

        // create the adapter
        adapter = new MovieAdapter(this, MovieList);

        // find the listview in the layout
        // set the adapter to listview
        mListView = findViewById(R.id.movie_list_view);
        mListView.setAdapter(adapter);


        // 1. each row should be clickable
        // when the row is clicked,
        // the intent is created and send

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                selectedMovie = MovieList.get(position);

                // create my intent package
                // add all the information needed for detail page
                // startActivity with that intent

                //explicit
                // from, to
                Intent detailIntent = new Intent(mContext, MovieDetailActivity.class);
                // put title and instruction URL
                detailIntent.putExtra("title", selectedMovie.title);
                // detailIntent.putExtra("url", selectedMovie.instructionUrl);

                startActivityForResult(detailIntent, RESULT_ID);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_ID) {
            if (resultCode == RESULT_OK) {
                selectedMovie.alreadyseen = data.hasExtra("Opinion") ? data.getStringExtra("Opinion") : "Has seen?";

                adapter.notifyDataSetChanged();
            }
        }
    }
}

