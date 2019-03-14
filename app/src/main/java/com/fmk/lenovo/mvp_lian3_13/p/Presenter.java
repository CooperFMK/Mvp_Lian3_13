package com.fmk.lenovo.mvp_lian3_13.p;

import android.util.Log;

import com.fmk.lenovo.mvp_lian3_13.jsonbean.JsonBean;
import com.fmk.lenovo.mvp_lian3_13.m.MyModle;
import com.fmk.lenovo.mvp_lian3_13.m.MyModleInterface;
import com.fmk.lenovo.mvp_lian3_13.v.VInterface;

/**
 * @Auther: 付明锟
 * @Date: 2019/3/13 19:59:36
 * @Description:
 */
public class Presenter implements PresentInterface {

    //获取m层接口的对象
    MyModleInterface myModleInterface;
    //获取V层接口的对象
    VInterface vInterface;

    //构造方法
    public Presenter(VInterface vInterface) {
        myModleInterface = new MyModle();
        this.vInterface = vInterface;
    }

    @Override
    public void toModel() {
           //调用请求数据的方法
           myModleInterface.toRequset("http://172.17.8.100/small/commodity/v1/commodityList",null,1 ,new MyModle.MyCallBack(){
               @Override
               public void succer(Object obj) {
                   //拿到返回数据list
                   //通过接口对象把list传到V层
                   if (obj instanceof JsonBean){
                      JsonBean jsonBean = (JsonBean) obj;
                       vInterface.vDisply(jsonBean.getResult().getRxxp().getCommodityList());
                       Log.e("tab", "aaa= "+jsonBean.toString() );
                   }
               }

               @Override
               public void error(String str) {
                   //拿到失败的异常信息
               }
           });
        }

    @Override
    public void onDestroy() {
        vInterface = null;
    }
}
