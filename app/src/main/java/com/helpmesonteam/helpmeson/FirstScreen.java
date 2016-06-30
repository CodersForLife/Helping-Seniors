package com.helpmesonteam.helpmeson;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.android.helpmeson.R;

public class FirstScreen extends Activity {
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
                    editor.putString("phone_number",number);
                    editor.apply();
                    editor.commit();
                    Intent i=new Intent(getApplicationContext(),AddDetails.class);
                    startActivity(i);
                    finish();
                }
                return false;
            }
        });
    }
}
