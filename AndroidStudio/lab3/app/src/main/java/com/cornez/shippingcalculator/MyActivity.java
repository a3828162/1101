package com.cornez.shippingcalculator;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
    private TextView stdweight;
    private TextView range;
    private TextView K;
    private Button btn1;
    private Button btn2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_2);

        //CREATE THE DATA MODEL FOR STORING THE ITEM TO BE SHIPPED
        shipItem = new ShipItem();
//alarm.setBackgroundResource(R.drawable.sc);
        //TASK 3: ESTABLISH THE REFERENCES TO INPUT WEIGHT ELEMENT
        height = (EditText) findViewById(R.id.editTextTextPersonName);
        weight = (EditText) findViewById(R.id.editTextTextPersonName2);
        activity = (EditText) findViewById(R.id.editTextTextPersonName3);
        kneeLen = (EditText) findViewById(R.id.editTextTextPersonName5);
        age = (EditText) findViewById(R.id.editTextTextPersonName4);

        height.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void afterTextChanged(Editable s) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { if(shipItem.getIn()!=1) compute(); }
        });

        weight.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void afterTextChanged(Editable s) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { compute(); }
        });

        activity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void afterTextChanged(Editable s) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { compute(); }
        });

        kneeLen.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void afterTextChanged(Editable s) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { compute(); }
        });

        age.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void afterTextChanged(Editable s) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { compute(); }
        });


        kneeLen.setFocusableInTouchMode(false);
        age.setFocusableInTouchMode(false);

        //TASK 3: ESTABLISH THE REFERENCES TO OUTPUT ELEMENTS
        alarm = (TextView) findViewById(R.id.textView11);
        stdweight = (TextView) findViewById(R.id.textView14);
        range = (TextView) findViewById(R.id.textView13);
        K = (TextView) findViewById(R.id.textView12);

        // add button
        btn1 = (Button) findViewById((R.id.button));
        btn2 = (Button) findViewById((R.id.button4));
        btn1.setText("男性");
        btn2.setText("自行輸入");
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

    public void gender(View view) {
        shipItem.ChangeGender();
        if(shipItem.getGender()) btn1.setText("男性");
        else btn1.setText("女性");
        alarm.setText("");
        compute();
    }

    public void reset(View view) {
        // reset all data

        height.getText().clear();
        weight.getText().clear();
        activity.getText().clear();
        kneeLen.getText().clear();
        age.getText().clear();
        shipItem.Reset();
        kneeLen.setFocusableInTouchMode(false);
        age.setFocusableInTouchMode(false);
        height.setFocusableInTouchMode(true);

        stdweight.setText("");
        range.setText("");
        K.setText("");
        alarm.setText("");

        btn1.setText("男性");
        btn2.setText("自行輸入");
        height.setHint("0");
    }

    public void compute() {

        stdweight.setText("");
        range.setText("");
        K.setText("");
        alarm.setText("");
        double h = 0;

        if(shipItem.getIn()==1&& kneeLen.length()!=0&&age.length()!=0)
        {
            if (shipItem.getGender() == true) h = 85.1 + (1.73 * Double.parseDouble(kneeLen.getText().toString())) - (0.11 * Integer.parseInt(age.getText().toString()));
            else h = 91.45 + (1.53 * Double.parseDouble(kneeLen.getText().toString())) - (0.16 * Integer.parseInt(age.getText().toString()));
            h = Math.round(h*10.0)/10.0;
            //height.setHint("" + h);
            //if(height.getText().toString()!="") height.getText().clear();
            height.setText("" + h);
        }
        else if(shipItem.getIn()==1) height.getText().clear();
        if( shipItem.getIn()== 0 && (kneeLen.length()!=0||age.length()!=0))return;
        if ((weight.length() != 0 && activity.length() != 0) && (height.length() != 0 || (kneeLen.length() != 0 && age.length() != 0)))
        {
            double w = Double.parseDouble(weight.getText().toString());
            int a = Integer.parseInt(activity.getText().toString());
            //double h = 0;

            if (shipItem.getIn() == 0) h = Integer.parseInt(height.getText().toString());
            /*else if(kneeLen.length()!=0&&age.length()!=0)
            {
                if (shipItem.getGender() == true) h = 85.1 + (1.73 * Double.parseDouble(kneeLen.getText().toString())) - (0.11 * Integer.parseInt(age.getText().toString()));
                else h = 91.45 + (1.53 * Double.parseDouble(kneeLen.getText().toString())) - (0.16 * Integer.parseInt(age.getText().toString()));
                h = Math.round(h*10.0)/10.0;
                //height.setHint("" + h);
                //if(height.getText().toString()!="") height.getText().clear();
                height.setText("" + h);
            }
            else height.getText().clear();*/

            if (h > 0 && w > 0 && a >= 1 && a <= 3)
            {
                shipItem.Compute(h, w, a);
                stdweight.setText("標準體重: " + Math.round(shipItem.getStdweight()*10.0)/10.0 + "公斤");
                range.setText("體重合理範圍: " + Math.round(shipItem.getRange1()*10.0)/10.0 + " ~ " + Math.round(shipItem.getRange2()*10.0)/10.0 + "公斤");
                K.setText("每日所需熱量: " + Math.round(shipItem.getK()*10.0)/10.0 + "大卡");
            }
            else alarm.setText("請完整輸入!!!!!!!");
        }
        else alarm.setText("請完整輸入!!!!!!!");
    }

    public void input(View view) {
        shipItem.ChangeInput();

        if (shipItem.getIn() == 0) {
            btn2.setText("自行輸入");
            height.getText().clear();
            kneeLen.getText().clear();
            age.getText().clear();
            kneeLen.setFocusableInTouchMode(false);
            age.setFocusableInTouchMode(false);
            height.setFocusableInTouchMode(true);

            //height.setHint("0");
        }
        else {
            btn2.setText("估算身高");
            kneeLen.setFocusableInTouchMode(true);
            age.setFocusableInTouchMode(true);
            height.setFocusableInTouchMode(false);
            height.getText().clear();
        }
    }
}
