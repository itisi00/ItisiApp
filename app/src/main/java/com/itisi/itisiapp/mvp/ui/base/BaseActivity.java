package com.itisi.itisiapp.mvp.ui.base;

import android.os.Bundle;

import com.itisi.itisiapp.R;
import com.itisi.itisiapp.app.ItisiApp;
import com.itisi.itisiapp.di.component.ActivityComponent;
import com.itisi.itisiapp.di.component.DaggerActivityComponent;
import com.itisi.itisiapp.di.modeule.ActivityModule;
import com.jaeger.library.StatusBarUtil;
import com.orhanobut.logger.Logger;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

public abstract class BaseActivity<P extends BasePresenter> extends SwipeBackActivity  implements BaseView {

    protected SwipeBackLayout mSwipeBackLayout;
    @Inject
    protected P mPresenter; //对应的presenter
    private Unbinder mUnbinder;//buterknife 绑定的对象

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //        //透明状态栏 如果开启的话 内容会延伸到状态栏
        //getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //        //透明导航栏
        //        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        super.onCreate(savedInstanceState);

        setContentView(getConentlayout());
        //初始化 ButterKnife
        mUnbinder = ButterKnife.bind(this);
        Logger.init(); //初始化日志

        initSwipeBack();//初始化 侧滑返回
        initInject();//dagger2 注入
        if (mPresenter!=null){
            mPresenter.attachView(this);
        }
        setStatusBarColor();//设置状态栏
        initData();//初始化 数据
    }

    /**
     * 注入
     */
    protected abstract void initInject();

    protected ActivityComponent getActivityComponent(){
        return DaggerActivityComponent.builder()
                .appComponent(ItisiApp.getAppComponent())
                .activityModule(getActivityModule())
                .build();
    }

    private ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }

    /**
     * 加载布局文件
     * @return
     */
    public abstract int getConentlayout();
    /**
     * 初始化
     */
    private void initSwipeBack() {
        mSwipeBackLayout = getSwipeBackLayout();
        //设置滑动方向，可设置EDGE_LEFT, EDGE_RIGHT, EDGE_ALL, EDGE_BOTTOM
        mSwipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);
        //        /**
        //         * 获取状态栏高度——方法1
        //         * */
        //        int statusBarHeight1 = -1;
        //        //获取status_bar_height资源的ID
        //        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        //        if (resourceId > 0) {
        //            //根据资源ID获取响应的尺寸值
        //            statusBarHeight1 = getResources().getDimensionPixelSize(resourceId);
        //        }
        //        Log.e("WangJ", "状态栏-方法1:" + statusBarHeight1);

    }
    /**
     * 设置状态栏颜色 默认就是透明的
     */
    public void setStatusBarColor() {
        //1. 设置状态栏颜色
        //        StatusBarUtil.setColor(this, getResources().getColor(R.color.colorContent1));
        //        StatusBarUtil.setColor(this, Color.parseColor("#0094ff"));
        //2. 设置状态栏半透明 0 -255
        // StatusBarUtil.setTranslucent(this, 0);
        //3. 设置状态栏 全透明
        //StatusBarUtil.setTransparent(this); //设置这个 不顶到状态烂
        StatusBarUtil.setTransparent(this);
        //        StatusBarUtil.setColorForSwipeBack(this, Color.parseColor("#ff0000"), 0);//设置滑动返回的activity颜色
    }
    /**
     * 初始化数据---如果需要加载数据 则去加载数据
     */
    protected void initData() {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter!=null){
            mPresenter.detachView();
        }
        mUnbinder.unbind();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(0, R.anim.scale_out);
    }

}
