package com.example.naman_3uwwmg4.contentproviders;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.UserDictionary;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    ListView tv1;
    Cursor cursor=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Init();
        ContentResolver contentResolver = getContentResolver();


        //Columns to display
        //IMP: _ID IS NEEDED IF YOU WANT TO USE SIMPLECURSORADAPTER
        String[] projection={
                UserDictionary.Words._ID,
                UserDictionary.Words.WORD,
                UserDictionary.Words.FREQUENCY
                };

        //Where freq>= : (? is used as security from sql injection)
        String selection=UserDictionary.Words.FREQUENCY + ">= ?";

        //Condition clause
        String[] selectionArg={"200"};

        //First arg tells which table to query
        // select PROJECTION from TABLE where SELECTION ? SELECTIONARG order by ASC(default);
        cursor= contentResolver.query(UserDictionary.Words.CONTENT_URI,
                                             projection,selection,selectionArg,
                                        null);
        updateList();

        ContentValues values= new ContentValues();
        values.put(UserDictionary.Words.WORD,"gamma");
        values.put(UserDictionary.Words.FREQUENCY,181);

        //returns the uri of inserted value and then we can display it
        Uri mUri= getContentResolver().insert(UserDictionary.Words.CONTENT_URI,values);
        cursor= getContentResolver().query(mUri,null,null,null,null);
        updateList();

        //TO RETRIEVE SINGLE VALUES USE cursor.movetonext() and cursorgetString(colIndex)

    }

    private void updateList() {
        //Textviews declared inside two_line_list_item
        int[] Textviews = {android.R.id.text1, android.R.id.text2};

        String[] columns={
                UserDictionary.Words.WORD,
                UserDictionary.Words.FREQUENCY
        };
        if(cursor!=null) {
            SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, android.R.layout.two_line_list_item,
                    cursor, columns, Textviews, 0);
            tv1.setAdapter(adapter);
        }
    }

    private void Init() {
        tv1= (ListView) findViewById(R.id.tv1);
    }
}
