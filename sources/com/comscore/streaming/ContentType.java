package com.comscore.streaming;

public enum ContentType {
    LongFormOnDemand("c11"),
    ShortFormOnDemand("c12"),
    Live("c13"),
    UserGeneratedLongFormOnDemand("c21"),
    UserGeneratedShortFormOnDemand("c22"),
    UserGeneratedLive("c23"),
    Bumper("c99"),
    Other("c00");
    
    private final String a;

    private ContentType(String str) {
        this.a = str;
    }

    /* Access modifiers changed, original: protected */
    public String a() {
        return this.a;
    }
}
