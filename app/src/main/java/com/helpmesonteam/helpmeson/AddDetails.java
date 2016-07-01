package com.helpmesonteam.helpmeson;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.helpmeson.R;

public class AddDetails extends AppCompatActivity {

    public EditText name,rnumber,address;
    String n=null,cty,rn=null,add=null;
    String[] cities;
    Spinner spinner;
    Button done;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_details);

        cities = getResources().getStringArray(R.array.cities_list);
        spinner = (Spinner) findViewById(R.id.city_spinner);

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, cities);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                cty = cities[spinner.getSelectedItemPosition()];
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });

        name=(EditText)findViewById(R.id.name);
        name.requestFocus();

     //   city=(EditText)findViewById(R.id.city);
     //   lname.requestFocus();

        rnumber=(EditText)findViewById(R.id.rnumber);
        address=(EditText)findViewById(R.id.address);

       // pnumber.requestFocus();
        done=(Button)findViewById(R.id.done);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                n=name.getText().toString();
      //          cty=city.getText().toString();
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
