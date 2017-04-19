package com.example.bmobtest;

import android.util.Log;

/*
 *      项目名：    AKUZxing
 *      包名：       com.example.bmobtest
 *      时间           2017/4/18.
 *      创建者：    qzhuorui
 *      描述：        TODO
 */
public class L {

    //开关
    public static final boolean DEBUG = true;
    //TAG
    public static final String TAG = "Smartbutler";

    //五个等级 DIWE

    public static void d(String text) {
        if (DEBUG) {
            Log.d(TAG, text);
        }
    }

    public static void i(String text) {
        if (DEBUG) {
            Log.i(TAG, text);
        }
    }

    public static void w(String text) {
        if (DEBUG) {
            Log.w(TAG, text);
        }
    }

    public static void e(String text) {
        if (DEBUG) {
            Log.e(TAG, text);
        }
    }


}
