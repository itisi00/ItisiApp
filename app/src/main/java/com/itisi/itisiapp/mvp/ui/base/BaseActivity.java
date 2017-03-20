package com.itisi.itisiapp.mvp.ui.base;

import android.os.Bundle;
import android.view.WindowManager;

import com.jaeger.library.StatusBarUtil;
import com.orhanobut.logger.Logger;

import butterknife.ButterKnife;
import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

public abstract class BaseActivity extends SwipeBackActivity implements IActivity {
    protected SwipeBackLayout mSwipeBackLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        //透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        //透明导航栏
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        super.onCreate(savedInstanceState);

        setContentView(getlayoutId());
        initBase();
        initData();
        setStatusBarColor();//设置状态栏
    }
    /**
     * 加载布局文件
     * @return
     */
    public abstract int getlayoutId() ;
    /**
     * 初始化
     */
    private void initBase() {
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

        ButterKnife.bind(this);//初始化 ButterKnife
        Logger.init(); //初始化日志

    }
    /**
     * 设置状态栏颜色 默认就是透明的
     */
    public void setStatusBarColor(){
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
    protected void onStart() {
        super.onStart();

    }
}
