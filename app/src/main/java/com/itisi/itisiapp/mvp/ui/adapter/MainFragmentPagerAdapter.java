package com.itisi.itisiapp.mvp.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.itisi.itisiapp.mvp.ui.main.Fragment_Main1;
import com.itisi.itisiapp.mvp.ui.main.Fragment_Main2;
import com.itisi.itisiapp.mvp.ui.main.Fragment_Main3;
import com.itisi.itisiapp.mvp.ui.main.Fragment_Main4;

/**
 * author: itisi---
 * created by Administrator on 2017/3/24.
 * desc:
 */

public class MainFragmentPagerAdapter extends FragmentPagerAdapter {
    private String[] mTitles=new String[]{"妹纸","科技","贵州","旅游"};
    private Fragment_Main1 mFragment_main1;
    private Fragment_Main2 mFragment_main2;
    private Fragment_Main3 mFragment_main3;
    private Fragment_Main4 mFragment_main4;
    private Fragment mFragment=null;

    public MainFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                if (mFragment_main1==null){
                    mFragment_main1= new Fragment_Main1();
                }
                mFragment=mFragment_main1;
                break;
            case 1:
                if (mFragment_main2==null){
                    mFragment_main2= new Fragment_Main2();
                }
                mFragment=mFragment_main2;
                break;
            case 2:
                if (mFragment_main3==null){
                    mFragment_main3= new Fragment_Main3();
                }
                mFragment=mFragment_main3;
                break;
            case 3:
                if (mFragment_main4==null){
                    mFragment_main4= new Fragment_Main4();
                }
                mFragment=mFragment_main4;
                break;
        }
        return mFragment;
    }

    @Override
    public int getCount() {
        return mTitles.length;
    }

    /**
     * viewpager 与tablayout绑定后 这里获取到的pagetitle就是tab的text
     * @param position
     * @return
     */
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
}
