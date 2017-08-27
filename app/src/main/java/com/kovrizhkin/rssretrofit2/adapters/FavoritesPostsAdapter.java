package com.kovrizhkin.rssretrofit2.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kovrizhkin.rssretrofit2.R;
import com.kovrizhkin.rssretrofit2.model.RealmPostModel;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

/**
 * Created by Kovrizhkin V.A. on 26.08.2017.
 */

public class FavoritesPostsAdapter extends RecyclerView.Adapter<FavoritesPostsAdapter.ViewHolder> implements RealmChangeListener {

    private RealmResults<RealmPostModel> postsList;

    private Realm realm;

    // private Context context;

    public FavoritesPostsAdapter(RealmResults<RealmPostModel> posts, Context context) {
        postsList = posts;
        postsList.addChangeListener(this);
        //this.context = context;
        realm = Realm.getInstance(context);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView text;
        TextView link;
        View container;

        public ViewHolder(View itemView) {
            super(itemView);

            text = itemView.findViewById(R.id.favorite_text);
            link = itemView.findViewById(R.id.favorite_link);
            container = itemView.findViewById(R.id.favorite_item_layout);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.favorite_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FavoritesPostsAdapter.ViewHolder holder, final int position) {

        holder.text.setText(postsList.get(position).getText());
        holder.link.setText(postsList.get(position).getLink());

        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                realm.beginTransaction();
                RealmResults<RealmPostModel> forRemoving = realm.where(RealmPostModel.class)
                        .equalTo("link", postsList.get(position).getLink())
                        .findAll();
                if(!forRemoving.isEmpty()){
                    for (int i = forRemoving.size()-1; i>=0; i--) {
                        forRemoving.get(i).removeFromRealm();
                    }
                }
                realm.commitTransaction();
            }
        });
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
