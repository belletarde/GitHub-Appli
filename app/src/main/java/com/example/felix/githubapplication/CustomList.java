package com.example.felix.githubapplication;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.felix.githubapplication.modelo.Items;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;

public class CustomList extends ArrayAdapter<Items> {

    private final Activity context;
    ArrayList<Items> objItems = new ArrayList<>();

    public CustomList(Activity context, ArrayList<Items> objItems) {
        super(context, R.layout.list_github, objItems);
        this.context = context;
        this.objItems = objItems;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        //--------Bind--------------------

        LayoutInflater inflater = context.getLayoutInflater();

        View rowView= inflater.inflate(R.layout.list_github, null, true);

        TextView fieldName = (TextView) rowView.findViewById(R.id.list_github_name);
        TextView fieldDescription = (TextView) rowView.findViewById(R.id.list_github_description);
        TextView fieldFork = (TextView) rowView.findViewById(R.id.list_github_fork);
        TextView fieldStar = (TextView) rowView.findViewById(R.id.list_github_star);
        TextView fieldNameUser = (TextView) rowView.findViewById(R.id.list_github_autor);

        ImageView fieldAvatarImg = (ImageView) rowView.findViewById(R.id.list_github_avatar);
        //----------------Cache--------------//

        Cache c = new Cache(context);
        c.getCache(objItems.get(position).getOwner().getAvatar_url(), fieldAvatarImg);

        //---------setText----------------//

        fieldNameUser.setText(objItems.get(position).getOwner().getLogin());
        fieldStar.setText(objItems.get(position).getStargazers_count());
        fieldFork.setText(objItems.get(position).getForks_count());
        fieldDescription.setText(objItems.get(position).getDescription());
        fieldName.setText(objItems.get(position).getName());

        //----------------Click-------------//

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent (context,DetailsActivitty.class);
                i.putExtra("nameR",objItems.get(position).getName());
                i.putExtra("nameUser",objItems.get(position).getOwner().getLogin());
                i.putExtra("pic", objItems.get(position).getOwner().getAvatar_url());
                context.startActivity(i);
            }
        }) ;

        return rowView;
    }
}