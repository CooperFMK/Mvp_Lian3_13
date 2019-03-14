package com.fmk.lenovo.mvp_lian3_13.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fmk.lenovo.mvp_lian3_13.R;
import com.fmk.lenovo.mvp_lian3_13.jsonbean.JsonBean;

import java.util.List;

/**
 * @Auther: 付明锟
 * @Date: 2019/3/13 17:01:11
 * @Description:
 */
public class MyAdpter extends RecyclerView.Adapter<MyAdpter.holder> {

    public List<JsonBean.ResultBean.RxxpBean.CommodityListBean> mList;
    public Context context;

    public MyAdpter(List<JsonBean.ResultBean.RxxpBean.CommodityListBean> mList, Context context) {
        this.mList = mList;
        this.context = context;
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, null);
        //返回holder对象
        return new holder(view);
    }

    //为控件设置值
    @Override
    public void onBindViewHolder(@NonNull holder holder, int i) {
        String name1 = mList.get(i).getCommodityName();
        String pic2 = mList.get(i).getMasterPic();
        holder.name.setText(name1);
        Glide.with(context).load(pic2).into(holder.image);
    }

    @Override
    public int getItemCount() {
        if (mList!=null){
            return mList.size();
        }
        return 0;
    }

    public class holder extends RecyclerView.ViewHolder{

        public ImageView image;
        public TextView name;

        public holder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            name = itemView.findViewById(R.id.name);
        }
    }
}
