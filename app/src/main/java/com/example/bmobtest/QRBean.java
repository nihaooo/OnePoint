package com.example.bmobtest;

import cn.bmob.v3.BmobObject;

/**
 * Created by www10 on 2017/4/11.
 */

public class QRBean extends BmobObject {
    private String studentclass;
    private String studentid;

    public String getStudentclass() {
        return studentclass;
    }

    public void setStudentclass(String studentclass) {
        this.studentclass = studentclass;
    }

    public String getStudentid() {
        return studentid;
    }

    public void setStudentid(String studentid) {
        this.studentid = studentid;
    }
}
