package com.example.english_words;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import com.example.english_words.adapter.StoriesAdapter;
import com.example.english_words.models.clickListener.CustomItemClickListener;
import com.example.english_words.models.stories.StoryModel;
import com.example.english_words.services.Service;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class StoriesActivity extends AppCompatActivity  {
    private static final String EXTRA_TITLE = "title";
    private RecyclerView mRecyclerView;
    private List<StoryModel> viewItems;
    private StoriesAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private static final String TAG = "StoriesActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stories);
        Log.d(TAG, "onCreate: ");
        mRecyclerView = findViewById(R.id.recycler_detail);
        layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        layoutManager = new LinearLayoutManager(this);

      //  getStoriesList();

    }


    public void getStoriesList(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
               .baseUrl("https://raw.githubusercontent.com/Elvin66635/Storage/main/")
               .addConverterFactory(GsonConverterFactory.create(gson))
               .build();

       Service service = retrofit.create(Service.class);
  //     Call<List<StoryModel>> call = service.getStories();
/*
       call.enqueue(new Callback<List<StoryModel>>() {
           @Override
           public void onResponse(Call<List<StoryModel>> call, Response<List<StoryModel>> response) {
               if (response.isSuccessful()) {
                    viewItems = response.body();
                   Log.d(TAG, "onResponse: " + response.body());
                    mAdapter = new StoriesAdapter(getApplicationContext(), (ArrayList<StoryModel>) viewItems, new CustomItemClickListener() {
                       @Override
                       public void onItemClick(int pos) {
                           if (pos != RecyclerView.NO_POSITION) {
                               Intent intent = new Intent(getApplicationContext(), DetailStoryActivity.class);
                               intent.putExtra(EXTRA_TITLE, viewItems.get(pos).getTitle());
                               intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                               getApplicationContext().startActivity(intent);
                           }
                       }
                   });
                   mRecyclerView.setLayoutManager(layoutManager);
                   mRecyclerView.setAdapter(mAdapter);
                   *//*for (int i = 0; i <viewItems.size() ; i++) {
                       viewItems = (List<StoryModel>) response.body().get(i);
                   }*//*

               }else {
                   Log.d(TAG, "onResponseError: " + response.code());
               }
           }

           @Override
           public void onFailure(Call<List<StoryModel>> call, Throwable t) {
               Log.d(TAG, "onFailure: " + t.getMessage());
           }
       });*/
    }
}
