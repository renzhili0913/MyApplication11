package com.bwie.renzhili.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.TextView;

import com.bwie.renzhili.R;

@SuppressLint("AppCompatCustomView")
public class WeekGroupLayoutView extends TextView {
    public WeekGroupLayoutView(Context context) {
        super(context);
    }

    public WeekGroupLayoutView(Context context,  AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.WeekGroupLayoutView);
        int color = typedArray.getColor(R.styleable.WeekGroupLayoutView_textColor, Color.RED);
        setTextColor(color);
        typedArray.recycle();

    }

    public WeekGroupLayoutView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
