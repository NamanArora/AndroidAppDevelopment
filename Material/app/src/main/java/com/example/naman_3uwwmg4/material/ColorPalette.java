package com.example.naman_3uwwmg4.material;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class ColorPalette extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_palette);

        final RelativeLayout relativeLayout=(RelativeLayout) findViewById(R.id.relative);

        Bitmap bitmap= BitmapFactory.decodeResource(getResources(),R.drawable.cat);
        ImageView IV= (ImageView)findViewById(R.id.imageView);

        Palette.PaletteAsyncListener asyncListener= new Palette.PaletteAsyncListener() {

            @Override
            public void onGenerated(Palette palette) {
                int defaultcolor=0x000000;
                int vibrant= palette.getDarkVibrantColor(defaultcolor);
                if(relativeLayout!=null) {
                    relativeLayout.setBackgroundColor(vibrant);
                }


            }
        };


        IV.setImageBitmap(bitmap);
        if(bitmap!=null && !bitmap.isRecycled())
        {
            Palette.from(bitmap).generate(asyncListener);
        }
    }
}
