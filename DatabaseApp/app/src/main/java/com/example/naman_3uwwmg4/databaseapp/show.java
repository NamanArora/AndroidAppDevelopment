package com.example.naman_3uwwmg4.databaseapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class show extends AppCompatActivity {
    SQLhelper helper;
    SQLiteDatabase database;
    EditText et1,et2;
    SimpleCursorAdapter sca;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        ListView list= (ListView) findViewById(R.id.lv);
        Intent intent= getIntent();
        helper= new SQLhelper(getBaseContext());
        database= helper.getWritableDatabase();


        String[] projection = {
                SQLcontract.FeedTable._ID,
                SQLcontract.FeedTable.COLUMN_NAME,
                SQLcontract.FeedTable.COLUMN_NUMBER
        };

        Cursor cursor= database.query(SQLcontract.FeedTable.TABLE_NAME,
                projection,null,null,null,null,null);
        cursor.moveToFirst();

        int[] Textviews = {android.R.id.text1, android.R.id.text2};

        String[] columns={
                SQLcontract.FeedTable.COLUMN_NAME,
                SQLcontract.FeedTable.COLUMN_NUMBER
        };


        sca= new SimpleCursorAdapter(this,android.R.layout.two_line_list_item,cursor,columns,Textviews,0);
        list.setAdapter(sca);


    }

}
