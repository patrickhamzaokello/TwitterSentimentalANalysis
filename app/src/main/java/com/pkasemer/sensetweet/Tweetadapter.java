package com.pkasemer.sensetweet;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

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

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            profile_image_urlView = itemView.findViewById(R.id.tweeterimage);
            nameView = itemView.findViewById(R.id.name);
            usernameView = itemView.findViewById(R.id.username);
            textView = itemView.findViewById(R.id.text_message);
            subjectivityView = itemView.findViewById(R.id.subjectivity_value);
            polarityView = itemView.findViewById(R.id.polarity_value);
            received_atView = itemView.findViewById(R.id.datevalue);


        }

        public void setData(View view, String id_str, String text, double polarity, double subjectivity, String username, String name, String profile_image_url, String received_at) {
            profile_image_urlView.setImageResource(R.drawable.ic_launcher_background);
            nameView.setText(name);
            usernameView.setText(username);
            textView.setText(text);
            subjectivityView.setText(String.valueOf(subjectivity));
            polarityView.setText(String.valueOf(polarity));
            received_atView.setText(received_at);

            if(subjectivity > 0){
                subjectivityView.setTextColor(ContextCompat.getColor(view.getContext(),R.color.negative));
            }



        }
    }
}
