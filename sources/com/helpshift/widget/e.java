package com.helpshift.widget;

import com.helpshift.conversation.dto.c;

public class e extends j {
    private c a;
    private boolean b = true;

    public void a(c cVar) {
        this.a = cVar;
        e();
    }

    public c a() {
        return this.a;
    }

    public String b() {
        if (this.a == null) {
            return "";
        }
        return this.a.b == null ? "" : this.a.b;
    }

    public void a(boolean z) {
        this.b = z;
        e();
    }

    public boolean c() {
        return this.b;
    }
}
