package com.example.zss.boxuegu.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;

import com.example.zss.boxuegu.bean.CourseBean;
import com.example.zss.boxuegu.fragment.AdBannerFragment;
import com.example.zss.boxuegu.view.CourseView;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class AdBannerAdapter extends FragmentStatePagerAdapter implements View.OnTouchListener {
    private Handler mHandler;
    private List<CourseBean> cadl;
    public AdBannerAdapter(FragmentManager fm){
        super(fm);
        cadl=new ArrayList<CourseBean>();
    }
    public AdBannerAdapter(FragmentManager fm,Handler handler){
        super(fm);
        mHandler=handler;
        cadl=new ArrayList<CourseBean>();
    }

    /**
     * 设置数据更新界面
     * @return
     */
    public void setDatas(List<CourseBean> cadl){
        this.cadl=cadl;
        notifyDataSetChanged();
    }
    @Override
    public Fragment getItem(int index) {
        Bundle args=new Bundle();
        if (cadl.size()>0){
            args.putString("ad",cadl.get(index % cadl.size()).icon);
        }
        return AdBannerFragment.newInstance(args);
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    /**
     * 返回数据集的额真实容量大小
     * @return
     */
    public int getSize(){
        return cadl==null ? 0:cadl.size();
    }
    @Override
    public int getItemPosition(Object object){
        //防止刷新结果显示列表时出现缓存数据，重载这个函数，使之默认返回POSITION_NONE
        return POSITION_NONE;
    }
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        mHandler.removeMessages(CourseView.MSG_AD_SLID);
        return false;
    }
}
