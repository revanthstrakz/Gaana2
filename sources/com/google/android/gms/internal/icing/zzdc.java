package com.google.android.gms.internal.icing;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

final class zzdc<FieldDescriptorType extends zzde<FieldDescriptorType>> {
    private static final zzdc zzhb = new zzdc(true);
    private final zzfi<FieldDescriptorType, Object> zzgy = zzfi.zzai(16);
    private boolean zzgz;
    private boolean zzha = false;

    private zzdc() {
    }

    private zzdc(boolean z) {
        zzaj();
    }

    public static <T extends zzde<T>> zzdc<T> zzbh() {
        return zzhb;
    }

    /* Access modifiers changed, original: final */
    public final boolean isEmpty() {
        return this.zzgy.isEmpty();
    }

    public final void zzaj() {
        if (!this.zzgz) {
            this.zzgy.zzaj();
            this.zzgz = true;
        }
    }

    public final boolean isImmutable() {
        return this.zzgz;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzdc)) {
            return false;
        }
        return this.zzgy.equals(((zzdc) obj).zzgy);
    }

    public final int hashCode() {
        return this.zzgy.hashCode();
    }

    public final Iterator<Entry<FieldDescriptorType, Object>> iterator() {
        if (this.zzha) {
            return new zzdx(this.zzgy.entrySet().iterator());
        }
        return this.zzgy.entrySet().iterator();
    }

    /* Access modifiers changed, original: final */
    public final Iterator<Entry<FieldDescriptorType, Object>> descendingIterator() {
        if (this.zzha) {
            return new zzdx(this.zzgy.zzdj().iterator());
        }
        return this.zzgy.zzdj().iterator();
    }

    private final Object zza(FieldDescriptorType fieldDescriptorType) {
        Object obj = this.zzgy.get(fieldDescriptorType);
        return obj instanceof zzdu ? zzdu.zzce() : obj;
    }

    private final void zza(FieldDescriptorType fieldDescriptorType, Object obj) {
        if (!fieldDescriptorType.zzbn()) {
            zza(fieldDescriptorType.zzbl(), obj);
        } else if (obj instanceof List) {
            ArrayList arrayList = new ArrayList();
            arrayList.addAll((List) obj);
            ArrayList arrayList2 = arrayList;
            int size = arrayList2.size();
            int i = 0;
            while (i < size) {
                Object obj2 = arrayList2.get(i);
                i++;
                zza(fieldDescriptorType.zzbl(), obj2);
            }
            obj = arrayList;
        } else {
            throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
        }
        if (obj instanceof zzdu) {
            this.zzha = true;
        }
        this.zzgy.put((Comparable) fieldDescriptorType, obj);
    }

    /* JADX WARNING: Missing block: B:5:0x001b, code skipped:
            if ((r3 instanceof com.google.android.gms.internal.icing.zzdu) == false) goto L_0x0043;
     */
    /* JADX WARNING: Missing block: B:9:0x0024, code skipped:
            if ((r3 instanceof com.google.android.gms.internal.icing.zzdo) == false) goto L_0x0043;
     */
    /* JADX WARNING: Missing block: B:14:0x002e, code skipped:
            if ((r3 instanceof byte[]) == false) goto L_0x0043;
     */
    private static void zza(com.google.android.gms.internal.icing.zzgl r2, java.lang.Object r3) {
        /*
        com.google.android.gms.internal.icing.zzdl.checkNotNull(r3);
        r0 = com.google.android.gms.internal.icing.zzdd.zzhc;
        r2 = r2.zzdx();
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
        r2 = r3 instanceof com.google.android.gms.internal.icing.zzeq;
        if (r2 != 0) goto L_0x0026;
    L_0x0019:
        r2 = r3 instanceof com.google.android.gms.internal.icing.zzdu;
        if (r2 == 0) goto L_0x0043;
    L_0x001d:
        goto L_0x0026;
    L_0x001e:
        r2 = r3 instanceof java.lang.Integer;
        if (r2 != 0) goto L_0x0026;
    L_0x0022:
        r2 = r3 instanceof com.google.android.gms.internal.icing.zzdo;
        if (r2 == 0) goto L_0x0043;
    L_0x0026:
        r1 = r0;
        goto L_0x0043;
    L_0x0028:
        r2 = r3 instanceof com.google.android.gms.internal.icing.zzce;
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
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.icing.zzdc.zza(com.google.android.gms.internal.icing.zzgl, java.lang.Object):void");
    }

    public final boolean isInitialized() {
        for (int i = 0; i < this.zzgy.zzdh(); i++) {
            if (!zzb(this.zzgy.zzaj(i))) {
                return false;
            }
        }
        for (Entry zzb : this.zzgy.zzdi()) {
            if (!zzb(zzb)) {
                return false;
            }
        }
        return true;
    }

    private static boolean zzb(Entry<FieldDescriptorType, Object> entry) {
        zzde zzde = (zzde) entry.getKey();
        if (zzde.zzbm() == zzgq.MESSAGE) {
            if (zzde.zzbn()) {
                for (zzeq isInitialized : (List) entry.getValue()) {
                    if (!isInitialized.isInitialized()) {
                        return false;
                    }
                }
            }
            Object value = entry.getValue();
            if (value instanceof zzeq) {
                if (!((zzeq) value).isInitialized()) {
                    return false;
                }
            } else if (value instanceof zzdu) {
                return true;
            } else {
                throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
            }
        }
        return true;
    }

    public final void zza(zzdc<FieldDescriptorType> zzdc) {
        for (int i = 0; i < zzdc.zzgy.zzdh(); i++) {
            zzc(zzdc.zzgy.zzaj(i));
        }
        for (Entry zzc : zzdc.zzgy.zzdi()) {
            zzc(zzc);
        }
    }

    private static Object zzf(Object obj) {
        if (obj instanceof zzew) {
            return ((zzew) obj).zzcu();
        }
        if (!(obj instanceof byte[])) {
            return obj;
        }
        byte[] bArr = (byte[]) obj;
        byte[] bArr2 = new byte[bArr.length];
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        return bArr2;
    }

    private final void zzc(Entry<FieldDescriptorType, Object> entry) {
        Comparable comparable = (zzde) entry.getKey();
        Object value = entry.getValue();
        if (value instanceof zzdu) {
            value = zzdu.zzce();
        }
        Object zza;
        if (comparable.zzbn()) {
            zza = zza((zzde) comparable);
            if (zza == null) {
                zza = new ArrayList();
            }
            for (Object zzf : (List) value) {
                ((List) zza).add(zzf(zzf));
            }
            this.zzgy.put(comparable, zza);
        } else if (comparable.zzbm() == zzgq.MESSAGE) {
            zza = zza((zzde) comparable);
            if (zza == null) {
                this.zzgy.put(comparable, zzf(value));
                return;
            }
            if (zza instanceof zzew) {
                value = comparable.zza((zzew) zza, (zzew) value);
            } else {
                value = comparable.zza(((zzeq) zza).zzbu(), (zzeq) value).zzca();
            }
            this.zzgy.put(comparable, value);
        } else {
            this.zzgy.put(comparable, zzf(value));
        }
    }

    static void zza(zzct zzct, zzgl zzgl, int i, Object obj) throws IOException {
        if (zzgl == zzgl.GROUP) {
            zzeq zzeq = (zzeq) obj;
            zzdl.zzf(zzeq);
            zzct.zzb(i, 3);
            zzeq.zzb(zzct);
            zzct.zzb(i, 4);
            return;
        }
        zzct.zzb(i, zzgl.zzdy());
        switch (zzgl) {
            case DOUBLE:
                zzct.zza(((Double) obj).doubleValue());
                return;
            case FLOAT:
                zzct.zza(((Float) obj).floatValue());
                return;
            case INT64:
                zzct.zzb(((Long) obj).longValue());
                return;
            case UINT64:
                zzct.zzb(((Long) obj).longValue());
                return;
            case INT32:
                zzct.zzo(((Integer) obj).intValue());
                return;
            case FIXED64:
                zzct.zzd(((Long) obj).longValue());
                return;
            case FIXED32:
                zzct.zzr(((Integer) obj).intValue());
                return;
            case BOOL:
                zzct.zzg(((Boolean) obj).booleanValue());
                return;
            case GROUP:
                ((zzeq) obj).zzb(zzct);
                return;
            case MESSAGE:
                zzct.zzb((zzeq) obj);
                return;
            case STRING:
                if (obj instanceof zzce) {
                    zzct.zza((zzce) obj);
                    return;
                } else {
                    zzct.zzu((String) obj);
                    return;
                }
            case BYTES:
                if (obj instanceof zzce) {
                    zzct.zza((zzce) obj);
                    return;
                }
                byte[] bArr = (byte[]) obj;
                zzct.zzb(bArr, 0, bArr.length);
                return;
            case UINT32:
                zzct.zzp(((Integer) obj).intValue());
                return;
            case SFIXED32:
                zzct.zzr(((Integer) obj).intValue());
                return;
            case SFIXED64:
                zzct.zzd(((Long) obj).longValue());
                return;
            case SINT32:
                zzct.zzq(((Integer) obj).intValue());
                return;
            case SINT64:
                zzct.zzc(((Long) obj).longValue());
                return;
            case ENUM:
                if (!(obj instanceof zzdo)) {
                    zzct.zzo(((Integer) obj).intValue());
                    break;
                } else {
                    zzct.zzo(((zzdo) obj).zzbk());
                    return;
                }
        }
    }

    public final int zzbi() {
        Entry zzaj;
        int i = 0;
        int i2 = 0;
        while (i < this.zzgy.zzdh()) {
            zzaj = this.zzgy.zzaj(i);
            i2 += zzb((zzde) zzaj.getKey(), zzaj.getValue());
            i++;
        }
        for (Entry zzaj2 : this.zzgy.zzdi()) {
            i2 += zzb((zzde) zzaj2.getKey(), zzaj2.getValue());
        }
        return i2;
    }

    public final int zzbj() {
        int i = 0;
        int i2 = 0;
        while (i < this.zzgy.zzdh()) {
            i2 += zzd(this.zzgy.zzaj(i));
            i++;
        }
        for (Entry zzd : this.zzgy.zzdi()) {
            i2 += zzd(zzd);
        }
        return i2;
    }

    private static int zzd(Entry<FieldDescriptorType, Object> entry) {
        zzde zzde = (zzde) entry.getKey();
        Object value = entry.getValue();
        if (zzde.zzbm() != zzgq.MESSAGE || zzde.zzbn() || zzde.zzbo()) {
            return zzb(zzde, value);
        }
        if (value instanceof zzdu) {
            return zzct.zzb(((zzde) entry.getKey()).zzbk(), (zzdu) value);
        }
        return zzct.zzb(((zzde) entry.getKey()).zzbk(), (zzeq) value);
    }

    static int zza(zzgl zzgl, int i, Object obj) {
        i = zzct.zzs(i);
        if (zzgl == zzgl.GROUP) {
            zzdl.zzf((zzeq) obj);
            i <<= 1;
        }
        return i + zzb(zzgl, obj);
    }

    private static int zzb(zzgl zzgl, Object obj) {
        switch (zzgl) {
            case DOUBLE:
                return zzct.zzb(((Double) obj).doubleValue());
            case FLOAT:
                return zzct.zzb(((Float) obj).floatValue());
            case INT64:
                return zzct.zze(((Long) obj).longValue());
            case UINT64:
                return zzct.zzf(((Long) obj).longValue());
            case INT32:
                return zzct.zzt(((Integer) obj).intValue());
            case FIXED64:
                return zzct.zzh(((Long) obj).longValue());
            case FIXED32:
                return zzct.zzw(((Integer) obj).intValue());
            case BOOL:
                return zzct.zzh(((Boolean) obj).booleanValue());
            case GROUP:
                return zzct.zzd((zzeq) obj);
            case MESSAGE:
                if (obj instanceof zzdu) {
                    return zzct.zza((zzdu) obj);
                }
                return zzct.zzc((zzeq) obj);
            case STRING:
                if (obj instanceof zzce) {
                    return zzct.zzb((zzce) obj);
                }
                return zzct.zzv((String) obj);
            case BYTES:
                if (obj instanceof zzce) {
                    return zzct.zzb((zzce) obj);
                }
                return zzct.zzc((byte[]) obj);
            case UINT32:
                return zzct.zzu(((Integer) obj).intValue());
            case SFIXED32:
                return zzct.zzx(((Integer) obj).intValue());
            case SFIXED64:
                return zzct.zzi(((Long) obj).longValue());
            case SINT32:
                return zzct.zzv(((Integer) obj).intValue());
            case SINT64:
                return zzct.zzg(((Long) obj).longValue());
            case ENUM:
                if (obj instanceof zzdo) {
                    return zzct.zzy(((zzdo) obj).zzbk());
                }
                return zzct.zzy(((Integer) obj).intValue());
            default:
                throw new RuntimeException("There is no way to get here, but the compiler thinks otherwise.");
        }
    }

    private static int zzb(zzde<?> zzde, Object obj) {
        zzgl zzbl = zzde.zzbl();
        int zzbk = zzde.zzbk();
        if (!zzde.zzbn()) {
            return zza(zzbl, zzbk, obj);
        }
        int i = 0;
        if (zzde.zzbo()) {
            for (Object obj2 : (List) obj2) {
                i += zzb(zzbl, obj2);
            }
            return (zzct.zzs(zzbk) + i) + zzct.zzaa(i);
        }
        for (Object obj22 : (List) obj22) {
            i += zza(zzbl, zzbk, obj22);
        }
        return i;
    }

    public final /* synthetic */ Object clone() throws CloneNotSupportedException {
        Entry zzaj;
        zzdc zzdc = new zzdc();
        for (int i = 0; i < this.zzgy.zzdh(); i++) {
            zzaj = this.zzgy.zzaj(i);
            zzdc.zza((zzde) zzaj.getKey(), zzaj.getValue());
        }
        for (Entry zzaj2 : this.zzgy.zzdi()) {
            zzdc.zza((zzde) zzaj2.getKey(), zzaj2.getValue());
        }
        zzdc.zzha = this.zzha;
        return zzdc;
    }
}
