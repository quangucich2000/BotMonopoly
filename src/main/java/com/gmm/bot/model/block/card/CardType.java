package com.gmm.bot.model.block.card;

public enum CardType {
    DEFAULT(-1),
    PAY_HALF(0),
    PAY_DOUBLE(1),
    PAY_ZERO(2),
    RECEIVE_MONEY_LV1(3),
    RECEIVE_MONEY_LV2(4),
    REDUCE_MONEY_LV1(5),
    REDUCE_MONEY_LV2(6),
    SWAP_CITY(7),
    DESTROY_CITY(8),
    FORCE_CELL_CITY(9),
    DISCOUNT_50_PERCENT(10),
    OUT_OF_JAIL(11)
    ;

    int code;

    CardType(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public static CardType of(int value) {
        if (value == -1) {
            return null;
        }
        for (CardType cardType : CardType.values()) {
            if (cardType.getCode() == value) {
                return cardType;
            }
        }
        return null;
    }

    public static int sizeValues() {
        return CardType.values().length;
    }
}
