package com.gmm.bot.model.block;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class Block {
    protected int position;
    protected boolean canRepurchase;
    protected boolean canSell;
    protected String name;

    public Block() {
    }

    public Block(int position, boolean canRepurchase, boolean canSell, String name) {
        this.position = position;
        this.canRepurchase = canRepurchase;
        this.canSell = canSell;
        this.name = name;
    }
}
