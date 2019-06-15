package com.helpshift.support.conversations.messages;

import android.content.Context;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.helpshift.e;
import com.helpshift.e.k;
import com.helpshift.support.util.h;

class f extends h<a, com.helpshift.conversation.activeconversation.message.f> {

    protected final class a extends ViewHolder {
        final TextView a;
        final TextView b;

        a(View view) {
            super(view);
            this.a = (TextView) view.findViewById(com.helpshift.e.f.admin_message_text);
            this.b = (TextView) view.findViewById(com.helpshift.e.f.admin_date_text);
            h.a(f.this.a, view.findViewById(com.helpshift.e.f.admin_message_container).getBackground());
        }
    }

    f(Context context) {
        super(context);
    }

    /* renamed from: a */
    public a b(ViewGroup viewGroup) {
        return new a(LayoutInflater.from(viewGroup.getContext()).inflate(e.h.hs__msg_txt_admin, viewGroup, false));
    }

    public void a(a aVar, com.helpshift.conversation.activeconversation.message.f fVar) {
        aVar.a.setText(k.hs__cr_msg);
        aVar.b.setText(fVar.f());
    }
}
