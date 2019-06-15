package com.simpl.android.zeroClickSdk.internal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class e extends BroadcastReceiver {
    private static final Pattern a = Pattern.compile("OTP is ([\\d]+)");
    private static final Pattern b = Pattern.compile("OTP: ([\\d]+)");
    private d c;

    public e(d dVar) {
        this.c = dVar;
    }

    public final void onReceive(Context context, Intent intent) {
        if (f.b().equals(intent.getAction())) {
            Bundle extras = intent.getExtras();
            int a = f.a(extras);
            if (a == f.c()) {
                String b = f.b(extras);
                Matcher matcher = a.matcher(b);
                if (matcher.find()) {
                    b = matcher.group(1);
                } else {
                    Matcher matcher2 = b.matcher(b);
                    b = matcher2.find() ? matcher2.group(1) : null;
                }
                if (!(this.c == null || b == null)) {
                    this.c.a(b);
                }
            } else if (a == f.d()) {
                this.c.a();
            }
        }
    }
}
