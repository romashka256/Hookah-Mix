package database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static database.TabakDbSchema.MixTable;
import static database.TabakDbSchema.TabakTable;


    public class TabakBaseHelper extends SQLiteOpenHelper {

        private static final int VERSION = 1;
        private static final String DATABASE_NAME = "tabaks.db";
        private static final String EXEC_SQL_TABAK_TABLE = "create table " + TabakTable.NAME + "(" + "_id integer primary key autoincrement, " +
                TabakTable.Cols.NAME + ", " +
                TabakTable.Cols.SECOND_NAME + ", " +
                TabakTable.Cols.DESCRIPTION + ", " +
                TabakTable.Cols.FAMILY + ", " +
                TabakTable.Cols.RATING + ", " +
                TabakTable.Cols.FAVOURITE +
                ")";
        private static final String EXEC_SQL_MIX_TABLE = "create table " + MixTable.NAME + "(" + "_id integer primary key autoincrement, " +
                MixTable.Cols.INGRED1 + ", " +
                MixTable.Cols.INGRED2 + ", " +
                MixTable.Cols.INGRED3 + ", " +
                MixTable.Cols.INGRED4 + ", " +
                MixTable.Cols.PROC1 + ", " +
                MixTable.Cols.PROC2 + ", " +
                MixTable.Cols.PROC3 + ", " +
                MixTable.Cols.PROC4 + ", " +
                MixTable.Cols.DESCRIPTION + ", " +
                MixTable.Cols.RATING + ", " +
                MixTable.Cols.FAVOURITE + ", " +
                MixTable.Cols.FAMILY +
                ")";
        public TabakBaseHelper(Context context) {
            super(context, DATABASE_NAME, null, VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(EXEC_SQL_TABAK_TABLE);
            db.execSQL(EXEC_SQL_MIX_TABLE);

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
