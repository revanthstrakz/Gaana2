package com.helpshift.conversation.activeconversation.message;

import com.helpshift.analytics.AnalyticsEventType;
import com.helpshift.common.domain.e;
import com.helpshift.common.platform.p;
import com.helpshift.common.util.a;
import java.util.HashMap;
import java.util.Map;

public class k extends j {
    public boolean a;
    public boolean b = true;

    public boolean a() {
        return true;
    }

    public k(String str, String str2, String str3, String str4, boolean z) {
        super(str2, str3, str4, true, MessageType.REQUESTED_APP_REVIEW);
        this.i = str;
        this.a = z;
    }

    public void a(boolean z) {
        this.b = z;
        g();
    }

    public a b(e eVar, p pVar) {
        if (this.a) {
            return null;
        }
        a(false);
        j aVar = new a("Accepted review request", a.b(pVar), "mobile", this.i);
        aVar.m = this.m;
        aVar.a(eVar, pVar);
        pVar.f().a(aVar);
        Map hashMap = new HashMap();
        hashMap.put("type", "conversation");
        eVar.d().a(AnalyticsEventType.REVIEWED_APP, hashMap);
        eVar.e().b("User reviewed the app");
        return aVar;
    }

    public void a(p pVar) {
        this.b = false;
        this.a = true;
        g();
        pVar.f().a((j) this);
    }

    public void a(j jVar) {
        super.a(jVar);
        if (jVar instanceof k) {
            this.a = ((k) jVar).a;
        }
    }
}
