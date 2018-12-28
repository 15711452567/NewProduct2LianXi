package com.example.lianxizhoukao3.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lianxizhoukao3.R;
import com.example.lianxizhoukao3.bean.ResponseBean;
import com.example.lianxizhoukao3.bean.ResponseBean2;
import com.example.lianxizhoukao3.bean.ResponseBean3;
import com.example.lianxizhoukao3.bean.XiangQingBean;
import com.example.lianxizhoukao3.utils.MyTent;
import com.example.lianxizhoukao3.utils.Net;
import com.facebook.drawee.view.SimpleDraweeView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShangpinFragment extends Fragment {


    @BindView(R.id.xiangqing_simple)
    Banner xiangqingSimple;
    @BindView(R.id.xiangqing_title)
    TextView xiangqingTitle;
    @BindView(R.id.xiangqing_price)
    TextView xiangqingPrice;

    Unbinder unbinder;

    private int mPid;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

            EventBus.getDefault().register(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shangpin, container, false);

        unbinder = ButterKnife.bind(this, view);
        initData();

        return view;
    }

    private void initData() {

        System.out.println("qwe : " + mPid);

        Map<String, Integer> map = new HashMap<>();
        map.put("pid", mPid);
        Retrofit build = new Retrofit.Builder()
                .baseUrl(MyTent.xiangqingURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Net net = build.create(Net.class);
        Call<XiangQingBean> call2 = net.getCall2(map);
        call2.enqueue(new Callback<XiangQingBean>() {
            @Override
            public void onResponse(Call<XiangQingBean> call, Response<XiangQingBean> response) {
                XiangQingBean.DataBean data = response.body().getData();

                String images = data.getImages();

                String title = data.getTitle();
                int price = data.getPrice();
                String[] split = images.split("\\|");

                xiangqingSimple.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
                xiangqingSimple.setImageLoader(new GlidApp());
                List<String> Imagelist = new ArrayList();
                List<String> Titlelist = new ArrayList();
                for (int i = 0; i < split.length; i++) {
                    Imagelist.add(split[i]);
                    Titlelist.add("name"+i);
                }

                xiangqingSimple.setImages(Imagelist);
                xiangqingSimple.setBannerAnimation(Transformer.DepthPage);
                xiangqingSimple.setBannerTitles(Titlelist);
                xiangqingSimple.isAutoPlay(true);
                xiangqingSimple.setDelayTime(3000);
                xiangqingSimple.setIndicatorGravity(BannerConfig.CENTER);
                xiangqingSimple.start();


                xiangqingTitle.setText(title);
                xiangqingPrice.setText(price + "");
                ResponseBean3 responseBean3=new ResponseBean3(price,title);
                EventBus.getDefault().postSticky(responseBean3);
            }

            @Override
            public void onFailure(Call<XiangQingBean> call, Throwable t) {

            }
        });
    }



    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void asijhjd(ResponseBean2 responseBean2) {

        mPid = responseBean2.pid;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
