package com.facebook.ads.internal.g;

import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import com.google.android.gms.auth.api.proxy.AuthApiStatusCodes;

@VisibleForTesting
public abstract class f<T> {
    private a a;

    public enum a {
        UNKNOWN(9000, "An unknown error has occurred."),
        DATABASE_SELECT(3001, "Failed to read from database."),
        DATABASE_INSERT(AuthApiStatusCodes.AUTH_API_CLIENT_ERROR, "Failed to insert row into database."),
        DATABASE_UPDATE(AuthApiStatusCodes.AUTH_API_SERVER_ERROR, "Failed to update row in database."),
        DATABASE_DELETE(AuthApiStatusCodes.AUTH_TOKEN_ERROR, "Failed to delete row from database.");
        
        private final int f;
        private final String g;

        private a(int i, String str) {
            this.f = i;
            this.g = str;
        }

        public int a() {
            return this.f;
        }

        public String b() {
            return this.g;
        }
    }

    /* Access modifiers changed, original: protected */
    public void a(a aVar) {
        this.a = aVar;
    }

    @Nullable
    public abstract T b();

    public a c() {
        return this.a;
    }
}
