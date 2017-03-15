package com.chen.coolweather.util;

import okhttp3.OkHttpClient;
import okhttp3.Request;


/**
 * Created by Admin on 2017/3/15.
 */

public class HttpUtil {


    /**
     * 获取网络数据
     * @param address 下载地址
     * @param callback 网络回调
     */
    public static void sendOkHttpRequest(String address, okhttp3.Callback callback) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(address).build();
        client.newCall(request);
    }
}
