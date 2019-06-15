package com.google.android.gms.ads.internal.overlay;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.webkit.WebChromeClient.CustomViewCallback;
import android.widget.FrameLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.common.util.GmsVersion;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.ads.zzaan;
import com.google.android.gms.internal.ads.zzaok;
import com.google.android.gms.internal.ads.zzaoq;
import com.google.android.gms.internal.ads.zzark;
import com.google.android.gms.internal.ads.zzayh;
import com.google.android.gms.internal.ads.zzayp;
import com.google.android.gms.internal.ads.zzbbd;
import com.google.android.gms.internal.ads.zzbgg;
import com.google.android.gms.internal.ads.zzwu;
import java.util.Collections;

@zzark
public class zzd extends zzaoq implements zzw {
    @VisibleForTesting
    private static final int zzdqt = Color.argb(0, 0, 0, 0);
    protected final Activity mActivity;
    @VisibleForTesting
    zzbgg zzdin;
    @VisibleForTesting
    AdOverlayInfoParcel zzdqu;
    @VisibleForTesting
    private zzi zzdqv;
    @VisibleForTesting
    private zzo zzdqw;
    @VisibleForTesting
    private boolean zzdqx = false;
    @VisibleForTesting
    private FrameLayout zzdqy;
    @VisibleForTesting
    private CustomViewCallback zzdqz;
    @VisibleForTesting
    private boolean zzdra = false;
    @VisibleForTesting
    private boolean zzdrb = false;
    @VisibleForTesting
    private zzh zzdrc;
    @VisibleForTesting
    private boolean zzdrd = false;
    @VisibleForTesting
    int zzdre = 0;
    private final Object zzdrf = new Object();
    private Runnable zzdrg;
    private boolean zzdrh;
    private boolean zzdri;
    private boolean zzdrj = false;
    private boolean zzdrk = false;
    private boolean zzdrl = true;

    public zzd(Activity activity) {
        this.mActivity = activity;
    }

    public final void onActivityResult(int i, int i2, Intent intent) {
    }

    public final void onRestart() {
    }

    public final void close() {
        this.zzdre = 2;
        this.mActivity.finish();
    }

    public final void zzvo() {
        if (this.zzdqu != null && this.zzdqx) {
            setRequestedOrientation(this.zzdqu.orientation);
        }
        if (this.zzdqy != null) {
            this.mActivity.setContentView(this.zzdrc);
            this.zzdri = true;
            this.zzdqy.removeAllViews();
            this.zzdqy = null;
        }
        if (this.zzdqz != null) {
            this.zzdqz.onCustomViewHidden();
            this.zzdqz = null;
        }
        this.zzdqx = false;
    }

    public final void zzvp() {
        this.zzdre = 1;
        this.mActivity.finish();
    }

    public final void onBackPressed() {
        this.zzdre = 0;
    }

    public final boolean zzvq() {
        this.zzdre = 0;
        if (this.zzdin == null) {
            return true;
        }
        boolean zzads = this.zzdin.zzads();
        if (!zzads) {
            this.zzdin.zza("onbackblocked", Collections.emptyMap());
        }
        return zzads;
    }

    public void onCreate(Bundle bundle) {
        this.mActivity.requestWindowFeature(1);
        this.zzdra = bundle != null ? bundle.getBoolean("com.google.android.gms.ads.internal.overlay.hasResumed", false) : false;
        try {
            this.zzdqu = AdOverlayInfoParcel.zzc(this.mActivity.getIntent());
            if (this.zzdqu == null) {
                throw new zzg("Could not get info for ad overlay.");
            }
            if (this.zzdqu.zzbsp.zzeov > GmsVersion.VERSION_QUESO) {
                this.zzdre = 3;
            }
            if (this.mActivity.getIntent() != null) {
                this.zzdrl = this.mActivity.getIntent().getBooleanExtra("shouldCallOnOverlayOpened", true);
            }
            if (this.zzdqu.zzdsc != null) {
                this.zzdrb = this.zzdqu.zzdsc.zzbpa;
            } else {
                this.zzdrb = false;
            }
            if (this.zzdrb && this.zzdqu.zzdsc.zzbpf != -1) {
                new zzj(this, null).zzyz();
            }
            if (bundle == null) {
                if (this.zzdqu.zzdru != null && this.zzdrl) {
                    this.zzdqu.zzdru.zziw();
                }
                if (!(this.zzdqu.zzdsa == 1 || this.zzdqu.zzdrt == null)) {
                    this.zzdqu.zzdrt.onAdClicked();
                }
            }
            this.zzdrc = new zzh(this.mActivity, this.zzdqu.zzdsb, this.zzdqu.zzbsp.zzdp);
            this.zzdrc.setId(1000);
            switch (this.zzdqu.zzdsa) {
                case 1:
                    zzae(false);
                    return;
                case 2:
                    this.zzdqv = new zzi(this.zzdqu.zzdrv);
                    zzae(false);
                    return;
                case 3:
                    zzae(true);
                    return;
                default:
                    throw new zzg("Could not determine ad overlay type.");
            }
        } catch (zzg e) {
            zzbbd.zzeo(e.getMessage());
            this.zzdre = 3;
            this.mActivity.finish();
        }
    }

    public final void onStart() {
        if (((Boolean) zzwu.zzpz().zzd(zzaan.zzcwm)).booleanValue()) {
            if (this.zzdin == null || this.zzdin.isDestroyed()) {
                zzbbd.zzeo("The webview does not exist. Ignoring action.");
            } else {
                zzbv.zzlh();
                zzayp.zzj(this.zzdin);
            }
        }
    }

    public final void onResume() {
        if (this.zzdqu.zzdru != null) {
            this.zzdqu.zzdru.onResume();
        }
        if (!((Boolean) zzwu.zzpz().zzd(zzaan.zzcwm)).booleanValue()) {
            if (this.zzdin == null || this.zzdin.isDestroyed()) {
                zzbbd.zzeo("The webview does not exist. Ignoring action.");
            } else {
                zzbv.zzlh();
                zzayp.zzj(this.zzdin);
            }
        }
    }

    public final void onPause() {
        zzvo();
        if (this.zzdqu.zzdru != null) {
            this.zzdqu.zzdru.onPause();
        }
        if (!(((Boolean) zzwu.zzpz().zzd(zzaan.zzcwm)).booleanValue() || this.zzdin == null || (this.mActivity.isFinishing() && this.zzdqv != null))) {
            zzbv.zzlh();
            zzayp.zzi(this.zzdin);
        }
        zzvs();
    }

    public final void zzq(IObjectWrapper iObjectWrapper) {
        if (((Boolean) zzwu.zzpz().zzd(zzaan.zzcwl)).booleanValue() && PlatformVersion.isAtLeastN()) {
            Configuration configuration = (Configuration) ObjectWrapper.unwrap(iObjectWrapper);
            zzbv.zzlf();
            if (zzayh.zza(this.mActivity, configuration)) {
                this.mActivity.getWindow().addFlags(1024);
                this.mActivity.getWindow().clearFlags(2048);
                return;
            }
            this.mActivity.getWindow().addFlags(2048);
            this.mActivity.getWindow().clearFlags(1024);
        }
    }

    public final void onSaveInstanceState(Bundle bundle) {
        bundle.putBoolean("com.google.android.gms.ads.internal.overlay.hasResumed", this.zzdra);
    }

    public final void onStop() {
        if (((Boolean) zzwu.zzpz().zzd(zzaan.zzcwm)).booleanValue() && this.zzdin != null && (!this.mActivity.isFinishing() || this.zzdqv == null)) {
            zzbv.zzlh();
            zzayp.zzi(this.zzdin);
        }
        zzvs();
    }

    public final void onDestroy() {
        if (this.zzdin != null) {
            this.zzdrc.removeView(this.zzdin.getView());
        }
        zzvs();
    }

    private final void zzad(boolean z) {
        int intValue = ((Integer) zzwu.zzpz().zzd(zzaan.zzcwo)).intValue();
        zzp zzp = new zzp();
        zzp.size = 50;
        zzp.paddingLeft = z ? intValue : 0;
        zzp.paddingRight = z ? 0 : intValue;
        zzp.paddingTop = 0;
        zzp.paddingBottom = intValue;
        this.zzdqw = new zzo(this.mActivity, zzp, this);
        LayoutParams layoutParams = new LayoutParams(-2, -2);
        layoutParams.addRule(10);
        layoutParams.addRule(z ? 11 : 9);
        zza(z, this.zzdqu.zzdrx);
        this.zzdrc.addView(this.zzdqw, layoutParams);
    }

    public final void zzay() {
        this.zzdri = true;
    }

    public final void zza(boolean z, boolean z2) {
        boolean z3 = false;
        boolean z4 = ((Boolean) zzwu.zzpz().zzd(zzaan.zzcrt)).booleanValue() && this.zzdqu != null && this.zzdqu.zzdsc != null && this.zzdqu.zzdsc.zzbph;
        boolean z5 = ((Boolean) zzwu.zzpz().zzd(zzaan.zzcru)).booleanValue() && this.zzdqu != null && this.zzdqu.zzdsc != null && this.zzdqu.zzdsc.zzbpi;
        if (z && z2 && z4 && !z5) {
            new zzaok(this.zzdin, "useCustomClose").zzda("Custom close has been disabled for interstitial ads in this ad slot.");
        }
        if (this.zzdqw != null) {
            zzo zzo = this.zzdqw;
            if (z5 || (z2 && !z4)) {
                z3 = true;
            }
            zzo.zzaf(z3);
        }
    }

    public final void zzvr() {
        this.zzdrc.removeView(this.zzdqw);
        zzad(true);
    }

    public final void setRequestedOrientation(int i) {
        if (this.mActivity.getApplicationInfo().targetSdkVersion >= ((Integer) zzwu.zzpz().zzd(zzaan.zzcyg)).intValue()) {
            if (this.mActivity.getApplicationInfo().targetSdkVersion <= ((Integer) zzwu.zzpz().zzd(zzaan.zzcyh)).intValue()) {
                if (VERSION.SDK_INT >= ((Integer) zzwu.zzpz().zzd(zzaan.zzcyi)).intValue()) {
                    if (VERSION.SDK_INT <= ((Integer) zzwu.zzpz().zzd(zzaan.zzcyj)).intValue()) {
                        return;
                    }
                }
            }
        }
        this.mActivity.setRequestedOrientation(i);
    }

    public final void zza(View view, CustomViewCallback customViewCallback) {
        this.zzdqy = new FrameLayout(this.mActivity);
        this.zzdqy.setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
        this.zzdqy.addView(view, -1, -1);
        this.mActivity.setContentView(this.zzdqy);
        this.zzdri = true;
        this.zzdqz = customViewCallback;
        this.zzdqx = true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:33:0x0095  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x008c  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x009d  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0098  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00a2  */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x010b  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x011c  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x0114  */
    /* JADX WARNING: Removed duplicated region for block: B:91:0x0207  */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x012e A:{SYNTHETIC, Splitter:B:60:0x012e} */
    /* JADX WARNING: Removed duplicated region for block: B:94:0x021f  */
    /* JADX WARNING: Removed duplicated region for block: B:102:0x0247  */
    /* JADX WARNING: Removed duplicated region for block: B:112:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:110:0x026c  */
    private final void zzae(boolean r20) throws com.google.android.gms.ads.internal.overlay.zzg {
        /*
        r19 = this;
        r1 = r19;
        r3 = r1.zzdri;
        r4 = 1;
        if (r3 != 0) goto L_0x000c;
    L_0x0007:
        r3 = r1.mActivity;
        r3.requestWindowFeature(r4);
    L_0x000c:
        r3 = r1.mActivity;
        r3 = r3.getWindow();
        if (r3 != 0) goto L_0x001c;
    L_0x0014:
        r2 = new com.google.android.gms.ads.internal.overlay.zzg;
        r3 = "Invalid activity, no window available.";
        r2.<init>(r3);
        throw r2;
    L_0x001c:
        r5 = com.google.android.gms.common.util.PlatformVersion.isAtLeastN();
        if (r5 == 0) goto L_0x0048;
    L_0x0022:
        r5 = com.google.android.gms.internal.ads.zzaan.zzcwl;
        r6 = com.google.android.gms.internal.ads.zzwu.zzpz();
        r5 = r6.zzd(r5);
        r5 = (java.lang.Boolean) r5;
        r5 = r5.booleanValue();
        if (r5 == 0) goto L_0x0048;
    L_0x0034:
        com.google.android.gms.ads.internal.zzbv.zzlf();
        r5 = r1.mActivity;
        r6 = r1.mActivity;
        r6 = r6.getResources();
        r6 = r6.getConfiguration();
        r5 = com.google.android.gms.internal.ads.zzayh.zza(r5, r6);
        goto L_0x0049;
    L_0x0048:
        r5 = r4;
    L_0x0049:
        r6 = r1.zzdqu;
        r6 = r6.zzdsc;
        r7 = 0;
        if (r6 == 0) goto L_0x005a;
    L_0x0050:
        r6 = r1.zzdqu;
        r6 = r6.zzdsc;
        r6 = r6.zzbpb;
        if (r6 == 0) goto L_0x005a;
    L_0x0058:
        r6 = r4;
        goto L_0x005b;
    L_0x005a:
        r6 = r7;
    L_0x005b:
        r8 = r1.zzdrb;
        if (r8 == 0) goto L_0x0061;
    L_0x005f:
        if (r6 == 0) goto L_0x0085;
    L_0x0061:
        if (r5 == 0) goto L_0x0085;
    L_0x0063:
        r5 = 1024; // 0x400 float:1.435E-42 double:5.06E-321;
        r3.setFlags(r5, r5);
        r5 = com.google.android.gms.common.util.PlatformVersion.isAtLeastKitKat();
        if (r5 == 0) goto L_0x0085;
    L_0x006e:
        r5 = r1.zzdqu;
        r5 = r5.zzdsc;
        if (r5 == 0) goto L_0x0085;
    L_0x0074:
        r5 = r1.zzdqu;
        r5 = r5.zzdsc;
        r5 = r5.zzbpg;
        if (r5 == 0) goto L_0x0085;
    L_0x007c:
        r5 = r3.getDecorView();
        r6 = 4098; // 0x1002 float:5.743E-42 double:2.0247E-320;
        r5.setSystemUiVisibility(r6);
    L_0x0085:
        r5 = r1.zzdqu;
        r5 = r5.zzdrv;
        r6 = 0;
        if (r5 == 0) goto L_0x0095;
    L_0x008c:
        r5 = r1.zzdqu;
        r5 = r5.zzdrv;
        r5 = r5.zzadl();
        goto L_0x0096;
    L_0x0095:
        r5 = r6;
    L_0x0096:
        if (r5 == 0) goto L_0x009d;
    L_0x0098:
        r5 = r5.zzmu();
        goto L_0x009e;
    L_0x009d:
        r5 = r7;
    L_0x009e:
        r1.zzdrd = r7;
        if (r5 == 0) goto L_0x00e2;
    L_0x00a2:
        r8 = r1.zzdqu;
        r8 = r8.orientation;
        r9 = com.google.android.gms.ads.internal.zzbv.zzlh();
        r9 = r9.zzzw();
        if (r8 != r9) goto L_0x00c2;
    L_0x00b0:
        r8 = r1.mActivity;
        r8 = r8.getResources();
        r8 = r8.getConfiguration();
        r8 = r8.orientation;
        if (r8 != r4) goto L_0x00bf;
    L_0x00be:
        r7 = r4;
    L_0x00bf:
        r1.zzdrd = r7;
        goto L_0x00e2;
    L_0x00c2:
        r8 = r1.zzdqu;
        r8 = r8.orientation;
        r9 = com.google.android.gms.ads.internal.zzbv.zzlh();
        r9 = r9.zzzx();
        if (r8 != r9) goto L_0x00e2;
    L_0x00d0:
        r8 = r1.mActivity;
        r8 = r8.getResources();
        r8 = r8.getConfiguration();
        r8 = r8.orientation;
        r9 = 2;
        if (r8 != r9) goto L_0x00e0;
    L_0x00df:
        r7 = r4;
    L_0x00e0:
        r1.zzdrd = r7;
    L_0x00e2:
        r7 = r1.zzdrd;
        r8 = 46;
        r9 = new java.lang.StringBuilder;
        r9.<init>(r8);
        r8 = "Delay onShow to next orientation change: ";
        r9.append(r8);
        r9.append(r7);
        r7 = r9.toString();
        com.google.android.gms.internal.ads.zzbbd.zzdn(r7);
        r7 = r1.zzdqu;
        r7 = r7.orientation;
        r1.setRequestedOrientation(r7);
        r7 = com.google.android.gms.ads.internal.zzbv.zzlh();
        r3 = r7.zza(r3);
        if (r3 == 0) goto L_0x0110;
    L_0x010b:
        r3 = "Hardware acceleration on the AdActivity window enabled.";
        com.google.android.gms.internal.ads.zzbbd.zzdn(r3);
    L_0x0110:
        r3 = r1.zzdrb;
        if (r3 != 0) goto L_0x011c;
    L_0x0114:
        r3 = r1.zzdrc;
        r7 = -16777216; // 0xffffffffff000000 float:-1.7014118E38 double:NaN;
        r3.setBackgroundColor(r7);
        goto L_0x0123;
    L_0x011c:
        r3 = r1.zzdrc;
        r7 = zzdqt;
        r3.setBackgroundColor(r7);
    L_0x0123:
        r3 = r1.mActivity;
        r7 = r1.zzdrc;
        r3.setContentView(r7);
        r1.zzdri = r4;
        if (r20 == 0) goto L_0x0207;
    L_0x012e:
        com.google.android.gms.ads.internal.zzbv.zzlg();	 Catch:{ Exception -> 0x01f8 }
        r8 = r1.mActivity;	 Catch:{ Exception -> 0x01f8 }
        r3 = r1.zzdqu;	 Catch:{ Exception -> 0x01f8 }
        r3 = r3.zzdrv;	 Catch:{ Exception -> 0x01f8 }
        if (r3 == 0) goto L_0x0143;
    L_0x0139:
        r3 = r1.zzdqu;	 Catch:{ Exception -> 0x01f8 }
        r3 = r3.zzdrv;	 Catch:{ Exception -> 0x01f8 }
        r3 = r3.zzadj();	 Catch:{ Exception -> 0x01f8 }
        r9 = r3;
        goto L_0x0144;
    L_0x0143:
        r9 = r6;
    L_0x0144:
        r3 = r1.zzdqu;	 Catch:{ Exception -> 0x01f8 }
        r3 = r3.zzdrv;	 Catch:{ Exception -> 0x01f8 }
        if (r3 == 0) goto L_0x0154;
    L_0x014a:
        r3 = r1.zzdqu;	 Catch:{ Exception -> 0x01f8 }
        r3 = r3.zzdrv;	 Catch:{ Exception -> 0x01f8 }
        r3 = r3.zzadk();	 Catch:{ Exception -> 0x01f8 }
        r10 = r3;
        goto L_0x0155;
    L_0x0154:
        r10 = r6;
    L_0x0155:
        r11 = 1;
        r13 = 0;
        r3 = r1.zzdqu;	 Catch:{ Exception -> 0x01f8 }
        r14 = r3.zzbsp;	 Catch:{ Exception -> 0x01f8 }
        r15 = 0;
        r16 = 0;
        r3 = r1.zzdqu;	 Catch:{ Exception -> 0x01f8 }
        r3 = r3.zzdrv;	 Catch:{ Exception -> 0x01f8 }
        if (r3 == 0) goto L_0x016f;
    L_0x0164:
        r3 = r1.zzdqu;	 Catch:{ Exception -> 0x01f8 }
        r3 = r3.zzdrv;	 Catch:{ Exception -> 0x01f8 }
        r3 = r3.zzid();	 Catch:{ Exception -> 0x01f8 }
        r17 = r3;
        goto L_0x0171;
    L_0x016f:
        r17 = r6;
    L_0x0171:
        r18 = com.google.android.gms.internal.ads.zzum.zzoi();	 Catch:{ Exception -> 0x01f8 }
        r12 = r5;
        r3 = com.google.android.gms.internal.ads.zzbgm.zza(r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18);	 Catch:{ Exception -> 0x01f8 }
        r1.zzdin = r3;	 Catch:{ Exception -> 0x01f8 }
        r3 = r1.zzdin;
        r7 = r3.zzadl();
        r8 = 0;
        r3 = r1.zzdqu;
        r9 = r3.zzdsd;
        r10 = 0;
        r3 = r1.zzdqu;
        r11 = r3.zzdrw;
        r3 = r1.zzdqu;
        r12 = r3.zzdrz;
        r13 = 1;
        r14 = 0;
        r3 = r1.zzdqu;
        r3 = r3.zzdrv;
        if (r3 == 0) goto L_0x01a4;
    L_0x0198:
        r3 = r1.zzdqu;
        r3 = r3.zzdrv;
        r3 = r3.zzadl();
        r6 = r3.zzaea();
    L_0x01a4:
        r15 = r6;
        r16 = 0;
        r17 = 0;
        r7.zza(r8, r9, r10, r11, r12, r13, r14, r15, r16, r17);
        r3 = r1.zzdin;
        r3 = r3.zzadl();
        r6 = new com.google.android.gms.ads.internal.overlay.zze;
        r6.<init>(r1);
        r3.zza(r6);
        r3 = r1.zzdqu;
        r3 = r3.url;
        if (r3 == 0) goto L_0x01ca;
    L_0x01c0:
        r3 = r1.zzdin;
        r6 = r1.zzdqu;
        r6 = r6.url;
        r3.loadUrl(r6);
        goto L_0x01e2;
    L_0x01ca:
        r3 = r1.zzdqu;
        r3 = r3.zzdry;
        if (r3 == 0) goto L_0x01f0;
    L_0x01d0:
        r6 = r1.zzdin;
        r3 = r1.zzdqu;
        r7 = r3.zzbde;
        r3 = r1.zzdqu;
        r8 = r3.zzdry;
        r9 = "text/html";
        r10 = "UTF-8";
        r11 = 0;
        r6.loadDataWithBaseURL(r7, r8, r9, r10, r11);
    L_0x01e2:
        r3 = r1.zzdqu;
        r3 = r3.zzdrv;
        if (r3 == 0) goto L_0x0214;
    L_0x01e8:
        r3 = r1.zzdqu;
        r3 = r3.zzdrv;
        r3.zzb(r1);
        goto L_0x0214;
    L_0x01f0:
        r2 = new com.google.android.gms.ads.internal.overlay.zzg;
        r3 = "No URL or HTML to display in ad overlay.";
        r2.<init>(r3);
        throw r2;
    L_0x01f8:
        r0 = move-exception;
        r2 = r0;
        r3 = "Error obtaining webview.";
        com.google.android.gms.internal.ads.zzbbd.zzb(r3, r2);
        r2 = new com.google.android.gms.ads.internal.overlay.zzg;
        r3 = "Could not obtain webview for the overlay.";
        r2.<init>(r3);
        throw r2;
    L_0x0207:
        r3 = r1.zzdqu;
        r3 = r3.zzdrv;
        r1.zzdin = r3;
        r3 = r1.zzdin;
        r6 = r1.mActivity;
        r3.zzbo(r6);
    L_0x0214:
        r3 = r1.zzdin;
        r3.zza(r1);
        r3 = r1.zzdqu;
        r3 = r3.zzdrv;
        if (r3 == 0) goto L_0x022c;
    L_0x021f:
        r3 = r1.zzdqu;
        r3 = r3.zzdrv;
        r3 = r3.zzadp();
        r6 = r1.zzdrc;
        zzb(r3, r6);
    L_0x022c:
        r3 = r1.zzdin;
        r3 = r3.getParent();
        if (r3 == 0) goto L_0x0243;
    L_0x0234:
        r6 = r3 instanceof android.view.ViewGroup;
        if (r6 == 0) goto L_0x0243;
    L_0x0238:
        r3 = (android.view.ViewGroup) r3;
        r6 = r1.zzdin;
        r6 = r6.getView();
        r3.removeView(r6);
    L_0x0243:
        r3 = r1.zzdrb;
        if (r3 == 0) goto L_0x024c;
    L_0x0247:
        r3 = r1.zzdin;
        r3.zzady();
    L_0x024c:
        r3 = r1.zzdrc;
        r6 = r1.zzdin;
        r6 = r6.getView();
        r7 = -1;
        r3.addView(r6, r7, r7);
        if (r20 != 0) goto L_0x0261;
    L_0x025a:
        r2 = r1.zzdrd;
        if (r2 != 0) goto L_0x0261;
    L_0x025e:
        r19.zzvv();
    L_0x0261:
        r1.zzad(r5);
        r2 = r1.zzdin;
        r2 = r2.zzadn();
        if (r2 == 0) goto L_0x026f;
    L_0x026c:
        r1.zza(r5, r4);
    L_0x026f:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.ads.internal.overlay.zzd.zzae(boolean):void");
    }

    private final void zzvs() {
        if (this.mActivity.isFinishing() && !this.zzdrj) {
            this.zzdrj = true;
            if (this.zzdin != null) {
                this.zzdin.zzdh(this.zzdre);
                synchronized (this.zzdrf) {
                    if (this.zzdrh || !this.zzdin.zzadu()) {
                    } else {
                        this.zzdrg = new zzf(this);
                        zzayh.zzelc.postDelayed(this.zzdrg, ((Long) zzwu.zzpz().zzd(zzaan.zzcrs)).longValue());
                        return;
                    }
                }
            }
            zzvt();
        }
    }

    /* Access modifiers changed, original: final */
    @VisibleForTesting
    public final void zzvt() {
        if (!this.zzdrk) {
            this.zzdrk = true;
            if (this.zzdin != null) {
                this.zzdrc.removeView(this.zzdin.getView());
                if (this.zzdqv != null) {
                    this.zzdin.zzbo(this.zzdqv.zzsp);
                    this.zzdin.zzav(false);
                    this.zzdqv.parent.addView(this.zzdin.getView(), this.zzdqv.index, this.zzdqv.zzdrp);
                    this.zzdqv = null;
                } else if (this.mActivity.getApplicationContext() != null) {
                    this.zzdin.zzbo(this.mActivity.getApplicationContext());
                }
                this.zzdin = null;
            }
            if (!(this.zzdqu == null || this.zzdqu.zzdru == null)) {
                this.zzdqu.zzdru.zziv();
            }
            if (!(this.zzdqu == null || this.zzdqu.zzdrv == null)) {
                zzb(this.zzdqu.zzdrv.zzadp(), this.zzdqu.zzdrv.getView());
            }
        }
    }

    private static void zzb(@Nullable IObjectWrapper iObjectWrapper, @Nullable View view) {
        if (iObjectWrapper != null && view != null) {
            zzbv.zzlw().zza(iObjectWrapper, view);
        }
    }

    public final void zzvu() {
        if (this.zzdrd) {
            this.zzdrd = false;
            zzvv();
        }
    }

    private final void zzvv() {
        this.zzdin.zzvv();
    }

    public final void zzvw() {
        this.zzdrc.zzdro = true;
    }

    public final void zzvx() {
        synchronized (this.zzdrf) {
            this.zzdrh = true;
            if (this.zzdrg != null) {
                zzayh.zzelc.removeCallbacks(this.zzdrg);
                zzayh.zzelc.post(this.zzdrg);
            }
        }
    }
}
