package com.GE20070469.apdassesment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.NotificationManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private EditText fname, lname, email, dob, address;
    private RadioGroup genderRG;
    private Button signup;
    private String temp, gender;
    private int radioID;
    private int position = 0;
    private RadioButton genderSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialiseVars();

        fname.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) { if (!hasFocus) { hideKeyboard(v); } }
        });
        lname.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) { if (!hasFocus) { hideKeyboard(v); } }
        });
        email.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) { if (!hasFocus) { hideKeyboard(v); } }
        });
        address.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) { if (!hasFocus) { hideKeyboard(v); } }
        });

        genderRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {// When a certain button is selected
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                radioID = genderRG.getCheckedRadioButtonId();// Getting the RadioButton checked ID
                genderSelected =  findViewById(radioID);// Finding the button
                gender = genderSelected.getText().toString();// Set the (global) string for later usage
            }
        });


        signup.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                RegFragment newFragment = new RegFragment();// Create fragment and give it an argument specifying the article it should show
                Bundle args = new Bundle();
                args.putInt(RegFragment.ARG_POSITION, position);
                newFragment.setArguments(args);
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.layout.activity_main, newFragment);// Replace whatever is in the fragment_container view with this fragment,
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
    }

    public void initialiseVars() {


        fname = findViewById(R.id.fname);
        lname = findViewById(R.id.lname);
        email = findViewById(R.id.email);
        dob = findViewById(R.id.dob);
        address = findViewById(R.id.address);
        signup = findViewById(R.id.submit);
        genderRG = findViewById(R.id.genderRG);
    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public boolean checkString(String str) {
        if (str == null) {
            return false;
        } else return str.matches("[A-Za-z]");
    }

    public static final Pattern VALID_EMAIL_PATTERN = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    public static final Pattern VALID_PASSWORD_PATTERN = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\\\S+$).{4,}$", Pattern.CASE_INSENSITIVE);
    public static final Pattern VALID_STRING_PATTERN = Pattern.compile("[A-Za-z]", Pattern.CASE_INSENSITIVE);

    // SimpleDateFormat class that's built to do this. More heavyweight, but more comprehensive.
    public static boolean checkInput(Pattern PATTERN, String str) {// This will check for either password or email, depending on the Pattern
        Matcher matcher = PATTERN.matcher(str);
        return matcher.find();
    }

}
