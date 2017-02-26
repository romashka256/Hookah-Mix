package com.hookah.roma.hookahmix;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.List;


public class TabakPagerActivity extends AppCompatActivity {
    private static final String EXTRA_TABAK_NAME = "com.hookah.roma.hookahmix.name";
    private List<Tabak> mTabaks;
    private ViewPager mViewPager;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabak_pager);

        mViewPager = (ViewPager) findViewById(R.id.activity__tabak_pager_view_pager);

        mTabaks = TabakLab.get(this).getTabaks();
        String name = (String) getIntent().getSerializableExtra(EXTRA_TABAK_NAME);

        FragmentManager fm = getSupportFragmentManager();

        mViewPager.setAdapter(new FragmentStatePagerAdapter(fm) {
            @Override
            public Fragment getItem(int position) {
                Tabak tabak = mTabaks.get(position);
                return TabakFragment.newInstance(tabak.getName());
            }

            @Override
            public int getCount() {
                return 0;
            }

        });
        setCurrentItem(name);

    }

    public static Intent newIntent(Context context, String name) {
        Intent intent = new Intent(context, TabakPagerActivity.class);
        intent.putExtra(EXTRA_TABAK_NAME, name);
        return intent;
    }

    public void setCurrentItem(String name) {
        for (int i = 0; i < mTabaks.size(); i++) {
            if (mTabaks.get(i).getName().equals(name)) {
                mViewPager.setCurrentItem(i, true);
                break;
            }
        }
    }
}
