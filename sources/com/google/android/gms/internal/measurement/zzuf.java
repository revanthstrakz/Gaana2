package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

final class zzuf<FieldDescriptorType extends zzuh<FieldDescriptorType>> {
    private static final zzuf zzbvj = new zzuf(true);
    private boolean zzbpy;
    private final zzwo<FieldDescriptorType, Object> zzbvh = zzwo.zzbw(16);
    private boolean zzbvi = false;

    private zzuf() {
    }

    private zzuf(boolean z) {
        zzsw();
    }

    public static <T extends zzuh<T>> zzuf<T> zzvw() {
        return zzbvj;
    }

    /* Access modifiers changed, original: final */
    public final boolean isEmpty() {
        return this.zzbvh.isEmpty();
    }

    public final void zzsw() {
        if (!this.zzbpy) {
            this.zzbvh.zzsw();
            this.zzbpy = true;
        }
    }

    public final boolean isImmutable() {
        return this.zzbpy;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzuf)) {
            return false;
        }
        return this.zzbvh.equals(((zzuf) obj).zzbvh);
    }

    public final int hashCode() {
        return this.zzbvh.hashCode();
    }

    public final Iterator<Entry<FieldDescriptorType, Object>> iterator() {
        if (this.zzbvi) {
            return new zzvb(this.zzbvh.entrySet().iterator());
        }
        return this.zzbvh.entrySet().iterator();
    }

    /* Access modifiers changed, original: final */
    public final Iterator<Entry<FieldDescriptorType, Object>> descendingIterator() {
        if (this.zzbvi) {
            return new zzvb(this.zzbvh.zzye().iterator());
        }
        return this.zzbvh.zzye().iterator();
    }

    private final Object zza(FieldDescriptorType fieldDescriptorType) {
        Object obj = this.zzbvh.get(fieldDescriptorType);
        return obj instanceof zzuy ? zzuy.zzwz() : obj;
    }

    private final void zza(FieldDescriptorType fieldDescriptorType, Object obj) {
        if (!fieldDescriptorType.zzwb()) {
            zza(fieldDescriptorType.zzvz(), obj);
        } else if (obj instanceof List) {
            ArrayList arrayList = new ArrayList();
            arrayList.addAll((List) obj);
            ArrayList arrayList2 = arrayList;
            int size = arrayList2.size();
            int i = 0;
            while (i < size) {
                Object obj2 = arrayList2.get(i);
                i++;
                zza(fieldDescriptorType.zzvz(), obj2);
            }
            obj = arrayList;
        } else {
            throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
        }
        if (obj instanceof zzuy) {
            this.zzbvi = true;
        }
        this.zzbvh.put((Comparable) fieldDescriptorType, obj);
    }

    /* JADX WARNING: Missing block: B:5:0x001b, code skipped:
            if ((r3 instanceof com.google.android.gms.internal.measurement.zzuy) == false) goto L_0x0043;
     */
    /* JADX WARNING: Missing block: B:9:0x0024, code skipped:
            if ((r3 instanceof com.google.android.gms.internal.measurement.zzur) == false) goto L_0x0043;
     */
    /* JADX WARNING: Missing block: B:14:0x002e, code skipped:
            if ((r3 instanceof byte[]) == false) goto L_0x0043;
     */
    private static void zza(com.google.android.gms.internal.measurement.zzxs r2, java.lang.Object r3) {
        /*
        com.google.android.gms.internal.measurement.zzuq.checkNotNull(r3);
        r0 = com.google.android.gms.internal.measurement.zzug.zzbvk;
        r2 = r2.zzyv();
        r2 = r2.ordinal();
        r2 = r0[r2];
        r0 = 1;
        r1 = 0;
        switch(r2) {
            case 1: goto L_0x0040;
            case 2: goto L_0x003d;
            case 3: goto L_0x003a;
            case 4: goto L_0x0037;
            case 5: goto L_0x0034;
            case 6: goto L_0x0031;
            case 7: goto L_0x0028;
            case 8: goto L_0x001e;
            case 9: goto L_0x0015;
            default: goto L_0x0014;
        };
    L_0x0014:
        goto L_0x0043;
    L_0x0015:
        r2 = r3 instanceof com.google.android.gms.internal.measurement.zzvv;
        if (r2 != 0) goto L_0x0026;
    L_0x0019:
        r2 = r3 instanceof com.google.android.gms.internal.measurement.zzuy;
        if (r2 == 0) goto L_0x0043;
    L_0x001d:
        goto L_0x0026;
    L_0x001e:
        r2 = r3 instanceof java.lang.Integer;
        if (r2 != 0) goto L_0x0026;
    L_0x0022:
        r2 = r3 instanceof com.google.android.gms.internal.measurement.zzur;
        if (r2 == 0) goto L_0x0043;
    L_0x0026:
        r1 = r0;
        goto L_0x0043;
    L_0x0028:
        r2 = r3 instanceof com.google.android.gms.internal.measurement.zzte;
        if (r2 != 0) goto L_0x0026;
    L_0x002c:
        r2 = r3 instanceof byte[];
        if (r2 == 0) goto L_0x0043;
    L_0x0030:
        goto L_0x0026;
    L_0x0031:
        r0 = r3 instanceof java.lang.String;
        goto L_0x0026;
    L_0x0034:
        r0 = r3 instanceof java.lang.Boolean;
        goto L_0x0026;
    L_0x0037:
        r0 = r3 instanceof java.lang.Double;
        goto L_0x0026;
    L_0x003a:
        r0 = r3 instanceof java.lang.Float;
        goto L_0x0026;
    L_0x003d:
        r0 = r3 instanceof java.lang.Long;
        goto L_0x0026;
    L_0x0040:
        r0 = r3 instanceof java.lang.Integer;
        goto L_0x0026;
    L_0x0043:
        if (r1 != 0) goto L_0x004d;
    L_0x0045:
        r2 = new java.lang.IllegalArgumentException;
        r3 = "Wrong object type used with protocol message reflection.";
        r2.<init>(r3);
        throw r2;
    L_0x004d:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzuf.zza(com.google.android.gms.internal.measurement.zzxs, java.lang.Object):void");
    }

    public final boolean isInitialized() {
        for (int i = 0; i < this.zzbvh.zzyc(); i++) {
            if (!zzc(this.zzbvh.zzbx(i))) {
                return false;
            }
        }
        for (Entry zzc : this.zzbvh.zzyd()) {
            if (!zzc(zzc)) {
                return false;
            }
        }
        return true;
    }

    private static boolean zzc(Entry<FieldDescriptorType, Object> entry) {
        zzuh zzuh = (zzuh) entry.getKey();
        if (zzuh.zzwa() == zzxx.MESSAGE) {
            if (zzuh.zzwb()) {
                for (zzvv isInitialized : (List) entry.getValue()) {
                    if (!isInitialized.isInitialized()) {
                        return false;
                    }
                }
            }
            Object value = entry.getValue();
            if (value instanceof zzvv) {
                if (!((zzvv) value).isInitialized()) {
                    return false;
                }
            } else if (value instanceof zzuy) {
                return true;
            } else {
                throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
            }
        }
        return true;
    }

    public final void zza(zzuf<FieldDescriptorType> zzuf) {
        for (int i = 0; i < zzuf.zzbvh.zzyc(); i++) {
            zzd(zzuf.zzbvh.zzbx(i));
        }
        for (Entry zzd : zzuf.zzbvh.zzyd()) {
            zzd(zzd);
        }
    }

    private static Object zzz(Object obj) {
        if (obj instanceof zzwb) {
            return ((zzwb) obj).zzxp();
        }
        if (!(obj instanceof byte[])) {
            return obj;
        }
        byte[] bArr = (byte[]) obj;
        byte[] bArr2 = new byte[bArr.length];
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        return bArr2;
    }

    private final void zzd(Entry<FieldDescriptorType, Object> entry) {
        Comparable comparable = (zzuh) entry.getKey();
        Object value = entry.getValue();
        if (value instanceof zzuy) {
            value = zzuy.zzwz();
        }
        Object zza;
        if (comparable.zzwb()) {
            zza = zza((zzuh) comparable);
            if (zza == null) {
                zza = new ArrayList();
            }
            for (Object zzz : (List) value) {
                ((List) zza).add(zzz(zzz));
            }
            this.zzbvh.put(comparable, zza);
        } else if (comparable.zzwa() == zzxx.MESSAGE) {
            zza = zza((zzuh) comparable);
            if (zza == null) {
                this.zzbvh.put(comparable, zzz(value));
                return;
            }
            if (zza instanceof zzwb) {
                value = comparable.zza((zzwb) zza, (zzwb) value);
            } else {
                value = comparable.zza(((zzvv) zza).zzwh(), (zzvv) value).zzwo();
            }
            this.zzbvh.put(comparable, value);
        } else {
            this.zzbvh.put(comparable, zzz(value));
        }
    }

    static void zza(zztv zztv, zzxs zzxs, int i, Object obj) throws IOException {
        if (zzxs == zzxs.GROUP) {
            zzvv zzvv = (zzvv) obj;
            zzuq.zzf(zzvv);
            zztv.zzc(i, 3);
            zzvv.zzb(zztv);
            zztv.zzc(i, 4);
            return;
        }
        zztv.zzc(i, zzxs.zzyw());
        switch (zzxs) {
            case DOUBLE:
                zztv.zzb(((Double) obj).doubleValue());
                return;
            case FLOAT:
                zztv.zza(((Float) obj).floatValue());
                return;
            case INT64:
                zztv.zzat(((Long) obj).longValue());
                return;
            case UINT64:
                zztv.zzat(((Long) obj).longValue());
                return;
            case INT32:
                zztv.zzaz(((Integer) obj).intValue());
                return;
            case FIXED64:
                zztv.zzav(((Long) obj).longValue());
                return;
            case FIXED32:
                zztv.zzbc(((Integer) obj).intValue());
                return;
            case BOOL:
                zztv.zzs(((Boolean) obj).booleanValue());
                return;
            case GROUP:
                ((zzvv) obj).zzb(zztv);
                return;
            case MESSAGE:
                zztv.zzb((zzvv) obj);
                return;
            case STRING:
                if (obj instanceof zzte) {
                    zztv.zza((zzte) obj);
                    return;
                } else {
                    zztv.zzgb((String) obj);
                    return;
                }
            case BYTES:
                if (obj instanceof zzte) {
                    zztv.zza((zzte) obj);
                    return;
                }
                byte[] bArr = (byte[]) obj;
                zztv.zze(bArr, 0, bArr.length);
                return;
            case UINT32:
                zztv.zzba(((Integer) obj).intValue());
                return;
            case SFIXED32:
                zztv.zzbc(((Integer) obj).intValue());
                return;
            case SFIXED64:
                zztv.zzav(((Long) obj).longValue());
                return;
            case SINT32:
                zztv.zzbb(((Integer) obj).intValue());
                return;
            case SINT64:
                zztv.zzau(((Long) obj).longValue());
                return;
            case ENUM:
                if (!(obj instanceof zzur)) {
                    zztv.zzaz(((Integer) obj).intValue());
                    break;
                } else {
                    zztv.zzaz(((zzur) obj).zzc());
                    return;
                }
        }
    }

    public final int zzvx() {
        Entry zzbx;
        int i = 0;
        int i2 = 0;
        while (i < this.zzbvh.zzyc()) {
            zzbx = this.zzbvh.zzbx(i);
            i2 += zzb((zzuh) zzbx.getKey(), zzbx.getValue());
            i++;
        }
        for (Entry zzbx2 : this.zzbvh.zzyd()) {
            i2 += zzb((zzuh) zzbx2.getKey(), zzbx2.getValue());
        }
        return i2;
    }

    public final int zzvy() {
        int i = 0;
        int i2 = 0;
        while (i < this.zzbvh.zzyc()) {
            i2 += zze(this.zzbvh.zzbx(i));
            i++;
        }
        for (Entry zze : this.zzbvh.zzyd()) {
            i2 += zze(zze);
        }
        return i2;
    }

    private static int zze(Entry<FieldDescriptorType, Object> entry) {
        zzuh zzuh = (zzuh) entry.getKey();
        Object value = entry.getValue();
        if (zzuh.zzwa() != zzxx.MESSAGE || zzuh.zzwb() || zzuh.zzwc()) {
            return zzb(zzuh, value);
        }
        if (value instanceof zzuy) {
            return zztv.zzb(((zzuh) entry.getKey()).zzc(), (zzuy) value);
        }
        return zztv.zzd(((zzuh) entry.getKey()).zzc(), (zzvv) value);
    }

    static int zza(zzxs zzxs, int i, Object obj) {
        i = zztv.zzbd(i);
        if (zzxs == zzxs.GROUP) {
            zzuq.zzf((zzvv) obj);
            i <<= 1;
        }
        return i + zzb(zzxs, obj);
    }

    private static int zzb(zzxs zzxs, Object obj) {
        switch (zzxs) {
            case DOUBLE:
                return zztv.zzc(((Double) obj).doubleValue());
            case FLOAT:
                return zztv.zzb(((Float) obj).floatValue());
            case INT64:
                return zztv.zzaw(((Long) obj).longValue());
            case UINT64:
                return zztv.zzax(((Long) obj).longValue());
            case INT32:
                return zztv.zzbe(((Integer) obj).intValue());
            case FIXED64:
                return zztv.zzaz(((Long) obj).longValue());
            case FIXED32:
                return zztv.zzbh(((Integer) obj).intValue());
            case BOOL:
                return zztv.zzt(((Boolean) obj).booleanValue());
            case GROUP:
                return zztv.zzd((zzvv) obj);
            case MESSAGE:
                if (obj instanceof zzuy) {
                    return zztv.zza((zzuy) obj);
                }
                return zztv.zzc((zzvv) obj);
            case STRING:
                if (obj instanceof zzte) {
                    return zztv.zzb((zzte) obj);
                }
                return zztv.zzgc((String) obj);
            case BYTES:
                if (obj instanceof zzte) {
                    return zztv.zzb((zzte) obj);
                }
                return zztv.zzk((byte[]) obj);
            case UINT32:
                return zztv.zzbf(((Integer) obj).intValue());
            case SFIXED32:
                return zztv.zzbi(((Integer) obj).intValue());
            case SFIXED64:
                return zztv.zzba(((Long) obj).longValue());
            case SINT32:
                return zztv.zzbg(((Integer) obj).intValue());
            case SINT64:
                return zztv.zzay(((Long) obj).longValue());
            case ENUM:
                if (obj instanceof zzur) {
                    return zztv.zzbj(((zzur) obj).zzc());
                }
                return zztv.zzbj(((Integer) obj).intValue());
            default:
                throw new RuntimeException("There is no way to get here, but the compiler thinks otherwise.");
        }
    }

    private static int zzb(zzuh<?> zzuh, Object obj) {
        zzxs zzvz = zzuh.zzvz();
        int zzc = zzuh.zzc();
        if (!zzuh.zzwb()) {
            return zza(zzvz, zzc, obj);
        }
        int i = 0;
        if (zzuh.zzwc()) {
            for (Object obj2 : (List) obj2) {
                i += zzb(zzvz, obj2);
            }
            return (zztv.zzbd(zzc) + i) + zztv.zzbl(i);
        }
        for (Object obj22 : (List) obj22) {
            i += zza(zzvz, zzc, obj22);
        }
        return i;
    }

    public final /* synthetic */ Object clone() throws CloneNotSupportedException {
        Entry zzbx;
        zzuf zzuf = new zzuf();
        for (int i = 0; i < this.zzbvh.zzyc(); i++) {
            zzbx = this.zzbvh.zzbx(i);
            zzuf.zza((zzuh) zzbx.getKey(), zzbx.getValue());
        }
        for (Entry zzbx2 : this.zzbvh.zzyd()) {
            zzuf.zza((zzuh) zzbx2.getKey(), zzbx2.getValue());
        }
        zzuf.zzbvi = this.zzbvi;
        return zzuf;
    }
}
