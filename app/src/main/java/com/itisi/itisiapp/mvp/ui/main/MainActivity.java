package com.itisi.itisiapp.mvp.ui.main;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;

import com.ashokvarma.bottomnavigation.BadgeItem;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.itisi.itisiapp.R;
import com.itisi.itisiapp.app.ItisiApp;
import com.itisi.itisiapp.mvp.model.entity.GankFuLiEntity;
import com.itisi.itisiapp.mvp.rx.annotation.UseRxBus;
import com.itisi.itisiapp.mvp.ui.about.AboutActivity;
import com.itisi.itisiapp.mvp.ui.account.AccountActivity;
import com.itisi.itisiapp.mvp.ui.agenda.AgendaActivity;
import com.itisi.itisiapp.mvp.ui.album.AlbumActivity;
import com.itisi.itisiapp.mvp.ui.base.BaseRxBusActivity;
import com.itisi.itisiapp.mvp.ui.birthday.BirthdayActivity;
import com.itisi.itisiapp.mvp.ui.collection.CollectionActivity;
import com.itisi.itisiapp.mvp.ui.footprint.FootprintActivity;
import com.itisi.itisiapp.mvp.ui.main.chat.ChatFragment;
import com.itisi.itisiapp.mvp.ui.main.guizhou.GuiZhouFragment;
import com.itisi.itisiapp.mvp.ui.main.home.HomeFragment;
import com.itisi.itisiapp.mvp.ui.main.leisure.LeisureFragment;
import com.itisi.itisiapp.mvp.ui.main.news.NewsFragment;
import com.itisi.itisiapp.mvp.ui.setting.SettingActivity;
import com.itisi.itisiapp.mvp.ui.user.UserActivity;
import com.itisi.itisiapp.utils.ClickTree;
import com.itisi.itisiapp.utils.ToastUtil;
import com.itisi.itisiapp.utils.imageload.ImageLoadConfiguration;
import com.itisi.itisiapp.utils.imageload.ImageLoadProxy;
import com.jaeger.library.StatusBarUtil;
import com.mxn.soul.flowingdrawer_core.ElasticDrawer;
import com.mxn.soul.flowingdrawer_core.FlowingDrawer;
import com.mxn.soul.flowingdrawer_core.FlowingMenuLayout;
import com.orhanobut.logger.Logger;
import com.sdsmdg.tastytoast.TastyToast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.itisi.itisiapp.utils.ResourceUtil.readStringSource;

/**
 * 主页面
 * 参考架构 http://www.jianshu.com/p/cdcc9bef5ea0
 * 如果需要事件传递 则继承 BaseRxBusActivity
 * 如果不需要 则可直接继承 BaseActivity
 */
@UseRxBus
public class MainActivity extends BaseRxBusActivity<MainPresenter> implements MainContract.View, RadioGroup.OnCheckedChangeListener, View.OnClickListener {
    //左侧
    @BindView(R.id.iv_left_header)
    protected ImageView iv_left_header;
    //主页 布局
    @BindView(R.id.drawerlayout)
    FlowingDrawer mDrawer;
    @BindView(R.id.fm_Menu)
    FlowingMenuLayout fm_menu;
    @BindView(R.id.bottom_main)
    BottomNavigationBar bottom_main;

    @BindView(R.id.rg_left_menu)
    RadioGroup rg_left_menu;
    @BindView(R.id.rg_left_bottom)
    RadioGroup rg_left_bottom;


    HomeFragment mHomeFragment;
    NewsFragment mNewsFragment;
    GuiZhouFragment mGuiZhouFragment;
    LeisureFragment mLeisureFragment;
    ChatFragment mChatFragment;
    //点击树
    ClickTree mClickTree = new ClickTree(2);
    private List<Fragment> mFragments;
    private Fragment mCurrentFragment;
    private boolean isShown = false;//菜单是否处于打开模式

    @Override
    protected void initView() {
        //fragment 页面初始化
        mFragments = getFragments();
        setDefaultFragment();

        //主页底部导航栏
        BadgeItem numberBadgeItem = new BadgeItem()
                .setBorderWidth(4)
                .setBackgroundColorResource(R.color.colorAccent)
                .setText("66")
                .setHideOnSelect(false);
        bottom_main
                .setActiveColor(R.color.colorAccent)
                .setInActiveColor(R.color.gray_btn_bg_color)
                .addItem(new BottomNavigationItem(R.mipmap.menu_home_white, R.string.menu_home)
                        .setActiveColor("#00776a"))
                .addItem(new BottomNavigationItem(R.mipmap.menu_time_white, R.string.menu_news)
                        .setActiveColor("#8d6b63")
                        .setBadgeItem(numberBadgeItem))
                .addItem(new BottomNavigationItem(R.mipmap.menu_love_white, R.string.menu_guizhou)
                        .setActiveColor("#2293f4"))
                .addItem(new BottomNavigationItem(R.mipmap.menu_music_white, R.string.menu_leisure)
                        .setActiveColor("#ff4081"))
                .addItem(new BottomNavigationItem(R.mipmap.menu_chat_white, R.string.menu_chat)
                        .setActiveColor("#9966cc"))
                .setFirstSelectedPosition(0)
                .initialise();

    }

    @Override
    protected void initListener() {
        setSwipeBackDisabled();//主页禁止滑动关闭
        //底部 bottomnavigation点击事件
        bottom_main.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                if (mFragments != null) {
                    if (position < mFragments.size()) {
                        FragmentManager fm = getSupportFragmentManager();
                        FragmentTransaction transaction = fm.beginTransaction();
                        mCurrentFragment = mFragments.get(position);
                        if (mCurrentFragment.isAdded()) {
                            transaction.replace(R.id.fl_main, mCurrentFragment);
                        } else {
                            transaction.add(R.id.fl_main, mCurrentFragment);
                        }
                        // transaction.commit();
                        transaction.commitAllowingStateLoss();

                    }
                }
            }

            @Override
            public void onTabUnselected(int position) {
                Logger.i("unselected:" + position);
                if (mFragments != null) {
                    if (position < mFragments.size()) {
                        FragmentManager fm = getSupportFragmentManager();
                        FragmentTransaction transaction = fm.beginTransaction();
                        transaction.remove(mFragments.get(position));
                        transaction.commitAllowingStateLoss();

                    }
                }

            }

            @Override
            public void onTabReselected(int position) {

            }
        });
        //头像
        rg_left_menu.setOnCheckedChangeListener(this);
        rg_left_bottom.setOnCheckedChangeListener(this);
        //左侧菜单点击事件
        iv_left_header.setOnClickListener(this);


        mDrawer.setOnDrawerStateChangeListener(new ElasticDrawer.OnDrawerStateChangeListener() {
            @Override
            public void onDrawerStateChange(int oldState, int newState) {
                if (newState == ElasticDrawer.STATE_CLOSED) {
                    isShown = false;
                } else if (newState == ElasticDrawer.STATE_OPEN) {
                    isShown = true;
                }
            }

            @Override
            public void onDrawerSlide(float openRatio, int offsetPixels) {
                //Log.i("MainActivity", "openRatio=" + openRatio + " ,offsetPixels=" + offsetPixels);
            }
        });



    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    public int getConentlayout() {
        return R.layout.activity_main;
    }

    @Override
    public void setStatusBarColor() {
        super.setStatusBarColor();
        //5.0 以上有用  4.4.4 以上 随背景颜色
        StatusBarUtil.setColor(this, Color.parseColor("#FF4081"));
    }

    /**
     * 主页 不显示返回按钮
     *
     * @return
     */
    @Override
    public boolean isShowBacking() {
        return false;
    }

    @Override
    public String setSubTitle() {
        return readStringSource(this, R.string.icon_temperature);
    }

    @Override
    protected void onSubTitleViewClick() {
        TastyToast.makeText(this, "跳转天气页面?", TastyToast.LENGTH_SHORT, TastyToast.SUCCESS).show();
    }

    /**
     * 主页禁止滑动关闭
     */
    private void setSwipeBackDisabled() {
        mSwipeBackLayout.setEnableGesture(false);//主页禁止滑动关闭
        mDrawer.setTouchMode(ElasticDrawer.TOUCH_MODE_FULLSCREEN);
        mDrawer.setClickable(false);
        //        mDrawer.setTouchMode(ElasticDrawer.TOUCH_MODE_BEZEL);
        //                mDrawer.setAlpha(0.5f);//整个界面的透明度
        //        mDrawer.setAnimation();
        // mDrawer.setRotationY(-10F);
        //        mDrawer.setRotationX(0.5F);
        //        mDrawer.setScaleX(0.5F);//整个界面缩小
        //        mDrawer.setFitsSystemWindows(true);
        //        mDrawer.setElevation(10F);
        //        fm_menu.setRotationY(8F);
        //        fm_menu.setScaleX(0.8F);
        //        fm_menu.setTranslationZ(100F); //api 21
        //        fm_menu.setTranslationX(100F);
        //                fm_menu.setTranslationY(50F);
        //侧滑菜单 滑动监听
        //        mDrawer.setOnDrawerStateChangeListener(new ElasticDrawer.OnDrawerStateChangeListener() {
        //            @Override
        //            public void onDrawerStateChange(int oldState, int newState) {
        //                if (newState == ElasticDrawer.STATE_CLOSED) {
        ////                    Logger.i("Drawer STATE_CLOSED");
        //                }
        //            }
        //
        //            @Override
        //            public void onDrawerSlide(float openRatio, int offsetPixels) {
        ////                Logger.i("openRatio:"+openRatio+"==="+"offsetPixels:"+offsetPixels);
        //            }
        //        });
    }

    @Override
    public void showError(String msg) {

    }

    @Override
    protected void initData() {
        ImageLoadProxy.getInstance().load(new ImageLoadConfiguration.Builder(this).url(R.mipmap.ic_launcher)
                .isCircle(true).defaultImageResId(R.mipmap.ic_launcher).imageView(iv_left_header).build());

    }

    /**
     * 初始化 主界面名的 fragment 集合
     *
     * @return
     */
    private List<Fragment> getFragments() {
        mFragments = new ArrayList<>();
        if (mHomeFragment == null) {
            mHomeFragment = new HomeFragment();
        }

        mFragments.add(mHomeFragment);
        if (mNewsFragment == null) {
            mNewsFragment = new NewsFragment();
        }
        mFragments.add(mNewsFragment);

        if (mGuiZhouFragment == null) {
            mGuiZhouFragment = new GuiZhouFragment();
        }
        mFragments.add(mGuiZhouFragment);

        if (mLeisureFragment == null) {
            mLeisureFragment = new LeisureFragment();
        }
        mFragments.add(mLeisureFragment);

        if (mChatFragment == null) {
            mChatFragment = new ChatFragment();
        }
        mFragments.add(mChatFragment);

        return mFragments;
    }

    /**
     * 设置默认显示的fragment
     */
    private void setDefaultFragment() {
        //        Logger.i(mHomeFragment.toString());//看看是不是同一个对象

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.fl_main, mHomeFragment);
        transaction.commit();

    }

    /**
     * 显示内容 这个方法可能会改动
     *
     * @param list
     */
    @Override
    public void showContent(List<GankFuLiEntity> list) {

    }

    @Override
    public void onBackPressed() {
        if (isShown) {//菜单打开的情况下 关闭菜单
            isShown = false;
            mDrawer.closeMenu(true);
        } else {
            //            super.onBackPressed();
            boolean clickResult = mClickTree.completeClickCount();
            if (clickResult) {
                System.exit(0);//直接杀死进程???? 是不是不妥
            } else {
                ToastUtil.Info("双击退出程序");
            }
        }
    }


    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        switch (checkedId) {
            case R.id.rb_agenda:
                startActivity(new Intent(ItisiApp.getInstance(), AgendaActivity.class));
                break;
            case R.id.rb_birthday:
                startActivity(new Intent(ItisiApp.getInstance(), BirthdayActivity.class));
                break;
            case R.id.rb_account:
                startActivity(new Intent(ItisiApp.getInstance(), AccountActivity.class));
                break;
            case R.id.rb_footprint:
                startActivity(new Intent(ItisiApp.getInstance(), FootprintActivity.class));
                break;
            case R.id.rb_album:
                startActivity(new Intent(ItisiApp.getInstance(), AlbumActivity.class));
                break;
            case R.id.rb_collection:
                startActivity(new Intent(ItisiApp.getInstance(), CollectionActivity.class));
                break;
            case R.id.rb_about:
                startActivity(new Intent(ItisiApp.getInstance(), AboutActivity.class));
                break;
            case R.id.rb_setting:
                startActivity(new Intent(ItisiApp.getInstance(), SettingActivity.class));
                break;
            case R.id.rb_theme:
                ToastUtil.Success("主题设置/切换");
                break;

        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.iv_left_header:
                startActivity(new Intent(ItisiApp.getInstance(), UserActivity.class));
                break;

        }

    }
}
