package com.example.felix.githubapplication;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.felix.githubapplication.dto.RepositorySync;
import com.example.felix.githubapplication.modelo.Items;
import com.example.felix.githubapplication.retrofit.RetrofitInitializer;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private int page = 1;
    private Button main_btn;
    private ProgressBar progress;
    private ListView list;
    ArrayList<String> listName = new ArrayList<>();
    ArrayList<Items> listItem = new ArrayList<>();

    private  CustomList adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       progress = (ProgressBar) findViewById(R.id.progressBar);
        go(page);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }
    private void initView(){
        adapter = new CustomList(this, listName,listItem);
        list=(ListView)findViewById(R.id.list);
        list.setAdapter(adapter);
        list.setOnScrollListener(new EndlessScrollListener(this));
    }

    protected void go(int nextPage){
        progress.setVisibility(View.VISIBLE);
        Call<RepositorySync> call = new RetrofitInitializer().GitHubService().lista(nextPage);
        call.enqueue(new Callback<RepositorySync>() {
            @Override
            public void onResponse(@NonNull Call<RepositorySync> call, @NonNull Response<RepositorySync> response) {
                RepositorySync gitSync = response.body();

                assert gitSync != null;
                int j =  gitSync.getItems().size();

                for (int i = 0; i < j; i++) {
                   listName.add(gitSync.getItems().get(i).getName());
                    listItem.add(gitSync.getItems().get(i));


                }

                ///init---------------or-----------notify--------------//
                if (listName.size() <= 30) {
                    initView();
                }else {
                    adapter.notifyDataSetChanged();
                }
                progress.setVisibility(View.GONE);
            }
            @Override
            public void onFailure(@NonNull Call<RepositorySync> call, @NonNull Throwable t) {

            }
        });
    }
    //-------------------------------------Scroll event---------------------------------------------------//
    public class EndlessScrollListener implements AbsListView.OnScrollListener {

        private int visibleThreshold = 1;
        private int currentPage = 1;
        private int previousTotal = 0;
        private boolean loading = true;
        private Activity act;


        public EndlessScrollListener(Activity act) {
            this.act = act;
        }
        public EndlessScrollListener(int visibleThreshold) {
            this.visibleThreshold = visibleThreshold;
        }

        @Override
        public void onScroll(AbsListView view, int firstVisibleItem,
                             int visibleItemCount, int totalItemCount) {
            if (loading) {
                if (totalItemCount > previousTotal) {
                    loading = false;
                    previousTotal = totalItemCount;
                    currentPage++;
                }
            }
            if (!loading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold)) {
                go(currentPage);
                loading = true;
            }
        }
        @Override
        public void onScrollStateChanged(AbsListView view, int scrollState) {
        }
    }

}
