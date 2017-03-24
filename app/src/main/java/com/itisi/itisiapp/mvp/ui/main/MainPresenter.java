package com.itisi.itisiapp.mvp.ui.main;

import com.itisi.itisiapp.api.GankService;
import com.itisi.itisiapp.app.ItisiApp;
import com.itisi.itisiapp.mvp.model.entity.BaseGankEntity;
import com.itisi.itisiapp.mvp.model.entity.GankFuLiEntity;
import com.itisi.itisiapp.mvp.model.net.BaseObserver;
import com.itisi.itisiapp.mvp.model.net.RetrofitFactory;
import com.itisi.itisiapp.mvp.rx.RxSchedulerHelper;
import com.itisi.itisiapp.mvp.ui.base.RxPresenter;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * author: itisi---
 * created by Administrator on 2017/3/23.
 * desc:
 */

public class MainPresenter extends RxPresenter<MainContract.View> implements MainContract.Presenter {

    private RetrofitFactory mRetrofitFactory;

    @Inject
    public MainPresenter(RetrofitFactory retrofitFactory) {
        mRetrofitFactory = retrofitFactory;
    }

    @Override
    public void getData() {
        GankService gankService = mRetrofitFactory.getGankService();
        Observable<BaseGankEntity<List<GankFuLiEntity>>> observable = gankService.gank("福利", 10, 1);
        observable.compose(RxSchedulerHelper.<BaseGankEntity<List<GankFuLiEntity>>>io_main())
                .subscribe(new BaseObserver<List<GankFuLiEntity>>(ItisiApp.getInstance()) {
                    @Override
                    protected void onHandleSuccess(List<GankFuLiEntity> gankFuLiEntities) {
//                        Logger.i(gankFuLiEntities.size()+"===success");
//                        mView.showError(gankFuLiEntities.size()+"===success");
                        mView.showContent(gankFuLiEntities);
                    }
                    @Override
                    public void onSubscribe(Disposable d) {
                        addSubscribe(d);
                    }
                });

    }
}
