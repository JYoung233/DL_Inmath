package com.inmath.dl_test;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.inmath.dl_test.model.Chapter;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.DbException;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

import java.util.List;


public class Register2 extends Activity {
	private EditText editview1;
	private EditText editview2;
	private EditText editview3;
	private Button button1;
	private Toolbar toolbar;
	SQLiteDatabase db = null;


	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.avtivity_register2);
		toolbar= (Toolbar) findViewById(R.id.toolbar7);
		toolbar.setTitle("用户注册");
		editview1 = (EditText) this.findViewById(R.id.editText1);
		editview2 = (EditText) this.findViewById(R.id.editText2);
		editview3 = (EditText) this.findViewById(R.id.editText3);
		button1 = (Button) this.findViewById(R.id.button3);
		String usrname = editview1.getText().toString();
		String pasword = editview2.getText().toString();
		String nopasword = editview3.getText().toString();

		button1.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				String usrname = editview1.getText().toString();
				String pasword = editview2.getText().toString();
				String nopasword = editview3.getText().toString();
				int flag = -1;

				if (usrname.equals("") || pasword.equals("")
						|| nopasword.equals("")) {
					Toast.makeText(Register2.this, "用户名或密码不能为空哦~",
							Toast.LENGTH_SHORT).show();
					flag = 0;
				}
				if (!(pasword.equals(nopasword))) {
					Toast.makeText(Register2.this, "两次输入密码不一致",
							Toast.LENGTH_SHORT).show();
					flag = 0;
				}
				registTask(usrname, pasword);
			}

		});
	}

	public void registTask(String name, String password) {
		HttpUtils http = new HttpUtils();
		RequestParams params = new RequestParams();
		params.addBodyParameter("name", name);
		params.addBodyParameter("password", password);
		http.send(HttpMethod.POST, "http://www.liuminhua.cn/Math/RegistAction",
				params, new RequestCallBack<String>() {

					@Override
					public void onFailure(HttpException arg0, String arg1) {
						Toast.makeText(Register2.this, "网络问题，请重试", Toast.LENGTH_SHORT).show();
					}

					@Override
					public void onSuccess(ResponseInfo<String> info) {
						String result = info.result;
						JSONObject jsonObject = JSONObject.parseObject(result);
						int errorCode = jsonObject.getInteger("errorCode");
						if (errorCode == 1000) {
							Toast.makeText(Register2.this, "注册成功",Toast.LENGTH_SHORT).show();
							Intent intent1=new Intent(Register2.this,SaveSecretActivity.class);//这里回到主窗口，一般回到信息窗口
							startActivity(intent1);
							Register2.this.finish();
						} else {
							Toast.makeText(Register2.this, "注册失败",Toast.LENGTH_SHORT).show();
						}
					}

				});
	}

	/***
	 * 数据库使用方法
	 * 
	 * @throws DbException
	 */
	public void db() {
		DbUtils dbUtils = DbUtils.create(this,  "Math");
		Chapter chapter = new Chapter();
		chapter.setChapterName("线性代数");
		try {
			dbUtils.save(chapter);
			List<Chapter> chapters = dbUtils.findAll(Chapter.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
