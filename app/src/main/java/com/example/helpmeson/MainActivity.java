package com.example.helpmeson;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;

import android.os.Bundle;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences sp=getSharedPreferences("Pref",MODE_PRIVATE);
        if(sp.contains("first")){
            Intent intent=new Intent(MainActivity.this,HomeActivity.class);
            startActivity(intent);
        }
        else{
            Intent intent=new Intent(MainActivity.this,AddDetails.class);
            startActivity(intent);
        }
    }
}
