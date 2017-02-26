package database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static database.TabakDbSchema.TabakTable;


    public class TabakBaseHelper extends SQLiteOpenHelper {

        private static final int VERSION = 1;
        private static final String DATABASE_NAME = "tabaks.db";

        public TabakBaseHelper(Context context) {
            super(context, DATABASE_NAME, null, VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("create table " + TabakTable.NAME + "(" + "_id integer primary key autoincrement, " +
                    TabakTable.Cols.NAME + ", " +
                    TabakTable.Cols.DESCRIPTION + ", " +
                    TabakTable.Cols.FAMILY + ", " +
                    TabakTable.Cols.RATING + ", " +
                    TabakTable.Cols.FAVOURITE +
                    ")"
            );
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
