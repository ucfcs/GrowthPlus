package com.GrowthPlus;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.GrowthPlus.dataAccessLayer.child.ChildSchema;

import io.realm.RealmList;
import io.realm.RealmResults;

public class LeaderboardAdapter extends RecyclerView.Adapter<LeaderboardChildView> {

    Context context;
    RealmResults<ChildSchema> children;

    public LeaderboardAdapter(Context context, RealmResults<ChildSchema> children) {
        this.context = context;
        this.children = children;
    }

    @NonNull
    @Override
    public LeaderboardChildView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LeaderboardChildView(LayoutInflater.from(context).inflate(R.layout.leaderboard_child_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull LeaderboardChildView holder, int position) {
        holder.nameView.setText(children.get(position).getName());
        holder.pointsView.setText(children.get(position).getReport().getChildScore());
        // TODO: Temporary fix of setting image resource as int, test and refactor later (setImageResource only takes int, we have Avatar set as string)
        int resourceId = Integer.parseInt(children.get(position).getAvatar());
        holder.avatarView.setImageResource(resourceId);
    }

    @Override
    public int getItemCount() {
        return children.size();
    }
}
