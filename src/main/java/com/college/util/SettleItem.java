package com.college.util;

/**
 * Created by G on 2017/3/12.
 */
public class SettleItem {
    private int membercard;
    private int collegecard;
    private double money;

    public SettleItem(int membercard, int collegecard, double money) {
        this.membercard = membercard;
        this.collegecard = collegecard;
        this.money = money;
    }

        public int getMembercard() {
        return membercard;
    }

    public void setMembercard(int membercard) {
        this.membercard = membercard;
    }

    public int getCollegecard() {
        return collegecard;
    }

    public void setCollegecard(int collegecard) {
        this.collegecard = collegecard;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }
}
