package com.inmath.dl_test.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.inmath.dl_test.MathTest;
import com.inmath.dl_test.R;
import com.inmath.dl_test.adapter.TabAdapter;

/**
 * Created by asus on 2016/3/12.
 * 第二个tab的显示效果
 */
public class Fragment2 extends Fragment {
    private RecyclerView mRecyclerView;
    private TabAdapter madapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tab2,container,false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        mRecyclerView= (RecyclerView) view.findViewById(R.id.tab2_recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        madapter=new TabAdapter(getActivity());
        madapter.msetOnClickListener(new TabAdapter.mClickListener() {
            @Override
            public void mOnClickListener(View view, int pos) {
                Intent intent = new Intent(getActivity(), MathTest.class);
                intent.putExtra("parentId", pos+1);
                startActivity(intent);
            }
        });
        mRecyclerView.setAdapter(madapter);

    }
}
