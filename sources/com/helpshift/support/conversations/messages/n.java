package com.helpshift.support.conversations.messages;

import android.content.Context;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.util.Linkify;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnCreateContextMenuListener;
import android.view.ViewGroup;
import android.widget.TextView;
import com.helpshift.conversation.activeconversation.message.UserMessageState;
import com.helpshift.conversation.activeconversation.message.o;
import com.helpshift.e.b;
import com.helpshift.e.f;
import com.helpshift.e.h;
import com.helpshift.e.k;
import com.helpshift.util.v;

public class n extends h<a, o> {

    protected final class a extends ViewHolder implements OnClickListener, OnCreateContextMenuListener {
        final TextView a;
        final TextView b;
        final View c;

        a(View view) {
            super(view);
            this.a = (TextView) view.findViewById(f.user_message_text);
            this.b = (TextView) view.findViewById(f.user_date_text);
            this.c = view.findViewById(f.user_message_container);
        }

        /* Access modifiers changed, original: 0000 */
        public void a() {
            this.a.setOnCreateContextMenuListener(this);
        }

        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenuInfo contextMenuInfo) {
            if (n.this.b != null) {
                n.this.b.a(contextMenu, view);
            }
        }

        public void onClick(View view) {
            if (n.this.b != null) {
                n.this.b.a(getAdapterPosition());
            }
        }
    }

    public n(Context context) {
        super(context);
    }

    /* renamed from: a */
    public a b(ViewGroup viewGroup) {
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(h.hs__msg_txt_user, viewGroup, false);
        com.helpshift.support.util.h.b(this.a, inflate.findViewById(f.user_message_container).getBackground());
        a aVar = new a(inflate);
        aVar.a();
        return aVar;
    }

    public void a(a aVar, o oVar) {
        UserMessageState userMessageState = oVar.a;
        aVar.a.setText(b(oVar.j));
        switch (userMessageState) {
            case UNSENT_NOT_RETRYABLE:
                aVar.b.setText(k.hs__message_not_sent);
                aVar.b.setTextColor(v.a(this.a, b.hs__errorTextColor));
                aVar.c.setAlpha(0.56f);
                Linkify.addLinks(aVar.a, 15);
                aVar.a.setOnClickListener(null);
                aVar.a.setEnabled(true);
                return;
            case UNSENT_RETRYABLE:
                aVar.b.setText(k.hs__sending_fail_msg);
                aVar.b.setTextColor(v.a(this.a, b.hs__errorTextColor));
                aVar.c.setAlpha(0.56f);
                aVar.a.setOnClickListener(aVar);
                aVar.a.setEnabled(true);
                return;
            case SENDING:
                aVar.b.setText(k.hs__sending_msg);
                aVar.b.setTextColor(v.a(this.a, 16842808));
                aVar.c.setAlpha(0.56f);
                aVar.a.setOnClickListener(null);
                aVar.a.setEnabled(false);
                return;
            case SENT:
                aVar.b.setText(oVar.f());
                aVar.b.setTextColor(v.a(this.a, 16842808));
                aVar.c.setAlpha(1.0f);
                Linkify.addLinks(aVar.a, 15);
                aVar.a.setOnClickListener(null);
                aVar.a.setEnabled(true);
                return;
            default:
                return;
        }
    }
}
