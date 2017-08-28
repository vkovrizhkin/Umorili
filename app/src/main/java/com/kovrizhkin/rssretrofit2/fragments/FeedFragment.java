package com.kovrizhkin.rssretrofit2.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.kovrizhkin.rssretrofit2.App;
import com.kovrizhkin.rssretrofit2.R;
import com.kovrizhkin.rssretrofit2.adapters.PostsAdapter;
import com.kovrizhkin.rssretrofit2.model.PostModel;
import com.melnykov.fab.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class FeedFragment extends Fragment {

    private RecyclerView recyclerView;

    private List<PostModel> posts;

    private ImageView imageView;

    private FloatingActionButton fab;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feed, container, false);

        posts = new ArrayList<>();
        imageView = (ImageView) view.findViewById(R.id.wi_fi_off);
        recyclerView = (RecyclerView) view.findViewById(R.id.posts_recycle_view);
        fab = (FloatingActionButton) view.findViewById(R.id.matches_fab);

        fab.attachToRecyclerView(recyclerView);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                posts.clear();
                loadContent();
            }
        });
        loadContent();

        return view;
    }

    public void loadContent() {

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        PostsAdapter adapter = new PostsAdapter(posts, getContext(), getActivity().getSupportFragmentManager());
        recyclerView.setAdapter(adapter);

        App.getApi().getData("bash.im", "bash", 50).enqueue(new Callback<List<PostModel>>() {
            @Override
            public void onResponse(Call<List<PostModel>> call, Response<List<PostModel>> response) {
                if (response.isSuccessful()) {
                    imageView.setVisibility(View.INVISIBLE);
                    posts.addAll(response.body());
                    recyclerView.getAdapter().notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<PostModel>> call, Throwable t) {
                imageView.setVisibility(View.VISIBLE);
                // Toast.makeText(getContext(), "An error occurred during networking", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
