package com.example.english_words.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.english_words.models.clickListener.CustomItemClickListener;
import com.example.english_words.R;
import com.example.english_words.models.stories.StoryModel;

import java.util.ArrayList;

public class StoriesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "StoriesAdapter";
    private Context context;
    private ArrayList<StoryModel> storyListItem;
    private static final int LOADING = 0;
    private static final int ITEM = 1;
    private boolean isLoadingAdded = false;


    public StoriesAdapter(Context context, ArrayList<StoryModel> listRecyclerItem) {
        this.context = context;
        this.storyListItem = listRecyclerItem;
    }



    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case ITEM:
                View viewItem = inflater.inflate(R.layout.story_list_item, parent, false);
                viewHolder = new StoryItemViewHolder(viewItem);
                break;
            case LOADING:
                View viewLoading = inflater.inflate(R.layout.item_progress, parent, false);
                viewHolder = new ProgressBarItemViewHolder(viewLoading);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        StoryModel story = storyListItem.get(position);
        switch (getItemViewType(position)) {
            case ITEM:
                StoryItemViewHolder movieViewHolder = (StoryItemViewHolder) holder;
                movieViewHolder.storyTitle.setText(story.getTitle());
                break;

            case LOADING:
                ProgressBarItemViewHolder loadingViewHolder = (ProgressBarItemViewHolder) holder;
                loadingViewHolder.progressBar.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return storyListItem == null ? 0 : storyListItem.size();
    }

    @Override
    public int getItemViewType(int position) {
        return (position == storyListItem.size() - 1 && isLoadingAdded) ? LOADING : ITEM;
    }


    static class StoryItemViewHolder extends RecyclerView.ViewHolder {
        TextView storyTitle;

        public StoryItemViewHolder(View itemView) {
            super(itemView);
            storyTitle = (TextView) itemView.findViewById(R.id.story_title);

        }
    }

    static class ProgressBarItemViewHolder extends RecyclerView.ViewHolder {
        ProgressBar progressBar;

        public ProgressBarItemViewHolder(View itemView) {
            super(itemView);
            progressBar = (ProgressBar) itemView.findViewById(R.id.load_more_progress);
        }
    }
}
