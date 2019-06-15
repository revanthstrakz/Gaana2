package com.til.colombia.android.adapters;

import android.os.Handler;
import android.os.Looper;
import com.facebook.ads.NativeAd;
import com.facebook.ads.NativeAdsManager;
import com.facebook.ads.NativeAdsManager.Listener;
import com.til.colombia.android.internal.a;
import com.til.colombia.android.internal.a.h;
import com.til.colombia.android.service.AdListener;
import com.til.colombia.android.service.AdRequestParams;
import com.til.colombia.android.service.ColombiaAdRequest;
import com.til.colombia.android.service.ItemResponse;
import com.til.colombia.android.service.bl;

public class FbAdsAdapter extends a implements Listener {
    private AdListener adListener;
    private int count = 0;
    private FbNativeAd fbNativeAd;
    private String imprUrl;
    private NativeAdsManager manager;
    private ColombiaAdRequest request;
    private String requestCode;

    /* JADX WARNING: Missing exception handler attribute for start block: B:8:0x002f */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing block: B:9:?, code skipped:
            onItemFailedOnMainThread(r6, r3);
     */
    /* JADX WARNING: Missing block: B:10:0x0032, code skipped:
            return;
     */
    public void requestAd(com.til.colombia.android.service.bl r3, java.lang.String r4, java.lang.String r5, com.til.colombia.android.service.AdListener r6) {
        /*
        r2 = this;
        r0 = "Col:aos:4.0.0";
        r1 = "Fb ad request";
        com.til.colombia.android.internal.Log.a(r0, r1);
        r2.adListener = r6;
        r0 = r3;
        r0 = (com.til.colombia.android.service.ColombiaAdRequest) r0;
        r2.request = r0;
        r2.requestCode = r4;
        r2.imprUrl = r5;
        r0 = com.til.colombia.android.internal.a.G();
        r1 = com.til.colombia.android.internal.a.h.a(r0);
        if (r1 == 0) goto L_0x0033;
    L_0x001c:
        com.til.colombia.android.internal.a.j();
        r0 = "com.til.colombia.android.adapters.GoogleAdsAdapter";
        r0 = com.til.colombia.android.adapters.a.getInstance(r0);	 Catch:{ Throwable -> 0x002f }
        r4 = r0.returnCacheAd(r3, r4, r5, r6);	 Catch:{ Throwable -> 0x002f }
        if (r4 != 0) goto L_0x002e;
    L_0x002b:
        r2.onItemFailedOnMainThread(r6, r3);	 Catch:{ Throwable -> 0x002f }
    L_0x002e:
        return;
    L_0x002f:
        r2.onItemFailedOnMainThread(r6, r3);	 Catch:{ all -> 0x0032 }
    L_0x0032:
        return;
    L_0x0033:
        r3 = r2.manager;
        if (r3 != 0) goto L_0x0051;
    L_0x0037:
        r3 = new com.facebook.ads.NativeAdsManager;
        r4 = com.til.colombia.android.internal.a.a();
        r5 = com.til.colombia.android.internal.a.I();
        r3.<init>(r4, r0, r5);
        r2.manager = r3;
        r3 = r2.manager;
        r3.setListener(r2);
        r3 = r2.manager;
        r3.loadAds();
        return;
    L_0x0051:
        r2.dispatchAd();
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.til.colombia.android.adapters.FbAdsAdapter.requestAd(com.til.colombia.android.service.bl, java.lang.String, java.lang.String, com.til.colombia.android.service.AdListener):void");
    }

    public void onAdsLoaded() {
        if (this.adListener != null) {
            dispatchAd();
        }
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x003a */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Can't wrap try/catch for region: R(3:7|8|9) */
    /* JADX WARNING: Missing block: B:8:?, code skipped:
            onItemFailedOnMainThread(r4.adListener, r4.request);
     */
    /* JADX WARNING: Missing block: B:9:0x0041, code skipped:
            return;
     */
    public void onAdError(com.facebook.ads.AdError r5) {
        /*
        r4 = this;
        r0 = "Col:aos:4.0.0";
        r1 = new java.lang.StringBuilder;
        r2 = "Fb ad error ";
        r1.<init>(r2);
        r5 = r5.getErrorCode();
        r1.append(r5);
        r5 = r1.toString();
        com.til.colombia.android.internal.Log.a(r0, r5);
        com.til.colombia.android.internal.a.j();
        r5 = r4.adListener;
        if (r5 == 0) goto L_0x0042;
    L_0x001e:
        r5 = "com.til.colombia.android.adapters.GoogleAdsAdapter";
        r5 = com.til.colombia.android.adapters.a.getInstance(r5);	 Catch:{ Throwable -> 0x003a }
        r0 = r4.request;	 Catch:{ Throwable -> 0x003a }
        r1 = r4.requestCode;	 Catch:{ Throwable -> 0x003a }
        r2 = r4.imprUrl;	 Catch:{ Throwable -> 0x003a }
        r3 = r4.adListener;	 Catch:{ Throwable -> 0x003a }
        r5 = r5.returnCacheAd(r0, r1, r2, r3);	 Catch:{ Throwable -> 0x003a }
        if (r5 != 0) goto L_0x0039;
    L_0x0032:
        r5 = r4.adListener;	 Catch:{ Throwable -> 0x003a }
        r0 = r4.request;	 Catch:{ Throwable -> 0x003a }
        r4.onItemFailedOnMainThread(r5, r0);	 Catch:{ Throwable -> 0x003a }
    L_0x0039:
        return;
    L_0x003a:
        r5 = r4.adListener;	 Catch:{ all -> 0x0041 }
        r0 = r4.request;	 Catch:{ all -> 0x0041 }
        r4.onItemFailedOnMainThread(r5, r0);	 Catch:{ all -> 0x0041 }
    L_0x0041:
        return;
    L_0x0042:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.til.colombia.android.adapters.FbAdsAdapter.onAdError(com.facebook.ads.AdError):void");
    }

    private void dispatchAd() {
        NativeAd nextNativeAd = this.manager.nextNativeAd();
        this.count++;
        if (nextNativeAd == null || this.count > a.I()) {
            this.count = 0;
            this.manager = new NativeAdsManager(a.a(), a.G(), a.I());
            this.manager.setListener(this);
            this.manager.loadAds();
            return;
        }
        nextNativeAd.setMediaViewAutoplay(false);
        this.fbNativeAd = new FbNativeAd(nextNativeAd);
        dispatchFbAd(this.fbNativeAd);
    }

    private void dispatchFbAd(FbNativeAd fbNativeAd) {
        AdRequestParams adRequestParams = new AdRequestParams();
        adRequestParams.setAdManager(this.request.getAdManager());
        ItemResponse itemResponse = new ItemResponse(adRequestParams, this.imprUrl);
        itemResponse.setPaidItems(fbNativeAd);
        itemResponse.setRequestCode(this.requestCode);
        fbNativeAd.setItemResponse(itemResponse);
        onItemLoadedOnMainThread(this.adListener, this.request, itemResponse);
    }

    public void createCache() {
        String G = a.G();
        if (h.a(G)) {
            a.j();
            return;
        }
        if (this.manager == null) {
            this.manager = new NativeAdsManager(a.a(), G, a.I());
            this.manager.loadAds();
        }
    }

    public boolean returnCacheAd(bl blVar, String str, String str2, AdListener adListener) {
        this.request = (ColombiaAdRequest) blVar;
        this.adListener = adListener;
        this.requestCode = str;
        this.imprUrl = str2;
        if (this.manager != null) {
            NativeAd nextNativeAd = this.manager.nextNativeAd();
            if (nextNativeAd != null) {
                nextNativeAd.setMediaViewAutoplay(false);
                this.fbNativeAd = new FbNativeAd(nextNativeAd);
                dispatchFbAd(this.fbNativeAd);
                return true;
            }
        }
        return false;
    }

    private void onItemLoadedOnMainThread(AdListener adListener, bl blVar, ItemResponse itemResponse) {
        new Handler(Looper.getMainLooper()).post(new b(this, adListener, blVar, itemResponse));
    }

    private void onItemFailedOnMainThread(AdListener adListener, bl blVar) {
        new Handler(Looper.getMainLooper()).post(new c(this, adListener, blVar));
    }
}
