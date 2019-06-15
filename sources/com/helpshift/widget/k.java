package com.helpshift.widget;

import com.helpshift.common.c;
import com.helpshift.configuration.a.a;
import com.helpshift.conversation.activeconversation.message.ConversationFooterState;
import com.helpshift.conversation.dto.ConversationStatus;
import java.util.concurrent.TimeUnit;

public class k {
    private final a a;
    private final com.helpshift.conversation.b.a b;

    public k(a aVar, com.helpshift.conversation.b.a aVar2) {
        this.a = aVar;
        this.b = aVar2;
    }

    public a a() {
        a aVar = new a();
        aVar.b(this.b.j() ^ 1);
        return aVar;
    }

    public i b() {
        return new i(this.b.h());
    }

    public void a(String str) {
        this.b.c(str);
    }

    public a a(com.helpshift.conversation.activeconversation.a aVar, boolean z) {
        a aVar2 = new a();
        a(aVar2, aVar, z);
        return aVar2;
    }

    public void a(a aVar, com.helpshift.conversation.activeconversation.a aVar2, boolean z) {
        boolean z2 = true;
        if (!(aVar2.d == ConversationStatus.NEW || aVar2.d == ConversationStatus.IN_PROGRESS || ((aVar2.d == ConversationStatus.RESOLUTION_REJECTED && z) || (z && aVar2.d == ConversationStatus.REJECTED)))) {
            z2 = false;
        }
        aVar.b(z2);
    }

    public a c() {
        a aVar = new a();
        aVar.b(this.a.a("showConversationInfoScreen"));
        return aVar;
    }

    public b b(com.helpshift.conversation.activeconversation.a aVar, boolean z) {
        b bVar = new b();
        a(bVar, aVar, z);
        return bVar;
    }

    public void a(b bVar, com.helpshift.conversation.activeconversation.a aVar, boolean z) {
        ConversationFooterState conversationFooterState = ConversationFooterState.NONE;
        if (aVar.d == ConversationStatus.RESOLUTION_ACCEPTED) {
            if (aVar.i()) {
                conversationFooterState = ConversationFooterState.CSAT_RATING;
            } else {
                conversationFooterState = ConversationFooterState.START_NEW_CONVERSATION;
            }
        } else if (aVar.d == ConversationStatus.ARCHIVED) {
            conversationFooterState = ConversationFooterState.ARCHIVAL_MESSAGE;
        } else if (aVar.d == ConversationStatus.RESOLUTION_REQUESTED && this.a.a("showConversationResolutionQuestion")) {
            conversationFooterState = ConversationFooterState.CONVERSATION_ENDED_MESSAGE;
        } else if (aVar.d == ConversationStatus.RESOLUTION_REJECTED) {
            if (z) {
                conversationFooterState = ConversationFooterState.NONE;
            } else if (aVar.i()) {
                conversationFooterState = ConversationFooterState.CSAT_RATING;
            } else {
                conversationFooterState = ConversationFooterState.START_NEW_CONVERSATION;
            }
        }
        bVar.a(conversationFooterState);
    }

    public a a(com.helpshift.conversation.activeconversation.a aVar) {
        a aVar2 = new a();
        a(aVar2, aVar);
        return aVar2;
    }

    public void a(a aVar, com.helpshift.conversation.activeconversation.a aVar2) {
        boolean z = aVar2.d == ConversationStatus.RESOLUTION_REQUESTED && this.a.a("showConversationResolutionQuestion");
        aVar.b(z);
    }

    public a d() {
        a aVar = new a();
        aVar.b(j());
        return aVar;
    }

    public a a(e eVar) {
        a aVar = new a();
        aVar.b(c(eVar));
        return aVar;
    }

    private boolean c(e eVar) {
        boolean z = false;
        if (!j()) {
            return false;
        }
        if (c.a(eVar.b()) && !this.b.j()) {
            z = true;
        }
        return z;
    }

    private boolean j() {
        return this.a.a("fullPrivacy") ^ 1;
    }

    public c e() {
        c cVar = new c(this.a.f());
        String str = "";
        String str2 = "";
        String d = this.b.d();
        String c = this.a.c("conversationPrefillText");
        com.helpshift.conversation.dto.a c2 = this.b.c();
        if (c2 != null) {
            if (c2.c != 2 || c.a(c)) {
                str2 = c2.a;
                long nanoTime = System.nanoTime() - c2.b;
                if (nanoTime < 0 || TimeUnit.NANOSECONDS.toSeconds(nanoTime) > 7200) {
                    this.b.a("", 0);
                    str2 = "";
                }
            } else {
                str2 = c;
            }
        }
        if (!c.a(str2)) {
            str = str2;
        } else if (!c.a(d)) {
            str = d;
        } else if (!c.a(c)) {
            this.b.a("", 2);
            str = c;
        }
        cVar.a(str);
        return cVar;
    }

    public void a(c cVar) {
        this.b.a(cVar.d(), 1);
    }

    public f f() {
        f fVar = new f();
        fVar.a(!this.a.e() ? this.b.e() : "Anonymous");
        return fVar;
    }

    public d g() {
        d dVar = new d();
        dVar.a(k());
        if (!this.a.e()) {
            dVar.a(this.b.f());
        }
        return dVar;
    }

    private boolean k() {
        if (this.a.a("fullPrivacy")) {
            return false;
        }
        if (this.a.a("requireNameAndEmail")) {
            return true;
        }
        if (this.a.a("profileFormEnable") && this.a.a("requireEmail")) {
            return true;
        }
        return false;
    }

    public e h() {
        e eVar = new e();
        if (this.a.a("fullPrivacy")) {
            eVar.a(null);
            b(eVar);
        } else {
            eVar.a(this.b.g());
            eVar.a(this.b.j() ^ 1);
        }
        return eVar;
    }

    public void b(e eVar) {
        this.b.a(eVar.a());
    }

    public g a(TextWidget textWidget, TextWidget textWidget2) {
        g gVar = new g();
        gVar.a(b(textWidget, textWidget2));
        return gVar;
    }

    private boolean b(TextWidget textWidget, TextWidget textWidget2) {
        boolean z = false;
        if (this.a.a("fullPrivacy")) {
            return false;
        }
        boolean a = this.a.a("profileFormEnable");
        boolean a2 = this.a.a("hideNameAndEmail");
        boolean z2 = textWidget.d().length() > 0;
        boolean z3 = textWidget2.d().length() > 0;
        if (this.a.a("requireNameAndEmail") && a2) {
            if (!(z2 && z3)) {
                z = true;
            }
            return z;
        }
        if (a && (!a2 || ((this.a.a("requireEmail") && !z3) || !z2))) {
            z = true;
        }
        return z;
    }

    public h i() {
        h hVar = new h();
        hVar.a(this.b.j());
        return hVar;
    }
}
