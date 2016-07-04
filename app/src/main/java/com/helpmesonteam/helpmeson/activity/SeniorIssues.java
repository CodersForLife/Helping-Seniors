package com.helpmesonteam.helpmeson.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.helpmeson.R;
import com.android.theme.activity.BaseActivity;
import com.helpmesonteam.helpmeson.Fragments.SeniorIssuesFragment;

public class SeniorIssues extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_senior_issues);
        SeniorIssuesFragment fragment=new SeniorIssuesFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.frame_layout_to_be_replaced_by_fragment,fragment).commit();

    }
    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0 ){
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }

    }
}