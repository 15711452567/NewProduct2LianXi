package com.example.lianxizhoukao2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class Main2Activity extends AppCompatActivity {


    private int mP;
    private SimpleDraweeView xiangqing_simple;
    private TextView xiangqing_title;
    private TextView xiangqing_price;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initView();


        EventBus.getDefault().register(Main2Activity.this);
        initData();

    }

    private void initData() {
        OkHttpUtils okHttpUtils = new OkHttpUtils();
        okHttpUtils.doGet(MyTent.SHOPURL + "?pid=" + mP, new OkHttpUtils.GetData() {
            @Override
            public void fail(Exception e) {

            }

            @Override
            public void response(String s) {
                XiangQingBean xiangQingBean = new Gson().fromJson(s, XiangQingBean.class);
                XiangQingBean.DataBean data = xiangQingBean.getData();
                String images = data.getImages();
                int price = data.getPrice();
                String title = data.getTitle();

                String[] split = images.split("\\|");
                xiangqing_simple.setImageURI(split[0]);
                xiangqing_title.setText(title);
                xiangqing_price.setText("￥"+price+"元");
            }
        });
}

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void asd(ResponseBean2 responseBean2) {
        mP = responseBean2.pid;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(Main2Activity.this);
    }


    private void initView() {
        xiangqing_simple = (SimpleDraweeView) findViewById(R.id.xiangqing_simple);
        xiangqing_title = (TextView) findViewById(R.id.xiangqing_title);
        xiangqing_price = (TextView) findViewById(R.id.xiangqing_price);
    }
}
