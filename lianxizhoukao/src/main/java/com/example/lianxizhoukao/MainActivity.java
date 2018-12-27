package com.example.lianxizhoukao;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TableLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  {

    TableLayout tab;
    private ViewPager vp;
    private RadioButton btn1;
    private RadioButton btn2;
    private RadioButton btn3;
    private RadioGroup group;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }


    private void initView() {

        vp = (ViewPager) findViewById(R.id.vp);
        btn1 = (RadioButton) findViewById(R.id.btn1);

        btn2 = (RadioButton) findViewById(R.id.btn2);

        btn3 = (RadioButton) findViewById(R.id.btn3);

        group = (RadioGroup) findViewById(R.id.group);

        final List<Fragment> list=new ArrayList<>();
        list.add(new LeftFragment());
        list.add(new CenterFragment());
        list.add(new RightFragment());

        vp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return list.get(i);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        });


        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                switch (i){
                    case 0:
                        group.check(R.id.btn1);
                        break;
                    case 1:
                        group.check(R.id.btn2);
                        break;
                    case 2:
                        group.check(R.id.btn3);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.btn1:
                        vp.setCurrentItem(0);
                        break;
                    case R.id.btn2:
                        vp.setCurrentItem(1);
                        break;
                    case R.id.btn3:
                        vp.setCurrentItem(2);
                        break;
                }
            }
        });

    }


}
