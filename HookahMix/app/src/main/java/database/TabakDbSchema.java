package database;


public class TabakDbSchema {
    public static final class TabakTable{
        public static final String NAME = "tabaks";
        public static final class Cols{
            public static final String NAME = "name";
            public static final String DESCRIPTION = "description";
            public static final String FAMILY = "family";
            public static final String RATING = "rating";
            public static final String FAVOURITE = "favourite";
            public static final String SECOND_NAME = "second_name";
        }
    }

    public static final class MixTable{
        public static final String NAME = "mixes";
        public static final class Cols{
            public static final String INGRED1 = "ingred1";
            public static final String INGRED2 = "ingred2";
            public static final String INGRED3 = "ingred3";
            public static final String INGRED4 = "ingred4";
            public static final String PROC1 = "proc1";
            public static final String PROC2 = "proc2";
            public static final String PROC3 = "proc3";
            public static final String PROC4 = "proc4";
            public static final String RATING = "rating";
            public static final String FAVOURITE = "favourite";
            public static final String DESCRIPTION = "description";
            public static final String FAMILY = "family";
        }
    }
}
