package com.facebook.ads.internal.s.c;

import android.annotation.TargetApi;
import android.content.Context;
import android.support.annotation.AnyThread;
import android.text.TextUtils;
import android.webkit.WebSettings;
import com.facebook.ads.internal.i.b;
import com.facebook.ads.internal.r.a.a;
import com.facebook.ads.internal.settings.AdInternalSettings;
import com.payu.custombrowser.util.CBConstant;
import java.lang.reflect.Constructor;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

@AnyThread
public class d {
    private static String a;
    private static final Set<String> b = new HashSet(1);
    private static final Set<String> c = new HashSet(2);

    static {
        b.add("1ww8E0AYsR2oX5lndk2hwp2Uosk=\n");
        c.add("toZ2GRnRjC9P5VVUdCpOrFH8lfQ=\n");
        c.add("3lKvjNsfmrn+WmfDhvr2iVh/yRs=\n");
        c.add("B08QtE4yLCdli4rptyqAEczXOeA=\n");
        c.add("XZXI6anZbdKf+taURdnyUH5ipgM=\n");
    }

    public static a a(Context context) {
        return a(context, true);
    }

    public static a a(Context context, boolean z) {
        a aVar = new a();
        a(context, aVar, z);
        return aVar;
    }

    private static String a(Context context, String str, String str2) {
        Class cls = Class.forName(str);
        Constructor declaredConstructor = cls.getDeclaredConstructor(new Class[]{Context.class, Class.forName(str2)});
        declaredConstructor.setAccessible(true);
        try {
            String str3 = (String) cls.getMethod("getUserAgentString", new Class[0]).invoke(declaredConstructor.newInstance(new Object[]{context, null}), new Object[0]);
            return str3;
        } finally {
            declaredConstructor.setAccessible(false);
        }
    }

    private static void a(Context context, a aVar, boolean z) {
        b bVar = new b(context);
        if (a()) {
            aVar.c(360000);
            aVar.d(CBConstant.VERIFY_HTTP_TIMEOUT);
        } else {
            aVar.c(30000);
        }
        aVar.b(3);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(c(context, z));
        stringBuilder.append(" [FBAN/AudienceNetworkForAndroid;FBSN/");
        stringBuilder.append(InternalLogger.EVENT_PARAM_SDK_ANDROID);
        stringBuilder.append(";FBSV/");
        stringBuilder.append(b.a);
        stringBuilder.append(";FBAB/");
        stringBuilder.append(bVar.f());
        stringBuilder.append(";FBAV/");
        stringBuilder.append(bVar.g());
        stringBuilder.append(";FBBV/");
        stringBuilder.append(bVar.h());
        stringBuilder.append(";FBVS/");
        stringBuilder.append("5.0.0");
        stringBuilder.append(";FBLC/");
        stringBuilder.append(Locale.getDefault().toString());
        stringBuilder.append("]");
        aVar.a("user-agent", stringBuilder.toString());
    }

    public static boolean a() {
        String urlPrefix = AdInternalSettings.getUrlPrefix();
        return !TextUtils.isEmpty(urlPrefix) && urlPrefix.endsWith(".sb");
    }

    public static a b(Context context) {
        return b(context, true);
    }

    public static a b(Context context, boolean z) {
        a aVar = new a();
        a(context, aVar, z);
        if (!a()) {
            aVar.b(c);
            aVar.a(b);
        }
        return aVar;
    }

    @TargetApi(17)
    private static String c(Context context) {
        return WebSettings.getDefaultUserAgent(context);
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:26:0x0030 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:28:0x003b */
    /* JADX WARNING: Missing exception handler attribute for start block: B:30:0x0046 */
    /* JADX WARNING: Can't wrap try/catch for region: R(9:18|19|(5:21|22|23|24|25)|26|27|28|29|30|31) */
    /* JADX WARNING: Can't wrap try/catch for region: R(9:18|19|(5:21|22|23|24|25)|26|27|28|29|30|31) */
    /* JADX WARNING: Can't wrap try/catch for region: R(9:18|19|(5:21|22|23|24|25)|26|27|28|29|30|31) */
    /* JADX WARNING: Missing block: B:34:0x005f, code skipped:
            return a;
     */
    private static java.lang.String c(android.content.Context r2, boolean r3) {
        /*
        if (r2 != 0) goto L_0x0005;
    L_0x0002:
        r2 = "Unknown";
        return r2;
    L_0x0005:
        if (r3 == 0) goto L_0x000e;
    L_0x0007:
        r2 = "http.agent";
        r2 = java.lang.System.getProperty(r2);
        return r2;
    L_0x000e:
        r3 = a;
        if (r3 == 0) goto L_0x0015;
    L_0x0012:
        r2 = a;
        return r2;
    L_0x0015:
        r3 = com.facebook.ads.internal.s.c.d.class;
        monitor-enter(r3);
        r0 = a;	 Catch:{ all -> 0x0060 }
        if (r0 == 0) goto L_0x0020;
    L_0x001c:
        r2 = a;	 Catch:{ all -> 0x0060 }
        monitor-exit(r3);	 Catch:{ all -> 0x0060 }
        return r2;
    L_0x0020:
        r0 = android.os.Build.VERSION.SDK_INT;	 Catch:{ all -> 0x0060 }
        r1 = 17;
        if (r0 < r1) goto L_0x0030;
    L_0x0026:
        r0 = c(r2);	 Catch:{ Exception -> 0x0030 }
        a = r0;	 Catch:{ Exception -> 0x0030 }
        r0 = a;	 Catch:{ Exception -> 0x0030 }
        monitor-exit(r3);	 Catch:{ all -> 0x0060 }
        return r0;
    L_0x0030:
        r0 = "android.webkit.WebSettings";
        r1 = "android.webkit.WebView";
        r0 = a(r2, r0, r1);	 Catch:{ Exception -> 0x003b }
        a = r0;	 Catch:{ Exception -> 0x003b }
        goto L_0x005c;
    L_0x003b:
        r0 = "android.webkit.WebSettingsClassic";
        r1 = "android.webkit.WebViewClassic";
        r0 = a(r2, r0, r1);	 Catch:{ Exception -> 0x0046 }
        a = r0;	 Catch:{ Exception -> 0x0046 }
        goto L_0x005c;
    L_0x0046:
        r0 = new android.webkit.WebView;	 Catch:{ all -> 0x0060 }
        r2 = r2.getApplicationContext();	 Catch:{ all -> 0x0060 }
        r0.<init>(r2);	 Catch:{ all -> 0x0060 }
        r2 = r0.getSettings();	 Catch:{ all -> 0x0060 }
        r2 = r2.getUserAgentString();	 Catch:{ all -> 0x0060 }
        a = r2;	 Catch:{ all -> 0x0060 }
        r0.destroy();	 Catch:{ all -> 0x0060 }
    L_0x005c:
        monitor-exit(r3);	 Catch:{ all -> 0x0060 }
        r2 = a;
        return r2;
    L_0x0060:
        r2 = move-exception;
        monitor-exit(r3);	 Catch:{ all -> 0x0060 }
        throw r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.ads.internal.s.c.d.c(android.content.Context, boolean):java.lang.String");
    }
}
