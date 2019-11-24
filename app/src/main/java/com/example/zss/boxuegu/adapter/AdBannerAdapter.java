package com.example.zss.boxuegu.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.MotionEvent;
import android.view.View;

import com.example.zss.boxuegu.bean.CourseBean;
import com.example.zss.boxuegu.fragment.AdBannerFragment;
import com.example.zss.boxuegu.view.CourseView;

import java.util.ArrayList;
import java.util.List;

public class AdBannerAdapter extends FragmentStatePagerAdapter implements View.OnTouchListener {
    private Handler mHandler;
    private List<CourseBean> cad1;
    public AdBannerAdapter(FragmentManager fm){
        super(fm);
        cad1=new ArrayList<CourseBean>();
    }
    public AdBannerAdapter(FragmentManager fm,Handler handler){
        super(fm);
        mHandler=handler;
        cad1=new ArrayList<CourseBean>();
    }

    /**
     * 设置数据更新界面
     * @return
     */
    public void setDatas(List<CourseBean> cad1){
        this.cad1=cad1;
        notifyDataSetChanged();
    }
    @Override
    public Fragment getItem(int index) {
        Bundle args=new Bundle();
        if (cad1.size()>0){
            args.putString("ad",cad1.get(index%cad1.size()).icon);
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
        return cad1==null ? 0:cad1.size();
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