package com.example.apple.githubuserslist.activity.activity.activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.apple.githubuserslist.R;
import com.example.apple.githubuserslist.activity.activity.adapters.UserViewAdapter;
import com.example.apple.githubuserslist.activity.activity.adapters.scrollView.CustomScroll;
import com.example.apple.githubuserslist.activity.activity.client.ApiManager;
import com.example.apple.githubuserslist.activity.activity.fragment.UserInfoFragment;
import com.example.apple.githubuserslist.activity.activity.networkUtils.NetworkUtils;
import com.example.apple.githubuserslist.activity.activity.user.User;
import com.example.apple.githubuserslist.activity.activity.user.UserList;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private UserViewAdapter userViewAdapter;
    private List<UserList> users;
    private static final int LIMIT = 10;
    private UserViewAdapter.OnItemSelectedListener onItemSelected = new UserViewAdapter.OnItemSelectedListener() {
        @Override
        public void onItemSelected(String user) {
            UserInfoFragment fragment = new UserInfoFragment();
            Bundle args = new Bundle();
            args.putString(UserInfoFragment.ARG_INTENT, user);
            fragment.setArguments(args);
            FragmentTransaction fragmentManager = getSupportFragmentManager().beginTransaction();
            fragmentManager.replace(R.id.placeHolder, fragment);
            fragmentManager.addToBackStack(null);
            fragmentManager.commit();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        users = new ArrayList<>();
        userViewAdapter = new UserViewAdapter();
        userViewAdapter.setData(users);
        userViewAdapter.setOnItemSelected(onItemSelected);
        recyclerView = findViewById(R.id.userRecycleView);
        recyclerView.setAdapter(userViewAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setEnabled(false);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        CustomScroll scrollListener = new CustomScroll(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                loadDataFromApi(totalItemsCount);
            }

        };
        loadDataFromApi(0);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addOnScrollListener(scrollListener);
    }

    private void loadDataFromApi(int page) {
        if (NetworkUtils.isNetworkAvailable(this)) {
            Call<List<UserList>> call = ApiManager.getApiClient().getUsers(page, LIMIT);
            call.enqueue(new Callback<List<UserList>>() {
                @Override
                public void onResponse(Call<List<UserList>> call, Response<List<UserList>> response) {
                    Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG).show();
                    List<UserList> userList = response.body();
                    if (userList != null) {
                        users.addAll(userList);
                        userViewAdapter.notifyDataSetChanged();
                    }
                }

                @Override
                public void onFailure(Call<List<UserList>> call, Throwable t) {
                    Log.v("TAG", "Failure : " + t.toString());
                }
            });
        } else {
            Toast.makeText(getApplicationContext(), "No Network connection", Toast.LENGTH_LONG).show();
        }
    }

}
