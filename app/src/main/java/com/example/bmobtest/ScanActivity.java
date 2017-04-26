package com.example.bmobtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.xys.libzxing.zxing.activity.CaptureActivity;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by www10 on 2017/4/11.
 */

public class ScanActivity extends AppCompatActivity {
    private TextView scancontent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scan);
        Toolbar toolbar = (Toolbar) findViewById(R.id.scan_toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar   != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.mipmap.ic_keyboard_arrow_left_white_24dp);
        }
        scancontent = (TextView) findViewById(R.id.tv_scancontent);
        Button scanButton = (Button) findViewById(R.id.bt_scan);
        scanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scan();
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

    private void scan() {
        startActivityForResult(new Intent(ScanActivity.this,
                CaptureActivity.class),0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK){
            Bundle bundle = data.getExtras();
            String result = bundle.getString("result");
//            scancontent.setText(result);
            Bean user = BmobUser.getCurrentUser(ScanActivity.this,Bean.class);
            scancontent.setText( "签到课程："+ result + "\n签到学生：" + user.getUsername());
            QRBean qrBean = new QRBean();
            qrBean.setStudentclass(result);
            qrBean.setStudentid(user.getUsername());
            qrBean.setXueyuan(user.getXueyuan());
            qrBean.setZhuanye(user.getZhuanye());
            qrBean.setStuname(user.getName());
            qrBean.save(ScanActivity.this, new SaveListener() {
                @Override
                public void onSuccess() {
                    Toast.makeText(ScanActivity.this, "签到成功", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(int i, String s) {
                    Toast.makeText(ScanActivity.this, "签到失败", Toast.LENGTH_SHORT).show();
                }
            });


        }
    }
}
