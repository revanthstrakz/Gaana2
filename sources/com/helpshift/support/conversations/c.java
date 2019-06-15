package com.helpshift.support.conversations;

import android.content.Context;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.ContextMenu;
import android.view.View;
import android.view.ViewGroup;
import com.helpshift.conversation.activeconversation.message.AdminAttachmentMessageDM;
import com.helpshift.conversation.activeconversation.message.AdminImageAttachmentMessageDM;
import com.helpshift.conversation.activeconversation.message.ConversationFooterState;
import com.helpshift.conversation.activeconversation.message.j;
import com.helpshift.conversation.activeconversation.message.k;
import com.helpshift.conversation.activeconversation.message.m;
import com.helpshift.support.conversations.messages.MessageViewType;
import com.helpshift.support.conversations.messages.g;
import com.helpshift.support.conversations.messages.g.a;
import com.helpshift.support.conversations.messages.g.b;
import com.helpshift.support.conversations.messages.h;
import com.helpshift.support.conversations.messages.i;
import java.util.List;

public class c extends Adapter<ViewHolder> implements a, h.a {
    private i a;
    private List<j> b;
    private com.helpshift.support.conversations.messages.j c;
    private ConversationFooterState d = ConversationFooterState.NONE;
    private boolean e = false;

    public c(Context context, List<j> list, com.helpshift.support.conversations.messages.j jVar) {
        this.a = new i(context);
        this.b = list;
        this.c = jVar;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i == MessageViewType.CONVERSATION_FOOTER.key) {
            g a = this.a.a();
            a.a((a) this);
            return a.a(viewGroup);
        } else if (i == MessageViewType.AGENT_TYPING_FOOTER.key) {
            return this.a.b().a(viewGroup);
        } else {
            h a2 = this.a.a(i);
            a2.a(this);
            return a2.b(viewGroup);
        }
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        int itemViewType = viewHolder.getItemViewType();
        if (itemViewType == MessageViewType.CONVERSATION_FOOTER.key) {
            this.a.a().a((b) viewHolder, this.d);
        } else if (itemViewType != MessageViewType.AGENT_TYPING_FOOTER.key) {
            this.a.a(itemViewType).a(viewHolder, b(i));
        }
    }

    public int getItemCount() {
        return b() + c();
    }

    private int c() {
        int i = this.e ? 1 : 0;
        return this.d != ConversationFooterState.NONE ? i + 1 : i;
    }

    public int getItemViewType(int i) {
        if (i >= this.b.size()) {
            return c(i - this.b.size());
        }
        return this.a.a(b(i));
    }

    private j b(int i) {
        return (j) this.b.get(i);
    }

    public void a(String str) {
        if (this.c != null) {
            this.c.a(str);
        }
    }

    public void a(ContextMenu contextMenu, View view) {
        if (this.c != null) {
            this.c.a(contextMenu, view);
        }
    }

    public void a(int i) {
        if (this.c != null && i != this.b.size()) {
            this.c.a(b(i));
        }
    }

    public void a(m mVar) {
        if (this.c != null) {
            this.c.a(mVar);
        }
    }

    public void a(k kVar) {
        if (this.c != null) {
            this.c.a(kVar);
        }
    }

    public void a(AdminAttachmentMessageDM adminAttachmentMessageDM) {
        if (this.c != null) {
            this.c.a(adminAttachmentMessageDM);
        }
    }

    public void a(AdminImageAttachmentMessageDM adminImageAttachmentMessageDM) {
        if (this.c != null) {
            this.c.a(adminImageAttachmentMessageDM);
        }
    }

    public void a(boolean z) {
        if (this.e != z) {
            this.e = z;
            if (z) {
                notifyItemRangeInserted(this.b.size(), 1);
            } else {
                notifyItemRangeRemoved(this.b.size(), 1);
            }
        }
    }

    private int c(int i) {
        Object obj = this.d != ConversationFooterState.NONE ? 1 : null;
        switch (i) {
            case 0:
                if (this.e) {
                    return MessageViewType.AGENT_TYPING_FOOTER.key;
                }
                if (obj != null) {
                    return MessageViewType.CONVERSATION_FOOTER.key;
                }
                break;
            case 1:
                if (obj != null) {
                    return MessageViewType.CONVERSATION_FOOTER.key;
                }
                break;
        }
        return -1;
    }

    public void a(ConversationFooterState conversationFooterState) {
        if (conversationFooterState == null) {
            conversationFooterState = ConversationFooterState.NONE;
        }
        this.d = conversationFooterState;
        notifyDataSetChanged();
    }

    public void a() {
        if (this.c != null) {
            this.c.g();
        }
    }

    public void a(int i, String str) {
        if (this.c != null) {
            this.c.a(i, str);
        }
    }

    public int b() {
        return this.b.size();
    }
}
