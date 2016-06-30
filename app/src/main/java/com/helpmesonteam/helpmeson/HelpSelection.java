package com.helpmesonteam.helpmeson;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.helpmeson.R;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class HelpSelection extends AppCompatActivity implements View.OnClickListener {
    public static int[] clicked;
    ImageView jobs, food, health, clothes, emergency, shelter;
    //RelativeLayout jobs_rl;
    String url = "http://cftd.in/webtecno_admin/register_data.php";
    Button nxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_selection);
        clicked = new int[6];
        // jobs_rl=(RelativeLayout)findViewById(R.id.jobs_rl);
        jobs = (ImageView) findViewById(R.id.jobs);
        food = (ImageView) findViewById(R.id.food);
        health = (ImageView) findViewById(R.id.health_care);
        clothes = (ImageView) findViewById(R.id.clothes);
        emergency = (ImageView) findViewById(R.id.emergency_money);
        shelter = (ImageView) findViewById(R.id.shelter);
        nxt = (Button) findViewById(R.id.nxt);

        jobs.setOnClickListener(this);
        food.setOnClickListener(this);
        health.setOnClickListener(this);
        clothes.setOnClickListener(this);
        emergency.setOnClickListener(this);
        nxt.setOnClickListener(this);
        //   shelter.setOnClickListener(this);
        for (int i = 0; i <= 4; i++)
            clicked[i] = 0;

    }

    @Override
    public void onClick(View v) {
        int k = v.getId();
        switch (k) {
            case R.id.jobs:
                if (clicked[0] == 0) {
                    jobs.setAlpha((float) 0.2);
                    clicked[0] = 1;
                } else {
                    jobs.setAlpha((float) 1.0);
                    clicked[0] = 0;
                }
                break;
            case R.id.food:
                if (clicked[1] == 0) {
                    food.setAlpha((float) 0.2);
                    clicked[1] = 1;
                } else {
                    food.setAlpha((float) 1.0);
                    clicked[1] = 0;
                }
                ;
                break;
            case R.id.health_care:
                if (clicked[2] == 0) {
                    health.setAlpha((float) 0.2);
                    clicked[2] = 1;
                } else {
                    health.setAlpha((float) 1.0);
                    clicked[2] = 0;
                }
                ;
                break;
            case R.id.clothes:
                if (clicked[3] == 0) {
                    clothes.setAlpha((float) 0.2);
                    clicked[3] = 1;
                } else {
                    clothes.setAlpha((float) 1.0);
                    clicked[3] = 0;
                }
                ;
                break;
            case R.id.emergency_money:
                if (clicked[4] == 0) {
                    emergency.setAlpha((float) 0.2);
                    clicked[4] = 1;
                } else {
                    emergency.setAlpha((float) 1.0);
                    clicked[4] = 0;
                }
                ;
                break;
            case R.id.nxt:
                if(clicked[0]==1 ||clicked[1]==1 ||clicked[2]==1 ||clicked[3]==1 ||clicked[4]==1 ){
                    SharedPreferences preferences = getSharedPreferences("Pref", MODE_APPEND);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putBoolean("third", true);
                    editor.apply();
                    editor.commit();
                    data_to_be_sent();
                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(intent);
                    finish();
                }
            else
                Toast.makeText(getApplicationContext(),"Select type of help you need",Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void data_to_be_sent() {
        SharedPreferences preferences = getSharedPreferences("Pref", MODE_APPEND);
        JSONObject obj=new JSONObject();
        try {
            obj.put("name", preferences.getString("name", ""));
            obj.put("city", preferences.getString("city", ""));
            obj.put("address", preferences.getString("address", ""));
            obj.put("phone", preferences.getString("phone_number", ""));

            if (clicked[0] == 1)
                obj.put("job", "1");
            else
                obj.put("job", "0");


            if (clicked[1] == 1)
                obj.put("food", "1");
            else
                obj.put("food", "0");


            if (clicked[2] == 1)
                obj.put("health", "1");
            else
                obj.put("health", "0");


            if (clicked[3] == 1)
                obj.put("cloths", "1");
            else
                obj.put("cloths", "0");

            if (clicked[4] == 1)
                obj.put("emergency", "1");
            else
                obj.put("emergency", "0");


        } catch (JSONException e) {
            e.printStackTrace();
        }

       JsonObjectRequest req=new JsonObjectRequest(Request.Method.POST, url, obj, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if(response.getString("Response").equalsIgnoreCase("True"))
                        Log.e("HelpSection","Values added successfully");
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("HelpSection","problem");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            Log.e("HelpSection",error.getMessage().toString());
            }
        });

        RequestQueue request= Volley.newRequestQueue(HelpSelection.this);
        request.add(req);
         }
    }

