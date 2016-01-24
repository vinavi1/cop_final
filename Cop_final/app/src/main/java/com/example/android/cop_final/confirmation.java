package com.example.android.cop_final;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


// final activity which corresponds to the details entered 
public class confirmation extends Activity implements View.OnClickListener{

    RequestQueue requestQueue;


    Button confirm;
    TextView team;
    TextView tn1;
    TextView te1;
    TextView tn2;
    TextView te2;
    TextView tn3;
    TextView te3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);

        requestQueue = Volley.newRequestQueue(this);
        team = (TextView) findViewById(R.id.tTeam);
        tn1 = (TextView) findViewById(R.id.tn1);
        tn2 = (TextView) findViewById(R.id.tn2);
        tn3 = (TextView) findViewById(R.id.tn3);
        te1 = (TextView) findViewById(R.id.te1);
        te2 = (TextView) findViewById(R.id.te2);
        te3 = (TextView) findViewById(R.id.te3);

        confirm = (Button) findViewById(R.id.confirm);

        confirm.setOnClickListener(this);
        Intent intent = getIntent();

        if(null != intent) {
            team.setText(intent.getStringExtra("Team-name"));
            tn1.setText(intent.getStringExtra("user1"));
            tn2.setText(intent.getStringExtra("user2"));
            tn3.setText(intent.getStringExtra("user3"));
            te1.setText(intent.getStringExtra("entry1"));
            te2.setText(intent.getStringExtra("entry2"));
            te3.setText(intent.getStringExtra("entry3"));

        }


    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.confirm:

                StringRequest stringRequest = new StringRequest(Request.Method.POST,"http://agni.iitd.ernet.in/cop290/assign0/register/",
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Toast.makeText(confirmation.this,response,Toast.LENGTH_LONG).show();
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(confirmation.this,"No Network Connection",Toast.LENGTH_LONG).show();
                            }
                        }){
                    @Override
                    protected Map<String,String> getParams(){
                        Map<String,String> params = new HashMap<String, String>();
                        params.put("teamname",team.getText().toString().trim());
                        params.put("entry1", te1.getText().toString().trim());
                        params.put("name1",tn1.getText().toString().trim());
                        params.put("entry2",te2.getText().toString().trim());
                        params.put("name2",tn2.getText().toString().trim());
                        params.put("entry3",te3.getText().toString().trim());
                        params.put("name3",tn3.getText().toString().trim());

                        return params;
                    }

                };

                RequestQueue requestQueue = Volley.newRequestQueue(this);
                requestQueue.add(stringRequest);

                break;

        }
    }



}
