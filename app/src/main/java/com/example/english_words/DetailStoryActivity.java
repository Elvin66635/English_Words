package com.example.english_words;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.UserDictionary;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.english_words.fragments.TranslationFragment;
import com.example.english_words.models.stories.WordsTranslation;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DetailStoryActivity extends AppCompatActivity {
    public static final String EXTRA_TITLE = "title";
    public static final String EXTRA_DESC = "desc";
    public static final String EXTRA_IMAGE = "img";
    public static final String EXTRA_LIST = "list";
    private static final String TAG = "DetailStoryActivity";
    TextView titleTxt, descTxt;
    ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_story);


        titleTxt = findViewById(R.id.detailStoryTitleEn);
        descTxt = findViewById(R.id.detailStoryDesc);
        image = findViewById(R.id.detailImg);
        Button btn;
        Intent i = getIntent();
        String titleString = i.getStringExtra(EXTRA_TITLE);
        String desc = i.getStringExtra(EXTRA_DESC);
        String imageString = i.getStringExtra(EXTRA_IMAGE);
        ArrayList<String> arrayListString = i.getStringArrayListExtra((EXTRA_LIST));
        Log.d(TAG, "onCreate: "+ arrayListString);
        titleTxt.setText(titleString);
        descTxt.setText(desc);

       Picasso.with(this).load(imageString).into(image);

        btn = findViewById(R.id.btnTranslate);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TranslationFragment f = new TranslationFragment();
                getSupportFragmentManager().beginTransaction()
                        .add(android.R.id.content, new TranslationFragment()).addToBackStack(null).commit();
            }
        });

    }
    @Override
    public void onBackPressed(){
        FragmentManager fm = getFragmentManager();
        if (fm.getBackStackEntryCount() > 0) {
            fm.popBackStack();
        } else {
            super.onBackPressed();
        }
    }
}