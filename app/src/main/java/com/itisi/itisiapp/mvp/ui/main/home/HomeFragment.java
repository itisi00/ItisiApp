package com.itisi.itisiapp.mvp.ui.main.home;


import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.itisi.itisiapp.R;
import com.itisi.itisiapp.app.ItisiApp;
import com.itisi.itisiapp.mvp.model.entity.GankFuLiEntity;
import com.itisi.itisiapp.mvp.ui.base.BaseFragment;
import com.itisi.itisiapp.mvp.ui.main.test.TabAndViewpagerActivity;
import com.itisi.itisiapp.mvp.ui.read.ReadActivity;
import com.itisi.itisiapp.mvp.ui.rental.RentalActivity;
import com.itisi.itisiapp.mvp.ui.select.SelectActivity;
import com.itisi.itisiapp.utils.SceneAnim;
import com.itisi.itisiapp.utils.imageload.ImageLoadConfiguration;
import com.itisi.itisiapp.utils.imageload.ImageLoadProxy;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 主页
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment  extends BaseFragment<HomePresenter> implements HomeContract.View,View.OnClickListener  {

    @BindView(R.id.banner_main)
    Banner banner_home;//轮播图
    @BindView(R.id.tv_home_recuit)
    TextView tv_home_recuit;//招聘
    @BindView(R.id.tv_home_rental)
    TextView tv_home_rental;//租房
    @BindView(R.id.tv_home_read)
    TextView tv_home_read;//阅读
    @BindView(R.id.tv_home_select)
    TextView tv_home_select;//微信精选

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    public void initLinstener() {
        //主页 菜单 点击事件
        tv_home_recuit.setOnClickListener(this);
        tv_home_rental.setOnClickListener(this);
        tv_home_read.setOnClickListener(this);
        tv_home_select.setOnClickListener(this);
    }

    @Override
    public void initData() {

        banner_home.setBannerAnimation(Transformer.DepthPage);
        //事实上  这个数据 应该在presenter 里面获取 临时征用了
       List<String> images=new ArrayList<>();
        List<String>titles=new ArrayList<>();
        titles.add("风景1");
        titles.add("风景2");
        titles.add("风景3");
        titles.add("风景4");
        titles.add("风景5");
        images.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1491211711&di=dc34fbb078c525a0f0b6b3e6906ec21d&imgtype=jpg&er=1&src=http%3A%2F%2Fimg.tuku.cn%2Ffile_big%2F201502%2Fd130653bfb884152b8a5ba9e846362d1.jpg");
        images.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1490616877150&di=18a87f61829c7c7787d33bc27d781dec&imgtype=0&src=http%3A%2F%2Ftupian.enterdesk.com%2F2016%2Fhxj%2F08%2F16%2F1602%2FChMkJlexsJmIIe8dAAghrgQhdMQAAUdNAJInfYACCHG699.jpg");
        images.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1490616877150&di=406214a0adc6c4f6bb2f5dc51c37dad9&imgtype=0&src=http%3A%2F%2Fimg.pconline.com.cn%2Fimages%2Fupload%2Fupc%2Ftx%2Fwallpaper%2F1608%2F31%2Fc12%2F26348420_1472658812267_800x800.jpg");
        images.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1490616877149&di=0d90b7bbc175410a715bf59cec6d153b&imgtype=0&src=http%3A%2F%2Fbbs.crsky.com%2F1236983883%2FMon_1209%2F25_187069_eaac13adbd074a5.jpg");
        images.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1490616917781&di=edd04f734bb2a25485d385525f488644&imgtype=0&src=http%3A%2F%2Fattach.bbs.miui.com%2Fforum%2F201505%2F27%2F172521n6az6c7477d66cxa.jpg");
        banner_home.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object o, ImageView imageView) {
                ImageLoadProxy.getInstance().load(new ImageLoadConfiguration.Builder(ItisiApp.getInstance()).url(o)
                               .defaultImageResId(R.mipmap.meizhi).imageView(imageView).build());

            }
        });

        banner_home.setImages(images);
        banner_home.setBannerTitles(titles);
        ////指示器 垂直显示 默认水平显示
        banner_home.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
//        banner_home.start(); // 在onstart 里面开始 播放
    }

    @Override
    public void showError(String msg) {
    }

    @Override
    public void showContent(List<GankFuLiEntity> list) {
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_home_recuit:
//                startActivity(new Intent(ItisiApp.getInstance(), RecruitActivity.class));
                startActivity(new Intent(ItisiApp.getInstance(), TabAndViewpagerActivity.class));
                SceneAnim.openActivityByScaleAlpha(getActivity());
                break;
            case R.id.tv_home_rental:
                startActivity(new Intent(ItisiApp.getInstance(), RentalActivity.class));
                SceneAnim.openActivityByScaleAlpha(getActivity());
                break;
            case R.id.tv_home_read:
                startActivity(new Intent(ItisiApp.getInstance(), ReadActivity.class));
                SceneAnim.openActivityByScaleAlpha(getActivity());
                break;
            case R.id.tv_home_select:
                startActivity(new Intent(ItisiApp.getInstance(), SelectActivity.class));
                SceneAnim.openActivityByScaleAlpha(getActivity());
                break;
        }

    }
    @Override
    public void onStart() {
        super.onStart();
        banner_home.startAutoPlay();//轮播图 开始轮播
    }

    @Override
    public void onStop() {
        super.onStop();
        banner_home.stopAutoPlay();//轮播图 结束轮播
    }
}
