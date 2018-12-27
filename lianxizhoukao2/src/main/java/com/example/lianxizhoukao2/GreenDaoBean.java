package com.example.lianxizhoukao2;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.lang.ref.SoftReference;
import org.greenrobot.greendao.annotation.Generated;

/**
 * date:2018.12.27
 * author:赵颖冰(lenovo)
 * function:
 */
@Entity
public class GreenDaoBean {
    @Id(autoincrement = true)
    long id;
    String name;
    float price;
    String  image;
    @Generated(hash = 645006795)
    public GreenDaoBean(long id, String name, float price, String image) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.image = image;
    }
    @Generated(hash = 826843181)
    public GreenDaoBean() {
    }
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public float getPrice() {
        return this.price;
    }
    public void setPrice(float price) {
        this.price = price;
    }
    public String getImage() {
        return this.image;
    }
    public void setImage(String image) {
        this.image = image;
    }
}
