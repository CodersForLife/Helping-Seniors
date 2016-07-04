package com.helpmesonteam.helpmeson.Fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.android.helpmeson.R;
import com.helpmesonteam.helpmeson.activity.InstaHelpDialogActivity;


public class HomeFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    LinearLayoutManager llm;
    FloatingActionButton add_help;
    TextView network_message;

    private WebView webview;

    private ProgressDialog progressDialog;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        String[] dataset={"STARTUP ADVICE","RESOURCES","NEWS","STORIES"};
        View v= inflater.inflate(R.layout.fragment_home, container, false);
        add_help=(FloatingActionButton)v.findViewById(R.id.add_new_help);
        network_message=(TextView)v.findViewById(R.id.connection_msg);

        webview = (WebView) v.findViewById(R.id.webView);
        progressDialog=new ProgressDialog(getActivity());
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Please wait...");
        progressDialog.setCancelable(false);


        
       if( isInternetAvailable(getActivity().getApplicationContext()))
       {
           //loading help me son blog

           webview.setWebViewClient(new WebViewClient() {

               @Override
               public void onPageStarted(WebView view, String url, Bitmap facIcon) {
                   progressDialog.show();
                   webview.setVisibility(View.GONE);
               }

               @Override
               public void onPageFinished(WebView view, String url) {
                   // hide element by class name

                   webview.setVisibility(View.VISIBLE);
                   progressDialog.dismiss();

               }
           });


           webview.loadUrl("http://helpmeson.in/blog");
       }

        else {
           network_message.setVisibility(View.VISIBLE);
           add_help.setVisibility(View.GONE);
       }

        add_help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity().getApplicationContext(), InstaHelpDialogActivity.class);
                startActivity(i);
            }
        });
        return v;
    }

    private boolean isInternetAvailable(Context context) {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getView().setFocusableInTouchMode(true);
        getView().requestFocus();

        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK) {
                        if (webview.canGoBack()) {
                            webview.goBack();
                        } else {
                            getActivity().finish();
                        }
                        return true;
                    }
                }
                return false;
            }
        });
    }
}
    // TODO: Rename method, update argument and hook method into UI event

