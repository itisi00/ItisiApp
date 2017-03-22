package com.itisi.itisiapp.mvp.ui.main;

import android.view.View;

import com.itisi.itisiapp.R;
import com.itisi.itisiapp.mvp.rx.RxBus;
import com.itisi.itisiapp.mvp.rx.annotation.UseRxBus;
import com.itisi.itisiapp.mvp.ui.base.BaseRxBusActivity;

import java.util.Date;

@UseRxBus
public class Test2Activity extends BaseRxBusActivity {


    @Override
    public int getlayoutId() {
        return R.layout.activity_test2;
    }

    /**
     * 发送事件
     * @param view
     */
    public void btnPost(View view){
        RxBus.getInstance().post(RxBus.getInstance().getTag(MainActivity.class,RxBus.TAG_UPDATE),new Date().toString());
    }

}
