package com.helpshift.conversation.b;

import com.helpshift.analytics.AnalyticsEventType;
import com.helpshift.common.AutoRetryFailedEventDM.EventType;
import com.helpshift.common.domain.Poller;
import com.helpshift.common.domain.e;
import com.helpshift.common.domain.f;
import com.helpshift.common.domain.i;
import com.helpshift.common.domain.network.h;
import com.helpshift.common.domain.network.j;
import com.helpshift.common.domain.network.k;
import com.helpshift.common.platform.network.g;
import com.helpshift.common.platform.p;
import com.helpshift.conversation.ConversationInboxPoller;
import com.helpshift.conversation.activeconversation.c;
import com.helpshift.conversation.activeconversation.d;
import com.helpshift.conversation.dto.ConversationStatus;
import com.helpshift.conversation.states.ConversationCSATState;
import com.helpshift.util.l;
import java.lang.ref.WeakReference;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.WeakHashMap;
import java.util.concurrent.atomic.AtomicReference;

public class a implements com.helpshift.common.a {
    final p a;
    final com.helpshift.account.a.b b;
    final e c;
    final Long d;
    final com.helpshift.conversation.a.a e;
    public AtomicReference<c<Integer>> f = null;
    private final com.helpshift.conversation.a.b g;
    private final com.helpshift.g.b.a h;
    private final com.helpshift.configuration.a.a i;
    private final d j;
    private final ConversationInboxPoller k;
    private h l;
    private WeakReference<b> m;
    private boolean n;
    private boolean o;
    private boolean p;
    private int q = -1;
    private Map<com.helpshift.conversation.activeconversation.a, Long> r = Collections.synchronizedMap(new WeakHashMap());
    private long s = -1;

    private class a {
        final String a;
        final String b;
        final String c;
        final com.helpshift.conversation.dto.c d;
        private final f f = new com.helpshift.common.domain.h(new f() {
            public void a() {
                a.this.b(a.this.a, a.this.b, a.this.c, a.this.d);
            }
        });

        a(String str, String str2, String str3, com.helpshift.conversation.dto.c cVar) {
            this.a = str;
            this.b = str2;
            this.c = str3;
            this.d = cVar;
        }

        /* Access modifiers changed, original: 0000 */
        public f a() {
            return this.f;
        }
    }

    public interface b {
        void a(long j);

        void a(Exception exception);
    }

    public ConversationInboxPoller a() {
        return this.k;
    }

    public a(p pVar, e eVar, com.helpshift.account.a.b bVar) {
        this.a = pVar;
        this.c = eVar;
        this.b = bVar;
        this.d = bVar.a;
        this.g = pVar.e();
        this.e = pVar.f();
        this.h = pVar.l();
        this.i = eVar.c();
        this.k = new ConversationInboxPoller(bVar, this.i, p());
        this.j = new d(eVar, pVar);
        this.c.m().a(EventType.CONVERSATION, (com.helpshift.common.a) this);
    }

    private Poller p() {
        return new Poller(this.c, new i<Integer>() {
            /* renamed from: b */
            public synchronized Integer a() {
                return Integer.valueOf(a.this.k());
            }
        });
    }

    public void b() {
        for (com.helpshift.conversation.activeconversation.a aVar : this.e.b(this.d.longValue())) {
            com.helpshift.conversation.activeconversation.a b = b(aVar.a);
            if (b != null) {
                a(b, true);
            } else {
                a(aVar, false);
            }
        }
    }

    private void a(com.helpshift.conversation.activeconversation.a aVar, boolean z) {
        aVar.a(this.a, this.c, this.b);
        aVar.c(z);
        if (aVar.n == ConversationCSATState.SUBMITTED_NOT_SYNCED) {
            aVar.j();
        }
    }

    public void a(boolean z) {
        this.p = z;
    }

    public com.helpshift.conversation.dto.a c() {
        return this.g.a(this.d.longValue());
    }

    public String d() {
        return this.g.f(this.d.longValue());
    }

    public void a(String str, int i) {
        this.g.a(this.d.longValue(), new com.helpshift.conversation.dto.a(str, System.nanoTime(), i));
    }

    public void a(String str) {
        this.g.a(this.d.longValue(), str);
    }

    public void b(String str) {
        this.g.b(this.d.longValue(), str);
    }

    public String e() {
        String b = this.g.b(this.d.longValue());
        return com.helpshift.common.c.a(b) ? this.b.d : b;
    }

    public String f() {
        String c = this.g.c(this.d.longValue());
        return com.helpshift.common.c.a(c) ? this.b.e : c;
    }

    public void a(com.helpshift.conversation.dto.c cVar) {
        this.g.a(this.d.longValue(), cVar);
    }

    public com.helpshift.conversation.dto.c g() {
        return this.g.d(this.d.longValue());
    }

    public void c(String str) {
        this.g.e(this.d.longValue(), str);
    }

    public String h() {
        return this.g.g(this.d.longValue());
    }

    public ArrayList d(String str) {
        return this.h.a(str);
    }

    public void i() {
        this.h.a();
    }

    public void b(boolean z) {
        this.n = z;
    }

    public void a(b bVar) {
        this.m = new WeakReference(bVar);
    }

    public void b(b bVar) {
        if (this.m != null && this.m.get() == bVar) {
            this.m = new WeakReference(null);
        }
    }

    public void a(String str, String str2, String str3, com.helpshift.conversation.dto.c cVar) {
        this.c.b(new a(str, str2, str3, cVar).a());
    }

    /* Access modifiers changed, original: 0000 */
    public void b(String str, String str2, String str3, com.helpshift.conversation.dto.c cVar) {
        this.o = true;
        com.helpshift.conversation.activeconversation.a b = b(str, str2, str3);
        a(b);
        a(b, cVar);
        this.o = false;
        if (this.m.get() != null) {
            ((b) this.m.get()).a(b.a.longValue());
        }
    }

    private synchronized void a(com.helpshift.conversation.activeconversation.a aVar) {
        this.s = aVar.a.longValue();
        this.r.put(aVar, aVar.a);
    }

    private com.helpshift.conversation.activeconversation.a b(String str, String str2, String str3) {
        try {
            com.helpshift.conversation.activeconversation.a a = a(str, str2, str3);
            a("", 0);
            if (!this.i.e()) {
                a(str2);
                b(str3);
            }
            this.g.d(this.d.longValue(), null);
            b(a);
            g(a.b);
            this.c.e().a(str);
            return a;
        } catch (Exception e) {
            this.o = false;
            if (this.m.get() != null) {
                ((b) this.m.get()).a(e);
            }
            throw e;
        }
    }

    private void b(com.helpshift.conversation.activeconversation.a aVar) {
        if (this.n) {
            aVar.b();
        }
    }

    private void a(com.helpshift.conversation.activeconversation.a aVar, com.helpshift.conversation.dto.c cVar) {
        if (cVar != null && cVar.b != null) {
            try {
                aVar.a(cVar, null);
            } catch (Exception unused) {
            }
            a(null);
        }
    }

    private void g(String str) {
        this.c.d().a(AnalyticsEventType.CONVERSATION_POSTED, str);
    }

    private void b(String str, String str2) {
        if (this.b.b == null) {
            l.a("Helpshift_ConvInboxDM", "Create new conversation : Profile not registered. Trying to register profile.");
            this.b.a(str, str2);
            k();
        }
    }

    public com.helpshift.conversation.activeconversation.a a(String str, String str2, String str3) {
        b(str2, str3);
        com.helpshift.conversation.activeconversation.a aVar = new com.helpshift.conversation.activeconversation.a(this.a, this.c, this.b, this.j);
        this.b.f();
        aVar.a(str, str2, str3, this.b.h, this.b.i);
        this.k.b();
        return aVar;
    }

    public boolean j() {
        return this.o;
    }

    public int k() {
        com.helpshift.account.a.b bVar = this.b;
        if (com.helpshift.common.c.a(bVar.b)) {
            return j.g.intValue();
        }
        HashMap hashMap = new HashMap();
        hashMap.put("profile-id", bVar.b);
        String e = this.g.e(this.d.longValue());
        if (e == null) {
            hashMap.put("since", "");
            hashMap.put("mc", "");
        } else {
            hashMap.put("since", e);
            hashMap.put("mc", s());
        }
        com.helpshift.conversation.activeconversation.a q = q();
        if (q != null) {
            hashMap.put("chat-launch-source", q.n());
        }
        hashMap.put("ucrm", String.valueOf(this.p));
        g c = r().c(hashMap);
        com.helpshift.conversation.dto.b i = this.a.j().i(c.b);
        this.g.c(this.d.longValue(), i.a);
        if (i.b != null && i.b.size() > 0) {
            List a = a(this.e.b(this.d.longValue()), i.b);
            a(a);
            c(a);
            if (!bVar.j && this.i.a("enableInAppNotification")) {
                b(a);
            }
        }
        if (this.f != null) {
            final c cVar = (c) this.f.get();
            if (cVar != null) {
                this.c.c(new f() {
                    public void a() {
                        cVar.a(Integer.valueOf(a.this.n()));
                    }
                });
            }
        }
        return c.a;
    }

    private com.helpshift.conversation.activeconversation.a q() {
        return b(Long.valueOf(this.s));
    }

    private void b(List<com.helpshift.conversation.activeconversation.a> list) {
        for (com.helpshift.conversation.activeconversation.a aVar : list) {
            if (d(aVar)) {
                aVar.a(this.a, this.c, this.b);
                c(aVar);
            }
        }
    }

    private void c(com.helpshift.conversation.activeconversation.a aVar) {
        if (this.i.a("enableInAppNotification")) {
            a(aVar.a, aVar.b, aVar.k(), "inapp", this.a.d().f());
        }
    }

    private boolean d(com.helpshift.conversation.activeconversation.a aVar) {
        boolean z = false;
        if (aVar == null || this.b.a.longValue() != aVar.r || com.helpshift.common.c.a(aVar.b)) {
            return false;
        }
        com.helpshift.conversation.activeconversation.a q = q();
        if (!this.p) {
            return true;
        }
        if (q == null || !aVar.b.equals(q.b)) {
            z = true;
        }
        return z;
    }

    private void a(Long l, String str, int i, String str2, String str3) {
        if (i > 0) {
            final Long l2 = l;
            final String str4 = str;
            final int i2 = i;
            final String str5 = str2;
            final String str6 = str3;
            this.c.c(new f() {
                public void a() {
                    a.this.a.a(l2, str4, i2, str5, str6);
                }
            });
        }
    }

    private void c(List<com.helpshift.conversation.activeconversation.a> list) {
        com.helpshift.conversation.activeconversation.a l = l();
        String str = l != null ? l.b : null;
        com.helpshift.conversation.activeconversation.a q = q();
        for (com.helpshift.conversation.activeconversation.a aVar : list) {
            aVar.a(this.a, this.c, this.b);
            boolean b = aVar.b(this.q, str);
            if (b && q != null && aVar.b.equals(q.b)) {
                q.m = false;
                q.a(aVar.d);
            }
            if (b && d(aVar)) {
                c(aVar);
            }
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void a(List<com.helpshift.conversation.activeconversation.a> list) {
        for (com.helpshift.conversation.activeconversation.a a : list) {
            a.a(this.a, this.c, this.b);
        }
        this.e.a((List) list);
    }

    private List<com.helpshift.conversation.activeconversation.a> a(List<com.helpshift.conversation.activeconversation.a> list, List<com.helpshift.conversation.activeconversation.a> list2) {
        HashMap hashMap = new HashMap();
        ArrayList arrayList = new ArrayList();
        for (com.helpshift.conversation.activeconversation.a aVar : list) {
            hashMap.put(aVar.b, aVar);
        }
        for (com.helpshift.conversation.activeconversation.a aVar2 : list2) {
            String str = aVar2.b;
            ConversationStatus conversationStatus;
            if (hashMap.containsKey(str)) {
                com.helpshift.conversation.activeconversation.a aVar3 = (com.helpshift.conversation.activeconversation.a) hashMap.get(str);
                com.helpshift.conversation.activeconversation.a b = b(aVar3.a);
                aVar3.a(this.a, this.c, this.b);
                ConversationStatus conversationStatus2 = aVar3.d;
                aVar3.a(aVar2, false);
                arrayList.add(aVar3);
                if ((b == null || !b.m()) && aVar3.d == ConversationStatus.REJECTED) {
                    com.helpshift.conversation.activeconversation.a l = l();
                    if (l != null && l.a.equals(aVar3.a)) {
                        aVar3.c();
                    }
                }
                if (b == null || !str.equals(b.b)) {
                    aVar3.b(conversationStatus2);
                } else {
                    conversationStatus = b.d;
                    b.a(aVar2, true);
                    b.b(conversationStatus);
                }
            } else {
                conversationStatus = aVar2.d;
                if (conversationStatus != null && (conversationStatus == ConversationStatus.RESOLUTION_ACCEPTED || conversationStatus == ConversationStatus.RESOLUTION_REJECTED || conversationStatus == ConversationStatus.REJECTED || conversationStatus == ConversationStatus.ARCHIVED)) {
                    aVar2.q = true;
                }
                arrayList.add(aVar2);
            }
        }
        return arrayList;
    }

    private h r() {
        if (this.l == null) {
            this.l = new com.helpshift.common.domain.network.f(new com.helpshift.common.domain.network.l(new k("/my-issues/", this.c, this.a), this.a));
        }
        return this.l;
    }

    private String s() {
        Map hashMap = new HashMap();
        for (com.helpshift.conversation.activeconversation.a aVar : this.e.b(this.d.longValue())) {
            hashMap.put(aVar.b, aVar.k);
        }
        return this.a.n().a(hashMap);
    }

    public com.helpshift.conversation.activeconversation.a a(Long l) {
        com.helpshift.conversation.activeconversation.a b = b(l);
        if (b == null) {
            com.helpshift.conversation.activeconversation.a a = this.e.a(l.longValue());
            if (a == null) {
                return b;
            }
            a.a(this.a, this.c, this.b);
            a.a(this.j);
            a(a);
            return a;
        }
        a(b);
        return b;
    }

    private com.helpshift.conversation.activeconversation.a b(Long l) {
        for (Entry entry : this.r.entrySet()) {
            if (((Long) entry.getValue()).equals(l)) {
                return (com.helpshift.conversation.activeconversation.a) entry.getKey();
            }
        }
        return null;
    }

    public boolean a(long j) {
        com.helpshift.conversation.activeconversation.a b = b(Long.valueOf(j));
        if (b == null) {
            b = this.e.a(j);
        }
        return b != null ? b.e() : false;
    }

    public com.helpshift.conversation.activeconversation.a l() {
        com.helpshift.conversation.activeconversation.a aVar = null;
        if (!this.i.a("disableInAppConversation")) {
            List<com.helpshift.conversation.activeconversation.a> b = this.e.b(this.d.longValue());
            if (b.size() == 0) {
                return null;
            }
            List arrayList = new ArrayList();
            for (com.helpshift.conversation.activeconversation.a aVar2 : b) {
                if (aVar2.e()) {
                    arrayList.add(aVar2);
                }
            }
            if (arrayList.size() > 0) {
                aVar = d(arrayList);
            }
        }
        return aVar;
    }

    private com.helpshift.conversation.activeconversation.a d(List<com.helpshift.conversation.activeconversation.a> list) {
        return (com.helpshift.conversation.activeconversation.a) Collections.max(list, new Comparator<com.helpshift.conversation.activeconversation.a>() {
            /* renamed from: a */
            public int compare(com.helpshift.conversation.activeconversation.a aVar, com.helpshift.conversation.activeconversation.a aVar2) {
                try {
                    Date parse = com.helpshift.common.util.a.a.parse(aVar.f);
                    Date parse2 = com.helpshift.common.util.a.a.parse(aVar2.f);
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
        });
    }

    public boolean m() {
        return this.g.h(this.d.longValue());
    }

    public void c(boolean z) {
        this.g.a(this.d.longValue(), z);
    }

    public void a(int i) {
        this.q = i;
    }

    public void a(String str, String str2) {
        int i;
        com.helpshift.conversation.activeconversation.a a = this.e.a(str);
        if (com.helpshift.common.c.a(str2)) {
            str2 = this.a.d().f();
        }
        String str3 = str2;
        com.helpshift.conversation.a.c a2 = this.g.a(str);
        if (a2 == null) {
            i = 1;
            str2 = str3;
        } else {
            int i2 = a2.a + 1;
            str2 = a2.b;
            i = i2;
        }
        this.g.a(str, new com.helpshift.conversation.a.c(i, str2));
        if (d(a) && i > 0) {
            a(a.a, str, i, "push", str3);
        }
    }

    public void e(String str) {
        this.g.a(str, null);
        this.c.e().a(0);
    }

    public void f(final String str) {
        this.c.c(new f() {
            public void a() {
                a.this.a.a(str);
            }
        });
    }

    public int n() {
        int i = 0;
        if (this.p) {
            return 0;
        }
        int k;
        String str = null;
        com.helpshift.conversation.activeconversation.a t = t();
        if (t != null) {
            str = t.b;
            k = t.k();
        } else {
            k = 0;
        }
        if (str != null) {
            com.helpshift.conversation.a.c a = this.g.a(str);
            if (a != null) {
                i = a.a;
            }
        }
        return Math.max(k, i);
    }

    private com.helpshift.conversation.activeconversation.a t() {
        com.helpshift.conversation.activeconversation.a q = q();
        if (q != null) {
            return q;
        }
        com.helpshift.conversation.activeconversation.a l = l();
        if (l == null) {
            return q;
        }
        l.a(this.a, this.c, this.b);
        return l;
    }

    public void o() {
        this.c.b(new f() {
            public void a() {
                for (com.helpshift.conversation.activeconversation.a aVar : a.this.e.b(a.this.d.longValue())) {
                    if (!aVar.e()) {
                        aVar.a(a.this.a, a.this.c, a.this.b);
                        aVar.l();
                    }
                }
            }
        });
    }
}
