package com.duxetech.mycontacts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;
/**
 * Created by Karthik Swamy on 12/11/18.
 */

public class CreateContact extends AppCompatActivity {

    DBManager db;
    EditText et_fName, et_lName, et_Mobile, et_mail, et_address;
    Button btn_addContact;
    List<Contacts> list ;
    String a, b,c,d,e;
    Contacts con;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_contact);
        btn_addContact = findViewById(R.id.btn_addContact);

        db = new DBManager(this);
        //db.delete();

        btn_addContact = findViewById(R.id.btn_addContact);
        et_fName = findViewById(R.id.et_fName);
        et_lName = findViewById(R.id.et_lName);
        et_Mobile = findViewById(R.id.et_Mobile);
        et_mail = findViewById(R.id.et_mail);
        et_address = findViewById(R.id.et_address);
        con = new Contacts();

        list = db.getAllContacts();

        btn_addContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                map();
                checkInputFields();
                if(a.equals("")|b.equals("")|c.equals("")|d.equals("")){
                    Toast.makeText(CreateContact.this, "Enter all the details", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(db.checkPhoneExists(c)){
                    Toast.makeText(CreateContact.this, "Mobile no already exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                db.insertContact(new Contacts(a,b,c,d,e));
                // db.read(list);

                Intent i = new Intent(CreateContact.this,ContactsList.class);
                startActivity(i);
                clear();
            }
        });
    }
    void map(){
        a = et_fName.getText().toString().trim();
        b = et_lName.getText().toString().trim();
        c = et_Mobile.getText().toString().trim();
        d = et_mail.getText().toString().trim();
        e = et_address.getText().toString().trim();
    }
    void checkInputFields(){

        if(checkA()|checkB()|checkC()|checkD()){
            return;
        }
    }
    void checkUserExists(){


    }
    boolean checkA() {
        if (a.isEmpty()) {
            et_fName.setError("Field can't be empty");
            return false;
        } else
            return true;
    }

    boolean checkB() {
        if (b.isEmpty()) {
            et_lName.setError("Field can't be empty");
            return false;
        } else
            return true;
    }
    boolean checkC() {
        if (c.isEmpty()) {
            et_Mobile.setError("Field can't be empty");
            return false;
        } else
            return true;
    }
    boolean checkD() {
        if (d.isEmpty()) {
            et_mail.setError("Field can't be empty");
            return false;
        } else
            return true;
    }

    void clear(){
        et_fName.setText("");
        et_lName.setText("");
        et_Mobile.setText("");
        et_mail.setText("");
        et_address.setText("");

    }

}
