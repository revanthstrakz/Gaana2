package com.google.android.gms.tagmanager;

import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.measurement.zzp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

@VisibleForTesting
public final class zzgj {
    private static final Object zzbgy = null;
    private static Long zzbgz = new Long(0);
    private static Double zzbha = new Double(0.0d);
    private static zzgi zzbhb = zzgi.zzaq(0);
    private static String zzbhc = new String("");
    private static Boolean zzbhd = new Boolean(false);
    private static List<Object> zzbhe = new ArrayList(0);
    private static Map<Object, Object> zzbhf = new HashMap();
    private static zzp zzbhg = zzj(zzbhc);

    public static Object zzqk() {
        return null;
    }

    public static Long zzql() {
        return zzbgz;
    }

    public static Double zzqm() {
        return zzbha;
    }

    public static Boolean zzqn() {
        return zzbhd;
    }

    public static zzgi zzqo() {
        return zzbhb;
    }

    public static String zzqp() {
        return zzbhc;
    }

    public static zzp zzqq() {
        return zzbhg;
    }

    private static String zzi(Object obj) {
        return obj == null ? zzbhc : obj.toString();
    }

    public static String zzc(zzp zzp) {
        return zzi(zzh(zzp));
    }

    public static zzgi zzd(zzp zzp) {
        Object zzh = zzh(zzp);
        if (zzh instanceof zzgi) {
            return (zzgi) zzh;
        }
        if (zzl(zzh)) {
            return zzgi.zzaq(zzm(zzh));
        }
        if (zzk(zzh)) {
            return zzgi.zza(Double.valueOf(getDouble(zzh)));
        }
        return zzeg(zzi(zzh));
    }

    public static Long zze(zzp zzp) {
        Object zzh = zzh(zzp);
        if (zzl(zzh)) {
            return Long.valueOf(zzm(zzh));
        }
        zzgi zzeg = zzeg(zzi(zzh));
        return zzeg == zzbhb ? zzbgz : Long.valueOf(zzeg.longValue());
    }

    public static Double zzf(zzp zzp) {
        Object zzh = zzh(zzp);
        if (zzk(zzh)) {
            return Double.valueOf(getDouble(zzh));
        }
        zzgi zzeg = zzeg(zzi(zzh));
        return zzeg == zzbhb ? zzbha : Double.valueOf(zzeg.doubleValue());
    }

    public static Boolean zzg(zzp zzp) {
        Object zzh = zzh(zzp);
        if (zzh instanceof Boolean) {
            return (Boolean) zzh;
        }
        String zzi = zzi(zzh);
        if ("true".equalsIgnoreCase(zzi)) {
            return Boolean.TRUE;
        }
        if (InternalLogger.EVENT_PARAM_EXTRAS_FALSE.equalsIgnoreCase(zzi)) {
            return Boolean.FALSE;
        }
        return zzbhd;
    }

    public static zzp zzj(Object obj) {
        zzp zzp = new zzp();
        if (obj instanceof zzp) {
            return (zzp) obj;
        }
        boolean z = false;
        ArrayList arrayList;
        if (obj instanceof String) {
            zzp.type = 1;
            zzp.string = (String) obj;
        } else if (obj instanceof List) {
            zzp.type = 2;
            List<Object> list = (List) obj;
            arrayList = new ArrayList(list.size());
            boolean z2 = false;
            for (Object zzj : list) {
                zzp zzj2 = zzj(zzj);
                if (zzj2 == zzbhg) {
                    return zzbhg;
                }
                z2 = z2 || zzj2.zzqs;
                arrayList.add(zzj2);
            }
            zzp.zzqj = (zzp[]) arrayList.toArray(new zzp[0]);
            z = z2;
        } else if (obj instanceof Map) {
            zzp.type = 3;
            Set<Entry> entrySet = ((Map) obj).entrySet();
            arrayList = new ArrayList(entrySet.size());
            ArrayList arrayList2 = new ArrayList(entrySet.size());
            boolean z3 = false;
            for (Entry entry : entrySet) {
                zzp zzj3 = zzj(entry.getKey());
                zzp zzj4 = zzj(entry.getValue());
                if (zzj3 == zzbhg || zzj4 == zzbhg) {
                    return zzbhg;
                }
                z3 = z3 || zzj3.zzqs || zzj4.zzqs;
                arrayList.add(zzj3);
                arrayList2.add(zzj4);
            }
            zzp.zzqk = (zzp[]) arrayList.toArray(new zzp[0]);
            zzp.zzql = (zzp[]) arrayList2.toArray(new zzp[0]);
            z = z3;
        } else if (zzk(obj)) {
            zzp.type = 1;
            zzp.string = obj.toString();
        } else if (zzl(obj)) {
            zzp.type = 6;
            zzp.zzqo = zzm(obj);
        } else if (obj instanceof Boolean) {
            zzp.type = 8;
            zzp.zzqp = ((Boolean) obj).booleanValue();
        } else {
            String str = "Converting to Value from unknown object type: ";
            if (obj == null) {
                obj = "null";
            } else {
                obj = obj.getClass().toString();
            }
            String valueOf = String.valueOf(obj);
            zzdi.e(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
            return zzbhg;
        }
        zzp.zzqs = z;
        return zzp;
    }

    public static zzp zzef(String str) {
        zzp zzp = new zzp();
        zzp.type = 5;
        zzp.zzqn = str;
        return zzp;
    }

    private static boolean zzk(Object obj) {
        return (obj instanceof Double) || (obj instanceof Float) || ((obj instanceof zzgi) && ((zzgi) obj).zzqi());
    }

    private static double getDouble(Object obj) {
        if (obj instanceof Number) {
            return ((Number) obj).doubleValue();
        }
        zzdi.e("getDouble received non-Number");
        return 0.0d;
    }

    private static boolean zzl(Object obj) {
        return (obj instanceof Byte) || (obj instanceof Short) || (obj instanceof Integer) || (obj instanceof Long) || ((obj instanceof zzgi) && ((zzgi) obj).zzqj());
    }

    private static long zzm(Object obj) {
        if (obj instanceof Number) {
            return ((Number) obj).longValue();
        }
        zzdi.e("getInt64 received non-Number");
        return 0;
    }

    private static zzgi zzeg(String str) {
        try {
            return zzgi.zzee(str);
        } catch (NumberFormatException unused) {
            StringBuilder stringBuilder = new StringBuilder(33 + String.valueOf(str).length());
            stringBuilder.append("Failed to convert '");
            stringBuilder.append(str);
            stringBuilder.append("' to a number.");
            zzdi.e(stringBuilder.toString());
            return zzbhb;
        }
    }

    public static Object zzh(zzp zzp) {
        if (zzp == null) {
            return null;
        }
        int i = 0;
        zzp[] zzpArr;
        int length;
        Object zzh;
        switch (zzp.type) {
            case 1:
                return zzp.string;
            case 2:
                ArrayList arrayList = new ArrayList(zzp.zzqj.length);
                zzpArr = zzp.zzqj;
                length = zzpArr.length;
                while (i < length) {
                    zzh = zzh(zzpArr[i]);
                    if (zzh == null) {
                        return null;
                    }
                    arrayList.add(zzh);
                    i++;
                }
                return arrayList;
            case 3:
                if (zzp.zzqk.length != zzp.zzql.length) {
                    String str = "Converting an invalid value to object: ";
                    String valueOf = String.valueOf(zzp.toString());
                    zzdi.e(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
                    return null;
                }
                HashMap hashMap = new HashMap(zzp.zzql.length);
                while (i < zzp.zzqk.length) {
                    Object zzh2 = zzh(zzp.zzqk[i]);
                    zzh = zzh(zzp.zzql[i]);
                    if (zzh2 == null || zzh == null) {
                        return null;
                    }
                    hashMap.put(zzh2, zzh);
                    i++;
                }
                return hashMap;
            case 4:
                zzdi.e("Trying to convert a macro reference to object");
                return null;
            case 5:
                zzdi.e("Trying to convert a function id to object");
                return null;
            case 6:
                return Long.valueOf(zzp.zzqo);
            case 7:
                StringBuilder stringBuilder = new StringBuilder();
                zzpArr = zzp.zzqq;
                length = zzpArr.length;
                while (i < length) {
                    String zzi = zzi(zzh(zzpArr[i]));
                    if (zzi == zzbhc) {
                        return null;
                    }
                    stringBuilder.append(zzi);
                    i++;
                }
                return stringBuilder.toString();
            case 8:
                return Boolean.valueOf(zzp.zzqp);
            default:
                int i2 = zzp.type;
                StringBuilder stringBuilder2 = new StringBuilder(46);
                stringBuilder2.append("Failed to convert a value of type: ");
                stringBuilder2.append(i2);
                zzdi.e(stringBuilder2.toString());
                return null;
        }
    }
}
