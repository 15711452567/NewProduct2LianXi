package com.example.lianxizhujie;

import android.app.Activity;
import android.view.View;

import java.lang.reflect.Field;

/**
 * date:2018.12.26
 * author:赵颖冰(lenovo)
 * function:
 */
public class InterNice {
    public static void bind(Object object) {
        try {
            parse(object);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void parse(Object object) throws Exception {
        final Class<?> clazz = object.getClass();
        View view = null;
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Inter.class)) {
                Inter annotation = field.getAnnotation(Inter.class);
                int id = annotation.value();
                if (id < 0) {
                    throw new Exception("fail");
                } else {
                    field.setAccessible(true);
                    if (object instanceof View) {
                        view = ((View) object).findViewById(id);
                        view.setOnClickListener((View.OnClickListener) object);
                    } else if (object instanceof Activity) {
                        view = ((Activity) object).findViewById(id);
                        view.setOnClickListener((View.OnClickListener) object);
                    }
                    field.set(object, view);
                }
            }
        }
    }


}
