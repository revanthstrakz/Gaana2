package com.helpshift.support.model;

import java.io.Serializable;

public class FuzzySearchToken implements Serializable {
    private static final long serialVersionUID = 1;
    public final String a;
    public final String b;

    public FuzzySearchToken(String str, String str2) {
        this.a = str;
        this.b = str2;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || !(obj instanceof FuzzySearchToken)) {
            return false;
        }
        FuzzySearchToken fuzzySearchToken = (FuzzySearchToken) obj;
        if (this.a != null ? this.a.equals(fuzzySearchToken.a) : fuzzySearchToken.a == null) {
            return this.b != null ? this.b.equals(fuzzySearchToken.b) : fuzzySearchToken.b == null;
        } else {
            return false;
        }
    }
}
