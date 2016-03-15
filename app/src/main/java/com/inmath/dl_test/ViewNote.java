package com.inmath.dl_test;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.inmath.dl_test.model.NoteSource;
import com.inmath.dl_test.rdata.DBopenHelper;
import com.inmath.dl_test.rdata.NoteMain;

import java.io.File;

/**
 * Created by asus on 2016/3/14.
 * 点击查看课堂笔记内容
 */
public class ViewNote extends Activity{
    private Toolbar toolbar;
    private ImageView imageView;
    private TextView tvContent;
    private FloatingActionButton fab;
    private Cursor cursor;
    private DBopenHelper dh;
    private SQLiteDatabase db;
    private int id;
    private String title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.noteview);
        toolbar= (Toolbar) findViewById(R.id.toolbar5);
        imageView= (ImageView) findViewById(R.id.showpic);
        tvContent= (TextView) findViewById(R.id.showcontent);
        fab= (FloatingActionButton) findViewById(R.id.fab2);
        Bundle bundle=getIntent().getExtras();
        id=bundle.getInt("id");
        dh=new DBopenHelper(this);
        db=dh.getReadableDatabase();
        cursor=db.rawQuery("select * from note1 where _id=?",new String[]{String.valueOf(id)});
        if(cursor.moveToFirst()){
            title=cursor.getString(1);
            toolbar.setTitle(title);
            tvContent.setText(cursor.getString(2));
        }
        cursor.close();

        String path = Environment.getExternalStorageDirectory().getPath() +"/myImage2/"+title+".jpg";
       File file = new File(path);
        if(file.exists()){
            Bitmap bm = BitmapFactory.decodeFile(path);
            imageView.setImageBitmap(bm);
         }
      fab.setOnClickListener(new View.OnClickListener() {@Override
            public void onClick(View v) {
                Intent intent=new Intent(ViewNote.this, NoteMain.class);
                startActivity(intent);
                ViewNote.this.finish();
           }
       });

    }
}
