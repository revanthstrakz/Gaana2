package com.helpshift.conversation.activeconversation.message;

import java.util.HashMap;
import java.util.Map;

public class h extends d {
    public String a;
    public int b;
    public String c;

    public boolean a() {
        return false;
    }

    public h(String str, String str2, String str3, String str4) {
        super(str, str2, str3, false, MessageType.FOLLOWUP_REJECTED);
        this.a = str4;
    }

    public void a(j jVar) {
        super.a(jVar);
        if (jVar instanceof h) {
            this.a = ((h) jVar).a;
        }
    }

    public void a(String str, String str2) {
        Map hashMap = new HashMap();
        hashMap.put("reason", String.valueOf(this.b));
        if (this.c != null) {
            hashMap.put("open-issue-id", String.valueOf(this.c));
        }
        String a = this.u.n().a(hashMap);
        HashMap hashMap2 = new HashMap();
        hashMap2.put("profile-id", str);
        hashMap2.put("message-text", "Rejected the follow-up");
        hashMap2.put("type", "rj");
        hashMap2.put("refers", this.a);
        hashMap2.put("message-meta", a);
        a(this.u.j().j(a_(str2).c(hashMap2).b));
        this.u.f().a((j) this);
    }
}
