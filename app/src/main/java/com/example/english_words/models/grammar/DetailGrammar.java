package com.example.english_words.models.grammar;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class DetailGrammar implements Parcelable {
    private String desc;
    private String themeTitle;

    public String getThemeTitle() {
        return themeTitle;
    }

    protected DetailGrammar(Parcel in) {
        desc = in.readString();
    }

    public static final Creator<DetailGrammar> CREATOR = new Creator<DetailGrammar>() {
        @Override
        public DetailGrammar createFromParcel(Parcel in) {
            return new DetailGrammar(in);
        }

        @Override
        public DetailGrammar[] newArray(int size) {
            return new DetailGrammar[size];
        }
    };

    public String getDesc() {
        return desc;
    }

    @Override
    public String toString() {
        return desc;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(desc);
    }
}
