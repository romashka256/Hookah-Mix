package database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.google.gson.Gson;
import com.hookah.roma.hookahmix.Tabak;

import java.util.ArrayList;

import static database.TabakDbSchema.TabakTable;

public class TabakDataBase {

    private TabakDataBaseHelper helper;
    private static TabakDataBase database;

    public static TabakDataBase getInstance(Context c) {
        if (database == null) {
            database = new TabakDataBase(c);
        }
        return database;
    }

    private TabakDataBase(Context c) {
        helper = new TabakDataBaseHelper(c);
    }

    public ArrayList getAllData() {
        SQLiteDatabase db = helper.getReadableDatabase();
        ArrayList<Tabak> result = new ArrayList<>();
        Gson gson = new Gson();

        Cursor c = db.query(TabakTable.NAME, null, null, null, null, null, null);

        if (c.moveToFirst()) {
            do {
                Tabak tabak = gson.fromJson(
                        c.getString(
                                c.getColumnIndex(TabakTable.Cols.NAME)
                        ),
                        Tabak.class);
                result.add(tabak);
            }
            while (c.moveToNext());
        }

        c.close();
        db.close();
        return result;
    }

    public long insertOrUpdateData(Tabak tabak) {
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor c = db.query(TabakTable.NAME, null, null, null, null, null, null);
        ContentValues val = new ContentValues();

        Gson gson = new Gson();

        if (c.moveToFirst()) {
            do {
                val.put(TabakTable.Cols.NAME, tabak.getName());
                val.put(TabakTable.Cols.RATING, tabak.getRating());
                val.put(TabakTable.Cols.DESCRIPTION, tabak.getDescription());
                val.put(TabakTable.Cols.FAMILY, tabak.getFamily());
                val.put(TabakTable.Cols.FAVOURITE, tabak.isfavourite());

                String selection = TabakTable.Cols.ENTRY_ID + "LIKE ?";
                String[] selectionArgs = {
                        String.valueOf(c.getString(c.getColumnIndex(TabakTable.Cols.ENTRY_ID)))
                };
                long id = db.update(TabakTable.NAME, val, selection, selectionArgs);
                c.close();
                db.close();
                return id;
            }
            while (c.moveToNext());
        }

        val.put(TabakTable.Cols.NAME,tabak.getName());
        val.put(TabakTable.Cols.DESCRIPTION,tabak.getDescription());
        val.put(TabakTable.Cols.FAMILY,tabak.getFamily());
        val.put(TabakTable.Cols.FAVOURITE,tabak.isfavourite());
        val.put(TabakTable.Cols.RATING,tabak.getRating());

        long id = db.insert(TabakTable.NAME,null,val);
        c.close();
        db.close();
        return id;
}


public class TabakDataBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "crimeBase.db";
    private Context context;


    public TabakDataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TabakTable.NAME + "(" + "_id integer primary key autoincrement, " +
                TabakTable.Cols.UUID + ", " +
                TabakTable.Cols.NAME + ", " +
                TabakTable.Cols.DESCRIPTION + ", " +
                TabakTable.Cols.RATING + ", " +
                TabakTable.Cols.FAVOURITE + ", " +
                TabakTable.Cols.FAMILY +
                ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL( "DROP TABLE IF EXISTS " + TabakTable.NAME);
        onCreate(db);

    }
}
}
