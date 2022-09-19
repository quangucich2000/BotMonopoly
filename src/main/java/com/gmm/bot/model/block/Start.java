package com.gmm.bot.model.block;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Start extends Block {
    private int receiveMoney;

    public Start() {
    }

    @Builder
    public Start(int position, boolean canRepurchase, boolean canSell, String name, int receiveMoney) {
        super(position, canRepurchase, canSell, name);
        this.receiveMoney = receiveMoney;
    }
}
