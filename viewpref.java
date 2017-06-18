package com.honeywell.h223490.stock;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class viewpref extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpref);


        com.android.volley.RequestQueue logoutqueue = Volley.newRequestQueue(viewpref.this);

        String stockurl = "http://stockticker20170616112945.azurewebsites.net/Auth/GetPref?id=1";

        //Json object request for Subscribe API

        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.GET, stockurl, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Log.d("Response: ", response.toString());


                        Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();


                        String Ticker = null;
                        try {
                            Ticker = response.getString("t");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                        Toast.makeText(getApplicationContext(), Ticker, Toast.LENGTH_SHORT).show();


                        AlertDialog.Builder builder = new AlertDialog.Builder(viewpref.this);
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
                        alert.setTitle("AlertDialogExample");
                        alert.show();
                        setContentView(R.layout.activity_viewpref);


                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                error.printStackTrace();
                Log.d("ErrorResponse: ", error.toString());

                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
                //Intent intent = new Intent(LoginActivity.class);


            }

        }) {


            @Override

            public Map<String, String> getHeaders() throws AuthFailureError {


                Map<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                headers.put("Accept", "application/json; charset=UTF-8 ");
                //    headers.put("x-session-token", sessionToken);

                return headers;
            }
        };
        //Log.d("url: ", stringRequest.getUrl());
        logoutqueue.add(stringRequest);

    }


}

