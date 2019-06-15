package com.helpshift.support.conversations.messages;

import android.content.Context;
import android.util.SparseArray;
import com.helpshift.conversation.activeconversation.message.AdminAttachmentMessageDM;
import com.helpshift.conversation.activeconversation.message.AdminImageAttachmentMessageDM;
import com.helpshift.conversation.activeconversation.message.a;
import com.helpshift.conversation.activeconversation.message.b;
import com.helpshift.conversation.activeconversation.message.f;
import com.helpshift.conversation.activeconversation.message.j;
import com.helpshift.conversation.activeconversation.message.k;
import com.helpshift.conversation.activeconversation.message.l;
import com.helpshift.conversation.activeconversation.message.m;
import com.helpshift.conversation.activeconversation.message.n;
import com.helpshift.conversation.activeconversation.message.o;

public class i {
    private SparseArray<h> a = new SparseArray();
    private g b;
    private e c;

    public i(Context context) {
        this.a.put(MessageViewType.ADMIN_TEXT_MESSAGE.key, new d(context));
        this.a.put(MessageViewType.USER_TEXT_MESSAGE.key, new n(context));
        this.a.put(MessageViewType.USER_SCREENSHOT_ATTACHMENT.key, new m(context));
        this.a.put(MessageViewType.ADMIN_ATTACHMENT_IMAGE.key, new c(context));
        this.a.put(MessageViewType.ADMIN_ATTACHMENT_GENERIC.key, new b(context));
        this.a.put(MessageViewType.REQUESTED_APP_REVIEW.key, new k(context));
        this.a.put(MessageViewType.ACCEPTED_APP_REVIEW.key, new a(context));
        this.a.put(MessageViewType.CONFIRMATION_REJECTED.key, new f(context));
        this.a.put(MessageViewType.ADMIN_REQUEST_ATTACHMENT.key, new l(context));
        this.a.put(MessageViewType.REQUEST_FOR_REOPEN.key, new d(context));
        this.b = new g(context);
        this.c = new e(context);
    }

    public int a(j jVar) {
        if (jVar instanceof b) {
            return MessageViewType.ADMIN_TEXT_MESSAGE.key;
        }
        if (jVar instanceof o) {
            return MessageViewType.USER_TEXT_MESSAGE.key;
        }
        if (jVar instanceof n) {
            return MessageViewType.USER_SCREENSHOT_ATTACHMENT.key;
        }
        if (jVar instanceof AdminImageAttachmentMessageDM) {
            return MessageViewType.ADMIN_ATTACHMENT_IMAGE.key;
        }
        if (jVar instanceof AdminAttachmentMessageDM) {
            return MessageViewType.ADMIN_ATTACHMENT_GENERIC.key;
        }
        if (jVar instanceof k) {
            return MessageViewType.REQUESTED_APP_REVIEW.key;
        }
        if (jVar instanceof a) {
            return MessageViewType.ACCEPTED_APP_REVIEW.key;
        }
        if (jVar instanceof f) {
            return MessageViewType.CONFIRMATION_REJECTED.key;
        }
        if (jVar instanceof m) {
            return MessageViewType.ADMIN_REQUEST_ATTACHMENT.key;
        }
        return jVar instanceof l ? MessageViewType.REQUEST_FOR_REOPEN.key : -1;
    }

    public h a(int i) {
        return (h) this.a.get(i);
    }

    public g a() {
        return this.b;
    }

    public e b() {
        return this.c;
    }
}
