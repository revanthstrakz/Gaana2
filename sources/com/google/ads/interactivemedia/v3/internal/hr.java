package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public final class hr extends gp<Time> {
    public static final gq a = new gq() {
        public <T> gp<T> a(fz fzVar, hw<T> hwVar) {
            return hwVar.a() == Time.class ? new hr() : null;
        }
    };
    private final DateFormat b = new SimpleDateFormat("hh:mm:ss a");

    /* renamed from: a */
    public synchronized Time read(hx hxVar) throws IOException {
        if (hxVar.f() == hy.NULL) {
            hxVar.j();
            return null;
        }
        try {
            return new Time(this.b.parse(hxVar.h()).getTime());
        } catch (ParseException e) {
            throw new gn(e);
        }
    }

    /* renamed from: a */
    public synchronized void write(hz hzVar, Time time) throws IOException {
        hzVar.b(time == null ? null : this.b.format(time));
    }
}
