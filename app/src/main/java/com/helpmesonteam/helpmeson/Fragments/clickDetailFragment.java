package com.helpmesonteam.helpmeson.Fragments;

import android.content.Context;
import android.net.Uri;
import com.android.helpmeson.R;
import com.helpmesonteam.helpmeson.data.ActiveAgeing;
import com.helpmesonteam.helpmeson.data.ElderAbuse;
import com.helpmesonteam.helpmeson.data.FinancialPlanning;
import com.helpmesonteam.helpmeson.data.Heathdata;
import com.helpmesonteam.helpmeson.data.Rights;
import com.helpmesonteam.helpmeson.data.Wills;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link clickDetailFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link clickDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class clickDetailFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    ImageView issue_category;
    ListView listview_issue;
    int img_path;

    private OnFragmentInteractionListener mListener;

    public clickDetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment clickDetailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static clickDetailFragment newInstance(String param1, String param2) {
        clickDetailFragment fragment = new clickDetailFragment();
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
        View v= inflater.inflate(R.layout.fragment_click_detail, container, false);

        issue_category=(ImageView)v.findViewById(R.id.image_category_elder_issue);
        listview_issue=(ListView)v.findViewById(R.id.list_of_elder_click_issues);

        final String[] health={"Arithritis and Rheumatism",
                "Constipation",
                "Dementia","Dental Care","Diabetes","Eyecare","Heart Attacks and Strokes","HIV AIDS","Mental health",
                "Osteoporosis","Parkinsons Disease","Urinary Incontinence"};
        final String[] active={"Dealing with Loneliness","Healthy Ageing","Laughing your way to Health","Nutrition for Healthy Ageing"};
        final String[] fin={"Creating an Asset Register","Essential Information for the Spouse","Reverse Mortgage","Safeguarding your Property",
                "Taxation and Elderly"};
        final String[] wills={"How to prepare your will"};
        final String[] elder={"Elder Abuse"};
        final String[] rights={"Health Insurance schemes","Maintenance and Welfare of parents and Senior Citizens Act","Pension Schemes"};


        final int k=getArguments().getInt("image_clicked");
        ArrayAdapter<String> adapter = null;

        if(k==1)
        {
            adapter=new ArrayAdapter<String>(getActivity().getApplicationContext(),R.layout.list_item,R.id.text_view,health);
            img_path=R.drawable.health;
            getActivity().setTitle("Health");
        }


        if(k==2){
            adapter=new ArrayAdapter<String>(getActivity().getApplicationContext(),R.layout.list_item,R.id.text_view,active);
            img_path=R.drawable.activeageing;
            getActivity().setTitle("Active Ageing");
        }

        if(k==3){
            adapter=new ArrayAdapter<String>(getActivity().getApplicationContext(),R.layout.list_item,R.id.text_view,fin);
            img_path=R.drawable.financialplanning;
            getActivity().setTitle("Financial Planning");
        }

        if(k==4){
            adapter=new ArrayAdapter<String>(getActivity().getApplicationContext(),R.layout.list_item,R.id.text_view,wills);
            img_path=R.drawable.willslegacies;
            getActivity().setTitle("Wills and Legacies");
        }

        if(k==5){
            adapter=new ArrayAdapter<String>(getActivity().getApplicationContext(),R.layout.list_item,R.id.text_view,elder);
            img_path=R.drawable.elder_abuse;
            getActivity().setTitle("Elder Abuse");
        }

        if(k==6){
            adapter=new ArrayAdapter<String>(getActivity().getApplicationContext(),R.layout.list_item,R.id.text_view,rights);
            img_path=R.drawable.rights_entitlements;
            getActivity().setTitle("Rights and Entitlements");
        }
        listview_issue.setAdapter(adapter);
        issue_category.setBackgroundResource(img_path);
        final Bundle bundle=new Bundle();
        final FinalDetailFragment finalDetailFragment=new FinalDetailFragment();
        listview_issue.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(k==1) {
                    bundle.putString("heading",health[position]);
                    Heathdata heathdata=new Heathdata();
                    bundle.putString("data",heathdata.getstring(position));
                    finalDetailFragment.setArguments(bundle);
                    getFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.frame_layout_to_be_replaced_by_fragment,finalDetailFragment).commit();
                    Log.e("cick", health[position] + " " + position);
                }
                if(k==2) {
                    bundle.putString("heading",active[position]);
                    ActiveAgeing activeAgeing=new ActiveAgeing();
                    bundle.putString("data",activeAgeing.getstring(position));
                    finalDetailFragment.setArguments(bundle);
                    getFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.frame_layout_to_be_replaced_by_fragment,finalDetailFragment).commit();
                    Log.e("cick", active[position] + " " + position);
                }
                if(k==3) {
                    bundle.putString("heading",fin[position]);
                    FinancialPlanning financialPlanning=new FinancialPlanning();
                    bundle.putString("data",financialPlanning.getstring(position));
                    finalDetailFragment.setArguments(bundle);
                    getFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.frame_layout_to_be_replaced_by_fragment,finalDetailFragment).commit();
                    Log.e("cick", fin[position] + " " + position);
                }
                if(k==4) {
                    bundle.putString("heading",wills[position]);
                    Wills will=new Wills();
                    bundle.putString("data",will.getstring(position));
                    finalDetailFragment.setArguments(bundle);
                    getFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.frame_layout_to_be_replaced_by_fragment,finalDetailFragment).commit();
                    Log.e("cick", wills[position] + " " + position);
                }
                if(k==5) {
                    bundle.putString("heading",elder[position]);
                    ElderAbuse elderAbuse=new ElderAbuse();
                    bundle.putString("data",elderAbuse.getstring(position));
                    finalDetailFragment.setArguments(bundle);
                    getFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.frame_layout_to_be_replaced_by_fragment,finalDetailFragment).commit();
                    Log.e("cick", elder[position] + " " + position);
                }
                if(k==6) {
                    bundle.putString("heading",rights[position]);
                    Rights rights1=new Rights();
                    bundle.putString("data",rights1.getstring(position));
                    finalDetailFragment.setArguments(bundle);
                    getFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.frame_layout_to_be_replaced_by_fragment,finalDetailFragment).commit();
                    Log.e("cick", rights[position] + " " + position);
                }
            }
        });
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
     /*   if (context instanceof OnFragmentInteractionListener) {
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
