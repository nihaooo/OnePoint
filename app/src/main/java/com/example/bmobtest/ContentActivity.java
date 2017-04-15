package com.example.bmobtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by www10 on 2017/4/1.
 */

public class ContentActivity extends BaseActivity {

    private static final String TAG = "ContentActivity";
    private DrawerLayout mDrawerLayout;
    private SwipeRefreshLayout swipeRefresh;
    private LostAdapter mAdapter;
    private List<Lost> mLostList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        Toolbar toolbar = (Toolbar) findViewById(R.id.list_toolbar);
        setSupportActionBar(toolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.mipmap.ic_list_white_24dp);
        }
        NavigationView navView = (NavigationView) findViewById(R.id.nav_view);
        navView.setCheckedItem(R.id.nav_Profile);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id){
                    case R.id.nav_Profile :
                        //语句1
                        break;
                    case R.id.nav_Change :
                        //语句2
                        Intent intent = new Intent(ContentActivity.this,ScanActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.nav_Settings :
                        //语句3
                        break;
                    case R.id.nav_quite :
                        //语句4
                        Toast.makeText(ContentActivity.this, "注销成功", Toast.LENGTH_SHORT).show();
                        BmobUser.logOut(ContentActivity.this);
                        BmobUser current = BmobUser.getCurrentUser(ContentActivity.this,Bean.class);
                        ActivityCollector.finishAll();
                        android.os.Process.killProcess(android.os.Process.myPid());
                        break;
                }
                mDrawerLayout.closeDrawers();
                return true;
            }
        });
        //对nav_header进行操作*****************************************************************************
        View view = navView.inflateHeaderView(R.layout.nav_header);
        TextView username = (TextView) view.findViewById(R.id.username);
//        ImageView imageView = (ImageView) view.findViewById(R.id.nav_head_img);
//        Glide.with(ContentActivity.this)
//                .load(R.drawable.back_img3)
//                .into(imageView);
        TextView studentid = (TextView) view.findViewById(R.id.studentid);
        Bean user = BmobUser.getCurrentUser(ContentActivity.this,Bean.class);
        username.setText(user.getUsername());
        studentid.setText(user.getName());


        mLostList = new ArrayList<>();//数据源
        mAdapter = new LostAdapter(this, mLostList);
        final ListView listList = (ListView) findViewById(R.id.lv_main);
        listList.setAdapter(mAdapter);
        listList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //待添加Item点击逻辑***************************************************************************
//                Toast.makeText(ContentActivity.this, "you clicked", Toast.LENGTH_SHORT).show();
            }
        });



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ContentActivity.this, things.class);
                startActivity(intent);
            }
        });
        swipeRefresh = (SwipeRefreshLayout) findViewById(R.id.swip_refresh);
        swipeRefresh.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshList();
                listList.setAdapter(mAdapter);
            }
        });
    }

    private void refreshList() {
        BmobQuery<Lost> query = new BmobQuery<Lost>();
        query.order("-createdAt");
        query.findObjects(this, new FindListener<Lost>() {
            @Override
            public void onSuccess(List<Lost> list) {
                mLostList.clear();
                mLostList.addAll(list);//添加进数据源
//                mAdapter = new LostAdapter(ContentActivity.this, mLostList);
                mAdapter.notifyDataSetChanged();
                Toast.makeText(ContentActivity.this, "刷新成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(int i, String s) {
                Toast.makeText(ContentActivity.this, "刷新失败", Toast.LENGTH_SHORT).show();
            }
        });
        swipeRefresh.setRefreshing(false);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            default:
        }
        return true;
    }

}
