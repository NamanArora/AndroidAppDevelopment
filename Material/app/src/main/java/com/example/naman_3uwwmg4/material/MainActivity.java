package com.example.naman_3uwwmg4.material;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button tabB,formB,palette;
    @Override
    public void onClick(View v) {
        Intent intent=null;
        switch(v.getId())
        {
            case R.id.formb:
                intent= new Intent(this,FormB.class);
                startActivity(intent);
                break;
            case R.id.tabb:
                intent=new Intent(this,TabB.class);
                break;
            case R.id.palette:
                intent=new Intent(this, ColorPalette.class);
                break;
        }
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        tabB=(Button)findViewById(R.id.tabb);
        formB=(Button) findViewById(R.id.formb);
        palette=(Button) findViewById(R.id.palette);
        tabB.setOnClickListener(this);
        formB.setOnClickListener(this);
        palette.setOnClickListener(this);
    }
}
