package com.example.english_words.models.grammar;

import java.util.ArrayList;
import java.util.List;

public class GrammarThemesModule {
    private String title;
    private boolean isExpanded;
    private ArrayList<DetailGrammar> details;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isExpanded() {
        return isExpanded;
    }

    public ArrayList<DetailGrammar> getDetails() {
        return details;
    }

    public void setExpanded(boolean expanded) {
        isExpanded = expanded;
    }

    @Override
    public String toString() {
        return "GrammarThemesModule{" +
                "title='" + title + '\'' +
                 + '\'' +
                '}';
    }
}
