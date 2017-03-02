package com.hookah.roma.hookahmix;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;


public abstract class SingleFragmentActivity extends AppCompatActivity {

    protected abstract Fragment createFragment();

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabak);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.cont_tabak_fragments);

        if(fragment == null){
            fragment = createFragment();
            fm.beginTransaction()
                    .add(R.id.cont_tabak_fragments,fragment)
                    .commit();
        }
    }
}
