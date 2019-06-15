package com.til.colombia.android.internal.a;

import android.content.Context;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import com.til.colombia.android.internal.Log;
import com.til.colombia.android.internal.i;

public abstract class a {
    PhoneStateListener a = new b(this);

    public abstract void a();

    public abstract void b();

    public final void a(Context context) {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            if (telephonyManager != null) {
                telephonyManager.listen(this.a, 32);
            }
        } catch (Exception e) {
            Log.a(i.f, "", e);
        }
    }

    public final void b(Context context) {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            if (telephonyManager != null) {
                telephonyManager.listen(this.a, 0);
            }
        } catch (Exception e) {
            Log.a(i.f, "", e);
        }
    }
}
