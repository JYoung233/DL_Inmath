package com.inmath.dl_test;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.inmath.dl_test.adapter.ViewPageAdapter;
import com.inmath.dl_test.fragment.Fragment3;
import com.inmath.dl_test.rdata.NoteMain;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private List<String> Title=new ArrayList<>();
    private SearchView searchView;
    private Myapplication app;
    private int day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("InMath");
        setSupportActionBar(toolbar);
        Title.add("每日公式");
        Title.add("试题库");
        Title.add("数学工具");
        app= (Myapplication) this.getApplication();
        day=app.getDay();
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
        ViewPager viewPager= (ViewPager) findViewById(R.id.viewpage);
        ViewPageAdapter viewPageAdapter=new ViewPageAdapter(getSupportFragmentManager(),Title);
        viewPager.setAdapter(viewPageAdapter);
        TabLayout tabLayout= (TabLayout) findViewById(R.id.tab);
        tabLayout.setupWithViewPager(viewPager);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }else if(id==R.id.action_search){
           Intent intent=new Intent(MainActivity.this,SearchMath.class);
            startActivity(intent);

        }else if(id==R.id.action_alarm){

        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
            Intent intent=new Intent(MainActivity.this,NoteMain.class);
            MainActivity.this.startActivity(intent);
        } else if (id == R.id.nav_gallery) {
            Intent intent=new Intent(MainActivity.this,CollectPic.class);
            MainActivity.this.startActivity(intent);

        } else if (id == R.id.nav_manage) {
            drawer.closeDrawer(GravityCompat.START);
            Intent intent=new Intent(MainActivity.this,Calculator.class);
            startActivity(intent);

        } else if (id == R.id.user_info) {
            drawer.closeDrawer(GravityCompat.START);
            Intent intent=new Intent(MainActivity.this,SaveSecretActivity.class);
            startActivity(intent);

        } else if (id == R.id.user_share) {
            drawer.closeDrawer(GravityCompat.START);
            LayoutInflater layout=this.getLayoutInflater();
            View view=layout.inflate(R.layout.reportdialog_item, (ViewGroup) findViewById(R.id.share_dialog));
            TextView tv= (TextView) view.findViewById(R.id.second);
            tv.setText(String.valueOf(app.getDay()));
            new AlertDialog.Builder(this).setTitle("打卡分享").setView(view).setIcon(R.mipmap.life).setPositiveButton("我要打卡", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    app.setDay(day+1);
                    Toast.makeText(MainActivity.this,"打卡成功",Toast.LENGTH_SHORT).show();

                }
            }).setNegativeButton("没学习，不打卡", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            }).show();


        }

//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
