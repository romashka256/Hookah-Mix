package com.hookah.roma.hookahmix.ui.fragments;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hookah.roma.hookahmix.R;

/**
 * Created by Roma on 16.03.2017.
 */

public class MyMixesFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private TabakAdapter mAdapter;

    public static MyMixesFragment newInstance() {
        MyMixesFragment fragment = new MyMixesFragment();
        return fragment;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.tabak_items, menu);
    }

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tabaks_list,container);

        mRecyclerView = (RecyclerView) v.findViewById(R.id.lst_tabaks);

        LinearLayoutManager lm = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(lm);
        mAdapter = new TabakAdapter();
        mRecyclerView.setAdapter(mAdapter);


        return v;
    }

    private class TabakHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView mNameTextView;
        public TextView mRatingTextView;
        public TextView mFamilyTextView;
        public RelativeLayout mItem;
        public ImageView mImageViewItem;

        public TabakHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            mNameTextView = (TextView) itemView.findViewById(R.id.name_textview_item);
            mFamilyTextView = (TextView) itemView.findViewById(R.id.family_textview_item);
            mRatingTextView = (TextView) itemView.findViewById(R.id.rating_int_item);
            mImageViewItem = (ImageView) itemView.findViewById(R.id.picture_imageview_item);
            mImageViewItem.setColorFilter(R.color.colorPrimaryea, PorterDuff.Mode.SRC_ATOP);
            mItem = (RelativeLayout) itemView.findViewById(R.id.list_item);
        }

        @Override
        public void onClick(View v) {

        }
    }
    private class TabakAdapter extends RecyclerView.Adapter<TabakHolder>{

        @Override
        public TabakHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            View v = inflater.inflate(R.layout.tabak_item,parent,false);
            return new TabakHolder(v);
        }

        @Override
        public void onBindViewHolder(TabakHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 0;
        }
    }

}
