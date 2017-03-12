package com.college.domain;

import java.io.Serializable;

/**
 * Created by G on 2017/3/13.
 */
public class SettleItemId implements Serializable{
    private int studentid;
    private int courseid;

    public SettleItemId() {
    }

    public int getStudentid() {
        return studentid;
    }

    public void setStudentid(int studentid) {
        this.studentid = studentid;
    }

    public int getCourseid() {
        return courseid;
    }

    public void setCourseid(int courseid) {
        this.courseid = courseid;
    }
}
