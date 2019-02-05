package com.example.apple.githubuserslist.activity.activity.user;

public class UserList {
    private String login, gravatar_id, avatar_url;

    public UserList() {
    }

    public String getUsername() { return login; }

    public String imageUrl() { return avatar_url; }

    public String getFullName() { return gravatar_id; }

}
