package com.comscore.streaming;

public enum AdType {
    LinearOnDemandPreRoll("a11"),
    LinearOnDemandMidRoll("a12"),
    LinearOnDemandPostRoll("a13"),
    LinearLive("a21"),
    Other("a00");
    
    private final String a;

    private AdType(String str) {
        this.a = str;
    }

    /* Access modifiers changed, original: protected */
    public String a() {
        return this.a;
    }
}
