package com.google.android.exoplayer2.util;

import java.util.Comparator;

final /* synthetic */ class SlidingPercentile$$Lambda$0 implements Comparator {
    static final Comparator $instance = new SlidingPercentile$$Lambda$0();

    private SlidingPercentile$$Lambda$0() {
    }

    public int compare(Object obj, Object obj2) {
        return (((Sample) obj).index - ((Sample) obj2).index);
    }
}
