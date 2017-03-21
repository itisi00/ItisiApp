package com.itisi.itisiapp.mvp.ui.main;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.itisi.itisiapp.R;
import com.itisi.itisiapp.mvp.rx.RxBus;
import com.itisi.itisiapp.mvp.ui.base.BaseActivity;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.OnClick;

public class TestSwipeBackActivity extends BaseActivity {


    @BindView(R.id.tv_test)
    protected TextView tv_test;
    @Override
    public int getlayoutId() {
      return   R.layout.activity_test_swipe_back;

    }
    @OnClick(R.id.btn_toast2)
    public void test(View view) {
        startActivity(new Intent(TestSwipeBackActivity.this,Test2Activity.class));
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Logger.i("destroy 执行了吗");
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        RxBus.getInstance().post(RxBus.getInstance().getTag(MainActivity.class,RxBus.TAG_UPDATE),"from rxbus post");
      Logger.i("zzzzz dfadsfads");

    }
}
