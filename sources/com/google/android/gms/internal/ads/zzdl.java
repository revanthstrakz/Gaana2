package com.google.android.gms.internal.ads;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.VisibleForTesting;
import android.util.Log;
import android.util.Pair;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import dalvik.system.DexClassLoader;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class zzdl {
    private static final String TAG = "zzdl";
    private volatile boolean zzrp = false;
    protected Context zzsp;
    private ExecutorService zzsq;
    private DexClassLoader zzsr;
    private zzcw zzss;
    private byte[] zzst;
    private volatile AdvertisingIdClient zzsu = null;
    private Future zzsv = null;
    private boolean zzsw;
    private volatile zzbl zzsx = null;
    private Future zzsy = null;
    private zzco zzsz;
    private boolean zzta = false;
    private boolean zztb = false;
    private Map<Pair<String, String>, zzes> zztc;
    private boolean zztd = false;
    private boolean zzte;
    private boolean zztf;

    final class zza extends BroadcastReceiver {
        private zza() {
        }

        public final void onReceive(Context context, Intent intent) {
            if ("android.intent.action.USER_PRESENT".equals(intent.getAction())) {
                zzdl.this.zzte = true;
                return;
            }
            if ("android.intent.action.SCREEN_OFF".equals(intent.getAction())) {
                zzdl.this.zzte = false;
            }
        }

        /* synthetic */ zza(zzdl zzdl, zzdm zzdm) {
            this();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0054 A:{Catch:{ zzcx -> 0x0150, zzdi -> 0x0157 }} */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0086 A:{Catch:{ all -> 0x011d, FileNotFoundException -> 0x0149, IOException -> 0x0142, zzcx -> 0x013b, NullPointerException -> 0x0134 }} */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00b0 A:{Catch:{ all -> 0x011d, FileNotFoundException -> 0x0149, IOException -> 0x0142, zzcx -> 0x013b, NullPointerException -> 0x0134 }} */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00f8 A:{Catch:{ zzcx -> 0x0150, zzdi -> 0x0157 }} */
    /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x004b */
    /* JADX WARNING: Can't wrap try/catch for region: R(18:1|2|(1:4)|5|6|7|8|(1:10)(1:11)|12|(1:14)(1:15)|16|17|18|(2:20|(2:22|23))|24|25|26|(14:27|28|(2:30|(2:32|33))|34|(1:36)|37|38|39|40|41|42|43|(1:45)|46)) */
    public static com.google.android.gms.internal.ads.zzdl zza(android.content.Context r8, java.lang.String r9, java.lang.String r10, boolean r11) {
        /*
        r0 = new com.google.android.gms.internal.ads.zzdl;
        r0.<init>(r8);
        r8 = new com.google.android.gms.internal.ads.zzdm;	 Catch:{ zzdi -> 0x0157 }
        r8.<init>();	 Catch:{ zzdi -> 0x0157 }
        r8 = java.util.concurrent.Executors.newCachedThreadPool(r8);	 Catch:{ zzdi -> 0x0157 }
        r0.zzsq = r8;	 Catch:{ zzdi -> 0x0157 }
        r0.zzrp = r11;	 Catch:{ zzdi -> 0x0157 }
        if (r11 == 0) goto L_0x0021;
    L_0x0014:
        r8 = r0.zzsq;	 Catch:{ zzdi -> 0x0157 }
        r11 = new com.google.android.gms.internal.ads.zzdn;	 Catch:{ zzdi -> 0x0157 }
        r11.<init>(r0);	 Catch:{ zzdi -> 0x0157 }
        r8 = r8.submit(r11);	 Catch:{ zzdi -> 0x0157 }
        r0.zzsv = r8;	 Catch:{ zzdi -> 0x0157 }
    L_0x0021:
        r8 = r0.zzsq;	 Catch:{ zzdi -> 0x0157 }
        r11 = new com.google.android.gms.internal.ads.zzdp;	 Catch:{ zzdi -> 0x0157 }
        r11.<init>(r0);	 Catch:{ zzdi -> 0x0157 }
        r8.execute(r11);	 Catch:{ zzdi -> 0x0157 }
        r8 = 0;
        r11 = 1;
        r1 = com.google.android.gms.common.GoogleApiAvailabilityLight.getInstance();	 Catch:{ Throwable -> 0x004b }
        r2 = r0.zzsp;	 Catch:{ Throwable -> 0x004b }
        r2 = r1.getApkVersion(r2);	 Catch:{ Throwable -> 0x004b }
        if (r2 <= 0) goto L_0x003b;
    L_0x0039:
        r2 = r11;
        goto L_0x003c;
    L_0x003b:
        r2 = r8;
    L_0x003c:
        r0.zzta = r2;	 Catch:{ Throwable -> 0x004b }
        r2 = r0.zzsp;	 Catch:{ Throwable -> 0x004b }
        r1 = r1.isGooglePlayServicesAvailable(r2);	 Catch:{ Throwable -> 0x004b }
        if (r1 != 0) goto L_0x0048;
    L_0x0046:
        r1 = r11;
        goto L_0x0049;
    L_0x0048:
        r1 = r8;
    L_0x0049:
        r0.zztb = r1;	 Catch:{ Throwable -> 0x004b }
    L_0x004b:
        r0.zza(r8, r11);	 Catch:{ zzdi -> 0x0157 }
        r1 = com.google.android.gms.internal.ads.zzds.isMainThread();	 Catch:{ zzdi -> 0x0157 }
        if (r1 == 0) goto L_0x006e;
    L_0x0054:
        r1 = com.google.android.gms.internal.ads.zzaan.zzctr;	 Catch:{ zzdi -> 0x0157 }
        r2 = com.google.android.gms.internal.ads.zzwu.zzpz();	 Catch:{ zzdi -> 0x0157 }
        r1 = r2.zzd(r1);	 Catch:{ zzdi -> 0x0157 }
        r1 = (java.lang.Boolean) r1;	 Catch:{ zzdi -> 0x0157 }
        r1 = r1.booleanValue();	 Catch:{ zzdi -> 0x0157 }
        if (r1 == 0) goto L_0x006e;
    L_0x0066:
        r8 = new java.lang.IllegalStateException;	 Catch:{ zzdi -> 0x0157 }
        r9 = "Task Context initialization must not be called from the UI thread.";
        r8.<init>(r9);	 Catch:{ zzdi -> 0x0157 }
        throw r8;	 Catch:{ zzdi -> 0x0157 }
    L_0x006e:
        r1 = new com.google.android.gms.internal.ads.zzcw;	 Catch:{ zzdi -> 0x0157 }
        r2 = 0;
        r1.<init>(r2);	 Catch:{ zzdi -> 0x0157 }
        r0.zzss = r1;	 Catch:{ zzdi -> 0x0157 }
        r1 = r0.zzss;	 Catch:{ zzcx -> 0x0150 }
        r9 = r1.zzl(r9);	 Catch:{ zzcx -> 0x0150 }
        r0.zzst = r9;	 Catch:{ zzcx -> 0x0150 }
        r9 = r0.zzsp;	 Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x0142, zzcx -> 0x013b, NullPointerException -> 0x0134 }
        r9 = r9.getCacheDir();	 Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x0142, zzcx -> 0x013b, NullPointerException -> 0x0134 }
        if (r9 != 0) goto L_0x0096;
    L_0x0086:
        r9 = r0.zzsp;	 Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x0142, zzcx -> 0x013b, NullPointerException -> 0x0134 }
        r1 = "dex";
        r9 = r9.getDir(r1, r8);	 Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x0142, zzcx -> 0x013b, NullPointerException -> 0x0134 }
        if (r9 != 0) goto L_0x0096;
    L_0x0090:
        r8 = new com.google.android.gms.internal.ads.zzdi;	 Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x0142, zzcx -> 0x013b, NullPointerException -> 0x0134 }
        r8.<init>();	 Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x0142, zzcx -> 0x013b, NullPointerException -> 0x0134 }
        throw r8;	 Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x0142, zzcx -> 0x013b, NullPointerException -> 0x0134 }
    L_0x0096:
        r1 = "1529567361524";
        r3 = new java.io.File;	 Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x0142, zzcx -> 0x013b, NullPointerException -> 0x0134 }
        r4 = "%s/%s.jar";
        r5 = 2;
        r6 = new java.lang.Object[r5];	 Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x0142, zzcx -> 0x013b, NullPointerException -> 0x0134 }
        r6[r8] = r9;	 Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x0142, zzcx -> 0x013b, NullPointerException -> 0x0134 }
        r6[r11] = r1;	 Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x0142, zzcx -> 0x013b, NullPointerException -> 0x0134 }
        r4 = java.lang.String.format(r4, r6);	 Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x0142, zzcx -> 0x013b, NullPointerException -> 0x0134 }
        r3.<init>(r4);	 Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x0142, zzcx -> 0x013b, NullPointerException -> 0x0134 }
        r4 = r3.exists();	 Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x0142, zzcx -> 0x013b, NullPointerException -> 0x0134 }
        if (r4 != 0) goto L_0x00c7;
    L_0x00b0:
        r4 = r0.zzss;	 Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x0142, zzcx -> 0x013b, NullPointerException -> 0x0134 }
        r6 = r0.zzst;	 Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x0142, zzcx -> 0x013b, NullPointerException -> 0x0134 }
        r10 = r4.zza(r6, r10);	 Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x0142, zzcx -> 0x013b, NullPointerException -> 0x0134 }
        r3.createNewFile();	 Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x0142, zzcx -> 0x013b, NullPointerException -> 0x0134 }
        r4 = new java.io.FileOutputStream;	 Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x0142, zzcx -> 0x013b, NullPointerException -> 0x0134 }
        r4.<init>(r3);	 Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x0142, zzcx -> 0x013b, NullPointerException -> 0x0134 }
        r6 = r10.length;	 Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x0142, zzcx -> 0x013b, NullPointerException -> 0x0134 }
        r4.write(r10, r8, r6);	 Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x0142, zzcx -> 0x013b, NullPointerException -> 0x0134 }
        r4.close();	 Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x0142, zzcx -> 0x013b, NullPointerException -> 0x0134 }
    L_0x00c7:
        r0.zzb(r9, r1);	 Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x0142, zzcx -> 0x013b, NullPointerException -> 0x0134 }
        r10 = new dalvik.system.DexClassLoader;	 Catch:{ all -> 0x011d }
        r4 = r3.getAbsolutePath();	 Catch:{ all -> 0x011d }
        r6 = r9.getAbsolutePath();	 Catch:{ all -> 0x011d }
        r7 = r0.zzsp;	 Catch:{ all -> 0x011d }
        r7 = r7.getClassLoader();	 Catch:{ all -> 0x011d }
        r10.<init>(r4, r6, r2, r7);	 Catch:{ all -> 0x011d }
        r0.zzsr = r10;	 Catch:{ all -> 0x011d }
        zzb(r3);	 Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x0142, zzcx -> 0x013b, NullPointerException -> 0x0134 }
        r0.zza(r9, r1);	 Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x0142, zzcx -> 0x013b, NullPointerException -> 0x0134 }
        r10 = "%s/%s.dex";
        r3 = new java.lang.Object[r5];	 Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x0142, zzcx -> 0x013b, NullPointerException -> 0x0134 }
        r3[r8] = r9;	 Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x0142, zzcx -> 0x013b, NullPointerException -> 0x0134 }
        r3[r11] = r1;	 Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x0142, zzcx -> 0x013b, NullPointerException -> 0x0134 }
        r8 = java.lang.String.format(r10, r3);	 Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x0142, zzcx -> 0x013b, NullPointerException -> 0x0134 }
        zzm(r8);	 Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x0142, zzcx -> 0x013b, NullPointerException -> 0x0134 }
        r8 = r0.zztf;	 Catch:{ zzdi -> 0x0157 }
        if (r8 != 0) goto L_0x0113;
    L_0x00f8:
        r8 = new android.content.IntentFilter;	 Catch:{ zzdi -> 0x0157 }
        r8.<init>();	 Catch:{ zzdi -> 0x0157 }
        r9 = "android.intent.action.USER_PRESENT";
        r8.addAction(r9);	 Catch:{ zzdi -> 0x0157 }
        r9 = "android.intent.action.SCREEN_OFF";
        r8.addAction(r9);	 Catch:{ zzdi -> 0x0157 }
        r9 = r0.zzsp;	 Catch:{ zzdi -> 0x0157 }
        r10 = new com.google.android.gms.internal.ads.zzdl$zza;	 Catch:{ zzdi -> 0x0157 }
        r10.<init>(r0, r2);	 Catch:{ zzdi -> 0x0157 }
        r9.registerReceiver(r10, r8);	 Catch:{ zzdi -> 0x0157 }
        r0.zztf = r11;	 Catch:{ zzdi -> 0x0157 }
    L_0x0113:
        r8 = new com.google.android.gms.internal.ads.zzco;	 Catch:{ zzdi -> 0x0157 }
        r8.<init>(r0);	 Catch:{ zzdi -> 0x0157 }
        r0.zzsz = r8;	 Catch:{ zzdi -> 0x0157 }
        r0.zztd = r11;	 Catch:{ zzdi -> 0x0157 }
        goto L_0x0157;
    L_0x011d:
        r10 = move-exception;
        zzb(r3);	 Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x0142, zzcx -> 0x013b, NullPointerException -> 0x0134 }
        r0.zza(r9, r1);	 Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x0142, zzcx -> 0x013b, NullPointerException -> 0x0134 }
        r2 = "%s/%s.dex";
        r3 = new java.lang.Object[r5];	 Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x0142, zzcx -> 0x013b, NullPointerException -> 0x0134 }
        r3[r8] = r9;	 Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x0142, zzcx -> 0x013b, NullPointerException -> 0x0134 }
        r3[r11] = r1;	 Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x0142, zzcx -> 0x013b, NullPointerException -> 0x0134 }
        r8 = java.lang.String.format(r2, r3);	 Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x0142, zzcx -> 0x013b, NullPointerException -> 0x0134 }
        zzm(r8);	 Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x0142, zzcx -> 0x013b, NullPointerException -> 0x0134 }
        throw r10;	 Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x0142, zzcx -> 0x013b, NullPointerException -> 0x0134 }
    L_0x0134:
        r8 = move-exception;
        r9 = new com.google.android.gms.internal.ads.zzdi;	 Catch:{ zzdi -> 0x0157 }
        r9.<init>(r8);	 Catch:{ zzdi -> 0x0157 }
        throw r9;	 Catch:{ zzdi -> 0x0157 }
    L_0x013b:
        r8 = move-exception;
        r9 = new com.google.android.gms.internal.ads.zzdi;	 Catch:{ zzdi -> 0x0157 }
        r9.<init>(r8);	 Catch:{ zzdi -> 0x0157 }
        throw r9;	 Catch:{ zzdi -> 0x0157 }
    L_0x0142:
        r8 = move-exception;
        r9 = new com.google.android.gms.internal.ads.zzdi;	 Catch:{ zzdi -> 0x0157 }
        r9.<init>(r8);	 Catch:{ zzdi -> 0x0157 }
        throw r9;	 Catch:{ zzdi -> 0x0157 }
    L_0x0149:
        r8 = move-exception;
        r9 = new com.google.android.gms.internal.ads.zzdi;	 Catch:{ zzdi -> 0x0157 }
        r9.<init>(r8);	 Catch:{ zzdi -> 0x0157 }
        throw r9;	 Catch:{ zzdi -> 0x0157 }
    L_0x0150:
        r8 = move-exception;
        r9 = new com.google.android.gms.internal.ads.zzdi;	 Catch:{ zzdi -> 0x0157 }
        r9.<init>(r8);	 Catch:{ zzdi -> 0x0157 }
        throw r9;	 Catch:{ zzdi -> 0x0157 }
    L_0x0157:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzdl.zza(android.content.Context, java.lang.String, java.lang.String, boolean):com.google.android.gms.internal.ads.zzdl");
    }

    public final Context getContext() {
        return this.zzsp;
    }

    public final boolean isInitialized() {
        return this.zztd;
    }

    public final ExecutorService zzac() {
        return this.zzsq;
    }

    public final DexClassLoader zzad() {
        return this.zzsr;
    }

    public final zzcw zzae() {
        return this.zzss;
    }

    public final byte[] zzaf() {
        return this.zzst;
    }

    public final boolean zzag() {
        return this.zzta;
    }

    public final zzco zzah() {
        return this.zzsz;
    }

    public final boolean zzai() {
        return this.zztb;
    }

    public final boolean zzaj() {
        return this.zzte;
    }

    public final zzbl zzak() {
        return this.zzsx;
    }

    public final Future zzal() {
        return this.zzsy;
    }

    private zzdl(Context context) {
        boolean z = true;
        this.zzte = true;
        this.zztf = false;
        Context applicationContext = context.getApplicationContext();
        if (applicationContext == null) {
            z = false;
        }
        this.zzsw = z;
        if (this.zzsw) {
            context = applicationContext;
        }
        this.zzsp = context;
        this.zztc = new HashMap();
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:26:0x0091 */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x00b2 A:{SYNTHETIC, Splitter:B:52:0x00b2} */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x00b7 A:{SYNTHETIC, Splitter:B:56:0x00b7} */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00a3 A:{SYNTHETIC, Splitter:B:39:0x00a3} */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00a8 A:{SYNTHETIC, Splitter:B:43:0x00a8} */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x00b2 A:{SYNTHETIC, Splitter:B:52:0x00b2} */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x00b7 A:{SYNTHETIC, Splitter:B:56:0x00b7} */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00a3 A:{SYNTHETIC, Splitter:B:39:0x00a3} */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00a8 A:{SYNTHETIC, Splitter:B:43:0x00a8} */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Can't wrap try/catch for region: R(10:20|21|22|23|24|25|26|27|28|30) */
    private final void zza(java.io.File r8, java.lang.String r9) {
        /*
        r7 = this;
        r0 = new java.io.File;
        r1 = "%s/%s.tmp";
        r2 = 2;
        r3 = new java.lang.Object[r2];
        r4 = 0;
        r3[r4] = r8;
        r5 = 1;
        r3[r5] = r9;
        r1 = java.lang.String.format(r1, r3);
        r0.<init>(r1);
        r1 = r0.exists();
        if (r1 == 0) goto L_0x001b;
    L_0x001a:
        return;
    L_0x001b:
        r1 = new java.io.File;
        r3 = "%s/%s.dex";
        r2 = new java.lang.Object[r2];
        r2[r4] = r8;
        r2[r5] = r9;
        r8 = java.lang.String.format(r3, r2);
        r1.<init>(r8);
        r8 = r1.exists();
        if (r8 != 0) goto L_0x0033;
    L_0x0032:
        return;
    L_0x0033:
        r2 = r1.length();
        r5 = 0;
        r8 = (r2 > r5 ? 1 : (r2 == r5 ? 0 : -1));
        if (r8 > 0) goto L_0x003e;
    L_0x003d:
        return;
    L_0x003e:
        r8 = (int) r2;
        r8 = new byte[r8];
        r2 = 0;
        r3 = new java.io.FileInputStream;	 Catch:{ zzcx | IOException | NoSuchAlgorithmException -> 0x00af, zzcx | IOException | NoSuchAlgorithmException -> 0x00af, zzcx | IOException | NoSuchAlgorithmException -> 0x00af, all -> 0x009f }
        r3.<init>(r1);	 Catch:{ zzcx | IOException | NoSuchAlgorithmException -> 0x00af, zzcx | IOException | NoSuchAlgorithmException -> 0x00af, zzcx | IOException | NoSuchAlgorithmException -> 0x00af, all -> 0x009f }
        r5 = r3.read(r8);	 Catch:{ zzcx | IOException | NoSuchAlgorithmException -> 0x00b0, zzcx | IOException | NoSuchAlgorithmException -> 0x00b0, zzcx | IOException | NoSuchAlgorithmException -> 0x00b0, all -> 0x009d }
        if (r5 > 0) goto L_0x0054;
    L_0x004d:
        r3.close();	 Catch:{ IOException -> 0x0050 }
    L_0x0050:
        zzb(r1);
        return;
    L_0x0054:
        r5 = new com.google.android.gms.internal.ads.zzbp;	 Catch:{ zzcx | IOException | NoSuchAlgorithmException -> 0x00b0, zzcx | IOException | NoSuchAlgorithmException -> 0x00b0, zzcx | IOException | NoSuchAlgorithmException -> 0x00b0, all -> 0x009d }
        r5.<init>();	 Catch:{ zzcx | IOException | NoSuchAlgorithmException -> 0x00b0, zzcx | IOException | NoSuchAlgorithmException -> 0x00b0, zzcx | IOException | NoSuchAlgorithmException -> 0x00b0, all -> 0x009d }
        r6 = android.os.Build.VERSION.SDK;	 Catch:{ zzcx | IOException | NoSuchAlgorithmException -> 0x00b0, zzcx | IOException | NoSuchAlgorithmException -> 0x00b0, zzcx | IOException | NoSuchAlgorithmException -> 0x00b0, all -> 0x009d }
        r6 = r6.getBytes();	 Catch:{ zzcx | IOException | NoSuchAlgorithmException -> 0x00b0, zzcx | IOException | NoSuchAlgorithmException -> 0x00b0, zzcx | IOException | NoSuchAlgorithmException -> 0x00b0, all -> 0x009d }
        r5.zzho = r6;	 Catch:{ zzcx | IOException | NoSuchAlgorithmException -> 0x00b0, zzcx | IOException | NoSuchAlgorithmException -> 0x00b0, zzcx | IOException | NoSuchAlgorithmException -> 0x00b0, all -> 0x009d }
        r9 = r9.getBytes();	 Catch:{ zzcx | IOException | NoSuchAlgorithmException -> 0x00b0, zzcx | IOException | NoSuchAlgorithmException -> 0x00b0, zzcx | IOException | NoSuchAlgorithmException -> 0x00b0, all -> 0x009d }
        r5.zzhn = r9;	 Catch:{ zzcx | IOException | NoSuchAlgorithmException -> 0x00b0, zzcx | IOException | NoSuchAlgorithmException -> 0x00b0, zzcx | IOException | NoSuchAlgorithmException -> 0x00b0, all -> 0x009d }
        r9 = r7.zzss;	 Catch:{ zzcx | IOException | NoSuchAlgorithmException -> 0x00b0, zzcx | IOException | NoSuchAlgorithmException -> 0x00b0, zzcx | IOException | NoSuchAlgorithmException -> 0x00b0, all -> 0x009d }
        r6 = r7.zzst;	 Catch:{ zzcx | IOException | NoSuchAlgorithmException -> 0x00b0, zzcx | IOException | NoSuchAlgorithmException -> 0x00b0, zzcx | IOException | NoSuchAlgorithmException -> 0x00b0, all -> 0x009d }
        r8 = r9.zzb(r6, r8);	 Catch:{ zzcx | IOException | NoSuchAlgorithmException -> 0x00b0, zzcx | IOException | NoSuchAlgorithmException -> 0x00b0, zzcx | IOException | NoSuchAlgorithmException -> 0x00b0, all -> 0x009d }
        r8 = r8.getBytes();	 Catch:{ zzcx | IOException | NoSuchAlgorithmException -> 0x00b0, zzcx | IOException | NoSuchAlgorithmException -> 0x00b0, zzcx | IOException | NoSuchAlgorithmException -> 0x00b0, all -> 0x009d }
        r5.data = r8;	 Catch:{ zzcx | IOException | NoSuchAlgorithmException -> 0x00b0, zzcx | IOException | NoSuchAlgorithmException -> 0x00b0, zzcx | IOException | NoSuchAlgorithmException -> 0x00b0, all -> 0x009d }
        r8 = com.google.android.gms.internal.ads.zzbw.zzb(r8);	 Catch:{ zzcx | IOException | NoSuchAlgorithmException -> 0x00b0, zzcx | IOException | NoSuchAlgorithmException -> 0x00b0, zzcx | IOException | NoSuchAlgorithmException -> 0x00b0, all -> 0x009d }
        r5.zzhm = r8;	 Catch:{ zzcx | IOException | NoSuchAlgorithmException -> 0x00b0, zzcx | IOException | NoSuchAlgorithmException -> 0x00b0, zzcx | IOException | NoSuchAlgorithmException -> 0x00b0, all -> 0x009d }
        r0.createNewFile();	 Catch:{ zzcx | IOException | NoSuchAlgorithmException -> 0x00b0, zzcx | IOException | NoSuchAlgorithmException -> 0x00b0, zzcx | IOException | NoSuchAlgorithmException -> 0x00b0, all -> 0x009d }
        r8 = new java.io.FileOutputStream;	 Catch:{ zzcx | IOException | NoSuchAlgorithmException -> 0x00b0, zzcx | IOException | NoSuchAlgorithmException -> 0x00b0, zzcx | IOException | NoSuchAlgorithmException -> 0x00b0, all -> 0x009d }
        r8.<init>(r0);	 Catch:{ zzcx | IOException | NoSuchAlgorithmException -> 0x00b0, zzcx | IOException | NoSuchAlgorithmException -> 0x00b0, zzcx | IOException | NoSuchAlgorithmException -> 0x00b0, all -> 0x009d }
        r9 = com.google.android.gms.internal.ads.zzbuz.zzb(r5);	 Catch:{ zzcx | IOException | NoSuchAlgorithmException -> 0x009b, zzcx | IOException | NoSuchAlgorithmException -> 0x009b, zzcx | IOException | NoSuchAlgorithmException -> 0x009b, all -> 0x0098 }
        r0 = r9.length;	 Catch:{ zzcx | IOException | NoSuchAlgorithmException -> 0x009b, zzcx | IOException | NoSuchAlgorithmException -> 0x009b, zzcx | IOException | NoSuchAlgorithmException -> 0x009b, all -> 0x0098 }
        r8.write(r9, r4, r0);	 Catch:{ zzcx | IOException | NoSuchAlgorithmException -> 0x009b, zzcx | IOException | NoSuchAlgorithmException -> 0x009b, zzcx | IOException | NoSuchAlgorithmException -> 0x009b, all -> 0x0098 }
        r8.close();	 Catch:{ zzcx | IOException | NoSuchAlgorithmException -> 0x009b, zzcx | IOException | NoSuchAlgorithmException -> 0x009b, zzcx | IOException | NoSuchAlgorithmException -> 0x009b, all -> 0x0098 }
        r3.close();	 Catch:{ IOException -> 0x0091 }
    L_0x0091:
        r8.close();	 Catch:{ IOException -> 0x0094 }
    L_0x0094:
        zzb(r1);
        return;
    L_0x0098:
        r9 = move-exception;
        r2 = r8;
        goto L_0x00a1;
    L_0x009b:
        r2 = r8;
        goto L_0x00b0;
    L_0x009d:
        r9 = move-exception;
        goto L_0x00a1;
    L_0x009f:
        r9 = move-exception;
        r3 = r2;
    L_0x00a1:
        if (r3 == 0) goto L_0x00a6;
    L_0x00a3:
        r3.close();	 Catch:{ IOException -> 0x00a6 }
    L_0x00a6:
        if (r2 == 0) goto L_0x00ab;
    L_0x00a8:
        r2.close();	 Catch:{ IOException -> 0x00ab }
    L_0x00ab:
        zzb(r1);
        throw r9;
    L_0x00af:
        r3 = r2;
    L_0x00b0:
        if (r3 == 0) goto L_0x00b5;
    L_0x00b2:
        r3.close();	 Catch:{ IOException -> 0x00b5 }
    L_0x00b5:
        if (r2 == 0) goto L_0x00ba;
    L_0x00b7:
        r2.close();	 Catch:{ IOException -> 0x00ba }
    L_0x00ba:
        zzb(r1);
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzdl.zza(java.io.File, java.lang.String):void");
    }

    private static void zzm(String str) {
        zzb(new File(str));
    }

    private static void zzb(File file) {
        if (file.exists()) {
            file.delete();
            return;
        }
        Log.d(TAG, String.format("File %s not found. No need for deletion", new Object[]{file.getAbsolutePath()}));
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:34:0x00b1 */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x00d3 A:{SYNTHETIC, Splitter:B:64:0x00d3} */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x00d8 A:{SYNTHETIC, Splitter:B:68:0x00d8} */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x00c7 A:{SYNTHETIC, Splitter:B:52:0x00c7} */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x00cc A:{SYNTHETIC, Splitter:B:56:0x00cc} */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x00d3 A:{SYNTHETIC, Splitter:B:64:0x00d3} */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x00d8 A:{SYNTHETIC, Splitter:B:68:0x00d8} */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x00c7 A:{SYNTHETIC, Splitter:B:52:0x00c7} */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x00cc A:{SYNTHETIC, Splitter:B:56:0x00cc} */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Can't wrap try/catch for region: R(8:29|30|31|32|33|34|35|36) */
    private final boolean zzb(java.io.File r10, java.lang.String r11) {
        /*
        r9 = this;
        r0 = new java.io.File;
        r1 = "%s/%s.tmp";
        r2 = 2;
        r3 = new java.lang.Object[r2];
        r4 = 0;
        r3[r4] = r10;
        r5 = 1;
        r3[r5] = r11;
        r1 = java.lang.String.format(r1, r3);
        r0.<init>(r1);
        r1 = r0.exists();
        if (r1 != 0) goto L_0x001b;
    L_0x001a:
        return r4;
    L_0x001b:
        r1 = new java.io.File;
        r3 = "%s/%s.dex";
        r2 = new java.lang.Object[r2];
        r2[r4] = r10;
        r2[r5] = r11;
        r10 = java.lang.String.format(r3, r2);
        r1.<init>(r10);
        r10 = r1.exists();
        if (r10 == 0) goto L_0x0033;
    L_0x0032:
        return r4;
    L_0x0033:
        r10 = 0;
        r2 = r0.length();	 Catch:{ zzcx | IOException | NoSuchAlgorithmException -> 0x00d0, zzcx | IOException | NoSuchAlgorithmException -> 0x00d0, zzcx | IOException | NoSuchAlgorithmException -> 0x00d0, all -> 0x00c3 }
        r6 = 0;
        r8 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1));
        if (r8 > 0) goto L_0x0042;
    L_0x003e:
        zzb(r0);	 Catch:{ zzcx | IOException | NoSuchAlgorithmException -> 0x00d0, zzcx | IOException | NoSuchAlgorithmException -> 0x00d0, zzcx | IOException | NoSuchAlgorithmException -> 0x00d0, all -> 0x00c3 }
        return r4;
    L_0x0042:
        r2 = (int) r2;	 Catch:{ zzcx | IOException | NoSuchAlgorithmException -> 0x00d0, zzcx | IOException | NoSuchAlgorithmException -> 0x00d0, zzcx | IOException | NoSuchAlgorithmException -> 0x00d0, all -> 0x00c3 }
        r2 = new byte[r2];	 Catch:{ zzcx | IOException | NoSuchAlgorithmException -> 0x00d0, zzcx | IOException | NoSuchAlgorithmException -> 0x00d0, zzcx | IOException | NoSuchAlgorithmException -> 0x00d0, all -> 0x00c3 }
        r3 = new java.io.FileInputStream;	 Catch:{ zzcx | IOException | NoSuchAlgorithmException -> 0x00d0, zzcx | IOException | NoSuchAlgorithmException -> 0x00d0, zzcx | IOException | NoSuchAlgorithmException -> 0x00d0, all -> 0x00c3 }
        r3.<init>(r0);	 Catch:{ zzcx | IOException | NoSuchAlgorithmException -> 0x00d0, zzcx | IOException | NoSuchAlgorithmException -> 0x00d0, zzcx | IOException | NoSuchAlgorithmException -> 0x00d0, all -> 0x00c3 }
        r6 = r3.read(r2);	 Catch:{ zzcx | IOException | NoSuchAlgorithmException -> 0x00d1, zzcx | IOException | NoSuchAlgorithmException -> 0x00d1, zzcx | IOException | NoSuchAlgorithmException -> 0x00d1, all -> 0x00c1 }
        if (r6 > 0) goto L_0x005e;
    L_0x0050:
        r11 = TAG;	 Catch:{ zzcx | IOException | NoSuchAlgorithmException -> 0x00d1, zzcx | IOException | NoSuchAlgorithmException -> 0x00d1, zzcx | IOException | NoSuchAlgorithmException -> 0x00d1, all -> 0x00c1 }
        r1 = "Cannot read the cache data.";
        android.util.Log.d(r11, r1);	 Catch:{ zzcx | IOException | NoSuchAlgorithmException -> 0x00d1, zzcx | IOException | NoSuchAlgorithmException -> 0x00d1, zzcx | IOException | NoSuchAlgorithmException -> 0x00d1, all -> 0x00c1 }
        zzb(r0);	 Catch:{ zzcx | IOException | NoSuchAlgorithmException -> 0x00d1, zzcx | IOException | NoSuchAlgorithmException -> 0x00d1, zzcx | IOException | NoSuchAlgorithmException -> 0x00d1, all -> 0x00c1 }
        r3.close();	 Catch:{ IOException -> 0x005d }
    L_0x005d:
        return r4;
    L_0x005e:
        r6 = new com.google.android.gms.internal.ads.zzbp;	 Catch:{ zzcx | IOException | NoSuchAlgorithmException -> 0x00d1, zzcx | IOException | NoSuchAlgorithmException -> 0x00d1, zzcx | IOException | NoSuchAlgorithmException -> 0x00d1, all -> 0x00c1 }
        r6.<init>();	 Catch:{ zzcx | IOException | NoSuchAlgorithmException -> 0x00d1, zzcx | IOException | NoSuchAlgorithmException -> 0x00d1, zzcx | IOException | NoSuchAlgorithmException -> 0x00d1, all -> 0x00c1 }
        r2 = com.google.android.gms.internal.ads.zzbuz.zza(r6, r2);	 Catch:{ zzcx | IOException | NoSuchAlgorithmException -> 0x00d1, zzcx | IOException | NoSuchAlgorithmException -> 0x00d1, zzcx | IOException | NoSuchAlgorithmException -> 0x00d1, all -> 0x00c1 }
        r2 = (com.google.android.gms.internal.ads.zzbp) r2;	 Catch:{ zzcx | IOException | NoSuchAlgorithmException -> 0x00d1, zzcx | IOException | NoSuchAlgorithmException -> 0x00d1, zzcx | IOException | NoSuchAlgorithmException -> 0x00d1, all -> 0x00c1 }
        r6 = new java.lang.String;	 Catch:{ zzcx | IOException | NoSuchAlgorithmException -> 0x00d1, zzcx | IOException | NoSuchAlgorithmException -> 0x00d1, zzcx | IOException | NoSuchAlgorithmException -> 0x00d1, all -> 0x00c1 }
        r7 = r2.zzhn;	 Catch:{ zzcx | IOException | NoSuchAlgorithmException -> 0x00d1, zzcx | IOException | NoSuchAlgorithmException -> 0x00d1, zzcx | IOException | NoSuchAlgorithmException -> 0x00d1, all -> 0x00c1 }
        r6.<init>(r7);	 Catch:{ zzcx | IOException | NoSuchAlgorithmException -> 0x00d1, zzcx | IOException | NoSuchAlgorithmException -> 0x00d1, zzcx | IOException | NoSuchAlgorithmException -> 0x00d1, all -> 0x00c1 }
        r11 = r11.equals(r6);	 Catch:{ zzcx | IOException | NoSuchAlgorithmException -> 0x00d1, zzcx | IOException | NoSuchAlgorithmException -> 0x00d1, zzcx | IOException | NoSuchAlgorithmException -> 0x00d1, all -> 0x00c1 }
        if (r11 == 0) goto L_0x00ba;
    L_0x0076:
        r11 = r2.zzhm;	 Catch:{ zzcx | IOException | NoSuchAlgorithmException -> 0x00d1, zzcx | IOException | NoSuchAlgorithmException -> 0x00d1, zzcx | IOException | NoSuchAlgorithmException -> 0x00d1, all -> 0x00c1 }
        r6 = r2.data;	 Catch:{ zzcx | IOException | NoSuchAlgorithmException -> 0x00d1, zzcx | IOException | NoSuchAlgorithmException -> 0x00d1, zzcx | IOException | NoSuchAlgorithmException -> 0x00d1, all -> 0x00c1 }
        r6 = com.google.android.gms.internal.ads.zzbw.zzb(r6);	 Catch:{ zzcx | IOException | NoSuchAlgorithmException -> 0x00d1, zzcx | IOException | NoSuchAlgorithmException -> 0x00d1, zzcx | IOException | NoSuchAlgorithmException -> 0x00d1, all -> 0x00c1 }
        r11 = java.util.Arrays.equals(r11, r6);	 Catch:{ zzcx | IOException | NoSuchAlgorithmException -> 0x00d1, zzcx | IOException | NoSuchAlgorithmException -> 0x00d1, zzcx | IOException | NoSuchAlgorithmException -> 0x00d1, all -> 0x00c1 }
        if (r11 == 0) goto L_0x00ba;
    L_0x0084:
        r11 = r2.zzho;	 Catch:{ zzcx | IOException | NoSuchAlgorithmException -> 0x00d1, zzcx | IOException | NoSuchAlgorithmException -> 0x00d1, zzcx | IOException | NoSuchAlgorithmException -> 0x00d1, all -> 0x00c1 }
        r6 = android.os.Build.VERSION.SDK;	 Catch:{ zzcx | IOException | NoSuchAlgorithmException -> 0x00d1, zzcx | IOException | NoSuchAlgorithmException -> 0x00d1, zzcx | IOException | NoSuchAlgorithmException -> 0x00d1, all -> 0x00c1 }
        r6 = r6.getBytes();	 Catch:{ zzcx | IOException | NoSuchAlgorithmException -> 0x00d1, zzcx | IOException | NoSuchAlgorithmException -> 0x00d1, zzcx | IOException | NoSuchAlgorithmException -> 0x00d1, all -> 0x00c1 }
        r11 = java.util.Arrays.equals(r11, r6);	 Catch:{ zzcx | IOException | NoSuchAlgorithmException -> 0x00d1, zzcx | IOException | NoSuchAlgorithmException -> 0x00d1, zzcx | IOException | NoSuchAlgorithmException -> 0x00d1, all -> 0x00c1 }
        if (r11 != 0) goto L_0x0093;
    L_0x0092:
        goto L_0x00ba;
    L_0x0093:
        r11 = r9.zzss;	 Catch:{ zzcx | IOException | NoSuchAlgorithmException -> 0x00d1, zzcx | IOException | NoSuchAlgorithmException -> 0x00d1, zzcx | IOException | NoSuchAlgorithmException -> 0x00d1, all -> 0x00c1 }
        r0 = r9.zzst;	 Catch:{ zzcx | IOException | NoSuchAlgorithmException -> 0x00d1, zzcx | IOException | NoSuchAlgorithmException -> 0x00d1, zzcx | IOException | NoSuchAlgorithmException -> 0x00d1, all -> 0x00c1 }
        r6 = new java.lang.String;	 Catch:{ zzcx | IOException | NoSuchAlgorithmException -> 0x00d1, zzcx | IOException | NoSuchAlgorithmException -> 0x00d1, zzcx | IOException | NoSuchAlgorithmException -> 0x00d1, all -> 0x00c1 }
        r2 = r2.data;	 Catch:{ zzcx | IOException | NoSuchAlgorithmException -> 0x00d1, zzcx | IOException | NoSuchAlgorithmException -> 0x00d1, zzcx | IOException | NoSuchAlgorithmException -> 0x00d1, all -> 0x00c1 }
        r6.<init>(r2);	 Catch:{ zzcx | IOException | NoSuchAlgorithmException -> 0x00d1, zzcx | IOException | NoSuchAlgorithmException -> 0x00d1, zzcx | IOException | NoSuchAlgorithmException -> 0x00d1, all -> 0x00c1 }
        r11 = r11.zza(r0, r6);	 Catch:{ zzcx | IOException | NoSuchAlgorithmException -> 0x00d1, zzcx | IOException | NoSuchAlgorithmException -> 0x00d1, zzcx | IOException | NoSuchAlgorithmException -> 0x00d1, all -> 0x00c1 }
        r1.createNewFile();	 Catch:{ zzcx | IOException | NoSuchAlgorithmException -> 0x00d1, zzcx | IOException | NoSuchAlgorithmException -> 0x00d1, zzcx | IOException | NoSuchAlgorithmException -> 0x00d1, all -> 0x00c1 }
        r0 = new java.io.FileOutputStream;	 Catch:{ zzcx | IOException | NoSuchAlgorithmException -> 0x00d1, zzcx | IOException | NoSuchAlgorithmException -> 0x00d1, zzcx | IOException | NoSuchAlgorithmException -> 0x00d1, all -> 0x00c1 }
        r0.<init>(r1);	 Catch:{ zzcx | IOException | NoSuchAlgorithmException -> 0x00d1, zzcx | IOException | NoSuchAlgorithmException -> 0x00d1, zzcx | IOException | NoSuchAlgorithmException -> 0x00d1, all -> 0x00c1 }
        r10 = r11.length;	 Catch:{ zzcx | IOException | NoSuchAlgorithmException -> 0x00b8, zzcx | IOException | NoSuchAlgorithmException -> 0x00b8, zzcx | IOException | NoSuchAlgorithmException -> 0x00b8, all -> 0x00b5 }
        r0.write(r11, r4, r10);	 Catch:{ zzcx | IOException | NoSuchAlgorithmException -> 0x00b8, zzcx | IOException | NoSuchAlgorithmException -> 0x00b8, zzcx | IOException | NoSuchAlgorithmException -> 0x00b8, all -> 0x00b5 }
        r3.close();	 Catch:{ IOException -> 0x00b1 }
    L_0x00b1:
        r0.close();	 Catch:{ IOException -> 0x00b4 }
    L_0x00b4:
        return r5;
    L_0x00b5:
        r11 = move-exception;
        r10 = r0;
        goto L_0x00c5;
    L_0x00b8:
        r10 = r0;
        goto L_0x00d1;
    L_0x00ba:
        zzb(r0);	 Catch:{ zzcx | IOException | NoSuchAlgorithmException -> 0x00d1, zzcx | IOException | NoSuchAlgorithmException -> 0x00d1, zzcx | IOException | NoSuchAlgorithmException -> 0x00d1, all -> 0x00c1 }
        r3.close();	 Catch:{ IOException -> 0x00c0 }
    L_0x00c0:
        return r4;
    L_0x00c1:
        r11 = move-exception;
        goto L_0x00c5;
    L_0x00c3:
        r11 = move-exception;
        r3 = r10;
    L_0x00c5:
        if (r3 == 0) goto L_0x00ca;
    L_0x00c7:
        r3.close();	 Catch:{ IOException -> 0x00ca }
    L_0x00ca:
        if (r10 == 0) goto L_0x00cf;
    L_0x00cc:
        r10.close();	 Catch:{ IOException -> 0x00cf }
    L_0x00cf:
        throw r11;
    L_0x00d0:
        r3 = r10;
    L_0x00d1:
        if (r3 == 0) goto L_0x00d6;
    L_0x00d3:
        r3.close();	 Catch:{ IOException -> 0x00d6 }
    L_0x00d6:
        if (r10 == 0) goto L_0x00db;
    L_0x00d8:
        r10.close();	 Catch:{ IOException -> 0x00db }
    L_0x00db:
        return r4;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzdl.zzb(java.io.File, java.lang.String):boolean");
    }

    public final boolean zza(String str, String str2, Class<?>... clsArr) {
        if (this.zztc.containsKey(new Pair(str, str2))) {
            return false;
        }
        this.zztc.put(new Pair(str, str2), new zzes(this, str, str2, clsArr));
        return true;
    }

    public final Method zza(String str, String str2) {
        zzes zzes = (zzes) this.zztc.get(new Pair(str, str2));
        if (zzes == null) {
            return null;
        }
        return zzes.zzax();
    }

    private final void zzam() {
        try {
            if (this.zzsu == null && this.zzsw) {
                AdvertisingIdClient advertisingIdClient = new AdvertisingIdClient(this.zzsp);
                advertisingIdClient.start();
                this.zzsu = advertisingIdClient;
            }
        } catch (GooglePlayServicesNotAvailableException | GooglePlayServicesRepairableException | IOException unused) {
            this.zzsu = null;
        }
    }

    /* Access modifiers changed, original: final */
    @VisibleForTesting
    public final void zza(int i, boolean z) {
        if (this.zztb) {
            Future submit = this.zzsq.submit(new zzdo(this, i, z));
            if (i == 0) {
                this.zzsy = submit;
            }
        }
    }

    /* Access modifiers changed, original: final */
    @VisibleForTesting
    public final zzbl zzb(int i, boolean z) {
        if (i > 0 && z) {
            try {
                Thread.sleep((long) (i * 1000));
            } catch (InterruptedException unused) {
            }
        }
        return zzan();
    }

    private static boolean zza(int i, zzbl zzbl) {
        if (i < 4) {
            if (zzbl == null) {
                return true;
            }
            if (((Boolean) zzwu.zzpz().zzd(zzaan.zzctu)).booleanValue() && (zzbl.zzdq == null || zzbl.zzdq.equals("0000000000000000000000000000000000000000000000000000000000000000"))) {
                return true;
            }
            if (((Boolean) zzwu.zzpz().zzd(zzaan.zzctv)).booleanValue() && (zzbl.zzgj == null || zzbl.zzgj.zzhh == null || zzbl.zzgj.zzhh.longValue() == -2)) {
                return true;
            }
        }
        return false;
    }

    @VisibleForTesting
    private final zzbl zzan() {
        try {
            return zzbjd.zzk(this.zzsp, this.zzsp.getPackageName(), Integer.toString(this.zzsp.getPackageManager().getPackageInfo(this.zzsp.getPackageName(), 0).versionCode));
        } catch (Throwable unused) {
            return null;
        }
    }

    public final AdvertisingIdClient zzao() {
        if (!this.zzrp) {
            return null;
        }
        if (this.zzsu != null) {
            return this.zzsu;
        }
        if (this.zzsv != null) {
            try {
                this.zzsv.get(AdaptiveTrackSelection.DEFAULT_MIN_TIME_BETWEEN_BUFFER_REEVALUTATION_MS, TimeUnit.MILLISECONDS);
                this.zzsv = null;
            } catch (InterruptedException | ExecutionException unused) {
            } catch (TimeoutException unused2) {
                this.zzsv.cancel(true);
            }
        }
        return this.zzsu;
    }

    public final int zzy() {
        return this.zzsz != null ? zzco.zzy() : Integer.MIN_VALUE;
    }
}
