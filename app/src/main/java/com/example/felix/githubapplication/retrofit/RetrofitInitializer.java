package com.example.felix.githubapplication.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RetrofitInitializer {

    private final Retrofit retrofit;

    public RetrofitInitializer() {
        retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
    }

    public GitHubService GitHubService() {
        return retrofit.create(GitHubService.class);
    }
}
