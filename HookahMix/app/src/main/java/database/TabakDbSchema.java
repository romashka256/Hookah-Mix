package database;

/**
 * Created by Roma on 23.02.2017.
 */

public class TabakDbSchema {
    public static final class TabakTable{
        public static final String NAME = "tabaks";
        public static final class Cols{
            public static final String NAME = "name";
            public static final String DESCRIPTION = "description";
            public static final String FAMILY = "family";
            public static final String RATING = "rating";
            public static final String FAVOURITE = "favourite";
        }
    }
}
