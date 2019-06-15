package com.google.android.gms.common.stats;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@KeepForSdk
public abstract class StatsEvent extends AbstractSafeParcelable implements ReflectedParcelable {

    @KeepForSdk
    public interface Types {
        @KeepForSdk
        public static final int EVENT_TYPE_ACQUIRE_WAKE_LOCK = 7;
        @KeepForSdk
        public static final int EVENT_TYPE_RELEASE_WAKE_LOCK = 8;
    }

    public abstract int getEventType();

    public abstract long getTimeMillis();

    public abstract long zzv();

    public abstract String zzw();

    public String toString() {
        long timeMillis = getTimeMillis();
        int eventType = getEventType();
        long zzv = zzv();
        String zzw = zzw();
        StringBuilder stringBuilder = new StringBuilder(53 + String.valueOf(zzw).length());
        stringBuilder.append(timeMillis);
        stringBuilder.append("\t");
        stringBuilder.append(eventType);
        stringBuilder.append("\t");
        stringBuilder.append(zzv);
        stringBuilder.append(zzw);
        return stringBuilder.toString();
    }
}
