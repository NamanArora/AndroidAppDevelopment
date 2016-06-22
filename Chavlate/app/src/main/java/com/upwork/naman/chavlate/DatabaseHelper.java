package com.upwork.naman.chavlate;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by naman_3uwwmg4 on 22-06-2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static String DB_NAME= "chavlatedb.db";
    private static String DB_PATH= "/data/data/com.upwork.naman.chavlate/databases/";
    private final Context myContext;
    private static int DB_VERSION=3;
    private SQLiteDatabase myDataBase;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.myContext=context;
        Log.e("chavlate","database version"+DB_VERSION);
    }

    public void createDatabase()
    {
        boolean dbExists= checkDatabase();
        if(!dbExists)
        {
            this.getWritableDatabase();
            try {
                copyDatabase();
            } catch (IOException e) {
                Log.e("chavlate","unable to copy database");
            }

        }

    }

    public boolean checkDatabase()
    {
        SQLiteDatabase db=null;
        try {
            db = SQLiteDatabase.openDatabase(DB_PATH + DB_NAME, null, SQLiteDatabase.OPEN_READONLY);
            if(db.getVersion() < DB_VERSION) {
                onUpgrade(db, db.getVersion(), DB_VERSION);
            }
        }catch(SQLiteException a)
        {
            Log.e("chavlate","database doesn't exist");
        }

        if(db!=null)
            db.close();
        return db != null ? true : false;
    }

    @Override
    public synchronized void close() {

        if(myDataBase != null)
            myDataBase.close();
        super.close();
    }

    public void copyDatabase() throws IOException {
        InputStream is = myContext.getAssets().open(DB_NAME);
        OutputStream os = new FileOutputStream(DB_PATH+DB_NAME);

        byte[] buffer = new byte[1024];
        int length;
        while ((length = is.read(buffer))>0){
            os.write(buffer, 0, length);
        }
        os.flush();
        os.close();
        is.close();
    }

    public void openDatabase()
    {
        try {
            myDataBase = SQLiteDatabase.openDatabase(DB_PATH + DB_NAME, null, SQLiteDatabase.OPEN_READONLY);
        }catch (SQLiteException A)
        {
            Log.e("chavlate","unable to opendatabase");
        }
    }

    public boolean itExists(String word)
    {
        if(getMeaning(word).contentEquals("No explanation!"))
        return false;
        else
        return true;

    }

    public String getMeaning(String word)
    {

        //Enter words in database in small letters and no special characters!
        word=word.replaceAll("'","");
        word=word.toLowerCase();
        String response="";
        String args[] = new String[1];
        args[0]=word;
        String QUERY= "select * from word_dictionary where word= '" + word+"'" + " or " + "close_word= '" +word+ "'" ;
        Cursor cursor= myDataBase.rawQuery(QUERY,null);
        if(cursor.moveToFirst())
        response= cursor.getString(cursor.getColumnIndex(SQLContract.FeedTable.COLUMN_MEANING));
        else
            response= "No explanation!";
        cursor.close();
        return response;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.e("chavlate","old:" + oldVersion + "new:" + newVersion);
        if(oldVersion<newVersion) {
            myContext.deleteDatabase(DB_NAME);
            Log.e("chavlate","database upgraded!");
            try {
                copyDatabase();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }
}
