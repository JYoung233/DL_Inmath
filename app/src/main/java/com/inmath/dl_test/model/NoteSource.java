package com.inmath.dl_test.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Environment;
import android.util.Log;

import com.inmath.dl_test.rdata.DBopenHelper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by asus on 2016/3/14.
 * 为课堂笔记功能的实现提供操作数据库的方法
 */
public class NoteSource  {
    private Context mContext;

    private DBopenHelper dBopenHelper;
    private SQLiteDatabase sqLiteDatabase;
    private Cursor cursor;
    public NoteSource(Context mContext) {
        this.mContext = mContext;
        dBopenHelper=new DBopenHelper(mContext);

    }
    //如果没有拍照的情况
    public void AddNote2(String title,String content){
        sqLiteDatabase=dBopenHelper.getWritableDatabase();
        int id=selectNote().getCount();
        ContentValues note=new ContentValues();
        note.put("_id",id);//因为从0开始
        note.put("title",title);
        note.put("content", content);
        sqLiteDatabase.insert("note1", null, note);
        sqLiteDatabase.close();
    }

    public void AddNote(String title,String content,Bitmap bitmap){
        sqLiteDatabase=dBopenHelper.getWritableDatabase();
        int id=selectNote().getCount();
        ContentValues note=new ContentValues();
        note.put("_id",id);//因为从0开始
        note.put("title",title);
        note.put("content", content);
        sqLiteDatabase.insert("note1", null, note);
        sqLiteDatabase.close();
        String sdStatus = Environment.getExternalStorageState();
        if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) { // 检测sd是否可用
            Log.v("TestFile",
                    "SD card is not avaiable/writeable right now.");
            return;
        }else {
            FileOutputStream b = null;
            File file = new File("/sdcard/myImage2/");
            file.mkdirs();// 创建文件夹
            String fileName = "/sdcard/myImage2/" + title.trim() + ".jpg";
            try {
                b = new FileOutputStream(fileName);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, b);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }finally {
                try {
                    b.flush();
                    b.close();
                    sqLiteDatabase.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
        Log.d("data", "插入成功");
    }
    public void updateNote(int id,String title,String content){
        sqLiteDatabase=dBopenHelper.getWritableDatabase();
        ContentValues note=new ContentValues();
        note.put("_id",id);
        note.put("title",title);
        note.put("content", content);
        sqLiteDatabase.update("note1", note, "_id=?", new String[]{String.valueOf(id)});
        //照片如果没有更好的方法就重新存储

//        sqLiteDatabase.close();
    }
    //批量更新数据库
    public void MoreUpgrade(int pos,int length){//根据位置进行更新
        sqLiteDatabase=dBopenHelper.getWritableDatabase();
        for(int i=pos+1;i<length;i++){
            ContentValues note=new ContentValues();
            note.put("_id",i-1);
            sqLiteDatabase.update("note1",note,"_id=?",new String[]{String.valueOf(i)});
        }

       Log.d("update","更新成功！");
    }
    public void DeleteNote(int id){
        sqLiteDatabase=dBopenHelper.getWritableDatabase();
        sqLiteDatabase.execSQL("delete from note1 where _id=?", new String[]{String.valueOf(id)});//这里再检查一下

        Log.d("delete", "删除成功！");

    }
    public Cursor selectNote(){
        sqLiteDatabase=dBopenHelper.getReadableDatabase();
        cursor=sqLiteDatabase.rawQuery("select * from note1", null);

        return cursor;

        //为了不引起冲突，这里只返回全部的查找数据
    }
    public void closeCur(){
        cursor.close();
    }
    public void closeDB(){
        sqLiteDatabase.close();
    }

}
