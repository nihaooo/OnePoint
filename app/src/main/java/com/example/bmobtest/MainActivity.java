package com.example.bmobtest;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.SaveListener;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private AutoCompleteTextView et_name;
    private android.support.design.widget.TextInputEditText et_password;
    private ImageView mimageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bmob.initialize(this, "f9fd3e06a0ee9b9d702db043e81e7393");

        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        setContentView(R.layout.activity_main);


        mimageView = (ImageView) findViewById(R.id.back_image);
        Glide.with(MainActivity.this)
                .load(R.drawable.ground_img)
                .into(mimageView);


        Bean userInfo = BmobUser.getCurrentUser(MainActivity.this, Bean.class);
        if (userInfo != null) {
            Intent intent = new Intent(MainActivity.this, ContentActivity.class);
            startActivity(intent);
            finish();
        } else {
        }


        Button bt_register = (Button) findViewById(R.id.btn_register);
        bt_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        Button bt_login = (Button) findViewById(R.id.btn_login);
        et_name = (AutoCompleteTextView) findViewById(R.id.et_name);
        et_password = (TextInputEditText) findViewById(R.id.et_password);
        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Bean bean = new Bean();
                bean.setUsername(et_name.getText().toString());
                bean.setPassword(et_password.getText().toString());
                bean.login(MainActivity.this, new SaveListener() {
                    @Override
                    public void onSuccess() {
                        Toast.makeText(MainActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, ContentActivity.class);
                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void onFailure(int i, String s) {
                        Toast.makeText(MainActivity.this, "登录失败,请检查网络及输入信息", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "onFailure: ddddddddddddddddddddddddddddddddddddddd" + bean.getObjectId());
                    }
                });
            }
        });
    }
}
