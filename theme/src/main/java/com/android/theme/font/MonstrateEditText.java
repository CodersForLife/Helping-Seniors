package com.android.theme.font;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Created by karthikeyan on 08-06-2016.
 */
public class MonstrateEditText extends EditText {

    public MonstrateEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public MonstrateEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MonstrateEditText(Context context) {
        super(context);
        init();
    }

    public void init() {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "montserrat_light.otf");
        setTypeface(tf ,1);

    }
}
