package com.simpl.android.fingerprint.a;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import com.simpl.android.fingerprint.SimplFingerprintListener;
import com.simpl.android.fingerprint.a.j.a;
import com.simpl.android.fingerprint.commons.exception.ExceptionNotifier;
import com.simpl.android.fingerprint.commons.models.Attribute;
import com.simpl.android.zeroClickSdk.Simpl;
import java.util.HashMap;
import org.json.JSONObject;

public class p implements o {
    private static p b;
    private j a;

    private p(@NonNull Context context, @NonNull String str, @NonNull String str2) {
        ExceptionNotifier.init(context, str);
        this.a = new j(context, str, str2);
    }

    /* synthetic */ p(Context context, String str, String str2, byte b) {
        this(context, str, str2);
    }

    public static o a() {
        return (o) e.a(new a<o>() {
            public final /* synthetic */ Object a() {
                return p.b();
            }
        }, new c());
    }

    public static void a(@NonNull final Context context, @NonNull final String str, @NonNull final String str2) {
        e.a(new a<Void>() {
            public final /* synthetic */ Object a() {
                if (TextUtils.isEmpty(str)) {
                    Log.e(Simpl.TAG, "Please call SimplFingerprint.init() with a valid phone number");
                    return null;
                }
                p.b = new p(context, str, str2, (byte) 0);
                return null;
            }
        });
    }

    private void a(final SimplFingerprintListener simplFingerprintListener, HashMap<String, String> hashMap) {
        try {
            this.a.a((HashMap) hashMap, new a() {
                public final void a(JSONObject jSONObject) {
                    p.a(p.this, jSONObject, simplFingerprintListener);
                }
            });
        } catch (Throwable th) {
            simplFingerprintListener.fingerprintData(th.getLocalizedMessage());
            ExceptionNotifier.getSharedInstance().send(th, new Attribute("generateFingerprint user", this.a.a));
        }
    }

    static /* synthetic */ o b() {
        return b == null ? new c() : b;
    }

    public void addFlags(final String... strArr) {
        e.a(new a<Void>() {
            public final /* synthetic */ Object a() {
                p.this.a.b = k.a(strArr);
                return null;
            }
        });
    }

    public void generateFingerprint(final SimplFingerprintListener simplFingerprintListener) {
        e.a(new a<Void>() {
            public final /* synthetic */ Object a() {
                p.this.generateFingerprint(simplFingerprintListener, null);
                return null;
            }
        }, new b<Void>() {
            public final /* synthetic */ Object a(Throwable th) {
                if (simplFingerprintListener != null) {
                    simplFingerprintListener.fingerprintData(th.getLocalizedMessage());
                }
                return null;
            }
        });
    }

    public void generateFingerprint(final SimplFingerprintListener simplFingerprintListener, final HashMap<String, String> hashMap) {
        e.a(new a<Void>() {
            public final /* bridge */ /* synthetic */ Object a() {
                p.this.a(simplFingerprintListener, hashMap);
                return null;
            }
        }, new b<Void>() {
            public final /* synthetic */ Object a(Throwable th) {
                if (simplFingerprintListener != null) {
                    simplFingerprintListener.fingerprintData(th.getLocalizedMessage());
                }
                return null;
            }
        });
    }

    public void generateTransactionFingerprint(@NonNull final SimplFingerprintListener simplFingerprintListener) {
        e.a(new a<Void>() {
            public final /* bridge */ /* synthetic */ Object a() {
                p.this.a(simplFingerprintListener, null);
                return null;
            }
        }, new b<Void>() {
            public final /* synthetic */ Object a(Throwable th) {
                if (simplFingerprintListener != null) {
                    simplFingerprintListener.fingerprintData(th.getLocalizedMessage());
                }
                return null;
            }
        });
        a(simplFingerprintListener, null);
    }
}
