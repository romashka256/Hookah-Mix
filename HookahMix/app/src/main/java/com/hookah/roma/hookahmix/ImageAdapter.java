package com.hookah.roma.hookahmix;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.File;

public class ImageAdapter extends ArrayAdapter<File>{
    LayoutInflater mInflater;
    Picasso mPicasso;

    public ImageAdapter(Context context, File[] objects) {
        super(context, R.layout.tabak_item , objects);
        mInflater = LayoutInflater.from(context);
        mPicasso = Picasso.with(context);
    }

    public View getView(int position, View convertView, ViewGroup parent){
        View view = convertView;
        if(view == null){
            view = mInflater.inflate(R.layout.tabak_item,parent,false);
        }
        ImageView imageView = (ImageView) view.findViewById(R.id.picture_imageview_item);
        mPicasso.load(getItem(position)).resizeDimen(R.dimen.image_size_item,R.dimen.image_size_item).centerInside().into(imageView);
        return view;
    }
}
