package com.gmm.bot.model.block.card;

import com.gmm.bot.model.block.Block;

import java.util.ArrayList;
import java.util.List;

/**
 * this card return list city can remove (it can be destroyed or force sell)
 *
 */
public class CardRemoveCityOtherPlayer extends BaseCard{
    private boolean isSell;
    private List<Block> landOwnedByOther;

    public CardRemoveCityOtherPlayer(boolean isSell) {
        this.isSell = isSell;
        landOwnedByOther = new ArrayList<>();
    }

}
