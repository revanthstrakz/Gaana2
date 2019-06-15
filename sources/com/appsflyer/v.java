package com.appsflyer;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.NonNull;
import com.facebook.internal.FacebookRequestErrorClassification;

final class v {
    private IntentFilter a = new IntentFilter("android.intent.action.BATTERY_CHANGED");

    static final class a {
        static final v a = new v();
    }

    static final class b {
        private final float a;
        private final String b;

        b(float f, String str) {
            this.a = f;
            this.b = str;
        }

        /* Access modifiers changed, original: final */
        public final float a() {
            return this.a;
        }

        /* Access modifiers changed, original: final */
        public final String b() {
            return this.b;
        }

        b() {
        }
    }

    v() {
    }

    /* Access modifiers changed, original: final */
    @NonNull
    public final b a(Context context) {
        String str = null;
        float f = 0.0f;
        try {
            Intent registerReceiver = context.registerReceiver(null, this.a);
            if (registerReceiver != null) {
                int intExtra;
                String str2;
                if ((2 == registerReceiver.getIntExtra("status", -1) ? 1 : null) != null) {
                    intExtra = registerReceiver.getIntExtra("plugged", -1);
                    if (intExtra != 4) {
                        switch (intExtra) {
                            case 1:
                                str2 = "ac";
                                break;
                            case 2:
                                str2 = "usb";
                                break;
                            default:
                                str2 = FacebookRequestErrorClassification.KEY_OTHER;
                                break;
                        }
                    }
                    str2 = "wireless";
                } else {
                    str2 = "no";
                }
                str = str2;
                intExtra = registerReceiver.getIntExtra("level", -1);
                int intExtra2 = registerReceiver.getIntExtra("scale", -1);
                if (!(-1 == intExtra || -1 == intExtra2)) {
                    f = (100.0f * ((float) intExtra)) / ((float) intExtra2);
                }
            }
        } catch (Throwable unused) {
        }
        return new b(f, str);
    }
}
