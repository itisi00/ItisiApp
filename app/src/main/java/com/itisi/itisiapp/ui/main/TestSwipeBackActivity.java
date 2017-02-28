package com.itisi.itisiapp.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.itisi.itisiapp.R;
import com.itisi.itisiapp.ui.base.BaseActivity;
import com.jaeger.library.StatusBarUtil;
import com.orhanobut.logger.Logger;

import butterknife.OnClick;

public class TestSwipeBackActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_swipe_back);
//        StatusBarUtil.setColor(this,getResources().getColor(R.color.colorContent2));
        StatusBarUtil.setTransparent(this);
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


}
