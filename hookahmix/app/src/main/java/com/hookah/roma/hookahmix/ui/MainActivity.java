package com.hookah.roma.hookahmix.ui;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.hookah.roma.hookahmix.NavigationTabStrip;
import com.hookah.roma.hookahmix.R;
import com.hookah.roma.hookahmix.ui.fragments.MixListFragment;
import com.hookah.roma.hookahmix.ui.fragments.MyTabakListFragment;
import com.hookah.roma.hookahmix.ui.fragments.TabakListFragment;


public class MainActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private NavigationTabStrip mNavigationTabStrip;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        Typeface notoSansBoldFont = Typeface.createFromAsset(getResources().getAssets(), "fonts/NotoSans-Bold.ttf");

        TextView mTitle = (TextView) findViewById(R.id.textViewofToolBar);
        mTitle.setTypeface(notoSansBoldFont);
        mTitle.setText("HOOKAH MIX");
        mTitle.setTextColor(getResources().getColor(R.color.white));

        mViewPager = (ViewPager) findViewById(R.id.vp);
        mNavigationTabStrip = (NavigationTabStrip) findViewById(R.id.nts_center);
        mNavigationTabStrip.setTypeface(notoSansBoldFont);
        mNavigationTabStrip.setTitleSize(17);

        mViewPager.setOffscreenPageLimit(4);

        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {


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
                        return MixListFragment.newInstance();
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
    }
}
