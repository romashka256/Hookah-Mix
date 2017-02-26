package com.hookah.roma.hookahmix;

import android.database.Cursor;
import android.database.CursorWrapper;

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
}
