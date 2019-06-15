package com.til.colombia.android.internal.a;

import android.telephony.PhoneStateListener;

final class b extends PhoneStateListener {
    final /* synthetic */ a a;

    b(a aVar) {
        this.a = aVar;
    }

    public final void onCallStateChanged(int i, String str) {
        if (i == 1) {
            this.a.a();
        } else if (i == 0) {
            this.a.b();
        } else if (i == 2) {
            this.a.a();
        }
        super.onCallStateChanged(i, str);
    }
}
