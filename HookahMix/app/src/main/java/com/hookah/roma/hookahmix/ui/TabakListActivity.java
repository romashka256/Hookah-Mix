package com.hookah.roma.hookahmix.ui;

import android.support.v4.app.Fragment;


public class TabakListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return  new TabakListFragment();
    }
}
