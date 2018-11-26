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

public class Register extends AppCompatActivity {

    TextView tvLogin;
    Button bRegister;
    SharedPreferences pref;
    String data;
    EditText et3, et4, et5;
    DBManager db;
    SharedPreferences.Editor editor;
    String a,b,c;
    String sf = "preferences";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mapViews();
        db = new DBManager(this);


        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Register.this,MainActivity.class);
                startActivity(i);
            }
        });

        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                readValues();

                if(a.isEmpty()||b.isEmpty()||c.isEmpty()){
                    return;
                }

                if (!db.checkUserRegistered(a)) {
                    if (db.insert(a, b, c)) {
                        editor.putString("key_name", a);
                        editor.putString("isLogged", "true");
                        editor.commit();
                        Intent i = new Intent(Register.this, ContactsList.class);
                        //Clear contacts of the other User
                        db.deleteContacts();
                        startActivity(i);
                        finish();
                        return;
                    }
                }
                Toast.makeText(Register.this, "User already exists", Toast.LENGTH_SHORT).show();
            }
        });
    }
    void mapViews(){
        tvLogin= findViewById(R.id.tvLogin);
        bRegister = findViewById(R.id.bRegister);
        pref = this.getSharedPreferences(sf,MODE_PRIVATE);
        editor = pref.edit();
        et3 = findViewById(R.id.et3);
        et4 = findViewById(R.id.et4);
        et5 = findViewById(R.id.et5);
    }
    void readValues(){
         a = et3.getText().toString().trim();
         b = et4.getText().toString().trim();
         c = et5.getText().toString().trim();
    }
}
