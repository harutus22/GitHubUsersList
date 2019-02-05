package com.example.apple.githubuserslist.activity.activity.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("avatar_url")
    @Expose
    private String image;
    @SerializedName("name")
    @Expose
    private String fullName;
    @SerializedName("login")
    @Expose
    private String userName;
    @SerializedName("location")
    @Expose
    private String residence;
    @SerializedName("public_repos")
    @Expose
    private int reposCount;
    @SerializedName("followers")
    @Expose
    private int followersCount;
    @SerializedName("html_url")
    @Expose private String url;

    public User() {
    }


    public String getImage() {
        return image;
    }

    public String getFullName() {
        return fullName;
    }

    public String getUserName() {
        return userName;
    }

    public String getResidence() {
        return residence;
    }

    public int getReposCount() {
        return reposCount;
    }


    public int getFollowersCount() {
        return followersCount;
    }

    public String getUrl() {
        return url;
    }
}
