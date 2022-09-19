package com.gmm.bot.model.block;

import lombok.Builder;

public class WorldTour extends Block {
    private int loseMoney;

    public WorldTour() {
    }

    @Builder
    public WorldTour(int position, boolean canRepurchase, boolean canSell, String name, int loseMoney) {
        super(position, canRepurchase, canSell, name);
        this.loseMoney = loseMoney;
    }
}