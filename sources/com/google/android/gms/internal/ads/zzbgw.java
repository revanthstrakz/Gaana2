package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import android.support.annotation.Nullable;
import com.facebook.internal.NativeProtocol;
import com.google.android.gms.common.util.CollectionUtils;
import java.util.HashMap;
import java.util.Map;

@zzark
public final class zzbgw extends zzyq {
    private final Object lock = new Object();
    private boolean zzcng;
    private boolean zzcnh;
    private zzys zzdnv;
    private final zzbdz zzerw;
    private final boolean zzfai;
    private final boolean zzfaj;
    private boolean zzfak;
    private boolean zzfal = true;
    private float zzfam;
    private float zzfan;
    private float zzfao;
    private int zzxe;

    public zzbgw(zzbdz zzbdz, float f, boolean z, boolean z2) {
        this.zzerw = zzbdz;
        this.zzfam = f;
        this.zzfai = z;
        this.zzfaj = z2;
    }

    public final void play() {
        zzf("play", null);
    }

    public final void pause() {
        zzf("pause", null);
    }

    public final void mute(boolean z) {
        zzf(z ? "mute" : "unmute", null);
    }

    public final void zzb(zzzw zzzw) {
        zza(zzzw.zzcnf, zzzw.zzcng, zzzw.zzcnh);
    }

    public final void zza(boolean z, boolean z2, boolean z3) {
        synchronized (this.lock) {
            this.zzcng = z2;
            this.zzcnh = z3;
        }
        zzf("initialState", CollectionUtils.mapOf("muteStart", z ? "1" : "0", "customControlsRequested", z2 ? "1" : "0", "clickToExpandRequested", z3 ? "1" : "0"));
    }

    private final void zzf(String str, @Nullable Map<String, String> map) {
        Map hashMap = map == null ? new HashMap() : new HashMap(map);
        hashMap.put(NativeProtocol.WEB_DIALOG_ACTION, str);
        zzbcg.zzepo.execute(new zzbgx(this, hashMap));
    }

    public final boolean isMuted() {
        boolean z;
        synchronized (this.lock) {
            z = this.zzfal;
        }
        return z;
    }

    public final int getPlaybackState() {
        int i;
        synchronized (this.lock) {
            i = this.zzxe;
        }
        return i;
    }

    public final float getAspectRatio() {
        float f;
        synchronized (this.lock) {
            f = this.zzfao;
        }
        return f;
    }

    public final float zzqf() {
        float f;
        synchronized (this.lock) {
            f = this.zzfam;
        }
        return f;
    }

    public final float zzqg() {
        float f;
        synchronized (this.lock) {
            f = this.zzfan;
        }
        return f;
    }

    public final void zza(zzys zzys) {
        synchronized (this.lock) {
            this.zzdnv = zzys;
        }
    }

    public final zzys zzqh() throws RemoteException {
        zzys zzys;
        synchronized (this.lock) {
            zzys = this.zzdnv;
        }
        return zzys;
    }

    public final boolean isCustomControlsEnabled() {
        boolean z;
        synchronized (this.lock) {
            z = this.zzfai && this.zzcng;
        }
        return z;
    }

    public final boolean isClickToExpandEnabled() {
        boolean isCustomControlsEnabled = isCustomControlsEnabled();
        synchronized (this.lock) {
            if (!isCustomControlsEnabled) {
                try {
                    if (this.zzcnh && this.zzfaj) {
                        isCustomControlsEnabled = true;
                    }
                } finally {
                }
            }
            isCustomControlsEnabled = false;
        }
        return isCustomControlsEnabled;
    }

    public final void zze(float f) {
        synchronized (this.lock) {
            this.zzfan = f;
        }
    }

    public final void zzaew() {
        boolean z;
        int i;
        synchronized (this.lock) {
            z = this.zzfal;
            i = this.zzxe;
            this.zzxe = 3;
        }
        zza(i, 3, z, z);
    }

    public final void zza(float f, float f2, int i, boolean z, float f3) {
        boolean z2;
        int i2;
        synchronized (this.lock) {
            this.zzfam = f2;
            this.zzfan = f;
            z2 = this.zzfal;
            this.zzfal = z;
            i2 = this.zzxe;
            this.zzxe = i;
            float f4 = this.zzfao;
            this.zzfao = f3;
            if (Math.abs(this.zzfao - f4) > 1.0E-4f) {
                this.zzerw.getView().invalidate();
            }
        }
        zza(i2, i, z2, z);
    }

    private final void zza(int i, int i2, boolean z, boolean z2) {
        zzbcg.zzepo.execute(new zzbgy(this, i, i2, z, z2));
    }

    /* Access modifiers changed, original: final|synthetic */
    public final /* synthetic */ void zzb(int i, int i2, boolean z, boolean z2) {
        synchronized (this.lock) {
            boolean z3 = false;
            boolean z4 = i != i2;
            boolean z5 = !this.zzfak && i2 == 1;
            boolean z6 = z4 && i2 == 1;
            boolean z7 = z4 && i2 == 2;
            z4 = z4 && i2 == 3;
            boolean z8 = z != z2;
            if (this.zzfak || z5) {
                z3 = true;
            }
            this.zzfak = z3;
            if (this.zzdnv == null) {
                return;
            }
            if (z5) {
                try {
                    this.zzdnv.onVideoStart();
                } catch (RemoteException e) {
                    zzbbd.zzc("Unable to call onVideoStart()", e);
                }
            }
            if (z6) {
                try {
                    this.zzdnv.onVideoPlay();
                } catch (RemoteException e2) {
                    zzbbd.zzc("Unable to call onVideoPlay()", e2);
                }
            }
            if (z7) {
                try {
                    this.zzdnv.onVideoPause();
                } catch (RemoteException e22) {
                    zzbbd.zzc("Unable to call onVideoPause()", e22);
                }
            }
            if (z4) {
                try {
                    this.zzdnv.onVideoEnd();
                } catch (RemoteException e3) {
                    zzbbd.zzc("Unable to call onVideoEnd()", e3);
                }
                this.zzerw.zzacc();
            }
            if (z8) {
                try {
                    this.zzdnv.onVideoMute(z2);
                } catch (RemoteException e32) {
                    zzbbd.zzc("Unable to call onVideoMute()", e32);
                }
            }
        }
        return;
    }

    /* Access modifiers changed, original: final|synthetic */
    public final /* synthetic */ void zzo(Map map) {
        this.zzerw.zza("pubVideoCmd", map);
    }
}
