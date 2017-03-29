package com.itisi.itisiapp.mvp.ui.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.itisi.itisiapp.R;
import com.itisi.itisiapp.app.ItisiApp;
import com.itisi.itisiapp.mvp.model.entity.GankFuLiEntity;
import com.itisi.itisiapp.utils.imageload.ImageLoadConfiguration;
import com.itisi.itisiapp.utils.imageload.ImageLoadProxy;

import java.util.List;

/**
 * author: itisi---
 * created by Administrator on 2017/3/29.
 * desc:
 */

public class TestAdapter extends BaseQuickAdapter<GankFuLiEntity,BaseViewHolder> {

    public TestAdapter(int layoutResId, List<GankFuLiEntity> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, GankFuLiEntity item) {
                ImageLoadProxy.getInstance().load(new ImageLoadConfiguration.Builder(ItisiApp.getInstance()).url(item.getUrl())
                .defaultImageResId(R.mipmap.ic_launcher).imageView((ImageView) helper.getView(R.id.iv_meizhi)).build());
    }
}
