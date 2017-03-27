package com.itisi.itisiapp.mvp.ui.main;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;

import com.ashokvarma.bottomnavigation.BadgeItem;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.itisi.itisiapp.R;
import com.itisi.itisiapp.mvp.rx.annotation.UseRxBus;
import com.itisi.itisiapp.mvp.ui.base.BaseRxBusActivity;
import com.itisi.itisiapp.mvp.ui.main.chat.ChatFragment;
import com.itisi.itisiapp.mvp.ui.main.guizhou.GuiZhouFragment;
import com.itisi.itisiapp.mvp.ui.main.home.HomeFragment;
import com.itisi.itisiapp.mvp.ui.main.leisure.LeisureFragment;
import com.itisi.itisiapp.mvp.ui.main.news.NewsFragment;
import com.itisi.itisiapp.mvp.ui.main.test.TabAndViewpagerActivity;
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

import javax.inject.Inject;

import butterknife.BindView;

import static com.itisi.itisiapp.utils.ResourceUtil.readStringSource;

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
    @BindView(R.id.bottom_main)
    BottomNavigationBar bottom_main;
    @Inject
    HomeFragment mHomeFragment;
    @Inject
    NewsFragment mNewsFragment;
    @Inject
    GuiZhouFragment mGuiZhouFragment;
    @Inject
    LeisureFragment mLeisureFragment;
    @Inject
    ChatFragment mChatFragment;
    private List<Fragment>mFragments;
    private Fragment mCurrentFragment;


    @Override
    protected void initView() {
        
        mFragments=getFragments();
        setDefaultFragment();

        BadgeItem numberBadgeItem =new BadgeItem()
                .setBorderWidth(4)
                .setBackgroundColorResource(R.color.colorAccent)
                .setText("66")
                .setHideOnSelect(false);
        bottom_main
                .setActiveColor(R.color.colorAccent)
                .setInActiveColor(R.color.gray_btn_bg_color)
                .addItem(new BottomNavigationItem(R.mipmap.menu_home_white,R.string.menu_home)
                        .setActiveColor("#00776a"))
                .addItem(new BottomNavigationItem(R.mipmap.menu_time_white,R.string.menu_news)
                        .setActiveColor("#8d6b63")
                .setBadgeItem(numberBadgeItem))
                .addItem(new BottomNavigationItem(R.mipmap.menu_love_white,R.string.menu_guizhou)
                        .setActiveColor("#2293f4"))
                .addItem(new BottomNavigationItem(R.mipmap.menu_music_white,R.string.menu_leisure)
                        .setActiveColor("#ff4081"))
                .addItem(new BottomNavigationItem(R.mipmap.menu_chat_white,R.string.menu_chat)
                        .setActiveColor("#9966cc"))
                .setFirstSelectedPosition(0)
                .initialise();


        iv_userHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this,TestSwipeBackActivity.class));
                startActivity(new Intent(MainActivity.this,TabAndViewpagerActivity.class));
            }
        });
    }

    //
    @Override
    protected void initListener() {
        setSwipeBackDisabled();//主页禁止滑动关闭
        bottom_main.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                if(mFragments!=null) {
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
                Logger.i("unselected:"+position);
                if(mFragments!=null){
                    if (position<mFragments.size()){
                        FragmentManager fm=getSupportFragmentManager();
                        FragmentTransaction transaction=fm.beginTransaction();
                        transaction.remove(mFragments.get(position));
                        transaction.commitAllowingStateLoss();

                    }
                }

            }

            @Override
            public void onTabReselected(int position) {

            }
        });
    }


    @Override
    protected void initInject() {

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
//        StatusBarUtil.setTranslucent(this);
    }

    /**
     * 主页 不显示返回按钮
     * @return
     */
    @Override
    public boolean isShowBacking() {
        return false;
    }

    @Override
    public String setSubTitle() {
        return readStringSource(this,R.string.icon_more);
    }


    @Override
    protected void onSubTitleViewClick() {
        TastyToast.makeText(this,"toolbar in main ",TastyToast.LENGTH_SHORT,TastyToast.SUCCESS).show();
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
//        mDrawer.setFitsSystemWindows(true);
//        mDrawer.setElevation(10F);
        fm_menu.setRotationY(8F);
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
    @Override
    public void showError(String msg) {

    }

    @Override
    protected void initData() {

        ImageLoadProxy.getInstance().load(new ImageLoadConfiguration.Builder(this).url(R.mipmap.ic_launcher)
                        .isCircle(true).defaultImageResId(R.mipmap.ic_launcher).imageView(iv_userHeader).build());

    }

    /**
     * 初始化 主界面名的 fragment 集合
     * @return
     */
    private List<Fragment> getFragments() {
        mFragments=new ArrayList<>();
        if (mHomeFragment==null){
            mHomeFragment=new HomeFragment();
        }

        mFragments.add(mHomeFragment);
        if (mNewsFragment==null){
            mNewsFragment=new NewsFragment();
        }
        mFragments.add(mNewsFragment);

        if (mGuiZhouFragment==null){
            mGuiZhouFragment=new GuiZhouFragment();
        }
        mFragments.add(mGuiZhouFragment);

        if (mLeisureFragment==null){
            mLeisureFragment=new LeisureFragment();
        }
        mFragments.add(mLeisureFragment);

        if (mChatFragment==null){
            mChatFragment=new ChatFragment();
        }
        mFragments.add(mChatFragment);

        return mFragments;
    }

    /**
     * 设置默认显示的fragment
     */
    private void setDefaultFragment() {
        Logger.i(mHomeFragment.toString());//看看是不是同一个对象

        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction transaction=fm.beginTransaction();
        transaction.replace(R.id.fl_main,mHomeFragment);
        transaction.commit();

    }

}
