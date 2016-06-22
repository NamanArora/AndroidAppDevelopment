package com.upwork.naman.chavlate;

import android.provider.BaseColumns;

/**
 * Created by naman_3uwwmg4 on 22-06-2016.
 */
public class SQLContract {

    public SQLContract(){super();}

    public abstract class FeedTable implements BaseColumns {

        public static final String TABLE_NAME = "word_dictionary";
        public static final String COLUMN_WORD="word";
        public static final String COLUMN_MEANING="meaning";
        public static final String COLUMN_CLOSE="close";
    }

}
