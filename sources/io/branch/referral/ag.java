package io.branch.referral;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Process;
import android.provider.Settings.Secure;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import io.branch.referral.Defines.Jsonkey;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class ag {
    String a = null;
    int b = 0;
    private Context c;
    private boolean d;

    interface a {
        void d();
    }

    private class b extends d<Void, Void, Void> {
        private final a b;

        public b(a aVar) {
            this.b = aVar;
        }

        /* Access modifiers changed, original: protected|varargs */
        /* renamed from: a */
        public Void doInBackground(Void... voidArr) {
            final CountDownLatch countDownLatch = new CountDownLatch(1);
            new Thread(new Runnable() {
                public void run() {
                    Process.setThreadPriority(-19);
                    Object k = ag.this.k();
                    ag.this.a(k);
                    ag.this.b(k);
                    countDownLatch.countDown();
                }
            }).start();
            try {
                countDownLatch.await(1500, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                ThrowableExtension.printStackTrace(e);
            }
            return null;
        }

        /* Access modifiers changed, original: protected */
        /* renamed from: a */
        public void onPostExecute(Void voidR) {
            super.onPostExecute(voidR);
            if (this.b != null) {
                this.b.d();
            }
        }
    }

    public String g() {
        return InternalLogger.EVENT_PARAM_SDK_ANDROID;
    }

    public ag(Context context) {
        this.c = context;
        this.d = true;
    }

    public String a(boolean z) {
        if (this.c == null) {
            return "bnc_no_value";
        }
        String str = null;
        if (!z) {
            str = Secure.getString(this.c.getContentResolver(), "android_id");
        }
        if (str == null) {
            str = UUID.randomUUID().toString();
            this.d = false;
        }
        return str;
    }

    public boolean a() {
        return this.d;
    }

    public String b() {
        return a(this.c.getPackageName());
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:31:0x005a */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x005f A:{SYNTHETIC, Splitter:B:37:0x005f} */
    /* JADX WARNING: Removed duplicated region for block: B:46:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x0064 A:{Catch:{ NameNotFoundException -> 0x0067 }} */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0052 A:{SYNTHETIC, Splitter:B:27:0x0052} */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0057 A:{Catch:{ IOException -> 0x005a }} */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x005f A:{SYNTHETIC, Splitter:B:37:0x005f} */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x0064 A:{Catch:{ NameNotFoundException -> 0x0067 }} */
    /* JADX WARNING: Removed duplicated region for block: B:46:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0052 A:{SYNTHETIC, Splitter:B:27:0x0052} */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0057 A:{Catch:{ IOException -> 0x005a }} */
    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* JADX WARNING: Can't wrap try/catch for region: R(5:19|(2:27|28)|(1:30)|31|32) */
    public java.lang.String a(java.lang.String r6) {
        /*
        r5 = this;
        r0 = "bnc_no_value";
        r1 = r5.l();
        if (r1 != 0) goto L_0x0067;
    L_0x0008:
        r1 = r5.c;
        r1 = r1.getPackageManager();
        r2 = 0;
        r6 = r1.getApplicationInfo(r6, r2);	 Catch:{  }
        r6 = r6.publicSourceDir;	 Catch:{  }
        r1 = 0;
        r2 = new java.util.jar.JarFile;	 Catch:{ Exception -> 0x005b, all -> 0x004c }
        r2.<init>(r6);	 Catch:{ Exception -> 0x005b, all -> 0x004c }
        r6 = "AndroidManifest.xml";
        r6 = r2.getEntry(r6);	 Catch:{ Exception -> 0x004a, all -> 0x0045 }
        r6 = r2.getInputStream(r6);	 Catch:{ Exception -> 0x004a, all -> 0x0045 }
        r1 = r6.available();	 Catch:{ Exception -> 0x005d, all -> 0x0043 }
        r1 = new byte[r1];	 Catch:{ Exception -> 0x005d, all -> 0x0043 }
        r6.read(r1);	 Catch:{ Exception -> 0x005d, all -> 0x0043 }
        r3 = new io.branch.referral.b;	 Catch:{ Exception -> 0x005d, all -> 0x0043 }
        r3.<init>();	 Catch:{ Exception -> 0x005d, all -> 0x0043 }
        r1 = r3.a(r1);	 Catch:{ Exception -> 0x005d, all -> 0x0043 }
        if (r6 == 0) goto L_0x003c;
    L_0x0039:
        r6.close();	 Catch:{ NameNotFoundException | IOException -> 0x0041, NameNotFoundException | IOException -> 0x0041 }
    L_0x003c:
        if (r2 == 0) goto L_0x0041;
    L_0x003e:
        r2.close();	 Catch:{ NameNotFoundException | IOException -> 0x0041, NameNotFoundException | IOException -> 0x0041 }
    L_0x0041:
        r0 = r1;
        goto L_0x0067;
    L_0x0043:
        r1 = move-exception;
        goto L_0x0050;
    L_0x0045:
        r6 = move-exception;
        r4 = r1;
        r1 = r6;
        r6 = r4;
        goto L_0x0050;
    L_0x004a:
        r6 = r1;
        goto L_0x005d;
    L_0x004c:
        r6 = move-exception;
        r2 = r1;
        r1 = r6;
        r6 = r2;
    L_0x0050:
        if (r6 == 0) goto L_0x0055;
    L_0x0052:
        r6.close();	 Catch:{ IOException -> 0x005a }
    L_0x0055:
        if (r2 == 0) goto L_0x005a;
    L_0x0057:
        r2.close();	 Catch:{ IOException -> 0x005a }
    L_0x005a:
        throw r1;	 Catch:{  }
    L_0x005b:
        r6 = r1;
        r2 = r6;
    L_0x005d:
        if (r6 == 0) goto L_0x0062;
    L_0x005f:
        r6.close();	 Catch:{ NameNotFoundException -> 0x0067 }
    L_0x0062:
        if (r2 == 0) goto L_0x0067;
    L_0x0064:
        r2.close();	 Catch:{ NameNotFoundException -> 0x0067 }
    L_0x0067:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: io.branch.referral.ag.a(java.lang.String):java.lang.String");
    }

    private boolean l() {
        ActivityManager activityManager = (ActivityManager) this.c.getSystemService("activity");
        MemoryInfo memoryInfo = new MemoryInfo();
        activityManager.getMemoryInfo(memoryInfo);
        return memoryInfo.lowMemory;
    }

    @SuppressLint({"NewApi"})
    public JSONArray c() {
        JSONArray jSONArray = new JSONArray();
        PackageManager packageManager = this.c.getPackageManager();
        List<ApplicationInfo> installedApplications = packageManager.getInstalledApplications(128);
        if (installedApplications != null) {
            for (ApplicationInfo applicationInfo : installedApplications) {
                if ((applicationInfo.flags & 1) != 1) {
                    JSONObject jSONObject = new JSONObject();
                    try {
                        Object obj;
                        CharSequence loadLabel = applicationInfo.loadLabel(packageManager);
                        if (loadLabel == null) {
                            obj = null;
                        } else {
                            obj = loadLabel.toString();
                        }
                        if (obj != null) {
                            jSONObject.put("name", obj);
                        }
                        String str = applicationInfo.packageName;
                        if (str != null) {
                            jSONObject.put(Jsonkey.AppIdentifier.getKey(), str);
                            str = a(str);
                            if (!str.equals("bnc_no_value")) {
                                jSONObject.put(Jsonkey.URIScheme.getKey(), str);
                            }
                        }
                        str = applicationInfo.publicSourceDir;
                        if (str != null) {
                            jSONObject.put("public_source_dir", str);
                        }
                        str = applicationInfo.sourceDir;
                        if (str != null) {
                            jSONObject.put("source_dir", str);
                        }
                        PackageInfo packageInfo = packageManager.getPackageInfo(applicationInfo.packageName, 4096);
                        if (packageInfo != null) {
                            if (packageInfo.versionCode >= 9) {
                                jSONObject.put("install_date", packageInfo.firstInstallTime);
                                jSONObject.put("last_update_date", packageInfo.lastUpdateTime);
                            }
                            jSONObject.put("version_code", packageInfo.versionCode);
                            if (packageInfo.versionName != null) {
                                jSONObject.put("version_name", packageInfo.versionName);
                            }
                        }
                        jSONObject.put(Jsonkey.OS.getKey(), g());
                        jSONArray.put(jSONObject);
                    } catch (NameNotFoundException | JSONException unused) {
                    }
                }
            }
        }
        return jSONArray;
    }

    public String d() {
        try {
            PackageInfo packageInfo = this.c.getPackageManager().getPackageInfo(this.c.getPackageName(), 0);
            return packageInfo.versionName != null ? packageInfo.versionName : "bnc_no_value";
        } catch (NameNotFoundException unused) {
            return "bnc_no_value";
        }
    }

    public String e() {
        return Build.MANUFACTURER;
    }

    public String f() {
        return Build.MODEL;
    }

    public int h() {
        return VERSION.SDK_INT;
    }

    @SuppressLint({"NewApi"})
    public int b(boolean z) {
        m a = m.a(this.c);
        String d = d();
        if ("bnc_no_value".equals(a.e())) {
            if (z) {
                a.a(d);
            }
            if (VERSION.SDK_INT >= 9) {
                try {
                    PackageInfo packageInfo = this.c.getPackageManager().getPackageInfo(this.c.getPackageName(), 0);
                    if (packageInfo.lastUpdateTime != packageInfo.firstInstallTime) {
                        return 2;
                    }
                    return 0;
                } catch (NameNotFoundException unused) {
                }
            }
            return 0;
        } else if (a.e().equals(d)) {
            return 1;
        } else {
            if (z) {
                a.a(d);
            }
            return 2;
        }
    }

    public DisplayMetrics i() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) this.c.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics;
    }

    public boolean j() {
        boolean z = false;
        if (this.c.checkCallingOrSelfPermission("android.permission.ACCESS_NETWORK_STATE") != 0) {
            return false;
        }
        NetworkInfo networkInfo = ((ConnectivityManager) this.c.getSystemService("connectivity")).getNetworkInfo(1);
        if (networkInfo != null && networkInfo.isConnected()) {
            z = true;
        }
        return z;
    }

    public Object k() {
        try {
            return Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient").getMethod("getAdvertisingIdInfo", new Class[]{Context.class}).invoke(null, new Object[]{this.c});
        } catch (Throwable unused) {
            return null;
        }
    }

    public String a(Object obj) {
        try {
            this.a = (String) obj.getClass().getMethod("getId", new Class[0]).invoke(obj, new Object[0]);
        } catch (Exception unused) {
        }
        return this.a;
    }

    public int b(Object obj) {
        try {
            this.b = ((Boolean) obj.getClass().getMethod("isLimitAdTrackingEnabled", new Class[0]).invoke(obj, new Object[0])).booleanValue();
        } catch (Exception unused) {
        }
        return this.b;
    }

    public boolean a(a aVar) {
        if (!TextUtils.isEmpty(this.a)) {
            return false;
        }
        new b(aVar).a(new Void[0]);
        return true;
    }
}
