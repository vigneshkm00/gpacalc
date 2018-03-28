package com.example.vignesh.gpacalc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class showgpaActivity extends AppCompatActivity {
    Button b1;
    TextView t1;
    EditText e1;
    String file_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showgpa);
        b1 = (Button) findViewById(R.id.gpbtn);
        e1 = (EditText) findViewById(R.id.editText);
        t1 = (TextView) findViewById(R.id.textView6);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                file_name = e1.getText().toString();
                String mod = getIntent().getStringExtra("mode");
                if(mod.equals("GPA"))
                {
//                    file_name = file_name +"c";
                    t1.setText("SEM    GPA");
                }
                else
                {
                    file_name = file_name +"c";
                    t1.setText("SEM    CGPA");
                }
                try {
                    String output;
                    FileInputStream fileInputStream = openFileInput(file_name);
                    InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
                    BufferedReader bufferedReader =new BufferedReader(inputStreamReader);
                    StringBuffer stringBuffer = new StringBuffer();

                    while ((output=bufferedReader.readLine())!=null)
                    {
                        stringBuffer.append("\n"+output +"\n");
                    }
                    t1.append(stringBuffer.toString());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
