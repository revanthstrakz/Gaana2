package com.android.b.a;

import android.content.Context;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.annotation.UiThread;

public abstract class a {

    public static final class a {
        private final Context a;

        private a(Context context) {
            this.a = context;
        }

        @UiThread
        public a a() {
            if (this.a != null) {
                return new b(this.a);
            }
            throw new IllegalArgumentException("Please provide a valid Context.");
        }
    }

    @UiThread
    public abstract void a(@NonNull c cVar);

    @UiThread
    public abstract boolean a();

    @UiThread
    public abstract void b();

    @UiThread
    public abstract d c() throws RemoteException;

    @UiThread
    public static a a(@NonNull Context context) {
        return new a(context);
    }
}
