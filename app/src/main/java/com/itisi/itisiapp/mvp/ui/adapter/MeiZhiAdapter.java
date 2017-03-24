package com.itisi.itisiapp.mvp.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.itisi.itisiapp.R;
import com.itisi.itisiapp.mvp.model.entity.GankFuLiEntity;
import com.itisi.itisiapp.utils.imageload.ImageLoadConfiguration;
import com.itisi.itisiapp.utils.imageload.ImageLoadProxy;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author: itisi---
 * created by Administrator on 2017/3/24.
 * desc:
 */

public class MeiZhiAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<GankFuLiEntity> mList;
    private LayoutInflater mInflater;

    public MeiZhiAdapter(Context context, List<GankFuLiEntity> list) {
        mContext = context;
        mList = list;
        mInflater=LayoutInflater.from(mContext);
    }

    /**
     * 多布局  暂时不管 如果是多种布局 则 onCreateViewHolder 和onBindViewHolder
     * 都要做相应变化
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new MeiZhiViewHolder(mInflater.inflate(R.layout.meizhi_item,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        ImageLoadProxy.getInstance().load(
                new ImageLoadConfiguration.Builder(mContext).url(mList.get(position).getUrl())
                .defaultImageResId(R.mipmap.meizhi)
                .imageView(((MeiZhiViewHolder) holder).iv_meizhi).build());

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class MeiZhiViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.iv_meizhi)
        ImageView iv_meizhi;
        public MeiZhiViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }


}
