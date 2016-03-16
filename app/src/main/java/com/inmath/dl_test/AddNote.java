package com.inmath.dl_test;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.inmath.dl_test.model.NoteSource;
import com.inmath.dl_test.rdata.NoteMain;

/**
 * Created by asus on 2016/3/14.
 * 添加课堂笔记
 */
public class AddNote extends Activity {
    private ImageButton save;
    private EditText title;
    private EditText content;
    private ImageButton camera;
    private NoteSource noteSource;
    private Toolbar mToolbar;
    Bitmap bitmap=null;
    Bundle bundle=new Bundle();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addnote);
        mToolbar= (Toolbar) findViewById(R.id.toolbar6);
        mToolbar.setTitle("课堂笔记");
        mToolbar.setNavigationIcon(R.mipmap.arrow);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddNote.this.finish();
            }
        });
        save=(ImageButton)findViewById(R.id.save);
        title=(EditText)findViewById(R.id.title);
        content=(EditText)findViewById(R.id.content);
        camera=(ImageButton)findViewById(R.id.camera);
        noteSource=new NoteSource(this);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bitmap!=null) {
                    noteSource.AddNote(title.getText().toString(), content.getText().toString(), bitmap);
                }else{
                    noteSource.AddNote2(title.getText().toString(), content.getText().toString());
                }

//                Snackbar.make(v, "添加成功了哦~~", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                Intent intent=new Intent(AddNote.this, NoteMain.class);
                startActivity(intent);
                AddNote.this.finish();
            }
        });
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 1);
            }
        });

    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            bundle = data.getExtras();
            bitmap = (Bitmap) bundle.get("data");// 获取相机返回的数据，并转换为Bitmap图片格式
            ((ImageView) findViewById(R.id.pic)).setImageBitmap(bitmap);
        }
    }

}
