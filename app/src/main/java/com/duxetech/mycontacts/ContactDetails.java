package com.duxetech.mycontacts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
/**
 * Created by Karthik Swamy on 12/11/18.
 */

public class ContactDetails extends AppCompatActivity {

    TextView tvFName, tvLName, tvPhone, tvMail, tvAddress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);
        tvFName = findViewById(R.id.tvFName);
        tvLName = findViewById(R.id.tvLName);
        tvMail = findViewById(R.id.tvMail);
        tvPhone = findViewById(R.id.tvPhone);
        tvAddress = findViewById(R.id.tvAddress);

        Bundle bundle = getIntent().getExtras();
        tvFName.setText(bundle.getString("fname"));
        tvLName.setText(bundle.getString("lname"));
        tvPhone.setText(bundle.getString("phone"));
        tvMail.setText(bundle.getString("mail"));
        tvAddress.setText(bundle.getString("address"));
    }
}
