package com.bwie.renzhili.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bwie.renzhili.R;

public class TitleBarView extends LinearLayout {
    private ImageView image_back,image_search;
    private EditText edit_title;
    public TitleBarView(Context context) {
        super(context);
        init(context);
    }

    public TitleBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public TitleBarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        View view = View.inflate(context, R.layout.title,null);
        //获取资源id
        image_back=view.findViewById(R.id.image_back);
        image_search=view.findViewById(R.id.image_search);
        edit_title=view.findViewById(R.id.edit_title);
        //点击事件
        image_search.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String trim = edit_title.getText().toString().trim();
                if (trim.equals("")){
                    return;
                }else{
                    if (onButtonClick!=null){
                        onButtonClick.onClick(trim);
                    }
                }
            }
        });
        addView(view);
    }
    onButtonClick onButtonClick;
    public void setOnButtonClickListener( onButtonClick onButtonClick){
        this.onButtonClick=onButtonClick;
    }
    //接口
    public interface onButtonClick{
        void onClick(String str);
    }
}
