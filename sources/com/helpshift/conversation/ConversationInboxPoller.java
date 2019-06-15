package com.helpshift.conversation;

import com.helpshift.account.a.b;
import com.helpshift.common.c;
import com.helpshift.common.domain.Poller;
import com.helpshift.common.domain.Poller.ActivePollingInterval;
import com.helpshift.configuration.a.a;
import com.helpshift.util.l;
import java.util.Observable;
import java.util.Observer;

public class ConversationInboxPoller implements Observer {
    public final Poller a;
    private final b b;
    private final a c;
    private ConversationInboxPollerState d;

    private enum ConversationInboxPollerState {
        IN_APP,
        SDK,
        CHAT
    }

    public ConversationInboxPoller(b bVar, a aVar, Poller poller) {
        this.b = bVar;
        this.c = aVar;
        this.a = poller;
        bVar.addObserver(this);
    }

    public void a() {
        if (this.d == ConversationInboxPollerState.CHAT) {
            d();
        } else if (this.d == ConversationInboxPollerState.SDK) {
            c();
        } else {
            b();
        }
    }

    public void b() {
        if (c.a(this.b.b) || this.b.j || this.c.a("disableInAppConversation")) {
            e();
        } else {
            l.a("Helpshift_ConvPoller", "Listening for in-app conversation updates");
            this.a.a(ActivePollingInterval.CONSERVATIVE);
        }
        this.d = ConversationInboxPollerState.IN_APP;
    }

    public void c() {
        if (!c.a(this.b.b)) {
            l.a("Helpshift_ConvPoller", "Listening for in-sdk conversation updates");
            this.a.a(ActivePollingInterval.CONSERVATIVE);
            this.d = ConversationInboxPollerState.SDK;
        }
    }

    public void d() {
        if (!c.a(this.b.b)) {
            l.a("Helpshift_ConvPoller", "Listening for in-chat conversation updates");
            this.a.a(ActivePollingInterval.AGGRESSIVE);
            this.d = ConversationInboxPollerState.CHAT;
        }
    }

    public void e() {
        l.a("Helpshift_ConvPoller", "Stopped listening for in-app conversation updates");
        this.a.a();
    }

    public void update(Observable observable, Object obj) {
        if (this.d != ConversationInboxPollerState.CHAT && this.d != ConversationInboxPollerState.SDK) {
            b();
        }
    }
}
