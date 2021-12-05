package com.cornez.shippingcalculator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class intro extends Activity {

    private TextView test;
    private double TransHeight, TransWeight, TransActivity,  TransKneeLen, TransAge, TransGender, TransMode;
    private Button exi;
    private int a;

    protected void onCreate (Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.intro);
        Bundle bd = getIntent().getExtras();
        TransHeight = bd.getDouble("TransHeight");
        TransWeight = bd.getDouble("TransWeight");
        TransActivity = bd.getDouble("TransActivity");
        TransKneeLen = bd.getDouble("TransKneeLen");
        TransAge = bd.getDouble("TransAge");
        TransGender = bd.getDouble("TransGender");
        TransMode = bd.getDouble("TransMode");
        a=bd.getInt("App");

        exi = (Button)findViewById(R.id.button3);

        //test = (TextView)findViewById(R.id.textView7);
        //test.setText(""+TransActivity+TransHeight+TransWeight);
        Button btn1 = (Button) findViewById(R.id.button);
        btn1.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View view) {
                Intent output1 = new Intent(intro.this, output.class);

                Bundle side = new Bundle();
                Bundle bd = getIntent().getExtras();

                side.putDouble("TransHeight", TransHeight);
                side.putDouble("TransWeight", TransWeight);
                side.putDouble("TransActivity", TransActivity);
                side.putDouble("TransKneeLen", TransKneeLen);
                side.putDouble("TransAge", TransAge);
                side.putDouble("TransGender", TransGender);
                side.putDouble("TransMode", TransMode );
                side.putInt("App",3);

                output1.putExtras(side);
                startActivity(output1);
            }
        });

        Button btn2 = (Button) findViewById(R.id.button2);
        btn2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent input1 = new Intent(intro.this, MyActivity.class);

                Bundle side = new Bundle();
                //Bundle bd = getIntent().getExtras();

                //side.putInt("app", 2);


                side.putDouble("TransHeight", TransHeight);
                side.putDouble("TransWeight", TransWeight);
                side.putDouble("TransActivity", TransActivity);
                side.putDouble("TransKneeLen", TransKneeLen);
                side.putDouble("TransAge", TransAge);
                side.putDouble("TransGender", TransGender);
                side.putDouble("TransMode", TransMode );
                side.putInt("App",3);
                input1.putExtras(side);
                startActivity(input1);
            }
        });
    }

    public void exit(View view)
    {
        /*Intent intent = new Intent(this, MyActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("Exit me", true);
        startActivity(intent);
        finish();*/
        finishAffinity();
    }

    @Override
    protected void onStart() {
        super.onStart();

        //Bundle bd = getIntent().getExtras();
        //int x = bd.getInt("app");

        if (a == 2) overridePendingTransition(R.anim.atob, R.anim.btoa);
        else if(a==1) overridePendingTransition(R.anim.btoc, R.anim.ctob);
    }

}