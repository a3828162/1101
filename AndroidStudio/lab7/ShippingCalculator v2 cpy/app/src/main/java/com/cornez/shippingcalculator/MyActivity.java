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
import android.widget.ListView;
import android.widget.TextView;
import android.content.Intent;
import java.util.ArrayList;
import java.util.List;
import android.widget.ArrayAdapter;
import android.widget.*;
import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.*;
import com.facebook.stetho.Stetho;

public class MyActivity extends Activity
{
    // EditText
    private EditText height;
    private EditText weight;
    private EditText activity;
    private EditText kneeLen;
    private EditText age;
    private EditText name;

    // Button
    private Button gender;
    private Button input;
    private Button gotoIntro;
    private Button gotoOutput;
    private Button addButton;

    private int isMale = 1;
    private int isInputHeight = 1;


    private ToDo_Item llist;

    private List<String> list;
    private ListView listview;
    private List<Boolean> listShow;

    String TAG = MyActivity.class.getSimpleName() + "My";

    private final String DB_NAME = "MyList.db";
    private String TABLE_NAME = "MyTable";
    private final int DB_VERSION = 1;
    DBHelper mDBHelper;

    ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();//取得所有資料
    ArrayList<HashMap<String, String>> getNowArray = new ArrayList<>();//取得被選中的項目資料

    long nowId;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_2);

        Stetho.initializeWithDefaults(this);

        init();
        setClick();
        loadAll();
    }


    public void setList()
    {
        ListAdapter adapterItem = new ListAdapter(this, list);
        listview.setAdapter(adapterItem);
    }

    public void addData(View view)
    {


        if(name.getText().toString().isEmpty())
        {
            Toast.makeText(this, "Name不能空白", Toast.LENGTH_LONG).show();
            return;
        }

        for(int i=0;i<list.size();++i)
        {
            if(list.get(0).toString().equals(name.getText().toString()))
            {
                Toast.makeText(this, "已經存在", Toast.LENGTH_LONG).show();
                return;
            }
        }

        list.add(""+name.getText().toString());
        listShow.add(false);

        mDBHelper.addData(name.getText().toString(),height.getText().toString(),weight.getText().toString(),activity.getText().toString(),kneeLen.getText().toString(),age.getText().toString(),String.valueOf(isMale),String.valueOf(isInputHeight));

        loadAll();
        //setList();

        reset2();
    }

    public void loadAll()
    {
        arrayList = mDBHelper.showAll();

        list.clear();
        listShow.clear();
        for(int i=0;i<arrayList.size();++i)
        {
            list.add(arrayList.get(i).get("Name"));
            listShow.add(false);
        }

        setList();
    }

    public void load(View view)
    {

        int haveClick = 0;

        for(int i=0;i<listShow.size();++i)
        {
            if(listShow.get(i))
            {
                haveClick = 1;
                break;
            }
        }
        if(haveClick==0)
        {
            Toast.makeText(this, "沒有選擇資料", Toast.LENGTH_LONG).show();
            return;
        }

        int isMulti = 0;

        for(int i=0;i<listShow.size();++i)
        {
            if(listShow.get(i)) isMulti++;
        }

        if(isMulti>1)
        {
            Toast.makeText(this, "只能load一筆資料", Toast.LENGTH_LONG).show();
            return;
        }

        ListAdapter adapterItem = new ListAdapter(this, list);

        for(int i=0;i<listShow.size();++i)
        {
            if(listShow.get(i)) nowId = i;
        }

        getNowArray = mDBHelper.searchByName(list.get((int)nowId).toString());


        //addButton.setText(""+getNowArray.get(0).get("Activity"));
        height.setText(getNowArray.get(0).get("Height"));
        weight.setText(getNowArray.get(0).get("Weight"));
        activity.setText(getNowArray.get(0).get("Activity"));
        age.setText(getNowArray.get(0).get("Age"));
        kneeLen.setText(getNowArray.get(0).get("Knee"));
        isMale = Integer.valueOf(getNowArray.get(0).get("Gender"));
        isInputHeight = Integer.valueOf(getNowArray.get(0).get("Mode"));
        setgender();
        setinput();
    }


    public void delete(View view)
    {
        int hasDelete = 0;

        for(int i=0;i<listShow.size();++i)
        {
            if(listShow.get(i))
            {
                hasDelete++;
                break;
            }
        }

        if(hasDelete==0)
        {
            Toast.makeText(this, "沒有選擇資料", Toast.LENGTH_LONG).show();
            return;
        }

        int alldelete = 0;
        long tmp = nowId;

        //while(true) {
          //  if(alldelete==1) break;
            for(int i=listShow.size()-1;i>=0;)
            {

                if(listShow.get(i))
                {
                    tmp = i;
                    //mDBHelper.deleteByNameEZ(list.get((int) tmp).toString());
                    mDBHelper.deleteByNameEZ(arrayList.get((int)tmp).get("id"));
                    arrayList = mDBHelper.showAll();
                    list.remove(list.remove((int) tmp));
                    List<Boolean> tShow = new ArrayList<Boolean>();
                    for(int k=0;k<listShow.size();++k)
                    {
                        if((long) k!=tmp)
                        {
                            boolean aa = listShow.get(k);
                            tShow.add(aa);
                        }
                    }
                    //listShow.remove(listShow.remove((int) tmp));
                    listShow.clear();

                    for(int k=0;k<tShow.size();++k)
                    {
                        listShow.add(tShow.get(k));
                    }

                    //loadAll();
                    i = listShow.size()-1;

                }
                else --i;
            }
      //  }

        //setList();
        loadAll();
    }

    public void init()
    {
        addButton = (Button)findViewById(R.id.button7);

        mDBHelper = new DBHelper(this, DB_NAME
                , null, DB_VERSION, TABLE_NAME);//初始化資料庫
        mDBHelper.chickTable();//確認是否存在資料表，沒有則新增

        listview = (ListView) findViewById(R.id.lv);
        listview.setOnItemClickListener(new OnItemClickListener()
                                        {
                                            public void onItemClick(AdapterView<?> parent, View v, int position, long id)
                                            {
                                                CheckedTextView chkItem = (CheckedTextView) v.findViewById(R.id.check1);
                                                chkItem.setChecked(!chkItem.isChecked());
                                                nowId=id;

                                                //addButton.setText(""+nowId);
                                                //Toast.makeText(MyActivity.this, "您點選了第 "+(position+1)+" 項", Toast.LENGTH_SHORT).show();
                                                //addButton.setText(""+id);
                                                listShow.set(position, chkItem.isChecked());
                                            }
                                        }
        );

        listShow = new ArrayList<Boolean>();
        list = new ArrayList<String>();

        setList();

        // EditText
        height = (EditText) findViewById(R.id.editTextTextPersonName);
        weight = (EditText) findViewById(R.id.editTextTextPersonName2);
        activity = (EditText) findViewById(R.id.editTextTextPersonName3);
        kneeLen = (EditText) findViewById(R.id.editTextTextPersonName5);
        age = (EditText) findViewById(R.id.editTextTextPersonName4);

        name = (EditText) findViewById(R.id.editTextTextPersonName6);

        kneeLen.setFocusableInTouchMode(false);
        age.setFocusableInTouchMode(false);

        // Button
        gender = (Button) findViewById((R.id.button));
        input = (Button) findViewById((R.id.button4));
        gotoOutput = (Button) findViewById(R.id.button6);
        gotoIntro = (Button) findViewById(R.id.button5);
    }

    public void setClick()
    {
        kneeLen.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void afterTextChanged(Editable s) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                if (!kneeLen.getText().toString().isEmpty() && !age.getText().toString().isEmpty()) {
                    double h;
                    double NL = Double.parseDouble(kneeLen.getText().toString());
                    double A = Double.parseDouble(age.getText().toString());
                    if (isMale == 1) h = 85.1 + (1.73 * NL) - (0.11 * A);
                    else h = 91.45 + (1.53 * NL) - (0.16 * A);
                    h = Math.round(h*10.0)/10.0;
                    height.setFocusableInTouchMode(true);
                    height.setText("" + h);
                    height.setFocusableInTouchMode(false);
                }
            }
        });

        age.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void afterTextChanged(Editable s) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                if (!kneeLen.getText().toString().isEmpty() && !age.getText().toString().isEmpty()) {
                    double h;
                    double NL = Double.parseDouble(kneeLen.getText().toString());
                    double A = Double.parseDouble(age.getText().toString());
                    if (isMale == 1) h = 85.1 + (1.73 * NL) - (0.11 * A);
                    else h = 91.45 + (1.53 * NL) - (0.16 * A);
                    h = Math.round(h*10.0)/10.0;
                    height.setFocusableInTouchMode(true);
                    height.setText("" + h);
                    height.setFocusableInTouchMode(false);
                }
            }
        });

        gotoOutput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent output1 = new Intent(MyActivity.this, output.class);
                Bundle side = new Bundle();

                if (height.getText().toString().isEmpty()) side.putDouble("height", 0);
                else side.putDouble("height", Double.parseDouble(height.getText().toString()));

                if (weight.getText().toString().isEmpty()) side.putDouble("weight", 0);
                else side.putDouble("weight", Double.parseDouble(weight.getText().toString()));

                if (activity.getText().toString().isEmpty()) side.putInt("activity", 0);
                else side.putInt("activity", Integer.parseInt(activity.getText().toString()));

                if (kneeLen.getText().toString().isEmpty()) side.putDouble("kneeLen", 0);
                else side.putDouble("kneeLen", Double.parseDouble(kneeLen.getText().toString()));

                if (age.getText().toString().isEmpty()) side.putInt("age", 0);
                else side.putInt("age", Integer.parseInt(age.getText().toString()));

                side.putInt("app", 1);
                side.putInt("isMale", isMale);
                side.putInt("isInputHeight", isInputHeight);

                output1.putExtras(side);
                startActivity(output1);
            }
        });

        gotoIntro.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view)
            {
                Intent intro1 = new Intent(MyActivity.this, intro.class);
                Bundle side = new Bundle();

                if (height.getText().toString().isEmpty()) side.putDouble("height", 0);
                else side.putDouble("height", Double.parseDouble(height.getText().toString()));

                if (weight.getText().toString().isEmpty()) side.putDouble("weight", 0);
                else side.putDouble("weight", Double.parseDouble(weight.getText().toString()));

                if (activity.getText().toString().isEmpty()) side.putInt("activity", 0);
                else side.putInt("activity", Integer.parseInt(activity.getText().toString()));

                if (kneeLen.getText().toString().isEmpty()) side.putDouble("kneeLen", 0);
                else side.putDouble("kneeLen", Double.parseDouble(kneeLen.getText().toString()));

                if (age.getText().toString().isEmpty()) side.putInt("age", 0);
                else side.putInt("age", Integer.parseInt(age.getText().toString()));

                side.putInt("app", 1);
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

        if (getIntent().getBooleanExtra("EXIT", false)) finishAffinity();

        Bundle bd = getIntent().getExtras();
        if (bd != null)
        {
            if (bd.getInt("app") == 3) overridePendingTransition(R.anim.atob, R.anim.btoa);
            else overridePendingTransition(R.anim.btoc, R.anim.ctob);

            isInputHeight = bd.getInt("isInputHeight");
            isMale = bd.getInt("isMale");

            if (isMale == 0) gender.setText("女性");
            else gender.setText("男性");
            if (isInputHeight == 1)
            {
                input.setText("自行輸入");
                double tmp_d = bd.getDouble("height");
                if (tmp_d == 0) height.setText("");
                else height.setText("" + (int)tmp_d);
                kneeLen.setFocusableInTouchMode(false);
                age.setFocusableInTouchMode(false);
                height.setFocusableInTouchMode(true);
            }
            else
            {
                input.setText("估算身高");
                double tmp_k = bd.getDouble("kneeLen");
                if (tmp_k == 0) kneeLen.setText("");
                else kneeLen.setText("" + tmp_k);
                double tmp_ag = bd.getInt("age");
                if (tmp_ag == 0) age.setText("");
                else age.setText("" + (int)tmp_ag);
                kneeLen.setFocusableInTouchMode(true);
                age.setFocusableInTouchMode(true);
                height.setFocusableInTouchMode(false);
            }

            double tmp_w = bd.getDouble("weight");
            if (tmp_w == 0) weight.setText("");
            else weight.setText("" + tmp_w);

            int tmp_a = bd.getInt("activity");
            if (tmp_a == 0) activity.setText("");
            else activity.setText("" + tmp_a);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) return true;
        return super.onOptionsItemSelected(item);
    }

    public void gender(View view)
    {
        isMale ^= 1;
        if (isMale == 0) gender.setText("女性");
        else gender.setText("男性");
    }

    public void setgender()
    {
        if (isMale == 0) gender.setText("女性");
        else gender.setText("男性");
    }

    public void reset2()
    {
        gender.setText("男性");
        input.setText("自行輸入");

        height.getText().clear();
        weight.getText().clear();
        activity.getText().clear();
        kneeLen.getText().clear();
        age.getText().clear();
        name.getText().clear();

        isMale = 1;
        isInputHeight = 1;

        kneeLen.setFocusableInTouchMode(false);
        age.setFocusableInTouchMode(false);
        height.setFocusableInTouchMode(true);
    }

    public void reset(View view)
    {
        gender.setText("男性");
        input.setText("自行輸入");

        height.getText().clear();
        weight.getText().clear();
        activity.getText().clear();
        kneeLen.getText().clear();
        age.getText().clear();
        name.getText().clear();

        isMale = 1;
        isInputHeight = 1;

        kneeLen.setFocusableInTouchMode(false);
        age.setFocusableInTouchMode(false);
        height.setFocusableInTouchMode(true);
    }

    public void setinput()
    {

        if (isInputHeight == 1)
        {
            input.setText("自行輸入");

            kneeLen.setFocusableInTouchMode(false);
            age.setFocusableInTouchMode(false);
            height.setFocusableInTouchMode(true);

            kneeLen.getText().clear();
            age.getText().clear();
        }
        else
        {
            input.setText("估算身高");

            kneeLen.setFocusableInTouchMode(true);
            age.setFocusableInTouchMode(true);
            height.setFocusableInTouchMode(false);

            //height.getText().clear();
        }
    }

    public void input(View view)
    {
        isInputHeight ^= 1;

        if (isInputHeight == 1)
        {
            input.setText("自行輸入");

            kneeLen.setFocusableInTouchMode(false);
            age.setFocusableInTouchMode(false);
            height.setFocusableInTouchMode(true);

            kneeLen.getText().clear();
            age.getText().clear();
        }
        else
        {
            input.setText("估算身高");

            kneeLen.setFocusableInTouchMode(true);
            age.setFocusableInTouchMode(true);
            height.setFocusableInTouchMode(false);

            height.getText().clear();
        }
    }
}
