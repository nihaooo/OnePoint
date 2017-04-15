package com.example.bmobtest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import cn.bmob.v3.listener.SaveListener;

/**
 * Created by www10 on 2017/4/5.
 */

public class things extends AppCompatActivity {

    private static final String TAG = "things";

    private EditText et_title, et_phone, et_describe;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.things);
        Toolbar toolbar = (Toolbar) findViewById(R.id.things_toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar   != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.mipmap.ic_arrow_back_white_24dp);
        }
        et_title = (EditText) findViewById(R.id.et_title);
        et_phone = (EditText) findViewById(R.id.et_phone);
        et_describe = (EditText) findViewById(R.id.et_describe);


    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.ok:
                sendinformation();
                break;
            default:
        }
        return true;
    }

    private void sendinformation() {
        Lost lost = new Lost();
        if (et_describe.getText().toString().equals("")&&et_phone.getText().toString().equals("")&&et_title.getText().toString().equals("")) {
            Toast.makeText(this, "输入信息不能为空", Toast.LENGTH_SHORT).show();
        }else {
            lost.setDescribe(et_describe.getText().toString());
            lost.setPhone(et_phone.getText().toString());
            lost.setTitle(et_title.getText().toString());
            lost.save(things.this, new SaveListener() {
                @Override
                public void onSuccess() {
                    Toast.makeText(things.this, "失物信息添加成功", Toast.LENGTH_SHORT).show();
                    finish();
                }

                @Override
                public void onFailure(int i, String s) {
                    Toast.makeText(things.this, "失物信息添加失败", Toast.LENGTH_SHORT).show();
                }
            });
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbal,menu);
        return true;
    }
}
