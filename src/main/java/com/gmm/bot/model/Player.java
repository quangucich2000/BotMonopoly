package com.gmm.bot.model;

import com.gmm.bot.enumeration.GemType;
import com.gmm.bot.model.block.card.BaseCard;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.*;


@Setter
@Getter
public class Player {
    private int id;
    private String displayName;
    private long balance;
    private List<BaseCard> cardsOwned;
    private int endTurnJail;

    public Player(int id, String displayName) {
        this.id = id;
        this.displayName = displayName;
        this.balance = 2000;
        this.cardsOwned = new ArrayList<>();
        this.endTurnJail = -1;
    }

}
