package com.helpshift.conversation.activeconversation.message;

import com.helpshift.common.platform.p;

public class m extends j {
    public boolean a;
    private boolean b = true;

    public boolean a() {
        return true;
    }

    public m(String str, String str2, String str3, String str4, boolean z) {
        super(str2, str3, str4, true, MessageType.REQUESTED_SCREENSHOT);
        this.i = str;
        this.a = z;
    }

    public boolean b() {
        return !this.a && this.b;
    }

    public void a(boolean z) {
        this.b = z;
        g();
    }

    public void a(p pVar, boolean z) {
        this.a = z;
        pVar.f().a((j) this);
        g();
    }

    public void a(j jVar) {
        super.a(jVar);
        if (jVar instanceof m) {
            this.a = ((m) jVar).a;
        }
    }
}
