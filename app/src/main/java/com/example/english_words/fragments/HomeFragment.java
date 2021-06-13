package com.example.english_words.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.english_words.R;
import com.example.english_words.StoriesActivity;
import com.example.english_words.activties.GrammarActivity;
import com.example.english_words.activties.WordsActivity;
import com.example.english_words.adapter.RecyclerAdapter;
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

public class HomeFragment extends Fragment {


    private static final String TAG = "HomeFragment";

    GridLayout mainGrid;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        mainGrid = (GridLayout) view.findViewById(R.id.mainGrid);

        setSingleEvent(mainGrid);
        return view;
    }

    private void setSingleEvent(GridLayout mainGrid) {
        //Loop all child item of Main Grid
        for (int i = 0; i < mainGrid.getChildCount(); i++) {
            //You can see , all child item is CardView , so we just cast object to CardView
            CardView cardView = (CardView) mainGrid.getChildAt(i);
            final int finalI = i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (finalI==0) {
                        Intent intent = new Intent(getActivity(), WordsActivity.class);
                        startActivity(intent);
                    }else if (finalI==1) {
                        Intent intent = new Intent(getActivity(), GrammarActivity.class);
                        startActivity(intent);
                    }
                }
            });
        }
    }

}