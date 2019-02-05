package com.example.apple.githubuserslist.activity.activity.adapters.userViewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.apple.githubuserslist.R;

public class UserViewHolder extends RecyclerView.ViewHolder {

    public ImageView userImage;
    private TextView fullName, userName;
    private OnItemClickListener onItemClickListener;

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(onItemClickListener != null){
                onItemClickListener.onItemClick(getAdapterPosition());
            }
        }
    };

    public UserViewHolder(@NonNull View itemView) {
        super(itemView);
        userImage = itemView.findViewById(R.id.userImage);
        fullName = itemView.findViewById(R.id.userFullName);
        userName = itemView.findViewById(R.id.userUserName);
        itemView.setOnClickListener(onClickListener);
    }

    public ImageView getUserImage() { return userImage; }

    public TextView getFullName() { return fullName; }

    public TextView getUserName() { return userName; }


    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(int adapter);
    }
}
