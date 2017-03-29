package com.itisi.itisiapp.mvp.ui.base;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.itisi.itisiapp.R;
import com.itisi.itisiapp.app.ItisiApp;
import com.itisi.itisiapp.di.component.ActivityComponent;
import com.itisi.itisiapp.di.component.DaggerActivityComponent;
import com.itisi.itisiapp.di.modeule.ActivityModule;
import com.itisi.itisiapp.utils.SceneAnim;
import com.jaeger.library.StatusBarUtil;
import com.orhanobut.logger.Logger;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

public abstract class BaseActivity<P extends BasePresenter> extends SwipeBackActivity implements BaseView {

    protected SwipeBackLayout mSwipeBackLayout;

    @BindView(R.id.toolbar)
    protected Toolbar mToolbar;
    @BindView(R.id.toolbar_title)
    protected TextView mToolbarTitle;
    @BindView(R.id.toolbar_subtitle)
    protected TextView mToolbarSubTitle;

    @Inject
    protected P mPresenter; //对应的presenter
    private Unbinder mUnbinder;//buterknife 绑定的对象

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getConentlayout());
        mUnbinder = ButterKnife.bind(this);//初始化 ButterKnife
        initView();
        setStatusBarColor();//设置状态栏
        initSwipeBack();//初始化 侧滑返回

        initListener();

        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
        }
        if (mToolbarTitle != null) {
            //getTitle() 的值是activity的 android:label的属性值
            mToolbarTitle.setText(getTitle());
            //默认的标题不显示
            getSupportActionBar().setDisplayShowTitleEnabled(false);

        }
        initInject();//dagger2 注入
        if (mPresenter != null) {//
            mPresenter.attachView(this);
        }
        initData();//初始化 数据
        Logger.init(); //初始化日志
    }
    /**
     * 初始化
     */
    private void initSwipeBack() {
        mSwipeBackLayout = getSwipeBackLayout();
        //设置滑动方向，可设置EDGE_LEFT, EDGE_RIGHT, EDGE_ALL, EDGE_BOTTOM
        mSwipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);
       // mSwipeBackLayout.setEdgeSize(20);//滑动范围???? 如果是那就太好了
        //这么设置不明显 修改源码 getEdgeTouched
//        mSwipeBackLayout.setRotationY(50F);

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

    @Override
    protected void onStart() {
        super.onStart();
       //判断是否有toolbar 并默认显示返回按钮
        if (getToolbar() != null && isShowBacking()) {
            showBack();
        }
        //设置大标题
        if (getToolbar() != null && !TextUtils.isEmpty(setToolbarTitle())) {
            if (getToolbar() != null) {
                mToolbarTitle.setText(setToolbarTitle());
            } else {
                getToolbar().setTitle(setToolbarTitle());
                setSupportActionBar(getToolbar());
            }
        }
        /**
         * 设置右侧文字
         */
        if (getSubTitleView() != null && !TextUtils.isEmpty(setSubTitle())) {
            getSubTitleView().setText(setSubTitle());
        }
        /**
         * 设置右侧图标
         */
        if (getSubTitleView() != null && setSubTitleIcon() > 0) {
            Drawable drawable = this.getResources().getDrawable(setSubTitleIcon());
            //必须设置图片大小 否则不显示
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            getSubTitleView().setCompoundDrawables(null, null, drawable, null);
        }
        //toolbar 右侧文字或者图标的 点击事件
        getSubTitleView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSubTitleViewClick();
            }
        });

    }

    private Toolbar getToolbar() {
        return mToolbar;
    }

    /**
     * 是否显示返回按钮
     * @return
     */
    public boolean isShowBacking() {
        return true;
    }

    /**
     * 设置toolbar右侧图标
     * @return
     */
    public int setSubTitleIcon() {
        return -1;
    }

    /**
     * 获取标题控件
     * @return
     */
    public TextView getTitleView() {
        return mToolbarTitle;
    }

    /**
     * 获取副标题控件
     * @return
     */
    public TextView getSubTitleView() {
        return mToolbarSubTitle;
    }

    /**
     * toolbar的标题 重写即可
     * @return
     */
    public String setToolbarTitle() {
        return "";
    }

    /**
     * 设置toolbar右侧标题
     */
    public String setSubTitle() {
        return "";
    }

    /**
     * 本班号小于21的后退按钮图片
     */
    protected void showBack() {
        //setNavigationIcon必须在setSupportActionBar(toolbar);方法后面加入
        getToolbar().setNavigationIcon(R.mipmap.back);
        getToolbar().setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    /**
     * toolbar右侧view的点击事件 由子类实现
     */
    protected  void onSubTitleViewClick(){}

    /**
     * 注入
     */
    protected abstract void initInject();

    protected ActivityComponent getActivityComponent() {
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
     * 设置状态栏颜色 默认就是透明的
     */
    public void setStatusBarColor() {
        //1. 设置状态栏颜色
        //        StatusBarUtil.setColor(this, getResources().getColor(R.color.colorContent1));
        //        StatusBarUtil.setColor(this, Color.parseColor("#0094ff"));
        //2. 设置状态栏半透明 0 -255
        // StatusBarUtil.setTranslucent(this, 0);
        //3. 设置状态栏 全透明
        //        StatusBarUtil.setTransparent(this); //设置这个 不顶到状态烂
        StatusBarUtil.setTransparent(this);
        //        StatusBarUtil.setColorForSwipeBack(this, Color.parseColor("#ff0000"), 0);//设置滑动返回的activity颜色
    }

    /**
     * 初始化其他组件 也行有需要
     */
    protected void initView() {}

    /**
     * 初始化事件监听
     */
    protected void initListener() {}
    /**
     * 初始化数据---如果需要加载数据 则去加载数据
     */
    protected void initData() {}

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        mUnbinder.unbind();
    }
    /**
     * 后退键事件
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        SceneAnim.closeActivityByScaleAlpha(this);
    }


}
