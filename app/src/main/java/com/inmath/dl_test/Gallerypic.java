package com.inmath.dl_test;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;

import com.inmath.dl_test.adapter.MyViewAdapter;
import com.inmath.dl_test.model.ImageSource;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by asus on 2016/3/13.
 * 滑动相册功能
 */
public class Gallerypic extends Activity {
    private ViewPager viewPager;
    private MyViewAdapter adapter;
    private ImageSource mSource;
    private AssetManager am;
    private Cursor cur;
    private String filename="";
    private List<ImageView> imageviewList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewcollect);
        imageviewList=new ArrayList<>();
        viewPager= (ViewPager) findViewById(R.id.viewcollect1);
        mSource=new ImageSource(this);
        cur=mSource.getCursor();//这样就相当于把索引放到了本地变量
        am=this.getResources().getAssets();
        cur.moveToFirst();
        while(!cur.isAfterLast()){
            filename=cur.getString(0);
            try {
                Bitmap bm= BitmapFactory.decodeStream(am.open(filename));
                ImageView im=new ImageView(this);
                im.setImageBitmap(bm);
                imageviewList.add(im);
                cur.moveToNext();

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        adapter=new MyViewAdapter(imageviewList);
        viewPager.setAdapter(adapter);
        Intent intent = getIntent();
        int pos= intent.getIntExtra("position", 0);
        viewPager.setCurrentItem(pos);
        mSource.closeCur();
        mSource.closeDB();


    }
}
