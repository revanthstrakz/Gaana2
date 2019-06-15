package com.til.colombia.android.service;

import android.util.Log;
import com.til.colombia.android.adapters.a;
import com.til.colombia.android.internal.g;
import com.til.colombia.android.internal.i;
import com.til.colombia.android.network.MediationNetworkItem;

final class k implements Runnable {
    final /* synthetic */ MediationNetworkItem a;
    final /* synthetic */ bl b;
    final /* synthetic */ String c;
    final /* synthetic */ String d;
    final /* synthetic */ AdListener e;
    final /* synthetic */ AdRequestResponse f;

    k(AdRequestResponse adRequestResponse, MediationNetworkItem mediationNetworkItem, bl blVar, String str, String str2, AdListener adListener) {
        this.f = adRequestResponse;
        this.a = mediationNetworkItem;
        this.b = blVar;
        this.c = str;
        this.d = str2;
        this.e = adListener;
    }

    public final void run() {
        try {
            Log.i(i.f, "Fetching Ads from Mediation services.");
            String networkId = this.a.getNetworkId();
            Object obj = -1;
            int hashCode = networkId.hashCode();
            if (hashCode != 1574014) {
                if (hashCode == 46911111) {
                    if (networkId.equals(g.f)) {
                        obj = 1;
                    }
                }
            } else if (networkId.equals(g.e)) {
                obj = null;
            }
            switch (obj) {
                case null:
                    a.getInstance(g.c).requestAd(this.b, this.c, this.d, this.e);
                    break;
                case 1:
                    a.getInstance(g.d).requestAd(this.b, this.c, this.d, this.e);
                    break;
                default:
                    b.a(this.f.colombiaAdRequest, this.e, new Exception("Colombia failed to load ads."));
                    Log.e(i.f, "request failed to load Ads.");
                    return;
            }
        } catch (Throwable th) {
            b.a(this.f.colombiaAdRequest, this.e, new Exception("Colombia failed to load ads for Mediation."));
            Log.e(i.f, "", th);
        }
    }
}
