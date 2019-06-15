package com.til.colombia.android.service;

import android.net.Uri;
import android.view.View;
import android.view.View.OnClickListener;
import com.til.colombia.android.network.n;

final class p implements OnClickListener {
    final /* synthetic */ AdView a;

    p(AdView adView) {
        this.a = adView;
    }

    public final void onClick(View view) {
        try {
            n.a(this.a.context, Uri.parse(((NativeItem) this.a.item).getAdChoiceClickUrl()));
        } catch (Exception unused) {
        }
    }
}
