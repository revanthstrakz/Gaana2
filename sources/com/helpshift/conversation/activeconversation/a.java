package com.helpshift.conversation.activeconversation;

import com.facebook.share.internal.ShareConstants;
import com.helpshift.account.a.b;
import com.helpshift.analytics.AnalyticsEventType;
import com.helpshift.common.AutoRetryFailedEventDM.EventType;
import com.helpshift.common.domain.e;
import com.helpshift.common.domain.network.f;
import com.helpshift.common.domain.network.g;
import com.helpshift.common.domain.network.k;
import com.helpshift.common.domain.network.l;
import com.helpshift.common.exception.NetworkException;
import com.helpshift.common.exception.RootAPIException;
import com.helpshift.common.platform.network.h;
import com.helpshift.common.platform.p;
import com.helpshift.common.util.HSObservableList;
import com.helpshift.conversation.activeconversation.message.AdminAttachmentMessageDM;
import com.helpshift.conversation.activeconversation.message.AdminImageAttachmentMessageDM;
import com.helpshift.conversation.activeconversation.message.MessageType;
import com.helpshift.conversation.activeconversation.message.d;
import com.helpshift.conversation.activeconversation.message.j;
import com.helpshift.conversation.activeconversation.message.m;
import com.helpshift.conversation.activeconversation.message.n;
import com.helpshift.conversation.activeconversation.message.o;
import com.helpshift.conversation.c.c;
import com.helpshift.conversation.dto.ConversationStatus;
import com.helpshift.conversation.states.ConversationCSATState;
import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

public class a implements e, Observer {
    private com.helpshift.configuration.a.a A;
    private d B;
    private boolean C;
    private Comparator<j> D;
    public Long a;
    public String b;
    public String c;
    public ConversationStatus d;
    public String e;
    public String f;
    public HSObservableList<j> g = new HSObservableList();
    public String h;
    public boolean i;
    public c j;
    public String k;
    public boolean l;
    public boolean m;
    public ConversationCSATState n = ConversationCSATState.NONE;
    public int o;
    public String p;
    public boolean q;
    public long r;
    p s;
    e t;
    b u;
    private final Map<String, j> v = new HashMap();
    private final List<j> w = new ArrayList();
    private h x;
    private com.helpshift.conversation.a.a y;
    private com.helpshift.meta.a z;

    public a(String str, String str2, ConversationStatus conversationStatus, String str3, String str4, String str5, String str6, boolean z) {
        this.b = str;
        this.c = str2;
        this.e = str3;
        this.d = conversationStatus;
        this.f = str4;
        this.h = str5;
        this.k = str6;
        this.i = z;
    }

    public a(p pVar, e eVar, b bVar, d dVar) {
        a(pVar, eVar, bVar);
        a(dVar);
    }

    public void a(p pVar, e eVar, b bVar) {
        this.s = pVar;
        this.t = eVar;
        this.u = bVar;
        this.x = pVar.j();
        this.y = pVar.f();
        this.z = eVar.f();
        this.A = eVar.c();
        this.r = bVar.a.longValue();
    }

    public void a(d dVar) {
        this.B = dVar;
    }

    public void a(com.helpshift.common.util.b bVar) {
        this.g.a(bVar);
        Iterator it = this.g.iterator();
        while (it.hasNext()) {
            ((j) it.next()).addObserver(this);
        }
    }

    public void a(String str, String str2, String str3, String str4, String str5) {
        HashMap hashMap = new HashMap();
        hashMap.put("profile-id", this.u.b);
        hashMap.put("message-text", str);
        hashMap.put("uid", str4);
        hashMap.put("did", str5);
        hashMap.put("meta", this.z.a(this.u.b(), this.u.g, new com.helpshift.meta.dto.c(str2, str3)).toString());
        Object a = this.t.g().a();
        if (a != null) {
            hashMap.put("custom_fields", a.toString());
        }
        a c = this.x.c(new f(new g(new l(new k("/issues/", this.t, this.s), this.s), this.s)).c(hashMap).b);
        this.b = c.b;
        this.c = c.c;
        this.d = c.d;
        this.e = c.e;
        this.f = c.f;
        this.g = c.g;
        this.h = c.h;
        this.i = c.i;
        this.y.a(this);
    }

    public void a(j jVar) {
        if (jVar instanceof o) {
            a((o) jVar);
        } else if (jVar instanceof n) {
            a((n) jVar, false);
        }
    }

    public void a(String str) {
        o oVar = new o(str, com.helpshift.common.util.a.b(this.s), "mobile");
        oVar.m = this.a;
        oVar.a(f());
        d((j) oVar);
        a(oVar);
    }

    private void a(o oVar) {
        try {
            oVar.a(this.u.b, this.b);
            if (this.d == ConversationStatus.RESOLUTION_REJECTED) {
                a(ConversationStatus.IN_PROGRESS);
            }
        } catch (RootAPIException e) {
            if (e.c == NetworkException.CONVERSATION_ARCHIVED) {
                a(ConversationStatus.ARCHIVED);
                return;
            }
            throw e;
        }
    }

    public void a(final com.helpshift.conversation.activeconversation.message.k kVar) {
        final com.helpshift.conversation.activeconversation.message.a b = kVar.b(this.t, this.s);
        if (b != null) {
            a(new com.helpshift.common.domain.f() {
                public void a() {
                    try {
                        b.a(a.this.u.b, a.this.b);
                        kVar.a(a.this.s);
                        a.this.b(b);
                    } catch (RootAPIException e) {
                        if (e.c == NetworkException.CONVERSATION_ARCHIVED) {
                            a.this.a(ConversationStatus.ARCHIVED);
                        } else {
                            kVar.a(true);
                            throw e;
                        }
                    }
                }
            });
        }
    }

    public void a() {
        if (this.d == ConversationStatus.RESOLUTION_REQUESTED && !this.A.a("showConversationResolutionQuestion")) {
            a(true);
        }
    }

    public void a(com.helpshift.conversation.dto.ConversationStatus r6) {
        /*
        r5 = this;
        r0 = r5.d;
        if (r0 != r6) goto L_0x0005;
    L_0x0004:
        return;
    L_0x0005:
        r0 = "Helpshift_ConvDM";
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "Changing conversation status from: ";
        r1.append(r2);
        r2 = r5.d;
        r1.append(r2);
        r2 = ", new status: ";
        r1.append(r2);
        r1.append(r6);
        r2 = ", for: ";
        r1.append(r2);
        r2 = r5.b;
        r1.append(r2);
        r1 = r1.toString();
        com.helpshift.util.l.a(r0, r1);
        r5.d = r6;
        r0 = com.helpshift.conversation.activeconversation.a.AnonymousClass8.a;
        r1 = r5.d;
        r1 = r1.ordinal();
        r0 = r0[r1];
        r1 = 1;
        switch(r0) {
            case 1: goto L_0x0055;
            case 2: goto L_0x0046;
            case 3: goto L_0x0041;
            default: goto L_0x003f;
        };
    L_0x003f:
        goto L_0x00bc;
    L_0x0041:
        r5.c();
        goto L_0x00bc;
    L_0x0046:
        r0 = r5.A;
        r2 = "showConversationResolutionQuestion";
        r0 = r0.a(r2);
        if (r0 != 0) goto L_0x00bc;
    L_0x0050:
        r0 = 0;
        r5.a(r1);
        goto L_0x00bd;
    L_0x0055:
        r0 = new java.util.ArrayList;
        r0.<init>();
        r2 = r5.y;
        r3 = r5.a;
        r3 = r3.longValue();
        r2 = r2.c(r3);
        r2 = r2.iterator();
    L_0x006a:
        r3 = r2.hasNext();
        if (r3 == 0) goto L_0x0084;
    L_0x0070:
        r3 = r2.next();
        r3 = (com.helpshift.conversation.activeconversation.message.j) r3;
        r4 = r3 instanceof com.helpshift.conversation.activeconversation.message.o;
        if (r4 == 0) goto L_0x006a;
    L_0x007a:
        r4 = r3.i;
        if (r4 != 0) goto L_0x006a;
    L_0x007e:
        r3 = (com.helpshift.conversation.activeconversation.message.o) r3;
        r0.add(r3);
        goto L_0x006a;
    L_0x0084:
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r0 = r0.iterator();
    L_0x008d:
        r3 = r0.hasNext();
        if (r3 == 0) goto L_0x00a4;
    L_0x0093:
        r3 = r0.next();
        r3 = (com.helpshift.conversation.activeconversation.message.o) r3;
        r3 = r3.j;
        r2.append(r3);
        r3 = "\n";
        r2.append(r3);
        goto L_0x008d;
    L_0x00a4:
        r0 = r5.s;
        r0 = r0.e();
        r3 = r5.u;
        r3 = r3.a;
        r3 = r3.longValue();
        r2 = r2.toString();
        r0.d(r3, r2);
        r5.c();
    L_0x00bc:
        r0 = r1;
    L_0x00bd:
        if (r0 == 0) goto L_0x00d0;
    L_0x00bf:
        r5.r();
        r0 = r5.j;
        if (r0 == 0) goto L_0x00cb;
    L_0x00c6:
        r0 = r5.j;
        r0.a(r6);
    L_0x00cb:
        r6 = r5.y;
        r6.b(r5);
    L_0x00d0:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.helpshift.conversation.activeconversation.a.a(com.helpshift.conversation.dto.ConversationStatus):void");
    }

    public void b() {
        this.z.a(null);
        this.z.b();
    }

    public void a(long j) {
        this.a = Long.valueOf(j);
        Iterator it = this.g.iterator();
        while (it.hasNext()) {
            ((j) it.next()).m = Long.valueOf(j);
        }
    }

    public void c() {
        if (!this.m) {
            this.t.e().c();
            this.m = true;
            this.y.b(this);
        }
    }

    public void d() {
        this.t.c(new com.helpshift.common.domain.f() {
            public void a() {
                Iterator it = a.this.g.iterator();
                while (it.hasNext()) {
                    j jVar = (j) it.next();
                    if (jVar.a()) {
                        jVar.a(a.this.t, a.this.s);
                        jVar.v = a.this.i;
                        if (jVar instanceof AdminImageAttachmentMessageDM) {
                            ((AdminImageAttachmentMessageDM) jVar).a(a.this.s);
                        }
                        a.this.a(jVar, a.this.f());
                    } else {
                        a.this.c(jVar);
                        it.remove();
                    }
                }
                a.this.g();
                a.this.h();
            }
        });
    }

    public void a(int i, ConversationCSATState conversationCSATState, String str) {
        this.o = i;
        this.n = conversationCSATState;
        this.p = str;
    }

    public boolean e() {
        switch (this.d) {
            case ARCHIVED:
            case RESOLUTION_ACCEPTED:
            case RESOLUTION_REJECTED:
                return 1 ^ this.q;
            case RESOLUTION_REQUESTED:
            case NEW:
            case IN_PROGRESS:
                return true;
            default:
                return false;
        }
    }

    public void update(Observable observable, Object obj) {
        if (observable instanceof j) {
            final j jVar = (j) observable;
            this.t.c(new com.helpshift.common.domain.f() {
                public void a() {
                    a.this.g.a(a.this.g.indexOf(jVar), jVar);
                }
            });
        }
    }

    private void r() {
        this.t.c(new com.helpshift.common.domain.f() {
            public void a() {
                boolean f = a.this.f();
                Iterator it = a.this.g.iterator();
                while (it.hasNext()) {
                    a.this.a((j) it.next(), f);
                }
            }
        });
    }

    /* Access modifiers changed, original: 0000 */
    public void a(j jVar, boolean z) {
        if (jVar instanceof o) {
            ((o) jVar).a(z);
        } else if (jVar instanceof n) {
            n nVar = (n) jVar;
            nVar.a(z);
            nVar.a(this.s);
        } else if (jVar instanceof m) {
            ((m) jVar).a(z);
        }
    }

    /* Access modifiers changed, original: 0000 */
    public boolean f() {
        switch (this.d) {
            case NEW:
            case IN_PROGRESS:
            case REJECTED:
                return true;
            case RESOLUTION_REJECTED:
                return this.C;
            default:
                return false;
        }
    }

    private void d(j jVar) {
        this.y.a(jVar);
        b(jVar);
    }

    /* Access modifiers changed, original: 0000 */
    public void b(final j jVar) {
        jVar.a(this.t, this.s);
        if (jVar.a()) {
            jVar.addObserver(this);
            this.t.c(new com.helpshift.common.domain.f() {
                public void a() {
                    a.this.g.add(jVar);
                    a.this.h();
                }
            });
        }
    }

    public void a(com.helpshift.conversation.dto.c cVar, final String str) {
        n nVar = new n(null, com.helpshift.common.util.a.b(this.s), "mobile", null, null, null, null, 0);
        nVar.d = cVar.c;
        nVar.g = cVar.b;
        nVar.b(str);
        nVar.a(f());
        nVar.m = this.a;
        d((j) nVar);
        if (str != null) {
            this.t.c(new com.helpshift.common.domain.f() {
                public void a() {
                    Iterator it = a.this.g.iterator();
                    while (it.hasNext()) {
                        j jVar = (j) it.next();
                        if (jVar.i != null && jVar.i.equals(str) && jVar.x == MessageType.REQUESTED_SCREENSHOT) {
                            ((m) jVar).a(a.this.s, true);
                            return;
                        }
                    }
                }
            });
        }
        a(nVar, cVar.e ^ 1);
    }

    private void a(n nVar, boolean z) {
        try {
            nVar.a(this.u.b, this.b, z);
            if (this.d == ConversationStatus.RESOLUTION_REJECTED) {
                a(ConversationStatus.IN_PROGRESS);
            }
        } catch (RootAPIException e) {
            if (e.c == NetworkException.CONVERSATION_ARCHIVED) {
                a(ConversationStatus.ARCHIVED);
                return;
            }
            throw e;
        }
    }

    public void a(com.helpshift.conversation.activeconversation.message.c cVar) {
        switch (cVar.x) {
            case ADMIN_IMAGE_ATTACHMENT:
                ((AdminImageAttachmentMessageDM) cVar).a(this.s, this.j);
                return;
            case ADMIN_ATTACHMENT:
                ((AdminAttachmentMessageDM) cVar).a(this.s, this.j);
                return;
            default:
                return;
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void a(final List<j> list) {
        this.t.c(new com.helpshift.common.domain.f() {
            public void a() {
                j jVar;
                HashMap hashMap = new HashMap();
                for (j jVar2 : list) {
                    hashMap.put(jVar2.i, jVar2);
                }
                Iterator it = a.this.g.iterator();
                while (it.hasNext()) {
                    jVar2 = (j) it.next();
                    String str = jVar2.i;
                    if (hashMap.containsKey(str)) {
                        jVar2.b((j) hashMap.remove(str));
                    }
                }
                for (j jVar3 : new ArrayList(hashMap.values())) {
                    if (jVar3.a()) {
                        jVar3.a(a.this.t, a.this.s);
                        jVar3.v = a.this.i;
                        switch (jVar3.x) {
                            case ADMIN_IMAGE_ATTACHMENT:
                                jVar3.m = a.this.a;
                                jVar3.addObserver(a.this);
                                a.this.g.add(jVar3);
                                ((AdminImageAttachmentMessageDM) jVar3).a(a.this.s);
                                break;
                            case ADMIN_ATTACHMENT:
                            case REQUESTED_SCREENSHOT:
                            case REQUESTED_APP_REVIEW:
                                jVar3.m = a.this.a;
                                jVar3.addObserver(a.this);
                                a.this.g.add(jVar3);
                                break;
                            case ADMIN_TEXT:
                                com.helpshift.conversation.activeconversation.message.b bVar = (com.helpshift.conversation.activeconversation.message.b) jVar3;
                                bVar.m = a.this.a;
                                jVar3.addObserver(a.this);
                                a.this.g.add(bVar);
                                break;
                            default:
                                break;
                        }
                    }
                    a.this.c(jVar3);
                }
                a.this.g();
                a.this.h();
            }
        });
    }

    /* Access modifiers changed, original: 0000 */
    public void c(j jVar) {
        if (jVar instanceof com.helpshift.conversation.activeconversation.message.l) {
            this.v.put(jVar.i, jVar);
        } else if (jVar instanceof com.helpshift.conversation.activeconversation.message.g) {
            String str = ((com.helpshift.conversation.activeconversation.message.g) jVar).a;
            if (this.v.containsKey(str)) {
                jVar = (j) this.v.remove(str);
                jVar.a(this.t, this.s);
                jVar.v = this.i;
                ((com.helpshift.conversation.activeconversation.message.l) jVar).a = true;
                this.y.a(jVar);
                this.w.add(jVar);
            }
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void g() {
        this.g.addAll(this.w);
        this.w.clear();
    }

    /* Access modifiers changed, original: 0000 */
    public void h() {
        if (this.D == null) {
            this.D = new Comparator<j>() {
                /* renamed from: a */
                public int compare(j jVar, j jVar2) {
                    try {
                        Date parse = com.helpshift.common.util.a.a.parse(jVar.k);
                        Date parse2 = com.helpshift.common.util.a.a.parse(jVar2.k);
                        if (parse.after(parse2)) {
                            return 1;
                        }
                        if (parse.before(parse2)) {
                            return -1;
                        }
                        return 0;
                    } catch (ParseException unused) {
                        return 0;
                    }
                }
            };
        }
        this.g.a(this.D);
    }

    public void b(List<j> list) {
        this.g = new HSObservableList(list);
        if (this.d == ConversationStatus.RESOLUTION_REQUESTED && list != null && list.size() > 0) {
            j jVar = null;
            for (int size = list.size() - 1; size >= 0; size--) {
                jVar = (j) list.get(size);
                if (!(jVar instanceof com.helpshift.conversation.activeconversation.message.h) && !(jVar instanceof com.helpshift.conversation.activeconversation.message.l)) {
                    break;
                }
            }
            if (jVar instanceof com.helpshift.conversation.activeconversation.message.e) {
                this.d = ConversationStatus.RESOLUTION_ACCEPTED;
            } else if (jVar instanceof com.helpshift.conversation.activeconversation.message.f) {
                this.d = ConversationStatus.RESOLUTION_REJECTED;
            }
        }
    }

    public void a(boolean z) {
        String b = com.helpshift.common.util.a.b(this.s);
        final j eVar;
        if (z) {
            eVar = new com.helpshift.conversation.activeconversation.message.e("Accepted the solution", b, "mobile");
            eVar.a(this.t, this.s);
            eVar.m = this.a;
            this.y.a(eVar);
            a(new com.helpshift.common.domain.f() {
                public void a() {
                    try {
                        eVar.a(a.this.u.b, a.this.b);
                    } catch (RootAPIException e) {
                        if (e.c == NetworkException.CONVERSATION_ARCHIVED) {
                            a.this.a(ConversationStatus.ARCHIVED);
                            return;
                        }
                        throw e;
                    }
                }
            });
            a(ConversationStatus.RESOLUTION_ACCEPTED);
            this.t.d().a(AnalyticsEventType.RESOLUTION_ACCEPTED, this.b);
            this.t.e().b("User accepted the solution");
            return;
        }
        eVar = new com.helpshift.conversation.activeconversation.message.f("Did not accept the solution", b, "mobile");
        eVar.m = this.a;
        d(eVar);
        a(new com.helpshift.common.domain.f() {
            public void a() {
                try {
                    eVar.a(a.this.u.b, a.this.b);
                } catch (RootAPIException e) {
                    if (e.c == NetworkException.CONVERSATION_ARCHIVED) {
                        a.this.a(ConversationStatus.ARCHIVED);
                        return;
                    }
                    throw e;
                }
            }
        });
        a(ConversationStatus.RESOLUTION_REJECTED);
        this.t.d().a(AnalyticsEventType.RESOLUTION_REJECTED, this.b);
        this.t.e().b("User rejected the solution");
    }

    public void a(a aVar, boolean z) {
        ConversationStatus conversationStatus = aVar.d;
        ConversationStatus conversationStatus2 = this.d;
        if (AnonymousClass8.a[conversationStatus.ordinal()] == 2 && (this.d == ConversationStatus.RESOLUTION_ACCEPTED || this.d == ConversationStatus.RESOLUTION_REJECTED)) {
            conversationStatus = conversationStatus2;
        }
        String str = aVar.k;
        if (str != null) {
            this.k = str;
        }
        this.c = aVar.c;
        this.i = aVar.i;
        this.h = aVar.h;
        this.e = aVar.e;
        this.f = aVar.f;
        if (z) {
            a(aVar.g);
            a(conversationStatus);
            return;
        }
        j jVar;
        this.d = conversationStatus;
        List<j> c = this.y.c(this.a.longValue());
        HashMap hashMap = new HashMap();
        ArrayList arrayList = new ArrayList();
        for (j jVar2 : c) {
            hashMap.put(jVar2.i, jVar2);
        }
        Iterator it = aVar.g.iterator();
        while (it.hasNext()) {
            j jVar3 = (j) it.next();
            String str2 = jVar3.i;
            if (hashMap.containsKey(str2)) {
                jVar2 = (j) hashMap.get(str2);
                jVar2.a(jVar3);
                arrayList.add(jVar2);
            } else {
                jVar3.m = this.a;
                arrayList.add(jVar3);
            }
        }
        this.g = new HSObservableList(arrayList);
    }

    public void b(String str) {
        List<j> c = this.y.c(this.a.longValue());
        final List<j> arrayList = new ArrayList();
        for (j jVar : c) {
            if (jVar.r != 1) {
                switch (jVar.x) {
                    case ADMIN_IMAGE_ATTACHMENT:
                    case ADMIN_ATTACHMENT:
                    case ADMIN_TEXT:
                    case REQUESTED_SCREENSHOT:
                    case REQUESTED_APP_REVIEW:
                    case REQUEST_FOR_REOPEN:
                        arrayList.add(jVar);
                        break;
                    default:
                        break;
                }
            }
        }
        if (arrayList.size() > 0) {
            String b = com.helpshift.common.util.a.b(this.s);
            for (j jVar2 : arrayList) {
                jVar2.o = b;
                jVar2.r = 1;
                jVar2.p = str;
                jVar2.q = this.k;
            }
            this.t.c(new com.helpshift.common.domain.f() {
                public void a() {
                    j jVar;
                    HashMap hashMap = new HashMap();
                    Iterator it = a.this.g.iterator();
                    while (it.hasNext()) {
                        jVar = (j) it.next();
                        if (jVar.n != null) {
                            hashMap.put(jVar.n, jVar);
                        }
                    }
                    for (j jVar2 : arrayList) {
                        j jVar3 = (j) hashMap.get(jVar2.n);
                        if (jVar3 != null) {
                            jVar3.o = jVar2.o;
                            jVar2.r = 1;
                            jVar3.p = jVar2.p;
                            jVar3.q = jVar2.q;
                        }
                    }
                }
            });
            this.s.f().b((List) arrayList);
            c((List) arrayList);
        }
    }

    private void c(List<j> list) {
        String str = ((j) list.get(0)).o;
        String str2 = ((j) list.get(0)).p;
        String str3 = ((j) list.get(0)).q;
        Collection arrayList = new ArrayList();
        for (j jVar : list) {
            switch (jVar.x) {
                case ADMIN_IMAGE_ATTACHMENT:
                case ADMIN_ATTACHMENT:
                    break;
                default:
                    arrayList.add(jVar.i);
                    break;
            }
        }
        HashMap hashMap = new HashMap();
        hashMap.put("message-ids", this.s.n().a(arrayList));
        hashMap.put(ShareConstants.FEED_SOURCE_PARAM, str2);
        hashMap.put("read-at", str);
        hashMap.put("profile-id", this.u.b);
        hashMap.put("mc", str3);
        try {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("/issues/");
            stringBuilder.append(this.b);
            stringBuilder.append("/messages-seen/");
            new f(new com.helpshift.common.domain.network.c(new l(new k(stringBuilder.toString(), this.t, this.s), this.s))).c(hashMap);
            for (j jVar2 : list) {
                jVar2.s = true;
            }
            this.s.f().b((List) list);
        } catch (RootAPIException e) {
            throw e;
        }
    }

    public void b(boolean z) {
        this.C = z;
        if (this.d == ConversationStatus.RESOLUTION_REJECTED) {
            r();
        }
    }

    public boolean i() {
        return this.n == ConversationCSATState.NONE && this.A.a("customerSatisfactionSurvey");
    }

    public void a(int i, String str) {
        if (i > 5) {
            i = 5;
        } else if (i < 0) {
            i = 0;
        }
        this.o = i;
        if (str != null) {
            str = str.trim();
        }
        this.p = str;
        a(ConversationCSATState.SUBMITTED_NOT_SYNCED);
        this.y.b(this);
        a(new com.helpshift.common.domain.f() {
            public void a() {
                a.this.j();
            }
        });
        this.t.e().a(this.o, this.p);
    }

    public void j() {
        HashMap hashMap = new HashMap();
        hashMap.put("rating", String.valueOf(this.o));
        hashMap.put("feedback", this.p);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("/issues/");
        stringBuilder.append(this.b);
        stringBuilder.append("/customer-survey/");
        new f(new com.helpshift.common.domain.network.c(new l(new k(stringBuilder.toString(), this.t, this.s), this.s))).c(hashMap);
        a(ConversationCSATState.SUBMITTED_SYNCED);
        this.y.b(this);
    }

    private void a(ConversationCSATState conversationCSATState) {
        if (this.n != conversationCSATState) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Update CSAT state : Conversation : ");
            stringBuilder.append(this.b);
            stringBuilder.append(", state : ");
            stringBuilder.append(conversationCSATState.toString());
            com.helpshift.util.l.a("Helpshift_ConvDM", stringBuilder.toString());
        }
        this.n = conversationCSATState;
    }

    public boolean b(int i, String str) {
        if (this.g == null || this.g.size() <= 0) {
            return false;
        }
        j jVar = (j) this.g.get(this.g.size() - 1);
        if (!(jVar instanceof com.helpshift.conversation.activeconversation.message.l)) {
            return false;
        }
        if (i == 1) {
            a(1, null, jVar.i);
            return false;
        } else if (i == 2) {
            a(3, null, jVar.i);
            return false;
        } else if (str == null || str.equals(this.b)) {
            this.d = ConversationStatus.IN_PROGRESS;
            this.m = false;
            this.y.b(this);
            final j gVar = new com.helpshift.conversation.activeconversation.message.g(null, com.helpshift.common.util.a.b(this.s), "mobile", jVar.i);
            gVar.a(this.t, this.s);
            this.s.f().a(gVar);
            jVar = (com.helpshift.conversation.activeconversation.message.l) jVar;
            jVar.a = true;
            this.s.f().a(jVar);
            a(new com.helpshift.common.domain.f() {
                public void a() {
                    gVar.a(a.this.u.b, a.this.b);
                }
            });
            return true;
        } else {
            a(2, str, jVar.i);
            return false;
        }
    }

    private void a(final com.helpshift.common.domain.f fVar) {
        this.t.b(new com.helpshift.common.domain.f() {
            public void a() {
                try {
                    fVar.a();
                } catch (RootAPIException e) {
                    if (e.c != NetworkException.NON_RETRIABLE) {
                        a.this.t.m().a(EventType.CONVERSATION, e.a());
                        throw e;
                    }
                }
            }
        });
    }

    private void a(int i, String str, String str2) {
        final j hVar = new com.helpshift.conversation.activeconversation.message.h(null, com.helpshift.common.util.a.b(this.s), "mobile", str2);
        hVar.b = i;
        hVar.c = str;
        hVar.a(this.t, this.s);
        this.s.f().a(hVar);
        a(new com.helpshift.common.domain.f() {
            public void a() {
                hVar.a(a.this.u.b, a.this.b);
            }
        });
    }

    public void a(boolean z, boolean z2) {
        this.q = z;
        if (z2) {
            this.y.b(this);
        }
    }

    public void b(boolean z, boolean z2) {
        if (this.l != z) {
            this.l = z;
            if (z2) {
                this.y.b(this);
            }
        }
    }

    public int k() {
        int i = 0;
        if (!e()) {
            return 0;
        }
        List<j> c = this.y.c(this.a.longValue());
        if (c != null) {
            for (j jVar : c) {
                if (jVar.a() && jVar.r != 1) {
                    switch (jVar.x) {
                        case ADMIN_IMAGE_ATTACHMENT:
                        case ADMIN_ATTACHMENT:
                        case ADMIN_TEXT:
                        case REQUESTED_SCREENSHOT:
                        case REQUESTED_APP_REVIEW:
                        case REQUEST_FOR_REOPEN:
                            i++;
                            break;
                        default:
                            break;
                    }
                }
            }
        }
        if (this.l) {
            i++;
        }
        return i;
    }

    public void c(boolean z) {
        List<j> c = this.y.c(this.a.longValue());
        ArrayList<d> arrayList = new ArrayList();
        ArrayList<j> arrayList2 = new ArrayList();
        HashMap hashMap = new HashMap();
        for (j jVar : c) {
            if (com.helpshift.common.c.a(jVar.i) && (jVar instanceof d)) {
                arrayList.add((d) jVar);
            }
            if (!(com.helpshift.common.c.a(jVar.o) || jVar.s)) {
                arrayList2.add(jVar);
            }
            if (jVar instanceof com.helpshift.conversation.activeconversation.message.k) {
                hashMap.put(jVar.i, (com.helpshift.conversation.activeconversation.message.k) jVar);
            }
        }
        for (d dVar : arrayList) {
            if (this.d != ConversationStatus.ARCHIVED) {
                try {
                    dVar.a(this.t, this.s);
                    dVar.a(this.u.b, this.b);
                    if (dVar instanceof com.helpshift.conversation.activeconversation.message.a) {
                        List arrayList3 = new ArrayList();
                        j jVar2 = (com.helpshift.conversation.activeconversation.message.a) dVar;
                        String str = jVar2.a;
                        if (hashMap.containsKey(str)) {
                            com.helpshift.conversation.activeconversation.message.k kVar = (com.helpshift.conversation.activeconversation.message.k) hashMap.get(str);
                            kVar.a(this.s);
                            arrayList3.add(kVar);
                        }
                        if (z) {
                            arrayList3.add(dVar);
                            b(jVar2);
                            a(arrayList3);
                        }
                    }
                } catch (RootAPIException e) {
                    if (e.c == NetworkException.CONVERSATION_ARCHIVED) {
                        a(ConversationStatus.ARCHIVED);
                    } else if (e.c != NetworkException.NON_RETRIABLE) {
                        throw e;
                    }
                }
            } else {
                return;
            }
        }
        HashMap hashMap2 = new HashMap();
        for (j jVar3 : arrayList2) {
            String str2 = jVar3.o;
            List list = (List) hashMap2.get(str2);
            if (list == null) {
                list = new ArrayList();
            }
            list.add(jVar3);
            hashMap2.put(str2, list);
        }
        for (String str3 : hashMap2.keySet()) {
            try {
                c((List) hashMap2.get(str3));
            } catch (RootAPIException e2) {
                if (e2.c != NetworkException.NON_RETRIABLE) {
                    throw e2;
                }
            }
        }
    }

    public void d(boolean z) {
        if (this.j != null) {
            this.j.b(z);
        }
    }

    public void b(ConversationStatus conversationStatus) {
        if (m()) {
            b(false, true);
        } else if ((conversationStatus == ConversationStatus.NEW || conversationStatus == ConversationStatus.IN_PROGRESS) && (this.d == ConversationStatus.RESOLUTION_REQUESTED || this.d == ConversationStatus.RESOLUTION_ACCEPTED || this.d == ConversationStatus.RESOLUTION_REJECTED)) {
            b(true, true);
        } else if (this.d == ConversationStatus.NEW || this.d == ConversationStatus.IN_PROGRESS) {
            b(false, true);
        }
    }

    public void l() {
        List<j> c = this.y.c(this.a.longValue());
        List arrayList = new ArrayList();
        for (j jVar : c) {
            if (jVar instanceof n) {
                n nVar = (n) jVar;
                String b = nVar.b();
                if (b != null) {
                    try {
                        if (new File(b).delete()) {
                            nVar.g = null;
                            arrayList.add(nVar);
                        }
                    } catch (Exception unused) {
                    }
                }
            }
        }
        this.y.b(arrayList);
    }

    public boolean m() {
        return this.j != null ? this.j.d() : false;
    }

    public String n() {
        if (this.j == null) {
            return null;
        }
        return this.j.e();
    }

    public void o() {
        if (this.A.a("enableTypingIndicator") && this.B != null) {
            this.B.a(this, this.h, this.u.b);
        }
    }

    public void p() {
        if (this.B != null) {
            this.B.c();
        }
    }

    public boolean q() {
        return this.A.a("enableTypingIndicator") && this.B != null && this.B.b();
    }
}
