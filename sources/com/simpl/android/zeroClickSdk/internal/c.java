package com.simpl.android.zeroClickSdk.internal;

import android.util.Log;
import com.simpl.android.fingerprint.commons.exception.ExceptionNotifier;

class c {
    private static final String a = "c";

    interface a<T> {
        T a();
    }

    c() {
    }

    static <T> T a(a<T> aVar, a<T> aVar2) {
        try {
            return aVar.a();
        } catch (Throwable th) {
            Log.e(a, th.getLocalizedMessage());
            a(th);
            return aVar2.a();
        }
    }

    static <T> T a(a<T> aVar, T t) {
        try {
            return aVar.a();
        } catch (Throwable th) {
            Log.e(a, th.getLocalizedMessage());
            a(th);
            return t;
        }
    }

    static void a(a<Void> aVar) {
        a((a) aVar, null);
    }

    private static void a(Throwable th) {
        try {
            ExceptionNotifier.getSharedInstance().send(th);
        } catch (Throwable th2) {
            Log.e(a, th2.getLocalizedMessage());
        }
    }
}
