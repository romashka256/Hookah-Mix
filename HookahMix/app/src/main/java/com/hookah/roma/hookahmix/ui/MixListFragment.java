package com.hookah.roma.hookahmix.ui;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.hookah.roma.hookahmix.HeightAnim;
import com.hookah.roma.hookahmix.Mix;
import com.hookah.roma.hookahmix.R;
import com.hookah.roma.hookahmix.TabakLab;

import java.util.List;


public class MixListFragment extends Fragment {
    private RecyclerView mMixRecyclerView;
    private Mix mMix;
    private List<Mix> mMixes;
    private MixAdapter mAdapter;
    private HeightAnim heightAnim;
    private boolean sad;
    private Animation rotatingarrow;
    private TranslateAnimation animationDetails;
    private int countOfitemsDetails;

    private boolean[] isExpended;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mix_list, container, false);
        mMixRecyclerView = (RecyclerView) view.findViewById(R.id.lst_mixes);

        rotatingarrow = AnimationUtils.loadAnimation(getContext(), R.anim.rotatingimageview);
        rotatingarrow.setDuration(150);

        isExpended = new boolean[13];

        setHasOptionsMenu(true);

        mMixes = TabakLab.get(getActivity()).getMixes();

        sad = false;

        Toolbar toolbar = (Toolbar) view.findViewById(R.id.tool_bar_list);

        ((MixActivity) getActivity()).setSupportActionBar(toolbar);
        ((MixActivity) getActivity()).getSupportActionBar().setTitle("");
        ((MixActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((MixActivity) getActivity()).getSupportActionBar().setHomeAsUpIndicator(R.mipmap.ic_launcher);

        TextView mTitle = (TextView) view.findViewById(R.id.textViewofToolBar);
        mTitle.setText("Страница миксов");

        LinearLayoutManager lm = new LinearLayoutManager(getActivity());
        mMixRecyclerView.setLayoutManager(lm);

        updateUI();


        return view;
    }


    private void updateUI() {
        TabakLab mixLab = TabakLab.get(getActivity());
        List<Mix> mixList = mixLab.getMixes();
        if (mAdapter == null) {
            mAdapter = new MixAdapter(getActivity(), mixList);
            mMixRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.setMixes(mixList);
        }
    }

    private class MixHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView mNameTextView;
        public View mDetails;
        public TextView mFamilyTextView;
        public TextView mRatingIntTextView;
        public RatingBar mRatingBar;
        public ImageView mButton;
        public View mSeparator;
        public TextView ingred1;
        public TextView ingred2;
        public TextView ingred3;
        public TextView ingred4;
        public TextView proc1;
        public TextView proc2;
        public TextView proc3;
        public TextView proc4;
        public TextView description;
        public LinearLayout mIngred3;
        public LinearLayout mIngred4;

        public MixHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            Typeface notoSansBoldFont = Typeface.createFromAsset(getResources().getAssets(), "fonts/NotoSans-Bold.ttf");

            mIngred3 = (LinearLayout) itemView.findViewById(R.id.layout_ingred3);
            mIngred4 = (LinearLayout) itemView.findViewById(R.id.layout_ingred4);
            mNameTextView = (TextView) itemView.findViewById(R.id.mix_name_item);
            mFamilyTextView = (TextView) itemView.findViewById(R.id.mix_family_item);
            mRatingIntTextView = (TextView) itemView.findViewById(R.id.mix_ratingbar_int_item);
            mRatingBar = (RatingBar) itemView.findViewById(R.id.mix_ratingbar_item);
            Drawable drawable = mRatingBar.getProgressDrawable();
            drawable.setColorFilter(Color.parseColor("#00c7af"), PorterDuff.Mode.SRC_ATOP);
            mButton = (ImageView) itemView.findViewById(R.id.mix_button_item);
            mSeparator = itemView.findViewById(R.id.separator_mix_list);
            mDetails = itemView.findViewById(R.id.include_details_mix);
            ingred1 = (TextView) itemView.findViewById(R.id.ingred1);
            ingred2 = (TextView) itemView.findViewById(R.id.ingred2);
            ingred3 = (TextView) itemView.findViewById(R.id.ingred3);
            ingred4 = (TextView) itemView.findViewById(R.id.ingred4);
            proc1 = (TextView) itemView.findViewById(R.id.proc1);
            proc1.setTypeface(notoSansBoldFont);
            proc2 = (TextView) itemView.findViewById(R.id.proc2);
            proc2.setTypeface(notoSansBoldFont);
            proc3 = (TextView) itemView.findViewById(R.id.proc3);
            proc3.setTypeface(notoSansBoldFont);
            proc4 = (TextView) itemView.findViewById(R.id.proc4);
            proc4.setTypeface(notoSansBoldFont);
            description = (TextView) itemView.findViewById(R.id.description_mix_details);
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
            animationDetails = new TranslateAnimation(v.getX(), v.getX(), v.getPivotY() + 80, v.getPivotY() + 90);
            animationDetails.setDuration(200);
            animationDetails.setFillAfter(true);
            mMix = mMixes.get(getAdapterPosition());
            int coord = 300;
            fillDetails();
            switch (countOfitemsDetails){
                case 2 :
                    coord = 210;
                    break;
                case 3 :
                    coord =255;
                    break;
                case 4 :
                    coord =280;
                    break;
            }
            mDetails.bringToFront();

            if (isExpended[getAdapterPosition()] == false) {
                mDetails.setVisibility(View.VISIBLE);
                mDetails.startAnimation(animationDetails);
                heightAnim = new HeightAnim(mSeparator, 1,coord);
                heightAnim.setDuration(200);
                heightAnim.setFillAfter(true);
                mSeparator.startAnimation(heightAnim);
                rotatingarrow.setFillAfter(true);
                mButton.startAnimation(rotatingarrow);
                isExpended[getAdapterPosition()] = true;
            } else {
                isExpended[getAdapterPosition()] = false;
                heightAnim = new HeightAnim(mSeparator, coord, 2);
                heightAnim.setDuration(200);
                heightAnim.setFillAfter(true);
                mSeparator.startAnimation(heightAnim);
                rotatingarrow.setFillAfter(true);
                mButton.startAnimation(rotatingarrow);
                mDetails.setVisibility(View.GONE);
            }
        }

        public void fillDetails() {
            if (mMix.getIngred3() == null) {
                ingred1.setText(mMix.getIngred1());
                ingred2.setText(mMix.getIngred2());
                proc1.setText(mMix.getProc1() + "%");
                proc2.setText(mMix.getProc2() + "%");
                mIngred3.setVisibility(View.INVISIBLE);
                mIngred4.setVisibility(View.INVISIBLE);
                description.setText(mMix.getDescription());
                countOfitemsDetails = 2;
            } else if (mMix.getIngred4() == null) {
                ingred1.setText(mMix.getIngred1());
                ingred2.setText(mMix.getIngred2());
                ingred3.setText(mMix.getIngred3());
                proc1.setText(mMix.getProc1() + "%");
                proc2.setText(mMix.getProc2() + "%");
                proc3.setText(mMix.getProc3() + "%");
                mIngred4.setVisibility(View.INVISIBLE);
                description.setText(mMix.getDescription());
                countOfitemsDetails = 3;
            } else {
                ingred1.setText(mMix.getIngred1());
                ingred2.setText(mMix.getIngred2());
                ingred3.setText(mMix.getIngred3());
                ingred4.setText(mMix.getIngred4());
                proc1.setText(mMix.getProc1() + "%");
                proc2.setText(mMix.getProc2() + "%");
                proc3.setText(mMix.getProc3() + "%");
                proc4.setText(mMix.getProc4() + "%");
                description.setText(mMix.getDescription());
                countOfitemsDetails = 4;
            }
        }
    }

    private class MixAdapter extends RecyclerView.Adapter<MixHolder> {
        public List<Mix> mMixes;
        private Context context;

        public MixAdapter(Context context, List<Mix> mixes) {
            mMixes = mixes;
        }

        @Override
        public MixHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            View view = inflater.inflate(R.layout.mix_item, parent, false);
            return new MixHolder(view);
        }

        @Override
        public void onBindViewHolder(MixHolder holder, int position) {

            Mix mix = mMixes.get(position);
            mMix = mix;
            holder.bindViews();
            holder.mFamilyTextView.setText(mix.getFamily());
            holder.mRatingIntTextView.setText(mix.getRating());
            Typeface notoSansBoldFont = Typeface.createFromAsset(getResources().getAssets(), "fonts/NotoSans-Bold.ttf");
            holder.mRatingIntTextView.setTypeface(notoSansBoldFont);
        }

        public void setMixes(List<Mix> mixes) {
            mMixes = mixes;
        }

        @Override
        public int getItemCount() {
            return mMixes.size();
        }
    }
}
