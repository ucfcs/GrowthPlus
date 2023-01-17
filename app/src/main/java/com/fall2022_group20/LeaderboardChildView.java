package com.fall2022_group20;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class LeaderboardChildView extends RecyclerView.ViewHolder {
    ImageView avatarView;
    TextView nameView, pointsView;

    public LeaderboardChildView(@NonNull View itemView) {
        super(itemView);
        avatarView = itemView.findViewById(R.id.leaderboardAvatarImg);
        nameView = itemView.findViewById(R.id.leaderboardName);
        pointsView = itemView.findViewById(R.id.leaderboardPoints);
    }
}
