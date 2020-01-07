package com.example.zss.boxuegu.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.zss.boxuegu.R;
import com.example.zss.boxuegu.adapter.ExercisesDetailAdapter;
import com.example.zss.boxuegu.bean.ExercisesBean;
import com.example.zss.boxuegu.utils.AnalysisUtils;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class ExercisesDetailActivity extends AppCompatActivity {
    private TextView tv_main_title;
    private TextView tv_back;
    private RelativeLayout rl_title_bar;
    private ListView lv_list;
    private String title;
    private int id;
    private List<ExercisesBean> eb1;
    private ExercisesDetailAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises_detail);
        //获取从习题界面传递过来的章节id
        id=getIntent().getIntExtra("id",0);
        //获取从习题页面传过来的章节标题
        title=getIntent().getStringExtra("title");
        eb1=new ArrayList<ExercisesBean>();
        initData();
        init();
    }
    private void initData() {
        try{
            //从xml文件中获取习题数据
            InputStream is=getResources().getAssets().open(
                    "chapter"+id+".xml");
            eb1= AnalysisUtils.getExercisesInfos(is);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    //初始化控件
    private void init(){
       tv_main_title=(TextView)findViewById(R.id.tv_main_title);
       tv_back=(TextView)findViewById(R.id.tv_back);
       rl_title_bar=(RelativeLayout)findViewById(R.id.title_bar);
       rl_title_bar.setBackgroundColor(Color.parseColor("#30B4FF"));
       lv_list=(ListView)findViewById(R.id.lv_list);
       TextView tv=new TextView(this);
       tv.setTextColor(Color.parseColor("#000000"));
       tv.setTextSize(16.0f);
       tv.setText("一.选择题");
       tv.setPadding(10,15,0,0);
       lv_list.addHeaderView(tv);
       tv_main_title.setText(title);
       tv_back.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               ExercisesDetailActivity.this.finish();
           }
       });
       adapter=new ExercisesDetailAdapter(ExercisesDetailActivity.this, new ExercisesDetailAdapter.OnSelectListener() {
           @Override
           public void onSelectA(int position, ImageView iv_a, ImageView iv_b, ImageView iv_c, ImageView iv_d) {
               //判断如果答案不是4即选项
               if (eb1.get(position).answer!=4){
                   eb1.get(position).select=4;
               }else {
                   eb1.get(position).select=0;
               }
               switch (eb1.get(position).answer){
                   case 1:
                       iv_a.setImageResource(R.mipmap.ic_launcher);
                       iv_d.setImageResource(R.mipmap.ic_launcher);
                       break;
                   case 2:
                       iv_b.setImageResource(R.mipmap.ic_launcher);
                       iv_d.setImageResource(R.mipmap.ic_launcher);
                       break;
                   case 3:
                       iv_c.setImageResource(R.mipmap.ic_launcher);
                       break;
                   case 4:
                       iv_c.setImageResource(R.mipmap.ic_launcher);
                       iv_d.setImageResource(R.mipmap.ic_launcher);
                       break;
               }
               AnalysisUtils.setABCDEnable(false,iv_a,iv_b,iv_c,iv_d);
           }

           @Override
           public void onSelectB(int position, ImageView iv_a, ImageView iv_b, ImageView iv_c, ImageView iv_d) {
               //判断如果答案不是2即B选项
               if (eb1.get(position).answer!=2){
                   eb1.get(position).select=2;
               }else {
                   eb1.get(position).select=0;
               }
               switch (eb1.get(position).answer){
                   case 1:
                       iv_a.setImageResource(R.mipmap.ic_launcher);
                       iv_b.setImageResource(R.mipmap.ic_launcher);
                       break;
                   case 2:
                       iv_b.setImageResource(R.mipmap.ic_launcher);
                       break;
                   case 3:
                       iv_b.setImageResource(R.mipmap.ic_launcher);
                       break;
                   case 4:
                       iv_b.setImageResource(R.mipmap.ic_launcher);
                       iv_d.setImageResource(R.mipmap.ic_launcher);
                       break;
               }
               AnalysisUtils.setABCDEnable(false,iv_a,iv_b,iv_c,iv_d);
           }

           @Override
           public void onSelectC(int position, ImageView iv_a, ImageView iv_b, ImageView iv_c, ImageView iv_d) {
               //判断如果答案不是3即C选项
               if (eb1.get(position).answer!=3){
                   eb1.get(position).select=3;
               }else {
                   eb1.get(position).select=0;
               }
               switch (eb1.get(position).answer){
                   case 1:
                       iv_a.setImageResource(R.mipmap.ic_launcher);
                       iv_c.setImageResource(R.mipmap.ic_launcher);
                       break;
                   case 2:
                       iv_b.setImageResource(R.mipmap.ic_launcher);
                       iv_c.setImageResource(R.mipmap.ic_launcher);
                       break;
                   case 3:
                       iv_c.setImageResource(R.mipmap.ic_launcher);
                       break;
                   case 4:
                       iv_c.setImageResource(R.mipmap.ic_launcher);
                       iv_d.setImageResource(R.mipmap.ic_launcher);
                       break;
               }
               AnalysisUtils.setABCDEnable(false,iv_a,iv_b,iv_c,iv_d);
           }

           @Override
           public void onSelectD(int position, ImageView iv_a, ImageView iv_b, ImageView iv_c, ImageView iv_d) {
            //判断如果答案不是4即选项
               if (eb1.get(position).answer!=4){
                   eb1.get(position).select=4;
               }else {
                   eb1.get(position).select=0;
               }
               switch (eb1.get(position).answer){
                   case 1:
                       iv_a.setImageResource(R.mipmap.ic_launcher);
                       iv_d.setImageResource(R.mipmap.ic_launcher);
                       break;
                   case 2:
                       iv_b.setImageResource(R.mipmap.ic_launcher);
                       iv_d.setImageResource(R.mipmap.ic_launcher);
                       break;
                   case 3:
                       iv_c.setImageResource(R.mipmap.ic_launcher);
                       break;
                   case 4:
                       iv_c.setImageResource(R.mipmap.ic_launcher);
                       iv_d.setImageResource(R.mipmap.ic_launcher);
                       break;
               }
               AnalysisUtils.setABCDEnable(false,iv_a,iv_b,iv_c,iv_d);
           }
       });
       adapter.setData(eb1);
       lv_list.setAdapter(adapter);
    }
}
