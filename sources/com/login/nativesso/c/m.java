package com.login.nativesso.c;

import com.android.volley.VolleyError;
import com.gaana.login.sso.SsoErrorCodes;
import com.login.nativesso.a.n;
import com.login.nativesso.b.a;
import com.login.nativesso.i.c;

public class m extends a {
    public void onErrorResponse(VolleyError volleyError) {
        super.onErrorResponse(volleyError);
        n nVar = (n) a.a("MigrateSessionCb");
        if (nVar != null) {
            nVar.onFailure(c.a((int) SsoErrorCodes.NETWORK_ERROR, "NETWORK_ERROR"));
            a.b("MigrateSessionCb");
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x0091 A:{Catch:{ ServerException -> 0x0120, SecurityException -> 0x0109, Exception -> 0x00f7 }} */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x00d1 A:{Catch:{ ServerException -> 0x0120, SecurityException -> 0x0109, Exception -> 0x00f7 }} */
    /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x008b */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0120 A:{ExcHandler: ServerException (r9_19 'e' com.login.nativesso.exception.ServerException), Splitter:B:1:0x0008} */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0109 A:{ExcHandler: SecurityException (r9_15 'e' com.login.nativesso.exception.SecurityException), Splitter:B:1:0x0008} */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing block: B:27:0x00f7, code skipped:
            r9 = move-exception;
     */
    /* JADX WARNING: Missing block: B:28:0x00f8, code skipped:
            com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r9);
     */
    /* JADX WARNING: Missing block: B:29:0x00fb, code skipped:
            if (r0 != null) goto L_0x00fd;
     */
    /* JADX WARNING: Missing block: B:30:0x00fd, code skipped:
            r0.onFailure(com.login.nativesso.i.c.a((int) com.gaana.login.sso.SsoErrorCodes.REQUEST_FAILED, "REQUEST_FAILED"));
     */
    /* JADX WARNING: Missing block: B:31:0x0109, code skipped:
            r9 = move-exception;
     */
    /* JADX WARNING: Missing block: B:32:0x010a, code skipped:
            if (r0 != null) goto L_0x010c;
     */
    /* JADX WARNING: Missing block: B:33:0x010c, code skipped:
            com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r9);
            r0.onFailure(com.login.nativesso.i.c.a(4008, "SECURITY_ISSUE"));
            com.login.nativesso.b.a.b("MigrateSessionCb");
     */
    /* JADX WARNING: Missing block: B:34:0x011f, code skipped:
            return;
     */
    /* JADX WARNING: Missing block: B:35:0x0120, code skipped:
            r9 = move-exception;
     */
    /* JADX WARNING: Missing block: B:36:0x0121, code skipped:
            if (r0 != null) goto L_0x0123;
     */
    /* JADX WARNING: Missing block: B:37:0x0123, code skipped:
            com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r9);
            r0.onFailure(com.login.nativesso.i.c.a((int) com.gaana.login.sso.SsoErrorCodes.SERVER_ERROR, "SERVER_ERROR"));
            com.login.nativesso.b.a.b("MigrateSessionCb");
     */
    /* JADX WARNING: Missing block: B:38:0x0136, code skipped:
            return;
     */
    /* renamed from: a */
    public void onResponse(org.json.JSONObject r9) {
        /*
        r8 = this;
        r0 = "MigrateSessionCb";
        r0 = com.login.nativesso.b.a.a(r0);
        r0 = (com.login.nativesso.a.n) r0;
        r1 = "status";
        r1 = r9.getString(r1);	 Catch:{ ServerException -> 0x0120, SecurityException -> 0x0109, Exception -> 0x00f7 }
        r2 = "SUCCESS";
        r1 = r2.equalsIgnoreCase(r1);	 Catch:{ ServerException -> 0x0120, SecurityException -> 0x0109, Exception -> 0x00f7 }
        if (r1 == 0) goto L_0x00e1;
    L_0x0016:
        r1 = "data";
        r9 = r9.getJSONObject(r1);	 Catch:{ ServerException -> 0x0120, SecurityException -> 0x0109, Exception -> 0x00f7 }
        r1 = "ssec";
        r1 = r9.getString(r1);	 Catch:{ ServerException -> 0x0120, SecurityException -> 0x0109, Exception -> 0x00f7 }
        r2 = "ticketId";
        r2 = r9.getString(r2);	 Catch:{ ServerException -> 0x0120, SecurityException -> 0x0109, Exception -> 0x00f7 }
        r3 = "type";
        r3 = r9.getString(r3);	 Catch:{ ServerException -> 0x0120, SecurityException -> 0x0109, Exception -> 0x00f7 }
        r4 = "identifier";
        r4 = r9.getString(r4);	 Catch:{ ServerException -> 0x0120, SecurityException -> 0x0109, Exception -> 0x00f7 }
        r5 = "unverifiedUser";
        r9 = r9.getString(r5);	 Catch:{ ServerException -> 0x0120, SecurityException -> 0x0109, Exception -> 0x00f7 }
        r5 = "1";
        r9 = r5.equalsIgnoreCase(r9);	 Catch:{ ServerException -> 0x0120, SecurityException -> 0x0109, Exception -> 0x00f7 }
        if (r9 == 0) goto L_0x0053;
    L_0x0042:
        r9 = 4011; // 0xfab float:5.62E-42 double:1.9817E-320;
        r1 = "MIGRATE_UNVERIFIED_USER_ERROR";
        r9 = com.login.nativesso.i.c.a(r9, r1);	 Catch:{ ServerException -> 0x0120, SecurityException -> 0x0109, Exception -> 0x00f7 }
        r0.onFailure(r9);	 Catch:{ ServerException -> 0x0120, SecurityException -> 0x0109, Exception -> 0x00f7 }
        r9 = "MigrateSessionCb";
        com.login.nativesso.b.a.b(r9);	 Catch:{ ServerException -> 0x0120, SecurityException -> 0x0109, Exception -> 0x00f7 }
        return;
    L_0x0053:
        r9 = com.login.nativesso.d.c.a();	 Catch:{ ServerException -> 0x0120, SecurityException -> 0x0109, Exception -> 0x00f7 }
        r9 = r9.d();	 Catch:{ ServerException -> 0x0120, SecurityException -> 0x0109, Exception -> 0x00f7 }
        r5 = com.login.nativesso.g.b.a();	 Catch:{ ServerException -> 0x0120, SecurityException -> 0x0109, Exception -> 0x00f7 }
        r6 = "SSECID";
        r5.a(r9, r6, r1);	 Catch:{ ServerException -> 0x0120, SecurityException -> 0x0109, Exception -> 0x00f7 }
        r5 = com.login.nativesso.g.b.a();	 Catch:{ ServerException -> 0x0120, SecurityException -> 0x0109, Exception -> 0x00f7 }
        r6 = "TICKETID";
        r5.a(r9, r6, r2);	 Catch:{ ServerException -> 0x0120, SecurityException -> 0x0109, Exception -> 0x00f7 }
        r5 = com.login.nativesso.g.b.a();	 Catch:{ ServerException -> 0x0120, SecurityException -> 0x0109, Exception -> 0x00f7 }
        r6 = "LAST_SESSION_SRC";
        r5.a(r9, r6, r3);	 Catch:{ ServerException -> 0x0120, SecurityException -> 0x0109, Exception -> 0x00f7 }
        r5 = com.login.nativesso.g.b.a();	 Catch:{ ServerException -> 0x0120, SecurityException -> 0x0109, Exception -> 0x00f7 }
        r6 = "LAST_SESSION_IDENTIFIER";
        r5.a(r9, r6, r4);	 Catch:{ ServerException -> 0x0120, SecurityException -> 0x0109, Exception -> 0x00f7 }
        r5 = com.login.nativesso.i.a.a(r9);	 Catch:{ ServerException -> 0x0120, SecurityException -> 0x0109, Exception -> 0x00f7 }
        r6 = 0;
        r7 = "SSECID";
        r7 = r5.getString(r7);	 Catch:{ Exception -> 0x008b, ServerException -> 0x0120, SecurityException -> 0x0109 }
        r6 = r7;
    L_0x008b:
        r6 = com.login.nativesso.i.c.a(r6);	 Catch:{ ServerException -> 0x0120, SecurityException -> 0x0109, Exception -> 0x00f7 }
        if (r6 == 0) goto L_0x00cf;
    L_0x0091:
        r6 = com.login.nativesso.g.b.a();	 Catch:{ ServerException -> 0x0120, SecurityException -> 0x0109, Exception -> 0x00f7 }
        r6 = r6.c(r9);	 Catch:{ ServerException -> 0x0120, SecurityException -> 0x0109, Exception -> 0x00f7 }
        r7 = "TGID";
        r5.put(r7, r6);	 Catch:{ ServerException -> 0x0120, SecurityException -> 0x0109, Exception -> 0x00f7 }
        r6 = "SSECID";
        r5.put(r6, r1);	 Catch:{ ServerException -> 0x0120, SecurityException -> 0x0109, Exception -> 0x00f7 }
        r1 = "TICKETID";
        r5.put(r1, r2);	 Catch:{ ServerException -> 0x0120, SecurityException -> 0x0109, Exception -> 0x00f7 }
        r1 = "sso";
        r1 = r3.contains(r1);	 Catch:{ ServerException -> 0x0120, SecurityException -> 0x0109, Exception -> 0x00f7 }
        if (r1 == 0) goto L_0x00c7;
    L_0x00b0:
        r1 = "SOCIALTYPE";
        r2 = new java.lang.StringBuilder;	 Catch:{ ServerException -> 0x0120, SecurityException -> 0x0109, Exception -> 0x00f7 }
        r2.<init>();	 Catch:{ ServerException -> 0x0120, SecurityException -> 0x0109, Exception -> 0x00f7 }
        r3 = "sso&";
        r2.append(r3);	 Catch:{ ServerException -> 0x0120, SecurityException -> 0x0109, Exception -> 0x00f7 }
        r2.append(r4);	 Catch:{ ServerException -> 0x0120, SecurityException -> 0x0109, Exception -> 0x00f7 }
        r2 = r2.toString();	 Catch:{ ServerException -> 0x0120, SecurityException -> 0x0109, Exception -> 0x00f7 }
        r5.put(r1, r2);	 Catch:{ ServerException -> 0x0120, SecurityException -> 0x0109, Exception -> 0x00f7 }
        goto L_0x00cc;
    L_0x00c7:
        r1 = "SOCIALTYPE";
        r5.put(r1, r3);	 Catch:{ ServerException -> 0x0120, SecurityException -> 0x0109, Exception -> 0x00f7 }
    L_0x00cc:
        com.login.nativesso.i.a.a(r9, r5);	 Catch:{ ServerException -> 0x0120, SecurityException -> 0x0109, Exception -> 0x00f7 }
    L_0x00cf:
        if (r0 == 0) goto L_0x00d4;
    L_0x00d1:
        r0.onSuccess();	 Catch:{ ServerException -> 0x0120, SecurityException -> 0x0109, Exception -> 0x00f7 }
    L_0x00d4:
        r9 = com.login.nativesso.d.c.a();	 Catch:{ ServerException -> 0x0120, SecurityException -> 0x0109, Exception -> 0x00f7 }
        r1 = new com.login.nativesso.c.m$1;	 Catch:{ ServerException -> 0x0120, SecurityException -> 0x0109, Exception -> 0x00f7 }
        r1.<init>();	 Catch:{ ServerException -> 0x0120, SecurityException -> 0x0109, Exception -> 0x00f7 }
        r9.a(r1);	 Catch:{ ServerException -> 0x0120, SecurityException -> 0x0109, Exception -> 0x00f7 }
        goto L_0x0137;
    L_0x00e1:
        r1 = "message";
        r1 = r9.getString(r1);	 Catch:{ ServerException -> 0x0120, SecurityException -> 0x0109, Exception -> 0x00f7 }
        r2 = "code";
        r9 = r9.getInt(r2);	 Catch:{ ServerException -> 0x0120, SecurityException -> 0x0109, Exception -> 0x00f7 }
        if (r0 == 0) goto L_0x0137;
    L_0x00ef:
        r9 = com.login.nativesso.i.c.a(r9, r1);	 Catch:{ ServerException -> 0x0120, SecurityException -> 0x0109, Exception -> 0x00f7 }
        r0.onFailure(r9);	 Catch:{ ServerException -> 0x0120, SecurityException -> 0x0109, Exception -> 0x00f7 }
        goto L_0x0137;
    L_0x00f7:
        r9 = move-exception;
        com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r9);
        if (r0 == 0) goto L_0x0137;
    L_0x00fd:
        r9 = 4002; // 0xfa2 float:5.608E-42 double:1.9773E-320;
        r1 = "REQUEST_FAILED";
        r9 = com.login.nativesso.i.c.a(r9, r1);
        r0.onFailure(r9);
        goto L_0x0137;
    L_0x0109:
        r9 = move-exception;
        if (r0 == 0) goto L_0x0137;
    L_0x010c:
        com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r9);
        r9 = 4008; // 0xfa8 float:5.616E-42 double:1.98E-320;
        r1 = "SECURITY_ISSUE";
        r9 = com.login.nativesso.i.c.a(r9, r1);
        r0.onFailure(r9);
        r9 = "MigrateSessionCb";
        com.login.nativesso.b.a.b(r9);
        return;
    L_0x0120:
        r9 = move-exception;
        if (r0 == 0) goto L_0x0137;
    L_0x0123:
        com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r9);
        r9 = 4007; // 0xfa7 float:5.615E-42 double:1.9797E-320;
        r1 = "SERVER_ERROR";
        r9 = com.login.nativesso.i.c.a(r9, r1);
        r0.onFailure(r9);
        r9 = "MigrateSessionCb";
        com.login.nativesso.b.a.b(r9);
        return;
    L_0x0137:
        r9 = "MigrateSessionCb";
        com.login.nativesso.b.a.b(r9);
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.login.nativesso.c.m.onResponse(org.json.JSONObject):void");
    }
}
