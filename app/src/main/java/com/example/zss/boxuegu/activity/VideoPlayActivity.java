package com.example.zss.boxuegu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.zss.boxuegu.R;

import androidx.appcompat.app.AppCompatActivity;

public class VideoPlayActivity extends AppCompatActivity {
    private VideoView videoView;
    private MediaController controller;
    private String videoPath;//本地视频地址
    private int position; //传递视频详情界面点击的视频位置
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置界面全屏显示
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_video_play);
        //获取从播放记录界面传递过来的视频地址
        videoPath=getIntent().getStringExtra("videoPath");
        position=getIntent().getIntExtra("position",0);
        init();
    }

    /**
     *初始化控件
     */
    private void init() {
        videoView=(VideoView)findViewById(R.id.videoView);
        controller=new MediaController(this);
        videoView.setMediaController(controller);
        play();
    }

    /**
     * 播放视频
     */
    private void play() {
        if (TextUtils.isEmpty(videoPath)){
            Toast.makeText(VideoPlayActivity.this,"本地没有此视频,暂时无法播放",Toast.LENGTH_SHORT).show();
            return;
        }
        String url="android.resource://"+getPackageName()+"/"+R.raw.video11;
        videoView.setVideoPath(url);
        videoView.start();
    }
    /**
     * 点击后退按钮
     */
    public boolean onKeyDown(int keyCode, KeyEvent event){
        //把视频详情页面传递过来的被点击视频的位置传递回去
        Intent data=new Intent();
        data.putExtra("position",position);
        setResult(RESULT_OK,data);
        return super.onKeyDown(keyCode,event);
    }
}
