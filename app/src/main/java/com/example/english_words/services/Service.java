package com.example.english_words.services;

import com.example.english_words.models.grammar.GrammarThemesModule;
import com.example.english_words.models.words.WordsModel;
import com.example.english_words.models.stories.StoryModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface Service {

    @GET("words.json")
    Call<List<List<WordsModel>>> getWords();

    @GET("stories-{page}-{limit}.json")
    Call<List<StoryModel>> getStories(@Path("page") int page,
                                      @Path("limit") int limit);
    @GET("stories.json")
    Call<List<StoryModel>> getAllStories();

    @GET("grammar.json")
    Call<List<GrammarThemesModule>> getGrammarList();
}
