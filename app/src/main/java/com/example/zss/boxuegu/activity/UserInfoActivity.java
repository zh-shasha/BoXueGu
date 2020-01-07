package com.example.zss.boxuegu.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zss.boxuegu.R;
import com.example.zss.boxuegu.bean.UserBean;
import com.example.zss.boxuegu.utils.AnalysisUtils;
import com.example.zss.boxuegu.utils.DBUtils;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class UserInfoActivity extends AppCompatActivity implements View.OnClickListener {
private TextView tv_back;
private TextView tv_main_title;
private TextView tv_nickName,tv_signature,tv_user_name,tv_sex;
private RelativeLayout rl_nickName,rl_sex,rl_signature,rl_tilte_bar;
private String spUserName;
private static final int CHANGE_NICKNAME=1;//修改昵称的自定义常量
private static final int CHANGE_SIGNATURE=2;//修改签名的自定义常量
private String new_info;   //最新数据
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        //设置为竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //从SharedPreferences中获取登录时的用户名
        spUserName= AnalysisUtils.readLoginUserName(this);
        init();
        initData();
        setListener();

    }

    /**
     * 回传数据
     */
    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
     super.onActivityResult(requestCode,resultCode,data);
     switch (requestCode){
         case CHANGE_NICKNAME:
             if (data!=null){
                 new_info=data.getStringExtra("nickName");
                 if (TextUtils.isEmpty(new_info)){
                     return;
                 }
                 tv_nickName.setText(new_info);
                 //更新数据库中的昵称字段
                 DBUtils.getInstance(UserInfoActivity.this).updateUserInfo(
                         "nickName",new_info,spUserName);
             }
             break;
         case CHANGE_SIGNATURE:
             if (data!=null){
                 new_info=data.getStringExtra("signature");
                 if (TextUtils.isEmpty(new_info)){
                     return;
                 }
                 tv_signature.setText(new_info);
                 //更新数据库中的签字字段
                 DBUtils.getInstance(UserInfoActivity.this).updateUserInfo(
                         "signature",new_info,spUserName);
             }
             break;
     }
    }

    /**
     * 初始化控件
     */
    private void init() {
        tv_back=(TextView)findViewById(R.id.tv_back);
        tv_main_title=(TextView)findViewById(R.id.tv_main_title);
        tv_main_title.setText("个人资料");
        rl_tilte_bar=(RelativeLayout)findViewById(R.id.title_bar);
        rl_tilte_bar.setBackgroundColor(Color.parseColor("#30B4FF"));
        rl_nickName=(RelativeLayout)findViewById(R.id.rl_nickName);
        rl_sex=(RelativeLayout)findViewById(R.id.rl_sex);
        rl_signature=(RelativeLayout)findViewById(R.id.rl_signature);
        tv_nickName=(TextView)findViewById(R.id.tv_nickName);
        tv_user_name=(TextView)findViewById(R.id.tv_user_name);
        tv_sex=(TextView)findViewById(R.id.tv_sex);
        tv_signature=(TextView)findViewById(R.id.tv_signature);
    }

    /**
     *获取数据
     */
    private void initData() {
        UserBean bean=null;
        bean= DBUtils.getInstance(this).getUserInfo(spUserName);
        //首先判断数据库是否有数据
        if (bean==null){
            bean=new UserBean();
            bean.userName=spUserName;
            bean.nickName="问答精灵";
            bean.sex="男";
            bean.signature="问答精灵";
            //保存用户信息到数据库
            DBUtils.getInstance(this).saveUserInfo(bean);
        }
        setValue(bean);
    }

    /**
     *为界面控件设置值
     */
    private void setValue(UserBean bean){
        tv_nickName.setText(bean.nickName);
        tv_user_name.setText(bean.userName);
        tv_sex.setText(bean.sex);
        tv_signature.setText(bean.signature);
    }
    //设置控件的点击监听事件
    private void setListener(){
        tv_back.setOnClickListener(this);
        rl_nickName.setOnClickListener(this);
        rl_sex.setOnClickListener(this);
        rl_signature.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_back:
                this.finish();
                break;
            case R.id.rl_nickName:
                String name=tv_nickName.getText().toString();
                Bundle bdName=new Bundle();
                bdName.putString("content",name);
                bdName.putString("title","昵称");
                bdName.putInt("flag",1);
                enterActivityForResult(ChangeUserInfoActivity.class,CHANGE_NICKNAME,bdName);
                break;
            case R.id.rl_sex:
                String sex=tv_sex.getText().toString();
                sexDialog(sex);
                break;
            case R.id.rl_signature:
                String signature=tv_signature.getText().toString();
                Bundle bdSignature=new Bundle();
                bdSignature.putString("content",signature);
                bdSignature.putString("title","昵称");
                bdSignature.putInt("flag",2);
                enterActivityForResult(ChangeUserInfoActivity.class,CHANGE_SIGNATURE,bdSignature);
                break;
                default:
                    break;
        }
    }
    //设置性别的弹出框
    private void sexDialog(String sex){
        int sexFlag=0;
        if ("男".equals(sex)){
            sexFlag=1;
        }else if ("女".equals(sex)){
            sexFlag=1;
        }
        final  String items[]={"男","女"};
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("性别");
        builder.setSingleChoiceItems(items, sexFlag,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Toast.makeText(UserInfoActivity.this,items[which],
                                Toast.LENGTH_SHORT).show();
                        setSex(items[which]);
                    }
                });
        builder.create().show();
    }
    //更新界面上的性别数据
    private void setSex(String sex){
        tv_sex.setText(sex);
        //更新数据库中的性别字段
        DBUtils.getInstance(UserInfoActivity.this).updateUserInfo("sex",sex,spUserName);
    }
    /**
     * 获取回传数据时使用的跳转方法，第一个参数to表示需要跳转的界面
     * 第二个参数requestCode表示一个请求码，第三个参数b表示跳转时传递的数据
     */
    private void enterActivityForResult(Class<?>to,int requestCode,Bundle b){
        Intent i=new Intent(this,to);
        i.putExtras(b);
        startActivityForResult(i,requestCode);
    }

}
