package com.example.a86130.x_day02_c;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.a86130.x_day02_c.bean.OneBean;

import java.util.ArrayList;

/**
 * Created by 86130 on 2019/9/22.
 */

class OneAdapter extends RecyclerView.Adapter{
    private ArrayList<OneBean.BodyBean.ResultBean> resultBeans;
    private Context context;

    public OneAdapter(ArrayList<OneBean.BodyBean.ResultBean> resultBeans, Context context) {
        this.resultBeans = resultBeans;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.recycler_layout,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        MyViewHolder holder1 = (MyViewHolder) holder;
        holder1.title.setText(resultBeans.get(position).getTitle());
        holder1.name.setText(resultBeans.get(position).getTeacherName());
        RequestOptions options = new RequestOptions().circleCrop();
        Glide.with(context).load(resultBeans.get(position).getTeacherPic()).apply(options).into(holder1.iv);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onclick.onclick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return resultBeans.size();
    }

    private class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv;
        private TextView name;
        private TextView title;
        private Button bt;
        public MyViewHolder(View view) {
            super(view);
              iv = view.findViewById(R.id.iv);
              name = view.findViewById(R.id.name);
              title = view.findViewById(R.id.title);
              bt = view.findViewById(R.id.bt);
        }
    }
    interface  Onclick{
        void onclick(int position);
    }
    private Onclick onclick;

    public void setOnclick(Onclick onclick) {
        this.onclick = onclick;
    }
}
