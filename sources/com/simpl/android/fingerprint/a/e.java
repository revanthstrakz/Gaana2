package com.simpl.android.fingerprint.a;

import android.util.Log;
import com.simpl.android.fingerprint.commons.exception.ExceptionNotifier;

class e {
    private static final String a = "e";

    interface a<T> {
        T a();
    }

    interface b<T> {
        T a(Throwable th);
    }

    e() {
    }

    static <T> T a(a<T> aVar, b<T> bVar) {
        try {
            return aVar.a();
        } catch (Throwable th) {
            Log.e(a, th.getLocalizedMessage());
            a(th);
            return bVar.a(th);
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
