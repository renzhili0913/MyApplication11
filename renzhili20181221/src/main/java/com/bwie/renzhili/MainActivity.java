package com.bwie.renzhili;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.bwie.renzhili.view.TitleBarView;
import com.bwie.renzhili.view.WeeKLayout;

public class MainActivity extends AppCompatActivity {
    private TitleBarView titleBarView;
    private WeeKLayout fl_search,vl_search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        titleBarView=findViewById(R.id.titleBarView);
        fl_search=findViewById(R.id.fl_search);
        vl_search=findViewById(R.id.vl_search);
        titleBarView.setOnButtonClickListener(new TitleBarView.onButtonClick() {
            @Override
            public void onClick(String str) {
                TextView textView = new TextView(MainActivity.this);
                textView.setText(str);
               // textView.setBackgroundResource(R.drawable.edit_shpae);
                fl_search.addView(textView);
                Intent intent = new Intent(MainActivity.this,ShowActivity.class);
                intent.putExtra("name",str);
                startActivity(intent);
                finish();
            }
        });
        String[] strings = {"情侣特色","毛衫"};
        for (int i =0; i<strings.length;i++){
            TextView textView = new TextView(MainActivity.this);
            textView.setText(strings[i]);
           // textView.setBackgroundResource(R.drawable.edit_shpae);
            vl_search.addView(textView);
        }
    }
}
