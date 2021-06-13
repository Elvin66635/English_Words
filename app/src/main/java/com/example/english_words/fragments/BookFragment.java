package com.example.english_words.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.english_words.DetailStoryActivity;
import com.example.english_words.R;
import com.example.english_words.listeners.RecyclerItemClickListener;
import com.example.english_words.adapter.StoriesAdapter;
import com.example.english_words.models.clickListener.CustomItemClickListener;
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

public class BookFragment extends Fragment {
    private static final String EXTRA_TITLE = "title";
    private static final String EXTRA_TITLE_RU = "title_ru";
    private static final String EXTRA_DESC = "desc";
    private static final String EXTRA_DESC_RU = "desc_ru";
    private static final String EXTRA_IMAGE = "img";
    private static final String EXTRA_LIST = "list";
    ProgressBar progressBar;
    private static int TOTAL_PAGES = 50;
    private int mPage = 0;
    private RecyclerView mRecyclerView;
    private List<StoryModel> storyList = new ArrayList<>();
    private StoriesAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private static final String TAG = "BookFragment";
    NestedScrollView nestedScrollView;
    int page = 0, limit = 10;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_book, container, false);
        nestedScrollView = view.findViewById(R.id.nestedScrollView);
        mRecyclerView = view.findViewById(R.id.recycler_books);
        mAdapter = new StoriesAdapter(getActivity(), (ArrayList<StoryModel>) storyList);
        progressBar = view.findViewById(R.id.loading_progress);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        mRecyclerView.setAdapter(mAdapter);

        getData(page,limit);
        nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if(v.getChildAt(v.getChildCount() - 1) != null) {
                    if ((scrollY >= (v.getChildAt(v.getChildCount() - 1).getMeasuredHeight() - v.getMeasuredHeight())) &&
                            scrollY > oldScrollY) {
                        increment();
                        progressBar.setVisibility(View.VISIBLE);
                        getData(page,limit);
                        //code to fetch more data for endless scrolling
                    }
                }
            }
        });
        CustomItemClickListener clickListener = new CustomItemClickListener() {
            @Override
            public void onItemClick(int pos) {
                Log.d(TAG, "onItemClick: ");
                if (pos != RecyclerView.NO_POSITION) {
                    Intent intent = new Intent(getActivity(), DetailStoryActivity.class);
                    intent.putExtra(EXTRA_TITLE, storyList.get(pos).getTitle());
                    intent.putExtra(EXTRA_TITLE_RU, storyList.get(pos).getTitle_ru());
                    intent.putExtra(EXTRA_DESC, storyList.get(pos).getDescription_en());
                    intent.putExtra(EXTRA_DESC_RU, storyList.get(pos).getDescription_ru());
                    intent.putExtra(EXTRA_IMAGE, storyList.get(pos).getImage());
                    intent.putExtra(EXTRA_LIST, storyList.get(pos).getTranslation_words());
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    getActivity().startActivity(intent);

                }
            }
        };

        mRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getActivity().getApplicationContext(), mRecyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int pos) {
                        // do whatever
                        Intent intent = new Intent(getActivity(), DetailStoryActivity.class);
                        intent.putExtra(EXTRA_TITLE, storyList.get(pos).getTitle());
                        intent.putExtra(EXTRA_TITLE_RU, storyList.get(pos).getTitle_ru());
                        intent.putExtra(EXTRA_DESC, storyList.get(pos).getDescription_en());
                        intent.putExtra(EXTRA_DESC_RU, storyList.get(pos).getDescription_ru());
                        intent.putExtra(EXTRA_IMAGE, storyList.get(pos).getImage());
                        intent.putExtra(EXTRA_LIST, storyList.get(pos).getTranslation_words());
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        getActivity().startActivity(intent);
                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                        Toast.makeText(getActivity().getApplicationContext(), "long", Toast.LENGTH_SHORT).show();
                    }
                })
        );



        return view;
    }
        private void getData(int page, int limit) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://raw.githubusercontent.com/Elvin66635/Storage/main/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            Service service = retrofit.create(Service.class);
            Call<List<StoryModel>> call = service.getStories(page, limit);
            call.enqueue(new Callback<List<StoryModel>>() {
                @Override
                public void onResponse(Call<List<StoryModel>> call, Response<List<StoryModel>> response) {

                    if (storyList.size() == 45){
                        Log.d(TAG, "onResponse:OOOK " + storyList.size());
                        progressBar.setVisibility(View.GONE);
                    }
                    if (response.isSuccessful() && response.body() != null) {
//                        progressBar.setVisibility(View.GONE);
                        Log.d(TAG, "on " + response.body());

                        for (int i = 0; i <response.body().size()-1 ; i++) {
                            StoryModel model = new StoryModel();
                            model = response.body().get(i);
                            storyList.add(response.body().get(i));
                        }
                        if (storyList.size() == 50){
                            progressBar.setVisibility(View.GONE);
                        }
                        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
                        mAdapter = new StoriesAdapter(getActivity().getApplicationContext(), (ArrayList<StoryModel>) storyList);
                        mRecyclerView.setAdapter(mAdapter);

                    } else {
                        Log.d(TAG, "onResponse: " + response.code());
                    }
                }

                @Override
                public void onFailure(Call<List<StoryModel>> call, Throwable t) {
                    Log.d(TAG, "onFailure: " + t.getMessage());
                }
            });
    }
    private void increment() {
        page++;
        limit = limit + 10;
    }
}
