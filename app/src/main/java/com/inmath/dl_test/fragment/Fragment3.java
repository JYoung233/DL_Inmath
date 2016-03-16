package com.inmath.dl_test.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.inmath.dl_test.AddNote;
import com.inmath.dl_test.Calculator;
import com.inmath.dl_test.CollectPic;
import com.inmath.dl_test.R;
import com.inmath.dl_test.adapter.MyTab3Adapter;

/**
 * Created by asus on 2016/3/12.
 * 滑动的第三个页面
 */
public class Fragment3 extends Fragment {
    private RecyclerView mRecyclerView;
    private LinearLayout l1,l2,l3;
    private MyTab3Adapter myTab3Adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tab3,container,false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView= (RecyclerView) view.findViewById(R.id.tab3_recycler);
        l1= (LinearLayout) view.findViewById(R.id.tab3_select_note);
        l2= (LinearLayout) view.findViewById(R.id.selelct_collect);
        l3= (LinearLayout) view.findViewById(R.id.select_calculate);
        l1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),AddNote.class);
                startActivity(intent);
            }
        });
        l2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),CollectPic.class);
                startActivity(intent);
            }
        });
        l3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),Calculator.class);//这里还要加上计算器
                startActivity(intent);
            }
        });
       mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        myTab3Adapter=new MyTab3Adapter(getActivity());
        myTab3Adapter.msetOnClickListener(new MyTab3Adapter.mClickListener() {
            @Override
            public void mOnClickListener(View view, int pos) {

            }
        });
        mRecyclerView.setAdapter(myTab3Adapter);

    }
}
