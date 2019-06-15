package com.til.colombia.android.adapters;

import com.til.colombia.android.service.AdListener;
import com.til.colombia.android.service.ColombiaAdRequest;
import com.til.colombia.android.service.bl;

final class k implements Runnable {
    final /* synthetic */ AdListener a;
    final /* synthetic */ bl b;
    final /* synthetic */ GoogleAdsAdapter c;

    k(GoogleAdsAdapter googleAdsAdapter, AdListener adListener, bl blVar) {
        this.c = googleAdsAdapter;
        this.a = adListener;
        this.b = blVar;
    }

    public final void run() {
        try {
            if (this.a != null) {
                this.a.onItemRequestFailed((ColombiaAdRequest) this.b, new Exception("failed with errorCode : empty google ad code"));
            }
        } catch (Throwable unused) {
        }
    }
}
