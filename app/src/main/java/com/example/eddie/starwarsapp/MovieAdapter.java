package com.example.eddie.starwarsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by eddie on 2/17/18.
 */


public class MovieAdapter extends BaseAdapter {


    // adapter takes the app itself and a list of data to display
    private Context mContext;
    private ArrayList<Movie> mMovieList;
    private LayoutInflater mInflater;

    // constructor
    public MovieAdapter(Context mContext, ArrayList<Movie> mMoviesList) {

        // initialize instances variables
        this.mContext = mContext;
        this.mMovieList = mMoviesList;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    // methods
    // a list of methods we need to override

    // gives you the number of recipes in the data source
    @Override
    public int getCount() {
        return mMovieList.size();
    }

    // returns the item at specific position in the data source

    @Override
    public Object getItem(int position) {
        return mMovieList.get(position);
    }

    // returns the row id associated with the specific position in the list
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;


        // check if the view already exists
        // if yes, you don't need to inflate and findViewbyID again
        if (convertView == null) {
            // inflate
            convertView = mInflater.inflate(R.layout.list_item_movie, parent, false);

            // add the views to the holder
            holder = new ViewHolder();

            //  ViewHolder holder = new ViewHolder((TextView)v);

            // views
            //assert holder != null;
            holder.titleTextView = (TextView) convertView.findViewById(R.id.movie_list_title);
            holder.descriptionTextView = (TextView) convertView.findViewById(R.id.movie_list_description);
            holder.thumbnailImageView = (ImageView) convertView.findViewById(R.id.movie_list_thumbnail);
            holder.mainCharacterTextView = (TextView) convertView.findViewById(R.id.movie_list_characters);
            holder.hasSeenTextView = convertView.findViewById(R.id.has_seen);
            // add the holder to the view
            // for future use
            convertView.setTag(holder);
        } else {
            // get the view holder from converview
            holder = (ViewHolder) convertView.getTag();
        }
        // get relavate subview of the row view
        TextView titleTextView = (TextView) holder.titleTextView;
        TextView descriptionTextView = (TextView) holder.descriptionTextView;
        ImageView thumbnailImageView = (ImageView) holder.thumbnailImageView;
        TextView mainCharacterTextView = (TextView) holder.mainCharacterTextView;

        // get corresonpinding recipe for each row
        Movie movie = (Movie) getItem(position);


        // update the row view's textviews and imageview to display the information

        // titleTextView
        titleTextView.setText(movie.title);

        // episodeTextView
        descriptionTextView.setText("Description: " + movie.description);

        // mainCharacterTextView
        StringBuilder Character = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            Character.append(movie.main_characters.get(i));

            if(i < 2) Character.append(", ");
        }

        mainCharacterTextView.setText(Character.toString());

        String hasSeenText = movie.alreadyseen != null ? movie.alreadyseen : "Has seen?";
        holder.hasSeenTextView.setText(hasSeenText);
        // imageView
        // use Picasso library to load image from the image url
        Picasso.with(mContext).load(movie.imageUrl).into(thumbnailImageView);
        return convertView;
    }


    public class ViewHolder {

        public TextView titleTextView;
        public TextView descriptionTextView;
        public ImageView thumbnailImageView;
        public TextView mainCharacterTextView;
        public TextView hasSeenTextView;

        //titleTextView
        //episodeTextView
        //thumbnailImageView

    }


// intent is used to pass information between activities
    // intent -> pacakge
    // sender, receiver

}