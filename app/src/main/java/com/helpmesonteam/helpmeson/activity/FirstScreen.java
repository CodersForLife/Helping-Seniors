package com.helpmesonteam.helpmeson.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.android.helpmeson.R;
import com.android.theme.activity.BaseActivity;

public class FirstScreen extends BaseActivity {
    EditText mnumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_screen);
        mnumber=(EditText)findViewById(R.id.mnumber);
       // mnumber.setText();
        mnumber.append("+91");
//        mnumber.moveCursorToVisibleOffset();
        mnumber.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                    String number=mnumber.getText().toString();
                    SharedPreferences preferences =getSharedPreferences("Pref",MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putBoolean("first",true);
                    editor.putBoolean("second",true);
                    editor.putBoolean("third",true);
                    editor.putString("phone_number",number);
                    editor.apply();
                    editor.commit();
                  //  Intent i=new Intent(getApplicationContext(),AddDetails.class);
                    Intent i=new Intent(getApplicationContext(),HomeActivity.class);
                    startActivity(i);
                    finish();
                }
                return false;
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
