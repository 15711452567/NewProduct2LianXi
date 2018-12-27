package com.example.lianxizhoukao2;


import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyFragment extends Fragment {

    private GreenDaoBeanDao mGreenDaoBeanDao;
    private MyAdapter mMyAdapter;
    private RecyclerView recy;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my, container, false);


        initView(view);
        initRecy();
        initGreen();
        return view;
    }
    private void initGreen() {
        DaoMaster.DevOpenHelper asd = new DaoMaster.DevOpenHelper(getContext(), "asd");
        SQLiteDatabase writableDatabase = asd.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(writableDatabase);
        DaoSession daoSession = daoMaster.newSession();
        mGreenDaoBeanDao = daoSession.getGreenDaoBeanDao();
    }

    private void initRecy() {
        OkHttpUtils okHttpUtils = new OkHttpUtils();
        okHttpUtils.doGet(MyTent.BANNERURL, new OkHttpUtils.GetData() {
            @Override
            public void fail(Exception e) {

            }

            @Override
            public void response(String s) {
                ResponseBean responseBean = new Gson().fromJson(s, ResponseBean.class);
                List<ResponseBean.DataBean> tuijian = responseBean.getData();


                if (tuijian != null) {
                    // mMyAdapter.setData(tuijian);

                    mMyAdapter = new MyAdapter(getContext(), tuijian);
                    recy.setAdapter(mMyAdapter);
                    recy.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
                    mMyAdapter.setOnClick(new MyAdapter.OnClick() {
                        @Override
                        public void setOnClick(int pid) {
                            Intent intent=new Intent(getContext(),Main2Activity.class);
                            startActivity(intent);
                        }

                        @Override
                        public void setOnLongClick(int sellerid) {

                            mMyAdapter.notifyItemRemoved(sellerid);
                        }
                    });
                }
            }
        });

    }

    private void initView(View view) {
        recy = (RecyclerView) view.findViewById(R.id.recy);
    }
}
