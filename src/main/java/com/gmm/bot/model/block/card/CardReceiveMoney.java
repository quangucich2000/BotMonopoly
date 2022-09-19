package com.gmm.bot.model.block.card;

/**
 * player receive money
 * @Param: amount received
 */
public class CardReceiveMoney extends BaseCard{
    private long moneyReceived;

    public CardReceiveMoney(long moneyReceived) {
        this.moneyReceived = moneyReceived;
    }


}
