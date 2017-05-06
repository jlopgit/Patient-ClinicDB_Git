package com.example.panggolowitski.clinicapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

//void showSearchForm()             Shows the search form, and sets search forms to empty.
//void hideSearchForm()             Hides search form.
//void showInputForm()              Empties new patient form, and shows the new patient form.
//void hideInputForm()              Hides input form.
//void newEntry(View)               Calls showInputForm(), and hideSearchForm().
//void save(View)                   Checks if input form is complete, and inserts new patient into database.
//void search(View)                 Calls showSearchForm(), and hideInputForm().
//void findPatient(View)            Search database according to user input. If found, switch to Result Activity.

public class Options extends AppCompatActivity {

    private Button newEntry;
    private Button search;

    //search views
    private TextView extraLineSearchInfo;
    private EditText firstNameSearch;
    private EditText lastNameSearch;
    private EditText DOBSearch;
    private EditText SSNSearch;
    private Button findPatient;
    private int patientNumber = 0;

    //input views
    private TextView extraLineInputInfo;
    private EditText firstnameInput;
    private EditText lastnameInput;
    private EditText dobInput;
    private EditText ssnInput;
    private EditText addressInput;
    private EditText aptNumberInput;
    private EditText cityInput;
    private EditText stateInput;
    private EditText zipcodeInput;
    private TextView insuranceInfo;
    private EditText insuranceNameInput;
    private EditText insuranceNumberInput;
    private TextView serviceInfo;
    private EditText serviceDateInput;
    private EditText conditionInput;
    private EditText treatmentInput;
    private Button saveInput;

    //hide keyboard
    InputMethodManager inputManager;
    private Intent result;

    //set up patient database
    private SQLiteDatabase patientsDB;
    public static final String _ID = "_id";
    public static final String DATABASE_NAME = "patients";
    public static final String TABLE_NAME = "mytable";
    public static final String TABLE_CREATE = " CREATE TABLE IF NOT EXISTS " + TABLE_NAME +
            "(_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            " firstname TEXT NOT NULL, " +
            " lastname TEXT NOT NULL, " +
            " dob TEXT NOT NULL, " +
            " ssn TEXT NOT NULL, " +
            " address TEXT NOT NULL, " +
            " insurancename Text NOT NULL, " +
            " insurancenumber TEXT NOT NULL, " +
            " dateofservice TEXT NOT NULL, " +
            " condition TEXT NOT NULL, " +
            " treatment TEXT NOT NULL);";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        newEntry = (Button) findViewById(R.id.newEntry);
        search = (Button) findViewById(R.id.search);

        extraLineSearchInfo = (TextView) findViewById(R.id.extraLineSearchInfo);
        firstNameSearch = (EditText) findViewById(R.id.firstnameSearch);
        lastNameSearch = (EditText) findViewById(R.id.lastnameSearch);
        DOBSearch = (EditText) findViewById(R.id.dobSearch);
        SSNSearch = (EditText) findViewById(R.id.ssnSearch);
        findPatient = (Button) findViewById(R.id.find);

        extraLineInputInfo = (TextView) findViewById(R.id.extraLineInputInfo);
        firstnameInput = (EditText) findViewById(R.id.firstnameInput);
        lastnameInput = (EditText) findViewById(R.id.lastnameInput);
        dobInput = (EditText) findViewById(R.id.dobInput);
        ssnInput = (EditText) findViewById(R.id.ssnInput);
        addressInput = (EditText) findViewById(R.id.addressInput);
        /*aptNumberInput = (EditText) findViewById(R.id.aptNumberInput);
        cityInput = (EditText) findViewById(R.id.cityInput);
        stateInput = (EditText) findViewById(R.id.stateInput);
        zipcodeInput = (EditText) findViewById(R.id.zipcodeInput);*/
        insuranceInfo = (TextView) findViewById(R.id.extraline2);
        insuranceNameInput = (EditText) findViewById(R.id.insuranceNameInput);
        insuranceNumberInput = (EditText) findViewById(R.id.insuranceNumberInput);
        serviceInfo = (TextView)findViewById(R.id.extraline3);
        serviceDateInput = (EditText) findViewById(R.id.serviceDateInput);
        conditionInput = (EditText) findViewById(R.id.conditionInput);
        treatmentInput = (EditText) findViewById(R.id.treatmentInput);
        saveInput = (Button) findViewById(R.id.saveInput);

        //set up patient database
        patientsDB = openOrCreateDatabase(DATABASE_NAME, Context.MODE_PRIVATE, null);
        patientsDB.execSQL(TABLE_CREATE);

        inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
    }

    //Shows the search form
    //set search forms to empty.
    public void showSearchForm(){

        firstNameSearch.setText(null);
        lastNameSearch.setText(null);
        DOBSearch.setText(null);
        SSNSearch.setText(null);

        extraLineSearchInfo.setVisibility(View.VISIBLE);
        firstNameSearch.setVisibility(View.VISIBLE);
        lastNameSearch.setVisibility(View.VISIBLE);
        DOBSearch.setVisibility(View.VISIBLE);
        SSNSearch.setVisibility(View.VISIBLE);
        findPatient.setVisibility(View.VISIBLE);
    }

    //hides search form
    public void hideSearchForm(){

        extraLineSearchInfo.setVisibility(View.INVISIBLE);
        firstNameSearch.setVisibility(View.INVISIBLE);
        lastNameSearch.setVisibility(View.INVISIBLE);
        DOBSearch.setVisibility(View.INVISIBLE);
        SSNSearch.setVisibility(View.INVISIBLE);
        findPatient.setVisibility(View.INVISIBLE);
    }

    //Empties new patient form inputs
    //shows the new patient form
    public void showInputForm(){

        firstnameInput.setText(null);
        lastnameInput.setText(null);
        dobInput.setText(null);
        ssnInput.setText(null);
        addressInput.setText(null);
        /*aptNumberInput.setText(null);
        cityInput.setText(null);
        stateInput.setText(null);
        zipcodeInput.setText(null);*/
        insuranceNameInput.setText(null);
        insuranceNumberInput.setText(null);
        serviceDateInput.setText(null);
        conditionInput.setText(null);
        treatmentInput.setText(null);

        extraLineInputInfo.setVisibility(View.VISIBLE);
        firstnameInput.setVisibility(View.VISIBLE);
        lastnameInput.setVisibility(View.VISIBLE);
        dobInput.setVisibility(View.VISIBLE);
        ssnInput.setVisibility(View.VISIBLE);
        addressInput.setVisibility(View.VISIBLE);
        /*aptNumberInput.setVisibility(View.VISIBLE);
        cityInput.setVisibility(View.VISIBLE);
        stateInput.setVisibility(View.VISIBLE);
        zipcodeInput.setVisibility(View.VISIBLE);*/
        insuranceInfo.setVisibility(View.VISIBLE);
        insuranceNameInput.setVisibility(View.VISIBLE);
        insuranceNumberInput.setVisibility(View.VISIBLE);
        serviceInfo.setVisibility(View.VISIBLE);
        serviceDateInput.setVisibility(View.VISIBLE);
        conditionInput.setVisibility(View.VISIBLE);
        treatmentInput.setVisibility(View.VISIBLE);
        saveInput.setVisibility(View.VISIBLE);
    }

    //hides input form
    public void hideInputForm(){

        extraLineInputInfo.setVisibility(View.INVISIBLE);
        firstnameInput.setVisibility(View.INVISIBLE);
        lastnameInput.setVisibility(View.INVISIBLE);
        dobInput.setVisibility(View.INVISIBLE);
        ssnInput.setVisibility(View.INVISIBLE);
        addressInput.setVisibility(View.INVISIBLE);
        /*aptNumberInput.setVisibility(View.INVISIBLE);
        cityInput.setVisibility(View.INVISIBLE);
        stateInput.setVisibility(View.INVISIBLE);
        zipcodeInput.setVisibility(View.INVISIBLE);*/
        insuranceInfo.setVisibility(View.INVISIBLE);
        insuranceNameInput.setVisibility(View.INVISIBLE);
        insuranceNumberInput.setVisibility(View.INVISIBLE);
        serviceInfo.setVisibility(View.INVISIBLE);
        serviceDateInput.setVisibility(View.INVISIBLE);
        conditionInput.setVisibility(View.INVISIBLE);
        treatmentInput.setVisibility(View.INVISIBLE);
        saveInput.setVisibility(View.INVISIBLE);
    }

    //empty new patient input form
    //shows new patient input form
    //hides search input form
    public void newEntry(View view){

        showInputForm();
        hideSearchForm();
    }

    //checks if new patient input fields are filled in
    //if they are all filled in, input patient info into patient database.
    public void save(View view){

        //make sure all firstname, lastname, and dob is filled in.
        if(firstnameInput.getText().toString().trim().length() == 0 ||
                lastnameInput.getText().toString().trim().length() == 0 ||
                dobInput.getText().toString().trim().length() == 0 ||
                ssnInput.getText().toString().trim().length() == 0 ||
                addressInput.getText().toString().trim().length() == 0 ||
                insuranceNameInput.getText().toString().trim().length() == 0 ||
                insuranceNumberInput.getText().toString().trim().length() == 0 ||
                serviceDateInput.getText().toString().trim().length() == 0 ||
                conditionInput.getText().toString().trim().length() == 0 ||
                treatmentInput.getText().toString().trim().length() == 0){

            Toast.makeText(this, "PLEASE FILL IN THE FIELDS!!", Toast.LENGTH_LONG).show();
        }

        else{

            //insert user input into database
            patientsDB.execSQL("INSERT INTO mytable(firstname, lastname, dob, ssn, address, insurancename, " +
                    "insurancenumber, dateofservice, condition, treatment) VALUES" +
                    "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", new String[]{firstnameInput.getText().toString(),
                    lastnameInput.getText().toString(), dobInput.getText().toString(),
                    ssnInput.getText().toString(), addressInput.getText().toString(),
                    insuranceNameInput.getText().toString(), insuranceNumberInput.getText().toString(),
                    serviceDateInput.getText().toString(), conditionInput.getText().toString(),
                    treatmentInput.getText().toString()});

            Toast.makeText(this, "User New Patient Entry Created!!", Toast.LENGTH_LONG).show();
        }
        //hide keyboard
        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
    }

    //shows search form
    //empties search form
    //hides new patient input form
    public void search(View view){

        showSearchForm();
        hideInputForm();
    }

    //searches database depending on input
    public void findPatient(View view){

        //use input to search for patient.
        queryFields(firstNameSearch.getText().toString(), lastNameSearch.getText().toString(),
                DOBSearch.getText().toString(), SSNSearch.getText().toString());

        //hide keyboard
        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
    }

    //search database using firstname
    //search database using lastname
    //search database using dob
    //search database using ssn
    //search database using firstname, and lastname
    //search database using firstname and dob
    //search database using lastname, and dob
    //search database using firstname, lastname, and dob
    //search database using firstname, lastname, dob, and ssn
    //once a match is found, the result is passed into Results activity
    public void queryFields(String first, String last, String dob, String ssn){

        //search by firstname.
        if(!first.isEmpty() && last.isEmpty() && dob.isEmpty() && ssn.isEmpty()){

            Cursor temp = patientsDB.query(TABLE_NAME, null, "firstname=?",
                    new String[]{first}, null, null, null);

            ArrayList<Integer> identification = new ArrayList<Integer>();
            ArrayList<String> firstN = new ArrayList<String>();
            ArrayList<String> lastN = new ArrayList<String>();
            ArrayList<String> dateofB = new ArrayList<String>();
            ArrayList<String> SSNumber = new ArrayList<String>();
            ArrayList<String> addr = new ArrayList<String>();
            ArrayList<String> insuranceNa = new ArrayList<String>();
            ArrayList<String> insuranceNum = new ArrayList<String>();
            ArrayList<String> dateofSer = new ArrayList<String>();
            ArrayList<String> cond = new ArrayList<String>();
            ArrayList<String> treat = new ArrayList<String>();

            result = new Intent(this, Result.class);

            if(temp.getCount() > 0 ){

                if(temp.moveToFirst()){

                    do{

                        //this was a problem because I was passing the lines individually
                        //which overwrote the lines for duplicate patients in the Results Activity.
                        /*result.putExtra("id", temp.getInt(temp.getColumnIndex("_id")));
                        result.putExtra("firstname", temp.getString(temp.getColumnIndex("firstname")));
                        result.putExtra("lastname", temp.getString(temp.getColumnIndex("lastname")));
                        result.putExtra("dob", temp.getString(temp.getColumnIndex("dob")));
                        result.putExtra("ssn", temp.getString(temp.getColumnIndex("ssn")));
                        result.putExtra("address", temp.getString(temp.getColumnIndex("address")));
                        result.putExtra("insurancename", temp.getString(temp.getColumnIndex("insurancename")));
                        result.putExtra("insurancenumber", temp.getString(temp.getColumnIndex("insurancenumber")));
                        result.putExtra("dateofservice", temp.getString(temp.getColumnIndex("dateofservice")));
                        result.putExtra("condition", temp.getString(temp.getColumnIndex("condition")));
                        result.putExtra("treatment", temp.getString(temp.getColumnIndex("treatment")));*/

                        //add lines to the arraylists
                        identification.add(temp.getInt(temp.getColumnIndex("_id")));
                        firstN.add(temp.getString(temp.getColumnIndex("firstname")));
                        lastN.add(temp.getString(temp.getColumnIndex("lastname")));
                        dateofB.add(temp.getString(temp.getColumnIndex("dob")));
                        SSNumber.add(temp.getString(temp.getColumnIndex("ssn")));
                        addr.add(temp.getString(temp.getColumnIndex("address")));
                        insuranceNa.add(temp.getString(temp.getColumnIndex("insurancename")));
                        insuranceNum.add(temp.getString(temp.getColumnIndex("insurancenumber")));
                        dateofSer.add(temp.getString(temp.getColumnIndex("dateofservice")));
                        cond.add(temp.getString(temp.getColumnIndex("condition")));
                        treat.add(temp.getString(temp.getColumnIndex("treatment")));

                    }while(temp.moveToNext());
                }

                //pass the arraylists to Result Activity
                result.putIntegerArrayListExtra("id", identification);
                result.putStringArrayListExtra("firstname", firstN);
                result.putStringArrayListExtra("lastname", lastN);
                result.putStringArrayListExtra("dob", dateofB);
                result.putStringArrayListExtra("ssn", SSNumber);
                result.putStringArrayListExtra("address", addr);
                result.putStringArrayListExtra("insurancename", insuranceNa);
                result.putStringArrayListExtra("insurancenumber", insuranceNum);
                result.putStringArrayListExtra("dateofservice", dateofSer);
                result.putStringArrayListExtra("condition", cond);
                result.putStringArrayListExtra("treatment", treat);

                startActivity(result);
            }
            else{
                Toast.makeText(this, "Patient Not Found!", Toast.LENGTH_LONG).show();
            }
        }

        //search by lastname.
        else if(first.isEmpty() && !last.isEmpty() && dob.isEmpty() && ssn.isEmpty()){

            Cursor temp = patientsDB.query(TABLE_NAME, null, "lastname=?",
                    new String[]{last}, null, null, null);

            result = new Intent(this, Result.class);

            ArrayList<Integer> identification = new ArrayList<Integer>();
            ArrayList<String> firstN = new ArrayList<String>();
            ArrayList<String> lastN = new ArrayList<String>();
            ArrayList<String> dateofB = new ArrayList<String>();
            ArrayList<String> SSNumber = new ArrayList<String>();
            ArrayList<String> addr = new ArrayList<String>();
            ArrayList<String> insuranceNa = new ArrayList<String>();
            ArrayList<String> insuranceNum = new ArrayList<String>();
            ArrayList<String> dateofSer = new ArrayList<String>();
            ArrayList<String> cond = new ArrayList<String>();
            ArrayList<String> treat = new ArrayList<String>();

            result = new Intent(this, Result.class);

            if(temp.getCount() > 0 ){

                if(temp.moveToFirst()){

                    do{

                        //this was a problem because I was passing the lines individually
                        //which overwrote the lines for duplicate patients in the Results Activity.
                        /*result.putExtra("id", temp.getInt(temp.getColumnIndex("_id")));
                        result.putExtra("firstname", temp.getString(temp.getColumnIndex("firstname")));
                        result.putExtra("lastname", temp.getString(temp.getColumnIndex("lastname")));
                        result.putExtra("dob", temp.getString(temp.getColumnIndex("dob")));
                        result.putExtra("ssn", temp.getString(temp.getColumnIndex("ssn")));
                        result.putExtra("address", temp.getString(temp.getColumnIndex("address")));
                        result.putExtra("insurancename", temp.getString(temp.getColumnIndex("insurancename")));
                        result.putExtra("insurancenumber", temp.getString(temp.getColumnIndex("insurancenumber")));
                        result.putExtra("dateofservice", temp.getString(temp.getColumnIndex("dateofservice")));
                        result.putExtra("condition", temp.getString(temp.getColumnIndex("condition")));
                        result.putExtra("treatment", temp.getString(temp.getColumnIndex("treatment")));*/

                        //add lines to the arraylists
                        identification.add(temp.getInt(temp.getColumnIndex("_id")));
                        firstN.add(temp.getString(temp.getColumnIndex("firstname")));
                        lastN.add(temp.getString(temp.getColumnIndex("lastname")));
                        dateofB.add(temp.getString(temp.getColumnIndex("dob")));
                        SSNumber.add(temp.getString(temp.getColumnIndex("ssn")));
                        addr.add(temp.getString(temp.getColumnIndex("address")));
                        insuranceNa.add(temp.getString(temp.getColumnIndex("insurancename")));
                        insuranceNum.add(temp.getString(temp.getColumnIndex("insurancenumber")));
                        dateofSer.add(temp.getString(temp.getColumnIndex("dateofservice")));
                        cond.add(temp.getString(temp.getColumnIndex("condition")));
                        treat.add(temp.getString(temp.getColumnIndex("treatment")));

                    }while(temp.moveToNext());
                }

                //pass the arraylists to Result Activity
                result.putIntegerArrayListExtra("id", identification);
                result.putStringArrayListExtra("firstname", firstN);
                result.putStringArrayListExtra("lastname", lastN);
                result.putStringArrayListExtra("dob", dateofB);
                result.putStringArrayListExtra("ssn", SSNumber);
                result.putStringArrayListExtra("address", addr);
                result.putStringArrayListExtra("insurancename", insuranceNa);
                result.putStringArrayListExtra("insurancenumber", insuranceNum);
                result.putStringArrayListExtra("dateofservice", dateofSer);
                result.putStringArrayListExtra("condition", cond);
                result.putStringArrayListExtra("treatment", treat);

                startActivity(result);

            }
            else{
                Toast.makeText(this, "Patient Not Found!", Toast.LENGTH_LONG).show();
            }
        }

        //search by dob
        else if(first.isEmpty() && last.isEmpty() && !dob.isEmpty() && ssn.isEmpty()){

            Cursor temp = patientsDB.query(TABLE_NAME, null, "dob=?",
                    new String[]{dob}, null, null, null);

            ArrayList<Integer> identification = new ArrayList<Integer>();
            ArrayList<String> firstN = new ArrayList<String>();
            ArrayList<String> lastN = new ArrayList<String>();
            ArrayList<String> dateofB = new ArrayList<String>();
            ArrayList<String> SSNumber = new ArrayList<String>();
            ArrayList<String> addr = new ArrayList<String>();
            ArrayList<String> insuranceNa = new ArrayList<String>();
            ArrayList<String> insuranceNum = new ArrayList<String>();
            ArrayList<String> dateofSer = new ArrayList<String>();
            ArrayList<String> cond = new ArrayList<String>();
            ArrayList<String> treat = new ArrayList<String>();

            result = new Intent(this, Result.class);

            if(temp.getCount() > 0 ){

                if(temp.moveToFirst()){

                    do{

                        //this was a problem because I was passing the lines individually
                        //which overwrote the lines for duplicate patients in the Results Activity.
                        /*result.putExtra("id", temp.getInt(temp.getColumnIndex("_id")));
                        result.putExtra("firstname", temp.getString(temp.getColumnIndex("firstname")));
                        result.putExtra("lastname", temp.getString(temp.getColumnIndex("lastname")));
                        result.putExtra("dob", temp.getString(temp.getColumnIndex("dob")));
                        result.putExtra("ssn", temp.getString(temp.getColumnIndex("ssn")));
                        result.putExtra("address", temp.getString(temp.getColumnIndex("address")));
                        result.putExtra("insurancename", temp.getString(temp.getColumnIndex("insurancename")));
                        result.putExtra("insurancenumber", temp.getString(temp.getColumnIndex("insurancenumber")));
                        result.putExtra("dateofservice", temp.getString(temp.getColumnIndex("dateofservice")));
                        result.putExtra("condition", temp.getString(temp.getColumnIndex("condition")));
                        result.putExtra("treatment", temp.getString(temp.getColumnIndex("treatment")));*/

                        //add lines to the arraylists
                        identification.add(temp.getInt(temp.getColumnIndex("_id")));
                        firstN.add(temp.getString(temp.getColumnIndex("firstname")));
                        lastN.add(temp.getString(temp.getColumnIndex("lastname")));
                        dateofB.add(temp.getString(temp.getColumnIndex("dob")));
                        SSNumber.add(temp.getString(temp.getColumnIndex("ssn")));
                        addr.add(temp.getString(temp.getColumnIndex("address")));
                        insuranceNa.add(temp.getString(temp.getColumnIndex("insurancename")));
                        insuranceNum.add(temp.getString(temp.getColumnIndex("insurancenumber")));
                        dateofSer.add(temp.getString(temp.getColumnIndex("dateofservice")));
                        cond.add(temp.getString(temp.getColumnIndex("condition")));
                        treat.add(temp.getString(temp.getColumnIndex("treatment")));

                    }while(temp.moveToNext());
                }

                //pass the arraylists to Result Activity
                result.putIntegerArrayListExtra("id", identification);
                result.putStringArrayListExtra("firstname", firstN);
                result.putStringArrayListExtra("lastname", lastN);
                result.putStringArrayListExtra("dob", dateofB);
                result.putStringArrayListExtra("ssn", SSNumber);
                result.putStringArrayListExtra("address", addr);
                result.putStringArrayListExtra("insurancename", insuranceNa);
                result.putStringArrayListExtra("insurancenumber", insuranceNum);
                result.putStringArrayListExtra("dateofservice", dateofSer);
                result.putStringArrayListExtra("condition", cond);
                result.putStringArrayListExtra("treatment", treat);

                startActivity(result);
            }
            else{
                Toast.makeText(this, "Patient Not Found!", Toast.LENGTH_LONG).show();
            }
        }

        //search by ssn
        else if(first.isEmpty() && last.isEmpty() && dob.isEmpty() && !ssn.isEmpty()){

            Cursor temp = patientsDB.query(TABLE_NAME, null, "ssn=?",
                    new String[]{ssn}, null, null, null);

            ArrayList<Integer> identification = new ArrayList<Integer>();
            ArrayList<String> firstN = new ArrayList<String>();
            ArrayList<String> lastN = new ArrayList<String>();
            ArrayList<String> dateofB = new ArrayList<String>();
            ArrayList<String> SSNumber = new ArrayList<String>();
            ArrayList<String> addr = new ArrayList<String>();
            ArrayList<String> insuranceNa = new ArrayList<String>();
            ArrayList<String> insuranceNum = new ArrayList<String>();
            ArrayList<String> dateofSer = new ArrayList<String>();
            ArrayList<String> cond = new ArrayList<String>();
            ArrayList<String> treat = new ArrayList<String>();

            result = new Intent(this, Result.class);

            if(temp.getCount() > 0 ){

                if(temp.moveToFirst()){

                    do{

                        //this was a problem because I was passing the lines individually
                        //which overwrote the lines for duplicate patients in the Results Activity.
                        /*result.putExtra("id", temp.getInt(temp.getColumnIndex("_id")));
                        result.putExtra("firstname", temp.getString(temp.getColumnIndex("firstname")));
                        result.putExtra("lastname", temp.getString(temp.getColumnIndex("lastname")));
                        result.putExtra("dob", temp.getString(temp.getColumnIndex("dob")));
                        result.putExtra("ssn", temp.getString(temp.getColumnIndex("ssn")));
                        result.putExtra("address", temp.getString(temp.getColumnIndex("address")));
                        result.putExtra("insurancename", temp.getString(temp.getColumnIndex("insurancename")));
                        result.putExtra("insurancenumber", temp.getString(temp.getColumnIndex("insurancenumber")));
                        result.putExtra("dateofservice", temp.getString(temp.getColumnIndex("dateofservice")));
                        result.putExtra("condition", temp.getString(temp.getColumnIndex("condition")));
                        result.putExtra("treatment", temp.getString(temp.getColumnIndex("treatment")));*/

                        //add lines to the arraylists
                        identification.add(temp.getInt(temp.getColumnIndex("_id")));
                        firstN.add(temp.getString(temp.getColumnIndex("firstname")));
                        lastN.add(temp.getString(temp.getColumnIndex("lastname")));
                        dateofB.add(temp.getString(temp.getColumnIndex("dob")));
                        SSNumber.add(temp.getString(temp.getColumnIndex("ssn")));
                        addr.add(temp.getString(temp.getColumnIndex("address")));
                        insuranceNa.add(temp.getString(temp.getColumnIndex("insurancename")));
                        insuranceNum.add(temp.getString(temp.getColumnIndex("insurancenumber")));
                        dateofSer.add(temp.getString(temp.getColumnIndex("dateofservice")));
                        cond.add(temp.getString(temp.getColumnIndex("condition")));
                        treat.add(temp.getString(temp.getColumnIndex("treatment")));

                    }while(temp.moveToNext());
                }

                //pass the arraylists to Result Activity
                result.putIntegerArrayListExtra("id", identification);
                result.putStringArrayListExtra("firstname", firstN);
                result.putStringArrayListExtra("lastname", lastN);
                result.putStringArrayListExtra("dob", dateofB);
                result.putStringArrayListExtra("ssn", SSNumber);
                result.putStringArrayListExtra("address", addr);
                result.putStringArrayListExtra("insurancename", insuranceNa);
                result.putStringArrayListExtra("insurancenumber", insuranceNum);
                result.putStringArrayListExtra("dateofservice", dateofSer);
                result.putStringArrayListExtra("condition", cond);
                result.putStringArrayListExtra("treatment", treat);

                startActivity(result);
            }
            else{
                Toast.makeText(this, "Patient Not Found!", Toast.LENGTH_LONG).show();
            }
        }

        //search by firstname and lastname.
        else if(!first.isEmpty() && !last.isEmpty() && dob.isEmpty() && ssn.isEmpty()){

            Cursor temp = patientsDB.query(TABLE_NAME, null, "firstname=? and lastname =?",
                    new String[]{first, last}, null, null, null);

            ArrayList<Integer> identification = new ArrayList<Integer>();
            ArrayList<String> firstN = new ArrayList<String>();
            ArrayList<String> lastN = new ArrayList<String>();
            ArrayList<String> dateofB = new ArrayList<String>();
            ArrayList<String> SSNumber = new ArrayList<String>();
            ArrayList<String> addr = new ArrayList<String>();
            ArrayList<String> insuranceNa = new ArrayList<String>();
            ArrayList<String> insuranceNum = new ArrayList<String>();
            ArrayList<String> dateofSer = new ArrayList<String>();
            ArrayList<String> cond = new ArrayList<String>();
            ArrayList<String> treat = new ArrayList<String>();

            result = new Intent(this, Result.class);

            if(temp.getCount() > 0 ){

                if(temp.moveToFirst()){

                    do{

                        //this was a problem because I was passing the lines individually
                        //which overwrote the lines for duplicate patients in the Results Activity.
                        /*result.putExtra("id", temp.getInt(temp.getColumnIndex("_id")));
                        result.putExtra("firstname", temp.getString(temp.getColumnIndex("firstname")));
                        result.putExtra("lastname", temp.getString(temp.getColumnIndex("lastname")));
                        result.putExtra("dob", temp.getString(temp.getColumnIndex("dob")));
                        result.putExtra("ssn", temp.getString(temp.getColumnIndex("ssn")));
                        result.putExtra("address", temp.getString(temp.getColumnIndex("address")));
                        result.putExtra("insurancename", temp.getString(temp.getColumnIndex("insurancename")));
                        result.putExtra("insurancenumber", temp.getString(temp.getColumnIndex("insurancenumber")));
                        result.putExtra("dateofservice", temp.getString(temp.getColumnIndex("dateofservice")));
                        result.putExtra("condition", temp.getString(temp.getColumnIndex("condition")));
                        result.putExtra("treatment", temp.getString(temp.getColumnIndex("treatment")));*/

                        //add lines to the arraylists
                        identification.add(temp.getInt(temp.getColumnIndex("_id")));
                        firstN.add(temp.getString(temp.getColumnIndex("firstname")));
                        lastN.add(temp.getString(temp.getColumnIndex("lastname")));
                        dateofB.add(temp.getString(temp.getColumnIndex("dob")));
                        SSNumber.add(temp.getString(temp.getColumnIndex("ssn")));
                        addr.add(temp.getString(temp.getColumnIndex("address")));
                        insuranceNa.add(temp.getString(temp.getColumnIndex("insurancename")));
                        insuranceNum.add(temp.getString(temp.getColumnIndex("insurancenumber")));
                        dateofSer.add(temp.getString(temp.getColumnIndex("dateofservice")));
                        cond.add(temp.getString(temp.getColumnIndex("condition")));
                        treat.add(temp.getString(temp.getColumnIndex("treatment")));

                    }while(temp.moveToNext());
                }

                //pass the arraylists to Result Activity
                result.putIntegerArrayListExtra("id", identification);
                result.putStringArrayListExtra("firstname", firstN);
                result.putStringArrayListExtra("lastname", lastN);
                result.putStringArrayListExtra("dob", dateofB);
                result.putStringArrayListExtra("ssn", SSNumber);
                result.putStringArrayListExtra("address", addr);
                result.putStringArrayListExtra("insurancename", insuranceNa);
                result.putStringArrayListExtra("insurancenumber", insuranceNum);
                result.putStringArrayListExtra("dateofservice", dateofSer);
                result.putStringArrayListExtra("condition", cond);
                result.putStringArrayListExtra("treatment", treat);

                startActivity(result);
            }
            else{
                Toast.makeText(this, "Patient Not Found!", Toast.LENGTH_LONG).show();
            }
        }

        //search by firstname and dob.
        else if(!first.isEmpty() && last.isEmpty() && !dob.isEmpty() && ssn.isEmpty()){

            Cursor temp = patientsDB.query(TABLE_NAME, null, "firstname=? and dob =?",
                    new String[]{first, dob}, null, null, null);

            ArrayList<Integer> identification = new ArrayList<Integer>();
            ArrayList<String> firstN = new ArrayList<String>();
            ArrayList<String> lastN = new ArrayList<String>();
            ArrayList<String> dateofB = new ArrayList<String>();
            ArrayList<String> SSNumber = new ArrayList<String>();
            ArrayList<String> addr = new ArrayList<String>();
            ArrayList<String> insuranceNa = new ArrayList<String>();
            ArrayList<String> insuranceNum = new ArrayList<String>();
            ArrayList<String> dateofSer = new ArrayList<String>();
            ArrayList<String> cond = new ArrayList<String>();
            ArrayList<String> treat = new ArrayList<String>();

            result = new Intent(this, Result.class);

            if(temp.getCount() > 0 ){

                if(temp.moveToFirst()){

                    do{

                        //this was a problem because I was passing the lines individually
                        //which overwrote the lines for duplicate patients in the Results Activity.
                        /*result.putExtra("id", temp.getInt(temp.getColumnIndex("_id")));
                        result.putExtra("firstname", temp.getString(temp.getColumnIndex("firstname")));
                        result.putExtra("lastname", temp.getString(temp.getColumnIndex("lastname")));
                        result.putExtra("dob", temp.getString(temp.getColumnIndex("dob")));
                        result.putExtra("ssn", temp.getString(temp.getColumnIndex("ssn")));
                        result.putExtra("address", temp.getString(temp.getColumnIndex("address")));
                        result.putExtra("insurancename", temp.getString(temp.getColumnIndex("insurancename")));
                        result.putExtra("insurancenumber", temp.getString(temp.getColumnIndex("insurancenumber")));
                        result.putExtra("dateofservice", temp.getString(temp.getColumnIndex("dateofservice")));
                        result.putExtra("condition", temp.getString(temp.getColumnIndex("condition")));
                        result.putExtra("treatment", temp.getString(temp.getColumnIndex("treatment")));*/

                        //add lines to the arraylists
                        identification.add(temp.getInt(temp.getColumnIndex("_id")));
                        firstN.add(temp.getString(temp.getColumnIndex("firstname")));
                        lastN.add(temp.getString(temp.getColumnIndex("lastname")));
                        dateofB.add(temp.getString(temp.getColumnIndex("dob")));
                        SSNumber.add(temp.getString(temp.getColumnIndex("ssn")));
                        addr.add(temp.getString(temp.getColumnIndex("address")));
                        insuranceNa.add(temp.getString(temp.getColumnIndex("insurancename")));
                        insuranceNum.add(temp.getString(temp.getColumnIndex("insurancenumber")));
                        dateofSer.add(temp.getString(temp.getColumnIndex("dateofservice")));
                        cond.add(temp.getString(temp.getColumnIndex("condition")));
                        treat.add(temp.getString(temp.getColumnIndex("treatment")));

                    }while(temp.moveToNext());
                }

                //pass the arraylists to Result Activity
                result.putIntegerArrayListExtra("id", identification);
                result.putStringArrayListExtra("firstname", firstN);
                result.putStringArrayListExtra("lastname", lastN);
                result.putStringArrayListExtra("dob", dateofB);
                result.putStringArrayListExtra("ssn", SSNumber);
                result.putStringArrayListExtra("address", addr);
                result.putStringArrayListExtra("insurancename", insuranceNa);
                result.putStringArrayListExtra("insurancenumber", insuranceNum);
                result.putStringArrayListExtra("dateofservice", dateofSer);
                result.putStringArrayListExtra("condition", cond);
                result.putStringArrayListExtra("treatment", treat);

                startActivity(result);
            }
            else{
                Toast.makeText(this, "Patient Not Found!", Toast.LENGTH_LONG).show();
            }
        }

        //search by lastname and dob.
        else if(first.isEmpty() && !last.isEmpty() && !dob.isEmpty() && ssn.isEmpty()){

            Cursor temp = patientsDB.query(TABLE_NAME, null, "lastname=? and dob=?",
                    new String[]{last, dob}, null, null, null);

            ArrayList<Integer> identification = new ArrayList<Integer>();
            ArrayList<String> firstN = new ArrayList<String>();
            ArrayList<String> lastN = new ArrayList<String>();
            ArrayList<String> dateofB = new ArrayList<String>();
            ArrayList<String> SSNumber = new ArrayList<String>();
            ArrayList<String> addr = new ArrayList<String>();
            ArrayList<String> insuranceNa = new ArrayList<String>();
            ArrayList<String> insuranceNum = new ArrayList<String>();
            ArrayList<String> dateofSer = new ArrayList<String>();
            ArrayList<String> cond = new ArrayList<String>();
            ArrayList<String> treat = new ArrayList<String>();

            result = new Intent(this, Result.class);

            if(temp.getCount() > 0 ){

                if(temp.moveToFirst()){

                    do{

                        //this was a problem because I was passing the lines individually
                        //which overwrote the lines for duplicate patients in the Results Activity.
                        /*result.putExtra("id", temp.getInt(temp.getColumnIndex("_id")));
                        result.putExtra("firstname", temp.getString(temp.getColumnIndex("firstname")));
                        result.putExtra("lastname", temp.getString(temp.getColumnIndex("lastname")));
                        result.putExtra("dob", temp.getString(temp.getColumnIndex("dob")));
                        result.putExtra("ssn", temp.getString(temp.getColumnIndex("ssn")));
                        result.putExtra("address", temp.getString(temp.getColumnIndex("address")));
                        result.putExtra("insurancename", temp.getString(temp.getColumnIndex("insurancename")));
                        result.putExtra("insurancenumber", temp.getString(temp.getColumnIndex("insurancenumber")));
                        result.putExtra("dateofservice", temp.getString(temp.getColumnIndex("dateofservice")));
                        result.putExtra("condition", temp.getString(temp.getColumnIndex("condition")));
                        result.putExtra("treatment", temp.getString(temp.getColumnIndex("treatment")));*/

                        //add lines to the arraylists
                        identification.add(temp.getInt(temp.getColumnIndex("_id")));
                        firstN.add(temp.getString(temp.getColumnIndex("firstname")));
                        lastN.add(temp.getString(temp.getColumnIndex("lastname")));
                        dateofB.add(temp.getString(temp.getColumnIndex("dob")));
                        SSNumber.add(temp.getString(temp.getColumnIndex("ssn")));
                        addr.add(temp.getString(temp.getColumnIndex("address")));
                        insuranceNa.add(temp.getString(temp.getColumnIndex("insurancename")));
                        insuranceNum.add(temp.getString(temp.getColumnIndex("insurancenumber")));
                        dateofSer.add(temp.getString(temp.getColumnIndex("dateofservice")));
                        cond.add(temp.getString(temp.getColumnIndex("condition")));
                        treat.add(temp.getString(temp.getColumnIndex("treatment")));

                    }while(temp.moveToNext());
                }

                //pass the arraylists to Result Activity
                result.putIntegerArrayListExtra("id", identification);
                result.putStringArrayListExtra("firstname", firstN);
                result.putStringArrayListExtra("lastname", lastN);
                result.putStringArrayListExtra("dob", dateofB);
                result.putStringArrayListExtra("ssn", SSNumber);
                result.putStringArrayListExtra("address", addr);
                result.putStringArrayListExtra("insurancename", insuranceNa);
                result.putStringArrayListExtra("insurancenumber", insuranceNum);
                result.putStringArrayListExtra("dateofservice", dateofSer);
                result.putStringArrayListExtra("condition", cond);
                result.putStringArrayListExtra("treatment", treat);

                startActivity(result);
            }
            else{
                Toast.makeText(this, "Patient Not Found!", Toast.LENGTH_LONG).show();
            }
        }

        //search by firstname, lastname and dob.
        else if(!first.isEmpty() && !last.isEmpty() && !dob.isEmpty() && ssn.isEmpty()){

            Cursor temp = patientsDB.query(TABLE_NAME, null, "firstname=? and lastname =? and dob=?",
                    new String[]{first, last, dob}, null, null, null);

            ArrayList<Integer> identification = new ArrayList<Integer>();
            ArrayList<String> firstN = new ArrayList<String>();
            ArrayList<String> lastN = new ArrayList<String>();
            ArrayList<String> dateofB = new ArrayList<String>();
            ArrayList<String> SSNumber = new ArrayList<String>();
            ArrayList<String> addr = new ArrayList<String>();
            ArrayList<String> insuranceNa = new ArrayList<String>();
            ArrayList<String> insuranceNum = new ArrayList<String>();
            ArrayList<String> dateofSer = new ArrayList<String>();
            ArrayList<String> cond = new ArrayList<String>();
            ArrayList<String> treat = new ArrayList<String>();

            result = new Intent(this, Result.class);

            if(temp.getCount() > 0 ){

                if(temp.moveToFirst()){

                    do{

                        //this was a problem because I was passing the lines individually
                        //which overwrote the lines for duplicate patients in the Results Activity.
                        /*result.putExtra("id", temp.getInt(temp.getColumnIndex("_id")));
                        result.putExtra("firstname", temp.getString(temp.getColumnIndex("firstname")));
                        result.putExtra("lastname", temp.getString(temp.getColumnIndex("lastname")));
                        result.putExtra("dob", temp.getString(temp.getColumnIndex("dob")));
                        result.putExtra("ssn", temp.getString(temp.getColumnIndex("ssn")));
                        result.putExtra("address", temp.getString(temp.getColumnIndex("address")));
                        result.putExtra("insurancename", temp.getString(temp.getColumnIndex("insurancename")));
                        result.putExtra("insurancenumber", temp.getString(temp.getColumnIndex("insurancenumber")));
                        result.putExtra("dateofservice", temp.getString(temp.getColumnIndex("dateofservice")));
                        result.putExtra("condition", temp.getString(temp.getColumnIndex("condition")));
                        result.putExtra("treatment", temp.getString(temp.getColumnIndex("treatment")));*/

                        //add lines to the arraylists
                        identification.add(temp.getInt(temp.getColumnIndex("_id")));
                        firstN.add(temp.getString(temp.getColumnIndex("firstname")));
                        lastN.add(temp.getString(temp.getColumnIndex("lastname")));
                        dateofB.add(temp.getString(temp.getColumnIndex("dob")));
                        SSNumber.add(temp.getString(temp.getColumnIndex("ssn")));
                        addr.add(temp.getString(temp.getColumnIndex("address")));
                        insuranceNa.add(temp.getString(temp.getColumnIndex("insurancename")));
                        insuranceNum.add(temp.getString(temp.getColumnIndex("insurancenumber")));
                        dateofSer.add(temp.getString(temp.getColumnIndex("dateofservice")));
                        cond.add(temp.getString(temp.getColumnIndex("condition")));
                        treat.add(temp.getString(temp.getColumnIndex("treatment")));

                    }while(temp.moveToNext());
                }

                //pass the arraylists to Result Activity
                result.putIntegerArrayListExtra("id", identification);
                result.putStringArrayListExtra("firstname", firstN);
                result.putStringArrayListExtra("lastname", lastN);
                result.putStringArrayListExtra("dob", dateofB);
                result.putStringArrayListExtra("ssn", SSNumber);
                result.putStringArrayListExtra("address", addr);
                result.putStringArrayListExtra("insurancename", insuranceNa);
                result.putStringArrayListExtra("insurancenumber", insuranceNum);
                result.putStringArrayListExtra("dateofservice", dateofSer);
                result.putStringArrayListExtra("condition", cond);
                result.putStringArrayListExtra("treatment", treat);
                startActivity(result);
            }
            else{
                Toast.makeText(this, "Patient Not Found!", Toast.LENGTH_LONG).show();
            }
        }

        //search by firstname, lastname, dob, and ssn
        else if(!first.isEmpty() && !last.isEmpty() && !dob.isEmpty() && !ssn.isEmpty()) {

            Cursor temp = patientsDB.query(TABLE_NAME, null, "firstname=? and lastname=? and dob=? and ssn=?",
                    new String[]{first, last, dob, ssn}, null, null, null);

            ArrayList<Integer> identification = new ArrayList<Integer>();
            ArrayList<String> firstN = new ArrayList<String>();
            ArrayList<String> lastN = new ArrayList<String>();
            ArrayList<String> dateofB = new ArrayList<String>();
            ArrayList<String> SSNumber = new ArrayList<String>();
            ArrayList<String> addr = new ArrayList<String>();
            ArrayList<String> insuranceNa = new ArrayList<String>();
            ArrayList<String> insuranceNum = new ArrayList<String>();
            ArrayList<String> dateofSer = new ArrayList<String>();
            ArrayList<String> cond = new ArrayList<String>();
            ArrayList<String> treat = new ArrayList<String>();

            result = new Intent(this, Result.class);

            if(temp.getCount() > 0 ){

                if(temp.moveToFirst()){

                    do{

                        //this was a problem because I was passing the lines individually
                        //which overwrote the lines for duplicate patients in the Results Activity.
                        /*result.putExtra("id", temp.getInt(temp.getColumnIndex("_id")));
                        result.putExtra("firstname", temp.getString(temp.getColumnIndex("firstname")));
                        result.putExtra("lastname", temp.getString(temp.getColumnIndex("lastname")));
                        result.putExtra("dob", temp.getString(temp.getColumnIndex("dob")));
                        result.putExtra("ssn", temp.getString(temp.getColumnIndex("ssn")));
                        result.putExtra("address", temp.getString(temp.getColumnIndex("address")));
                        result.putExtra("insurancename", temp.getString(temp.getColumnIndex("insurancename")));
                        result.putExtra("insurancenumber", temp.getString(temp.getColumnIndex("insurancenumber")));
                        result.putExtra("dateofservice", temp.getString(temp.getColumnIndex("dateofservice")));
                        result.putExtra("condition", temp.getString(temp.getColumnIndex("condition")));
                        result.putExtra("treatment", temp.getString(temp.getColumnIndex("treatment")));*/

                        //add lines to the arraylists
                        identification.add(temp.getInt(temp.getColumnIndex("_id")));
                        firstN.add(temp.getString(temp.getColumnIndex("firstname")));
                        lastN.add(temp.getString(temp.getColumnIndex("lastname")));
                        dateofB.add(temp.getString(temp.getColumnIndex("dob")));
                        SSNumber.add(temp.getString(temp.getColumnIndex("ssn")));
                        addr.add(temp.getString(temp.getColumnIndex("address")));
                        insuranceNa.add(temp.getString(temp.getColumnIndex("insurancename")));
                        insuranceNum.add(temp.getString(temp.getColumnIndex("insurancenumber")));
                        dateofSer.add(temp.getString(temp.getColumnIndex("dateofservice")));
                        cond.add(temp.getString(temp.getColumnIndex("condition")));
                        treat.add(temp.getString(temp.getColumnIndex("treatment")));

                    }while(temp.moveToNext());
                }

                //pass the arraylists to Result Activity
                result.putIntegerArrayListExtra("id", identification);
                result.putStringArrayListExtra("firstname", firstN);
                result.putStringArrayListExtra("lastname", lastN);
                result.putStringArrayListExtra("dob", dateofB);
                result.putStringArrayListExtra("ssn", SSNumber);
                result.putStringArrayListExtra("address", addr);
                result.putStringArrayListExtra("insurancename", insuranceNa);
                result.putStringArrayListExtra("insurancenumber", insuranceNum);
                result.putStringArrayListExtra("dateofservice", dateofSer);
                result.putStringArrayListExtra("condition", cond);
                result.putStringArrayListExtra("treatment", treat);

                startActivity(result);
            }
            else {
                Toast.makeText(this, "Patient Not Found!", Toast.LENGTH_LONG).show();
            }
        }

        else{   //field combination incorrect.

            Toast.makeText(this, "Invalid Search!", Toast.LENGTH_LONG).show();
        }
    }
}
