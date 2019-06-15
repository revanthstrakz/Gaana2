package com.helpshift.support.model;

import java.io.Serializable;

public class TfIdfSearchToken implements Serializable {
    private static final long serialVersionUID = 1;
    public final String a;
    public final int b;

    public TfIdfSearchToken(String str, int i) {
        this.a = str;
        this.b = i;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("value: ");
        stringBuilder.append(this.a);
        stringBuilder.append(", type: ");
        stringBuilder.append(this.b);
        return stringBuilder.toString();
    }
}
