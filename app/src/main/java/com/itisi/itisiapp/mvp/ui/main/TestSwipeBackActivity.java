package com.itisi.itisiapp.mvp.ui.main;

import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.itisi.itisiapp.R;
import com.itisi.itisiapp.mvp.rx.annotation.UseRxBus;
import com.itisi.itisiapp.mvp.ui.base.BaseRxBusActivity;
import com.jaeger.library.StatusBarUtil;

import butterknife.BindView;
import butterknife.OnClick;

@UseRxBus
public class TestSwipeBackActivity extends BaseRxBusActivity {


    @BindView(R.id.tv_test)
    protected TextView tv_test;

    @Override
    protected void initInject() {

    }

    @Override
    public int getConentlayout() {
      return   R.layout.activity_test_swipe_back;

    }

    /**
     * 重新设置statusBar的颜色
     */
    @Override
    public void setStatusBarColor() {
        super.setStatusBarColor();
        StatusBarUtil.setColor(this, Color.parseColor("#ff4081"));
    }

    @OnClick(R.id.btn_toast2)
    public void test(View view) {
        startActivity(new Intent(TestSwipeBackActivity.this,Test2Activity.class));
    }

    @Override
    public void showError(String msg) {

    }
}
