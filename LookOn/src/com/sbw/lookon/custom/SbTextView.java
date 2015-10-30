package com.sbw.lookon.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

public class SbTextView extends TextView {

    public SbTextView(Context context) {
        super(context);
    }

    public SbTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        ViewUtil.setTypeface(attrs, this);
    }

    public SbTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        ViewUtil.setTypeface(attrs, this);
    }
}