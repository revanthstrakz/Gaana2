package com.google.ads.interactivemedia.v3.impl.data;

import com.google.ads.interactivemedia.v3.internal.ki;

@ki(a = d.class)
public abstract class a {

    public interface a {
        a appState(String str);

        a build();

        a eventId(String str);

        a nativeTime(long j);

        a nativeViewAttached(boolean z);

        a nativeViewBounds(m mVar);

        a nativeViewHidden(boolean z);

        a nativeViewVisibleBounds(m mVar);

        a nativeVolume(double d);

        a queryId(String str);

        a vastEvent(String str);
    }

    public abstract String appState();

    public abstract String eventId();

    public abstract long nativeTime();

    public abstract boolean nativeViewAttached();

    public abstract m nativeViewBounds();

    public abstract boolean nativeViewHidden();

    public abstract m nativeViewVisibleBounds();

    public abstract double nativeVolume();

    public abstract String queryId();

    public abstract String vastEvent();

    public static a builder() {
        return new a();
    }
}
