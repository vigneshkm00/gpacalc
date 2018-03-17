package com.example.vignesh.gpacalc;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Main3Activity extends AppCompatActivity implements dialogbox.dialoglistener {

    float s;
    Button savbt,srebt,show;
    int credit;
    float sum,cgpa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String mode = getIntent().getStringExtra("mode");
       // if(mode.equals("GPA"))

           TextView txt1=(TextView) findViewById(R.id.textView5);
//            txt1.setVisibility(View.VISIBLE);
            setContentView(R.layout.activity_main3);;
           TextView txt2 = (TextView) findViewById(R.id.textView4);
            txt2.setVisibility(View.VISIBLE);
            String dep = getIntent().getStringExtra("gpa");
            txt2.setText(dep);

        srebt = (Button) findViewById(R.id.sharebt);
       srebt.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent si = new Intent();
               si.setAction(Intent.ACTION_SEND);
               si.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
               si.setType("text/plain");
               startActivity(Intent.createChooser(si,"Send this message to"));
           }
       });
       savbt = (Button) findViewById(R.id.savebt);
       savbt.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               openDialog();
           }
       });
      /*  else
        {
          TextView  txt1=(TextView) findViewById(R.id.textView8);
            txt1.setVisibility(View.VISIBLE);
            setContentView(R.layout.activity_main3);;
          TextView  txt2 = (TextView) findViewById(R.id.textView9);
            txt2.setVisibility(View.VISIBLE);
            String su = getIntent().getStringExtra("sum");
            String  cr = getIntent().getStringExtra("credit");
            credit=Integer.parseInt(cr);
            sum=Float.parseFloat(su);
            cgpa=(Float)sum/credit;
            String cgpa1 = String.format("%f",cgpa).toString();
            txt2.setText(cgpa1);

        }*/
        show = (Button) findViewById(R.id.button2);
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences prefer = getSharedPreferences("411714",Context.MODE_PRIVATE);
                String na = prefer.getString("name","").toString();
                String rn = prefer.getString("regno","").toString();
                String g = prefer.getString("gpa","").toString();
                Toast.makeText(getApplicationContext(),na+" "+rn+" "+g,Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void openDialog()
    {
    dialogbox dialogbox = new dialogbox();
    dialogbox.show(getSupportFragmentManager(),"user details");
    }

    @Override
    public void applytext(String name, String regno) {
        SharedPreferences prefer = getSharedPreferences(regno,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefer.edit();
        String dep = getIntent().getStringExtra("gpa");
        String sem = getIntent().getStringExtra("sem");
        editor.putString("name",name.toString());
        editor.putString("regno",regno.toString());
        editor.putString("gpa",dep.toString());
        editor.putString("sem",sem.toString());
        editor.apply();
        Toast.makeText(getApplicationContext(),"save successfull",Toast.LENGTH_SHORT).show();
    }
}
