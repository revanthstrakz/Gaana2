package com.helpshift.conversation.activeconversation.message;

import java.util.HashMap;

public class e extends d {
    public boolean a() {
        return false;
    }

    public e(String str, String str2, String str3) {
        super(str, str2, str3, false, MessageType.CONFIRMATION_ACCEPTED);
    }

    public void a(String str, String str2) {
        HashMap hashMap = new HashMap();
        hashMap.put("profile-id", str);
        hashMap.put("message-text", this.j);
        hashMap.put("type", "ca");
        hashMap.put("refers", "");
        e g = this.u.j().g(a_(str2).c(hashMap).b);
        a(g);
        this.i = g.i;
        this.u.f().a((j) this);
    }
}
