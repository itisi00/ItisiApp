package com.itisi.itisiapp.ui.main;

import android.os.Bundle;

import com.itisi.itisiapp.R;
import com.itisi.itisiapp.ui.base.BaseActivity;
import com.jaeger.library.StatusBarUtil;

public class Test2Activity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test2);
       // StatusBarUtil.setColor(this,getResources().getColor(R.color.colorContent3));
        StatusBarUtil.setTransparent(this);

    }


}
