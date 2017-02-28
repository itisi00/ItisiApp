package com.itisi.itisiapp.ui.main;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.itisi.itisiapp.R;
import com.itisi.itisiapp.global.Constant;
import com.itisi.itisiapp.ui.base.BaseActivity;
import com.jaeger.library.StatusBarUtil;
import com.mxn.soul.flowingdrawer_core.ElasticDrawer;
import com.mxn.soul.flowingdrawer_core.FlowingDrawer;
import com.orhanobut.logger.Logger;
import com.yancy.gallerypick.config.GalleryConfig;
import com.yancy.gallerypick.config.GalleryPick;
import com.yancy.gallerypick.inter.IHandlerCallBack;
import com.yancy.gallerypick.inter.ImageLoader;
import com.yancy.gallerypick.widget.GalleryImageView;

import java.util.ArrayList;
import java.util.List;

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
    protected ImageView iv_header;
    private List<String> path = new ArrayList<>();//保存路径的集合
    private GalleryConfig galleryConfig;//相册配置文件
    private IHandlerCallBack iHandlerCallBack; //相册选择回调

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Logger.i("子类的oncreate");
        initPermissions();
        initGallery();

    }

    private void initPermissions() {
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            Logger.i("需要授权 ");
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                Logger.i("拒绝过了");
                // 提示用户如果想要正常使用，要手动去设置中授权。
                Toast.makeText(MainActivity.this, "请在 设置-应用管理 中开启此应用的储存授权。", Toast.LENGTH_SHORT).show();
            } else {
                Logger.i("进行授权");
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        Constant.RESUESTCODE.PERMISSIONS_REQUEST_READ_CONTACTS);
            }
        } else {
            Logger.i("不需要授权 ");
            // 进行正常操作
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        initMain();
    }

    private void initMain() {
        //设置状态栏颜色
        StatusBarUtil.setColor(this, getResources().getColor(R.color.colorContent1));
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

        //配置相册
        galleryConfig = new GalleryConfig.Builder()
                .imageLoader(new ImageLoader() {
                    @Override
                    public void displayImage(Activity activity, Context context, String path, GalleryImageView galleryImageView, int width, int height) {
Logger.i("显示的image 路径:"+path);
                    }

                    @Override
                    public void clearMemoryCache() {

                    }
                })    // ImageLoader 加载框架（必填）
                .iHandlerCallBack(iHandlerCallBack)     // 监听接口（必填）
                .provider("com.yancy.gallerypickdemo.fileprovider")   // provider(必填)
                .pathList(path)                         // 记录已选的图片
                .multiSelect(false)                      // 是否多选   默认：false
                .multiSelect(false, 9)                   // 配置是否多选的同时 配置多选数量   默认：false ， 9
                .maxSize(9)                             // 配置多选时 的多选数量。    默认：9
                .crop(true)                             // 快捷开启裁剪功能，仅当单选 或直接开启相机时有效
                .crop(true, 1, 1, 100, 100)             // 配置裁剪功能的参数，   默认裁剪比例 1:1
                .isShowCamera(true)                     // 是否现实相机按钮  默认：false
                .filePath("/Gallery/Pictures")          // 图片存放路径
                .build();





    }


    @OnClick(R.id.btn_toast)
    public void test(View view) {
        // 如果已配置好  galleryConfig 不想修改：
        GalleryPick.getInstance().setGalleryConfig(galleryConfig).openCamera(MainActivity.this);

        //startActivity(new Intent(MainActivity.this,TestSwipeBackActivity.class));


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //
        // super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode==Constant.RESUESTCODE.PERMISSIONS_REQUEST_READ_CONTACTS){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Logger.i( "同意授权");
                // 进行正常操作。
            } else {
                Logger.i("拒绝授权");
            }
        }
    }

    private void initGallery(){
        iHandlerCallBack = new IHandlerCallBack() {
            @Override
            public void onStart() {
                Logger.i( "onStart: 开启");
            }

            @Override
            public void onSuccess(List<String> photoList) {

                path.clear();
                for (String s : photoList) {
                    Logger.i( s);
                    path.add(s);
                }
                Logger.i( "onSuccess: 返回数据"+path.size());
                //photoAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancel() {
                Logger.i( "onCancel: 取消");
            }

            @Override
            public void onFinish() {
                Logger.i( "onFinish: 结束");
            }

            @Override
            public void onError() {
                Logger.i("onError: 出错");
            }
        };

    }
}
