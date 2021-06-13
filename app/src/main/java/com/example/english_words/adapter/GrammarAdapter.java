package com.example.english_words.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.english_words.R;
import com.example.english_words.models.grammar.GrammarDetailActivity;
import com.example.english_words.models.grammar.GrammarThemesModule;

import java.util.ArrayList;
import java.util.List;

public class GrammarAdapter extends RecyclerView.Adapter<GrammarAdapter.ViewHolder> {
    private static final String TAG = "GrammarAdapter";
    private ArrayList<GrammarThemesModule> grammarList;
    private Context mContext;

    public GrammarAdapter(ArrayList<GrammarThemesModule> grammarList, Context mContext) {
        this.grammarList = grammarList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public GrammarAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.grammar_list, parent, false);
        ViewHolder myItemViewHolder = new ViewHolder(view);

        return myItemViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GrammarThemesModule grammar = grammarList.get(position);
        holder.title.setText(grammar.getTitle());
        holder.detailTheme.setText(grammar.getDetails().get(position).getThemeTitle());

        holder.expandableLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isExpanded = grammarList.get(position).isExpanded();
                holder.expandableLayout.setVisibility(View.GONE);
                notifyItemChanged(position);
                Bundle b = new Bundle();
                b.putString("desc", grammarList.get(position).getDetails().get(position).getDesc());
                Intent i = new Intent(mContext, GrammarDetailActivity.class);
                i.putExtras(b);
             //   i.putExtra("desc", grammarList.get(position).getDetail().toString());
                Log.d(TAG, "onClick: " + b.toString());
                mContext.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return grammarList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, detailTheme;
        ConstraintLayout expandableLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.grammarTitle);
            expandableLayout = itemView.findViewById(R.id.expandableLayout);
            detailTheme = itemView.findViewById(R.id.detailThemeTitle);
            title.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    GrammarThemesModule grammar = grammarList.get(getAdapterPosition());
                    grammar.setExpanded(!grammar.isExpanded());
                    notifyItemChanged(getAdapterPosition());


                }
            });
        }
    }
}
