package com.honeywell.h223490.stock;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.firebase.iid.FirebaseInstanceId;


public class StockWindow extends AppCompatActivity {

    public Button button;
    public Button buttonB;
    public Button buttonN;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_window);
      //  button = (Button) findViewById(R.id.buttonRegister);
      // buttonB = (Button) findViewById(R.id.button2);
     //   buttonN = (Button) findViewById(R.id.button3);


         Button NASDAQ = (Button) findViewById(R.id.button3);
        final Button BSE = (Button) findViewById(R.id.button2);
        final Button NSE = (Button) findViewById(R.id.buttonRegister);
        final Button Preference = (Button) findViewById(R.id.button5);


        String token = FirebaseInstanceId.getInstance().getToken();
        SharedPreferences gcmtoken = getApplicationContext().getSharedPreferences("MyPref", 0);

        SharedPreferences.Editor editor = gcmtoken.edit();
        editor.putString("Token",token);





        NASDAQ.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v9) {

                Intent intent = new Intent(StockWindow.this, Nasdaqexchange.class);
                startActivity(intent);
            }
        });











        BSE.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v10) {


                if (v10 == BSE) {


                    Intent intent = new Intent(StockWindow.this, bseexchange.class);
                    startActivity(intent);
          //          registerUser();
                }


            }
        });



        NSE.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v11) {


                if (v11 == NSE) {


                    Intent intent = new Intent(StockWindow.this, Nseexchange.class);
                    startActivity(intent);
                    //          registerUser();
                }


            }
        });





        Preference.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v13) {


                if (v13 == Preference) {


                    Intent intent3 = new Intent(StockWindow.this, preference.class);
                    startActivity(intent3);
                    //          registerUser();
                }


            }
        });


        //  buttonN.setOnClickListener(new View.OnClickListener() {
       //     public void onClick(View v) {
                ;
      //          Intent intent = new Intent(StockWindow.this, LoginActivity.class);
       //         startActivity(intent);
      //      }
      //  });



















    }}










