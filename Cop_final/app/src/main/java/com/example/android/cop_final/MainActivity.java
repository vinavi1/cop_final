package com.example.android.cop_final;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //hello world!
    Button send;
    EditText tname;
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
        tname = (EditText) findViewById(R.id.Team);
        name1 = (EditText) findViewById (R.id.n1);
        entry1 = (EditText) findViewById(R.id.e1);
        name2 = (EditText) findViewById (R.id.n2);
        entry2 = (EditText) findViewById(R.id.e2);
        name3 = (EditText) findViewById (R.id.n3);
        entry3 = (EditText) findViewById(R.id.e3);

        send = (Button) findViewById(R.id.send);

        send.setOnClickListener(this);

    }

    public static String gtext (EditText v) {
        String data =  v.getText().toString() ;
        return data;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.send:

                Intent intent = new Intent (this ,confirmation.class);

                intent.putExtra("Team-name",gtext(tname));
                intent.putExtra("user1", gtext(name1));
                intent.putExtra("user2", gtext(name2));
                intent.putExtra("user3", gtext(name3));

                intent.putExtra("entry1", gtext(entry1));
                intent.putExtra("entry2", gtext(entry2));
                intent.putExtra("entry3", gtext(entry3));
                startActivity(intent);

                break;

        }
    }




}

