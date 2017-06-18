package com.honeywell.h223490.stock;

import android.content.DialogInterface;
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
import org.json.JSONException;
import org.json.JSONObject;

import static com.honeywell.h223490.stock.R.id.nasdaqbutton;

public class Nasdaqexchange extends AppCompatActivity {

    public EditText textView;
    public Button buttonn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nasdaqexchange);
        textView = (EditText) findViewById(R.id.nasdaqtext);
        textView.getText();
        buttonn = (Button) findViewById(nasdaqbutton);


        buttonn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v12) {
                if (v12 == buttonn) {
                    showBseStock();
                }


            }














            public void showBseStock() {


                com.android.volley.RequestQueue logoutqueue = Volley.newRequestQueue(Nasdaqexchange.this);

                String stockurl = "http://stockticker20170616112945.azurewebsites.net/Home/GetDataV2?market=NASDAQ&companies="+textView.getText();

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
                                try{
                                    // Loop through the array elements
                                    for(int i=0;i<response.length();i++){
                                        // Get current json object
                                        JSONObject student = response.getJSONObject(i);
                                        String Ticker = student.getString("t");
                                        String Exchange = student.getString("e");
                                        String LastPrice = student.getString("l");
                                        String Price = student.getString("l_cur");
                                        String Change = student.getString("c");
                                        String ChangePercentage = student.getString("cp");

                                       // Toast.makeText(getApplicationContext(), Ticker, Toast.LENGTH_SHORT).show();



                                        AlertDialog.Builder builder = new AlertDialog.Builder(Nasdaqexchange.this);
                                        builder.setMessage("Ticker:"+Ticker+

                                                           "Exchange:"+Exchange+

                                                           "Last Price:"+LastPrice+

                                                           "Price:"+Price+

                                                           "Change:"+Change+

                                                           "Change Percentage:"+ChangePercentage+"")
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
                                        setContentView(R.layout.activity_nasdaqexchange);






















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

                                AlertDialog.Builder builder = new AlertDialog.Builder(Nasdaqexchange.this);
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
                                setContentView(R.layout.activity_nasdaqexchange);



                            }
                        }
                );

                // Add JsonArrayRequest to the RequestQueue
                logoutqueue.add(jsonArrayRequest);
            }




        });










    }
}
