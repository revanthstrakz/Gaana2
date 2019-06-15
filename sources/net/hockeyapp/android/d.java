package net.hockeyapp.android;

import android.os.Process;
import android.text.TextUtils;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.Date;
import java.util.UUID;
import net.hockeyapp.android.objects.a;

public class d implements UncaughtExceptionHandler {
    private boolean a = false;
    private c b;
    private UncaughtExceptionHandler c;

    public d(UncaughtExceptionHandler uncaughtExceptionHandler, c cVar, boolean z) {
        this.c = uncaughtExceptionHandler;
        this.a = z;
        this.b = cVar;
    }

    public void a(c cVar) {
        this.b = cVar;
    }

    public static void a(Throwable th, Thread thread, c cVar) {
        Date date = new Date();
        Date date2 = new Date(b.a());
        ThrowableExtension.printStackTrace(th, new PrintWriter(new StringWriter()));
        String uuid = UUID.randomUUID().toString();
        a aVar = new a(uuid, th);
        aVar.f(a.d);
        aVar.h(a.b);
        aVar.g(a.c);
        aVar.a(date2);
        aVar.b(date);
        if (cVar == null || cVar.includeDeviceData()) {
            aVar.b(a.e);
            aVar.c(a.f);
            aVar.d(a.h);
            aVar.e(a.g);
        }
        if (thread != null && (cVar == null || cVar.includeThreadDetails())) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(thread.getName());
            stringBuilder.append("-");
            stringBuilder.append(thread.getId());
            aVar.i(stringBuilder.toString());
        }
        if (a.i != null && (cVar == null || cVar.includeDeviceIdentifier())) {
            aVar.a(a.i);
        }
        aVar.a();
        if (cVar != null) {
            try {
                String a = a(cVar.getUserID());
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append(uuid);
                stringBuilder2.append(".user");
                a(a, stringBuilder2.toString());
                a = a(cVar.getContact());
                stringBuilder2 = new StringBuilder();
                stringBuilder2.append(uuid);
                stringBuilder2.append(".contact");
                a(a, stringBuilder2.toString());
                a = cVar.getDescription();
                stringBuilder2 = new StringBuilder();
                stringBuilder2.append(uuid);
                stringBuilder2.append(".description");
                a(a, stringBuilder2.toString());
            } catch (IOException th2) {
                net.hockeyapp.android.d.d.a("Error saving crash meta data!", th2);
            }
        }
    }

    public void uncaughtException(Thread thread, Throwable th) {
        if (a.a == null) {
            this.c.uncaughtException(thread, th);
            return;
        }
        a(th, thread, this.b);
        if (this.a) {
            Process.killProcess(Process.myPid());
            System.exit(10);
            return;
        }
        this.c.uncaughtException(thread, th);
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0047  */
    /* JADX WARNING: Missing block: B:17:0x0041, code skipped:
            if (r0 != null) goto L_0x004d;
     */
    /* JADX WARNING: Missing block: B:22:0x004b, code skipped:
            if (r0 != null) goto L_0x004d;
     */
    /* JADX WARNING: Missing block: B:24:0x004d, code skipped:
            r0.close();
     */
    /* JADX WARNING: Missing block: B:25:0x0050, code skipped:
            return;
     */
    private static void a(java.lang.String r3, java.lang.String r4) throws java.io.IOException {
        /*
        r0 = android.text.TextUtils.isEmpty(r3);
        if (r0 == 0) goto L_0x0007;
    L_0x0006:
        return;
    L_0x0007:
        r0 = 0;
        r1 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x004b, all -> 0x0044 }
        r1.<init>();	 Catch:{ IOException -> 0x004b, all -> 0x0044 }
        r2 = net.hockeyapp.android.a.a;	 Catch:{ IOException -> 0x004b, all -> 0x0044 }
        r1.append(r2);	 Catch:{ IOException -> 0x004b, all -> 0x0044 }
        r2 = "/";
        r1.append(r2);	 Catch:{ IOException -> 0x004b, all -> 0x0044 }
        r1.append(r4);	 Catch:{ IOException -> 0x004b, all -> 0x0044 }
        r4 = r1.toString();	 Catch:{ IOException -> 0x004b, all -> 0x0044 }
        r1 = android.text.TextUtils.isEmpty(r3);	 Catch:{ IOException -> 0x004b, all -> 0x0044 }
        if (r1 != 0) goto L_0x0041;
    L_0x0024:
        r1 = android.text.TextUtils.getTrimmedLength(r3);	 Catch:{ IOException -> 0x004b, all -> 0x0044 }
        if (r1 <= 0) goto L_0x0041;
    L_0x002a:
        r1 = new java.io.BufferedWriter;	 Catch:{ IOException -> 0x004b, all -> 0x0044 }
        r2 = new java.io.FileWriter;	 Catch:{ IOException -> 0x004b, all -> 0x0044 }
        r2.<init>(r4);	 Catch:{ IOException -> 0x004b, all -> 0x0044 }
        r1.<init>(r2);	 Catch:{ IOException -> 0x004b, all -> 0x0044 }
        r1.write(r3);	 Catch:{ IOException -> 0x003f, all -> 0x003c }
        r1.flush();	 Catch:{ IOException -> 0x003f, all -> 0x003c }
        r0 = r1;
        goto L_0x0041;
    L_0x003c:
        r3 = move-exception;
        r0 = r1;
        goto L_0x0045;
    L_0x003f:
        r0 = r1;
        goto L_0x004b;
    L_0x0041:
        if (r0 == 0) goto L_0x0050;
    L_0x0043:
        goto L_0x004d;
    L_0x0044:
        r3 = move-exception;
    L_0x0045:
        if (r0 == 0) goto L_0x004a;
    L_0x0047:
        r0.close();
    L_0x004a:
        throw r3;
    L_0x004b:
        if (r0 == 0) goto L_0x0050;
    L_0x004d:
        r0.close();
    L_0x0050:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: net.hockeyapp.android.d.a(java.lang.String, java.lang.String):void");
    }

    private static String a(String str) {
        return (TextUtils.isEmpty(str) || str.length() <= 255) ? str : str.substring(0, 255);
    }
}
