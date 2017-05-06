package com.example.panggolowitski.clinicapplication;

import android.support.v7.app.AppCompatActivity;
import android.view.inputmethod.InputMethodManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;
import android.widget.Toast;

//Cursor query(String, String)   Checks for username and password combination
//void signIn(View view)         Calls query(), sets signUp form to empty
//void signUp(View view)         Sets signIn form to empty, makes signUp form visible
//void createAccount(View view)  Checks if form is filled in, puts user input into database

public class MainActivity extends AppCompatActivity {

    //views
    private EditText username;
    private EditText password;
    private Button signIn;
    private Button signUp;
    private EditText firstname;
    private EditText lastname;
    private EditText dateOfBirth;
    private EditText user;
    private EditText pass;
    private Button create;

    private Intent options;
    //hide keyboard
    InputMethodManager inputManager;

    //set up database
    private SQLiteDatabase myDatabase;
    public static final String DATABASE_NAME = "myaccounts";
    public static final String TABLE_NAME = "mytable";
    public static final String TABLE_CREATE = " CREATE TABLE IF NOT EXISTS " +
            TABLE_NAME + " (firstname TEXT NOT NULL, " +
            " lastname TEXT NOT NULL, " +
            " dob TEXT NOT NULL, " +
            " username TEXT NOT NULL, " +
            " password TEXT NOT NULL);";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        signIn = (Button) findViewById(R.id.signIn);
        signUp = (Button) findViewById(R.id.signUp);
        firstname = (EditText) findViewById(R.id.firstname);
        lastname = (EditText) findViewById(R.id.lastname);
        dateOfBirth = (EditText) findViewById(R.id.dob);
        user = (EditText) findViewById(R.id.user);
        pass = (EditText) findViewById(R.id.pw);
        create = (Button) findViewById(R.id.createAccount);

        myDatabase = openOrCreateDatabase(DATABASE_NAME, Context.MODE_PRIVATE, null);
        myDatabase.execSQL(TABLE_CREATE);

        inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
    }

    //checks if username and password combo is in the database.
    public Cursor query(String user, String pw) {
        //look for a pair of username and password.
        Cursor temp = myDatabase.query(TABLE_NAME, null, "username=? and password=?",
                new String[]{user, pw}, null, null, null);

        return temp;
    }

    //Button click
    //Empty new user info fields.
    //checks if username and password combo matches an entry in the database.
    //if a match is found, switch to Options activity.
    public void signIn(View view){

        //clear all sign up editTexts
        firstname.setText(null);
        lastname.setText(null);
        dateOfBirth.setText(null);
        user.setText(null);
        pass.setText(null);

        //render them invsible
        firstname.setVisibility(View.INVISIBLE);
        lastname.setVisibility(View.INVISIBLE);
        dateOfBirth.setVisibility(View.INVISIBLE);
        user.setVisibility(View.INVISIBLE);
        pass.setVisibility(View.INVISIBLE);
        create.setVisibility(View.INVISIBLE);

        //check if username and password matches database entry.
        Cursor temp = query(username.getText().toString(), password.getText().toString());

        //if input does not match the entries in the database
        if (temp.getCount() == 0) {

            Toast.makeText(this, "No credentials not found!!", Toast.LENGTH_LONG).show();
            //if entry does match
        } else if (temp.getCount() == 1){
            //switch to next intent
            options = new Intent(MainActivity.this, Options.class);
            startActivity(options);
        }

        //clear entries on the user and passowrd editTexts
        username.setText(null);
        password.setText(null);

        //hide keyboard
        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
    }

    //Empty username and password fields.
    //show new user fields.
    public void signUp(View view){

        //clear entries on the user and passowrd editTexts
        username.setText(null);
        password.setText(null);

        //clear all sign up editTexts
        firstname.setText(null);
        lastname.setText(null);
        dateOfBirth.setText(null);
        user.setText(null);
        pass.setText(null);

        //show registration form
        firstname.setVisibility(View.VISIBLE);
        lastname.setVisibility(View.VISIBLE);
        dateOfBirth.setVisibility(View.VISIBLE);
        user.setVisibility(View.VISIBLE);
        pass.setVisibility(View.VISIBLE);
        create.setVisibility(View.VISIBLE);

        //hide keyboard
        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
    }

    //check if all fields are filled in.
    //if they are input entries into the database
    public void createAccount(View view){

        //make sure all boxes are filled out.
        if(firstname.getText().toString().trim().length() == 0 ||
                lastname.getText().toString().trim().length() == 0 ||
                dateOfBirth.getText().toString().trim().length() == 0 ||
                user.getText().toString().trim().length() == 0 ||
                pass.getText().toString().trim().length() == 0){

            Toast.makeText(this, "Please fill the required form", Toast.LENGTH_LONG).show();
        }

        else{

            Cursor temp = myDatabase.query(TABLE_NAME, null, "username=?",
                    new String[]{user.getText().toString()}, null, null, null);

            if(temp.getCount() > 0){
                Toast.makeText(this, "Username already exists!!", Toast.LENGTH_LONG).show();
            }

            else {

                //insert entry into the database
                myDatabase.execSQL("INSERT INTO mytable(firstname, lastname, dob, username, password) VALUES" +
                        "(?, ?, ?, ?, ?)", new String[]{firstname.getText().toString(),
                        lastname.getText().toString(), dateOfBirth.getText().toString(),
                        user.getText().toString(), pass.getText().toString()});

                Toast.makeText(this, "User Account Created!!", Toast.LENGTH_LONG).show();

                //clear fields
                firstname.setText(null);
                lastname.setText(null);
                dateOfBirth.setText(null);
                user.setText(null);
                pass.setText(null);

                //clear username and password forms
                username.setText(null);
                password.setText(null);
            }
        }

        //hide keyboard
        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
    }
}
