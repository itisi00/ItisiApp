package com.itisi.itisiapp.api;

import com.itisi.itisiapp.mvp.model.entity.BaseGankEntity;
import com.itisi.itisiapp.mvp.model.entity.GankFuLiEntity;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * author: itisi---
 * created by Administrator on 2017/3/22.
 * desc: 干货api
 */

public interface GankService {
    /**
     * 加载干货数据
     * @param type 干货类型: Android|iOS|休息视频|福利|拓展资源|前端|瞎推荐|App
     * @param count 每页数量
     * @param index 页数
     * @return
     */
    @GET(APPURL.gank)
    Observable<BaseGankEntity<List<GankFuLiEntity>>>gank(@Path("type")String type, @Path("count")int count, @Path("index")int index);

    // 获取其他数据的方法


}
