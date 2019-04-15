package com.GE20070469.apdassesment;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
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


public class RegFragment extends Fragment implements View.OnClickListener{
    final static String ARG_POSITION = "position"; // Positioning for the fragments
    int mCurrentPosition = -1;
    private View myView;// Global for further uses
    private EditText fname, lname, email, pass1, pass2; // Adding all the UI components to the class
    private TextView dob ; // Adding all the UI components to the class
    private Button submit;// Submitting the registration form
    private RadioGroup rg ;// Radio buttons for gender selection
    private RadioButton genderSelected;// Radio buttons for gender selection
    private String gender;// For internal variable processing
    private int radioID;// For internal variable processing
    private Context context; // Getting local context
    private String sName, sLame, sDob, sEmail, sPass1, sPass2; // For local processes
    final Calendar myCalendar = Calendar.getInstance();// For internal variable processing
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (savedInstanceState != null)
            mCurrentPosition = savedInstanceState.getInt(ARG_POSITION);
        myView = inflater.inflate(R.layout.fragment_reg, container, false); // Initialising the view the right parameters
        context = myView.getContext(); // getting context from current view

        return myView; // Inflate the layout for this fragment
    }






    @Override
    public void onStart() {
        super.onStart();
        Bundle args = getArguments();
        if (args != null) { // Set article based on argument passed in
            updateArticleView(args.getInt(ARG_POSITION));
        } else if (mCurrentPosition != -1) {// Set article based on saved instance state defined during onCreateView
            updateArticleView(mCurrentPosition);
        }
    }
    public void updateArticleView(int position) { mCurrentPosition = position; }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(ARG_POSITION, mCurrentPosition);// Save the current article selection in case we need to recreate the fragment
    }
    @Override
    public void onClick(View v) {  }
}
