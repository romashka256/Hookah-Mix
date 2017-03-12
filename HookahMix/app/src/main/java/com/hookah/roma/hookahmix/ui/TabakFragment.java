package com.hookah.roma.hookahmix.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

import com.hookah.roma.hookahmix.Mix;
import com.hookah.roma.hookahmix.R;
import com.hookah.roma.hookahmix.Tabak;
import com.hookah.roma.hookahmix.TabakLab;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

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
    private TextView mMixWithTextView;
    private RecyclerView mRecyclerView;
    private List<Mix> mMixes;
    private List<Mix> mResult;
    private TabakLab mTabakLab;
    private Mix mMix;
    private MixWithTabakAdapter mAdapter;
    private String tabakName;
    public static final String EXTRA_TABAK_NAME = "com.hookah.roma.hookahmix.name";
    private static final String DIALOG_MIX = "DialogMix";
    private static final int REQUEST_MIX = 0;

    public static TabakFragment newInstance(String name) {
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_TABAK_NAME, name);
        TabakFragment fragment = new TabakFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        mTabakLab = TabakLab.get(getActivity());
        tabakName = (String) getArguments().get(EXTRA_TABAK_NAME);
        mTabak = TabakLab.get(getActivity()).getTabak(tabakName);
        mMixes = mTabakLab.getMixes();
        AsynkTask task = new AsynkTask();
        task.execute();

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.tabak_items, menu);
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tabak_fragment, container, false);
        context = getActivity();
        int resId = context.getResources().getIdentifier(mTabak.getSecond_name(), "drawable", context.getPackageName());

        mNameTextView = (TextView) v.findViewById(R.id.tabak_name);
        mNameTextView.setText(mTabak.getName());

        LinearLayoutManager lm = new LinearLayoutManager(getActivity());
        lm.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView = (RecyclerView) v.findViewById(R.id.list_view_fragment_tabak);
        mRecyclerView.setLayoutManager(lm);

        if (mAdapter == null) {
            mAdapter = new TabakFragment.MixWithTabakAdapter(mResult);
            mRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.setMixes(mResult);
        }

        mDescriptionTextView = (TextView) v.findViewById(R.id.tabak_description);
        mDescriptionTextView.setText(mTabak.getDescription());

        mMixWithTextView = (TextView) v.findViewById(R.id.mixWithTextView);
        mMixWithTextView.setText("МИКСЫ С  \"" + mTabak.getName().toUpperCase() + "\"");

        mFamilyTextView = (TextView) v.findViewById(R.id.tabak_family);
        mFamilyTextView.setText(mTabak.getFamily());

        mRatingBarInt = (TextView) v.findViewById(R.id.rating_int);

        mRatingBar = (RatingBar) v.findViewById(R.id.ratingBar);
        mRatingBar.setProgress(5);
        Drawable drawable = mRatingBar.getProgressDrawable();
        drawable.setColorFilter(Color.parseColor("#00c7af"), PorterDuff.Mode.SRC_ATOP);

        mFavouriteButton = (Button) v.findViewById(R.id.button_favourite);



        Drawable drawableRating = mRatingBar.getProgressDrawable();
        drawableRating.setColorFilter(Color.parseColor("#00c7af"), PorterDuff.Mode.SRC_ATOP);

        mRatingBarInt = (TextView) v.findViewById(R.id.rating_int);

        Typeface notoSansBoldFont = Typeface.createFromAsset(getResources().getAssets(), "fonts/NotoSans-Bold.ttf");
        mRatingBarInt.setTypeface(notoSansBoldFont);

        Typeface notoSansRegularFont = Typeface.createFromAsset(getResources().getAssets(), "fonts/NotoSansUI-Regular.ttf");

        mDescriptionTextView.setTextColor(getResources().getColor(R.color.description));
        mDescriptionTextView.setTypeface(notoSansRegularFont);

        mImageView = (ImageView) v.findViewById(R.id.imageView);
        Picasso.with(getActivity()).load(resId).into(mImageView);
        mImageView.setColorFilter(R.color.colorPrimaryea, PorterDuff.Mode.DARKEN);


        mFavouriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MixActivity.class);
                startActivity(intent);
            }
        });

        //  updateUI();

        return v;
    }
   /* private void updateUI() {
        TabakLab mixLab = TabakLab.get(getActivity());
        List<Mix> mixList = mixLab.getMixes();
        if (mAdapter == null) {
            mAdapter = new TabakFragment.MixWithTabakAdapter(getActivity(), mixList);
            mRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.setMixes(mixList);
        }} */

    private class MixWithTabakHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView mNameTextView;
        private TextView mFamilyTextView;
        private TextView mRatingTextView;

        public MixWithTabakHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            mNameTextView = (TextView) itemView.findViewById(R.id.name_textview_item_tabak_fragment);
            mFamilyTextView = (TextView) itemView.findViewById(R.id.family_textview_item_tabak_fragment);
            mRatingTextView = (TextView) itemView.findViewById(R.id.rating_textview_item_tabak_fragment);
        }

        public void bindViews() {
            if (mMix.getIngred3() == null) {
                mNameTextView.setText(mMix.getIngred1() + ", " + mMix.getIngred2());
            } else if (mMix.getIngred4() == null) {
                mNameTextView.setText(mMix.getIngred1() + ", " + mMix.getIngred2() + ", " + mMix.getIngred3());
            } else {
                mNameTextView.setText(mMix.getIngred1() + ", " + mMix.getIngred2() + ", " + mMix.getIngred3() + ", " + mMix.getIngred4());
            }
        }

        @Override
        public void onClick(View v) {
            mMix = mMixes.get(getPosition());
            FragmentManager fm = getFragmentManager();
            MixDialogFragment dialog = MixDialogFragment.newInstance(mMix.getDescription());
            dialog.setTargetFragment(TabakFragment.this,REQUEST_MIX);
            dialog.show(fm,DIALOG_MIX);
        }
    }

    private class MixWithTabakAdapter extends RecyclerView.Adapter<MixWithTabakHolder> {
        public List<Mix> mMixes;

        public MixWithTabakAdapter(List<Mix> mixes) {
            mMixes = mixes;
        }

        @Override
        public MixWithTabakHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            View view = inflater.inflate(R.layout.list_tabak_fragment_item, parent, false);
            return new MixWithTabakHolder(view);
        }

        @Override
        public void onBindViewHolder(MixWithTabakHolder holder, int position) {
            Mix mix = mMixes.get(position);
            mMix = mix;
            holder.bindViews();
            holder.mFamilyTextView.setText(mix.getFamily());
            holder.mRatingTextView.setText(mix.getRating());

        }

        public void setMixes(List<Mix> mixes) {
            mMixes = mixes;
        }


        @Override
        public int getItemCount() {
            return mMixes.size();
        }
    }

    private class AsynkTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            mResult = new ArrayList<>();

            for (Mix mix : mMixes) {
                if (mix.getIngred3() == null) {
                    if (mix.getIngred1().equals(tabakName) || mix.getIngred2().equals(tabakName)) {
                        mResult.add(mix);
                    }
                } else if (mix.getIngred4() == null) {
                    if (mix.getIngred1().equals(tabakName) || mix.getIngred2().equals(tabakName) || mix.getIngred3().equals(tabakName)) {
                        mResult.add(mix);
                    }
                } else {
                    if (mix.getIngred1().equals(tabakName) || mix.getIngred2().equals(tabakName) || mix.getIngred3().equals(tabakName) || mix.getIngred4().equals(tabakName)) {
                        mResult.add(mix);
                    }
                }
            }
            return null;
        }
    }
}




