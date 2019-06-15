package com.helpshift.util;

import android.text.style.URLSpan;
import android.view.View;
import com.helpshift.util.k.a;

class HSLinkify$4 extends URLSpan {
    final /* synthetic */ a a;
    final /* synthetic */ String b;

    HSLinkify$4(String str, a aVar, String str2) {
        this.a = aVar;
        this.b = str2;
        super(str);
    }

    public void onClick(View view) {
        super.onClick(view);
        if (this.a != null) {
            this.a.a(this.b);
        }
    }
}
