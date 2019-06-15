package com.simpl.android.zeroClickSdk.internal;

import android.content.Context;
import android.content.Intent;
import com.simpl.android.fingerprint.commons.exception.ExceptionNotifier;
import com.simpl.android.fingerprint.commons.models.Attribute;
import com.simpl.android.zeroClickSdk.SimplZeroClickTokenAuthorization;
import com.simpl.android.zeroClickSdk.SimplZeroClickTokenListener;
import com.simpl.approvalsdk.SimplUser;
import com.simpl.approvalsdk.executor.Executor;
import java.lang.ref.WeakReference;

public final class m {
    WeakReference<Context> a;
    String b;
    SimplUser c;

    /* renamed from: com.simpl.android.zeroClickSdk.internal.m$1 */
    class AnonymousClass1 implements a<Void> {
        final /* synthetic */ SimplZeroClickTokenListener a;

        AnonymousClass1(SimplZeroClickTokenListener simplZeroClickTokenListener) {
            this.a = simplZeroClickTokenListener;
        }

        public final /* synthetic */ Object a() {
            m mVar = m.this;
            Executor.get().execute(new com.simpl.android.zeroClickSdk.internal.h.AnonymousClass1(mVar.c, mVar.b));
            return null;
        }
    }

    /* renamed from: com.simpl.android.zeroClickSdk.internal.m$2 */
    class AnonymousClass2 implements SimplZeroClickTokenListener {
        final /* synthetic */ SimplZeroClickTokenListener a;

        AnonymousClass2(SimplZeroClickTokenListener simplZeroClickTokenListener) {
            this.a = simplZeroClickTokenListener;
        }

        public final void onFailure(Throwable th) {
            this.a.onFailure(th);
            ExceptionNotifier.getSharedInstance().send(th, new Attribute("user", m.this.c.toString()));
        }

        public final void onSuccess(SimplZeroClickTokenAuthorization simplZeroClickTokenAuthorization) {
            l lVar = new l(((i) i.a()).c, m.this.c, simplZeroClickTokenAuthorization.getZeroClickVerificationUrl(), m.this.b);
            ((i) i.a()).e = new SimplZeroClickTokenListener() {
                public final void onFailure(Throwable th) {
                    AnonymousClass2.this.a.onFailure(th);
                    ExceptionNotifier.getSharedInstance().send(th, new Attribute("user", m.this.c.toString()));
                }

                public final void onSuccess(SimplZeroClickTokenAuthorization simplZeroClickTokenAuthorization) {
                    AnonymousClass2.this.a.onSuccess(simplZeroClickTokenAuthorization);
                }
            };
            lVar.a.startActivity(new Intent(lVar.a, BaseSimplScreen.class).addFlags(335544320).putExtra("merchant_id", lVar.c).putExtra("user", lVar.b).putExtra("verification_url", lVar.d));
        }
    }

    m(WeakReference<Context> weakReference, String str, SimplUser simplUser) {
        this.a = weakReference;
        this.b = str;
        this.c = simplUser;
    }
}
