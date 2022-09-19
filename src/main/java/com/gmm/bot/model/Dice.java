package com.gmm.bot.model;

import com.gmm.bot.enumeration.DotNumber;
import lombok.Getter;

@Getter
public class Dice {
    private DotNumber dots;
    private String skin;

    public Dice(DotNumber dots) {
        this.dots = dots;
    }
}
