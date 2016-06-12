package com.example.naman_3uwwmg4.databaseapp;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener{

    Button show,delete,insert;
    SQLhelper helper;
    SQLiteDatabase database; //for database operations
    EditText et1,et2;

    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.insert:
                insert();
                break;
            case R.id.del:
                del();
                break;
            case R.id.show:
                transfer();
                break;
        }

    }

    private void transfer() {
        Intent intent= new Intent(this, show.class);
        startActivity(intent);
    }

    private void del() {
        String[] clause=new String[1];
        clause[0]=et1.getText().toString();
        int t=database.delete(SQLcontract.FeedTable.TABLE_NAME, SQLcontract.FeedTable.COLUMN_NAME + "= ?",clause);
        Toast.makeText(getBaseContext(),"Removed "+ t + " entries",Toast.LENGTH_SHORT).show();

    }

    private void insert() {
        ContentValues values= new ContentValues();
        values.put(SQLcontract.FeedTable.COLUMN_NAME,et1.getText().toString());
        values.put(SQLcontract.FeedTable.COLUMN_NUMBER,et2.getText().toString());
        database.insert(SQLcontract.FeedTable.TABLE_NAME,null,values);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {

        insert=(Button) findViewById(R.id.insert);
        delete=(Button) findViewById(R.id.del);
        show=(Button) findViewById(R.id.show);
        et1=(EditText) findViewById(R.id.editText);
        et2=(EditText) findViewById(R.id.editText2);
        helper= new SQLhelper(getBaseContext());
        database= helper.getWritableDatabase();
        insert.setOnClickListener(this);
        delete.setOnClickListener(this);
        show.setOnClickListener(this);
    }
}
