package com.bwie.renzhili.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.bwie.renzhili.LoginActivity;
import com.bwie.renzhili.R;
import com.bwie.renzhili.bean.ChildBean;


import java.util.ArrayList;
import java.util.List;

public class MyLoginAdapter extends RecyclerView.Adapter<MyLoginAdapter.ViewHolder> {
    private Context context;
    private List<ChildBean.DataBean> list;
    private MyZiAdapter myZiAdapter;

    public MyLoginAdapter(Context context) {
        this.context = context;
        list=new ArrayList<>();
    }

    public void setList(List<ChildBean.DataBean> data) {
        list.clear();
        if (data!=null){
            list.addAll(data);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyLoginAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.shop_jia_item,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyLoginAdapter.ViewHolder viewHolder, int i) {
           viewHolder.text_name.setText(list.get(i).getSellerName());
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            viewHolder.recyclerView.setLayoutManager(linearLayoutManager);
             myZiAdapter = new MyZiAdapter(context, list.get(i).getList());
             viewHolder.recyclerView.setAdapter(myZiAdapter);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CheckBox check_shop;
        TextView text_name;
        RecyclerView recyclerView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            check_shop=itemView.findViewById(R.id.check_shop);
            text_name=itemView.findViewById(R.id.text_name);
            recyclerView=itemView.findViewById(R.id.recyclerview);
        }
    }
}
