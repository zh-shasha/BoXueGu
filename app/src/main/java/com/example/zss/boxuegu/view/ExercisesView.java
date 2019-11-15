package com.example.zss.boxuegu.view;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import com.example.zss.boxuegu.R;
import com.example.zss.boxuegu.adapter.ExercisesAdapter;
import com.example.zss.boxuegu.bean.ExercisesBean;

import java.util.ArrayList;
import java.util.List;

public class ExercisesView {
    private ListView lv_list;
    private ExercisesAdapter adapter;
    private List<ExercisesBean> eb1;
    private Activity mContext;
    private LayoutInflater mInflater;
    private View mCurrentView;
    public ExercisesView(Activity context){
        this.mContext=context;
        //为之后将layout转换为view时用
        mInflater= LayoutInflater.from(mContext);
    }
    private void createView(){
        initView();
    }
//初始化控件
    private void initView() {
    mCurrentView=mInflater.inflate(R.layout.main_view_exercises,null);
    lv_list=(ListView)mCurrentView.findViewById(R.id.lv_list);
    adapter=new ExercisesAdapter(mContext);
    initData();
    adapter.setData(eb1);
    lv_list.setAdapter(adapter);
    }
    /**
     * 设置数据
     */
    private void initData(){
        eb1=new ArrayList<ExercisesBean>();
        for (int i=0;i<10;i++){
            ExercisesBean bean=new ExercisesBean();
            bean.id=(i+1);
            switch (i){
                case 0:
                    bean.title="第一章Android 入门基础";
                    bean.content="共计5题";
                    bean.background=(R.mipmap.ic_launcher);
                    break;
                case 1:
                    bean.title="第二章Android UI开发";
                    bean.content="共计5题";
                    bean.background=(R.mipmap.ic_launcher);
                    break;
                case 2:
                    bean.title="第三章Activity";
                    bean.content="共计5题";
                    bean.background=(R.mipmap.ic_launcher);
                    break;
                case 3:
                    bean.title="第四章Android 数据存储";
                    bean.content="共计5题";
                    bean.background=(R.mipmap.ic_launcher);
                    break;
                case 4:
                    bean.title="第五章 SQLite数据库";
                    bean.content="共计5题";
                    bean.background=(R.mipmap.ic_launcher);
                    break;
                case 5:
                    bean.title="第六章 广播接收者";
                    bean.content="共计5题";
                    bean.background=(R.mipmap.ic_launcher);
                    break;
                case 6:
                    bean.title="第七章 服务";
                    bean.content="共计5题";
                    bean.background=(R.mipmap.ic_launcher);
                    break;
                case 7:
                    bean.title="第八章 内容提供者";
                    bean.content="共计5题";
                    bean.background=(R.mipmap.ic_launcher);
                    break;
                case 8:
                    bean.title="第九章 网络编程";
                    bean.content="共计5题";
                    bean.background=(R.mipmap.ic_launcher);
                    break;
                case 9:
                    bean.title="第十章 高级编程";
                    bean.content="共计5题";
                    bean.background=(R.mipmap.ic_launcher);
                    break;
                    default:
                        break;
            }
            eb1.add(bean);
        }
    }
    /**
     * 获取当前在导航栏上方显示对应的View
     */
    public View getView(){
      if (mCurrentView==null){
          createView();
      }
      return mCurrentView;
    }
    /**
     * 显示当前导航栏上方对应的view界面
     */
    public void showView(){
        if (mCurrentView==null){
            createView();
        }
        mCurrentView.setVisibility(View.VISIBLE);
    }
}
