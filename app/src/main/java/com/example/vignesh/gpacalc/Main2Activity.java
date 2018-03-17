package com.example.vignesh.gpacalc;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import android.widget.Toast;
import java.util.List;
import java.util.ArrayList;
import android.support.design.widget.FloatingActionButton;
import android.content.Intent;
import android.widget.Toolbar;

import static java.lang.String.*;

public class Main2Activity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    ListView lst, ls2;
    Spinner spr;
    TextView txt;
    FloatingActionButton btn;
    List<Integer> points;
    static int semester =1;
    List<String> selections;
    ArrayAdapter<String> adapter, adapter1;
    String[] arr;
    int[] crdts;
    int reg12;
    float gpa, sum=0,cgpa;

    public Main2Activity() {
        selections = new ArrayList<String>();
        this.lst=lst;
        this.ls2=ls2;
        this.txt=txt;
        this.points=points;
        this.arr=arr;
        this.crdts=crdts;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        btn = (FloatingActionButton) findViewById(R.id.btn1);
        txt = (TextView) findViewById(R.id.textView);
        lst = (ListView) findViewById(R.id.listvm);
        ls2 = (ListView) findViewById(R.id.listvm2);
        spr = (Spinner) findViewById(R.id.list_item);
        String mo = getIntent().getStringExtra("mode");
        String dep = getIntent().getStringExtra("dep");
        final String sem = getIntent().getStringExtra("sem");
        String reg = getIntent().getStringExtra("reg");
        final int reg1 =Integer.parseInt(reg);
        final int sem1 = Integer.parseInt(sem);
        reg12=reg1;
        final int seme =sem1;
        if(reg1==2013) {
            if (dep.equals("Information Technology")) {
                switch (sem1) {
                    case 1:
                        arr = getResources().getStringArray(R.array.it1);
                        crdts = getResources().getIntArray(R.array.it1crdts);
                        break;

                    case 2:
                        arr = getResources().getStringArray(R.array.it2);
                        crdts = getResources().getIntArray(R.array.it2credits);
                        break;

                    case 3:
                        arr = getResources().getStringArray(R.array.it3);
                        crdts = getResources().getIntArray(R.array.it3credits);
                        break;
                    case 4:
                        arr = getResources().getStringArray(R.array.it4);
                        crdts = getResources().getIntArray(R.array.it4credits);
                        break;
                    case 5:
                        arr = getResources().getStringArray(R.array.it5);
                        crdts = getResources().getIntArray(R.array.it5credits);
                        break;
                    case 6:
                        arr = getResources().getStringArray(R.array.it6);
                        crdts = getResources().getIntArray(R.array.it6credits);
                        break;
                    case 7:
                        arr = getResources().getStringArray(R.array.it7);
                        crdts = getResources().getIntArray(R.array.it7credits);
                        break;
                    case 8:
                        arr = getResources().getStringArray(R.array.it8);
                        crdts = getResources().getIntArray(R.array.it8credits);
                        break;

                }
            } else if (dep.equals("Mechanical")) {
                switch (sem1) {
                    case 1:
                        arr = getResources().getStringArray(R.array.it1);
                        crdts = getResources().getIntArray(R.array.it1crdts);
                        break;

                    case 2:
                        arr = getResources().getStringArray(R.array.mech2);
                        crdts = getResources().getIntArray(R.array.mech2credits);
                        break;

                    case 3:
                        arr = getResources().getStringArray(R.array.mech3);
                        crdts = getResources().getIntArray(R.array.mech3credits);
                        break;
                    case 4:
                        arr = getResources().getStringArray(R.array.mech4);
                        crdts = getResources().getIntArray(R.array.mech4credits);
                        break;
                    case 5:
                        arr = getResources().getStringArray(R.array.mech5);
                        crdts = getResources().getIntArray(R.array.mech5credits);
                        break;
                    case 6:
                        arr = getResources().getStringArray(R.array.mech6);
                        crdts = getResources().getIntArray(R.array.mech6credits);
                        break;
                    case 7:
                        arr = getResources().getStringArray(R.array.mech7);
                        crdts = getResources().getIntArray(R.array.mech7credits);
                        break;
                    case 8:
                        arr = getResources().getStringArray(R.array.mech8);
                        crdts = getResources().getIntArray(R.array.cse8credits);
                        break;

                }
            } else if (dep.equals("Civil")) {
                switch (sem1) {
                    case 1:
                        arr = getResources().getStringArray(R.array.it1);
                        crdts = getResources().getIntArray(R.array.it1crdts);
                        break;

                    case 2:
                        arr = getResources().getStringArray(R.array.civil2);
                        crdts = getResources().getIntArray(R.array.civil2credits);
                        break;

                    case 3:
                        arr = getResources().getStringArray(R.array.civil3);
                        crdts = getResources().getIntArray(R.array.civil3credits);
                        break;
                    case 4:
                        arr = getResources().getStringArray(R.array.civil4);
                        crdts = getResources().getIntArray(R.array.civil4credits);
                        break;
                    case 5:
                        arr = getResources().getStringArray(R.array.civil5);
                        crdts = getResources().getIntArray(R.array.civil5credits);
                        break;
                    case 6:
                        arr = getResources().getStringArray(R.array.civil6);
                        crdts = getResources().getIntArray(R.array.civil6credits);
                        break;
                    case 7:
                        arr = getResources().getStringArray(R.array.civil7);
                        crdts = getResources().getIntArray(R.array.civil7credits);
                        break;
                    case 8:
                        arr = getResources().getStringArray(R.array.civil8);
                        crdts = getResources().getIntArray(R.array.cse8credits);
                        break;

                }
            } else if (dep.equals("Electrical and Electronicals")) {
                switch (sem1) {
                    case 1:
                        arr = getResources().getStringArray(R.array.it1);
                        crdts = getResources().getIntArray(R.array.it1crdts);
                        break;

                    case 2:
                        arr = getResources().getStringArray(R.array.eee2);
                        crdts = getResources().getIntArray(R.array.eee2credits);
                        break;

                    case 3:
                        arr = getResources().getStringArray(R.array.eee3);
                        crdts = getResources().getIntArray(R.array.eee3credits);
                        break;
                    case 4:
                        arr = getResources().getStringArray(R.array.eee4);
                        crdts = getResources().getIntArray(R.array.eee4credits);
                        break;
                    case 5:
                        arr = getResources().getStringArray(R.array.eee5);
                        crdts = getResources().getIntArray(R.array.eee5credits);
                        break;
                    case 6:
                        arr = getResources().getStringArray(R.array.eee6);
                        crdts = getResources().getIntArray(R.array.eee6credits);
                        break;
                    case 7:
                        arr = getResources().getStringArray(R.array.eee7);
                        crdts = getResources().getIntArray(R.array.eee7credits);
                        break;
                    case 8:
                        arr = getResources().getStringArray(R.array.eee8);
                        crdts = getResources().getIntArray(R.array.cse8credits);
                        break;

                }
            } else if (dep.equals("Electronic and Communication")) {
                switch (sem1) {
                    case 1:
                        arr = getResources().getStringArray(R.array.it1);
                        crdts = getResources().getIntArray(R.array.it1crdts);
                        break;

                    case 2:
                        arr = getResources().getStringArray(R.array.ece2);
                        crdts = getResources().getIntArray(R.array.ece2credits);
                        break;

                    case 3:
                        arr = getResources().getStringArray(R.array.ece3);
                        crdts = getResources().getIntArray(R.array.ece3credits);
                        break;
                    case 4:
                        arr = getResources().getStringArray(R.array.ece4);
                        crdts = getResources().getIntArray(R.array.ece4credits);
                        break;
                    case 5:
                        arr = getResources().getStringArray(R.array.ece5);
                        crdts = getResources().getIntArray(R.array.ece5credits);
                        break;
                    case 6:
                        arr = getResources().getStringArray(R.array.ece6);
                        crdts = getResources().getIntArray(R.array.ece6credits);
                        break;
                    case 7:
                        arr = getResources().getStringArray(R.array.ece7);
                        crdts = getResources().getIntArray(R.array.ece7credits);
                        break;
                    case 8:
                        arr = getResources().getStringArray(R.array.ece8);
                        crdts = getResources().getIntArray(R.array.ece8credits);
                        break;

                }
            } else if (dep.equals("Computer Science")) {
                switch (sem1) {
                    case 1:
                        arr = getResources().getStringArray(R.array.cse1);
                        crdts = getResources().getIntArray(R.array.it1crdts);
                        break;

                    case 2:
                        arr = getResources().getStringArray(R.array.cse2);
                        crdts = getResources().getIntArray(R.array.it2credits);
                        break;

                    case 3:
                        arr = getResources().getStringArray(R.array.cse3);
                        crdts = getResources().getIntArray(R.array.cse3credits);
                        break;
                    case 4:
                        arr = getResources().getStringArray(R.array.cse4);
                        crdts = getResources().getIntArray(R.array.cse4credits);
                        break;
                    case 5:
                        arr = getResources().getStringArray(R.array.cse5);
                        crdts = getResources().getIntArray(R.array.cse5credits);
                        break;
                    case 6:
                        arr = getResources().getStringArray(R.array.cse6);
                        crdts = getResources().getIntArray(R.array.cse6credits);
                        break;
                    case 7:
                        arr = getResources().getStringArray(R.array.cse7);
                        crdts = getResources().getIntArray(R.array.cse7credits);
                        break;
                    case 8:
                        arr = getResources().getStringArray(R.array.cse8);
                        crdts = getResources().getIntArray(R.array.cse8credits);
                        break;

                }
            }
        }
        else
        {
            if(dep.equals("Information Technology"))
            {
                switch (sem1)
                {
                    case 1:
                        arr=getResources().getStringArray(R.array.it1r17);
                        crdts=getResources().getIntArray(R.array.it1r17c);
                        break;

                    case 2:
                        arr=getResources().getStringArray(R.array.it2r17);
                        crdts=getResources().getIntArray(R.array.it2r17c);
                        break;
                    default:
                        Toast.makeText(getApplicationContext(),"Please select the correct semester you have entered is wrong",Toast.LENGTH_SHORT).show();

                }

            }
            if(dep.equals("Mechanical"))
            {
                switch (sem1)
                {
                    case 1:
                        arr=getResources().getStringArray(R.array.it1r17);
                        crdts=getResources().getIntArray(R.array.it1r17c);
                        break;

                    case 2:
                        arr=getResources().getStringArray(R.array.mech2r17);
                        crdts=getResources().getIntArray(R.array.mech2r17c);
                        break;
                    default:
                    {
                        Toast.makeText(getApplicationContext(),"Please select the correct semester you have entered is wrong",Toast.LENGTH_SHORT).show();
                        System.exit(0);}

                }

            }
            if(dep.equals("Civil"))
            {
                switch (sem1)
                {
                    case 1:
                        arr=getResources().getStringArray(R.array.it1r17);
                        crdts=getResources().getIntArray(R.array.it1r17c);
                        break;

                    case 2:
                        arr=getResources().getStringArray(R.array.civil2r17);
                        crdts=getResources().getIntArray(R.array.civil2r17c);
                        break;
                    default:
                    {
                        Toast.makeText(getApplicationContext(),"Please select the correct semester you have entered is wrong",Toast.LENGTH_SHORT).show();
                        System.exit(0);}


                }

            }
            if(dep.equals("Electrical and Electronicals"))
            {
                switch (sem1)
                {
                    case 1:
                        arr=getResources().getStringArray(R.array.it1r17);
                        crdts=getResources().getIntArray(R.array.it1r17c);
                        break;

                    case 2:
                        arr=getResources().getStringArray(R.array.eee2r17);
                        crdts=getResources().getIntArray(R.array.eee2r17c);
                        break;
                    default:
                    {
                        Toast.makeText(getApplicationContext(),"Please select the correct semester you have entered is wrong",Toast.LENGTH_SHORT).show();
                        System.exit(0);}

                }

            }
            if(dep.equals("Electronic and Communication"))
            {
                switch (sem1)
                {
                    case 1:
                        arr=getResources().getStringArray(R.array.it1r17);
                        crdts=getResources().getIntArray(R.array.it1r17c);
                        break;

                    case 2:
                        arr=getResources().getStringArray(R.array.ece2r17);
                        crdts=getResources().getIntArray(R.array.ece2r17c);
                        break;
                    default:
                    {
                        Toast.makeText(getApplicationContext(),"Please select the correct semester you have entered is wrong",Toast.LENGTH_SHORT).show();
                        System.exit(0);}


                }

            }
            if(dep.equals("Computer Science"))
            {
                switch (sem1)
                {
                    case 1:
                        arr=getResources().getStringArray(R.array.it1r17);
                        crdts=getResources().getIntArray(R.array.it1r17c);
                        break;

                    case 2:
                        arr=getResources().getStringArray(R.array.cse2r17);
                        crdts=getResources().getIntArray(R.array.cse2r17c);
                        break;
                    default:
                    {
                        Toast.makeText(getApplicationContext(),"Please select the correct semester you have entered is wrong",Toast.LENGTH_SHORT).show();
                        System.exit(0);}


                }

            }
        }


        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arr) {
            @NonNull
            public View getView(int position, View convertView, @NonNull ViewGroup parent) {

                View view = super.getView(position, convertView, parent);

                TextView tv = (TextView) view.findViewById(android.R.id.text1);

                tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 17);

                // Return the view
                return view;
            }
        };

        lst.setAdapter(adapter);
        if(reg1==2013) {

            adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.grades)) {
                @NonNull
                public View getView(int position, View convertView, @NonNull ViewGroup parent) {
                    /// Get the Item from ListView
                    View view = super.getView(position, convertView, parent);

                    TextView tv = (TextView) view.findViewById(android.R.id.text1);

                    tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 17);

                    // Return the view
                    return view;
                }
            };
        }
        else {
            adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.grades2)) {
                @NonNull
                public View getView(int position, View convertView, @NonNull ViewGroup parent) {
                    /// Get the Item from ListView
                    View view = super.getView(position, convertView, parent);

                    TextView tv = (TextView) view.findViewById(android.R.id.text1);

                    tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 17);

                    // Return the view
                    return view;
                }
            };

        }
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ls2.setAdapter(new CustomAdapter(this, lst));


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int credits = 0;

                int val,val1;
                int  p;
                String s;

                for (int i = 0; i < ls2.getChildCount(); i++) {


                    View listItem = ls2.getChildAt(i);
                    Spinner spinner = (Spinner) listItem.findViewById(R.id.list_item);


                    String selection = (String) spinner.getSelectedItem();

                    selections.add(selection);
                }

                points = calculateGradetoPoints(selections);

                for (int i = 0; i < points.size(); i++) {
                    val=points.get(i);
                    val1=Integer.parseInt(String.valueOf(val));



                    if (val1 == 0) {
                        // crdts[i]=0;
                    } else {

                        p = val1* crdts[i];//((int)gpacredits.get(i));
                        sum = sum + p;
                        credits = credits + crdts[i];



                    }

                }

                gpa = sum/credits;
                //Toast.makeText(getApplicationContext(),"Hello Javatpoint"+sum,Toast.LENGTH_SHORT).show();
                s= String.format("%.2f", gpa);
               // String pcgp = getIntent().getStringExtra("pcg");
               // String sem3 = getIntent().getStringExtra("sem");
               // int se = Integer.parseInt(sem3);
               // float pcgpa = Float.parseFloat(pcgp);
               // cgpa = ((pcgpa*(se-1))+gpa)/se;
              //

             //   String cgpa1 = String.format("%.2f",cgpa);
                String sum1 = String.format("%f",sum);
                String credit1 = String.format("%d",credits);
                String mo = getIntent().getStringExtra("mode");
                String totsem = getIntent().getStringExtra("totsem");
                    if(mo.equals("GPA")) {
                        Intent i = new Intent(Main2Activity.this, Main3Activity.class);
                        i.putExtra("mode",mo);
                        i.putExtra("gpa", s);
                        i.putExtra("sem",sem1);
                        startActivity(i);
                    }
                    if(mo.equals("CGPA"))
                    {
                        Intent j = new Intent(Main2Activity.this,MainActivity.class);
                        j.putExtra("sum", sum1);
                        j.putExtra("credit", credit1);
                        setResult(sem1,j);
                        finish();
                     /* switch (sem1)
                        {
                            case 1:
                                setResult(1,j);
                                finish();
                                break;
                            case 2:
                                setResult(2,j);
                                finish();
                                break;
                            case 3:
                                setResult(3,j);
                                finish();
                                break;
                            case 4:
                                setResult(4,j);
                                finish();
                                break;
                            case 5:
                                setResult(5,j);
                                finish();
                                break;
                            case 6:
                                setResult(6,j);
                                finish();
                                break;
                            case 7:
                                setResult(7,j);
                                finish();
                                break;
                            case 8:
                                setResult(8,j);
                                finish();
                                break;
                        }*/
                    }
            }


        });
    }

    @Override
    public void onItemSelected (AdapterView < ? > parent, View view,int position, long id){
        System.out.print("helloworld");
    }

    @Override
    public void onNothingSelected (AdapterView < ? > parent){
        System.out.print("hello worst world");
    }

    public List<Integer> calculateGradetoPoints (List < String > selections) {
        int c;
        String s;
        List<Integer> points = new ArrayList<Integer>();

        for (int i = 0; i < selections.size(); i++) {
            s = selections.get(i);
            switch(s)
            {
                case "S":
                    c=10;
                    points.add(c);
                    break;
                case "A":
                    if(reg12==2013)
                    {
                        c = 9;
                        points.add(c);
                    }
                    else{
                        c = 8;
                        points.add(c);
                    }
                    break;
                case "B":
                    if(reg12==2013) {
                        c = 8;
                        points.add(c);
                    }
                    else { c =6;
                        points.add(c);
                    }
                    break;
                case "C":
                    c = 7;
                    points.add(c);
                    break;
                case "D":
                    c = 6;
                    points.add(c);
                    break;
                case "E":
                    c = 5;
                    points.add(c);
                    break;
                case "U":
                    c = 0;
                    points.add(c);
                    break;
                case "O":
                    c = 10;
                    points.add(c);
                    break;
                case "A+":
                    c =9;
                    points.add(c);
                    break;
                case "B+":
                    c =7;
                    points.add(c);
                    break;
                case "RA":
                    c =0;
                    points.add(c);
                    break;
            }
        }
        return points;
    }



    class CustomAdapter extends BaseAdapter {
        LayoutInflater inflater;
        ListView lv;


        private CustomAdapter(Context context, ListView listview) {
            inflater = LayoutInflater.from(context);
            lv = listview;
        }

        public int getCount() {

            return lv.getCount();
        }

        public Object getItem(int position) {
            return position;
        }

        public long getItemId(int position) {
            return position;
        }

        @SuppressLint("InflateParams")
        public View getView(int arg0, View convertview, ViewGroup arg2) {
            ViewHolder viewHolder;
            if (convertview == null) {
                convertview = inflater.inflate(R.layout.listrow, null);
                viewHolder = new ViewHolder();
                viewHolder.spinner = (Spinner) convertview.findViewById(R.id.list_item);

                viewHolder.spinner.setAdapter(adapter1);
                convertview.setTag(viewHolder);
            }
            return convertview;
        }

        class ViewHolder {
            Spinner spinner;

        }
    }
}

