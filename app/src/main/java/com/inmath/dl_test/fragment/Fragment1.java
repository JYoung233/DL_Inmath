package com.inmath.dl_test.fragment;

import android.content.Intent;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;

import com.inmath.dl_test.Myapplication;
import com.inmath.dl_test.R;
import com.inmath.dl_test.Selectpage;

/**
 * Created by asus on 2016/3/12.
 */
public class Fragment1 extends Fragment {

    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;
    private Button button7;
    private Button button8;
    private Button button9;

    Bundle bundle=new Bundle();
    private String[] title1=new String[]{"求导法则","微分方程","罗尔定律"};
    private String[] title2=new String[]{"基本积分","莱布尼兹","矩阵运算"};
    private String[] title3=new String[]{"矩阵运算","向量乘法","换元积分"};
    private String[] title4=new String[]{"泰勒公式","柯西中值","高斯公式"};
    private String[] title5=new String[]{"柯西中值","0-1分布","贝叶斯"};
    private String[] title6=new String[]{"傅里叶级数","泊松分布","莱布尼兹"};
    private String[] title7=new String[]{"泊松分布","分步积分","积分上限"};
    private String[] title8=new String[]{"微分运算","多重积分","超几何分步"};
    private String[] title9=new String[]{"莱布尼兹","佚名统计学","F分布"};
    private Myapplication app;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tab1,container,false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        app= (Myapplication) getActivity().getApplication();

        button1 = (Button) view.findViewById(R.id.button1);
        button2 = (Button) view.findViewById(R.id.button2);
        button3 = (Button) view.findViewById(R.id.button3);
        button4 = (Button) view.findViewById(R.id.button4);
        button5 = (Button) view.findViewById(R.id.button5);
        button6 = (Button) view.findViewById(R.id.button6);
        button7 = (Button) view.findViewById(R.id.button7);
        button8 = (Button) view.findViewById(R.id.button8);
        button9 = (Button) view.findViewById(R.id.button9);
        int day=app.getChange();//要不要给day加个最大值？？
        button1.setText(title1[day%3]);
        button2.setText(title2[day%3]);
        button3.setText(title3[day%3]);
        button4.setText(title4[day%3]);
        button5.setText(title5[day%3]);
        button6.setText(title6[day%3]);
        button7.setText(title7[day%3]);
        button8.setText(title8[day%3]);
        button9.setText(title9[day%3]);
        if(day>=1000){
            app.setChange(1);
        }else {
            app.setChange(day + 1);
        }

        Animation an= AnimationUtils.loadAnimation(getActivity(),R.anim.scalebutton );
        an.setFillAfter(true);
        an.startNow();
        Animation an1= AnimationUtils.loadAnimation(getActivity(),R.anim.scalebutton1 );
        an1.setFillAfter(true);
        an1.startNow();
        Animation an2= AnimationUtils.loadAnimation(getActivity(), R.anim.scalebutton2);
        an2.setFillAfter(true);
        an2.startNow();
        button1.setAnimation(an1);
        button2.setAnimation(an2);
        button3.setAnimation(an);
        button4.setAnimation(an1);
        button5.setAnimation(an2);
        button6.setAnimation(an);
        button7.setAnimation(an1);
        button8.setAnimation(an2);
        button9.setAnimation(an);

        button1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Selectpage.class);
                bundle.putString("keyname", "2");
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });
        button2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Selectpage.class);
                bundle.putString("keyname", "9");
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });
        button3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Selectpage.class);
                bundle.putString("keyname", "35");
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });
        button4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Selectpage.class);
                bundle.putString("keyname", "8");
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });
        button5.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Selectpage.class);
                bundle.putString("keyname", "7");
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });
        button6.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Selectpage.class);
                bundle.putString("keyname", "33");
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });
        button7.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Selectpage.class);
                bundle.putString("keyname", "39");
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });
        button8.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Selectpage.class);
                bundle.putString("keyname", "4");
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });
        button9.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Selectpage.class);
                bundle.putString("keyname", "3");
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });
    }
}
