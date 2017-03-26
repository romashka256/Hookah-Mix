package com.hookah.roma.hookahmix.ui.fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.os.Bundle;
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

import com.hookah.roma.hookahmix.JSONHelper;
import com.hookah.roma.hookahmix.R;
import com.hookah.roma.hookahmix.Tabak;
import com.hookah.roma.hookahmix.ui.activites.TabakPagerActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class TabakListFragment extends Fragment {
    private static final String EXTRA_TABAK_NAME = "com.hookah.roma.hookahmix.name";
    private RecyclerView mTabakRecyclerView;
    private Tabak mTabak;
    private TabakAdapter mAdapter;
    private List<Tabak> mTabaks;
    public static List<Tabak> mMyTabaks;

    public static TabakListFragment newInstance() {
        TabakListFragment fragment = new TabakListFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tabaks_list, container, false);
        mTabakRecyclerView = (RecyclerView) view.findViewById(R.id.lst_tabaks);
        setHasOptionsMenu(true);

        mTabaks = JSONHelper.importFromJSON(getActivity());

        mMyTabaks = new ArrayList<>();
        //Toolbar toolbar = (Toolbar) view.findViewById(R.id.tool_bar_list);

        //(((MainActivity) getActivity()).setSupportActionBar(toolbar);
        //(((MainActivity) getActivity()).getSupportActionBar().setTitle("");
        //((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //((MainActivity) getActivity()).getSupportActionBar().setHomeAsUpIndicator(R.mipmap.ic_launcher);

        //TextView mTitle = (TextView) view.findViewById(R.id.textViewofToolBar);
        //mTitle.setText("Страница табаков");

        LinearLayoutManager lm = new LinearLayoutManager(getActivity());
        mTabakRecyclerView.setLayoutManager(lm);

        updateUI();
        return view;

    }


    private void updateUI() {
        List<Tabak> tabakList = JSONHelper.importFromJSON(getActivity());
        if (mAdapter == null) {
            mAdapter = new TabakAdapter(getActivity(), tabakList);
            mTabakRecyclerView.setAdapter(mAdapter);

        } else {
            mAdapter.setTabaks(tabakList);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        JSONHelper.exportToJSON(getActivity(),mTabaks);
    }

    private class TabakHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView mNameTextView;
        public TextView mRatingTextView;
        public TextView mFamilyTextView;
        public RelativeLayout mItem;
        public ImageView mImageViewItem;
        public CheckBox mCheckBox;

        public TabakHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            mNameTextView = (TextView) itemView.findViewById(R.id.name_textview_item);
            mFamilyTextView = (TextView) itemView.findViewById(R.id.family_textview_item);
            mRatingTextView = (TextView) itemView.findViewById(R.id.rating_int_item);
            mImageViewItem = (ImageView) itemView.findViewById(R.id.picture_imageview_item);
            mImageViewItem.setColorFilter(R.color.colorPrimaryea, PorterDuff.Mode.SRC_ATOP);
            mCheckBox = (CheckBox) itemView.findViewById(R.id.checkBox);
            mItem = (RelativeLayout) itemView.findViewById(R.id.list_item);

            mImageViewItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    Tabak tabak = mTabaks.get(pos);
                    Intent intent = TabakPagerActivity.newIntent(getActivity(), tabak.getName());
                    startActivity(intent);
                }
            });

            mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    Tabak tabak = mTabaks.get(getAdapterPosition());
                    if (isChecked) {
                        tabak.setIsfavourite("1");
                        mItem.setBackgroundColor(getResources().getColor(R.color.colorToolbar));
                        mMyTabaks.add(mMyTabaks.size(), tabak);

                    } else {
                        tabak.setIsfavourite(null);
                        mItem.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                        for(Tabak tabaker : mMyTabaks) {
                            if (tabaker.getName().equals(tabak.getName())) {
                                mMyTabaks.remove(tabaker);
                            }
                        }
                    }
                    mAdapter.setTabaks(mTabaks);
                }
            });
        }



        @Override
        public void onClick(View v) {
            bindngCheckBoxes(v);
        }

        public void bindngCheckBoxes(View v) {
            Tabak tabak = mTabaks.get(getAdapterPosition());
            mCheckBox.setChecked(!mCheckBox.isChecked());
            if (mCheckBox.isChecked()) {
                tabak.setIsfavourite("1");
                v.setBackgroundColor(getResources().getColor(R.color.colorToolbar));
            } else {
                v.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                tabak.setIsfavourite(null);

            }
        }
    }

    public class TabakAdapter extends RecyclerView.Adapter<TabakHolder> {
        public List<Tabak> mTabaks;
        private Context context;

        public TabakAdapter(Context context, List<Tabak> tabaks) {
            this.context = context;
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
            Tabak tabak = mTabaks.get(position);
            mTabak = tabak;
            int resId = context.getResources().getIdentifier(mTabak.getSecond_name(), "drawable", context.getPackageName());
            holder.mNameTextView.setText(tabak.getName());
            holder.mFamilyTextView.setText(tabak.getFamily());
            holder.mRatingTextView.setText(tabak.getRating());
            Typeface notoSansBoldFont = Typeface.createFromAsset(getResources().getAssets(), "fonts/NotoSans-Bold.ttf");
            holder.mRatingTextView.setTypeface(notoSansBoldFont);
            if (resId != 0) {
                Picasso.with(context).load(resId).resizeDimen(R.dimen.image_size_item, R.dimen.image_size_item).centerCrop().into(holder.mImageViewItem);
            } else {
                Picasso.with(context).load(R.drawable.al_fakher).resizeDimen(R.dimen.image_size_item, R.dimen.image_size_item).centerCrop().into(holder.mImageViewItem);
            }
            if (mTabak.isfavourite() != null) {
                holder.mCheckBox.setChecked(true);
                holder.mItem.setBackgroundColor(getResources().getColor(R.color.colorToolbar));
            } else {
                holder.mCheckBox.setChecked(false);
                holder.mItem.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            }
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
