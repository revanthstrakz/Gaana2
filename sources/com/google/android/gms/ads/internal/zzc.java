package com.google.android.gms.ads.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.overlay.zzn;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.ads.zzaan;
import com.google.android.gms.internal.ads.zzaba;
import com.google.android.gms.internal.ads.zzadx;
import com.google.android.gms.internal.ads.zzaeh;
import com.google.android.gms.internal.ads.zzaks;
import com.google.android.gms.internal.ads.zzakz;
import com.google.android.gms.internal.ads.zzalg;
import com.google.android.gms.internal.ads.zzark;
import com.google.android.gms.internal.ads.zzarn;
import com.google.android.gms.internal.ads.zzasj;
import com.google.android.gms.internal.ads.zzatd;
import com.google.android.gms.internal.ads.zzaxf;
import com.google.android.gms.internal.ads.zzaxj;
import com.google.android.gms.internal.ads.zzaxv;
import com.google.android.gms.internal.ads.zzayh;
import com.google.android.gms.internal.ads.zzayp;
import com.google.android.gms.internal.ads.zzbbd;
import com.google.android.gms.internal.ads.zzbbi;
import com.google.android.gms.internal.ads.zzbcg;
import com.google.android.gms.internal.ads.zzbgg;
import com.google.android.gms.internal.ads.zzsx;
import com.google.android.gms.internal.ads.zzuo.zza.zzb;
import com.google.android.gms.internal.ads.zzur;
import com.google.android.gms.internal.ads.zzwb;
import com.google.android.gms.internal.ads.zzwf;
import com.google.android.gms.internal.ads.zzwu;
import java.util.concurrent.Executor;
import org.json.JSONException;
import org.json.JSONObject;

@zzark
public abstract class zzc extends zza implements zzn, zzbo, zzaks {
    protected final zzalg zzbma;
    private transient boolean zzbmb;

    public zzc(Context context, zzwf zzwf, String str, zzalg zzalg, zzbbi zzbbi, zzv zzv) {
        this(new zzbw(context, zzwf, str, zzbbi), zzalg, null, zzv);
    }

    @VisibleForTesting
    private zzc(zzbw zzbw, zzalg zzalg, @Nullable zzbl zzbl, zzv zzv) {
        super(zzbw, null, zzv);
        this.zzbma = zzalg;
        this.zzbmb = false;
    }

    public final boolean zza(zzwb zzwb, zzaba zzaba, int i) {
        if (!zziu()) {
            return false;
        }
        Bundle bundle;
        zzaxj zzzi;
        zzbv.zzlf();
        zzsx zzzo = zzbv.zzlj().zzyq().zzzo();
        String str = null;
        if (zzzo == null) {
            bundle = null;
        } else {
            bundle = zzayh.zza(zzzo);
        }
        this.zzblr.cancel();
        this.zzbls.zzbtw = 0;
        if (((Boolean) zzwu.zzpz().zzd(zzaan.zzcuz)).booleanValue()) {
            zzzi = zzbv.zzlj().zzyq().zzzi();
            zzad zzln = zzbv.zzln();
            Context context = this.zzbls.zzsp;
            zzbbi zzbbi = this.zzbls.zzbsp;
            String str2 = this.zzbls.zzbsn;
            if (zzzi != null) {
                str = zzzi.zzyf();
            }
            zzln.zza(context, zzbbi, false, zzzi, str, str2, null);
        } else {
            zzzi = null;
        }
        return zza(zza(zzwb, bundle, zzzi, i), zzaba);
    }

    public final boolean zza(zzasj zzasj, zzaba zzaba) {
        zzaxv zzatd;
        this.zzbln = zzaba;
        zzaba.zzg("seq_num", zzasj.zzdwj);
        zzaba.zzg("request_id", zzasj.zzdws);
        zzaba.zzg("session_id", zzasj.zzclm);
        if (zzasj.zzdwh != null) {
            zzaba.zzg("app_version", String.valueOf(zzasj.zzdwh.versionCode));
        }
        zzbw zzbw = this.zzbls;
        zzbv.zzlb();
        Context context = this.zzbls.zzsp;
        zzur zzur = this.zzbly.zzbmv;
        if (zzasj.zzdwg.extras.getBundle("sdk_less_server_data") != null) {
            zzatd = new zzatd(context, zzasj, this, zzur);
        } else {
            zzatd = new zzarn(context, zzasj, this, zzur);
        }
        zzatd.zzyz();
        zzbw.zzbsr = zzatd;
        return true;
    }

    public boolean zza(zzwb zzwb, zzaba zzaba) {
        return zza(zzwb, zzaba, 1);
    }

    public final void zzb(zzaxf zzaxf) {
        super.zzb(zzaxf);
        if (zzaxf.zzdnb != null) {
            zzbbd.zzdn("Disable the debug gesture detector on the mediation ad frame.");
            if (this.zzbls.zzbsq != null) {
                this.zzbls.zzbsq.zzmp();
            }
            zzbbd.zzdn("Pinging network fill URLs.");
            zzbv.zzlz();
            zzakz.zza(this.zzbls.zzsp, this.zzbls.zzbsp.zzdp, zzaxf, this.zzbls.zzbsn, false, zzaxf.zzdnb.zzdld);
            if (!(zzaxf.zzehj == null || zzaxf.zzehj.zzdlu == null || zzaxf.zzehj.zzdlu.size() <= 0)) {
                zzbbd.zzdn("Pinging urls remotely");
                zzbv.zzlf().zza(this.zzbls.zzsp, zzaxf.zzehj.zzdlu);
            }
        } else {
            zzbbd.zzdn("Enable the debug gesture detector on the admob ad frame.");
            if (this.zzbls.zzbsq != null) {
                this.zzbls.zzbsq.zzmo();
            }
        }
        if (zzaxf.errorCode == 3 && zzaxf.zzehj != null && zzaxf.zzehj.zzdlt != null) {
            zzbbd.zzdn("Pinging no fill URLs.");
            zzbv.zzlz();
            zzakz.zza(this.zzbls.zzsp, this.zzbls.zzbsp.zzdp, zzaxf, this.zzbls.zzbsn, false, zzaxf.zzehj.zzdlt);
        }
    }

    /* Access modifiers changed, original: protected */
    public boolean zza(@Nullable zzaxf zzaxf, zzaxf zzaxf2) {
        int i;
        if (!(zzaxf == null || zzaxf.zzdne == null)) {
            zzaxf.zzdne.zza(null);
        }
        if (zzaxf2.zzdne != null) {
            zzaxf2.zzdne.zza((zzaks) this);
        }
        int i2 = 0;
        if (zzaxf2.zzehj != null) {
            i2 = zzaxf2.zzehj.zzdmg;
            i = zzaxf2.zzehj.zzdmh;
        } else {
            i = 0;
        }
        this.zzbls.zzbtu.zzl(i2, i);
        return true;
    }

    public void onAdClicked() {
        if (this.zzbls.zzbsu == null) {
            zzbbd.zzeo("Ad state was null when trying to ping click URLs.");
            return;
        }
        if (!(this.zzbls.zzbsu.zzehj == null || this.zzbls.zzbsu.zzehj.zzdlq == null)) {
            zzbv.zzlz();
            zzakz.zza(this.zzbls.zzsp, this.zzbls.zzbsp.zzdp, this.zzbls.zzbsu, this.zzbls.zzbsn, false, zza(this.zzbls.zzbsu.zzehj.zzdlq, this.zzbls.zzbsu.zzdzf));
        }
        if (!(this.zzbls.zzbsu.zzdnb == null || this.zzbls.zzbsu.zzdnb.zzdkz == null)) {
            zzbv.zzlz();
            zzakz.zza(this.zzbls.zzsp, this.zzbls.zzbsp.zzdp, this.zzbls.zzbsu, this.zzbls.zzbsn, false, this.zzbls.zzbsu.zzdnb.zzdkz);
        }
        super.onAdClicked();
    }

    /* Access modifiers changed, original: final */
    public final boolean zza(zzaxf zzaxf) {
        zzwb zzwb;
        boolean z = false;
        if (this.zzblt != null) {
            zzwb = this.zzblt;
            this.zzblt = null;
        } else {
            zzwb = zzaxf.zzdwg;
            if (zzwb.extras != null) {
                z = zzwb.extras.getBoolean("_noRefresh", false);
            }
        }
        return zza(zzwb, zzaxf, z);
    }

    /* Access modifiers changed, original: protected */
    public boolean zza(zzwb zzwb, zzaxf zzaxf, boolean z) {
        if (!z && this.zzbls.zzmj()) {
            if (zzaxf.zzdlx > 0) {
                this.zzblr.zza(zzwb, zzaxf.zzdlx);
            } else if (zzaxf.zzehj != null && zzaxf.zzehj.zzdlx > 0) {
                this.zzblr.zza(zzwb, zzaxf.zzehj.zzdlx);
            } else if (!zzaxf.zzdyd && zzaxf.errorCode == 2) {
                this.zzblr.zzg(zzwb);
            }
        }
        return this.zzblr.zzkv();
    }

    public void pause() {
        Preconditions.checkMainThread("pause must be called on the main UI thread.");
        if (!(this.zzbls.zzbsu == null || this.zzbls.zzbsu.zzdrv == null || !this.zzbls.zzmj())) {
            zzbv.zzlh();
            zzayp.zzi(this.zzbls.zzbsu.zzdrv);
        }
        if (!(this.zzbls.zzbsu == null || this.zzbls.zzbsu.zzdnc == null)) {
            try {
                this.zzbls.zzbsu.zzdnc.pause();
            } catch (RemoteException unused) {
                zzbbd.zzeo("Could not pause mediation adapter.");
            }
        }
        this.zzblu.zzj(this.zzbls.zzbsu);
        this.zzblr.pause();
    }

    public void resume() {
        Preconditions.checkMainThread("resume must be called on the main UI thread.");
        zzbgg zzbgg = (this.zzbls.zzbsu == null || this.zzbls.zzbsu.zzdrv == null) ? null : this.zzbls.zzbsu.zzdrv;
        if (zzbgg != null && this.zzbls.zzmj()) {
            zzbv.zzlh();
            zzayp.zzj(this.zzbls.zzbsu.zzdrv);
        }
        if (!(this.zzbls.zzbsu == null || this.zzbls.zzbsu.zzdnc == null)) {
            try {
                this.zzbls.zzbsu.zzdnc.resume();
            } catch (RemoteException unused) {
                zzbbd.zzeo("Could not resume mediation adapter.");
            }
        }
        if (zzbgg == null || !zzbgg.zzadt()) {
            this.zzblr.resume();
        }
        this.zzblu.zzk(this.zzbls.zzbsu);
    }

    /* Access modifiers changed, original: protected|final */
    public final boolean zzc(zzwb zzwb) {
        return super.zzc(zzwb) && !this.zzbmb;
    }

    /* Access modifiers changed, original: protected */
    public boolean zziu() {
        zzbv.zzlf();
        if (zzayh.zzn(this.zzbls.zzsp, "android.permission.INTERNET")) {
            zzbv.zzlf();
            if (zzayh.zzah(this.zzbls.zzsp)) {
                return true;
            }
        }
        return false;
    }

    public void zziv() {
        this.zzbmb = false;
        zzii();
        this.zzbls.zzbsw.zzxx();
    }

    public void zziw() {
        this.zzbmb = true;
        zzik();
    }

    public final void onPause() {
        this.zzblu.zzj(this.zzbls.zzbsu);
    }

    public final void onResume() {
        this.zzblu.zzk(this.zzbls.zzbsu);
    }

    public void zzix() {
        zzbbd.zzeo("Mediated ad does not support onVideoEnd callback");
    }

    public void zziy() {
        onAdClicked();
    }

    public final void zziz() {
        zziv();
    }

    public final void zzja() {
        zzij();
    }

    public final void zzjb() {
        zziw();
    }

    public final void zzjc() {
        if (this.zzbls.zzbsu != null) {
            String str = this.zzbls.zzbsu.zzdnd;
            StringBuilder stringBuilder = new StringBuilder(74 + String.valueOf(str).length());
            stringBuilder.append("Mediation adapter ");
            stringBuilder.append(str);
            stringBuilder.append(" refreshed, but mediation adapters should never refresh.");
            zzbbd.zzeo(stringBuilder.toString());
        }
        zza(this.zzbls.zzbsu, true);
        zzb(this.zzbls.zzbsu, true);
        zzil();
    }

    public void zzjd() {
        recordImpression();
    }

    public final void zzd(String str, String str2) {
        onAppEvent(str, str2);
    }

    public final void zza(zzadx zzadx, String str) {
        Object customTemplateId;
        zzaeh zzaeh = null;
        if (zzadx != null) {
            try {
                customTemplateId = zzadx.getCustomTemplateId();
            } catch (RemoteException e) {
                zzbbd.zzc("Unable to call onCustomClick.", e);
                return;
            }
        }
        customTemplateId = null;
        if (!(this.zzbls.zzbtg == null || customTemplateId == null)) {
            zzaeh = (zzaeh) this.zzbls.zzbtg.get(customTemplateId);
        }
        if (zzaeh == null) {
            zzbbd.zzeo("Mediation adapter invoked onCustomClick but no listeners were set.");
        } else {
            zzaeh.zzb(zzadx, str);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:34:0x010f  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x0151  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x014a  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x0165  */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x018c  */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x0175  */
    private final com.google.android.gms.internal.ads.zzasj zza(com.google.android.gms.internal.ads.zzwb r69, android.os.Bundle r70, com.google.android.gms.internal.ads.zzaxj r71, int r72) {
        /*
        r68 = this;
        r0 = r68;
        r1 = r0.zzbls;
        r1 = r1.zzsp;
        r7 = r1.getApplicationInfo();
        r2 = 0;
        r3 = r0.zzbls;	 Catch:{ NameNotFoundException -> 0x001b }
        r3 = r3.zzsp;	 Catch:{ NameNotFoundException -> 0x001b }
        r3 = com.google.android.gms.common.wrappers.Wrappers.packageManager(r3);	 Catch:{ NameNotFoundException -> 0x001b }
        r4 = r7.packageName;	 Catch:{ NameNotFoundException -> 0x001b }
        r3 = r3.getPackageInfo(r4, r2);	 Catch:{ NameNotFoundException -> 0x001b }
        r8 = r3;
        goto L_0x001c;
    L_0x001b:
        r8 = 0;
    L_0x001c:
        r3 = r0.zzbls;
        r3 = r3.zzsp;
        r3 = r3.getResources();
        r3 = r3.getDisplayMetrics();
        r4 = r0.zzbls;
        r4 = r4.zzbsq;
        if (r4 == 0) goto L_0x0093;
    L_0x002e:
        r4 = r0.zzbls;
        r4 = r4.zzbsq;
        r4 = r4.getParent();
        if (r4 == 0) goto L_0x0093;
    L_0x0038:
        r4 = 2;
        r4 = new int[r4];
        r5 = r0.zzbls;
        r5 = r5.zzbsq;
        r5.getLocationOnScreen(r4);
        r5 = r4[r2];
        r6 = 1;
        r4 = r4[r6];
        r9 = r0.zzbls;
        r9 = r9.zzbsq;
        r9 = r9.getWidth();
        r10 = r0.zzbls;
        r10 = r10.zzbsq;
        r10 = r10.getHeight();
        r11 = r0.zzbls;
        r11 = r11.zzbsq;
        r11 = r11.isShown();
        if (r11 == 0) goto L_0x0072;
    L_0x0061:
        r11 = r5 + r9;
        if (r11 <= 0) goto L_0x0072;
    L_0x0065:
        r11 = r4 + r10;
        if (r11 <= 0) goto L_0x0072;
    L_0x0069:
        r11 = r3.widthPixels;
        if (r5 > r11) goto L_0x0072;
    L_0x006d:
        r11 = r3.heightPixels;
        if (r4 > r11) goto L_0x0072;
    L_0x0071:
        goto L_0x0073;
    L_0x0072:
        r6 = r2;
    L_0x0073:
        r11 = new android.os.Bundle;
        r12 = 5;
        r11.<init>(r12);
        r12 = "x";
        r11.putInt(r12, r5);
        r5 = "y";
        r11.putInt(r5, r4);
        r4 = "width";
        r11.putInt(r4, r9);
        r4 = "height";
        r11.putInt(r4, r10);
        r4 = "visible";
        r11.putInt(r4, r6);
        goto L_0x0094;
    L_0x0093:
        r11 = 0;
    L_0x0094:
        r4 = r0.zzbls;
        r5 = com.google.android.gms.ads.internal.zzbv.zzlj();
        r5 = r5.zzys();
        r6 = com.google.android.gms.ads.internal.zzbv.zzlm();
        r9 = r0.zzbls;
        r9 = r9.zzbsn;
        r5 = r5.zza(r6, r9);
        r4.zzbsw = r5;
        r4 = r0.zzbls;
        r4 = r4.zzbsw;
        r5 = r69;
        r4.zzn(r5);
        com.google.android.gms.ads.internal.zzbv.zzlf();
        r4 = r0.zzbls;
        r4 = r4.zzsp;
        r6 = r0.zzbls;
        r6 = r6.zzbsq;
        r9 = r0.zzbls;
        r9 = r9.zzbst;
        r20 = com.google.android.gms.internal.ads.zzayh.zza(r4, r6, r9);
        r9 = 0;
        r4 = r0.zzbls;
        r4 = r4.zzbtb;
        if (r4 == 0) goto L_0x00e0;
    L_0x00d0:
        r4 = r0.zzbls;	 Catch:{ RemoteException -> 0x00db }
        r4 = r4.zzbtb;	 Catch:{ RemoteException -> 0x00db }
        r12 = r4.getValue();	 Catch:{ RemoteException -> 0x00db }
        r21 = r12;
        goto L_0x00e2;
    L_0x00db:
        r4 = "Cannot get correlation id, default to 0.";
        com.google.android.gms.internal.ads.zzbbd.zzeo(r4);
    L_0x00e0:
        r21 = r9;
    L_0x00e2:
        r4 = java.util.UUID.randomUUID();
        r23 = r4.toString();
        r4 = com.google.android.gms.ads.internal.zzbv.zzlj();
        r4 = r4.zzys();
        r6 = r0.zzbls;
        r6 = r6.zzsp;
        r12 = r4.zza(r6, r0);
        r14 = new java.util.ArrayList;
        r14.<init>();
        r15 = new java.util.ArrayList;
        r15.<init>();
        r4 = r2;
    L_0x0105:
        r6 = r0.zzbls;
        r6 = r6.zzbth;
        r6 = r6.size();
        if (r4 >= r6) goto L_0x0136;
    L_0x010f:
        r6 = r0.zzbls;
        r6 = r6.zzbth;
        r6 = r6.keyAt(r4);
        r6 = (java.lang.String) r6;
        r14.add(r6);
        r9 = r0.zzbls;
        r9 = r9.zzbtg;
        r9 = r9.containsKey(r6);
        if (r9 == 0) goto L_0x0133;
    L_0x0126:
        r9 = r0.zzbls;
        r9 = r9.zzbtg;
        r9 = r9.get(r6);
        if (r9 == 0) goto L_0x0133;
    L_0x0130:
        r15.add(r6);
    L_0x0133:
        r4 = r4 + 1;
        goto L_0x0105;
    L_0x0136:
        r4 = new com.google.android.gms.ads.internal.zzf;
        r4.<init>(r0);
        r34 = com.google.android.gms.internal.ads.zzayf.zza(r4);
        r4 = new com.google.android.gms.ads.internal.zzg;
        r4.<init>(r0);
        r44 = com.google.android.gms.internal.ads.zzayf.zza(r4);
        if (r71 == 0) goto L_0x0151;
    L_0x014a:
        r4 = r71.zzye();
        r35 = r4;
        goto L_0x0153;
    L_0x0151:
        r35 = 0;
    L_0x0153:
        r4 = r0.zzbls;
        r4 = r4.zzbtt;
        if (r4 == 0) goto L_0x01ab;
    L_0x0159:
        r4 = r0.zzbls;
        r4 = r4.zzbtt;
        r4 = r4.size();
        if (r4 <= 0) goto L_0x01ab;
    L_0x0163:
        if (r8 == 0) goto L_0x0167;
    L_0x0165:
        r2 = r8.versionCode;
    L_0x0167:
        r4 = com.google.android.gms.ads.internal.zzbv.zzlj();
        r4 = r4.zzyq();
        r4 = r4.zzzh();
        if (r2 <= r4) goto L_0x018c;
    L_0x0175:
        r4 = com.google.android.gms.ads.internal.zzbv.zzlj();
        r4 = r4.zzyq();
        r4.zzzn();
        r4 = com.google.android.gms.ads.internal.zzbv.zzlj();
        r4 = r4.zzyq();
        r4.zzcv(r2);
        goto L_0x01ab;
    L_0x018c:
        r2 = com.google.android.gms.ads.internal.zzbv.zzlj();
        r2 = r2.zzyq();
        r2 = r2.zzzm();
        if (r2 == 0) goto L_0x01ab;
    L_0x019a:
        r4 = r0.zzbls;
        r4 = r4.zzbsn;
        r2 = r2.optJSONArray(r4);
        if (r2 == 0) goto L_0x01ab;
    L_0x01a4:
        r2 = r2.toString();
        r46 = r2;
        goto L_0x01ad;
    L_0x01ab:
        r46 = 0;
    L_0x01ad:
        r56 = new com.google.android.gms.internal.ads.zzasj;
        r2 = r0.zzbls;
        r6 = r2.zzbst;
        r2 = r0.zzbls;
        r9 = r2.zzbsn;
        r2 = r0.zzbls;
        r2 = r2.zzbsw;
        r10 = r2.zzxy();
        r13 = com.google.android.gms.internal.ads.zzwu.zzqa();
        r2 = r0.zzbls;
        r4 = r2.zzbsp;
        r2 = r0.zzbls;
        r2 = r2.zzbtt;
        r1 = com.google.android.gms.ads.internal.zzbv.zzlj();
        r1 = r1.zzyq();
        r16 = r1.zzzb();
        r1 = r3.widthPixels;
        r57 = r1;
        r1 = r3.heightPixels;
        r3 = r3.density;
        r24 = com.google.android.gms.internal.ads.zzaan.zzqw();
        r58 = r2;
        r2 = r0.zzbls;
        r2 = r2.zzbsm;
        r59 = r2;
        r2 = r0.zzbls;
        r2 = r2.zzbti;
        r60 = r2;
        r2 = r0.zzbls;
        r27 = r2.zzml();
        r2 = com.google.android.gms.ads.internal.zzbv.zzlk();
        r28 = r2.zzkj();
        r2 = com.google.android.gms.ads.internal.zzbv.zzlk();
        r29 = r2.zzkk();
        com.google.android.gms.ads.internal.zzbv.zzlf();
        r2 = r0.zzbls;
        r2 = r2.zzsp;
        r30 = com.google.android.gms.internal.ads.zzayh.zzas(r2);
        com.google.android.gms.ads.internal.zzbv.zzlf();
        r2 = r0.zzbls;
        r2 = r2.zzbsq;
        r31 = com.google.android.gms.internal.ads.zzayh.zzy(r2);
        r2 = r0.zzbls;
        r2 = r2.zzsp;
        r2 = r2 instanceof android.app.Activity;
        r61 = r2;
        r2 = com.google.android.gms.ads.internal.zzbv.zzlj();
        r2 = r2.zzyq();
        r33 = r2.zzzg();
        r2 = com.google.android.gms.ads.internal.zzbv.zzlj();
        r36 = r2.zzyj();
        r2 = com.google.android.gms.ads.internal.zzbv.zzmd();
        r37 = r2.zzada();
        com.google.android.gms.ads.internal.zzbv.zzlf();
        r38 = com.google.android.gms.internal.ads.zzayh.zzzv();
        r2 = com.google.android.gms.ads.internal.zzbv.zzlp();
        r39 = r2.zzaag();
        r2 = r0.zzbls;
        r2 = r2.zzbtl;
        r62 = r2;
        r2 = com.google.android.gms.ads.internal.zzbv.zzlp();
        r41 = r2.zzaah();
        r2 = com.google.android.gms.internal.ads.zzahq.zzto();
        r42 = r2.zztx();
        r2 = com.google.android.gms.ads.internal.zzbv.zzlj();
        r2 = r2.zzyq();
        r63 = r3;
        r3 = r0.zzbls;
        r3 = r3.zzbsn;
        r43 = r2.zzdu(r3);
        r2 = r0.zzbls;
        r3 = r2.zzbtn;
        r2 = r0.zzbls;
        r2 = r2.zzsp;
        r2 = com.google.android.gms.common.wrappers.Wrappers.packageManager(r2);
        r49 = r2.isCallerInstantApp();
        r2 = com.google.android.gms.ads.internal.zzbv.zzlj();
        r50 = r2.zzyk();
        com.google.android.gms.ads.internal.zzbv.zzlh();
        r51 = com.google.android.gms.internal.ads.zzayp.zzaaa();
        r2 = com.google.android.gms.ads.internal.zzbv.zzlj();
        r2 = r2.zzyr();
        r65 = r3;
        r64 = r4;
        r3 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        r66 = r15;
        r15 = java.util.concurrent.TimeUnit.MILLISECONDS;
        r67 = r1;
        r1 = 0;
        r1 = com.google.android.gms.internal.ads.zzbbq.zza(r2, r1, r3, r15);
        r52 = r1;
        r52 = (java.util.ArrayList) r52;
        r1 = com.google.android.gms.ads.internal.zzbv.zzlf();
        r2 = r0.zzbls;
        r2 = r2.zzsp;
        r53 = r1.zzaw(r2);
        r1 = r0.zzbls;
        r1 = r1.zzbtk;
        r2 = com.google.android.gms.ads.internal.zzbv.zzlf();
        r3 = r0.zzbls;
        r3 = r3.zzsp;
        r55 = r2.zzax(r3);
        r15 = r58;
        r25 = r59;
        r26 = r60;
        r32 = r61;
        r40 = r62;
        r2 = r56;
        r19 = r63;
        r45 = r65;
        r3 = r11;
        r11 = r64;
        r4 = r5;
        r5 = r6;
        r6 = r9;
        r9 = r10;
        r10 = r13;
        r13 = r15;
        r47 = r66;
        r15 = r70;
        r17 = r57;
        r18 = r67;
        r48 = r72;
        r54 = r1;
        r2.<init>(r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r23, r24, r25, r26, r27, r28, r29, r30, r31, r32, r33, r34, r35, r36, r37, r38, r39, r40, r41, r42, r43, r44, r45, r46, r47, r48, r49, r50, r51, r52, r53, r54, r55);
        return r56;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.ads.internal.zzc.zza(com.google.android.gms.internal.ads.zzwb, android.os.Bundle, com.google.android.gms.internal.ads.zzaxj, int):com.google.android.gms.internal.ads.zzasj");
    }

    public final void recordImpression() {
        zza(this.zzbls.zzbsu, false);
    }

    /* Access modifiers changed, original: protected */
    public void zza(@Nullable zzaxf zzaxf, boolean z) {
        if (zzaxf == null) {
            zzbbd.zzeo("Ad state was null when trying to ping impression URLs.");
            return;
        }
        if (zzaxf == null) {
            zzbbd.zzeo("Ad state was null when trying to ping impression URLs.");
        } else {
            zzbbd.zzdn("Pinging Impression URLs.");
            if (this.zzbls.zzbsw != null) {
                this.zzbls.zzbsw.zzxv();
            }
            zzaxf.zzehw.zza(zzb.AD_IMPRESSION);
            if (!(zzaxf.zzdlr == null || zzaxf.zzehq)) {
                zzbv.zzlf();
                zzayh.zza(this.zzbls.zzsp, this.zzbls.zzbsp.zzdp, zza(zzaxf.zzdlr, zzaxf.zzdzf));
                zzaxf.zzehq = true;
            }
        }
        if (!zzaxf.zzehs || z) {
            if (!(zzaxf.zzehj == null || zzaxf.zzehj.zzdlr == null)) {
                zzbv.zzlz();
                zzakz.zza(this.zzbls.zzsp, this.zzbls.zzbsp.zzdp, zzaxf, this.zzbls.zzbsn, z, zza(zzaxf.zzehj.zzdlr, zzaxf.zzdzf));
            }
            if (!(zzaxf.zzdnb == null || zzaxf.zzdnb.zzdla == null)) {
                zzbv.zzlz();
                zzakz.zza(this.zzbls.zzsp, this.zzbls.zzbsp.zzdp, zzaxf, this.zzbls.zzbsn, z, zzaxf.zzdnb.zzdla);
            }
            zzaxf.zzehs = true;
        }
    }

    /* Access modifiers changed, original: protected|final */
    public final void zzb(@Nullable zzaxf zzaxf, boolean z) {
        if (zzaxf != null) {
            if (!(zzaxf == null || zzaxf.zzdls == null || zzaxf.zzehr)) {
                zzbv.zzlf();
                zzayh.zza(this.zzbls.zzsp, this.zzbls.zzbsp.zzdp, zzc(zzaxf.zzdls));
                zzaxf.zzehr = true;
            }
            if (!zzaxf.zzeht || z) {
                if (!(zzaxf.zzehj == null || zzaxf.zzehj.zzdls == null)) {
                    zzbv.zzlz();
                    zzakz.zza(this.zzbls.zzsp, this.zzbls.zzbsp.zzdp, zzaxf, this.zzbls.zzbsn, z, zzc(zzaxf.zzehj.zzdls));
                }
                if (!(zzaxf.zzdnb == null || zzaxf.zzdnb.zzdlb == null)) {
                    zzbv.zzlz();
                    zzakz.zza(this.zzbls.zzsp, this.zzbls.zzbsp.zzdp, zzaxf, this.zzbls.zzbsn, z, zzaxf.zzdnb.zzdlb);
                }
                zzaxf.zzeht = true;
            }
        }
    }

    @Nullable
    public final String getMediationAdapterClassName() {
        if (this.zzbls.zzbsu == null) {
            return null;
        }
        return this.zzbls.zzbsu.zzdnd;
    }

    @Nullable
    public final String zzje() {
        if (this.zzbls.zzbsu == null) {
            return null;
        }
        return zzc(this.zzbls.zzbsu);
    }

    @Nullable
    static String zzc(zzaxf zzaxf) {
        if (zzaxf == null) {
            return null;
        }
        String str = zzaxf.zzdnd;
        Object obj = ("com.google.android.gms.ads.mediation.customevent.CustomEventAdapter".equals(str) || "com.google.ads.mediation.customevent.CustomEventAdapter".equals(str)) ? 1 : null;
        if (!(obj == null || zzaxf.zzdnb == null)) {
            try {
                return new JSONObject(zzaxf.zzdnb.zzdle).getString("class_name");
            } catch (NullPointerException | JSONException unused) {
            }
        }
        return str;
    }

    public void showInterstitial() {
        zzbbd.zzeo("showInterstitial is not supported for current ad type");
    }

    public final void zzjf() {
        Executor executor = zzbcg.zzepo;
        zzbl zzbl = this.zzblr;
        zzbl.getClass();
        executor.execute(zzd.zza(zzbl));
    }

    public final void zzjg() {
        Executor executor = zzbcg.zzepo;
        zzbl zzbl = this.zzblr;
        zzbl.getClass();
        executor.execute(zze.zza(zzbl));
    }
}
