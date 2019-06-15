package com.appsflyer;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.text.TextUtils;
import com.google.android.gms.stats.CodePackage;
import com.google.firebase.iid.FirebaseInstanceIdService;
import java.lang.ref.WeakReference;

final class ag {

    static class a extends AsyncTask<Void, Void, String> {
        private final WeakReference<Context> a;
        private String b;

        /* Access modifiers changed, original: protected|final|synthetic */
        public final /* synthetic */ Object doInBackground(Object[] objArr) {
            return a();
        }

        /* Access modifiers changed, original: protected|final|synthetic */
        public final /* synthetic */ void onPostExecute(Object obj) {
            String str = (String) obj;
            if (!TextUtils.isEmpty(str)) {
                String a = i.a().a("afUninstallToken");
                r rVar = new r(str);
                if (a == null) {
                    ag.a((Context) this.a.get(), rVar);
                    return;
                }
                r a2 = r.a(a);
                if (a2.a(rVar)) {
                    ag.a((Context) this.a.get(), a2);
                }
            }
        }

        a(WeakReference<Context> weakReference) {
            this.a = weakReference;
        }

        /* Access modifiers changed, original: protected|final */
        public final void onPreExecute() {
            super.onPreExecute();
            this.b = i.a().a("gcmProjectNumber");
        }

        private String a() {
            String str = null;
            try {
                if (this.b != null) {
                    str = ag.b(this.a, this.b);
                }
                return str;
            } catch (Throwable th) {
                AFLogger.a("Error registering for uninstall feature", th);
                return null;
            }
        }
    }

    static boolean a(Context context) {
        return c(context) | b(context);
    }

    /* JADX WARNING: Missing block: B:13:0x0049, code skipped:
            if ((r6.getPackageManager().queryIntentServices(r2, 0).size() > 0) != false) goto L_0x004b;
     */
    private static boolean b(android.content.Context r6) {
        /*
        r0 = com.appsflyer.h.c();
        r0 = r0.d();
        r1 = 0;
        if (r0 == 0) goto L_0x000c;
    L_0x000b:
        return r1;
    L_0x000c:
        r0 = "com.google.android.gms.iid.InstanceIDListenerService";
        java.lang.Class.forName(r0);	 Catch:{ ClassNotFoundException -> 0x009a, Throwable -> 0x0093 }
        r0 = new android.content.Intent;	 Catch:{ ClassNotFoundException -> 0x009a, Throwable -> 0x0093 }
        r2 = "com.google.android.gms.iid.InstanceID";
        r3 = com.appsflyer.k.class;
        r4 = 0;
        r0.<init>(r2, r4, r6, r3);	 Catch:{ ClassNotFoundException -> 0x009a, Throwable -> 0x0093 }
        r2 = new android.content.Intent;	 Catch:{ ClassNotFoundException -> 0x009a, Throwable -> 0x0093 }
        r3 = "com.google.android.gms.iid.InstanceID";
        r5 = com.google.android.gms.iid.InstanceIDListenerService.class;
        r2.<init>(r3, r4, r6, r5);	 Catch:{ ClassNotFoundException -> 0x009a, Throwable -> 0x0093 }
        r3 = r6.getPackageManager();	 Catch:{ ClassNotFoundException -> 0x009a, Throwable -> 0x0093 }
        r0 = r3.queryIntentServices(r0, r1);	 Catch:{ ClassNotFoundException -> 0x009a, Throwable -> 0x0093 }
        r0 = r0.size();	 Catch:{ ClassNotFoundException -> 0x009a, Throwable -> 0x0093 }
        r3 = 1;
        if (r0 <= 0) goto L_0x0035;
    L_0x0033:
        r0 = r3;
        goto L_0x0036;
    L_0x0035:
        r0 = r1;
    L_0x0036:
        if (r0 != 0) goto L_0x004b;
    L_0x0038:
        r0 = r6.getPackageManager();	 Catch:{ ClassNotFoundException -> 0x009a, Throwable -> 0x0093 }
        r0 = r0.queryIntentServices(r2, r1);	 Catch:{ ClassNotFoundException -> 0x009a, Throwable -> 0x0093 }
        r0 = r0.size();	 Catch:{ ClassNotFoundException -> 0x009a, Throwable -> 0x0093 }
        if (r0 <= 0) goto L_0x0048;
    L_0x0046:
        r0 = r3;
        goto L_0x0049;
    L_0x0048:
        r0 = r1;
    L_0x0049:
        if (r0 == 0) goto L_0x00a2;
    L_0x004b:
        r0 = new android.content.Intent;	 Catch:{ ClassNotFoundException -> 0x009a, Throwable -> 0x0093 }
        r2 = "com.google.android.c2dm.intent.RECEIVE";
        r5 = "com.google.android.gms.gcm.GcmReceiver";
        r5 = java.lang.Class.forName(r5);	 Catch:{ ClassNotFoundException -> 0x009a, Throwable -> 0x0093 }
        r0.<init>(r2, r4, r6, r5);	 Catch:{ ClassNotFoundException -> 0x009a, Throwable -> 0x0093 }
        r2 = r6.getPackageManager();	 Catch:{ ClassNotFoundException -> 0x009a, Throwable -> 0x0093 }
        r0 = r2.queryBroadcastReceivers(r0, r1);	 Catch:{ ClassNotFoundException -> 0x009a, Throwable -> 0x0093 }
        r0 = r0.size();	 Catch:{ ClassNotFoundException -> 0x009a, Throwable -> 0x0093 }
        if (r0 <= 0) goto L_0x0068;
    L_0x0066:
        r0 = r3;
        goto L_0x0069;
    L_0x0068:
        r0 = r1;
    L_0x0069:
        if (r0 == 0) goto L_0x008d;
    L_0x006b:
        r0 = r6.getPackageName();	 Catch:{ ClassNotFoundException -> 0x009a, Throwable -> 0x0093 }
        r2 = new java.lang.StringBuilder;	 Catch:{ ClassNotFoundException -> 0x009a, Throwable -> 0x0093 }
        r2.<init>();	 Catch:{ ClassNotFoundException -> 0x009a, Throwable -> 0x0093 }
        r2.append(r0);	 Catch:{ ClassNotFoundException -> 0x009a, Throwable -> 0x0093 }
        r0 = ".permission.C2D_MESSAGE";
        r2.append(r0);	 Catch:{ ClassNotFoundException -> 0x009a, Throwable -> 0x0093 }
        r0 = r2.toString();	 Catch:{ ClassNotFoundException -> 0x009a, Throwable -> 0x0093 }
        r6 = com.appsflyer.w.b.a(r6, r0);	 Catch:{ ClassNotFoundException -> 0x009a, Throwable -> 0x0093 }
        if (r6 == 0) goto L_0x0087;
    L_0x0086:
        return r3;
    L_0x0087:
        r6 = "Cannot verify existence of the app's \"permission.C2D_MESSAGE\" permission in the manifest. Please refer to documentation.";
        com.appsflyer.AFLogger.e(r6);	 Catch:{ ClassNotFoundException -> 0x009a, Throwable -> 0x0093 }
        goto L_0x00a2;
    L_0x008d:
        r6 = "Cannot verify existence of GcmReceiver receiver in the manifest. Please refer to documentation.";
        com.appsflyer.AFLogger.e(r6);	 Catch:{ ClassNotFoundException -> 0x009a, Throwable -> 0x0093 }
        goto L_0x00a2;
    L_0x0093:
        r6 = move-exception;
        r0 = "An error occurred while trying to verify manifest declarations: ";
        com.appsflyer.AFLogger.a(r0, r6);
        goto L_0x00a2;
    L_0x009a:
        r6 = move-exception;
        r6 = r6.getMessage();
        com.appsflyer.AFLogger.a(r6);
    L_0x00a2:
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.ag.b(android.content.Context):boolean");
    }

    private static boolean c(Context context) {
        if (h.c().d()) {
            return false;
        }
        try {
            Class.forName("com.google.firebase.iid.FirebaseInstanceIdService");
            Intent intent = new Intent("com.google.firebase.INSTANCE_ID_EVENT", null, context, j.class);
            Intent intent2 = new Intent("com.google.firebase.INSTANCE_ID_EVENT", null, context, FirebaseInstanceIdService.class);
            if (!(context.getPackageManager().queryIntentServices(intent, 0).size() > 0)) {
                if (!(context.getPackageManager().queryIntentServices(intent2, 0).size() > 0)) {
                    AFLogger.e("Cannot verify existence of our InstanceID Listener Service in the manifest. Please refer to documentation.");
                    return false;
                }
            }
            return true;
        } catch (ClassNotFoundException unused) {
        } catch (Throwable th) {
            AFLogger.a("An error occurred while trying to verify manifest declarations: ", th);
        }
    }

    private static String b(WeakReference<Context> weakReference, String str) {
        try {
            Class cls = Class.forName("com.google.android.gms.iid.InstanceID");
            Class.forName("com.google.android.gms.gcm.GcmReceiver");
            Object invoke = cls.getDeclaredMethod("getInstance", new Class[]{Context.class}).invoke(cls, new Object[]{weakReference.get()});
            String str2 = (String) cls.getDeclaredMethod("getToken", new Class[]{String.class, String.class}).invoke(invoke, new Object[]{str, CodePackage.GCM});
            if (str2 != null) {
                return str2;
            }
            AFLogger.e("Couldn't get token using reflection.");
            return null;
        } catch (ClassNotFoundException unused) {
            return null;
        } catch (Throwable th) {
            AFLogger.a("Couldn't get token using GoogleCloudMessaging. ", th);
            return null;
        }
    }

    static void a(Context context, r rVar) {
        StringBuilder stringBuilder = new StringBuilder("updateServerUninstallToken called with: ");
        stringBuilder.append(rVar.toString());
        AFLogger.d(stringBuilder.toString());
        r a = r.a(i.a().a("afUninstallToken"));
        if (!context.getSharedPreferences("appsflyer-data", 0).getBoolean("sentRegisterRequestToAF", false) || a.a() == null || !a.a().equals(rVar.a())) {
            i.a().a("afUninstallToken", rVar.toString());
            h.c().c(context, rVar.a());
        }
    }
}
