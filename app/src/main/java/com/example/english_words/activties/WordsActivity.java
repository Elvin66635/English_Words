package com.example.english_words.activties;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.english_words.R;
import com.example.english_words.adapter.RecyclerAdapter;
import com.example.english_words.models.stories.StoryModel;
import com.example.english_words.models.words.WordsModel;
import com.example.english_words.services.Service;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WordsActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private List<WordsModel> viewItems = new ArrayList<>();
    private RecyclerAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private static final String TAG = "WordsActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words);

        mRecyclerView = findViewById(R.id.recycler_view);
        layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        
       getList();
    }

    private void getList() {
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://raw.githubusercontent.com/Elvin66635/Storage/main/")
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();

            Service service = retrofit.create(Service.class);
            Call<List<List<WordsModel>>> call = service.getWords();
            call.enqueue(new Callback<List<List<WordsModel>>>() {
                @Override
                public void onResponse(Call<List<List<WordsModel>>> call, Response<List<List<WordsModel>>> response) {
                    Log.d(TAG, "onResponse: " + response.body());
                }

                @Override
                public void onFailure(Call<List<List<WordsModel>>> call, Throwable t) {

                }
            });
    }


  /*   if (response.isSuccessful()) {
        viewItems = (List<WordsModel>) response.body();
        mAdapter = new RecyclerAdapter(getApplicationContext(), viewItems);
        mRecyclerView.setAdapter(mAdapter);
        Log.d(TAG, "onResponse: " + response.body());
    } else {
        Log.d(TAG, "Error: " + response.message());
        Log.d(TAG, "Error: " + response.code());
    }
}
*/
}