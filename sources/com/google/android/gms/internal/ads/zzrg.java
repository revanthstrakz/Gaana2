package com.google.android.gms.internal.ads;

import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.PowerManager;
import android.provider.Settings.System;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import android.view.WindowManager;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.common.util.VisibleForTesting;
import com.integralads.avid.library.inmobi.utils.AvidJSONUtil;
import com.moengage.inapp.InAppMessage;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@zzark
public final class zzrg implements OnGlobalLayoutListener, OnScrollChangedListener {
    private final Object mLock = new Object();
    private boolean zzbqq = false;
    private zzbai zzbua;
    private final Context zzbup;
    private final WeakReference<zzaxf> zzbur;
    private WeakReference<ViewTreeObserver> zzbus;
    private final zzsq zzbut;
    protected final zzre zzbuu;
    private final WindowManager zzbuv;
    private final PowerManager zzbuw;
    private final KeyguardManager zzbux;
    private final DisplayMetrics zzbuy;
    @Nullable
    private zzrn zzbuz;
    private boolean zzbva;
    private boolean zzbvb = false;
    private boolean zzbvc;
    private boolean zzbvd;
    private boolean zzbve;
    @Nullable
    @VisibleForTesting
    private BroadcastReceiver zzbvf;
    private final HashSet<zzrd> zzbvg = new HashSet();
    private final HashSet<zzsb> zzbvh = new HashSet();
    private final Rect zzbvi = new Rect();
    private final zzrj zzbvj;
    private float zzbvk;

    public zzrg(Context context, zzwf zzwf, zzaxf zzaxf, zzbbi zzbbi, zzsq zzsq) {
        this.zzbur = new WeakReference(zzaxf);
        this.zzbut = zzsq;
        this.zzbus = new WeakReference(null);
        this.zzbvc = true;
        this.zzbve = false;
        this.zzbua = new zzbai(200);
        this.zzbuu = new zzre(UUID.randomUUID().toString(), zzbbi, zzwf.zzckk, zzaxf.zzehh, zzaxf.zzmu(), zzwf.zzckn);
        this.zzbuv = (WindowManager) context.getSystemService("window");
        this.zzbuw = (PowerManager) context.getApplicationContext().getSystemService("power");
        this.zzbux = (KeyguardManager) context.getSystemService("keyguard");
        this.zzbup = context;
        this.zzbvj = new zzrj(this, new Handler());
        this.zzbup.getContentResolver().registerContentObserver(System.CONTENT_URI, true, this.zzbvj);
        this.zzbuy = context.getResources().getDisplayMetrics();
        Display defaultDisplay = this.zzbuv.getDefaultDisplay();
        this.zzbvi.right = defaultDisplay.getWidth();
        this.zzbvi.bottom = defaultDisplay.getHeight();
        zzmw();
    }

    public final void zzmw() {
        this.zzbvk = zzaza.zzbb(this.zzbup);
    }

    public final void zzmx() {
        synchronized (this.mLock) {
            if (this.zzbvc) {
                this.zzbvd = true;
                try {
                    JSONObject zznb = zznb();
                    zznb.put("doneReasonCode", "u");
                    zza(zznb, true);
                } catch (JSONException e) {
                    zzbbd.zzb("JSON failure while processing active view data.", e);
                } catch (RuntimeException e2) {
                    zzbbd.zzb("Failure while processing active view data.", e2);
                }
                String str = "Untracking ad unit: ";
                String valueOf = String.valueOf(this.zzbuu.zzmt());
                zzbbd.zzdn(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
            }
        }
    }

    /* Access modifiers changed, original: protected|final */
    /* JADX WARNING: Missing block: B:64:0x00cd, code skipped:
            return;
     */
    public final void zzbu(int r8) {
        /*
        r7 = this;
        r0 = r7.mLock;
        monitor-enter(r0);
        r1 = r7.zzbvh;	 Catch:{ all -> 0x00ce }
        r1 = r1.iterator();	 Catch:{ all -> 0x00ce }
    L_0x0009:
        r2 = r1.hasNext();	 Catch:{ all -> 0x00ce }
        r3 = 0;
        r4 = 1;
        if (r2 == 0) goto L_0x001f;
    L_0x0011:
        r2 = r1.next();	 Catch:{ all -> 0x00ce }
        r2 = (com.google.android.gms.internal.ads.zzsb) r2;	 Catch:{ all -> 0x00ce }
        r2 = r2.zznf();	 Catch:{ all -> 0x00ce }
        if (r2 == 0) goto L_0x0009;
    L_0x001d:
        r1 = r4;
        goto L_0x0020;
    L_0x001f:
        r1 = r3;
    L_0x0020:
        if (r1 == 0) goto L_0x00cc;
    L_0x0022:
        r1 = r7.zzbvc;	 Catch:{ all -> 0x00ce }
        if (r1 != 0) goto L_0x0028;
    L_0x0026:
        goto L_0x00cc;
    L_0x0028:
        r1 = r7.zzbut;	 Catch:{ all -> 0x00ce }
        r1 = r1.zznc();	 Catch:{ all -> 0x00ce }
        if (r1 == 0) goto L_0x0040;
    L_0x0030:
        r2 = com.google.android.gms.ads.internal.zzbv.zzlf();	 Catch:{ all -> 0x00ce }
        r5 = r7.zzbuw;	 Catch:{ all -> 0x00ce }
        r6 = r7.zzbux;	 Catch:{ all -> 0x00ce }
        r2 = r2.zza(r1, r5, r6);	 Catch:{ all -> 0x00ce }
        if (r2 == 0) goto L_0x0040;
    L_0x003e:
        r2 = r4;
        goto L_0x0041;
    L_0x0040:
        r2 = r3;
    L_0x0041:
        if (r1 == 0) goto L_0x0053;
    L_0x0043:
        if (r2 == 0) goto L_0x0053;
    L_0x0045:
        r5 = new android.graphics.Rect;	 Catch:{ all -> 0x00ce }
        r5.<init>();	 Catch:{ all -> 0x00ce }
        r6 = 0;
        r5 = r1.getGlobalVisibleRect(r5, r6);	 Catch:{ all -> 0x00ce }
        if (r5 == 0) goto L_0x0053;
    L_0x0051:
        r5 = r4;
        goto L_0x0054;
    L_0x0053:
        r5 = r3;
    L_0x0054:
        r6 = r7.zzbut;	 Catch:{ all -> 0x00ce }
        r6 = r6.zznd();	 Catch:{ all -> 0x00ce }
        if (r6 == 0) goto L_0x0061;
    L_0x005c:
        r7.zzmx();	 Catch:{ all -> 0x00ce }
        monitor-exit(r0);	 Catch:{ all -> 0x00ce }
        return;
    L_0x0061:
        if (r8 != r4) goto L_0x0071;
    L_0x0063:
        r6 = r7.zzbua;	 Catch:{ all -> 0x00ce }
        r6 = r6.tryAcquire();	 Catch:{ all -> 0x00ce }
        if (r6 != 0) goto L_0x0071;
    L_0x006b:
        r6 = r7.zzbve;	 Catch:{ all -> 0x00ce }
        if (r5 != r6) goto L_0x0071;
    L_0x006f:
        monitor-exit(r0);	 Catch:{ all -> 0x00ce }
        return;
    L_0x0071:
        if (r5 != 0) goto L_0x007b;
    L_0x0073:
        r6 = r7.zzbve;	 Catch:{ all -> 0x00ce }
        if (r6 != 0) goto L_0x007b;
    L_0x0077:
        if (r8 != r4) goto L_0x007b;
    L_0x0079:
        monitor-exit(r0);	 Catch:{ all -> 0x00ce }
        return;
    L_0x007b:
        r8 = java.lang.Boolean.valueOf(r2);	 Catch:{ RuntimeException | JSONException -> 0x0089, RuntimeException | JSONException -> 0x0089 }
        r8 = r7.zza(r1, r8);	 Catch:{ RuntimeException | JSONException -> 0x0089, RuntimeException | JSONException -> 0x0089 }
        r7.zza(r8, r3);	 Catch:{ RuntimeException | JSONException -> 0x0089, RuntimeException | JSONException -> 0x0089 }
        r7.zzbve = r5;	 Catch:{ RuntimeException | JSONException -> 0x0089, RuntimeException | JSONException -> 0x0089 }
        goto L_0x008f;
    L_0x0089:
        r8 = move-exception;
        r1 = "Active view update failed.";
        com.google.android.gms.internal.ads.zzbbd.zza(r1, r8);	 Catch:{ all -> 0x00ce }
    L_0x008f:
        r8 = r7.zzbut;	 Catch:{ all -> 0x00ce }
        r8 = r8.zzne();	 Catch:{ all -> 0x00ce }
        r8 = r8.zznc();	 Catch:{ all -> 0x00ce }
        if (r8 == 0) goto L_0x00c7;
    L_0x009b:
        r1 = r7.zzbus;	 Catch:{ all -> 0x00ce }
        r1 = r1.get();	 Catch:{ all -> 0x00ce }
        r1 = (android.view.ViewTreeObserver) r1;	 Catch:{ all -> 0x00ce }
        r8 = r8.getViewTreeObserver();	 Catch:{ all -> 0x00ce }
        if (r8 == r1) goto L_0x00c7;
    L_0x00a9:
        r7.zzna();	 Catch:{ all -> 0x00ce }
        r2 = r7.zzbva;	 Catch:{ all -> 0x00ce }
        if (r2 == 0) goto L_0x00b8;
    L_0x00b0:
        if (r1 == 0) goto L_0x00c0;
    L_0x00b2:
        r1 = r1.isAlive();	 Catch:{ all -> 0x00ce }
        if (r1 == 0) goto L_0x00c0;
    L_0x00b8:
        r7.zzbva = r4;	 Catch:{ all -> 0x00ce }
        r8.addOnScrollChangedListener(r7);	 Catch:{ all -> 0x00ce }
        r8.addOnGlobalLayoutListener(r7);	 Catch:{ all -> 0x00ce }
    L_0x00c0:
        r1 = new java.lang.ref.WeakReference;	 Catch:{ all -> 0x00ce }
        r1.<init>(r8);	 Catch:{ all -> 0x00ce }
        r7.zzbus = r1;	 Catch:{ all -> 0x00ce }
    L_0x00c7:
        r7.zzmy();	 Catch:{ all -> 0x00ce }
        monitor-exit(r0);	 Catch:{ all -> 0x00ce }
        return;
    L_0x00cc:
        monitor-exit(r0);	 Catch:{ all -> 0x00ce }
        return;
    L_0x00ce:
        r8 = move-exception;
        monitor-exit(r0);	 Catch:{ all -> 0x00ce }
        throw r8;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzrg.zzbu(int):void");
    }

    private final void zzmy() {
        if (this.zzbuz != null) {
            this.zzbuz.zza(this);
        }
    }

    public final boolean zzmz() {
        boolean z;
        synchronized (this.mLock) {
            z = this.zzbvc;
        }
        return z;
    }

    private static int zza(int i, DisplayMetrics displayMetrics) {
        return (int) (((float) i) / displayMetrics.density);
    }

    /* Access modifiers changed, original: final */
    public final boolean zzc(@Nullable Map<String, String> map) {
        if (map == null) {
            return false;
        }
        String str = (String) map.get("hashCode");
        if (TextUtils.isEmpty(str) || !str.equals(this.zzbuu.zzmt())) {
            return false;
        }
        return true;
    }

    /* Access modifiers changed, original: final */
    public final void zzd(Map<String, String> map) {
        zzbu(3);
    }

    /* Access modifiers changed, original: final */
    public final void zza(zzsb zzsb, Map<String, String> map) {
        String str = "Received request to untrack: ";
        String valueOf = String.valueOf(this.zzbuu.zzmt());
        zzbbd.zzdn(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
        zzb(zzsb);
    }

    /* Access modifiers changed, original: final */
    public final void zze(Map<String, String> map) {
        if (map.containsKey("isVisible")) {
            boolean z = "1".equals(map.get("isVisible")) || "true".equals(map.get("isVisible"));
            Iterator it = this.zzbvg.iterator();
            while (it.hasNext()) {
                ((zzrd) it.next()).zza(this, z);
            }
        }
    }

    private final void zzna() {
        ViewTreeObserver viewTreeObserver = (ViewTreeObserver) this.zzbus.get();
        if (viewTreeObserver != null && viewTreeObserver.isAlive()) {
            viewTreeObserver.removeOnScrollChangedListener(this);
            viewTreeObserver.removeGlobalOnLayoutListener(this);
        }
    }

    private final JSONObject zznb() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("afmaVersion", this.zzbuu.zzmr()).put("activeViewJSON", this.zzbuu.zzms()).put(AvidJSONUtil.KEY_TIMESTAMP, zzbv.zzlm().elapsedRealtime()).put("adFormat", this.zzbuu.zzmq()).put("hashCode", this.zzbuu.zzmt()).put("isMraid", this.zzbuu.zzmu()).put("isStopped", this.zzbvb).put("isPaused", this.zzbqq).put("isNative", this.zzbuu.zzmv()).put("isScreenOn", isScreenOn()).put("appMuted", zzbv.zzlk().zzkk()).put("appVolume", (double) zzbv.zzlk().zzkj()).put("deviceVolume", (double) this.zzbvk);
        return jSONObject;
    }

    private static JSONObject zza(JSONObject jSONObject) throws JSONException {
        JSONArray jSONArray = new JSONArray();
        JSONObject jSONObject2 = new JSONObject();
        jSONArray.put(jSONObject);
        jSONObject2.put("units", jSONArray);
        return jSONObject2;
    }

    private final void zza(JSONObject jSONObject, boolean z) {
        try {
            jSONObject = zza(jSONObject);
            ArrayList arrayList = new ArrayList(this.zzbvh);
            int size = arrayList.size();
            int i = 0;
            while (i < size) {
                Object obj = arrayList.get(i);
                i++;
                ((zzsb) obj).zzb(jSONObject, z);
            }
        } catch (Throwable th) {
            zzbbd.zzb("Skipping active view message.", th);
        }
    }

    public final void zza(zzsb zzsb) {
        if (this.zzbvh.isEmpty()) {
            synchronized (this.mLock) {
                if (this.zzbvf != null) {
                } else {
                    IntentFilter intentFilter = new IntentFilter();
                    intentFilter.addAction("android.intent.action.SCREEN_ON");
                    intentFilter.addAction("android.intent.action.SCREEN_OFF");
                    this.zzbvf = new zzrh(this);
                    zzbv.zzmc().zza(this.zzbup, this.zzbvf, intentFilter);
                }
            }
            zzbu(3);
        }
        this.zzbvh.add(zzsb);
        try {
            zzsb.zzb(zza(zza(this.zzbut.zznc(), null)), false);
        } catch (JSONException e) {
            zzbbd.zzb("Skipping measurement update for new client.", e);
        }
    }

    public final void zzb(zzsb zzsb) {
        this.zzbvh.remove(zzsb);
        zzsb.zzng();
        if (this.zzbvh.isEmpty()) {
            synchronized (this.mLock) {
                zzna();
                synchronized (this.mLock) {
                    if (this.zzbvf != null) {
                        try {
                            zzbv.zzmc().zza(this.zzbup, this.zzbvf);
                        } catch (IllegalStateException e) {
                            zzbbd.zzb("Failed trying to unregister the receiver", e);
                        } catch (Exception e2) {
                            zzbv.zzlj().zza(e2, "ActiveViewUnit.stopScreenStatusMonitoring");
                        }
                        this.zzbvf = null;
                    }
                }
                this.zzbup.getContentResolver().unregisterContentObserver(this.zzbvj);
                int i = 0;
                this.zzbvc = false;
                zzmy();
                ArrayList arrayList = new ArrayList(this.zzbvh);
                int size = arrayList.size();
                while (i < size) {
                    Object obj = arrayList.get(i);
                    i++;
                    zzb((zzsb) obj);
                }
            }
        }
    }

    private final JSONObject zza(@Nullable View view, @Nullable Boolean bool) throws JSONException {
        if (view == null) {
            return zznb().put("isAttachedToWindow", false).put("isScreenOn", isScreenOn()).put("isVisible", false);
        }
        boolean isAttachedToWindow = zzbv.zzlh().isAttachedToWindow(view);
        int[] iArr = new int[2];
        int[] iArr2 = new int[2];
        try {
            view.getLocationOnScreen(iArr);
            view.getLocationInWindow(iArr2);
        } catch (Exception e) {
            zzbbd.zzb("Failure getting view location.", e);
        }
        Rect rect = new Rect();
        rect.left = iArr[0];
        rect.top = iArr[1];
        rect.right = rect.left + view.getWidth();
        rect.bottom = rect.top + view.getHeight();
        Rect rect2 = new Rect();
        boolean globalVisibleRect = view.getGlobalVisibleRect(rect2, null);
        Rect rect3 = new Rect();
        boolean localVisibleRect = view.getLocalVisibleRect(rect3);
        Rect rect4 = new Rect();
        view.getHitRect(rect4);
        JSONObject zznb = zznb();
        zznb.put("windowVisibility", view.getWindowVisibility()).put("isAttachedToWindow", isAttachedToWindow).put("viewBox", new JSONObject().put(InAppMessage.INAPP_ALIGN_TOP, zza(this.zzbvi.top, this.zzbuy)).put(InAppMessage.INAPP_ALIGN_BOTTOM, zza(this.zzbvi.bottom, this.zzbuy)).put("left", zza(this.zzbvi.left, this.zzbuy)).put("right", zza(this.zzbvi.right, this.zzbuy))).put("adBox", new JSONObject().put(InAppMessage.INAPP_ALIGN_TOP, zza(rect.top, this.zzbuy)).put(InAppMessage.INAPP_ALIGN_BOTTOM, zza(rect.bottom, this.zzbuy)).put("left", zza(rect.left, this.zzbuy)).put("right", zza(rect.right, this.zzbuy))).put("globalVisibleBox", new JSONObject().put(InAppMessage.INAPP_ALIGN_TOP, zza(rect2.top, this.zzbuy)).put(InAppMessage.INAPP_ALIGN_BOTTOM, zza(rect2.bottom, this.zzbuy)).put("left", zza(rect2.left, this.zzbuy)).put("right", zza(rect2.right, this.zzbuy))).put("globalVisibleBoxVisible", globalVisibleRect).put("localVisibleBox", new JSONObject().put(InAppMessage.INAPP_ALIGN_TOP, zza(rect3.top, this.zzbuy)).put(InAppMessage.INAPP_ALIGN_BOTTOM, zza(rect3.bottom, this.zzbuy)).put("left", zza(rect3.left, this.zzbuy)).put("right", zza(rect3.right, this.zzbuy))).put("localVisibleBoxVisible", localVisibleRect).put("hitBox", new JSONObject().put(InAppMessage.INAPP_ALIGN_TOP, zza(rect4.top, this.zzbuy)).put(InAppMessage.INAPP_ALIGN_BOTTOM, zza(rect4.bottom, this.zzbuy)).put("left", zza(rect4.left, this.zzbuy)).put("right", zza(rect4.right, this.zzbuy))).put("screenDensity", (double) this.zzbuy.density);
        if (bool == null) {
            bool = Boolean.valueOf(zzbv.zzlf().zza(view, this.zzbuw, this.zzbux));
        }
        zznb.put("isVisible", bool.booleanValue());
        return zznb;
    }

    @VisibleForTesting
    private final boolean isScreenOn() {
        if (VERSION.SDK_INT >= 20) {
            return this.zzbuw.isInteractive();
        }
        return this.zzbuw.isScreenOn();
    }

    public final void onScrollChanged() {
        zzbu(1);
    }

    public final void onGlobalLayout() {
        zzbu(2);
    }

    public final void zza(zzrn zzrn) {
        synchronized (this.mLock) {
            this.zzbuz = zzrn;
        }
    }

    public final void stop() {
        synchronized (this.mLock) {
            this.zzbvb = true;
            zzbu(3);
        }
    }

    public final void pause() {
        synchronized (this.mLock) {
            this.zzbqq = true;
            zzbu(3);
        }
    }

    public final void resume() {
        synchronized (this.mLock) {
            this.zzbqq = false;
            zzbu(3);
        }
    }
}
