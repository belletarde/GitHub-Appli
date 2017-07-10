package com.example.felix.githubapplication;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.example.felix.githubapplication.dto.RepositorySync;
import com.example.felix.githubapplication.retrofit.RetrofitInitializer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private int page = 1;
    private Button main_btn;
    private ProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        main_btn = (Button) findViewById(R.id.main_btn);
        progress = (ProgressBar) findViewById(R.id.progressBar);
        progress.setVisibility(View.GONE);
        main_btn.setVisibility(View.GONE);
        progress.setVisibility(View.VISIBLE);
        main_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                page++;
                main_btn.setVisibility(View.GONE);
                progress.setVisibility(View.VISIBLE);
                go(page);

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        go(page);
    }



    protected void go(int nextPage){
        Call<RepositorySync> call = new RetrofitInitializer().GitHubService().lista(nextPage);
        call.enqueue(new Callback<RepositorySync>() {
            @Override
            public void onResponse(@NonNull Call<RepositorySync> call, @NonNull Response<RepositorySync> response) {
                RepositorySync gitSync = response.body();

                assert gitSync != null;
                int j =  gitSync.getItems().size();
                Card c = new Card(MainActivity.this,true);
                for (int i = 0; i < j; i++) {
                    c.addItemMain(gitSync.getItems().get(i).getName(), // nome
                            gitSync.getItems().get(i).getDescription(), // descricao
                            gitSync.getItems().get(i).getForks_count(), // fork
                            gitSync.getItems().get(i).getStargazers_count(), // estrelas
                            gitSync.getItems().get(i).getOwner().getLogin(), //nome do autor
                            gitSync.getItems().get(i).getOwner().getAvatar_url()); // foto
                }

                progress.setVisibility(View.GONE);
                main_btn.setVisibility(View.VISIBLE);
            }
            @Override
            public void onFailure(@NonNull Call<RepositorySync> call, @NonNull Throwable t) {

            }
        });
    }

}
