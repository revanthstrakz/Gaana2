package com.til.colombia.android.service;

import android.util.Log;
import com.til.colombia.android.internal.a;
import com.til.colombia.android.internal.i;
import com.til.colombia.android.network.ErrorCode;
import java.util.concurrent.ExecutorService;

final class cl extends ca {
    public cl(ExecutorService executorService, bl blVar) {
        super(executorService, blVar);
    }

    public final void a(Exception exception) {
        String str = i.f;
        StringBuilder stringBuilder = new StringBuilder("failed to load Colombia Ads:");
        stringBuilder.append(exception);
        Log.i(str, stringBuilder.toString());
        try {
            m mVar = new m(true, exception);
            mVar.c = false;
            new cn(this.a, mVar).a();
        } catch (Exception unused) {
        }
    }

    public final void a(m mVar) {
        try {
            new cn(this.a, mVar).a();
        } catch (Exception e) {
            a(e);
        }
    }

    /* Access modifiers changed, original: protected|final */
    /* JADX WARNING: Missing block: B:11:0x0022, code skipped:
            return false;
     */
    public final boolean d() {
        /*
        r2 = this;
        r0 = r2.d;
        r1 = 0;
        if (r0 == 0) goto L_0x0022;
    L_0x0005:
        r0 = r2.a;
        if (r0 != 0) goto L_0x000a;
    L_0x0009:
        goto L_0x0022;
    L_0x000a:
        r0 = r2.a;
        r0 = r0.getAdRequests();
        if (r0 == 0) goto L_0x0021;
    L_0x0012:
        r0 = r2.a;
        r0 = r0.getAdRequests();
        r0 = r0.size();
        if (r0 != 0) goto L_0x001f;
    L_0x001e:
        goto L_0x0021;
    L_0x001f:
        r0 = 1;
        return r0;
    L_0x0021:
        return r1;
    L_0x0022:
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.til.colombia.android.service.cl.d():boolean");
    }

    public final boolean e() {
        boolean z = (this.d == null || this.a == null || this.a.getAdRequests() == null || this.a.getAdRequests().size() == 0) ? false : true;
        if (z && a.t()) {
            if (this.a != null) {
                for (AdRequestResponse adRequestResponse : this.a.getAdRequests()) {
                    if (!(this.a.getAdManager() == null || this.a.getAdManager().getAdUtil() == null)) {
                        this.a.getAdManager().getAdUtil().putReqCode(adRequestResponse.getRequestCode());
                    }
                }
            }
            return a();
        }
        a(new Exception(ErrorCode.INVALID_REQUEST.toString()));
        return false;
    }
}
