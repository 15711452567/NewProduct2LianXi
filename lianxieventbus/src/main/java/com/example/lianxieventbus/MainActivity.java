package com.example.lianxieventbus;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.button)
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        Bean bean=new Bean(4);
        EventBus.getDefault().postSticky(bean);
    }



    @OnClick(R.id.button)
    public void asd(){
        Toast.makeText(this, "点击", Toast.LENGTH_SHORT).show();
    }
}
