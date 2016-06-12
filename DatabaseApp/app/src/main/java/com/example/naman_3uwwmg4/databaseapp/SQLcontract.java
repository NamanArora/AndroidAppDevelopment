package com.example.naman_3uwwmg4.databaseapp;

import android.provider.BaseColumns;

/**
 * Created by naman_3uwwmg4 on 12-06-2016.
 */
public class SQLcontract {


// Contract classes are used for defining schema related constants only.
// All strings will be public static final


    public SQLcontract() {
        super();
    }
    public abstract class FeedTable implements BaseColumns{

        public static final String TABLE_NAME = "test";
        public static final String COLUMN_NAME="name";
        public static final String COLUMN_NUMBER="number";
    }
}
