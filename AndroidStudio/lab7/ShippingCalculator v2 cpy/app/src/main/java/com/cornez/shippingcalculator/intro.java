package com.cornez.shippingcalculator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class intro extends Activity
{
    private Button gotoOutput;
    private Button gotoInput;

    protected void onCreate (Bundle savedInstance)
    {
        super.onCreate(savedInstance);
        setContentView(R.layout.intro);

        gotoOutput = (Button) findViewById(R.id.button);
        gotoInput = (Button) findViewById(R.id.button2);

        gotoOutput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent output1 = new Intent(intro.this, output.class);
                Bundle side = new Bundle();
                Bundle bd = getIntent().getExtras();

                side.putDouble("height", bd.getDouble("height"));
                side.putDouble("weight", bd.getDouble("weight"));
                side.putInt("activity", bd.getInt("activity"));
                side.putDouble("kneeLen", bd.getDouble("kneeLen"));
                side.putInt("age", bd.getInt("age"));

                side.putInt("app", 3);
                side.putInt("isMale", bd.getInt("isMale"));
                side.putInt("isInputHeight", bd.getInt("isInputHeight"));

                output1.putExtras(side);
                startActivity(output1);
            }
        });

        gotoInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent input1 = new Intent(intro.this, MyActivity.class);
                Bundle side = new Bundle();
                Bundle bd = getIntent().getExtras();

                side.putDouble("height", bd.getDouble("height"));
                side.putDouble("weight", bd.getDouble("weight"));
                side.putInt("activity", bd.getInt("activity"));
                side.putDouble("kneeLen", bd.getDouble("kneeLen"));
                side.putInt("age", bd.getInt("age"));

                side.putInt("app", 3);
                side.putInt("isMale", bd.getInt("isMale"));
                side.putInt("isInputHeight", bd.getInt("isInputHeight"));

                input1.putExtras(side);
                startActivity(input1);
            }
        });
    }

    @Override
    protected void onStart()
    {
        super.onStart();

        Bundle bd = getIntent().getExtras();
        if (bd.getInt("app") == 2) overridePendingTransition(R.anim.atob, R.anim.btoa);
        else overridePendingTransition(R.anim.btoc, R.anim.ctob);
    }

    public void finish(View view)
    {
        finishAffinity();
    }
}