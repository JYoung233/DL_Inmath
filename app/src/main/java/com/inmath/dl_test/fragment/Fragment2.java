package com.inmath.dl_test.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.inmath.dl_test.MathTest;
import com.inmath.dl_test.R;

/**
 * Created by asus on 2016/3/12.
 */
public class Fragment2 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tab2,container,false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        LinearLayout fen= (LinearLayout) view.findViewById(R.id.lfen);
        LinearLayout li= (LinearLayout) view.findViewById(R.id.lli);
        LinearLayout da= (LinearLayout) view.findViewById(R.id.lda);
        LinearLayout kao= (LinearLayout) view.findViewById(R.id.lkao);
        fen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MathTest.class);
                intent.putExtra("parentId", 1);
                startActivity(intent);

            }
        });
        li.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MathTest.class);
                intent.putExtra("parentId", 2);
                startActivity(intent);

            }
        });
        da.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MathTest.class);
                intent.putExtra("parentId", 3);
                startActivity(intent);

            }
        });
        kao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MathTest.class);
                intent.putExtra("parentId", 4);
                startActivity(intent);

            }
        });

    }
}
