package com.simpl.android.fingerprint.commons.exception;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.os.AsyncTask;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.Thread.UncaughtExceptionHandler;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import org.apache.http.entity.mime.MIME;

final class a {
    private static String a = "production";
    private static String b = "unknown";
    private static String c = "unknown";
    private static Map<String, String> d = null;
    private static String e = null;
    private static boolean f = false;
    private static String g = null;
    private static boolean h = false;

    static class a implements UncaughtExceptionHandler {
        private UncaughtExceptionHandler a;

        a(UncaughtExceptionHandler uncaughtExceptionHandler) {
            this.a = uncaughtExceptionHandler;
        }

        public final void uncaughtException(Thread thread, Throwable th) {
            if (a.b(th)) {
                a.a(th);
            }
            this.a.uncaughtException(thread, th);
        }
    }

    static void a(Context context, String str, String str2) {
        e = str;
        if (context == null) {
            throw new IllegalArgumentException("context cannot be null.");
        }
        a = str2;
        f = true;
        UncaughtExceptionHandler defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        if (!(defaultUncaughtExceptionHandler instanceof a) && str2.equals("production")) {
            Thread.setDefaultUncaughtExceptionHandler(new a(defaultUncaughtExceptionHandler));
        }
        try {
            b = context.getPackageName();
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(b, 0);
            if (packageInfo.versionName != null) {
                c = packageInfo.versionName;
            }
        } catch (Exception unused) {
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(context.getFilesDir().getAbsolutePath());
        stringBuilder.append("/unsent_airbrake_exceptions/");
        g = stringBuilder.toString();
        File file = new File(g);
        file.mkdirs();
        h = file.exists();
        new AsyncTask<Void, Void, Void>() {
            /* Access modifiers changed, original: protected|final|synthetic */
            public final /* synthetic */ Object doInBackground(Object[] objArr) {
                a.b(null, null);
                return null;
            }
        }.execute(new Void[0]);
    }

    private static void a(File file) {
        HttpURLConnection httpURLConnection;
        try {
            httpURLConnection = (HttpURLConnection) new URL("http://airbrakeapp.com/notifier_api/v2/notices").openConnection();
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setRequestProperty(MIME.CONTENT_TYPE, "text/xml; charset=utf-8");
            FileInputStream fileInputStream = new FileInputStream(file);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            byte[] bArr = new byte[4096];
            while (true) {
                int read = fileInputStream.read(bArr);
                if (read >= 0) {
                    outputStream.write(bArr, 0, read);
                } else {
                    outputStream.flush();
                    outputStream.close();
                    fileInputStream.close();
                    int responseCode = httpURLConnection.getResponseCode();
                    StringBuilder stringBuilder = new StringBuilder("Sent exception file ");
                    stringBuilder.append(file.getName());
                    stringBuilder.append(" to airbrake. Got response code ");
                    stringBuilder.append(String.valueOf(responseCode));
                    file.delete();
                    httpURLConnection.disconnect();
                    return;
                }
            }
        } catch (IOException unused) {
            httpURLConnection.disconnect();
        } catch (Exception unused2) {
        } catch (Throwable th) {
            httpURLConnection.disconnect();
        }
    }

    static void a(String str) {
        a = str;
    }

    public static void a(Throwable th) {
        a(th, null);
    }

    public static void a(final Throwable th, final Map<String, String> map) {
        if (th != null && h) {
            new AsyncTask<Void, Void, Void>() {
                /* Access modifiers changed, original: protected|final|synthetic */
                public final /* synthetic */ Object doInBackground(Object[] objArr) {
                    a.c(th, map);
                    a.b(th.getMessage(), map);
                    return null;
                }
            }.execute(new Void[0]);
        }
    }

    static void a(Map<String, String> map) {
        d = map;
    }

    private static synchronized void b(String str, Map<String, String> map) {
        synchronized (a.class) {
            try {
                File file = new File(g);
                if (file.exists() && file.isDirectory()) {
                    for (File file2 : file.listFiles()) {
                        if (file2.exists() && file2.isFile()) {
                            a(file2);
                        }
                    }
                }
            } catch (Throwable th) {
                if (map != null) {
                    map.put("message_caused_error", str);
                }
                c(th, map);
            }
        }
    }

    static /* synthetic */ boolean b(Throwable th) {
        if (th != null) {
            if (th.getMessage() != null && th.getMessage().contains("simpl")) {
                return true;
            }
            for (StackTraceElement stackTraceElement : th.getStackTrace()) {
                if (stackTraceElement != null && stackTraceElement.toString().contains("simpl")) {
                    return true;
                }
            }
        }
        return false;
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:14:0x0160 */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0274 A:{LOOP_END, LOOP:2: B:30:0x026e->B:32:0x0274, Catch:{ Exception -> 0x0349 }} */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x02b5 A:{LOOP_END, LOOP:3: B:37:0x02af->B:39:0x02b5, Catch:{ Exception -> 0x0349 }} */
    /* JADX WARNING: Missing exception handler attribute for start block: B:24:0x01a2 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x0199 */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Can't wrap try/catch for region: R(7:7|8|9|(1:11)(1:12)|13|14|15) */
    /* JADX WARNING: Can't wrap try/catch for region: R(7:18|19|20|21|22|23|44) */
    private static void c(java.lang.Throwable r11, java.util.Map<java.lang.String, java.lang.String> r12) {
        /*
        r0 = new java.util.Random;	 Catch:{ Exception -> 0x0349 }
        r0.<init>();	 Catch:{ Exception -> 0x0349 }
        r1 = 99999; // 0x1869f float:1.40128E-40 double:4.9406E-319;
        r0 = r0.nextInt(r1);	 Catch:{ Exception -> 0x0349 }
        r1 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0349 }
        r1.<init>();	 Catch:{ Exception -> 0x0349 }
        r2 = g;	 Catch:{ Exception -> 0x0349 }
        r1.append(r2);	 Catch:{ Exception -> 0x0349 }
        r2 = c;	 Catch:{ Exception -> 0x0349 }
        r1.append(r2);	 Catch:{ Exception -> 0x0349 }
        r2 = "-";
        r1.append(r2);	 Catch:{ Exception -> 0x0349 }
        r0 = java.lang.String.valueOf(r0);	 Catch:{ Exception -> 0x0349 }
        r1.append(r0);	 Catch:{ Exception -> 0x0349 }
        r0 = ".xml";
        r1.append(r0);	 Catch:{ Exception -> 0x0349 }
        r0 = r1.toString();	 Catch:{ Exception -> 0x0349 }
        r1 = new java.io.BufferedWriter;	 Catch:{ Exception -> 0x0349 }
        r2 = new java.io.FileWriter;	 Catch:{ Exception -> 0x0349 }
        r2.<init>(r0);	 Catch:{ Exception -> 0x0349 }
        r1.<init>(r2);	 Catch:{ Exception -> 0x0349 }
        r0 = android.util.Xml.newSerializer();	 Catch:{ Exception -> 0x0349 }
        r0.setOutput(r1);	 Catch:{ Exception -> 0x0349 }
        r2 = "UTF-8";
        r3 = java.lang.Boolean.TRUE;	 Catch:{ Exception -> 0x0349 }
        r0.startDocument(r2, r3);	 Catch:{ Exception -> 0x0349 }
        r2 = "";
        r3 = "notice";
        r0.startTag(r2, r3);	 Catch:{ Exception -> 0x0349 }
        r2 = "";
        r3 = "version";
        r4 = "2.0";
        r0.attribute(r2, r3, r4);	 Catch:{ Exception -> 0x0349 }
        r2 = "";
        r3 = "api-key";
        r0.startTag(r2, r3);	 Catch:{ Exception -> 0x0349 }
        r2 = e;	 Catch:{ Exception -> 0x0349 }
        r0.text(r2);	 Catch:{ Exception -> 0x0349 }
        r2 = "";
        r3 = "api-key";
        r0.endTag(r2, r3);	 Catch:{ Exception -> 0x0349 }
        r2 = "";
        r3 = "notifier";
        r0.startTag(r2, r3);	 Catch:{ Exception -> 0x0349 }
        r2 = "";
        r3 = "name";
        r0.startTag(r2, r3);	 Catch:{ Exception -> 0x0349 }
        r2 = "Android Airbrake Notifier";
        r0.text(r2);	 Catch:{ Exception -> 0x0349 }
        r2 = "";
        r3 = "name";
        r0.endTag(r2, r3);	 Catch:{ Exception -> 0x0349 }
        r2 = "";
        r3 = "version";
        r0.startTag(r2, r3);	 Catch:{ Exception -> 0x0349 }
        r2 = "1.3.0";
        r0.text(r2);	 Catch:{ Exception -> 0x0349 }
        r2 = "";
        r3 = "version";
        r0.endTag(r2, r3);	 Catch:{ Exception -> 0x0349 }
        r2 = "";
        r3 = "url";
        r0.startTag(r2, r3);	 Catch:{ Exception -> 0x0349 }
        r2 = "http://loopj.com";
        r0.text(r2);	 Catch:{ Exception -> 0x0349 }
        r2 = "";
        r3 = "url";
        r0.endTag(r2, r3);	 Catch:{ Exception -> 0x0349 }
        r2 = "";
        r3 = "notifier";
        r0.endTag(r2, r3);	 Catch:{ Exception -> 0x0349 }
        r2 = "";
        r3 = "error";
        r0.startTag(r2, r3);	 Catch:{ Exception -> 0x0349 }
        r2 = "";
        r3 = "class";
        r0.startTag(r2, r3);	 Catch:{ Exception -> 0x0349 }
        r2 = r11.getClass();	 Catch:{ Exception -> 0x0349 }
        r2 = r2.getName();	 Catch:{ Exception -> 0x0349 }
        r0.text(r2);	 Catch:{ Exception -> 0x0349 }
        r2 = "";
        r3 = "class";
        r0.endTag(r2, r3);	 Catch:{ Exception -> 0x0349 }
        r2 = "";
        r3 = "message";
        r0.startTag(r2, r3);	 Catch:{ Exception -> 0x0349 }
        r2 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0349 }
        r3 = "[";
        r2.<init>(r3);	 Catch:{ Exception -> 0x0349 }
        r3 = c;	 Catch:{ Exception -> 0x0349 }
        r2.append(r3);	 Catch:{ Exception -> 0x0349 }
        r3 = "] ";
        r2.append(r3);	 Catch:{ Exception -> 0x0349 }
        r3 = r11.toString();	 Catch:{ Exception -> 0x0349 }
        r2.append(r3);	 Catch:{ Exception -> 0x0349 }
        r2 = r2.toString();	 Catch:{ Exception -> 0x0349 }
        r0.text(r2);	 Catch:{ Exception -> 0x0349 }
        r2 = "";
        r3 = "message";
        r0.endTag(r2, r3);	 Catch:{ Exception -> 0x0349 }
        r2 = "";
        r3 = "backtrace";
        r0.startTag(r2, r3);	 Catch:{ Exception -> 0x0349 }
        r2 = r11;
    L_0x0107:
        if (r2 == 0) goto L_0x01a2;
    L_0x0109:
        r3 = r2.getStackTrace();	 Catch:{ Throwable -> 0x01a2 }
        r4 = r3.length;	 Catch:{ Throwable -> 0x01a2 }
        r5 = 0;
    L_0x010f:
        if (r5 >= r4) goto L_0x016a;
    L_0x0111:
        r6 = r3[r5];	 Catch:{ Throwable -> 0x01a2 }
        r7 = "";
        r8 = "line";
        r0.startTag(r7, r8);	 Catch:{ Throwable -> 0x01a2 }
        r7 = "";
        r8 = "method";
        r9 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x0160 }
        r9.<init>();	 Catch:{ Throwable -> 0x0160 }
        r10 = r6.getClassName();	 Catch:{ Throwable -> 0x0160 }
        r9.append(r10);	 Catch:{ Throwable -> 0x0160 }
        r10 = ".";
        r9.append(r10);	 Catch:{ Throwable -> 0x0160 }
        r10 = r6.getMethodName();	 Catch:{ Throwable -> 0x0160 }
        r9.append(r10);	 Catch:{ Throwable -> 0x0160 }
        r9 = r9.toString();	 Catch:{ Throwable -> 0x0160 }
        r0.attribute(r7, r8, r9);	 Catch:{ Throwable -> 0x0160 }
        r7 = "";
        r8 = "file";
        r9 = r6.getFileName();	 Catch:{ Throwable -> 0x0160 }
        if (r9 != 0) goto L_0x014a;
    L_0x0147:
        r9 = "Unknown";
        goto L_0x014e;
    L_0x014a:
        r9 = r6.getFileName();	 Catch:{ Throwable -> 0x0160 }
    L_0x014e:
        r0.attribute(r7, r8, r9);	 Catch:{ Throwable -> 0x0160 }
        r7 = "";
        r8 = "number";
        r6 = r6.getLineNumber();	 Catch:{ Throwable -> 0x0160 }
        r6 = java.lang.String.valueOf(r6);	 Catch:{ Throwable -> 0x0160 }
        r0.attribute(r7, r8, r6);	 Catch:{ Throwable -> 0x0160 }
    L_0x0160:
        r6 = "";
        r7 = "line";
        r0.endTag(r6, r7);	 Catch:{ Throwable -> 0x01a2 }
        r5 = r5 + 1;
        goto L_0x010f;
    L_0x016a:
        r2 = r2.getCause();	 Catch:{ Throwable -> 0x01a2 }
        if (r2 == 0) goto L_0x0107;
    L_0x0170:
        r3 = "";
        r4 = "line";
        r0.startTag(r3, r4);	 Catch:{ Throwable -> 0x01a2 }
        r3 = "";
        r4 = "file";
        r5 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x0199 }
        r6 = "### CAUSED BY ###: ";
        r5.<init>(r6);	 Catch:{ Throwable -> 0x0199 }
        r6 = r2.toString();	 Catch:{ Throwable -> 0x0199 }
        r5.append(r6);	 Catch:{ Throwable -> 0x0199 }
        r5 = r5.toString();	 Catch:{ Throwable -> 0x0199 }
        r0.attribute(r3, r4, r5);	 Catch:{ Throwable -> 0x0199 }
        r3 = "";
        r4 = "number";
        r5 = "";
        r0.attribute(r3, r4, r5);	 Catch:{ Throwable -> 0x0199 }
    L_0x0199:
        r3 = "";
        r4 = "line";
        r0.endTag(r3, r4);	 Catch:{ Throwable -> 0x01a2 }
        goto L_0x0107;
    L_0x01a2:
        r2 = "";
        r3 = "backtrace";
        r0.endTag(r2, r3);	 Catch:{ Exception -> 0x0349 }
        r2 = "";
        r3 = "error";
        r0.endTag(r2, r3);	 Catch:{ Exception -> 0x0349 }
        r2 = "";
        r3 = "request";
        r0.startTag(r2, r3);	 Catch:{ Exception -> 0x0349 }
        r2 = "";
        r3 = "url";
        r0.startTag(r2, r3);	 Catch:{ Exception -> 0x0349 }
        r2 = "";
        r3 = "url";
        r0.endTag(r2, r3);	 Catch:{ Exception -> 0x0349 }
        r2 = "";
        r3 = "component";
        r0.startTag(r2, r3);	 Catch:{ Exception -> 0x0349 }
        r2 = "";
        r3 = "component";
        r0.endTag(r2, r3);	 Catch:{ Exception -> 0x0349 }
        r2 = "";
        r3 = "action";
        r0.startTag(r2, r3);	 Catch:{ Exception -> 0x0349 }
        r2 = "";
        r3 = "action";
        r0.endTag(r2, r3);	 Catch:{ Exception -> 0x0349 }
        r2 = "";
        r3 = "cgi-data";
        r0.startTag(r2, r3);	 Catch:{ Exception -> 0x0349 }
        r2 = "";
        r3 = "var";
        r0.startTag(r2, r3);	 Catch:{ Exception -> 0x0349 }
        r2 = "";
        r3 = "key";
        r4 = "Device";
        r0.attribute(r2, r3, r4);	 Catch:{ Exception -> 0x0349 }
        r2 = android.os.Build.MODEL;	 Catch:{ Exception -> 0x0349 }
        r0.text(r2);	 Catch:{ Exception -> 0x0349 }
        r2 = "";
        r3 = "var";
        r0.endTag(r2, r3);	 Catch:{ Exception -> 0x0349 }
        r2 = "";
        r3 = "var";
        r0.startTag(r2, r3);	 Catch:{ Exception -> 0x0349 }
        r2 = "";
        r3 = "key";
        r4 = "Android Version";
        r0.attribute(r2, r3, r4);	 Catch:{ Exception -> 0x0349 }
        r2 = android.os.Build.VERSION.RELEASE;	 Catch:{ Exception -> 0x0349 }
        r0.text(r2);	 Catch:{ Exception -> 0x0349 }
        r2 = "";
        r3 = "var";
        r0.endTag(r2, r3);	 Catch:{ Exception -> 0x0349 }
        r2 = "";
        r3 = "var";
        r0.startTag(r2, r3);	 Catch:{ Exception -> 0x0349 }
        r2 = "";
        r3 = "key";
        r4 = "App Version";
        r0.attribute(r2, r3, r4);	 Catch:{ Exception -> 0x0349 }
        r2 = c;	 Catch:{ Exception -> 0x0349 }
        r0.text(r2);	 Catch:{ Exception -> 0x0349 }
        r2 = "";
        r3 = "var";
        r0.endTag(r2, r3);	 Catch:{ Exception -> 0x0349 }
        r2 = "";
        r3 = "var";
        r0.startTag(r2, r3);	 Catch:{ Exception -> 0x0349 }
        r2 = "";
        r3 = "key";
        r4 = "Parent App";
        r0.attribute(r2, r3, r4);	 Catch:{ Exception -> 0x0349 }
        r2 = b;	 Catch:{ Exception -> 0x0349 }
        r0.text(r2);	 Catch:{ Exception -> 0x0349 }
        r2 = "";
        r3 = "var";
        r0.endTag(r2, r3);	 Catch:{ Exception -> 0x0349 }
        r2 = d;	 Catch:{ Exception -> 0x0349 }
        if (r2 == 0) goto L_0x029f;
    L_0x025c:
        r2 = d;	 Catch:{ Exception -> 0x0349 }
        r2 = r2.isEmpty();	 Catch:{ Exception -> 0x0349 }
        if (r2 != 0) goto L_0x029f;
    L_0x0264:
        r2 = d;	 Catch:{ Exception -> 0x0349 }
        r2 = r2.entrySet();	 Catch:{ Exception -> 0x0349 }
        r2 = r2.iterator();	 Catch:{ Exception -> 0x0349 }
    L_0x026e:
        r3 = r2.hasNext();	 Catch:{ Exception -> 0x0349 }
        if (r3 == 0) goto L_0x029f;
    L_0x0274:
        r3 = r2.next();	 Catch:{ Exception -> 0x0349 }
        r3 = (java.util.Map.Entry) r3;	 Catch:{ Exception -> 0x0349 }
        r4 = "";
        r5 = "var";
        r0.startTag(r4, r5);	 Catch:{ Exception -> 0x0349 }
        r4 = "";
        r5 = "key";
        r6 = r3.getKey();	 Catch:{ Exception -> 0x0349 }
        r6 = (java.lang.String) r6;	 Catch:{ Exception -> 0x0349 }
        r0.attribute(r4, r5, r6);	 Catch:{ Exception -> 0x0349 }
        r3 = r3.getValue();	 Catch:{ Exception -> 0x0349 }
        r3 = (java.lang.String) r3;	 Catch:{ Exception -> 0x0349 }
        r0.text(r3);	 Catch:{ Exception -> 0x0349 }
        r3 = "";
        r4 = "var";
        r0.endTag(r3, r4);	 Catch:{ Exception -> 0x0349 }
        goto L_0x026e;
    L_0x029f:
        if (r12 == 0) goto L_0x02e0;
    L_0x02a1:
        r2 = r12.isEmpty();	 Catch:{ Exception -> 0x0349 }
        if (r2 != 0) goto L_0x02e0;
    L_0x02a7:
        r12 = r12.entrySet();	 Catch:{ Exception -> 0x0349 }
        r12 = r12.iterator();	 Catch:{ Exception -> 0x0349 }
    L_0x02af:
        r2 = r12.hasNext();	 Catch:{ Exception -> 0x0349 }
        if (r2 == 0) goto L_0x02e0;
    L_0x02b5:
        r2 = r12.next();	 Catch:{ Exception -> 0x0349 }
        r2 = (java.util.Map.Entry) r2;	 Catch:{ Exception -> 0x0349 }
        r3 = "";
        r4 = "var";
        r0.startTag(r3, r4);	 Catch:{ Exception -> 0x0349 }
        r3 = "";
        r4 = "key";
        r5 = r2.getKey();	 Catch:{ Exception -> 0x0349 }
        r5 = (java.lang.String) r5;	 Catch:{ Exception -> 0x0349 }
        r0.attribute(r3, r4, r5);	 Catch:{ Exception -> 0x0349 }
        r2 = r2.getValue();	 Catch:{ Exception -> 0x0349 }
        r2 = (java.lang.String) r2;	 Catch:{ Exception -> 0x0349 }
        r0.text(r2);	 Catch:{ Exception -> 0x0349 }
        r2 = "";
        r3 = "var";
        r0.endTag(r2, r3);	 Catch:{ Exception -> 0x0349 }
        goto L_0x02af;
    L_0x02e0:
        r12 = "";
        r2 = "cgi-data";
        r0.endTag(r12, r2);	 Catch:{ Exception -> 0x0349 }
        r12 = "";
        r2 = "request";
        r0.endTag(r12, r2);	 Catch:{ Exception -> 0x0349 }
        r12 = "";
        r2 = "server-environment";
        r0.startTag(r12, r2);	 Catch:{ Exception -> 0x0349 }
        r12 = "";
        r2 = "environment-name";
        r0.startTag(r12, r2);	 Catch:{ Exception -> 0x0349 }
        r12 = a;	 Catch:{ Exception -> 0x0349 }
        r0.text(r12);	 Catch:{ Exception -> 0x0349 }
        r12 = "";
        r2 = "environment-name";
        r0.endTag(r12, r2);	 Catch:{ Exception -> 0x0349 }
        r12 = "";
        r2 = "app-version";
        r0.startTag(r12, r2);	 Catch:{ Exception -> 0x0349 }
        r12 = c;	 Catch:{ Exception -> 0x0349 }
        r0.text(r12);	 Catch:{ Exception -> 0x0349 }
        r12 = "";
        r2 = "app-version";
        r0.endTag(r12, r2);	 Catch:{ Exception -> 0x0349 }
        r12 = "";
        r2 = "server-environment";
        r0.endTag(r12, r2);	 Catch:{ Exception -> 0x0349 }
        r12 = "";
        r2 = "notice";
        r0.endTag(r12, r2);	 Catch:{ Exception -> 0x0349 }
        r0.endDocument();	 Catch:{ Exception -> 0x0349 }
        r1.flush();	 Catch:{ Exception -> 0x0349 }
        r1.close();	 Catch:{ Exception -> 0x0349 }
        r12 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0349 }
        r0 = "Writing new ";
        r12.<init>(r0);	 Catch:{ Exception -> 0x0349 }
        r11 = r11.getClass();	 Catch:{ Exception -> 0x0349 }
        r11 = r11.getName();	 Catch:{ Exception -> 0x0349 }
        r12.append(r11);	 Catch:{ Exception -> 0x0349 }
        r11 = " exception to disk.";
        r12.append(r11);	 Catch:{ Exception -> 0x0349 }
    L_0x0349:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.simpl.android.fingerprint.commons.exception.a.c(java.lang.Throwable, java.util.Map):void");
    }
}
