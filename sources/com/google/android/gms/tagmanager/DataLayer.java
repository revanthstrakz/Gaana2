package com.google.android.gms.tagmanager;

import com.google.android.gms.common.util.VisibleForTesting;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantLock;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@VisibleForTesting
public class DataLayer {
    public static final String EVENT_KEY = "event";
    public static final Object OBJECT_NOT_PRESENT = new Object();
    private static final String[] zzbaz = "gtm.lifetime".toString().split("\\.");
    private static final Pattern zzbba = Pattern.compile("(\\d+)\\s*([smhd]?)");
    private final ConcurrentHashMap<zzb, Integer> zzbbb;
    private final Map<String, Object> zzbbc;
    private final ReentrantLock zzbbd;
    private final LinkedList<Map<String, Object>> zzbbe;
    private final zzc zzbbf;
    private final CountDownLatch zzbbg;

    static final class zza {
        public final String mKey;
        public final Object mValue;

        zza(String str, Object obj) {
            this.mKey = str;
            this.mValue = obj;
        }

        public final String toString() {
            String str = this.mKey;
            String obj = this.mValue.toString();
            StringBuilder stringBuilder = new StringBuilder((13 + String.valueOf(str).length()) + String.valueOf(obj).length());
            stringBuilder.append("Key: ");
            stringBuilder.append(str);
            stringBuilder.append(" value: ");
            stringBuilder.append(obj);
            return stringBuilder.toString();
        }

        public final int hashCode() {
            return Arrays.hashCode(new Integer[]{Integer.valueOf(this.mKey.hashCode()), Integer.valueOf(this.mValue.hashCode())});
        }

        public final boolean equals(Object obj) {
            if (!(obj instanceof zza)) {
                return false;
            }
            zza zza = (zza) obj;
            if (this.mKey.equals(zza.mKey) && this.mValue.equals(zza.mValue)) {
                return true;
            }
            return false;
        }
    }

    interface zzb {
        void zzd(Map<String, Object> map);
    }

    interface zzc {
        void zza(zzaq zzaq);

        void zza(List<zza> list, long j);

        void zzdj(String str);
    }

    @VisibleForTesting
    DataLayer() {
        this(new zzao());
    }

    DataLayer(zzc zzc) {
        this.zzbbf = zzc;
        this.zzbbb = new ConcurrentHashMap();
        this.zzbbc = new HashMap();
        this.zzbbd = new ReentrantLock();
        this.zzbbe = new LinkedList();
        this.zzbbg = new CountDownLatch(1);
        this.zzbbf.zza(new zzap(this));
    }

    public String toString() {
        String stringBuilder;
        synchronized (this.zzbbc) {
            StringBuilder stringBuilder2 = new StringBuilder();
            for (Entry entry : this.zzbbc.entrySet()) {
                stringBuilder2.append(String.format("{\n\tKey: %s\n\tValue: %s\n}\n", new Object[]{entry.getKey(), entry.getValue()}));
            }
            stringBuilder = stringBuilder2.toString();
        }
        return stringBuilder;
    }

    public void pushEvent(String str, Map<String, Object> map) {
        HashMap hashMap = new HashMap(map);
        hashMap.put("event", str);
        push(hashMap);
    }

    public void push(String str, Object obj) {
        push(zzk(str, obj));
    }

    public void push(Map<String, Object> map) {
        try {
            this.zzbbg.await();
        } catch (InterruptedException unused) {
            zzdi.zzab("DataLayer.push: unexpected InterruptedException");
        }
        zzf(map);
    }

    private final void zzf(Map<String, Object> map) {
        this.zzbbd.lock();
        try {
            Long l;
            this.zzbbe.offer(map);
            int i = 0;
            if (this.zzbbd.getHoldCount() == 1) {
                int i2 = 0;
                do {
                    Map map2 = (Map) this.zzbbe.poll();
                    if (map2 != null) {
                        synchronized (this.zzbbc) {
                            for (String str : map2.keySet()) {
                                zzb(zzk(str, map2.get(str)), this.zzbbc);
                            }
                        }
                        for (zzb zzd : this.zzbbb.keySet()) {
                            zzd.zzd(map2);
                        }
                        i2++;
                    }
                } while (i2 <= 500);
                this.zzbbe.clear();
                throw new RuntimeException("Seems like an infinite loop of pushing to the data layer");
            }
            String[] strArr = zzbaz;
            int length = strArr.length;
            Object obj = map;
            while (true) {
                l = null;
                if (i >= length) {
                    break;
                }
                Object obj2 = strArr[i];
                if (!(obj instanceof Map)) {
                    obj = null;
                    break;
                } else {
                    obj = ((Map) obj).get(obj2);
                    i++;
                }
            }
            if (obj != null) {
                l = zzdi(obj.toString());
            }
            if (l != null) {
                ArrayList arrayList = new ArrayList();
                zza(map, "", arrayList);
                this.zzbbf.zza(arrayList, l.longValue());
            }
            this.zzbbd.unlock();
        } catch (Throwable th) {
            this.zzbbd.unlock();
        }
    }

    /* Access modifiers changed, original: final */
    public final void zzdh(String str) {
        push(str, null);
        this.zzbbf.zzdj(str);
    }

    private final void zza(Map<String, Object> map, String str, Collection<zza> collection) {
        for (Entry entry : map.entrySet()) {
            Object obj = str.length() == 0 ? "" : ".";
            String str2 = (String) entry.getKey();
            StringBuilder stringBuilder = new StringBuilder((String.valueOf(str).length() + String.valueOf(obj).length()) + String.valueOf(str2).length());
            stringBuilder.append(str);
            stringBuilder.append(obj);
            stringBuilder.append(str2);
            String stringBuilder2 = stringBuilder.toString();
            if (entry.getValue() instanceof Map) {
                zza((Map) entry.getValue(), stringBuilder2, collection);
            } else if (!stringBuilder2.equals("gtm.lifetime")) {
                collection.add(new zza(stringBuilder2, entry.getValue()));
            }
        }
    }

    @VisibleForTesting
    private static Long zzdi(String str) {
        Matcher matcher = zzbba.matcher(str);
        String str2;
        if (matcher.matches()) {
            long parseLong;
            try {
                parseLong = Long.parseLong(matcher.group(1));
            } catch (NumberFormatException unused) {
                String str3 = "illegal number in _lifetime value: ";
                String valueOf = String.valueOf(str);
                zzdi.zzab(valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3));
                parseLong = 0;
            }
            if (parseLong <= 0) {
                str2 = "non-positive _lifetime: ";
                str = String.valueOf(str);
                zzdi.zzdm(str.length() != 0 ? str2.concat(str) : new String(str2));
                return null;
            }
            str2 = matcher.group(2);
            if (str2.length() == 0) {
                return Long.valueOf(parseLong);
            }
            char charAt = str2.charAt(0);
            if (charAt == 'd') {
                return Long.valueOf((((parseLong * 1000) * 60) * 60) * 24);
            }
            if (charAt == 'h') {
                return Long.valueOf(((parseLong * 1000) * 60) * 60);
            }
            if (charAt == 'm') {
                return Long.valueOf((parseLong * 1000) * 60);
            }
            if (charAt == 's') {
                return Long.valueOf(parseLong * 1000);
            }
            str2 = "unknown units in _lifetime: ";
            str = String.valueOf(str);
            zzdi.zzab(str.length() != 0 ? str2.concat(str) : new String(str2));
            return null;
        }
        str2 = "unknown _lifetime: ";
        str = String.valueOf(str);
        zzdi.zzdm(str.length() != 0 ? str2.concat(str) : new String(str2));
        return null;
    }

    public Object get(String str) {
        synchronized (this.zzbbc) {
            Object obj = this.zzbbc;
            String[] split = str.split("\\.");
            int length = split.length;
            int i = 0;
            while (i < length) {
                Object obj2 = split[i];
                if (obj instanceof Map) {
                    obj = ((Map) obj).get(obj2);
                    if (obj == null) {
                        return null;
                    }
                    i++;
                } else {
                    return null;
                }
            }
            return obj;
        }
    }

    @VisibleForTesting
    public static Map<String, Object> mapOf(Object... objArr) {
        if (objArr.length % 2 != 0) {
            throw new IllegalArgumentException("expected even number of key-value pairs");
        }
        HashMap hashMap = new HashMap();
        int i = 0;
        while (i < objArr.length) {
            if (objArr[i] instanceof String) {
                hashMap.put((String) objArr[i], objArr[i + 1]);
                i += 2;
            } else {
                String valueOf = String.valueOf(objArr[i]);
                StringBuilder stringBuilder = new StringBuilder(21 + String.valueOf(valueOf).length());
                stringBuilder.append("key is not a string: ");
                stringBuilder.append(valueOf);
                throw new IllegalArgumentException(stringBuilder.toString());
            }
        }
        return hashMap;
    }

    @VisibleForTesting
    public static List<Object> listOf(Object... objArr) {
        ArrayList arrayList = new ArrayList();
        for (Object add : objArr) {
            arrayList.add(add);
        }
        return arrayList;
    }

    /* Access modifiers changed, original: final */
    public final void zza(zzb zzb) {
        this.zzbbb.put(zzb, Integer.valueOf(0));
    }

    static Map<String, Object> zzk(String str, Object obj) {
        HashMap hashMap = new HashMap();
        String[] split = str.toString().split("\\.");
        int i = 0;
        Map map = hashMap;
        while (i < split.length - 1) {
            HashMap hashMap2 = new HashMap();
            map.put(split[i], hashMap2);
            i++;
            Object map2 = hashMap2;
        }
        map2.put(split[split.length - 1], obj);
        return hashMap;
    }

    @VisibleForTesting
    private final void zzb(Map<String, Object> map, Map<String, Object> map2) {
        for (String str : map.keySet()) {
            Object obj = map.get(str);
            if (obj instanceof List) {
                if (!(map2.get(str) instanceof List)) {
                    map2.put(str, new ArrayList());
                }
                zza((List) obj, (List) map2.get(str));
            } else if (obj instanceof Map) {
                if (!(map2.get(str) instanceof Map)) {
                    map2.put(str, new HashMap());
                }
                zzb((Map) obj, (Map) map2.get(str));
            } else {
                map2.put(str, obj);
            }
        }
    }

    @VisibleForTesting
    private final void zza(List<Object> list, List<Object> list2) {
        while (list2.size() < list.size()) {
            list2.add(null);
        }
        for (int i = 0; i < list.size(); i++) {
            Object obj = list.get(i);
            if (obj instanceof List) {
                if (!(list2.get(i) instanceof List)) {
                    list2.set(i, new ArrayList());
                }
                zza((List) obj, (List) list2.get(i));
            } else if (obj instanceof Map) {
                if (!(list2.get(i) instanceof Map)) {
                    list2.set(i, new HashMap());
                }
                zzb((Map) obj, (Map) list2.get(i));
            } else if (obj != OBJECT_NOT_PRESENT) {
                list2.set(i, obj);
            }
        }
    }
}
