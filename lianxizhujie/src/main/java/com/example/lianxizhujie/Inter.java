package com.example.lianxizhujie;

import android.support.annotation.IdRes;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * date:2018.12.26
 * author:赵颖冰(lenovo)
 * function:
 */
@Retention(RUNTIME)
@Target(ElementType.FIELD)
public @interface Inter {
    @IdRes int value();
}
