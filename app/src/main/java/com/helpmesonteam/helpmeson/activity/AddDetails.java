package com.helpmesonteam.helpmeson.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.helpmeson.R;
import com.android.theme.activity.BaseActivity;
import com.helpmesonteam.helpmeson.activity.HelpSelection;

public class AddDetails extends BaseActivity {

    public EditText name,rnumber,address;
    String n=null,cty,rn=null,add=null,age = null, state = null;
    String[] cities;
    String[] states;
    Spinner spinner;
    Button done;

    //private Spinner statesSpinner;
    private EditText ageEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_details);
        setTitle(R.string.help_request);

        cities = getResources().getStringArray(R.array.cities_list);
        states = getResources().getStringArray(R.array.states_list);
        spinner = (Spinner) findViewById(R.id.city_spinner);

       // statesSpinner = (Spinner) findViewById(R.id.state_spinner);

        ageEditText = (EditText) findViewById(R.id.age_edit_text);

        ArrayAdapter<String> stateAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, states);
        stateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
       /* statesSpinner.setAdapter(stateAdapter);

        statesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                state = states[statesSpinner.getSelectedItemPosition()];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });*/


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
                age = ageEditText.getText().toString();


                if(n.length()>=1 && cty.length()>=1 && rn.length()>=1 && add.length()>=1)
                {
                   // SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    SharedPreferences preferences =getSharedPreferences("Pref",MODE_APPEND);
                            SharedPreferences.Editor editor = preferences.edit();
                    editor.putBoolean("second",true);
                    editor.putString("name", n);
                    editor.putString("city", cty);
                    editor.putString("reference_number",rn);
                    editor.putString("address",add);
                    editor.putString("age",age);
                    editor.putString("state",state);
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


        SharedPreferences preferences =getSharedPreferences("Pref",MODE_APPEND);
        if(preferences.getBoolean("second",false)){
            if(preferences.contains("name"))
                name.setText(preferences.getString("name",""));

            if(preferences.contains("city"))
            {
                for(int i=0;i<cities.length;i++)
                {
                    if(cities[i].equalsIgnoreCase(preferences.getString("city","")))
                    {
                        spinner.setSelection(i);
                        cty=preferences.getString("city","");
                    }

                }

            }

            if(preferences.contains("address"))
                address.setText(preferences.getString("address",""));
            if(preferences.contains("reference_number"))
                rnumber.setText(preferences.getString("reference_number",""));

            if(preferences.contains("age")){
                ageEditText.setText(preferences.getString("age",""));
            }

           /* if(preferences.contains("state")){
                for(int i=0;i<cities.length;i++)
                {
                    if(cities[i].equalsIgnoreCase(preferences.getString("state","")))
                    {
                        statesSpinner.setSelection(i);
                        cty=preferences.getString("city","");
                    }

                }
            }*/
        }


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
