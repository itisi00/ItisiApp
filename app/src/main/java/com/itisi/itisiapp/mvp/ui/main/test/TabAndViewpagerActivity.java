package com.itisi.itisiapp.mvp.ui.main.test;

import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;

import com.itisi.itisiapp.R;
import com.itisi.itisiapp.mvp.rx.RxBus;
import com.itisi.itisiapp.mvp.rx.annotation.Subscribe;
import com.itisi.itisiapp.mvp.ui.adapter.MainFragmentPagerAdapter;
import com.itisi.itisiapp.mvp.ui.base.BaseRxBusActivity;
import com.itisi.itisiapp.utils.ResourceUtil;
import com.jaeger.library.StatusBarUtil;
import com.sdsmdg.tastytoast.TastyToast;

import butterknife.BindView;

public class TabAndViewpagerActivity  extends BaseRxBusActivity {

    @BindView(R.id.tablayout_main)
    TabLayout mTabLayout;
    @BindView(R.id.viewpager_main)
    ViewPager mViewPager;


    private MainFragmentPagerAdapter mMainFragmentPagerAdapter;
    private TabLayout.Tab one;
    private TabLayout.Tab two;
    private TabLayout.Tab three;
    private TabLayout.Tab four;

    @Override
    protected void initView() {

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

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


    }

    @Override
    protected void initListener() {
        //有tab 滑动 暂时禁止滑动关闭 待解决手势冲突后 再说
//        mSwipeBackLayout.setEnableGesture(false);
    }

    @Override
    public void setStatusBarColor() {
        super.setStatusBarColor();
        //5.0 以上有用  4.4.4 以上 随背景颜色
        StatusBarUtil.setColor(this, Color.parseColor("#FF4081"));
        //        StatusBarUtil.setTranslucent(this);
    }



    @Override
    public String setSubTitle() {
        return ResourceUtil.readStringSource(this,R.string.icon_more);
    }


    @Override
    protected void onSubTitleViewClick() {
        TastyToast.makeText(this,"toolbar in tabandviewpager ",TastyToast.LENGTH_SHORT,TastyToast.SUCCESS).show();
    }


    @Override
    protected void initInject() {

    }

    @Override
    public int getConentlayout() {
        return R.layout.activity_tab_and_viewpager;
    }

    @Override
    public void showError(String msg) {

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

}
