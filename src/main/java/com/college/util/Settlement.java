package com.college.util;

import java.util.List;

/**
 * Created by G on 2017/3/12.
 */
public class Settlement {
    private List<SettleItem> list;
    private double total;

    public Settlement(List<SettleItem> list) {
        this.list = list;
    }

    public double getTotal(){
        double sum = 0;
        for (SettleItem item: list
             ) {
            sum += item.getMoney();
        }
        return sum;
    }
}
