package com.gmm.bot.model.block;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Jail extends Block {
    private static final int DEFAULT_TURN_IN_JAIL = 3;
    private int loseMoney;

    public static final String USE_50 = "USE_50";
    public static final String USE_CARD = "USE_CARD";
    public static final String WAIT_3_TURN = "WAIT_3_TURN";

    public Jail() {
    }

    @Builder
    public Jail(int position, boolean canRepurchase, boolean canSell, String name, int loseMoney) {
        super(position, canRepurchase, canSell, name);
        this.loseMoney = loseMoney;
    }
}
