package com.google.android.gms.common.util;

import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import android.support.v4.util.ArraySet;
import com.google.android.gms.common.annotation.KeepForSdk;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@KeepForSdk
public final class CollectionUtils {
    private CollectionUtils() {
    }

    @KeepForSdk
    public static boolean isEmpty(@Nullable Collection<?> collection) {
        return collection == null ? true : collection.isEmpty();
    }

    @KeepForSdk
    @Deprecated
    public static <T> List<T> listOf() {
        return Collections.emptyList();
    }

    @KeepForSdk
    @Deprecated
    public static <T> List<T> listOf(T t) {
        return Collections.singletonList(t);
    }

    @KeepForSdk
    @Deprecated
    public static <T> List<T> listOf(T... tArr) {
        switch (tArr.length) {
            case 0:
                return listOf();
            case 1:
                return listOf(tArr[0]);
            default:
                return Collections.unmodifiableList(Arrays.asList(tArr));
        }
    }

    private static <T> Set<T> zza(int i, boolean z) {
        float f = z ? 0.75f : 1.0f;
        if (i <= (z ? 128 : 256)) {
            return new ArraySet(i);
        }
        return new HashSet(i, f);
    }

    @KeepForSdk
    @Deprecated
    public static <T> Set<T> setOf(T t, T t2, T t3) {
        Set zza = zza(3, false);
        zza.add(t);
        zza.add(t2);
        zza.add(t3);
        return Collections.unmodifiableSet(zza);
    }

    @KeepForSdk
    @Deprecated
    public static <T> Set<T> setOf(T... tArr) {
        Object obj;
        Object obj2;
        Set zza;
        switch (tArr.length) {
            case 0:
                return Collections.emptySet();
            case 1:
                return Collections.singleton(tArr[0]);
            case 2:
                obj = tArr[0];
                obj2 = tArr[1];
                zza = zza(2, false);
                zza.add(obj);
                zza.add(obj2);
                return Collections.unmodifiableSet(zza);
            case 3:
                return setOf(tArr[0], tArr[1], tArr[2]);
            case 4:
                obj = tArr[0];
                Object obj3 = tArr[1];
                Object obj4 = tArr[2];
                obj2 = tArr[3];
                Set zza2 = zza(4, false);
                zza2.add(obj);
                zza2.add(obj3);
                zza2.add(obj4);
                zza2.add(obj2);
                return Collections.unmodifiableSet(zza2);
            default:
                zza = zza(tArr.length, false);
                Collections.addAll(zza, tArr);
                return Collections.unmodifiableSet(zza);
        }
    }

    @KeepForSdk
    public static <T> Set<T> mutableSetOfWithSize(int i) {
        if (i == 0) {
            return new ArraySet();
        }
        return zza(i, true);
    }

    private static <K, V> Map<K, V> zzb(int i, boolean z) {
        if (i <= 256) {
            return new ArrayMap(i);
        }
        return new HashMap(i, 1.0f);
    }

    @KeepForSdk
    public static <K, V> Map<K, V> mapOf(K k, V v, K k2, V v2, K k3, V v3) {
        Map zzb = zzb(3, false);
        zzb.put(k, v);
        zzb.put(k2, v2);
        zzb.put(k3, v3);
        return Collections.unmodifiableMap(zzb);
    }

    @KeepForSdk
    public static <K, V> Map<K, V> mapOf(K k, V v, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5, K k6, V v6) {
        Map zzb = zzb(6, false);
        zzb.put(k, v);
        zzb.put(k2, v2);
        zzb.put(k3, v3);
        zzb.put(k4, v4);
        zzb.put(k5, v5);
        zzb.put(k6, v6);
        return Collections.unmodifiableMap(zzb);
    }

    @KeepForSdk
    public static <K, V> Map<K, V> mapOfKeyValueArrays(K[] kArr, V[] vArr) {
        if (kArr.length != vArr.length) {
            int length = kArr.length;
            int length2 = vArr.length;
            StringBuilder stringBuilder = new StringBuilder(66);
            stringBuilder.append("Key and values array lengths not equal: ");
            stringBuilder.append(length);
            stringBuilder.append(" != ");
            stringBuilder.append(length2);
            throw new IllegalArgumentException(stringBuilder.toString());
        }
        int i = 0;
        switch (kArr.length) {
            case 0:
                return Collections.emptyMap();
            case 1:
                return Collections.singletonMap(kArr[0], vArr[0]);
            default:
                Map zzb = zzb(kArr.length, false);
                while (i < kArr.length) {
                    zzb.put(kArr[i], vArr[i]);
                    i++;
                }
                return Collections.unmodifiableMap(zzb);
        }
    }
}
