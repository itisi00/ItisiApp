package com.itisi.itisiapp.mvp.ui.main.leisure;


import android.support.v4.app.Fragment;

import com.itisi.itisiapp.R;
import com.itisi.itisiapp.mvp.model.entity.GankFuLiEntity;
import com.itisi.itisiapp.mvp.ui.base.BaseFragment;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class LeisureFragment extends BaseFragment<LeisurePresenter> implements LeisureContract.View {


    @Override
    public int getLayoutId() {
        return R.layout.fragment_leisure;
    }

    @Override
    public void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    public void initEventAndData() {

    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void showContent(List<GankFuLiEntity> list) {

    }
}
