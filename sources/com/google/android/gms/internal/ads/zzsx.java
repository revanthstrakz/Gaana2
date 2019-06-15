package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.KeyguardManager;
import android.content.Context;
import android.graphics.Rect;
import android.os.PowerManager;
import android.os.Process;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.TextView;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

@zzark
@TargetApi(14)
public final class zzsx extends Thread {
    private final Object mLock;
    private boolean mStarted;
    private boolean zzbm;
    private final int zzbwy;
    private final int zzbxa;
    private boolean zzbxz;
    private final zzss zzbya;
    private final int zzbyb;
    private final int zzbyc;
    private final int zzbyd;
    private final int zzbye;
    private final int zzbyf;
    private final int zzbyg;
    private final String zzbyh;
    private final boolean zzbyi;

    public zzsx() {
        this(new zzss());
    }

    @VisibleForTesting
    private zzsx(zzss zzss) {
        this.mStarted = false;
        this.zzbxz = false;
        this.zzbm = false;
        this.zzbya = zzss;
        this.mLock = new Object();
        this.zzbwy = ((Integer) zzwu.zzpz().zzd(zzaan.zzcqa)).intValue();
        this.zzbyc = ((Integer) zzwu.zzpz().zzd(zzaan.zzcqb)).intValue();
        this.zzbxa = ((Integer) zzwu.zzpz().zzd(zzaan.zzcqc)).intValue();
        this.zzbyd = ((Integer) zzwu.zzpz().zzd(zzaan.zzcqd)).intValue();
        this.zzbye = ((Integer) zzwu.zzpz().zzd(zzaan.zzcqf)).intValue();
        this.zzbyf = ((Integer) zzwu.zzpz().zzd(zzaan.zzcqg)).intValue();
        this.zzbyg = ((Integer) zzwu.zzpz().zzd(zzaan.zzcqh)).intValue();
        this.zzbyb = ((Integer) zzwu.zzpz().zzd(zzaan.zzcqe)).intValue();
        this.zzbyh = (String) zzwu.zzpz().zzd(zzaan.zzcqj);
        this.zzbyi = ((Boolean) zzwu.zzpz().zzd(zzaan.zzcqk)).booleanValue();
        setName("ContentFetchTask");
    }

    public final void zzns() {
        synchronized (this.mLock) {
            if (this.mStarted) {
                zzbbd.zzdn("Content hash thread already started, quiting...");
                return;
            }
            this.mStarted = true;
            start();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x007a A:{Splitter:B:0:0x0000, ExcHandler: InterruptedException (r0_15 'e' java.lang.InterruptedException)} */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0083 A:{LOOP_START, SYNTHETIC, LOOP:1: B:29:0x0083->B:41:0x0083} */
    /* JADX WARNING: Missing exception handler attribute for start block: B:29:0x0083 */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing block: B:15:0x003d, code skipped:
            r0 = move-exception;
     */
    /* JADX WARNING: Missing block: B:17:?, code skipped:
            com.google.android.gms.ads.internal.zzbv.zzlj().zza(r0, "ContentFetchTask.extractContent");
            com.google.android.gms.internal.ads.zzbbd.zzdn("Failed getting root view of activity. Content not extracted.");
     */
    /* JADX WARNING: Missing block: B:25:0x007a, code skipped:
            r0 = move-exception;
     */
    /* JADX WARNING: Missing block: B:26:0x007b, code skipped:
            com.google.android.gms.internal.ads.zzbbd.zzb("Error in ContentFetchTask", r0);
     */
    public final void run() {
        /*
        r4 = this;
    L_0x0000:
        r0 = zznt();	 Catch:{ InterruptedException -> 0x007a, Exception -> 0x006a }
        if (r0 == 0) goto L_0x0059;
    L_0x0006:
        r0 = com.google.android.gms.ads.internal.zzbv.zzli();	 Catch:{ InterruptedException -> 0x007a, Exception -> 0x006a }
        r0 = r0.getActivity();	 Catch:{ InterruptedException -> 0x007a, Exception -> 0x006a }
        if (r0 != 0) goto L_0x0019;
    L_0x0010:
        r0 = "ContentFetchThread: no activity. Sleeping.";
        com.google.android.gms.internal.ads.zzbbd.zzdn(r0);	 Catch:{ InterruptedException -> 0x007a, Exception -> 0x006a }
        r4.zznv();	 Catch:{ InterruptedException -> 0x007a, Exception -> 0x006a }
        goto L_0x0061;
    L_0x0019:
        if (r0 == 0) goto L_0x0061;
    L_0x001b:
        r1 = 0;
        r2 = r0.getWindow();	 Catch:{ Exception -> 0x003d, InterruptedException -> 0x007a }
        if (r2 == 0) goto L_0x004c;
    L_0x0022:
        r2 = r0.getWindow();	 Catch:{ Exception -> 0x003d, InterruptedException -> 0x007a }
        r2 = r2.getDecorView();	 Catch:{ Exception -> 0x003d, InterruptedException -> 0x007a }
        if (r2 == 0) goto L_0x004c;
    L_0x002c:
        r0 = r0.getWindow();	 Catch:{ Exception -> 0x003d, InterruptedException -> 0x007a }
        r0 = r0.getDecorView();	 Catch:{ Exception -> 0x003d, InterruptedException -> 0x007a }
        r2 = 16908290; // 0x1020002 float:2.3877235E-38 double:8.353805E-317;
        r0 = r0.findViewById(r2);	 Catch:{ Exception -> 0x003d, InterruptedException -> 0x007a }
        r1 = r0;
        goto L_0x004c;
    L_0x003d:
        r0 = move-exception;
        r2 = com.google.android.gms.ads.internal.zzbv.zzlj();	 Catch:{ InterruptedException -> 0x007a, Exception -> 0x006a }
        r3 = "ContentFetchTask.extractContent";
        r2.zza(r0, r3);	 Catch:{ InterruptedException -> 0x007a, Exception -> 0x006a }
        r0 = "Failed getting root view of activity. Content not extracted.";
        com.google.android.gms.internal.ads.zzbbd.zzdn(r0);	 Catch:{ InterruptedException -> 0x007a, Exception -> 0x006a }
    L_0x004c:
        if (r1 == 0) goto L_0x0061;
    L_0x004e:
        if (r1 == 0) goto L_0x0061;
    L_0x0050:
        r0 = new com.google.android.gms.internal.ads.zzsy;	 Catch:{ InterruptedException -> 0x007a, Exception -> 0x006a }
        r0.<init>(r4, r1);	 Catch:{ InterruptedException -> 0x007a, Exception -> 0x006a }
        r1.post(r0);	 Catch:{ InterruptedException -> 0x007a, Exception -> 0x006a }
        goto L_0x0061;
    L_0x0059:
        r0 = "ContentFetchTask: sleeping";
        com.google.android.gms.internal.ads.zzbbd.zzdn(r0);	 Catch:{ InterruptedException -> 0x007a, Exception -> 0x006a }
        r4.zznv();	 Catch:{ InterruptedException -> 0x007a, Exception -> 0x006a }
    L_0x0061:
        r0 = r4.zzbyb;	 Catch:{ InterruptedException -> 0x007a, Exception -> 0x006a }
        r0 = r0 * 1000;
        r0 = (long) r0;	 Catch:{ InterruptedException -> 0x007a, Exception -> 0x006a }
        java.lang.Thread.sleep(r0);	 Catch:{ InterruptedException -> 0x007a, Exception -> 0x006a }
        goto L_0x0080;
    L_0x006a:
        r0 = move-exception;
        r1 = "Error in ContentFetchTask";
        com.google.android.gms.internal.ads.zzbbd.zzb(r1, r0);
        r1 = com.google.android.gms.ads.internal.zzbv.zzlj();
        r2 = "ContentFetchTask.run";
        r1.zza(r0, r2);
        goto L_0x0080;
    L_0x007a:
        r0 = move-exception;
        r1 = "Error in ContentFetchTask";
        com.google.android.gms.internal.ads.zzbbd.zzb(r1, r0);
    L_0x0080:
        r0 = r4.mLock;
        monitor-enter(r0);
    L_0x0083:
        r1 = r4.zzbxz;	 Catch:{ all -> 0x0095 }
        if (r1 == 0) goto L_0x0092;
    L_0x0087:
        r1 = "ContentFetchTask: waiting";
        com.google.android.gms.internal.ads.zzbbd.zzdn(r1);	 Catch:{ InterruptedException -> 0x0083 }
        r1 = r4.mLock;	 Catch:{ InterruptedException -> 0x0083 }
        r1.wait();	 Catch:{ InterruptedException -> 0x0083 }
        goto L_0x0083;
    L_0x0092:
        monitor-exit(r0);	 Catch:{ all -> 0x0095 }
        goto L_0x0000;
    L_0x0095:
        r1 = move-exception;
        monitor-exit(r0);	 Catch:{ all -> 0x0095 }
        throw r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzsx.run():void");
    }

    /* Access modifiers changed, original: final */
    @VisibleForTesting
    public final void zzk(View view) {
        try {
            zzsr zzsr = new zzsr(this.zzbwy, this.zzbyc, this.zzbxa, this.zzbyd, this.zzbye, this.zzbyf, this.zzbyg);
            Context context = zzbv.zzli().getContext();
            if (!(context == null || TextUtils.isEmpty(this.zzbyh))) {
                String str = (String) view.getTag(context.getResources().getIdentifier((String) zzwu.zzpz().zzd(zzaan.zzcqi), "id", context.getPackageName()));
                if (str != null && str.equals(this.zzbyh)) {
                    return;
                }
            }
            zztb zza = zza(view, zzsr);
            zzsr.zznp();
            if (zza.zzbyq != 0 || zza.zzbyr != 0) {
                if (zza.zzbyr != 0 || zzsr.zznq() != 0) {
                    if (zza.zzbyr != 0 || !this.zzbya.zza(zzsr)) {
                        this.zzbya.zzc(zzsr);
                    }
                }
            }
        } catch (Exception e) {
            zzbbd.zzb("Exception in fetchContentOnUIThread", e);
            zzbv.zzlj().zza(e, "ContentFetchTask.fetchContent");
        }
    }

    @VisibleForTesting
    private static boolean zznt() {
        try {
            Context context = zzbv.zzli().getContext();
            if (context == null) {
                return false;
            }
            ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
            KeyguardManager keyguardManager = (KeyguardManager) context.getSystemService("keyguard");
            if (activityManager != null) {
                if (keyguardManager != null) {
                    List<RunningAppProcessInfo> runningAppProcesses = activityManager.getRunningAppProcesses();
                    if (runningAppProcesses == null) {
                        return false;
                    }
                    for (RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                        if (Process.myPid() == runningAppProcessInfo.pid) {
                            if (runningAppProcessInfo.importance == 100 && !keyguardManager.inKeyguardRestrictedInputMode()) {
                                boolean z;
                                PowerManager powerManager = (PowerManager) context.getSystemService("power");
                                if (powerManager == null) {
                                    z = false;
                                } else {
                                    z = powerManager.isScreenOn();
                                }
                                if (z) {
                                    return true;
                                }
                            }
                            return false;
                        }
                    }
                    return false;
                }
            }
            return false;
        } catch (Throwable th) {
            zzbv.zzlj().zza(th, "ContentFetchTask.isInForeground");
            return false;
        }
    }

    @VisibleForTesting
    private final zztb zza(@Nullable View view, zzsr zzsr) {
        int i = 0;
        if (view == null) {
            return new zztb(this, 0, 0);
        }
        boolean globalVisibleRect = view.getGlobalVisibleRect(new Rect());
        if ((view instanceof TextView) && !(view instanceof EditText)) {
            CharSequence text = ((TextView) view).getText();
            if (TextUtils.isEmpty(text)) {
                return new zztb(this, 0, 0);
            }
            zzsr.zzb(text.toString(), globalVisibleRect, view.getX(), view.getY(), (float) view.getWidth(), (float) view.getHeight());
            return new zztb(this, 1, 0);
        } else if ((view instanceof WebView) && !(view instanceof zzbgg)) {
            int i2;
            zzsr.zzno();
            WebView webView = (WebView) view;
            if (PlatformVersion.isAtLeastKitKat()) {
                zzsr.zzno();
                webView.post(new zzsz(this, zzsr, webView, globalVisibleRect));
                i2 = 1;
            } else {
                i2 = 0;
            }
            if (i2 != 0) {
                return new zztb(this, 0, 1);
            }
            return new zztb(this, 0, 0);
        } else if (!(view instanceof ViewGroup)) {
            return new zztb(this, 0, 0);
        } else {
            ViewGroup viewGroup = (ViewGroup) view;
            int i3 = 0;
            int i4 = i3;
            while (i < viewGroup.getChildCount()) {
                zztb zza = zza(viewGroup.getChildAt(i), zzsr);
                i3 += zza.zzbyq;
                i4 += zza.zzbyr;
                i++;
            }
            return new zztb(this, i3, i4);
        }
    }

    /* Access modifiers changed, original: final */
    @VisibleForTesting
    public final void zza(zzsr zzsr, WebView webView, String str, boolean z) {
        zzsr.zznn();
        try {
            if (!TextUtils.isEmpty(str)) {
                String optString = new JSONObject(str).optString(MimeTypes.BASE_TYPE_TEXT);
                if (this.zzbyi || TextUtils.isEmpty(webView.getTitle())) {
                    zzsr.zza(optString, z, webView.getX(), webView.getY(), (float) webView.getWidth(), (float) webView.getHeight());
                } else {
                    str = webView.getTitle();
                    StringBuilder stringBuilder = new StringBuilder((1 + String.valueOf(str).length()) + String.valueOf(optString).length());
                    stringBuilder.append(str);
                    stringBuilder.append("\n");
                    stringBuilder.append(optString);
                    zzsr.zza(stringBuilder.toString(), z, webView.getX(), webView.getY(), (float) webView.getWidth(), (float) webView.getHeight());
                }
            }
            if (zzsr.zzni()) {
                this.zzbya.zzb(zzsr);
            }
        } catch (JSONException unused) {
            zzbbd.zzdn("Json string may be malformed.");
        } catch (Throwable th) {
            zzbbd.zza("Failed to get webview content.", th);
            zzbv.zzlj().zza(th, "ContentFetchTask.processWebViewContent");
        }
    }

    public final zzsr zznu() {
        return this.zzbya.zznr();
    }

    public final void wakeup() {
        synchronized (this.mLock) {
            this.zzbxz = false;
            this.mLock.notifyAll();
            zzbbd.zzdn("ContentFetchThread: wakeup");
        }
    }

    private final void zznv() {
        synchronized (this.mLock) {
            this.zzbxz = true;
            boolean z = this.zzbxz;
            StringBuilder stringBuilder = new StringBuilder(42);
            stringBuilder.append("ContentFetchThread: paused, mPause = ");
            stringBuilder.append(z);
            zzbbd.zzdn(stringBuilder.toString());
        }
    }

    public final boolean zznw() {
        return this.zzbxz;
    }
}
