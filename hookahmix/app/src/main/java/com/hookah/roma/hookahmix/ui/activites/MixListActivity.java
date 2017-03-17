package com.hookah.roma.hookahmix.ui.activites;

import android.support.v4.app.Fragment;

import com.hookah.roma.hookahmix.ui.fragments.MixListFragment;

/**
 * Created by Roma on 02.03.2017.
 */

public class MixListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new MixListFragment();
    }
}
