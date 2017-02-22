package com.hookah.roma.hookahmix.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import com.hookah.roma.hookahmix.R;
import com.hookah.roma.hookahmix.models.objects.Tabak;


public class TabakFragment extends Fragment {
    private static final String TABAK = "TABAK";
    private TextView name,description,rating,family;
    private RatingBar ratingBar;
    private FragmentEditItemCallback callback;
    private Tabak tabak;
    private Button button_favourite;

    public TabakFragment(){

    }

    public static  TabakFragment newIntance(Tabak tabak){
        TabakFragment fragment = new TabakFragment();
        Bundle args = new Bundle();
        args.putParcelable(TABAK,tabak);
        return fragment;
    }

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
            this.tabak = getArguments().getParcelable(TABAK);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_main,container,false);

        name = (TextView) v.findViewById(R.id.tabak_name);
        description = (TextView) v.findViewById(R.id.tabak_description);
        family = (TextView) v.findViewById(R.id.tabak_family);
        ratingBar = (RatingBar) v.findViewById(R.id.ratingBar);
        rating = (TextView) v.findViewById(R.id.rating_int);
        button_favourite = (Button) v.findViewById(R.id.button_favourite);
        Toolbar toolbar = (Toolbar) v.findViewById(R.id.tool_bar);
        /*setSupportActionBar(toolbar);
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
        descriptionTextView.setTypeface(notoSansRegularFont);*/

        return  v;
    }

    @Override
    public void onActivityCreated( Bundle savedInstanceState) {
        name.setText(tabak.getName());
        description.setText(tabak.getDescription());
        family.setText(tabak.getFamily());
        rating.setText(tabak.getRating());
        ratingBar.setProgress(Integer.parseInt(tabak.getRating()));
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FragmentEditItemCallback) {
            callback = (FragmentEditItemCallback) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement callback");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        callback = null;
    }

    public interface FragmentEditItemCallback {
        void onEditButtonClick(Tabak tabak);
    }

}
