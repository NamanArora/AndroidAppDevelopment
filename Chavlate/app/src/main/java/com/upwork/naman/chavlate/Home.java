package com.upwork.naman.chavlate;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Home extends AppCompatActivity {
    Toolbar toolbar;
    TextToSpeech t1;
    Intent recognizerIntent;
    ImageButton fab,exit;
    public static String PACKAGE_NAME;
    TextView said,mean;
    DatabaseHelper dbHelper;
    private final int REQ_CODE_SPEECH_INPUT = 100;
    ArrayList<String> myDataset;
    String result;
    private RecyclerView mRecyclerView;
    SpeechRecognizer sr;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        init();
        //setSupportActionBar(toolbar);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sr.startListening(recognizerIntent);
                Log.e("chavlate","onclick");
            }
        });
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sr.destroy();
    }

    @Override
    protected void onStop() {
        if(t1!=null)
        {
            t1.stop();
            t1.shutdown();
        }
        super.onStop();

    }

    private void init() {
        PACKAGE_NAME = getApplicationContext().getPackageName();
        myDataset= new ArrayList<>();
        //toolbar = (Toolbar) findViewById(R.id.toolbar);
//        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
//        mLayoutManager = new LinearLayoutManager(this);
//        mRecyclerView.setLayoutManager(mLayoutManager);
//        mAdapter = new MyAdapter(myDataset);
//        mRecyclerView.setAdapter(mAdapter);
        said= (TextView) findViewById(R.id.said);
        mean= (TextView) findViewById(R.id.mean);
        fab = (ImageButton) findViewById(R.id.fab);
        exit = (ImageButton) findViewById(R.id.exit);
        fab.setEnabled(false);
        recognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_PREFERENCE,
                "en-US");
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE,
                this.getPackageName());
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_WEB_SEARCH);
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 5);
        sr= SpeechRecognizer.createSpeechRecognizer(this);
        sr.setRecognitionListener(new listener());
        //updateDb();
        dbHelper= new DatabaseHelper(getApplicationContext());

        dbHelper.createDatabase();
        dbHelper.openDatabase();
    }

//    private void updateDb() {
//
//        try
//        {
//            String myPath = getDatabasePath(DatabaseHelper.DB_NAME).toString();
//            //open a database directly without Sqliteopenhelper
//            SQLiteDatabase myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE | SQLiteDatabase.NO_LOCALIZED_COLLATORS);
//            //if the version is default 0 the update the version
//            if (myDataBase.getVersion() == 0)
//            {
//                //update the database version to the previous one
//                myDataBase.execSQL("PRAGMA user_version = " + 1);
//            }
//            //Close DataBase
//            myDataBase.close();
//        }
//        catch (Exception e)
//        {
//            //Do Nothing a fresh install happened
//        }
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_clear) {
            myDataset.clear();
            mAdapter.notifyDataSetChanged();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();

        t1= new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status!= TextToSpeech.ERROR)
                    t1.setLanguage(Locale.getDefault());
                if(status==TextToSpeech.SUCCESS)
                    fab.setEnabled(true);
            }
        });

    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        switch (requestCode) {
//            case REQ_CODE_SPEECH_INPUT: {
//                if (resultCode == RESULT_OK && null != data) {
//
//                    result = data
//                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
//                    myDataset.add(0,result.get(0));
//                    mAdapter.notifyDataSetChanged();
//                    t1.speak(SpeechHelper.meaning(result.get(0)), TextToSpeech.QUEUE_FLUSH, null);
//                }
//                break;
//            }
//
//        }
//    }

    private class listener implements RecognitionListener {


        @Override
        public void onReadyForSpeech(Bundle params) {
            Log.d("jarvis","ready");

        }

        @Override
        public void onBeginningOfSpeech() {
            Log.d("jarvis","begins");

        }

        @Override
        public void onRmsChanged(float rmsdB) {
            Log.d("jarvis","rms change");

        }

        @Override
        public void onBufferReceived(byte[] buffer) {

        }

        @Override
        public void onEndOfSpeech() {
            Log.d("jarvis","end of speech");
        }


        @Override
        public void onError(int error) {
            //called when you don't speak. Error text= no match
            String errorMessage = SpeechHelper.getErrorText(error);
            Log.d("jarvis", "FAILED " + errorMessage);
        }


        @Override
        public void onResults(Bundle results) {
            String meaning;
//            StringBuilder sb= new StringBuilder();
            int i;
            ArrayList<String> a= results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
            //Check database for close sounding words, if word found break.
            for(i=0; i<a.size(); i++) {
                result = a.get(i);
//                sb.append(result);
//                sb.append(" ");
                if(dbHelper.itExists(result))
                    break;
            }
            //If no such word is found, go with the most accurate prediction.
            if(i==a.size())
                result=a.get(0);
            meaning= dbHelper.getMeaning(result);
//            said.setText(sb.toString());
            said.setText(result);
            t1.speak(meaning, TextToSpeech.QUEUE_FLUSH, null);
            mean.setText(meaning);
        }

        @Override
        public void onPartialResults(Bundle partialResults) {
            Log.d("jarvis","partial");

        }

        @Override
        public void onEvent(int eventType, Bundle params) {
            Log.d("jarvis","event");

        }
    }
}
