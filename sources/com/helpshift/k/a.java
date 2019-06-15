package com.helpshift.k;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.helpshift.q.d;
import com.helpshift.util.t;

public class a {
    public String a = ((String) this.n.a("apiKey"));
    public String b = ((String) this.n.a("domainName"));
    public String c;
    public Integer d;
    public Integer e;
    public Integer f;
    public Boolean g;
    public Boolean h;
    public Boolean i;
    public Boolean j;
    public Integer k;
    public String l;
    private String m;
    private d n;

    protected a(d dVar) {
        this.n = dVar;
        if (!(this.b == null || t.c(this.b))) {
            this.b = null;
        }
        this.c = (String) this.n.a("platformId");
        if (!(this.c == null || t.a(this.c))) {
            this.c = null;
        }
        this.m = (String) this.n.a("font");
        this.d = (Integer) this.n.a("notificationSound");
        this.e = (Integer) this.n.a("notificationIcon");
        this.f = (Integer) this.n.a("largeNotificationIcon");
        this.g = (Boolean) this.n.a("disableHelpshiftBranding");
        this.h = (Boolean) this.n.a("enableInboxPolling");
        this.i = (Boolean) this.n.a("muteNotifications");
        this.j = (Boolean) this.n.a("disableAnimations");
        this.k = (Integer) this.n.a("screenOrientation");
        this.l = (String) this.n.a("campaignsNotificationChannelId");
    }

    @Nullable
    public String a() {
        return this.m;
    }

    public void a(String str) {
        this.m = str;
        this.n.a("font", str);
    }

    public void a(Integer num) {
        this.k = num;
        this.n.a("screenOrientation", this.k);
    }

    public void a(Boolean bool) {
        this.j = bool;
        this.n.a("disableAnimations", bool);
    }

    public boolean b() {
        return (TextUtils.isEmpty(this.a) || TextUtils.isEmpty(this.b) || TextUtils.isEmpty(this.c)) ? false : true;
    }
}
