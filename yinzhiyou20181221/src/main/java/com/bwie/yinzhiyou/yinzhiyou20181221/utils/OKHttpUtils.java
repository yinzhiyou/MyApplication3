package com.bwie.yinzhiyou.yinzhiyou20181221.utils;

import android.os.Handler;
import android.os.Looper;
import android.widget.EditText;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

public class OKHttpUtils {
    private static OKHttpUtils instance;
    private OkHttpClient mClient;
    private Handler mHandler=new Handler(Looper.getMainLooper());
    private final Gson gson;

    public static OKHttpUtils getInstance(){
    if (instance==null){
        synchronized (OKHttpUtils.class){
            if (null==instance){
                instance=new OKHttpUtils();
            }
        }
    }
        return instance;
    }
    private OKHttpUtils(){
        gson = new Gson();
        HttpLoggingInterceptor interceptor=new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        mClient=new OkHttpClient.Builder()
                .writeTimeout(10,TimeUnit.SECONDS)
                .readTimeout(10,TimeUnit.SECONDS)
                .connectTimeout(10,TimeUnit.SECONDS)
                .addInterceptor(interceptor).build();
    }
    public interface ICallBack{
        void onFailed(Exception e);
        void onSuccess(Object object);
    }
    public void postEnqueue(String trl, Map<String,String>params, final Class clazz, final ICallBack iCallBack){
        FormBody.Builder builder=new FormBody.Builder();
        for (Map.Entry<String,String> entry:params.entrySet()){
            builder.add(entry.getKey(),entry.getValue());
        }
        FormBody body=builder.build();
        Request request=new Request.Builder()
                .url(trl)
                .post(body).build();
        Call call = mClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        iCallBack.onFailed(e);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String s = response.body().string();
                final Object o = gson.fromJson(s, clazz);
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        iCallBack.onSuccess(o);
                    }
                });
            }
        });
    }
}
