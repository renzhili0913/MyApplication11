package com.bwie.renzhili.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bwie.renzhili.R;
import com.bwie.renzhili.bean.ChildBean;

import java.util.List;

public class MyZiAdapter extends RecyclerView.Adapter<MyZiAdapter.ViewHolder> {
    private Context context;
    private List<ChildBean.DataBean.ListBean> list;

    public MyZiAdapter(Context context, List<ChildBean.DataBean.ListBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyZiAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View  view = LayoutInflater.from(context).inflate(R.layout.zi_item,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyZiAdapter.ViewHolder viewHolder, int i) {
        String replace = list.get(i).getImages().split("\\|")[0].replace("https", "http");
        Glide.with(context).load(replace).into(viewHolder.imageView);
        viewHolder.price.setText("价格："+list.get(i).getPrice());
        viewHolder.title.setText(list.get(i).getTitle());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CheckBox checkBox;
        ImageView imageView;
        TextView title,price;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            checkBox=itemView.findViewById(R.id.child_item);
            imageView=itemView.findViewById(R.id.image);
            title=itemView.findViewById(R.id.title);
            price=itemView.findViewById(R.id.price);
        }
    }
}
