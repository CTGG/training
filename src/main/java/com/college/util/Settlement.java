package com.college.util;

import com.college.domain.SettleItem;

import java.util.List;

/**
 * Created by G on 2017/3/12.
 */
public class Settlement {
    private int collegeid;
    private List<SettleItem> list;
    private double total;

    public Settlement(int collegeid) {
        this.collegeid = collegeid;
        total = 0;
    }

    public Settlement(List<SettleItem> list) {
        this.list = list;
    }

    public int getCollegeid() {
        return collegeid;
    }

    public List<SettleItem> addItem(SettleItem item){
        list.add(item);
        return list;
    }

    public void setCollegeid(int collegeid) {
        this.collegeid = collegeid;
    }

    public List<SettleItem> getList() {
        return list;
    }

    public void setList(List<SettleItem> list) {
        this.list = list;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void setTotal(){
        double sum = 0;
        for (SettleItem item: list
             ) {
            sum += item.getMoney();
        }
        total = sum;
    }

    public double getTotal() {
        return total;
    }
}
