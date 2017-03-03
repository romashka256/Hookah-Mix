package com.hookah.roma.hookahmix.ui;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import com.hookah.roma.hookahmix.Mix;
import com.hookah.roma.hookahmix.R;
import com.hookah.roma.hookahmix.TabakLab;

import java.util.List;



public class MixListFragment extends Fragment {
    private RecyclerView mMixRecyclerView;
    private Mix mMix;
    private List<Mix> mMixes;
    private MixAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mix_list,container,false);
        mMixRecyclerView = (RecyclerView) view.findViewById(R.id.lst_mixes);
        setHasOptionsMenu(true);
        mMixes = TabakLab.get(getActivity()).getMixes();

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
            mAdapter = new MixAdapter(getActivity(),mixList);
            mMixRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.setMixes(mixList);
        }
    }

    private class MixHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView mNameTextView;
        public TextView mFamilyTextView;
        public TextView mRatingIntTextView;
        public RatingBar mRatingBar;
        public Button mButton;

        public MixHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            mNameTextView = (TextView) itemView.findViewById(R.id.mix_name_item);
            mFamilyTextView = (TextView) itemView.findViewById(R.id.mix_family_item);
            mRatingIntTextView = (TextView) itemView.findViewById(R.id.mix_ratingbar_int_item);
            mRatingBar = (RatingBar) itemView.findViewById(R.id.mix_ratingbar_item);
            mButton = (Button) itemView.findViewById(R.id.mix_button_item);
        }

        public void bindViews(){
            if(mMix.getIngred3() == null){
                mNameTextView.setText(mMix.getIngred1() +", " + mMix.getIngred2());
            }else if(mMix.getIngred4() == null){
                mNameTextView.setText(mMix.getIngred1() +", " + mMix.getIngred2() + ", " + mMix.getIngred3());
            }else {
                mNameTextView.setText(mMix.getIngred1() + ", " + mMix.getIngred2() + ", " + mMix.getIngred3() + ", " + mMix.getIngred4());
            }
        }

        @Override
        public void onClick(View v) {

        }
    }

    private class MixAdapter extends RecyclerView.Adapter<MixHolder>{
        public List<Mix> mMixes;
        private Context context;

        public MixAdapter(Context context,List<Mix> mixes){
            mMixes = mixes;
        }
        @Override
        public MixHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            View view = inflater.inflate(R.layout.mix_item,parent,false);
            return new MixHolder(view);
        }


        @Override
        public void onBindViewHolder(MixHolder holder, int position) {
            Mix mix = mMixes.get(position);
            mMix = mix;
            holder.bindViews();
            holder.mFamilyTextView.setText(mix.getFamily());
            holder.mRatingIntTextView.setText(mix.getRating());
            Typeface notoSansBoldFont = Typeface.createFromAsset(getResources().getAssets(),"fonts/NotoSans-Bold.ttf");
            holder.mRatingIntTextView.setTypeface(notoSansBoldFont);
        }

        public void setMixes(List<Mix> mixes){mMixes = mixes; }

        @Override
        public int getItemCount() {
            return mMixes.size();
        }
    }
}
