package com.login.nativesso.d;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.util.Log;
import com.gaana.login.LoginManager;
import com.gaana.login.sso.SsoErrorCodes;
import com.google.android.exoplayer2.C;
import com.login.nativesso.a.aa;
import com.login.nativesso.a.ab;
import com.login.nativesso.a.ac;
import com.login.nativesso.a.ad;
import com.login.nativesso.a.ae;
import com.login.nativesso.a.af;
import com.login.nativesso.a.ag;
import com.login.nativesso.a.ah;
import com.login.nativesso.a.d;
import com.login.nativesso.a.e;
import com.login.nativesso.a.f;
import com.login.nativesso.a.h;
import com.login.nativesso.a.i;
import com.login.nativesso.a.j;
import com.login.nativesso.a.k;
import com.login.nativesso.a.l;
import com.login.nativesso.a.m;
import com.login.nativesso.a.n;
import com.login.nativesso.a.o;
import com.login.nativesso.a.p;
import com.login.nativesso.a.q;
import com.login.nativesso.a.r;
import com.login.nativesso.a.s;
import com.login.nativesso.a.t;
import com.login.nativesso.a.u;
import com.login.nativesso.a.v;
import com.login.nativesso.a.w;
import com.login.nativesso.a.x;
import com.login.nativesso.a.y;
import com.login.nativesso.a.z;
import com.login.nativesso.activity.DummyActivity;
import com.login.nativesso.b.a;
import com.login.nativesso.e.g;
import com.login.nativesso.exception.SecurityException;
import com.login.nativesso.exception.ServerException;
import com.login.nativesso.g.b;
import java.lang.Thread.UncaughtExceptionHandler;

public class c {
    public static String e = "";
    public static String f = "";
    private static c i = new c();
    public final int a = 1;
    public final int b = 0;
    s c;
    boolean d = false;
    private int g;
    private Context h;

    public static c a() {
        return i;
    }

    private c() {
    }

    public void a(Context context, String str, String str2, String str3, s sVar) {
        this.c = sVar;
        if (this.c != null) {
            if ((context == null || com.login.nativesso.i.c.a(str) || com.login.nativesso.i.c.a(str2) || com.login.nativesso.i.c.a(str3)) && this.c != null) {
                this.c.onFailure(com.login.nativesso.i.c.a(413, "INVALID_REQUEST"));
                return;
            }
            this.h = context;
            if (this.c != null) {
                sVar = this.c;
                a.a("SdkInitializeCb", this.c);
            }
            AnonymousClass1 anonymousClass1 = new UncaughtExceptionHandler() {
                public void uncaughtException(Thread thread, Throwable th) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("Uncaught exception: ");
                    stringBuilder.append(th);
                    Log.e("NATIVESSO", stringBuilder.toString());
                }
            };
            final Context context2 = context;
            final String str4 = str;
            final String str5 = str2;
            final String str6 = str3;
            Thread thread = new Thread(new Runnable() {
                public void run() {
                    try {
                        com.login.nativesso.f.a.a().a(context2);
                        b a = b.a();
                        a.a(context2, "APP_AUTHORITY", str4);
                        a.a(context2, "siteId", str5);
                        a.a(context2, "channel", str6);
                        com.login.nativesso.i.a.a(context2, new com.login.nativesso.a.c() {
                            public void a() {
                                c.this.c = (s) a.a("SdkInitializeCb");
                                if (c.this.c != null) {
                                    new Handler(context2.getMainLooper()).post(new Runnable() {
                                        public void run() {
                                            if (c.this.c != null) {
                                                c.this.c.onSuccess();
                                                s sVar = c.this.c;
                                                a.b("SdkInitializeCb");
                                            }
                                        }
                                    });
                                }
                            }

                            public void a(final com.login.nativesso.e.c cVar) {
                                new Handler(context2.getMainLooper()).post(new Runnable() {
                                    public void run() {
                                        if (c.this.c != null) {
                                            c.this.c.onFailure(cVar);
                                            s sVar = c.this.c;
                                            a.b("SdkInitializeCb");
                                        }
                                    }
                                });
                            }
                        });
                    } catch (ServerException unused) {
                        if (c.this.c != null) {
                            new Handler(context2.getMainLooper()).post(new Runnable() {
                                public void run() {
                                    if (c.this.c != null) {
                                        c.this.c.onFailure(com.login.nativesso.i.c.a((int) SsoErrorCodes.SERVER_ERROR, "SERVER_ERROR"));
                                        s sVar = c.this.c;
                                        a.b("SdkInitializeCb");
                                    }
                                }
                            });
                        }
                    } catch (SecurityException unused2) {
                        if (c.this.c != null) {
                            new Handler(context2.getMainLooper()).post(new Runnable() {
                                public void run() {
                                    if (c.this.c != null) {
                                        c.this.c.onFailure(com.login.nativesso.i.c.a(4008, "SECURITY_ISSUE"));
                                        s sVar = c.this.c;
                                        a.b("SdkInitializeCb");
                                    }
                                }
                            });
                        }
                    } catch (Exception unused3) {
                        if (c.this.c != null) {
                            new Handler(context2.getMainLooper()).post(new Runnable() {
                                public void run() {
                                    if (c.this.c != null) {
                                        c.this.c.onFailure(com.login.nativesso.i.c.a((int) SsoErrorCodes.REQUEST_FAILED, "REQUEST_FAILED"));
                                        s sVar = c.this.c;
                                        a.b("SdkInitializeCb");
                                    }
                                }
                            });
                        }
                    }
                }
            });
            thread.setUncaughtExceptionHandler(anonymousClass1);
            thread.start();
        }
    }

    public int b() {
        return this.g;
    }

    public void a(int i) {
        this.g = i;
    }

    public void a(String str, x xVar) {
        if (xVar != null) {
            boolean c = c();
            if (xVar != null && !c) {
                xVar.onLoginFailure(com.login.nativesso.i.c.a((int) SsoErrorCodes.SDK_NOT_INITIALIZED, "SDK_NOT_INITIALIZED"));
            } else if (!c || com.login.nativesso.i.c.c(this.h)) {
                a.a("SocialLoginCb", xVar);
                Intent intent = new Intent();
                intent.setClass(this.h, DummyActivity.class);
                intent.setFlags(C.ENCODING_PCM_MU_LAW);
                intent.putExtra("SignInBy", "googlePlus");
                intent.putExtra("login_link_pic", "login");
                intent.putExtra("clientId", str);
                this.h.startActivity(intent);
            } else {
                xVar.onLoginFailure(com.login.nativesso.i.c.a((int) SsoErrorCodes.NO_INTERNET_CONNECTION, "NO_INTERNET_CONNECTION"));
            }
        }
    }

    public boolean c() {
        if (b() == 0 || this.h == null) {
            return false;
        }
        SharedPreferences d = b.a().d(this.h);
        if (d == null) {
            return false;
        }
        String string = d.getString("channel", null);
        String string2 = d.getString("siteId", null);
        String string3 = d.getString("APP_AUTHORITY", null);
        String string4 = d.getString("TGID", null);
        if (com.login.nativesso.i.c.a(string) || com.login.nativesso.i.c.a(string2) || com.login.nativesso.i.c.a(string3) || com.login.nativesso.i.c.a(string4)) {
            return false;
        }
        return true;
    }

    public void b(String str, x xVar) {
        if (xVar != null) {
            boolean c = c();
            if (xVar != null && !c) {
                xVar.onLoginFailure(com.login.nativesso.i.c.a((int) SsoErrorCodes.SDK_NOT_INITIALIZED, "SDK_NOT_INITIALIZED"));
            } else if (!c || com.login.nativesso.i.c.c(this.h)) {
                a.a("SocialLoginCb", xVar);
                Intent intent = new Intent();
                intent.setClass(this.h, DummyActivity.class);
                intent.setFlags(C.ENCODING_PCM_MU_LAW);
                intent.putExtra("SignInBy", "googlePlus");
                intent.putExtra("login_link_pic", "login");
                intent.putExtra("clientId", str);
                this.h.startActivity(intent);
            } else {
                xVar.onLoginFailure(com.login.nativesso.i.c.a((int) SsoErrorCodes.NO_INTERNET_CONNECTION, "NO_INTERNET_CONNECTION"));
            }
        }
    }

    public void a(x xVar) {
        b(null, xVar);
    }

    public void a(String[] strArr, x xVar) {
        b(strArr, xVar);
    }

    private void b(String[] strArr, x xVar) {
        if (xVar != null) {
            boolean c = c();
            if (xVar != null && !c) {
                xVar.onLoginFailure(com.login.nativesso.i.c.a((int) SsoErrorCodes.SDK_NOT_INITIALIZED, "SDK_NOT_INITIALIZED"));
            } else if (!c || com.login.nativesso.i.c.c(this.h)) {
                a.a("SocialLoginCb", xVar);
                Intent intent = new Intent();
                intent.setClass(this.h, DummyActivity.class);
                intent.setFlags(C.ENCODING_PCM_MU_LAW);
                intent.putExtra("SignInBy", LoginManager.SSO_SOCIAL_LOGIN_TYPE_FACEBOOK);
                intent.putExtra("login_link_pic", "login");
                if (strArr != null) {
                    intent.putExtra("permissionRequired", true);
                    intent.putExtra("permission", strArr);
                } else {
                    intent.putExtra("permissionRequired", false);
                }
                this.h.startActivity(intent);
            } else {
                xVar.onLoginFailure(com.login.nativesso.i.c.a((int) SsoErrorCodes.NO_INTERNET_CONNECTION, "NO_INTERNET_CONNECTION"));
            }
        }
    }

    public void a(String str, String str2, k kVar) {
        if (kVar != null) {
            boolean c = c();
            if (kVar != null && !c) {
                kVar.a(com.login.nativesso.i.c.a((int) SsoErrorCodes.SDK_NOT_INITIALIZED, "SDK_NOT_INITIALIZED"));
            } else if (!c || com.login.nativesso.i.c.c(this.h)) {
                com.login.nativesso.i.c.a(str, str2, kVar);
            } else {
                kVar.a(com.login.nativesso.i.c.a((int) SsoErrorCodes.NO_INTERNET_CONNECTION, "NO_INTERNET_CONNECTION"));
            }
        }
    }

    public void a(String str, String str2, m mVar) {
        a(str, str2, null, null, null, mVar);
    }

    public void a(String str, String str2, String str3, String str4, String str5, m mVar) {
        if (mVar != null) {
            boolean c = c();
            if (mVar != null && !c) {
                mVar.onLoginFailure(com.login.nativesso.i.c.a((int) SsoErrorCodes.SDK_NOT_INITIALIZED, "SDK_NOT_INITIALIZED"));
            } else if (!c || com.login.nativesso.i.c.c(this.h)) {
                com.login.nativesso.i.c.a(str, str2, str3, str4, str5, mVar);
            } else {
                mVar.onLoginFailure(com.login.nativesso.i.c.a((int) SsoErrorCodes.NO_INTERNET_CONNECTION, "NO_INTERNET_CONNECTION"));
            }
        }
    }

    public Context d() {
        return this.h;
    }

    public void b(String str, String str2, m mVar) {
        b(str, str2, null, null, null, mVar);
    }

    public void b(String str, String str2, String str3, String str4, String str5, m mVar) {
        if (mVar != null) {
            boolean c = c();
            if (mVar != null && !c) {
                mVar.onLoginFailure(com.login.nativesso.i.c.a((int) SsoErrorCodes.SDK_NOT_INITIALIZED, "SDK_NOT_INITIALIZED"));
            } else if (!c || com.login.nativesso.i.c.c(this.h)) {
                com.login.nativesso.i.c.b(str, str2, str3, str4, str5, mVar);
            } else {
                mVar.onLoginFailure(com.login.nativesso.i.c.a((int) SsoErrorCodes.NO_INTERNET_CONNECTION, "NO_INTERNET_CONNECTION"));
            }
        }
    }

    public void a(String str, String str2, q qVar) {
        if (qVar != null) {
            boolean c = c();
            if (qVar != null && !c) {
                qVar.a(com.login.nativesso.i.c.a((int) SsoErrorCodes.SDK_NOT_INITIALIZED, "SDK_NOT_INITIALIZED"));
            } else if (!c || com.login.nativesso.i.c.c(this.h)) {
                com.login.nativesso.i.c.a(str, str2, qVar);
            } else {
                qVar.a(com.login.nativesso.i.c.a((int) SsoErrorCodes.NO_INTERNET_CONNECTION, "NO_INTERNET_CONNECTION"));
            }
        }
    }

    public void a(Context context, boolean z, u uVar) {
        if (uVar != null) {
            if (context == null) {
                uVar.onFailure(com.login.nativesso.i.c.a(413, "INVALID_REQUEST"));
            } else {
                com.login.nativesso.i.c.a(context, z, uVar);
            }
        }
    }

    public void a(l lVar) {
        if (lVar != null) {
            boolean c = c();
            if (lVar != null && !c) {
                lVar.onFailure(com.login.nativesso.i.c.a((int) SsoErrorCodes.SDK_NOT_INITIALIZED, "SDK_NOT_INITIALIZED"));
            } else if (c && !com.login.nativesso.i.c.c(this.h)) {
                lVar.onFailure(com.login.nativesso.i.c.a((int) SsoErrorCodes.NO_INTERNET_CONNECTION, "NO_INTERNET_CONNECTION"));
            } else if (com.login.nativesso.i.c.d(this.h)) {
                com.login.nativesso.i.c.a(this.h, lVar);
            } else {
                lVar.onFailure(com.login.nativesso.i.c.a(404, "UNAUTHORIZED_ACCESS"));
            }
        }
    }

    public void a(String str, String str2, t tVar) {
        if (tVar != null) {
            boolean c = c();
            if (tVar != null && !c) {
                tVar.a(com.login.nativesso.i.c.a((int) SsoErrorCodes.SDK_NOT_INITIALIZED, "SDK_NOT_INITIALIZED"));
            } else if (!c || com.login.nativesso.i.c.c(this.h)) {
                com.login.nativesso.i.c.a(str, str2, tVar);
            } else {
                tVar.a(com.login.nativesso.i.c.a((int) SsoErrorCodes.NO_INTERNET_CONNECTION, "NO_INTERNET_CONNECTION"));
            }
        }
    }

    public void a(String str, String str2, String str3, com.login.nativesso.a.b bVar) {
        if (bVar != null) {
            boolean c = c();
            if (bVar != null && !c) {
                bVar.a(com.login.nativesso.i.c.a((int) SsoErrorCodes.SDK_NOT_INITIALIZED, "SDK_NOT_INITIALIZED"));
            } else if (!c || com.login.nativesso.i.c.c(this.h)) {
                com.login.nativesso.i.c.a(str, str2, str3, bVar);
            } else {
                bVar.a(com.login.nativesso.i.c.a((int) SsoErrorCodes.NO_INTERNET_CONNECTION, "NO_INTERNET_CONNECTION"));
            }
        }
    }

    public void a(String str, i iVar) {
        if (iVar != null) {
            boolean c = c();
            if (iVar != null && !c) {
                iVar.a(com.login.nativesso.i.c.a((int) SsoErrorCodes.SDK_NOT_INITIALIZED, "SDK_NOT_INITIALIZED"));
            } else if (!c || com.login.nativesso.i.c.c(this.h)) {
                com.login.nativesso.i.c.a(str, null, iVar, "email");
            } else {
                iVar.a(com.login.nativesso.i.c.a((int) SsoErrorCodes.NO_INTERNET_CONNECTION, "NO_INTERNET_CONNECTION"));
            }
        }
    }

    public void b(String str, i iVar) {
        if (iVar != null) {
            boolean c = c();
            if (iVar != null && !c) {
                iVar.a(com.login.nativesso.i.c.a((int) SsoErrorCodes.SDK_NOT_INITIALIZED, "SDK_NOT_INITIALIZED"));
            } else if (!c || com.login.nativesso.i.c.c(this.h)) {
                com.login.nativesso.i.c.a(null, str, iVar, "mobile");
            } else {
                iVar.a(com.login.nativesso.i.c.a((int) SsoErrorCodes.NO_INTERNET_CONNECTION, "NO_INTERNET_CONNECTION"));
            }
        }
    }

    public void a(String str, String str2, String str3, String str4, ag agVar) {
        if (agVar != null) {
            boolean c = c();
            if (agVar != null && !c) {
                agVar.a(com.login.nativesso.i.c.a((int) SsoErrorCodes.SDK_NOT_INITIALIZED, "SDK_NOT_INITIALIZED"));
            } else if (!c || com.login.nativesso.i.c.c(this.h)) {
                com.login.nativesso.i.c.a(str, null, str2, str3, str4, agVar);
            } else {
                agVar.a(com.login.nativesso.i.c.a((int) SsoErrorCodes.NO_INTERNET_CONNECTION, "NO_INTERNET_CONNECTION"));
            }
        }
    }

    public void b(String str, String str2, String str3, String str4, ag agVar) {
        if (agVar != null) {
            boolean c = c();
            if (agVar != null && !c) {
                agVar.a(com.login.nativesso.i.c.a((int) SsoErrorCodes.SDK_NOT_INITIALIZED, "SDK_NOT_INITIALIZED"));
            } else if (!c || com.login.nativesso.i.c.c(this.h)) {
                com.login.nativesso.i.c.a(null, str, str2, str3, str4, agVar);
            } else {
                agVar.a(com.login.nativesso.i.c.a((int) SsoErrorCodes.NO_INTERNET_CONNECTION, "NO_INTERNET_CONNECTION"));
            }
        }
    }

    public void a(g gVar) {
        if (gVar != null) {
            boolean c = c();
            v g = gVar.g();
            if (g != null) {
                if (g != null && !c) {
                    g.onFailure(com.login.nativesso.i.c.a((int) SsoErrorCodes.SDK_NOT_INITIALIZED, "SDK_NOT_INITIALIZED"));
                } else if (!c || com.login.nativesso.i.c.c(this.h) || g == null) {
                    com.login.nativesso.i.c.a(gVar);
                } else {
                    g.onFailure(com.login.nativesso.i.c.a((int) SsoErrorCodes.NO_INTERNET_CONNECTION, "NO_INTERNET_CONNECTION"));
                }
            }
        }
    }

    public void a(String str, String str2, String str3, ah ahVar) {
        if (ahVar != null) {
            boolean c = c();
            if (ahVar != null && !c) {
                ahVar.onFailure(com.login.nativesso.i.c.a((int) SsoErrorCodes.SDK_NOT_INITIALIZED, "SDK_NOT_INITIALIZED"));
            } else if (!c || com.login.nativesso.i.c.c(this.h)) {
                com.login.nativesso.i.c.a(str, str2, str3, ahVar);
            } else {
                ahVar.onFailure(com.login.nativesso.i.c.a((int) SsoErrorCodes.NO_INTERNET_CONNECTION, "NO_INTERNET_CONNECTION"));
            }
        }
    }

    public void a(String str, String str2, r rVar) {
        if (rVar != null) {
            boolean c = c();
            if (rVar != null && !c) {
                rVar.onFailure(com.login.nativesso.i.c.a((int) SsoErrorCodes.SDK_NOT_INITIALIZED, "SDK_NOT_INITIALIZED"));
            } else if (!c || com.login.nativesso.i.c.c(this.h)) {
                com.login.nativesso.i.c.a(str, str2, rVar);
            } else {
                rVar.onFailure(com.login.nativesso.i.c.a((int) SsoErrorCodes.NO_INTERNET_CONNECTION, "NO_INTERNET_CONNECTION"));
            }
        }
    }

    public void a(boolean z, j jVar) {
        if (jVar != null) {
            boolean c = c();
            if (jVar != null && !c) {
                jVar.a(com.login.nativesso.i.c.a((int) SsoErrorCodes.SDK_NOT_INITIALIZED, "SDK_NOT_INITIALIZED"));
            } else if (!c || com.login.nativesso.i.c.c(this.h)) {
                com.login.nativesso.i.c.a(this.h, z, jVar);
            } else {
                jVar.a(com.login.nativesso.i.c.a((int) SsoErrorCodes.NO_INTERNET_CONNECTION, "NO_INTERNET_CONNECTION"));
            }
        }
    }

    public void a(h hVar) {
        if (hVar != null) {
            boolean c = c();
            if (hVar == null || c) {
                com.login.nativesso.i.c.a(this.h, hVar);
            } else {
                hVar.onFailure(com.login.nativesso.i.c.a((int) SsoErrorCodes.SDK_NOT_INITIALIZED, "SDK_NOT_INITIALIZED"));
            }
        }
    }

    public void a(e eVar) {
        if (eVar != null) {
            boolean c = c();
            if (eVar != null && !c) {
                eVar.a(com.login.nativesso.i.c.a((int) SsoErrorCodes.SDK_NOT_INITIALIZED, "SDK_NOT_INITIALIZED"));
            } else if (!c || com.login.nativesso.i.c.c(this.h)) {
                com.login.nativesso.i.c.a(this.h, eVar);
            } else {
                eVar.a(com.login.nativesso.i.c.a((int) SsoErrorCodes.NO_INTERNET_CONNECTION, "NO_INTERNET_CONNECTION"));
            }
        }
    }

    public void a(String str, n nVar) {
        if (nVar != null) {
            boolean c = c();
            if (nVar != null && !c) {
                nVar.onFailure(com.login.nativesso.i.c.a((int) SsoErrorCodes.SDK_NOT_INITIALIZED, "SDK_NOT_INITIALIZED"));
            } else if (!c || com.login.nativesso.i.c.c(this.h)) {
                com.login.nativesso.i.c.a(str, this.h, nVar);
            } else {
                nVar.onFailure(com.login.nativesso.i.c.a((int) SsoErrorCodes.NO_INTERNET_CONNECTION, "NO_INTERNET_CONNECTION"));
            }
        }
    }

    public void a(String str, f fVar) {
        if (fVar != null) {
            boolean c = c();
            if (fVar != null && !c) {
                fVar.onFailure(com.login.nativesso.i.c.a((int) SsoErrorCodes.SDK_NOT_INITIALIZED, "SDK_NOT_INITIALIZED"));
            } else if (!c || com.login.nativesso.i.c.c(this.h)) {
                com.login.nativesso.i.c.a(str, this.h, fVar);
            } else {
                fVar.onFailure(com.login.nativesso.i.c.a((int) SsoErrorCodes.NO_INTERNET_CONNECTION, "NO_INTERNET_CONNECTION"));
            }
        }
    }

    public void a(String str, aa aaVar) {
        if (aaVar != null) {
            boolean c = c();
            if (aaVar != null && !c) {
                aaVar.a(com.login.nativesso.i.c.a((int) SsoErrorCodes.SDK_NOT_INITIALIZED, "SDK_NOT_INITIALIZED"));
            } else if (c && !com.login.nativesso.i.c.c(this.h)) {
                aaVar.a(com.login.nativesso.i.c.a((int) SsoErrorCodes.NO_INTERNET_CONNECTION, "NO_INTERNET_CONNECTION"));
            } else if (com.login.nativesso.i.c.d(this.h)) {
                com.login.nativesso.i.c.a(str, aaVar);
            } else {
                aaVar.a(com.login.nativesso.i.c.a(404, "UNAUTHORIZED_ACCESS"));
            }
        }
    }

    public void a(String str, String str2, af afVar) {
        if (afVar != null) {
            boolean c = c();
            if (afVar != null && !c) {
                afVar.a(com.login.nativesso.i.c.a((int) SsoErrorCodes.SDK_NOT_INITIALIZED, "SDK_NOT_INITIALIZED"));
            } else if (c && !com.login.nativesso.i.c.c(this.h)) {
                afVar.a(com.login.nativesso.i.c.a((int) SsoErrorCodes.NO_INTERNET_CONNECTION, "NO_INTERNET_CONNECTION"));
            } else if (com.login.nativesso.i.c.d(this.h)) {
                com.login.nativesso.i.c.a(str, str2, afVar);
            } else {
                afVar.a(com.login.nativesso.i.c.a(404, "UNAUTHORIZED_ACCESS"));
            }
        }
    }

    public void a(String str, String str2, String str3, String str4, String str5, ab abVar) {
        if (abVar != null) {
            boolean c = c();
            if (abVar != null && !c) {
                abVar.a(com.login.nativesso.i.c.a((int) SsoErrorCodes.SDK_NOT_INITIALIZED, "SDK_NOT_INITIALIZED"));
            } else if (c && !com.login.nativesso.i.c.c(this.h)) {
                abVar.a(com.login.nativesso.i.c.a((int) SsoErrorCodes.NO_INTERNET_CONNECTION, "NO_INTERNET_CONNECTION"));
            } else if (com.login.nativesso.i.c.d(this.h)) {
                com.login.nativesso.i.c.a(str, str2, str3, str4, str5, abVar);
            } else {
                abVar.a(com.login.nativesso.i.c.a(404, "UNAUTHORIZED_ACCESS"));
            }
        }
    }

    public void a(String str, String str2, String str3, ac acVar) {
        if (acVar != null) {
            boolean c = c();
            if (acVar != null && !c) {
                acVar.a(com.login.nativesso.i.c.a((int) SsoErrorCodes.SDK_NOT_INITIALIZED, "SDK_NOT_INITIALIZED"));
            } else if (c && !com.login.nativesso.i.c.c(this.h)) {
                acVar.a(com.login.nativesso.i.c.a((int) SsoErrorCodes.NO_INTERNET_CONNECTION, "NO_INTERNET_CONNECTION"));
            } else if (com.login.nativesso.i.c.d(this.h)) {
                com.login.nativesso.i.c.a(str, str2, str3, acVar);
            } else {
                acVar.a(com.login.nativesso.i.c.a(404, "UNAUTHORIZED_ACCESS"));
            }
        }
    }

    public void b(String str, aa aaVar) {
        if (aaVar != null) {
            boolean c = c();
            if (aaVar != null && !c) {
                aaVar.a(com.login.nativesso.i.c.a((int) SsoErrorCodes.SDK_NOT_INITIALIZED, "SDK_NOT_INITIALIZED"));
            } else if (c && !com.login.nativesso.i.c.c(this.h)) {
                aaVar.a(com.login.nativesso.i.c.a((int) SsoErrorCodes.NO_INTERNET_CONNECTION, "NO_INTERNET_CONNECTION"));
            } else if (com.login.nativesso.i.c.d(this.h)) {
                com.login.nativesso.i.c.b(str, aaVar);
            } else {
                aaVar.a(com.login.nativesso.i.c.a(404, "UNAUTHORIZED_ACCESS"));
            }
        }
    }

    public void b(String str, String str2, af afVar) {
        if (afVar != null) {
            boolean c = c();
            if (afVar != null && !c) {
                afVar.a(com.login.nativesso.i.c.a((int) SsoErrorCodes.SDK_NOT_INITIALIZED, "SDK_NOT_INITIALIZED"));
            } else if (c && !com.login.nativesso.i.c.c(this.h)) {
                afVar.a(com.login.nativesso.i.c.a((int) SsoErrorCodes.NO_INTERNET_CONNECTION, "NO_INTERNET_CONNECTION"));
            } else if (com.login.nativesso.i.c.d(this.h)) {
                com.login.nativesso.i.c.b(str, str2, afVar);
            } else {
                afVar.a(com.login.nativesso.i.c.a(404, "UNAUTHORIZED_ACCESS"));
            }
        }
    }

    public void a(ad adVar) {
        a(null, adVar);
    }

    public void a(String str, ad adVar) {
        if (adVar != null) {
            boolean c = c();
            if (adVar != null && !c) {
                adVar.a(com.login.nativesso.i.c.a((int) SsoErrorCodes.SDK_NOT_INITIALIZED, "SDK_NOT_INITIALIZED"));
            } else if (c && !com.login.nativesso.i.c.c(this.h)) {
                adVar.a(com.login.nativesso.i.c.a((int) SsoErrorCodes.NO_INTERNET_CONNECTION, "NO_INTERNET_CONNECTION"));
            } else if (com.login.nativesso.i.c.d(this.h)) {
                com.login.nativesso.i.c.a(str, this.h, adVar);
            } else {
                adVar.a(com.login.nativesso.i.c.a(404, "UNAUTHORIZED_ACCESS"));
            }
        }
    }

    public void a(String str, com.login.nativesso.a.g gVar) {
        if (gVar != null) {
            boolean c = c();
            if (gVar != null && !c) {
                gVar.a(com.login.nativesso.i.c.a((int) SsoErrorCodes.SDK_NOT_INITIALIZED, "SDK_NOT_INITIALIZED"));
            } else if (c && !com.login.nativesso.i.c.c(this.h)) {
                gVar.a(com.login.nativesso.i.c.a((int) SsoErrorCodes.NO_INTERNET_CONNECTION, "NO_INTERNET_CONNECTION"));
            } else if (com.login.nativesso.i.c.d(this.h)) {
                com.login.nativesso.i.c.a(str, gVar);
            } else {
                gVar.a(com.login.nativesso.i.c.a(404, "UNAUTHORIZED_ACCESS"));
            }
        }
    }

    public void a(w wVar) {
        if (wVar != null) {
            boolean c = c();
            if (wVar != null && !c) {
                wVar.a(com.login.nativesso.i.c.a((int) SsoErrorCodes.SDK_NOT_INITIALIZED, "SDK_NOT_INITIALIZED"));
            } else if (c && !com.login.nativesso.i.c.c(this.h)) {
                wVar.a(com.login.nativesso.i.c.a((int) SsoErrorCodes.NO_INTERNET_CONNECTION, "NO_INTERNET_CONNECTION"));
            } else if (com.login.nativesso.i.c.d(this.h)) {
                a.a("SocialLinkCb", wVar);
                Intent intent = new Intent();
                intent.setFlags(C.ENCODING_PCM_MU_LAW);
                intent.setClass(this.h, DummyActivity.class);
                intent.putExtra("SignInBy", LoginManager.SSO_SOCIAL_LOGIN_TYPE_FACEBOOK);
                intent.putExtra("login_link_pic", "link");
                this.h.startActivity(intent);
            } else {
                wVar.a(com.login.nativesso.i.c.a(404, "UNAUTHORIZED_ACCESS"));
            }
        }
    }

    public void a(String str, w wVar) {
        if (wVar != null) {
            boolean c = c();
            if (wVar != null && !c) {
                wVar.a(com.login.nativesso.i.c.a((int) SsoErrorCodes.SDK_NOT_INITIALIZED, "SDK_NOT_INITIALIZED"));
            } else if (c && !com.login.nativesso.i.c.c(this.h)) {
                wVar.a(com.login.nativesso.i.c.a((int) SsoErrorCodes.NO_INTERNET_CONNECTION, "NO_INTERNET_CONNECTION"));
            } else if (com.login.nativesso.i.c.d(this.h)) {
                a.a("SocialLinkCb", wVar);
                Intent intent = new Intent();
                intent.setFlags(C.ENCODING_PCM_MU_LAW);
                intent.setClass(this.h, DummyActivity.class);
                intent.putExtra("SignInBy", "googlePlus");
                intent.putExtra("login_link_pic", "link");
                intent.putExtra("clientId", str);
                this.h.startActivity(intent);
            } else {
                wVar.a(com.login.nativesso.i.c.a(404, "UNAUTHORIZED_ACCESS"));
            }
        }
    }

    public void a(String str, y yVar) {
        if (yVar != null) {
            boolean c = c();
            if (yVar != null && !c) {
                yVar.a(com.login.nativesso.i.c.a((int) SsoErrorCodes.SDK_NOT_INITIALIZED, "SDK_NOT_INITIALIZED"));
            } else if (c && !com.login.nativesso.i.c.c(this.h)) {
                yVar.a(com.login.nativesso.i.c.a((int) SsoErrorCodes.NO_INTERNET_CONNECTION, "NO_INTERNET_CONNECTION"));
            } else if (com.login.nativesso.i.c.d(this.h)) {
                a.a("SocialPicUploadCb", yVar);
                Intent intent = new Intent();
                intent.setFlags(C.ENCODING_PCM_MU_LAW);
                intent.setClass(this.h, DummyActivity.class);
                intent.putExtra("SignInBy", "googlePlus");
                intent.putExtra("login_link_pic", "pic");
                intent.putExtra("clientId", str);
                this.h.startActivity(intent);
            } else {
                yVar.a(com.login.nativesso.i.c.a(404, "UNAUTHORIZED_ACCESS"));
            }
        }
    }

    public void a(y yVar) {
        if (yVar != null) {
            boolean c = c();
            if (yVar != null && !c) {
                yVar.a(com.login.nativesso.i.c.a((int) SsoErrorCodes.SDK_NOT_INITIALIZED, "SDK_NOT_INITIALIZED"));
            } else if (c && !com.login.nativesso.i.c.c(this.h)) {
                yVar.a(com.login.nativesso.i.c.a((int) SsoErrorCodes.NO_INTERNET_CONNECTION, "NO_INTERNET_CONNECTION"));
            } else if (com.login.nativesso.i.c.d(this.h)) {
                a.a("SocialPicUploadCb", yVar);
                Intent intent = new Intent();
                intent.setFlags(C.ENCODING_PCM_MU_LAW);
                intent.setClass(this.h, DummyActivity.class);
                intent.putExtra("SignInBy", LoginManager.SSO_SOCIAL_LOGIN_TYPE_FACEBOOK);
                intent.putExtra("login_link_pic", "pic");
                this.h.startActivity(intent);
            } else {
                yVar.a(com.login.nativesso.i.c.a(404, "UNAUTHORIZED_ACCESS"));
            }
        }
    }

    public void a(String str, String str2, String str3, boolean z, x xVar) {
        a(str, str2, str3, "", z, xVar);
    }

    public void a(String str, String str2, String str3, String str4, boolean z, x xVar) {
        if (xVar != null) {
            boolean c = c();
            if (xVar != null && !c) {
                xVar.onLoginFailure(com.login.nativesso.i.c.a((int) SsoErrorCodes.SDK_NOT_INITIALIZED, "SDK_NOT_INITIALIZED"));
            } else if (!c || com.login.nativesso.i.c.c(this.h)) {
                com.login.nativesso.i.c.a(this.h, str, str2, str3, str4, z, xVar);
            } else {
                xVar.onLoginFailure(com.login.nativesso.i.c.a((int) SsoErrorCodes.NO_INTERNET_CONNECTION, "NO_INTERNET_CONNECTION"));
            }
        }
    }

    public void a(String str, String str2, String str3, w wVar) {
        if (wVar != null) {
            boolean c = c();
            if (wVar != null && !c) {
                wVar.a(com.login.nativesso.i.c.a((int) SsoErrorCodes.SDK_NOT_INITIALIZED, "SDK_NOT_INITIALIZED"));
            } else if (c && !com.login.nativesso.i.c.c(this.h)) {
                wVar.a(com.login.nativesso.i.c.a((int) SsoErrorCodes.NO_INTERNET_CONNECTION, "NO_INTERNET_CONNECTION"));
            } else if (!com.login.nativesso.i.c.d(this.h)) {
                wVar.a(com.login.nativesso.i.c.a(404, "UNAUTHORIZED_ACCESS"));
            } else if (com.login.nativesso.i.c.a(str3)) {
                wVar.a(com.login.nativesso.i.c.a(4005, "SOCIAL_TYPE_MISSING"));
            } else if (LoginManager.SSO_SOCIAL_LOGIN_TYPE_FACEBOOK.equalsIgnoreCase(str3) || LoginManager.SSO_SOCIAL_LOGIN_TYPE_GOOGLE.equalsIgnoreCase(str3)) {
                a.a("SocialLinkCb", wVar);
                com.login.nativesso.i.c.a(str, str2, str3.toLowerCase());
            } else {
                wVar.a(com.login.nativesso.i.c.a(4006, "SOCIAL_TYPE_INVALID"));
            }
        }
    }

    public void a(p pVar) {
        if (pVar != null) {
            boolean c = c();
            if (pVar != null && !c) {
                pVar.a(com.login.nativesso.i.c.a((int) SsoErrorCodes.SDK_NOT_INITIALIZED, "SDK_NOT_INITIALIZED"));
            } else if (!c || com.login.nativesso.i.c.c(this.h)) {
                com.login.nativesso.i.c.a(pVar);
            } else {
                pVar.a(com.login.nativesso.i.c.a((int) SsoErrorCodes.NO_INTERNET_CONNECTION, "NO_INTERNET_CONNECTION"));
            }
        }
    }

    public void a(com.login.nativesso.a.a aVar) {
        if (aVar != null) {
            boolean c = c();
            if (aVar != null && !c) {
                aVar.a(com.login.nativesso.i.c.a((int) SsoErrorCodes.SDK_NOT_INITIALIZED, "SDK_NOT_INITIALIZED"));
            } else if (!c || com.login.nativesso.i.c.c(this.h)) {
                com.login.nativesso.i.c.a(aVar);
            } else {
                aVar.a(com.login.nativesso.i.c.a((int) SsoErrorCodes.NO_INTERNET_CONNECTION, "NO_INTERNET_CONNECTION"));
            }
        }
    }

    public void a(String str, d dVar) {
        if (dVar != null) {
            boolean c = c();
            if (dVar != null && !c) {
                dVar.a(com.login.nativesso.i.c.a((int) SsoErrorCodes.SDK_NOT_INITIALIZED, "SDK_NOT_INITIALIZED"));
            } else if (!c || com.login.nativesso.i.c.c(this.h)) {
                com.login.nativesso.i.c.a(str, dVar);
            } else {
                dVar.a(com.login.nativesso.i.c.a((int) SsoErrorCodes.NO_INTERNET_CONNECTION, "NO_INTERNET_CONNECTION"));
            }
        }
    }

    public void a(String str, String str2, String str3, String str4, String str5, String str6, o oVar) {
        if (oVar != null) {
            boolean c = c();
            if (oVar != null && !c) {
                oVar.a(com.login.nativesso.i.c.a((int) SsoErrorCodes.SDK_NOT_INITIALIZED, "SDK_NOT_INITIALIZED"));
            } else if (!c || com.login.nativesso.i.c.c(this.h)) {
                com.login.nativesso.i.c.a(str, str2, str3, str4, str5, str6, oVar);
            } else {
                oVar.a(com.login.nativesso.i.c.a((int) SsoErrorCodes.NO_INTERNET_CONNECTION, "NO_INTERNET_CONNECTION"));
            }
        }
    }

    public void a(String str, String str2, String str3, ae aeVar) {
        if (aeVar != null) {
            boolean c = c();
            if (aeVar != null && !c) {
                aeVar.a(com.login.nativesso.i.c.a((int) SsoErrorCodes.SDK_NOT_INITIALIZED, "SDK_NOT_INITIALIZED"));
            } else if (!c || com.login.nativesso.i.c.c(this.h)) {
                com.login.nativesso.i.c.a(str, str2, str3, aeVar);
            } else {
                aeVar.a(com.login.nativesso.i.c.a((int) SsoErrorCodes.NO_INTERNET_CONNECTION, "NO_INTERNET_CONNECTION"));
            }
        }
    }

    public void c(String str, String str2, m mVar) {
        if (mVar != null) {
            boolean c = c();
            if (mVar != null && !c) {
                mVar.onLoginFailure(com.login.nativesso.i.c.a((int) SsoErrorCodes.SDK_NOT_INITIALIZED, "SDK_NOT_INITIALIZED"));
            } else if (!c || com.login.nativesso.i.c.c(this.h)) {
                com.login.nativesso.i.c.a(this.h, str, str2, mVar);
            } else {
                mVar.onLoginFailure(com.login.nativesso.i.c.a((int) SsoErrorCodes.NO_INTERNET_CONNECTION, "NO_INTERNET_CONNECTION"));
            }
        }
    }

    public void a(String str, String str2) {
        e = str;
        f = str2;
    }

    public void b(l lVar) {
        if (lVar != null) {
            boolean c = c();
            if (lVar != null && !c) {
                lVar.onFailure(com.login.nativesso.i.c.a((int) SsoErrorCodes.SDK_NOT_INITIALIZED, "SDK_NOT_INITIALIZED"));
            } else if (com.login.nativesso.i.c.d(this.h)) {
                lVar.onSuccess((com.login.nativesso.e.e) com.login.nativesso.g.a.a(this.h, "object_prefs", 0).a("USER_INFO", com.login.nativesso.e.e.class));
            } else {
                lVar.onFailure(com.login.nativesso.i.c.a(404, "UNAUTHORIZED_ACCESS"));
            }
        }
    }

    public void a(String str, z zVar) {
        if (zVar != null) {
            boolean c = c();
            if (zVar != null && !c) {
                zVar.a(com.login.nativesso.i.c.a((int) SsoErrorCodes.SDK_NOT_INITIALIZED, "SDK_NOT_INITIALIZED"));
            } else if (!c || com.login.nativesso.i.c.c(this.h)) {
                a.a("TrueCallerLoginCb", zVar);
                Intent intent = new Intent();
                intent.setFlags(C.ENCODING_PCM_MU_LAW);
                intent.setClass(this.h, DummyActivity.class);
                intent.putExtra("SignInBy", "trueCaller");
                intent.putExtra("Language", str);
                this.h.startActivity(intent);
            } else {
                zVar.a(com.login.nativesso.i.c.a((int) SsoErrorCodes.NO_INTERNET_CONNECTION, "NO_INTERNET_CONNECTION"));
            }
        }
    }
}
