package com.helpmesonteam.helpmeson.activity;

import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.framework.utils.DeviceManager;
import com.android.framework.utils.Utils;
import com.android.helpmeson.R;
import com.android.network.listener.ServiceResponseListener;
import com.android.theme.activity.BaseActivity;
import com.android.theme.utils.Constants;
import com.helpmesonteam.helpmeson.Fragments.helpDialogFragment;
import com.helpmesonteam.helpmeson.manager.ServiceManager;
import com.helpmesonteam.helpmeson.model.RequestHelp;
import com.loopj.android.http.RequestParams;

public class HelpSelection extends BaseActivity implements View.OnClickListener {
    //public static int[] clicked;
    ImageView jobs, food, health, clothes, emergency, shelter;
    //RelativeLayout jobs_rl;
    String url = "http://cftd.in/webtecno_admin/register_data.php";

    private String typeOfHelp = null;

    private Context context;

    Button nxt;

    private boolean isService = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_selection);
        context = HelpSelection.this;
        //clicked = new int[6];
        // jobs_rl=(RelativeLayout)findViewById(R.id.jobs_rl);
        jobs = (ImageView) findViewById(R.id.jobs);
        food = (ImageView) findViewById(R.id.food);
        health = (ImageView) findViewById(R.id.health_care);
        clothes = (ImageView) findViewById(R.id.clothes);
        emergency = (ImageView) findViewById(R.id.emergency_money);
        shelter = (ImageView) findViewById(R.id.shelter);
      //  nxt = (Button) findViewById(R.id.nxt);

        jobs.setOnClickListener(this);
        food.setOnClickListener(this);
        health.setOnClickListener(this);
        clothes.setOnClickListener(this);
        emergency.setOnClickListener(this);
       // nxt.setOnClickListener(this);
        //   shelter.setOnClickListener(this);
        // for (int i = 0; i <= 4; i++)
        //    clicked[i] = 0;

    }

    @Override
    public void onClick(View v) {
        int k = v.getId();
        switch (k) {
            case R.id.jobs:
               /* if (clicked[0] == 0) {
                    jobs.setAlpha((float) 0.2);
                    clicked[0] = 1;
                } else {
                    jobs.setAlpha((float) 1.0);
                    clicked[0] = 0;
                }*/
                typeOfHelp = "jobs";
                showAlert(HelpSelection.this,getString(R.string.alert),getString(R.string.alert_message) + " jobs", getString(R.string.Yes), yes,getString(R.string.No),No);
                break;
            case R.id.food:
               /* if (clicked[1] == 0) {
                    food.setAlpha((float) 0.2);
                    clicked[1] = 1;
                } else {
                    food.setAlpha((float) 1.0);
                    clicked[1] = 0;
                }*/
                typeOfHelp = "food";
                showAlert(HelpSelection.this,getString(R.string.alert),getString(R.string.alert_message) + " food", getString(R.string.Yes), yes,getString(R.string.No),No);


                break;
            case R.id.health_care:
                /*if (clicked[2] == 0) {
                    health.setAlpha((float) 0.2);
                    clicked[2] = 1;
                } else {
                    health.setAlpha((float) 1.0);
                    clicked[2] = 0;
                }*/
                typeOfHelp = "health care";
                showAlert(HelpSelection.this,getString(R.string.alert),getString(R.string.alert_message) + " health care", getString(R.string.Yes), yes,getString(R.string.No),No);


                break;
            case R.id.clothes:
                /*if (clicked[3] == 0) {
                    clothes.setAlpha((float) 0.2);
                    clicked[3] = 1;
                } else {
                    clothes.setAlpha((float) 1.0);
                    clicked[3] = 0;
                }
                ;*/
                typeOfHelp = "clothes";
                showAlert(HelpSelection.this,getString(R.string.alert),getString(R.string.alert_message) + " clothes", getString(R.string.Yes), yes,getString(R.string.No),No);

                break;
            case R.id.emergency_money:
               /* if (clicked[4] == 0) {
                    emergency.setAlpha((float) 0.2);
                    clicked[4] = 1;
                } else {
                    emergency.setAlpha((float) 1.0);
                    clicked[4] = 0;
                }
                ;*/
                typeOfHelp = "emergency money";
                showAlert(HelpSelection.this,getString(R.string.alert),getString(R.string.alert_message) + " emergency money", getString(R.string.Yes), yes,getString(R.string.No),No);

                break;
          /*  case R.id.nxt:
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
                break;*/
        }
    }


    DialogInterface.OnClickListener yes = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {

            RequestParams requestParams = new RequestParams();
            SharedPreferences preferences = getSharedPreferences("Pref", MODE_APPEND);
            requestParams.put("name",preferences.getString("name"," "));
            requestParams.put("age",preferences.getString("age"," "));
            requestParams.put("state",preferences.getString("state","TEST"));
            requestParams.put("city",preferences.getString("city"," "));
            requestParams.put("address",preferences.getString("address"," "));
            requestParams.put("reference_no",preferences.getString("reference_number"," "));
            requestParams.put("type_of_help",typeOfHelp);

            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("third", true);
            editor.apply();
            editor.commit();

            if (DeviceManager.isDeviceOffline(context)) {
                showAlert(context, getResources().getString(R.string.title_of_data_connection), getResources().getString(R.string.title_of_data_message),
                        getResources().getString(R.string.ok), null, null, null);
                return;
            }
            mainCategoryHandler.sendEmptyMessage(Constants.SHOW_PROGRESS_DIALOG);
            if (!isService) {
                ServiceManager.registerRequest(getApplicationContext(), requestParams, new ServiceResponseListener<RequestHelp>() {
                            @Override
                            public void onSuccess(RequestHelp response) {
                                isService = false;
                                mainCategoryHandler.sendEmptyMessage(Constants.DISMISS_PROGRESS_DIALOG);

                                if (!response.isError()) {
                                    helpDialogFragment dialog=new helpDialogFragment();
                                    FragmentManager manager = getFragmentManager();
                                    dialog.show(manager, "HelpSelection");

                                } else if (response.isError()) {
                                    String message = response.getMessage();
                                    showAlert(context, getResources().getString(R.string.alert), message,
                                            getResources().getString(R.string.ok), null, null, null);
                                }
                            }

                            @Override
                            public void onFailure(Throwable throwable, String errorResponse) {
                                isService = false;
                                mainCategoryHandler.sendEmptyMessage(Constants.DISMISS_PROGRESS_DIALOG);
                                showAlert(context, getResources().getString(R.string.server_failed), getResources().getString(R.string.server_unavailable),
                                        getResources().getString(R.string.ok), null, null, null);
                            }
                        }

                );
            }



        }
    };


    DialogInterface.OnClickListener No = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            dialog.dismiss();
        }
    };

 /*   private void data_to_be_sent() {
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
         }*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

}


