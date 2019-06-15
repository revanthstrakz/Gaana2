package com.simpl.android.zeroClickSdk.internal;

import android.content.Context;
import android.content.Intent;
import com.simpl.android.zeroClickSdk.SimplPaymentDueListener;
import com.simpl.android.zeroClickSdk.SimplPaymentUrlRequest;
import com.simpl.approvalsdk.SimplUser;

public final class j implements SimplPaymentUrlRequest {
    private Context a;
    private SimplUser b;
    private String c;
    private String d;

    j(Context context, SimplUser simplUser, String str, String str2) {
        this.a = context;
        this.b = simplUser;
        this.c = str;
        this.d = str2;
    }

    public final void execute(final SimplPaymentDueListener simplPaymentDueListener) {
        c.a(new a<Void>() {
            public final /* synthetic */ Object a() {
                ((i) i.a()).d = simplPaymentDueListener;
                j.this.a.startActivity(new Intent(j.this.a, BaseSimplScreen.class).addFlags(335544320).putExtra("merchant_id", j.this.c).putExtra("user", j.this.b).putExtra("redirection_url", j.this.d));
                return null;
            }
        });
    }
}
