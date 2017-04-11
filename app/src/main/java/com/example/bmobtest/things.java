package com.example.bmobtest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
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
            actionBar.setHomeAsUpIndicator(R.mipmap.ic_keyboard_arrow_left_white_24dp);
        }
        et_title = (EditText) findViewById(R.id.et_title);
        et_phone = (EditText) findViewById(R.id.et_phone);
        et_describe = (EditText) findViewById(R.id.et_describe);
        Button mbutton = (Button) findViewById(R.id.btn_finish);
        mbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Lost lost = new Lost();
                lost.setDescribe(et_describe.getText().toString());
                lost.setPhone(et_phone.getText().toString());
                lost.setTitle(et_title.getText().toString());
                lost.save(things.this, new SaveListener() {
                    @Override
                    public void onSuccess() {
                        Toast.makeText(things.this, "失物信息添加成功", Toast.LENGTH_SHORT).show();
                        finish();
                        Log.d(TAG, "onSuccess:" + "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx添加信息成功");
                    }

                    @Override
                    public void onFailure(int i, String s) {
                        Toast.makeText(things.this, "失物信息添加失败", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "onSuccess:" + "ccccccccccccccccccccccccccccccccccccc添加信息失败");
                    }
                });
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            default:
        }
        return true;
    }
}
