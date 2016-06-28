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

    public EditText name,city,rnumber,address;
    String n=null,cty=null,rn=null,add=null;
    Button done;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_details);

        name=(EditText)findViewById(R.id.name);
        name.requestFocus();

        city=(EditText)findViewById(R.id.city);
     //   lname.requestFocus();

        rnumber=(EditText)findViewById(R.id.rnumber);
        address=(EditText)findViewById(R.id.address);

       // pnumber.requestFocus();
        done=(Button)findViewById(R.id.done);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                n=name.getText().toString();
                cty=city.getText().toString();
                rn=rnumber.getText().toString();
                add=address.getText().toString();

                if(n.length()>=1 && cty.length()>=1 && rn.length()>=1 && add.length()>=1)
                {
                   // SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    SharedPreferences preferences =getSharedPreferences("Pref",MODE_APPEND);
                            SharedPreferences.Editor editor = preferences.edit();
                    editor.putBoolean("second",true);
                    editor.putString("name",n);
                    editor.putString("city",cty);
                    editor.putString("reference_number",rn);
                    editor.putString("address",add);
                    editor.apply();
                    editor.commit();
                    Intent intent=new Intent(getApplicationContext(),HelpSelection.class);
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
