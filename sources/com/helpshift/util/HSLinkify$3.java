package com.helpshift.util;

import android.text.style.URLSpan;
import android.view.View;
import com.helpshift.util.k.a;

class HSLinkify$3 extends URLSpan {
    final /* synthetic */ a a;
    final /* synthetic */ q b;

    HSLinkify$3(String str, a aVar, q qVar) {
        this.a = aVar;
        this.b = qVar;
        super(str);
    }

    public void onClick(View view) {
        super.onClick(view);
        if (this.a != null) {
            this.a.a(this.b.a);
        }
    }
}
