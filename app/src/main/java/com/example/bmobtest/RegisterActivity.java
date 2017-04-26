package com.example.bmobtest;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import cn.bmob.v3.listener.SaveListener;

/**
 * Created by www10 on 2017/3/31.
 */

public class RegisterActivity extends Activity {

    private EditText et_name,et_password,et_nicheng,et_erjixueyuan,et_zhuanye,et_repassword;
    private static final String TAG = "RegisterActivity";
    private ImageView mimageView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        setContentView(R.layout.activity_register);

        mimageView2 = (ImageView) findViewById(R.id.back_image2);
        Glide.with(RegisterActivity.this)
                .load(R.drawable.ground_img)
                .into(mimageView2);

        Button bt_register = (Button) findViewById(R.id.btn_register);
        et_name = (EditText) findViewById(R.id.et_name);
        et_password = (EditText) findViewById(R.id.et_password);
        et_nicheng = (EditText) findViewById(R.id.et_nicheng);
        et_erjixueyuan = (EditText) findViewById(R.id.et_erjixueyuan);
        et_zhuanye = (EditText) findViewById(R.id.et_zhuanye);
        et_repassword = (EditText) findViewById(R.id.et_repassword);

        bt_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Bean bean = new Bean();
                if (et_nicheng.getText().equals("")||et_name.getText().equals("")||et_password.getText().equals("")) {
                    Toast.makeText(RegisterActivity.this, "输入信息不能为空", Toast.LENGTH_SHORT).show();
                }else {
                    bean.setName(et_nicheng.getText().toString().trim());
                    bean.setUsername(et_name.getText().toString().trim());
                    bean.setPassword(et_password.getText().toString().trim());
                    bean.setXueyuan(et_erjixueyuan.getText().toString().trim());
                    bean.setZhuanye(et_zhuanye.getText().toString().trim());
                    bean.signUp(RegisterActivity.this, new SaveListener() {
                        @Override
                        public void onSuccess() {
                            Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                            finish();
                        }

                        @Override
                        public void onFailure(int i, String s) {
                            Toast.makeText(RegisterActivity.this, "注册失败，请检查网络或已重复注册", Toast.LENGTH_SHORT).show();
                            Log.d(TAG, "onFailure: rrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr"+bean.getObjectId());
                        }
                    });
                }

            }
        });
    }
}
