package com.hookah.roma.hookahmix.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.hookah.roma.hookahmix.R;
import com.hookah.roma.hookahmix.ui.activites.KatalogsActivity;

/**
 * Created by Roma on 29.03.2017.
 */

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        TextView mNameAppTextView;
        Button mKatalogsButton;
        Button mHowToButton;
        Button mNearButton;

        mNearButton = (Button) findViewById(R.id.near_button);
        mKatalogsButton = (Button) findViewById(R.id.katalogs_button);
        mNameAppTextView = (TextView) findViewById(R.id.name_app_textview);
        Typeface notoSansBoldFont = Typeface.createFromAsset(getResources().getAssets(), "fonts/NotoSans-Bold.ttf");
        mNameAppTextView.setTypeface(notoSansBoldFont);

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
