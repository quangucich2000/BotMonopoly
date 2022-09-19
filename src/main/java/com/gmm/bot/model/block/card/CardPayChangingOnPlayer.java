package com.gmm.bot.model.block.card;

import lombok.Getter;

/**
 * this card change money need to pay when player on the land of other player
 * @Param: percent change (can be negative)
 */
@Getter
public class CardPayChangingOnPlayer extends BaseCard{
    private int percentChange;

    public CardPayChangingOnPlayer(int percentChange) {
        this.percentChange = percentChange;
    }


}
