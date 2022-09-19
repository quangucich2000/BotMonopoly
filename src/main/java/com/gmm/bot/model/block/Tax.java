package com.gmm.bot.model.block;

import lombok.Builder;

public class Tax extends Block {
    public static long TAX_MONEY_NEED_TO_PAY = 2000;

    private int loseMoneyPercent;

    public Tax() {
    }

    @Builder
    public Tax(int position, boolean canRepurchase, boolean canSell, String name, int loseMoneyPercent) {
        super(position, canRepurchase, canSell, name);
        this.loseMoneyPercent = loseMoneyPercent;
    }
}
