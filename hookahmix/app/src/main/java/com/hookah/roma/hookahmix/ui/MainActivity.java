package com.hookah.roma.hookahmix.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.hookah.roma.hookahmix.R;
import com.hookah.roma.hookahmix.ui.activites.KatalogsActivity;

/**
 * Created by Roma on 29.03.2017.
 */

public class MainActivity extends AppCompatActivity{
    private TextView mNameAppTextView;
    private TextView mKatalogsButton;
    private TextView mRandomTextView;
    private TextView mNearButton;
    private TextView mDescriptionTextView;
    public static int countOfMyTabaks;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        mNearButton = (TextView) findViewById(R.id.main_button_near);
        mRandomTextView = (TextView) findViewById(R.id.main_button_random);
        mKatalogsButton = (TextView) findViewById(R.id.main_button_katalogs);
        mNameAppTextView = (TextView) findViewById(R.id.main_name);
        mDescriptionTextView = (TextView) findViewById(R.id.main_description);
        mDescriptionTextView.setText("У вас " + countOfMyTabaks + " табаков");
        Typeface notoSansBoldFont = Typeface.createFromAsset(getResources().getAssets(), "fonts/10091.otf");
        mNameAppTextView.setTypeface(notoSansBoldFont);
        mNearButton.setTypeface(notoSansBoldFont);
        mRandomTextView.setTypeface(notoSansBoldFont);
        mKatalogsButton.setTypeface(notoSansBoldFont);
        mDescriptionTextView.setTypeface(notoSansBoldFont);


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
                Uri gmmIntentUri = Uri.parse("geo:0,0?q=Кальянная");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW,gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });
    }
}
