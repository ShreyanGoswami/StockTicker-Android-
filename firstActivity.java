package com.honeywell.h223490.stock;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class firstActivity extends AppCompatActivity {

    public static final String KEY_USERNAME = "username";
    public static final String KEY_PASSWORD = "password";
    private static final String TAG = "Login";
    public static String sessionToken = "";
    public EditText editTextUsername;
    public EditText editTextPassword;


    private Button buttonRegister;
    public Button button;
    private Spinner spinner1, spinner2;
    private ImageView image;
    private String[] states;
    private Spinner spinner;
    private TypedArray imgs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        editTextUsername = (EditText) findViewById(R.id.editTextUsername);
        editTextUsername.getText();
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        editTextPassword.getText();
        button = (Button) findViewById(R.id.login);
        //  buttonRegister = (Button) findViewById(buttonRegister);


        // states = getResources().getStringArray(R.array.language_list);
        // imgs = getResources().obtainTypedArray(R.array.countries_flag_list);

        //    image = (ImageView) findViewById(R.id.country_image);
        //   spinner = (Spinner) findViewById(R.id.language_spinner);

        //  ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
        //            android.R.layout.simple_spinner_item, states);
        //    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //    spinner.setAdapter(dataAdapter);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == button) {
                    login();
                }


            }


        });
    }


    private void login() {


        String authURL = "http://stockticker20170616112945.azurewebsites.net/Auth/AuthUser?name=" + editTextUsername.getText() + "&pwd=" + editTextPassword.getText();

     //   String authURL = "http://stockticker20170616112945.azurewebsites.net/Auth/AuthUser?name=Shreyan&pwd=Test";
        RequestQueue logoutqueue = Volley.newRequestQueue(firstActivity.this);

        // String logouturl = url+"/applicationservice/domoweb/security/logout/?restoreHB=false";

        //Json object request for Subscribe API

        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.GET, authURL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Log.d("Response: ", response.toString());
                      //  Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
                        String Identity;
                        try {
                            Identity=response.getString("Id");
                            SharedPreferences idlang = getSharedPreferences("MyPref", Activity.MODE_PRIVATE);
                            SharedPreferences.Editor languageid = idlang.edit();
                            languageid.putString("langid", String.valueOf(Identity));
                            languageid.commit();


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        String Validity = null;
                        try {
                            Validity= response.getString("isPresent");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                        assert Validity != null;
                        if(Validity.equals("True"))
                         {
                            // Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();



                             AlertDialog.Builder builder = new AlertDialog.Builder(firstActivity.this);
                             builder.setMessage("Would You Like To Continue!")
                                     .setCancelable(true)
                                     .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                                         public void onClick(DialogInterface dialog, int id) {

                                             Intent intent = new Intent(firstActivity.this, StockWindow.class);
                                             startActivity(intent);
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
                             alert.setTitle("USER CREDENTIAL");
                             alert.show();
                             setContentView(R.layout.activity_first);




                         }





                        //  Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                error.printStackTrace();
                Log.d("ErrorResponse: ", error.toString());

             Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
                //String Tom=error.toString();


                AlertDialog.Builder builder = new AlertDialog.Builder(firstActivity.this);
                builder.setMessage("Please to check your credentials")
                        .setCancelable(true)
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
                alert.setTitle("USER CREDENTIAL");
                alert.show();
                setContentView(R.layout.activity_first);



               // Intent intent = new Intent(LoginActivity.class);


            }

        }) {


            @Override

            public Map<String, String> getHeaders() throws AuthFailureError {


                Map<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                headers.put("Accept", "application/json; charset=UTF-8 ");
              //  headers.put("x-session-token", sessionToken);

                return headers;
            }
        };
        //Log.d("url: ", stringRequest.getUrl());
        logoutqueue.add(stringRequest);



    }


}






