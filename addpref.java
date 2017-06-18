package com.honeywell.h223490.stock;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;

public class addpref extends AppCompatActivity {

    public EditText editTextName;
    public EditText editTextTicker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addpref);

        editTextName = (EditText) findViewById(R.id.editTextUsername);
        editTextName.getText();
        editTextTicker = (EditText) findViewById(R.id.editTextPassword);
        editTextTicker.getText();
        final Button Preference = (Button) findViewById(R.id.button9);


        SharedPreferences idlang = getSharedPreferences("MyPref", Activity.MODE_PRIVATE);
        final String languageid = idlang.getString("langid", idlang.toString());


        Preference.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v19) {



                com.android.volley.RequestQueue logoutqueue = Volley.newRequestQueue(addpref.this);

                String stockurl = "http://stockticker20170616112945.azurewebsites.net/Auth/AddPref?id="+languageid+"&ticker="+editTextTicker.getText()+"&company="+editTextName+"&market=NASDAQ";

                //Json object request for Subscribe API

                // com.android.volley.RequestQueue nsequeue = Volley.newRequestQueue(Nseexchange.this);

                // String stockurl = "http://stockticker20170616112945.azurewebsites.net/Home/GetDataV2?market=NASDAQ&companies="+textView.getText();

                //  com.android.volley.RequestQueue logoutqueue = Volley.newRequestQueue(preference.this);

                //   String stockurl = "http://stockticker20170616112945.azurewebsites.net/Auth/GetPref?id="+languageid;

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




                                        AlertDialog.Builder builder = new AlertDialog.Builder(addpref.this);
                                        builder.setMessage(response.toString())
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
                                        setContentView(R.layout.activity_addpref);






                            }
                        },
                        new Response.ErrorListener(){
                            @Override
                            public void onErrorResponse(VolleyError error){
                                // Do something when error occurred

                                AlertDialog.Builder builder = new AlertDialog.Builder(addpref.this);
                                builder.setMessage("Enter Correct Ticker")
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
                                setContentView(R.layout.activity_addpref);



                            }
                        }
                );

                // Add JsonArrayRequest to the RequestQueue
                logoutqueue.add(jsonArrayRequest);
            }



























        });






    }
}
