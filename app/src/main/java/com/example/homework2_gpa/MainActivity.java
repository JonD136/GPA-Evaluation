package com.example.homework2_gpa;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.method.DigitsKeyListener;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final TextView textName = (EditText) findViewById(R.id.editTextName);
        final TextView textGPA = (EditText) findViewById(R.id.editTextGPA);

        final TextView outputTextView = (TextView) findViewById(R.id.outputTextView);
        outputTextView.setVisibility(View.INVISIBLE);

        //making it have 1 number before the decimal and taking in 2 decimal points after the decimal
        // use this website to help me figure it out: https://stackoverflow.com/questions/48753337/android-edittext-two-decimal-places

        textGPA.setFilters(new InputFilter[] {
                new DigitsKeyListener(Boolean.FALSE, Boolean.TRUE) {
                    int beforeDecimal = 1, afterDecimal = 2;

                    @Override
                    public CharSequence filter(CharSequence source, int start, int end,
                                               Spanned dest, int dstart, int dend) {
                        String temp = textGPA.getText() + source.toString();

                        if (temp.equals(".")) {
                            return "0.";
                        }
                        else if (temp.toString().indexOf(".") == -1) {
                            // no decimal point placed yet
                            if (temp.length() > beforeDecimal) {
                                return "";
                            }
                        } else {
                            temp = temp.substring(temp.indexOf(".") + 1);
                            if (temp.length() > afterDecimal) {
                                return "";
                            }
                        }

                        return super.filter(source, start, end, dest, dstart, dend);
                    }
                }
        });
            //end of code for decimal

        final TextView opTextView = (TextView) findViewById(R.id.outputTextView);

        Button pressMe = (Button) findViewById(R.id.btnGo);





        // clicking button
        pressMe.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                outputTextView.setVisibility(View.VISIBLE);

                // taking the string and turning it to an integer
                double numGPA = Double.parseDouble(textGPA.getText().toString());

                if ( numGPA >= 3.00) {

                    String txtName = textName.getText().toString();
                    opTextView.setText("Congrats " + txtName + ", you will be considered for the job");


                } else {

                    String txtName = textName.getText().toString();
                    opTextView.setText("Sorry " + txtName + ", you will not be considered for the job");

                }
            }
        }
        );

    }
}