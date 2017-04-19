package com.example.bmobtest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import cn.bmob.v3.listener.SaveListener;

/**
 * Created by www10 on 2017/4/5.
 */

public class things extends AppCompatActivity {

    private static final String TAG = "things";

    private EditText et_title, et_phone, et_describe;
    private DatePicker dp_date;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.things);
        et_title = (EditText) findViewById(R.id.et_title);
        et_phone = (EditText) findViewById(R.id.et_phone);
        et_describe = (EditText) findViewById(R.id.et_describe);
        dp_date = (DatePicker) findViewById(R.id.dp_date);

        Button mButton = (Button) findViewById(R.id.button_add);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendinformation();
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

    private void sendinformation() {
        Lost lost = new Lost();
        lost.setDescribe(et_describe.getText().toString());
        lost.setPhone(et_phone.getText().toString());
        lost.setTitle(et_title.getText().toString());
        lost.setTime(dp_date.getYear() + "-" + (dp_date.getMonth() + 1) + "-" + dp_date.getDayOfMonth());
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
