package com.kovrizhkin.rssretrofit2.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kovrizhkin.rssretrofit2.R;
import com.kovrizhkin.rssretrofit2.model.RealmPostModel;

import java.util.List;

import io.realm.RealmChangeListener;
import io.realm.RealmResults;

/**
 * Created by Kovrizhkin V.A. on 26.08.2017.
 */

public class FavoritesPostsAdapter extends RecyclerView.Adapter<FavoritesPostsAdapter.ViewHolder> implements RealmChangeListener {

    private RealmResults<RealmPostModel> postsList;

    public FavoritesPostsAdapter(RealmResults<RealmPostModel> posts){
        postsList = posts;
        postsList.addChangeListener(this);

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView text;
        TextView link;

        public ViewHolder(View itemView) {
            super(itemView);

            text = itemView.findViewById(R.id.favorite_text);
            link = itemView.findViewById(R.id.favorite_link);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.favorite_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FavoritesPostsAdapter.ViewHolder holder, int position) {

        holder.text.setText(postsList.get(position).getText());
        holder.link.setText(postsList.get(position).getLink());
    }

    @Override
    public int getItemCount() {
        if (postsList.size() > 0) {
            return postsList.size();
        } else {
            return 0;
        }

    }

    @Override
    public void onChange() {
        notifyDataSetChanged();
    }
}
