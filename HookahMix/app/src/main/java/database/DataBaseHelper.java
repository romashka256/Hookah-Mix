package database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static database.TabakDbSchema.TabakTable;

/**
 * Created by Roma on 21.02.2017.
 */

public class DataBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "crimeBase.db";


    public DataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, VERSION);
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

    }
}
