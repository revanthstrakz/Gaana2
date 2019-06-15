package com.helpshift.conversation.activeconversation.message;

import java.util.HashMap;

public class g extends d {
    public String a;

    public boolean a() {
        return false;
    }

    public g(String str, String str2, String str3, String str4) {
        super(str, str2, str3, false, MessageType.FOLLOWUP_ACCEPTED);
        this.a = str4;
    }

    public void a(j jVar) {
        super.a(jVar);
        if (jVar instanceof g) {
            this.a = ((g) jVar).a;
        }
    }

    public void a(String str, String str2) {
        HashMap hashMap = new HashMap();
        hashMap.put("profile-id", str);
        hashMap.put("message-text", "Accepted the follow-up");
        hashMap.put("type", "ra");
        hashMap.put("refers", this.a);
        a(this.u.j().k(a_(str2).c(hashMap).b));
        this.u.f().a((j) this);
    }
}
