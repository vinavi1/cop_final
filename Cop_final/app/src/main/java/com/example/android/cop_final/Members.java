package com.example.android.cop_final;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Members extends AppCompatActivity {

    //declaring image buttons for choosing 2 or 3 for team
    ImageButton mem2;
    ImageButton mem3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_members);
        addListenerOnButton();
    }

    
    public void addListenerOnButton() {

        mem2 = (ImageButton) findViewById(R.id.mem2);
        mem3 = (ImageButton) findViewById(R.id.mem3);

        mem2.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View arg0) {

                Intent intent2 = new Intent(Members.this, MainActivity.class);
                intent2.putExtra("bool","mem2");
                startActivity(intent2);

            }

        });

        mem3.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View arg0) {

                Intent intent3 = new Intent(Members.this, MainActivity.class);
                intent3.putExtra("bool","mem3");
                startActivity(intent3);

            }

        });

    }
}
