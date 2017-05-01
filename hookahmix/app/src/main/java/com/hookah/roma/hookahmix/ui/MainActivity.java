package com.hookah.roma.hookahmix.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.hookah.roma.hookahmix.JSONHelper;
import com.hookah.roma.hookahmix.R;
import com.hookah.roma.hookahmix.Tabak;
import com.hookah.roma.hookahmix.ui.activites.KatalogsActivity;
import com.hookah.roma.hookahmix.ui.fragments.MyMixesFragment;

import java.util.List;

public class MainActivity extends AppCompatActivity{
    private TextView mNameAppTextView;
    private TextView mKatalogsButton;
    private TextView mRandomTextView;
    private TextView mNearButton;
    private TextView mBuyTabak;
    private List<Tabak> mTabaks;
    private TextView mDescriptionTextView;
    private SharedPreferences prefs;
    private final String PREF_KEY = "count";
    public static int countOfMyTabaks;
    public static final String APP_PREFERENCES = "mysettings";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        prefs = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        countOfMyTabaks = prefs.getInt(PREF_KEY,0);

        mNearButton = (TextView) findViewById(R.id.main_button_near);
        mRandomTextView = (TextView) findViewById(R.id.main_button_random);
        mKatalogsButton = (TextView) findViewById(R.id.main_button_katalogs);
        mNameAppTextView = (TextView) findViewById(R.id.main_name);
        mDescriptionTextView = (TextView) findViewById(R.id.main_description);
        mBuyTabak = (TextView) findViewById(R.id.main_button_asd);
        updateDiscr();
        Typeface notoSansBoldFont = Typeface.createFromAsset(getResources().getAssets(), "fonts/10091.otf");
        mNameAppTextView.setTypeface(notoSansBoldFont);
        mNearButton.setTypeface(notoSansBoldFont);
        mRandomTextView.setTypeface(notoSansBoldFont);
        mKatalogsButton.setTypeface(notoSansBoldFont);
        mDescriptionTextView.setTypeface(notoSansBoldFont);
        mBuyTabak.setTypeface(notoSansBoldFont);

        mRandomTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getSupportFragmentManager();
                MyMixesFragment.RandomMix(MyMixesFragment.mMyMixes,fm);
            }
        });

        mKatalogsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), KatalogsActivity.class);
                startActivity(intent);
            }
        });

        mNearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenGoogleMaps("Кальянная");
            }
        });

        mBuyTabak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenGoogleMaps("Купить табак для кальяна");

            }
        });
    }

    private void OpenGoogleMaps(String query){
        Uri gmmIntentUri = Uri.parse("geo:0,0?q="+ query);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW,gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }

    private void updateDiscr(){
        mDescriptionTextView.setText("У вас " + countOfMyTabaks + " табаков");
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateDiscr();
        mTabaks = JSONHelper.importFromJSON(this);

    }



}
