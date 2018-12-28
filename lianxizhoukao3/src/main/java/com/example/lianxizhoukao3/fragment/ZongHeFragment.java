package com.example.lianxizhoukao3.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lianxizhoukao3.Main2Activity;
import com.example.lianxizhoukao3.R;
import com.example.lianxizhoukao3.adapter.ZongHeAdapter;
import com.example.lianxizhoukao3.bean.ResponseBean;
import com.example.lianxizhoukao3.bean.ResponseBean2;
import com.example.lianxizhoukao3.bean.ZongHeBean;
import com.example.lianxizhoukao3.utils.MyTent;
import com.example.lianxizhoukao3.utils.Net;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class ZongHeFragment extends Fragment {


    Unbinder unbinder;
    public RecyclerView recy_zonghe;
    private boolean mB;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_zong_he, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView(view);



        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }

    }


    private void initView(View view) {
        recy_zonghe = (RecyclerView) view.findViewById(R.id.recy_zonghe);
    }


    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void asdd(ResponseBean responseBean){
        mB = responseBean.b;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        EventBus.getDefault().unregister(this);
    }


}
