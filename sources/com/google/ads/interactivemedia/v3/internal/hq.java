package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public final class hq extends gp<Date> {
    public static final gq a = new gq() {
        public <T> gp<T> a(fz fzVar, hw<T> hwVar) {
            return hwVar.a() == Date.class ? new hq() : null;
        }
    };
    private final DateFormat b = new SimpleDateFormat("MMM d, yyyy");

    /* renamed from: a */
    public synchronized Date read(hx hxVar) throws IOException {
        if (hxVar.f() == hy.NULL) {
            hxVar.j();
            return null;
        }
        try {
            return new Date(this.b.parse(hxVar.h()).getTime());
        } catch (ParseException e) {
            throw new gn(e);
        }
    }

    /* renamed from: a */
    public synchronized void write(hz hzVar, Date date) throws IOException {
        hzVar.b(date == null ? null : this.b.format(date));
    }
}
