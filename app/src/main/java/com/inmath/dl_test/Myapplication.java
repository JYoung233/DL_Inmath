package com.inmath.dl_test;

import android.app.Application;

/**
 * Created by asus on 2016/3/16.
 * 创建全局变量实现打卡以及公式推送的功能
 */
public class Myapplication extends Application {
    private int day,change;
    @Override
    public void onCreate() {
        super.onCreate();
        day=0;
        change=1;

    }


    public int getChange() {
        return change;
    }

    public void setChange(int change) {
        this.change = change;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }
}
