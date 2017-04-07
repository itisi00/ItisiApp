package com.itisi.itisiapp.mvp.ui.nation;

import com.itisi.itisiapp.R;
import com.itisi.itisiapp.mvp.model.entity.GankFuLiEntity;
import com.itisi.itisiapp.mvp.ui.base.BaseRxBusActivity;
import com.itisi.itisiapp.mvp.ui.birthday.BirthdayContract;

import java.util.List;

/**
 * 民族
 */
public class NationActivity extends BaseRxBusActivity<NationPresenter> implements BirthdayContract.View {


    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    public int getConentlayout() {
        return R.layout.activity_nation;
    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void showContent(List<GankFuLiEntity> list) {

    }
}
