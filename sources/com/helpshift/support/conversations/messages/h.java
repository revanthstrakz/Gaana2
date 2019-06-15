package com.helpshift.support.conversations.messages;

import android.content.Context;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.Html;
import android.view.ContextMenu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.helpshift.conversation.activeconversation.message.AdminAttachmentMessageDM;
import com.helpshift.conversation.activeconversation.message.AdminImageAttachmentMessageDM;
import com.helpshift.conversation.activeconversation.message.j;
import com.helpshift.conversation.activeconversation.message.k;
import com.helpshift.conversation.activeconversation.message.m;

public abstract class h<VH extends ViewHolder, M extends j> {
    protected Context a;
    protected a b;

    public interface a {
        void a(int i);

        void a(ContextMenu contextMenu, View view);

        void a(AdminAttachmentMessageDM adminAttachmentMessageDM);

        void a(AdminImageAttachmentMessageDM adminImageAttachmentMessageDM);

        void a(k kVar);

        void a(m mVar);

        void a(String str);
    }

    public abstract void a(VH vh, M m);

    public abstract VH b(ViewGroup viewGroup);

    public h(Context context) {
        this.a = context;
    }

    public void a(a aVar) {
        this.b = aVar;
    }

    /* Access modifiers changed, original: protected */
    public void a(TextView textView, com.helpshift.util.k.a aVar) {
        com.helpshift.util.k.a(textView, 15, aVar);
        com.helpshift.util.k.a(textView, com.helpshift.util.m.c(), null, null, null, aVar);
    }

    /* Access modifiers changed, original: protected */
    public String b(String str) {
        return Html.fromHtml(str.replace("\n", "<br/>")).toString();
    }

    /* Access modifiers changed, original: protected */
    public void a(View view, boolean z) {
        if (z) {
            view.setVisibility(0);
        } else {
            view.setVisibility(8);
        }
    }
}
