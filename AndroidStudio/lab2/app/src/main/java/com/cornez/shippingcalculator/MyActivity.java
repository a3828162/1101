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
    private EditText kneelong;
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

        //TASK 3: ESTABLISH THE REFERENCES TO INPUT WEIGHT ELEMENT
        height = (EditText) findViewById(R.id.editTextTextPersonName);
        weight = (EditText) findViewById(R.id.editTextTextPersonName2);
        activity = (EditText) findViewById(R.id.editTextTextPersonName3);
        kneelong = (EditText) findViewById(R.id.editTextTextPersonName4);
        age = (EditText) findViewById(R.id.editTextTextPersonName5);

        //TASK 3: ESTABLISH THE REFERENCES TO OUTPUT ELEMENTS
        alarm = (TextView) findViewById(R.id.textView11);
        stdweight = (TextView) findViewById(R.id.textView14);
        range = (TextView) findViewById(R.id.textView13);
        K = (TextView) findViewById(R.id.textView12);

        // add button
        btn1 = (Button) findViewById((R.id.button));
        btn2 = (Button) findViewById((R.id.button4));

        height.addTextChangedListener(new TextWatcher() {
            //THE INPUT ELEMENT IS ATTACHED TO AN EDITABLE,
            //THEREFORE THESE METHODS ARE CALLED WHEN THE TEXT IS CHANGED

            public void onTextChanged(CharSequence s,
                                      int start, int before, int count){
                //CATCH AN EXCEPTION WHEN THE INPUT IS NOT A NUMBER
                compute();
            }
            public void afterTextChanged(Editable s) {}
            public void beforeTextChanged(CharSequence s,
                                          int start, int count, int after){}
        });

        weight.addTextChangedListener(new TextWatcher() {
            //THE INPUT ELEMENT IS ATTACHED TO AN EDITABLE,
            //THEREFORE THESE METHODS ARE CALLED WHEN THE TEXT IS CHANGED

            public void onTextChanged(CharSequence s,
                                      int start, int before, int count){
                //CATCH AN EXCEPTION WHEN THE INPUT IS NOT A NUMBER
                compute();

            }
            public void afterTextChanged(Editable s) {}
            public void beforeTextChanged(CharSequence s,
                                          int start, int count, int after){}
        });


        activity.addTextChangedListener(new TextWatcher() {
            //THE INPUT ELEMENT IS ATTACHED TO AN EDITABLE,
            //THEREFORE THESE METHODS ARE CALLED WHEN THE TEXT IS CHANGED

            public void onTextChanged(CharSequence s,
                                      int start, int before, int count){
                //CATCH AN EXCEPTION WHEN THE INPUT IS NOT A NUMBER
                compute();
            }
            public void afterTextChanged(Editable s) {}
            public void beforeTextChanged(CharSequence s,
                                          int start, int count, int after){}
        });

        kneelong.addTextChangedListener(new TextWatcher() {
            //THE INPUT ELEMENT IS ATTACHED TO AN EDITABLE,
            //THEREFORE THESE METHODS ARE CALLED WHEN THE TEXT IS CHANGED

            public void onTextChanged(CharSequence s,
                                      int start, int before, int count){
                //CATCH AN EXCEPTION WHEN THE INPUT IS NOT A NUMBER
                compute();
            }
            public void afterTextChanged(Editable s) {}
            public void beforeTextChanged(CharSequence s,
                                          int start, int count, int after){}
        });

        age.addTextChangedListener(new TextWatcher() {
            //THE INPUT ELEMENT IS ATTACHED TO AN EDITABLE,
            //THEREFORE THESE METHODS ARE CALLED WHEN THE TEXT IS CHANGED

            public void onTextChanged(CharSequence s,
                                      int start, int before, int count){
                //CATCH AN EXCEPTION WHEN THE INPUT IS NOT A NUMBER
                compute();
            }
            public void afterTextChanged(Editable s) {}
            public void beforeTextChanged(CharSequence s,
                                          int start, int count, int after){}
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

    public void gender(View view) {
        // change gender
        shipItem.ChangeGender();
        // print new gender
        if(shipItem.getGender()) btn1.setText("男性");
        else btn1.setText("女性");
        alarm.setText("");
        compute();
    }

    public void chintype(View view) {
        // change gender
        shipItem.Changeintype();
        // print new gender
        if(shipItem.getintype()) btn2.setText("估算身高");
        else btn1.setText("自行輸入");
        alarm.setText("");
    }

    public void reset(View view) {
        // reset all data
        shipItem.Reset();
        btn1.setText("男性");
        btn2.setText("估算身高");
        height.getText().clear();
        weight.getText().clear();
        kneelong.getText().clear();
        age.getText().clear();
        activity.getText().clear();
        stdweight.setText("");
        range.setText("");
        K.setText("");
        alarm.setText("");

    }

    public void compute() {
        stdweight.setText("");
        range.setText("");
        K.setText("");
        alarm.setText("");
        if (height.length() == 0 || weight.length() == 0 || activity.length() == 0) alarm.setText("請完整輸入!!!!!!!");
        else
        {
            double h = Integer.parseInt(height.getText().toString());
            double w = Double.parseDouble(weight.getText().toString());
            int a = Integer.parseInt(activity.getText().toString());

            if (h > 0 && w > 0 && a >= 1 && a <= 3)
            {
                shipItem.Compute(h, w, a);
                stdweight.setText("標準體重: " + Math.round(shipItem.getStdweight()*10.0)/10.0 + "公斤");
                range.setText("體重合理範圍: " + Math.round(shipItem.getRange1()*10.0)/10.0 + " ~ " + Math.round(shipItem.getRange2()*10.0)/10.0 + "公斤");
                K.setText("每日所需熱量: " + Math.round(shipItem.getK()*10.0)/10.0 + "大卡");
            }
            else alarm.setText("請完整輸入!!!!!!!");
        }



    }
}
