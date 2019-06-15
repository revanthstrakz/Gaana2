package com.helpshift.conversation.activeconversation.message;

import com.helpshift.analytics.AnalyticsEventType;
import com.helpshift.common.domain.network.e;
import com.helpshift.common.domain.network.f;
import com.helpshift.common.domain.network.m;
import com.helpshift.common.exception.RootAPIException;
import com.helpshift.common.platform.p;
import com.helpshift.downloader.SupportDownloader.StorageDirType;
import com.helpshift.downloader.a;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class n extends i {
    public String a;
    public UserMessageState y;

    public boolean a() {
        return true;
    }

    public n(String str, String str2, String str3, String str4, String str5, String str6, String str7, int i) {
        super(str, str2, str3, str7, str6, str5, str4, i, false, MessageType.SCREENSHOT);
    }

    public void b(String str) {
        if (str == null) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("localRscMessage_");
            stringBuilder.append(UUID.randomUUID().toString());
            str = stringBuilder.toString();
        }
        this.a = str;
    }

    public void a(String str, String str2, boolean z) {
        if (b() != null) {
            if (z) {
                this.g = this.u.a(b(), this.a);
                this.u.f().a((j) this);
            }
            a(UserMessageState.SENDING);
            HashMap hashMap = new HashMap();
            hashMap.put("profile-id", str);
            hashMap.put("message-text", "Screenshot sent");
            hashMap.put("type", "sc");
            hashMap.put("refers", this.a);
            hashMap.put("screenshot", b());
            hashMap.put("originalFileName", this.d);
            try {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("/issues/");
                stringBuilder.append(str2);
                stringBuilder.append("/messages/");
                n h = this.u.j().h(new f(new e(new m(stringBuilder.toString(), this.t, this.u))).c(hashMap).b);
                this.i = h.i;
                a(h);
                a(UserMessageState.SENT);
                this.u.f().a((j) this);
                g();
                Map hashMap2 = new HashMap();
                hashMap2.put("id", str2);
                hashMap2.put("body", h.e);
                hashMap2.put("type", "url");
                this.t.d().a(AnalyticsEventType.MESSAGE_ADDED, hashMap2);
                this.t.e().b("User sent a screenshot");
            } catch (RootAPIException e) {
                a(UserMessageState.UNSENT_RETRYABLE);
                throw RootAPIException.a(e);
            }
        }
    }

    private void a(UserMessageState userMessageState) {
        this.y = userMessageState;
        g();
    }

    public void a(boolean z) {
        if (this.i != null) {
            a(UserMessageState.SENT);
        } else if (this.y != UserMessageState.SENDING) {
            if (z) {
                a(UserMessageState.UNSENT_RETRYABLE);
            } else {
                a(UserMessageState.UNSENT_NOT_RETRYABLE);
            }
        }
    }

    public String b() {
        if (!a(this.g)) {
            this.g = null;
        }
        return this.g;
    }

    public void a(final p pVar) {
        if (this.y == UserMessageState.SENT && !a(b())) {
            pVar.u().a(this.e, StorageDirType.INTERNAL_ONLY, new a() {
                public void a(String str) {
                }

                public void a(String str, int i) {
                }

                public void a(String str, String str2) {
                    n.this.g = str2;
                    pVar.f().a(n.this);
                    n.this.g();
                }
            });
        }
    }
}
