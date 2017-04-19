package com.example.bmobtest;

import android.content.Context;
import android.content.SharedPreferences;

/*
 *      项目名：    AKUZxing
 *      包名：       com.example.bmobtest
 *      时间           2017/4/18.
 *      创建者：    qzhuorui
 *      描述：        TODO
 */
public class ShareUtils {

    public static final String NAME = "config";

    public static void putString(Context mContext, String key, String value) {
        SharedPreferences sp = mContext.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        sp.edit().putString(key, value).commit();

    }

    public static String getString(Context mContext, String key, String devalue) {
        SharedPreferences sp = mContext.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        return sp.getString(key, devalue);
    }

    public static void putInt(Context mContext, String key, int value) {
        SharedPreferences sp = mContext.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        sp.edit().putInt(key, value).commit();

    }

    public static int getInt(Context mContext, String key, int devalue) {
        SharedPreferences sp = mContext.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        return sp.getInt(key, devalue);
    }

    public static void putBoolean(Context mContext, String key, boolean value) {
        SharedPreferences sp = mContext.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        sp.edit().putBoolean(key, value).commit();

    }

    public static boolean getBoolean(Context mContext, String key, boolean devalue) {
        SharedPreferences sp = mContext.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        return sp.getBoolean(key, devalue);
    }

    public static void deleShare(Context mContext, String key) {
        SharedPreferences sp = mContext.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        sp.edit().remove(key).commit();
    }

    public void deleAll(Context mContext) {
        SharedPreferences sp = mContext.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        sp.edit().clear().commit();
    }


    public void test(Context mContext) {
        SharedPreferences sp = mContext.getSharedPreferences("config", Context.MODE_PRIVATE);//name of SharedPreferences
        SharedPreferences.Editor editor = sp.edit();

        editor.putString("key", "value");

        editor.commit();

        sp.getString("key", "未获取到");
    }
}
