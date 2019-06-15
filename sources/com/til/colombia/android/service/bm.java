package com.til.colombia.android.service;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.widget.Toast;
import com.google.android.exoplayer2.C;
import com.til.colombia.android.internal.Log;
import com.til.colombia.android.internal.a;
import com.til.colombia.android.internal.a.h;
import com.til.colombia.android.internal.e;
import com.til.colombia.android.internal.i;
import com.til.colombia.android.network.n;
import com.til.colombia.android.service.ColombiaAdManager.ITEM_TYPE;
import com.til.colombia.android.utils.c;
import com.til.colombia.android.vast.VastSponsoredAdConfig;

final class bm {
    public static final String a = "snippet";
    public static final String b = "itemId";
    public static final String c = "lId";

    bm() {
    }

    public static void a(Item item) {
        if (item.getItemType() == ITEM_TYPE.LEADGEN) {
            NativeItem nativeItem = (NativeItem) item;
            nativeItem.registerItemClick();
            if (!a.b(a.a())) {
                Toast.makeText(a.a(), "Network is not available", 0).show();
            } else if (c.c(a.a(), c.a, nativeItem.getItemId())) {
                Toast.makeText(a.a(), "Your query is submitted", 0).show();
            } else {
                String snippet = nativeItem.getSnippet();
                if (snippet != null) {
                    Intent intent = new Intent(a.a(), LeadGenActivity.class);
                    intent.addFlags(C.ENCODING_PCM_MU_LAW);
                    intent.putExtra(a, snippet);
                    intent.putExtra(b, nativeItem.getItemId());
                    intent.putExtra(c, item.getLineItemId());
                    intent.putExtra(e.ab, item);
                    a.a().startActivity(intent);
                }
            }
        } else if (item.getItemType() == ITEM_TYPE.VIDEO_INCENTIVE) {
            c(item);
        } else {
            String deepLink = item.getDeepLink();
            Context a = a.a();
            if (!h.a(deepLink)) {
                try {
                    n.a(a, Uri.parse(deepLink));
                    ((NativeItem) item).registerItemClick();
                    return;
                } catch (Exception unused) {
                }
            }
            a(a, item);
        }
    }

    private static void a(Context context, Item item) {
        try {
            if (item.getItemType() == ITEM_TYPE.VIDEO || item.getItemType() == ITEM_TYPE.INTERSTITIAL_VIDEO) {
                ((NativeItem) item).registerItemClick();
            }
            n.a(context, Uri.parse(((NativeItem) item).getUrl()));
        } catch (Exception e) {
            Log.a(i.f, "", e);
        }
    }

    private static void d(Item item) {
        if (a.b(a.a())) {
            NativeItem nativeItem = (NativeItem) item;
            if (c.c(a.a(), c.a, nativeItem.getItemId())) {
                Toast.makeText(a.a(), "Your query is submitted", 0).show();
                return;
            }
            String snippet = nativeItem.getSnippet();
            if (snippet != null) {
                Intent intent = new Intent(a.a(), LeadGenActivity.class);
                intent.addFlags(C.ENCODING_PCM_MU_LAW);
                intent.putExtra(a, snippet);
                intent.putExtra(b, nativeItem.getItemId());
                intent.putExtra(c, item.getLineItemId());
                intent.putExtra(e.ab, item);
                a.a().startActivity(intent);
            }
            return;
        }
        Toast.makeText(a.a(), "Network is not available", 0).show();
    }

    protected static boolean b(Item item) {
        if (item != null) {
            NativeItem nativeItem = (NativeItem) item;
            if (nativeItem.getVastHelper() != null) {
                VastSponsoredAdConfig sponsoredAdConfig = nativeItem.getVastHelper().getSponsoredAdConfig();
                if (sponsoredAdConfig == null || !sponsoredAdConfig.isPreConfigPresent() || nativeItem.getAdManager() == null) {
                    a(a.a(), ck.e, null, item.getUID());
                    return false;
                }
                Activity activity = (Activity) nativeItem.getAdManager().getActivityContext();
                Intent intent = new Intent(activity, InterstitialActivity.class);
                intent.putExtra(e.ab, item);
                intent.putExtra(e.ac, nativeItem.getItemResponse());
                activity.startActivity(intent);
                return true;
            }
        }
        return false;
    }

    protected static boolean c(Item item) {
        if (item != null) {
            NativeItem nativeItem = (NativeItem) item;
            if (nativeItem.getVastHelper() != null) {
                if (nativeItem.getAdManager() == null) {
                    Log.a(i.f, "ColombiaAdManager is null.");
                    a(a.a(), ck.e, null, item.getUID());
                    return false;
                }
                Activity activity = (Activity) nativeItem.getAdManager().getActivityContext();
                Intent intent = new Intent(activity, InterstitialActivity.class);
                intent.putExtra(e.ab, item);
                intent.putExtra(e.ac, nativeItem.getItemResponse());
                intent.putExtra("skipPreCompanion", new Boolean(true));
                activity.startActivity(intent);
                return true;
            }
        }
        return false;
    }

    private static void a(Context context, String str, Bundle bundle, String str2) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        stringBuilder.append(":");
        stringBuilder.append(str2);
        LocalBroadcastManager.getInstance(context).sendBroadcastSync(new Intent(stringBuilder.toString()));
    }
}
