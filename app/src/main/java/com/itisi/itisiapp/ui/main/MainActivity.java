package com.itisi.itisiapp.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.itisi.itisiapp.R;
import com.itisi.itisiapp.ui.base.BaseActivity;
import com.jaeger.library.StatusBarUtil;
import com.mxn.soul.flowingdrawer_core.ElasticDrawer;
import com.mxn.soul.flowingdrawer_core.FlowingDrawer;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 主页面
 * 参考架构 http://www.jianshu.com/p/cdcc9bef5ea0
 */
public class MainActivity extends BaseActivity {

    @BindView(R.id.drawerlayout)
    protected FlowingDrawer mDrawer;

    @BindView(R.id.iv_header)
    protected ImageView iv_header ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Logger.i("子类的oncreate");

    }

    @Override
    protected void onStart() {
        super.onStart();
        initMain();
    }

    private void initMain() {
        //设置状态栏颜色
        StatusBarUtil.setColor(this,getResources().getColor(R.color.colorContent1));
//        ButterKnife.bind(this);//初始化 能否放在 basexxx中?
        mSwipeBackLayout.setEnableGesture(false);//主页禁止滑动关闭
        mDrawer.setTouchMode(ElasticDrawer.TOUCH_MODE_FULLSCREEN);
//        mDrawer.setAlpha(0.5f);
//        mDrawer.setTouchBezelSize(1);
//        mDrawer.setFitsSystemWindows(true);
        //侧滑菜单 滑动监听
        mDrawer.setOnDrawerStateChangeListener(new ElasticDrawer.OnDrawerStateChangeListener() {
            @Override
            public void onDrawerStateChange(int oldState, int newState) {
                if (newState == ElasticDrawer.STATE_CLOSED) {
                }
            }
            @Override
            public void onDrawerSlide(float openRatio, int offsetPixels) {
            }
        });

        //设置左侧菜单 距离顶部的距离
       // iv_header.setTop(100);
    }


    @OnClick(R.id.btn_toast)
    public void test(View view) {
        startActivity(new Intent(MainActivity.this,TestSwipeBackActivity.class));
    }


}
