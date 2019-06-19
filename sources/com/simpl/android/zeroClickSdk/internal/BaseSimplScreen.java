package com.simpl.android.zeroClickSdk.internal;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import com.simpl.android.zeroClickSdk.R;
import com.simpl.approvalsdk.SimplUser;

public class BaseSimplScreen extends FragmentActivity {
    private SimplUser a;
    private Fragment b;
    private String c;
    private a d;

    /* renamed from: com.simpl.android.zeroClickSdk.internal.BaseSimplScreen$3 */
    class AnonymousClass3 implements a<Void> {
        final /* synthetic */ a a;

        AnonymousClass3(a aVar) {
            this.a = aVar;
        }

        public final /* bridge */ /* synthetic */ Object a() {
            BaseSimplScreen.this.d = this.a;
            return null;
        }
    }

    public interface a {
        void a(boolean z, String str);
    }

    static /* synthetic */ void b(BaseSimplScreen baseSimplScreen, String str) {
        final int i = R.id.__simpl_fragment_container;
        final SimplZeroClickWebViewFragment a = SimplZeroClickWebViewFragment.a(str, baseSimplScreen.a, baseSimplScreen.c);
        final String str2 = SimplZeroClickWebViewFragment.a;
        c.a(new a<Void>() {
            public final /* synthetic */ Object a() {
                FragmentTransaction beginTransaction = BaseSimplScreen.this.getSupportFragmentManager().beginTransaction();
                if (BaseSimplScreen.this.b != null) {
                    beginTransaction.remove(a);
                }
                BaseSimplScreen.this.b = a;
                beginTransaction.replace(i, a, str2);
                beginTransaction.commitAllowingStateLoss();
                return null;
            }
        }, new a<Void>() {
            public final /* synthetic */ Object a() {
                BaseSimplScreen.this.finish();
                return null;
            }
        });
    }

    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        c.a(new a<Void>() {
            public final /* synthetic */ Object a() {
                BaseSimplScreen baseSimplScreen;
                String str;
                BaseSimplScreen.this.setContentView(R.layout.__activity_simpl);
                BaseSimplScreen.this.a = (SimplUser) BaseSimplScreen.this.getIntent().getExtras().getParcelable("user");
                if (BaseSimplScreen.this.getIntent().hasExtra("verification_url")) {
                    BaseSimplScreen.this.c = BaseSimplScreen.this.getIntent().getExtras().getString("verification_url");
                    baseSimplScreen = BaseSimplScreen.this;
                    str = "verification_url";
                } else {
                    if (BaseSimplScreen.this.getIntent().hasExtra("redirection_url")) {
                        BaseSimplScreen.this.c = BaseSimplScreen.this.getIntent().getExtras().getString("redirection_url");
                        baseSimplScreen = BaseSimplScreen.this;
                        str = "redirection_url";
                    }
                    return null;
                }
                BaseSimplScreen.b(baseSimplScreen, str);
                return null;
            }
        }, new a<Void>() {
            public final /* synthetic */ Object a() {
                BaseSimplScreen.this.finish();
                return null;
            }
        });
    }

    public void onRequestPermissionsResult(final int i, @NonNull final String[] strArr, @NonNull final int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        c.a(new a<Void>() {
            public final /* synthetic */ Object a() {
                if (i == 2003) {
                    BaseSimplScreen.this.d.a(iArr[0] == 0, strArr[0]);
                }
                return null;
            }
        }, new a<Void>() {
            public final /* synthetic */ Object a() {
                BaseSimplScreen.this.finish();
                return null;
            }
        });
    }
}
