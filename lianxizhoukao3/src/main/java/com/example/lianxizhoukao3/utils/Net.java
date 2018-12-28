package com.example.lianxizhoukao3.utils;

import com.example.lianxizhoukao3.bean.XiangQingBean;
import com.example.lianxizhoukao3.bean.ZongHeBean;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * date:2018.12.27
 * author:赵颖冰(lenovo)
 * function:
 */
public interface Net {

    @GET("searchProducts?keywords=笔记本&page=1")
    Call<ZongHeBean> getCall();

    @GET("getProductDetail")
    Call<XiangQingBean> getCall2(@QueryMap Map<String,Integer> map);
}
