package com.facebook.ads.internal.adapters;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import com.facebook.ads.internal.view.g.a.a;
import com.facebook.ads.internal.view.g.b.b;
import com.facebook.ads.internal.view.g.b.f;
import com.facebook.ads.internal.view.g.b.g;
import com.facebook.ads.internal.view.g.b.h;
import com.facebook.ads.internal.view.g.b.p;
import com.facebook.ads.internal.view.j;
import java.io.Serializable;

public class w extends BroadcastReceiver {
    private Context a;
    private j b;
    private boolean c = false;

    public w(j jVar, Context context) {
        this.b = jVar;
        this.a = context.getApplicationContext();
    }

    public void a() {
        IntentFilter intentFilter = new IntentFilter();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("com.facebook.ads.interstitial.displayed:");
        stringBuilder.append(this.b.getUniqueId());
        intentFilter.addAction(stringBuilder.toString());
        stringBuilder = new StringBuilder();
        stringBuilder.append("videoInterstitalEvent:");
        stringBuilder.append(this.b.getUniqueId());
        intentFilter.addAction(stringBuilder.toString());
        stringBuilder = new StringBuilder();
        stringBuilder.append("performCtaClick:");
        stringBuilder.append(this.b.getUniqueId());
        intentFilter.addAction(stringBuilder.toString());
        LocalBroadcastManager.getInstance(this.a).registerReceiver(this, intentFilter);
    }

    public void b() {
        try {
            LocalBroadcastManager.getInstance(this.a).unregisterReceiver(this);
        } catch (Exception unused) {
        }
    }

    public void onReceive(Context context, Intent intent) {
        String[] split = intent.getAction().split(":");
        if (split.length == 2 && split[1].equals(this.b.getUniqueId())) {
            if (split[0].equals("com.facebook.ads.interstitial.displayed")) {
                if (this.b.getListener() != null) {
                    this.b.getListener().g();
                    this.b.getListener().a();
                }
            } else if (split[0].equals("videoInterstitalEvent")) {
                Serializable serializableExtra = intent.getSerializableExtra("event");
                if (serializableExtra instanceof p) {
                    if (this.b.getListener() != null) {
                        this.b.getListener().f();
                        this.b.getListener().a();
                    }
                    if (this.c) {
                        this.b.a(1);
                    } else {
                        this.b.a(((p) serializableExtra).b());
                    }
                    this.b.setVisibility(0);
                    this.b.a(a.USER_STARTED);
                } else if (serializableExtra instanceof f) {
                    if (this.b.getListener() != null) {
                        this.b.getListener().d();
                    }
                } else if (serializableExtra instanceof g) {
                    if (this.b.getListener() != null) {
                        this.b.getListener().e();
                    }
                } else if (serializableExtra instanceof b) {
                    if (this.b.getListener() != null) {
                        this.b.getListener().h();
                    }
                    this.c = true;
                } else if (serializableExtra instanceof com.facebook.ads.internal.view.g.b.j) {
                    if (this.b.getListener() != null) {
                        this.b.getListener().c();
                    }
                    this.c = false;
                } else if ((serializableExtra instanceof h) && this.b.getListener() != null) {
                    this.b.getListener().b();
                }
            } else if (split[0].equals("performCtaClick")) {
                this.b.b();
            }
        }
    }
}
