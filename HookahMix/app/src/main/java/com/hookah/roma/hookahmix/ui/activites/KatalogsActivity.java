package com.hookah.roma.hookahmix.ui.activites;

import android.support.v4.app.Fragment;

import com.hookah.roma.hookahmix.ui.fragments.KatalogsFragment;

/**
 * Created by Roma on 29.03.2017.
 */

public class KatalogsActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new KatalogsFragment();
    }
}
