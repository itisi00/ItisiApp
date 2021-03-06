package com.itisi.itisiapp.mvp.ui.scenic;

import com.itisi.itisiapp.R;
import com.itisi.itisiapp.mvp.model.entity.GankFuLiEntity;
import com.itisi.itisiapp.mvp.ui.base.BaseRxBusActivity;
import com.itisi.itisiapp.mvp.ui.birthday.BirthdayContract;

import java.util.List;

/**
 * 景点
 */
public class ScenicActivity extends BaseRxBusActivity<ScenicPresenter> implements BirthdayContract.View {



    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    public int getConentlayout() {
        return R.layout.activity_scenic;
    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void showContent(List<GankFuLiEntity> list) {

    }
}
