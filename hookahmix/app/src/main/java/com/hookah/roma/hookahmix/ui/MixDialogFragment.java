package com.hookah.roma.hookahmix.ui;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hookah.roma.hookahmix.Mix;
import com.hookah.roma.hookahmix.TabakLab;

import java.util.List;

/**
 * Created by Roma on 11.03.2017.
 */

public class MixDialogFragment extends DialogFragment {

    private static String ARG_DESC = "description";
    private static TabakLab mTabakLab;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        mTabakLab = TabakLab.get(getActivity());

        String desc = (String) getArguments().getSerializable(ARG_DESC);

        Mix mix = mTabakLab.getMix(desc);

        return
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    public static MixDialogFragment newInstance(String desc) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_DESC, desc);
        MixDialogFragment fragment = new MixDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
