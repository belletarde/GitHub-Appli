package com.example.felix.githubapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;

import com.example.felix.githubapplication.dto.PullsSync;
import com.example.felix.githubapplication.retrofit.RetrofitInitializer;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailsActivitty extends AppCompatActivity {
    String na,nr,pic;
    private ProgressBar progressBar2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_activitty);
        progressBar2 = (ProgressBar) findViewById(R.id.progressBar2);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent i = getIntent();
        na = i.getStringExtra("nameUser");
        nr = i.getStringExtra("nameR");
        pic = i.getStringExtra("pic");

        Call<List<PullsSync>> call = new RetrofitInitializer().GitHubService().listPull(na,nr);
        progressBar2.setVisibility(View.VISIBLE);
            call.enqueue(new Callback<List<PullsSync>>() {
                @Override
                public void onResponse(@NonNull Call<List<PullsSync>> call, @NonNull Response<List<PullsSync>> response) {
                    List<PullsSync> pullsSync = response.body();
                    assert pullsSync != null;
                    Card c = new Card(DetailsActivitty.this);
                    if (pullsSync.size() <= 0) {
                        progressBar2.setVisibility(View.GONE);
                        NoPullFragment fragmento = new NoPullFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragmento).commit();
                    } else {
                        for (int i = 0; i < pullsSync.size(); i++) {
                            c.addItemDetail(pullsSync.get(i).getTitle(), pullsSync.get(i).getBody(), pullsSync.get(i).getHtml_url(), na, pic);
                        }
                        progressBar2.setVisibility(View.GONE);
                    }
                }
                @Override
                public void onFailure(@NonNull Call<List<PullsSync>> call, @NonNull Throwable t) {

                }
            });



        }


}
