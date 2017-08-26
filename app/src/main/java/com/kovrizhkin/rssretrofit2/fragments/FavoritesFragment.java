package com.kovrizhkin.rssretrofit2.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kovrizhkin.rssretrofit2.R;
import com.kovrizhkin.rssretrofit2.adapters.FavoritesPostsAdapter;
import com.kovrizhkin.rssretrofit2.model.RealmPostModel;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoritesFragment extends Fragment {

    private RecyclerView recyclerView;

    private RealmResults<RealmPostModel> postsList;

    private Realm realm;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favorites, container, false);
        recyclerView = view.findViewById(R.id.favorite_recycler_view);
        realm = Realm.getInstance(getContext());
        postsList = realm.allObjects(RealmPostModel.class);
        FavoritesPostsAdapter favoritesPostsAdapter = new FavoritesPostsAdapter(postsList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(favoritesPostsAdapter);

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        realm.close();
    }
}
