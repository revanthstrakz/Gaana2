package com.google.android.gms.tagmanager;

import android.os.Looper;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.util.VisibleForTesting;

@VisibleForTesting
final class zzv implements ContainerHolder {
    private final Looper zzazw;
    private Container zzazx;
    private Container zzazy;
    private Status zzazz;
    private zzx zzbaa;
    private zzw zzbab;
    private boolean zzbac;
    private TagManager zzbad;

    public zzv(Status status) {
        this.zzazz = status;
        this.zzazw = null;
    }

    public zzv(TagManager tagManager, Looper looper, Container container, zzw zzw) {
        this.zzbad = tagManager;
        if (looper == null) {
            looper = Looper.getMainLooper();
        }
        this.zzazw = looper;
        this.zzazx = container;
        this.zzbab = zzw;
        this.zzazz = Status.RESULT_SUCCESS;
        tagManager.zza(this);
    }

    public final Status getStatus() {
        return this.zzazz;
    }

    public final synchronized Container getContainer() {
        if (this.zzbac) {
            zzdi.e("ContainerHolder is released.");
            return null;
        }
        if (this.zzazy != null) {
            this.zzazx = this.zzazy;
            this.zzazy = null;
        }
        return this.zzazx;
    }

    /* JADX WARNING: Missing block: B:18:0x0024, code skipped:
            return;
     */
    public final synchronized void setContainerAvailableListener(com.google.android.gms.tagmanager.ContainerHolder.ContainerAvailableListener r3) {
        /*
        r2 = this;
        monitor-enter(r2);
        r0 = r2.zzbac;	 Catch:{ all -> 0x0025 }
        if (r0 == 0) goto L_0x000c;
    L_0x0005:
        r3 = "ContainerHolder is released.";
        com.google.android.gms.tagmanager.zzdi.e(r3);	 Catch:{ all -> 0x0025 }
        monitor-exit(r2);
        return;
    L_0x000c:
        if (r3 != 0) goto L_0x0013;
    L_0x000e:
        r3 = 0;
        r2.zzbaa = r3;	 Catch:{ all -> 0x0025 }
        monitor-exit(r2);
        return;
    L_0x0013:
        r0 = new com.google.android.gms.tagmanager.zzx;	 Catch:{ all -> 0x0025 }
        r1 = r2.zzazw;	 Catch:{ all -> 0x0025 }
        r0.<init>(r2, r3, r1);	 Catch:{ all -> 0x0025 }
        r2.zzbaa = r0;	 Catch:{ all -> 0x0025 }
        r3 = r2.zzazy;	 Catch:{ all -> 0x0025 }
        if (r3 == 0) goto L_0x0023;
    L_0x0020:
        r2.zznr();	 Catch:{ all -> 0x0025 }
    L_0x0023:
        monitor-exit(r2);
        return;
    L_0x0025:
        r3 = move-exception;
        monitor-exit(r2);
        throw r3;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zzv.setContainerAvailableListener(com.google.android.gms.tagmanager.ContainerHolder$ContainerAvailableListener):void");
    }

    public final synchronized void refresh() {
        if (this.zzbac) {
            zzdi.e("Refreshing a released ContainerHolder.");
        } else {
            this.zzbab.zzns();
        }
    }

    public final synchronized void release() {
        if (this.zzbac) {
            zzdi.e("Releasing a released ContainerHolder.");
            return;
        }
        this.zzbac = true;
        this.zzbad.zzb(this);
        this.zzazx.release();
        this.zzazx = null;
        this.zzazy = null;
        this.zzbab = null;
        this.zzbaa = null;
    }

    public final synchronized void zza(Container container) {
        if (!this.zzbac) {
            this.zzazy = container;
            zznr();
        }
    }

    public final synchronized void zzde(String str) {
        if (!this.zzbac) {
            this.zzazx.zzde(str);
        }
    }

    /* Access modifiers changed, original: final */
    public final String getContainerId() {
        if (!this.zzbac) {
            return this.zzazx.getContainerId();
        }
        zzdi.e("getContainerId called on a released ContainerHolder.");
        return "";
    }

    /* Access modifiers changed, original: final */
    public final void zzdf(String str) {
        if (this.zzbac) {
            zzdi.e("setCtfeUrlPathAndQuery called on a released ContainerHolder.");
        } else {
            this.zzbab.zzdf(str);
        }
    }

    /* Access modifiers changed, original: final */
    public final String zznq() {
        if (!this.zzbac) {
            return this.zzbab.zznq();
        }
        zzdi.e("setCtfeUrlPathAndQuery called on a released ContainerHolder.");
        return "";
    }

    private final void zznr() {
        if (this.zzbaa != null) {
            zzx zzx = this.zzbaa;
            zzx.sendMessage(zzx.obtainMessage(1, this.zzazy.zzno()));
        }
    }
}
