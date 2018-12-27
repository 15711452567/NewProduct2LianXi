package com.example.lianxizhoukao2;

import android.os.Handler;
import android.os.Looper;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * date:2018.12.26
 * author:赵颖冰(lenovo)
 * function:
 */
public class OkHttpUtils {
    private final Handler mHandler;
    private final OkHttpClient mOkHttpClient;
    OkHttpUtils mOkhttpUtils;

    public OkHttpUtils() {
        mHandler = new Handler(Looper.getMainLooper());
        mOkHttpClient = new OkHttpClient.Builder()
                .connectTimeout(5,TimeUnit.SECONDS)
                .readTimeout(5,TimeUnit.SECONDS)
                .writeTimeout(5,TimeUnit.SECONDS)
                .build();
    }

    public OkHttpUtils getOkHttpClient(){
        if (mOkhttpUtils==null){
            synchronized (OkHttpUtils.class){
                if (mOkhttpUtils==null){
                    mOkhttpUtils=new OkHttpUtils();
                }
            }
        }
        return mOkhttpUtils;
    }

    public interface GetData{
        void fail(Exception e);
        void response(String s);
    }

    public void doGet(String url, final GetData getData){
        Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        getData.fail(e);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String string = response.body().string();
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        getData.response(string);
                    }
                });
            }
        });
    }

    public void doPost(String url, Map<String ,String> map, final GetData getData){
        FormBody.Builder builder=new FormBody.Builder();
        for (String key:map.keySet()) {
            builder.add(key,map.get(key));
        }
        FormBody build = builder.build();
        Request request = new Request.Builder()
                .url(url)
                .post(build)
                .build();
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        getData.fail(e);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String string = response.body().string();
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        getData.response(string);
                    }
                });
            }
        });
    }

    public void doHeadPuts(String url, int h, String h2, Map<String, String> map, final GetData getData) {
        FormBody.Builder builder=new FormBody.Builder();
        for (String key:map.keySet()) {
            builder.add(key, map.get(key));
        }
        FormBody build = builder.build();
        Request request = new Request.Builder()
                .addHeader("userId", String.valueOf(h))
                .addHeader("sessionId", h2)
                .put(build)
                .url(url)
                .build();
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        getData.fail(e);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String json = response.body().string();
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        getData.response(json);
                    }
                });
            }
        });
    }


    public void doHeadGet(String url, int h,String h2,   final GetData getData) {

        Request request = new Request.Builder()
                .addHeader("userId", String.valueOf(h))
                .addHeader("sessionId", h2)
                .url(url)
                .build();
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        getData.fail(e);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String json = response.body().string();
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        getData.response(json);
                    }
                });
            }
        });
    }


    public void doPut(String url, int userId,String sessionId,final GetData getData){
        Request request = new Request.Builder()
                .url(url)
                .addHeader("userId", String.valueOf(userId))
                .addHeader("sessionId",sessionId)
                .build();
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        getData.fail(e);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String string = response.body().string();
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        getData.response(string);
                    }
                });
            }
        });
    }

}
