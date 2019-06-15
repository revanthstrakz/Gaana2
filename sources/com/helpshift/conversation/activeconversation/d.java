package com.helpshift.conversation.activeconversation;

import com.helpshift.common.domain.f;
import com.helpshift.common.platform.Device;
import com.helpshift.common.platform.p;
import com.helpshift.util.l;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class d implements com.helpshift.common.platform.network.a.b {
    final long a = TimeUnit.SECONDS.toMillis(3);
    final String b;
    AtomicInteger c;
    com.helpshift.common.platform.network.a.a d;
    boolean e;
    boolean f;
    boolean g;
    e h;
    com.helpshift.common.domain.e i;
    p j;
    AtomicInteger k;
    boolean l;
    f m = new f() {
        public void a() {
            if (d.this.d == null) {
                return;
            }
            if (d.this.f) {
                d.this.e = true;
                return;
            }
            try {
                l.a("Helpshift_LiveUpdateDM", "Disconnecting web-socket");
                d.this.d.b();
            } catch (Exception e) {
                l.c("Helpshift_LiveUpdateDM", "Exception in disconnecting web-socket", e);
            }
            d.this.d = null;
        }
    };
    boolean n;
    private final String o = "[110]";
    private final String p = "hs-sdk-ver";
    private String q;
    private String r;
    private f s = new f() {
        public void a() {
            if (d.this.h != null) {
                d.this.i.j().b();
                d.this.g = true;
                new a(d.this.c.incrementAndGet()).a();
            }
        }
    };

    interface e {
        void d(boolean z);
    }

    private class a extends f {
        private final int b;

        a(int i) {
            this.b = i;
        }

        public void a() {
            if (d.this.h != null && this.b == d.this.c.get() && !d.this.n && !d.this.f) {
                com.helpshift.c.b.a a = d.this.i.j().a();
                if (a == null) {
                    d.this.d();
                    return;
                }
                l.a("Helpshift_LiveUpdateDM", "Connecting web-socket");
                try {
                    d.this.d = new com.helpshift.common.platform.network.a.a.a(d.this.a(a)).a((int) TimeUnit.SECONDS.toMillis(60)).a("permessage-deflate").a("client_no_context_takeover").a("server_no_context_takeover").b("dirigent-pubsub-v1").a("hs-sdk-ver", d.this.b).a(d.this).a();
                    d.this.f = true;
                    d.this.d.a();
                } catch (Exception e) {
                    l.c("Helpshift_LiveUpdateDM", "Exception in connecting web-socket", e);
                    d.this.d();
                }
            }
        }
    }

    private class b extends f {
        private final String b;

        b(String str) {
            this.b = str;
        }

        public void a() {
            com.helpshift.conversation.dto.f m = d.this.j.j().m(this.b);
            if (m instanceof com.helpshift.conversation.dto.d) {
                d.this.i.b(new c(d.this.c.incrementAndGet()), ((com.helpshift.conversation.dto.d) m).a + d.this.a);
                if (d.this.d != null) {
                    d.this.d.a("[110]");
                }
            } else if (d.this.h != null && (m instanceof com.helpshift.conversation.dto.e)) {
                com.helpshift.conversation.dto.e eVar = (com.helpshift.conversation.dto.e) m;
                if (eVar.a) {
                    d.this.l = true;
                    d.this.i.b(new d(d.this.k.incrementAndGet()), eVar.b + d.this.a);
                } else {
                    d.this.l = false;
                }
                d.this.e();
            }
        }
    }

    private class c extends f {
        int a;

        c(int i) {
            this.a = i;
        }

        public void a() {
            if (this.a == d.this.c.get() && d.this.h != null) {
                l.a("Helpshift_LiveUpdateDM", "Ping timed out, resetting connection");
                d.this.m.a();
                new a(d.this.c.incrementAndGet()).a();
            }
        }
    }

    private class d extends f {
        int a;

        d(int i) {
            this.a = i;
        }

        public void a() {
            if (this.a == d.this.k.get() && d.this.h != null) {
                l.a("Helpshift_LiveUpdateDM", "Start Typing action timed out, disabling TAI");
                d.this.l = false;
                d.this.e();
            }
        }
    }

    public d(com.helpshift.common.domain.e eVar, p pVar) {
        this.i = eVar;
        this.j = pVar;
        this.c = new AtomicInteger(-1);
        this.k = new AtomicInteger(-1);
        Device d = pVar.d();
        StringBuilder stringBuilder = new StringBuilder(d.a().toLowerCase());
        stringBuilder.append("-");
        stringBuilder.append(d.b());
        this.b = stringBuilder.toString();
    }

    /* Access modifiers changed, original: 0000 */
    public boolean b() {
        return this.l;
    }

    /* Access modifiers changed, original: declared_synchronized */
    public synchronized void a(e eVar, String str, String str2) {
        if (this.h == null) {
            this.r = str;
            this.h = eVar;
            this.q = str2;
            this.g = false;
            this.e = false;
            this.i.b(new a(this.c.incrementAndGet()));
        }
    }

    /* Access modifiers changed, original: declared_synchronized */
    public synchronized void c() {
        if (this.h != null) {
            this.l = false;
            e();
            this.k.incrementAndGet();
            this.c.incrementAndGet();
            this.h = null;
        }
        this.i.b(this.m);
    }

    public void a(com.helpshift.common.platform.network.a.a aVar) {
        l.a("Helpshift_LiveUpdateDM", "web-socket connected");
        this.f = false;
        this.n = true;
        if (this.e) {
            this.m.a();
        } else if (this.h != null) {
            l.a("Helpshift_LiveUpdateDM", "Subscribing to conversation topic");
            aVar.a(f());
            this.i.b(new c(this.c.incrementAndGet()), TimeUnit.SECONDS.toMillis(60));
        } else {
            this.m.a();
        }
    }

    public void a() {
        l.a("Helpshift_LiveUpdateDM", "web-socket disconnected");
        this.n = false;
        this.e = false;
    }

    public void a(com.helpshift.common.platform.network.a.a aVar, String str) {
        this.i.b(new b(str));
    }

    public void b(com.helpshift.common.platform.network.a.a aVar, String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Error in web-socket connection: ");
        stringBuilder.append(str);
        l.a("Helpshift_LiveUpdateDM", stringBuilder.toString());
        this.f = false;
        if (this.h == null) {
            return;
        }
        if (a(str) != 403) {
            d();
        } else if (!this.g) {
            this.i.b(this.s);
        }
    }

    private int a(String str) {
        String[] split = str.split("The status line is: ");
        if (2 == split.length) {
            split = split[1].split(" +");
            if (split.length >= 2) {
                if ("403".equals(split[1])) {
                    return 403;
                }
            }
        }
        return -1;
    }

    private String f() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[104, [\"");
        stringBuilder.append(this.q);
        stringBuilder.append(".agent_type_act.issue.");
        stringBuilder.append(this.r);
        stringBuilder.append("\"]]");
        return stringBuilder.toString();
    }

    /* Access modifiers changed, original: 0000 */
    public String a(com.helpshift.c.b.a aVar) {
        String c = this.j.c();
        String[] split = this.j.b().split("\\.");
        String str = "";
        if (split.length == 3) {
            str = split[0];
        }
        String str2 = "";
        try {
            str2 = URLEncoder.encode(aVar.a, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            l.c("Helpshift_LiveUpdateDM", "Exception in encoding authToken", e);
        }
        if (com.helpshift.common.c.a(str2) || com.helpshift.common.c.a(aVar.b)) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder(aVar.b);
        stringBuilder.append("/subscribe/websocket/?origin_v3=");
        stringBuilder.append(str2);
        stringBuilder.append("&platform_id=");
        stringBuilder.append(c);
        stringBuilder.append("&domain=");
        stringBuilder.append(str);
        return stringBuilder.toString();
    }

    /* Access modifiers changed, original: 0000 */
    public void d() {
        this.i.b(new a(this.c.incrementAndGet()), TimeUnit.SECONDS.toMillis(10));
    }

    /* Access modifiers changed, original: 0000 */
    public void e() {
        if (this.h != null) {
            this.h.d(this.l);
        }
    }
}
