package com.ranktrends.quizely.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

    String baseUrl = "http://192.168.0.105/quizely/";

    public Retrofit getRetrofitInstance(){
        return new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).build();
    }
}
