package com.college.service;

import com.college.domain.Card;

/**
 * Created by G on 2017/3/11.
 */
public interface CardService {
    /**
     * 默认充值1000元，并将active置为1，修改最新日期，增加钱，增加历史金额，level仍为0
     * @param id
     *
     */
    public boolean activate(int id);


    /**
     * 充值，增加钱
     * @param id
     * @param money
     */
    public void recharge(int id, double money);

    /**
     * 将active置为0
     * @param id
     */
    public void cancelActive(int id);

    /**
     * 查看active的状态
     * @param id
     * @return
     */
    public boolean checkActive(int id);

    /**
     * 得到卡所有信息，自动更新过active的并将更新后的存入数据库
     * @param id
     * @return
     */
    public Card getCard(int id);

    /**
     * 兑换积分为卡金额，money增加，历史金额减少，查看level是否需要减少
     * @param id
     * @param points
     */
    public void exchangePoints(int id, double points);

}
