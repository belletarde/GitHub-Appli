package com.example.felix.githubapplication.retrofit;

import com.example.felix.githubapplication.dto.RepositorySync;
import com.example.felix.githubapplication.dto.PullsSync;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GitHubService {




    @GET("search/repositories?q=language:kotlin&sort=stars&page=")
    Call<RepositorySync> lista(@Query("page") int page);

    @GET("repos/{details}/{detail}/pulls")
    Call<List<PullsSync>> listPull(@Path("details") String details, @Path("detail") String detail);



}
