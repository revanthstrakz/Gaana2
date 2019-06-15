package com.helpshift.support.conversations.messages;

import android.content.Context;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.helpshift.e;
import com.helpshift.e.f;
import com.helpshift.e.k;
import com.helpshift.support.util.h;

public class a extends h<a, com.helpshift.conversation.activeconversation.message.a> {

    protected final class a extends ViewHolder {
        final TextView a;
        final TextView b;

        a(View view) {
            super(view);
            this.a = (TextView) view.findViewById(f.review_accepted_message);
            this.b = (TextView) view.findViewById(f.review_accepted_date);
            h.a(a.this.a, view.findViewById(f.review_accepted_message_container).getBackground());
        }
    }

    public a(Context context) {
        super(context);
    }

    /* renamed from: a */
    public a b(ViewGroup viewGroup) {
        return new a(LayoutInflater.from(viewGroup.getContext()).inflate(e.h.hs__msg_review_accepted, viewGroup, false));
    }

    public void a(a aVar, com.helpshift.conversation.activeconversation.message.a aVar2) {
        aVar.a.setText(k.hs__review_accepted_message);
        aVar.b.setText(aVar2.f());
    }
}
