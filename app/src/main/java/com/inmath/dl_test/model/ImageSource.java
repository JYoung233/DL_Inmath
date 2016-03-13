package com.inmath.dl_test.model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.inmath.dl_test.rdata.DBopenHelper;

/**
 * Created by yangyang on 2016/3/13.
 * 为CollectAdapter准备好数据源
 */
public class ImageSource {
    private Context mContext;
    private Cursor cursor;
    private DBopenHelper dBopenHelper;
    private SQLiteDatabase sqLiteDatabase;

    public ImageSource(Context mContext) {
        this.mContext = mContext;
        dBopenHelper=new DBopenHelper(mContext);
        sqLiteDatabase=dBopenHelper.getReadableDatabase();
        cursor=sqLiteDatabase.rawQuery("select * from photo",null);

    }
    public Cursor getCursor(){
        return cursor;
    }
    public int getLength(){
        return cursor.getCount();
    }
    public void closeCur(){
        cursor.close();
    }
    public void closeDB(){
        sqLiteDatabase.close();
    }
}



