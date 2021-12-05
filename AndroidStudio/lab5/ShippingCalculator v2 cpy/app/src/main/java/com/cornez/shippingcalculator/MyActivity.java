package com.cornez.shippingcalculator;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Intent;

public class MyActivity extends Activity {

    //DATA MODEL FOR SHIP ITEM
    private ShipItem shipItem;

    //VIEW OBJECTS FOR LAYOUT UI REFERENCE
    private EditText height;
    private EditText weight;
    private EditText activity;
    private EditText kneeLen;
    private EditText age;

    private TextView alarm;
    private Button btn1;
    private Button btn2;

    private double d1, d2, d3, d4, d5, d6;
    private double TransHeight,TransWeight,TransActivity,TransKneeLen,TransAge,TransGender=1,TransMode;
    private int a;

    /*@Override
    public void onConfigurationChange(Configuration newConfig)
    {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // 什麼都不用寫
            setContentView(R.layout.land);
        }
        else {
            // 什麼都不用寫
            setContentView(R.layout.activity_my_2);
        }
    }*/

    /*@Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // land do nothing is ok
            setContentView(R.layout.land);
        } else if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            // port do nothing is ok
            setContentView(R.layout.activity_my_2);
        }
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_2);

        //CREATE THE DATA MODEL FOR STORING THE ITEM TO BE SHIPPED
        shipItem = new ShipItem();

        //TASK 3: ESTABLISH THE REFERENCES TO INPUT WEIGHT ELEMENT
        height = (EditText) findViewById(R.id.editTextTextPersonName);
        weight = (EditText) findViewById(R.id.editTextTextPersonName2);
        activity = (EditText) findViewById(R.id.editTextTextPersonName3);
        kneeLen = (EditText) findViewById(R.id.editTextTextPersonName5);
        age = (EditText) findViewById(R.id.editTextTextPersonName4);

        alarm = (TextView) findViewById(R.id.textView11);

        // add button
        btn1 = (Button) findViewById((R.id.button));
        btn2 = (Button) findViewById((R.id.button4));

        /*if(savedInstanceState!=null)
        {
            TransHeight = savedInstanceState.getDouble("TransHeight");
            TransWeight = savedInstanceState.getDouble("TransWeight");
            TransActivity = savedInstanceState.getDouble("TransActivity");
            TransKneeLen = savedInstanceState.getDouble("TransKneeLen");
            TransAge = savedInstanceState.getDouble("TransAge");
            TransGender = savedInstanceState.getDouble("TransGender");
            TransMode = savedInstanceState.getDouble("TransMode");
            a=savedInstanceState.getInt("App");

            TransHeight=(Math.round(TransHeight * 10.0) / 10.0);
            TransWeight=(Math.round(TransWeight * 10.0) / 10.0);
            TransActivity=(Math.round(TransActivity * 10.0) / 10.0);
            TransKneeLen=(Math.round(TransKneeLen * 10.0) / 10.0);
            TransAge=(Math.round(TransAge * 10.0) / 10.0);

            if(TransHeight!=0) height.setText("" + TransHeight);
            else height.setText("");
            if(TransWeight!=0) weight.setText(""+ TransWeight);
            else weight.setText("");
            if(TransActivity!=0) activity.setText(""+ (int)TransActivity);
            else weight.setText("");
            if(TransKneeLen!=0) kneeLen.setText(""+(int)TransKneeLen);
            else kneeLen.setText("");
            if(TransAge!=0) age.setText(""+(int)TransAge);
            else age.setText("");
            alarm.setText(""+savedInstanceState.getDouble("TransHeight"));
        }*/
        Bundle bd = getIntent().getExtras();
        if (bd != null)
        {
            TransHeight = bd.getDouble("TransHeight");
            TransWeight = bd.getDouble("TransWeight");
            TransActivity = bd.getDouble("TransActivity");
            TransKneeLen = bd.getDouble("TransKneeLen");
            TransAge = bd.getDouble("TransAge");
            TransGender = bd.getDouble("TransGender");
            TransMode = bd.getDouble("TransMode");
            a=bd.getInt("App");

            TransHeight=(Math.round(TransHeight * 10.0) / 10.0);
            TransWeight=(Math.round(TransWeight * 10.0) / 10.0);
            TransActivity=(Math.round(TransActivity * 10.0) / 10.0);
            TransKneeLen=(Math.round(TransKneeLen * 10.0) / 10.0);
            TransAge=(Math.round(TransAge * 10.0) / 10.0);

            if(TransHeight!=0) height.setText("" + TransHeight);
            else height.setText("");
            if(TransWeight!=0) weight.setText(""+ TransWeight);
            else weight.setText("");
            if(TransActivity!=0) activity.setText(""+ (int)TransActivity);
            else weight.setText("");
            if(TransKneeLen!=0) kneeLen.setText(""+(int)TransKneeLen);
            else kneeLen.setText("");
            if(TransAge!=0) age.setText(""+(int)TransAge);
            else age.setText("");


            //alarm.setText(""+TransGender);

        }
        //alarm.setText(""+TransGender);
        //kneeLen.setFocusableInTouchMode(false);
        //age.setFocusableInTouchMode(false);


        setg((int)TransGender);
        iii((int)TransMode);


        Button btn3 = (Button) findViewById(R.id.button6);
        btn3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent output1 = new Intent(MyActivity.this, output.class);

                Bundle side = new Bundle();
                side.putInt("app", 1);


                if(!height.getText().toString().isEmpty())TransHeight=Double.parseDouble(height.getText().toString());
                else TransHeight=0;
                if(!weight.getText().toString().isEmpty())TransWeight=Double.parseDouble(weight.getText().toString());
                else TransWeight=0;
                if(!activity.getText().toString().isEmpty())TransActivity=Double.parseDouble(activity.getText().toString());
                else TransActivity=0;
                if(!kneeLen.getText().toString().isEmpty())TransKneeLen=Double.parseDouble(kneeLen.getText().toString());
                else TransKneeLen=0;
                if(!age.getText().toString().isEmpty()) TransAge=Double.parseDouble(age.getText().toString());
                else TransAge=0;
                if(shipItem.getGender()==0)TransGender=0;
                else TransGender=1;
                if(shipItem.getIn()==1) TransMode=1;
                else TransMode=0;

                side.putDouble("TransHeight", TransHeight);
                side.putDouble("TransWeight", TransWeight);
                side.putDouble("TransActivity", TransActivity);
                side.putDouble("TransKneeLen", TransKneeLen);
                side.putDouble("TransAge", TransAge);
                side.putDouble("TransGender", TransGender);
                side.putDouble("TransMode", TransMode);
                side.putInt("App",1);

                output1.putExtras(side);

                startActivity(output1);
            }
        });

        Button btn4 = (Button) findViewById(R.id.button5);
        btn4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intro1 = new Intent(MyActivity.this, intro.class);

                Bundle side = new Bundle();
                side.putInt("app", 1);




                if(!height.getText().toString().isEmpty())TransHeight=Double.parseDouble(height.getText().toString());
                else TransHeight=0;
                if(!weight.getText().toString().isEmpty())TransWeight=Double.parseDouble(weight.getText().toString());
                else TransWeight=0;
                if(!activity.getText().toString().isEmpty())TransActivity=Double.parseDouble(activity.getText().toString());
                else TransActivity=0;
                if(!kneeLen.getText().toString().isEmpty())TransKneeLen=Double.parseDouble(kneeLen.getText().toString());
                else TransKneeLen=0;
                if(!age.getText().toString().isEmpty()) TransAge=Double.parseDouble(age.getText().toString());
                else TransAge=0;
                if(shipItem.getGender()==0)TransGender=0;
                else TransGender=1;
                if(shipItem.getIn()==1) TransMode=1;
                else TransMode=0;

                side.putDouble("TransHeight", TransHeight);
                side.putDouble("TransWeight", TransWeight);
                side.putDouble("TransActivity", TransActivity);
                side.putDouble("TransKneeLen", TransKneeLen);
                side.putDouble("TransAge", TransAge);
                side.putDouble("TransGender", TransGender);
                side.putDouble("TransMode", TransMode);
                side.putInt("App",1);

                intro1.putExtras(side);

                startActivity(intro1);
                //android.os.Process.killProcess(android.os.Process.myPid());
            }
        });

        kneeLen.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void afterTextChanged(Editable s) {c();}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {  }
        });

        age.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void afterTextChanged(Editable s) {c();}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {  }
        });


    }

    /*@Override
    public void onSaveInstanceState(Bundle savedInstanceState) {

        // Save away the original text, so we still have it if the activity needs to be killed while paused.

        //savedInstanceState.putInt("IntTest", 0);

        //savedInstanceState.putString("StrTest", "savedInstanceState test");

        savedInstanceState.putDouble("TransHeight", TransHeight);
        savedInstanceState.putDouble("TransWeight", TransWeight);
        savedInstanceState.putDouble("TransActivity", TransActivity);
        savedInstanceState.putDouble("TransKneeLen", TransKneeLen);
        savedInstanceState.putDouble("TransAge", TransAge);
        savedInstanceState.putDouble("TransGender", TransGender);
        savedInstanceState.putDouble("TransMode", TransMode);

        super.onSaveInstanceState(savedInstanceState);

    }*/

    /*@Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //String saveWord = tvShow.getText().toString();
        //outState.putString("SAVE",saveWord);
        outState.putDouble("TransHeight", TransHeight);
        outState.putDouble("TransWeight", TransWeight);
        outState.putDouble("TransActivity", TransActivity);
        outState.putDouble("TransKneeLen", TransKneeLen);
        outState.putDouble("TransAge", TransAge);
        outState.putDouble("TransGender", TransGender);
        outState.putDouble("TransMode", TransMode);
    }*/
    public void c()
    {
        double tmp=0;
        //alarm.setText("11111");
        if(!kneeLen.getText().toString().isEmpty()&&!age.getText().toString().isEmpty())
        {

            if (shipItem.getGender() == 1) tmp = 85.1 + (1.73 * Double.parseDouble(kneeLen.getText().toString())) - (0.11 * Integer.parseInt(age.getText().toString()));
            else tmp = 91.45 + (1.53 * Double.parseDouble(kneeLen.getText().toString())) - (0.16 * Integer.parseInt(age.getText().toString()));
            tmp=(Math.round(tmp * 10.0) / 10.0);
            height.setText(""+tmp);
        }
        else
        {
            height.setText("");
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if( getIntent().getBooleanExtra("Exit me", false)){
            finish();
        }
        if(a==3)overridePendingTransition(R.anim.atob, R.anim.btoa);
        else if(a==2) overridePendingTransition(R.anim.btoc, R.anim.ctob);
    }


    /*android:configChanges="orientation|screenSize"*/
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

    public void gender(View view) {
        //System.exit(0);
        shipItem.ChangeGender();
        if(shipItem.getGender()==1) btn1.setText("男性");
        else btn1.setText("女性");
        alarm.setText("");
        if(shipItem.getIn()==1) c();
    }

    public void setg(int g) {
        shipItem.setG(g);
        if(shipItem.getGender()==1) btn1.setText("男性");
        else btn1.setText("女性");
        if(shipItem.getIn()==1) c();
    }

    public void reset(View view) {
        // reset all data
        TransHeight=0;
        TransWeight=0;
        TransKneeLen=0;
        TransAge=0;
        TransActivity=0;
        shipItem.Reset();
        height.getText().clear();
        weight.getText().clear();
        activity.getText().clear();
        kneeLen.getText().clear();
        age.getText().clear();

        kneeLen.setFocusableInTouchMode(false);
        age.setFocusableInTouchMode(false);
        height.setFocusableInTouchMode(true);

        alarm.setText("");

        btn1.setText("男性");
        btn2.setText("自行輸入");
        height.setHint("0");
    }

    /*public void compute() {

        alarm.setText("");

        if ((weight.length() != 0 && activity.length() != 0) && (height.length() != 0 || (kneeLen.length() != 0 && age.length() != 0)))
        {
            double w = Double.parseDouble(weight.getText().toString());
            int a = Integer.parseInt(activity.getText().toString());
            double h = 0;

            if (shipItem.getIn() == 0) h = Integer.parseInt(height.getText().toString());
            else
            {
                if (shipItem.getGender() == 1) h = 85.1 + (1.73 * Double.parseDouble(kneeLen.getText().toString())) - (0.11 * Integer.parseInt(age.getText().toString()));
                else h = 91.45 + (1.53 * Double.parseDouble(kneeLen.getText().toString())) - (0.16 * Integer.parseInt(age.getText().toString()));
                h = Math.round(h*10.0)/10.0;
                height.setHint("" + h);
            }

            if (h > 0 && w > 0 && a >= 1 && a <= 3)
            {
                shipItem.Compute(h, w, a);
                if (shipItem.getGender() == 1) d1 = 1;
                else d1 = 2;
                d2 = h;
                d3 = w;
                d4 = a;

                if (!kneeLen.getText().toString().isEmpty()) d5 = Double.parseDouble(kneeLen.getText().toString());
                else d5 = 0;

                if (!age.getText().toString().isEmpty()) d6 = Integer.parseInt(age.getText().toString());
                else d6 = 0;
            }
            else alarm.setText("請完整輸入!!!!!!!");
        }
        else alarm.setText("請完整輸入!!!!!!!");
    }*/

    public void input(View view) {
        shipItem.ChangeInput();
        if (shipItem.getIn() == 0) {
            btn2.setText("自行輸入");
            kneeLen.setFocusableInTouchMode(false);
            age.setFocusableInTouchMode(false);
            height.setFocusableInTouchMode(true);
            kneeLen.getText().clear();
            age.getText().clear();
            height.setHint("0");
        }
        else {
            btn2.setText("估算身高");
            kneeLen.setFocusableInTouchMode(true);
            age.setFocusableInTouchMode(true);
            height.setFocusableInTouchMode(false);
            height.getText().clear();
        }
    }

    public void iii(int i) {
        shipItem.setI(i);
        if (shipItem.getIn() == 0) {
            btn2.setText("自行輸入");
            kneeLen.setFocusableInTouchMode(false);
            age.setFocusableInTouchMode(false);
            height.setFocusableInTouchMode(true);
            kneeLen.getText().clear();
            age.getText().clear();
            height.setHint("0");
        }
        else {
            btn2.setText("估算身高");
            kneeLen.setFocusableInTouchMode(true);
            age.setFocusableInTouchMode(true);
            height.setFocusableInTouchMode(false);
            //height.getText().clear();
        }
    }
}
