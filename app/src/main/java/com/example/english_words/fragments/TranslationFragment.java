package com.example.english_words.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.english_words.DetailStoryActivity;
import com.example.english_words.NonScrollListView;
import com.example.english_words.R;
import com.example.english_words.models.stories.WordsTranslation;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Locale;

public class TranslationFragment extends Fragment {
    private static final String TAG = "TranslationFragment";
    private static final String EXTRA_DESC_RU = "desc_ru";
    private static final String EXTRA_TITLE_RU = "title_ru";
    public static final String EXTRA_IMAGE = "img";
    public static final String EXTRA_LIST = "list";
    private static final String EXTRA_WORD = "word";
    TextView descriptionTxt, titleTxt;
    ImageView imageView;
    NonScrollListView wordsList;
    Button btnTranslate;
    private TextToSpeech mTTS;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_detail_story, container, false);
        wordsList = (NonScrollListView) view.findViewById(R.id.lv_nonscroll_list);
        descriptionTxt = view.findViewById(R.id.fragmentDetailStoryDesc);
        titleTxt = view.findViewById(R.id.fragmentTitleRu);
        imageView = view.findViewById(R.id.fragmentImage);
        btnTranslate = view.findViewById(R.id.btnTranslate);
        mTTS = new TextToSpeech(getActivity(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int result = mTTS.setLanguage(Locale.ENGLISH);
                    if (result == TextToSpeech.LANG_MISSING_DATA
                            || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("TTS", "Language not supported");
                    } else {
                       // mButtonSpeak.setEnabled(true);
                    }
                } else {
                    Log.e("TTS", "Initialization failed");
                }
            }
        });
        Intent intentReceived = getActivity().getIntent();
        Bundle data = intentReceived.getExtras();
        Log.d(TAG, "data " + data);
        if (data != null) {
            String desc = data.getString(EXTRA_DESC_RU);
            String title = data.getString(EXTRA_TITLE_RU);
            String image = data.getString(EXTRA_IMAGE);
            String word = data.getParcelable(EXTRA_WORD);

            Log.d(TAG, "onCreateView: " + word);
            Picasso.with(getActivity()).load(image).into(imageView);
            ArrayList<WordsTranslation> arrayListString = data.getParcelableArrayList((EXTRA_LIST));
            Log.d(TAG, "arrayList: " + arrayListString );
            ArrayAdapter<WordsTranslation> adapter = new ArrayAdapter<>(getActivity(),
                    android.R.layout.simple_list_item_1, android.R.id.text1,  arrayListString);
            wordsList.setAdapter(adapter);

            wordsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                     String textSpeech = arrayListString.get(position).getOriginal();
                     mTTS.speak(textSpeech, TextToSpeech.QUEUE_FLUSH, null);
                }
            });

            descriptionTxt.setText(desc);
            titleTxt.setText(title);
        } else {
            Log.d(TAG, "error ");
        }

        btnTranslate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
        return view;

    }


}
