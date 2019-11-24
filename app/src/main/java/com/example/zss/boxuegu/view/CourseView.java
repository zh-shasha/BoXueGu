package com.example.zss.boxuegu.view;

import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import com.example.zss.boxuegu.R;
import com.example.zss.boxuegu.adapter.AdBannerAdapter;
import com.example.zss.boxuegu.adapter.CourseAdapter;
import com.example.zss.boxuegu.bean.CourseBean;

import java.util.List;

import java.util.logging.LogRecord;

public class CourseView {
    public static final int MSG_AD_SLID = 002;
    private ListView lv_list;
    private CourseAdapter adapter;
    private List<List<CourseBean>> cb1;
    private FragmentActivity mContext;
    private LayoutInflater mInflater;
    private View mCurrentView;
    private ViewPager adPager;
    private View adBannerLay;
    private AdBannerAdapter ada;

    private ViewPagerIndicator vpi;
    private MHandler mHandler;
    private List<CourseBean>cad1;
    public CourseView(FragmentActivity context){
        mContext=context;
        //为之后将Layout转化为view时用
        mInflater=LayoutInflater.from(mContext);
    }
    private void createView(){
        mHandler=new MHandler();
        initAdData();
        getCourseData();
        initView();
        new AdAutoSlidThread().start();
    }
//获取课程信息
    private void getCourseData() {
    }

    //事件捕获
    class MHandler extends Handler {
    @Override
    public void dispatchMessage(Message msg) {
        super.dispatchMessage(msg);
        switch (msg.what) {
            case MSG_AD_SLID:
                if (ada.getCount() > 0) {
                    adPager.setCurrentItem(
                            adPager.getCurrentItem() + 1
                    );
                }
                break;
        }
    }
}
    //广告自动滑动
    class AdAutoSlidThread extends Thread{
        @Override
        public void run(){
            super.run();
        while (true){
            try {
                sleep(5000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            if (mHandler!=null){
                mHandler.sendEmptyMessage(MSG_AD_SLID);
            }
        }
        }
    }
    //初始化控件
    private void initView() {
        mCurrentView=mInflater.inflate(R.layout.main_view_course,null);
        lv_list=(ListView)mCurrentView.findViewById(R.id.lv_list);
        adapter=new CourseAdapter(mContext);
        adapter.setData(cb1);
        lv_list.setAdapter(adapter);
        adPager=(ViewPager)mCurrentView.findViewById(R.id.vp_advertBanner);
        ada=new AdBannerAdapter(mContext.getSupportFragmentManager(),mHandler);
        adPager.setAdapter(ada);//给ViewPager设置适配器
        adPager.setOnTouchListener(ada);
        vpi=(ViewPagerIndicator)mCurrentView.findViewById(R.id.vpi_advert_indicator);
        vpi.setCount(ada.getSize());//设置小数点的个数
        adBannerLay=mCurrentView.findViewById(R.id.rl_adBanner);
        adPager.addOnPageChangeListener(
                new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int i, float positionOffset, int positionOffsetPixels) {

                    }

                    @Override
                    public void onPageSelected(int position) {
                    if (ada.getSize()>0){
                        //由于index数据在滑动时是累加的
                        //因此用index % ada.getSize(）来标记滑动到当前位置
                        vpi.setCurrentPosition(position % ada.getSize());
                    }
                    }

                    @Override
                    public void onPageScrollStateChanged(int i) {

                    }
                }
        );
    }

    private void initAdData() {
    }
}
