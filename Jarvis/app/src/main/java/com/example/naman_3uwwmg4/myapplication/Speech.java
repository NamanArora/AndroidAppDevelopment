package com.example.naman_3uwwmg4.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View.OnLongClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class Speech extends AppCompatActivity implements RecognitionListener {

    private SpeechRecognizer speech=null;
    private Intent recognizerIntent;
    private TextView returnedText;
    private Typeface med;
    private Typeface sl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speech);
        //if(SpeechRecognizer.isRecognitionAvailable(this))
        setup();


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        returnedText = (TextView) findViewById(R.id.returnedText);

        returnedText.setTypeface(sl);


        final FloatingActionButton record = (FloatingActionButton) findViewById(R.id.fab);

        record.setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                speech.startListening(recognizerIntent);
                Snackbar.make(v, "Listening", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();
                return true;
            }
        });
    }

    private void setup() {
        Log.d("jarvis","begin()");
        Window w = getWindow();
        w.setFlags(
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        w.setFlags(
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        med= Typeface.createFromAsset(getAssets(),"fonts/OratorStd.otf");
        sl= Typeface.createFromAsset(getAssets(),"fonts/Orator-Std-Slanted_34152.ttf");
        recognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_PREFERENCE,
                "en");
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE,
                this.getPackageName());
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_WEB_SEARCH);
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 3);
        speech = SpeechRecognizer.createSpeechRecognizer(this);
        speech.setRecognitionListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_speech, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

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
        String errorMessage = getErrorText(error);
        Log.d("jarvis", "FAILED " + errorMessage);


    }

    @Override
    public void onResults(Bundle results) {
        RelativeLayout ll= (RelativeLayout) findViewById(R.id.container);
        String match = results
                .getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION).get(0);

        match= match.substring(0,1).toUpperCase()+match.substring(1);
        returnedText.setText(match);
        if(match.contentEquals("power up"))
        ll.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color1));


    }

    @Override
    public void onPartialResults(Bundle partialResults) {
        Log.d("jarvis","partial");

    }

    @Override
    public void onEvent(int eventType, Bundle params) {
        Log.d("jarvis","event");

    }


    public static String getErrorText(int errorCode) {
        String message;
        switch (errorCode) {
            case SpeechRecognizer.ERROR_AUDIO:
                message = "Audio recording error";
                break;
            case SpeechRecognizer.ERROR_CLIENT:
                message = "Client side error";
                break;
            case SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS:
                message = "Insufficient permissions";
                break;
            case SpeechRecognizer.ERROR_NETWORK:
                message = "Network error";
                break;
            case SpeechRecognizer.ERROR_NETWORK_TIMEOUT:
                message = "Network timeout";
                break;
            case SpeechRecognizer.ERROR_NO_MATCH:
                message = "No match";
                break;
            case SpeechRecognizer.ERROR_RECOGNIZER_BUSY:
                message = "RecognitionService busy";
                break;
            case SpeechRecognizer.ERROR_SERVER:
                message = "error from server";
                break;
            case SpeechRecognizer.ERROR_SPEECH_TIMEOUT:
                message = "No speech input";
                break;
            default:
                message = "Didn't understand, please try again.";
                break;
        }
        return message;
    }
}
