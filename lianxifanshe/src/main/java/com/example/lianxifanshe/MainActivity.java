package com.example.lianxifanshe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn;
    private Button btn2;
    private Button btn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }

    private void initView() {
        btn = (Button) findViewById(R.id.btn);

        btn.setOnClickListener(this);
        btn2 = (Button) findViewById(R.id.btn2);
        btn2.setOnClickListener(this);
        btn3 = (Button) findViewById(R.id.btn3);
        btn3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //遍历类，属性
            case R.id.btn:

                try {
                    Class c = Class.forName("com.example.lianxifanshe.Bean");
                    // 获取所有的属性
                    Field[] fields = c.getDeclaredFields();
                    StringBuffer sb = new StringBuffer();
                    sb.append(Modifier.toString(c.getModifiers()) + " class " + c.getSimpleName() + "{\n");
                    // 遍历每一个属性
                    for (Field field : fields) {
                        sb.append("\t");// 空格
                        sb.append(Modifier.toString(field.getModifiers()) + " ");// 获得属性的修饰符，例如public，static等等
                        sb.append(field.getType().getSimpleName() + " ");// 属性的类型的名字
                        sb.append(field.getName() + ";\n");// 属性的名字+回车
                    }
                    sb.append("}\n");
                    System.out.println(sb);


                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }


                break;


            //获取bean类中变量的值并进行修改
            case R.id.btn2:
                Bean bean = new Bean();
                try {

                    Class c = Class.forName("com.example.lianxifanshe.Bean");

                    Field fieldPassword = c.getDeclaredField("abc");
                    fieldPassword.setAccessible(true);

                    System.out.println("修改前" + bean.getAbc());
                    fieldPassword.set(bean, "159");
                    System.out.println("修改后" + bean.getAbc());

                } catch (Exception e) {
                    e.printStackTrace();
                }

                break;



                //获取所有的方法名
            case R.id.btn3:
                try {
                    Class c = Class.forName("com.example.lianxifanshe.Bean");

                    Method[] declaredMethods = c.getDeclaredMethods();

                    for (Method method: declaredMethods) {
                        System.out.println(""+method.getName());
                    }

                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }


                break;
        }
    }
}
