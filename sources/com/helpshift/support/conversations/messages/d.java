package com.helpshift.support.conversations.messages;

import android.content.Context;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnCreateContextMenuListener;
import android.view.ViewGroup;
import android.widget.TextView;
import com.helpshift.conversation.activeconversation.message.j;
import com.helpshift.e.f;
import com.helpshift.e.h;

class d extends h<a, j> implements com.helpshift.util.k.a {

    protected final class a extends ViewHolder implements OnCreateContextMenuListener {
        final TextView a;
        final TextView b;

        a(View view) {
            super(view);
            this.a = (TextView) view.findViewById(f.admin_message_text);
            this.b = (TextView) view.findViewById(f.admin_date_text);
        }

        /* Access modifiers changed, original: 0000 */
        public void a() {
            this.a.setOnCreateContextMenuListener(this);
        }

        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenuInfo contextMenuInfo) {
            if (d.this.b != null) {
                d.this.b.a(contextMenu, view);
            }
        }
    }

    d(Context context) {
        super(context);
    }

    /* renamed from: a */
    public a b(ViewGroup viewGroup) {
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(h.hs__msg_txt_admin, viewGroup, false);
        com.helpshift.support.util.h.a(this.a, inflate.findViewById(f.admin_message_container).getBackground());
        a aVar = new a(inflate);
        aVar.a();
        return aVar;
    }

    public void a(a aVar, j jVar) {
        aVar.a.setText(b(jVar.j));
        aVar.b.setText(jVar.f());
        a(aVar.a, (com.helpshift.util.k.a) this);
    }

    public void a(String str) {
        if (this.b != null) {
            this.b.a(str);
        }
    }
}
