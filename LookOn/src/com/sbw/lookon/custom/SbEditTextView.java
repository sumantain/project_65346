package com.sbw.lookon.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;

public class SbEditTextView extends EditText {

    public SbEditTextView(Context context) {
        super(context);
    }

    public SbEditTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        ViewUtil.setTypeface(attrs, this);
    }

    public SbEditTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        ViewUtil.setTypeface(attrs, this);
    }
}