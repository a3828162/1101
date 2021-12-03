package com.cornez.activitylifecycle;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.os.*;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;


public class MyActivity extends Activity {

    private String createMsg;
    private String startMsg;
    private String resumeMsg;
    private String pauseMsg;
    private String stopMsg;
    private String restartMsg;

    private TextView msg;
    private TextView[][] textViewArray = new TextView[8][8];
    private TextView location, test;
    private int num, cur_x, cur_y, new_x, new_y;
    private int[][] step = new int[8][8];
    private int[][] jump = new int[][]{ {-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}, {-2, -1} };
    private int[][] touchCount = new int[8][8];
    private boolean isEnd;

    private Button btn2;
    private Button btn3;

    private int level = 0;
    private int hit=0;
    private int miss=0;
    private double score=0;
    private int red=0,save,savered;
    private TextView missN;
    private TextView scoreN;
    private TextView tt;
    private int savelevel,savehit,savemiss;
    private double savescore;
    private int[][] savetouchCount = new int[8][8];
    private int[][] savestep = new int[8][8];
    private int savecur_x,savecur_y;
    private boolean saveisEnd;

    Handler mHandler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            next();
            mHandler.postDelayed(this,level);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        Bundle bd = getIntent().getExtras();
        if (bd != null)
        {
            level = bd.getInt("level");
            save = bd.getInt("issave");
            savelevel = bd.getInt("savelevel");
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

            savered = bd.getInt("savered");

            savecur_x = bd.getInt("savecur_x");
            savecur_y = bd.getInt("savecur_y");

            saveisEnd = bd.getBoolean("saveisEnd");
        }


        //hitN=(TextView) findViewById(R.id.textView);
        hit=0;
        msg = (TextView) findViewById(R.id.textView);
        miss=0;
        missN = (TextView) findViewById(R.id.textView6);
        score=0;
        scoreN = (TextView) findViewById(R.id.textView7);
        red=1;
        //red=0


        tt=(TextView) findViewById(R.id.textView5);

        location = (TextView) findViewById(R.id.textView4);
        test = (TextView) findViewById(R.id.textView5);
        if(true) {
            textViewArray[0][0] = (TextView) findViewById(R.id.textView00);
            textViewArray[0][1] = (TextView) findViewById(R.id.textView01);
            textViewArray[0][2] = (TextView) findViewById(R.id.textView02);
            textViewArray[0][3] = (TextView) findViewById(R.id.textView03);
            textViewArray[0][4] = (TextView) findViewById(R.id.textView04);
            textViewArray[0][5] = (TextView) findViewById(R.id.textView05);
            textViewArray[0][6] = (TextView) findViewById(R.id.textView06);
            textViewArray[0][7] = (TextView) findViewById(R.id.textView07);

            textViewArray[1][0] = (TextView) findViewById(R.id.textView10);
            textViewArray[1][1] = (TextView) findViewById(R.id.textView11);
            textViewArray[1][2] = (TextView) findViewById(R.id.textView12);
            textViewArray[1][3] = (TextView) findViewById(R.id.textView13);
            textViewArray[1][4] = (TextView) findViewById(R.id.textView14);
            textViewArray[1][5] = (TextView) findViewById(R.id.textView15);
            textViewArray[1][6] = (TextView) findViewById(R.id.textView16);
            textViewArray[1][7] = (TextView) findViewById(R.id.textView17);

            textViewArray[2][0] = (TextView) findViewById(R.id.textView20);
            textViewArray[2][1] = (TextView) findViewById(R.id.textView21);
            textViewArray[2][2] = (TextView) findViewById(R.id.textView22);
            textViewArray[2][3] = (TextView) findViewById(R.id.textView23);
            textViewArray[2][4] = (TextView) findViewById(R.id.textView24);
            textViewArray[2][5] = (TextView) findViewById(R.id.textView25);
            textViewArray[2][6] = (TextView) findViewById(R.id.textView26);
            textViewArray[2][7] = (TextView) findViewById(R.id.textView27);

            textViewArray[3][0] = (TextView) findViewById(R.id.textView30);
            textViewArray[3][1] = (TextView) findViewById(R.id.textView31);
            textViewArray[3][2] = (TextView) findViewById(R.id.textView32);
            textViewArray[3][3] = (TextView) findViewById(R.id.textView33);
            textViewArray[3][4] = (TextView) findViewById(R.id.textView34);
            textViewArray[3][5] = (TextView) findViewById(R.id.textView35);
            textViewArray[3][6] = (TextView) findViewById(R.id.textView36);
            textViewArray[3][7] = (TextView) findViewById(R.id.textView37);

            textViewArray[4][0] = (TextView) findViewById(R.id.textView40);
            textViewArray[4][1] = (TextView) findViewById(R.id.textView41);
            textViewArray[4][2] = (TextView) findViewById(R.id.textView42);
            textViewArray[4][3] = (TextView) findViewById(R.id.textView43);
            textViewArray[4][4] = (TextView) findViewById(R.id.textView44);
            textViewArray[4][5] = (TextView) findViewById(R.id.textView45);
            textViewArray[4][6] = (TextView) findViewById(R.id.textView46);
            textViewArray[4][7] = (TextView) findViewById(R.id.textView47);

            textViewArray[5][0] = (TextView) findViewById(R.id.textView50);
            textViewArray[5][1] = (TextView) findViewById(R.id.textView51);
            textViewArray[5][2] = (TextView) findViewById(R.id.textView52);
            textViewArray[5][3] = (TextView) findViewById(R.id.textView53);
            textViewArray[5][4] = (TextView) findViewById(R.id.textView54);
            textViewArray[5][5] = (TextView) findViewById(R.id.textView55);
            textViewArray[5][6] = (TextView) findViewById(R.id.textView56);
            textViewArray[5][7] = (TextView) findViewById(R.id.textView57);

            textViewArray[6][0] = (TextView) findViewById(R.id.textView60);
            textViewArray[6][1] = (TextView) findViewById(R.id.textView61);
            textViewArray[6][2] = (TextView) findViewById(R.id.textView62);
            textViewArray[6][3] = (TextView) findViewById(R.id.textView63);
            textViewArray[6][4] = (TextView) findViewById(R.id.textView64);
            textViewArray[6][5] = (TextView) findViewById(R.id.textView65);
            textViewArray[6][6] = (TextView) findViewById(R.id.textView66);
            textViewArray[6][7] = (TextView) findViewById(R.id.textView67);

            textViewArray[7][0] = (TextView) findViewById(R.id.textView70);
            textViewArray[7][1] = (TextView) findViewById(R.id.textView71);
            textViewArray[7][2] = (TextView) findViewById(R.id.textView72);
            textViewArray[7][3] = (TextView) findViewById(R.id.textView73);
            textViewArray[7][4] = (TextView) findViewById(R.id.textView74);
            textViewArray[7][5] = (TextView) findViewById(R.id.textView75);
            textViewArray[7][6] = (TextView) findViewById(R.id.textView76);
            textViewArray[7][7] = (TextView) findViewById(R.id.textView77);
        }

        btn2 = (Button) findViewById((R.id.button2));
        btn3 = (Button) findViewById((R.id.button3));

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent _1 = new Intent(MyActivity.this, set.class);
                Bundle side = new Bundle();

                save=1;
                side.putInt("issave",save);
                side.putInt("savelevel",level);
                side.putInt("savehit",hit);
                side.putInt("savemiss",miss);
                side.putDouble("savescore",score);

                side.putIntArray("savetouchcount0",touchCount[0]);
                side.putIntArray("savetouchcount1",touchCount[1]);
                side.putIntArray("savetouchcount2",touchCount[2]);
                side.putIntArray("savetouchcount3",touchCount[3]);
                side.putIntArray("savetouchcount4",touchCount[4]);
                side.putIntArray("savetouchcount5",touchCount[5]);
                side.putIntArray("savetouchcount6",touchCount[6]);
                side.putIntArray("savetouchcount7",touchCount[7]);

                side.putIntArray("savestep0",step[0]);
                side.putIntArray("savestep1",step[1]);
                side.putIntArray("savestep2",step[2]);
                side.putIntArray("savestep3",step[3]);
                side.putIntArray("savestep4",step[4]);
                side.putIntArray("savestep5",step[5]);
                side.putIntArray("savestep6",step[6]);
                side.putIntArray("savestep7",step[7]);

                side.putInt("savered",red);

                side.putInt("savecur_x",cur_x);
                side.putInt("savecur_y",cur_y);

                side.putBoolean("saveisEnd",isEnd);

                _1.putExtras(side);
                startActivity(_1);
            }
        });

        if(save!=1)
        {
            num = (int)(Math.random() * 64);
            cur_x = num / 8;
            cur_y = num % 8;
            textViewArray[cur_x][cur_y].setText("@");
            for (int i = 0; i < 8 ;++i) for (int j = 0; j < 8; ++j) textViewArray[i][j].setTextColor(Color.parseColor("#ff0000"));

            for (int i = 0; i < 8 ;++i) for (int j = 0; j < 8; ++j) step[i][j] = 0;

            for (int i = 0; i < 8 ;++i) for (int j = 0; j < 8; ++j) touchCount[i][j] = 0;
            step[cur_x][cur_y] = 1;
            location.setText("位置 : ( " + cur_y + ", " + cur_x + " )");
            isEnd = false;
        }
        else if(save==1)
        {
            red = savered;
            level = savelevel;
            hit = savehit;
            miss = savemiss;
            score = savescore;
            msg.setText("hit: "+hit);
            missN.setText("miss: "+miss);
            scoreN.setText("score: "+score);

            step[cur_x][cur_y] = 0;
            textViewArray[cur_x][cur_y].setText("");
            cur_x = savecur_x;
            cur_y = savecur_y;
            textViewArray[cur_x][cur_y].setText("@");
            location.setText("位置 : ( " + cur_y + ", " + cur_x + " )");
            int a=0;
            for(int i=0;i<8;++i)
            {
                for(int j=0;j<8;++j)
                {
                    step[i][j]=savestep[i][j];
                    touchCount[i][j]=savetouchCount[i][j];
                    if(step[i][j]==1) ++a;
                }
            }
            //tt.setText(""+a);

            for(int i=0;i<8;++i)
            {
                for(int j=0;j<8;++j)
                {
                    if(step[i][j]==1&&!(i==cur_x&&j==cur_y))
                    {
                        textViewArray[i][j].setBackgroundResource(R.drawable.red);
                    }
                }
            }

            isEnd = saveisEnd;
        }

        if (isEnd == true) return;
        mHandler.postDelayed(runnable, level);
    }




    public void next()
    {
        if (isEnd == true) return;

        int tmp;
        int check[] = new int[8];
        int z;

        while(true){
            tmp = (int)(Math.random() * 8);
            new_x = cur_x + jump[tmp][0];
            new_y = cur_y + jump[tmp][1];

            if (check[tmp] == 0 && new_x >= 0 && new_x < 8 && new_y >= 0 && new_y < 8 && step[new_x][new_y] == 0) break;

            check[tmp] = 1;
            z = 1;
            for (int i = 0; i< 8; ++i) z &= check[i];
            if (z == 1)
            {
                isEnd = true;
                //msg.setText("Oops!!!!!!!!");
                return;
            }
        }

        textViewArray[new_x][new_y].setText("@");
        textViewArray[cur_x][cur_y].setText("");
        textViewArray[cur_x][cur_y].setBackgroundResource(R.drawable.red);
        step[new_x][new_y] = 1;
        cur_x = new_x;
        cur_y = new_y;
        location.setText("位置 : ( " + cur_y + ", " + cur_x + " )");
        red++;

        /*double t3=(hit/red)*100;
        double t4=((double)hit/(double)red)*100;
        tt.setText("R:"+red+" H:"+hit+"Division"+t4);*/

        score = Math.max(Math.ceil(((double)hit/(double)red)*100)-miss,0);
        scoreN.setText("score: "+score);

        z = 1;
        for (int i = 0; i < 8 ;++i) for (int j = 0; j < 8; ++j) z &= step[i][j];
        if (z == 1)
        {
            isEnd = true;
            //msg.setText("Great!!!!!!!!");
        }
    }

    public void finish(View view)
    {
        hit=0;
        miss=0;
        score=0;
        red=1;
        //red=0
        for (int i = 0; i < 8 ;++i) for (int j = 0; j < 8; ++j) textViewArray[i][j].setText("");
        for (int i = 0; i < 8 ;++i) for (int j = 0; j < 8; ++j) touchCount[i][j] = 0;
        num = (int)(Math.random() * 64);
        cur_x = num / 8;
        cur_y = num % 8;
        textViewArray[cur_x][cur_y].setText("@");

        msg.setText("hit: "+hit);
        missN.setText("miss: "+miss);
        scoreN.setText("score: "+score);

        for (int i = 0; i < 8 ;++i) for (int j = 0; j < 8; ++j) step[i][j] = 0;
        step[cur_x][cur_y] = 1;
        location.setText("位置 : ( " + cur_y + ", " + cur_x + " )");
        isEnd = false;

        for (int i = 0; i < 8; ++i) for (int j = 0; j < 8; ++j)
        {
            if ((i + j) % 2 == 0) textViewArray[i][j].setBackgroundResource(R.drawable.black);
            else textViewArray[i][j].setBackgroundResource(R.drawable.white);
        }

        //msg.setText("");
        mHandler.removeCallbacksAndMessages(null);
        mHandler.removeCallbacksAndMessages(0);

        if (isEnd == true) return;
        mHandler.postDelayed(runnable, level);
    }

    private void initializeMessages(){
        createMsg = (String) getResources().getText(R.string.create_message);
        startMsg = (String) getResources().getText(R.string.start_message);
        resumeMsg = (String) getResources().getText(R.string.resume_message);
        pauseMsg = (String) getResources().getText(R.string.pause_message);
        stopMsg = (String) getResources().getText(R.string.stop_message);
        restartMsg = (String) getResources().getText(R.string.restart_message);
    }


    @Override
    protected void onStart(){
        super.onStart();
        Toast.makeText(this, startMsg, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onResume(){
        super.onResume();
        Toast.makeText(this, resumeMsg, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onPause(){
        super.onPause();
        Toast.makeText(this, pauseMsg, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onStop(){
        super.onStop();
        Toast.makeText(this, stopMsg, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        Toast.makeText(this, restartMsg, Toast.LENGTH_LONG).show();
    }


    public void click00(View view)
    {
        if(isEnd) return;
        if(cur_x==0&&cur_y==0&&touchCount[0][0]==0) {hit++;touchCount[0][0]++;}
        else if(cur_x!=0||cur_y!=0) miss++;
        missN.setText("miss: "+miss);
        msg.setText("hit: "+hit);
        score = Math.max(Math.ceil(((double)hit/(double)red)*100)-miss,0);
        scoreN.setText("score: "+score);
    }
    public void click01(View view)
    {
        if(isEnd) return;
        if(cur_x==0&&cur_y==1&&touchCount[0][1]==0) {hit++;touchCount[0][1]++;}
        else if(cur_x!=0||cur_y!=1) miss++;
        missN.setText("miss: "+miss);
        msg.setText("hit: "+hit);
        score = Math.max(Math.ceil(((double)hit/(double)red)*100)-miss,0);
        scoreN.setText("score: "+score);
    }
    public void click02(View view)
    {
        if(isEnd) return;
        if(cur_x==0&&cur_y==2&&touchCount[0][2]==0) {hit++;touchCount[0][2]++;}
        else if(cur_x!=0||cur_y!=2) miss++;
        missN.setText("miss: "+miss);
        msg.setText("hit: "+hit);
        score = Math.max(Math.ceil(((double)hit/(double)red)*100)-miss,0);
        scoreN.setText("score: "+score);
    }
    public void click03(View view)
    {
        if(isEnd) return;
        if(cur_x==0&&cur_y==3&&touchCount[0][3]==0) {hit++;touchCount[0][3]++;}
        else if(cur_x!=0||cur_y!=3) miss++;
        missN.setText("miss: "+miss);
        msg.setText("hit: "+hit);
        score = Math.max(Math.ceil(((double)hit/(double)red)*100)-miss,0);
        scoreN.setText("score: "+score);
    }
    public void click04(View view)
    {
        if(isEnd) return;
        if(cur_x==0&&cur_y==4&&touchCount[0][4]==0) {hit++;touchCount[0][4]++;}
        else if(cur_x!=0||cur_y!=4) miss++;
        missN.setText("miss: "+miss);
        msg.setText("hit: "+hit);
        score = Math.max(Math.ceil(((double)hit/(double)red)*100)-miss,0);
        scoreN.setText("score: "+score);
    }
    public void click05(View view)
    {
        if(isEnd) return;
        if(cur_x==0&&cur_y==5&&touchCount[0][5]==0) {hit++;touchCount[0][5]++;}
        else if(cur_x!=0||cur_y!=5) miss++;
        missN.setText("miss: "+miss);
        msg.setText("hit: "+hit);
        score = Math.max(Math.ceil(((double)hit/(double)red)*100)-miss,0);
        scoreN.setText("score: "+score);
    }
    public void click06(View view)
    {
        if(isEnd) return;
        if(cur_x==0&&cur_y==6&&touchCount[0][6]==0) {hit++;touchCount[0][6]++;}
        else if(cur_x!=0||cur_y!=6) miss++;
        missN.setText("miss: "+miss);
        msg.setText("hit: "+hit);
        score = Math.max(Math.ceil(((double)hit/(double)red)*100)-miss,0);
        scoreN.setText("score: "+score);
    }
    public void click07(View view)
    {
        if(isEnd) return;
        if(cur_x==0&&cur_y==7&&touchCount[0][7]==0) {hit++;touchCount[0][7]++;}
        else if(cur_x!=0||cur_y!=7) miss++;
        missN.setText("miss: "+miss);
        msg.setText("hit: "+hit);
        score = Math.max(Math.ceil(((double)hit/(double)red)*100)-miss,0);
        scoreN.setText("score: "+score);
    }

    public void click10(View view)
    {
        if(isEnd) return;
        if(cur_x==1&&cur_y==0&&touchCount[1][0]==0) {hit++;touchCount[1][0]++;}
        else if(cur_x!=1||cur_y!=0) miss++;
        missN.setText("miss: "+miss);
        msg.setText("hit: "+hit);
        score = Math.max(Math.ceil(((double)hit/(double)red)*100)-miss,0);
        scoreN.setText("score: "+score);
    }
    public void click11(View view)
    {
        if(isEnd) return;
        if(cur_x==1&&cur_y==1&&touchCount[1][1]==0) {hit++;touchCount[1][1]++;}
        else if(cur_x!=1||cur_y!=1) miss++;
        missN.setText("miss: "+miss);
        msg.setText("hit: "+hit);
        score = Math.max(Math.ceil(((double)hit/(double)red)*100)-miss,0);
        scoreN.setText("score: "+score);
    }
    public void click12(View view)
    {
        if(isEnd) return;
        if(cur_x==1&&cur_y==2&&touchCount[1][2]==0) {hit++;touchCount[1][2]++;}
        else if(cur_x!=1||cur_y!=2) miss++;
        missN.setText("miss: "+miss);
        msg.setText("hit: "+hit);
        score = Math.max(Math.ceil(((double)hit/(double)red)*100)-miss,0);
        scoreN.setText("score: "+score);
    }
    public void click13(View view)
    {
        if(isEnd) return;
        if(cur_x==1&&cur_y==3&&touchCount[1][3]==0) {hit++;touchCount[1][3]++;}
        else if(cur_x!=1||cur_y!=3) miss++;
        missN.setText("miss: "+miss);
        msg.setText("hit: "+hit);
        score = Math.max(Math.ceil(((double)hit/(double)red)*100)-miss,0);
        scoreN.setText("score: "+score);
    }
    public void click14(View view)
    {
        if(isEnd) return;
        if(cur_x==1&&cur_y==4&&touchCount[1][4]==0) {hit++;touchCount[1][4]++;}
        else if(cur_x!=1||cur_y!=4) miss++;
        missN.setText("miss: "+miss);
        msg.setText("hit: "+hit);
        score = Math.max(Math.ceil(((double)hit/(double)red)*100)-miss,0);
        scoreN.setText("score: "+score);
    }
    public void click15(View view)
    {
        if(isEnd) return;
        if(cur_x==1&&cur_y==5&&touchCount[1][5]==0) {hit++;touchCount[1][5]++;}
        else if(cur_x!=1||cur_y!=5) miss++;
        missN.setText("miss: "+miss);
        msg.setText("hit: "+hit);
        score = Math.max(Math.ceil(((double)hit/(double)red)*100)-miss,0);
        scoreN.setText("score: "+score);
    }
    public void click16(View view)
    {
        if(isEnd) return;
        if(cur_x==1&&cur_y==6&&touchCount[1][6]==0) {hit++;touchCount[1][6]++;}
        else if(cur_x!=1||cur_y!=6) miss++;
        missN.setText("miss: "+miss);
        msg.setText("hit: "+hit);
        score = Math.max(Math.ceil(((double)hit/(double)red)*100)-miss,0);
        scoreN.setText("score: "+score);
    }
    public void click17(View view)
    {
        if(isEnd) return;
        if(cur_x==1&&cur_y==7&&touchCount[1][7]==0) {hit++;touchCount[1][7]++;}
        else if(cur_x!=1||cur_y!=7) miss++;
        missN.setText("miss: "+miss);
        msg.setText("hit: "+hit);
        score = Math.max(Math.ceil(((double)hit/(double)red)*100)-miss,0);
        scoreN.setText("score: "+score);
    }

    public void click20(View view)
    {
        if(isEnd) return;
        if(cur_x==2&&cur_y==0&&touchCount[2][0]==0) {hit++;touchCount[2][0]++;}
        else if(cur_x!=2||cur_y!=0) miss++;
        missN.setText("miss: "+miss);
        msg.setText("hit: "+hit);
        score = Math.max(Math.ceil(((double)hit/(double)red)*100)-miss,0);
        scoreN.setText("score: "+score);
    }
    public void click21(View view)
    {
        if(isEnd) return;
        if(cur_x==2&&cur_y==1&&touchCount[2][1]==0) {hit++;touchCount[2][1]++;}
        else if(cur_x!=2||cur_y!=1) miss++;
        missN.setText("miss: "+miss);
        msg.setText("hit: "+hit);
        score = Math.max(Math.ceil(((double)hit/(double)red)*100)-miss,0);
        scoreN.setText("score: "+score);
    }
    public void click22(View view)
    {
        if(isEnd) return;
        if(cur_x==2&&cur_y==2&&touchCount[2][2]==0) {hit++;touchCount[2][2]++;}
        else if(cur_x!=2||cur_y!=2) miss++;
        missN.setText("miss: "+miss);
        msg.setText("hit: "+hit);
        score = Math.max(Math.ceil(((double)hit/(double)red)*100)-miss,0);
        scoreN.setText("score: "+score);
    }
    public void click23(View view)
    {
        if(isEnd) return;
        if(cur_x==2&&cur_y==3&&touchCount[2][3]==0) {hit++;touchCount[2][3]++;}
        else if(cur_x!=2||cur_y!=3) miss++;
        missN.setText("miss: "+miss);
        msg.setText("hit: "+hit);
        score = Math.max(Math.ceil(((double)hit/(double)red)*100)-miss,0);
        scoreN.setText("score: "+score);
    }
    public void click24(View view)
    {
        if(isEnd) return;
        if(cur_x==2&&cur_y==4&&touchCount[2][4]==0) {hit++;touchCount[2][4]++;}
        else if(cur_x!=2||cur_y!=4) miss++;
        missN.setText("miss: "+miss);
        msg.setText("hit: "+hit);
        score = Math.max(Math.ceil(((double)hit/(double)red)*100)-miss,0);
        scoreN.setText("score: "+score);
    }
    public void click25(View view)
    {
        if(isEnd) return;
        if(cur_x==2&&cur_y==5&&touchCount[2][5]==0) {hit++;touchCount[2][5]++;}
        else if(cur_x!=2||cur_y!=5) miss++;
        missN.setText("miss: "+miss);
        msg.setText("hit: "+hit);
        score = Math.max(Math.ceil(((double)hit/(double)red)*100)-miss,0);
        scoreN.setText("score: "+score);
    }
    public void click26(View view)
    {
        if(isEnd) return;
        if(cur_x==2&&cur_y==6&&touchCount[2][6]==0) {hit++;touchCount[2][6]++;}
        else if(cur_x!=2||cur_y!=6) miss++;
        missN.setText("miss: "+miss);
        msg.setText("hit: "+hit);
        score = Math.max(Math.ceil(((double)hit/(double)red)*100)-miss,0);
        scoreN.setText("score: "+score);
    }
    public void click27(View view)
    {
        if(isEnd) return;
        if(cur_x==2&&cur_y==7&&touchCount[2][7]==0) {hit++;touchCount[2][7]++;}
        else if(cur_x!=2||cur_y!=7) miss++;
        missN.setText("miss: "+miss);
        msg.setText("hit: "+hit);
        score = Math.max(Math.ceil(((double)hit/(double)red)*100)-miss,0);
        scoreN.setText("score: "+score);
    }

    public void click30(View view)
    {
        if(isEnd) return;
        if(cur_x==3&&cur_y==0&&touchCount[3][0]==0) {hit++;touchCount[3][0]++;}
        else if(cur_x!=3||cur_y!=0) miss++;
        missN.setText("miss: "+miss);
        msg.setText("hit: "+hit);
        score = Math.max(Math.ceil(((double)hit/(double)red)*100)-miss,0);
        scoreN.setText("score: "+score);
    }
    public void click31(View view)
    {
        if(isEnd) return;
        if(cur_x==3&&cur_y==1&&touchCount[3][1]==0) {hit++;touchCount[3][1]++;}
        else if(cur_x!=3||cur_y!=1) miss++;
        missN.setText("miss: "+miss);
        msg.setText("hit: "+hit);
        score = Math.max(Math.ceil(((double)hit/(double)red)*100)-miss,0);
        scoreN.setText("score: "+score);
    }
    public void click32(View view)
    {
        if(isEnd) return;
        if(cur_x==3&&cur_y==2&&touchCount[3][2]==0) {hit++;touchCount[3][2]++;}
        else if(cur_x!=3||cur_y!=2) miss++;
        missN.setText("miss: "+miss);
        msg.setText("hit: "+hit);
        score = Math.max(Math.ceil(((double)hit/(double)red)*100)-miss,0);
        scoreN.setText("score: "+score);
    }
    public void click33(View view)
    {
        if(isEnd) return;
        if(cur_x==3&&cur_y==3&&touchCount[3][3]==0) {hit++;touchCount[3][3]++;}
        else if(cur_x!=3||cur_y!=3) miss++;
        missN.setText("miss: "+miss);
        msg.setText("hit: "+hit);
        score = Math.max(Math.ceil(((double)hit/(double)red)*100)-miss,0);
        scoreN.setText("score: "+score);
    }
    public void click34(View view)
    {
        if(isEnd) return;
        if(cur_x==3&&cur_y==4&&touchCount[3][4]==0) {hit++;touchCount[3][4]++;}
        else if(cur_x!=3||cur_y!=4) miss++;
        missN.setText("miss: "+miss);
        msg.setText("hit: "+hit);
        score = Math.max(Math.ceil(((double)hit/(double)red)*100)-miss,0);
        scoreN.setText("score: "+score);
    }
    public void click35(View view)
    {
        if(isEnd) return;
        if(cur_x==3&&cur_y==5&&touchCount[3][5]==0) {hit++;touchCount[3][5]++;}
        else if(cur_x!=3||cur_y!=5) miss++;
        missN.setText("miss: "+miss);
        msg.setText("hit: "+hit);
        score = Math.max(Math.ceil(((double)hit/(double)red)*100)-miss,0);
        scoreN.setText("score: "+score);
    }
    public void click36(View view)
    {
        if(isEnd) return;
        if(cur_x==3&&cur_y==6&&touchCount[3][6]==0) {hit++;touchCount[3][6]++;}
        else if(cur_x!=3||cur_y!=6) miss++;
        missN.setText("miss: "+miss);
        msg.setText("hit: "+hit);
        score = Math.max(Math.ceil(((double)hit/(double)red)*100)-miss,0);
        scoreN.setText("score: "+score);
    }
    public void click37(View view)
    {
        if(isEnd) return;
        if(cur_x==3&&cur_y==7&&touchCount[3][7]==0) {hit++;touchCount[3][7]++;}
        else if(cur_x!=3||cur_y!=7) miss++;
        missN.setText("miss: "+miss);
        msg.setText("hit: "+hit);
        score = Math.max(Math.ceil(((double)hit/(double)red)*100)-miss,0);
        scoreN.setText("score: "+score);
    }

    public void click40(View view)
    {
        if(isEnd) return;
        if(cur_x==4&&cur_y==0&&touchCount[4][0]==0) {hit++;touchCount[4][0]++;}
        else if(cur_x!=4||cur_y!=0) miss++;
        missN.setText("miss: "+miss);
        msg.setText("hit: "+hit);
        score = Math.max(Math.ceil(((double)hit/(double)red)*100)-miss,0);
        scoreN.setText("score: "+score);
    }
    public void click41(View view)
    {
        if(isEnd) return;
        if(cur_x==4&&cur_y==1&&touchCount[4][1]==0) {hit++;touchCount[4][1]++;}
        else if(cur_x!=4||cur_y!=1) miss++;
        missN.setText("miss: "+miss);
        msg.setText("hit: "+hit);
        score = Math.max(Math.ceil(((double)hit/(double)red)*100)-miss,0);
        scoreN.setText("score: "+score);
    }
    public void click42(View view)
    {
        if(isEnd) return;
        if(cur_x==4&&cur_y==2&&touchCount[4][2]==0) {hit++;touchCount[4][2]++;}
        else if(cur_x!=4||cur_y!=2) miss++;
        missN.setText("miss: "+miss);
        msg.setText("hit: "+hit);
        score = Math.max(Math.ceil(((double)hit/(double)red)*100)-miss,0);
        scoreN.setText("score: "+score);
    }
    public void click43(View view)
    {
        if(isEnd) return;
        if(cur_x==4&&cur_y==3&&touchCount[4][3]==0) {hit++;touchCount[4][3]++;}
        else if(cur_x!=4||cur_y!=3) miss++;
        missN.setText("miss: "+miss);
        msg.setText("hit: "+hit);
        score = Math.max(Math.ceil(((double)hit/(double)red)*100)-miss,0);
        scoreN.setText("score: "+score);
    }
    public void click44(View view)
    {
        if(isEnd) return;
        if(cur_x==4&&cur_y==4&&touchCount[4][4]==0) {hit++;touchCount[4][4]++;}
        else if(cur_x!=4||cur_y!=4) miss++;
        missN.setText("miss: "+miss);
        msg.setText("hit: "+hit);
        score = Math.max(Math.ceil(((double)hit/(double)red)*100)-miss,0);
        scoreN.setText("score: "+score);
    }
    public void click45(View view)
    {
        if(isEnd) return;
        if(cur_x==4&&cur_y==5&&touchCount[4][5]==0) {hit++;touchCount[4][5]++;}
        else if(cur_x!=4||cur_y!=5) miss++;
        missN.setText("miss: "+miss);
        msg.setText("hit: "+hit);
        score = Math.max(Math.ceil(((double)hit/(double)red)*100)-miss,0);
        scoreN.setText("score: "+score);
    }
    public void click46(View view)
    {
        if(isEnd) return;
        if(cur_x==4&&cur_y==6&&touchCount[4][6]==0) {hit++;touchCount[4][6]++;}
        else if(cur_x!=4||cur_y!=6) miss++;
        missN.setText("miss: "+miss);
        msg.setText("hit: "+hit);
        score = Math.max(Math.ceil(((double)hit/(double)red)*100)-miss,0);
        scoreN.setText("score: "+score);
    }
    public void click47(View view)
    {
        if(isEnd) return;
        if(cur_x==4&&cur_y==7&&touchCount[4][7]==0) {hit++;touchCount[4][7]++;}
        else if(cur_x!=4||cur_y!=7) miss++;
        missN.setText("miss: "+miss);
        msg.setText("hit: "+hit);
        score = Math.max(Math.ceil(((double)hit/(double)red)*100)-miss,0);
        scoreN.setText("score: "+score);
    }

    public void click50(View view)
    {
        if(isEnd) return;
        if(cur_x==5&&cur_y==0&&touchCount[5][0]==0) {hit++;touchCount[5][0]++;}
        else if(cur_x!=5||cur_y!=0) miss++;
        missN.setText("miss: "+miss);
        msg.setText("hit: "+hit);
        score = Math.max(Math.ceil(((double)hit/(double)red)*100)-miss,0);
        scoreN.setText("score: "+score);
    }
    public void click51(View view)
    {
        if(isEnd) return;
        if(cur_x==5&&cur_y==1&&touchCount[5][1]==0) {hit++;touchCount[5][1]++;}
        else if(cur_x!=5||cur_y!=1) miss++;
        missN.setText("miss: "+miss);
        msg.setText("hit: "+hit);
        score = Math.max(Math.ceil(((double)hit/(double)red)*100)-miss,0);
        scoreN.setText("score: "+score);
    }
    public void click52(View view)
    {
        if(isEnd) return;
        if(cur_x==5&&cur_y==2&&touchCount[5][2]==0) {hit++;touchCount[5][2]++;}
        else if(cur_x!=5||cur_y!=2) miss++;
        missN.setText("miss: "+miss);
        msg.setText("hit: "+hit);
        score = Math.max(Math.ceil(((double)hit/(double)red)*100)-miss,0);
        scoreN.setText("score: "+score);
    }
    public void click53(View view)
    {
        if(isEnd) return;
        if(cur_x==5&&cur_y==3&&touchCount[5][3]==0) {hit++;touchCount[5][3]++;}
        else if(cur_x!=5||cur_y!=3) miss++;
        missN.setText("miss: "+miss);
        msg.setText("hit: "+hit);
        score = Math.max(Math.ceil(((double)hit/(double)red)*100)-miss,0);
        scoreN.setText("score: "+score);
    }
    public void click54(View view)
    {
        if(isEnd) return;
        if(cur_x==5&&cur_y==4&&touchCount[5][4]==0) {hit++;touchCount[5][4]++;}
        else if(cur_x!=5||cur_y!=4) miss++;
        missN.setText("miss: "+miss);
        msg.setText("hit: "+hit);
        score = Math.max(Math.ceil(((double)hit/(double)red)*100)-miss,0);
        scoreN.setText("score: "+score);
    }
    public void click55(View view)
    {
        if(isEnd) return;
        if(cur_x==5&&cur_y==5&&touchCount[5][5]==0) {hit++;touchCount[5][5]++;}
        else if(cur_x!=5||cur_y!=5) miss++;
        missN.setText("miss: "+miss);
        msg.setText("hit: "+hit);
        score = Math.max(Math.ceil(((double)hit/(double)red)*100)-miss,0);
        scoreN.setText("score: "+score);
    }
    public void click56(View view)
    {
        if(isEnd) return;
        if(cur_x==5&&cur_y==6&&touchCount[5][6]==0) {hit++;touchCount[5][6]++;}
        else if(cur_x!=5||cur_y!=6) miss++;
        missN.setText("miss: "+miss);
        msg.setText("hit: "+hit);
        score = Math.max(Math.ceil(((double)hit/(double)red)*100)-miss,0);
        scoreN.setText("score: "+score);
    }
    public void click57(View view)
    {
        if(isEnd) return;
        if(cur_x==5&&cur_y==7&&touchCount[5][7]==0) {hit++;touchCount[5][7]++;}
        else if(cur_x!=5||cur_y!=7) miss++;
        missN.setText("miss: "+miss);
        msg.setText("hit: "+hit);
        score = Math.max(Math.ceil(((double)hit/(double)red)*100)-miss,0);
        scoreN.setText("score: "+score);
    }

    public void click60(View view)
    {
        if(isEnd) return;
        if(cur_x==6&&cur_y==0&&touchCount[6][0]==0) {hit++;touchCount[6][0]++;}
        else if(cur_x!=6||cur_y!=0) miss++;
        missN.setText("miss: "+miss);
        msg.setText("hit: "+hit);
        score = Math.max(Math.ceil(((double)hit/(double)red)*100)-miss,0);
        scoreN.setText("score: "+score);
    }
    public void click61(View view)
    {
        if(isEnd) return;
        if(cur_x==6&&cur_y==1&&touchCount[6][1]==0) {hit++;touchCount[6][1]++;}
        else if(cur_x!=6||cur_y!=1) miss++;
        missN.setText("miss: "+miss);
        msg.setText("hit: "+hit);
        score = Math.max(Math.ceil(((double)hit/(double)red)*100)-miss,0);
        scoreN.setText("score: "+score);
    }
    public void click62(View view)
    {
        if(isEnd) return;
        if(cur_x==6&&cur_y==2&&touchCount[6][2]==0) {hit++;touchCount[6][2]++;}
        else if(cur_x!=6||cur_y!=2) miss++;
        missN.setText("miss: "+miss);
        msg.setText("hit: "+hit);
        score = Math.max(Math.ceil(((double)hit/(double)red)*100)-miss,0);
        scoreN.setText("score: "+score);
    }
    public void click63(View view)
    {
        if(isEnd) return;
        if(cur_x==6&&cur_y==3&&touchCount[6][3]==0) {hit++;touchCount[6][3]++;}
        else if(cur_x!=6||cur_y!=3) miss++;
        missN.setText("miss: "+miss);
        msg.setText("hit: "+hit);
        score = Math.max(Math.ceil(((double)hit/(double)red)*100)-miss,0);
        scoreN.setText("score: "+score);
    }
    public void click64(View view)
    {
        if(isEnd) return;
        if(cur_x==6&&cur_y==4&&touchCount[6][4]==0) {hit++;touchCount[6][4]++;}
        else if(cur_x!=6||cur_y!=4) miss++;
        missN.setText("miss: "+miss);
        msg.setText("hit: "+hit);
        score = Math.max(Math.ceil(((double)hit/(double)red)*100)-miss,0);
        scoreN.setText("score: "+score);
    }
    public void click65(View view)
    {
        if(isEnd) return;
        if(cur_x==6&&cur_y==5&&touchCount[6][5]==0) {hit++;touchCount[6][5]++;}
        else if(cur_x!=6||cur_y!=5) miss++;
        missN.setText("miss: "+miss);
        msg.setText("hit: "+hit);
        score = Math.max(Math.ceil(((double)hit/(double)red)*100)-miss,0);
        scoreN.setText("score: "+score);
    }
    public void click66(View view)
    {
        if(isEnd) return;
        if(cur_x==6&&cur_y==6&&touchCount[6][6]==0) {hit++;touchCount[6][6]++;}
        else if(cur_x!=6||cur_y!=6) miss++;
        missN.setText("miss: "+miss);
        msg.setText("hit: "+hit);
        score = Math.max(Math.ceil(((double)hit/(double)red)*100)-miss,0);
        scoreN.setText("score: "+score);
    }
    public void click67(View view)
    {
        if(isEnd) return;
        if(cur_x==6&&cur_y==7&&touchCount[6][7]==0) {hit++;touchCount[6][7]++;}
        else if(cur_x!=6||cur_y!=7) miss++;
        missN.setText("miss: "+miss);
        msg.setText("hit: "+hit);
        score = Math.max(Math.ceil(((double)hit/(double)red)*100)-miss,0);
        scoreN.setText("score: "+score);
    }

    public void click70(View view)
    {
        if(isEnd) return;
        if(cur_x==7&&cur_y==0&&touchCount[7][0]==0) {hit++;touchCount[7][0]++;}
        else if(cur_x!=7||cur_y!=0) miss++;
        missN.setText("miss: "+miss);
        msg.setText("hit: "+hit);
        score = Math.max(Math.ceil(((double)hit/(double)red)*100)-miss,0);
        scoreN.setText("score: "+score);
    }
    public void click71(View view)
    {
        if(isEnd) return;
        if(cur_x==7&&cur_y==1&&touchCount[7][1]==0) {hit++;touchCount[7][1]++;}
        else if(cur_x!=7||cur_y!=1) miss++;
        missN.setText("miss: "+miss);
        msg.setText("hit: "+hit);
        score = Math.max(Math.ceil(((double)hit/(double)red)*100)-miss,0);
        scoreN.setText("score: "+score);
    }
    public void click72(View view)
    {
        if(isEnd) return;
        if(cur_x==7&&cur_y==2&&touchCount[7][2]==0) {hit++;touchCount[7][2]++;}
        else if(cur_x!=7||cur_y!=2) miss++;
        missN.setText("miss: "+miss);
        msg.setText("hit: "+hit);
        score = Math.max(Math.ceil(((double)hit/(double)red)*100)-miss,0);
        scoreN.setText("score: "+score);
    }
    public void click73(View view)
    {
        if(isEnd) return;
        if(cur_x==7&&cur_y==3&&touchCount[7][3]==0) {hit++;touchCount[7][3]++;}
        else if(cur_x!=7||cur_y!=3) miss++;
        missN.setText("miss: "+miss);
        msg.setText("hit: "+hit);
        score = Math.max(Math.ceil(((double)hit/(double)red)*100)-miss,0);
        scoreN.setText("score: "+score);
    }
    public void click74(View view)
    {
        if(isEnd) return;
        if(cur_x==7&&cur_y==4&&touchCount[7][4]==0) {hit++;touchCount[7][4]++;}
        else if(cur_x!=7||cur_y!=4) miss++;
        missN.setText("miss: "+miss);
        msg.setText("hit: "+hit);
        score = Math.max(Math.ceil(((double)hit/(double)red)*100)-miss,0);
        scoreN.setText("score: "+score);
    }
    public void click75(View view)
    {
        if(isEnd) return;
        if(cur_x==7&&cur_y==5&&touchCount[7][5]==0) {hit++;touchCount[7][5]++;}
        else if(cur_x!=7||cur_y!=5) miss++;
        missN.setText("miss: "+miss);
        msg.setText("hit: "+hit);
        score = Math.max(Math.ceil(((double)hit/(double)red)*100)-miss,0);
        scoreN.setText("score: "+score);
    }
    public void click76(View view)
    {
        if(isEnd) return;
        if(cur_x==7&&cur_y==6&&touchCount[7][6]==0) {hit++;touchCount[7][6]++;}
        else if(cur_x!=7||cur_y!=6) miss++;
        missN.setText("miss: "+miss);
        msg.setText("hit: "+hit);
        score = Math.max(Math.ceil(((double)hit/(double)red)*100)-miss,0);
        scoreN.setText("score: "+score);
    }
    public void click77(View view)
    {
        if(isEnd) return;
        if(cur_x==7&&cur_y==7&&touchCount[7][7]==0) {hit++;touchCount[7][7]++;}
        else if(cur_x!=7||cur_y!=7) miss++;
        missN.setText("miss: "+miss);
        msg.setText("hit: "+hit);
        score = Math.max(Math.ceil(((double)hit/(double)red)*100)-miss,0);
        scoreN.setText("score: "+score);
    }
}


