package com.sbw.lookon.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;

public class SbButton extends Button {

    public SbButton(Context context) {
        super(context);
    }

    public SbButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        ViewUtil.setTypeface(attrs, this);
    }

    public SbButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        ViewUtil.setTypeface(attrs, this);
    }
}