package com.example.felix.githubapplication;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

class Card {
    private Activity act;
    private ViewGroup eachItemGitHub;
    Card(Activity act, Boolean mainOrDetail){
        this.act = act;
        if(mainOrDetail) {
            this.eachItemGitHub = (ViewGroup) act.findViewById(R.id.container);
        }else {
            this.eachItemGitHub = (ViewGroup) act.findViewById(R.id.container_details);
        }
    }

    void addItemMain(final String nameR, String description, String fork, String star, final String nameUser, final String pic){
        CardView cardView = (CardView) LayoutInflater.from(act).inflate(R.layout.list_github, eachItemGitHub, false);
        TextView fieldName = (TextView) cardView.findViewById(R.id.list_github_name);
        TextView fieldDescription = (TextView) cardView.findViewById(R.id.list_github_description);
        TextView fieldFork = (TextView) cardView.findViewById(R.id.list_github_fork);
        TextView fieldStar = (TextView) cardView.findViewById(R.id.list_github_star);
        TextView fieldNameUser = (TextView) cardView.findViewById(R.id.list_github_autor);
        final ImageView fieldAvatarImg = (ImageView) cardView.findViewById(R.id.list_github_avatar);

        Cache(pic, fieldAvatarImg);

        fieldNameUser.setText(nameUser);
        fieldName.setText(nameR);
        fieldDescription.setText(description);
        fieldFork.setText(fork);
        fieldStar.setText(star);

        eachItemGitHub.addView(cardView);

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent (act,DetailsActivitty.class);
                i.putExtra("nameR",nameR);
                i.putExtra("nameUser",nameUser);
                i.putExtra("pic", pic);
                act.startActivity(i);
            }
        }) ;
    }

    void addItemDetail(final String nameR, String description, final String url, final String nameUser, String pic){
        CardView cardView = (CardView) LayoutInflater.from(act).inflate(R.layout.list_pull, eachItemGitHub, false);
        TextView fieldTitle = (TextView) cardView.findViewById(R.id.list_pull_title);
        TextView fieldDescription = (TextView) cardView.findViewById(R.id.list_pull_body);

        TextView fieldNameUser = (TextView) cardView.findViewById(R.id.list_pull_name);
        ImageView fieldAvatarImg = (ImageView) cardView.findViewById(R.id.list_pull_img);



        Cache(pic, fieldAvatarImg);

        fieldNameUser.setText(nameUser);
        fieldTitle.setText(nameR);
        fieldDescription.setText(description);


        eachItemGitHub.addView(cardView);

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                act.startActivity(new Intent(Intent.ACTION_VIEW).setData(Uri.parse(url)));
            }
        }) ;
    }

    private void Cache(String pt, ImageView avatar){


        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.init(ImageLoaderConfiguration.createDefault(act));
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnFail(R.mipmap.ic_launcher)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();
        imageLoader.displayImage(pt, avatar, options);
    }

}
