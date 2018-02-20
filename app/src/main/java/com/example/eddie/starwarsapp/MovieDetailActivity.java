package com.example.eddie.starwarsapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by eddie on 2/17/18.
 */


public class MovieDetailActivity extends AppCompatActivity {

    public TextView titleTextView;
    public TextView descriptionTextView;
    public ImageView thumbnailImageView;
    RadioGroup radioGroup;
    RadioButton radioButton;

    String movieTitleFromIntent;
    Movie theMovie;

    // override onCreate method
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        if (!getIntent().hasExtra("title")) finish();

        movieTitleFromIntent = getIntent().getStringExtra("title");

        final ArrayList<Movie> MovieList = Movie.getMoviesFromFile("Movies.json", this);

        for (Movie m : MovieList) {
            if (m.title.equals(movieTitleFromIntent)) {
                theMovie = m;
                break;
            }
        }

        if (theMovie == null) finish();

        titleTextView = findViewById(R.id.movie_list_title2);
        descriptionTextView = findViewById(R.id.movie_list_description2);
        thumbnailImageView = findViewById(R.id.movie_list_thumbnail2);

        titleTextView.setText(theMovie.title);
        descriptionTextView.setText(theMovie.description);
        Picasso.with(this).load(theMovie.imageUrl).into(thumbnailImageView);
        titleTextView.setTextSize(35);

        radioGroup = findViewById(R.id.radio_group);

//        final TextView TV = (TextView) findViewById(R.id.has_seen);
        Button buttonSend = (Button) findViewById(R.id.submit_backbutton);


        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int radioId = radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(radioId);

                String Result = radioButton.getText().toString();
                System.out.println(Result);

                Intent intent = new Intent();
                intent.putExtra("Opinion", Result);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}

