package com.inmath.dl_test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.SearchView;


/**
 * Created by asus on 2016/3/16.
 * 点击查找后直接出现SearchView 页面
 */
public class SearchMath extends Activity {
    private SearchView search;
    private String SearchString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sezrchview);
        search= (SearchView) findViewById(R.id.search_view);
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                String contrast = null;
                String content = null;
                content=query;
                if (content.contains("导数"))
                    contrast = "1";

                else if (content.indexOf("三角函数") != -1
                        || content.indexOf("三角函数求导") != -1
                        || content.indexOf("基本函数求导") != -1)
                    contrast = "2";
                else if (content.contains("莱布尼兹"))
                    contrast = "3";
                else if (content.contains("微分"))
                    contrast = "4";
                else if (content.indexOf("罗耳") != -1
                        || content.indexOf("罗尔") != -1)
                    contrast = "5";
                else if (content.contains("拉格朗日"))
                    contrast = "6";
                else if (content.contains("柯西"))
                    contrast = "7";
                else if (content.indexOf("泰勒") != -1)
                    contrast = "8";
                else if (content.indexOf("基本积分") != -1)
                    contrast = "9";
                else if (content.indexOf("换元积分") != -1)
                    contrast = "10";
                else if (content.indexOf("分步积分") != -1)
                    contrast = "11";
                else if (content.indexOf("定积分") != -1)
                    contrast = "12";
                else if (content.indexOf("积分上限函数") != -1)
                    contrast = "13";
                else if (content.indexOf("牛顿莱布尼茨") != -1)
                    contrast = "14";
                else if (content.indexOf("定积分换元积分") != -1)
                    contrast = "15";
                else if (content.indexOf("定积分分步积分") != -1)
                    contrast = "16";
                else if (content.indexOf("向量加减") != -1)
                    contrast = "17";
                else if (content.indexOf("向量乘法") != -1)
                    contrast = "18";
                else if (content.indexOf("向量数量积") != -1
                        || content.indexOf("数量积") != -1)
                    contrast = "19";
                else if (content.indexOf("向量向量积") != -1
                        || content.indexOf("向量积") != -1)
                    contrast = "20";
                else if (content.indexOf("向量混合积") != -1
                        || content.indexOf("混合积") != -1)
                    contrast = "21";
                else if (content.indexOf("直线方程") != -1)
                    contrast = "22";
                else if (content.indexOf("参数方程") != -1)
                    contrast = "23";
                else if (content.indexOf("隐函数") != -1)
                    contrast = "24";
                else if (content.indexOf("方向导数") != -1
                        || content.indexOf("梯度") != -1)
                    contrast = "25";
                else if (content.indexOf("二元函数泰勒公式") != -1)
                    contrast = "26";
                else if (content.indexOf("二重积分") != -1)
                    contrast = "27";
                else if (content.indexOf("格林公式") != -1
                        || content.indexOf("格林") != -1)
                    contrast = "28";
                else if (content.indexOf("三重积分") != -1
                        || content.indexOf("累次积分") != -1)
                    contrast = "29";
                else if (content.indexOf("高斯") != -1)
                    contrast = "31";
                else if (content.indexOf("斯托克") != -1)
                    contrast = "32";
                else if (content.indexOf("傅立叶") != -1)
                    contrast = "33";
                else if (content.indexOf("行列式") != -1)
                    contrast = "34";
                else if (content.indexOf("矩阵") != -1)
                    contrast = "35";
                else if (content.indexOf("贝叶斯") != -1)
                    contrast = "36";
                else if (content.indexOf("0-1分布") != -1
                        || content.indexOf("01分布") != -1)
                    contrast = "37";
                else if (content.indexOf("二项分布") != -1)
                    contrast = "38";
                else if (content.indexOf("泊松分布") != -1)
                    contrast = "39";
                else if (content.indexOf("超几何分布") != -1)
                    contrast = "40";
                else if (content.indexOf("几何分布") != -1)
                    contrast = "41";
                else if (content.indexOf("均匀分布") != -1)
                    contrast = "42";
                else if (content.indexOf("指数分布") != -1)
                    contrast = "43";
                else if (content.indexOf("正态分布") != -1)
                    contrast = "44";
                else if (content.indexOf("佚名统计学") != -1)
                    contrast = "45";
                else if (content.indexOf("期望") != -1)
                    contrast = "46";
                else if (content.indexOf("方差") != -1)
                    contrast = "47";
                else if (content.indexOf("相关系数") != -1)
                    contrast = "48";
                else if (content.indexOf("矩") != -1)
                    contrast = "49";
                else if (content.indexOf("切比雪夫不等式") != -1)
                    contrast = "50";
                else if (content.indexOf("切比雪夫大数") != -1)
                    contrast = "51";
                else if (content.indexOf("伯努利大数") != -1)
                    contrast = "52";
                else if (content.indexOf("泊松大数") != -1)
                    contrast = "53";
                else if (content.indexOf("辛钦大数") != -1)
                    contrast = "54";
                else if (content.indexOf("卡方分布") != -1)
                    contrast = "55";
                else if (content.indexOf("t分布") != -1)
                    contrast = "56";
                else if (content.indexOf("F分布") != -1)
                    contrast = "57";
                else
                    contrast = "none";
                Intent intent=new Intent(SearchMath.this,Selectpage.class);
                Bundle bundle=new Bundle();
                bundle.putString("keyname", contrast);
                intent.putExtras(bundle);
                startActivity(intent);
                SearchMath.this.finish();


                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //这里可以出现提示，开一个新的线程
                return false;
            }
        });

    }
}
