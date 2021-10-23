package com.cornez.tapbuttoncounter;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

    //MODEL
    private Counter count;
    private Counter count2;
    private Counter count3;

    //VIEW
    private TextView display;
    private TextView display2;
    private TextView display3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        count = new Counter();
        count2 = new Counter();
        count3 = new Counter();

        count.setCount(28);
        count2.setCount(50);
        count3.setCount(0);

        display = (TextView) findViewById(R.id.textView2);
        display2 = (TextView) findViewById(R.id.textView4);
        display3 = (TextView) findViewById(R.id.textView6);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu;
        // this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void addTap1(View view){
        count.addCount();
        if(count.getCount()>50.0) count.setCount(15);
        //String i = Integer.toString(count.dtoi());
        display.setText(count.getICount().toString());

    }

    public void subTap1(View view){
        count.subCount();
        if(count.getCount()<15.0) count.setCount(50);
        //String i = Integer.toString(count.dtoi());
        display.setText(count.getICount().toString());
    }

    public void addTap2(View view){
        count2.addCount();
        if(count2.getCount()>90.0) count2.setCount(40);
        //String i = Integer.toString(count2.dtoi());
        display2.setText(count2.getICount().toString());
    }

    public void subTap2(View view){
        count2.subCount();
        if(count2.getCount()<40.0) count2.setCount(90);
        //String i = Integer.toString(count2.dtoi());
        display2.setText(count2.getICount().toString());
    }

    public void CompTap(View view){
        count3.Comp(count.getCount(),count2.getCount());
        display3.setText(count3.getCount().toString());
    }

    public void ResetTap(View view){
        count3.setCount(0);
        display3.setText(count3.getCount().toString());
    }
}
