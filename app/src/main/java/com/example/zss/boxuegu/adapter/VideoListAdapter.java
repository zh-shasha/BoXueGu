package com.example.zss.boxuegu.adapter;

import android.content.Context;
import android.graphics.Color;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zss.boxuegu.R;
import com.example.zss.boxuegu.bean.VideoBean;

import java.util.List;

public class VideoListAdapter extends BaseAdapter {
    private Context mContext;
    private List<VideoBean> vb1;//视频数据列表
    private int selectedPosition=1;//点击时选中的位置
    private  OnSelectListener onSelectListener;
    public VideoListAdapter(Context context,   OnSelectListener onSelectListener){
        this.mContext=context;
        this.onSelectListener=onSelectListener;
    }
    public void setSelectedPosition(int position){
        selectedPosition=position;
    }

    /**
     * 设置数据更新
     * @param vb1
     */
    public void setData(List<VideoBean> vb1){
        this.vb1=vb1;
        notifyDataSetChanged();
    }

    /**
     * 获取item总数
     * @return
     */
    @Override
    public int getCount() {
        return vb1==null?0:vb1.size();
    }
   //获取item的对象
    @Override
    public VideoBean getItem(int position) {
        return vb1==null?null:vb1.get(position);
    }
    //获取item的对象的id
    @Override
    public long getItemId(int position) {
        return position;
    }
    //显示xml页面，设置item的点击事件
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder vh;
         if (convertView==null){
             vh=new ViewHolder();
             convertView= LayoutInflater.from(mContext).inflate(R.layout.video_list_item,null);
             vh.tv_tilte=(TextView)convertView.findViewById(R.id.tv_video_title);
             vh.iv_icon=(ImageView)convertView.findViewById(R.id.iv_left_icon);
             convertView.setTag(vh);
         }else {
             vh=(ViewHolder)convertView.getTag();
         }
         final VideoBean bean=getItem(position);
         vh.iv_icon.setImageResource(R.mipmap.play);
         vh.tv_tilte.setTextColor(Color.parseColor("#333333"));
         if (bean !=null){
             vh.tv_tilte.setText(bean.secondTitle);
             //设置选中效果
             if (selectedPosition==position){
                 vh.iv_icon.setImageResource(R.mipmap.play);
                 vh.tv_tilte.setTextColor(Color.parseColor("#009958"));
             }else {
                 vh.iv_icon.setImageResource(R.mipmap.play);
                 vh.tv_tilte.setTextColor(Color.parseColor("#333333"));
             }
         }
         convertView.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 if (bean==null){
                     return;
                 }
                 //播放视频
                 onSelectListener.onSelect(position,vh.iv_icon);
             }
         });
        return convertView;
    }
    class ViewHolder{
        public TextView tv_tilte;
        public ImageView iv_icon;
    }
    /**
     * 创建接口，传递position和ImageView
     */
    public interface OnSelectListener {
        void onSelect(int position,ImageView iv);
    }
}
