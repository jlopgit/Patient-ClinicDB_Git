package com.example.panggolowitski.clinicapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import java.util.ArrayList;

//void setViews()           Show patient info of the patient chosen from the search result.

public class Patient extends AppCompatActivity {

    private TextView patientnumber;
    private TextView firstname;
    private TextView lastname;
    private TextView dob;
    private TextView ssn;
    private TextView address;
    private TextView insuranceName;
    private TextView insuranceNumber;
    private TextView dos;
    private TextView condition;
    private TextView treatment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);

        patientnumber = (TextView) findViewById(R.id.patientNumber);
        firstname = (TextView) findViewById(R.id.firstname);
        lastname = (TextView) findViewById(R.id.lastname);
        dob = (TextView) findViewById(R.id.dob);
        ssn = (TextView) findViewById(R.id.ssn);
        address = (TextView) findViewById(R.id.address);
        insuranceName = (TextView) findViewById(R.id.insuranceName);
        insuranceNumber = (TextView) findViewById(R.id.insuranceNumber);
        dos = (TextView) findViewById(R.id.dos);
        condition = (TextView) findViewById(R.id.condition);
        treatment = (TextView) findViewById(R.id.treatment);

        setViews();
    }

    //Shows info of chosen patient from the searchh result.
    public void setViews(){

        Intent previous = getIntent();

        patientnumber.setText("Patient Number: " + previous.getIntExtra("id",0));
        firstname.setText("First Name: " + previous.getStringExtra("firstname"));
        lastname.setText("Last Name: " + previous.getStringExtra("lastname"));
        dob.setText("Date of Birth: "+ previous.getStringExtra("dob"));
        ssn.setText("SSN: " + previous.getStringExtra("ssn"));
        address.setText("Address: " + previous.getStringExtra("address"));
        insuranceName.setText("Insurance Name: " + previous.getStringExtra("insurancename"));
        insuranceNumber.setText("Insurance Number: " + previous.getStringExtra("insurancenumber"));
        dos.setText("Date of Service: " + previous.getStringExtra("dateofservice"));
        condition.setText("Condition: " + previous.getStringExtra("condition"));
        treatment.setText("Treatment: " + previous.getStringExtra("treatment"));
    }

}
