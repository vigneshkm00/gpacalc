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
        // tool.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
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
                        int id2 = R.id.showgpa1;
                        if(id1 == id2)
                        {
                            Intent i = new Intent(MainActivity.this,showgpaActivity.class);
                            startActivity(i);
                            Toast.makeText(getApplicationContext(),"open gpa show",Toast.LENGTH_SHORT).show();
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
        String reg = s2.getSelectedItem().toString();
        final String sem = s3.getSelectedItem().toString();

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
                                      if(mode.equals("GPA")) {
                                          depsem.putString("mode", mode);
                                          Intent i = new Intent(MainActivity.this, Main2Activity.class);
                                          i.putExtras(depsem);
                                          startActivity(i);
                                      }
                                      else
                                          if (mode.equals("CGPA")) {
                                              for (int x = semes,y=1; x >=1; x--,y++) {
                                                  Intent s = new Intent(MainActivity.this, Main2Activity.class);
                                                  String x1 = String.format("%d",x);
                                                  depsem.putString("sem",x1);
                                                  depsem.putString("mode", mode);
                                                  s.putExtras(depsem);
                                                  startActivityForResult(s,x);
                                                  /*switch (y)
                                                  {
                                                      case 1: startActivityForResult(s,1);
                                                          break;
                                                      case 2: startActivityForResult(s,2);
                                                          break;
                                                      case 3: startActivityForResult(s,3);
                                                          break;
                                                      case 4: startActivityForResult(s,4);
                                                          break;
                                                      case 5: startActivityForResult(s,5);
                                                          break;
                                                      case 6: startActivityForResult(s,6);
                                                          break;
                                                      case 7: startActivityForResult(s,7);
                                                          break;
                                                      case 8: startActivityForResult(s,8);
                                                          break;

                                                  }*/

                                              }
                                          }
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
        /*
        if (resultCode == 1) {
            su = su + Float.parseFloat(data.getStringExtra("sum"));
            cr = cr + Integer.parseInt(data.getStringExtra("credit"));
            if(sems1==1) {
              showresult();
            }

        }
        if (resultCode == 2) {
            su = su + Float.parseFloat(data.getStringExtra("sum"));
            cr = cr + Integer.parseInt(data.getStringExtra("credit"));
            if(sems1==2) {
              showresult();
            }
        }
        if (resultCode == 3) {
            su = su + Float.parseFloat(data.getStringExtra("sum"));
            cr = cr + Integer.parseInt(data.getStringExtra("credit"));
            if(sems1==3) {
                showresult();
            }
        }
        if (resultCode == 4) {
            su = su + Float.parseFloat(data.getStringExtra("sum"));
            cr = cr + Integer.parseInt(data.getStringExtra("credit"));
            if(sems1==4) {
              showresult();
            }
        }
        if (resultCode == 5) {
            su = su + Float.parseFloat(data.getStringExtra("sum"));
            cr = cr + Integer.parseInt(data.getStringExtra("credit"));
            if(sems1==5) {
          showresult();
            }
        }
        if (resultCode == 6) {
            su = su + Float.parseFloat(data.getStringExtra("sum"));
            cr = cr + Integer.parseInt(data.getStringExtra("credit"));
            if(sems1==6) {
           showresult();
            }
        }
        if (resultCode == 7) {
            su = su + Float.parseFloat(data.getStringExtra("sum"));
            cr = cr + Integer.parseInt(data.getStringExtra("credit"));
            if(sems1==7) {
             showresult();
            }
        }
        if (resultCode == 8) {
            su = su + Float.parseFloat(data.getStringExtra("sum"));
            cr = cr + Integer.parseInt(data.getStringExtra("credit"));
            if(sems1==8) {
               showresult();
            }
        }*/
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    @Override
    public boolean onCreateOptionsMenu(Menu men){
        getMenuInflater().inflate(R.menu.main_menu, men);
        return true;
    }
    public void showresult()
    {
        int selectedId = radioGroup.getCheckedRadioButtonId();
        radioButton = (RadioButton) findViewById(selectedId);
        String mode1 = radioButton.getText().toString();
        Intent i = new Intent(MainActivity.this, Main6Activity.class);
        String su1 = String.format("%f",su);
        String cr1 = String.format("%d",cr);
        i.putExtra("sum",su1);
        i.putExtra("credit",cr1);
        i.putExtra("mode",mode1);
        startActivity(i);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id=item.getItemId();
        if(id==R.id.abt)
        {
            return true;
        }

        if(adt.onOptionsItemSelected(item))
        {
            return true;
        }
            return super.onOptionsItemSelected(item);
    }

}




