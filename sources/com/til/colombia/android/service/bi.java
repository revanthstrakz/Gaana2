package com.til.colombia.android.service;

import android.util.Log;
import com.til.colombia.android.internal.a;
import com.til.colombia.android.internal.i;
import com.til.colombia.android.network.n;
import com.til.colombia.android.service.ColombiaAdManager.ITEM_TYPE;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class bi {
    private static final bi b = new bi();
    ExecutorService a = Executors.newFixedThreadPool(4);

    private bi() {
    }

    public static bi a() {
        return b;
    }

    static void a(Item item, boolean z) {
        if (a.t() && item.thirdPartyAd() == null) {
            Log.i(i.f, "performing click.");
            if (item.getItemType() == ITEM_TYPE.VIDEO || item.getItemType() == ITEM_TYPE.INTERSTITIAL_VIDEO) {
                n.a(((NativeItem) item).getVastClickTrackers(), 10, "vast click trackers tracked.");
            }
            if (z) {
                NativeItem nativeItem = (NativeItem) item;
                nativeItem.registerItemClick();
                nativeItem.performClick();
                return;
            }
            bm.a(item);
            ((NativeItem) item).performClick();
        }
    }

    static void a(Item item) {
        if (a.t()) {
            ((NativeItem) item).registerItemClick();
        }
    }

    /* Access modifiers changed, original: final */
    public final void a(ColombiaAdRequest colombiaAdRequest) {
        cl clVar = new cl(this.a, colombiaAdRequest);
        clVar.e();
        String str = i.f;
        StringBuilder stringBuilder = new StringBuilder("requesting ad.");
        stringBuilder.append(clVar.toString());
        Log.i(str, stringBuilder.toString());
    }

    /* Access modifiers changed, original: final|declared_synchronized */
    /* JADX WARNING: Missing block: B:31:0x0059, code skipped:
            return false;
     */
    /* JADX WARNING: Missing block: B:36:0x005e, code skipped:
            return false;
     */
    public final synchronized boolean a(com.til.colombia.android.service.ItemResponse r4, com.til.colombia.android.service.Item r5, android.view.View r6) {
        /*
        r3 = this;
        monitor-enter(r3);
        r0 = 0;
        if (r4 == 0) goto L_0x005d;
    L_0x0004:
        if (r6 != 0) goto L_0x0007;
    L_0x0006:
        goto L_0x005d;
    L_0x0007:
        r1 = r5.isImpressed();	 Catch:{ all -> 0x005a }
        r2 = 1;
        if (r1 == 0) goto L_0x0010;
    L_0x000e:
        monitor-exit(r3);
        return r2;
    L_0x0010:
        r1 = r4.getAdManager();	 Catch:{ all -> 0x005a }
        if (r1 == 0) goto L_0x0038;
    L_0x0016:
        r1 = r4.getAdManager();	 Catch:{ all -> 0x005a }
        r1 = r1.getActivityContext();	 Catch:{ all -> 0x005a }
        if (r1 == 0) goto L_0x0038;
    L_0x0020:
        r1 = r4.getAdManager();	 Catch:{ all -> 0x005a }
        r1 = r1.getImpressionTracker();	 Catch:{ all -> 0x005a }
        if (r1 == 0) goto L_0x0038;
    L_0x002a:
        r0 = com.til.colombia.android.network.l.a();	 Catch:{ all -> 0x005a }
        r1 = new com.til.colombia.android.service.bj;	 Catch:{ all -> 0x005a }
        r1.<init>(r3, r4, r6, r5);	 Catch:{ all -> 0x005a }
        r0.a(r1, r2);	 Catch:{ all -> 0x005a }
        monitor-exit(r3);
        return r2;
    L_0x0038:
        r5 = r4.getAdManager();	 Catch:{ all -> 0x005a }
        if (r5 != 0) goto L_0x0047;
    L_0x003e:
        r4 = "Col:aos:4.0.0";
        r5 = "recordAdImpression failed : AdManager is null";
        android.util.Log.i(r4, r5);	 Catch:{ all -> 0x005a }
        monitor-exit(r3);
        return r0;
    L_0x0047:
        r4 = r4.getAdManager();	 Catch:{ all -> 0x005a }
        r4 = r4.getActivityContext();	 Catch:{ all -> 0x005a }
        if (r4 != 0) goto L_0x0058;
    L_0x0051:
        r4 = "Col:aos:4.0.0";
        r5 = "recordAdImpression failed : Invalid ColombiaAdManager. ColombiaAdManager must be registered in current activity.";
        android.util.Log.i(r4, r5);	 Catch:{ all -> 0x005a }
    L_0x0058:
        monitor-exit(r3);
        return r0;
    L_0x005a:
        r4 = move-exception;
        monitor-exit(r3);
        throw r4;
    L_0x005d:
        monitor-exit(r3);
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.til.colombia.android.service.bi.a(com.til.colombia.android.service.ItemResponse, com.til.colombia.android.service.Item, android.view.View):boolean");
    }

    static void a(CmItem cmItem) {
        if (cmItem != null) {
            NativeItem nativeItem = (NativeItem) cmItem;
            if (nativeItem.getItemResponse() != null) {
                ItemResponse itemResponse = nativeItem.getItemResponse();
                if (!itemResponse.isImpressed()) {
                    itemResponse.recordItemResponseImpression();
                }
            }
        }
    }

    /* JADX WARNING: Missing block: B:18:0x0022, code skipped:
            return;
     */
    public final synchronized void b(com.til.colombia.android.service.Item r2) {
        /*
        r1 = this;
        monitor-enter(r1);
        r0 = r2 instanceof com.til.colombia.android.service.NativeItem;	 Catch:{ all -> 0x0023 }
        if (r0 == 0) goto L_0x000d;
    L_0x0005:
        r2 = (com.til.colombia.android.service.NativeItem) r2;	 Catch:{ all -> 0x0023 }
        r0 = 0;
        r2.recordImpression(r0);	 Catch:{ all -> 0x0023 }
        monitor-exit(r1);
        return;
    L_0x000d:
        r0 = r2 instanceof com.til.colombia.android.adapters.FbNativeAd;	 Catch:{ all -> 0x0023 }
        if (r0 == 0) goto L_0x0018;
    L_0x0011:
        r2 = (com.til.colombia.android.adapters.FbNativeAd) r2;	 Catch:{ all -> 0x0023 }
        r2.recordImpression();	 Catch:{ all -> 0x0023 }
        monitor-exit(r1);
        return;
    L_0x0018:
        r0 = r2 instanceof com.til.colombia.android.adapters.GoogleNativeAd;	 Catch:{ all -> 0x0023 }
        if (r0 == 0) goto L_0x0021;
    L_0x001c:
        r2 = (com.til.colombia.android.adapters.GoogleNativeAd) r2;	 Catch:{ all -> 0x0023 }
        r2.recordImpression();	 Catch:{ all -> 0x0023 }
    L_0x0021:
        monitor-exit(r1);
        return;
    L_0x0023:
        r2 = move-exception;
        monitor-exit(r1);
        throw r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.til.colombia.android.service.bi.b(com.til.colombia.android.service.Item):void");
    }
}
