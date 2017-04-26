package com.example.bmobtest;

import cn.bmob.v3.BmobUser;

/**
 * Created by www10 on 2017/3/31.
 */

public class Bean extends BmobUser {
    private String name;
    private int studentid;
    private int pass;
    private String xueyuan;
    private String zhuanye;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStudentid() {
        return studentid;
    }

    public void setStudentid(int studentid) {
        this.studentid = studentid;
    }

    public int getPass() {
        return pass;
    }

    public void setPass(int pass) {
        this.pass = pass;
    }

    public String getXueyuan() {
        return xueyuan;
    }

    public void setXueyuan(String xueyuan) {
        this.xueyuan = xueyuan;
    }

    public String getZhuanye() {
        return zhuanye;
    }

    public void setZhuanye(String zhuanye) {
        this.zhuanye = zhuanye;
    }
}
