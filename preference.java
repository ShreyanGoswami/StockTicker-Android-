package com.honeywell.h223490.stock;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class preference extends AppCompatActivity {
    public Button button;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preference);


        // Button NASDAQ = (Button) findViewById(R.id.button3);
        //final Button addpref1 = (Button) findViewById(R.id.button7);
        final Button viewpref = (Button) findViewById(R.id.button8);
       // final Button Preference = (Button) findViewById(R.id.addpreference);
        button = (Button) findViewById(R.id.addpreference);
        //  final Button Preference = (Button) findViewById(R.id.button5);
        SharedPreferences idlang = getSharedPreferences("MyPref", Activity.MODE_PRIVATE);
        final String languageid = idlang.getString("langid", idlang.toString());


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v44) {


                    Intent intent35 = new Intent(preference.this, addingpeff.class);
                    startActivity(intent35);

                }















        });




        viewpref.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v9) {

             //   Intent intent = new Intent(preference.this, viewpref.class);
           //     startActivity(intent);


                com.android.volley.RequestQueue logoutqueue = Volley.newRequestQueue(preference.this);

                String stockurl = "http://stockticker20170616112945.azurewebsites.net/Auth/GetPref?id="+languageid;

                //Json object request for Subscribe API
             //   RequestQueue requestQueue = Volley.newRequestQueue(mContext);

                // Initialize a new JsonArrayRequest instance
                JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                        Request.Method.GET,
                        stockurl,
                        null,
                        new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {
                                // Do something with response
                                //mTextView.setText(response.toString());

                                // Process the JSON
                                try{
                                    // Loop through the array elements
                                    for(int i=0;i<response.length();i++){
                                        // Get current json object
                                        JSONObject student = response.getJSONObject(i);
                                        String Ticker = student.getString("Ticker");

                                     //   Toast.makeText(getApplicationContext(), Ticker, Toast.LENGTH_SHORT).show();



                                        AlertDialog.Builder builder = new AlertDialog.Builder(preference.this);
                                        builder.setMessage(Ticker)

                                                .setCancelable(false)
                                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                                                    public void onClick(DialogInterface dialog, int id) {
                                                        dialog.cancel();
                                                    }
                                                })
                                                .setNegativeButton("No", new DialogInterface.OnClickListener() {




                                                    public void onClick(DialogInterface dialog, int id) {                //  Action for 'NO' Button
                                                        dialog.cancel();
                                                    }
                                                });

                                        //Creating dialog box
                                        AlertDialog alert = builder.create();
                                        //Setting the title manually
                                        alert.setTitle("STOCK DETAILS");
                                        alert.show();
                                        setContentView(R.layout.activity_preference);






















                                    }
                                }catch (JSONException e){
                                    e.printStackTrace();
                                }
                            }
                        },
                        new Response.ErrorListener(){
                            @Override
                            public void onErrorResponse(VolleyError error){
                                // Do something when error occurred




                            }
                        }
                );

                // Add JsonArrayRequest to the RequestQueue
                logoutqueue.add(jsonArrayRequest);
            }




        });













    }
    }