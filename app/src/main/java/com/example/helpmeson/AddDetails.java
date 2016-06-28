package com.example.helpmeson;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddDetails extends AppCompatActivity {

    public EditText fname,lname,pnumber;
    String fn=null,ln=null,pn=null;
    Button done;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_details);

        fname=(EditText)findViewById(R.id.fname);
        fname.requestFocus();

        lname=(EditText)findViewById(R.id.lname);
     //   lname.requestFocus();

        pnumber=(EditText)findViewById(R.id.pnumber);

       // pnumber.requestFocus();
        done=(Button)findViewById(R.id.done);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fn=fname.getText().toString();
                ln=lname.getText().toString();
                pn=pnumber.getText().toString();

                if(fn.length()>=1 && ln.length()>=1 && pn.length()>=1)
                {
                   // SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    SharedPreferences preferences =getSharedPreferences("Pref",MODE_PRIVATE);
                            SharedPreferences.Editor editor = preferences.edit();
                    editor.putBoolean("first",true);
                    editor.putString("first_name",fn);
                    editor.putString("last_name",ln);
                    editor.putString("phone_number",pn);
                    editor.apply();
                    editor.commit();
                    Intent intent=new Intent(getApplicationContext(),HomeActivity.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    Toast.makeText(getApplicationContext(),"You have left some fields empty",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
