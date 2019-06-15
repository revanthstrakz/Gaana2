package com.til.colombia.android.adapters;

import android.os.Handler;
import android.os.Looper;
import com.google.android.gms.ads.AdLoader.Builder;
import com.google.android.gms.ads.doubleclick.PublisherAdRequest;
import com.google.android.gms.ads.formats.NativeAdOptions;
import com.til.colombia.android.internal.a;
import com.til.colombia.android.internal.a.h;
import com.til.colombia.android.service.AdListener;
import com.til.colombia.android.service.AdRequestParams;
import com.til.colombia.android.service.ColombiaAdRequest;
import com.til.colombia.android.service.ItemResponse;
import com.til.colombia.android.service.bl;

class GoogleAdsAdapter extends a {
    private AdListener adListener;
    private long cacheExpiryLimit = 1200;
    private long cacheTimeStamp;
    private GoogleNativeAd googleNativeAd;
    private String imprUrl;
    private ColombiaAdRequest request;
    private boolean requestCache = false;
    private String requestCode;

    /* JADX WARNING: Missing exception handler attribute for start block: B:8:0x002f */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing block: B:9:?, code skipped:
            onItemFailedOnMainThread(r13, r10);
     */
    /* JADX WARNING: Missing block: B:10:0x0032, code skipped:
            return;
     */
    public void requestAd(com.til.colombia.android.service.bl r10, java.lang.String r11, java.lang.String r12, com.til.colombia.android.service.AdListener r13) {
        /*
        r9 = this;
        r0 = "Col:aos:4.0.0";
        r1 = "Google ad request";
        com.til.colombia.android.internal.Log.a(r0, r1);
        r0 = r10;
        r0 = (com.til.colombia.android.service.ColombiaAdRequest) r0;
        r9.request = r0;
        r9.adListener = r13;
        r9.requestCode = r11;
        r9.imprUrl = r12;
        r0 = com.til.colombia.android.internal.a.D();
        r1 = com.til.colombia.android.internal.a.h.a(r0);
        if (r1 == 0) goto L_0x0033;
    L_0x001c:
        com.til.colombia.android.internal.a.k();
        r0 = "com.til.colombia.android.adapters.FbAdsAdapter";
        r0 = com.til.colombia.android.adapters.a.getInstance(r0);	 Catch:{ Throwable -> 0x002f }
        r11 = r0.returnCacheAd(r10, r11, r12, r13);	 Catch:{ Throwable -> 0x002f }
        if (r11 != 0) goto L_0x002e;
    L_0x002b:
        r9.onItemFailedOnMainThread(r13, r10);	 Catch:{ Throwable -> 0x002f }
    L_0x002e:
        return;
    L_0x002f:
        r9.onItemFailedOnMainThread(r13, r10);	 Catch:{ all -> 0x0032 }
    L_0x0032:
        return;
    L_0x0033:
        r1 = r9.returnCacheAd(r10, r11, r12, r13);
        if (r1 == 0) goto L_0x003a;
    L_0x0039:
        return;
    L_0x003a:
        r1 = new com.google.android.gms.ads.AdLoader$Builder;
        r2 = com.til.colombia.android.internal.a.a();
        r1.<init>(r2, r0);
        r0 = new com.til.colombia.android.adapters.d;
        r3 = r0;
        r4 = r9;
        r5 = r10;
        r6 = r12;
        r7 = r11;
        r8 = r13;
        r3.<init>(r4, r5, r6, r7, r8);
        r1.forContentAd(r0);
        r0 = new com.til.colombia.android.adapters.e;
        r2 = r0;
        r3 = r9;
        r4 = r10;
        r5 = r12;
        r6 = r11;
        r7 = r13;
        r2.<init>(r3, r4, r5, r6, r7);
        r1.forAppInstallAd(r0);
        r0 = new com.til.colombia.android.adapters.f;
        r2 = r0;
        r5 = r11;
        r6 = r12;
        r2.<init>(r3, r4, r5, r6, r7);
        r11 = r1.withAdListener(r0);
        r12 = new com.google.android.gms.ads.formats.NativeAdOptions$Builder;
        r12.<init>();
        r13 = com.til.colombia.android.internal.a.f();
        r12 = r12.setImageOrientation(r13);
        r13 = r10.downloadImage();
        r0 = 0;
        if (r13 != 0) goto L_0x0087;
    L_0x007f:
        r13 = r10.downloadIcon();
        if (r13 != 0) goto L_0x0087;
    L_0x0085:
        r13 = 1;
        goto L_0x0088;
    L_0x0087:
        r13 = r0;
    L_0x0088:
        r12 = r12.setReturnUrlsForImageAssets(r13);
        r12 = r12.setRequestMultipleImages(r0);
        r12 = r12.build();
        r11 = r11.withNativeAdOptions(r12);
        r11 = r11.build();
        r12 = new com.google.android.gms.ads.doubleclick.PublisherAdRequest$Builder;
        r12.<init>();
        if (r10 == 0) goto L_0x00d5;
    L_0x00a3:
        r13 = r10.getBirthDay();
        if (r13 == 0) goto L_0x00b0;
    L_0x00a9:
        r13 = r10.getBirthDay();
        r12.setBirthday(r13);
    L_0x00b0:
        r13 = r10.getLocation();
        if (r13 == 0) goto L_0x00bd;
    L_0x00b6:
        r13 = r10.getLocation();
        r12.setLocation(r13);
    L_0x00bd:
        r13 = r10.getReferer();
        if (r13 == 0) goto L_0x00ca;
    L_0x00c3:
        r13 = r10.getReferer();
        r12.setContentUrl(r13);
    L_0x00ca:
        r10 = r10.getGender();
        r10 = r10.ordinal();
        r12.setGender(r10);
    L_0x00d5:
        r10 = r12.build();
        r11.loadAd(r10);
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.til.colombia.android.adapters.GoogleAdsAdapter.requestAd(com.til.colombia.android.service.bl, java.lang.String, java.lang.String, com.til.colombia.android.service.AdListener):void");
    }

    public void createCache() {
        if (!this.requestCache) {
            this.requestCache = true;
            String D = a.D();
            if (h.a(D)) {
                a.k();
                return;
            }
            Builder builder = new Builder(a.a(), D);
            builder.forContentAd(new g(this));
            builder.forAppInstallAd(new h(this));
            builder.withAdListener(new i(this)).withNativeAdOptions(new NativeAdOptions.Builder().setImageOrientation(a.f()).setReturnUrlsForImageAssets(false).setRequestMultipleImages(false).build()).build().loadAd(new PublisherAdRequest.Builder().build());
        }
    }

    public boolean returnCacheAd(bl blVar, String str, String str2, AdListener adListener) {
        this.request = (ColombiaAdRequest) blVar;
        this.adListener = adListener;
        this.requestCode = str;
        this.imprUrl = str2;
        if (this.googleNativeAd == null || System.currentTimeMillis() / 1000 > this.cacheTimeStamp + this.cacheExpiryLimit) {
            return false;
        }
        GoogleNativeAd googleNativeAd = new GoogleNativeAd(this.googleNativeAd.thirdPartyAd(), this.googleNativeAd.getItemType());
        AdRequestParams adRequestParams = new AdRequestParams();
        adRequestParams.setAdManager(blVar.getAdManager());
        ItemResponse itemResponse = new ItemResponse(adRequestParams, str2);
        itemResponse.setPaidItems(googleNativeAd);
        itemResponse.setRequestCode(str);
        googleNativeAd.setItemResponse(itemResponse);
        onItemLoadedOnMainThread(adListener, blVar, itemResponse);
        this.googleNativeAd = null;
        createCache();
        return true;
    }

    private void onItemLoadedOnMainThread(AdListener adListener, bl blVar, ItemResponse itemResponse) {
        new Handler(Looper.getMainLooper()).post(new j(this, adListener, blVar, itemResponse));
    }

    private void onItemFailedOnMainThread(AdListener adListener, bl blVar) {
        new Handler(Looper.getMainLooper()).post(new k(this, adListener, blVar));
    }
}
