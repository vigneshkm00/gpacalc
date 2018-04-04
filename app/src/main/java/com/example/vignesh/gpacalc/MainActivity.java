package com.example.vignesh.gpacalc;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ActionBarContextView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    static String  sums,crdts;
   static int credit=0;
   static float sum=0;
   static float su;
   static int cr;
   Button show;
   private DrawerLayout drl;
   private ActionBarDrawerToggle adt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drl = (DrawerLayout) findViewById(R.id.drawer_layout);
        adt = new ActionBarDrawerToggle(this,drl,R.string.open,R.string.close);
        drl.addDrawerListener(adt);
        adt.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Spinner spin = (Spinner) findViewById(R.id.spinner);
        Spinner spin2 = (Spinner) findViewById(R.id.spinner2);
        Spinner spin3 = (Spinner) findViewById(R.id.spinner3);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Dept));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapter);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Regu));
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin2.setAdapter(adapter1);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.sem));
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin3.setAdapter(adapter2);

        spin.setOnItemSelectedListener(this);
        spin2.setOnItemSelectedListener(this);
        spin3.setOnItemSelectedListener(this);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // set item as selected to persist highlight
                        menuItem.setChecked(true);
                        // close drawer when item is tapped
                        drl.closeDrawers();
                        int id1 = menuItem.getItemId();
                        if(id1 == R.id.showgpa1)
                        {
                            Intent i = new Intent(MainActivity.this,showgpaActivity.class);
                            i.putExtra("mode","GPA");
                            startActivity(i);
                            Toast.makeText(getApplicationContext(),"open gpa show",Toast.LENGTH_SHORT).show();
                        }
                        if (id1 == R.id.showcgpa1)
                        {
                            Intent i = new Intent(MainActivity.this,showgpaActivity.class);
                            i.putExtra("mode","CGPA");
                            startActivity(i);
                            Toast.makeText(getApplicationContext(),"open gpa show",Toast.LENGTH_SHORT).show();
                        }
                        if (id1== R.id.auportal)
                        {
                            Intent i = new Intent(MainActivity.this,Web_view.class);
                            startActivity(i);
                        }

                        return true;
                    }
                });
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Spinner s = (Spinner) findViewById(R.id.spinner);
        Spinner s2 = (Spinner) findViewById(R.id.spinner2);
        Spinner s3 = (Spinner) findViewById(R.id.spinner3);
     //   EditText ed = (EditText) findViewById(R.id.ed);
        String dep = s.getSelectedItem().toString();
        final String reg = s2.getSelectedItem().toString();
        final String sem = s3.getSelectedItem().toString();
       final int se = Integer.parseInt(sem);


       // String pcg = ed.getText().toString();
        final String gpamod = "gp";
        final String cgpamod = "cgp";
        Button b1 = (Button) findViewById(R.id.button);
        //Button b2 = (Button) findViewById(R.id.button2);
        RadioButton gprd = (RadioButton) findViewById(R.id.gpard);
        RadioButton cgprd = (RadioButton) findViewById(R.id.cgpard);
        final Bundle depsem = new Bundle();
        depsem.putString("dep", dep);
        depsem.putString("reg", reg);
        depsem.putString("sem", sem);
         String s1;
        final int semes = Integer.parseInt(sem);
        //depsem.putString("pcg", pcg);
        radioGroup = (RadioGroup) findViewById(R.id.rdgrp);
       b1.setOnClickListener(new View.OnClickListener() {

                                  @Override
                                  public void onClick(View v) {
                                      int selectedId = radioGroup.getCheckedRadioButtonId();
                                      radioButton = (RadioButton) findViewById(selectedId);
                                      String mode = radioButton.getText().toString();
                                      if(reg.equals("2013")||(reg.equals("2017")&&se<=2)) {


                                          if (mode.equals("GPA")) {
                                              depsem.putString("mode", mode);
                                              Intent i = new Intent(MainActivity.this, Main2Activity.class);
                                              i.putExtras(depsem);
                                              startActivity(i);
                                          } else if (mode.equals("CGPA")) {
                                              for (int x = semes, y = 1; x >= 1; x--, y++) {
                                                  Intent s = new Intent(MainActivity.this, Main2Activity.class);
                                                  String x1 = String.format("%d", x);
                                                  depsem.putString("sem", x1);
                                                  depsem.putString("mode", mode);
                                                  s.putExtras(depsem);
                                                  startActivityForResult(s, x);
                                              }
                                          }
                                      }
                                      else Toast.makeText(getApplicationContext(),"please select appropriate semester for 2017 regulation",Toast.LENGTH_SHORT).show();
                                  }
                              }
        );
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding t
        Spinner s3 = (Spinner) findViewById(R.id.spinner3);
        String sem = s3.getSelectedItem().toString();
        int sems1 = Integer.parseInt(sem);
        super.onActivityResult(requestCode, resultCode, data);
        su = su + Float.parseFloat(data.getStringExtra("sum"));
        cr = cr + Integer.parseInt(data.getStringExtra("credit"));
        if(resultCode==sems1) {
            showresult();
        }

    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void showresult()
    {
        int selectedId = radioGroup.getCheckedRadioButtonId();
        radioButton = (RadioButton) findViewById(selectedId);
        String mode1 = radioButton.getText().toString();
        Spinner s3 = (Spinner) findViewById(R.id.spinner3);
        String sem = s3.getSelectedItem().toString();
        Intent i = new Intent(MainActivity.this, Main3Activity.class);
        String su1 = String.format("%f",su);
        String cr1 = String.format("%d",cr);
        i.putExtra("sum",su1);
        i.putExtra("credit",cr1);
        i.putExtra("mode",mode1);
        i.putExtra("sem",sem);
        startActivity(i);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {

        if(adt.onOptionsItemSelected(item))
        {
            return true;
        }
            return super.onOptionsItemSelected(item);
    }

}




