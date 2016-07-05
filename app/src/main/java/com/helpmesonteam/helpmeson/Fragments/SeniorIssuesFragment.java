package com.helpmesonteam.helpmeson.Fragments;

import android.content.Context;
import android.net.Uri;
import com.android.helpmeson.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SeniorIssuesFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SeniorIssuesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SeniorIssuesFragment extends Fragment implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    TextView health,active,financial,wills,abuse,rights;
    private OnFragmentInteractionListener mListener;

    public SeniorIssuesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SeniorIssuesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SeniorIssuesFragment newInstance(String param1, String param2) {
        SeniorIssuesFragment fragment = new SeniorIssuesFragment();
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
        View v= inflater.inflate(R.layout.fragment_senior_issues, container, false);

        health=(TextView) v.findViewById(R.id.health);
        active=(TextView) v.findViewById(R.id.activeageing);
        financial=(TextView)v.findViewById(R.id.financialplanning);
        wills=(TextView) v.findViewById(R.id.willslegacies);
        abuse=(TextView) v.findViewById(R.id.elderabuse);
        rights=(TextView) v.findViewById(R.id.rightsenlightenments);

        health.setOnClickListener(this);
        active.setOnClickListener(this);
        financial.setOnClickListener(this);
        wills.setOnClickListener(this);
        abuse.setOnClickListener(this);
        rights.setOnClickListener(this);
        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
       /* if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {
    int id=v.getId();
        clickDetailFragment frag=new clickDetailFragment();
        switch (id){
            case R.id.health:
                Bundle b=new Bundle();
                b.putInt("image_clicked",1);
                frag.setArguments(b);
                getFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.frame_layout_to_be_replaced_by_fragment,frag).commit();
                break;
            case R.id.activeageing:
                Bundle c=new Bundle();
                c.putInt("image_clicked",2);
                frag.setArguments(c);
                getFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.frame_layout_to_be_replaced_by_fragment,frag).commit();
                break;
            case R.id.financialplanning:
                Bundle d=new Bundle();
                d.putInt("image_clicked",3);
                frag.setArguments(d);
                getFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.frame_layout_to_be_replaced_by_fragment,frag).commit();
                break;
            case R.id.willslegacies:
                Bundle e=new Bundle();
                e.putInt("image_clicked",4);
                frag.setArguments(e);
                getFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.frame_layout_to_be_replaced_by_fragment,frag).commit();
                break;
            case R.id.elderabuse:
                Bundle f=new Bundle();
                f.putInt("image_clicked",5);
                frag.setArguments(f);
                getFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.frame_layout_to_be_replaced_by_fragment,frag).commit();
                break;
            case R.id.rightsenlightenments:
                Bundle g=new Bundle();
                g.putInt("image_clicked",6);
                frag.setArguments(g);
                getFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.frame_layout_to_be_replaced_by_fragment,frag).commit();
                break;
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

}
