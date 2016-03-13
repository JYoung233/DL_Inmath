package com.inmath.dl_test;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.inmath.dl_test.adapter.ExamAdpter;
import com.inmath.dl_test.model.Pager;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class MathTest extends Activity implements OnScrollListener {
	private int parentId = 1;

	private static final String TAG = MathTest.class.getSimpleName();

	private ListView listView;

	private View moreView;
	private Toolbar mToolbar;

	private int lastItem;

	private List<Pager> exams = new ArrayList<>();

	private ExamAdpter adpter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_one);
		mToolbar= (Toolbar) findViewById(R.id.toolbar3);
        mToolbar.setTitle("Inmath");
		parentId = getIntent().getIntExtra("parentId", 2);

		listView = (ListView) findViewById(R.id.listView);
		moreView = getLayoutInflater().inflate(R.layout.load, null);
		listView.addFooterView(moreView);
		moreView.setVisibility(View.GONE);
		adpter = new ExamAdpter(exams, this);
		listView.setAdapter(adpter);
		listView.setOnScrollListener(this);

		getData(0, LIMINT_COUNT);
		listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int postion, long id) {
				Pager pager = (Pager) adpter.getItem(postion);

				if (!TextUtils.isEmpty(pager.getPath())) {
					Intent intent = new Intent(MathTest.this,
							PaperShow.class);
					intent.putExtra("name", pager.getName());
					intent.putExtra("path", pager.getPath());
					startActivity(intent);
				} else {
					Intent intent = new Intent(MathTest.this,
							MathTest.class);
					intent.putExtra("parentId", pager.getId());
					startActivity(intent);
				}
			}
		});

	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		// TODO Auto-generated method stub
		Log.i(TAG, "firstVisibleItem=" + firstVisibleItem
				+ "\nvisibleItemCount=" + visibleItemCount + "\ntotalItemCount"
				+ totalItemCount);

		lastItem = firstVisibleItem + visibleItemCount - 1;
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		// TODO Auto-generated method stub
		Log.i(TAG, "scrollState=" + scrollState);

		if (lastItem == adpter.getCount()
				&& scrollState == this.SCROLL_STATE_IDLE) {
			Log.i(TAG, "拉到最底部");
			if (adpter.getCount() == 0) {

				getData(0, LIMINT_COUNT);
			} else {
				getData(adpter.getCount(), LIMINT_COUNT);
			}
		}
	}


	final int LIMINT_COUNT = 5;

	public void getData(int first, final int count) {
		moreView.setVisibility(View.VISIBLE);
		HttpUtils httpUtils = new HttpUtils();
		RequestParams params = new RequestParams();
		params.addBodyParameter("parentId", parentId + "");
		params.addBodyParameter("first", first + "");
		params.addBodyParameter("count", count + "");
		httpUtils.send(HttpMethod.POST,
				"http://www.liuminhua.cn/Math/PagerAction", params,
				new RequestCallBack<String>() {

					@Override
					public void onCancelled() {
						super.onCancelled();
					}

					@Override
					public void onFailure(HttpException e, String arg1) {
						Log.d(MathTest.class.getName(), e.getMessage());
						Toast.makeText(MathTest.this, "网络连接失败，请重试",Toast.LENGTH_SHORT)
								.show();
						moreView.setVisibility(View.GONE);
					}

					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {
						moreView.setVisibility(View.GONE);
						String info = responseInfo.result;
						JSONObject infoJson = JSONObject.parseObject(info);
						int errorCode = infoJson.getInteger("errorCode");
						if (errorCode == 1000) {

							JSONArray jsonDataFromNet = infoJson
									.getJSONArray("model");
							List<Pager> dataFromNet = JSONArray.parseArray(
									jsonDataFromNet.toJSONString(), Pager.class);
							if (dataFromNet != null && !dataFromNet.isEmpty()) {
								exams.addAll(dataFromNet);
								adpter.notifyDataSetChanged();
								if (dataFromNet.size() < count) {

									Toast.makeText(MathTest.this, "木有更多数据！",
											Toast.LENGTH_SHORT).show();
									listView.removeFooterView(moreView);
								}
							} else {

								Toast.makeText(MathTest.this, "木有更多数据！",
										Toast.LENGTH_SHORT).show();
								listView.removeFooterView(moreView);
							}
						}
					}

				});
	}
}
