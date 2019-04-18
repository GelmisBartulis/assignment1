package com.GE20070469.apdassesment;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class RegFragment extends Fragment implements View.OnClickListener{
    final static String ARG_POSITION = "position"; // Positioning for the fragments
    int mCurrentPosition = -1;
    private View myView;// Global for further uses
    private Context context; // Getting local context
    private LinearLayout linearLay;
    private String[] array;
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (savedInstanceState != null)
            mCurrentPosition = savedInstanceState.getInt(ARG_POSITION);
        myView = inflater.inflate(R.layout.activity_main, container, false); // Initialising the view the right parameters
        context = myView.getContext(); // getting context from current view
        linearLay = myView.findViewById(R.id.your_placeholder);
        linearLay.setBackground(ContextCompat.getDrawable(context, R.drawable.round_light_full));
        linearLay.setPadding(60, 60, 60, 20);
        showData();
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
        LinearLayout.LayoutParams imageParams = new LinearLayout.LayoutParams( 100, 100);
        imageParams.setLayoutDirection(Gravity.END|Gravity.CENTER_VERTICAL);

        Button ib = new Button(context);
        TextView title = new TextView(context);
        TextView fname = new TextView(context);
        TextView lname = new TextView(context);
        TextView email = new TextView(context);
        TextView address = new TextView(context);
        TextView gender = new TextView(context);
        TextView dob = new TextView(context);

        ib.setLayoutParams(imageParams);
        title.setLayoutParams(lparams);
        fname.setLayoutParams(lparams);
        lname.setLayoutParams(lparams);
        email.setLayoutParams(lparams);
        address.setLayoutParams(lparams);
        gender.setLayoutParams(lparams);
        dob.setLayoutParams(lparams);

        ib.setBackgroundResource(R.drawable.close);
        ib.setOnClickListener(this);

        title.setText(R.string.details_title);
        fname.setText(array[0] );
        lname.setText(array[1]);
        email.setText(array[2]);
        dob.setText(array[3]);
        address.setText(array[4]);
        gender.setText(array[5]);
        ContextCompat.getColor(context, R.color.error);

        fname.setTextColor(ContextCompat.getColor(context, R.color.error));
        lname.setTextColor( ContextCompat.getColor(context, R.color.error));
        email.setTextColor(ContextCompat.getColor(context, R.color.error));
        dob.setTextColor(ContextCompat.getColor(context, R.color.error));
        address.setTextColor(ContextCompat.getColor(context, R.color.error));
        gender.setTextColor(ContextCompat.getColor(context, R.color.error));

        title.setTextSize(40);
        title.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        title.setTextAppearance(R.style.Widget_AppCompat_Light_AutoCompleteTextView);
        title.setPadding(0, 20, 0, 40);
        Typeface boldTypeface = Typeface.defaultFromStyle(Typeface.BOLD);
        title.setTypeface(boldTypeface);

        fname.setTextSize(20);
        lname.setTextSize(20);
        email.setTextSize(20);
        dob.setTextSize(20);
        address.setTextSize(20);
        gender.setTextSize(20);

        linearLay.addView(ib);
        linearLay.addView(title);
        linearLay.addView(fname);
        linearLay.addView(lname);
        linearLay.addView(email);
        linearLay.addView(dob);
        linearLay.addView(address);
        linearLay.addView(gender);
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
    public void onClick(View v) { getActivity().onBackPressed(); }
}
