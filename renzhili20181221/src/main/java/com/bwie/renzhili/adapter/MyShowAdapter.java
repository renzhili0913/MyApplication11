package com.bwie.renzhili.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bwie.renzhili.R;
import com.bwie.renzhili.bean.ShopBean;

import java.util.ArrayList;
import java.util.List;

public class MyShowAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<ShopBean.DataBean> list;

    public MyShowAdapter(Context context) {
        this.context = context;
        list=new ArrayList<>();
    }

    public void setList(List<ShopBean.DataBean> data) {
        list.clear();
        if (data!=null){
            list.addAll(data);
        }
        notifyDataSetChanged();
    }
    public void addList(List<ShopBean.DataBean> data) {
        if (data!=null){
            list.addAll(data);
        }
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(context).inflate(R.layout.show_shop_item,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        MyViewHolder myViewHolder = (MyViewHolder) viewHolder;
        myViewHolder.text_title.setText(list.get(i).getTitle());
        myViewHolder.text_price.setText("价格："+list.get(i).getPrice());
        String replace = list.get(i).getImages().split("\\|")[0].replace("https", "http");
        Glide.with(context).load(replace).into(myViewHolder.imageView);
        myViewHolder.text_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (addListener!=null){
                    addListener.onClick(list.get(i).getPid());
                }
            }
        });
        myViewHolder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (click!=null){
                    click.onClick(i);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView text_title,text_price,text_add;
        ConstraintLayout constraintLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.images);
            text_title=itemView.findViewById(R.id.text_title);
            text_price=itemView.findViewById(R.id.text_price);
            text_add=itemView.findViewById(R.id.text_add);
            constraintLayout=itemView.findViewById(R.id.constraintlayout);
        }
    }
    AddListener addListener;
    public void setAddListener(AddListener addListener){
        this.addListener=addListener;
    }
    //加入购物车的接口
    public interface AddListener{
        void onClick(int pid);
    }
    Click click;
    public void setAddListener(Click click){
        this.click=click;
    }
    //加入购物车的接口
    public interface Click{
        void onClick(int psoition);
    }
}
