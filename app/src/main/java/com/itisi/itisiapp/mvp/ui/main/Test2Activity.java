package com.itisi.itisiapp.mvp.ui.main;

import android.view.View;
import android.widget.ImageView;

import com.itisi.itisiapp.R;
import com.itisi.itisiapp.mvp.model.entity.BaseGankEntity;
import com.itisi.itisiapp.mvp.model.entity.GankFuLiEntity;
import com.itisi.itisiapp.mvp.model.net.BaseObserver;
import com.itisi.itisiapp.mvp.model.net.RetrofitFactory;
import com.itisi.itisiapp.mvp.rx.RxBus;
import com.itisi.itisiapp.mvp.rx.RxSchedulerHelper;
import com.itisi.itisiapp.mvp.rx.annotation.UseRxBus;
import com.itisi.itisiapp.mvp.ui.base.BaseRxBusActivity;
import com.orhanobut.logger.Logger;

import java.util.Date;
import java.util.List;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

@UseRxBus
public class Test2Activity extends BaseRxBusActivity {

    @BindView(R.id.iv_test)
    protected ImageView iv_test;

    @Override
    public int getlayoutId() {
        return R.layout.activity_test2;
    }


    /**
     * 发送事件
     * @param view
     */
    public void btnPost(View view){
        //图片加载
//        String url="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1490171034491&di=8c5b580fac94786511f4ce2bae65e8b1&imgtype=0&src=http%3A%2F%2Fimg2015.zdface.com%2F20160717%2Fc08eb20a19477625d31ca616c67b5717.jpg";
//        ImageLoadProxy.getInstance().load(new ImageLoadConfiguration.Builder(this).url(url)
//        .isCircle(true).defaultImageResId(R.mipmap.ic_launcher).isGray(true).imageView(iv_test).build());
        //事件总线
        RxBus.getInstance().post(RxBus.getInstance().getTag(MainActivity.class,RxBus.TAG_UPDATE),new Date().toString());
        //网络请求
        Observable<BaseGankEntity<List<GankFuLiEntity>>> observable = RetrofitFactory.getGankService().gank("福利", 10, 1);
        observable.compose(RxSchedulerHelper.<BaseGankEntity<List<GankFuLiEntity>>>io_main())
                .subscribe(new BaseObserver<List<GankFuLiEntity>>(Test2Activity.this) {
                    @Override
                    protected void onHandleSuccess(List<GankFuLiEntity> gankFuLiEntities) {
                        Logger.i(gankFuLiEntities.size()+"");
                    }
                    @Override
                    public void onSubscribe(Disposable d) {

                    }
                });
    }

}
