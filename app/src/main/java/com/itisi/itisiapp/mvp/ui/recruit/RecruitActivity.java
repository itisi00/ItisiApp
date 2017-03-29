package com.itisi.itisiapp.mvp.ui.recruit;

import android.os.Bundle;

import com.itisi.itisiapp.R;
import com.itisi.itisiapp.mvp.model.entity.GankFuLiEntity;
import com.itisi.itisiapp.mvp.ui.base.BaseRxBusActivity;

import java.util.List;

public class RecruitActivity extends BaseRxBusActivity<RecruitPresenter> implements RecruitContract.View {

    @Override
    protected void initInject() {
getActivityComponent().inject(this);
    }

    @Override
    public int getConentlayout() {
        return R.layout.activity_recruit;
    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void showContent(List<GankFuLiEntity> list) {

    }
}
