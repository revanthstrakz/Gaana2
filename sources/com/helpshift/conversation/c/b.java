package com.helpshift.conversation.c;

import com.helpshift.analytics.AnalyticsEventType;
import com.helpshift.common.c;
import com.helpshift.common.domain.e;
import com.helpshift.common.domain.f;
import com.helpshift.common.platform.p;
import com.helpshift.conversation.activeconversation.message.ConversationFooterState;
import com.helpshift.conversation.activeconversation.message.j;
import com.helpshift.conversation.b.a;
import com.helpshift.conversation.dto.ConversationStatus;
import com.helpshift.util.l;
import com.helpshift.widget.i;
import com.helpshift.widget.k;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

public class b implements c, Observer {
    final a a;
    final com.helpshift.conversation.activeconversation.a b;
    final com.helpshift.configuration.a.a c;
    com.helpshift.conversation.activeconversation.b d;
    e e;
    i f = this.h.b();
    String g;
    k h;
    private com.helpshift.widget.a i;
    private com.helpshift.widget.a j;
    private com.helpshift.widget.b k;
    private a l;
    private boolean m;

    /* renamed from: com.helpshift.conversation.c.b$10 */
    static /* synthetic */ class AnonymousClass10 {
        static final /* synthetic */ int[] b = new int[AnalyticsEventType.values().length];

        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x005e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0027 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0048 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0032 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0053 */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Can't wrap try/catch for region: R(19:0|1|2|3|5|6|7|8|9|10|11|12|13|14|15|16|17|18|20) */
        /* JADX WARNING: Can't wrap try/catch for region: R(19:0|1|2|3|5|6|7|8|9|10|11|12|13|14|15|16|17|18|20) */
        /* JADX WARNING: Can't wrap try/catch for region: R(19:0|1|2|3|5|6|7|8|9|10|11|12|13|14|15|16|17|18|20) */
        /* JADX WARNING: Can't wrap try/catch for region: R(19:0|1|2|3|5|6|7|8|9|10|11|12|13|14|15|16|17|18|20) */
        /* JADX WARNING: Can't wrap try/catch for region: R(18:0|(2:1|2)|3|5|6|7|8|9|10|11|12|13|14|15|16|17|18|20) */
        static {
            /*
            r0 = com.helpshift.analytics.AnalyticsEventType.values();
            r0 = r0.length;
            r0 = new int[r0];
            b = r0;
            r0 = 1;
            r1 = b;	 Catch:{ NoSuchFieldError -> 0x0014 }
            r2 = com.helpshift.analytics.AnalyticsEventType.OPEN_ISSUE;	 Catch:{ NoSuchFieldError -> 0x0014 }
            r2 = r2.ordinal();	 Catch:{ NoSuchFieldError -> 0x0014 }
            r1[r2] = r0;	 Catch:{ NoSuchFieldError -> 0x0014 }
        L_0x0014:
            r1 = com.helpshift.conversation.dto.ConversationStatus.values();
            r1 = r1.length;
            r1 = new int[r1];
            a = r1;
            r1 = a;	 Catch:{ NoSuchFieldError -> 0x0027 }
            r2 = com.helpshift.conversation.dto.ConversationStatus.NEW;	 Catch:{ NoSuchFieldError -> 0x0027 }
            r2 = r2.ordinal();	 Catch:{ NoSuchFieldError -> 0x0027 }
            r1[r2] = r0;	 Catch:{ NoSuchFieldError -> 0x0027 }
        L_0x0027:
            r0 = a;	 Catch:{ NoSuchFieldError -> 0x0032 }
            r1 = com.helpshift.conversation.dto.ConversationStatus.IN_PROGRESS;	 Catch:{ NoSuchFieldError -> 0x0032 }
            r1 = r1.ordinal();	 Catch:{ NoSuchFieldError -> 0x0032 }
            r2 = 2;
            r0[r1] = r2;	 Catch:{ NoSuchFieldError -> 0x0032 }
        L_0x0032:
            r0 = a;	 Catch:{ NoSuchFieldError -> 0x003d }
            r1 = com.helpshift.conversation.dto.ConversationStatus.RESOLUTION_REQUESTED;	 Catch:{ NoSuchFieldError -> 0x003d }
            r1 = r1.ordinal();	 Catch:{ NoSuchFieldError -> 0x003d }
            r2 = 3;
            r0[r1] = r2;	 Catch:{ NoSuchFieldError -> 0x003d }
        L_0x003d:
            r0 = a;	 Catch:{ NoSuchFieldError -> 0x0048 }
            r1 = com.helpshift.conversation.dto.ConversationStatus.REJECTED;	 Catch:{ NoSuchFieldError -> 0x0048 }
            r1 = r1.ordinal();	 Catch:{ NoSuchFieldError -> 0x0048 }
            r2 = 4;
            r0[r1] = r2;	 Catch:{ NoSuchFieldError -> 0x0048 }
        L_0x0048:
            r0 = a;	 Catch:{ NoSuchFieldError -> 0x0053 }
            r1 = com.helpshift.conversation.dto.ConversationStatus.RESOLUTION_ACCEPTED;	 Catch:{ NoSuchFieldError -> 0x0053 }
            r1 = r1.ordinal();	 Catch:{ NoSuchFieldError -> 0x0053 }
            r2 = 5;
            r0[r1] = r2;	 Catch:{ NoSuchFieldError -> 0x0053 }
        L_0x0053:
            r0 = a;	 Catch:{ NoSuchFieldError -> 0x005e }
            r1 = com.helpshift.conversation.dto.ConversationStatus.RESOLUTION_REJECTED;	 Catch:{ NoSuchFieldError -> 0x005e }
            r1 = r1.ordinal();	 Catch:{ NoSuchFieldError -> 0x005e }
            r2 = 6;
            r0[r1] = r2;	 Catch:{ NoSuchFieldError -> 0x005e }
        L_0x005e:
            r0 = a;	 Catch:{ NoSuchFieldError -> 0x0069 }
            r1 = com.helpshift.conversation.dto.ConversationStatus.ARCHIVED;	 Catch:{ NoSuchFieldError -> 0x0069 }
            r1 = r1.ordinal();	 Catch:{ NoSuchFieldError -> 0x0069 }
            r2 = 7;
            r0[r1] = r2;	 Catch:{ NoSuchFieldError -> 0x0069 }
        L_0x0069:
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.helpshift.conversation.c.b$AnonymousClass10.<clinit>():void");
        }
    }

    public b(p pVar, e eVar, a aVar, com.helpshift.conversation.activeconversation.a aVar2, com.helpshift.conversation.activeconversation.b bVar, boolean z) {
        this.e = eVar;
        this.a = aVar;
        this.b = aVar2;
        this.c = eVar.c();
        this.m = z;
        this.c.addObserver(this);
        this.h = new k(this.c, aVar);
        this.l = new a(eVar);
        this.l.b(this.h.c());
        this.l.a(this.h.d());
        com.helpshift.widget.a aVar3 = new com.helpshift.widget.a();
        this.l.c(aVar3);
        this.l.a(this.f);
        boolean o = o();
        aVar2.b(o);
        this.i = this.h.a(aVar2, o);
        this.l.d(this.i);
        this.j = this.h.a(aVar2);
        this.l.e(this.j);
        this.k = this.h.b(aVar2, o);
        this.l.a(this.k);
        if (this.i.b()) {
            aVar.a(2);
        } else {
            aVar.a(-1);
        }
        if (!o && aVar2.d == ConversationStatus.RESOLUTION_REJECTED) {
            aVar2.c();
        }
        this.b.j = this;
        this.d = bVar;
        this.l.a(this.d);
        this.l.a();
        aVar2.a();
        aVar2.d();
        aVar2.a(a(bVar));
        this.l.a(aVar2.d == ConversationStatus.REJECTED);
        this.d.a(aVar2.g);
        this.d.a(this.f.a());
    }

    public void a() {
        boolean o = o();
        this.h.a(this.i, this.b, o);
        this.h.a(this.j, this.b);
        this.h.a(this.k, this.b, o);
        if (this.i.b()) {
            this.a.a(2);
        } else {
            this.a.a(-1);
        }
        this.b.a(a(this.d));
        this.b.j = this;
    }

    public void b() {
        if (this.b.d == ConversationStatus.REJECTED) {
            this.b.c();
        }
        this.d = null;
        this.l.a(null);
        this.c.deleteObserver(this);
    }

    public void a(boolean z) {
        this.a.a(z);
        b(this.b.q());
    }

    public void a(final String str) {
        this.e.a(new f() {
            public void a() {
                b.this.g = str;
            }
        });
    }

    private com.helpshift.common.util.b a(final com.helpshift.conversation.activeconversation.b bVar) {
        return new com.helpshift.common.util.b() {
            public void a(int i, int i2) {
                bVar.a(i, i2);
            }

            public void b(int i, int i2) {
                bVar.b(i, i2);
            }
        };
    }

    public void b(final String str) {
        this.e.a(new f() {
            public void a() {
                b.this.f.a(str);
                b.this.h.a(str);
            }
        });
    }

    public void c() {
        this.e.a(new f() {
            public void a() {
                final String a = b.this.f.a();
                if (!c.a(a)) {
                    b.this.e.c(new f() {
                        public void a() {
                            if (b.this.d != null) {
                                b.this.d.a("");
                            }
                        }
                    });
                    b.this.e.b(new f() {
                        public void a() {
                            b.this.a.c(true);
                            b.this.b.a(a);
                        }
                    });
                }
            }
        });
    }

    public void a(final j jVar) {
        this.e.a(new f() {
            public void a() {
                b.this.e.b(new f() {
                    public void a() {
                        b.this.b.a(jVar);
                    }
                });
            }
        });
    }

    public void a(final com.helpshift.conversation.activeconversation.message.k kVar) {
        this.e.a(new f() {
            public void a() {
                final String trim = b.this.c.c("reviewUrl").trim();
                if (!c.a(trim)) {
                    b.this.c.a(true);
                    b.this.e.c(new f() {
                        public void a() {
                            if (b.this.d != null) {
                                b.this.d.b(trim);
                            }
                        }
                    });
                }
                b.this.b.a(kVar);
            }
        });
    }

    public boolean d() {
        return this.d != null;
    }

    public String e() {
        return this.g;
    }

    public void b(final boolean z) {
        this.e.c(new f() {
            public void a() {
                if (b.this.d == null) {
                    return;
                }
                if (b.this.b.d == ConversationStatus.NEW || b.this.b.d == ConversationStatus.IN_PROGRESS) {
                    b.this.d.a(z);
                } else {
                    b.this.d.a(false);
                }
            }
        });
    }

    public void a(final com.helpshift.conversation.dto.c cVar, final String str) {
        this.e.b(new f() {
            public void a() {
                b.this.b.a(cVar, str);
            }
        });
    }

    public void a(final com.helpshift.conversation.activeconversation.message.c cVar) {
        this.e.a(new f() {
            public void a() {
                b.this.b.a(cVar);
            }
        });
    }

    public void a(final String str, final String str2) {
        this.e.c(new f() {
            public void a() {
                if (b.this.d != null) {
                    b.this.d.a(str, str2);
                }
            }
        });
    }

    public void c(final boolean z) {
        this.e.a(new f() {
            public void a() {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Sending resolution event : Accepted? ");
                stringBuilder.append(z);
                l.a("Helpshift_ConvVM", stringBuilder.toString());
                if (b.this.b.d == ConversationStatus.RESOLUTION_REQUESTED) {
                    b.this.b.a(z);
                }
            }
        });
    }

    public void f() {
        this.e.b(new f() {
            public void a() {
                b.this.b.b(b.this.g);
            }
        });
    }

    public void g() {
        this.l.a();
    }

    public void h() {
        if (this.d != null) {
            this.d.b(this.b.b, this.b.h);
        }
    }

    /* JADX WARNING: Missing block: B:14:0x007f, code skipped:
            r0 = -1;
     */
    /* JADX WARNING: Missing block: B:15:0x0080, code skipped:
            r4 = false;
     */
    /* JADX WARNING: Missing block: B:16:0x0081, code skipped:
            if (r1 == false) goto L_0x0086;
     */
    /* JADX WARNING: Missing block: B:17:0x0083, code skipped:
            b(false);
     */
    /* JADX WARNING: Missing block: B:18:0x0086, code skipped:
            r3.a.a(r0);
            r3.l.a(r4);
     */
    /* JADX WARNING: Missing block: B:19:0x0090, code skipped:
            return;
     */
    public void a(com.helpshift.conversation.dto.ConversationStatus r4) {
        /*
        r3 = this;
        r0 = "Helpshift_ConvVM";
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "Changing conversation status to: ";
        r1.append(r2);
        r1.append(r4);
        r1 = r1.toString();
        com.helpshift.util.l.a(r0, r1);
        r0 = com.helpshift.conversation.c.b.AnonymousClass10.a;
        r4 = r4.ordinal();
        r4 = r0[r4];
        r0 = 2;
        r1 = 1;
        r2 = 0;
        switch(r4) {
            case 1: goto L_0x0077;
            case 2: goto L_0x0077;
            case 3: goto L_0x005e;
            case 4: goto L_0x005c;
            case 5: goto L_0x0038;
            case 6: goto L_0x002d;
            case 7: goto L_0x0025;
            default: goto L_0x0024;
        };
    L_0x0024:
        goto L_0x007f;
    L_0x0025:
        r4 = r3.l;
        r0 = com.helpshift.conversation.activeconversation.message.ConversationFooterState.ARCHIVAL_MESSAGE;
        r4.a(r0);
        goto L_0x007f;
    L_0x002d:
        r4 = r3.l;
        r4.h();
        r4 = r3.b;
        r4.b(r1);
        goto L_0x0080;
    L_0x0038:
        r4 = r3.h;
        r0 = "";
        r4.a(r0);
        r4 = r3.a;
        r4.c(r2);
        r4 = r3.b;
        r4 = r4.i();
        if (r4 == 0) goto L_0x0054;
    L_0x004c:
        r4 = r3.l;
        r0 = com.helpshift.conversation.activeconversation.message.ConversationFooterState.CSAT_RATING;
        r4.a(r0);
        goto L_0x007f;
    L_0x0054:
        r4 = r3.l;
        r0 = com.helpshift.conversation.activeconversation.message.ConversationFooterState.START_NEW_CONVERSATION;
        r4.a(r0);
        goto L_0x007f;
    L_0x005c:
        r4 = r1;
        goto L_0x0081;
    L_0x005e:
        r4 = r3.a;
        r4.c(r2);
        r4 = r3.c;
        r0 = "showConversationResolutionQuestion";
        r4 = r4.a(r0);
        if (r4 == 0) goto L_0x0073;
    L_0x006d:
        r4 = r3.l;
        r4.i();
        goto L_0x007f;
    L_0x0073:
        r3.c(r1);
        goto L_0x007f;
    L_0x0077:
        r4 = r3.l;
        r4.h();
        r4 = r2;
        r1 = r4;
        goto L_0x0081;
    L_0x007f:
        r0 = -1;
    L_0x0080:
        r4 = r2;
    L_0x0081:
        if (r1 == 0) goto L_0x0086;
    L_0x0083:
        r3.b(r2);
    L_0x0086:
        r1 = r3.a;
        r1.a(r0);
        r0 = r3.l;
        r0.a(r4);
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.helpshift.conversation.c.b.a(com.helpshift.conversation.dto.ConversationStatus):void");
    }

    public void i() {
        this.e.a(new f() {
            public void a() {
                b.this.a.c(true);
            }
        });
    }

    private boolean o() {
        return !c.a(this.a.h()) || this.a.m() || this.m;
    }

    public void a(final int i, final String str) {
        if (this.d != null) {
            this.d.k();
        }
        if (!(this.b.d == ConversationStatus.NEW || this.b.d == ConversationStatus.IN_PROGRESS)) {
            this.l.a(ConversationFooterState.START_NEW_CONVERSATION);
        }
        this.e.a(new f() {
            public void a() {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Sending CSAT rating : ");
                stringBuilder.append(i);
                stringBuilder.append(", feedback: ");
                stringBuilder.append(str);
                l.a("Helpshift_ConvVM", stringBuilder.toString());
                b.this.b.a(i, str);
            }
        });
    }

    public void a(int i) {
        this.a.a(i);
    }

    public void j() {
        n();
        this.b.a(true, true);
    }

    public void k() {
        this.e.a(new f() {
            public void a() {
                b.this.a.f(b.this.b.b);
                b.this.a.e(b.this.b.b);
            }
        });
    }

    public void l() {
        this.e.a(new f() {
            public void a() {
                b.this.b.b(false, true);
            }
        });
    }

    public void a(AnalyticsEventType analyticsEventType, Map<String, Object> map) {
        Map map2;
        if (AnonymousClass10.b[analyticsEventType.ordinal()] == 1) {
            map2 = new HashMap();
            map2.put("id", this.b.b);
        }
        this.e.d().a(analyticsEventType, map2);
    }

    public void c(String str) {
        String str2 = null;
        try {
            URI create = URI.create(str);
            if (create != null) {
                str2 = create.getScheme();
            }
        } catch (Exception unused) {
        }
        if (!c.a(str2)) {
            Map hashMap = new HashMap();
            hashMap.put("id", this.b.b);
            hashMap.put(TtmlNode.TAG_P, str2);
            hashMap.put("u", str);
            a(AnalyticsEventType.ADMIN_MESSAGE_DEEPLINK_CLICKED, hashMap);
        }
    }

    public void m() {
        this.b.o();
    }

    public void n() {
        this.b.p();
    }

    public void update(final Observable observable, Object obj) {
        this.e.c(new f() {
            public void a() {
                if (b.this.d != null && (observable instanceof com.helpshift.configuration.a.a)) {
                    b.this.d.b(0, b.this.b.g.size());
                }
            }
        });
    }
}
