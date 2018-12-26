package com.example.lianxieventbus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        EventBus.getDefault().register(this);

    }
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void oasd(){
        Toast.makeText(this, "asd", Toast.LENGTH_SHORT).show();
    }
}
