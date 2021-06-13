package com.example.english_words.models.stories;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class WordsTranslation implements Serializable, Parcelable {

    private String original;
    private String transcription;
    private String translation;

    protected WordsTranslation(Parcel in) {
        original = in.readString();
        transcription = in.readString();
        translation = in.readString();
    }

    public static final Creator<WordsTranslation> CREATOR = new Creator<WordsTranslation>() {
        @Override
        public WordsTranslation createFromParcel(Parcel in) {
            return new WordsTranslation(in);
        }

        @Override
        public WordsTranslation[] newArray(int size) {
            return new WordsTranslation[size];
        }
    };

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

    public String getTranscription() {
        return transcription;
    }

    public void setTranscription(String transcription) {
        this.transcription = transcription;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    @Override
    public String toString() {
        return original + ' ' + transcription + ' ' +
                "- " + translation;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(original);
        dest.writeString(transcription);
        dest.writeString(translation);
    }
}
