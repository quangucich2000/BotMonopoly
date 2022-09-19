package com.gmm.bot.model.layout;

import com.gmm.bot.model.Dice;
import com.gmm.bot.model.Player;
import com.gmm.bot.model.block.Block;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
public abstract class Layout implements ICreateLayout{
    public final int FOUR_SIDES = 4;
    protected int size;
    protected List<Block> blockList;
    protected List<Dice> diceList;

    protected Layout() {
        blockList = Arrays.asList(new Block[8 * FOUR_SIDES]);
        diceList = new ArrayList<>();
    }

    protected Layout(int size, List<Dice> diceList) {
        this.size = size;
        blockList = Arrays.asList(new Block[size * FOUR_SIDES]);
        this.diceList = diceList;
    }
}
