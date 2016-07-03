package com.helpmesonteam.helpmeson;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.helpmeson.R;

import im.delight.android.location.SimpleLocation;

public class InstaHelp extends AppCompatActivity {
    Button wait_for_help,direct_help;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insta_help);
        wait_for_help=(Button)findViewById(R.id.wait_for_help);
        direct_help=(Button)findViewById(R.id.direct_help);

        wait_for_help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(InstaHelp.this,AddDetails.class);
                startActivity(i);
            }
        });

        direct_help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              /*  GPSTracker gpsTracker=new GPSTracker(InstaHelp.this);
                if(gpsTracker.canGetLocation()){
                    String lattitude=String.valueOf(gpsTracker.getLatitude());
                    String longitude=String.valueOf(gpsTracker.getLongitude());
                    String country=gpsTracker.getCountryName(InstaHelp.this);
                    String city=gpsTracker.getLocality(InstaHelp.this);
                    String postol_code=gpsTracker.getPostalCode(InstaHelp.this);
                    String addressLine=gpsTracker.getAddressLine(InstaHelp.this);

                    Log.e("add",lattitude+" "+longitude+""+country+""+city+""+postol_code+""+addressLine);
                }
                else {
                    gpsTracker.showSettingsAlert();
                }*/

                SimpleLocation location=new SimpleLocation(InstaHelp.this);
                if (!location.hasLocationEnabled()) {
                    // ask the user to enable location access
                    SimpleLocation.openSettings(InstaHelp.this);
                }
                final double latitude = location.getLatitude();
                final double longitude = location.getLongitude();
                Log.e("loc",latitude+" "+longitude);
                SharedPreferences preferences = getSharedPreferences("Pref", MODE_APPEND);
               String number= preferences.getString("phone_number","");
            }
        });
    }
}
