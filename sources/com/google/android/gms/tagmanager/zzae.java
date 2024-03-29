package com.google.android.gms.tagmanager;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.measurement.zzo;

final class zzae implements zzdh<zzo> {
    private final /* synthetic */ zzy zzbas;

    private zzae(zzy zzy) {
        this.zzbas = zzy;
    }

    public final void zznx() {
    }

    public final void zzu(int i) {
        if (i == zzcz.zzbdh) {
            this.zzbas.zzbaj.zzob();
        }
        synchronized (this.zzbas) {
            if (!this.zzbas.isReady()) {
                if (this.zzbas.zzbam != null) {
                    this.zzbas.setResult(this.zzbas.zzbam);
                } else {
                    this.zzbas.setResult(this.zzbas.createFailedResult(Status.RESULT_TIMEOUT));
                }
            }
        }
        this.zzbas.zzao(this.zzbas.zzbaj.zzoa());
    }

    /* JADX WARNING: Missing block: B:15:0x0076, code skipped:
            return;
     */
    public final /* synthetic */ void onSuccess(java.lang.Object r6) {
        /*
        r5 = this;
        r6 = (com.google.android.gms.internal.measurement.zzo) r6;
        r0 = r5.zzbas;
        r0 = r0.zzbaj;
        r0.zzoc();
        r0 = r5.zzbas;
        monitor-enter(r0);
        r1 = r6.zzqg;	 Catch:{ all -> 0x0077 }
        if (r1 != 0) goto L_0x003c;
    L_0x0012:
        r1 = r5.zzbas;	 Catch:{ all -> 0x0077 }
        r1 = r1.zzbao;	 Catch:{ all -> 0x0077 }
        r1 = r1.zzqg;	 Catch:{ all -> 0x0077 }
        if (r1 != 0) goto L_0x0032;
    L_0x001c:
        r6 = "Current resource is null; network resource is also null";
        com.google.android.gms.tagmanager.zzdi.e(r6);	 Catch:{ all -> 0x0077 }
        r6 = r5.zzbas;	 Catch:{ all -> 0x0077 }
        r6 = r6.zzbaj;	 Catch:{ all -> 0x0077 }
        r1 = r6.zzoa();	 Catch:{ all -> 0x0077 }
        r6 = r5.zzbas;	 Catch:{ all -> 0x0077 }
        r6.zzao(r1);	 Catch:{ all -> 0x0077 }
        monitor-exit(r0);	 Catch:{ all -> 0x0077 }
        return;
    L_0x0032:
        r1 = r5.zzbas;	 Catch:{ all -> 0x0077 }
        r1 = r1.zzbao;	 Catch:{ all -> 0x0077 }
        r1 = r1.zzqg;	 Catch:{ all -> 0x0077 }
        r6.zzqg = r1;	 Catch:{ all -> 0x0077 }
    L_0x003c:
        r1 = r5.zzbas;	 Catch:{ all -> 0x0077 }
        r2 = r5.zzbas;	 Catch:{ all -> 0x0077 }
        r2 = r2.zzrz;	 Catch:{ all -> 0x0077 }
        r2 = r2.currentTimeMillis();	 Catch:{ all -> 0x0077 }
        r4 = 0;
        r1.zza(r6, r2, r4);	 Catch:{ all -> 0x0077 }
        r1 = r5.zzbas;	 Catch:{ all -> 0x0077 }
        r1 = r1.zzazt;	 Catch:{ all -> 0x0077 }
        r3 = 58;
        r4 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0077 }
        r4.<init>(r3);	 Catch:{ all -> 0x0077 }
        r3 = "setting refresh time to current time: ";
        r4.append(r3);	 Catch:{ all -> 0x0077 }
        r4.append(r1);	 Catch:{ all -> 0x0077 }
        r1 = r4.toString();	 Catch:{ all -> 0x0077 }
        com.google.android.gms.tagmanager.zzdi.v(r1);	 Catch:{ all -> 0x0077 }
        r1 = r5.zzbas;	 Catch:{ all -> 0x0077 }
        r1 = r1.zznw();	 Catch:{ all -> 0x0077 }
        if (r1 != 0) goto L_0x0075;
    L_0x0070:
        r1 = r5.zzbas;	 Catch:{ all -> 0x0077 }
        r1.zza(r6);	 Catch:{ all -> 0x0077 }
    L_0x0075:
        monitor-exit(r0);	 Catch:{ all -> 0x0077 }
        return;
    L_0x0077:
        r6 = move-exception;
        monitor-exit(r0);	 Catch:{ all -> 0x0077 }
        throw r6;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zzae.onSuccess(java.lang.Object):void");
    }

    /* synthetic */ zzae(zzy zzy, zzz zzz) {
        this(zzy);
    }
}
