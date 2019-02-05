package com.example.apple.githubuserslist.activity.activity.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.apple.githubuserslist.R;
import com.example.apple.githubuserslist.activity.activity.adapters.userViewHolder.UserViewHolder;
import com.example.apple.githubuserslist.activity.activity.user.UserList;

import java.util.List;

public class UserViewAdapter extends RecyclerView.Adapter<UserViewHolder> {

    private List<UserList> data;
    private OnItemSelectedListener onItemSelected;
    private UserViewHolder.OnItemClickListener onItemClickListener = new UserViewHolder.OnItemClickListener() {
        @Override
        public void onItemClick(int adapter) {
            if(onItemSelected != null){
                onItemSelected.onItemSelected(data.get(adapter).getUsername());
            }
        }
    };

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        UserViewHolder userViewHolder = new UserViewHolder(LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.user_view_holder, viewGroup, false));
        userViewHolder.setOnItemClickListener(onItemClickListener);
        return userViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder userViewHolder, int i) {
        UserList user = data.get(i);
        Glide.with(userViewHolder.itemView).load(user.imageUrl()).into(userViewHolder.getUserImage());
        userViewHolder.getUserName().setText(user.getUsername());
        userViewHolder.getFullName().setText(user.getFullName());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setOnItemSelected(OnItemSelectedListener onItemSelected) {
        this.onItemSelected = onItemSelected;
    }

    public interface OnItemSelectedListener{
        void onItemSelected(String user);
    }

    public void setData(List<UserList> data) {
        this.data = data;
    }
}
