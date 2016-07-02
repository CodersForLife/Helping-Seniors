package com.helpmesonteam.helpmeson;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import android.support.design.widget.NavigationView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.android.helpmeson.R;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    private DrawerLayout homeDrawerLayout;
    private NavigationView homeNavigationView;
    private View homeNavigationHeaderView;
    private Toolbar homeTopToolbar;
    private FragmentTransaction fragmentTransaction;
    private LinearLayout about,story,call,mail,rate;
    Button btn1;
    Button btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fl_home_screen_frame_layout, new HomeFragment());
        fragmentTransaction.commit();

        homeNavigationView = (NavigationView) findViewById(R.id.nv_home_screen_navigation_view);
        homeNavigationHeaderView = homeNavigationView.getHeaderView(0);
        homeDrawerLayout = (DrawerLayout) findViewById(R.id.dl_home_screen_drawer_layout);
        homeTopToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(homeTopToolbar);
        about= (LinearLayout) homeNavigationHeaderView.findViewById(R.id.abt_hlp);
        story= (LinearLayout) homeNavigationHeaderView.findViewById(R.id.top_sto);
        call= (LinearLayout) homeNavigationHeaderView.findViewById(R.id.call);
        mail= (LinearLayout) homeNavigationHeaderView.findViewById(R.id.mail);
        rate= (LinearLayout) homeNavigationHeaderView.findViewById(R.id.rate);

        final SharedPreferences sp=getSharedPreferences("Pref",MODE_PRIVATE);
        Log.e("kk",sp.getBoolean("fourth",true)+"");
        if (sp.getBoolean("fourth",true)){
            helpDialogFragment dialog=new helpDialogFragment();
            FragmentManager manager = getFragmentManager();
            dialog.show(manager,"fdfd");
            SharedPreferences preferences = getSharedPreferences("Pref", MODE_APPEND);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("fourth", false);
            editor.apply();
            editor.commit();
        }
        else{
        }
        about.setOnClickListener(this);
        story.setOnClickListener(this);
        call.setOnClickListener(this);
        mail.setOnClickListener(this);
        rate.setOnClickListener(this);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, homeDrawerLayout, homeTopToolbar, R.string.openDrawer, R.string.closeDrawer) {
            @Override
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
            }
            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank
                super.onDrawerOpened(drawerView);
            }
        };
        //Setting the actionbarToggle to drawer layout
        homeDrawerLayout.setDrawerListener(actionBarDrawerToggle);
        //calling sync state is necessary or else your hamburger icon wont show up
        actionBarDrawerToggle.syncState();
       /* btn1 = (Button) findViewById(R.id.button2);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              *//*  Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:+911204270411"));
                startActivity(intent);*//*
                helpDialogFragment dialog=new helpDialogFragment();
                FragmentManager manager = getFragmentManager();
                dialog.show(manager,"fdfd");
            }
        });

        btn2 = (Button) findViewById(R.id.button);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto", "info@helpmeson.in", null));
                intent.putExtra(Intent.EXTRA_SUBJECT, "Need Help");
                startActivity(Intent.createChooser(intent, "Choose an Email client :"));
            }
        });*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement

        switch (id) {

            case R.id.edit_detail:
                Intent intent = new Intent(HomeActivity.this, FirstScreen.class);
                startActivity(intent);
                return true;
            case R.id.ambulance:
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:102"));
                try {
                    startActivity(callIntent);
                }catch (Exception e){
                    e.printStackTrace();
                }
                return true;
            case R.id.fire:
                Intent callIntent2 = new Intent(Intent.ACTION_DIAL);
                callIntent2.setData(Uri.parse("tel:101"));
                try {
                    startActivity(callIntent2);
                }catch (Exception e){
                    e.printStackTrace();
                }
                return true;

            case R.id.police:
                Intent callIntent3 = new Intent(Intent.ACTION_DIAL);
                callIntent3.setData(Uri.parse("tel:100"));
                try {
                    startActivity(callIntent3);
                }catch (Exception e){
                    e.printStackTrace();
                }
                return true;
            case R.id.helpmeson:
                Intent callIntent4 = new Intent(Intent.ACTION_DIAL);
                callIntent4.setData(Uri.parse("tel:+911204270411"));
                try {
                    startActivity(callIntent4);
                }catch (Exception e){
                    e.printStackTrace();
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if(v == about){
            Intent i=new Intent(this,SeniorIssues.class);
            startActivity(i);
        }
        else if(v == story){

        }
        else if(v == call){
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:+911204270411"));
            startActivity(intent);
        }
        else if(v == mail){
            Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                    "mailto", "info@helpmeson.in", null));
            intent.putExtra(Intent.EXTRA_SUBJECT, "Need Help");
            startActivity(Intent.createChooser(intent, "Choose an Email client :"));
        }
        else if (v == rate){
            Uri uri = Uri.parse("market://details?id=" + getPackageName());
            Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
            // To count with Play market backstack, After pressing back button,
            // to taken back to our application, we need to add following flags to intent.
            goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                    Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET |
                    Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
            try {
                startActivity(goToMarket);
            } catch (ActivityNotFoundException e) {
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://play.google.com/store/apps/details?id=" + getPackageName())));
            }
        }
    }
}
