package com.helpshift.support.conversations.messages;

import android.content.Context;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.helpshift.conversation.activeconversation.message.AdminAttachmentMessageDM;
import com.helpshift.e;
import com.helpshift.e.f;
import com.helpshift.support.util.h;

class b extends h<a, AdminAttachmentMessageDM> {

    protected final class a extends ViewHolder {
        final TextView a;
        final TextView b;
        final View c;
        final View d;
        final ProgressBar e;
        final ImageView f;
        final ImageView g;
        final TextView h;

        a(View view) {
            super(view);
            this.a = (TextView) view.findViewById(f.attachment_file_name);
            this.b = (TextView) view.findViewById(f.attachment_file_size);
            this.c = view.findViewById(f.admin_message);
            this.d = view.findViewById(f.download_button_ring);
            this.f = (ImageView) view.findViewById(f.download_icon);
            this.e = (ProgressBar) view.findViewById(f.progress);
            this.g = (ImageView) view.findViewById(f.attachment_icon);
            this.h = (TextView) view.findViewById(f.attachment_date);
            h.a(b.this.a, view.findViewById(f.admin_message).getBackground());
            h.c(b.this.a, this.f.getDrawable());
            h.c(b.this.a, this.g.getDrawable());
            h.c(b.this.a, this.e.getIndeterminateDrawable());
            h.c(b.this.a, this.d.getBackground());
        }
    }

    b(Context context) {
        super(context);
    }

    /* renamed from: a */
    public a b(ViewGroup viewGroup) {
        return new a(LayoutInflater.from(this.a).inflate(e.h.hs__msg_attachment_generic, viewGroup, false));
    }

    public void a(a aVar, final AdminAttachmentMessageDM adminAttachmentMessageDM) {
        boolean z;
        boolean z2;
        boolean z3 = true;
        boolean z4 = false;
        switch (adminAttachmentMessageDM.b) {
            case DOWNLOAD_NOT_STARTED:
                z = false;
                z2 = z;
                z4 = true;
                break;
            case DOWNLOADING:
                z2 = true;
                z = false;
                break;
            case DOWNLOADED:
                z = true;
                z3 = false;
                z2 = z3;
                break;
            default:
                z = false;
                z2 = z;
                break;
        }
        a(aVar.d, z4);
        a((View) aVar.f, z3);
        a((View) aVar.g, z);
        a((View) aVar.e, z2);
        aVar.h.setText(adminAttachmentMessageDM.f());
        aVar.a.setText(adminAttachmentMessageDM.d);
        aVar.b.setText(adminAttachmentMessageDM.c());
        aVar.c.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (b.this.b != null) {
                    b.this.b.a(adminAttachmentMessageDM);
                }
            }
        });
    }
}
