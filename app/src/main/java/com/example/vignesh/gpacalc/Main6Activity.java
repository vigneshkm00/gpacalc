package com.example.vignesh.gpacalc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class Main6Activity extends AppCompatActivity {
TextView t1,t2,t3;
int credit;
float sum,cgpa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        t1 = (TextView)findViewById(R.id.textView6);
        t2 = (TextView) findViewById(R.id.textView7);
        t3 = (TextView) findViewById(R.id.textView8);
        String su = getIntent().getStringExtra("sum");
        String  cr = getIntent().getStringExtra("credit");
        credit=Integer.parseInt(cr);
        sum=Float.parseFloat(su);
        cgpa=(Float)sum/credit;
        String cgpa1 = String.format("%f",cgpa).toString();
        t3.setText(cgpa1);
        t1.setText(su);
        t2.setText(cr);
    }
}
