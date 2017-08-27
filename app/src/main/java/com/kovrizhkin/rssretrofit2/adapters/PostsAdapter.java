package com.kovrizhkin.rssretrofit2.adapters;

/**
 * Created by Kovrizhkin V.A. on 20.08.2017.
 */

import android.content.Context;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kovrizhkin.rssretrofit2.R;
import com.kovrizhkin.rssretrofit2.model.PostModel;
import com.kovrizhkin.rssretrofit2.model.RealmPostModel;

import java.util.List;

import io.realm.Realm;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder> {

    private List<PostModel> posts;

    private Realm realm;

    public PostsAdapter(List<PostModel> posts, Context context) {
        realm = Realm.getInstance(context);
        this.posts = posts;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        PostModel post = posts.get(position);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            holder.post.setText(Html.fromHtml(post.getElementPureHtml(), Html.FROM_HTML_MODE_LEGACY));
        } else {
            holder.post.setText(Html.fromHtml(post.getElementPureHtml()));
        }
        holder.site.setText(post.getSite());

        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                realm.beginTransaction();
                RealmPostModel realmPostModel = realm.createObject(RealmPostModel.class);
                realmPostModel.setText(posts.get(position).getElementPureHtml());
                realmPostModel.setLink(posts.get(position).getLink());
                realmPostModel.setSite(posts.get(position).getSite());
                realm.commitTransaction();
            }
        });
    }

    @Override
    public int getItemCount() {
        if (posts == null)
            return 0;
        return posts.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView post;

        TextView site;

        View container;

        public ViewHolder(View itemView) {
            super(itemView);
            post = itemView.findViewById(R.id.postitem_post);
            site = itemView.findViewById(R.id.postitem_site);
            container = itemView.findViewById(R.id.post_item_layout);
        }
    }
}
