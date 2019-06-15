package com.google.ads.interactivemedia.v3.internal;

import java.util.Iterator;

public final class ld {
    public static boolean a(Iterator<?> it, Iterator<?> it2) {
        while (it.hasNext()) {
            if (!it2.hasNext()) {
                return false;
            }
            if (!ko.a(it.next(), it2.next())) {
                return false;
            }
        }
        return it2.hasNext() ^ 1;
    }
}
