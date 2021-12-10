package com.cornez.shippingcalculator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class output extends Activity
{
    private Button gotoIntro;
    private Button gotoInput;
    private TextView alarm;
    private TextView stdw;
    private TextView range;
    private TextView Ko;

    private double height;
    private double weight;
    private int activity;
    private double kneeLen;
    private int age;
    private int isMale;
    private int isInputHeight;

    protected void onCreate (Bundle savedInstance)
    {
        super.onCreate(savedInstance);
        setContentView(R.layout.output);

        gotoInput = (Button) findViewById(R.id.button);
        gotoIntro = (Button) findViewById(R.id.button2);
        alarm = (TextView) findViewById(R.id.textView8);
        stdw = (TextView) findViewById(R.id.textView14);
        range = (TextView) findViewById(R.id.textView13);
        Ko = (TextView) findViewById(R.id.textView12);

        Bundle bd = getIntent().getExtras();
        height = bd.getDouble("height");
        weight = bd.getDouble("weight");
        activity = bd.getInt("activity");
        kneeLen = bd.getDouble("kneeLen");
        age = bd.getInt("age");
        isMale = bd.getInt("isMale");
        isInputHeight = bd.getInt("isInputHeight");

        compute();

        gotoInput.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view)
            {
                Intent input1 = new Intent(output.this, MyActivity.class);
                Bundle side = new Bundle();

                side.putDouble("height", height);
                side.putDouble("weight", weight);
                side.putInt("activity", activity);
                side.putDouble("kneeLen", kneeLen);
                side.putInt("age", age);

                side.putInt("app", 2);
                side.putInt("isMale", isMale);
                side.putInt("isInputHeight", isInputHeight);

                input1.putExtras(side);
                startActivity(input1);
            }
        });

        gotoIntro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intro1 = new Intent(output.this, intro.class);
                Bundle side = new Bundle();

                side.putDouble("height", height);
                side.putDouble("weight", weight);
                side.putInt("activity", activity);
                side.putDouble("kneeLen", kneeLen);
                side.putInt("age", age);

                side.putInt("app", 2);
                side.putInt("isMale", isMale);
                side.putInt("isInputHeight", isInputHeight);

                intro1.putExtras(side);
                startActivity(intro1);
            }
        });
    }

    @Override
    protected void onStart()
    {
        super.onStart();

        Bundle bd = getIntent().getExtras();
        if (bd.getInt("app")== 1) overridePendingTransition(R.anim.atob, R.anim.btoa);
        else overridePendingTransition(R.anim.btoc, R.anim.ctob);
    }

    public void compute()
    {
        alarm.setText("");

        if ((weight > 0 && activity != 0) && (height > 0 || (kneeLen > 0 && age > 0)))
        {
            double h = 0;

            // compute h
            if (isInputHeight == 1) h = height;
            else
            {
                if (isMale == 1) h = 85.1 + (1.73 * kneeLen) - (0.11 * age);
                else h = 91.45 + (1.53 * kneeLen) - (0.16 * age);
                h = Math.round(h*10.0)/10.0;
            }

            if (h > 0 && weight > 0 && activity >= 1 && activity <= 3)
            {
                double stdweight;
                double range1;
                double range2;
                double K;

                if (isMale == 1) stdweight = (h - 80) * 0.7;
                else stdweight = (h - 70) * 0.6;
                range1 = stdweight * 0.9;
                range2 = stdweight * 1.1;
                if (activity == 1)
                {
                    if (weight < range1) K = stdweight * 35;
                    else if (weight > range2) K = stdweight * 25;
                    else K = stdweight * 30;
                }
                else if (activity == 2)
                {
                    if (weight < range1) K = stdweight * 40;
                    else if (weight > range2) K = stdweight * 30;
                    else K = stdweight * 35;
                }
                else
                {
                    if (weight < range1) K = stdweight * 45;
                    else if (weight > range2) K = stdweight * 35;
                    else K = stdweight * 40;
                }

                stdw.setText("標準體重: " + Math.round(stdweight*10.0)/10.0 + "公斤");
                range.setText("體重合理範圍: " + Math.round(range1*10.0)/10.0 + " ~ " + Math.round(range2*10.0)/10.0 + "公斤");
                Ko.setText("每日所需熱量: " + Math.round(K*10.0)/10.0 + "大卡");
            }
            else alarm.setText("請完整輸入!!!!!!!");
        }
        else alarm.setText("請完整輸入!!!!!!!");
    }
}
