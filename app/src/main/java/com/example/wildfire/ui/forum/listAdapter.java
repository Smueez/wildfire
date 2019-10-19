package com.example.wildfire.ui.forum;


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.wildfire.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;



public class listAdapter extends ArrayAdapter<forum> {

    private Activity context;
    private List<forum> list;

    public listAdapter(Activity context,List<forum>list){
        super(context, R.layout.fragment_forum,list);

        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View mylistview = inflater.inflate(R.layout.fragment_forum,null,true);

        //ImageView image1 = mylistview.findViewById(R.id.imageView3);
        TextView locationName = mylistview.findViewById(R.id.location);
        TextView time = mylistview.findViewById(R.id.time);

        TextView area = mylistview.findViewById(R.id.area);
        TextView user = mylistview.findViewById(R.id.user);

        forum  forum1 = list.get(position);

        time.setText(forum1.gettime());
        area.setText(forum1.getArea());
        user.setText(forum1.getUser());
        return mylistview;
    }
}
