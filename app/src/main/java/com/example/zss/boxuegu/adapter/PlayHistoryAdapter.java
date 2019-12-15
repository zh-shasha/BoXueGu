package com.example.zss.boxuegu.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zss.boxuegu.R;
import com.example.zss.boxuegu.activity.VideoPlayActivity;
import com.example.zss.boxuegu.bean.VideoBean;

import java.util.List;

public class PlayHistoryAdapter extends BaseAdapter {
    private Context mContext;
    private List<VideoBean> vbl;
    public PlayHistoryAdapter(Context context){
        this.mContext=context;
    }

    /**
     * 设置数据
     * @return
     */
    public void setData(List<VideoBean> vbl){
        this.vbl=vbl;
        notifyDataSetChanged();
    }

    /**
     * 获取item的总数
     * @return
     */
    @Override
    public int getCount() {
        return vbl==null ? 0:vbl.size();
    }

    /**
     * 根据position得到对应Item的对象
     * @param position
     * @return
     */
    @Override
    public VideoBean getItem(int position) {
        return vbl==null ? null:vbl.get(position);
    }

    /**
     * 根据position得到item的id
     * @param position
     * @return
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * 得到相应的position对应的Item视图
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder vh;
        if (convertView==null){
            vh=new ViewHolder();
            convertView= LayoutInflater.from(mContext).inflate(R.layout.play_history_list_item,null);
            vh.tv_title=(TextView)convertView.findViewById(R.id.tv_adapter_title);
            vh.tv_video_title=(TextView)convertView.findViewById(R.id.tv_video_title);
            vh.iv_icon=(ImageView)convertView.findViewById(R.id.iv_video_icon);
            convertView.setTag(vh);
        }else{
            vh=(ViewHolder) convertView.getTag();
        }
        final VideoBean bean=getItem(position);
        if (bean !=null){
            vh.tv_title.setText(bean.title);
            vh.tv_video_title.setText(bean.secondTitle);
            switch (bean.chapterId){
                case 1:
                    vh.iv_icon.setImageResource(R.mipmap.ic_launcher);
                    break;
                case 2:
                    vh.iv_icon.setImageResource(R.mipmap.ic_launcher);
                    break;
                case 3:
                    vh.iv_icon.setImageResource(R.mipmap.ic_launcher);
                    break;
                case 4:
                    vh.iv_icon.setImageResource(R.mipmap.ic_launcher);
                    break;
                case 5:
                    vh.iv_icon.setImageResource(R.mipmap.ic_launcher);
                    break;
                case 6:
                    vh.iv_icon.setImageResource(R.mipmap.ic_launcher);
                    break;
                case 7:
                    vh.iv_icon.setImageResource(R.mipmap.ic_launcher);
                    break;
                case 8:
                    vh.iv_icon.setImageResource(R.mipmap.ic_launcher);
                    break;
                case 9:
                    vh.iv_icon.setImageResource(R.mipmap.ic_launcher);
                    break;
                case 10:
                    vh.iv_icon.setImageResource(R.mipmap.ic_launcher);
                    break;
                default:
                    vh.iv_icon.setImageResource(R.mipmap.ic_launcher);
                    break;
            }
        }
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bean==null)
                    return;
                //跳转到播放视频页面
                Intent intent=new Intent(mContext, VideoPlayActivity.class);
                intent.putExtra("videoPath",bean.videoPath);
                mContext.startActivity(intent);
            }
        });
        return convertView;
    }
    class ViewHolder {
        public TextView tv_title,tv_video_title;
        public ImageView iv_icon;
    }
}
