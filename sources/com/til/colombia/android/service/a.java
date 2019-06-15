package com.til.colombia.android.service;

import android.net.Uri;
import android.view.View;
import android.view.View.OnClickListener;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.til.colombia.android.network.n;

final class a implements OnClickListener {
    final /* synthetic */ AdChoiceView a;

    a(AdChoiceView adChoiceView) {
        this.a = adChoiceView;
    }

    public final void onClick(View view) {
        try {
            if (this.a.mcontext != null) {
                n.a(this.a.mcontext, Uri.parse(com.til.colombia.android.internal.a.r()));
            }
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
        }
    }
}
