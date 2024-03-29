package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.support.annotation.GuardedBy;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.common.wrappers.Wrappers;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

@zzark
public final class zzaxk {
    private Context mContext;
    private final Object mLock = new Object();
    private zzrf zzblu;
    private zzbbi zzbob;
    private boolean zzbps = false;
    private final zzayc zzeiv = new zzayc();
    private final zzaxt zzeiw = new zzaxt(zzwu.zzqa(), this.zzeiv);
    private zzaaq zzeix = null;
    private Boolean zzeiy = null;
    private String zzeiz;
    private final AtomicInteger zzeja = new AtomicInteger(0);
    private final zzaxn zzejb = new zzaxn();
    private final Object zzejc = new Object();
    @GuardedBy("mGrantedPermissionLock")
    private zzbcb<ArrayList<String>> zzejd;

    public final zzaaq zzyh() {
        zzaaq zzaaq;
        synchronized (this.mLock) {
            zzaaq = this.zzeix;
        }
        return zzaaq;
    }

    public final void zza(Boolean bool) {
        synchronized (this.mLock) {
            this.zzeiy = bool;
        }
    }

    public final Boolean zzyi() {
        Boolean bool;
        synchronized (this.mLock) {
            bool = this.zzeiy;
        }
        return bool;
    }

    public final void zzal(boolean z) {
        this.zzejb.zzal(z);
    }

    public final boolean zzyj() {
        return this.zzejb.zzyj();
    }

    public final boolean zzyk() {
        return this.zzejb.zzyk();
    }

    public final void zzyl() {
        this.zzejb.zzyl();
    }

    @TargetApi(23)
    public final void zzd(Context context, zzbbi zzbbi) {
        synchronized (this.mLock) {
            if (!this.zzbps) {
                this.mContext = context.getApplicationContext();
                this.zzbob = zzbbi;
                zzbv.zzli().zza(this.zzeiw);
                zzaaq zzaaq = null;
                this.zzeiv.zza(this.mContext, null, true);
                zzare.zzc(this.mContext, this.zzbob);
                this.zzeiz = zzbv.zzlf().zzo(context, zzbbi.zzdp);
                this.zzblu = new zzrf(context.getApplicationContext(), this.zzbob);
                zzbv.zzlo();
                if (((Boolean) zzwu.zzpz().zzd(zzaan.zzcpw)).booleanValue()) {
                    zzaaq = new zzaaq();
                } else {
                    zzaxz.v("CsiReporterFactory: CSI is not enabled. No CSI reporter created.");
                }
                this.zzeix = zzaaq;
                if (this.zzeix != null) {
                    zzbbo.zza((zzbcb) new zzaxm(this).zzwa(), "AppState.registerCsiReporter");
                }
                this.zzbps = true;
                zzyr();
            }
        }
    }

    public final zzrf zzym() {
        return this.zzblu;
    }

    public final Resources getResources() {
        if (this.zzbob.zzeow) {
            return this.mContext.getResources();
        }
        try {
            zzbbe.zzbm(this.mContext).getResources();
            return null;
        } catch (zzbbg e) {
            zzbbd.zzc("Cannot load resource from dynamite apk or local jar", e);
            return null;
        }
    }

    public final void zza(Throwable th, String str) {
        zzare.zzc(this.mContext, this.zzbob).zza(th, str);
    }

    public final void zzb(Throwable th, String str) {
        zzare.zzc(this.mContext, this.zzbob).zza(th, str, ((Float) zzwu.zzpz().zzd(zzaan.zzcoi)).floatValue());
    }

    public final void zzyn() {
        this.zzeja.incrementAndGet();
    }

    public final void zzyo() {
        this.zzeja.decrementAndGet();
    }

    public final int zzyp() {
        return this.zzeja.get();
    }

    @Deprecated
    public final zzayb zzyq() {
        zzayc zzayc;
        synchronized (this.mLock) {
            zzayc = this.zzeiv;
        }
        return zzayc;
    }

    public final Context getApplicationContext() {
        return this.mContext;
    }

    public final zzbcb<ArrayList<String>> zzyr() {
        if (PlatformVersion.isAtLeastJellyBean() && this.mContext != null) {
            if (!((Boolean) zzwu.zzpz().zzd(zzaan.zzctm)).booleanValue()) {
                synchronized (this.zzejc) {
                    zzbcb zzbcb;
                    if (this.zzejd != null) {
                        zzbcb = this.zzejd;
                        return zzbcb;
                    }
                    zzbcb = zzayf.zza(new zzaxl(this));
                    this.zzejd = zzbcb;
                    return zzbcb;
                }
            }
        }
        return zzbbq.zzm(new ArrayList());
    }

    @TargetApi(16)
    private static ArrayList<String> zzae(Context context) {
        ArrayList arrayList = new ArrayList();
        try {
            PackageInfo packageInfo = Wrappers.packageManager(context).getPackageInfo(context.getApplicationInfo().packageName, 4096);
            if (packageInfo.requestedPermissions == null || packageInfo.requestedPermissionsFlags == null) {
                return arrayList;
            }
            for (int i = 0; i < packageInfo.requestedPermissions.length; i++) {
                if ((packageInfo.requestedPermissionsFlags[i] & 2) != 0) {
                    arrayList.add(packageInfo.requestedPermissions[i]);
                }
            }
            return arrayList;
        } catch (NameNotFoundException unused) {
            return arrayList;
        }
    }

    public final zzaxt zzys() {
        return this.zzeiw;
    }

    /* Access modifiers changed, original: final|synthetic */
    public final /* synthetic */ ArrayList zzyt() throws Exception {
        return zzae(zzaum.zzu(this.mContext));
    }
}
