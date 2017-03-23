package com.itisi.itisiapp.mvp.ui.main;

import android.content.Intent;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.itisi.itisiapp.R;
import com.itisi.itisiapp.mvp.rx.RxBus;
import com.itisi.itisiapp.mvp.rx.annotation.Subscribe;
import com.itisi.itisiapp.mvp.rx.annotation.UseRxBus;
import com.itisi.itisiapp.mvp.ui.base.BaseRxBusActivity;
import com.itisi.itisiapp.utils.imageload.ImageLoadConfiguration;
import com.itisi.itisiapp.utils.imageload.ImageLoadProxy;
import com.jaeger.library.StatusBarUtil;
import com.mxn.soul.flowingdrawer_core.ElasticDrawer;
import com.mxn.soul.flowingdrawer_core.FlowingDrawer;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 主页面
 * 参考架构 http://www.jianshu.com/p/cdcc9bef5ea0
 */
@UseRxBus
public class MainActivity extends BaseRxBusActivity {

    @BindView(R.id.drawerlayout)
    protected FlowingDrawer mDrawer;


    @BindView(R.id.tv_main)
    protected TextView tv_main;

    @BindView(R.id.iv_userheader)
    protected ImageView iv_userHeader;


    @Override
    protected void onStart() {
        super.onStart();
        setSwipeBackDisabled();//主页禁止滑动关闭
    }

    @Override
    protected void initInject() {

    }

    @Override
    public int getConentlayout() {
        //透明状态栏 如果开启的话 内容会延伸到状态栏
        //getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        return R.layout.activity_main;
    }

    @Override
    public void setStatusBarColor() {
//        super.setStatusBarColor();
        StatusBarUtil.setColor(this, Color.parseColor("#FF4081"));
    }

    /**
     * 主页禁止滑动关闭
     */
    private void setSwipeBackDisabled() {
        mSwipeBackLayout.setEnableGesture(false);//主页禁止滑动关闭
        mDrawer.setTouchMode(ElasticDrawer.TOUCH_MODE_FULLSCREEN);
//        mDrawer.setTouchMode(ElasticDrawer.TOUCH_MODE_BEZEL);
        //        mDrawer.setAlpha(0.5f);
        //        mDrawer.setTouchBezelSize(1);
//                mDrawer.setFitsSystemWindows(true);
        //侧滑菜单 滑动监听
        mDrawer.setOnDrawerStateChangeListener(new ElasticDrawer.OnDrawerStateChangeListener() {
            @Override
            public void onDrawerStateChange(int oldState, int newState) {
                if (newState == ElasticDrawer.STATE_CLOSED) {
//                    Logger.i("Drawer STATE_CLOSED");
                }
            }

            @Override
            public void onDrawerSlide(float openRatio, int offsetPixels) {
//                Logger.i("openRatio:"+openRatio+"==="+"offsetPixels:"+offsetPixels);
            }
        });
    }

    @OnClick(R.id.btn_toast)
    public void test(View view) {
        startActivity(new Intent(MainActivity.this,TestSwipeBackActivity.class));
    }

    @Subscribe(tag= RxBus.TAG_UPDATE)
    private void testRxBus(String txt){
        if (!TextUtils.isEmpty(txt)){
            tv_main.setText(txt);
        }
        else{
            tv_main.setText("没有传值过来");
        }
    }

    @Override
    public void showError(String msg) {

    }

    @Override
    protected void initData() {

        ImageLoadProxy.getInstance().load(new ImageLoadConfiguration.Builder(this).url(R.mipmap.ic_launcher)
                        .isCircle(true).defaultImageResId(R.mipmap.ic_launcher).imageView(iv_userHeader).build());

    }
}
