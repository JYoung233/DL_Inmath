package com.inmath.dl_test;

import android.app.Activity;
import android.content.ContentValues;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.inmath.dl_test.rdata.DBopenHelper;

import java.io.IOException;
import java.io.InputStream;

public class Selectpage extends Activity implements OnGestureListener {
	private ImageView iv;
	AssetManager am =null;
	Bitmap image;
	GestureDetector detector;
	private int width,height;
	private boolean iscollect=false;
	private String filename="";
	private float currentScale=1;
	private ImageButton im;

	Matrix matrix ;
    DBopenHelper dh=new DBopenHelper(this);
	SQLiteDatabase db;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.showselect);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar1);
		toolbar.setTitle("InMath");
		iv=(ImageView)findViewById(R.id.imageView6);
		im= (ImageButton) findViewById(R.id.Collect);
		am=getAssets();
		matrix=new Matrix();
		Bundle bundle = this.getIntent().getExtras();
		filename=bundle.getString("keyname").toString()+".png";
		detector=new GestureDetector(this,this);
		InputStream is=null;
		try {
		    	
			is = am.open(filename);
		} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		image=BitmapFactory.decodeStream(is);
		width=image.getWidth();
		height=image.getHeight();
		iv.setAdjustViewBounds(true);
		iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
		iv.setImageBitmap(image);
		im.setBackgroundResource(R.mipmap.shoucang);
		im.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(!iscollect){
					im.setBackgroundResource(R.mipmap.favourite);

					iscollect=true;
					db=dh.getWritableDatabase();
					Cursor cur=db.rawQuery("select * from photo where photoname like ?",new String[]{filename});
					if(cur.moveToFirst()){
						Toast.makeText(Selectpage.this, "已经收藏过啦~",
								Toast.LENGTH_SHORT).show();
						cur.close();
						db.close();
					}
					else{
						ContentValues values=new ContentValues();
						values.put("photoname", filename);
						db.insert("photo", null, values);
						db.close();
						Toast.makeText(Selectpage.this, "收藏成功~",
								Toast.LENGTH_SHORT).show();
					}
				}
				else{
					im.setBackgroundResource(R.mipmap.shoucang);
					db=dh.getWritableDatabase();
					db.delete("photo", "photoname=?",new String[]{filename});
					db.close();
					Toast.makeText(Selectpage.this, "取消收藏~",
							Toast.LENGTH_SHORT).show();
					iscollect=false;
				}


			}


		});

	}
		@Override
		public boolean onTouchEvent(MotionEvent me){
			return detector.onTouchEvent(me);
		}
		public boolean onDown(MotionEvent arg0) {
			// TODO Auto-generated method stub
			return true;
		}
		@Override
		public boolean onFling(MotionEvent event1, MotionEvent event2, float velocityX,
				float velocityY) {
			//TODO Auto-generated method stub
			velocityX=velocityX>4000?4000:velocityX;
			velocityX=velocityX<-4000?-4000:velocityX;//根据手势的速度来计算缩放比，如果》0放大图片，否则缩小
			currentScale+=currentScale*velocityX/4000.0f;
			currentScale=currentScale>0.01?currentScale:0.01f;
			
			
			
			matrix.reset();
			matrix.setScale(currentScale,currentScale,160,200);
			

			BitmapDrawable tmp=(BitmapDrawable)iv.getDrawable();
			
			Bitmap bitmap=Bitmap.createBitmap(image,0,0,width,height,matrix,true);
			
				if(!tmp.getBitmap().isRecycled()){
					tmp.getBitmap().recycle();
					}
				iv.setImageBitmap(bitmap);
		   
			
			
			return true;
		}
		@Override
		public void onLongPress(MotionEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public boolean onScroll(MotionEvent arg0, MotionEvent arg1, float arg2,
				float arg3) {
			// TODO Auto-generated method stub
			return true;
		}
		@Override
		public void onShowPress(MotionEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public boolean onSingleTapUp(MotionEvent arg0) {
			
			
				double scale=0.8; 
				
				currentScale=(float)(currentScale*scale);
				Matrix matrix ;
				matrix=new Matrix();
				matrix.reset();
				matrix.setScale(currentScale,currentScale,160,200);
				BitmapDrawable tmp=(BitmapDrawable)iv.getDrawable();
				
				if(!tmp.getBitmap().isRecycled()){
					tmp.getBitmap().recycle();
					}
				Bitmap bitmap=Bitmap.createBitmap(image,0,0,width,height,matrix,true);
				iv.setImageBitmap(bitmap);
				
			
			
			return true;
		}


}
		   
		    

		

