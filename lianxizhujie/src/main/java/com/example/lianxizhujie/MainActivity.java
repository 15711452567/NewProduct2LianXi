package com.example.lianxizhujie;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Inter(R.id.tv)
    Button tv;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InterNice.bind(this);

    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv:
                Toast.makeText(this, "点击", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

}
