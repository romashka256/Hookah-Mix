package database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.hookah.roma.hookahmix.models.objects.Tabak;

/**
 * Created by Roma on 22.02.2017.
 */

public class TabakCursorWrapper extends CursorWrapper {

    public TabakCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Tabak getTabak(){
        String name = getString(getColumnIndex(TabakDbSchema.TabakTable.Cols.NAME));
        String description = getString(getColumnIndex(TabakDbSchema.TabakTable.Cols.NAME));
        String family = getString(getColumnIndex(TabakDbSchema.TabakTable.Cols.NAME));
        String rating = getString(getColumnIndex(TabakDbSchema.TabakTable.Cols.NAME));

        Tabak tabak = new Tabak();

        tabak.setName(name);
        tabak.setFamily(family);
        tabak.setDescription(description);
        tabak.setRating(rating);

        return tabak;
    }
}
