package com.itisi.itisiapp.mvp.ui.main;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.itisi.itisiapp.R;
import com.itisi.itisiapp.mvp.rx.RxBus;
import com.itisi.itisiapp.mvp.rx.annotation.Subscribe;
import com.itisi.itisiapp.mvp.rx.annotation.UseRxBus;
import com.itisi.itisiapp.mvp.ui.adapter.MainFragmentPagerAdapter;
import com.itisi.itisiapp.mvp.ui.base.BaseRxBusActivity;
import com.itisi.itisiapp.utils.imageload.ImageLoadConfiguration;
import com.itisi.itisiapp.utils.imageload.ImageLoadProxy;
import com.mxn.soul.flowingdrawer_core.ElasticDrawer;
import com.mxn.soul.flowingdrawer_core.FlowingDrawer;
import com.mxn.soul.flowingdrawer_core.FlowingMenuLayout;
import com.orhanobut.logger.Logger;

import butterknife.BindView;

/**
 * 主页面
 * 参考架构 http://www.jianshu.com/p/cdcc9bef5ea0
 */
@UseRxBus
public class MainActivity extends BaseRxBusActivity {

    @BindView(R.id.iv_userheader)
    protected ImageView iv_userHeader;
    @BindView(R.id.drawerlayout)
     FlowingDrawer mDrawer;
    @BindView(R.id.fm_Menu)
    FlowingMenuLayout fm_menu;
    @BindView(R.id.tablayout_main)
     TabLayout mTabLayout;
    @BindView(R.id.viewpager_main)
     ViewPager mViewPager;

//    @BindView(R.id.tv_main)
//    protected TextView tv_main;
    private MainFragmentPagerAdapter mMainFragmentPagerAdapter;
    private TabLayout.Tab one;
    private TabLayout.Tab two;
    private TabLayout.Tab three;
    private TabLayout.Tab four;


    //事件分发顺序 Activity(Window)->ViewGroup->View



    @Override
    protected void initListener() {
        setSwipeBackDisabled();//主页禁止滑动关闭
    }

    @Override
    protected void initView() {
        //使用适配器将viewpager与fragment绑定在一起
        mMainFragmentPagerAdapter=new MainFragmentPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mMainFragmentPagerAdapter);

        //将tablayout与viewpager绑定在一起
        mTabLayout.setupWithViewPager(mViewPager);

        //指定tab的位置
        one=mTabLayout.getTabAt(0);
        two=mTabLayout.getTabAt(1);
        three=mTabLayout.getTabAt(2);
        four=mTabLayout.getTabAt(3);
        //设置tabitem 图标
//        one.setIcon(R.mipmap.ic_launcher);
//        two.setIcon(R.mipmap.ic_launcher);
//        three.setIcon(R.mipmap.ic_launcher);
//        four.setIcon(R.mipmap.ic_launcher);

        // 页面切换事件
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                Logger.i(position+", positionOffset="+positionOffset+", positionOffsetPixels="+positionOffsetPixels);

            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        iv_userHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logger.i("test");
            }
        });
    }

    @Override
    protected void initInject() {

    }

    @Override
    public int getConentlayout() {
        //透明状态栏 如果开启的话 内容会延伸到状态栏
        return R.layout.activity_main;
    }

    @Override
    public void setStatusBarColor() {
//        super.setStatusBarColor();
//        StatusBarUtil.setColor(this, Color.parseColor("#99FF4081"));
//        StatusBarUtil.setTranslucent(this);
    }

    /**
     * 主页禁止滑动关闭
     */
    private void setSwipeBackDisabled() {
        mSwipeBackLayout.setEnableGesture(false);//主页禁止滑动关闭
//        mDrawer.setTouchMode(ElasticDrawer.TOUCH_MODE_FULLSCREEN);
        mDrawer.setTouchMode(ElasticDrawer.TOUCH_MODE_BEZEL);
//                mDrawer.setAlpha(0.5f);//整个界面的透明度
//        mDrawer.setAnimation();

       // mDrawer.setRotationY(-10F);
//        mDrawer.setRotationX(0.5F);
//        mDrawer.setScaleX(0.5F);//整个界面缩小
        mDrawer.setFitsSystemWindows(true);

        fm_menu.setRotationY(10F);
        //        fm_menu.setScaleX(0.8F);
//        fm_menu.setTranslationZ(100F); //api 21
        //        fm_menu.setTranslationX(100F);
//                fm_menu.setTranslationY(50F);
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

//    @OnClick(R.id.btn_toast)
//    public void test(View view) {
//        startActivity(new Intent(MainActivity.this,TestSwipeBackActivity.class));
//    }


//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        return super.onTouchEvent(event);
//    }



    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
//        int currentItem = mViewPager.getCurrentItem();
//        if (currentItem!=0){//在其他页面
//            return true;
//        }
        return super.dispatchTouchEvent(ev);
    }

    @Subscribe(tag= RxBus.TAG_UPDATE)
    private void testRxBus(String txt){
        if (!TextUtils.isEmpty(txt)){
           // tv_main.setText(txt);
        }
        else{
            //tv_main.setText("没有传值过来");
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
