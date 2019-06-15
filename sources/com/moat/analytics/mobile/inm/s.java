package com.moat.analytics.mobile.inm;

import android.content.Context;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.provider.Settings.Global;
import android.provider.Settings.Secure;
import android.support.annotation.FloatRange;
import android.telephony.TelephonyManager;
import java.lang.ref.WeakReference;

class s {
    private static String a;
    private static a b;
    private static b c;

    static class a {
        private boolean a;
        private String b;
        private String c;
        private String d;

        private a() {
            this.a = false;
            this.b = "_unknown_";
            this.c = "_unknown_";
            this.d = "_unknown_";
            try {
                Context c = s.c();
                if (c != null) {
                    this.a = true;
                    PackageManager packageManager = c.getPackageManager();
                    this.c = c.getPackageName();
                    this.b = packageManager.getApplicationLabel(c.getApplicationInfo()).toString();
                    this.d = packageManager.getInstallerPackageName(this.c);
                    return;
                }
                p.a(3, "Util", (Object) this, "Can't get app name, appContext is null.");
            } catch (Exception e) {
                m.a(e);
            }
        }

        /* synthetic */ a(AnonymousClass1 anonymousClass1) {
            this();
        }

        /* Access modifiers changed, original: 0000 */
        public String a() {
            return this.b;
        }

        /* Access modifiers changed, original: 0000 */
        public String b() {
            return this.c;
        }

        /* Access modifiers changed, original: 0000 */
        public String c() {
            return this.d != null ? this.d : "_unknown_";
        }
    }

    static class b {
        String a;
        String b;
        Integer c;
        boolean d;
        boolean e;
        boolean f;

        private b() {
            this.a = "_unknown_";
            this.b = "_unknown_";
            this.c = Integer.valueOf(-1);
            this.d = false;
            this.e = false;
            this.f = false;
            try {
                Context c = s.c();
                if (c != null) {
                    this.f = true;
                    TelephonyManager telephonyManager = (TelephonyManager) c.getSystemService("phone");
                    this.a = telephonyManager.getSimOperatorName();
                    this.b = telephonyManager.getNetworkOperatorName();
                    this.c = Integer.valueOf(telephonyManager.getPhoneType());
                    this.d = s.i();
                    this.e = s.b(c);
                }
            } catch (Exception e) {
                m.a(e);
            }
        }

        /* synthetic */ b(AnonymousClass1 anonymousClass1) {
            this();
        }
    }

    s() {
    }

    @FloatRange(from = 0.0d, to = 1.0d)
    static double a() {
        try {
            AudioManager audioManager = (AudioManager) a.a().getSystemService("audio");
            return ((double) h()) / ((double) audioManager.getStreamMaxVolume(3));
        } catch (Exception e) {
            m.a(e);
            return 0.0d;
        }
    }

    static void a(final Context context) {
        try {
            AsyncTask.execute(new Runnable() {
                public final void run() {
                    Throwable e;
                    String str;
                    String str2;
                    try {
                        Class cls = Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient");
                        Class cls2 = Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient$Info");
                        Object invoke = cls.getMethod("getAdvertisingIdInfo", new Class[]{Context.class}).invoke(null, new Object[]{context});
                        if (((Boolean) cls2.getMethod("isLimitAdTrackingEnabled", new Class[0]).invoke(invoke, new Object[0])).booleanValue()) {
                            p.a(3, "Util", (Object) this, "User has limited ad tracking");
                            return;
                        }
                        s.a = (String) cls2.getMethod("getId", new Class[0]).invoke(invoke, new Object[0]);
                        StringBuilder stringBuilder = new StringBuilder("Retrieved Advertising ID = ");
                        stringBuilder.append(s.a);
                        p.a(3, "Util", (Object) this, stringBuilder.toString());
                    } catch (ClassNotFoundException e2) {
                        e = e2;
                        str = "Util";
                        str2 = "ClassNotFoundException while retrieving Advertising ID";
                        p.a(str, (Object) this, str2, e);
                    } catch (NoSuchMethodException e3) {
                        e = e3;
                        str = "Util";
                        str2 = "NoSuchMethodException while retrieving Advertising ID";
                        p.a(str, (Object) this, str2, e);
                    } catch (Exception e4) {
                        m.a(e4);
                    }
                }
            });
        } catch (Exception e) {
            m.a(e);
        }
    }

    static String b() {
        return a;
    }

    static boolean b(Context context) {
        return (context.getApplicationInfo().flags & 2) != 0;
    }

    static Context c() {
        WeakReference weakReference = ((k) MoatAnalytics.getInstance()).e;
        return weakReference != null ? (Context) weakReference.get() : null;
    }

    static a d() {
        if (b == null || !b.a) {
            b = new a();
        }
        return b;
    }

    static b e() {
        if (c == null || !c.f) {
            c = new b();
        }
        return c;
    }

    private static int h() {
        try {
            return ((AudioManager) a.a().getSystemService("audio")).getStreamVolume(3);
        } catch (Exception e) {
            m.a(e);
            return 0;
        }
    }

    private static boolean i() {
        Context c = c();
        int i = c != null ? VERSION.SDK_INT >= 17 ? Global.getInt(c.getContentResolver(), "adb_enabled", 0) : Secure.getInt(c.getContentResolver(), "adb_enabled", 0) : 0;
        return i == 1;
    }
}
