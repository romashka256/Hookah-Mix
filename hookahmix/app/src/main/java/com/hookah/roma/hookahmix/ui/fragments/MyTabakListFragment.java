package com.hookah.roma.hookahmix.ui.fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hookah.roma.hookahmix.R;
import com.hookah.roma.hookahmix.Tabak;
import com.hookah.roma.hookahmix.ui.activites.TabakPagerActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Roma on 16.03.2017.
 */

public class MyTabakListFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private Tabak mTabak;
    private TabakAdapter mAdapter;

    public static MyTabakListFragment newInstance() {
        MyTabakListFragment fragemnt = new MyTabakListFragment();
        return fragemnt;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tabaks_list, container, false);

        mRecyclerView = (RecyclerView) v.findViewById(R.id.lst_tabaks);

        LinearLayoutManager lm = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(lm);

        updateUI();
        return v;
    }


    @Override
    public void onResume() {
        super.onResume();
        updateUI();
        mAdapter.notifyDataSetChanged();
    }

    private void updateUI() {
        List<Tabak> tabaks = TabakListFragment.mMyTabaks;
        if (mAdapter == null) {
            mAdapter = new TabakAdapter(getActivity(),tabaks);
            mRecyclerView.setAdapter(mAdapter);
        }else{
            mAdapter.notifyDataSetChanged();
        }
    }

    private class TabakHolder extends RecyclerView.ViewHolder {
        public TextView mNameTextView;
        public TextView mFamilyTextView;
        public TextView mRatingIntTextView;
        public RelativeLayout mItem;
        public ImageView mImageViewItem;
        public CheckBox mCheckBox;

        public TabakHolder(View itemView) {
            super(itemView);
            mNameTextView = (TextView) itemView.findViewById(R.id.name_textview_item);
            mFamilyTextView = (TextView) itemView.findViewById(R.id.family_textview_item);
            mRatingIntTextView = (TextView) itemView.findViewById(R.id.rating_int_item);
            mImageViewItem = (ImageView) itemView.findViewById(R.id.picture_imageview_item);
            mImageViewItem.setColorFilter(R.color.colorPrimaryea, PorterDuff.Mode.SRC_ATOP);
            mCheckBox = (CheckBox) itemView.findViewById(R.id.checkBox);
            mItem = (RelativeLayout) itemView.findViewById(R.id.list_item);
            mImageViewItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    Tabak tabak = TabakListFragment.mMyTabaks.get(pos);
                    Intent intent = TabakPagerActivity.newIntent(getActivity(), tabak.getName());
                    startActivity(intent);
                }
            });



            mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    Tabak tabak = TabakListFragment.mMyTabaks.get(getAdapterPosition());
                    if (isChecked) {
                        tabak.setIsfavourite("1");
                        mItem.setBackgroundColor(getResources().getColor(R.color.colorToolbar));
                    } else {
                        tabak.setIsfavourite("0");
                        mItem.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    }
                }
            });
        }
    }

    private class TabakAdapter extends RecyclerView.Adapter<TabakHolder> {

        private Context context;
        private List<Tabak> mTabaks;


        public TabakAdapter(Context context,List<Tabak> tabaks) {
            this.context = context;
            mTabaks = tabaks;

        }

        @Override
        public TabakHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            View v = inflater.inflate(R.layout.tabak_item, parent, false);
            return new TabakHolder(v);
        }

        @Override
        public void onBindViewHolder(TabakHolder holder, int position) {
            Tabak tabak = mTabaks.get(position);
            mTabak = tabak;
            int resId = context.getResources().getIdentifier(mTabak.getSecond_name(), "drawable", context.getPackageName());
            holder.mNameTextView.setText(tabak.getName());
            holder.mFamilyTextView.setText(tabak.getFamily());
            holder.mRatingIntTextView.setText(tabak.getRating());
            Typeface notoSansBoldFont = Typeface.createFromAsset(getResources().getAssets(), "fonts/NotoSans-Bold.ttf");
            holder.mRatingIntTextView.setTypeface(notoSansBoldFont);
            if (resId != 0) {
                Picasso.with(context).load(resId).resizeDimen(R.dimen.image_size_item, R.dimen.image_size_item).centerCrop().into(holder.mImageViewItem);
            } else {
                Picasso.with(context).load(R.drawable.al_fakher).resizeDimen(R.dimen.image_size_item, R.dimen.image_size_item).centerCrop().into(holder.mImageViewItem);
            }
        }


        @Override
        public int getItemCount() {
            return TabakListFragment.mMyTabaks.size();
        }

        public void setTabaks(List<Tabak> tabaks) {
            mTabaks = tabaks;
        }
    }
}
