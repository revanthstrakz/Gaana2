package com.google.android.gms.internal.clearcut;

import java.lang.reflect.Field;
import java.util.Arrays;

final class zzed {
    private final int flags;
    private final Object[] zzmj;
    private final int zzmk;
    private final int zzml;
    private final int zzmm;
    private final int[] zzms;
    private final zzee zznh;
    private Class<?> zzni;
    private final int zznj;
    private final int zznk;
    private final int zznl;
    private final int zznm;
    private final int zznn;
    private final int zzno;
    private int zznp;
    private int zznq;
    private int zznr = Integer.MAX_VALUE;
    private int zzns = Integer.MIN_VALUE;
    private int zznt = 0;
    private int zznu = 0;
    private int zznv = 0;
    private int zznw = 0;
    private int zznx = 0;
    private int zzny;
    private int zznz;
    private int zzoa;
    private int zzob;
    private int zzoc;
    private Field zzod;
    private Object zzoe;
    private Object zzof;
    private Object zzog;

    zzed(Class<?> cls, String str, Object[] objArr) {
        this.zzni = cls;
        this.zznh = new zzee(str);
        this.zzmj = objArr;
        this.flags = this.zznh.next();
        this.zznj = this.zznh.next();
        int[] iArr = null;
        if (this.zznj == 0) {
            this.zznk = 0;
            this.zznl = 0;
            this.zzmk = 0;
            this.zzml = 0;
            this.zznm = 0;
            this.zznn = 0;
            this.zzmm = 0;
            this.zzno = 0;
            this.zzms = null;
            return;
        }
        this.zznk = this.zznh.next();
        this.zznl = this.zznh.next();
        this.zzmk = this.zznh.next();
        this.zzml = this.zznh.next();
        this.zznn = this.zznh.next();
        this.zzmm = this.zznh.next();
        this.zznm = this.zznh.next();
        this.zzno = this.zznh.next();
        int next = this.zznh.next();
        if (next != 0) {
            iArr = new int[next];
        }
        this.zzms = iArr;
        this.zznp = (this.zznk << 1) + this.zznl;
    }

    private static Field zza(Class<?> cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (NoSuchFieldException unused) {
            Field[] declaredFields = cls.getDeclaredFields();
            for (Field field : declaredFields) {
                if (str.equals(field.getName())) {
                    return field;
                }
            }
            String name = cls.getName();
            String arrays = Arrays.toString(declaredFields);
            StringBuilder stringBuilder = new StringBuilder(((40 + String.valueOf(str).length()) + String.valueOf(name).length()) + String.valueOf(arrays).length());
            stringBuilder.append("Field ");
            stringBuilder.append(str);
            stringBuilder.append(" for ");
            stringBuilder.append(name);
            stringBuilder.append(" not found. Known fields are ");
            stringBuilder.append(arrays);
            throw new RuntimeException(stringBuilder.toString());
        }
    }

    private final Object zzcw() {
        Object[] objArr = this.zzmj;
        int i = this.zznp;
        this.zznp = i + 1;
        return objArr[i];
    }

    private final boolean zzcz() {
        return (this.flags & 1) == 1;
    }

    /* Access modifiers changed, original: final */
    /* JADX WARNING: Missing block: B:37:0x00d5, code skipped:
            if (zzcz() != false) goto L_0x00d7;
     */
    /* JADX WARNING: Missing block: B:65:0x0161, code skipped:
            if (r1 != false) goto L_0x00d7;
     */
    /* JADX WARNING: Missing block: B:67:0x0169, code skipped:
            if (zzcz() != false) goto L_0x00d7;
     */
    public final boolean next() {
        /*
        r5 = this;
        r0 = r5.zznh;
        r0 = r0.hasNext();
        r1 = 0;
        if (r0 != 0) goto L_0x000a;
    L_0x0009:
        return r1;
    L_0x000a:
        r0 = r5.zznh;
        r0 = r0.next();
        r5.zzny = r0;
        r0 = r5.zznh;
        r0 = r0.next();
        r5.zznz = r0;
        r0 = r5.zznz;
        r0 = r0 & 255;
        r5.zzoa = r0;
        r0 = r5.zzny;
        r2 = r5.zznr;
        if (r0 >= r2) goto L_0x002a;
    L_0x0026:
        r0 = r5.zzny;
        r5.zznr = r0;
    L_0x002a:
        r0 = r5.zzny;
        r2 = r5.zzns;
        if (r0 <= r2) goto L_0x0034;
    L_0x0030:
        r0 = r5.zzny;
        r5.zzns = r0;
    L_0x0034:
        r0 = r5.zzoa;
        r2 = com.google.android.gms.internal.clearcut.zzcb.MAP;
        r2 = r2.id();
        r3 = 1;
        if (r0 != r2) goto L_0x0045;
    L_0x003f:
        r0 = r5.zznt;
        r0 = r0 + r3;
        r5.zznt = r0;
        goto L_0x005e;
    L_0x0045:
        r0 = r5.zzoa;
        r2 = com.google.android.gms.internal.clearcut.zzcb.DOUBLE_LIST;
        r2 = r2.id();
        if (r0 < r2) goto L_0x005e;
    L_0x004f:
        r0 = r5.zzoa;
        r2 = com.google.android.gms.internal.clearcut.zzcb.GROUP_LIST;
        r2 = r2.id();
        if (r0 > r2) goto L_0x005e;
    L_0x0059:
        r0 = r5.zznu;
        r0 = r0 + r3;
        r5.zznu = r0;
    L_0x005e:
        r0 = r5.zznx;
        r0 = r0 + r3;
        r5.zznx = r0;
        r0 = r5.zznr;
        r2 = r5.zzny;
        r4 = r5.zznx;
        r0 = com.google.android.gms.internal.clearcut.zzeh.zzc(r0, r2, r4);
        if (r0 == 0) goto L_0x007c;
    L_0x006f:
        r0 = r5.zzny;
        r0 = r0 + r3;
        r5.zznw = r0;
        r0 = r5.zznw;
        r2 = r5.zznr;
        r0 = r0 - r2;
    L_0x0079:
        r5.zznv = r0;
        goto L_0x0080;
    L_0x007c:
        r0 = r5.zznv;
        r0 = r0 + r3;
        goto L_0x0079;
    L_0x0080:
        r0 = r5.zznz;
        r0 = r0 & 1024;
        if (r0 == 0) goto L_0x0088;
    L_0x0086:
        r0 = r3;
        goto L_0x0089;
    L_0x0088:
        r0 = r1;
    L_0x0089:
        if (r0 == 0) goto L_0x0097;
    L_0x008b:
        r0 = r5.zzms;
        r2 = r5.zznq;
        r4 = r2 + 1;
        r5.zznq = r4;
        r4 = r5.zzny;
        r0[r2] = r4;
    L_0x0097:
        r0 = 0;
        r5.zzoe = r0;
        r5.zzof = r0;
        r5.zzog = r0;
        r0 = r5.zzda();
        if (r0 == 0) goto L_0x00e5;
    L_0x00a4:
        r0 = r5.zznh;
        r0 = r0.next();
        r5.zzob = r0;
        r0 = r5.zzoa;
        r1 = com.google.android.gms.internal.clearcut.zzcb.MESSAGE;
        r1 = r1.id();
        r1 = r1 + 51;
        if (r0 == r1) goto L_0x00de;
    L_0x00b8:
        r0 = r5.zzoa;
        r1 = com.google.android.gms.internal.clearcut.zzcb.GROUP;
        r1 = r1.id();
        r1 = r1 + 51;
        if (r0 != r1) goto L_0x00c5;
    L_0x00c4:
        goto L_0x00de;
    L_0x00c5:
        r0 = r5.zzoa;
        r1 = com.google.android.gms.internal.clearcut.zzcb.ENUM;
        r1 = r1.id();
        r1 = r1 + 51;
        if (r0 != r1) goto L_0x0175;
    L_0x00d1:
        r0 = r5.zzcz();
        if (r0 == 0) goto L_0x0175;
    L_0x00d7:
        r0 = r5.zzcw();
        r5.zzof = r0;
        return r3;
    L_0x00de:
        r0 = r5.zzcw();
    L_0x00e2:
        r5.zzoe = r0;
        return r3;
    L_0x00e5:
        r0 = r5.zzni;
        r2 = r5.zzcw();
        r2 = (java.lang.String) r2;
        r0 = zza(r0, r2);
        r5.zzod = r0;
        r0 = r5.zzde();
        if (r0 == 0) goto L_0x0101;
    L_0x00f9:
        r0 = r5.zznh;
        r0 = r0.next();
        r5.zzoc = r0;
    L_0x0101:
        r0 = r5.zzoa;
        r2 = com.google.android.gms.internal.clearcut.zzcb.MESSAGE;
        r2 = r2.id();
        if (r0 == r2) goto L_0x016d;
    L_0x010b:
        r0 = r5.zzoa;
        r2 = com.google.android.gms.internal.clearcut.zzcb.GROUP;
        r2 = r2.id();
        if (r0 != r2) goto L_0x0116;
    L_0x0115:
        goto L_0x016d;
    L_0x0116:
        r0 = r5.zzoa;
        r2 = com.google.android.gms.internal.clearcut.zzcb.MESSAGE_LIST;
        r2 = r2.id();
        if (r0 == r2) goto L_0x00de;
    L_0x0120:
        r0 = r5.zzoa;
        r2 = com.google.android.gms.internal.clearcut.zzcb.GROUP_LIST;
        r2 = r2.id();
        if (r0 != r2) goto L_0x012b;
    L_0x012a:
        goto L_0x00de;
    L_0x012b:
        r0 = r5.zzoa;
        r2 = com.google.android.gms.internal.clearcut.zzcb.ENUM;
        r2 = r2.id();
        if (r0 == r2) goto L_0x0165;
    L_0x0135:
        r0 = r5.zzoa;
        r2 = com.google.android.gms.internal.clearcut.zzcb.ENUM_LIST;
        r2 = r2.id();
        if (r0 == r2) goto L_0x0165;
    L_0x013f:
        r0 = r5.zzoa;
        r2 = com.google.android.gms.internal.clearcut.zzcb.ENUM_LIST_PACKED;
        r2 = r2.id();
        if (r0 != r2) goto L_0x014a;
    L_0x0149:
        goto L_0x0165;
    L_0x014a:
        r0 = r5.zzoa;
        r2 = com.google.android.gms.internal.clearcut.zzcb.MAP;
        r2 = r2.id();
        if (r0 != r2) goto L_0x0175;
    L_0x0154:
        r0 = r5.zzcw();
        r5.zzog = r0;
        r0 = r5.zznz;
        r0 = r0 & 2048;
        if (r0 == 0) goto L_0x0161;
    L_0x0160:
        r1 = r3;
    L_0x0161:
        if (r1 == 0) goto L_0x0175;
    L_0x0163:
        goto L_0x00d7;
    L_0x0165:
        r0 = r5.zzcz();
        if (r0 == 0) goto L_0x0175;
    L_0x016b:
        goto L_0x00d7;
    L_0x016d:
        r0 = r5.zzod;
        r0 = r0.getType();
        goto L_0x00e2;
    L_0x0175:
        return r3;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.clearcut.zzed.next():boolean");
    }

    /* Access modifiers changed, original: final */
    public final int zzcx() {
        return this.zzny;
    }

    /* Access modifiers changed, original: final */
    public final int zzcy() {
        return this.zzoa;
    }

    /* Access modifiers changed, original: final */
    public final boolean zzda() {
        return this.zzoa > zzcb.MAP.id();
    }

    /* Access modifiers changed, original: final */
    public final Field zzdb() {
        int i = this.zzob << 1;
        Object obj = this.zzmj[i];
        if (obj instanceof Field) {
            return (Field) obj;
        }
        Field zza = zza(this.zzni, (String) obj);
        this.zzmj[i] = zza;
        return zza;
    }

    /* Access modifiers changed, original: final */
    public final Field zzdc() {
        int i = (this.zzob << 1) + 1;
        Object obj = this.zzmj[i];
        if (obj instanceof Field) {
            return (Field) obj;
        }
        Field zza = zza(this.zzni, (String) obj);
        this.zzmj[i] = zza;
        return zza;
    }

    /* Access modifiers changed, original: final */
    public final Field zzdd() {
        return this.zzod;
    }

    /* Access modifiers changed, original: final */
    public final boolean zzde() {
        return zzcz() && this.zzoa <= zzcb.GROUP.id();
    }

    /* Access modifiers changed, original: final */
    public final Field zzdf() {
        int i = (this.zznk << 1) + (this.zzoc / 32);
        Object obj = this.zzmj[i];
        if (obj instanceof Field) {
            return (Field) obj;
        }
        Field zza = zza(this.zzni, (String) obj);
        this.zzmj[i] = zza;
        return zza;
    }

    /* Access modifiers changed, original: final */
    public final int zzdg() {
        return this.zzoc % 32;
    }

    /* Access modifiers changed, original: final */
    public final boolean zzdh() {
        return (this.zznz & 256) != 0;
    }

    /* Access modifiers changed, original: final */
    public final boolean zzdi() {
        return (this.zznz & 512) != 0;
    }

    /* Access modifiers changed, original: final */
    public final Object zzdj() {
        return this.zzoe;
    }

    /* Access modifiers changed, original: final */
    public final Object zzdk() {
        return this.zzof;
    }

    /* Access modifiers changed, original: final */
    public final Object zzdl() {
        return this.zzog;
    }
}
