package com.hookah.roma.hookahmix;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.File;

public class TabakFragment extends Fragment {
    private Tabak mTabak;
    private Button mFavouriteButton;
    private Button mFavouriteButtonItem;
    private Toolbar mToolBar;
    private TextView mDescriptionTextView;
    private TextView mNameTextView;
    private TextView mFamilyTextView;
    private ImageView mImageView;
    private RatingBar mRatingBar;
    private Context context;
    private TextView mRatingBarInt;
    private File mPhotoFile;
    public static final String EXTRA_TABAK_NAME = "com.hookah.roma.hookahmix.name";

    public static TabakFragment newInstance(String name){
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_TABAK_NAME,name);
        TabakFragment fragment = new TabakFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
       String tabakName = (String) getArguments().get(EXTRA_TABAK_NAME);
        mTabak = TabakLab.get(getActivity()).getTabak(tabakName);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu,inflater);
        inflater.inflate(R.menu.tabak_items,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_favourite:
                mTabak.setIsfavourite("1");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tabak_fragment,container,false);
        context=getActivity();
        int resId = context.getResources().getIdentifier("ananas","drawable",context.getPackageName());

        mNameTextView = (TextView) v.findViewById(R.id.tabak_name);
        mNameTextView.setText(mTabak.getName());

        mDescriptionTextView = (TextView) v.findViewById(R.id.tabak_description);
        mDescriptionTextView.setText(mTabak.getDescription());

        mFamilyTextView = (TextView) v.findViewById(R.id.tabak_family);
        mFamilyTextView.setText(mTabak.getFamily());

        mRatingBarInt = (TextView) v.findViewById(R.id.rating_int);

        mRatingBar = (RatingBar) v.findViewById(R.id.ratingBar);
        mRatingBar.setProgress(4);

        mFavouriteButton = (Button) v.findViewById(R.id.button_favourite);

        mToolBar = (Toolbar) v.findViewById(R.id.tool_bar);

        ((TabakPagerActivity) getActivity()).setSupportActionBar(mToolBar);
        ((TabakPagerActivity) getActivity()).getSupportActionBar().setTitle("");
        ((TabakPagerActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((TabakPagerActivity) getActivity()).getSupportActionBar().setHomeAsUpIndicator(R.mipmap.ic_launcher);

        Drawable drawable = mRatingBar.getProgressDrawable();
        drawable.setColorFilter(Color.parseColor("#00c7af"), PorterDuff.Mode.SRC_ATOP);

        mRatingBarInt = (TextView) v.findViewById(R.id.rating_int);

        Typeface notoSansBoldFont = Typeface.createFromAsset(getResources().getAssets(),"fonts/NotoSans-Bold.ttf");
        mRatingBarInt.setTypeface(notoSansBoldFont);

        Typeface notoSansRegularFont = Typeface.createFromAsset(getResources().getAssets(),"fonts/NotoSansUI-Regular.ttf");

        mDescriptionTextView.setTextColor(getResources().getColor(R.color.description));
        mDescriptionTextView.setTypeface(notoSansRegularFont);

        mImageView = (ImageView) v.findViewById(R.id.imageView);
        Picasso.with(getActivity()).load(R.drawable.ananas).resizeDimen(R.dimen.image_size_w,R.dimen.image_size_h).centerCrop().into(mImageView);
        mImageView.setColorFilter(R.color.colorPrimaryea, PorterDuff.Mode.DARKEN);

        return v;
    }
}


