package com.facebook.internal;

import java.util.EnumSet;
import java.util.Iterator;

public enum SmartLoginOption {
    None(0),
    Enabled(1),
    RequireConfirm(2);
    
    public static final EnumSet<SmartLoginOption> ALL = null;
    private final long mValue;

    static {
        ALL = EnumSet.allOf(SmartLoginOption.class);
    }

    public static EnumSet<SmartLoginOption> parseOptions(long j) {
        EnumSet noneOf = EnumSet.noneOf(SmartLoginOption.class);
        Iterator it = ALL.iterator();
        while (it.hasNext()) {
            SmartLoginOption smartLoginOption = (SmartLoginOption) it.next();
            if ((j & smartLoginOption.getValue()) != 0) {
                noneOf.add(smartLoginOption);
            }
        }
        return noneOf;
    }

    private SmartLoginOption(long j) {
        this.mValue = j;
    }

    public long getValue() {
        return this.mValue;
    }
}
