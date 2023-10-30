package com.ranktrends.quizely.retrofit;

import com.ranktrends.quizely.model.QuestionsList;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;

public interface QuestionsAPI {

    @GET("questionsapi.php")
    Call<QuestionsList> getQuestions();
}
