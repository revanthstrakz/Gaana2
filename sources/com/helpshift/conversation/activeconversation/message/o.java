package com.helpshift.conversation.activeconversation.message;

import com.helpshift.analytics.AnalyticsEventType;
import com.helpshift.common.exception.NetworkException;
import com.helpshift.common.exception.RootAPIException;
import java.util.HashMap;
import java.util.Map;

public class o extends j {
    public UserMessageState a;

    public boolean a() {
        return true;
    }

    public o(String str, String str2, String str3) {
        super(str, str2, str3, false, MessageType.USER_TEXT);
    }

    public void a(String str, String str2) {
        if (this.a != UserMessageState.SENDING && this.a != UserMessageState.SENT && this.a != UserMessageState.UNSENT_NOT_RETRYABLE) {
            String str3 = this.j;
            this.a = UserMessageState.SENDING;
            g();
            HashMap hashMap = new HashMap();
            hashMap.put("profile-id", str);
            hashMap.put("message-text", str3);
            hashMap.put("type", "txt");
            hashMap.put("refers", "");
            try {
                o d = this.u.j().d(a_(str2).c(hashMap).b);
                this.a = UserMessageState.SENT;
                a(d);
                this.i = d.i;
                this.u.f().a((j) this);
                g();
                this.t.e().b(str3);
                Map hashMap2 = new HashMap();
                hashMap2.put("id", str2);
                hashMap2.put("body", str3);
                hashMap2.put("type", "txt");
                this.t.d().a(AnalyticsEventType.MESSAGE_ADDED, hashMap2);
            } catch (RootAPIException e) {
                if (e.c != NetworkException.CONVERSATION_ARCHIVED) {
                    this.a = UserMessageState.UNSENT_RETRYABLE;
                    g();
                }
                throw RootAPIException.a(e);
            } catch (Throwable th) {
                this.t.e().b(str3);
                Map hashMap3 = new HashMap();
                hashMap3.put("id", str2);
                hashMap3.put("body", str3);
                hashMap3.put("type", "txt");
                this.t.d().a(AnalyticsEventType.MESSAGE_ADDED, hashMap3);
            }
        }
    }

    public void a(boolean z) {
        if (this.i != null) {
            this.a = UserMessageState.SENT;
        } else if (this.a != UserMessageState.SENDING) {
            if (z) {
                this.a = UserMessageState.UNSENT_RETRYABLE;
            } else {
                this.a = UserMessageState.UNSENT_NOT_RETRYABLE;
            }
        }
    }
}
