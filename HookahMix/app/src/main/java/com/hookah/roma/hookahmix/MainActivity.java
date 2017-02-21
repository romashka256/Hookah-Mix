package com.hookah.roma.hookahmix;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.RatingBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView descriptionTextView;
    private TextView ratingIntTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.ic_launcher);

        RatingBar ratingBar = (RatingBar) findViewById(R.id.ratingBar);

        Drawable drawable = ratingBar.getProgressDrawable();
        drawable.setColorFilter(Color.parseColor("#00c7af"), PorterDuff.Mode.SRC_ATOP);

        ratingIntTextView = (TextView) findViewById(R.id.rating_int);

        Typeface notoSansBoldFont = Typeface.createFromAsset(getAssets(),"fonts/NotoSans-Bold.ttf");
        ratingIntTextView.setTypeface(notoSansBoldFont);


        descriptionTextView = (TextView) findViewById(R.id.tabak_description);
        Typeface notoSansRegularFont = Typeface.createFromAsset(getAssets(),"fonts/NotoSansUI-Regular.ttf");

        descriptionTextView.setTextColor(getResources().getColor(R.color.description));
        descriptionTextView.setTypeface(notoSansRegularFont);


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.tabak_items, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
