package com.appsflyer;

import android.content.Context;
import com.android.b.a.a;
import com.android.b.a.c;

final class u implements c {
    private a a;
    private s b;

    u() {
    }

    /* Access modifiers changed, original: protected|final */
    public final void a(Context context, s sVar) {
        this.b = sVar;
        this.a = a.a(context).a();
        try {
            this.a.a((c) this);
        } catch (Exception e) {
            AFLogger.a("referrerClient -> startConnection", e);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:27:0x00bf  */
    /* JADX WARNING: Removed duplicated region for block: B:35:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x00ec  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00bf  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x00ec  */
    /* JADX WARNING: Removed duplicated region for block: B:35:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00bf  */
    /* JADX WARNING: Removed duplicated region for block: B:35:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x00ec  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00bf  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x00ec  */
    /* JADX WARNING: Removed duplicated region for block: B:35:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00bf  */
    /* JADX WARNING: Removed duplicated region for block: B:35:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x00ec  */
    public final void onInstallReferrerSetupFinished(int r6) {
        /*
        r5 = this;
        r0 = new java.util.HashMap;
        r0.<init>();
        r1 = "code";
        r2 = java.lang.String.valueOf(r6);
        r0.put(r1, r2);
        r1 = 0;
        switch(r6) {
            case 0: goto L_0x0027;
            case 1: goto L_0x0020;
            case 2: goto L_0x0019;
            default: goto L_0x0012;
        };
    L_0x0012:
        r6 = "responseCode not found.";
        com.appsflyer.AFLogger.e(r6);
        goto L_0x00bd;
    L_0x0019:
        r6 = "InstallReferrer not supported";
        com.appsflyer.AFLogger.e(r6);
        goto L_0x00bd;
    L_0x0020:
        r6 = "InstallReferrer not supported";
        com.appsflyer.AFLogger.e(r6);
        goto L_0x00bd;
    L_0x0027:
        r6 = "InstallReferrer connected";
        com.appsflyer.AFLogger.c(r6);	 Catch:{ RemoteException -> 0x009e, IllegalStateException -> 0x007e, Throwable -> 0x005e }
        r6 = r5.a;	 Catch:{ RemoteException -> 0x009e, IllegalStateException -> 0x007e, Throwable -> 0x005e }
        r6 = r6.a();	 Catch:{ RemoteException -> 0x009e, IllegalStateException -> 0x007e, Throwable -> 0x005e }
        if (r6 == 0) goto L_0x0051;
    L_0x0034:
        r6 = r5.a;	 Catch:{ RemoteException -> 0x009e, IllegalStateException -> 0x007e, Throwable -> 0x005e }
        r6 = r6.c();	 Catch:{ RemoteException -> 0x009e, IllegalStateException -> 0x007e, Throwable -> 0x005e }
        r1 = r5.a;	 Catch:{ RemoteException -> 0x004c, IllegalStateException -> 0x0047, Throwable -> 0x0042 }
        r1.b();	 Catch:{ RemoteException -> 0x004c, IllegalStateException -> 0x0047, Throwable -> 0x0042 }
        r1 = r6;
        goto L_0x00bd;
    L_0x0042:
        r1 = move-exception;
        r4 = r1;
        r1 = r6;
        r6 = r4;
        goto L_0x005f;
    L_0x0047:
        r1 = move-exception;
        r4 = r1;
        r1 = r6;
        r6 = r4;
        goto L_0x007f;
    L_0x004c:
        r1 = move-exception;
        r4 = r1;
        r1 = r6;
        r6 = r4;
        goto L_0x009f;
    L_0x0051:
        r6 = "ReferrerClient: InstallReferrer is not ready";
        com.appsflyer.AFLogger.e(r6);	 Catch:{ RemoteException -> 0x009e, IllegalStateException -> 0x007e, Throwable -> 0x005e }
        r6 = "err";
        r2 = "ReferrerClient: InstallReferrer is not ready";
        r0.put(r6, r2);	 Catch:{ RemoteException -> 0x009e, IllegalStateException -> 0x007e, Throwable -> 0x005e }
        goto L_0x00bd;
    L_0x005e:
        r6 = move-exception;
    L_0x005f:
        r2 = new java.lang.StringBuilder;
        r3 = "Failed to get install referrer: ";
        r2.<init>(r3);
        r3 = r6.getMessage();
        r2.append(r3);
        r2 = r2.toString();
        com.appsflyer.AFLogger.e(r2);
        r2 = "err";
        r6 = r6.getMessage();
        r0.put(r2, r6);
        goto L_0x00bd;
    L_0x007e:
        r6 = move-exception;
    L_0x007f:
        r2 = new java.lang.StringBuilder;
        r3 = "Failed to get install referrer: ";
        r2.<init>(r3);
        r3 = r6.getMessage();
        r2.append(r3);
        r2 = r2.toString();
        com.appsflyer.AFLogger.e(r2);
        r2 = "err";
        r6 = r6.getMessage();
        r0.put(r2, r6);
        goto L_0x00bd;
    L_0x009e:
        r6 = move-exception;
    L_0x009f:
        r2 = new java.lang.StringBuilder;
        r3 = "Failed to get install referrer: ";
        r2.<init>(r3);
        r3 = r6.getMessage();
        r2.append(r3);
        r2 = r2.toString();
        com.appsflyer.AFLogger.e(r2);
        r2 = "err";
        r6 = r6.getMessage();
        r0.put(r2, r6);
    L_0x00bd:
        if (r1 == 0) goto L_0x00e8;
    L_0x00bf:
        r6 = r1.a();
        if (r6 == 0) goto L_0x00ce;
    L_0x00c5:
        r6 = "val";
        r2 = r1.a();
        r0.put(r6, r2);
    L_0x00ce:
        r6 = "clk";
        r2 = r1.b();
        r2 = java.lang.Long.toString(r2);
        r0.put(r6, r2);
        r6 = "install";
        r1 = r1.c();
        r1 = java.lang.Long.toString(r1);
        r0.put(r6, r1);
    L_0x00e8:
        r6 = r5.b;
        if (r6 == 0) goto L_0x00f1;
    L_0x00ec:
        r6 = r5.b;
        r6.a(r0);
    L_0x00f1:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.u.onInstallReferrerSetupFinished(int):void");
    }

    public final void onInstallReferrerServiceDisconnected() {
        AFLogger.c("Install Referrer service disconnected");
    }
}
