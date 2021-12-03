package com.cornez.activitylifecycle;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.os.*;
import android.content.Intent;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class set extends Activity
{
    private Button goto2,save2;
    private SeekBar sb;
    private  TextView t1;

    private int level = 2,save=0;
    private int savelevel=2,savehit,savemiss;
    private double savescore;
    private int[][] savetouchCount = new int[8][8];
    private int[][] savestep = new int[8][8];
    private int savecur_x,savecur_y,savered;
    private boolean saveisEnd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set);

        t1 = (TextView) findViewById(R.id.textView3);
        goto2 = (Button) findViewById(R.id.button4);
        sb = (SeekBar) findViewById(R.id.seekBar);
        save2 = (Button) findViewById(R.id.button5);

        Bundle bd = getIntent().getExtras();
        if (bd != null)
        {
            savelevel = bd.getInt("savelevel");
            save = bd.getInt("issave");
            savehit = bd.getInt("savehit");
            savemiss = bd.getInt("savemiss");
            savescore = bd.getDouble("savescore");

            savetouchCount[0] = bd.getIntArray("savetouchcount0");
            savetouchCount[1] = bd.getIntArray("savetouchcount1");
            savetouchCount[2] = bd.getIntArray("savetouchcount2");
            savetouchCount[3] = bd.getIntArray("savetouchcount3");
            savetouchCount[4] = bd.getIntArray("savetouchcount4");
            savetouchCount[5] = bd.getIntArray("savetouchcount5");
            savetouchCount[6] = bd.getIntArray("savetouchcount6");
            savetouchCount[7] = bd.getIntArray("savetouchcount7");

            savestep[0] = bd.getIntArray("savestep0");
            savestep[1] = bd.getIntArray("savestep1");
            savestep[2] = bd.getIntArray("savestep2");
            savestep[3] = bd.getIntArray("savestep3");
            savestep[4] = bd.getIntArray("savestep4");
            savestep[5] = bd.getIntArray("savestep5");
            savestep[6] = bd.getIntArray("savestep6");
            savestep[7] = bd.getIntArray("savestep7");

            savecur_x = bd.getInt("savecur_x");
            savecur_y = bd.getInt("savecur_y");

            savered = bd.getInt("savered");

            saveisEnd = bd.getBoolean("saveisEnd");

            if(savelevel==500) level = 3;
            else if(savelevel==1000) level=2;
            else level = 1;
            t1.setText("Level: "+level);
            sb.setProgress(level);
        }


        if(save==0) save2.setEnabled(false);
        else save2.setEnabled(true);

        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
            {
                level = progress;
                t1.setText("Level : " + level);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                t1.setText("Level : " + level);
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        goto2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent _2 = new Intent(set.this, MyActivity.class);
                Bundle side = new Bundle();
                if (level == 1) side.putInt("level", 2000);
                else if (level == 2) side.putInt("level", 1000);
                else side.putInt("level", 500);
                //save=0;
                //side.putInt("");
                _2.putExtras(side);
                startActivity(_2);
            }
        });

        save2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent _2 = new Intent(set.this, MyActivity.class);
                Bundle side = new Bundle();
                /*if (savelevel == 1) side.putInt("savelevel", 2000);
                else if (savelevel == 2) side.putInt("savelevel", 1000);
                else side.putInt("savelevel", 500);*/

                if(level==3) savelevel = 500;
                else if(level==2) savelevel=1000;
                else savelevel = 2000;

                side.putInt("savelevel",savelevel);
                side.putInt("issave",save);
                side.putInt("savehit",savehit);
                side.putInt("savemiss",savemiss);
                side.putDouble("savescore",savescore);

                side.putIntArray("savetouchcount0",savetouchCount[0]);
                side.putIntArray("savetouchcount1",savetouchCount[1]);
                side.putIntArray("savetouchcount2",savetouchCount[2]);
                side.putIntArray("savetouchcount3",savetouchCount[3]);
                side.putIntArray("savetouchcount4",savetouchCount[4]);
                side.putIntArray("savetouchcount5",savetouchCount[5]);
                side.putIntArray("savetouchcount6",savetouchCount[6]);
                side.putIntArray("savetouchcount7",savetouchCount[7]);

                side.putIntArray("savestep0",savestep[0]);
                side.putIntArray("savestep1",savestep[1]);
                side.putIntArray("savestep2",savestep[2]);
                side.putIntArray("savestep3",savestep[3]);
                side.putIntArray("savestep4",savestep[4]);
                side.putIntArray("savestep5",savestep[5]);
                side.putIntArray("savestep6",savestep[6]);
                side.putIntArray("savestep7",savestep[7]);

                side.putInt("savered",savered);

                side.putInt("savecur_x",savecur_x);
                side.putInt("savecur_y",savecur_y);

                side.putBoolean("saveisEnd",saveisEnd);

                _2.putExtras(side);
                startActivity(_2);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void new_game(View view) {

    }

    public void quit(View view)
    {
        finishAffinity();
    }
}
