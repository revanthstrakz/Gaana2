package com.til.colombia.android.adapters;

import com.til.colombia.android.service.AdListener;
import com.til.colombia.android.service.ColombiaAdRequest;
import com.til.colombia.android.service.bl;

final class c implements Runnable {
    final /* synthetic */ AdListener a;
    final /* synthetic */ bl b;
    final /* synthetic */ FbAdsAdapter c;

    c(FbAdsAdapter fbAdsAdapter, AdListener adListener, bl blVar) {
        this.c = fbAdsAdapter;
        this.a = adListener;
        this.b = blVar;
    }

    public final void run() {
        try {
            if (this.a != null) {
                this.a.onItemRequestFailed((ColombiaAdRequest) this.b, new Exception("failed with errorCode : empty fb ad code"));
            }
        } catch (Throwable unused) {
        }
    }
}
