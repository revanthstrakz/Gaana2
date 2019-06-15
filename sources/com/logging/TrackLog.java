package com.logging;

import android.text.TextUtils;
import com.gaana.models.Tracks.Track;
import com.logging.GaanaLogger.CONTENT_TYPE;
import com.logging.GaanaLogger.PLAYOUT_SOURCE;
import com.managers.DownloadManager;
import com.managers.DownloadManager.DownloadStatus;
import com.managers.ap;
import com.utilities.Util;
import java.io.Serializable;
import java.util.concurrent.TimeUnit;

public class TrackLog implements Serializable {
    private static final long serialVersionUID = 1;
    private String a;
    private String b;
    private String c;
    private String d;
    private Track e;
    private String f;
    private String g;
    private String h;
    private String i;
    private String j = "0";
    private String k = "0";
    private boolean l = false;
    private String m;
    private String n;
    private String o;
    private String p = String.valueOf(CONTENT_TYPE.AUDIO_TRACK.ordinal());
    private String q = String.valueOf(PLAYOUT_SOURCE.NETWORK.ordinal());

    public String a() {
        return this.k;
    }

    public void a(String str) {
        this.k = str;
    }

    public String b() {
        return this.a;
    }

    public void b(String str) {
        this.a = str;
    }

    public void c(String str) {
        this.d = str;
    }

    public String c() {
        return this.d;
    }

    public String d() {
        return this.f;
    }

    public void d(String str) {
        this.f = str;
    }

    public void e(String str) {
        this.g = str;
    }

    public String e() {
        return this.h;
    }

    public void f(String str) {
        this.h = str;
    }

    public String f() {
        return this.p;
    }

    public void g(String str) {
        this.p = str;
    }

    public String g() {
        return this.q;
    }

    public void h(String str) {
        this.q = str;
    }

    public void a(long j) {
        this.i = String.valueOf(j / 1000);
    }

    public String h() {
        if (this.j == null) {
            return "0";
        }
        if (this.j.contains(".")) {
            String[] split = this.j.split("\\.");
            if (split.length > 0) {
                this.j = split[0];
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("");
        stringBuilder.append(TimeUnit.MILLISECONDS.toSeconds(Long.parseLong(this.j) + Util.y()));
        return stringBuilder.toString();
    }

    public String i() {
        if (this.j == null) {
            return "0";
        }
        if (this.j.contains(".")) {
            String[] split = this.j.split("\\.");
            if (split.length > 0) {
                this.j = split[0];
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("");
        stringBuilder.append(TimeUnit.MILLISECONDS.toSeconds(Long.parseLong(this.j)));
        return stringBuilder.toString();
    }

    public void i(String str) {
        this.j = str;
    }

    public String toString() {
        String str = "1";
        if (!TextUtils.isEmpty(this.a) && TextUtils.isDigitsOnly(this.a) && ((ap.a().j() || Util.v()) && DownloadManager.c().e(Integer.parseInt(this.a)) == DownloadStatus.DOWNLOADED)) {
            str = "0";
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.a);
        stringBuilder.append("#");
        stringBuilder.append(h());
        stringBuilder.append("#");
        stringBuilder.append(this.i);
        stringBuilder.append("#0#");
        stringBuilder.append(str);
        return stringBuilder.toString();
    }

    public boolean j() {
        return this.l;
    }

    public void a(boolean z) {
        this.l = z;
    }

    public void j(String str) {
        this.b = str;
    }

    public void k(String str) {
        this.c = str;
    }

    public void a(Track track) {
        this.e = track;
    }

    public String k() {
        return this.m;
    }

    public void l(String str) {
        this.m = str;
    }

    public String l() {
        return this.n;
    }

    public void m(String str) {
        this.n = str;
    }

    public String m() {
        return this.o;
    }

    public void n(String str) {
        this.o = str;
    }
}
