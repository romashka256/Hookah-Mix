package com.hookah.roma.hookahmix.tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.hookah.roma.hookahmix.R;
import com.hookah.roma.hookahmix.models.objects.ListOfTabaks;
import com.hookah.roma.hookahmix.models.objects.Tabak;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

import database.TabakDataBase;


public class LoatInitial  extends AsyncTask<Void,Void,Void> {

    private Context context;
    private OnDatabaseBuilt onDatabaseBuilt;

    public interface OnDatabaseBuilt {
        void buildComplete();
    }

    public LoatInitial (Context context){
        this.context = context;
    }

    public void setOnDatabaseBuilt(OnDatabaseBuilt onDatabaseBuilt) {
        this.onDatabaseBuilt = onDatabaseBuilt;
    }

    @Override
    protected Void doInBackground(Void... params) {
        TabakDataBase dataBase = TabakDataBase.getInstance(context);

        InputStream raw = context.getResources().openRawResource(R.raw.tabaks);
        Reader reader = new BufferedReader(new InputStreamReader(raw));

        ListOfTabaks listOfTabaks = new Gson().fromJson(reader,ListOfTabaks.class);
        List<Tabak> tabakList = listOfTabaks.getTabakArrayList();
        for(Tabak tabak: tabakList){
            Log.e("tag",tabak.getName() + tabak.getFamily());
        }
        for(Tabak tabak: tabakList){
            dataBase.insertOrUpdateData(tabak);
        }
        return null;
    }
    @Override
    protected void onPostExecute(Void param){
        onDatabaseBuilt.buildComplete();
    }

}
