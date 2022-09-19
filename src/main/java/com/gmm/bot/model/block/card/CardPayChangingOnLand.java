package com.gmm.bot.model.block.card;

import com.gmm.bot.model.block.Block;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * this card change money need to pay at the land
 * @Param: percentChange (can be negative)
 * @Param: isSpread = True land next to target and have same owner we have same effect
 */
@Getter
public class CardPayChangingOnLand extends BaseCard{
    private int percentChange;
    private int timeEffect;
    private boolean isSpread;
    List<Block> landOwnedByOther;

    public CardPayChangingOnLand(int percentChange, int timeEffect) {
        this.percentChange = percentChange;
        this.landOwnedByOther = new ArrayList<>();
    }

    public CardPayChangingOnLand(int percentChange, int timeEffect, boolean isSpread) {
        this.percentChange = percentChange;
        this.timeEffect = timeEffect;
        this.isSpread = isSpread;
    }

}
