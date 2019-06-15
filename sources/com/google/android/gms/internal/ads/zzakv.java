package com.google.android.gms.internal.ads;

final class zzakv implements Runnable {
    private final /* synthetic */ zzakt zzdmx;
    private final /* synthetic */ zzaku zzdmy;

    zzakv(zzaku zzaku, zzakt zzakt) {
        this.zzdmy = zzaku;
        this.zzdmx = zzakt;
    }

    public final void run() {
        synchronized (this.zzdmy.mLock) {
            if (this.zzdmy.zzdmv != -2) {
                return;
            }
            this.zzdmy.zzdmu = this.zzdmy.zzuo();
            if (this.zzdmy.zzdmu == null) {
                this.zzdmy.zzco(4);
            } else if (!this.zzdmy.zzup() || this.zzdmy.zzcp(1)) {
                this.zzdmx.zza(this.zzdmy);
                this.zzdmy.zza(this.zzdmx);
            } else {
                String zzf = this.zzdmy.zzdml;
                StringBuilder stringBuilder = new StringBuilder(56 + String.valueOf(zzf).length());
                stringBuilder.append("Ignoring adapter ");
                stringBuilder.append(zzf);
                stringBuilder.append(" as delayed impression is not supported");
                zzbbd.zzeo(stringBuilder.toString());
                this.zzdmy.zzco(2);
            }
        }
    }
}
