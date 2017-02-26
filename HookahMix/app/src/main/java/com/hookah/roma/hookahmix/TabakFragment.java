package com.hookah.roma.hookahmix;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;



public class TabakFragment extends Fragment {
    private Tabak mTabak;
    private Button mFavouriteButton;
    private Button mFavouriteButtonItem;
    private Toolbar mToolBar;
    private TextView mDescriptionTextView;
    private TextView mNameTextView;
    private TextView mFamilyTextView;
    private RatingBar mRatingBar;
    private TextView mRatingBarInt;
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

        String tabakName = (String)getArguments().get(EXTRA_TABAK_NAME);
        mTabak = TabakLab.get(getActivity()).getTabak(tabakName);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.tabak_items,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_favourite:

                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tabak_fragment,container,false);

        mNameTextView = (TextView) v.findViewById(R.id.tabak_name);
        mNameTextView.setText(mTabak.getName());

        mDescriptionTextView = (TextView) v.findViewById(R.id.tabak_description);
        mDescriptionTextView.setText(mTabak.getDescription());

        mFamilyTextView = (TextView) v.findViewById(R.id.tabak_family);
        mFamilyTextView.setText(mTabak.getFamily());

        mRatingBar = (RatingBar) v.findViewById(R.id.ratingBar);
        mRatingBar.setProgress(Integer.parseInt(mTabak.getRating()));

        mFavouriteButton = (Button) v.findViewById(R.id.button_favourite);

        mToolBar = (Toolbar) v.findViewById(R.id.tool_bar);
        ((ActionBarActivity)getActivity()).setSupportActionBar(mToolBar);

        return v;
    }
}


