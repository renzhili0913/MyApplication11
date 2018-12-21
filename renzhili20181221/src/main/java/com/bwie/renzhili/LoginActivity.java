package com.bwie.renzhili;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.renzhili.adapter.MyLoginAdapter;
import com.bwie.renzhili.bean.ChildBean;
import com.bwie.renzhili.bean.ShopBean;
import com.bwie.renzhili.presenter.IPresenterImpl;
import com.bwie.renzhili.view.IView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoginActivity extends AppCompatActivity implements IView,View.OnClickListener {
    private IPresenterImpl iPresenter;
    private RecyclerView recyclerView;
    private CheckBox select_all;
    private TextView text_price;
    private MyLoginAdapter myLoginAdapter;
    private ChildBean childBean;
    private List<ChildBean.DataBean> data;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_item);
        iPresenter=new IPresenterImpl(this);
        initView();
        initData();
    }

    private void initData() {
        Map<String,String> params = new HashMap<>();
        params.put("uid",String.valueOf(23011));
        iPresenter.getRequeryData(Apis.URI_SHOP,params,ChildBean.class);
    }

    private void initView() {
        recyclerView=findViewById(R.id.recyclerview);
        select_all=findViewById(R.id.select_all);
        text_price=findViewById(R.id.text_price);
        select_all.setOnClickListener(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(LoginActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(LoginActivity.this,OrientationHelper.VERTICAL));
        //创建适配qi
        myLoginAdapter = new MyLoginAdapter(this);
        recyclerView.setAdapter(myLoginAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.select_all:

                break;
                default:
                    break;
        }
    }

    @Override
    public void showRequeryData(Object o) {
        if (o instanceof ChildBean){
            ChildBean childBean = (ChildBean) o;
            if (childBean==null||!childBean.isSuccess()){
                Toast.makeText(LoginActivity.this,childBean.getMsg(),Toast.LENGTH_SHORT).show();
            }else{
                data = childBean.getData();
                data.remove(0);
                myLoginAdapter.setList(data);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        iPresenter.onAtch();
    }
}
