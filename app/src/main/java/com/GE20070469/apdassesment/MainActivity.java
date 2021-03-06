package com.GE20070469.apdassesment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private EditText fname, lname, email, address;
    private TextView dob;
    private RadioGroup genderRG;
    private RadioButton genderSelected;
    private Button signup;
    private String sFame, sLame, sEmail, sAddress, sDob, sGender;
    private int radioID, position = 0;
    private boolean isAdult = false;
    final Calendar myCalendar = Calendar.getInstance();
    public static final Pattern VALID_EMAIL_PATTERN = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    public static final Pattern VALID_ADDRESS_PATTERN = Pattern.compile("[-0-9A-Za-z.,/ ]+", Pattern.CASE_INSENSITIVE);
    public static final Pattern VALID_STRING_PATTERN = Pattern.compile("^[a-zA-Z]+$", Pattern.CASE_INSENSITIVE);
    private LinearLayout linearlayout;
    private RegFragment newFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        linearlayout = findViewById(R.id.linearContent);
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
                sGender = genderSelected.getText().toString();// Set the (global) string for later usage
            }
        });
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {// Allowing the to extract the chosen dates
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                if(getAge(year, monthOfYear, dayOfMonth) >= 18 && getAge(year, monthOfYear, dayOfMonth) <= 100) {
                    updateLabel();
                    isAdult = true;
                    Log.i("Notification", "The user is over 18 y/o");
                } else {
                    Log.i("Notification", "The user in not 18 " + getAge(year, monthOfYear, dayOfMonth));
                }
            }
        };

        dob.setOnClickListener(new View.OnClickListener() { // When user clicks to pick their DOB a calendar will pop up - asking for input
            @Override
            public void onClick(View v) {
                new DatePickerDialog(MainActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                retrieveData();
                if(!checkInput(VALID_STRING_PATTERN, sFame )) {
                    Toast.makeText(getApplicationContext(), "Only letters allowed", Toast.LENGTH_SHORT).show();
                    fname.setBackgroundColor( ContextCompat.getColor(getApplicationContext(), R.color.error) );
                } else {
                    fname.setBackgroundColor( ContextCompat.getColor(getApplicationContext(), R.color.good) );
                }
                if(!checkInput(VALID_STRING_PATTERN, sLame) ) {
                    Toast.makeText(getApplicationContext(), "Only letters allowed", Toast.LENGTH_SHORT).show();
                    lname.setBackgroundColor( ContextCompat.getColor(getApplicationContext(), R.color.error) );
                }else {
                    lname.setBackgroundColor( ContextCompat.getColor(getApplicationContext(), R.color.good) );
                }
                if (!checkInput(VALID_EMAIL_PATTERN, sEmail)) {
                    Toast.makeText(getApplicationContext(), "Please the following format: user@example.com", Toast.LENGTH_LONG).show();
                    email.setBackgroundColor( ContextCompat.getColor(getApplicationContext(), R.color.error) );
                } else {
                    email.setBackgroundColor( ContextCompat.getColor(getApplicationContext(), R.color.good) );
                }
                if (!checkInput(VALID_ADDRESS_PATTERN, sAddress)) {
                    Toast.makeText(getApplicationContext(), "Address is not correct", Toast.LENGTH_SHORT).show();
                    address.setBackgroundColor( ContextCompat.getColor(getApplicationContext(), R.color.error) );
                }else {
                    address.setBackgroundColor( ContextCompat.getColor(getApplicationContext(), R.color.good) );
                }
                if (!isAdult) {
                    Toast.makeText(getApplicationContext(), "You have to be over 18", Toast.LENGTH_SHORT).show();
                    dob.setBackgroundColor( ContextCompat.getColor(getApplicationContext(), R.color.error) );
                }else {
                    dob.setBackgroundColor( ContextCompat.getColor(getApplicationContext(), R.color.good) );
                }
                if (checkInput(VALID_STRING_PATTERN, sFame) && checkInput(VALID_STRING_PATTERN, sLame) && checkInput(VALID_EMAIL_PATTERN, sEmail) && checkInput(VALID_ADDRESS_PATTERN, sAddress) && isAdult) {
                    newFragment = new RegFragment();// Create fragment and give it an argument specifying the article it should show
                    Bundle args = new Bundle();
                    args.putInt(RegFragment.ARG_POSITION, position);
                    args.putString("fname", sFame);
                    args.putString("lname", sLame);
                    args.putString("email", sEmail);
                    args.putString("dob", sDob);
                    args.putString("gender", sGender);
                    args.putString("address", sAddress);
                    newFragment.setArguments(args);
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out);
                    transaction.replace(R.id.your_placeholder, newFragment);// Replace whatever is in the fragment_container view with this fragment,
                    transaction.addToBackStack(null);
                    transaction.commit();
                    Toast.makeText(getApplicationContext(), "Information correct, welcome", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Warning: Information provided is not correct", Toast.LENGTH_SHORT).show();
                }
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
    public void retrieveData() {
        sFame = fname.getText().toString();
        sLame = lname.getText().toString();
        sEmail = email.getText().toString();
        sAddress = address.getText().toString();
    }
    private void updateLabel() {   // Formatting the given date to UK format
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.UK);
        dob.setText(sdf.format(myCalendar.getTime()));
        sDob = dob.getText().toString();
    }
    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
    public static boolean checkInput(Pattern PATTERN, String str) {// This will check for either password or email, depending on the Pattern
        Matcher matcher = PATTERN.matcher(str);
        if (str == null) {
            return false;
        } else return matcher.find();
    }
    private int getAge(int year, int month, int day){
        Calendar dob = Calendar.getInstance();
        Calendar today = Calendar.getInstance();
        dob.set(year, month, day);
        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);
        if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)){
            age--;
        }
        return age;
    }
}
