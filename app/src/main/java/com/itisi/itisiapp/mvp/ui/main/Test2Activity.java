package com.itisi.itisiapp.mvp.ui.main;

import android.view.View;
import android.widget.ImageView;

import com.itisi.itisiapp.R;
import com.itisi.itisiapp.mvp.model.entity.GankFuLiEntity;
import com.itisi.itisiapp.mvp.rx.annotation.UseRxBus;
import com.itisi.itisiapp.mvp.ui.base.BaseRxBusActivity;
import com.itisi.itisiapp.utils.ClickTree;
import com.sdsmdg.tastytoast.TastyToast;

import java.util.List;

import butterknife.BindView;

@UseRxBus
public class Test2Activity extends BaseRxBusActivity<MainPresenter> implements MainContract.View {


    @BindView(R.id.iv_test)
    protected ImageView iv_test;
    //多次点击事件
    ClickTree mClickTree=new ClickTree(3);

    @Override
    protected void initInject() {

        getActivityComponent().inject(this);
    }
    @Override
    public int getConentlayout() {
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
       // RxBus.getInstance().post(RxBus.getInstance().getTag(MainActivity.class,RxBus.TAG_UPDATE),new Date().toString());
        //网络请求
//        Observable<BaseGankEntity<List<GankFuLiEntity>>> observable = RetrofitFactory.getGankService().gank("福利", 10, 1);
//        observable.compose(RxSchedulerHelper.<BaseGankEntity<List<GankFuLiEntity>>>io_main())
//                .subscribe(new BaseObserver<List<GankFuLiEntity>>(Test2Activity.this) {
//                    @Override
//                    protected void onHandleSuccess(List<GankFuLiEntity> gankFuLiEntities) {
//                        Logger.i(gankFuLiEntities.size()+"");
//                    }
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//                });

        //多次点击 事件 点击树
//       boolean res= mClickTree.completeClickCount();
//        Logger.i(res+"");
        mPresenter.getData();
    }
    @Override
    public void showError(String msg) {
        TastyToast.makeText(this,msg,TastyToast.LENGTH_SHORT,TastyToast.SUCCESS);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }


    @Override
    public void showContent(List<GankFuLiEntity> list) {

    }
}
