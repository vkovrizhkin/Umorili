package com.kovrizhkin.rssretrofit2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.melnykov.fab.FloatingActionButton;
import com.vk.sdk.VKSdk;
import com.vk.sdk.util.VKUtil;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class MainActivity extends AppCompatActivity {

    private static final int LOAD_POSTS_NUMBER = 10;
    private static  int LOAD_ITERATOR = 0;
    private static  int CURRENT_LIST_INDEX = 0;

    RecyclerView recyclerView;

    List<PostModel> posts;

    ImageView imageView;

    FloatingActionButton fab;
    FloatingActionButton navFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        posts = new ArrayList<>();
        imageView = (ImageView) findViewById(R.id.wi_fi_off);
        recyclerView = (RecyclerView) findViewById(R.id.posts_recycle_view);
        fab = (FloatingActionButton) findViewById(R.id.matches_fab);

        fab.attachToRecyclerView(recyclerView);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                posts.clear();
                loadContent();
            }
        });

        fab = (FloatingActionButton) findViewById(R.id.matches_fab);
        navFab = (FloatingActionButton) findViewById(R.id.go_to_post_fab);
        navFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, VkActivity.class);
                //myIntent.putExtra("key", value); //Optional parameters
                MainActivity.this.startActivity(myIntent);
            }
        });

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (!recyclerView.canScrollVertically(1)) {
                    CURRENT_LIST_INDEX = 10 + LOAD_ITERATOR * LOAD_POSTS_NUMBER;
                    LOAD_ITERATOR++;
                    posts.clear();
                    loadContent();
                    Toast.makeText(MainActivity.this, "Loaded", Toast.LENGTH_SHORT).show();

                }
            }
        });
        loadContent();

    }

    public void loadContent() {

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        PostsAdapter adapter = new PostsAdapter(posts);
        recyclerView.setAdapter(adapter);

        String[] fingerprints = VKUtil.getCertificateFingerprint(this, this.getPackageName());
        int count = 10 + LOAD_ITERATOR * LOAD_POSTS_NUMBER;
        Log.i("count", Integer.toString(count));
        App.getApi().getData("bash.im", "bash", 100).enqueue(new Callback<List<PostModel>>() {
            @Override
            public void onResponse(Call<List<PostModel>> call, Response<List<PostModel>> response) {
                if (response.isSuccessful()) {
                    imageView.setVisibility(View.INVISIBLE);
                    posts.addAll(response.body());
                    recyclerView.getAdapter().notifyDataSetChanged();
                    Log.i("list index ", Integer.toString(CURRENT_LIST_INDEX));
                    recyclerView.scrollToPosition(CURRENT_LIST_INDEX-1);
                }
            }

            @Override
            public void onFailure(Call<List<PostModel>> call, Throwable t) {
                imageView.setVisibility(View.VISIBLE);
                Toast.makeText(MainActivity.this, "An error occurred during networking", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
