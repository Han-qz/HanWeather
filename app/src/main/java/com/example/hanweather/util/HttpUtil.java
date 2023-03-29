package com.example.hanweather.util;

import okhttp3.OkHttpClient;
import okhttp3.Request;

public class HttpUtil {
    public static void sendOkHttpRequest(String address,okhttp3.Callback callback){
        //OkHttp用法（数据都是从服务器获取到的，需要和服务器进行交互）
        //1、创建一个OkHttpClient实例
        OkHttpClient client = new OkHttpClient();
        //2、想要发起一条http请求，就需要创建一个Request对象
        //其中，通过url（）方法来设置目标的网络地址
        Request request = new Request.Builder().url(address).build();
        //3、调用enqueue()方法，OkHttp 在enqueue ()方法的内部已经帮我们开好子线程了
        //然后会在子线程中去执行HTTP请求，并将最终的请求结果回调到callback当中。
        client.newCall(request).enqueue(callback);
    }
}
