package com.hookah.roma.hookahmix.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.hookah.roma.hookahmix.Mix;
import com.hookah.roma.hookahmix.R;
import com.hookah.roma.hookahmix.TabakLab;

/**
 * Created by Roma on 11.03.2017.
 */

public class MixDialogFragment extends DialogFragment {

    private static String ARG_DESC = "description";
    private static TabakLab mTabakLab;
    public TextView ingred1;
    public TextView ingred2;
    public TextView ingred3;
    public TextView ingred4;
    public TextView proc1;
    public TextView proc2;
    public TextView proc3;
    public LinearLayout layout_ingred3;
    public LinearLayout layout_ingred4;
    public TextView proc4;
    public TextView description;
    public TextView name;
    public TextView ratingBarInt;
    public RatingBar ratingBar;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        mTabakLab = TabakLab.get(getActivity());
        String desc = (String) getArguments().getSerializable(ARG_DESC);
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.mix_dialog_fragment, null);
        Typeface notoSansBoldFont = Typeface.createFromAsset(getResources().getAssets(), "fonts/NotoSans-Bold.ttf");

        name = (TextView) v.findViewById(R.id.mix_name_dialog);
        ingred1 = (TextView) v.findViewById(R.id.ingred1);
        ingred2 = (TextView) v.findViewById(R.id.ingred2);
        ingred3 = (TextView) v.findViewById(R.id.ingred3);
        ingred4 = (TextView) v.findViewById(R.id.ingred4);
        proc1 = (TextView) v.findViewById(R.id.proc1);
        proc1.setTypeface(notoSansBoldFont);
        proc2 = (TextView) v.findViewById(R.id.proc2);
        proc2.setTypeface(notoSansBoldFont);
        proc3 = (TextView) v.findViewById(R.id.proc3);
        proc3.setTypeface(notoSansBoldFont);
        proc4 = (TextView) v.findViewById(R.id.proc4);
        proc4.setTypeface(notoSansBoldFont);
        layout_ingred3 = (LinearLayout) v.findViewById(R.id.layout_ingred3);
        layout_ingred4 = (LinearLayout) v.findViewById(R.id.layout_ingred4);

        description = (TextView) v.findViewById(R.id.mix_description_dialog);
        ratingBar = (RatingBar) v.findViewById(R.id.mix_dialog_ratingBar);
        ratingBarInt = (TextView) v.findViewById(R.id.mix_dialog_ratingInt);
        ratingBarInt.setTypeface(notoSansBoldFont);

        Mix mMix = TabakLab.get(getActivity()).getMix(desc);
        ratingBar.setProgress((int)Float.parseFloat(mMix.getRating()) * 2);

            if (mMix.getIngred3() == null) {
                name.setText(mMix.getIngred1() + ", " + mMix.getIngred2());
                ingred1.setText(mMix.getIngred1());
                ingred2.setText(mMix.getIngred2());
                proc1.setText(mMix.getProc1() + "%");
                proc2.setText(mMix.getProc2() + "%");
                layout_ingred3.setVisibility(View.GONE);
                layout_ingred4.setVisibility(View.GONE);
                description.setText(mMix.getDescription());
                ingred2.setPadding(0,0,0,65);
            } else if (mMix.getIngred4() == null) {
                name.setText(mMix.getIngred1() + ", " + mMix.getIngred2() + ", " + mMix.getIngred3());
                ingred1.setText(mMix.getIngred1());
                ingred2.setText(mMix.getIngred2());
                ingred3.setText(mMix.getIngred3());
                proc1.setText(mMix.getProc1() + "%");
                proc2.setText(mMix.getProc2() + "%");
                proc3.setText(mMix.getProc3() + "%");
                layout_ingred4.setVisibility(View.GONE);
                description.setText(mMix.getDescription());
                ingred3.setPadding(0,0,0,65);
            } else {
                name.setText(mMix.getIngred1() + ", " + mMix.getIngred2() + ", " + mMix.getIngred3() + ", " + mMix.getIngred4());
                ingred1.setText(mMix.getIngred1());
                ingred2.setText(mMix.getIngred2());
                ingred3.setText(mMix.getIngred3());
                ingred4.setText(mMix.getIngred4());
                proc1.setText(mMix.getProc1() + "%");
                proc2.setText(mMix.getProc2() + "%");
                proc3.setText(mMix.getProc3() + "%");
                proc4.setText(mMix.getProc4() + "%");
                description.setText(mMix.getDescription());
                ingred4.setPadding(0,0,0,65);
            }

        AlertDialog.Builder adb = new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle("");

        return adb.create();
    }
    public static MixDialogFragment newInstance(String desc) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_DESC, desc);
        MixDialogFragment fragment = new MixDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

}
