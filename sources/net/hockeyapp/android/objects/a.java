package net.hockeyapp.android.objects;

import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class a {
    public static final SimpleDateFormat a = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
    private final String b;
    private String c;
    private Date d;
    private Date e;
    private String f;
    private String g;
    private String h;
    private String i;
    private String j;
    private String k;
    private String l;
    private String m;
    private String n;
    private Boolean o;

    public a(String str) {
        this.b = str;
    }

    public a(String str, Throwable th) {
        this(str);
        this.o = Boolean.valueOf(false);
        StringWriter stringWriter = new StringWriter();
        ThrowableExtension.printStackTrace(th, new PrintWriter(stringWriter));
        this.n = stringWriter.toString();
    }

    /* JADX WARNING: Removed duplicated region for block: B:32:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x00c7 A:{SYNTHETIC, Splitter:B:20:0x00c7} */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x00d4 A:{SYNTHETIC, Splitter:B:25:0x00d4} */
    public void a() {
        /*
        r4 = this;
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r1 = net.hockeyapp.android.a.a;
        r0.append(r1);
        r1 = "/";
        r0.append(r1);
        r1 = r4.b;
        r0.append(r1);
        r1 = ".stacktrace";
        r0.append(r1);
        r0 = r0.toString();
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "Writing unhandled exception to: ";
        r1.append(r2);
        r1.append(r0);
        r1 = r1.toString();
        net.hockeyapp.android.d.d.a(r1);
        r1 = 0;
        r2 = new java.io.BufferedWriter;	 Catch:{ IOException -> 0x00bf }
        r3 = new java.io.FileWriter;	 Catch:{ IOException -> 0x00bf }
        r3.<init>(r0);	 Catch:{ IOException -> 0x00bf }
        r2.<init>(r3);	 Catch:{ IOException -> 0x00bf }
        r0 = "Package";
        r1 = r4.j;	 Catch:{ IOException -> 0x00b9, all -> 0x00b7 }
        r4.a(r2, r0, r1);	 Catch:{ IOException -> 0x00b9, all -> 0x00b7 }
        r0 = "Version Code";
        r1 = r4.l;	 Catch:{ IOException -> 0x00b9, all -> 0x00b7 }
        r4.a(r2, r0, r1);	 Catch:{ IOException -> 0x00b9, all -> 0x00b7 }
        r0 = "Version Name";
        r1 = r4.k;	 Catch:{ IOException -> 0x00b9, all -> 0x00b7 }
        r4.a(r2, r0, r1);	 Catch:{ IOException -> 0x00b9, all -> 0x00b7 }
        r0 = "Android";
        r1 = r4.f;	 Catch:{ IOException -> 0x00b9, all -> 0x00b7 }
        r4.a(r2, r0, r1);	 Catch:{ IOException -> 0x00b9, all -> 0x00b7 }
        r0 = "Android Build";
        r1 = r4.g;	 Catch:{ IOException -> 0x00b9, all -> 0x00b7 }
        r4.a(r2, r0, r1);	 Catch:{ IOException -> 0x00b9, all -> 0x00b7 }
        r0 = "Manufacturer";
        r1 = r4.h;	 Catch:{ IOException -> 0x00b9, all -> 0x00b7 }
        r4.a(r2, r0, r1);	 Catch:{ IOException -> 0x00b9, all -> 0x00b7 }
        r0 = "Model";
        r1 = r4.i;	 Catch:{ IOException -> 0x00b9, all -> 0x00b7 }
        r4.a(r2, r0, r1);	 Catch:{ IOException -> 0x00b9, all -> 0x00b7 }
        r0 = "Thread";
        r1 = r4.m;	 Catch:{ IOException -> 0x00b9, all -> 0x00b7 }
        r4.a(r2, r0, r1);	 Catch:{ IOException -> 0x00b9, all -> 0x00b7 }
        r0 = "CrashReporter Key";
        r1 = r4.c;	 Catch:{ IOException -> 0x00b9, all -> 0x00b7 }
        r4.a(r2, r0, r1);	 Catch:{ IOException -> 0x00b9, all -> 0x00b7 }
        r0 = "Start Date";
        r1 = a;	 Catch:{ IOException -> 0x00b9, all -> 0x00b7 }
        r3 = r4.d;	 Catch:{ IOException -> 0x00b9, all -> 0x00b7 }
        r1 = r1.format(r3);	 Catch:{ IOException -> 0x00b9, all -> 0x00b7 }
        r4.a(r2, r0, r1);	 Catch:{ IOException -> 0x00b9, all -> 0x00b7 }
        r0 = "Date";
        r1 = a;	 Catch:{ IOException -> 0x00b9, all -> 0x00b7 }
        r3 = r4.e;	 Catch:{ IOException -> 0x00b9, all -> 0x00b7 }
        r1 = r1.format(r3);	 Catch:{ IOException -> 0x00b9, all -> 0x00b7 }
        r4.a(r2, r0, r1);	 Catch:{ IOException -> 0x00b9, all -> 0x00b7 }
        r0 = r4.o;	 Catch:{ IOException -> 0x00b9, all -> 0x00b7 }
        r0 = r0.booleanValue();	 Catch:{ IOException -> 0x00b9, all -> 0x00b7 }
        if (r0 == 0) goto L_0x00a4;
    L_0x009d:
        r0 = "Format";
        r1 = "Xamarin";
        r4.a(r2, r0, r1);	 Catch:{ IOException -> 0x00b9, all -> 0x00b7 }
    L_0x00a4:
        r0 = "\n";
        r2.write(r0);	 Catch:{ IOException -> 0x00b9, all -> 0x00b7 }
        r0 = r4.n;	 Catch:{ IOException -> 0x00b9, all -> 0x00b7 }
        r2.write(r0);	 Catch:{ IOException -> 0x00b9, all -> 0x00b7 }
        r2.flush();	 Catch:{ IOException -> 0x00b9, all -> 0x00b7 }
        if (r2 == 0) goto L_0x00d1;
    L_0x00b3:
        r2.close();	 Catch:{ IOException -> 0x00cb }
        goto L_0x00d1;
    L_0x00b7:
        r0 = move-exception;
        goto L_0x00d2;
    L_0x00b9:
        r0 = move-exception;
        r1 = r2;
        goto L_0x00c0;
    L_0x00bc:
        r0 = move-exception;
        r2 = r1;
        goto L_0x00d2;
    L_0x00bf:
        r0 = move-exception;
    L_0x00c0:
        r2 = "Error saving crash report!";
        net.hockeyapp.android.d.d.a(r2, r0);	 Catch:{ all -> 0x00bc }
        if (r1 == 0) goto L_0x00d1;
    L_0x00c7:
        r1.close();	 Catch:{ IOException -> 0x00cb }
        goto L_0x00d1;
    L_0x00cb:
        r0 = move-exception;
        r1 = "Error saving crash report!";
        net.hockeyapp.android.d.d.a(r1, r0);
    L_0x00d1:
        return;
    L_0x00d2:
        if (r2 == 0) goto L_0x00de;
    L_0x00d4:
        r2.close();	 Catch:{ IOException -> 0x00d8 }
        goto L_0x00de;
    L_0x00d8:
        r1 = move-exception;
        r2 = "Error saving crash report!";
        net.hockeyapp.android.d.d.a(r2, r1);
    L_0x00de:
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: net.hockeyapp.android.objects.a.a():void");
    }

    private void a(Writer writer, String str, String str2) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        stringBuilder.append(": ");
        stringBuilder.append(str2);
        stringBuilder.append("\n");
        writer.write(stringBuilder.toString());
    }

    public void a(String str) {
        this.c = str;
    }

    public void a(Date date) {
        this.d = date;
    }

    public void b(Date date) {
        this.e = date;
    }

    public void b(String str) {
        this.f = str;
    }

    public void c(String str) {
        this.g = str;
    }

    public void d(String str) {
        this.h = str;
    }

    public void e(String str) {
        this.i = str;
    }

    public void f(String str) {
        this.j = str;
    }

    public void g(String str) {
        this.k = str;
    }

    public void h(String str) {
        this.l = str;
    }

    public void i(String str) {
        this.m = str;
    }
}
