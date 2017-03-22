package com.itisi.itisiapp.mvp.model.net;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.itisi.itisiapp.api.APPURL;
import com.itisi.itisiapp.api.GankService;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.orhanobut.logger.Logger;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * author: itisi---
 * created by Administrator on 2017/3/22.
 * desc:
 */

public class RetrofitFactory {
    /**
     * 网络连接超时 读取超时 时长 30s
     */
    private static final long TIMEOUT=30;

    /**
     * Retrofit是基于OkHttpClient的，可以创建一个OkHttpClient进行一些配置
     */
    private static OkHttpClient httpClient=new OkHttpClient.Builder()
            /**
             * 添加通用头 比如请求token appkey securet之类的
             */
            .addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request.Builder builder=chain.request().newBuilder();
                    builder.addHeader("token","itisi-123");
                    return chain.proceed(builder.build());
                }
            })
            /**
             *  这里可以添加一个HttpLoggingInterceptor，因为Retrofit封装好了从Http请求到解析，
             *  出了bug很难找出来问题，添加HttpLoggingInterceptor拦截器方便调试接口
             */
            .addInterceptor(new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                @Override
                public void log(String message) {
                    Logger.i("请求日志:"+message);
                }
            })
                    .setLevel(HttpLoggingInterceptor.Level.BASIC)
            )
            .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT,TimeUnit.SECONDS)
            .build();

    /**
     * 创建干货api的服务请求对象
     */
    private static GankService gankService =new Retrofit.Builder()
            .baseUrl(APPURL.BaseURL_GANK)
            .addConverterFactory(GsonConverterFactory.create(buildGson()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(httpClient)
            .build()
            .create(GankService.class);

    //创建其他的服务请求对象

    /**
     * 获取 干货api的服务请求对象
     * @return
     */
    public static GankService getGankService(){
        return gankService;
    }

    //其他服务请求对象的获取

    /**
     * 自定义的GSON 解析
     * @return
     */
    private static Gson buildGson(){
        return new GsonBuilder()
                .serializeNulls()
                .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
//                .registerTypeAdapter()
        .create();
    }


}
