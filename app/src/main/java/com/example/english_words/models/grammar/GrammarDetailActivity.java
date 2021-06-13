package com.example.english_words.models.grammar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.english_words.R;
import com.example.english_words.models.stories.WordsTranslation;

import java.util.ArrayList;
import java.util.Arrays;

public class GrammarDetailActivity extends AppCompatActivity {
    private static final String TAG = "GrammarDetailActivity";
    TextView descTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grammar_detail_acitivity);
        descTxt = findViewById(R.id.descTxt);

        Bundle b = this.getIntent().getExtras();
        String desc=b.getString("desc");
        Log.d(TAG, "onCreate: " + desc);
        descTxt.setText(desc);
     /*
        ArrayList<GrammarThemesModule> arrayListString = i.getParcelableArrayListExtra(("desc"));
        ArrayList<String> desc = i.getStringArrayListExtra("desc");
        Log.d(TAG, "onCreate: " + desc);
        descTxt.setText((CharSequence) desc);*/
    }
}