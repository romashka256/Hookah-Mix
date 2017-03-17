package com.hookah.roma.hookahmix.ui.activites;

import android.support.v4.app.Fragment;

import com.hookah.roma.hookahmix.ui.fragments.MyMixesFragment;

/**
 * Created by Roma on 16.03.2017.
 */

public class MyMixesActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new MyMixesFragment();
    }
}
