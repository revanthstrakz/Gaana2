package com.helpshift.common;

import com.helpshift.common.b.c;
import com.helpshift.common.b.c.a;
import com.helpshift.common.b.c.b;
import com.helpshift.common.domain.e;
import com.helpshift.common.domain.f;
import com.helpshift.common.exception.RootAPIException;
import com.helpshift.common.platform.p;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class AutoRetryFailedEventDM {
    private final e a;
    private final p b;
    private final c c;
    private AtomicBoolean d = new AtomicBoolean(false);
    private Map<EventType, a> e = new HashMap();
    private Set<EventType> f = Collections.synchronizedSet(new HashSet());

    public enum EventType {
        ACCOUNT,
        CONVERSATION,
        FAQ,
        ANALYTICS
    }

    public AutoRetryFailedEventDM(e eVar, p pVar) {
        this.a = eVar;
        this.b = pVar;
        this.c = new a().a(com.helpshift.common.b.a.a(5, TimeUnit.SECONDS)).b(com.helpshift.common.b.a.a(60, TimeUnit.SECONDS)).a(10).a(0.1f).b(2.0f).a(b.a).a();
    }

    public void a(EventType eventType, a aVar) {
        this.e.put(eventType, aVar);
    }

    public void a(EventType eventType, int i) {
        this.f.add(eventType);
        a(i);
    }

    public void a() {
        this.f.add(EventType.ACCOUNT);
        this.f.add(EventType.CONVERSATION);
        this.f.add(EventType.FAQ);
        this.f.add(EventType.ANALYTICS);
        this.a.b(new f() {
            public void a() {
                AutoRetryFailedEventDM.this.b();
            }
        });
    }

    private void a(int i) {
        if (this.d.compareAndSet(false, true)) {
            long a = this.c.a(i);
            if (a != -100) {
                this.a.b(new f() {
                    public void a() {
                        AutoRetryFailedEventDM.this.b();
                    }
                }, a);
            } else {
                this.d.compareAndSet(true, false);
            }
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void b() {
        this.d.compareAndSet(true, false);
        if (this.b.x()) {
            try {
                for (EventType eventType : new ArrayList(this.f)) {
                    a aVar = (a) this.e.get(eventType);
                    if (aVar != null) {
                        aVar.b();
                    }
                    this.f.remove(eventType);
                }
                this.c.a();
            } catch (RootAPIException e) {
                a(e.a());
            }
            return;
        }
        a(0);
    }
}
