package com.simpl.android.zeroClickSdk.internal;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;
import com.simpl.android.fingerprint.commons.exception.ExceptionNotifier;
import com.simpl.android.zeroClickSdk.SimplPaymentDueListener;
import com.simpl.android.zeroClickSdk.SimplPaymentUrlRequest;
import com.simpl.android.zeroClickSdk.SimplUser;
import com.simpl.android.zeroClickSdk.SimplUserApprovalRequest;
import com.simpl.android.zeroClickSdk.SimplZeroClickTokenListener;
import com.simpl.approvalsdk.SimplApproval;
import java.lang.ref.WeakReference;

public class i implements g {
    private static final String f = "i";
    private static i i;
    boolean a;
    boolean b;
    Context c;
    SimplPaymentDueListener d;
    SimplZeroClickTokenListener e;
    private WeakReference<Context> g;
    private String h;

    private i(Context context, String str) {
        if (context != null) {
            if (TextUtils.isEmpty(str)) {
                Log.e(f, "Error: Missing Simpl client_id.\nPlease add\n<meta-data\n    android:name=\"com.simpl.android.approvalSdk.merchant_id\"\n    android:value=\"CLIENT_ID\" />\n\nto your AndroidManifest.xml or\npass it in Simpl.init(context, CLIENT_ID)\n\nCLIENT_ID can be obtained from Simpl merchant dashboard.");
                Toast.makeText(context, "Error: Missing Simpl client_id. Check logs for more details.", 1).show();
                ExceptionNotifier sharedInstance = ExceptionNotifier.getSharedInstance();
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(f);
                stringBuilder.append(" Simpl(): Merchant Id is not added");
                sharedInstance.send(new Throwable(stringBuilder.toString()));
            }
            this.g = new WeakReference(context);
            this.c = context.getApplicationContext();
            this.h = str;
            SimplApproval.init(context, str);
        }
    }

    public static g a() {
        return (g) c.a(new a<g>() {
            public final /* synthetic */ Object a() {
                return i.b();
            }
        }, new b());
    }

    public static void a(@NonNull final Context context) {
        c.a(new a<Void>() {
            public final /* synthetic */ Object a() {
                i.b(context, i.c(context));
                return null;
            }
        });
    }

    public static void a(@NonNull final Context context, @NonNull final String str) {
        c.a(new a<Void>() {
            public final /* synthetic */ Object a() {
                i.b(context, str);
                return null;
            }
        });
    }

    static /* synthetic */ g b() {
        return i == null ? new b() : i;
    }

    static /* synthetic */ void b(Context context, String str) {
        if (i != null) {
            Log.w(f, "Simpl is already initialized");
        } else {
            i = new i(context, str);
        }
    }

    private static String c(Context context) {
        ExceptionNotifier sharedInstance;
        StringBuilder stringBuilder;
        Throwable th;
        try {
            return context.getApplicationContext().getPackageManager().getApplicationInfo(context.getApplicationContext().getPackageName(), 128).metaData.getString("com.simpl.android.sdk.merchant_id");
        } catch (NameNotFoundException e) {
            sharedInstance = ExceptionNotifier.getSharedInstance();
            stringBuilder = new StringBuilder();
            stringBuilder.append(f);
            stringBuilder.append(" : ");
            stringBuilder.append(e);
            th = new Throwable(stringBuilder.toString());
            sharedInstance.send(th);
            return null;
        } catch (NullPointerException e2) {
            sharedInstance = ExceptionNotifier.getSharedInstance();
            stringBuilder = new StringBuilder();
            stringBuilder.append(f);
            stringBuilder.append(" : ");
            stringBuilder.append(e2);
            th = new Throwable(stringBuilder.toString());
            sharedInstance.send(th);
            return null;
        }
    }

    public void addFlags(final String... strArr) {
        c.a(new a<Void>() {
            public final /* synthetic */ Object a() {
                SimplApproval.getInstance().addFlags(strArr);
                return null;
            }
        });
    }

    public void generateZeroClickToken(@NonNull final SimplUser simplUser, @NonNull final SimplZeroClickTokenListener simplZeroClickTokenListener) {
        c.a(new a<Void>() {
            public final /* bridge */ /* synthetic */ Object a() {
                c.a(new com.simpl.android.zeroClickSdk.internal.m.AnonymousClass1(simplZeroClickTokenListener));
                return null;
            }
        });
    }

    public void generateZeroClickToken(@NonNull final SimplZeroClickTokenListener simplZeroClickTokenListener) {
        c.a(new a<Void>() {
            public final /* synthetic */ Object a() {
                if (SimplApproval.getInstance().isSimplApproved()) {
                    c.a(new com.simpl.android.zeroClickSdk.internal.m.AnonymousClass1(simplZeroClickTokenListener));
                } else {
                    simplZeroClickTokenListener.onFailure(new Throwable("user_not_approved_on_simpl"));
                }
                return null;
            }
        });
    }

    public boolean isSimplApproved() {
        return ((Boolean) c.a(new a<Boolean>() {
            public final /* synthetic */ Object a() {
                return Boolean.valueOf(SimplApproval.getInstance().isSimplApproved());
            }
        }, Boolean.FALSE)).booleanValue();
    }

    public SimplUserApprovalRequest isUserApproved(@NonNull final SimplUser simplUser) {
        return (SimplUserApprovalRequest) c.a(new a<SimplUserApprovalRequest>() {
            public final /* synthetic */ Object a() {
                return new k(simplUser, i.this.h);
            }
        }, new b());
    }

    public SimplPaymentUrlRequest openRedirectionURL(@NonNull final Context context, @NonNull final String str) {
        return (SimplPaymentUrlRequest) c.a(new a<SimplPaymentUrlRequest>() {
            public final /* synthetic */ Object a() {
                return new j(context, SimplApproval.getInstance().getSimplUser(), i.this.h, str);
            }
        }, new b());
    }

    public SimplPaymentUrlRequest openRedirectionURL(@NonNull final Context context, @NonNull final String str, @NonNull final SimplUser simplUser) {
        return (SimplPaymentUrlRequest) c.a(new a<SimplPaymentUrlRequest>() {
            public final /* synthetic */ Object a() {
                return new j(context, simplUser, i.this.h, str);
            }
        }, new b());
    }

    public void runInSandboxMode() {
        c.a(new a<Void>() {
            public final /* synthetic */ Object a() {
                i.this.a = true;
                SimplApproval.getInstance().runInSandboxMode();
                return null;
            }
        });
    }

    public void runInStagingMode() {
        c.a(new a<Void>() {
            public final /* synthetic */ Object a() {
                i.this.b = true;
                SimplApproval.getInstance().runInStagingMode();
                return null;
            }
        });
    }

    public void setMerchantId(final String str) {
        c.a(new a<Void>() {
            public final /* synthetic */ Object a() {
                if (!TextUtils.isEmpty(str)) {
                    i.this.h = str;
                    SimplApproval.getInstance().setMerchantId(str);
                }
                return null;
            }
        });
    }
}
