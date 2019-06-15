package com.helpshift.conversation.activeconversation.message;

import java.util.HashMap;

public class f extends d {
    public boolean a() {
        return true;
    }

    public f(String str, String str2, String str3) {
        super(str, str2, str3, false, MessageType.CONFIRMATION_REJECTED);
    }

    public void a(String str, String str2) {
        HashMap hashMap = new HashMap();
        hashMap.put("profile-id", str);
        hashMap.put("message-text", this.j);
        hashMap.put("type", "ncr");
        hashMap.put("refers", "");
        f f = this.u.j().f(a_(str2).c(hashMap).b);
        a(f);
        this.i = f.i;
        g();
        this.u.f().a((j) this);
    }
}
