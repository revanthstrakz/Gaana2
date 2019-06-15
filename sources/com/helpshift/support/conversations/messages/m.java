package com.helpshift.support.conversations.messages;

import android.content.Context;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.helpshift.conversation.activeconversation.message.n;
import com.helpshift.e;
import com.helpshift.e.f;
import com.helpshift.support.util.h;
import com.helpshift.support.views.HSRoundedImageView;

public class m extends h<a, n> {

    protected final class a extends ViewHolder implements OnClickListener {
        final View a;
        final HSRoundedImageView b;
        final TextView c;
        final View d;
        final View e;
        private final ProgressBar g;

        a(View view) {
            super(view);
            this.d = view.findViewById(f.imageview_container);
            this.g = (ProgressBar) view.findViewById(f.upload_attachment_progressbar);
            this.a = view.findViewById(f.progressbar_container);
            this.b = (HSRoundedImageView) view.findViewById(f.user_attachment_imageview);
            this.c = (TextView) view.findViewById(f.date);
            this.e = view.findViewById(f.user_message);
            h.b(m.this.a, this.d.getBackground());
            h.d(m.this.a, this.a.getBackground());
            h.a(m.this.a, this.g);
        }

        public void onClick(View view) {
            if (m.this.b != null) {
                m.this.b.a(getAdapterPosition());
            }
        }
    }

    public m(Context context) {
        super(context);
    }

    /* renamed from: a */
    public a b(ViewGroup viewGroup) {
        return new a(LayoutInflater.from(this.a).inflate(e.h.hs__msg_screenshot_status, viewGroup, false));
    }

    public void a(com.helpshift.support.conversations.messages.m.a r10, com.helpshift.conversation.activeconversation.message.n r11) {
        /*
        r9 = this;
        r0 = r11.b();
        r1 = r9.a;
        r2 = 16842808; // 0x1010038 float:2.3693715E-38 double:8.321453E-317;
        r1 = com.helpshift.support.util.h.a(r1, r2);
        r2 = com.helpshift.util.w.a(r0);
        r3 = 1;
        r2 = r2 ^ r3;
        r4 = com.helpshift.support.conversations.messages.m.AnonymousClass1.a;
        r5 = r11.y;
        r5 = r5.ordinal();
        r4 = r4[r5];
        r5 = 1057971241; // 0x3f0f5c29 float:0.56 double:5.227072445E-315;
        r6 = 0;
        r7 = 0;
        switch(r4) {
            case 1: goto L_0x0059;
            case 2: goto L_0x0044;
            case 3: goto L_0x0037;
            case 4: goto L_0x0028;
            default: goto L_0x0025;
        };
    L_0x0025:
        r11 = r6;
    L_0x0026:
        r3 = r7;
        goto L_0x0070;
    L_0x0028:
        r11 = r11.f();
        r4 = 1065353216; // 0x3f800000 float:1.0 double:5.263544247E-315;
        r8 = com.helpshift.util.w.a(r0);
        if (r8 == 0) goto L_0x0035;
    L_0x0034:
        goto L_0x0070;
    L_0x0035:
        r5 = r4;
        goto L_0x0026;
    L_0x0037:
        r11 = r9.a;
        r11 = r11.getResources();
        r4 = com.helpshift.e.k.hs__sending_msg;
        r11 = r11.getString(r4);
        goto L_0x0070;
    L_0x0044:
        r11 = r9.a;
        r11 = r11.getResources();
        r1 = com.helpshift.e.k.hs__message_not_sent;
        r11 = r11.getString(r1);
        r1 = r9.a;
        r3 = com.helpshift.e.b.hs__errorTextColor;
        r1 = com.helpshift.support.util.h.a(r1, r3);
        goto L_0x0026;
    L_0x0059:
        r11 = r9.a;
        r11 = r11.getResources();
        r1 = com.helpshift.e.k.hs__sending_fail_msg;
        r6 = r11.getString(r1);
        r11 = r9.a;
        r1 = com.helpshift.e.b.hs__errorTextColor;
        r1 = com.helpshift.support.util.h.a(r11, r1);
        r11 = r6;
        r3 = r7;
        r6 = r10;
    L_0x0070:
        r4 = r10.b;
        r4.a(r0);
        r0 = r10.b;
        r9.a(r0, r2);
        r0 = r10.c;
        r0.setVisibility(r7);
        r0 = r10.c;
        r0.setText(r11);
        r11 = r10.c;
        r11.setTextColor(r1);
        r11 = r10.d;
        r11.setAlpha(r5);
        r11 = r10.a;
        r9.a(r11, r3);
        r10 = r10.e;
        r10.setOnClickListener(r6);
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.helpshift.support.conversations.messages.m.a(com.helpshift.support.conversations.messages.m$a, com.helpshift.conversation.activeconversation.message.n):void");
    }
}
