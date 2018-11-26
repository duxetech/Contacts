package com.duxetech.mycontacts;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
/**
 * Created by Karthik Swamy on 12/11/18.
 */

public class MainActivity extends AppCompatActivity {

    SharedPreferences pref;
    EditText et1, et2;
    Button bLogin;
    String sf = "preferences";
    TextView tvRegister;
    Intent i ;
    DBManager db;
    String a,b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapViews();
        db = new DBManager(this);


        //Checking Login status

        if(pref.getString("isLogged","").equals("true")) {

            if (!pref.getString("key_name", "").isEmpty()) {
                i = new Intent(MainActivity.this, ContactsList.class);

                startActivity(i);
                return;
            }
        }

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = new Intent(MainActivity.this,Register.class);

                startActivity(i);

            }
        });

        final SharedPreferences.Editor editor = pref.edit();
        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readValues();
                if (a.equals("") || b.equals("")) {
                    return;
                }
                if (db.validateLogin(a, b)) {
                    editor.putString("key_name", a);
                    editor.putString("isLogged", "true");
                    editor.apply();
                    //Toast.makeText(MainActivity.this, ""+pref.getString("key_name",""), Toast.LENGTH_SHORT).show();
                    i = new Intent(MainActivity.this, ContactsList.class);

                    startActivity(i);
                    finish();
                } else
                    Toast.makeText(MainActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                    return;
            }
        });

    }
    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    void readValues(){
        a = et1.getText().toString().trim();
        b = et2.getText().toString().trim();

    }
    void mapViews(){
        et1 = findViewById(R.id.et1);
        et2 = findViewById(R.id.et2);
        bLogin = findViewById(R.id.bLogin);
        pref = this.getSharedPreferences(sf,MODE_PRIVATE);
        tvRegister = findViewById(R.id.tvRegister);
    }

}
