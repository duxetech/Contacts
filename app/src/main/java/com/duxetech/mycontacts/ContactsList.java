package com.duxetech.mycontacts;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by Karthik Swamy on 12/11/18.
 */

public class ContactsList extends AppCompatActivity {

    DBManager db;
    ListView lv;
    List<Contacts> list = new ArrayList<>();
    CustAdapter custAdapter;
    Button btn_add, bLogout;
    Contacts con;
    TextView tvWelcome;
    SharedPreferences pref;
    String sf = "preferences";
    SharedPreferences.Editor editor;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts_list);
        lv = findViewById(R.id.lv);
        tvWelcome = findViewById(R.id.tvWelcome);

        db = new DBManager(this);
        //db.insert();
        list = db.getAllContacts();
        //list.clear();
        btn_add = findViewById(R.id.btn_add);
        bLogout = findViewById(R.id.bLogout);

        pref = getSharedPreferences(sf,MODE_PRIVATE);

        tvWelcome.setText("Welcome "+pref.getString("key_name",""));


        // adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,list);
        custAdapter = new CustAdapter(ContactsList.this, list);

        lv.setAdapter(custAdapter);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = new Intent(ContactsList.this,CreateContact.class);
                startActivity(i);
            }
        });


        bLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  updateSF();
                  i = new Intent(ContactsList.this,MainActivity.class);
                  startActivity(i);
                  finish();
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder alert = new AlertDialog.Builder(ContactsList.this);
                con = list.get(position);
                // alert.setTitle(con.getFirst_name()+" "+con.getLast_name())
                //       .setMessage(con.getMobile()+"\n"
                //     +con.getMail()).show();

                Intent i = new Intent(ContactsList.this,ContactDetails.class);

                i.putExtra("fname",con.getFirst_name());
                i.putExtra("lname",con.getLast_name());
                i.putExtra("phone",con.getMobile());
                i.putExtra("mail",con.getMail());
                startActivity(i);
                //finish();
            }
        });


    }
    void updateSF(){
        editor = pref.edit();
        editor.putString("isLogged", "false");
        editor.apply();
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}
