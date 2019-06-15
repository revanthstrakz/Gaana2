package com.til.colombia.android.adapters;

import com.til.colombia.android.service.AdListener;
import com.til.colombia.android.service.ColombiaAdRequest;
import com.til.colombia.android.service.ItemResponse;
import com.til.colombia.android.service.bl;

final class b implements Runnable {
    final /* synthetic */ AdListener a;
    final /* synthetic */ bl b;
    final /* synthetic */ ItemResponse c;
    final /* synthetic */ FbAdsAdapter d;

    b(FbAdsAdapter fbAdsAdapter, AdListener adListener, bl blVar, ItemResponse itemResponse) {
        this.d = fbAdsAdapter;
        this.a = adListener;
        this.b = blVar;
        this.c = itemResponse;
    }

    public final void run() {
        try {
            if (this.a != null) {
                this.a.onItemLoaded((ColombiaAdRequest) this.b, this.c);
            }
        } catch (Throwable unused) {
        }
    }
}
