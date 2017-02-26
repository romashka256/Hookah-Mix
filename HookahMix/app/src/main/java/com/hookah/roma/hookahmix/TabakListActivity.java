package com.hookah.roma.hookahmix;

import android.support.v4.app.Fragment;

/**
 * Created by Roma on 23.02.2017.
 */

public class TabakListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return  new TabakListFragment();
    }
}
