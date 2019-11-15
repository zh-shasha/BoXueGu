package com.example.zss.boxuegu.adapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zss.boxuegu.R;
import com.example.zss.boxuegu.bean.ExercisesBean;
import com.example.zss.boxuegu.utils.AnalysisUtils;

import java.util.ArrayList;
import java.util.List;

public class ExercisesDetailAdapter extends BaseAdapter {
    private Context mContext;
    private List<ExercisesBean> eb1;
    private OnSelectListener onSelectListener;
    public ExercisesDetailAdapter(Context context,OnSelectListener onSelectListener){
        this.mContext=context;
        this.onSelectListener=onSelectListener;
    }
    public void setData(List<ExercisesBean>eb1){
        this.eb1=eb1;
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return eb1==null?0:eb1.size();
    }

    @Override
    public ExercisesBean getItem(int position) {
        return eb1==null?null:eb1.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    //记录点击的位置
    private ArrayList<String> selectedPosition=new ArrayList<String>();

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder vh;
        if (convertView==null) {
        vh=new ViewHolder();
        convertView= LayoutInflater.from(mContext).inflate(
                R.layout.exercises_detail_list_item,null
        );
        vh.subject=(TextView)convertView.findViewById(R.id.tv_subject);
        vh.tv_a=(TextView)convertView.findViewById(R.id.tv_a);
        vh.tv_b=(TextView)convertView.findViewById(R.id.tv_b);
        vh.tv_c=(TextView)convertView.findViewById(R.id.tv_c);
        vh.tv_d=(TextView)convertView.findViewById(R.id.tv_d);
        vh.iv_a=(ImageView)convertView.findViewById(R.id.iv_a);
        vh.iv_b=(ImageView)convertView.findViewById(R.id.iv_b);
        vh.iv_c=(ImageView)convertView.findViewById(R.id.iv_c);
        vh.iv_d=(ImageView)convertView.findViewById(R.id.iv_d);
        convertView.setTag(vh);
        }else {
            vh=(ViewHolder)convertView.getTag();
        }
        final ExercisesBean bean=getItem(position);
        if (bean!=null){
            vh.subject.setText(bean.subject);
            vh.tv_a.setText(bean.a);
            vh.tv_b.setText(bean.b);
            vh.tv_c.setText(bean.c);
            vh.tv_b.setText(bean.d);
        }
        if (!selectedPosition.contains(""+position)){
            vh.iv_a.setImageResource(R.mipmap.ic_launcher);
            vh.iv_b.setImageResource(R.mipmap.ic_launcher);
            vh.iv_c.setImageResource(R.mipmap.ic_launcher);
            vh.iv_b.setImageResource(R.mipmap.ic_launcher);
            AnalysisUtils.setABCDEnable(true,vh.iv_a,vh.iv_b,vh.iv_c,vh.iv_d);
        }else {
            AnalysisUtils.setABCDEnable(false,vh.iv_a,vh.iv_b,vh.iv_c,vh.iv_d);
            switch (bean.select){
                case 0:
                    //用户所选项是正确的
                    if (bean.answer==1){
                        vh.iv_a.setImageResource(R.mipmap.ic_launcher);
                        vh.iv_b.setImageResource(R.mipmap.ic_launcher);
                        vh.iv_c.setImageResource(R.mipmap.ic_launcher);
                        vh.iv_d.setImageResource(R.mipmap.ic_launcher);
                    }else if (bean.answer==2){
                        vh.iv_a.setImageResource(R.mipmap.ic_launcher);
                        vh.iv_b.setImageResource(R.mipmap.ic_launcher);
                        vh.iv_c.setImageResource(R.mipmap.ic_launcher);
                        vh.iv_d.setImageResource(R.mipmap.ic_launcher);
                    }else if (bean.answer==3){
                        vh.iv_a.setImageResource(R.mipmap.ic_launcher);
                        vh.iv_b.setImageResource(R.mipmap.ic_launcher);
                        vh.iv_c.setImageResource(R.mipmap.ic_launcher);
                        vh.iv_d.setImageResource(R.mipmap.ic_launcher);
                    }else if (bean.answer==4){
                        vh.iv_a.setImageResource(R.mipmap.ic_launcher);
                        vh.iv_b.setImageResource(R.mipmap.ic_launcher);
                        vh.iv_c.setImageResource(R.mipmap.ic_launcher);
                        vh.iv_d.setImageResource(R.mipmap.ic_launcher);
                    }
                    break;
                case 1:
                    //用户所选项A是错误的
                    vh.iv_a.setImageResource(R.mipmap.ic_launcher);
                    if (bean.answer==2){
                        vh.iv_a.setImageResource(R.mipmap.ic_launcher);
                        vh.iv_b.setImageResource(R.mipmap.ic_launcher);
                        vh.iv_c.setImageResource(R.mipmap.ic_launcher);
                        vh.iv_d.setImageResource(R.mipmap.ic_launcher);
                    }else if (bean.answer==3){
                        vh.iv_a.setImageResource(R.mipmap.ic_launcher);
                        vh.iv_b.setImageResource(R.mipmap.ic_launcher);
                        vh.iv_c.setImageResource(R.mipmap.ic_launcher);
                        vh.iv_d.setImageResource(R.mipmap.ic_launcher);
                    }else if (bean.answer==4){
                        vh.iv_a.setImageResource(R.mipmap.ic_launcher);
                        vh.iv_b.setImageResource(R.mipmap.ic_launcher);
                        vh.iv_c.setImageResource(R.mipmap.ic_launcher);
                        vh.iv_d.setImageResource(R.mipmap.ic_launcher);
                    }
                    break;
            }
        }

        return null;
    }
    class ViewHolder{
        public TextView subject,tv_a,tv_b,tv_c,tv_d;
        public ImageView iv_a,iv_b,iv_c,iv_d;
    }
    public interface OnSelectListener{}
}
