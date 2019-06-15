package com.google.android.gms.internal.clearcut;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map.Entry;

final class zzdu<T> implements zzef<T> {
    private final zzdo zzmn;
    private final boolean zzmo;
    private final zzex<?, ?> zzmx;
    private final zzbu<?> zzmy;

    private zzdu(zzex<?, ?> zzex, zzbu<?> zzbu, zzdo zzdo) {
        this.zzmx = zzex;
        this.zzmo = zzbu.zze(zzdo);
        this.zzmy = zzbu;
        this.zzmn = zzdo;
    }

    static <T> zzdu<T> zza(zzex<?, ?> zzex, zzbu<?> zzbu, zzdo zzdo) {
        return new zzdu(zzex, zzbu, zzdo);
    }

    public final boolean equals(T t, T t2) {
        return !this.zzmx.zzq(t).equals(this.zzmx.zzq(t2)) ? false : this.zzmo ? this.zzmy.zza((Object) t).equals(this.zzmy.zza((Object) t2)) : true;
    }

    public final int hashCode(T t) {
        int hashCode = this.zzmx.zzq(t).hashCode();
        return this.zzmo ? (hashCode * 53) + this.zzmy.zza((Object) t).hashCode() : hashCode;
    }

    public final T newInstance() {
        return this.zzmn.zzbd().zzbi();
    }

    public final void zza(T t, zzfr zzfr) throws IOException {
        Iterator it = this.zzmy.zza((Object) t).iterator();
        while (it.hasNext()) {
            Entry entry = (Entry) it.next();
            zzca zzca = (zzca) entry.getKey();
            if (zzca.zzav() != zzfq.MESSAGE || zzca.zzaw() || zzca.zzax()) {
                throw new IllegalStateException("Found invalid MessageSet item.");
            }
            int zzc;
            Object zzr;
            if (entry instanceof zzct) {
                zzc = zzca.zzc();
                zzr = ((zzct) entry).zzbs().zzr();
            } else {
                zzc = zzca.zzc();
                zzr = entry.getValue();
            }
            zzfr.zza(zzc, zzr);
        }
        zzex zzex = this.zzmx;
        zzex.zzc(zzex.zzq(t), zzfr);
    }

    public final void zza(T r7, byte[] r8, int r9, int r10, com.google.android.gms.internal.clearcut.zzay r11) throws java.io.IOException {
        /*
        r6 = this;
        r7 = (com.google.android.gms.internal.clearcut.zzcg) r7;
        r0 = r7.zzjp;
        r1 = com.google.android.gms.internal.clearcut.zzey.zzea();
        if (r0 != r1) goto L_0x0010;
    L_0x000a:
        r0 = com.google.android.gms.internal.clearcut.zzey.zzeb();
        r7.zzjp = r0;
    L_0x0010:
        r7 = r0;
    L_0x0011:
        if (r9 >= r10) goto L_0x0069;
    L_0x0013:
        r2 = com.google.android.gms.internal.clearcut.zzax.zza(r8, r9, r11);
        r0 = r11.zzfd;
        r9 = 11;
        r1 = 2;
        if (r0 == r9) goto L_0x0030;
    L_0x001e:
        r9 = r0 & 7;
        if (r9 != r1) goto L_0x002b;
    L_0x0022:
        r1 = r8;
        r3 = r10;
        r4 = r7;
        r5 = r11;
        r9 = com.google.android.gms.internal.clearcut.zzax.zza(r0, r1, r2, r3, r4, r5);
        goto L_0x0011;
    L_0x002b:
        r9 = com.google.android.gms.internal.clearcut.zzax.zza(r0, r8, r2, r10, r11);
        goto L_0x0011;
    L_0x0030:
        r9 = 0;
        r0 = 0;
    L_0x0032:
        if (r2 >= r10) goto L_0x005f;
    L_0x0034:
        r2 = com.google.android.gms.internal.clearcut.zzax.zza(r8, r2, r11);
        r3 = r11.zzfd;
        r4 = r3 >>> 3;
        r5 = r3 & 7;
        switch(r4) {
            case 2: goto L_0x004d;
            case 3: goto L_0x0042;
            default: goto L_0x0041;
        };
    L_0x0041:
        goto L_0x0056;
    L_0x0042:
        if (r5 != r1) goto L_0x0056;
    L_0x0044:
        r2 = com.google.android.gms.internal.clearcut.zzax.zze(r8, r2, r11);
        r0 = r11.zzff;
        r0 = (com.google.android.gms.internal.clearcut.zzbb) r0;
        goto L_0x0032;
    L_0x004d:
        if (r5 != 0) goto L_0x0056;
    L_0x004f:
        r2 = com.google.android.gms.internal.clearcut.zzax.zza(r8, r2, r11);
        r9 = r11.zzfd;
        goto L_0x0032;
    L_0x0056:
        r4 = 12;
        if (r3 == r4) goto L_0x005f;
    L_0x005a:
        r2 = com.google.android.gms.internal.clearcut.zzax.zza(r3, r8, r2, r10, r11);
        goto L_0x0032;
    L_0x005f:
        if (r0 == 0) goto L_0x0067;
    L_0x0061:
        r9 = r9 << 3;
        r9 = r9 | r1;
        r7.zzb(r9, r0);
    L_0x0067:
        r9 = r2;
        goto L_0x0011;
    L_0x0069:
        if (r9 == r10) goto L_0x0070;
    L_0x006b:
        r7 = com.google.android.gms.internal.clearcut.zzco.zzbo();
        throw r7;
    L_0x0070:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.clearcut.zzdu.zza(java.lang.Object, byte[], int, int, com.google.android.gms.internal.clearcut.zzay):void");
    }

    public final void zzc(T t) {
        this.zzmx.zzc(t);
        this.zzmy.zzc(t);
    }

    public final void zzc(T t, T t2) {
        zzeh.zza(this.zzmx, (Object) t, (Object) t2);
        if (this.zzmo) {
            zzeh.zza(this.zzmy, (Object) t, (Object) t2);
        }
    }

    public final int zzm(T t) {
        zzex zzex = this.zzmx;
        int zzr = 0 + zzex.zzr(zzex.zzq(t));
        return this.zzmo ? zzr + this.zzmy.zza((Object) t).zzat() : zzr;
    }

    public final boolean zzo(T t) {
        return this.zzmy.zza((Object) t).isInitialized();
    }
}
