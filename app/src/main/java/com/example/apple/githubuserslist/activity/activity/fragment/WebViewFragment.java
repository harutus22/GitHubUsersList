package com.example.apple.githubuserslist.activity.activity.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.example.apple.githubuserslist.R;

public class WebViewFragment extends Fragment {

    public static final String ARG_BUNDLE = "KEY_2";
    private View view;
    private WebView webView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return view = inflater.inflate(R.layout.web_view_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        String url = getArguments().getString(ARG_BUNDLE);
        webView = view.findViewById(R.id.webView);
        webView.loadUrl(url);

    }
}
