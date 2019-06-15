package com.helpshift.g;

import com.helpshift.analytics.AnalyticsEventType;
import com.helpshift.common.AutoRetryFailedEventDM.EventType;
import com.helpshift.common.domain.e;
import com.helpshift.common.domain.f;
import com.helpshift.common.domain.network.c;
import com.helpshift.common.domain.network.k;
import com.helpshift.common.domain.network.l;
import com.helpshift.common.exception.NetworkException;
import com.helpshift.common.exception.RootAPIException;
import com.helpshift.common.platform.p;
import java.util.HashMap;
import java.util.Map;

public class a implements com.helpshift.common.a {
    final e a;
    com.helpshift.g.a.a b;
    private final p c;

    public a(e eVar, p pVar) {
        this.a = eVar;
        this.c = pVar;
        this.b = pVar.r();
        this.a.m().a(EventType.FAQ, (com.helpshift.common.a) this);
    }

    public void a(final String str, final boolean z) {
        AnalyticsEventType analyticsEventType;
        this.a.b(new f() {
            public void a() {
                try {
                    a.this.b(str, z);
                } catch (RootAPIException e) {
                    if (e.c != NetworkException.NON_RETRIABLE) {
                        a.this.b.a(str, z);
                        a.this.a.m().a(EventType.FAQ, e.a());
                        throw e;
                    }
                }
            }
        });
        if (z) {
            analyticsEventType = AnalyticsEventType.MARKED_HELPFUL;
        } else {
            analyticsEventType = AnalyticsEventType.MARKED_UNHELPFUL;
        }
        this.a.d().a(analyticsEventType, str);
    }

    public void b() {
        Map a = this.b.a();
        if (a != null) {
            for (String str : a.keySet()) {
                try {
                    b(str, ((Boolean) a.get(str)).booleanValue());
                    this.b.a(str);
                } catch (RootAPIException e) {
                    if (e.c == NetworkException.NON_RETRIABLE) {
                        this.b.a(str);
                    } else {
                        throw e;
                    }
                }
            }
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void b(String str, boolean z) {
        StringBuilder stringBuilder;
        if (z) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("/faqs/");
            stringBuilder.append(str);
            stringBuilder.append("/helpful/");
            str = stringBuilder.toString();
        } else {
            stringBuilder = new StringBuilder();
            stringBuilder.append("/faqs/");
            stringBuilder.append(str);
            stringBuilder.append("/unhelpful/");
            str = stringBuilder.toString();
        }
        new com.helpshift.common.domain.network.f(new l(new c(new k(str, this.a, this.c)), this.c)).c(new HashMap());
    }
}
