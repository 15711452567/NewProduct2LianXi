package com.example.lianxizhoukao2;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TabLayout tab;
    private ViewPager vp;
    private List<String> titles = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        initEvent();

    }


    private void initData() {
        titles.add("综合");
        titles.add("销量");
        titles.add("价格");
        titles.add("筛选");
    }

    private void initView() {
        tab = (TabLayout) findViewById(R.id.tab);
        vp = (ViewPager) findViewById(R.id.vp);
    }

    private void initEvent() {
        for (int i = 0; i < titles.size(); i++) {
            tab.addTab(tab.newTab().setText(titles.get(i)));
        }
        List<Fragment> fragments = new ArrayList<Fragment>();
        for (int i = 0; i < titles.size(); i++) {
            fragments.add(new MyFragment());
        }
        MyFragmentPager fragmentPager = new MyFragmentPager(getSupportFragmentManager(), fragments, titles);
        // 给ViewPager 设置适配器
        vp.setAdapter(fragmentPager);
        //  将TabLayout 和 ViewPager 关联起来
        tab.setupWithViewPager(vp);
    }

}
