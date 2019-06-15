package com.managers;

import com.managers.URLManager.BusinessObjectType;
import java.util.ArrayList;

public class i {
    private static i a;
    private ArrayList<String> b;
    private ArrayList<String> c;
    private boolean d;
    private boolean e;

    private i() {
        b();
    }

    public static i a() {
        if (a == null) {
            a = new i();
        }
        return a;
    }

    public void b() {
        this.b = new ArrayList();
        this.c = new ArrayList();
    }

    public void a(String str, boolean z) {
        if (z) {
            this.b.add(str);
        } else {
            this.c.add(str);
        }
    }

    public void b(String str, boolean z) {
        this.d = false;
        if (z) {
            this.b.remove(str);
        } else {
            this.c.remove(str);
        }
    }

    public void c() {
        if (this.b.size() > 0) {
            DownloadManager.c().a(this.b, true);
        }
        if (this.c.size() > 0) {
            DownloadManager.c().a(this.c, false);
        }
    }

    public void d() {
        this.b.clear();
        this.c.clear();
        this.b.addAll(DownloadManager.c().x());
        this.c.addAll(DownloadManager.c().a(BusinessObjectType.Playlists));
        this.c.addAll(DownloadManager.c().a(BusinessObjectType.Albums));
        this.d = true;
    }

    public void e() {
        this.b.clear();
        this.c.clear();
        this.d = false;
    }

    public boolean f() {
        return this.e;
    }

    public void a(boolean z) {
        this.e = z;
    }

    public boolean g() {
        return this.d;
    }

    public void b(boolean z) {
        this.d = z;
    }

    public boolean c(String str, boolean z) {
        if (z) {
            return this.b.contains(str);
        }
        return this.c.contains(str);
    }

    public boolean h() {
        boolean z = false;
        if (this.c == null || this.b == null) {
            return false;
        }
        if (this.c.size() == 0 && this.b.size() == 0) {
            z = true;
        }
        return z;
    }
}
