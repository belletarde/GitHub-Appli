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
    private Activity context;
    private ViewGroup eachItemGitHub;

    Card(Activity context){
        this.context = context;
        this.eachItemGitHub = (ViewGroup) context.findViewById(R.id.container_details);

    }

    void addItemDetail(final String nameR, String description, final String url, final String nameUser, String pic){
        //-----------Bind-------------------//
        CardView cardView = (CardView) LayoutInflater.from(context).inflate(R.layout.list_pull, eachItemGitHub, false);

        TextView fieldTitle = (TextView) cardView.findViewById(R.id.list_pull_title);
        TextView fieldDescription = (TextView) cardView.findViewById(R.id.list_pull_body);
        TextView fieldNameUser = (TextView) cardView.findViewById(R.id.list_pull_name);

        ImageView fieldAvatarImg = (ImageView) cardView.findViewById(R.id.list_pull_img);

        //-----------Cache-----------------------------------------//
        Cache c = new Cache(context);
        c.getCache(pic, fieldAvatarImg);

        //-----------Sets-----------------------------------------//

        fieldNameUser.setText(nameUser);
        fieldTitle.setText(nameR);
        fieldDescription.setText(description);

        //-----------Click----------------------------------------//
        eachItemGitHub.addView(cardView);

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(Intent.ACTION_VIEW).setData(Uri.parse(url)));
            }
        }) ;
    }



}
