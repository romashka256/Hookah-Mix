package com.hookah.roma.hookahmix.ui.fragments;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hookah.roma.hookahmix.NavigationTabStrip;
import com.hookah.roma.hookahmix.R;


public class KatalogsFragment extends Fragment{

    private ViewPager mViewPager;
    private NavigationTabStrip mNavigationTabStrip;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_activity, container, false);
        super.onCreate(savedInstanceState);


        Typeface notoSansBoldFont = Typeface.createFromAsset(getResources().getAssets(), "fonts/NotoSans-Bold.ttf");

        TextView mTitle = (TextView) view.findViewById(R.id.textViewofToolBar);
        mTitle.setTypeface(notoSansBoldFont);
        mTitle.setText("HOOKAH MIX");
        mTitle.setTextColor(getResources().getColor(R.color.white));

        mViewPager = (ViewPager) view.findViewById(R.id.vp);
        mNavigationTabStrip = (NavigationTabStrip) view.findViewById(R.id.nts_center);
        mNavigationTabStrip.setTypeface(notoSansBoldFont);
        mNavigationTabStrip.setTitleSize(17);
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.tool_bar_main);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        toolbar.setTitle(" ");
        mViewPager.setOffscreenPageLimit(4);

        mViewPager.setAdapter(new FragmentPagerAdapter(getFragmentManager()) {


            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                        return TabakListFragment.newInstance();
                    case 1:
                        return MixListFragment.newInstance();
                    case 2:
                        return MyTabakListFragment.newInstance();
                    case 3:
                        return MyMixesFragment.newInstance();
                    default:
                        return null;
                }
            }

            @Override
            public int getCount() {
                return 4;
            }
        });
        mNavigationTabStrip.setViewPager(mViewPager, 0);
        mNavigationTabStrip.setAnimationDuration(150);
        return view;
    }
}
