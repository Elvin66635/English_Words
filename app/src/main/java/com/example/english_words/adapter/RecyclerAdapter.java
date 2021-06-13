package com.example.english_words.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.english_words.R;
import com.example.english_words.models.words.WordsModel;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "RecyclerAdapter";
    private final Context context;
    private final List<WordsModel> listRecyclerItem;

    public RecyclerAdapter(Context context, List<WordsModel> listRecyclerItem) {
        this.context = context;
        this.listRecyclerItem = listRecyclerItem;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.list_item, viewGroup, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
        itemViewHolder.wordName.setText(listRecyclerItem.get(position).getPages().get(position).toString());

    }


    @Override
    public int getItemCount() {
        return listRecyclerItem.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView wordName;

        public ItemViewHolder(View itemView) {
            super(itemView);

            wordName = (TextView) itemView.findViewById(R.id.wordName);
        }
    }
}