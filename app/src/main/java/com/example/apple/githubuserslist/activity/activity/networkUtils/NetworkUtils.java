package com.example.apple.githubuserslist.activity.activity.networkUtils;

import android.content.Context;
import android.net.ConnectivityManager;

public class NetworkUtils {

    public NetworkUtils() {
    }

    public static boolean isNetworkAvailable(Context context) {
        final ConnectivityManager connectivityManager =
                ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() !=
                null && connectivityManager.getActiveNetworkInfo().isConnected();
    }
}
