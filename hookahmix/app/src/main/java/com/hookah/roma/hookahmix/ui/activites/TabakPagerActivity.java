package com.hookah.roma.hookahmix.ui.activites;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.hookah.roma.hookahmix.JSONHelper;
import com.hookah.roma.hookahmix.R;
import com.hookah.roma.hookahmix.Tabak;
import com.hookah.roma.hookahmix.ui.fragments.TabakFragment;

import java.util.List;


public class TabakPagerActivity extends AppCompatActivity {
    private static final String EXTRA_TABAK_NAME = "com.hookah.roma.hookahmix.name";
    private List<Tabak> mTabaks;
    private Tabak mCurTabak;
    private Toolbar mToolBar;

    @Override
    protected void onStart() {
        super.onStart();


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabak_pager);

        mToolBar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(mToolBar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.ic_launcher);

        ViewPager mViewPager = (ViewPager) findViewById(R.id.activity__tabak_pager_view_pager);

        mTabaks = JSONHelper.importFromJSON(getApplicationContext());
        String name = (String) getIntent().getSerializableExtra(EXTRA_TABAK_NAME);
        FragmentManager fm = getSupportFragmentManager();

        mViewPager.setAdapter(new FragmentStatePagerAdapter(fm) {
            @Override
            public int getCount() {
                return mTabaks.size();
            }
            @Override
            public Fragment getItem(int position) {
                Tabak tabak = mTabaks.get(position);
                return TabakFragment.newInstance(tabak.getName());
            }
        });
        for (int i = 0; i < mTabaks.size(); i++) {
            if (mTabaks.get(i).getName().equals(name)) {
                mViewPager.setCurrentItem(i, true);
                break;
            }
        }
    }

    public static Intent newIntent(Context context, String name) {
        Intent intent = new Intent(context, TabakPagerActivity.class);
        intent.putExtra(EXTRA_TABAK_NAME, name);
        return intent;
    }
}