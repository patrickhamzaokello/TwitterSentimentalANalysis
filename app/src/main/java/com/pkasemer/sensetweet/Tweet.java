package com.pkasemer.sensetweet;

public class Tweet {

    private String id_str;
    private String text;
    private double polarity;
    private double subjectivity;
    private String username;
    private String name;
    private String profile_image_url;
    private String received_at;


    public Tweet(String id_str, String text, double polarity, double subjectivity, String username, String name, String profile_image_url, String received_at){
        this.id_str = id_str;
        this.text = text;
        this.polarity = polarity;
        this.subjectivity = subjectivity;
        this.username = username;
        this.name = name;
        this.profile_image_url = profile_image_url;
        this.received_at = received_at;
    }

    public String getId_str() {
        return id_str;
    }

    public String getText() {
        return text;
    }

    public double getPolarity() {
        return polarity;
    }

    public double getSubjectivity() {
        return subjectivity;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getProfile_image_url() {
        return profile_image_url;
    }

    public String getReceived_at() {
        return received_at;
    }


    public void setId_str(String id_str) {
        this.id_str = id_str;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setPolarity(double polarity) {
        this.polarity = polarity;
    }

    public void setSubjectivity(double subjectivity) {
        this.subjectivity = subjectivity;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProfile_image_url(String profile_image_url) {
        this.profile_image_url = profile_image_url;
    }

    public void setReceived_at(String received_at) {
        this.received_at = received_at;
    }
}
