package com.example.py.wirelesslocator;


import android.Manifest;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Collections;
import java.util.List;

public class SecondActivity extends AppCompatActivity {

    private Handler handler = new Handler();
    WifiManager wifiManager;
    String x,y,c;
    TextView textView1, textView2, textView3, fill;
    WifiInfo connection;
    Button button1, button2, send;
    String server_url = "http://wirelesslocator.herokuapp.com/wifidata";
    String name, value;
    JsonObjectRequest jsonObjectRequest;
    RequestQueue requestQueue;
    JSONObject obj = new JSONObject();
    JSONArray array = new JSONArray();

    AlertDialog.Builder builder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);
        requestQueue = Volley.newRequestQueue(this);
        wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        connection = wifiManager.getConnectionInfo();
        builder = new AlertDialog.Builder(SecondActivity.this);

        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        send = findViewById(R.id.send);
        fill = findViewById(R.id.fill);

        x = getIntent().getExtras().getString("x");
        y = getIntent().getExtras().getString("y");
        c = getIntent().getExtras().getString("c");
        //server_url = getIntent().getExtras().getString("url").trim();

        textView1.setText(textView1.getText().toString()+x);
        textView2.setText(textView2.getText().toString()+y);
        textView3.setText(textView3.getText().toString()+c);
        //url.setText(url.getText().toString() + server_url);

    }
    @TargetApi(Build.VERSION_CODES.M)
    public void StartScan(View view)
    {
        Log.e("object", obj.toString());
        runi();
    }
    public void StopScan(View view){
        //handler.removeCallbacks(repeat);
    }

    // private Runnable repeat = new Runnable() {
    @RequiresApi(api = Build.VERSION_CODES.M)
    //@Override
    public void runi() {

        List<ScanResult> list = Collections.EMPTY_LIST;

        if (!wifiManager.isWifiEnabled())
            wifiManager.setWifiEnabled(true);

        else if (checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED)
            requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.ACCESS_FINE_LOCATION},
                    0x12345);


        else {

            StringBuilder stringBuilder = new StringBuilder();
            if (wifiManager.isWifiEnabled()) {
                if (wifiManager.startScan()) {
                    list = wifiManager.getScanResults();

                }



                if (list != null) {
                    //count++;
                    for (ScanResult scanResult : list) {

                        Log.e("x",x);
                        Log.e("y", y);
                        Log.e("c",c);

                        try {
                            obj.put("cellID",c);
                            obj.put("x",x);
                            obj.put("y",y);

                                    /*obj.put("x", array.put(x));
                                    obj.put("y", array1.put(y));
                                    obj.put("cell", array4.put(c));*/
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        name = scanResult.SSID;
                        value = String.valueOf(scanResult.level);

                        try {

                            obj.put(name.toString(),value);


                                    /*obj.put("SSID", array2.put(name));
                                    obj.put("value", array3.put(value));*/
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        //list2.put(name, value);
                        stringBuilder.append(name +
                                "\t\t\t" + value + "\n");
                        //jsonObject = new JSONObject(list2);
                    }
                    Log.e("paparoni", obj.toString());
                    fill.setText(stringBuilder.toString());
                }
            }
        }
        //Log.e("count is", String.valueOf(count));
        //handler.postDelayed(this, 5000);
        //Toast.makeText(SecondActivity.this, String.valueOf(count),Toast.LENGTH_LONG).show();
        //if (count==5)
        //{
        //handler.removeCallbacks(repeat);
                   /* builder.setTitle("20 completed");
                    builder.setMessage("values logged");
                    builder.setCancelable(false);
                    builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            *//*textView.setText("");
                            editText1.setText("");
                            editText2.setText("");*//*

                        }
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();*/
        Toast.makeText(SecondActivity.this, "Scan Completed, Press 'Send Data'", Toast.LENGTH_LONG).show();
        //}
    }
    //};

    public void sendData(View view) {

            Log.e("url", server_url);

        jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, server_url, obj, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(SecondActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
                obj=null;

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(SecondActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
                Log.e("ErrOR",error.toString());
                String r = error.toString();
                Log.e("THE", r);
            }
        });
        jsonObjectRequest.setShouldCache(false);
        requestQueue.add(jsonObjectRequest);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                final Intent mainIntent = new Intent(SecondActivity.this, MainActivity.class);
                startActivity(mainIntent);
                finish();
            }
        }, 3000);
    }


}

