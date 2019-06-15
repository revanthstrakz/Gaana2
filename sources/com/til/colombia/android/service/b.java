package com.til.colombia.android.service;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.til.colombia.android.internal.i;
import com.til.colombia.android.service.ColombiaAdManager.ITEM_TYPE;
import com.til.colombia.android.utils.a;
import com.til.colombia.android.utils.a.c;
import java.util.ArrayList;

public final class b implements c {
    bl a;
    AdListener b;
    ItemResponse c;

    private b(bl blVar, AdListener adListener, ItemResponse itemResponse) {
        this.b = adListener;
        this.a = blVar;
        this.c = itemResponse;
    }

    public final void a() {
        if (this.c != null) {
            new Handler(Looper.getMainLooper()).post(new c(this));
        } else {
            a(this.a, this.b, new Exception("response is null."));
        }
    }

    private void a(bl blVar, ItemResponse itemResponse) {
        a aVar = new a();
        ArrayList<Item> arrayList = new ArrayList();
        if (itemResponse.getPaidItems() != null) {
            arrayList.addAll(itemResponse.getPaidItems());
        }
        if (itemResponse.getOrganicItems() != null) {
            arrayList.addAll(itemResponse.getOrganicItems());
        }
        if (arrayList.size() > 0) {
            if (blVar.downloadIcon()) {
                for (Item item : arrayList) {
                    if (item.getIconUrl() != null) {
                        aVar.a(new d(this, item), item.getIconUrl());
                    }
                }
            }
            if (blVar.downloadImage() || !(itemResponse.getPaidItems() == null || itemResponse.getPaidItems().get(0) == null || ((Item) itemResponse.getPaidItems().get(0)).getItemType() != ITEM_TYPE.GENERAL)) {
                for (Item item2 : arrayList) {
                    if (item2.getImageUrl() != null) {
                        aVar.a(new e(this, item2), item2.getImageUrl());
                    }
                }
            }
            aVar.b = this;
            try {
                aVar.a();
            } catch (Exception e) {
                String str = i.f;
                StringBuilder stringBuilder = new StringBuilder("is-error:");
                stringBuilder.append(e);
                Log.e(str, stringBuilder.toString());
            }
        }
    }

    private void a(bl blVar, Exception exception) {
        a(blVar, this.b, exception);
    }

    public static void a(bl blVar, AdListener adListener, Exception exception) {
        new Handler(Looper.getMainLooper()).post(new f(adListener, blVar, exception));
    }

    public static void a(ColombiaAdRequest colombiaAdRequest, AdListener adListener, ItemResponse itemResponse) {
        if (itemResponse == null) {
            a((bl) colombiaAdRequest, adListener, new Exception("response is null"));
        } else if (itemResponse.isCarousel() || !(colombiaAdRequest.downloadIcon() || colombiaAdRequest.downloadImage())) {
            new Handler(Looper.getMainLooper()).post(new g(adListener, colombiaAdRequest, itemResponse));
        } else {
            new b(colombiaAdRequest, adListener, itemResponse).a((bl) colombiaAdRequest, itemResponse);
        }
    }
}
