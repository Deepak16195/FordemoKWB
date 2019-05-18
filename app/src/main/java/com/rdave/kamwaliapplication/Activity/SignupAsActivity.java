package com.rdave.kamwaliapplication.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.rdave.kamwaliapplication.R;

public class SignupAsActivity extends AppCompatActivity {

    Button candi,employ,agent;
    String st1,st2,st3;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_as);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitle("Sign Up As");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                Intent i = new Intent(SignupAsActivity.this,LoginActivity.class);
                startActivity(i);

            }
        });
        candi = (Button) findViewById(R.id.candi_btn);
        employ = (Button) findViewById(R.id.employ_btn);
        agent = (Button) findViewById(R.id.agent_btn);
         st1 = employ.getText().toString();
         st2 = candi.getText().toString();
         st3 = agent.getText().toString();


        candi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent c = new Intent(SignupAsActivity.this,SignUp.class);
                c.putExtra("V1",st2);
                startActivity(c);
            }
        });
        employ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent e = new Intent(SignupAsActivity.this,SignUp.class);
                e.putExtra("V1",st1);
                startActivity(e);
            }
        });
        agent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(SignupAsActivity.this,SignUp.class);
                a.putExtra("V1",st3);
                startActivity(a);
            }
        });




    }
}
