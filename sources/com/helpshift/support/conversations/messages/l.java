package com.helpshift.support.conversations.messages;

import android.content.Context;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.helpshift.conversation.activeconversation.message.m;
import com.helpshift.e;
import com.helpshift.e.f;
import com.helpshift.support.util.h;

public class l extends h<a, m> implements com.helpshift.util.k.a {

    protected final class a extends ViewHolder {
        final TextView a;
        final Button b;
        final TextView c;
        private final LinearLayout e;

        a(View view) {
            super(view);
            this.a = (TextView) view.findViewById(f.admin_attachment_request_text);
            this.b = (Button) view.findViewById(f.admin_attach_screenshot_button);
            this.e = (LinearLayout) view.findViewById(f.admin_message);
            this.c = (TextView) view.findViewById(f.admin_date_text);
            h.a(l.this.a, this.e.getBackground());
        }
    }

    public l(Context context) {
        super(context);
    }

    /* renamed from: a */
    public a b(ViewGroup viewGroup) {
        return new a(LayoutInflater.from(viewGroup.getContext()).inflate(e.h.hs__msg_request_screenshot, viewGroup, false));
    }

    public void a(a aVar, final m mVar) {
        aVar.a.setText(b(mVar.j));
        aVar.c.setText(mVar.f());
        a((View) aVar.b, (boolean) mVar.a ^ 1);
        aVar.b.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (mVar.b() && l.this.b != null) {
                    l.this.b.a(mVar);
                }
            }
        });
        a(aVar.a, (com.helpshift.util.k.a) this);
    }

    public void a(String str) {
        if (this.b != null) {
            this.b.a(str);
        }
    }
}
