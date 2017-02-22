package com.hookah.roma.hookahmix.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.widget.TextView;

import com.hookah.roma.hookahmix.tasks.LoatInitial;
import com.hookah.roma.hookahmix.R;
import com.hookah.roma.hookahmix.tasks.ReadFromDatabase;
import com.hookah.roma.hookahmix.models.objects.Tabak;

import java.io.File;
import java.util.ArrayList;

public class TabakActivity extends AppCompatActivity implements FragmentTabakList.FragmentItemClickCallback,
        TabakFragment.FragmentEditItemCallback {

    private TextView descriptionTextView;
    private TextView ratingIntTextView;
    private ArrayList<Tabak> listData;
    private FragmentManager manager;

    private static final String FRAG_TABAK_LIST = "FRAG_TABAK_LIST";
    private static final String FRAG_TABAK = "FRAG_TABAK";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabak);
        manager = getSupportFragmentManager();

        File database = getApplicationContext().getDatabasePath("tabaks.db");

        if(!database.exists()) {
            LoatInitial loader = new LoatInitial(getApplicationContext());
            loader.setOnDatabaseBuilt(new LoatInitial.OnDatabaseBuilt() {
                @Override
                public void buildComplete() {
                    loadTabakList();
                }
            });
            loader.execute();
        }else{
            loadTabakList();
        }
        }

        private void loadTabakList(){
        ReadFromDatabase reader = new ReadFromDatabase(getApplicationContext());
        reader.setQueryCompleteListener(new ReadFromDatabase.OnQueryComplete() {
            @Override
            public void setQueryComplete(ArrayList result) {
                listData = result;
                loadListFragment();
            }
        });
        reader.execute();
    }

    private void loadListFragment(){
        Fragment listFrag = FragmentTabakList.newInstance(listData);
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);

        transaction.replace(R.id.cont_tabak_fragments,listFrag,FRAG_TABAK_LIST);
        transaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.tabak_items, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public void onBackPressed() {
        Fragment listFrag = manager.findFragmentByTag(FRAG_TABAK_LIST);
        if (listFrag == null) {
            loadListFragment();
        }
    }

    @Override
    public void onListItemClicked(int position) {
        Fragment tabakfragment = TabakFragment.newIntance(listData.get(position));
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);

        transaction.replace(R.id.cont_tabak_fragments, tabakfragment, FRAG_TABAK);
        transaction.commit();
    }

    @Override
    public void onEditButtonClick(Tabak tabak) {

    }
}
