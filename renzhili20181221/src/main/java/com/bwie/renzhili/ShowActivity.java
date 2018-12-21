package com.bwie.renzhili;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bwie.renzhili.adapter.MyShowAdapter;
import com.bwie.renzhili.bean.AddBean;
import com.bwie.renzhili.bean.ShopBean;
import com.bwie.renzhili.presenter.IPresenterImpl;
import com.bwie.renzhili.view.IView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.HashMap;
import java.util.Map;

public class ShowActivity extends AppCompatActivity implements IView, View.OnClickListener {
    private ImageView image_search;
    private EditText edit_text;
    private XRecyclerView xRecyclerView;
    private IPresenterImpl iPresenter;
    private int mpage;
    private MyShowAdapter myShowAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_item);
        iPresenter=new IPresenterImpl(this);
        initView();
        init();
    }

    private void init() {
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        Map<String,String> params = new HashMap<>();
        params.put("keywords",name);
        params.put("page",String.valueOf(mpage));
        iPresenter.getRequeryData(Apis.URI_DATA,params,ShopBean.class);
    }

    private void initView() {
        mpage=1;
        xRecyclerView=findViewById(R.id.xecyclerview);
        image_search=findViewById(R.id.image_search);
        edit_text=findViewById(R.id.edit_text);
        image_search.setOnClickListener(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ShowActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        xRecyclerView.setLayoutManager(linearLayoutManager);
        xRecyclerView.addItemDecoration(new DividerItemDecoration(ShowActivity.this,OrientationHelper.VERTICAL));
        //创建适配qi
        myShowAdapter = new MyShowAdapter(this);
        xRecyclerView.setAdapter(myShowAdapter);
        xRecyclerView.setLoadingMoreEnabled(true);
        xRecyclerView.setPullRefreshEnabled(true);
        xRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mpage=1;
                initData();
            }

            @Override
            public void onLoadMore() {
                initData();
            }
        });
        myShowAdapter.setAddListener(new MyShowAdapter.AddListener() {
            @Override
            public void onClick(int pid) {
                Map<String,String> params = new HashMap<>();
                params.put("uid",String.valueOf(23011));
                params.put("pid",String.valueOf(pid));
                iPresenter.getRequeryData(Apis.URI_ADD,params,AddBean.class);
            }
        });
        myShowAdapter.setAddListener(new MyShowAdapter.Click() {
            @Override
            public void onClick(int psoition) {
                Intent intent = new Intent(ShowActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View v) {
    switch (v.getId()){
        case R.id.image_search:
            initView();
            initData();
            break;
            default:
                break;
    }
    }

    private void initData() {
        Map<String,String> params = new HashMap<>();
        params.put("keywords",edit_text.getText().toString().trim());
        params.put("page",String.valueOf(mpage));
        iPresenter.getRequeryData(Apis.URI_DATA,params,ShopBean.class);
    }

    @Override
    public void showRequeryData(Object o) {
        if (o instanceof ShopBean){
            ShopBean shopBean = (ShopBean) o;
            if (shopBean==null||!shopBean.isSuccess()){
                Toast.makeText(ShowActivity.this,shopBean.getMsg(),Toast.LENGTH_SHORT).show();
            }else{
               if (mpage==1){
                    myShowAdapter.setList(shopBean.getData());
               }else{
                   myShowAdapter.addList(shopBean.getData());
               }
               mpage++;
               xRecyclerView.refreshComplete();
               xRecyclerView.loadMoreComplete();
            }
        }else if(o instanceof AddBean){
            AddBean addBean = (AddBean) o;
            Toast.makeText(ShowActivity.this,addBean.getMsg(),Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        iPresenter.onAtch();
    }
}
