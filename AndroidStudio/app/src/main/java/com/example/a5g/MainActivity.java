package com.example.a5g;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import android.util.Log;
import java.io.IOException;
//import org.w3c.dom.Document;
//import org.w3c.dom.Document;
//import org.w3c.dom.Element;


public class MainActivity extends AppCompatActivity {

    private TextView a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        isNetworkConnect(this);
        //doin();
        //description_webscrape dw = new

       // doin();
        setText();

    }



    public void doin()
    {
        /*a.setText("Success");
        try {
            //從一個URL載入一個Document物件。
            Document doc = Jsoup.connect("https://home.meishichina.com/show-top-type-recipe.html").get();
            //選擇“美食天下”所在節點
            Elements elements = doc.select("div.top-bar");
            //列印 <a>標籤裡面的title
            a.setText(""+elements.select("a").attr("title"));
            Log.i("mytag",elements.select("a").attr("title"));
        }catch(Exception e) {
            a.setText("Wrong");
            Log.i("mytag", e.toString());
        }*/
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    a.setText("Success");
                    //從一個URL載入一個Document物件。
                    Document doc = Jsoup.connect("https://home.meishichina.com/show-top-type-recipe.html").get();
                    //選擇“美食天下”所在節點
                    Elements elements = doc.select("div.top-bar");
                    //列印 <a>標籤裡面的title
                    a.setText(""+elements.select("a").attr("title"));
                    Log.i("mytag",elements.select("a").attr("title"));
                    a.setText("Pineapple");
                }catch(Exception e) {
                    a.setText("Wrong");
                    Log.i("mytag", e.toString());
                }
            }
        });
        thread.start();
    }

    private void setText(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    a.setText("Success");
                    //從一個URL載入一個Document物件。
                    Document doc = Jsoup.connect("https://home.meishichina.com/show-top-type-recipe.html").get();
                    //選擇“美食天下”所在節點
                    Elements elements = doc.select("div.top-bar");
                    //列印 <a>標籤裡面的title
                    a.setText(""+elements.select("a").attr("title"));
                    Log.i("mytag",elements.select("a").attr("title"));
                    a.setText("Pineapple");
                }catch(Exception e) {
                    a.setText("Wrong");
                    Log.i("mytag", e.toString());
                }
            }
        });

    }




    public boolean isNetworkConnect(Activity activity) {
        Context context = activity.getApplicationContext();
        ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if(cm==null)
        {
            a.setText("Not link");
            return false;
        }
        else
        {
            a.setText("Link success");
            return true;
        }

    }
}