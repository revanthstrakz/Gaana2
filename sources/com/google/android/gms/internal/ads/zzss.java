package com.google.android.gms.internal.ads;

import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.zzbv;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

@zzark
public final class zzss {
    private final Object mLock = new Object();
    private int zzbxn;
    private List<zzsr> zzbxo = new LinkedList();

    @Nullable
    public final zzsr zznr() {
        synchronized (this.mLock) {
            zzsr zzsr = null;
            if (this.zzbxo.size() == 0) {
                zzbbd.zzdn("Queue empty");
                return null;
            }
            int i = 0;
            if (this.zzbxo.size() >= 2) {
                int i2 = Integer.MIN_VALUE;
                int i3 = 0;
                for (zzsr zzsr2 : this.zzbxo) {
                    int score = zzsr2.getScore();
                    if (score > i2) {
                        i = i3;
                        zzsr = zzsr2;
                        i2 = score;
                    }
                    i3++;
                }
                this.zzbxo.remove(i);
                return zzsr;
            }
            zzsr zzsr3 = (zzsr) this.zzbxo.get(0);
            zzsr3.zznm();
            return zzsr3;
        }
    }

    public final boolean zza(zzsr zzsr) {
        synchronized (this.mLock) {
            if (this.zzbxo.contains(zzsr)) {
                return true;
            }
            return false;
        }
    }

    public final boolean zzb(zzsr zzsr) {
        synchronized (this.mLock) {
            Iterator it = this.zzbxo.iterator();
            while (it.hasNext()) {
                zzsr zzsr2 = (zzsr) it.next();
                if (zzbv.zzlj().zzyq().zzzc()) {
                    if (!(zzbv.zzlj().zzyq().zzze() || zzsr == zzsr2 || !zzsr2.zznl().equals(zzsr.zznl()))) {
                        it.remove();
                        return true;
                    }
                } else if (zzsr != zzsr2 && zzsr2.zznj().equals(zzsr.zznj())) {
                    it.remove();
                    return true;
                }
            }
            return false;
        }
    }

    public final void zzc(zzsr zzsr) {
        synchronized (this.mLock) {
            int size;
            if (this.zzbxo.size() >= 10) {
                size = this.zzbxo.size();
                StringBuilder stringBuilder = new StringBuilder(41);
                stringBuilder.append("Queue is full, current size = ");
                stringBuilder.append(size);
                zzbbd.zzdn(stringBuilder.toString());
                this.zzbxo.remove(0);
            }
            size = this.zzbxn;
            this.zzbxn = size + 1;
            zzsr.zzbx(size);
            this.zzbxo.add(zzsr);
        }
    }
}
