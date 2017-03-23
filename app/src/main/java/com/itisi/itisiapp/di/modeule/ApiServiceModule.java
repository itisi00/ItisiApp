package com.itisi.itisiapp.di.modeule;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.itisi.itisiapp.BuildConfig;
import com.itisi.itisiapp.api.APPURL;
import com.itisi.itisiapp.api.GankService;
import com.itisi.itisiapp.app.Constants;
import com.itisi.itisiapp.utils.SystemUtil;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.CacheControl;
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
 * desc: api 请求接口 module 提供各种 apiservice的创建
 */
@Module
public class ApiServiceModule {

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

    /**
     * retrofit builder
     * @return
     */
    @Singleton
    @Provides
    Retrofit.Builder provideRetrofitBuilder(){
        return new Retrofit.Builder();
    }

    /**
     * okhttpclient builder
     * @return
     */
    @Singleton
    @Provides
    OkHttpClient.Builder provideOkHttpBuilder(){
        return new OkHttpClient.Builder();
    }

    /**
     * okhttp client
     * @param builder
     * @return
     */
    @Singleton
    @Provides
    OkHttpClient provideClient(OkHttpClient.Builder builder){
        if (BuildConfig.DEBUG){ //调试模式
            HttpLoggingInterceptor loggingInterceptor=new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
            builder.addInterceptor(loggingInterceptor);
        }
        File cacheFile=new File(Constants.PATH_CACHE);
        Cache cache=new Cache(cacheFile,2014*1024*50);
        Interceptor cacheInterceptor=new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request=chain.request();
                    //检测是否有网络
                if (!SystemUtil.isNetworkConnected()){
                    request=request.newBuilder()
                            .cacheControl(CacheControl.FORCE_CACHE)
                            .build();
                }

                Response response = chain.proceed(request);
                int maxAge=0;
                if (SystemUtil.isNetworkConnected()){
                    //有网络时 不缓存 最大保存时长为0
                    response.newBuilder()
                            .header("Cache-Control", "public, max-age=" + maxAge)
                            .removeHeader("Pragma")
                            .build();
                }
                else{
                     maxAge=60 * 60 * 24 * 28;
                    // 无网络时，设置超时为4周
                    response.newBuilder()
                            .header("Cache-Control", "public, only-if-cached, max-stale=" + maxAge)
                            .removeHeader("Pragma")
                            .build();
                }
                return response;
            }
        };
        //        设置统一的请求头部参数
        //        Interceptor apikey = new Interceptor() {
        //            @Override
        //            public Response intercept(Chain chain) throws IOException {
        //                Request request = chain.request();
        //                request = request.newBuilder()
        //                        .addHeader("apikey",Constants.KEY_API)
        //                        .build();
        //                return chain.proceed(request);
        //            }
        //        }
        //        builder.addInterceptor(apikey);

        //设置缓存
        builder.addNetworkInterceptor(cacheInterceptor);
        builder.addInterceptor(cacheInterceptor);
        builder.cache(cache);
        //设置超时
        builder.connectTimeout(Constants.CONNECT_TIMEOUT, TimeUnit.SECONDS);
        builder.readTimeout(Constants.READ_TIMEOUT, TimeUnit.SECONDS);
        builder.writeTimeout(Constants.WRITE_TIMEOUT, TimeUnit.SECONDS);
        //错误重连
        builder.retryOnConnectionFailure(true);
        return builder.build();
    }

    /**
     * 干货服务类
     * @param builder
     * @param client
     * @return
     */
    @Singleton
    @Provides
    Retrofit provideGank(Retrofit.Builder builder,OkHttpClient client){
        return createRetrofit(builder,client, APPURL.BaseURL_GANK);
    }

    @Singleton
    @Provides
    GankService provideCankService(Retrofit retrofit){
        return retrofit.create(GankService.class);
    }

    private Retrofit createRetrofit(Retrofit.Builder builder,OkHttpClient client,String baseUrl){
        return builder
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(buildGson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }



}
