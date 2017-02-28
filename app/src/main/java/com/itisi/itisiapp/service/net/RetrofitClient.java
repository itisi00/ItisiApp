package com.itisi.itisiapp.service.net;

import android.support.compat.BuildConfig;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.itisi.itisiapp.global.Constant;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

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
 * Retrofit 客户端
 * Created by Administrator on 2017/2/24.
 */
public class RetrofitClient {
    private static Retrofit mRetrofit;
    private static int DEFAULT_TIMEOUT=5;

    public static Retrofit retofit(){
        if (mRetrofit==null){
            OkHttpClient.Builder clientBuilder=new OkHttpClient.Builder();
            if (BuildConfig.DEBUG){//调试模式
                //log信息拦截器
                HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
                loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);//日志级别
                //设置Dbbug Log模式
                clientBuilder.addInterceptor(loggingInterceptor);
            }
            /**
             * 添加拦截器
             */
            clientBuilder.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request original=chain.request();
                    Request request=original.newBuilder()
                            .header("","")
                            .method(original.method(),original.body())
                            .build();
                    return chain.proceed(request);

                }
            });

            clientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
            //配置gson
            Gson gson=new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd hh:mm:ss")
                    .create();
            OkHttpClient okHttpClient=clientBuilder.build();
            mRetrofit=new Retrofit.Builder()
                    .baseUrl(Constant.URL.BaseURL_GANK)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(okHttpClient)
                    .build();
        }

        return mRetrofit;
    }



}
