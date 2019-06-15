package com.helpshift.support.conversations.messages;

import android.content.Context;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.helpshift.conversation.activeconversation.message.AdminImageAttachmentMessageDM;
import com.helpshift.e;
import com.helpshift.e.f;
import com.helpshift.support.util.h;
import com.helpshift.support.views.HSRoundedImageView;

class c extends h<a, AdminImageAttachmentMessageDM> {

    protected final class a extends ViewHolder {
        final HSRoundedImageView a;
        final ProgressBar b;
        final View c;
        final View d;
        final TextView e;
        final TextView f;
        private final View h;

        a(View view) {
            super(view);
            this.a = (HSRoundedImageView) view.findViewById(f.admin_attachment_imageview);
            this.c = view.findViewById(f.download_button);
            this.d = view.findViewById(f.download_progressbar_container);
            this.b = (ProgressBar) view.findViewById(f.download_attachment_progressbar);
            this.h = view.findViewById(f.admin_message);
            this.e = (TextView) view.findViewById(f.attachment_file_size);
            this.f = (TextView) view.findViewById(f.date);
            h.a(c.this.a, this.h.getBackground());
            h.d(c.this.a, this.d.getBackground());
            h.a(c.this.a, this.b);
        }
    }

    c(Context context) {
        super(context);
    }

    /* renamed from: a */
    public a b(ViewGroup viewGroup) {
        return new a(LayoutInflater.from(this.a).inflate(e.h.hs__msg_attachment_image, viewGroup, false));
    }

    public void a(a aVar, final AdminImageAttachmentMessageDM adminImageAttachmentMessageDM) {
        String str;
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4 = true;
        boolean z5 = false;
        boolean z6;
        switch (adminImageAttachmentMessageDM.a) {
            case THUMBNAIL_DOWNLOADING:
                str = null;
                z = true;
                z2 = false;
                z3 = z2;
                z6 = z3;
                break;
            case THUMBNAIL_DOWNLOADED:
                str = adminImageAttachmentMessageDM.d();
                z2 = true;
                z3 = z2;
                z6 = z3;
                z = false;
                break;
            case IMAGE_DOWNLOADING:
                str = adminImageAttachmentMessageDM.d();
                z = true;
                z3 = z;
                z2 = false;
                z6 = z2;
                break;
            case IMAGE_DOWNLOADED:
                str = adminImageAttachmentMessageDM.e();
                z3 = true;
                z6 = z3;
                z4 = false;
                z = z4;
                z2 = z;
                break;
            default:
                str = null;
                z = true;
                z3 = z;
                z6 = z3;
                z2 = false;
                break;
        }
        if (str != null) {
            z5 = z3;
        }
        a(aVar.d, z4);
        a((View) aVar.b, z);
        a(aVar.c, z2);
        a((View) aVar.a, z5);
        aVar.a.a(str);
        aVar.f.setText(adminImageAttachmentMessageDM.f());
        aVar.e.setText(adminImageAttachmentMessageDM.c());
        AnonymousClass1 anonymousClass1 = new OnClickListener() {
            public void onClick(View view) {
                if (c.this.b != null) {
                    c.this.b.a(adminImageAttachmentMessageDM);
                }
            }
        };
        if (z2) {
            aVar.c.setOnClickListener(anonymousClass1);
        } else {
            aVar.c.setOnClickListener(null);
        }
        if (z5 && z6) {
            aVar.a.setOnClickListener(anonymousClass1);
        } else {
            aVar.a.setOnClickListener(null);
        }
    }
}
