package com.example.apple.githubuserslist.activity.activity.client;

import com.example.apple.githubuserslist.activity.activity.user.User;
import com.example.apple.githubuserslist.activity.activity.user.UserList;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GitHubService {
    @GET("users")
    Call<List<UserList>> getUsers(@Query("since") int offset, @Query("per_page") int limit);

    @GET("users/{userName}")
    Call<User> getSingleUser(@Path("userName") String name);
//
//    @GET("users/{userName/repos}")
//    Call<List> getRepos(@Path("userName/repos") String l);
}
