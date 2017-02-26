package com.hookah.roma.hookahmix;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import database.TabakBaseHelper;

import static database.TabakDbSchema.TabakTable;


public class TabakLab {
    private static SQLiteDatabase mDataBase;
    private Context mContext;

    private static TabakLab sTabakLab;
    private Context mAppContext;

    private TabakLab(Context context) {
        mContext = context.getApplicationContext();
        mDataBase = new TabakBaseHelper(mContext).getWritableDatabase();
        LoadTabaksFromJson();
    }

    public static TabakLab get(Context c) {
        if (sTabakLab == null) {
            sTabakLab = new TabakLab(c.getApplicationContext());
        }
        return sTabakLab;
    }

    private static ContentValues getContentValues(TabaksArrayList tabak) {
        ContentValues val = new ContentValues();
        val.put(TabakTable.Cols.NAME, tabak.getName());
        val.put(TabakTable.Cols.DESCRIPTION, tabak.getDescription());
        val.put(TabakTable.Cols.FAMILY, tabak.getFamily());
        val.put(TabakTable.Cols.RATING, tabak.getRating());
        val.put(TabakTable.Cols.FAVOURITE, tabak.getFavourite());
        return val;
    }

    private void LoadTabaksFromJson() {
        InputStream raw = mContext.getResources().openRawResource(R.raw.tabaks);
        Reader reader = new BufferedReader(new InputStreamReader(raw));
        Tabak listOfTabaks = new Gson().fromJson(reader,Tabak.class);
        List<TabaksArrayList> tabakList = listOfTabaks.getTabaksArrayList();

        for(TabaksArrayList tabak : tabakList){
            mDataBase.insert(TabakTable.NAME,null,getContentValues(tabak));
        }
    }


    /*    JsonParser jsonParser = new JsonParser();
        URL u = null;

        try {
            u = new URL("https://raw.githubusercontent.com/romashka256/Hookah-Mix/master/tabaks.json");
            InputStream in = u.openStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            JsonElement jsonElement = jsonParser.parse(br);

            //Create generic type
            Type type = new TypeToken<List<Tabak>>() {}.getType();
            return gson.fromJson(jsonElement, type);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Log.e("tag", e.getMessage());
        } catch (IOException e) {
            e.getMessage();
        }
        return patterns*/


    private TabakCursorWrapper queryCrimes(String whereClause, String[] whereArgs) {
        Cursor cursor = mDataBase.query(
                TabakTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null);
        return new TabakCursorWrapper(cursor);
    }

    // Return entire list of crimes
    public List<Tabak> getTabaks() {
        List<Tabak> crimes = new ArrayList<>();

        TabakCursorWrapper cursor = queryCrimes(null, null);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                crimes.add(cursor.getTabak());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return crimes;
    }

    public Tabak getTabak(String name){
        TabakCursorWrapper cursor = queryCrimes(TabakTable.Cols.NAME + " = ?",new String[]{name});
        try{
            cursor.moveToFirst();
            return cursor.getTabak();
        }finally {
            cursor.close();
        }


    }
}


