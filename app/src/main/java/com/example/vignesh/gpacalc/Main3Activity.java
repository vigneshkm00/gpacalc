package com.example.vignesh.gpacalc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

public class Main3Activity extends AppCompatActivity {
    TextView txt1,txt2;
    float s;
    Button b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);;
        txt1 = (TextView) findViewById(R.id.textView4);
        String dep = getIntent().getStringExtra("gpa");
        txt1.setText(dep);
    }
}
