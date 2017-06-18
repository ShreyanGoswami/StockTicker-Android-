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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class addingpeff extends AppCompatActivity {


    private Button buttonRegister;
    public Button button6;

    public EditText editText;
    public EditText editText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addingpeff);
        editText = (EditText) findViewById(R.id.editText);
        editText.getText();
        editText2 = (EditText) findViewById(R.id.editText2);
        editText2.getText();
        button6 = (Button) findViewById(R.id.button6);


        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == button6) {
                    registerUser();
                }
            }


            public void registerUser() {


                SharedPreferences idlang = getSharedPreferences("MyPref", Activity.MODE_PRIVATE);
                final String languageid = idlang.getString("langid", idlang.toString());

                com.android.volley.RequestQueue logoutqueue = Volley.newRequestQueue(addingpeff.this);

                String stockurl = "http://stockticker20170616112945.azurewebsites.net/Auth/AddPref?id="+languageid+"&ticker="+editText.getText()+"&company="+editText2.getText()+"&market=NASDAQ";

                //Json object request for Subscribe API

                // com.android.volley.RequestQueue nsequeue = Volley.newRequestQueue(Nseexchange.this);

                // String stockurl = "http://stockticker20170616112945.azurewebsites.net/Home/GetDataV2?market=NASDAQ&companies="+textView.getText();

                //  com.android.volley.RequestQueue logoutqueue = Volley.newRequestQueue(preference.this);

                //   String stockurl = "http://stockticker20170616112945.azurewebsites.net/Auth/GetPref?id="+languageid;

                //Json object request for Subscribe API
                //   RequestQueue requestQueue = Volley.newRequestQueue(mContext);

                // Initialize a new JsonArrayRequest instance
                JsonObjectRequest jsonArrayRequest = new JsonObjectRequest(
                        Request.Method.GET,
                        stockurl,
                        null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                // Do something with response
                                //mTextView.setText(response.toString());

                                // Process the JSON


                                AlertDialog.Builder builder = new AlertDialog.Builder(addingpeff.this);
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
                                setContentView(R.layout.activity_addingpeff);


                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // Do something when error occurred

                                AlertDialog.Builder builder = new AlertDialog.Builder(addingpeff.this);
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
                                setContentView(R.layout.activity_addingpeff);


                            }
                        }
                );

                // Add JsonArrayRequest to the RequestQueue
                logoutqueue.add(jsonArrayRequest);
            }










        });




    }
}
