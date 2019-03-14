package com.fmk.lenovo.mvp_lian3_13.m;

import java.util.Map;

/**
 * @Auther: 付明锟
 * @Date: 2019/3/13 18:27:07
 * @Description:
 */
public interface MyModleInterface {
    public void toRequset(String url, Map<String,String>map, int type, MyModle.MyCallBack myCallBack);
}
