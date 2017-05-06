package com.example.panggolowitski.clinicapplication;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;

//ListView of resulted items from the patient database.

public class Result extends AppCompatActivity{

    ArrayList<Integer> id = new ArrayList<Integer>();
    ArrayList<String> firstname = new ArrayList<String>();
    ArrayList<String> lastname = new ArrayList<String>();
    ArrayList<String> dob = new ArrayList<String>();
    ArrayList<String> ssn = new ArrayList<String>();
    ArrayList<String> address = new ArrayList<String>();
    ArrayList<String> insurancename = new ArrayList<String>();
    ArrayList<String> insurancenumber = new ArrayList<String>();
    ArrayList<String> dos = new ArrayList<String>();
    ArrayList<String> condition = new ArrayList<String>();
    ArrayList<String> treatment = new ArrayList<String>();

    ArrayList<String> queryResult = new ArrayList<String>();

    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        list = (ListView) findViewById(R.id.list);

        Intent intent = getIntent();

        //this was being used to receive only one set of info at a time.
        /*id.add(intent.getIntExtra("id", 0));
        firstname.add(intent.getStringExtra("firstname"));
        lastname.add(intent.getStringExtra("lastname"));
        dob.add(intent.getStringExtra("dob"));
        ssn.add(intent.getStringExtra("ssn"));
        address.add(intent.getStringExtra("address"));
        insurancename.add(intent.getStringExtra("insurancename"));
        insurancenumber.add(intent.getStringExtra("insurancenumber"));
        dos.add(intent.getStringExtra("dateofservice"));
        condition.add(intent.getStringExtra("condition"));
        treatment.add(intent.getStringExtra("treatment"));*/

        //get arraylists from Options activity. Get every patient info
        //that matches the search from Options activity.
        id = intent.getIntegerArrayListExtra("id");
        firstname = intent.getStringArrayListExtra("firstname");
        lastname = intent.getStringArrayListExtra("lastname");
        dob = intent.getStringArrayListExtra("dob");
        ssn = intent.getStringArrayListExtra("ssn");
        address = intent.getStringArrayListExtra("address");
        insurancename = intent.getStringArrayListExtra("insurancename");
        insurancenumber = intent.getStringArrayListExtra("insurancenumber");
        dos = intent.getStringArrayListExtra("dateofservice");
        condition = intent.getStringArrayListExtra("condition");
        treatment = intent.getStringArrayListExtra("treatment");

        //Show the resulted patient id, name, dob
        for(int i = 0; i < id.size(); i++){

            queryResult.add("ID: " + id.get(i).toString() + ", " +
                    firstname.get(i) + " " + lastname.get(i) +
                    ", " + dob.get(i));
        }

        //set up listview of resulted patients
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.activity_listview, queryResult);

        list.setAdapter(adapter);

        list.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                Intent patient = new Intent(Result.this, Patient.class);

                //pass patient info at the clicked position to Patient Activity.
                patient.putExtra("id", Result.this.id.get(position));
                patient.putExtra("firstname", Result.this.firstname.get(position).toString());
                patient.putExtra("lastname", Result.this.lastname.get(position).toString());
                patient.putExtra("dob", Result.this.dob.get(position).toString());
                patient.putExtra("ssn", Result.this.ssn.get(position).toString());
                patient.putExtra("address", Result.this.address.get(position).toString());
                patient.putExtra("insurancename", Result.this.insurancename.get(position).toString());
                patient.putExtra("insurancenumber", Result.this.insurancenumber.get(position).toString());
                patient.putExtra("dateofservice", Result.this.dos.get(position).toString());
                patient.putExtra("condition", Result.this.condition.get(position).toString());
                patient.putExtra("treatment", Result.this.treatment.get(position).toString());

                startActivity(patient);
            }
        });

    }

}
