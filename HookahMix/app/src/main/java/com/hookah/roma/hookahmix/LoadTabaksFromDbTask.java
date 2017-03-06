package com.hookah.roma.hookahmix;

import android.content.Context;
import android.os.AsyncTask;



public class LoadTabaksFromDbTask extends AsyncTask<String,Void,Tabak> {

    Context context;

    public LoadTabaksFromDbTask(Context context){
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Tabak doInBackground(String... params) {
        TabakLab tabakLab = TabakLab.get(context);

        return tabakLab.getTabak(params[0]);
    }

    @Override
    protected void onPostExecute(Tabak tabak) {
        super.onPostExecute(tabak);
    }
}
