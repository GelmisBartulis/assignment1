package com.GE20070469.apdassesment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


public class RegFragment extends Fragment implements View.OnClickListener{
    final static String ARG_POSITION = "position"; // Positioning for the fragments
    int mCurrentPosition = -1;
    private View myView;// Global for further uses
    private Context context; // Getting local context
    private LinearLayout ll;
    private String[] array;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (savedInstanceState != null)
            mCurrentPosition = savedInstanceState.getInt(ARG_POSITION);
        myView = inflater.inflate(R.layout.activity_main, container, false); // Initialising the view the right parameters
        context = myView.getContext(); // getting context from current view
        ll = myView.findViewById(R.id.linearContent);
        ll.setBackgroundColor(Color.DKGRAY);
        showData();

        assert array != null;
        createUI();
        return myView; // Inflate the layout for this fragment
    }

    public void showData () {
        assert getArguments() != null;
        array = new String[]{"First name: " + getArguments().getString("fname"), "Second name: " + getArguments().getString("lname"), "Email: " + getArguments().getString("email"), "Date of birth: " + getArguments().getString("dob"), "Address: " + getArguments().getString("address"), "Gender: " + getArguments().getString("gender")};
        for(int i = 0; i < array.length; i++) {
            Log.i("Data" + i, "" + array[i]);
        }
    }

    public void createUI() {
        ViewGroup.LayoutParams lparams = new ViewGroup.LayoutParams( ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        TextView fname = new TextView(context);
        TextView lname = new TextView(context);
        TextView email = new TextView(context);
        TextView address = new TextView(context);
        TextView gender = new TextView(context);
        TextView dob = new TextView(context);
        fname.setLayoutParams(lparams);
        lname.setLayoutParams(lparams);
        email.setLayoutParams(lparams);
        address.setLayoutParams(lparams);
        gender.setLayoutParams(lparams);
        dob.setLayoutParams(lparams);
        fname.setText("First name: " );
        lname.setText("Second name: " + array[1]);
        email.setText("Email: " + array[2]);
        dob.setText("Date of birth: " + array[3]);
        address.setText("Address: " + array[4]);
        gender.setText("Gender: " + array[5]);
        fname.setTextColor(Color.RED);
        lname.setTextColor(Color.RED);
        email.setTextColor(Color.RED);
        dob.setTextColor(Color.RED);
        address.setTextColor(Color.RED);
        gender.setTextColor(Color.RED);
        ll.addView(fname);
        ll.addView(lname);
        ll.addView(email);
        ll.addView(dob);
        ll.addView(address);
        ll.addView(gender);

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
