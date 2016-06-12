package com.example.naman_3uwwmg4.databaseapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Helper classes actually handle the low level implementation of
 * database. It creates database and provides method to access it.
 * It also returns a database using getWritableDatabase() which
 * can be modified later.
 * Initial table create commands go here. By convention add all
 * database related operations here.
 */
public class SQLhelper extends SQLiteOpenHelper {
    private static final String TEXT_TYPE = " TEXT";
    private static final String INT_TYPE = " INT";
    private static final String COMMA_SEP = ",";

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "FeedReader.db";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + SQLcontract.FeedTable.TABLE_NAME + " (" +
                    SQLcontract.FeedTable._ID + " INTEGER PRIMARY KEY," +
                    SQLcontract.FeedTable.COLUMN_NAME + TEXT_TYPE + COMMA_SEP +
                    SQLcontract.FeedTable.COLUMN_NUMBER + INT_TYPE +
            " )";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public synchronized void close() {
        super.close();
    }

    public SQLhelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public String getDatabaseName() {
        return super.getDatabaseName();
    }
}
