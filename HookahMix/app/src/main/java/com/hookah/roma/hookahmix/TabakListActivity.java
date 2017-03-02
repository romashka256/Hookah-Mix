package com.hookah.roma.hookahmix;

import android.support.v4.app.Fragment;


public class TabakListActivity extends SingleFragmentActivity  {

    @Override
    protected Fragment createFragment() {
        return  new TabakListFragment();
    }
}
