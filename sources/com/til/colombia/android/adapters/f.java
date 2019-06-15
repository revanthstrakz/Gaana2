package com.til.colombia.android.adapters;

import com.google.android.gms.ads.AdListener;
import com.til.colombia.android.service.bl;

final class f extends AdListener {
    final /* synthetic */ bl a;
    final /* synthetic */ String b;
    final /* synthetic */ String c;
    final /* synthetic */ com.til.colombia.android.service.AdListener d;
    final /* synthetic */ GoogleAdsAdapter e;

    f(GoogleAdsAdapter googleAdsAdapter, bl blVar, String str, String str2, com.til.colombia.android.service.AdListener adListener) {
        this.e = googleAdsAdapter;
        this.a = blVar;
        this.b = str;
        this.c = str2;
        this.d = adListener;
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:6:0x0021 */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing block: B:7:?, code skipped:
            r4.e.onItemFailedOnMainThread(r4.d, r4.a);
     */
    /* JADX WARNING: Missing block: B:8:0x002a, code skipped:
            return;
     */
    public final void onAdFailedToLoad(int r5) {
        /*
        r4 = this;
        com.til.colombia.android.internal.a.k();
        r5 = "com.til.colombia.android.adapters.FbAdsAdapter";
        r5 = com.til.colombia.android.adapters.a.getInstance(r5);	 Catch:{ Throwable -> 0x0021 }
        r0 = r4.a;	 Catch:{ Throwable -> 0x0021 }
        r1 = r4.b;	 Catch:{ Throwable -> 0x0021 }
        r2 = r4.c;	 Catch:{ Throwable -> 0x0021 }
        r3 = r4.d;	 Catch:{ Throwable -> 0x0021 }
        r5 = r5.returnCacheAd(r0, r1, r2, r3);	 Catch:{ Throwable -> 0x0021 }
        if (r5 != 0) goto L_0x0020;
    L_0x0017:
        r5 = r4.e;	 Catch:{ Throwable -> 0x0021 }
        r0 = r4.d;	 Catch:{ Throwable -> 0x0021 }
        r1 = r4.a;	 Catch:{ Throwable -> 0x0021 }
        r5.onItemFailedOnMainThread(r0, r1);	 Catch:{ Throwable -> 0x0021 }
    L_0x0020:
        return;
    L_0x0021:
        r5 = r4.e;	 Catch:{ all -> 0x002a }
        r0 = r4.d;	 Catch:{ all -> 0x002a }
        r1 = r4.a;	 Catch:{ all -> 0x002a }
        r5.onItemFailedOnMainThread(r0, r1);	 Catch:{ all -> 0x002a }
    L_0x002a:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.til.colombia.android.adapters.f.onAdFailedToLoad(int):void");
    }
}
