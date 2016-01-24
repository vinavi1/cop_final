package com.example.android.cop_final;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowId;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button send;
    EditText teamname;
    EditText name1;
    EditText entry1;
    EditText name2;
    EditText entry2;
    EditText name3;
    EditText entry3;
    String[] data;
    String[] datan;
    HashMap<String,String> refer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        refer = new HashMap<>();

        teamname = (EditText) findViewById(R.id.Team);
        name1 = (EditText) findViewById(R.id.n1);
        entry1 = (EditText) findViewById(R.id.e1);
        name2 = (EditText) findViewById(R.id.n2);
        entry2 = (EditText) findViewById(R.id.e2);
        name3 = (EditText) findViewById(R.id.n3);
        entry3 = (EditText) findViewById(R.id.e3);



        send = (Button) findViewById(R.id.send);
        send.setOnClickListener(this);

        Intent intent3 = getIntent();

        String check = intent3.getStringExtra("bool");

                if(check.equals("mem2")){
                    entry3.setVisibility(View.GONE);
                    name3.setVisibility(View.GONE);
                }
                else{
                    entry3.setVisibility(View.VISIBLE);
                    name3.setVisibility(View.VISIBLE);
                }

        name1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (name1.getText().toString().isEmpty()) {
                        name1.setError("must not be empty");
                    } else {
                        name1.setError(null);
                    }
                }
            }
        });

        entry1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (!checkEntryno(entry1.getText().toString().toUpperCase())) {
                        Toast.makeText(MainActivity.this, "entrynumber-1 is invalid", Toast.LENGTH_LONG).show();
                        entry1.setError("invalid entry");
                    } else {
                        entry1.setError(null);
                        if (refer.containsKey(entry1.getText().toString().toUpperCase())){
                            name1.setText(refer.get(entry1.getText().toString().toUpperCase()));
                        }
                    }
                }
            }
        });

        name2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (name2.getText().toString().isEmpty()) {
                        name2.setError("must not be empty");
                    } else {
                        name2.setError(null);
                    }
                }
            }
        });

        entry2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (!checkEntryno(entry2.getText().toString().toUpperCase())) {
                        Toast.makeText(MainActivity.this, "entrynumber-2 is invalid", Toast.LENGTH_LONG).show();
                        entry2.setError("invalid entry");
                    } else {
                        entry2.setError(null);
                    }
                }
            }
        });

        name3.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (name3.getText().toString().isEmpty()) {
                        name3.setError("must not be empty");
                    } else {
                        name3.setError(null);
                    }
                }
            }
        });

        entry3.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (!checkEntryno(entry3.getText().toString().toUpperCase())) {
                        Toast.makeText(MainActivity.this, "entrynumber-3 is invalid", Toast.LENGTH_LONG).show();
                        entry3.setError("invalid entry");
                    } else {
                        entry3.setError(null);
                    }
                }
            }
        });


        BufferedReader br=null;
        try {
            List<String> list=new ArrayList<String>();

            String line;
            br=new BufferedReader(new InputStreamReader(getAssets().open("entrynumbers.txt")));
            while((line=br.readLine())!=null){
                String[] temp;
                temp=line.split(" ",2);
                list.add(temp[0]);
                refer.put(temp[0],temp[1]);
            }
            data = list.toArray(new String[list.size()]);

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try{
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>
                (MainActivity.this,R.layout.support_simple_spinner_dropdown_item,data);
        AutoCompleteTextView en1= (AutoCompleteTextView) findViewById(R.id.e1);
        en1.setThreshold(1);
        en1.setAdapter(arrayAdapter);
        AutoCompleteTextView en2= (AutoCompleteTextView) findViewById(R.id.e2);
        en2.setThreshold(1);
        en2.setAdapter(arrayAdapter);
        AutoCompleteTextView en3= (AutoCompleteTextView) findViewById(R.id.e3);
        en3.setThreshold(1);
        en3.setAdapter(arrayAdapter);

    }

    public static boolean checkEntryno(String entryno) {

        if(entryno.length()!=11){return false;}

        int year = Integer.parseInt(entryno.substring(0, 4));
        String dept = entryno.substring(4, 6);
        String spec = entryno.substring(6,7);

        if (year > 2014 || year < 2000) {return false;}
        if (!dept.matches("CS") && !dept.matches("EE")) {return false;}
        if (!spec.matches("1") && !spec.matches("5")) {return false;}

        return true;
    }

    public static String gtext (EditText v) {
        String data =  v.getText().toString() ;
        return data;
    }
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.send:

                if(entry1.getError()==null&&entry2.getError()==null&&entry3.getError()==null && name1.getError()==null&&name2.getError()==null&&name3.getError()==null ){
                    Intent intent = new Intent(this, confirmation.class);

                    intent.putExtra("Team-name", gtext(teamname));
                    intent.putExtra("user1", gtext(name1));
                    intent.putExtra("user2", gtext(name2));
                    intent.putExtra("user3", gtext(name3));

                    intent.putExtra("entry1", gtext(entry1));
                    intent.putExtra("entry2", gtext(entry2));
                    intent.putExtra("entry3", gtext(entry3));
                    startActivity(intent);

                    break;
                }
                else{
                    if(!checkEntryno(entry1.getText().toString().toUpperCase())) {
                        entry1.requestFocus();
                    }
                    else {
                        if (!checkEntryno(entry1.getText().toString().toUpperCase())){
                            entry2.requestFocus();
                        }
                        else entry3.requestFocus();
                    }
                }
        }
    }

}