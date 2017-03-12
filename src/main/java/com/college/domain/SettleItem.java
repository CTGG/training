package com.college.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by G on 2017/3/12.
 */
@Entity(name = "settleitem")
public class SettleItem implements Serializable{
    @Id
    private int studentid;
    private int collegeid;
    @Id
    private int courseid;
    private double money;
    private boolean counted;

    public SettleItem(int studentid, int collegeid, int courseid, double money) {
        this.studentid = studentid;
        this.collegeid = collegeid;
        this.courseid = courseid;
        this.money = money;
    }

    public SettleItem() {

    }

    public int getStudentid() {
        return studentid;
    }

    public void setStudentid(int studentid) {
        this.studentid = studentid;
    }

    public int getCollegeid() {
        return collegeid;
    }

    public void setCollegeid(int collegeid) {
        this.collegeid = collegeid;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public int getCourseid() {
        return courseid;
    }

    public void setCourseid(int courseid) {
        this.courseid = courseid;
    }

    public boolean isCounted() {
        return counted;
    }

    public void setCounted(boolean counted) {
        this.counted = counted;
    }
}
