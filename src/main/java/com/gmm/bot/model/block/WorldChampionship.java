package com.gmm.bot.model.block;

import lombok.Builder;

public class WorldChampionship extends Block {
    private static final long MONEY_USING_UPDATE = 50000;

    private int loseMoney;

    public WorldChampionship() {
    }

    @Builder
    public WorldChampionship(int position, boolean canRepurchase, boolean canSell, String name, int loseMoney) {
        super(position, canRepurchase, canSell, name);
        this.loseMoney = loseMoney;
    }
}
