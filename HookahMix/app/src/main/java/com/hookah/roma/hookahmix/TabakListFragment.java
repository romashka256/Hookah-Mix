package com.hookah.roma.hookahmix;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class TabakListFragment extends Fragment {
    private static final String EXTRA_TABAK_NAME = "com.hookah.roma.hookahmix.name";
    private RecyclerView mTabakRecyclerView;
    private Tabak mTabak;
    private TabakAdapter mAdapter;
    private ImageView mImageViewItem;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tabaks_list, container, false);
        mTabakRecyclerView = (RecyclerView) view.findViewById(R.id.lst_tabaks);

        LinearLayoutManager lm = new LinearLayoutManager(getActivity());
        mTabakRecyclerView.setLayoutManager(lm);

        updateUI();
        return view;

    }

    private void updateUI() {
        TabakLab tabakLab = TabakLab.get(getActivity());
        List<Tabak> tabakList = tabakLab.getTabaks();
        if (mAdapter == null) {
            mAdapter = new TabakAdapter(tabakList);
            mTabakRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.setTabaks(tabakList);
        }
    }

    private class TabakHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView mNameTextView;
        public TextView mRatingTextView;
        public TextView mFamilyTextView;
        public ImageView mImageViewItem;
        public CheckBox mCheckBox;

        public TabakHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            mNameTextView = (TextView) itemView.findViewById(R.id.name_textview_item);
            mFamilyTextView = (TextView) itemView.findViewById(R.id.family_textview_item);
            mRatingTextView = (TextView) itemView.findViewById(R.id.rating_int_item);
            mImageViewItem = (ImageView) itemView.findViewById(R.id.picture_imageview_item);
            mCheckBox = (CheckBox) itemView.findViewById(R.id.checkBox);
        }

        public void bindTabaks(Tabak tabak) {
            mTabak = tabak;
            mNameTextView.setText(tabak.getName());
            mFamilyTextView.setText(tabak.getFamily());
            mRatingTextView.setText(tabak.getRating());
            mCheckBox.setChecked(false);
            mImageViewItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = TabakPagerActivity.newIntent(getActivity(), mTabak.getName());
                    startActivity(intent);
                }
            });
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.picture_imageview_item) {
                Intent intent = TabakPagerActivity.newIntent(getActivity(), mTabak.getName());
                startActivity(intent);
            } else {
                mCheckBox.setChecked(!mCheckBox.isChecked());
                if (mCheckBox.isChecked()) {
                    v.setBackgroundColor(getResources().getColor(R.color.colorToolbar));
                } else {
                    v.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                }
            }
        }
    }

        public class TabakAdapter extends RecyclerView.Adapter<TabakHolder> {
            private List<Tabak> mTabaks;

            public TabakAdapter(List<Tabak> tabaks) {
                mTabaks = tabaks;
            }


            @Override
            public TabakHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                LayoutInflater inflater = LayoutInflater.from(getActivity());
                View view = inflater.inflate(R.layout.tabak_item, parent, false);
                return new TabakHolder(view);

            }

            @Override
            public void onBindViewHolder(TabakHolder holder, int position) {
                position = holder.getAdapterPosition();
                Tabak tabak = mTabaks.get(position);
                holder.bindTabaks(tabak);
            }

            public void setTabaks(List<Tabak> tabaks) {
                mTabaks = tabaks;
            }

            @Override
            public int getItemCount() {
                return mTabaks.size();
            }
        }
    }
