package com.example.english_words.activties;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.english_words.DetailStoryActivity;
import com.example.english_words.R;
import com.example.english_words.adapter.GrammarAdapter;
import com.example.english_words.adapter.StoriesAdapter;
import com.example.english_words.models.clickListener.CustomItemClickListener;
import com.example.english_words.models.grammar.GrammarThemesModule;
import com.example.english_words.models.stories.StoryModel;
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

public class GrammarActivity extends AppCompatActivity {
    private static final String TAG = "GrammarActivity";
    RecyclerView recyclerView;
    private List<GrammarThemesModule> grammarList;
    private GrammarAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grammar);
        recyclerView = findViewById(R.id.recycler_grammar);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        layoutManager = new LinearLayoutManager(this);

        getGrammarData();
    }

    private void getGrammarData() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://raw.githubusercontent.com/Elvin66635/Storage/main/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        Service service = retrofit.create(Service.class);
        Call<List<GrammarThemesModule>> call = service.getGrammarList();
        call.enqueue(new Callback<List<GrammarThemesModule>>() {
            @Override
            public void onResponse(Call<List<GrammarThemesModule>> call, Response<List<GrammarThemesModule>> response) {
                if (response.isSuccessful()) {
                    grammarList = response.body();
                    Log.d(TAG, "onResponse: " + response.body());
                    mAdapter = new GrammarAdapter((ArrayList<GrammarThemesModule>) grammarList, getApplicationContext()) {
                    };
                }
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(mAdapter);
            }
            @Override
            public void onFailure(Call<List<GrammarThemesModule>> call, Throwable t) {
                Log.d(TAG, "onFailure: "+ t.getMessage());
            }
        });
    }
}