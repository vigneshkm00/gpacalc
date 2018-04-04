package com.example.vignesh.gpacalc;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
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

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Main3Activity extends AppCompatActivity implements dialogbox.dialoglistener {

    float s;
    String mode;
    int credit;
    float sum,cgpa;
    private TextView txt1,txt2;
    private Button save;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            String mode = getIntent().getStringExtra("mode");
            switch (item.getItemId()) {
                case R.id.hm:
                    return true;
                case R.id.save1:
                    openDialog();
                    return true;
                case R.id.savaspdf1:
                    if (mode.equals("GPA"))
                    {
                        saveaspdf();
                    }
                    else return true;
                    // mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.shareb1:
                    if(mode.equals("GPA"))
                    {
                        String dep = getIntent().getStringExtra("gpa");
                        Intent si = new Intent();
                        si.setAction(Intent.ACTION_SEND);
                        si.putExtra(Intent.EXTRA_TEXT, "GPA:" + dep + "\nThis GPA was calculated by the app \"GPA/CGPA Calculator\" Clink This Link to download.");
                        si.setType("text/plain");
                        startActivity(Intent.createChooser(si, "Send this message to"));
                    }
                    else
                    {
                        String cgpa1 = String.format("%.2f",cgpa).toString();
                        Intent si = new Intent();
                        si.setAction(Intent.ACTION_SEND);
                        si.putExtra(Intent.EXTRA_TEXT, "CGPA:"+cgpa1+"\nThis CGPA was calculated by the app \"GPA/CGPA Calculator\" Clink This Link to download.");
                        si.setType("text/plain");
                        startActivity(Intent.createChooser(si,"Send this message to"));
                    }
                    //  mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        mode = getIntent().getStringExtra("mode");
       if(mode.equals("GPA")) {
           final String dep = getIntent().getStringExtra("gpa");
           txt1 = (TextView) findViewById(R.id.textView4);
           txt2 = (TextView) findViewById(R.id.textView5);
           txt2.setText("Your GPA");
           txt1.setText(dep);
       }
      else {
           txt1 = (TextView) findViewById(R.id.textView4);
           String su = getIntent().getStringExtra("sum");
           String  cr = getIntent().getStringExtra("credit");
           txt2 = (TextView) findViewById(R.id.textView5);
           txt2.setText("Your CGPA");
           credit=Integer.parseInt(cr);
           sum=Float.parseFloat(su);
           cgpa=(Float)sum/credit;
           final String cgpa1 = String.format("%.2f",cgpa).toString();
           txt1.setText(cgpa1);
       }
    }
    public void openDialog()
    {
    dialogbox dialogbox = new dialogbox();
    dialogbox.show(getSupportFragmentManager(),"user details");
    }
    public void saveaspdf()
    {
        final String[] arr = getIntent().getStringArrayExtra("subj");
        final int[] crdts = getIntent().getIntArrayExtra("cr");
        final String[] selections = getIntent().getStringArrayExtra("grds");
        final String dep = getIntent().getStringExtra("gpa");
        //   Toast.makeText(getApplicationContext(),selections[1]+arr[1]+crdts[1], Toast.LENGTH_SHORT).show();
                final Bundle sub1 = new Bundle();
                sub1.putStringArray("subj", arr);
                sub1.putIntArray("cr", crdts);
                sub1.putString("gpa", dep);
                sub1.putStringArray("grds", selections);
                final Intent i = new Intent(Main3Activity.this, pdfdisplay.class);
                // i.putStringArrayListExtra("Sbj", arr);
                i.putExtras(sub1);
                startActivityForResult(i,101);
    }

    @Override
    public void applytext(String regno) {
        String dep;
        String mod = getIntent().getStringExtra("mode");
        if(mod.equals("GPA")) {
             dep = getIntent().getStringExtra("gpa");
        }
        else
        {
            dep = String.format("%.2f",cgpa).toString();
            regno = regno + "c";
        }
        String sem1 = getIntent().getStringExtra("sem");
        String space = "      ";
        String nextl ="\n";
        String[] sem11 = new String[50],gpa1 = new String[50];
        try {
            FileOutputStream fileOutputStream = openFileOutput(regno,MODE_APPEND);
            fileOutputStream.write(sem1.getBytes());
            fileOutputStream.write(space.getBytes());
            fileOutputStream.write(dep.getBytes());
            fileOutputStream.write(nextl.getBytes());
            fileOutputStream.close();
            Toast.makeText(getApplicationContext(),"file written successfully",Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Toast.makeText(getApplicationContext(),"save successfull",Toast.LENGTH_SHORT).show();
    }
}
