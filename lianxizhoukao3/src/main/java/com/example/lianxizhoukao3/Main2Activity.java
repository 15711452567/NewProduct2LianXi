package com.example.lianxizhoukao3;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.lianxizhoukao3.adapter.MyFragmentAdapter;

import com.example.lianxizhoukao3.fragment.PingLunFragment;
import com.example.lianxizhoukao3.fragment.ShangpinFragment;
import com.example.lianxizhoukao3.fragment.XiangQingFragment;
import com.example.lianxizhoukao3.fragment.XiaoLiangFragment;
import com.example.lianxizhoukao3.fragment.ZongHeFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Main2Activity extends AppCompatActivity {

    @BindView(R.id.tablayout_xiangqing)
    TabLayout tablayoutXiangqing;
    @BindView(R.id.vp_xiangqing)
    ViewPager vpXiangqing;

    @BindView(R.id.tiaozhuan_xiangqing)
    Button tiaozhuanXiangqing;
    @BindView(R.id.tiaozhuan_pinglun)
    Button tiaozhuanPinglun;
    List<String> titlelist = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);

        initData();
        initTab();
    }
    private void initTab() {
        for (int i = 0; i < titlelist.size(); i++) {
            tablayoutXiangqing.addTab(tablayoutXiangqing.newTab().setText(titlelist.get(i)));
        }
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new ShangpinFragment());
        fragmentList.add(new XiangQingFragment());
        fragmentList.add(new PingLunFragment());


        MyFragmentAdapter myFragmentAdapter = new MyFragmentAdapter(getSupportFragmentManager(), fragmentList, titlelist);
        vpXiangqing.setAdapter(myFragmentAdapter);
        tablayoutXiangqing.setupWithViewPager(vpXiangqing);

    }

    private void initData() {
        titlelist.add("商品");
        titlelist.add("详情");
        titlelist.add("评论");

    }

    @OnClick({R.id.tiaozhuan_pinglun,R.id.tiaozhuan_xiangqing})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.tiaozhuan_pinglun:
                vpXiangqing.setCurrentItem(1);
                break;
            case R.id.tiaozhuan_xiangqing:
                vpXiangqing.setCurrentItem(2);
                break;
        }
    }

}
