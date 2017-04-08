package com.itisi.itisiapp.mvp.ui.base;


import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.itisi.itisiapp.app.ItisiApp;
import com.itisi.itisiapp.di.component.DaggerFragmentComponent;
import com.itisi.itisiapp.di.component.FragmentComponent;
import com.itisi.itisiapp.di.modeule.FragmentModule;
import com.jaeger.library.StatusBarUtil;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public abstract class BaseFragment<P extends BasePresenter> extends Fragment implements BaseView {

    @Inject
    protected P mPresenter;
    protected Activity mActivity;
    protected Context mContext;
    protected boolean isInited=false;
    private View mView;
    private Unbinder mUnbinder;//ButterKnife init 返回值
    //这东西 别人是放在更上层的
    private boolean mIsHidden = true;   // 用于记录Fragment show/hide 状态

    @Override
    public void onAttach(Context context) {
        mActivity= (Activity) context;
        mContext=context;
        super.onAttach(context);
    }
    protected FragmentComponent getFragmentComponent() {
        return DaggerFragmentComponent.builder()
                .appComponent(ItisiApp.getAppComponent())
                .fragmentModule(getFragmentModele())
                .build();
    }
    public FragmentModule getFragmentModele() {
        return new FragmentModule(this);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView=inflater.inflate(getLayoutId(),null);
       // StatusBarUtil.setTranslucentForImageViewInFragment(getActivity(), null);
        initInject();
        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter.attachView(this);
        mUnbinder= ButterKnife.bind(this,mView);
        initLinstener();
        //其实这儿还差很多代码
        initData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter!=null){
            mPresenter.detachView();
        }
    }

    //初始化数据
    public abstract int getLayoutId();
    public abstract void initInject();
    public abstract void initData();
    //设置事件
    public void initLinstener(){}


}
