package com.itisi.itisiapp.mvp.ui.about;

import android.os.Bundle;

import com.itisi.itisiapp.R;
import com.itisi.itisiapp.mvp.model.entity.GankFuLiEntity;
import com.itisi.itisiapp.mvp.ui.base.BaseRxBusActivity;

import java.util.List;

public class AboutActivity extends BaseRxBusActivity<AboutPresenter> implements AboutContrat.View {


    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    public int getConentlayout() {
        return R.layout.activity_about;
    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void showContent(List<GankFuLiEntity> list) {

    }
}
