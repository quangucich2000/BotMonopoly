package com.gmm.bot.model;

import com.gmm.bot.enumeration.GemType;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.*;


@Setter
@Getter
public class Player {
    private int id;
    private String displayName;

    public Player(int id, String displayName) {
        this.id = id;
        this.displayName = displayName;
    }

}
