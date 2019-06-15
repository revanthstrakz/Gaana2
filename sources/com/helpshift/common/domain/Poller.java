package com.helpshift.common.domain;

import com.helpshift.common.b.c;
import com.helpshift.common.b.c.a;
import com.helpshift.common.b.c.b;
import com.helpshift.common.domain.network.j;
import com.helpshift.common.exception.NetworkException;
import com.helpshift.common.exception.RootAPIException;
import java.util.concurrent.TimeUnit;

public class Poller {
    boolean a = false;
    boolean b;
    boolean c;
    public ActivePollingInterval d = null;
    private final e e;
    private final f f;
    private final f g;

    public enum ActivePollingInterval {
        AGGRESSIVE,
        CONSERVATIVE
    }

    public Poller(e eVar, i<Integer> iVar) {
        this.e = eVar;
        this.f = b((i) iVar);
        this.g = a((i) iVar);
    }

    public synchronized void a(ActivePollingInterval activePollingInterval) {
        this.a = true;
        b(activePollingInterval);
    }

    public synchronized void a() {
        this.a = false;
        this.d = null;
    }

    private void b(ActivePollingInterval activePollingInterval) {
        if (activePollingInterval != null && !activePollingInterval.equals(this.d)) {
            this.d = activePollingInterval;
            switch (activePollingInterval) {
                case AGGRESSIVE:
                    b(0);
                    return;
                case CONSERVATIVE:
                    a(0);
                    return;
                default:
                    return;
            }
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void a(long j) {
        if (!this.b) {
            this.b = true;
            this.e.b(this.g, j);
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void b(long j) {
        if (!this.c) {
            this.c = true;
            this.e.b(this.f, j);
        }
    }

    private f a(final i<Integer> iVar) {
        return new f() {
            private c c = new a().a(com.helpshift.common.b.a.a(5, TimeUnit.SECONDS)).b(com.helpshift.common.b.a.a(1, TimeUnit.MINUTES)).a(0.1f).b(2.0f).a(b.a).a();

            public void a() {
                Poller.this.b = false;
                if (Poller.this.a && Poller.this.d == ActivePollingInterval.CONSERVATIVE) {
                    int intValue;
                    try {
                        intValue = ((Integer) iVar.a()).intValue();
                    } catch (RootAPIException e) {
                        if (e.c instanceof NetworkException) {
                            intValue = j.b.intValue();
                        } else {
                            throw e;
                        }
                    }
                    if (intValue == j.h.intValue()) {
                        this.c.a();
                    }
                    long a = this.c.a(intValue);
                    if (a != -100) {
                        Poller.this.a(a);
                        return;
                    }
                    return;
                }
                this.c.a();
            }
        };
    }

    private f b(final i<Integer> iVar) {
        return new f() {
            private c c = new a().a(com.helpshift.common.b.a.a(3, TimeUnit.SECONDS)).b(com.helpshift.common.b.a.a(3, TimeUnit.SECONDS)).a(0.0f).b(1.0f).a(b.a).a();

            public void a() {
                Poller.this.c = false;
                if (Poller.this.a && Poller.this.d == ActivePollingInterval.AGGRESSIVE) {
                    int intValue;
                    try {
                        intValue = ((Integer) iVar.a()).intValue();
                    } catch (RootAPIException e) {
                        if (e.c instanceof NetworkException) {
                            intValue = j.b.intValue();
                        } else {
                            throw e;
                        }
                    }
                    if (intValue == j.h.intValue()) {
                        this.c.a();
                    }
                    long a = this.c.a(intValue);
                    if (a != -100) {
                        Poller.this.b(a);
                        return;
                    }
                    return;
                }
                this.c.a();
            }
        };
    }
}
