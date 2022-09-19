package com.gmm.bot.model.block.card;

/**
 * player reduce money
 * @param: amount deducted
 */
public class CardReduceMoney extends BaseCard{
    private long moneyReduce;

    public CardReduceMoney(long moneyReduce) {
        this.moneyReduce = moneyReduce;
    }


}
