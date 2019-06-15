package com.bumptech.glide.request.b;

import android.graphics.drawable.Drawable;
import com.bumptech.glide.load.DataSource;
import com.google.api.client.http.HttpStatusCodes;

public class a implements e<Drawable> {
    private final int a;
    private final boolean b;
    private b c;

    public static class a {
        private int a;
        private boolean b;

        public a() {
            this(HttpStatusCodes.STATUS_CODE_MULTIPLE_CHOICES);
        }

        public a(int i) {
            this.a = i;
        }

        public a a() {
            return new a(this.a, this.b);
        }
    }

    protected a(int i, boolean z) {
        this.a = i;
        this.b = z;
    }

    public d<Drawable> a(DataSource dataSource, boolean z) {
        return dataSource == DataSource.MEMORY_CACHE ? c.b() : a();
    }

    private d<Drawable> a() {
        if (this.c == null) {
            this.c = new b(this.a, this.b);
        }
        return this.c;
    }
}
