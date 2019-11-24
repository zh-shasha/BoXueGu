package com.example.zss.boxuegu.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.zss.boxuegu.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AdBannerFragment extends Fragment {
    private String ab;     //广告
    private ImageView iv;  //图片
    public  static AdBannerFragment newInstance(Bundle args) {
        AdBannerFragment af=new AdBannerFragment();
        af.setArguments(args);
        return af;
    }
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Bundle arg=getArguments();
        //获取广告图片名称
        ab=arg.getString("ad");
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
    }
    @Override
    public void onResume(){
        super.onResume();
        if (ab!=null){
            if ("banner_1".equals(ab)){
                iv.setImageResource(R.mipmap.ic_launcher);
            }else if ("banner_2".equals(ab)){
                iv.setImageResource(R.mipmap.ic_launcher);
            }else if ("banner_3".equals(ab)){
                iv.setImageResource(R.mipmap.ic_launcher);
            }
        }
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        if (iv!=null){
            iv.setImageDrawable(null);
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //创建广告控件
        iv=new ImageView(getActivity());
        ViewGroup.LayoutParams lp=new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
        iv.setLayoutParams(lp);
        iv.setScaleType(ImageView.ScaleType.FIT_XY);
        return iv;
//        View view=inflater.inflate(R.layout.fragment_ad_banner, container, false);
//        return view;
    }

}
