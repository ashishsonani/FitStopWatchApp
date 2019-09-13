package com.ashishpatel.stopwatchapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;

public class StopWatchAct extends AppCompatActivity {

    Button btnstart, btnstop,btnhome;
    ImageView icanchor;
    Animation roundingalone;
    Chronometer timerhere;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop_watch);

        btnstart = findViewById(R.id.btnstart);
        btnstop = findViewById(R.id.btnstop);
        btnhome = findViewById(R.id.btnHome);
        icanchor = findViewById(R.id.icanchor);
        timerhere = findViewById(R.id.timerHere);

        //create optional animation
        btnstop.setAlpha(0);
        btnhome.setAlpha(0);

        //load animation
        roundingalone = AnimationUtils.loadAnimation(this, R.anim.roundingalone);

        //import font
        Typeface MMedium = Typeface.createFromAsset(getAssets(), "fonts/MMedium.ttf");

        //customize font
        btnstart.setTypeface(MMedium);
        btnstop.setTypeface(MMedium);

        btnstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //passing animation
                icanchor.startAnimation(roundingalone);
                btnstop.animate().alpha(1).translationY(-80).setDuration(300).start();
                btnstart.animate().alpha(0).setDuration(300).start();
                //time start
                timerhere.setBase(SystemClock.elapsedRealtime());
                timerhere.start();
            }
        });
        try {

            btnstop.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    icanchor.getAnimation().cancel();
                    icanchor.clearAnimation();
                    btnhome.animate().alpha(1).translationY(-80).setDuration(300).start();
                    roundingalone.setAnimationListener(null);
                    timerhere.stop();
                }
            });
        }catch (Exception e){
            e.printStackTrace();
            Log.e("Stop Button " , " Error"+ e);
            return;
        }
        btnhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(StopWatchAct.this ,MainActivity.class);
                startActivity(a);
            }
        });
    }
}
