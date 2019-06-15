package com.helpshift.conversation.activeconversation.message;

import com.helpshift.common.c;
import com.helpshift.common.exception.RootAPIException;
import java.util.HashMap;

public class a extends d {
    public String a;

    public a(String str, String str2, String str3, String str4) {
        super(str, str2, str3, false, MessageType.ACCEPTED_APP_REVIEW);
        this.a = str4;
    }

    public void a(j jVar) {
        super.a(jVar);
        if (jVar instanceof a) {
            this.a = ((a) jVar).a;
        }
    }

    public boolean a() {
        return c.a(this.i) ^ 1;
    }

    public void a(String str, String str2) throws RootAPIException {
        HashMap hashMap = new HashMap();
        hashMap.put("profile-id", str);
        hashMap.put("message-text", this.j);
        hashMap.put("type", "ar");
        hashMap.put("refers", this.a);
        a e = this.u.j().e(a_(str2).c(hashMap).b);
        a(e);
        this.i = e.i;
        this.u.f().a((j) this);
    }
}
