package com.college.service;

import com.college.domain.Card;
import com.college.domain.Log;
import com.college.domain.SettleItem;
import com.college.repository.CardRepo;
import com.college.repository.LogRepo;
import com.college.repository.SettleRepo;
import com.college.util.TimeHelper;
import com.college.util.Type;
import org.joda.time.Days;
import org.joda.time.LocalDate;

import javax.annotation.Resource;
import java.sql.Date;

/**
 * Created by G on 2017/3/11.
 */
public class CardServiceImpl implements CardService{
    @Resource
    CardRepo cardRepo;
    @Resource
    LogRepo logRepo;


    @Override
    public boolean activate(int id) {
        Card card = new Card();
        card.setId(id);
        card.setLastpay(TimeHelper.getCurrentDate());
        card.setMoney(1000);
        card.setPayhis(1000);
        card.setLevel(0);
        card.setActive(true);
        Card newcard = save(card);
        Log log = new Log(id, "充值1000元激活会员卡", Type.MEMBER);
        logRepo.save(log);
        return newcard.isActive();
    }


    @Override
    public void recharge(int id, double money) {
        Card card = getCard(id);
        double balance = card.getMoney();
        balance += money;
        card.setMoney(balance);
        save(card);
        Log log = new Log(id, "充值"+money+"元", Type.MEMBER);
        logRepo.save(log);
    }

    @Override
    public void cancelActive(int id) {
        Card card = getCard(id);
        card.setActive(false);
        save(card);
        Log log = new Log(id, "取消会员资格", Type.MEMBER);
        logRepo.save(log);
    }

    @Override
    public boolean checkActive(int id) {
        Card card = getCard(id);
        return card.isActive();
    }

    @Override
    public Card getCard(int id) {
        Card card = cardRepo.findById(id);
        Date date1 = TimeHelper.getCurrentDate();
        Date date2 = card.getLastpay();
        int days = Days.daysBetween(LocalDate.fromDateFields(date1), LocalDate.fromDateFields(date2)).getDays();
        if (days>365){
            card.setActive(false);
        }
        save(card);
        return card;
    }

    @Override
    public void exchangePoints(int id, double points) {
        Card card = getCard(id);
        double oldpoints = card.getPayhis()/100;
        double oldmoney = card.getMoney();
        //check whether member input too much points
        double exchage = points;
        if (points > oldpoints){
            exchage = oldpoints;
        }
        //1. set new payhis by newpoints
        double newpoints = oldpoints - exchage;
        double newpayhis = newpoints * 100;
        card.setPayhis(newpayhis);
        //2. set new level by new payhis
        double level = newpayhis / 10000;
        card.setLevel((int) level);

        //3. add money
        double newmoney = oldmoney + exchage * 100;
        card.setMoney(newmoney);
        save(card);

        Log log = new Log(id, "兑换"+exchage+"积分", Type.MEMBER);
        logRepo.save(log);
    }

    @Override
    public void pay(int id, double price) {
        Card card = moneyChange(id, price);
        card.setLastpay(TimeHelper.getCurrentDate());
        card.setActive(true);
        save(card);
    }

    @Override
    public void refund(int id, double price) {
        Card card = moneyChange(id, 0-price);
        save(card);
    }

    private Card moneyChange(int id, double price){
        Card card = getCard(id);
        double money = card.getMoney();
        money -= price;
        card.setMoney(money);



        double payhis = card.getPayhis();
        payhis += price;

        if (payhis < 0){
            payhis = 0;
        }

        card.setPayhis(payhis);

        double level = payhis / 10000;
        if (level>6) level = 6;
        card.setLevel((int) level);

        return card;

    }

    public Card save(Card card){
        return cardRepo.save(card);
    }

}
