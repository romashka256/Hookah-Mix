package com.hookah.roma.hookahmix;

import android.database.Cursor;
import android.database.CursorWrapper;

import database.TabakDbSchema;

import static database.TabakDbSchema.TabakTable;

/**
 * Created by Roma on 24.02.2017.
 */

public class TabakCursorWrapper extends CursorWrapper {

    public TabakCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public  Tabak getTabak(){
        String name = getString(getColumnIndex(TabakTable.Cols.NAME));
        String description = getString(getColumnIndex(TabakTable.Cols.DESCRIPTION));
        String family = getString(getColumnIndex(TabakTable.Cols.FAMILY));
        String rating = getString(getColumnIndex(TabakTable.Cols.RATING));
        String favourite = getString(getColumnIndex(TabakTable.Cols.FAVOURITE));

        Tabak tabak = new Tabak();

        tabak.setName(name);
        tabak.setDescription(description);
        tabak.setFamily(family);
        tabak.setRating(rating);
        tabak.setIsfavourite(favourite);

        return tabak;
    }

    public  Mix getMix(){
        String ingred1 = getString(getColumnIndex(TabakDbSchema.MixTable.Cols.INGRED1));
        String ingred2 = getString(getColumnIndex(TabakDbSchema.MixTable.Cols.INGRED2));
        String ingred3 = getString(getColumnIndex(TabakDbSchema.MixTable.Cols.INGRED3));
        String ingred4 = getString(getColumnIndex(TabakDbSchema.MixTable.Cols.INGRED4));
        String proc1 = getString(getColumnIndex(TabakDbSchema.MixTable.Cols.PROC1));
        String proc2 = getString(getColumnIndex(TabakDbSchema.MixTable.Cols.PROC2));
        String proc3= getString(getColumnIndex(TabakDbSchema.MixTable.Cols.PROC3));
        String proc4 = getString(getColumnIndex(TabakDbSchema.MixTable.Cols.PROC4));
        String family  = getString(getColumnIndex(TabakDbSchema.MixTable.Cols.FAMILY));
        String favourite  = getString(getColumnIndex(TabakDbSchema.MixTable.Cols.FAVOURITE));
        String rating  = getString(getColumnIndex(TabakDbSchema.MixTable.Cols.RATING));
        String description  = getString(getColumnIndex(TabakDbSchema.MixTable.Cols.DESCRIPTION));

        Mix mix = new Mix();

        mix.setIngred1(ingred1);
        mix.setIngred2(ingred2);
        mix.setIngred3(ingred3);
        mix.setIngred4(ingred4);
        mix.setProc1(proc1);
        mix.setProc2(proc2);
        mix.setProc3(proc3);
        mix.setProc4(proc4);
        mix.setFavourite(favourite);
        mix.setFamily(family);
        mix.setDescription(description);
        mix.setRating(rating);

        return mix;
    }
}
