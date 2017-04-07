package com.itisi.itisiapp.mvp.ui.main.guizhou;


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
import com.itisi.itisiapp.mvp.ui.blacklist.BlacklistActivity;
import com.itisi.itisiapp.mvp.ui.nation.NationActivity;
import com.itisi.itisiapp.mvp.ui.scenic.ScenicActivity;
import com.itisi.itisiapp.mvp.ui.speciality.SpecialityActivity;
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
 * 大贵州
 * A simple {@link Fragment} subclass.
 */
public class GuiZhouFragment extends BaseFragment<GuiZhouPresenter> implements GuiZhouContract.View,View.OnClickListener {


    @BindView(R.id.banner_guizhou)
    Banner banner_guizhou;//轮播图
    @BindView(R.id.tv_guizhou_nation)
    TextView tv_guizhou_nation;// 民族
    @BindView(R.id.tv_guizhou_scenic)
    TextView tv_guizhou_scenic; //景点
    @BindView(R.id.tv_guizhou_speciality)
    TextView tv_guizhou_speciality;//特产
    @BindView(R.id.tv_guizhou_blacklist)
    TextView tv_guizhou_blacklist;//黑名单


    @Override
    public int getLayoutId() {
        return R.layout.fragment_gui_zhou;
    }

    @Override
    public void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    public void initData() {
        banner_guizhou.setBannerAnimation(Transformer.DepthPage);
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
        banner_guizhou.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object o, ImageView imageView) {
                ImageLoadProxy.getInstance().load(new ImageLoadConfiguration.Builder(ItisiApp.getInstance()).url(o)
                        .defaultImageResId(R.mipmap.meizhi).imageView(imageView).build());

            }
        });

        banner_guizhou.setImages(images);
        banner_guizhou.setBannerTitles(titles);
        ////指示器 垂直显示 默认水平显示
        banner_guizhou.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);

    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void showContent(List<GankFuLiEntity> list) {

    }

    @Override
    public void initLinstener() {
        //页面菜单点击事件
        tv_guizhou_nation.setOnClickListener(this);
        tv_guizhou_scenic.setOnClickListener(this);
        tv_guizhou_speciality.setOnClickListener(this);
        tv_guizhou_blacklist.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_guizhou_nation:
                startActivity(new Intent(ItisiApp.getInstance(), NationActivity.class));
                SceneAnim.openActivityByScaleAlpha(getActivity());
                break;
            case R.id.tv_guizhou_scenic:
                startActivity(new Intent(ItisiApp.getInstance(), ScenicActivity.class));
                SceneAnim.openActivityByScaleAlpha(getActivity());
                break;
            case R.id.tv_guizhou_speciality:
                startActivity(new Intent(ItisiApp.getInstance(), SpecialityActivity.class));
                SceneAnim.openActivityByScaleAlpha(getActivity());
                break;
            case R.id.tv_guizhou_blacklist:
                startActivity(new Intent(ItisiApp.getInstance(), BlacklistActivity.class));
                SceneAnim.openActivityByScaleAlpha(getActivity());
                break;
        }
    }


    @Override
    public void onStart() {
        super.onStart();
        banner_guizhou.start();
    }

    @Override
    public void onStop() {
        super.onStop();
        banner_guizhou.stopAutoPlay();
    }
}
