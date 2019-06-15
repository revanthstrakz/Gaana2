package com.facebook.ads.internal.q;

import android.content.Context;
import android.text.TextUtils;
import com.facebook.ads.internal.e.b;
import com.facebook.ads.internal.h.e;
import com.facebook.ads.internal.protocol.AdErrorType;
import com.facebook.ads.internal.protocol.h;
import com.facebook.ads.internal.r.a.m;
import com.facebook.ads.internal.s.a.i;
import com.facebook.ads.internal.s.a.n;
import com.facebook.ads.internal.s.a.u;
import java.security.MessageDigest;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import org.json.JSONException;

public class c {
    private static final n i = new n();
    private static final ThreadPoolExecutor j = ((ThreadPoolExecutor) Executors.newCachedThreadPool(i));
    private final Context a;
    private final e b = e.a();
    private final com.facebook.ads.internal.n.a c = com.facebook.ads.internal.n.a.G(this.a);
    private Map<String, String> d;
    private a e;
    private b f;
    private com.facebook.ads.internal.r.a.a g;
    private final String h = d.a();

    public interface a {
        void a(com.facebook.ads.internal.protocol.a aVar);

        void a(g gVar);
    }

    public c(Context context) {
        this.a = context.getApplicationContext();
    }

    private void a(com.facebook.ads.internal.protocol.a aVar) {
        if (this.e != null) {
            this.e.a(aVar);
        }
        a();
    }

    private void a(g gVar) {
        if (this.e != null) {
            this.e.a(gVar);
        }
        a();
    }

    private void a(String str) {
        try {
            com.facebook.ads.internal.protocol.a a;
            f a2 = this.b.a(str);
            com.facebook.ads.internal.j.c a3 = a2.a();
            if (a3 != null) {
                this.c.a(a3.b());
                com.facebook.ads.internal.c.a.a(this.a, a3.c());
                a.a(a3.a().d(), this.f);
            }
            String c;
            switch (a2.b()) {
                case ADS:
                    if (com.facebook.ads.internal.n.a.h(this.a)) {
                        b.a(this.a);
                    }
                    g gVar = (g) a2;
                    if (a3 != null) {
                        if (a3.a().e()) {
                            a.a(str, this.f);
                        }
                        CharSequence charSequence = this.d != null ? (String) this.d.get("CLIENT_REQUEST_ID") : null;
                        c = a2.c();
                        if (!(TextUtils.isEmpty(c) || TextUtils.isEmpty(charSequence))) {
                            StringBuilder stringBuilder = new StringBuilder();
                            for (int i = 0; i < "O1JZDdNevO246WTKNUEii946VaYq5upx".length(); i++) {
                                int i2;
                                char charAt = "O1JZDdNevO246WTKNUEii946VaYq5upx".charAt(i);
                                if ((charAt < 'a' || charAt > 'm') && (charAt < 'A' || charAt > 'M')) {
                                    if ((charAt >= 'n' && charAt <= 'z') || (charAt >= 'N' && charAt <= 'Z')) {
                                        i2 = charAt - 13;
                                    }
                                    stringBuilder.append(charAt);
                                } else {
                                    i2 = charAt + 13;
                                }
                                charAt = (char) i2;
                                stringBuilder.append(charAt);
                            }
                            StringBuilder stringBuilder2 = new StringBuilder();
                            stringBuilder2.append(charSequence);
                            stringBuilder2.append(c);
                            stringBuilder2.append(stringBuilder.toString());
                            byte[] bytes = stringBuilder2.toString().getBytes("iso-8859-1");
                            MessageDigest instance = MessageDigest.getInstance("SHA-1");
                            instance.update(bytes, 0, bytes.length);
                            if (!a2.d().equals(i.a(instance.digest()))) {
                                com.facebook.ads.internal.s.d.a.a(this.a, "network", com.facebook.ads.internal.s.d.b.k, new h());
                            }
                            stringBuilder2 = new StringBuilder();
                            stringBuilder2.append(c);
                            stringBuilder2.append(charSequence);
                            stringBuilder2.append(stringBuilder.toString());
                            byte[] bytes2 = stringBuilder2.toString().getBytes("iso-8859-1");
                            MessageDigest instance2 = MessageDigest.getInstance("SHA-1");
                            instance2.update(bytes2, 0, bytes2.length);
                            e.a(new com.facebook.ads.internal.h.a(c, i.a(instance2.digest())), this.a);
                        }
                        if (!(TextUtils.isEmpty(a2.e()) || TextUtils.isEmpty(charSequence))) {
                            new com.facebook.ads.internal.m.a(this.a, charSequence, a2.e()).a();
                        }
                    }
                    a(gVar);
                    return;
                case ERROR:
                    h hVar = (h) a2;
                    c = hVar.f();
                    AdErrorType adErrorTypeFromCode = AdErrorType.adErrorTypeFromCode(hVar.g(), AdErrorType.ERROR_MESSAGE);
                    if (c != null) {
                        str = c;
                    }
                    a = com.facebook.ads.internal.protocol.a.a(adErrorTypeFromCode, str);
                    break;
                default:
                    a = com.facebook.ads.internal.protocol.a.a(AdErrorType.UNKNOWN_RESPONSE, str);
                    break;
            }
            a(a);
        } catch (Exception e) {
            a(com.facebook.ads.internal.protocol.a.a(AdErrorType.PARSER_FAILURE, e.getMessage()));
        }
    }

    private com.facebook.ads.internal.r.a.b b() {
        return new com.facebook.ads.internal.r.a.b() {
            /* Access modifiers changed, original: 0000 */
            public void a(m mVar) {
                a.b(c.this.f);
                c.this.g = null;
                try {
                    com.facebook.ads.internal.r.a.n a = mVar.a();
                    if (a != null) {
                        String e = a.e();
                        f a2 = c.this.b.a(e);
                        if (a2.b() == a.ERROR) {
                            h hVar = (h) a2;
                            String f = hVar.f();
                            AdErrorType adErrorTypeFromCode = AdErrorType.adErrorTypeFromCode(hVar.g(), AdErrorType.ERROR_MESSAGE);
                            c cVar = c.this;
                            if (f != null) {
                                e = f;
                            }
                            cVar.a(com.facebook.ads.internal.protocol.a.a(adErrorTypeFromCode, e));
                            return;
                        }
                    }
                } catch (JSONException unused) {
                }
                c.this.a(com.facebook.ads.internal.protocol.a.a(AdErrorType.NETWORK_ERROR, mVar.getMessage()));
            }

            public void a(com.facebook.ads.internal.r.a.n nVar) {
                if (nVar != null) {
                    String e = nVar.e();
                    a.b(c.this.f);
                    c.this.g = null;
                    c.this.a(e);
                }
            }

            public void a(Exception exception) {
                if (m.class.equals(exception.getClass())) {
                    a((m) exception);
                } else {
                    c.this.a(com.facebook.ads.internal.protocol.a.a(AdErrorType.NETWORK_ERROR, exception.getMessage()));
                }
            }
        };
    }

    public void a() {
        if (this.g != null) {
            this.g.c(1);
            this.g.b(1);
            this.g = null;
        }
    }

    public void a(final b bVar) {
        a();
        if (u.a(this.a) == com.facebook.ads.internal.s.a.u.a.NONE) {
            a(new com.facebook.ads.internal.protocol.a(AdErrorType.NETWORK_ERROR, "No network connection"));
            return;
        }
        this.f = bVar;
        com.facebook.ads.internal.i.a.a(this.a);
        if (a.a(bVar)) {
            String c = a.c(bVar);
            if (c != null) {
                a(c);
                return;
            } else {
                a(com.facebook.ads.internal.protocol.a.a(AdErrorType.LOAD_TOO_FREQUENTLY, null));
                return;
            }
        }
        j.submit(new Runnable() {
            /* JADX WARNING: Removed duplicated region for block: B:21:0x00bd A:{Catch:{ Exception -> 0x00f9 }} */
            /* JADX WARNING: Removed duplicated region for block: B:20:0x00bc A:{Catch:{ Exception -> 0x00f9 }} */
            /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x009e */
            /* JADX WARNING: Failed to process nested try/catch */
            public void run() {
                /*
                r6 = this;
                r0 = com.facebook.ads.internal.q.c.this;
                r0 = r0.a;
                com.facebook.ads.internal.d.b.a(r0);
                r0 = com.facebook.ads.internal.q.c.this;
                r0 = r0.a;
                com.facebook.ads.internal.k.c.a(r0);
                r0 = r3;
                r0 = r0.e();
                r0 = r0.a();
                if (r0 == 0) goto L_0x0044;
            L_0x001e:
                r0 = r3;	 Catch:{ b -> 0x002a }
                r0 = r0.e();	 Catch:{ b -> 0x002a }
                r1 = com.facebook.ads.internal.d.b.b;	 Catch:{ b -> 0x002a }
                r0.a(r1);	 Catch:{ b -> 0x002a }
                goto L_0x0034;
            L_0x002a:
                r0 = move-exception;
                r1 = com.facebook.ads.internal.q.c.this;
                r0 = com.facebook.ads.internal.protocol.a.a(r0);
                r1.a(r0);
            L_0x0034:
                r0 = com.facebook.ads.internal.q.c.this;
                r1 = r3;
                r1 = r1.e();
                r1 = r1.b();
                r0.a(r1);
                return;
            L_0x0044:
                r0 = com.facebook.ads.internal.q.c.this;
                r1 = r3;
                r1 = r1.f();
                r0.d = r1;
                r0 = com.facebook.ads.internal.q.c.this;	 Catch:{ Exception -> 0x009e }
                r0 = r0.d;	 Catch:{ Exception -> 0x009e }
                r1 = "M_BANNER_KEY";
                r2 = new java.lang.String;	 Catch:{ Exception -> 0x009e }
                r3 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x009e }
                r3.<init>();	 Catch:{ Exception -> 0x009e }
                r4 = com.facebook.ads.internal.q.c.this;	 Catch:{ Exception -> 0x009e }
                r4 = r4.a;	 Catch:{ Exception -> 0x009e }
                r4 = r4.getPackageName();	 Catch:{ Exception -> 0x009e }
                r3.append(r4);	 Catch:{ Exception -> 0x009e }
                r4 = " ";
                r3.append(r4);	 Catch:{ Exception -> 0x009e }
                r4 = com.facebook.ads.internal.q.c.this;	 Catch:{ Exception -> 0x009e }
                r4 = r4.a;	 Catch:{ Exception -> 0x009e }
                r4 = r4.getPackageManager();	 Catch:{ Exception -> 0x009e }
                r5 = com.facebook.ads.internal.q.c.this;	 Catch:{ Exception -> 0x009e }
                r5 = r5.a;	 Catch:{ Exception -> 0x009e }
                r5 = r5.getPackageName();	 Catch:{ Exception -> 0x009e }
                r4 = r4.getInstallerPackageName(r5);	 Catch:{ Exception -> 0x009e }
                r3.append(r4);	 Catch:{ Exception -> 0x009e }
                r3 = r3.toString();	 Catch:{ Exception -> 0x009e }
                r3 = r3.getBytes();	 Catch:{ Exception -> 0x009e }
                r4 = 2;
                r3 = android.util.Base64.encode(r3, r4);	 Catch:{ Exception -> 0x009e }
                r2.<init>(r3);	 Catch:{ Exception -> 0x009e }
                r0.put(r1, r2);	 Catch:{ Exception -> 0x009e }
            L_0x009e:
                r0 = r3;	 Catch:{ Exception -> 0x00f9 }
                r0 = r0.c;	 Catch:{ Exception -> 0x00f9 }
                r1 = com.facebook.ads.internal.protocol.e.NATIVE_250;	 Catch:{ Exception -> 0x00f9 }
                if (r0 == r1) goto L_0x00bf;
            L_0x00a6:
                r0 = r3;	 Catch:{ Exception -> 0x00f9 }
                r0 = r0.c;	 Catch:{ Exception -> 0x00f9 }
                r1 = com.facebook.ads.internal.protocol.e.NATIVE_UNKNOWN;	 Catch:{ Exception -> 0x00f9 }
                if (r0 == r1) goto L_0x00bf;
            L_0x00ae:
                r0 = r3;	 Catch:{ Exception -> 0x00f9 }
                r0 = r0.c;	 Catch:{ Exception -> 0x00f9 }
                r1 = com.facebook.ads.internal.protocol.e.NATIVE_BANNER;	 Catch:{ Exception -> 0x00f9 }
                if (r0 == r1) goto L_0x00bf;
            L_0x00b6:
                r0 = r3;	 Catch:{ Exception -> 0x00f9 }
                r0 = r0.c;	 Catch:{ Exception -> 0x00f9 }
                if (r0 != 0) goto L_0x00bd;
            L_0x00bc:
                goto L_0x00bf;
            L_0x00bd:
                r0 = 0;
                goto L_0x00c0;
            L_0x00bf:
                r0 = 1;
            L_0x00c0:
                r1 = com.facebook.ads.internal.q.c.this;	 Catch:{ Exception -> 0x00f9 }
                r2 = com.facebook.ads.internal.q.c.this;	 Catch:{ Exception -> 0x00f9 }
                r2 = r2.a;	 Catch:{ Exception -> 0x00f9 }
                r0 = com.facebook.ads.internal.s.c.d.b(r2, r0);	 Catch:{ Exception -> 0x00f9 }
                r1.g = r0;	 Catch:{ Exception -> 0x00f9 }
                r0 = com.facebook.ads.internal.q.c.this;	 Catch:{ Exception -> 0x00f9 }
                r0 = r0.g;	 Catch:{ Exception -> 0x00f9 }
                r1 = com.facebook.ads.internal.q.c.this;	 Catch:{ Exception -> 0x00f9 }
                r1 = r1.h;	 Catch:{ Exception -> 0x00f9 }
                r2 = com.facebook.ads.internal.q.c.this;	 Catch:{ Exception -> 0x00f9 }
                r2 = r2.g;	 Catch:{ Exception -> 0x00f9 }
                r2 = r2.b();	 Catch:{ Exception -> 0x00f9 }
                r3 = com.facebook.ads.internal.q.c.this;	 Catch:{ Exception -> 0x00f9 }
                r3 = r3.d;	 Catch:{ Exception -> 0x00f9 }
                r2 = r2.a(r3);	 Catch:{ Exception -> 0x00f9 }
                r3 = com.facebook.ads.internal.q.c.this;	 Catch:{ Exception -> 0x00f9 }
                r3 = r3.b();	 Catch:{ Exception -> 0x00f9 }
                r0.a(r1, r2, r3);	 Catch:{ Exception -> 0x00f9 }
                return;
            L_0x00f9:
                r0 = move-exception;
                r1 = com.facebook.ads.internal.q.c.this;
                r2 = com.facebook.ads.internal.protocol.AdErrorType.AD_REQUEST_FAILED;
                r0 = r0.getMessage();
                r0 = com.facebook.ads.internal.protocol.a.a(r2, r0);
                r1.a(r0);
                return;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.facebook.ads.internal.q.c$AnonymousClass1.run():void");
            }
        });
    }

    public void a(a aVar) {
        this.e = aVar;
    }
}
