package com.bwie.renzhili.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

public class WeeKLayout extends LinearLayout {
    private Context context;
    private int MaxHeight;
    private int mHSpan=20;
    private int mVSpan=20;
    public WeeKLayout(Context context) {
        super(context);
    }

    public WeeKLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    public WeeKLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
        measureChildren(widthMeasureSpec,heightMeasureSpec);
        //最高孩子
        getfindchiledHeight();
        int left =0;
        int top =0;
        int childCount = getChildCount();
        for (int i=0;i<childCount;i++){
            View view =getChildAt(i);
            if (left!=0){
                if ((left+view.getMeasuredWidth())>sizeWidth){
                    top+=MaxHeight+mVSpan;
                    left=0;
                }
            }
            //view.layout(left,top,left+getMeasuredWidth(),top+MaxHeight);
            left+=view.getMeasuredWidth()+mHSpan;
        }
        setMeasuredDimension(sizeWidth,(top+MaxHeight)>sizeHeight?sizeHeight:(top+MaxHeight));
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        //最高孩子
        getfindchiledHeight();
        int left =0;
        int top =0;
        int childCount = getChildCount();
        for (int i=0;i<childCount;i++){
            View view =getChildAt(i);
            if (left!=0){
                if ((left+view.getMeasuredWidth())>getWidth()){
                    top+=MaxHeight+mVSpan;
                    left=0;
                }
            }
            view.layout(left,top,left+getMeasuredWidth(),top+MaxHeight);
            left+=view.getMeasuredWidth()+mHSpan;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    }

    private void getfindchiledHeight() {
        MaxHeight=0;
        int childCount = getChildCount();
        for (int i=0;i<childCount;i++){
            View view = getChildAt(i);
            if (view.getMeasuredHeight()>MaxHeight){
                MaxHeight=view.getMeasuredHeight();
            }
        }
    }
}
