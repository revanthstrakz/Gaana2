package com.helpshift.conversation.activeconversation.message;

import com.helpshift.common.domain.e;
import com.helpshift.common.domain.network.f;
import com.helpshift.common.domain.network.h;
import com.helpshift.common.domain.network.k;
import com.helpshift.common.platform.p;
import com.helpshift.configuration.a.a;
import com.helpshift.util.l;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Observable;
import java.util.TimeZone;

public abstract class j extends Observable {
    public String i;
    public String j;
    public String k;
    public String l;
    public Long m;
    public Long n;
    public String o;
    public String p;
    public String q;
    public int r;
    public boolean s;
    protected e t;
    protected p u;
    public boolean v;
    public final boolean w;
    public final MessageType x;

    public abstract boolean a();

    j(String str, String str2, String str3, boolean z, MessageType messageType) {
        this.j = str;
        this.k = str2;
        this.l = str3;
        this.w = z;
        this.x = messageType;
    }

    public void a(e eVar, p pVar) {
        this.t = eVar;
        this.u = pVar;
    }

    public String f() {
        Date parse;
        a c = this.t.c();
        Locale c2 = this.t.k().c();
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", c2);
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
            parse = simpleDateFormat.parse(this.k);
        } catch (ParseException e) {
            Date date = new Date();
            l.a("Helpshift_MessageDM", "getSubText : ParseException", e);
            parse = date;
        }
        String format = new SimpleDateFormat("dd-MMM-yyyy HH:mm", c2).format(parse);
        if (!this.w || !c.a("showAgentName") || !this.v) {
            return format;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.l);
        stringBuilder.append(", ");
        stringBuilder.append(format);
        return stringBuilder.toString();
    }

    /* Access modifiers changed, original: 0000 */
    public void g() {
        setChanged();
        notifyObservers();
    }

    public void b(j jVar) {
        a(jVar);
        g();
    }

    public void a(j jVar) {
        this.j = jVar.j;
        this.k = jVar.k;
        this.l = jVar.l;
    }

    /* Access modifiers changed, original: 0000 */
    public h a_(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("/issues/");
        stringBuilder.append(str);
        stringBuilder.append("/messages/");
        return new f(new com.helpshift.common.domain.network.e(new com.helpshift.common.domain.network.l(new k(stringBuilder.toString(), this.t, this.u), this.u)));
    }
}
