package com.example.zss.boxuegu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.zss.boxuegu.R;
import com.example.zss.boxuegu.bean.ExercisesBean;

import java.util.List;

public class ExercisesAdapter extends BaseAdapter {
    private Context mContext;
    private List<ExercisesBean> eb1;
    public ExercisesAdapter(Context context){
        this.mContext=context;
    }
    /**
     * 设置数据更新界面
     * @return
     */
    public void setData(List<ExercisesBean> eb1){
        this.eb1=eb1;
        notifyDataSetChanged();
    }
    /**
     * 获取Item的总数
     * @param
     * @return
     */
    @Override
    public int getCount() {
        return eb1==null?0:eb1.size();
    }

    /**
     * 根据position得到对应的的item对象
     */
    @Override
    public ExercisesBean getItem(int position) {
        return eb1==null?null:eb1.get(position);
    }

    /**
     * 根据position得到对应的的item的id
     * @param position
     * @return
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * 得到相应的position对应的Item视图，position是当前Item的位置
     * converView参数就是划出屏幕的Item的View
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder vh;
        //复用convertView
        if (convertView == null) {
            vh = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(
                    R.layout.exercises_list_item, null
            );
            vh.title = (TextView) convertView.findViewById(R.id.tv_title);
            vh.content = (TextView) convertView.findViewById(R.id.tv_content);
            vh.order = (TextView) convertView.findViewById(R.id.tv_order);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        //获取position对应的Item的数据对象
        final ExercisesBean bean =getItem(position);
        if (bean != null) {
            vh.order.setText(position+1+"");
            vh.title.setText(bean.title);
            vh.content.setText(bean.content);
            vh.order.setBackgroundResource(bean.background);
        }
        //每个Item的点击事件
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bean==null){
                    return;
                //跳转到习题详情页面
                }
            }
        });
        return convertView;
    }
    class ViewHolder{
        public TextView title,content;
        public TextView order;
    }
    }

