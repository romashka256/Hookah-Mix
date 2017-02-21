package com.hookah.roma.hookahmix;

import android.content.Context;
import android.os.AsyncTask;

import database.TabakDataBase;

/**
 * Writes an item to the Database. 'nuff said
 * Created by Ryan on 29/07/2016.
 */
public class WriteToDatabase extends AsyncTask<Void, Void, Long> {
    private Tabak tabak;
    private Context context;

    private OnWriteComplete onWriteComplete;

    public interface OnWriteComplete {
        void setWriteComplete(long result);
    }

    public void setWriteCompleteListener(OnWriteComplete onWriteComplete) {
        this.onWriteComplete = onWriteComplete;
    }

    public WriteToDatabase(Context context, Tabak todo){
        this.tabak = todo;
        this.context = context;
    }

    @Override
    protected Long doInBackground(Void... params) {
        TabakDataBase database = TabakDataBase.getInstance(context);
        return database.insertOrUpdateData(tabak);

    }

    /**
     *If param ends up as -1, then our To do has failed to be entered into the Database
     * @param param - Result of database operation
     */
    @Override
    protected void onPostExecute(Long param) {
        onWriteComplete.setWriteComplete(param);
    }
}
