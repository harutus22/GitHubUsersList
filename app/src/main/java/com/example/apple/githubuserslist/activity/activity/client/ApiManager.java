package com.example.apple.githubuserslist.activity.activity.client;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiManager {

    private final static String BASE_URL = "https://api.github.com/";
    private static GitHubService gitHubService;

    public static GitHubService getApiClient() {
        if (gitHubService == null) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder().addInterceptor(logging);
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build();

            gitHubService = retrofit.create(GitHubService.class);
        }
        return gitHubService;
    }
}
