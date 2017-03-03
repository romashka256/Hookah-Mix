package com.hookah.roma.hookahmix.ui;

import android.support.v4.app.Fragment;

/**
 * Created by Roma on 02.03.2017.
 */

public class MixActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new MixListFragment();
    }
}
