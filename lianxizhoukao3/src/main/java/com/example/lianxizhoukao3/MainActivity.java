package com.example.lianxizhoukao3;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.example.lianxizhoukao3.adapter.MyFragmentAdapter;
import com.example.lianxizhoukao3.adapter.ZongHeAdapter;
import com.example.lianxizhoukao3.bean.ResponseBean;
import com.example.lianxizhoukao3.bean.ResponseBean2;
import com.example.lianxizhoukao3.bean.ZongHeBean;
import com.example.lianxizhoukao3.fragment.JiaGeFragment;
import com.example.lianxizhoukao3.fragment.ShaiXuanFragment;
import com.example.lianxizhoukao3.fragment.XiaoLiangFragment;
import com.example.lianxizhoukao3.fragment.ZongHeFragment;
import com.example.lianxizhoukao3.utils.MyTent;
import com.example.lianxizhoukao3.utils.Net;
import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.qiehuan)
    SimpleDraweeView qiehuan;
    @BindView(R.id.tablayout)
    TabLayout tablayout;


    @BindView(R.id.recy_zonghe)
    RecyclerView recyZonghe;
    List<String> titlelist = new ArrayList<>();
    private boolean b = true;
    private List<ZongHeBean.DataBean> mData;
    private ZongHeAdapter mZongHeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        initData();
        initData2();
        initTab();
    }

    private void initData2() {

        Retrofit build1 = new Retrofit.Builder()
                .baseUrl(MyTent.LIEBIAOURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Net net = build1.create(Net.class);
        Call<ZongHeBean> call = net.getCall();
        call.enqueue(new Callback<ZongHeBean>() {
            @Override
            public void onResponse(Call<ZongHeBean> call, Response<ZongHeBean> response) {
                mData = response.body().getData();

                mZongHeAdapter = new ZongHeAdapter(MainActivity.this, mData);
                recyZonghe.setAdapter(mZongHeAdapter);
                recyZonghe.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

                mZongHeAdapter.setOnClick(new ZongHeAdapter.OnClick() {
                    @Override
                    public void setOnClick(int pid) {

                        System.out.println("asd : "+pid);

                        startActivity(new Intent(MainActivity.this, Main2Activity.class));
                        ResponseBean2 responseBean2 = new ResponseBean2(pid);
                        EventBus.getDefault().postSticky(responseBean2);
                    }

                });

            }

            @Override
            public void onFailure(Call<ZongHeBean> call, Throwable t) {

            }
        });
    }

    private void initTab() {
        for (int i = 0; i < titlelist.size(); i++) {
            tablayout.addTab(tablayout.newTab().setText(titlelist.get(i)));
        }



    }

    private void initData() {
        titlelist.add("综合排序");
        titlelist.add("销量");
        titlelist.add("价格");
        titlelist.add("筛选");
    }


    @OnClick(R.id.qiehuan)
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.qiehuan:
                if (b){
                    mZongHeAdapter = new ZongHeAdapter(MainActivity.this, mData);
                    recyZonghe.setAdapter(mZongHeAdapter);
                    recyZonghe.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

                    b=false;

                }else{
                    ZongHeAdapter zongHeAdapter = new ZongHeAdapter(MainActivity.this, mData);
                    recyZonghe.setAdapter(zongHeAdapter);
                    recyZonghe.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));

                    b=true;
                }
                break;
        }
    }


}
