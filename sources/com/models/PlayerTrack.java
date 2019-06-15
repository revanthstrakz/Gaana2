package com.models;

import android.text.TextUtils;
import com.gaana.application.GaanaApplication;
import com.gaana.models.BusinessObject;
import com.gaana.models.Item;
import com.gaana.models.Tracks.Track;
import com.logging.GaanaLogger.PAGE_SORCE_NAME;
import com.logging.GaanaLogger.PLAYOUT_SECTION_TYPE;
import com.logging.GaanaLogger.PLAYOUT_SOURCE_NAME;
import com.logging.GaanaLogger.SOURCE_TYPE;
import com.managers.DownloadManager;
import com.utilities.Util;
import java.io.Serializable;

public class PlayerTrack implements Serializable {
    private Track a;
    private String b;
    private int c = SOURCE_TYPE.OTHER.ordinal();
    private String d = PLAYOUT_SOURCE_NAME.OTHERS.name();
    private String e = PLAYOUT_SECTION_TYPE.OTHERS.name();
    private String f = PAGE_SORCE_NAME.OTHER.name();
    private boolean g = false;
    private boolean h = false;
    private String i = "";
    private boolean j = false;
    private BusinessObject k = null;
    private boolean l = false;
    private Item m;
    private String n;
    private int o = 0;

    public PlayerTrack(Track track, String str, int i, String str2) {
        this.a = track;
        this.b = str;
        this.c = i;
        this.d = str2;
        this.e = GaanaApplication.getInstance().getPlayoutSectionName();
    }

    public PlayerTrack(Item item, String str, int i, String str2) {
        this.m = item;
        this.b = str;
        this.c = i;
        this.d = str2;
        this.e = GaanaApplication.getInstance().getPlayoutSectionName();
    }

    public Track a(boolean z) {
        if (z && this.a == null && !TextUtils.isEmpty(this.i)) {
            this.a = (Track) DownloadManager.c().a(this.i, true);
        }
        if (this.a == null && this.m != null) {
            this.a = (Track) Util.g(this.m);
            this.m = null;
        }
        return this.a;
    }

    public void a(int i) {
        this.o = i;
    }

    public int a() {
        return this.o;
    }

    public Track b() {
        return a(false);
    }

    public void a(Track track) {
        this.a = track;
    }

    public String c() {
        if (this.b == null && this.a != null) {
            this.b = this.a.getAlbumId();
        }
        return this.b;
    }

    public void a(String str) {
        this.b = str;
    }

    public void b(String str) {
        this.n = str;
    }

    public String d() {
        return this.n;
    }

    public int e() {
        return this.c;
    }

    public void b(int i) {
        this.c = i;
    }

    public String f() {
        return this.d;
    }

    public void c(String str) {
        this.d = str;
    }

    public String g() {
        return this.e;
    }

    public void d(String str) {
        this.e = str;
    }

    public String h() {
        if (this.a != null) {
            return this.a.getBusinessObjId();
        }
        if (this.m != null) {
            return this.m.getBusinessObjId();
        }
        return this.i;
    }

    public boolean i() {
        return this.h;
    }

    public void b(boolean z) {
        this.h = z;
    }

    public void e(String str) {
        this.i = str;
    }

    public String j() {
        return this.f;
    }

    public void f(String str) {
        this.f = str;
    }

    public boolean k() {
        return this.g;
    }

    public void c(boolean z) {
        this.g = z;
    }

    public boolean l() {
        return this.j;
    }

    public void d(boolean z) {
        this.j = z;
    }

    public boolean m() {
        return this.l;
    }

    public void e(boolean z) {
        this.l = z;
    }
}
