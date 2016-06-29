package com.example.helpmeson;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class HelpSelection extends AppCompatActivity implements View.OnClickListener{
    int[] clicked;
    ImageView jobs,food,health,clothes,emergency,shelter;
    Button nxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_selection);
        clicked=new int[6];
        jobs=(ImageView)findViewById(R.id.jobs);
        food=(ImageView)findViewById(R.id.food);
        health=(ImageView)findViewById(R.id.health_care);
        clothes=(ImageView)findViewById(R.id.clothes);
        emergency=(ImageView)findViewById(R.id.emergency_money);
        shelter=(ImageView)findViewById(R.id.shelter);
        nxt=(Button)findViewById(R.id.nxt);

        jobs.setOnClickListener(this);
        food.setOnClickListener(this);
        health.setOnClickListener(this);
        clothes.setOnClickListener(this);
        emergency.setOnClickListener(this);
        nxt.setOnClickListener(this);
     //   shelter.setOnClickListener(this);
        for(int i=0;i<=4;i++)
            clicked[i]=0;

    }

    @Override
    public void onClick(View v) {
    int k=v.getId();
        switch (k){
            case R.id.jobs:
                if(clicked[0]==0) {
                    jobs.setAlpha((float) 0.2);
                    clicked[0] = 1;
                }
                else{
                jobs.setAlpha((float) 1.0);
                clicked[0]=0;
            }
                break;
            case R.id.food:
                if(clicked[1]==0) {
                    food.setAlpha((float) 0.2);
                    clicked[1] = 1;
                }
                else{
                    food.setAlpha((float) 1.0);
                    clicked[1]=0;
                };break;
            case R.id.health_care:
                if(clicked[2]==0) {
                    health.setAlpha((float) 0.2);
                    clicked[2] = 1;
                }
                else{
                    health.setAlpha((float) 1.0);
                    clicked[2]=0;
                }
                ;break;
            case R.id.clothes:
                if(clicked[3]==0) {
                    clothes.setAlpha((float) 0.2);
                    clicked[3] = 1;
                }
                else{
                    clothes.setAlpha((float) 1.0);
                    clicked[3]=0;
                };break;
            case R.id.emergency_money:
                if(clicked[4]==0) {
                    emergency.setAlpha((float) 0.2);
                    clicked[4] = 1;
                }
                else{
                    emergency.setAlpha((float) 1.0);
                    clicked[4]=0;
                };break;
            case R.id.nxt:
                SharedPreferences preferences =getSharedPreferences("Pref",MODE_APPEND);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean("third",true);
                editor.apply();
                editor.commit();

                Intent intent=new Intent(getApplicationContext(),HomeActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }
}
