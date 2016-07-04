package com.android.theme.activity;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.android.theme.R;


public class ToolbarActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private LayoutInflater layoutInflater;
    private View rootView;
    private FrameLayout baseFragmentContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        layoutInflater = LayoutInflater.from(this);
        rootView = layoutInflater.inflate(R.layout.toolbar_base_layout, null, false);

        initView();
        initToolbar();
    }

    private void initView() {
        toolbar = (Toolbar) rootView.findViewById(R.id.header_actionbar);
        baseFragmentContainer = (FrameLayout) rootView.findViewById(R.id.base_fragment_container);
    }

    public Toolbar getToolbar() {
        return toolbar;
    }

    protected void setNavigationIcon(int iconId) {
        toolbar.setNavigationIcon(iconId);
    }

    private void initToolbar() {

        toolbar.setVisibility(View.VISIBLE);
        toolbar.setNavigationIcon(R.drawable.arrow_white);
        if (Integer.valueOf(android.os.Build.VERSION.SDK_INT) > 20) {
            Resources r = getResources();
            float elevationpx = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 0, r.getDisplayMetrics());
            toolbar.setElevation(elevationpx);
            toolbar.setTranslationZ(elevationpx);
        }
        setToolbarTheme();
        setSupportActionBar(toolbar);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (toolbar != null) {
            toolbar.setNavigationIcon(R.drawable.arrow_white);
        }
    }


    public void setContentView(int layout) {

        View view = layoutInflater.inflate(layout, null, false);
        baseFragmentContainer.addView(view);
        super.setContentView(rootView);

    }


    protected void setToolbarTheme() {
        if (toolbar != null) {
            toolbar.setBackgroundColor(getResources().getColor(R.color.actionbar_bg));
            toolbar.setTitleTextAppearance(this, R.style.BrainacTheme_ActionBar_TitleTextStyle);
            toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        }
    }

    public void hideToolbar(){
        toolbar.setVisibility(View.GONE);
    }

}
