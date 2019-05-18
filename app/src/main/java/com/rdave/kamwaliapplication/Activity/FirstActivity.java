package com.rdave.kamwaliapplication.Activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.rdave.kamwaliapplication.R;

public class FirstActivity extends AppCompatActivity {

    Button jBtn,hBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        hBtn = (Button) findViewById(R.id.hiringbtn);
        jBtn = (Button) findViewById(R.id.jobbtn);

        Typeface myCustomFont = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Bold.ttf");
        hBtn.setTypeface(myCustomFont);
        Typeface myCustomFont2 = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Bold.ttf");
        jBtn.setTypeface(myCustomFont2);

        hBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent h =new  Intent(FirstActivity.this, C_listActivity.class);
                startActivity(h);
            }
        });


    }
}
