package com.inmath.dl_test.model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.inmath.dl_test.rdata.DBopenHelper;

/**
 * Created by yangyang on 2016/3/13.
 * 为CollectAdapter准备好数据源,下次注意尽量再把方法写细些，这样其他适配器才可以复用
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


    }
    public Cursor SeletPic(){
        cursor=sqLiteDatabase.rawQuery("select * from photo",null);
        return cursor;
    }
    public void DeletePic(int pos){
        if(cursor.isClosed()){
            cursor=sqLiteDatabase.rawQuery("select * from photo",null);
        }
        if(cursor.moveToPosition(pos)){
            String filename=cursor.getString(0);
            sqLiteDatabase.execSQL("delete from photo where photoname=?", new String[]{String.valueOf(filename)});
        }

    }
    public Cursor getCursor(){
        return cursor;
    }

    public void closeCur(){
        cursor.close();
    }
    public void closeDB(){
        sqLiteDatabase.close();
    }
}



