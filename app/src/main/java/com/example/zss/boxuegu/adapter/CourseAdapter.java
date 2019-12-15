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
import com.example.zss.boxuegu.activity.VideoListActivity;
import com.example.zss.boxuegu.bean.CourseBean;

import java.util.List;

public class CourseAdapter extends BaseAdapter {
    private Context mContext;
    private List<List<CourseBean>> cb1;
    public CourseAdapter(Context context){
        this.mContext=context;
    }
    //设置数据更新页面
    public void setData(List<List<CourseBean>> cb1){
        this.cb1=cb1;
        notifyDataSetChanged();
    }
    //获取Item的总数
    @Override
    public int getCount() {
        return cb1==null ? 0:cb1.size();
    }
//根据position得到对应Item的对象
    @Override
    public List<CourseBean> getItem(int position) {
        return cb1==null ? null :cb1.get(position);
    }
//根据position得到对应Item的id
    @Override
    public long getItemId(int position) {
        return position;
    }
//获取对应position的item视图
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder vh;
        if (convertView==null){
            vh=new ViewHolder();
            convertView= LayoutInflater.from(mContext).inflate(
                    R.layout.course_list_item,null);
            vh.iv_left_img=(ImageView)convertView.findViewById(R.id.iv_left_img);
            vh.iv_right_img=(ImageView)convertView.findViewById(R.id.iv_right_img);
            vh.tv_left_img_title=(TextView)convertView.findViewById(R.id.tv_left_img_title);
            vh.tv_right_img_title=(TextView) convertView.findViewById(R.id.tv_right_img_title);
            vh.tv_right_title=(TextView)convertView.findViewById(R.id.tv_right_title);
            convertView.setTag(vh);
        } else {
            //复用convertView
            vh=(ViewHolder)convertView.getTag();
        }
        final List<CourseBean> list= getItem(position);
        if (list!=null){
            for (int i=0;i<list.size();i++){
                final CourseBean bean=list.get(i);
                switch (i){
                    case 0:  //设置左边图片与标题信息
                        vh.tv_left_img_title.setText(bean.imgTitle);
                        vh.tv_left_title.setText(bean.title);
                        setLeftImg(bean.id,vh.iv_left_img);
                        vh.iv_left_img.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //跳转到课程详情页面
                                Intent intent=new Intent(mContext, VideoListActivity.class);
                                intent.putExtra("id",bean.id);
                                intent.putExtra("intro",bean.intro);
                                mContext.startActivity(intent);
                            }
                        });
                        break;
                    case 1://设置右边图片和标题信息
                        vh.tv_right_img_title.setText(bean.imgTitle);
                        vh.tv_right_title.setText(bean.title);
                        setRightImg(bean.id,vh.iv_right_img);
                        vh.iv_right_img.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //跳转到课程详情页面
                                Intent intent=new Intent(mContext, VideoListActivity.class);
                                intent.putExtra("id",bean.id);
                                intent.putExtra("intro",bean.intro);
                                mContext.startActivity(intent);
                            }
                        });
                        break;
                        default:
                        break;

                }
            }
        }
        return convertView;
    }

    /**
     * 设置左边的图片
     */
    private void setLeftImg(int id,ImageView iv_left_img){
        switch (id){
            case 1:
            case 3:
            case 5:
            case 7:
            case 9:
                iv_left_img.setImageResource(R.mipmap.ic_launcher);
                break;
        }
    }
    /**
     * 设置右边的图片
     */
    private void setRightImg(int id,ImageView iv_right_img){
        switch (id){
            case 2:
                iv_right_img.setImageResource(R.drawable.tu1);
            case 4:
                iv_right_img.setImageResource(R.drawable.tu1);
            case 6:
                iv_right_img.setImageResource(R.drawable.tu1);
            case 8:
                iv_right_img.setImageResource(R.mipmap.ic_launcher);
            case 10:
                iv_right_img.setImageResource(R.mipmap.ic_launcher);
                break;
        }
    }
    class ViewHolder{
        public TextView tv_left_img_title,tv_left_title,tv_right_img_title,tv_right_title;
        public ImageView iv_left_img,iv_right_img;
}
}
