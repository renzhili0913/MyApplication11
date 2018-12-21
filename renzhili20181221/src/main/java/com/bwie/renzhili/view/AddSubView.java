package com.bwie.renzhili.view;


import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bwie.renzhili.R;

public class AddSubView extends LinearLayout implements View.OnClickListener{
    private ImageButton image_sub,image_add;
    private EditText edit_text;
    private String trim;
    private Context context;
    private int i;

    public AddSubView(Context context) {
        super(context);
        init(context);
    }

    public AddSubView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public AddSubView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        this.context=context;
        View view =View.inflate(context, R.layout.addsubview,null);
        image_add=view.findViewById(R.id.image_add);
        image_sub=view.findViewById(R.id.image_sub);
        edit_text=view.findViewById(R.id.edit_text);
        image_add.setOnClickListener(this);
        image_sub.setOnClickListener(this);
        addView(view);
    }

    @Override
    public void onClick(View v) {
        i = Integer.parseInt(trim = edit_text.getText().toString().trim());
        switch (v.getId()){
            case R.id.image_add:
                i++;
                edit_text.setText(i +"");
                break;
            case R.id.image_sub:
                if (i >1){
                    i--;
                    edit_text.setText(i +"");
                }else{
                    Toast.makeText(context,"最少为1",Toast.LENGTH_SHORT).show();
                }

                break;
                default:
                    break;
        }
    }
}
