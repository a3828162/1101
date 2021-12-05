package com.cornez.shippingcalculator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class output extends Activity
{

    private TextView Texstdweight;
    private TextView Texrange;
    private TextView TexK;
    private TextView wrong;

    private double TransHeight, TransWeight, TransActivity,  TransKneeLen, TransAge, TransGender, TransMode;
    private int a;

    protected void onCreate (Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.output);

        Bundle bd = getIntent().getExtras();

        Texstdweight = (TextView)findViewById(R.id.textView14);
        Texrange = (TextView)findViewById(R.id.textView13);
        TexK = (TextView)findViewById(R.id.textView12);
        wrong = (TextView)findViewById(R.id.textView8);

        TransHeight = bd.getDouble("TransHeight");
        TransWeight = bd.getDouble("TransWeight");
        TransActivity = bd.getDouble("TransActivity");
        TransKneeLen = bd.getDouble("TransKneeLen");
        TransAge = bd.getDouble("TransAge");
        TransGender = bd.getDouble("TransGender");
        TransMode = bd.getDouble("TransMode");
        a=bd.getInt("App");

        Button btn1 = (Button) findViewById(R.id.button);
        btn1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent input1 = new Intent(output.this, MyActivity.class);

                Bundle side = new Bundle();
                Bundle bd = getIntent().getExtras();

                //side.putInt("app", 2);


                side.putDouble("TransHeight", TransHeight);
                side.putDouble("TransWeight", TransWeight);
                side.putDouble("TransActivity", TransActivity);
                side.putDouble("TransKneeLen", TransKneeLen);
                side.putDouble("TransAge", TransAge);
                side.putDouble("TransGender", TransGender);
                side.putDouble("TransMode", TransMode );
                side.putInt("App",2);

                input1.putExtras(side);

                startActivity(input1);
            }
        });

        Button btn2 = (Button) findViewById(R.id.button2);
        btn2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intro1 = new Intent(output.this, intro.class);

                Bundle side = new Bundle();
                Bundle bd = getIntent().getExtras();

                //side.putInt("app", 2);



                side.putDouble("TransHeight", TransHeight);
                side.putDouble("TransWeight", TransWeight);
                side.putDouble("TransActivity", TransActivity);
                side.putDouble("TransKneeLen", TransKneeLen);
                side.putDouble("TransAge", TransAge);
                side.putDouble("TransGender", TransGender);
                side.putDouble("TransMode", TransMode );
                side.putInt("App",2);
                intro1.putExtras(side);

                startActivity(intro1);
            }
        });

        compute();
    }

    public void compute()
    {
        //Texstdweight.setText(""+TransGender);

        if(TransMode==0) {
            if(TransWeight==0||TransActivity==0||TransHeight==0) {
                //Texstdweight.setText("請完整輸入");
                //Texstdweight.setText("請完整輸入");
                wrong.setText("請完整輸入");
                return;
            }
        }
        if(TransMode==1) {
            if(TransWeight==0||TransActivity==0||TransKneeLen==0||TransAge==0) {
                ///Texstdweight.setText("請完整輸入222  "+TransWeight+" "+TransActivity+" "+TransKneeLen+" "+TransAge);
                //Texstdweight.setText("請完整輸入");
                wrong.setText("請完整輸入");

                return;
            }
        }
        if(TransActivity<1||TransActivity>3)
        {
            //Texstdweight.setText("請完整輸入222  "+TransWeight+" "+TransActivity+" "+TransKneeLen+" "+TransAge);
            //Texstdweight.setText("請完整輸入");
            wrong.setText("請完整輸入");

            return;
        }
        if (TransMode == 1) {
            if(TransGender==1) TransHeight = 85.1 + (1.73 * TransKneeLen - 0.11 * TransAge);
            else TransHeight = 91.45 + (1.53 * TransKneeLen - (0.16 * TransAge));
        }

        double stdweight=0;
        double range1=0,range2=0;
        double K=0;
        if (TransGender>0) stdweight = (TransHeight - 80) * 0.7;
        else stdweight = (TransHeight - 70) * 0.6;
        range1 = stdweight * 0.9;
        range2 = stdweight * 1.1;
        if (TransActivity == 1) {
            if (TransWeight < range1) K = stdweight * 35;
            else if (TransWeight > range2) K = stdweight * 25;
            else K = stdweight * 30;
        }
        else if (TransActivity == 2) {
            if (TransWeight < range1) K = stdweight * 40;
            else if (TransWeight > range2) K = stdweight * 30;
            else K = stdweight * 35;
        }
        else {
            if (TransWeight < range1) K = stdweight * 45;
            else if (TransWeight > range2) K = stdweight * 35;
            else K = stdweight * 40;
        }
        stdweight=(Math.round(stdweight * 10.0) / 10.0);
        range1=(Math.round(range1 * 10.0) / 10.0);
        range2=(Math.round(range2 * 10.0) / 10.0);
        K=(Math.round(K * 10.0) / 10.0);

        Texstdweight.setText("標準體重: " + stdweight + "公斤");
        Texrange.setText("體重合理範圍: " + range1 + " ~ " + range2 + "公斤");
        TexK.setText("每日所需熱量: " + K + "大卡");

    }

    @Override
    protected void onStart() {
        super.onStart();

        //Bundle bd = getIntent().getExtras();
        //int x = bd.getInt("app");

        if (a == 1) overridePendingTransition(R.anim.atob, R.anim.btoa);
        else if(a==3) overridePendingTransition(R.anim.btoc, R.anim.ctob);
    }

}
