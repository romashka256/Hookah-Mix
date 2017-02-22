package com.hookah.roma.hookahmix.tasks;

import android.content.Context;
import android.os.AsyncTask;


import java.util.ArrayList;

import database.TabakDataBase;

/**
 * Self-explanatory
 * Created by Ryan on 29/07/2016.
 */
public class ReadFromDatabase extends AsyncTask<Void, Void, ArrayList> {
    private Context context;

    private OnQueryComplete onQueryComplete;

    public interface OnQueryComplete {
        void setQueryComplete(ArrayList result);
    }

    public void setQueryCompleteListener(OnQueryComplete onQueryComplete) {
        this.onQueryComplete = onQueryComplete;
    }

    public ReadFromDatabase(Context context) {
        this.context = context;
    }

    @Override
    protected ArrayList doInBackground(Void... params) {
        TabakDataBase database = TabakDataBase.getInstance(context);
        return database.getAllData();
    }

    @Override
    protected void onPostExecute(ArrayList result) {
        onQueryComplete.setQueryComplete(result);
    }
}
