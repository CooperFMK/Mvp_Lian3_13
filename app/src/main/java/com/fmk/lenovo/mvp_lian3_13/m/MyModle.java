package com.fmk.lenovo.mvp_lian3_13.m;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.fmk.lenovo.mvp_lian3_13.jsonbean.JsonBean;
import com.fmk.lenovo.mvp_lian3_13.util.OkHttpUtil;
import com.google.gson.Gson;

import java.util.Map;

/**
 * @Auther: 付明锟
 * @Date: 2019/3/13 18:29:47
 * @Description:
 */
public class MyModle implements MyModleInterface {

    //全局变量
    MyCallBack myCallBack;

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            int type = msg.arg1;
            switch (type){
                case 1:
                    String json = (String) msg.obj;
                    Gson gson = new Gson();
                    JsonBean jsonBean = gson.fromJson(json, JsonBean.class);
                    myCallBack.succer(jsonBean);
                    Log.e("tab", "tab=="+jsonBean.toString());
                    break;
                case 2:
                    String json2 = (String) msg.obj;
                    Gson gson2 = new Gson();
                    JsonBean jsonBean2 = gson2.fromJson(json2, JsonBean.class);
                    myCallBack.succer(jsonBean2);
                    break;
            }
        }
    };

    public void toRequset(String url, Map<String,String>map,int type,final MyModle.MyCallBack myCallBack){
        this.myCallBack = myCallBack;
        OkHttpUtil.getInHttpUtil().doGet(url,map,handler,type);
    }

    //创建接口
    public interface MyCallBack{
        public void succer(Object obj);
        public void error(String str);
    }
}
