package com.helpshift.support.conversations.messages;

import android.content.Context;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.helpshift.e;
import com.helpshift.e.f;
import com.helpshift.support.util.h;

public class k extends h<a, com.helpshift.conversation.activeconversation.message.k> {

    protected final class a extends ViewHolder {
        final TextView a;
        final Button b;
        final TextView c;

        a(View view) {
            super(view);
            this.a = (TextView) view.findViewById(f.review_request_message);
            this.b = (Button) view.findViewById(f.review_request_button);
            this.c = (TextView) view.findViewById(f.review_request_date);
            h.a(k.this.a, view.findViewById(f.review_request_message_container).getBackground());
        }
    }

    public k(Context context) {
        super(context);
    }

    /* renamed from: a */
    public a b(ViewGroup viewGroup) {
        return new a(LayoutInflater.from(viewGroup.getContext()).inflate(e.h.hs__msg_review_request, viewGroup, false));
    }

    public void a(a aVar, final com.helpshift.conversation.activeconversation.message.k kVar) {
        aVar.a.setText(com.helpshift.e.k.hs__review_request_message);
        aVar.c.setText(kVar.f());
        if (kVar.a) {
            aVar.b.setVisibility(8);
        } else {
            aVar.b.setVisibility(0);
        }
        if (kVar.b) {
            aVar.b.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    if (k.this.b != null) {
                        k.this.b.a(kVar);
                    }
                }
            });
        } else {
            aVar.b.setOnClickListener(null);
        }
    }
}
