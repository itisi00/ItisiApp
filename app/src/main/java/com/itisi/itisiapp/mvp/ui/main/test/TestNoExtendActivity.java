package com.itisi.itisiapp.mvp.ui.main.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.itisi.itisiapp.R;
import com.jaeger.library.StatusBarUtil;

public class TestNoExtendActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_no_extend);

        StatusBarUtil.setColor(this, getResources().getColor(R.color.colorAccent));
    }
}
