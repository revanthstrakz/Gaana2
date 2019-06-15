package com.payu.custombrowser.a;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.payu.custombrowser.util.CBUtil;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.Timer;
import java.util.TimerTask;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class a {
    private static a b;
    private String a;
    private final Context c;
    private volatile boolean d = false;
    private Timer e;
    private CBUtil f;
    private volatile boolean g;
    private String h = "analytics_buffer_key";

    private a(final Context context, String str) {
        this.c = context;
        this.a = str;
        this.f = new CBUtil();
        final UncaughtExceptionHandler defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(new UncaughtExceptionHandler() {
            public void uncaughtException(Thread thread, Throwable th) {
                while (a.this.d) {
                }
                a.this.c();
                try {
                    int i = 0;
                    FileOutputStream openFileOutput = a.this.c.openFileOutput(a.this.a, 0);
                    if (a.this.f.getStringSharedPreference(a.this.c, a.this.h).length() > 0) {
                        JSONArray jSONArray = new JSONArray();
                        JSONArray jSONArray2 = new JSONArray(a.this.f.getStringSharedPreference(a.this.c, a.this.h).toString());
                        while (i < jSONArray2.length()) {
                            jSONArray.put(jSONArray.length(), jSONArray2.getJSONObject(i));
                            i++;
                        }
                        openFileOutput.write(jSONArray.toString().getBytes());
                        a.this.f.deleteSharedPrefKey(context, a.this.h);
                    }
                    openFileOutput.close();
                } catch (IOException e) {
                    ThrowableExtension.printStackTrace(e);
                } catch (Exception e2) {
                    ThrowableExtension.printStackTrace(e2);
                }
                a.this.d();
                defaultUncaughtExceptionHandler.uncaughtException(thread, th);
            }
        });
    }

    public static a a(Context context, String str) {
        if (b == null) {
            synchronized (a.class) {
                if (b == null) {
                    b = new a(context, str);
                }
            }
        }
        return b;
    }

    public void a(final String str) {
        if (e()) {
            b();
        }
        if (this.d) {
            new Thread(new Runnable() {
                public void run() {
                    JSONArray jSONArray;
                    while (a.this.g) {
                        try {
                        } catch (Exception e) {
                            ThrowableExtension.printStackTrace(e);
                            return;
                        }
                    }
                    String stringSharedPreference = a.this.f.getStringSharedPreference(a.this.c, a.this.h);
                    if (stringSharedPreference == null || stringSharedPreference.equalsIgnoreCase("")) {
                        jSONArray = new JSONArray();
                    } else {
                        jSONArray = new JSONArray(stringSharedPreference);
                    }
                    jSONArray.put(new JSONObject(str));
                    a.this.f.setStringSharedPreference(a.this.c, a.this.h, jSONArray.toString());
                }
            }).start();
        } else {
            new Thread(new Runnable() {
                public void run() {
                    while (a.this.d) {
                    }
                    a.this.c();
                    try {
                        JSONArray jSONArray;
                        FileOutputStream openFileOutput;
                        JSONObject jSONObject = new JSONObject(str);
                        String readFileInputStream = a.this.f.readFileInputStream(a.this.c, a.this.a, 0);
                        if (readFileInputStream != null) {
                            if (!readFileInputStream.equalsIgnoreCase("")) {
                                jSONArray = new JSONArray(readFileInputStream);
                                openFileOutput = a.this.c.openFileOutput(a.this.a, 0);
                                jSONArray.put(jSONArray.length(), jSONObject);
                                openFileOutput.write(jSONArray.toString().getBytes());
                                openFileOutput.close();
                                a.this.d();
                            }
                        }
                        jSONArray = new JSONArray();
                        openFileOutput = a.this.c.openFileOutput(a.this.a, 0);
                        jSONArray.put(jSONArray.length(), jSONObject);
                        openFileOutput.write(jSONArray.toString().getBytes());
                        openFileOutput.close();
                    } catch (IOException e) {
                        ThrowableExtension.printStackTrace(e);
                    } catch (JSONException e2) {
                        ThrowableExtension.printStackTrace(e2);
                    } catch (Exception e3) {
                        ThrowableExtension.printStackTrace(e3);
                    } catch (Throwable th) {
                        a.this.d();
                    }
                    a.this.d();
                }
            }).start();
        }
    }

    private void b() {
        if (this.e != null) {
            this.e.cancel();
        }
        this.e = new Timer();
        this.e.schedule(new TimerTask() {
            /* JADX WARNING: Removed duplicated region for block: B:18:0x0068 A:{Catch:{ Exception -> 0x0044 }} */
            /* JADX WARNING: Removed duplicated region for block: B:21:0x0094 A:{Catch:{ Exception -> 0x0044 }} */
            /* JADX WARNING: Removed duplicated region for block: B:44:0x0129 A:{Catch:{ Exception -> 0x0105 }} */
            /* JADX WARNING: Removed duplicated region for block: B:47:0x0155 A:{Catch:{ Exception -> 0x0105 }} */
            public void run() {
                /*
                r8 = this;
            L_0x0000:
                r0 = com.payu.custombrowser.a.a.this;
                r0 = r0.d;
                if (r0 == 0) goto L_0x0009;
            L_0x0008:
                goto L_0x0000;
            L_0x0009:
                r0 = com.payu.custombrowser.a.a.this;
                r0.c();
                r0 = com.payu.custombrowser.a.a.this;
                r0 = r0.e();
                r1 = 1;
                if (r0 == 0) goto L_0x01b3;
            L_0x0017:
                r0 = "";
                r2 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
                r3 = 30000; // 0x7530 float:4.2039E-41 double:1.4822E-319;
                r4 = com.payu.custombrowser.a.a.this;	 Catch:{ all -> 0x00f4 }
                r4 = r4.f;	 Catch:{ all -> 0x00f4 }
                r5 = com.payu.custombrowser.a.a.this;	 Catch:{ all -> 0x00f4 }
                r5 = r5.c;	 Catch:{ all -> 0x00f4 }
                r6 = com.payu.custombrowser.a.a.this;	 Catch:{ all -> 0x00f4 }
                r6 = r6.a;	 Catch:{ all -> 0x00f4 }
                r7 = 0;
                r4 = r4.readFileInputStream(r5, r6, r7);	 Catch:{ all -> 0x00f4 }
                if (r4 == 0) goto L_0x0047;
            L_0x0036:
                r0 = "";
                r0 = r4.equalsIgnoreCase(r0);	 Catch:{ Exception -> 0x0044 }
                if (r0 != 0) goto L_0x0047;
            L_0x003e:
                r0 = new org.json.JSONArray;	 Catch:{ Exception -> 0x0044 }
                r0.<init>(r4);	 Catch:{ Exception -> 0x0044 }
                goto L_0x004c;
            L_0x0044:
                r0 = move-exception;
                goto L_0x00ef;
            L_0x0047:
                r0 = new org.json.JSONArray;	 Catch:{ Exception -> 0x0044 }
                r0.<init>();	 Catch:{ Exception -> 0x0044 }
            L_0x004c:
                r4 = com.payu.custombrowser.a.a.this;	 Catch:{ Exception -> 0x0044 }
                r4 = r4.f;	 Catch:{ Exception -> 0x0044 }
                r5 = com.payu.custombrowser.a.a.this;	 Catch:{ Exception -> 0x0044 }
                r5 = r5.c;	 Catch:{ Exception -> 0x0044 }
                r6 = com.payu.custombrowser.a.a.this;	 Catch:{ Exception -> 0x0044 }
                r6 = r6.h;	 Catch:{ Exception -> 0x0044 }
                r4 = r4.getStringSharedPreference(r5, r6);	 Catch:{ Exception -> 0x0044 }
                r4 = r4.length();	 Catch:{ Exception -> 0x0044 }
                if (r4 <= r1) goto L_0x008e;
            L_0x0068:
                r4 = com.payu.custombrowser.a.a.this;	 Catch:{ Exception -> 0x0044 }
                r4.g = r1;	 Catch:{ Exception -> 0x0044 }
                r4 = new org.json.JSONArray;	 Catch:{ Exception -> 0x0044 }
                r5 = com.payu.custombrowser.a.a.this;	 Catch:{ Exception -> 0x0044 }
                r5 = r5.f;	 Catch:{ Exception -> 0x0044 }
                r6 = com.payu.custombrowser.a.a.this;	 Catch:{ Exception -> 0x0044 }
                r6 = r6.c;	 Catch:{ Exception -> 0x0044 }
                r7 = com.payu.custombrowser.a.a.this;	 Catch:{ Exception -> 0x0044 }
                r7 = r7.h;	 Catch:{ Exception -> 0x0044 }
                r5 = r5.getStringSharedPreference(r6, r7);	 Catch:{ Exception -> 0x0044 }
                r4.<init>(r5);	 Catch:{ Exception -> 0x0044 }
                r5 = com.payu.custombrowser.a.a.this;	 Catch:{ Exception -> 0x0044 }
                r0 = r5.a(r0, r4);	 Catch:{ Exception -> 0x0044 }
            L_0x008e:
                r4 = r0.length();	 Catch:{ Exception -> 0x0044 }
                if (r4 <= 0) goto L_0x01b3;
            L_0x0094:
                r4 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0044 }
                r4.<init>();	 Catch:{ Exception -> 0x0044 }
                r5 = "command=EventAnalytics&data=";
                r4.append(r5);	 Catch:{ Exception -> 0x0044 }
                r0 = r0.toString();	 Catch:{ Exception -> 0x0044 }
                r4.append(r0);	 Catch:{ Exception -> 0x0044 }
                r0 = r4.toString();	 Catch:{ Exception -> 0x0044 }
                r4 = com.payu.custombrowser.a.a.this;	 Catch:{ Exception -> 0x0044 }
                r4 = r4.f;	 Catch:{ Exception -> 0x0044 }
                r5 = "https://info.payu.in/merchant/MobileAnalytics";
                r0 = r4.getHttpsConn(r5, r0, r3);	 Catch:{ Exception -> 0x0044 }
                if (r0 == 0) goto L_0x01b3;
            L_0x00b7:
                r3 = r0.getResponseCode();	 Catch:{ Exception -> 0x0044 }
                if (r3 != r2) goto L_0x01b3;
            L_0x00bd:
                r2 = r0.getInputStream();	 Catch:{ Exception -> 0x0044 }
                if (r2 == 0) goto L_0x01b3;
            L_0x00c3:
                r0 = r0.getInputStream();	 Catch:{ Exception -> 0x0044 }
                r0 = com.payu.custombrowser.util.CBUtil.getStringBufferFromInputStream(r0);	 Catch:{ Exception -> 0x0044 }
                if (r0 == 0) goto L_0x01b3;
            L_0x00cd:
                r2 = new org.json.JSONObject;	 Catch:{ Exception -> 0x0044 }
                r0 = r0.toString();	 Catch:{ Exception -> 0x0044 }
                r2.<init>(r0);	 Catch:{ Exception -> 0x0044 }
                r0 = "status";
                r0 = r2.has(r0);	 Catch:{ Exception -> 0x0044 }
                if (r0 == 0) goto L_0x01b3;
            L_0x00de:
                r0 = com.payu.custombrowser.a.a.this;	 Catch:{ Exception -> 0x0044 }
                r0 = r0.c;	 Catch:{ Exception -> 0x0044 }
                r2 = com.payu.custombrowser.a.a.this;	 Catch:{ Exception -> 0x0044 }
                r2 = r2.a;	 Catch:{ Exception -> 0x0044 }
                r0.deleteFile(r2);	 Catch:{ Exception -> 0x0044 }
                goto L_0x01b3;
            L_0x00ef:
                com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r0);
                goto L_0x01b3;
            L_0x00f4:
                r4 = move-exception;
                if (r0 == 0) goto L_0x0108;
            L_0x00f7:
                r5 = "";
                r5 = r0.equalsIgnoreCase(r5);	 Catch:{ Exception -> 0x0105 }
                if (r5 != 0) goto L_0x0108;
            L_0x00ff:
                r5 = new org.json.JSONArray;	 Catch:{ Exception -> 0x0105 }
                r5.<init>(r0);	 Catch:{ Exception -> 0x0105 }
                goto L_0x010d;
            L_0x0105:
                r0 = move-exception;
                goto L_0x01af;
            L_0x0108:
                r5 = new org.json.JSONArray;	 Catch:{ Exception -> 0x0105 }
                r5.<init>();	 Catch:{ Exception -> 0x0105 }
            L_0x010d:
                r0 = com.payu.custombrowser.a.a.this;	 Catch:{ Exception -> 0x0105 }
                r0 = r0.f;	 Catch:{ Exception -> 0x0105 }
                r6 = com.payu.custombrowser.a.a.this;	 Catch:{ Exception -> 0x0105 }
                r6 = r6.c;	 Catch:{ Exception -> 0x0105 }
                r7 = com.payu.custombrowser.a.a.this;	 Catch:{ Exception -> 0x0105 }
                r7 = r7.h;	 Catch:{ Exception -> 0x0105 }
                r0 = r0.getStringSharedPreference(r6, r7);	 Catch:{ Exception -> 0x0105 }
                r0 = r0.length();	 Catch:{ Exception -> 0x0105 }
                if (r0 <= r1) goto L_0x014f;
            L_0x0129:
                r0 = com.payu.custombrowser.a.a.this;	 Catch:{ Exception -> 0x0105 }
                r0.g = r1;	 Catch:{ Exception -> 0x0105 }
                r0 = new org.json.JSONArray;	 Catch:{ Exception -> 0x0105 }
                r1 = com.payu.custombrowser.a.a.this;	 Catch:{ Exception -> 0x0105 }
                r1 = r1.f;	 Catch:{ Exception -> 0x0105 }
                r6 = com.payu.custombrowser.a.a.this;	 Catch:{ Exception -> 0x0105 }
                r6 = r6.c;	 Catch:{ Exception -> 0x0105 }
                r7 = com.payu.custombrowser.a.a.this;	 Catch:{ Exception -> 0x0105 }
                r7 = r7.h;	 Catch:{ Exception -> 0x0105 }
                r1 = r1.getStringSharedPreference(r6, r7);	 Catch:{ Exception -> 0x0105 }
                r0.<init>(r1);	 Catch:{ Exception -> 0x0105 }
                r1 = com.payu.custombrowser.a.a.this;	 Catch:{ Exception -> 0x0105 }
                r5 = r1.a(r5, r0);	 Catch:{ Exception -> 0x0105 }
            L_0x014f:
                r0 = r5.length();	 Catch:{ Exception -> 0x0105 }
                if (r0 <= 0) goto L_0x01b2;
            L_0x0155:
                r0 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0105 }
                r0.<init>();	 Catch:{ Exception -> 0x0105 }
                r1 = "command=EventAnalytics&data=";
                r0.append(r1);	 Catch:{ Exception -> 0x0105 }
                r1 = r5.toString();	 Catch:{ Exception -> 0x0105 }
                r0.append(r1);	 Catch:{ Exception -> 0x0105 }
                r0 = r0.toString();	 Catch:{ Exception -> 0x0105 }
                r1 = com.payu.custombrowser.a.a.this;	 Catch:{ Exception -> 0x0105 }
                r1 = r1.f;	 Catch:{ Exception -> 0x0105 }
                r5 = "https://info.payu.in/merchant/MobileAnalytics";
                r0 = r1.getHttpsConn(r5, r0, r3);	 Catch:{ Exception -> 0x0105 }
                if (r0 == 0) goto L_0x01b2;
            L_0x0178:
                r1 = r0.getResponseCode();	 Catch:{ Exception -> 0x0105 }
                if (r1 != r2) goto L_0x01b2;
            L_0x017e:
                r1 = r0.getInputStream();	 Catch:{ Exception -> 0x0105 }
                if (r1 == 0) goto L_0x01b2;
            L_0x0184:
                r0 = r0.getInputStream();	 Catch:{ Exception -> 0x0105 }
                r0 = com.payu.custombrowser.util.CBUtil.getStringBufferFromInputStream(r0);	 Catch:{ Exception -> 0x0105 }
                if (r0 == 0) goto L_0x01b2;
            L_0x018e:
                r1 = new org.json.JSONObject;	 Catch:{ Exception -> 0x0105 }
                r0 = r0.toString();	 Catch:{ Exception -> 0x0105 }
                r1.<init>(r0);	 Catch:{ Exception -> 0x0105 }
                r0 = "status";
                r0 = r1.has(r0);	 Catch:{ Exception -> 0x0105 }
                if (r0 == 0) goto L_0x01b2;
            L_0x019f:
                r0 = com.payu.custombrowser.a.a.this;	 Catch:{ Exception -> 0x0105 }
                r0 = r0.c;	 Catch:{ Exception -> 0x0105 }
                r1 = com.payu.custombrowser.a.a.this;	 Catch:{ Exception -> 0x0105 }
                r1 = r1.a;	 Catch:{ Exception -> 0x0105 }
                r0.deleteFile(r1);	 Catch:{ Exception -> 0x0105 }
                goto L_0x01b2;
            L_0x01af:
                com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r0);
            L_0x01b2:
                throw r4;
            L_0x01b3:
                r0 = com.payu.custombrowser.a.a.this;
                r0.d();
                r0 = com.payu.custombrowser.a.a.this;
                r0 = r0.f;
                r2 = com.payu.custombrowser.a.a.this;
                r2 = r2.c;
                r3 = com.payu.custombrowser.a.a.this;
                r3 = r3.h;
                r0 = r0.getStringSharedPreference(r2, r3);
                r0 = r0.length();
                if (r0 <= r1) goto L_0x01d9;
            L_0x01d4:
                r0 = com.payu.custombrowser.a.a.this;
                r0.b();
            L_0x01d9:
                return;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.payu.custombrowser.a.a$AnonymousClass4.run():void");
            }
        }, DefaultRenderersFactory.DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS);
    }

    private synchronized void c() {
        while (this.d) {
        }
        this.d = true;
    }

    private void d() {
        this.d = false;
    }

    private boolean e() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.c.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }

    public Timer a() {
        return this.e;
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x0055 A:{SYNTHETIC, Splitter:B:25:0x0055} */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0062 A:{SYNTHETIC, Splitter:B:32:0x0062} */
    private org.json.JSONArray a(org.json.JSONArray r7, org.json.JSONArray r8) {
        /*
        r6 = this;
        r0 = 0;
        r1 = 0;
        r2 = new org.json.JSONArray;	 Catch:{ Exception -> 0x004f }
        r3 = r7.toString();	 Catch:{ Exception -> 0x004f }
        r2.<init>(r3);	 Catch:{ Exception -> 0x004f }
        r3 = r0;
    L_0x000c:
        r4 = r8.length();	 Catch:{ Exception -> 0x004f }
        if (r3 >= r4) goto L_0x001c;
    L_0x0012:
        r4 = r8.getJSONObject(r3);	 Catch:{ Exception -> 0x004f }
        r2.put(r4);	 Catch:{ Exception -> 0x004f }
        r3 = r3 + 1;
        goto L_0x000c;
    L_0x001c:
        r8 = r6.c;	 Catch:{ Exception -> 0x004f }
        r3 = r6.a;	 Catch:{ Exception -> 0x004f }
        r8 = r8.openFileOutput(r3, r0);	 Catch:{ Exception -> 0x004f }
        r1 = r2.toString();	 Catch:{ Exception -> 0x0048, all -> 0x0045 }
        r1 = r1.getBytes();	 Catch:{ Exception -> 0x0048, all -> 0x0045 }
        r8.write(r1);	 Catch:{ Exception -> 0x0048, all -> 0x0045 }
        r1 = r6.f;	 Catch:{ Exception -> 0x0048, all -> 0x0045 }
        r3 = r6.c;	 Catch:{ Exception -> 0x0048, all -> 0x0045 }
        r4 = r6.h;	 Catch:{ Exception -> 0x0048, all -> 0x0045 }
        r1.deleteSharedPrefKey(r3, r4);	 Catch:{ Exception -> 0x0048, all -> 0x0045 }
        if (r8 == 0) goto L_0x0042;
    L_0x003a:
        r8.close();	 Catch:{ IOException -> 0x003e }
        goto L_0x0042;
    L_0x003e:
        r7 = move-exception;
        com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r7);
    L_0x0042:
        r6.g = r0;
        return r2;
    L_0x0045:
        r7 = move-exception;
        r1 = r8;
        goto L_0x0060;
    L_0x0048:
        r1 = move-exception;
        r5 = r1;
        r1 = r8;
        r8 = r5;
        goto L_0x0050;
    L_0x004d:
        r7 = move-exception;
        goto L_0x0060;
    L_0x004f:
        r8 = move-exception;
    L_0x0050:
        com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r8);	 Catch:{ all -> 0x004d }
        if (r1 == 0) goto L_0x005d;
    L_0x0055:
        r1.close();	 Catch:{ IOException -> 0x0059 }
        goto L_0x005d;
    L_0x0059:
        r8 = move-exception;
        com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r8);
    L_0x005d:
        r6.g = r0;
        return r7;
    L_0x0060:
        if (r1 == 0) goto L_0x006a;
    L_0x0062:
        r1.close();	 Catch:{ IOException -> 0x0066 }
        goto L_0x006a;
    L_0x0066:
        r8 = move-exception;
        com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r8);
    L_0x006a:
        r6.g = r0;
        throw r7;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.payu.custombrowser.a.a.a(org.json.JSONArray, org.json.JSONArray):org.json.JSONArray");
    }
}
