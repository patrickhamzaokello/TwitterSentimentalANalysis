package com.pkasemer.sensetweet;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Tweetadapter extends RecyclerView.Adapter<Tweetadapter.ViewHolder> {

    private List<Tweet> tweetList;

    public  Tweetadapter(List<Tweet>tweetList){
        this.tweetList = tweetList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tweet_design,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String id_str = tweetList.get(position).getId_str();
        String text = tweetList.get(position).getText();
        double polarity = tweetList.get(position).getPolarity();
        double subjectivity = tweetList.get(position).getSubjectivity();
        String username = tweetList.get(position).getUsername();
        String name = tweetList.get(position).getName();
        String profile_image_url = tweetList.get(position).getProfile_image_url();
        String received_at = tweetList.get(position).getReceived_at();

        holder.setData(holder.itemView,id_str, text, polarity, subjectivity, username, name, profile_image_url, received_at);
    }

    @Override
    public int getItemCount() {
        return tweetList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView id_strView;
        private TextView textView;
        private TextView polarityView;
        private TextView subjectivityView;
        private TextView usernameView;
        private TextView nameView;
        private ImageView profile_image_urlView;
        private TextView received_atView;
        private ImageView likedTweeticon, sharetweetView;
        private CardView polaritystatusview;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            profile_image_urlView = itemView.findViewById(R.id.tweeterimage);
            nameView = itemView.findViewById(R.id.name);
            usernameView = itemView.findViewById(R.id.username);
            textView = itemView.findViewById(R.id.text_message);
            subjectivityView = itemView.findViewById(R.id.subjectivity_value);
            polarityView = itemView.findViewById(R.id.polarity_value);
            received_atView = itemView.findViewById(R.id.datevalue);
            likedTweeticon = itemView.findViewById(R.id.likedtweet);
            sharetweetView = itemView.findViewById(R.id.sharetweet);
            polaritystatusview = itemView.findViewById(R.id.polaritystatus);


        }

        public void setData(View view, String id_str, String text, double polarity, double subjectivity, String username, String name, String profile_image_url, String received_at) {
//            profile_image_urlView.setImageResource(R.drawable.ic_launcher_background);
            Picasso.get().load(profile_image_url).into(profile_image_urlView);
            nameView.setText(name);
            usernameView.setText(username);
            textView.setText(text);
            subjectivityView.setText(String.valueOf(subjectivity));
            polarityView.setText(String.valueOf(polarity));
            received_atView.setText(received_at);

            SenseDBHelper  db = new SenseDBHelper(view.getContext());

            if(db.checktweetindb(id_str)){
                likedTweeticon.setImageResource(R.drawable.ic_baseline_favorite_border_24);

            } else {

                likedTweeticon.setImageResource(R.drawable.ic_baseline_favorite_liked);

            }


            likedTweeticon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SenseDBHelper  db = new SenseDBHelper(v.getContext());
                    boolean record = db.checktweetindb(id_str);
                    if(record){
                        db.addTweet(id_str,text, polarity,subjectivity,username,name, profile_image_url, received_at);
                        likedTweeticon.setImageResource(R.drawable.ic_baseline_favorite_liked);
                        Toast.makeText(v.getContext(),
                                "Tweet Saved",
                                Toast.LENGTH_SHORT).show();
                    } else {
                        db.deleteTweet(id_str);
                        likedTweeticon.setImageResource(R.drawable.ic_baseline_favorite_border_24);
                        Toast.makeText(v.getContext(),
                                "Tweet Deleted",
                                Toast.LENGTH_SHORT).show();

                    }

                }
            });


            sharetweetView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {

                    Toast.makeText(v.getContext(),
                             "Tweet Shared!",
                            Toast.LENGTH_SHORT).show();


                }
            });



            if(polarity > 0){
                //positive
                polarityView.setTextColor(ContextCompat.getColor(view.getContext(),R.color.positive));
                polaritystatusview.setCardBackgroundColor(ContextCompat.getColor(view.getContext(), R.color.positive));

            } if(polarity < 0){
                //negative
                polarityView.setTextColor(ContextCompat.getColor(view.getContext(),R.color.negative));
                polaritystatusview.setCardBackgroundColor(ContextCompat.getColor(view.getContext(), R.color.negative));

            } if (polarity == 0){
                // neutral
                polarityView.setTextColor(ContextCompat.getColor(view.getContext(),R.color.neutral));
                polaritystatusview.setCardBackgroundColor(ContextCompat.getColor(view.getContext(), R.color.neutral));

            }



        }

    }
}
