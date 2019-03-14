package com.fmk.lenovo.mvp_lian3_13.util;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @Auther: 付明锟
 * @Date: 2019/3/13 18:23:19
 * @Description:
 */
public class OkHttpUtil {

    public OkHttpClient okHttpClient;

    public static OkHttpUtil okHttp;

    //构造方法私有化
    private OkHttpUtil(){
        okHttpClient = new OkHttpClient();
        /*****/
        okHttpClient.newBuilder().addInterceptor(new MyInterceptor())
                .build();
    }

    //单利
    public static synchronized OkHttpUtil getInHttpUtil(){

        if (okHttp == null){
            okHttp = new OkHttpUtil();
        }
        return okHttp;
    }

    //get请求
    public void doGet(String url, Map<String,String> map, final Handler mH, final int type){
        if (map != null && map.size()>0){
            //遍历集合
            String str = "";
            StringBuilder builder = new StringBuilder();
            //拼接参数
            for (String key : map.keySet()){
                String pkey = key;
                String value = map.get(pkey);
                builder.append(pkey)
                        .append("=")
                        .append(value)
                        .append("&");
            }
            str = builder.toString();
            //因为for循环遍历键值队，所以会多拼接一个&
            int i = str.lastIndexOf("&");
            str = str.substring(0, i);
            url = url+"?"+str;
        }

         Request request = new Request.Builder()
                .get()
                .url(url)
                .build();

        Call call = okHttpClient.newCall(request);

        //使用okhttp异步请求
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("tab", "e== "+e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                 //请求成功，获取json字符串
                String s = response.body().string();
                Message message = new Message();
                message.obj = s;
                message.arg1 = type;
                mH.sendMessage(message);
            }
        });
    }

    //post请求
    public void dopost(String url, final Map<String,String> map, final Handler mH, final int type){

        //Get和Post请求的区别在这里
        FormBody.Builder formBody = new FormBody.Builder();
        for (String str : map.keySet()){
            formBody.add(str,map.get(str));
        }

         Request request = new Request.Builder()
                .get()
                .url(url)
                .build();

        Call call = okHttpClient.newCall(request);

        //使用okhttp异步请求
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //请求成功，获取json字符串
                String s = response.body().string();
                Message message = new Message();
                message.obj = s;
                message.arg1 = type;
                mH.sendMessage(message);
            }
        });
    }

    //***********
    public class MyInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            Response response = chain.proceed(request);
            Log.e("tag" ,"Url = " + request.url());
            Log.e("tag" ,"response = " + response.body().string());
            return response;
        }
    }
}
