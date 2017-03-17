package com.hookah.roma.hookahmix.ui.activites;

import android.support.v4.app.Fragment;

import com.hookah.roma.hookahmix.ui.fragments.TabakListFragment;


public class TabakListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return  new TabakListFragment();
    }
}
