package com.helpmesonteam.helpmeson;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.helpmeson.R;
import com.helpmesonteam.helpmeson.Fragments.SeniorIssuesFragment;

public class SeniorIssues extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_senior_issues);
        SeniorIssuesFragment fragment=new SeniorIssuesFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.frame_layout_to_be_replaced_by_fragment,fragment).commit();
    }
}
