package com.cast_music;

import android.app.Service;
import android.support.v7.app.MediaRouteDialogFactory;
import com.cast_music.b.d;
import com.google.android.gms.cast.LaunchOptions;
import com.google.android.gms.cast.LaunchOptions.Builder;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class b {
    private List<Integer> a;
    private List<Integer> b;
    private int c;
    private int d;
    private String e;
    private Class<?> f;
    private Class<? extends Service> g;
    private List<String> h;
    private LaunchOptions i;
    private boolean j;
    private int k;
    private MediaRouteDialogFactory l;
    private final boolean m;

    public static class a {
        private List<Integer> a;
        private List<Integer> b;
        private boolean c;
        private boolean d;
        private boolean e;
        private boolean f;
        private boolean g;
        private boolean h;
        private int i = 2;
        private String j;
        private Class<?> k;
        private List<String> l;
        private boolean m;
        private Locale n;
        private boolean o = true;
        private int p = 30;
        private Class<? extends Service> q;
        private MediaRouteDialogFactory r;
        private boolean s;

        public a(String str) {
            this.j = d.a(str, "applicationId");
            this.a = new ArrayList();
            this.b = new ArrayList();
            this.l = new ArrayList();
        }

        public b a() {
            if (!this.e && !this.a.isEmpty()) {
                throw new IllegalArgumentException("Notification was not enabled but some notification actions were configured");
            } else if (this.a.size() > 5) {
                throw new IllegalArgumentException("You cannot add more than 5 notification actions for the expanded view");
            } else if (this.b.size() > 3) {
                throw new IllegalArgumentException("You cannot add more than 3 compact notification actions for the compact view");
            } else if (this.q == null || this.e) {
                return new b(this);
            } else {
                throw new IllegalArgumentException("For custom notifications, you should enable notifications first");
            }
        }

        public a b() {
            this.d = true;
            return this;
        }

        public a c() {
            this.e = true;
            return this;
        }

        public a d() {
            this.f = true;
            return this;
        }

        public a e() {
            this.h = true;
            return this;
        }
    }

    private b(a aVar) {
        if (aVar.c) {
            this.d |= 1;
        }
        if (aVar.d) {
            this.d |= 2;
        }
        if (aVar.e) {
            this.d |= 4;
        }
        if (aVar.f) {
            this.d |= 8;
        }
        if (aVar.g) {
            this.d |= 16;
        }
        if (aVar.h) {
            this.d |= 32;
        }
        this.a = new ArrayList(aVar.a);
        this.b = new ArrayList(aVar.b);
        this.c = aVar.i;
        this.e = aVar.j;
        this.f = aVar.k;
        if (!aVar.l.isEmpty()) {
            this.h = new ArrayList(aVar.l);
        }
        if (aVar.n != null) {
            this.i = new Builder().setLocale(aVar.n).setRelaunchIfRunning(aVar.m).build();
        } else {
            this.i = new Builder().setRelaunchIfRunning(false).build();
        }
        this.j = aVar.o;
        this.k = aVar.p;
        this.g = aVar.q;
        this.l = aVar.r;
        this.m = aVar.s;
    }

    public int a() {
        return this.d;
    }

    public String b() {
        return this.e;
    }

    public List<String> c() {
        return this.h;
    }

    public LaunchOptions d() {
        return this.i;
    }

    public boolean e() {
        return this.m;
    }

    public Class<? extends Service> f() {
        return this.g;
    }

    public MediaRouteDialogFactory g() {
        return this.l;
    }
}
