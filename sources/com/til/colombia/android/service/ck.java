package com.til.colombia.android.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import com.gaana.cardoption.AssetsHelper.CARD;
import com.til.colombia.android.commons.USER_ACTION;
import com.til.colombia.android.internal.Log;
import com.til.colombia.android.internal.a.h;
import com.til.colombia.android.internal.i;
import com.til.colombia.android.service.ColombiaAdManager.ITEM_TYPE;

public final class ck extends BroadcastReceiver {
    public static String a = "com.til.colombia.android.interstitial.displayed";
    public static String b = "com.til.colombia.android.interstitial.clicked.pre";
    public static String c = "com.til.colombia.android.interstitial.dismissed";
    public static String d = "com.til.colombia.android.interstitial.completed.media";
    public static String e = "com.til.colombia.android.interstitial.error";
    public static String f = "com.til.colombia.android.interstitial.skipEnabled";
    Context g;
    String h;
    private Item i;
    private AdListener j;

    public ck(Context context, String str, Item item, AdListener adListener) {
        this.g = context;
        this.h = str;
        this.i = item;
        this.j = adListener;
    }

    public final void onReceive(Context context, Intent intent) {
        Object obj = intent.getAction().split(":")[0];
        if (!(this.j == null || obj == null)) {
            if (b.equals(obj)) {
                this.j.onMediaItemClicked(this.i);
            } else if (c.equals(obj)) {
                String stringExtra = intent.getStringExtra("USER_ACTION");
                if (h.a(stringExtra)) {
                    stringExtra = CARD.UNKNOWN;
                }
                this.j.onMediaItemClosed(this.i, (USER_ACTION) Enum.valueOf(USER_ACTION.class, stringExtra));
            } else if (a.equals(obj)) {
                this.j.onMediaItemDisplayed(this.i);
            } else if (d.equals(obj)) {
                try {
                    if (this.i.getItemType() != ITEM_TYPE.VIDEO_INCENTIVE) {
                        this.j.onMediaItemCompleted(this.i, 0);
                    } else {
                        this.j.onMediaItemCompleted(this.i, ((NativeItem) this.i).getVastHelper().getSponsoredAdConfig().getAdFreeDuration());
                    }
                } catch (Exception unused) {
                    this.j.onMediaItemCompleted(this.i, 0);
                }
            } else if (e.equals(obj)) {
                this.j.onMediaItemError(this.i, new Exception(intent.getStringExtra("ERROR")));
            } else if (f.equalsIgnoreCase(obj)) {
                this.j.onMediaItemSkipEnabled(this.i);
            }
        }
    }

    public final void a() {
        IntentFilter intentFilter = new IntentFilter();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(a);
        stringBuilder.append(":");
        stringBuilder.append(this.h);
        intentFilter.addAction(stringBuilder.toString());
        stringBuilder = new StringBuilder();
        stringBuilder.append(b);
        stringBuilder.append(":");
        stringBuilder.append(this.h);
        intentFilter.addAction(stringBuilder.toString());
        stringBuilder = new StringBuilder();
        stringBuilder.append(c);
        stringBuilder.append(":");
        stringBuilder.append(this.h);
        intentFilter.addAction(stringBuilder.toString());
        stringBuilder = new StringBuilder();
        stringBuilder.append(d);
        stringBuilder.append(":");
        stringBuilder.append(this.h);
        intentFilter.addAction(stringBuilder.toString());
        stringBuilder = new StringBuilder();
        stringBuilder.append(e);
        stringBuilder.append(":");
        stringBuilder.append(this.h);
        intentFilter.addAction(stringBuilder.toString());
        stringBuilder = new StringBuilder();
        stringBuilder.append(f);
        stringBuilder.append(":");
        stringBuilder.append(this.h);
        intentFilter.addAction(stringBuilder.toString());
        LocalBroadcastManager.getInstance(this.g).registerReceiver(this, intentFilter);
    }

    public final void b() {
        try {
            LocalBroadcastManager.getInstance(this.g).unregisterReceiver(this);
        } catch (Exception e) {
            Log.a(i.f, "", e);
        }
    }
}
