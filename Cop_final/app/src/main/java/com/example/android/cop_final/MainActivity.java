package com.example.android.cop_final;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowId;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button send;
    EditText teamname;
    EditText name1;
    EditText entry1;
    EditText name2;
    EditText entry2;
    EditText name3;
    EditText entry3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        teamname = (EditText) findViewById(R.id.Team);
        name1 = (EditText) findViewById (R.id.n1);
        entry1 = (EditText) findViewById(R.id.e1);
        name2 = (EditText) findViewById (R.id.n2);
        entry2 = (EditText) findViewById(R.id.e2);
        name3 = (EditText) findViewById (R.id.n3);
        entry3 = (EditText) findViewById(R.id.e3);

        send = (Button) findViewById(R.id.send);
        send.setOnClickListener(this);

        int falg1=0,flag2=0,flag3=0;

        entry1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (!checkEntryno(entry1.getText().toString())) {
                        Toast.makeText(MainActivity.this, "entry1 is invalid entry number", Toast.LENGTH_LONG).show();
                        entry1.setError("INVALID ENTRY NUMBER-1");
                    }
                    else{
                        entry1.setError(null);
                    }
                }
            }
        });

        entry2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (!checkEntryno(entry2.getText().toString())) {
                        Toast.makeText(MainActivity.this, "entry2 is invalid entry number", Toast.LENGTH_LONG).show();
                        entry2.setError("INVALID ENTRY NUMBER-2");
                    } else{
                        entry2.setError(null);
                    }
                }
            }
        });

        entry3.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    if (!checkEntryno(entry3.getText().toString())) {
                        Toast.makeText(MainActivity.this, "entry3 is invalid entry number", Toast.LENGTH_LONG).show();
                        entry3.setError("INVALID ENTRY NUMBER-3");
                    } else{
                        entry3.setError(null);
                    }
                }
            }
        });
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

                if(entry1.getError()==null&&entry2.getError()==null&&entry3.getError()==null){
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
                    if(entry1.getError()!=null) {
                        entry1.requestFocus();
                    }
                    else {
                        if (entry2.getError()!=null){
                            entry2.requestFocus();
                        }
                        else entry3.requestFocus();
                    }
                }
        }
    }

}