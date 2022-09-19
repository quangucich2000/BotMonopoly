package com.gmm.bot.enumeration;

public enum DotNumber {
    ONE((byte) 1),
    TWO((byte) 2),
    THREE((byte) 3),
    FOUR((byte) 4),
    FIVE((byte) 5),
    SIX((byte) 6),
    ;

    public static final int SIZE = values().length;

    private final int code;

    DotNumber(byte code) {
        this.code = code;
    }

    public byte getCode() {
        return (byte) this.code;
    }

    public static DotNumber from(int value) {
        for (DotNumber type : values()) {
            if (type.code == value) return type;
        }
        return null;
    }

    public static DotNumber from(String value) {
        int code = 0;
        try {
            code = Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return null;
        }
        return from(code);
    }
}
