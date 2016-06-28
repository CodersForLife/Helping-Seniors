package com.example.helpmeson;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class HelpSelection extends AppCompatActivity implements View.OnClickListener{
    ImageView jobs,food,health,clothes,emergency,shelter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_selection);

        jobs=(ImageView)findViewById(R.id.jobs);
        food=(ImageView)findViewById(R.id.food);
        health=(ImageView)findViewById(R.id.health_care);
        clothes=(ImageView)findViewById(R.id.clothes);
        emergency=(ImageView)findViewById(R.id.emergency_money);
        shelter=(ImageView)findViewById(R.id.shelter);

        jobs.setOnClickListener(this);
        food.setOnClickListener(this);
        health.setOnClickListener(this);
        clothes.setOnClickListener(this);
        emergency.setOnClickListener(this);
        shelter.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
    int k=v.getId();
        switch (k){
            case R.id.jobs:jobs.setAlpha((float) 0.2);
                break;
        }
    }
}
