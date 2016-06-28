package com.example.helpmeson;

import android.app.Activity;
import android.content.SharedPreferences;

import android.os.Bundle;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences sp=getSharedPreferences("Pref",MODE_PRIVATE);
        sp.contains("first");
    }
}
