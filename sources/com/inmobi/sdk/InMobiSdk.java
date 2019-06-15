package com.inmobi.sdk;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Build.VERSION;
import android.os.SystemClock;
import com.facebook.internal.AnalyticsEvents;
import com.inmobi.a.o;
import com.inmobi.ads.cache.AssetStore;
import com.inmobi.commons.a.a;
import com.inmobi.commons.core.d.c;
import com.inmobi.commons.core.e.b;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.commons.core.utilities.b.e;
import com.inmobi.commons.core.utilities.b.g;
import com.payu.custombrowser.util.CBConstant;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONObject;

public final class InMobiSdk {
    private static final ExecutorService COMPONENT_SERVICE = Executors.newSingleThreadExecutor();
    public static final String IM_GDPR_CONSENT_AVAILABLE = "gdpr_consent_available";
    private static final String TAG = "InMobiSdk";

    public enum AgeGroup {
        BELOW_18("below18"),
        BETWEEN_18_AND_24("between18and24"),
        BETWEEN_25_AND_29("between25and29"),
        BETWEEN_30_AND_34("between30and34"),
        BETWEEN_35_AND_44("between35and44"),
        BETWEEN_45_AND_54("between45and54"),
        BETWEEN_55_AND_65("between55and65"),
        ABOVE_65("above65");
        
        private String a;

        private AgeGroup(String str) {
            this.a = str;
        }

        public final String toString() {
            return this.a;
        }
    }

    public enum Education {
        HIGH_SCHOOL_OR_LESS("highschoolorless"),
        COLLEGE_OR_GRADUATE("collegeorgraduate"),
        POST_GRADUATE_OR_ABOVE("postgraduateorabove");
        
        private String a;

        private Education(String str) {
            this.a = str;
        }

        public final String toString() {
            return this.a;
        }
    }

    public enum Gender {
        FEMALE("f"),
        MALE("m");
        
        private String a;

        private Gender(String str) {
            this.a = str;
        }

        public final String toString() {
            return this.a;
        }
    }

    public enum LogLevel {
        NONE,
        ERROR,
        DEBUG
    }

    public static String getVersion() {
        return "7.2.1";
    }

    public static void init(Context context, String str) {
        init(context, str, null);
    }

    public static void init(Context context, String str, JSONObject jSONObject) {
        StringBuilder stringBuilder;
        Exception e;
        long elapsedRealtime = SystemClock.elapsedRealtime();
        String trim;
        InternalLogLevel internalLogLevel;
        StringBuilder stringBuilder2;
        HashMap hashMap;
        try {
            e.a(jSONObject);
            if (VERSION.SDK_INT < 14) {
                Logger.a(InternalLogLevel.ERROR, TAG, "The minimum supported Android API level is 14, SDK could not be initialized.");
            } else if (context == null) {
                Logger.a(InternalLogLevel.ERROR, TAG, "Context supplied as null, SDK could not be initialized.");
            } else {
                if (str != null) {
                    if (str.trim().length() != 0) {
                        Intent intent = new Intent();
                        intent.setClassName(context.getPackageName(), "com.inmobi.rendering.InMobiAdActivity");
                        if (context.getPackageManager().resolveActivity(intent, 65536) == null) {
                            Logger.a(InternalLogLevel.ERROR, TAG, "The activity com.inmobi.rendering.InMobiAdActivity not present in AndroidManifest. SDK could not be initialized.");
                            return;
                        }
                        if (com.inmobi.commons.core.utilities.e.a(context, "ads", "android.permission.INTERNET")) {
                            if (com.inmobi.commons.core.utilities.e.a(context, "ads", "android.permission.ACCESS_NETWORK_STATE")) {
                                if (!(com.inmobi.commons.core.utilities.e.a(context, "ads", "android.permission.ACCESS_COARSE_LOCATION") || com.inmobi.commons.core.utilities.e.a(context, "ads", "android.permission.ACCESS_FINE_LOCATION"))) {
                                    Logger.a(InternalLogLevel.ERROR, TAG, "Please grant the location permissions (ACCESS_COARSE_LOCATION or ACCESS_FINE_LOCATION, or both) for better ad targeting.");
                                }
                                trim = str.trim();
                                try {
                                    if (!(trim.length() == 32 || trim.length() == 36)) {
                                        Logger.a(InternalLogLevel.DEBUG, TAG, "Invalid account id passed to init. Please provide a valid account id.");
                                    }
                                    if (a.a()) {
                                        try {
                                            b.a();
                                            b.a("root", "InitRequested", null);
                                            return;
                                        } catch (Exception e2) {
                                            stringBuilder = new StringBuilder("Error in submitting telemetry event : (");
                                            stringBuilder.append(e2.getMessage());
                                            stringBuilder.append(")");
                                            return;
                                        }
                                    }
                                    if (hasSdkVersionChanged(context)) {
                                        com.inmobi.commons.a.b.a(context, a.a(context));
                                        c.a(context, "sdk_version_store").a("sdk_version", "7.2.1");
                                        resetMediaCache(context.getApplicationContext());
                                    }
                                    a.a(context, trim);
                                    com.inmobi.commons.core.configs.b.a().b();
                                    b.a().b();
                                    if (c.a(context, "sdk_version_store").b("db_deletion_failed", false)) {
                                        List<String> b = a.b(context);
                                        for (String sendDbDeletionTelemetryEvent : b) {
                                            sendDbDeletionTelemetryEvent(sendDbDeletionTelemetryEvent);
                                        }
                                        if (b.isEmpty()) {
                                            com.inmobi.commons.a.b.a(context, false);
                                        }
                                    }
                                    g.b();
                                    initComponents();
                                    com.inmobi.commons.core.configs.b.a();
                                    com.inmobi.commons.core.configs.b.d();
                                    COMPONENT_SERVICE.execute(new Runnable() {
                                        /*  JADX ERROR: ConcurrentModificationException in pass: EliminatePhiNodes
                                            java.util.ConcurrentModificationException
                                            	at java.util.ArrayList$Itr.checkForComodification(Unknown Source)
                                            	at java.util.ArrayList$Itr.next(Unknown Source)
                                            	at jadx.core.dex.visitors.ssa.EliminatePhiNodes.replaceMerge(EliminatePhiNodes.java:114)
                                            	at jadx.core.dex.visitors.ssa.EliminatePhiNodes.replaceMergeInstructions(EliminatePhiNodes.java:68)
                                            	at jadx.core.dex.visitors.ssa.EliminatePhiNodes.visit(EliminatePhiNodes.java:31)
                                            	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
                                            	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
                                            	at java.util.ArrayList.forEach(Unknown Source)
                                            	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
                                            	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$0(DepthTraversal.java:13)
                                            	at java.util.ArrayList.forEach(Unknown Source)
                                            	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:13)
                                            	at jadx.core.ProcessClass.process(ProcessClass.java:32)
                                            	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:51)
                                            	at java.lang.Iterable.forEach(Unknown Source)
                                            	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:51)
                                            	at jadx.core.ProcessClass.process(ProcessClass.java:37)
                                            	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:292)
                                            	at jadx.api.JavaClass.decompile(JavaClass.java:62)
                                            	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
                                            */
                                        public final void run() {
                                            /*
                                            r20 = this;
                                            com.inmobi.ads.b.c();	 Catch:{ Exception -> 0x0101 }
                                            r1 = com.inmobi.ads.cache.AssetStore.a();	 Catch:{ Exception -> 0x0101 }
                                            r1.b();	 Catch:{ Exception -> 0x0101 }
                                            r1 = com.inmobi.ads.cache.AssetStore.a();	 Catch:{ Exception -> 0x0101 }
                                            r2 = com.inmobi.ads.cache.AssetStore.e;	 Catch:{ Exception -> 0x0101 }
                                            monitor-enter(r2);	 Catch:{ Exception -> 0x0101 }
                                            r3 = r1.a;	 Catch:{ all -> 0x00fd }
                                            r3 = com.inmobi.ads.cache.d.b();	 Catch:{ all -> 0x00fd }
                                            r4 = r3.isEmpty();	 Catch:{ all -> 0x00fd }
                                            if (r4 == 0) goto L_0x001f;	 Catch:{ all -> 0x00fd }
                                        L_0x001d:
                                            monitor-exit(r2);	 Catch:{ all -> 0x00fd }
                                            return;	 Catch:{ all -> 0x00fd }
                                        L_0x001f:
                                            r4 = r3.iterator();	 Catch:{ all -> 0x00fd }
                                        L_0x0023:
                                            r5 = r4.hasNext();	 Catch:{ all -> 0x00fd }
                                            r6 = 1;	 Catch:{ all -> 0x00fd }
                                            r7 = 0;	 Catch:{ all -> 0x00fd }
                                            if (r5 == 0) goto L_0x0043;	 Catch:{ all -> 0x00fd }
                                        L_0x002b:
                                            r5 = r4.next();	 Catch:{ all -> 0x00fd }
                                            r5 = (com.inmobi.ads.cache.a) r5;	 Catch:{ all -> 0x00fd }
                                            r8 = java.lang.System.currentTimeMillis();	 Catch:{ all -> 0x00fd }
                                            r10 = r5.h;	 Catch:{ all -> 0x00fd }
                                            r12 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1));	 Catch:{ all -> 0x00fd }
                                            if (r12 <= 0) goto L_0x003c;	 Catch:{ all -> 0x00fd }
                                        L_0x003b:
                                            goto L_0x003d;	 Catch:{ all -> 0x00fd }
                                        L_0x003c:
                                            r6 = r7;	 Catch:{ all -> 0x00fd }
                                        L_0x003d:
                                            if (r6 == 0) goto L_0x0023;	 Catch:{ all -> 0x00fd }
                                        L_0x003f:
                                            com.inmobi.ads.cache.AssetStore.a(r5);	 Catch:{ all -> 0x00fd }
                                            goto L_0x0023;	 Catch:{ all -> 0x00fd }
                                        L_0x0043:
                                            r4 = com.inmobi.ads.cache.d.b();	 Catch:{ all -> 0x00fd }
                                            r8 = 0;	 Catch:{ all -> 0x00fd }
                                            r4 = r4.iterator();	 Catch:{ all -> 0x00fd }
                                        L_0x004d:
                                            r5 = r4.hasNext();	 Catch:{ all -> 0x00fd }
                                            if (r5 == 0) goto L_0x0068;	 Catch:{ all -> 0x00fd }
                                        L_0x0053:
                                            r5 = r4.next();	 Catch:{ all -> 0x00fd }
                                            r5 = (com.inmobi.ads.cache.a) r5;	 Catch:{ all -> 0x00fd }
                                            r10 = new java.io.File;	 Catch:{ all -> 0x00fd }
                                            r5 = r5.e;	 Catch:{ all -> 0x00fd }
                                            r10.<init>(r5);	 Catch:{ all -> 0x00fd }
                                            r10 = r10.length();	 Catch:{ all -> 0x00fd }
                                            r12 = r8 + r10;	 Catch:{ all -> 0x00fd }
                                            r8 = r12;	 Catch:{ all -> 0x00fd }
                                            goto L_0x004d;	 Catch:{ all -> 0x00fd }
                                        L_0x0068:
                                            r4 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00fd }
                                            r5 = "MAX CACHESIZE ";	 Catch:{ all -> 0x00fd }
                                            r4.<init>(r5);	 Catch:{ all -> 0x00fd }
                                            r5 = r1.b;	 Catch:{ all -> 0x00fd }
                                            r10 = r5.d;	 Catch:{ all -> 0x00fd }
                                            r4.append(r10);	 Catch:{ all -> 0x00fd }
                                            r4 = r1.b;	 Catch:{ all -> 0x00fd }
                                            r4 = r4.d;	 Catch:{ all -> 0x00fd }
                                            r10 = (r8 > r4 ? 1 : (r8 == r4 ? 0 : -1));	 Catch:{ all -> 0x00fd }
                                            if (r10 <= 0) goto L_0x00ac;	 Catch:{ all -> 0x00fd }
                                        L_0x007e:
                                            r11 = com.inmobi.commons.core.d.b.a();	 Catch:{ all -> 0x00fd }
                                            r12 = "asset";	 Catch:{ all -> 0x00fd }
                                            r13 = com.inmobi.ads.cache.d.a;	 Catch:{ all -> 0x00fd }
                                            r14 = 0;	 Catch:{ all -> 0x00fd }
                                            r15 = 0;	 Catch:{ all -> 0x00fd }
                                            r16 = 0;	 Catch:{ all -> 0x00fd }
                                            r17 = 0;	 Catch:{ all -> 0x00fd }
                                            r18 = "ts ASC ";	 Catch:{ all -> 0x00fd }
                                            r19 = 0;	 Catch:{ all -> 0x00fd }
                                            r4 = r11.a(r12, r13, r14, r15, r16, r17, r18, r19);	 Catch:{ all -> 0x00fd }
                                            r5 = r4.size();	 Catch:{ all -> 0x00fd }
                                            if (r5 != 0) goto L_0x009c;	 Catch:{ all -> 0x00fd }
                                        L_0x009a:
                                            r4 = 0;	 Catch:{ all -> 0x00fd }
                                            goto L_0x00a6;	 Catch:{ all -> 0x00fd }
                                        L_0x009c:
                                            r4 = r4.get(r7);	 Catch:{ all -> 0x00fd }
                                            r4 = (android.content.ContentValues) r4;	 Catch:{ all -> 0x00fd }
                                            r4 = com.inmobi.ads.cache.d.a(r4);	 Catch:{ all -> 0x00fd }
                                        L_0x00a6:
                                            if (r4 == 0) goto L_0x00ac;	 Catch:{ all -> 0x00fd }
                                        L_0x00a8:
                                            com.inmobi.ads.cache.AssetStore.a(r4);	 Catch:{ all -> 0x00fd }
                                            goto L_0x0043;	 Catch:{ all -> 0x00fd }
                                        L_0x00ac:
                                            r1 = com.inmobi.commons.a.a.b();	 Catch:{ all -> 0x00fd }
                                            r1 = com.inmobi.commons.a.a.a(r1);	 Catch:{ all -> 0x00fd }
                                            r4 = r1.exists();	 Catch:{ all -> 0x00fd }
                                            if (r4 == 0) goto L_0x00fb;	 Catch:{ all -> 0x00fd }
                                        L_0x00ba:
                                            r1 = r1.listFiles();	 Catch:{ all -> 0x00fd }
                                            if (r1 == 0) goto L_0x00fb;	 Catch:{ all -> 0x00fd }
                                        L_0x00c0:
                                            r4 = r1.length;	 Catch:{ all -> 0x00fd }
                                            r5 = r7;	 Catch:{ all -> 0x00fd }
                                        L_0x00c2:
                                            if (r5 >= r4) goto L_0x00fb;	 Catch:{ all -> 0x00fd }
                                        L_0x00c4:
                                            r8 = r1[r5];	 Catch:{ all -> 0x00fd }
                                            r9 = r3.iterator();	 Catch:{ all -> 0x00fd }
                                        L_0x00ca:
                                            r10 = r9.hasNext();	 Catch:{ all -> 0x00fd }
                                            if (r10 == 0) goto L_0x00e4;	 Catch:{ all -> 0x00fd }
                                        L_0x00d0:
                                            r10 = r9.next();	 Catch:{ all -> 0x00fd }
                                            r10 = (com.inmobi.ads.cache.a) r10;	 Catch:{ all -> 0x00fd }
                                            r11 = r8.getAbsolutePath();	 Catch:{ all -> 0x00fd }
                                            r10 = r10.e;	 Catch:{ all -> 0x00fd }
                                            r10 = r11.equals(r10);	 Catch:{ all -> 0x00fd }
                                            if (r10 == 0) goto L_0x00ca;	 Catch:{ all -> 0x00fd }
                                        L_0x00e2:
                                            r9 = r6;	 Catch:{ all -> 0x00fd }
                                            goto L_0x00e5;	 Catch:{ all -> 0x00fd }
                                        L_0x00e4:
                                            r9 = r7;	 Catch:{ all -> 0x00fd }
                                        L_0x00e5:
                                            if (r9 != 0) goto L_0x00f8;	 Catch:{ all -> 0x00fd }
                                        L_0x00e7:
                                            r9 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00fd }
                                            r10 = "found Orphan file ";	 Catch:{ all -> 0x00fd }
                                            r9.<init>(r10);	 Catch:{ all -> 0x00fd }
                                            r10 = r8.getAbsolutePath();	 Catch:{ all -> 0x00fd }
                                            r9.append(r10);	 Catch:{ all -> 0x00fd }
                                            r8.delete();	 Catch:{ all -> 0x00fd }
                                        L_0x00f8:
                                            r5 = r5 + 1;	 Catch:{ all -> 0x00fd }
                                            goto L_0x00c2;	 Catch:{ all -> 0x00fd }
                                        L_0x00fb:
                                            monitor-exit(r2);	 Catch:{ all -> 0x00fd }
                                            return;	 Catch:{ all -> 0x00fd }
                                        L_0x00fd:
                                            r0 = move-exception;	 Catch:{ all -> 0x00fd }
                                            r1 = r0;	 Catch:{ all -> 0x00fd }
                                            monitor-exit(r2);	 Catch:{ all -> 0x00fd }
                                            throw r1;	 Catch:{ Exception -> 0x0101 }
                                        L_0x0101:
                                            r0 = move-exception;
                                            r1 = r0;
                                            com.inmobi.sdk.InMobiSdk.TAG;
                                            r2 = new java.lang.StringBuilder;
                                            r3 = "Error in starting Asset Cache : (";
                                            r2.<init>(r3);
                                            r1 = r1.getMessage();
                                            r2.append(r1);
                                            r1 = ")";
                                            r2.append(r1);
                                            return;
                                            */
                                            throw new UnsupportedOperationException("Method not decompiled: com.inmobi.sdk.InMobiSdk$AnonymousClass1.run():void");
                                        }
                                    });
                                    if (context instanceof Activity) {
                                        com.inmobi.commons.core.utilities.a a = com.inmobi.commons.core.utilities.a.a();
                                        if (a != null) {
                                            a.a(new com.inmobi.commons.core.utilities.a.b() {
                                                public final void a(boolean z) {
                                                    a.b(z);
                                                    if (z) {
                                                        try {
                                                            InMobiSdk.initComponents();
                                                            return;
                                                        } catch (Exception e) {
                                                            InMobiSdk.TAG;
                                                            new StringBuilder("Encountered unexpected error in the onFocusChanged handler: ").append(e.getMessage());
                                                            Logger.a(InternalLogLevel.DEBUG, InMobiSdk.TAG, "SDK encountered an unexpected error; some components may not work as advertised");
                                                            return;
                                                        }
                                                    }
                                                    InMobiSdk.deInitComponents();
                                                }
                                            });
                                        }
                                    }
                                    try {
                                        b.a();
                                        b.a("root", "InitRequested", null);
                                    } catch (Exception e22) {
                                        stringBuilder = new StringBuilder("Error in submitting telemetry event : (");
                                        stringBuilder.append(e22.getMessage());
                                        stringBuilder.append(")");
                                    }
                                    internalLogLevel = InternalLogLevel.DEBUG;
                                    str = TAG;
                                    stringBuilder2 = new StringBuilder("InMobi SDK initialized with account id: ");
                                    stringBuilder2.append(trim);
                                    Logger.a(internalLogLevel, str, stringBuilder2.toString());
                                    try {
                                        hashMap = new HashMap();
                                        hashMap.put("initTime", Long.valueOf(SystemClock.elapsedRealtime() - elapsedRealtime));
                                        b.a();
                                        b.a("root", "SdkInitialized", hashMap);
                                    } catch (Exception e222) {
                                        stringBuilder = new StringBuilder("Error in submitting telemetry event : (");
                                        stringBuilder.append(e222.getMessage());
                                        stringBuilder.append(")");
                                    }
                                    printGrantedPermissions();
                                } catch (Exception e3) {
                                    e222 = e3;
                                }
                            }
                        }
                        Logger.a(InternalLogLevel.ERROR, TAG, "Please grant the mandatory permissions : INTERNET and ACCESS_NETWORK_STATE, SDK could not be initialized.");
                        return;
                    }
                }
                Logger.a(InternalLogLevel.ERROR, TAG, "Account id cannot be null or empty. Please provide a valid account id.");
            }
        } catch (Exception e4) {
            e222 = e4;
            trim = str;
            a.c();
            Logger.a(InternalLogLevel.ERROR, TAG, "SDK could not be initialized; an unexpected error was encountered");
            new StringBuilder("Encountered unexpected error while initializing the SDK: ").append(e222.getMessage());
            internalLogLevel = InternalLogLevel.DEBUG;
            str = TAG;
            stringBuilder2 = new StringBuilder("InMobi SDK initialized with account id: ");
            stringBuilder2.append(trim);
            Logger.a(internalLogLevel, str, stringBuilder2.toString());
            hashMap = new HashMap();
            hashMap.put("initTime", Long.valueOf(SystemClock.elapsedRealtime() - elapsedRealtime));
            b.a();
            b.a("root", "SdkInitialized", hashMap);
            printGrantedPermissions();
        }
    }

    public static void updateGDPRConsent(JSONObject jSONObject) {
        e.a(jSONObject);
    }

    public static void setApplicationMuted(boolean z) {
        a.a(z);
    }

    private static void sendDbDeletionTelemetryEvent(String str) {
        if (a.a()) {
            try {
                HashMap hashMap = new HashMap();
                hashMap.put("filename", str);
                StringBuilder stringBuilder = new StringBuilder("DB Deleted : ");
                stringBuilder.append(str);
                hashMap.put("description", stringBuilder.toString());
                b.a();
                b.a("ads", "PersistentDataCleanFail", hashMap);
            } catch (Exception e) {
                StringBuilder stringBuilder2 = new StringBuilder("Error in submitting telemetry event : (");
                stringBuilder2.append(e.getMessage());
                stringBuilder2.append(")");
            }
        }
    }

    private static boolean hasSdkVersionChanged(Context context) {
        return com.inmobi.commons.a.b.a(context) == null || !com.inmobi.commons.a.b.a(context).equals("7.2.1");
    }

    private static void printGrantedPermissions() {
        COMPONENT_SERVICE.execute(new Runnable() {
            public final void run() {
                r1 = new String[8];
                int i = 0;
                r1[0] = "android.permission.ACCESS_COARSE_LOCATION";
                r1[1] = "android.permission.ACCESS_FINE_LOCATION";
                r1[2] = "android.permission.ACCESS_WIFI_STATE";
                r1[3] = "android.permission.CHANGE_WIFI_STATE";
                r1[4] = "android.permission.VIBRATE";
                r1[5] = "android.permission.READ_CALENDAR";
                r1[6] = "android.permission.WRITE_CALENDAR";
                r1[7] = "com.google.android.gms.permission.ACTIVITY_RECOGNITION";
                StringBuilder stringBuilder = new StringBuilder("Permissions granted to SDK are :\nandroid.permission.INTERNET\nandroid.permission.ACCESS_NETWORK_STATE");
                while (i < 8) {
                    String str = r1[i];
                    if (com.inmobi.commons.core.utilities.e.a(a.b(), "ads", str)) {
                        stringBuilder.append("\n");
                        stringBuilder.append(str);
                    }
                    i++;
                }
                Logger.a(InternalLogLevel.DEBUG, InMobiSdk.TAG, stringBuilder.toString());
            }
        });
    }

    private static void initComponents() {
        try {
            COMPONENT_SERVICE.execute(new Runnable() {
                public final void run() {
                    try {
                        com.inmobi.commons.core.utilities.uid.c a = com.inmobi.commons.core.utilities.uid.c.a();
                        try {
                            com.inmobi.commons.core.utilities.uid.c.c();
                            a.b();
                        } catch (Exception e) {
                            new StringBuilder("SDK encountered an unexpected error while initializing the UID helper component; ").append(e.getMessage());
                        }
                        com.inmobi.commons.core.utilities.uid.c.a().b();
                        com.inmobi.commons.core.configs.b.a().b();
                        com.inmobi.rendering.a.c.a().b();
                        com.inmobi.commons.core.configs.b.c a2 = com.inmobi.commons.core.a.a.a();
                        com.inmobi.commons.core.a.a.b.set(false);
                        com.inmobi.commons.core.configs.b.a().a(a2.c, a2);
                        a2.d = a2.c.a;
                        a2.a.execute(new Runnable() {
                            public final void run() {
                                a.this.a.execute(new Runnable() {
                                    public final void run() {
                                        if (a.this.h.a(CBConstant.DEFAULT_VALUE) > 0) {
                                            a.a(a.this);
                                        }
                                    }
                                });
                            }
                        });
                        b.a().b();
                        a2 = com.inmobi.b.a.a();
                        com.inmobi.b.a.b.set(false);
                        e.c();
                        com.inmobi.commons.core.configs.b.a().a(a2.c, a2);
                        a2.d = a2.c.b;
                        a2.a.execute(new Runnable() {
                            public final void run() {
                                a.a(a.this);
                            }
                        });
                        com.inmobi.ads.b.a();
                        o.a().b();
                        com.inmobi.ads.c.b.d().a();
                        com.inmobi.ads.c.a.a(AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE).a();
                        AssetStore.a().b();
                    } catch (Exception e2) {
                        InMobiSdk.TAG;
                        new StringBuilder("Encountered unexpected error in starting SDK components: ").append(e2.getMessage());
                        Logger.a(InternalLogLevel.DEBUG, InMobiSdk.TAG, "SDK encountered unexpected error while starting internal components");
                    }
                }
            });
        } catch (Exception e) {
            new StringBuilder("Encountered unexpected error in starting SDK components: ").append(e.getMessage());
            Logger.a(InternalLogLevel.DEBUG, TAG, "SDK encountered unexpected error while starting internal components");
        }
    }

    private static void deInitComponents() {
        try {
            COMPONENT_SERVICE.execute(new Runnable() {
                public final void run() {
                    try {
                        com.inmobi.commons.core.configs.b.a().c();
                        b a = b.a();
                        b.b.set(true);
                        a.a.execute(new Runnable() {
                            public final void run() {
                                if (b.this.j != null) {
                                    b.this.j.a();
                                    b.this.j = null;
                                }
                            }
                        });
                        com.inmobi.b.a a2 = com.inmobi.b.a.a();
                        com.inmobi.b.a.b.set(true);
                        a2.a.execute(new Runnable() {
                            public final void run() {
                                if (a.this.i != null) {
                                    a.this.i.a();
                                    a.this.i = null;
                                }
                            }
                        });
                        o.a().c();
                        com.inmobi.ads.c.b.d().b();
                        com.inmobi.ads.c.a.a(AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE).b();
                        AssetStore a3 = AssetStore.a();
                        a3.d.set(true);
                        a3.c();
                    } catch (Exception e) {
                        InMobiSdk.TAG;
                        new StringBuilder("Encountered unexpected error in stopping SDK components; ").append(e.getMessage());
                        Logger.a(InternalLogLevel.ERROR, InMobiSdk.TAG, "SDK encountered unexpected error while stopping internal components");
                    }
                }
            });
        } catch (Exception e) {
            new StringBuilder("Encountered unexpected error in stopping SDK components; ").append(e.getMessage());
            Logger.a(InternalLogLevel.ERROR, TAG, "SDK encountered unexpected error while stopping internal components");
        }
    }

    private static void resetMediaCache(final Context context) {
        final File a = a.a(context);
        COMPONENT_SERVICE.execute(new Runnable() {
            public final void run() {
                a.a(a);
                a.b(context);
            }
        });
        if (!a.mkdir()) {
            a.isDirectory();
        }
    }

    public static void setLogLevel(LogLevel logLevel) {
        switch (logLevel) {
            case NONE:
                Logger.a(InternalLogLevel.NONE);
                return;
            case ERROR:
                Logger.a(InternalLogLevel.ERROR);
                return;
            case DEBUG:
                Logger.a(InternalLogLevel.DEBUG);
                break;
        }
    }

    public static void setAge(int i) {
        g.a(i);
    }

    public static void setAgeGroup(AgeGroup ageGroup) {
        g.a(ageGroup.toString().toLowerCase(Locale.ENGLISH));
    }

    public static void setAreaCode(String str) {
        g.b(str);
    }

    public static void setPostalCode(String str) {
        g.c(str);
    }

    public static void setLocationWithCityStateCountry(String str, String str2, String str3) {
        g.d(str);
        g.e(str2);
        g.f(str3);
    }

    public static void setYearOfBirth(int i) {
        g.b(i);
    }

    public static void setGender(Gender gender) {
        g.g(gender.toString().toLowerCase(Locale.ENGLISH));
    }

    public static void setEducation(Education education) {
        g.h(education.toString().toLowerCase(Locale.ENGLISH));
    }

    public static void setLanguage(String str) {
        g.i(str);
    }

    public static void setInterests(String str) {
        g.j(str);
    }

    public static void setLocation(Location location) {
        g.a(location);
    }
}
