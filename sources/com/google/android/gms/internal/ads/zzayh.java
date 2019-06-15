package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningTaskInfo;
import android.app.AlertDialog.Builder;
import android.app.KeyguardManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Debug;
import android.os.Debug.MemoryInfo;
import android.os.Handler;
import android.os.Looper;
import android.os.PowerManager;
import android.os.Process;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.customtabs.CustomTabsIntent;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.PopupWindow;
import com.google.android.exoplayer2.C;
import com.google.android.gms.ads.AdActivity;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.common.util.CrashUtils;
import com.google.android.gms.common.util.IOUtils;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.wrappers.Wrappers;
import com.til.colombia.android.internal.e;
import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@zzark
public final class zzayh {
    public static final Handler zzelc = new zzaya(Looper.getMainLooper());
    private final Object mLock = new Object();
    private String zzeiz;
    private boolean zzeld = true;
    private boolean zzele = false;
    private boolean zzelf = false;
    private Pattern zzelg;
    private Pattern zzelh;

    public final void zza(Context context, String str, boolean z, HttpURLConnection httpURLConnection) {
        httpURLConnection.setConnectTimeout(60000);
        httpURLConnection.setInstanceFollowRedirects(false);
        httpURLConnection.setReadTimeout(60000);
        httpURLConnection.setRequestProperty(e.c, zzo(context, str));
        httpURLConnection.setUseCaches(false);
    }

    public static boolean zzah(Context context) {
        context = zzaum.zzu(context);
        Intent intent = new Intent();
        intent.setClassName(context, AdActivity.CLASS_NAME);
        boolean z = false;
        try {
            ResolveInfo resolveActivity = context.getPackageManager().resolveActivity(intent, 65536);
            if (resolveActivity == null || resolveActivity.activityInfo == null) {
                zzbbd.zzeo("Could not find com.google.android.gms.ads.AdActivity, please make sure it is declared in AndroidManifest.xml.");
            } else {
                boolean z2;
                String str = "com.google.android.gms.ads.AdActivity requires the android:configChanges value to contain \"%s\".";
                if ((resolveActivity.activityInfo.configChanges & 16) == 0) {
                    zzbbd.zzeo(String.format(str, new Object[]{"keyboard"}));
                    z2 = false;
                } else {
                    z2 = true;
                }
                if ((resolveActivity.activityInfo.configChanges & 32) == 0) {
                    zzbbd.zzeo(String.format(str, new Object[]{"keyboardHidden"}));
                    z2 = false;
                }
                if ((resolveActivity.activityInfo.configChanges & 128) == 0) {
                    zzbbd.zzeo(String.format(str, new Object[]{"orientation"}));
                    z2 = false;
                }
                if ((resolveActivity.activityInfo.configChanges & 256) == 0) {
                    zzbbd.zzeo(String.format(str, new Object[]{"screenLayout"}));
                    z2 = false;
                }
                if ((resolveActivity.activityInfo.configChanges & 512) == 0) {
                    zzbbd.zzeo(String.format(str, new Object[]{"uiMode"}));
                    z2 = false;
                }
                if ((resolveActivity.activityInfo.configChanges & 1024) == 0) {
                    zzbbd.zzeo(String.format(str, new Object[]{"screenSize"}));
                    z2 = false;
                }
                if ((resolveActivity.activityInfo.configChanges & 2048) == 0) {
                    zzbbd.zzeo(String.format(str, new Object[]{"smallestScreenSize"}));
                } else {
                    z = z2;
                }
            }
            return z;
        } catch (Exception e) {
            zzbbd.zzc("Could not verify that com.google.android.gms.ads.AdActivity is declared in AndroidManifest.xml", e);
            zzbv.zzlj().zza(e, "AdUtil.hasAdActivity");
            return false;
        }
    }

    public static boolean zzn(Context context, String str) {
        context = zzaum.zzu(context);
        return Wrappers.packageManager(context).checkPermission(str, context.getPackageName()) == 0;
    }

    public static void zza(Context context, String str, List<String> list) {
        for (String zzbah : list) {
            new zzbah(context, str, zzbah).zzwa();
        }
    }

    public static void zzc(Context context, String str, String str2) {
        List arrayList = new ArrayList();
        arrayList.add(str2);
        zza(context, str, arrayList);
    }

    public final void zza(Context context, List<String> list) {
        if (context instanceof Activity) {
            Activity activity = (Activity) context;
            if (!TextUtils.isEmpty(zzbwi.zzbp(activity))) {
                if (list == null) {
                    zzaxz.v("Cannot ping urls: empty list.");
                } else if (zzabk.zzj(context)) {
                    zzabk zzabk = new zzabk();
                    zzabk.zza(new zzayk(this, list, zzabk, context));
                    zzabk.zzd(activity);
                } else {
                    zzaxz.v("Cannot ping url because custom tabs is not supported");
                }
            }
        }
    }

    public static String zza(InputStreamReader inputStreamReader) throws IOException {
        StringBuilder stringBuilder = new StringBuilder(8192);
        char[] cArr = new char[2048];
        while (true) {
            int read = inputStreamReader.read(cArr);
            if (read == -1) {
                return stringBuilder.toString();
            }
            stringBuilder.append(cArr, 0, read);
        }
    }

    public final boolean zzai(Context context) {
        if (this.zzele) {
            return false;
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.USER_PRESENT");
        intentFilter.addAction("android.intent.action.SCREEN_OFF");
        context.getApplicationContext().registerReceiver(new zzayo(this, null), intentFilter);
        this.zzele = true;
        return true;
    }

    public final boolean zzaj(Context context) {
        if (this.zzelf) {
            return false;
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.google.android.ads.intent.DEBUG_LOGGING_ENABLEMENT_CHANGED");
        context.getApplicationContext().registerReceiver(new zzayn(this, null), intentFilter);
        this.zzelf = true;
        return true;
    }

    public final void zza(Context context, String str, WebSettings webSettings) {
        webSettings.setUserAgentString(zzo(context, str));
    }

    private static String zzzr() {
        StringBuilder stringBuilder = new StringBuilder(256);
        stringBuilder.append("Mozilla/5.0 (Linux; U; Android");
        if (VERSION.RELEASE != null) {
            stringBuilder.append(" ");
            stringBuilder.append(VERSION.RELEASE);
        }
        stringBuilder.append("; ");
        stringBuilder.append(Locale.getDefault());
        if (Build.DEVICE != null) {
            stringBuilder.append("; ");
            stringBuilder.append(Build.DEVICE);
            if (Build.DISPLAY != null) {
                stringBuilder.append(" Build/");
                stringBuilder.append(Build.DISPLAY);
            }
        }
        stringBuilder.append(") AppleWebKit/533 Version/4.0 Safari/533");
        return stringBuilder.toString();
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x0045 */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0025 A:{Catch:{ Exception -> 0x00b7 }} */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00a8 A:{Catch:{ Exception -> 0x00b7 }} */
    /* JADX WARNING: Missing exception handler attribute for start block: B:14:0x001d */
    /* JADX WARNING: Can't wrap try/catch for region: R(12:12|13|14|15|(2:17|(3:19|(6:22|23|24|48|46|20)|47)(1:31))|32|33|34|(1:36)|40|41|42) */
    /* JADX WARNING: Can't wrap try/catch for region: R(5:25|26|(1:28)(1:29)|30|49) */
    /* JADX WARNING: Missing block: B:26:?, code skipped:
            r4.zzeiz = zzzr();
            r1 = "Interrupted, use default user agent: ";
            r2 = java.lang.String.valueOf(r4.zzeiz);
     */
    /* JADX WARNING: Missing block: B:27:0x0057, code skipped:
            if (r2.length() != 0) goto L_0x0059;
     */
    /* JADX WARNING: Missing block: B:28:0x0059, code skipped:
            r1 = r1.concat(r2);
     */
    /* JADX WARNING: Missing block: B:29:0x005e, code skipped:
            r1 = new java.lang.String(r1);
     */
    /* JADX WARNING: Missing block: B:30:0x0064, code skipped:
            com.google.android.gms.internal.ads.zzbbd.zzeo(r1);
     */
    public final java.lang.String zzo(android.content.Context r5, java.lang.String r6) {
        /*
        r4 = this;
        r0 = r4.mLock;
        monitor-enter(r0);
        r1 = r4.zzeiz;	 Catch:{ all -> 0x00d3 }
        if (r1 == 0) goto L_0x000b;
    L_0x0007:
        r5 = r4.zzeiz;	 Catch:{ all -> 0x00d3 }
        monitor-exit(r0);	 Catch:{ all -> 0x00d3 }
        return r5;
    L_0x000b:
        if (r6 != 0) goto L_0x0013;
    L_0x000d:
        r5 = zzzr();	 Catch:{ all -> 0x00d3 }
        monitor-exit(r0);	 Catch:{ all -> 0x00d3 }
        return r5;
    L_0x0013:
        r1 = com.google.android.gms.ads.internal.zzbv.zzlh();	 Catch:{ Exception -> 0x001d }
        r1 = r1.getDefaultUserAgent(r5);	 Catch:{ Exception -> 0x001d }
        r4.zzeiz = r1;	 Catch:{ Exception -> 0x001d }
    L_0x001d:
        r1 = r4.zzeiz;	 Catch:{ all -> 0x00d3 }
        r1 = android.text.TextUtils.isEmpty(r1);	 Catch:{ all -> 0x00d3 }
        if (r1 == 0) goto L_0x006e;
    L_0x0025:
        com.google.android.gms.internal.ads.zzwu.zzpv();	 Catch:{ all -> 0x00d3 }
        r1 = com.google.android.gms.internal.ads.zzbat.zzaar();	 Catch:{ all -> 0x00d3 }
        if (r1 != 0) goto L_0x0068;
    L_0x002e:
        r1 = 0;
        r4.zzeiz = r1;	 Catch:{ all -> 0x00d3 }
        r1 = zzelc;	 Catch:{ all -> 0x00d3 }
        r2 = new com.google.android.gms.internal.ads.zzayl;	 Catch:{ all -> 0x00d3 }
        r2.<init>(r4, r5);	 Catch:{ all -> 0x00d3 }
        r1.post(r2);	 Catch:{ all -> 0x00d3 }
    L_0x003b:
        r1 = r4.zzeiz;	 Catch:{ all -> 0x00d3 }
        if (r1 != 0) goto L_0x006e;
    L_0x003f:
        r1 = r4.mLock;	 Catch:{ InterruptedException -> 0x0045 }
        r1.wait();	 Catch:{ InterruptedException -> 0x0045 }
        goto L_0x003b;
    L_0x0045:
        r1 = zzzr();	 Catch:{ all -> 0x00d3 }
        r4.zzeiz = r1;	 Catch:{ all -> 0x00d3 }
        r1 = "Interrupted, use default user agent: ";
        r2 = r4.zzeiz;	 Catch:{ all -> 0x00d3 }
        r2 = java.lang.String.valueOf(r2);	 Catch:{ all -> 0x00d3 }
        r3 = r2.length();	 Catch:{ all -> 0x00d3 }
        if (r3 == 0) goto L_0x005e;
    L_0x0059:
        r1 = r1.concat(r2);	 Catch:{ all -> 0x00d3 }
        goto L_0x0064;
    L_0x005e:
        r2 = new java.lang.String;	 Catch:{ all -> 0x00d3 }
        r2.<init>(r1);	 Catch:{ all -> 0x00d3 }
        r1 = r2;
    L_0x0064:
        com.google.android.gms.internal.ads.zzbbd.zzeo(r1);	 Catch:{ all -> 0x00d3 }
        goto L_0x003b;
    L_0x0068:
        r1 = zzak(r5);	 Catch:{ all -> 0x00d3 }
        r4.zzeiz = r1;	 Catch:{ all -> 0x00d3 }
    L_0x006e:
        r1 = r4.zzeiz;	 Catch:{ all -> 0x00d3 }
        r1 = java.lang.String.valueOf(r1);	 Catch:{ all -> 0x00d3 }
        r2 = 10;
        r3 = java.lang.String.valueOf(r1);	 Catch:{ all -> 0x00d3 }
        r3 = r3.length();	 Catch:{ all -> 0x00d3 }
        r2 = r2 + r3;
        r3 = java.lang.String.valueOf(r6);	 Catch:{ all -> 0x00d3 }
        r3 = r3.length();	 Catch:{ all -> 0x00d3 }
        r2 = r2 + r3;
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00d3 }
        r3.<init>(r2);	 Catch:{ all -> 0x00d3 }
        r3.append(r1);	 Catch:{ all -> 0x00d3 }
        r1 = " (Mobile; ";
        r3.append(r1);	 Catch:{ all -> 0x00d3 }
        r3.append(r6);	 Catch:{ all -> 0x00d3 }
        r6 = r3.toString();	 Catch:{ all -> 0x00d3 }
        r4.zzeiz = r6;	 Catch:{ all -> 0x00d3 }
        r5 = com.google.android.gms.common.wrappers.Wrappers.packageManager(r5);	 Catch:{ Exception -> 0x00b7 }
        r5 = r5.isCallerInstantApp();	 Catch:{ Exception -> 0x00b7 }
        if (r5 == 0) goto L_0x00c1;
    L_0x00a8:
        r5 = r4.zzeiz;	 Catch:{ Exception -> 0x00b7 }
        r5 = java.lang.String.valueOf(r5);	 Catch:{ Exception -> 0x00b7 }
        r6 = ";aia";
        r5 = r5.concat(r6);	 Catch:{ Exception -> 0x00b7 }
        r4.zzeiz = r5;	 Catch:{ Exception -> 0x00b7 }
        goto L_0x00c1;
    L_0x00b7:
        r5 = move-exception;
        r6 = com.google.android.gms.ads.internal.zzbv.zzlj();	 Catch:{ all -> 0x00d3 }
        r1 = "AdUtil.getUserAgent";
        r6.zza(r5, r1);	 Catch:{ all -> 0x00d3 }
    L_0x00c1:
        r5 = r4.zzeiz;	 Catch:{ all -> 0x00d3 }
        r5 = java.lang.String.valueOf(r5);	 Catch:{ all -> 0x00d3 }
        r6 = ")";
        r5 = r5.concat(r6);	 Catch:{ all -> 0x00d3 }
        r4.zzeiz = r5;	 Catch:{ all -> 0x00d3 }
        r5 = r4.zzeiz;	 Catch:{ all -> 0x00d3 }
        monitor-exit(r0);	 Catch:{ all -> 0x00d3 }
        return r5;
    L_0x00d3:
        r5 = move-exception;
        monitor-exit(r0);	 Catch:{ all -> 0x00d3 }
        throw r5;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzayh.zzo(android.content.Context, java.lang.String):java.lang.String");
    }

    @VisibleForTesting
    protected static String zzak(Context context) {
        try {
            return new WebView(context).getSettings().getUserAgentString();
        } catch (Throwable unused) {
            return zzzr();
        }
    }

    public static String zzdx(String str) {
        return Uri.parse(str).buildUpon().query(null).build().toString();
    }

    public final JSONObject zzn(Map<String, ?> map) throws JSONException {
        try {
            JSONObject jSONObject = new JSONObject();
            for (String str : map.keySet()) {
                zza(jSONObject, str, map.get(str));
            }
            return jSONObject;
        } catch (ClassCastException e) {
            String str2 = "Could not convert map to JSON: ";
            String valueOf = String.valueOf(e.getMessage());
            throw new JSONException(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
        }
    }

    public final JSONObject zza(@Nullable Bundle bundle, JSONObject jSONObject) {
        if (bundle == null) {
            return null;
        }
        try {
            return zze(bundle);
        } catch (JSONException e) {
            zzbbd.zzb("Error converting Bundle to JSON", e);
            return null;
        }
    }

    private final JSONObject zze(Bundle bundle) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        for (String str : bundle.keySet()) {
            zza(jSONObject, str, bundle.get(str));
        }
        return jSONObject;
    }

    private final JSONArray zza(Collection<?> collection) throws JSONException {
        JSONArray jSONArray = new JSONArray();
        for (Object zza : collection) {
            zza(jSONArray, zza);
        }
        return jSONArray;
    }

    private final void zza(JSONArray jSONArray, Object obj) throws JSONException {
        if (obj instanceof Bundle) {
            jSONArray.put(zze((Bundle) obj));
        } else if (obj instanceof Map) {
            jSONArray.put(zzn((Map) obj));
        } else if (obj instanceof Collection) {
            jSONArray.put(zza((Collection) obj));
        } else if (obj instanceof Object[]) {
            Object[] objArr = (Object[]) obj;
            JSONArray jSONArray2 = new JSONArray();
            for (Object zza : objArr) {
                zza(jSONArray2, zza);
            }
            jSONArray.put(jSONArray2);
        } else {
            jSONArray.put(obj);
        }
    }

    private final void zza(JSONObject jSONObject, String str, Object obj) throws JSONException {
        if (obj instanceof Bundle) {
            jSONObject.put(str, zze((Bundle) obj));
        } else if (obj instanceof Map) {
            jSONObject.put(str, zzn((Map) obj));
        } else if (obj instanceof Collection) {
            if (str == null) {
                str = "null";
            }
            jSONObject.put(str, zza((Collection) obj));
        } else if (obj instanceof Object[]) {
            jSONObject.put(str, zza(Arrays.asList((Object[]) obj)));
        } else {
            jSONObject.put(str, obj);
        }
    }

    public static Map<String, String> zzg(Uri uri) {
        if (uri == null) {
            return null;
        }
        HashMap hashMap = new HashMap();
        for (String str : zzbv.zzlh().zzh(uri)) {
            hashMap.put(str, uri.getQueryParameter(str));
        }
        return hashMap;
    }

    public static String zzzs() {
        return UUID.randomUUID().toString();
    }

    public static int zzdy(String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            str = String.valueOf(e);
            StringBuilder stringBuilder = new StringBuilder(22 + String.valueOf(str).length());
            stringBuilder.append("Could not parse value:");
            stringBuilder.append(str);
            zzbbd.zzeo(stringBuilder.toString());
            return 0;
        }
    }

    public static String zzzt() {
        String str = Build.MANUFACTURER;
        String str2 = Build.MODEL;
        if (str2.startsWith(str)) {
            return str2;
        }
        StringBuilder stringBuilder = new StringBuilder((1 + String.valueOf(str).length()) + String.valueOf(str2).length());
        stringBuilder.append(str);
        stringBuilder.append(" ");
        stringBuilder.append(str2);
        return stringBuilder.toString();
    }

    private static int[] zzzu() {
        return new int[]{0, 0};
    }

    public static int[] zzg(Activity activity) {
        Window window = activity.getWindow();
        if (window == null || window.findViewById(16908290) == null) {
            return zzzu();
        }
        return new int[]{window.findViewById(16908290).getWidth(), window.findViewById(16908290).getHeight()};
    }

    public final int[] zzh(Activity activity) {
        int[] zzg = zzg(activity);
        r1 = new int[2];
        zzwu.zzpv();
        r1[0] = zzbat.zzb((Context) activity, zzg[0]);
        zzwu.zzpv();
        r1[1] = zzbat.zzb((Context) activity, zzg[1]);
        return r1;
    }

    public final int[] zzi(Activity activity) {
        Window window = activity.getWindow();
        int[] zzzu = (window == null || window.findViewById(16908290) == null) ? zzzu() : new int[]{window.findViewById(16908290).getTop(), window.findViewById(16908290).getBottom()};
        r0 = new int[2];
        zzwu.zzpv();
        r0[0] = zzbat.zzb((Context) activity, zzzu[0]);
        zzwu.zzpv();
        r0[1] = zzbat.zzb((Context) activity, zzzu[1]);
        return r0;
    }

    public static boolean zzdz(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return str.matches("([^\\s]+(\\.(?i)(jpg|png|gif|bmp|webp))$)");
    }

    public static DisplayMetrics zza(WindowManager windowManager) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics;
    }

    public static Builder zzal(Context context) {
        return new Builder(context);
    }

    public static zzzy zzam(Context context) {
        return new zzzy(context);
    }

    public static Bitmap zzt(View view) {
        view.setDrawingCacheEnabled(true);
        Bitmap createBitmap = Bitmap.createBitmap(view.getDrawingCache());
        view.setDrawingCacheEnabled(false);
        return createBitmap;
    }

    public static PopupWindow zza(View view, int i, int i2, boolean z) {
        return new PopupWindow(view, i, i2, false);
    }

    private static String zzan(Context context) {
        try {
            ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
            if (activityManager == null) {
                return null;
            }
            List runningTasks = activityManager.getRunningTasks(1);
            if (!(runningTasks == null || runningTasks.isEmpty())) {
                RunningTaskInfo runningTaskInfo = (RunningTaskInfo) runningTasks.get(0);
                if (!(runningTaskInfo == null || runningTaskInfo.topActivity == null)) {
                    return runningTaskInfo.topActivity.getClassName();
                }
            }
            return null;
        } catch (Exception unused) {
        }
    }

    public static String zza(Context context, View view, zzwf zzwf) {
        if (!((Boolean) zzwu.zzpz().zzd(zzaan.zzcqo)).booleanValue()) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("width", zzwf.width);
            jSONObject2.put("height", zzwf.height);
            jSONObject.put("size", jSONObject2);
            jSONObject.put("activity", zzan(context));
            if (!zzwf.zzckl) {
                JSONArray jSONArray = new JSONArray();
                while (view != null) {
                    ViewParent parent = view.getParent();
                    if (parent != null) {
                        int i = -1;
                        if (parent instanceof ViewGroup) {
                            i = ((ViewGroup) parent).indexOfChild(view);
                        }
                        JSONObject jSONObject3 = new JSONObject();
                        jSONObject3.put("type", parent.getClass().getName());
                        jSONObject3.put("index_of_child", i);
                        jSONArray.put(jSONObject3);
                    }
                    view = (parent == null || !(parent instanceof View)) ? null : (View) parent;
                }
                if (jSONArray.length() > 0) {
                    jSONObject.put("parents", jSONArray);
                }
            }
            return jSONObject.toString();
        } catch (JSONException e) {
            zzbbd.zzc("Fail to get view hierarchy json", e);
            return null;
        }
    }

    public static boolean zzao(Context context) {
        try {
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
                            if (runningAppProcessInfo.importance == 100 && !keyguardManager.inKeyguardRestrictedInputMode() && zzaq(context)) {
                                return true;
                            }
                            return false;
                        }
                    }
                    return false;
                }
            }
            return false;
        } catch (Throwable unused) {
            return false;
        }
    }

    public static boolean zzap(Context context) {
        try {
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
                            if (runningAppProcessInfo.importance == 100 && !keyguardManager.inKeyguardRestrictedInputMode() && zzaq(context)) {
                                return false;
                            }
                            return true;
                        }
                    }
                    return true;
                }
            }
            return false;
        } catch (Throwable unused) {
            return false;
        }
    }

    private static boolean zzaq(Context context) {
        PowerManager powerManager = (PowerManager) context.getSystemService("power");
        if (powerManager == null) {
            return false;
        }
        return powerManager.isScreenOn();
    }

    public final void zza(Context context, @Nullable String str, String str2, Bundle bundle, boolean z) {
        if (z) {
            zzbv.zzlf();
            bundle.putString("device", zzzt());
            bundle.putString("eids", TextUtils.join(",", zzaan.zzqw()));
        }
        zzwu.zzpv();
        zzbat.zza(context, str, str2, bundle, z, new zzaym(this, context, str));
    }

    public final void zzb(Context context, String str, String str2, Bundle bundle, boolean z) {
        if (((Boolean) zzwu.zzpz().zzd(zzaan.zzcsw)).booleanValue()) {
            zza(context, str, str2, bundle, z);
        }
    }

    public static void zzd(Runnable runnable) {
        if (Looper.getMainLooper().getThread() != Thread.currentThread()) {
            runnable.run();
        } else {
            zzayf.zzc(runnable);
        }
    }

    public static Bitmap zzu(View view) {
        if (view == null) {
            return null;
        }
        Bitmap zzw = zzw(view);
        if (zzw == null) {
            zzw = zzv(view);
        }
        return zzw;
    }

    private static Bitmap zzv(@NonNull View view) {
        try {
            int width = view.getWidth();
            int height = view.getHeight();
            if (width != 0) {
                if (height != 0) {
                    Bitmap createBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Config.RGB_565);
                    Canvas canvas = new Canvas(createBitmap);
                    view.layout(0, 0, width, height);
                    view.draw(canvas);
                    return createBitmap;
                }
            }
            zzbbd.zzeo("Width or height of view is zero");
            return null;
        } catch (RuntimeException e) {
            zzbbd.zzb("Fail to capture the webview", e);
            return null;
        }
    }

    private static Bitmap zzw(@NonNull View view) {
        Bitmap bitmap = null;
        try {
            boolean isDrawingCacheEnabled = view.isDrawingCacheEnabled();
            view.setDrawingCacheEnabled(true);
            Bitmap drawingCache = view.getDrawingCache();
            if (drawingCache != null) {
                bitmap = Bitmap.createBitmap(drawingCache);
            }
            view.setDrawingCacheEnabled(isDrawingCacheEnabled);
        } catch (RuntimeException e) {
            zzbbd.zzb("Fail to capture the web view", e);
        }
        return bitmap;
    }

    public static Bitmap zzar(Context context) {
        Bitmap bitmap = null;
        if (!(context instanceof Activity)) {
            return null;
        }
        try {
            Window window = ((Activity) context).getWindow();
            if (window != null) {
                bitmap = zzw(window.getDecorView().getRootView());
            }
        } catch (RuntimeException e) {
            zzbbd.zzb("Fail to capture screen shot", e);
        }
        return bitmap;
    }

    public static void zza(Context context, Intent intent) {
        try {
            context.startActivity(intent);
        } catch (Throwable unused) {
            intent.addFlags(C.ENCODING_PCM_MU_LAW);
            context.startActivity(intent);
        }
    }

    public static int zzas(Context context) {
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        if (applicationInfo == null) {
            return 0;
        }
        return applicationInfo.targetSdkVersion;
    }

    public final boolean zza(View view, Context context) {
        Context applicationContext = context.getApplicationContext();
        return zza(view, applicationContext != null ? (PowerManager) applicationContext.getSystemService("power") : null, zzat(context));
    }

    @Nullable
    private static KeyguardManager zzat(Context context) {
        Object systemService = context.getSystemService("keyguard");
        return (systemService == null || !(systemService instanceof KeyguardManager)) ? null : (KeyguardManager) systemService;
    }

    public final boolean zza(View view, PowerManager powerManager, KeyguardManager keyguardManager) {
        boolean z;
        boolean z2;
        if (!zzbv.zzlf().zzeld) {
            if (keyguardManager == null) {
                z = false;
            } else {
                z = keyguardManager.inKeyguardRestrictedInputMode();
            }
            if (z && !zzx(view)) {
                z = false;
                if (view.getVisibility() == 0 && view.isShown()) {
                    z2 = powerManager != null || powerManager.isScreenOn();
                    if (z2 && r6) {
                        if (((Boolean) zzwu.zzpz().zzd(zzaan.zzcst)).booleanValue() || view.getLocalVisibleRect(new Rect()) || view.getGlobalVisibleRect(new Rect())) {
                            return true;
                        }
                    }
                }
                return false;
            }
        }
        z = true;
        if (powerManager != null) {
        }
        if (((Boolean) zzwu.zzpz().zzd(zzaan.zzcst)).booleanValue()) {
        }
        return true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:9:0x0017  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0016 A:{RETURN} */
    public static boolean zzx(android.view.View r2) {
        /*
        r2 = r2.getRootView();
        r0 = 0;
        if (r2 == 0) goto L_0x0012;
    L_0x0007:
        r2 = r2.getContext();
        r1 = r2 instanceof android.app.Activity;
        if (r1 == 0) goto L_0x0012;
    L_0x000f:
        r2 = (android.app.Activity) r2;
        goto L_0x0013;
    L_0x0012:
        r2 = r0;
    L_0x0013:
        r1 = 0;
        if (r2 != 0) goto L_0x0017;
    L_0x0016:
        return r1;
    L_0x0017:
        r2 = r2.getWindow();
        if (r2 != 0) goto L_0x001e;
    L_0x001d:
        goto L_0x0022;
    L_0x001e:
        r0 = r2.getAttributes();
    L_0x0022:
        if (r0 == 0) goto L_0x002d;
    L_0x0024:
        r2 = r0.flags;
        r0 = 524288; // 0x80000 float:7.34684E-40 double:2.590327E-318;
        r2 = r2 & r0;
        if (r2 == 0) goto L_0x002d;
    L_0x002b:
        r2 = 1;
        return r2;
    L_0x002d:
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzayh.zzx(android.view.View):boolean");
    }

    @TargetApi(16)
    public static boolean zzau(Context context) {
        if (context == null || !PlatformVersion.isAtLeastJellyBean()) {
            return false;
        }
        KeyguardManager zzat = zzat(context);
        if (zzat == null || !zzat.isKeyguardLocked()) {
            return false;
        }
        return true;
    }

    public static int zzy(@Nullable View view) {
        if (view == null) {
            return -1;
        }
        ViewParent parent = view.getParent();
        while (parent != null && !(parent instanceof AdapterView)) {
            parent = parent.getParent();
        }
        if (parent == null) {
            return -1;
        }
        return ((AdapterView) parent).getPositionForView(view);
    }

    public static boolean zzav(Context context) {
        try {
            context.getClassLoader().loadClass("com.google.android.gms.ads.internal.ClientApi");
            return false;
        } catch (ClassNotFoundException unused) {
            return true;
        } catch (Throwable th) {
            zzbbd.zzb("Error loading class.", th);
            zzbv.zzlj().zza(th, "AdUtil.isLiteSdk");
            return false;
        }
    }

    public static Bundle zzzv() {
        Bundle bundle = new Bundle();
        try {
            if (((Boolean) zzwu.zzpz().zzd(zzaan.zzcpi)).booleanValue()) {
                MemoryInfo memoryInfo = new MemoryInfo();
                Debug.getMemoryInfo(memoryInfo);
                bundle.putParcelable("debug_memory_info", memoryInfo);
            }
            Runtime runtime = Runtime.getRuntime();
            bundle.putLong("runtime_free_memory", runtime.freeMemory());
            bundle.putLong("runtime_max_memory", runtime.maxMemory());
            bundle.putLong("runtime_total_memory", runtime.totalMemory());
            bundle.putInt("web_view_count", zzbv.zzlj().zzyp());
        } catch (Exception e) {
            zzbbd.zzc("Unable to gather memory stats", e);
        }
        return bundle;
    }

    @TargetApi(18)
    public static void zza(Context context, Uri uri) {
        try {
            Intent intent = new Intent("android.intent.action.VIEW", uri);
            Bundle bundle = new Bundle();
            intent.putExtras(bundle);
            zzb(context, intent);
            bundle.putString("com.android.browser.application_id", context.getPackageName());
            context.startActivity(intent);
            String uri2 = uri.toString();
            StringBuilder stringBuilder = new StringBuilder(26 + String.valueOf(uri2).length());
            stringBuilder.append("Opening ");
            stringBuilder.append(uri2);
            stringBuilder.append(" in a new browser.");
            zzbbd.zzdn(stringBuilder.toString());
        } catch (ActivityNotFoundException e) {
            zzbbd.zzb("No browser is found.", e);
        }
    }

    @TargetApi(18)
    public static void zzb(Context context, Intent intent) {
        if (intent != null && PlatformVersion.isAtLeastJellyBeanMR2()) {
            Bundle extras = intent.getExtras() != null ? intent.getExtras() : new Bundle();
            extras.putBinder(CustomTabsIntent.EXTRA_SESSION, null);
            extras.putString("com.android.browser.application_id", context.getPackageName());
            intent.putExtras(extras);
        }
    }

    public static void zzd(Context context, String str, String str2) {
        try {
            FileOutputStream openFileOutput = context.openFileOutput(str, 0);
            openFileOutput.write(str2.getBytes("UTF-8"));
            openFileOutput.close();
        } catch (Exception e) {
            zzbbd.zzb("Error writing to file in internal storage.", e);
        }
    }

    public static String zzp(Context context, String str) {
        try {
            return new String(IOUtils.readInputStreamFully(context.openFileInput(str), true), "UTF-8");
        } catch (IOException unused) {
            zzbbd.zzdn("Error reading from internal storage.");
            return "";
        }
    }

    @TargetApi(24)
    public static boolean zza(Activity activity, Configuration configuration) {
        if (((Boolean) zzwu.zzpz().zzd(zzaan.zzcwn)).booleanValue()) {
            return !activity.isInMultiWindowMode();
        } else {
            zzwu.zzpv();
            int zza = zzbat.zza((Context) activity, configuration.screenHeightDp);
            int zza2 = zzbat.zza((Context) activity, configuration.screenWidthDp);
            DisplayMetrics zza3 = zza((WindowManager) activity.getApplicationContext().getSystemService("window"));
            int i = zza3.heightPixels;
            int i2 = zza3.widthPixels;
            int identifier = activity.getResources().getIdentifier("status_bar_height", "dimen", "android");
            identifier = identifier > 0 ? activity.getResources().getDimensionPixelSize(identifier) : 0;
            int round = ((int) Math.round(((double) activity.getResources().getDisplayMetrics().density) + 0.5d)) * ((Integer) zzwu.zzpz().zzd(zzaan.zzcwk)).intValue();
            return zze(i, zza + identifier, round) && zze(i2, zza2, round);
        }
    }

    /* JADX WARNING: Missing block: B:9:0x0023, code skipped:
            if (((java.lang.String) com.google.android.gms.internal.ads.zzwu.zzpz().zzd(com.google.android.gms.internal.ads.zzaan.zzcqq)).equals(r3.zzelg.pattern()) == false) goto L_0x0025;
     */
    public final boolean zzea(java.lang.String r4) {
        /*
        r3 = this;
        r0 = android.text.TextUtils.isEmpty(r4);
        r1 = 0;
        if (r0 == 0) goto L_0x0008;
    L_0x0007:
        return r1;
    L_0x0008:
        monitor-enter(r3);	 Catch:{ PatternSyntaxException -> 0x0046 }
        r0 = r3.zzelg;	 Catch:{ all -> 0x0043 }
        if (r0 == 0) goto L_0x0025;
    L_0x000d:
        r0 = com.google.android.gms.internal.ads.zzaan.zzcqq;	 Catch:{ all -> 0x0043 }
        r2 = com.google.android.gms.internal.ads.zzwu.zzpz();	 Catch:{ all -> 0x0043 }
        r0 = r2.zzd(r0);	 Catch:{ all -> 0x0043 }
        r0 = (java.lang.String) r0;	 Catch:{ all -> 0x0043 }
        r2 = r3.zzelg;	 Catch:{ all -> 0x0043 }
        r2 = r2.pattern();	 Catch:{ all -> 0x0043 }
        r0 = r0.equals(r2);	 Catch:{ all -> 0x0043 }
        if (r0 != 0) goto L_0x0037;
    L_0x0025:
        r0 = com.google.android.gms.internal.ads.zzaan.zzcqq;	 Catch:{ all -> 0x0043 }
        r2 = com.google.android.gms.internal.ads.zzwu.zzpz();	 Catch:{ all -> 0x0043 }
        r0 = r2.zzd(r0);	 Catch:{ all -> 0x0043 }
        r0 = (java.lang.String) r0;	 Catch:{ all -> 0x0043 }
        r0 = java.util.regex.Pattern.compile(r0);	 Catch:{ all -> 0x0043 }
        r3.zzelg = r0;	 Catch:{ all -> 0x0043 }
    L_0x0037:
        r0 = r3.zzelg;	 Catch:{ all -> 0x0043 }
        r4 = r0.matcher(r4);	 Catch:{ all -> 0x0043 }
        r4 = r4.matches();	 Catch:{ all -> 0x0043 }
        monitor-exit(r3);	 Catch:{ all -> 0x0043 }
        return r4;
    L_0x0043:
        r4 = move-exception;
        monitor-exit(r3);	 Catch:{ all -> 0x0043 }
        throw r4;	 Catch:{ PatternSyntaxException -> 0x0046 }
    L_0x0046:
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzayh.zzea(java.lang.String):boolean");
    }

    /* JADX WARNING: Missing block: B:9:0x0023, code skipped:
            if (((java.lang.String) com.google.android.gms.internal.ads.zzwu.zzpz().zzd(com.google.android.gms.internal.ads.zzaan.zzcqr)).equals(r3.zzelh.pattern()) == false) goto L_0x0025;
     */
    public final boolean zzeb(java.lang.String r4) {
        /*
        r3 = this;
        r0 = android.text.TextUtils.isEmpty(r4);
        r1 = 0;
        if (r0 == 0) goto L_0x0008;
    L_0x0007:
        return r1;
    L_0x0008:
        monitor-enter(r3);	 Catch:{ PatternSyntaxException -> 0x0046 }
        r0 = r3.zzelh;	 Catch:{ all -> 0x0043 }
        if (r0 == 0) goto L_0x0025;
    L_0x000d:
        r0 = com.google.android.gms.internal.ads.zzaan.zzcqr;	 Catch:{ all -> 0x0043 }
        r2 = com.google.android.gms.internal.ads.zzwu.zzpz();	 Catch:{ all -> 0x0043 }
        r0 = r2.zzd(r0);	 Catch:{ all -> 0x0043 }
        r0 = (java.lang.String) r0;	 Catch:{ all -> 0x0043 }
        r2 = r3.zzelh;	 Catch:{ all -> 0x0043 }
        r2 = r2.pattern();	 Catch:{ all -> 0x0043 }
        r0 = r0.equals(r2);	 Catch:{ all -> 0x0043 }
        if (r0 != 0) goto L_0x0037;
    L_0x0025:
        r0 = com.google.android.gms.internal.ads.zzaan.zzcqr;	 Catch:{ all -> 0x0043 }
        r2 = com.google.android.gms.internal.ads.zzwu.zzpz();	 Catch:{ all -> 0x0043 }
        r0 = r2.zzd(r0);	 Catch:{ all -> 0x0043 }
        r0 = (java.lang.String) r0;	 Catch:{ all -> 0x0043 }
        r0 = java.util.regex.Pattern.compile(r0);	 Catch:{ all -> 0x0043 }
        r3.zzelh = r0;	 Catch:{ all -> 0x0043 }
    L_0x0037:
        r0 = r3.zzelh;	 Catch:{ all -> 0x0043 }
        r4 = r0.matcher(r4);	 Catch:{ all -> 0x0043 }
        r4 = r4.matches();	 Catch:{ all -> 0x0043 }
        monitor-exit(r3);	 Catch:{ all -> 0x0043 }
        return r4;
    L_0x0043:
        r4 = move-exception;
        monitor-exit(r3);	 Catch:{ all -> 0x0043 }
        throw r4;	 Catch:{ PatternSyntaxException -> 0x0046 }
    L_0x0046:
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzayh.zzeb(java.lang.String):boolean");
    }

    @VisibleForTesting
    private static boolean zze(int i, int i2, int i3) {
        return Math.abs(i - i2) <= i3;
    }

    @VisibleForTesting
    public static Bundle zza(zzsx zzsx) {
        if (zzsx == null) {
            return null;
        }
        if (zzbv.zzlj().zzyq().zzzc() && zzbv.zzlj().zzyq().zzze()) {
            return null;
        }
        String zznj;
        Object zznk;
        String zznl;
        if (zzsx.zznw()) {
            zzsx.wakeup();
        }
        zzsr zznu = zzsx.zznu();
        if (zznu != null) {
            zznj = zznu.zznj();
            zznk = zznu.zznk();
            zznl = zznu.zznl();
            if (zznj != null) {
                zzbv.zzlj().zzyq().zzdq(zznj);
            }
            if (zznl != null) {
                zzbv.zzlj().zzyq().zzdr(zznl);
            }
        } else {
            zznj = zzbv.zzlj().zzyq().zzzd();
            zznl = zzbv.zzlj().zzyq().zzzf();
            zznk = null;
        }
        Bundle bundle = new Bundle(1);
        if (!(zznl == null || zzbv.zzlj().zzyq().zzze())) {
            bundle.putString("v_fp_vertical", zznl);
        }
        if (!(zznj == null || zzbv.zzlj().zzyq().zzzc())) {
            bundle.putString("fingerprint", zznj);
            if (!zznj.equals(zznk)) {
                bundle.putString("v_fp", zznk);
            }
        }
        if (bundle.isEmpty()) {
            return null;
        }
        return bundle;
    }

    @Nullable
    public static WebResourceResponse zze(Context context, String str, String str2) {
        try {
            HashMap hashMap = new HashMap();
            hashMap.put(e.c, zzbv.zzlf().zzo(context, str));
            hashMap.put("Cache-Control", "max-stale=3600");
            String str3 = (String) new zzazs(context).zzc(str2, hashMap).get(60, TimeUnit.SECONDS);
            if (str3 != null) {
                return new WebResourceResponse("application/javascript", "UTF-8", new ByteArrayInputStream(str3.getBytes("UTF-8")));
            }
        } catch (IOException | InterruptedException | ExecutionException | TimeoutException e) {
            zzbbd.zzc("Could not fetch MRAID JS.", e);
        }
        return null;
    }

    public static WebResourceResponse zzd(HttpURLConnection httpURLConnection) throws IOException {
        zzbv.zzlf();
        String contentType = httpURLConnection.getContentType();
        if (TextUtils.isEmpty(contentType)) {
            contentType = "";
        } else {
            contentType = contentType.split(";")[0].trim();
        }
        String str = contentType;
        zzbv.zzlf();
        contentType = httpURLConnection.getContentType();
        if (!TextUtils.isEmpty(contentType)) {
            String[] split = contentType.split(";");
            if (split.length != 1) {
                for (int i = 1; i < split.length; i++) {
                    if (split[i].trim().startsWith("charset")) {
                        String[] split2 = split[i].trim().split("=");
                        if (split2.length > 1) {
                            contentType = split2[1].trim();
                            break;
                        }
                    }
                }
            }
        }
        contentType = "";
        String str2 = contentType;
        Map headerFields = httpURLConnection.getHeaderFields();
        HashMap hashMap = new HashMap(headerFields.size());
        for (Entry entry : headerFields.entrySet()) {
            if (!(entry.getKey() == null || entry.getValue() == null || ((List) entry.getValue()).size() <= 0)) {
                hashMap.put((String) entry.getKey(), (String) ((List) entry.getValue()).get(0));
            }
        }
        return zzbv.zzlh().zza(str, str2, httpURLConnection.getResponseCode(), httpURLConnection.getResponseMessage(), hashMap, httpURLConnection.getInputStream());
    }

    public static void zza(Context context, Throwable th) {
        if (context != null) {
            boolean z = false;
            try {
                z = ((Boolean) zzwu.zzpz().zzd(zzaan.zzcof)).booleanValue();
            } catch (IllegalStateException unused) {
            }
            if (z) {
                CrashUtils.addDynamiteErrorToDropBox(context, th);
            }
        }
    }

    @Deprecated
    public final String zzaw(Context context) {
        if (!((Boolean) zzwu.zzpz().zzd(zzaan.zzcym)).booleanValue()) {
            return "";
        }
        try {
            return (String) zzayf.zza(new zzayi(this, context)).get();
        } catch (InterruptedException unused) {
            Thread.interrupted();
            return "";
        } catch (ExecutionException unused2) {
            return "";
        }
    }

    @Deprecated
    public final Bundle zzax(Context context) {
        if (!((Boolean) zzwu.zzpz().zzd(zzaan.zzcyn)).booleanValue()) {
            return null;
        }
        try {
            return (Bundle) zzayf.zza(new zzayj(this, context)).get();
        } catch (InterruptedException unused) {
            Thread.interrupted();
            return null;
        } catch (ExecutionException unused2) {
            return null;
        }
    }

    public static boolean zzay(Context context) {
        if (!(context instanceof Activity)) {
            return false;
        }
        Window window = ((Activity) context).getWindow();
        if (window == null || window.getDecorView() == null) {
            return false;
        }
        Rect rect = new Rect();
        Rect rect2 = new Rect();
        window.getDecorView().getGlobalVisibleRect(rect, null);
        window.getDecorView().getWindowVisibleDisplayFrame(rect2);
        if (rect.bottom == 0 || rect2.bottom == 0 || rect.top != rect2.top) {
            return false;
        }
        return true;
    }

    public static boolean zzec(String str) {
        if (!zzbax.isEnabled()) {
            return false;
        }
        if (!((Boolean) zzwu.zzpz().zzd(zzaan.zzcws)).booleanValue()) {
            return false;
        }
        String str2 = (String) zzwu.zzpz().zzd(zzaan.zzcwu);
        if (!str2.isEmpty()) {
            for (String equals : str2.split(";")) {
                if (equals.equals(str)) {
                    return false;
                }
            }
        }
        str2 = (String) zzwu.zzpz().zzd(zzaan.zzcwt);
        if (str2.isEmpty()) {
            return true;
        }
        for (String equals2 : str2.split(";")) {
            if (equals2.equals(str)) {
                return true;
            }
        }
        return false;
    }
}
