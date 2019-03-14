package com.fmk.lenovo.mvp_lian3_13.v;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.fmk.lenovo.mvp_lian3_13.R;
import com.fmk.lenovo.mvp_lian3_13.adapter.MyAdpter;
import com.fmk.lenovo.mvp_lian3_13.jsonbean.JsonBean;
import com.fmk.lenovo.mvp_lian3_13.p.PresentInterface;
import com.fmk.lenovo.mvp_lian3_13.p.Presenter;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: 付明锟
 * @Date: 2019/3/13 16:25:39
 * @Description:
 */
public class MyActivity extends Activity implements VInterface{

    public List<JsonBean.ResultBean.RxxpBean.CommodityListBean> mList = new ArrayList<JsonBean.ResultBean.RxxpBean.CommodityListBean>();
    public RecyclerView res_list;
    public MyAdpter myAdpter;
    public PresentInterface presentInterface;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         init();
    }

    private void init() {
        res_list = findViewById(R.id.rec_list);
        //实例化布局管理器
        LinearLayoutManager manager = new LinearLayoutManager(this);
        //设置管理器显示方式
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        //衔接RecyclerView
        res_list.setLayoutManager(manager);
        //适配器
        myAdpter = new MyAdpter(mList,this);
        res_list.setAdapter(myAdpter);
        //p接口对象实例化 new的是p层的实现类
        presentInterface = new Presenter(this);
        //请求数据
        presentInterface.toModel();
    }

    @Override
    public void vDisply(List<JsonBean.ResultBean.RxxpBean.CommodityListBean> list) {
        //将数据添加到集合中
        mList.addAll(list);
        //刷新数据
        myAdpter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //回调p层接口把v层接口对象置为null
        presentInterface.onDestroy();
        //p层接口对象置为null
        presentInterface = null;
    }
}
