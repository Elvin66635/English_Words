package com.example.english_words.models.stories;

import java.util.ArrayList;
import java.util.List;

public class StoryModel {
    private String title;
    private String title_ru;
    private String description_en;
    private String image;
    private String description_ru;
    private ArrayList<WordsTranslation> translation_words;


    public ArrayList<WordsTranslation> getTranslation_words() {
        return translation_words;
    }

    public String getTitle() {
        return title;
    }

    public String getTitle_ru() {
        return title_ru;
    }

    public void setTitle_ru(String title_ru) {
        this.title_ru = title_ru;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription_en() {
        return description_en;
    }

    public void setDescription_en(String description_en) {
        this.description_en = description_en;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription_ru() {
        return description_ru;
    }

    public void setDescription_ru(String description_ru) {
        this.description_ru = description_ru;
    }

    @Override
    public String toString() {
        return "StoryModel{" +
                "title='" + title + '\'' +
                ", description_en='" + description_en + '\'' +
                ", image='" + image + '\'' +
                ", description_ru='" + description_ru + '\'' +
                ", translation_words=" + translation_words +
                '}';
    }
}
