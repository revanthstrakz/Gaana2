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
import com.helpshift.e.f;
import com.helpshift.e.h;
import com.helpshift.support.widget.CSATView;

public class g {
    a a;
    private Context b;

    public interface a {
        void a();

        void a(int i, String str);
    }

    public final class b extends ViewHolder implements OnClickListener, com.helpshift.support.widget.CSATView.a {
        final View a;
        final TextView b;
        final LinearLayout c;
        final Button d;
        final CSATView e;
        final View f;

        public b(View view) {
            super(view);
            this.a = view;
            this.b = (TextView) view.findViewById(f.footer_message);
            this.c = (LinearLayout) view.findViewById(f.hs__new_conversation);
            this.d = (Button) view.findViewById(f.hs__new_conversation_btn);
            this.e = (CSATView) view.findViewById(f.csat_view_layout);
            this.f = view.findViewById(f.issue_archival_message_view_stub);
        }

        public void onClick(View view) {
            if (g.this.a != null) {
                g.this.a.a();
            }
        }

        public void a(int i, String str) {
            if (g.this.a != null) {
                g.this.a.a(i, str);
            }
        }
    }

    public g(Context context) {
        this.b = context;
    }

    public b a(ViewGroup viewGroup) {
        return new b(LayoutInflater.from(viewGroup.getContext()).inflate(h.hs__messages_list_footer, viewGroup, false));
    }

    /* JADX WARNING: Missing block: B:2:0x0019, code skipped:
            r9 = 1;
            r3 = 0;
     */
    /* JADX WARNING: Missing block: B:3:0x001b, code skipped:
            r4 = r3;
     */
    /* JADX WARNING: Missing block: B:4:0x001c, code skipped:
            r5 = r4;
     */
    /* JADX WARNING: Missing block: B:11:0x004b, code skipped:
            if (r1 == 0) goto L_0x00a1;
     */
    /* JADX WARNING: Missing block: B:12:0x004d, code skipped:
            r8.a.setVisibility(0);
     */
    /* JADX WARNING: Missing block: B:13:0x0052, code skipped:
            if (r9 == 0) goto L_0x005f;
     */
    /* JADX WARNING: Missing block: B:14:0x0054, code skipped:
            r8.b.setText(r0);
            r8.b.setVisibility(0);
     */
    /* JADX WARNING: Missing block: B:15:0x005f, code skipped:
            r8.b.setVisibility(8);
     */
    /* JADX WARNING: Missing block: B:17:0x0065, code skipped:
            if (r3 == 0) goto L_0x0072;
     */
    /* JADX WARNING: Missing block: B:18:0x0067, code skipped:
            r8.c.setVisibility(0);
            r8.d.setOnClickListener(r8);
     */
    /* JADX WARNING: Missing block: B:19:0x0072, code skipped:
            r8.c.setVisibility(8);
            r8.c.setOnClickListener(null);
     */
    /* JADX WARNING: Missing block: B:20:0x007c, code skipped:
            if (r4 == 0) goto L_0x0089;
     */
    /* JADX WARNING: Missing block: B:21:0x007e, code skipped:
            r8.e.setVisibility(0);
            r8.e.setCSATListener(r8);
     */
    /* JADX WARNING: Missing block: B:22:0x0089, code skipped:
            r8.e.setVisibility(8);
            r8.e.setCSATListener(null);
     */
    /* JADX WARNING: Missing block: B:23:0x0093, code skipped:
            if (r5 == 0) goto L_0x009b;
     */
    /* JADX WARNING: Missing block: B:24:0x0095, code skipped:
            r8.f.setVisibility(0);
     */
    /* JADX WARNING: Missing block: B:25:0x009b, code skipped:
            r8.f.setVisibility(8);
     */
    /* JADX WARNING: Missing block: B:26:0x00a1, code skipped:
            r8.a.setVisibility(8);
     */
    /* JADX WARNING: Missing block: B:27:?, code skipped:
            return;
     */
    /* JADX WARNING: Missing block: B:28:?, code skipped:
            return;
     */
    /* JADX WARNING: Missing block: B:29:?, code skipped:
            return;
     */
    public void a(com.helpshift.support.conversations.messages.g.b r8, com.helpshift.conversation.activeconversation.message.ConversationFooterState r9) {
        /*
        r7 = this;
        r0 = r7.b;
        r0 = r0.getResources();
        r1 = com.helpshift.e.k.hs__conversation_end_msg;
        r0 = r0.getString(r1);
        r1 = com.helpshift.support.conversations.messages.g.AnonymousClass1.a;
        r9 = r9.ordinal();
        r9 = r1[r9];
        r1 = 1;
        r2 = 0;
        switch(r9) {
            case 1: goto L_0x0045;
            case 2: goto L_0x0038;
            case 3: goto L_0x0034;
            case 4: goto L_0x0023;
            case 5: goto L_0x001e;
            default: goto L_0x0019;
        };
    L_0x0019:
        r9 = r1;
        r3 = r2;
    L_0x001b:
        r4 = r3;
    L_0x001c:
        r5 = r4;
        goto L_0x0049;
    L_0x001e:
        r3 = r1;
        r5 = r3;
        r9 = r2;
        r4 = r9;
        goto L_0x0049;
    L_0x0023:
        r9 = r7.b;
        r9 = r9.getResources();
        r0 = com.helpshift.e.k.hs__confirmation_footer_msg;
        r0 = r9.getString(r0);
        r9 = r1;
        r3 = r9;
        r4 = r3;
        r5 = r2;
        goto L_0x0049;
    L_0x0034:
        r9 = r1;
        r3 = r9;
        r4 = r2;
        goto L_0x001c;
    L_0x0038:
        r9 = r7.b;
        r9 = r9.getResources();
        r0 = com.helpshift.e.k.hs__confirmation_footer_msg;
        r0 = r9.getString(r0);
        goto L_0x0019;
    L_0x0045:
        r9 = r2;
        r1 = r9;
        r3 = r1;
        goto L_0x001b;
    L_0x0049:
        r6 = 8;
        if (r1 == 0) goto L_0x00a1;
    L_0x004d:
        r1 = r8.a;
        r1.setVisibility(r2);
        if (r9 == 0) goto L_0x005f;
    L_0x0054:
        r9 = r8.b;
        r9.setText(r0);
        r9 = r8.b;
        r9.setVisibility(r2);
        goto L_0x0064;
    L_0x005f:
        r9 = r8.b;
        r9.setVisibility(r6);
    L_0x0064:
        r9 = 0;
        if (r3 == 0) goto L_0x0072;
    L_0x0067:
        r0 = r8.c;
        r0.setVisibility(r2);
        r0 = r8.d;
        r0.setOnClickListener(r8);
        goto L_0x007c;
    L_0x0072:
        r0 = r8.c;
        r0.setVisibility(r6);
        r0 = r8.c;
        r0.setOnClickListener(r9);
    L_0x007c:
        if (r4 == 0) goto L_0x0089;
    L_0x007e:
        r9 = r8.e;
        r9.setVisibility(r2);
        r9 = r8.e;
        r9.setCSATListener(r8);
        goto L_0x0093;
    L_0x0089:
        r0 = r8.e;
        r0.setVisibility(r6);
        r0 = r8.e;
        r0.setCSATListener(r9);
    L_0x0093:
        if (r5 == 0) goto L_0x009b;
    L_0x0095:
        r8 = r8.f;
        r8.setVisibility(r2);
        goto L_0x00a6;
    L_0x009b:
        r8 = r8.f;
        r8.setVisibility(r6);
        goto L_0x00a6;
    L_0x00a1:
        r8 = r8.a;
        r8.setVisibility(r6);
    L_0x00a6:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.helpshift.support.conversations.messages.g.a(com.helpshift.support.conversations.messages.g$b, com.helpshift.conversation.activeconversation.message.ConversationFooterState):void");
    }

    public void a(a aVar) {
        this.a = aVar;
    }
}
