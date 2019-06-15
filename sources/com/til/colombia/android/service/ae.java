package com.til.colombia.android.service;

import com.android.volley.VolleyError;
import com.android.volley.i.a;
import com.til.colombia.android.commons.CmError;

final class ae implements a {
    final /* synthetic */ com.til.colombia.android.service.listener.a a;
    final /* synthetic */ CmManager b;

    ae(CmManager cmManager, com.til.colombia.android.service.listener.a aVar) {
        this.b = cmManager;
        this.a = aVar;
    }

    public final void onErrorResponse(VolleyError volleyError) {
        if (this.a != null) {
            this.a.onFeedFailed(CmError.NETWORK_ERROR);
        }
    }
}
