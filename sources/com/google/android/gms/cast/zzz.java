package com.google.android.gms.cast;

import android.view.Display;
import com.google.android.gms.tasks.OnCompleteListener;

final class zzz implements OnCompleteListener<Display> {
    private final /* synthetic */ CastRemoteDisplayLocalService zzci;

    zzz(CastRemoteDisplayLocalService castRemoteDisplayLocalService) {
        this.zzci = castRemoteDisplayLocalService;
    }

    /* JADX WARNING: Missing block: B:13:0x0041, code skipped:
            r5 = (android.view.Display) r5.getResult();
     */
    /* JADX WARNING: Missing block: B:14:0x0047, code skipped:
            if (r5 == null) goto L_0x004f;
     */
    /* JADX WARNING: Missing block: B:15:0x0049, code skipped:
            r4.zzci.zza(r5);
     */
    /* JADX WARNING: Missing block: B:16:0x004f, code skipped:
            com.google.android.gms.cast.CastRemoteDisplayLocalService.zzbf.e("Cast Remote Display session created without display", new java.lang.Object[0]);
     */
    /* JADX WARNING: Missing block: B:17:0x005a, code skipped:
            com.google.android.gms.cast.CastRemoteDisplayLocalService.zzbr.set(false);
     */
    /* JADX WARNING: Missing block: B:18:0x0067, code skipped:
            if (r4.zzci.zzca == null) goto L_0x0097;
     */
    /* JADX WARNING: Missing block: B:20:0x006f, code skipped:
            if (r4.zzci.zzcb == null) goto L_0x0097;
     */
    /* JADX WARNING: Missing block: B:22:?, code skipped:
            r4.zzci.zzca.unbindService(r4.zzci.zzcb);
     */
    /* JADX WARNING: Missing block: B:23:0x0081, code skipped:
            com.google.android.gms.cast.CastRemoteDisplayLocalService.zzbf.d("No need to unbind service, already unbound", new java.lang.Object[0]);
     */
    public final void onComplete(@android.support.annotation.NonNull com.google.android.gms.tasks.Task<android.view.Display> r5) {
        /*
        r4 = this;
        r0 = r5.isSuccessful();
        r1 = 0;
        if (r0 != 0) goto L_0x0018;
    L_0x0007:
        r5 = com.google.android.gms.cast.CastRemoteDisplayLocalService.zzbf;
        r0 = "Connection was not successful";
        r1 = new java.lang.Object[r1];
        r5.e(r0, r1);
        r5 = r4.zzci;
        r5.zze();
        return;
    L_0x0018:
        r0 = com.google.android.gms.cast.CastRemoteDisplayLocalService.zzbf;
        r2 = "startRemoteDisplay successful";
        r3 = new java.lang.Object[r1];
        r0.d(r2, r3);
        r0 = com.google.android.gms.cast.CastRemoteDisplayLocalService.zzbq;
        monitor-enter(r0);
        r2 = com.google.android.gms.cast.CastRemoteDisplayLocalService.zzcg;	 Catch:{ all -> 0x0098 }
        if (r2 != 0) goto L_0x0040;
    L_0x002e:
        r5 = com.google.android.gms.cast.CastRemoteDisplayLocalService.zzbf;	 Catch:{ all -> 0x0098 }
        r2 = "Remote Display started but session already cancelled";
        r1 = new java.lang.Object[r1];	 Catch:{ all -> 0x0098 }
        r5.d(r2, r1);	 Catch:{ all -> 0x0098 }
        r5 = r4.zzci;	 Catch:{ all -> 0x0098 }
        r5.zze();	 Catch:{ all -> 0x0098 }
        monitor-exit(r0);	 Catch:{ all -> 0x0098 }
        return;
    L_0x0040:
        monitor-exit(r0);	 Catch:{ all -> 0x0098 }
        r5 = r5.getResult();
        r5 = (android.view.Display) r5;
        if (r5 == 0) goto L_0x004f;
    L_0x0049:
        r0 = r4.zzci;
        r0.zza(r5);
        goto L_0x005a;
    L_0x004f:
        r5 = com.google.android.gms.cast.CastRemoteDisplayLocalService.zzbf;
        r0 = "Cast Remote Display session created without display";
        r2 = new java.lang.Object[r1];
        r5.e(r0, r2);
    L_0x005a:
        r5 = com.google.android.gms.cast.CastRemoteDisplayLocalService.zzbr;
        r5.set(r1);
        r5 = r4.zzci;
        r5 = r5.zzca;
        if (r5 == 0) goto L_0x0097;
    L_0x0069:
        r5 = r4.zzci;
        r5 = r5.zzcb;
        if (r5 == 0) goto L_0x0097;
    L_0x0071:
        r5 = r4.zzci;	 Catch:{ IllegalArgumentException -> 0x0081 }
        r5 = r5.zzca;	 Catch:{ IllegalArgumentException -> 0x0081 }
        r0 = r4.zzci;	 Catch:{ IllegalArgumentException -> 0x0081 }
        r0 = r0.zzcb;	 Catch:{ IllegalArgumentException -> 0x0081 }
        r5.unbindService(r0);	 Catch:{ IllegalArgumentException -> 0x0081 }
        goto L_0x008c;
    L_0x0081:
        r5 = com.google.android.gms.cast.CastRemoteDisplayLocalService.zzbf;
        r0 = "No need to unbind service, already unbound";
        r1 = new java.lang.Object[r1];
        r5.d(r0, r1);
    L_0x008c:
        r5 = r4.zzci;
        r0 = 0;
        r5.zzcb = null;
        r5 = r4.zzci;
        r5.zzca = null;
    L_0x0097:
        return;
    L_0x0098:
        r5 = move-exception;
        monitor-exit(r0);	 Catch:{ all -> 0x0098 }
        throw r5;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.cast.zzz.onComplete(com.google.android.gms.tasks.Task):void");
    }
}
