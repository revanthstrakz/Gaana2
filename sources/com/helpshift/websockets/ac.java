package com.helpshift.websockets;

public class ac {
    private final String a;
    private final int b;
    private final String c;
    private final String d;

    ac(String str) {
        String[] split = str.split(" +", 3);
        if (split.length < 2) {
            throw new IllegalArgumentException();
        }
        this.a = split[0];
        this.b = Integer.parseInt(split[1]);
        this.c = split.length == 3 ? split[2] : null;
        this.d = str;
    }

    public int a() {
        return this.b;
    }

    public String toString() {
        return this.d;
    }
}
