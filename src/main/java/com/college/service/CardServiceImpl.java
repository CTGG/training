package com.college.service;

import com.college.domain.Card;
import com.college.repository.CardRepo;
import com.college.util.TimeHelper;

import javax.annotation.Resource;

/**
 * Created by G on 2017/3/11.
 */
public class CardServiceImpl implements CardService{
    @Resource
    CardRepo cardRepo;

    @Override
    public boolean activate(int id) {
        Card card = new Card();
        card.setId(id);
        card.setLastpay(TimeHelper.getCurrentDate());
        card.setMoney(1000);
        card.setPayhis(1000);
        card.setLevel(0);
        card.setActive(true);
        Card newcard = cardRepo.save(card);
        return newcard.isActive();
    }

    @Override
    public void recharge(int id, double money) {
        
    }

    @Override
    public void cancelActive(int id) {

    }

    @Override
    public int checkActive(int id) {
        return 0;
    }

    @Override
    public Card getCard(int id) {
        return null;
    }

    @Override
    public void exchangePoints(int id, double points) {

    }
}
