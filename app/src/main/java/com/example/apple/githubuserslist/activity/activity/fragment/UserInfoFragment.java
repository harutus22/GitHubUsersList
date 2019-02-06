package com.example.apple.githubuserslist.activity.activity.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.apple.githubuserslist.R;
import com.example.apple.githubuserslist.activity.activity.client.ApiManager;
import com.example.apple.githubuserslist.activity.activity.networkUtils.NetworkUtils;
import com.example.apple.githubuserslist.activity.activity.user.Repo;
import com.example.apple.githubuserslist.activity.activity.user.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserInfoFragment extends Fragment {

    public static final String ARG_INTENT = "KEY";
    private View view;
    private ImageView image, externLink;
    private TextView userName, fullName, residence, followersCount, reposCount;
    private ListView listView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.user_info_fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        String user = getArguments().getString(ARG_INTENT);
        init(view);
        downLoadInfo(user);
        downloadRepo(user);
    }

    private void downLoadInfo(String user) {
        if (NetworkUtils.isNetworkAvailable(getContext())) {
            Call<User> call = ApiManager.getApiClient().getSingleUser(user);
            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    User user = response.body();
                    if (user != null) {
                        setUser(user);
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Toast.makeText(getContext(),"Connection Failed", Toast.LENGTH_LONG).show();
                }
            });
        } else {
            Toast.makeText(getContext(),"No Connection", Toast.LENGTH_LONG).show();
        }
    }

    private void init(View view) {
        userName = view.findViewById(R.id.fragment_username);
        fullName = view.findViewById(R.id.fragment_full_name);
        image = view.findViewById(R.id.fragment_image);
        followersCount = view.findViewById(R.id.fragment_followers_count);
        reposCount = view.findViewById(R.id.fragment_repo_count);
        externLink = view.findViewById(R.id.linkToGit);
        listView = view.findViewById(R.id.listView);
    }

    private void setUser(User user){
        Glide.with(getContext()).load(user.getImage()).into(image);
        userName.setText(user.getUserName());
        fullName.setText(user.getFullName());
        followersCount.setText(String.valueOf(user.getFollowersCount()));
        reposCount.setText(String.valueOf(user.getReposCount()));
        final String url = user.getUrl();
        externLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WebViewFragment fragment = new WebViewFragment();
                Bundle args = new Bundle();
                args.putString(WebViewFragment.ARG_BUNDLE, url);
                fragment.setArguments(args);
                FragmentTransaction fragmentManager = getFragmentManager().beginTransaction();
                fragmentManager.replace(R.id.placeHolder, fragment);
                fragmentManager.addToBackStack(null);
                fragmentManager.commit();
            }
        });
    }

    private void downloadRepo(String user){
        if(NetworkUtils.isNetworkAvailable(getContext())){
            Call<List<Repo>> call = ApiManager.getApiClient().getRepos(user);
            call.enqueue(new Callback<List<Repo>>() {
                @Override
                public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
                    final ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(),
                            R.layout.repo_holder, R.id.listTextView);
                    List<Repo> repos = response.body();
                    if(repos != null ){
                        for(Repo repo: repos){
                            adapter.add(repo.getName());
                        }
                        listView.setAdapter(adapter);
                    }
                }

                @Override
                public void onFailure(Call<List<Repo>> call, Throwable t) {
                    Toast.makeText(getContext(),"Connection Failed", Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}
