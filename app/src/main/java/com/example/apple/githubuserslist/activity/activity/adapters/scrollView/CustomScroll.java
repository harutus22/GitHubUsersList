package com.example.apple.githubuserslist.activity.activity.adapters.scrollView;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public abstract class CustomScroll extends RecyclerView.OnScrollListener {

    private final int VISIBLE_USERS= 10;
    private int currentPage = 0;
    private int previousTotalItemCount = 0;
    private boolean loading = true;

    private LinearLayoutManager mLayoutManager;

    public CustomScroll(LinearLayoutManager layoutManager) {
        this.mLayoutManager = layoutManager;
    }

    @Override
    public void onScrolled(@NonNull RecyclerView view, int dx, int dy) {
        int lastVisibleItemPosition = 0;
        int totalItemCount = mLayoutManager.getItemCount();

        lastVisibleItemPosition = mLayoutManager.findLastVisibleItemPosition();


        if (loading && (totalItemCount > previousTotalItemCount)) {
            loading = false;
            previousTotalItemCount = totalItemCount;
        }

        if (!loading && (lastVisibleItemPosition + VISIBLE_USERS) > totalItemCount) {
            currentPage++;
            onLoadMore(currentPage, totalItemCount, view);
            loading = true;
        }
    }

    public abstract void onLoadMore(int page, int totalItemsCount, RecyclerView view);
}
