package com.itisi.itisiapp.mvp.ui.main;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;

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
import com.itisi.itisiapp.utils.SceneAnim;
import com.itisi.itisiapp.utils.ToastUtil;
import com.itisi.itisiapp.utils.imageload.ImageLoadConfiguration;
import com.itisi.itisiapp.utils.imageload.ImageLoadProxy;
import com.mxn.soul.flowingdrawer_core.ElasticDrawer;
import com.mxn.soul.flowingdrawer_core.FlowingDrawer;
import com.mxn.soul.flowingdrawer_core.FlowingMenuLayout;
import com.orhanobut.logger.Logger;
import com.sdsmdg.tastytoast.TastyToast;
import com.yalantis.phoenix.PullToRefreshView;

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
public class MainActivity extends BaseRxBusActivity<MainPresenter> implements MainContract.View, View.OnClickListener {

    //左侧
    @BindView(R.id.iv_left_header)
    protected ImageView iv_left_header;
    @BindView(R.id.pullrefresh)
    PullToRefreshView mPullToRefreshView ;
    //主页 布局
    @BindView(R.id.drawerlayout)
    FlowingDrawer mDrawer;
    @BindView(R.id.fm_Menu)
    FlowingMenuLayout fm_menu;
    @BindView(R.id.bottom_main)
    BottomNavigationBar bottom_main;

    // 左侧菜单的 item
    @BindView(R.id.rb_agenda)
    RadioButton rb_agenda;
    @BindView(R.id.rb_birthday)
    RadioButton rb_birthday;
    @BindView(R.id.rb_account)
    RadioButton rb_account;
    @BindView(R.id.rb_footprint)
    RadioButton rb_footprint;
    @BindView(R.id.rb_album)
    RadioButton rb_album;
    @BindView(R.id.rb_collection)
    RadioButton rb_collection;
    @BindView(R.id.rb_setting)
    RadioButton rb_setting;
    @BindView(R.id.rb_theme)
    RadioButton rb_theme;

    //5个主界面
    HomeFragment mHomeFragment;//主页
    NewsFragment mNewsFragment;//新闻
    GuiZhouFragment mGuiZhouFragment;//大贵州
    LeisureFragment mLeisureFragment;//休闲
    ChatFragment mChatFragment;//聊天

    ClickTree mClickTree = new ClickTree(2); //点击树
    private List<Fragment> mFragments;//main 中的几个页面的fragment集合
    private Fragment mCurrentFragment;//home中当前显示的fragment
    private boolean isShown = false;//菜单是否处于打开状态




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

        //下拉刷新 设置
//        mPullToRefreshView.setRefreshStyle(PullToRefreshView.STYLE_SUN);
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
//                Logger.i("unselected:" + position);
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

        //左侧菜单点击事件
        iv_left_header.setOnClickListener(this);
        rb_agenda.setOnClickListener(this);
        rb_birthday.setOnClickListener(this);
        rb_account.setOnClickListener(this);
        rb_footprint.setOnClickListener(this);
        rb_album.setOnClickListener(this);
        rb_collection.setOnClickListener(this);
        rb_setting.setOnClickListener(this);
        rb_theme.setOnClickListener(this);

       //侧滑菜单 滑动监听
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

        //下拉刷新
        mPullToRefreshView .setOnRefreshListener(new PullToRefreshView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPullToRefreshView .postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Logger.i("刷新数据");
                        mPullToRefreshView .setRefreshing(false);
                    }
                },3000);
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


    /**
     * 主页 不显示返回按钮
     * @return
     */
    @Override
    public boolean isShowBacking() {
        return false;
    }

    /**
     * 设置toolbar右侧标题
     * @return
     */
    @Override
    public String setSubTitle() {
        return readStringSource(this, R.string.icon_temperature);
    }

    /**
     * toolbar 右侧标题 点击事件
     */
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

    /**
     * 显示错误信息
     * @param msg
     */
    @Override
    public void showError(String msg) {

    }

    /**
     * 初始化数据
     */
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
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.fl_main, mHomeFragment);
        transaction.commit();
    }

    /**
     * 显示内容 这个方法可能会改动
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
            boolean clickResult = mClickTree.completeClickCount();
            if (clickResult) {
                System.exit(0);//直接杀死进程???? 是不是不妥
            } else {
                ToastUtil.Info("双击退出程序");
            }
        }
    }

    /**
     * 点击事件
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_left_header:
                startActivity(new Intent(ItisiApp.getInstance(), UserActivity.class));
                SceneAnim.openActivityByScaleAlpha(this);
                break;
            case R.id.rb_agenda:
                startActivity(new Intent(ItisiApp.getInstance(), AgendaActivity.class));
                SceneAnim.openActivityByScaleAlpha(this);
                break;
            case R.id.rb_birthday:
                startActivity(new Intent(ItisiApp.getInstance(), BirthdayActivity.class));
                SceneAnim.openActivityByScaleAlpha(this);
                break;
            case R.id.rb_account:
                startActivity(new Intent(ItisiApp.getInstance(), AccountActivity.class));
                SceneAnim.openActivityByScaleAlpha(this);
                break;
            case R.id.rb_footprint:
                startActivity(new Intent(ItisiApp.getInstance(), FootprintActivity.class));
                SceneAnim.openActivityByScaleAlpha(this);
                break;
            case R.id.rb_album:
                startActivity(new Intent(ItisiApp.getInstance(), AlbumActivity.class));
                SceneAnim.openActivityByScaleAlpha(this);
                break;
            case R.id.rb_collection:
                startActivity(new Intent(ItisiApp.getInstance(), CollectionActivity.class));
                SceneAnim.openActivityByScaleAlpha(this);
                break;
            case R.id.rb_about:
                startActivity(new Intent(ItisiApp.getInstance(), AboutActivity.class));
                SceneAnim.openActivityByScaleAlpha(this);
                break;
            case R.id.rb_setting:
                startActivity(new Intent(ItisiApp.getInstance(), SettingActivity.class));
                SceneAnim.openActivityByScaleAlpha(this);
                break;
            case R.id.rb_theme:
                ToastUtil.Success("主题设置/切换");
                break;
        }

    }

}
