package com.itisi.itisiapp.mvp.ui.album;

import android.view.View;

import com.itisi.itisiapp.R;
import com.itisi.itisiapp.mvp.model.entity.GankFuLiEntity;
import com.itisi.itisiapp.mvp.ui.base.BaseRxBusActivity;
import com.mingle.sweetpick.SweetSheet;

import java.util.List;

public class AlbumActivity extends BaseRxBusActivity<AlbumPresenter> implements AlbumContract.View {

    private SweetSheet mSweetSheet;

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    public int getConentlayout() {
        return R.layout.activity_album;
    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void showContent(List<GankFuLiEntity> list) {

    }

    public void openActionSheet(View view){
        // TODO: 2017/3/30   测试 actionsheet

    }
}
