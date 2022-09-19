package com.gmm.bot.model.block;

import com.gmm.bot.model.Player;
import com.gmm.bot.model.block.card.BaseCard;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Land extends Block {
    private static final int MAX_LEVEL = 5;
    private static final int MAX_LEVEL_FIRST_ROUND = 3;
    private int price;
    private int coefficient;
    private int rentPrice;
    private int upgradePrice;
    private int level;
    private int maxLevel;
    private int repurchasePrice;
    private BlockType blockType;
    private List<BaseCard> cards;
    private Player owner;

    public Land() {
    }

    @Builder
    public Land(int position, boolean canRepurchase, boolean canSell, String name, int price,
                int coefficient, int rentPrice, int upgradePrice, int level, int maxLevel,
                int repurchasePrice, BlockType blockType, List<BaseCard> cards, Player owner) {
        super(position, canRepurchase, canSell, name);
        this.price = price;
        this.coefficient = coefficient;
        this.rentPrice = rentPrice;
        this.upgradePrice = upgradePrice;
        this.level = level;
        this.maxLevel = maxLevel;
        this.repurchasePrice = repurchasePrice;
        this.blockType = blockType;
        this.cards = cards;
        this.owner = owner;
    }
}
