package com.login.nativesso.c;

import com.android.volley.VolleyError;
import com.gaana.login.sso.SsoErrorCodes;
import com.login.nativesso.a.l;
import com.login.nativesso.b.a;
import com.login.nativesso.i.c;

public class k extends a {
    public void onErrorResponse(VolleyError volleyError) {
        super.onErrorResponse(volleyError);
        l lVar = (l) a.a("GetUserDetailsCb");
        if (lVar != null) {
            lVar.onFailure(c.a((int) SsoErrorCodes.NETWORK_ERROR, "NETWORK_ERROR"));
            a.b("GetUserDetailsCb");
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:32:0x0161 A:{Catch:{ Exception -> 0x01a4 }} */
    /* JADX WARNING: Missing exception handler attribute for start block: B:29:0x015c */
    /* JADX WARNING: Can't wrap try/catch for region: R(10:6|(4:8|(2:11|9)|46|12)|13|(4:15|(2:18|16)|47|19)|20|21|(4:23|(1:25)|26|(1:28))|29|30|(1:32)) */
    /* renamed from: a */
    public void onResponse(org.json.JSONObject r9) {
        /*
        r8 = this;
        r0 = "GetUserDetailsCb";
        r0 = com.login.nativesso.b.a.a(r0);
        r0 = (com.login.nativesso.a.l) r0;
        r1 = "status";
        r1 = r9.getString(r1);	 Catch:{ Exception -> 0x01a4 }
        r2 = "SUCCESS";
        r1 = r2.equalsIgnoreCase(r1);	 Catch:{ Exception -> 0x01a4 }
        if (r1 == 0) goto L_0x017b;
    L_0x0016:
        r1 = "data";
        r1 = r9.getJSONObject(r1);	 Catch:{ Exception -> 0x01a4 }
        com.login.nativesso.g.b.a();	 Catch:{ Exception -> 0x01a4 }
        r2 = com.login.nativesso.d.c.a();	 Catch:{ Exception -> 0x01a4 }
        r2 = r2.d();	 Catch:{ Exception -> 0x01a4 }
        if (r1 == 0) goto L_0x0165;
    L_0x0029:
        r9 = new com.login.nativesso.e.e;	 Catch:{ Exception -> 0x01a4 }
        r9.<init>();	 Catch:{ Exception -> 0x01a4 }
        r3 = "dob";
        r3 = com.login.nativesso.i.a.a(r1, r3);	 Catch:{ Exception -> 0x01a4 }
        r9.e(r3);	 Catch:{ Exception -> 0x01a4 }
        r3 = "dp";
        r3 = com.login.nativesso.i.a.a(r1, r3);	 Catch:{ Exception -> 0x01a4 }
        r9.a(r3);	 Catch:{ Exception -> 0x01a4 }
        r3 = "emailList";
        r3 = r1.getJSONObject(r3);	 Catch:{ Exception -> 0x01a4 }
        if (r3 == 0) goto L_0x0068;
    L_0x0048:
        r4 = r3.keys();	 Catch:{ Exception -> 0x01a4 }
        r5 = new java.util.HashMap;	 Catch:{ Exception -> 0x01a4 }
        r5.<init>();	 Catch:{ Exception -> 0x01a4 }
    L_0x0051:
        r6 = r4.hasNext();	 Catch:{ Exception -> 0x01a4 }
        if (r6 == 0) goto L_0x0065;
    L_0x0057:
        r6 = r4.next();	 Catch:{ Exception -> 0x01a4 }
        r6 = (java.lang.String) r6;	 Catch:{ Exception -> 0x01a4 }
        r7 = r3.getString(r6);	 Catch:{ Exception -> 0x01a4 }
        r5.put(r6, r7);	 Catch:{ Exception -> 0x01a4 }
        goto L_0x0051;
    L_0x0065:
        r9.a(r5);	 Catch:{ Exception -> 0x01a4 }
    L_0x0068:
        r3 = "fbConnected";
        r3 = r1.getBoolean(r3);	 Catch:{ Exception -> 0x01a4 }
        r9.a(r3);	 Catch:{ Exception -> 0x01a4 }
        r3 = "gpConnected";
        r3 = r1.getBoolean(r3);	 Catch:{ Exception -> 0x01a4 }
        r9.b(r3);	 Catch:{ Exception -> 0x01a4 }
        r3 = "passwordExists";
        r3 = r1.getBoolean(r3);	 Catch:{ Exception -> 0x01a4 }
        r9.c(r3);	 Catch:{ Exception -> 0x01a4 }
        r3 = "primaryEmail";
        r3 = com.login.nativesso.i.a.a(r1, r3);	 Catch:{ Exception -> 0x01a4 }
        r9.f(r3);	 Catch:{ Exception -> 0x01a4 }
        r3 = "firstName";
        r3 = com.login.nativesso.i.a.a(r1, r3);	 Catch:{ Exception -> 0x01a4 }
        r9.b(r3);	 Catch:{ Exception -> 0x01a4 }
        r3 = "lastName";
        r3 = com.login.nativesso.i.a.a(r1, r3);	 Catch:{ Exception -> 0x01a4 }
        r9.c(r3);	 Catch:{ Exception -> 0x01a4 }
        r3 = "gender";
        r3 = com.login.nativesso.i.a.a(r1, r3);	 Catch:{ Exception -> 0x01a4 }
        r9.d(r3);	 Catch:{ Exception -> 0x01a4 }
        r3 = "ssoid";
        r3 = com.login.nativesso.i.a.a(r1, r3);	 Catch:{ Exception -> 0x01a4 }
        r9.g(r3);	 Catch:{ Exception -> 0x01a4 }
        r3 = "city";
        r3 = com.login.nativesso.i.a.a(r1, r3);	 Catch:{ Exception -> 0x01a4 }
        r9.h(r3);	 Catch:{ Exception -> 0x01a4 }
        r3 = "isEuUser";
        r3 = com.login.nativesso.i.a.a(r1, r3);	 Catch:{ Exception -> 0x01a4 }
        r9.l(r3);	 Catch:{ Exception -> 0x01a4 }
        r3 = "primeProfile";
        r3 = com.login.nativesso.i.a.a(r1, r3);	 Catch:{ Exception -> 0x01a4 }
        r9.i(r3);	 Catch:{ Exception -> 0x01a4 }
        r3 = "shareDataAllowed";
        r3 = com.login.nativesso.i.a.a(r1, r3);	 Catch:{ Exception -> 0x01a4 }
        r9.j(r3);	 Catch:{ Exception -> 0x01a4 }
        r3 = "termsAccepted";
        r3 = com.login.nativesso.i.a.a(r1, r3);	 Catch:{ Exception -> 0x01a4 }
        r9.k(r3);	 Catch:{ Exception -> 0x01a4 }
        r3 = "timespointsPolicy";
        r3 = com.login.nativesso.i.a.a(r1, r3);	 Catch:{ Exception -> 0x01a4 }
        r9.q(r3);	 Catch:{ Exception -> 0x01a4 }
        r3 = "mobileList";
        r3 = r1.getJSONObject(r3);	 Catch:{ Exception -> 0x01a4 }
        if (r3 == 0) goto L_0x010e;
    L_0x00ee:
        r4 = r3.keys();	 Catch:{ Exception -> 0x01a4 }
        r5 = new java.util.HashMap;	 Catch:{ Exception -> 0x01a4 }
        r5.<init>();	 Catch:{ Exception -> 0x01a4 }
    L_0x00f7:
        r6 = r4.hasNext();	 Catch:{ Exception -> 0x01a4 }
        if (r6 == 0) goto L_0x010b;
    L_0x00fd:
        r6 = r4.next();	 Catch:{ Exception -> 0x01a4 }
        r6 = (java.lang.String) r6;	 Catch:{ Exception -> 0x01a4 }
        r7 = r3.getString(r6);	 Catch:{ Exception -> 0x01a4 }
        r5.put(r6, r7);	 Catch:{ Exception -> 0x01a4 }
        goto L_0x00f7;
    L_0x010b:
        r9.b(r5);	 Catch:{ Exception -> 0x01a4 }
    L_0x010e:
        r3 = "mobileData";
        r3 = r1.has(r3);	 Catch:{ Exception -> 0x015c }
        if (r3 == 0) goto L_0x015c;
    L_0x0116:
        r3 = "mobileData";
        r1 = r1.getJSONObject(r3);	 Catch:{ Exception -> 0x015c }
        r3 = "Verified";
        r3 = r1.has(r3);	 Catch:{ Exception -> 0x015c }
        if (r3 == 0) goto L_0x013c;
    L_0x0124:
        r3 = "Verified";
        r3 = r1.getJSONObject(r3);	 Catch:{ Exception -> 0x015c }
        r4 = "code";
        r4 = com.login.nativesso.i.a.a(r3, r4);	 Catch:{ Exception -> 0x015c }
        r9.m(r4);	 Catch:{ Exception -> 0x015c }
        r4 = "mobile";
        r3 = com.login.nativesso.i.a.a(r3, r4);	 Catch:{ Exception -> 0x015c }
        r9.n(r3);	 Catch:{ Exception -> 0x015c }
    L_0x013c:
        r3 = "Unverified";
        r3 = r1.has(r3);	 Catch:{ Exception -> 0x015c }
        if (r3 == 0) goto L_0x015c;
    L_0x0144:
        r3 = "Unverified";
        r1 = r1.getJSONObject(r3);	 Catch:{ Exception -> 0x015c }
        r3 = "code";
        r3 = com.login.nativesso.i.a.a(r1, r3);	 Catch:{ Exception -> 0x015c }
        r9.o(r3);	 Catch:{ Exception -> 0x015c }
        r3 = "mobile";
        r1 = com.login.nativesso.i.a.a(r1, r3);	 Catch:{ Exception -> 0x015c }
        r9.p(r1);	 Catch:{ Exception -> 0x015c }
    L_0x015c:
        com.login.nativesso.g.b.a(r2, r9);	 Catch:{ Exception -> 0x01a4 }
        if (r0 == 0) goto L_0x01b5;
    L_0x0161:
        r0.onSuccess(r9);	 Catch:{ Exception -> 0x01a4 }
        goto L_0x01b5;
    L_0x0165:
        if (r0 == 0) goto L_0x01b5;
    L_0x0167:
        r1 = "code";
        r1 = r9.getInt(r1);	 Catch:{ Exception -> 0x01a4 }
        r2 = "message";
        r9 = r9.getString(r2);	 Catch:{ Exception -> 0x01a4 }
        r9 = com.login.nativesso.i.c.a(r1, r9);	 Catch:{ Exception -> 0x01a4 }
        r0.onFailure(r9);	 Catch:{ Exception -> 0x01a4 }
        goto L_0x01b5;
    L_0x017b:
        r1 = "message";
        r1 = r9.getString(r1);	 Catch:{ Exception -> 0x01a4 }
        r2 = "UNAUTHORIZED_ACCESS";
        r2 = r2.equals(r1);	 Catch:{ Exception -> 0x01a4 }
        if (r2 == 0) goto L_0x0194;
    L_0x0189:
        r2 = com.login.nativesso.d.c.a();	 Catch:{ Exception -> 0x01a4 }
        r2 = r2.d();	 Catch:{ Exception -> 0x01a4 }
        com.login.nativesso.i.c.a(r2);	 Catch:{ Exception -> 0x01a4 }
    L_0x0194:
        if (r0 == 0) goto L_0x01b5;
    L_0x0196:
        r2 = "code";
        r9 = r9.getInt(r2);	 Catch:{ Exception -> 0x01a4 }
        r9 = com.login.nativesso.i.c.a(r9, r1);	 Catch:{ Exception -> 0x01a4 }
        r0.onFailure(r9);	 Catch:{ Exception -> 0x01a4 }
        goto L_0x01b5;
    L_0x01a4:
        r9 = move-exception;
        com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r9);
        if (r0 == 0) goto L_0x01b5;
    L_0x01aa:
        r9 = 4002; // 0xfa2 float:5.608E-42 double:1.9773E-320;
        r1 = "REQUEST_FAILED";
        r9 = com.login.nativesso.i.c.a(r9, r1);
        r0.onFailure(r9);
    L_0x01b5:
        r9 = "GetUserDetailsCb";
        com.login.nativesso.b.a.b(r9);
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.login.nativesso.c.k.onResponse(org.json.JSONObject):void");
    }
}
