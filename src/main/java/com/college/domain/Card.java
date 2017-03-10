package com.college.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Date;

/**
 * Created by G on 2017/3/10.
 */
@Entity(name = "card")
public class Card implements Serializable{
    @Id
    private int id;
    @Column(nullable = false)
    private int cardid;
    private Date lastpay;
    private double money;
    private double payhis;
    private int level;

    public Card() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCardid() {
        return cardid;
    }

    public void setCardid(int cardid) {
        this.cardid = cardid;
    }

    public Date getLastpay() {
        return lastpay;
    }

    public void setLastpay(Date lastpay) {
        this.lastpay = lastpay;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public double getPayhis() {
        return payhis;
    }

    public void setPayhis(double payhis) {
        this.payhis = payhis;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
