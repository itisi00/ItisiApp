package com.itisi.itisiapp.mvp.ui.main.test;


import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.itisi.itisiapp.R;
import com.itisi.itisiapp.mvp.model.entity.GankFuLiEntity;
import com.itisi.itisiapp.mvp.ui.adapter.MeiZhiAdapter;
import com.itisi.itisiapp.mvp.ui.adapter.TestAdapter;
import com.itisi.itisiapp.mvp.ui.base.BaseFragment;
import com.itisi.itisiapp.mvp.ui.main.MainContract;
import com.itisi.itisiapp.mvp.ui.main.MainPresenter;
import com.sdsmdg.tastytoast.TastyToast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Main1 extends BaseFragment<MainPresenter> implements MainContract.View {

    @BindView(R.id.rv_meizhi)
    RecyclerView rv_meizhi;

    MeiZhiAdapter mAdapter;
    List<GankFuLiEntity>mList;
    private BaseQuickAdapter mHomeAdapter;


    @Override
    public void showError(String msg) {
        TastyToast.makeText(mContext, msg, TastyToast.LENGTH_SHORT, TastyToast.ERROR);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_fragment__main1;
    }

    @Override
    public void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    public void initData() {
        mList=new ArrayList<>();
        mPresenter.getData();

//        mAdapter=new MeiZhiAdapter(mContext,mList);
//
//        rv_meizhi.setLayoutManager(new LinearLayoutManager(mContext));
//        rv_meizhi.setAdapter(mAdapter );

        mHomeAdapter = new TestAdapter(R.layout.meizhi_item, mList);
        rv_meizhi.setLayoutManager(new LinearLayoutManager(mContext));
        mHomeAdapter.openLoadAnimation();
        rv_meizhi.setAdapter(mHomeAdapter);

    }


    @Override
    public void showContent(List<GankFuLiEntity> list) {
        mList.clear();
        mList.addAll(list);
       // mAdapter.notifyDataSetChanged();
        mHomeAdapter.notifyDataSetChanged();
    }
}
