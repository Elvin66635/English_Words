package com.example.english_words;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

import com.example.english_words.adapter.RecyclerAdapter;
import com.example.english_words.fragments.BookFragment;
import com.example.english_words.fragments.HomeFragment;
import com.example.english_words.fragments.ProfileFragment;
import com.example.english_words.models.words.WordsModel;
import com.example.english_words.services.Service;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private ArrayList<WordsModel> viewItems = new ArrayList<>();
    private RecyclerAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new HomeFragment()).commit();
        }
        initNavBottom();
    }
    private void initNavBottom() {

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(MenuItem item) {
                    Fragment selectedFragment = null;
                    switch (item.getItemId()) {
                        case R.id.nav_home:
                            selectedFragment = new HomeFragment();
                            break;
                        case R.id.nav_favorites:
                            selectedFragment = new BookFragment();
                            break;
                        case R.id.nav_search:
                            selectedFragment = new ProfileFragment();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();
                    return true;
                }
            };

    public void onclick(View view) {
        Intent i = new Intent(this, StoriesActivity.class);
        startActivity(i);
    }
}




























/*  private void addItemsFromJSON() {
        try {

            String jsonDataString = parseJSONData();
            JSONArray jsonArray = new JSONArray(jsonDataString);

            for (int i = 0; i < jsonArray.length(); ++i) {

                JSONObject itemObj = jsonArray.getJSONObject(i);
                wordsModel = new WordsModel();
                viewItems.add(wordsModel);
                String name = itemObj.getString("word");
                String def = itemObj.getString("meanings");

                Log.d(TAG, "addItemsFromJSON: " + name);
                Log.d(TAG, "addItemsFromJSON: " + def);

            }

        } catch (JSONException e) {
            Log.d(TAG, "addItemsFromJSON: ", e);
        }
    }*/