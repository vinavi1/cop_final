
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


    // necessary objects are declared
    Button send;
    EditText teamname;
    EditText name1;
    EditText entry1;
    EditText name2;
    EditText entry2;
    EditText name3;
    EditText entry3;
    String check;

    //data stores the entry-numbers read from .txt file externally
    static String[] data;

    //refer maps the entry-numbers with the respective names
    static HashMap<String,String> refer;

    //these are done on creation of MainActivity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //views and other objects are initialized
        refer = new HashMap<>();
        teamname = (EditText) findViewById(R.id.Team);
        name1 = (EditText) findViewById(R.id.n1);
        entry1 = (EditText) findViewById(R.id.e1);
        name2 = (EditText) findViewById(R.id.n2);
        entry2 = (EditText) findViewById(R.id.e2);
        name3 = (EditText) findViewById(R.id.n3);
        entry3 = (EditText) findViewById(R.id.e3);
        send = (Button) findViewById(R.id.send);

        //keeps waiting until the send button is clicked
        send.setOnClickListener(this);

        //accepts the data sent to this activity from Members Activity
        Intent intent3 = getIntent();
        check = intent3.getStringExtra("bool");

        //checks the value of bool and decides visibility of entry3
        if(check.equals("mem2")){
            entry3.setVisibility(View.GONE);
            name3.setVisibility(View.GONE);
        }
        else{
            entry3.setVisibility(View.VISIBLE);
            name3.setVisibility(View.VISIBLE);
        }

        /*this method is used to check for the validity of the entry number
        entered and displaying the error in case it is wrong and
         also getting the name corresponding to the entry number and the entryfocus here is only for the edittext
        corresponding to entry numbers*/
        View.OnFocusChangeListener entryfocus = null;
        entryfocus=new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    //temp is a temporary object to get the view which calls this method
                    EditText temp= (EditText) findViewById(v.getId());
                    if (!CheckEntryNumber.checkEntryno(gtext(temp))) {
                        temp.setError("invalid entry");
                    } else {
                            /*refer is the hashTable which contais entry numbers as the key and names as the values
                            * if there is no such key we don't suggest any name to the name blank*/
                        temp.setError(null);
                        if(v.getId()==entry1.getId()){
                            if (refer.containsKey(entry1.getText().toString().toUpperCase())){
                                name1.setText(refer.get(entry1.getText().toString().toUpperCase()));
                            }
                        }
                        if(v.getId()==entry2.getId()){
                            if (refer.containsKey(entry2.getText().toString().toUpperCase())){
                                name2.setText(refer.get(entry2.getText().toString().toUpperCase()));
                            }
                        }
                        if(v.getId()==entry3.getId()){
                            if (refer.containsKey(entry3.getText().toString().toUpperCase())){
                                name3.setText(refer.get(entry3.getText().toString().toUpperCase()));
                            }
                        }
                    }
                }
            }
        };

        /*this is to check if the name is left empty and display error if it left empty
        * activated each time the focus of it changes*/
        View.OnFocusChangeListener namefocus = null;
        namefocus=new View.OnFocusChangeListener() {
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
        };

        /*setting the focus listeners to the corresponding editTexts depending on name or entry number*/
        teamname.setOnFocusChangeListener(namefocus);
        entry1.setOnFocusChangeListener(entryfocus);
        name1.setOnFocusChangeListener(namefocus);
        entry2.setOnFocusChangeListener(entryfocus);
        name2.setOnFocusChangeListener(namefocus);
        entry3.setOnFocusChangeListener(entryfocus);
        name3.setOnFocusChangeListener(namefocus);

        /*reading the text file from the assets into an hashtable and an array for drop down menu
        * and for suggesting names*/
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

        /*creating an array adpater using the
        array created previously from the text file and setting the array adapter to the autocomplete textview */
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

    //gtext is function which takes an edit text and gets the string enteres in the edit text
    public static String gtext (EditText v) {
        String data =  v.getText().toString() ;
        return data;
    }
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.send:
                /*checking if the number of memebers are 2 or 3 and accordingly setting the
                * intent that is being transfered and ignoring the empty spaces in the entry3 and name3
                * if the number of members selected is 3*/
                if(!check.equals("mem2")) {
                    boolean en1val=CheckEntryNumber.checkEntryno(gtext(entry1));
                    boolean en2val=CheckEntryNumber.checkEntryno(gtext(entry2));
                    boolean en3val=CheckEntryNumber.checkEntryno(gtext(entry3));

                    if(!(teamname.getText().toString().isEmpty())&&en1val&&en2val&&en3val&&!(name1.getText().toString().isEmpty())&&!(name2.getText().toString().isEmpty())&&!(name3.getText().toString().isEmpty())){
                        Intent intent = new Intent(this, confirmation.class);

                        intent.putExtra("Team-name", gtext(teamname));  //passing the intent to the next activity
                        intent.putExtra("user1", gtext(name1));
                        intent.putExtra("user2", gtext(name2));
                        intent.putExtra("user3", gtext(name3));

                        intent.putExtra("entry1", gtext(entry1));
                        intent.putExtra("entry2", gtext(entry2));
                        intent.putExtra("entry3", gtext(entry3));
                        startActivity(intent);

                        break;
                    }
                    else{       //if any entry is wrong requesting focus to the first mistake
                        if(!en1val) {
                            entry1.requestFocus();
                        }
                        else {
                            if(!en2val){
                                entry2.requestFocus();
                            }
                            else{
                                entry3.requestFocus();
                            }
                        }
                    }
                }
                else {              //same as for 3 members only that we send the entry3 and name3 intent as null
                    boolean en1val=CheckEntryNumber.checkEntryno(gtext(entry1));
                    boolean en2val=CheckEntryNumber.checkEntryno(gtext(entry2));

                    if(!(teamname.getText().toString().isEmpty())&&en1val&&en2val&&!(name1.getText().toString().isEmpty())&&!(name2.getText().toString().isEmpty())){
                        Intent intent = new Intent(this, confirmation.class);

                        intent.putExtra("Team-name", gtext(teamname));
                        intent.putExtra("user1", gtext(name1));
                        intent.putExtra("user2", gtext(name2));
                        intent.putExtra("user3", (String) null);

                        intent.putExtra("entry1", gtext(entry1));
                        intent.putExtra("entry2", gtext(entry2));
                        intent.putExtra("entry3", (String) null);
                        startActivity(intent);

                        break;
                    }
                    else{
                        if(!en1val) {
                            entry1.requestFocus();
                        }
                        else {
                            if(!en2val){
                                entry2.requestFocus();
                            }
                        }
                    }
                }
        }
    }
}