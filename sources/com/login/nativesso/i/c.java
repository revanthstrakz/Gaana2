package com.login.nativesso.i;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.toolbox.n;
import com.gaana.login.LoginManager;
import com.gaana.login.sso.SsoErrorCodes;
import com.google.android.exoplayer2.C;
import com.google.api.client.http.HttpMethods;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.login.nativesso.a.aa;
import com.login.nativesso.a.ae;
import com.login.nativesso.a.af;
import com.login.nativesso.a.ag;
import com.login.nativesso.a.ah;
import com.login.nativesso.a.d;
import com.login.nativesso.a.k;
import com.login.nativesso.a.m;
import com.login.nativesso.a.p;
import com.login.nativesso.a.t;
import com.login.nativesso.a.u;
import com.login.nativesso.a.x;
import com.login.nativesso.activity.UploadProfilePicActivity;
import com.login.nativesso.c.ab;
import com.login.nativesso.c.ac;
import com.login.nativesso.c.ad;
import com.login.nativesso.c.g;
import com.login.nativesso.c.h;
import com.login.nativesso.c.i;
import com.login.nativesso.c.l;
import com.login.nativesso.c.o;
import com.login.nativesso.c.r;
import com.login.nativesso.c.s;
import com.login.nativesso.c.v;
import com.login.nativesso.d.a;
import com.login.nativesso.exception.ServerException;
import com.login.nativesso.g.b;
import com.login.nativesso.h.e;
import com.login.nativesso.h.f;
import com.login.nativesso.h.j;
import com.login.nativesso.h.q;
import com.login.nativesso.h.w;
import com.login.nativesso.h.y;
import com.login.nativesso.h.z;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.Thread.UncaughtExceptionHandler;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;

public class c {
    public static void a(Context context, boolean z, u uVar) {
        b a = b.a();
        String b = a.b(context);
        if (a(a.a("TICKETID", context))) {
            uVar.onFailure(a(413, "INVALID_REQUEST"));
            return;
        }
        if (!(com.login.nativesso.d.c.a().d() == null || context == null || !c(context))) {
            a();
        }
        try {
            JSONObject a2 = a.a(context);
            String str = null;
            if (a2 != null && a2.has("SSECID")) {
                str = a2.getString("SSECID");
            }
            a(context);
            if (!a(str) && str.equals(b)) {
                a.a(context, a.a(context));
            }
            if (!z) {
                a.a();
                a.c();
            }
            if (uVar != null) {
                com.login.nativesso.g.a a3 = com.login.nativesso.g.a.a(context, "object_prefs", 0);
                a3.b();
                a3.a();
                uVar.onSuccess();
            }
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            if (uVar != null) {
                uVar.onFailure(a((int) SsoErrorCodes.REQUEST_FAILED, "REQUEST_FAILED"));
            }
        }
    }

    public static void a() {
        r rVar = new r();
        com.login.nativesso.f.a.a().a(new q(1, null, rVar, rVar, null));
    }

    public static b a(Context context) {
        b a = b.a();
        Editor edit = a.d(context).edit();
        edit.putString("SSECID", "");
        edit.putString("SOCIALTYPE", "");
        edit.putString("TICKETID", "");
        edit.apply();
        com.login.nativesso.g.a a2 = com.login.nativesso.g.a.a(context, "object_prefs", 0);
        a2.b();
        a2.a();
        return a;
    }

    public static boolean a(String str) {
        return str == null || str.isEmpty();
    }

    public static void a(String str, String str2, k kVar) {
        if (a(str) && a(str2)) {
            kVar.a(a(413, "INVALID_REQUEST"));
            return;
        }
        com.login.nativesso.b.a.a("GetLoginOtpCb", kVar);
        JSONObject a = f.a(str, str2);
        g gVar = new g();
        com.login.nativesso.f.a.a().a(new f(1, a, gVar, gVar, null));
    }

    public static void a(String str, String str2, String str3, String str4, String str5, m mVar) {
        if (a(str) || a(str2)) {
            mVar.onLoginFailure(a(413, "INVALID_REQUEST"));
            return;
        }
        com.login.nativesso.b.a.a("LoginCb", mVar);
        JSONObject a = com.login.nativesso.h.k.a(str, str2, str3, str4, str5);
        l lVar = new l();
        Request kVar;
        if (str3 == null || str4 == null || str5 == null) {
            kVar = new com.login.nativesso.h.k(1, a, lVar, lVar, null, b.j);
        } else {
            kVar = new com.login.nativesso.h.k(1, a, lVar, lVar, null, b.k);
        }
        com.login.nativesso.f.a.a().a(r8);
    }

    public static void a(String str, String str2, String str3, String str4, boolean z, String str5, String str6, String str7, x xVar) {
        com.login.nativesso.b.a.a("SocialLoginCb", xVar);
        v vVar = new v(LoginManager.SSO_SOCIAL_LOGIN_TYPE_GOOGLE);
        final String str8 = str4;
        final String str9 = str2;
        final String str10 = str3;
        final String str11 = str5;
        final String str12 = str;
        final String str13 = str6;
        final String str14 = str7;
        com.login.nativesso.f.a.a().a(new n(1, b.d, vVar, vVar) {
            /* Access modifiers changed, original: protected */
            public Map<String, String> getParams() throws AuthFailureError {
                HashMap hashMap = new HashMap();
                hashMap.put("oauthId", str8);
                hashMap.put("siteId", str9);
                hashMap.put("accessToken", str10);
                hashMap.put("deviceId", str11);
                hashMap.put("ssecreq", "yes");
                hashMap.put("channel", str12);
                hashMap.put("sitereg", str13);
                if (str14 != null && str14.length() > 1) {
                    hashMap.put("deviceId", str14);
                }
                try {
                    Context d = com.login.nativesso.d.c.a().d();
                    if (d != null) {
                        hashMap.put("appVersion", d.getPackageManager().getPackageInfo(d.getPackageName(), 0).versionName);
                    }
                } catch (NameNotFoundException e) {
                    ThrowableExtension.printStackTrace(e);
                }
                hashMap.put("platform", "android");
                return hashMap;
            }

            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap hashMap = new HashMap();
                try {
                    hashMap.put("appVersion", com.login.nativesso.d.c.a().d().getPackageManager().getPackageInfo(com.login.nativesso.d.c.a().d().getPackageName(), 0).versionName);
                } catch (NameNotFoundException e) {
                    ThrowableExtension.printStackTrace(e);
                }
                hashMap.put("platform", "android");
                return hashMap;
            }
        });
    }

    public static void b(String str, String str2, String str3, String str4, boolean z, String str5, String str6, String str7, x xVar) {
        com.login.nativesso.b.a.a("SocialLoginCb", xVar);
        v vVar = new v("linkedin");
        final String str8 = str4;
        final String str9 = str2;
        final String str10 = str3;
        final String str11 = str5;
        final String str12 = str;
        final String str13 = str6;
        final String str14 = str7;
        com.login.nativesso.f.a.a().a(new n(1, b.e, vVar, vVar) {
            /* Access modifiers changed, original: protected */
            public Map<String, String> getParams() throws AuthFailureError {
                HashMap hashMap = new HashMap();
                hashMap.put("oauthId", str8);
                hashMap.put("siteId", str9);
                hashMap.put("accessToken", str10);
                hashMap.put("deviceId", str11);
                hashMap.put("ssecreq", "yes");
                hashMap.put("channel", str12);
                hashMap.put("sitereg", str13);
                if (str14 != null && str14.length() > 1) {
                    hashMap.put("deviceId", str14);
                }
                try {
                    Context d = com.login.nativesso.d.c.a().d();
                    if (d != null) {
                        hashMap.put("appVersion", d.getPackageManager().getPackageInfo(d.getPackageName(), 0).versionName);
                    }
                } catch (NameNotFoundException e) {
                    ThrowableExtension.printStackTrace(e);
                }
                hashMap.put("platform", "android");
                return hashMap;
            }

            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap hashMap = new HashMap();
                try {
                    hashMap.put("appVersion", com.login.nativesso.d.c.a().d().getPackageManager().getPackageInfo(com.login.nativesso.d.c.a().d().getPackageName(), 0).versionName);
                } catch (NameNotFoundException e) {
                    ThrowableExtension.printStackTrace(e);
                }
                hashMap.put("platform", "android");
                return hashMap;
            }
        });
    }

    public static void a(String str, String str2, String str3, String str4, boolean z, String str5, String str6, String str7, String str8, x xVar) {
        com.login.nativesso.b.a.a("SocialLoginCb", xVar);
        v vVar = new v(LoginManager.SSO_SOCIAL_LOGIN_TYPE_FACEBOOK);
        final String str9 = str4;
        final String str10 = str2;
        final String str11 = str3;
        final String str12 = str5;
        final String str13 = str;
        final String str14 = str6;
        final String str15 = str7;
        final String str16 = str8;
        com.login.nativesso.f.a.a().a(new n(1, b.g, vVar, vVar) {
            /* Access modifiers changed, original: protected */
            public Map<String, String> getParams() throws AuthFailureError {
                HashMap hashMap = new HashMap();
                hashMap.put("oauthId", str9);
                hashMap.put("siteId", str10);
                hashMap.put("accessToken", str11);
                hashMap.put("deviceId", str12);
                hashMap.put("ssecreq", "yes");
                hashMap.put("channel", str13);
                hashMap.put("sitereg", str14);
                hashMap.put("user_mobile_phone", str15);
                if (str16 != null && str16.length() > 1) {
                    hashMap.put("deviceId", str16);
                }
                try {
                    Context d = com.login.nativesso.d.c.a().d();
                    if (d != null) {
                        hashMap.put("appVersion", d.getPackageManager().getPackageInfo(d.getPackageName(), 0).versionName);
                    }
                } catch (NameNotFoundException e) {
                    ThrowableExtension.printStackTrace(e);
                }
                hashMap.put("platform", "android");
                return hashMap;
            }

            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap hashMap = new HashMap();
                try {
                    hashMap.put("appVersion", com.login.nativesso.d.c.a().d().getPackageManager().getPackageInfo(com.login.nativesso.d.c.a().d().getPackageName(), 0).versionName);
                } catch (NameNotFoundException e) {
                    ThrowableExtension.printStackTrace(e);
                }
                hashMap.put("platform", "android");
                return hashMap;
            }
        });
    }

    public static void b(String str, String str2, String str3, String str4, String str5, m mVar) {
        if (a(str) || a(str2)) {
            mVar.onLoginFailure(a(413, "INVALID_REQUEST"));
            return;
        }
        com.login.nativesso.b.a.a("LoginCb", mVar);
        JSONObject b = com.login.nativesso.h.k.b(str, str2, str3, str4, str5);
        l lVar = new l();
        Request kVar;
        if (str3 == null || str4 == null || str5 == null) {
            kVar = new com.login.nativesso.h.k(1, b, lVar, lVar, null, b.j);
        } else {
            kVar = new com.login.nativesso.h.k(1, b, lVar, lVar, null, b.k);
        }
        com.login.nativesso.f.a.a().a(r8);
    }

    public static void a(String str, String str2, com.login.nativesso.a.q qVar) {
        if (a(str) && a(str2)) {
            qVar.a(a(413, "INVALID_REQUEST"));
            return;
        }
        JSONObject a = com.login.nativesso.h.n.a(str, str2);
        com.login.nativesso.b.a.a("ResendFPOtpCb", qVar);
        o oVar = new o();
        com.login.nativesso.f.a.a().a(new com.login.nativesso.h.n(1, a, oVar, oVar, null));
    }

    public static void a(Context context, String str, String str2, m mVar) {
        com.login.nativesso.b.a.a("LoginCb", mVar);
        l lVar = new l();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(AccountKitGraphConstants.TOKEN_RESPONSE_TYPE, str);
            jSONObject.put("phoneNumber", str2);
        } catch (JSONException unused) {
        }
        com.login.nativesso.f.a.a().a(new com.login.nativesso.h.k(1, jSONObject, lVar, lVar, null, b.G));
    }

    public static void a(com.login.nativesso.a.a aVar) {
        com.login.nativesso.b.a.a("BlockUserChannelCb", aVar);
        com.login.nativesso.c.b bVar = new com.login.nativesso.c.b();
        com.login.nativesso.f.a.a().a(new com.login.nativesso.h.b(1, null, bVar, bVar, null, b.N));
    }

    public static String b(Context context) {
        return new BigInteger(UUID.randomUUID().toString().replaceAll("-", ""), 16).toString(36);
    }

    public static void b() {
        h hVar = new h();
        com.login.nativesso.f.a.a().a(new com.login.nativesso.h.g(1, null, hVar, hVar, null, b.q));
    }

    public static void a(Context context, com.login.nativesso.a.l lVar) {
        b a = b.a();
        String b = a.b(context);
        String a2 = a.a("channel", context);
        String a3 = a.a("TICKETID", context);
        if (a(b) || a(a3)) {
            lVar.onFailure(a(413, "INVALID_REQUEST"));
            return;
        }
        com.login.nativesso.b.a.a("GetUserDetailsCb", lVar);
        com.login.nativesso.c.k kVar = new com.login.nativesso.c.k();
        com.login.nativesso.f.a.a().a(new j(1, null, kVar, kVar, null));
        try {
            JSONObject a4 = a.a(context);
            if (a4 != null) {
                String string = a4.getString("SSECID");
                if (b.equalsIgnoreCase(string)) {
                    String string2 = a4.getString("TICKETID");
                    if (!(a(string) || a(string2))) {
                        HashMap hashMap = new HashMap();
                        hashMap.put("channel", a2);
                        hashMap.put("ticketId", string2);
                        hashMap.put("ssec", string);
                        i iVar = new i();
                        com.login.nativesso.f.a.a().a(new com.login.nativesso.h.h(1, null, iVar, iVar, hashMap));
                    }
                }
            }
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
        }
    }

    public static void a(p pVar) {
        b a = b.a();
        Context d = com.login.nativesso.d.c.a().d();
        String b = a.b(d);
        String a2 = a.a("channel", d);
        String a3 = a.a("TICKETID", d);
        if (a(a3) || a(b)) {
            pVar.a(a(413, "INVALID_REQUEST"));
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("channel", a2);
        hashMap.put("ticketId", a3);
        hashMap.put("ssec", b);
        com.login.nativesso.b.a.a("RenewTicketCallback", pVar);
        com.login.nativesso.c.j jVar = new com.login.nativesso.c.j();
        com.login.nativesso.f.a.a().a(new com.login.nativesso.h.h(1, null, jVar, jVar, hashMap));
    }

    public static void a(String str, String str2, t tVar) {
        if (a(str) && a(str2)) {
            tVar.a(a(413, "INVALID_REQUEST"));
            return;
        }
        com.login.nativesso.b.a.a("SetPasswordCb", tVar);
        com.login.nativesso.c.q qVar = new com.login.nativesso.c.q();
        com.login.nativesso.f.a.a().a(new com.login.nativesso.h.p(1, com.login.nativesso.h.p.a(str, str2), qVar, qVar, null));
    }

    public static void a(String str, String str2, String str3, com.login.nativesso.a.b bVar) {
        if (a(str) && a(str2) && a(str3)) {
            bVar.a(a(413, "INVALID_REQUEST"));
            return;
        }
        com.login.nativesso.b.a.a("ChangePasswordCb", bVar);
        com.login.nativesso.c.c cVar = new com.login.nativesso.c.c();
        com.login.nativesso.f.a.a().a(new com.login.nativesso.h.c(1, com.login.nativesso.h.c.a(str, str2, str3), cVar, cVar, null));
    }

    public static void a(String str, String str2, com.login.nativesso.a.i iVar, String str3) {
        if (("email".equals(str3) && a(str)) || ("mobile".equals(str3) && a(str2))) {
            iVar.a(a(413, "INVALID_REQUEST"));
            return;
        }
        com.login.nativesso.b.a.a("GetForgotPassOtpCb", iVar);
        com.login.nativesso.c.f fVar = new com.login.nativesso.c.f();
        com.login.nativesso.f.a.a().a(new e(1, e.a(str, str2), fVar, fVar, null));
    }

    public static void a(String str, String str2, String str3, String str4, String str5, ag agVar) {
        if (a(str) && a(str2)) {
            agVar.a(a(413, "INVALID_REQUEST"));
        } else if (a(str3)) {
            agVar.a(a(413, "INVALID_REQUEST"));
        } else {
            com.login.nativesso.b.a.a("VerifyForgotPassOtpCb", agVar);
            ac acVar = new ac();
            com.login.nativesso.f.a.a().a(new y(1, y.a(str, str2, str3, str4, str5), acVar, acVar, null));
        }
    }

    public static void a(com.login.nativesso.e.g gVar) {
        com.login.nativesso.b.a.a("SignUpCb", gVar.g());
        s sVar = new s();
        com.login.nativesso.f.a.a().a(new com.login.nativesso.h.r(1, com.login.nativesso.h.r.a(gVar), sVar, sVar, null));
    }

    public static void a(String str, String str2, String str3, ah ahVar) {
        com.login.nativesso.b.a.a("VerifySignUpOtpCb", ahVar);
        ad adVar = new ad();
        com.login.nativesso.f.a.a().a(new z(1, z.a(str, str2, str3, b.a().a("ssoid", com.login.nativesso.d.c.a().d())), adVar, adVar));
    }

    public static void a(String str, String str2, com.login.nativesso.a.r rVar) {
        com.login.nativesso.b.a.a("ResendSignUpOtpCb", rVar);
        com.login.nativesso.c.p pVar = new com.login.nativesso.c.p();
        com.login.nativesso.f.a.a().a(new com.login.nativesso.h.o(1, com.login.nativesso.h.o.a(str, str2, b.a().a("ssoid", com.login.nativesso.d.c.a().d())), pVar, pVar));
    }

    /* JADX WARNING: Missing block: B:14:0x003b, code skipped:
            r0 = null;
     */
    public static void a(android.content.Context r10, boolean r11, com.login.nativesso.a.j r12) {
        /*
        r0 = com.login.nativesso.i.a.a(r10);	 Catch:{ ServerException -> 0x0029, SecurityException -> 0x0017, Exception -> 0x0005 }
        goto L_0x003c;
    L_0x0005:
        r0 = move-exception;
        if (r12 == 0) goto L_0x003b;
    L_0x0008:
        com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r0);
        r10 = 4002; // 0xfa2 float:5.608E-42 double:1.9773E-320;
        r11 = "REQUEST_FAILED";
        r10 = a(r10, r11);
        r12.a(r10);
        return;
    L_0x0017:
        r0 = move-exception;
        if (r12 == 0) goto L_0x003b;
    L_0x001a:
        com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r0);
        r10 = 4008; // 0xfa8 float:5.616E-42 double:1.98E-320;
        r11 = "SECURITY_ISSUE";
        r10 = a(r10, r11);
        r12.a(r10);
        return;
    L_0x0029:
        r0 = move-exception;
        if (r12 == 0) goto L_0x003b;
    L_0x002c:
        com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r0);
        r10 = 4007; // 0xfa7 float:5.615E-42 double:1.9797E-320;
        r11 = "SERVER_ERROR";
        r10 = a(r10, r11);
        r12.a(r10);
        return;
    L_0x003b:
        r0 = 0;
    L_0x003c:
        r1 = 4004; // 0xfa4 float:5.611E-42 double:1.978E-320;
        if (r0 != 0) goto L_0x004c;
    L_0x0040:
        if (r12 == 0) goto L_0x004b;
    L_0x0042:
        r10 = "GLOBAL_SESSION_NOT_EXIST";
        r10 = a(r1, r10);
        r12.a(r10);
    L_0x004b:
        return;
    L_0x004c:
        r2 = com.login.nativesso.g.b.a();
        r3 = "channel";
        r10 = r2.a(r3, r10);
        r2 = "SSECID";
        r2 = r0.getString(r2);	 Catch:{ JSONException -> 0x00b2 }
        r3 = "TICKETID";
        r0 = r0.getString(r3);	 Catch:{ JSONException -> 0x00b2 }
        r3 = a(r2);	 Catch:{ JSONException -> 0x00b2 }
        if (r3 != 0) goto L_0x00a6;
    L_0x0068:
        r3 = a(r0);	 Catch:{ JSONException -> 0x00b2 }
        if (r3 != 0) goto L_0x00a6;
    L_0x006e:
        r3 = "GetGlobalSessionCb";
        com.login.nativesso.b.a.a(r3, r12);	 Catch:{ JSONException -> 0x00b2 }
        r9 = new java.util.HashMap;	 Catch:{ JSONException -> 0x00b2 }
        r9.<init>();	 Catch:{ JSONException -> 0x00b2 }
        r3 = "channel";
        r9.put(r3, r10);	 Catch:{ JSONException -> 0x00b2 }
        r10 = "ticketId";
        r9.put(r10, r0);	 Catch:{ JSONException -> 0x00b2 }
        r10 = "ssec";
        r9.put(r10, r2);	 Catch:{ JSONException -> 0x00b2 }
        if (r11 == 0) goto L_0x0090;
    L_0x0089:
        r10 = "getData";
        r11 = "true";
        r9.put(r10, r11);	 Catch:{ JSONException -> 0x00b2 }
    L_0x0090:
        r8 = new com.login.nativesso.c.i;	 Catch:{ JSONException -> 0x00b2 }
        r8.<init>();	 Catch:{ JSONException -> 0x00b2 }
        r10 = new com.login.nativesso.h.h;	 Catch:{ JSONException -> 0x00b2 }
        r5 = 1;
        r6 = 0;
        r4 = r10;
        r7 = r8;
        r4.<init>(r5, r6, r7, r8, r9);	 Catch:{ JSONException -> 0x00b2 }
        r11 = com.login.nativesso.f.a.a();	 Catch:{ JSONException -> 0x00b2 }
        r11.a(r10);	 Catch:{ JSONException -> 0x00b2 }
        goto L_0x00c8;
    L_0x00a6:
        if (r12 == 0) goto L_0x00c8;
    L_0x00a8:
        r10 = "GLOBAL_SESSION_NOT_EXIST";
        r10 = a(r1, r10);	 Catch:{ JSONException -> 0x00b2 }
        r12.a(r10);	 Catch:{ JSONException -> 0x00b2 }
        goto L_0x00c8;
    L_0x00b2:
        r10 = move-exception;
        com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r10);
        if (r12 == 0) goto L_0x00c1;
    L_0x00b8:
        r10 = "GLOBAL_SESSION_NOT_EXIST";
        r10 = a(r1, r10);
        r12.a(r10);
    L_0x00c1:
        r10 = "NATIVESSO";
        r11 = "Error while parsing Json in getGlobalSession";
        android.util.Log.e(r10, r11);
    L_0x00c8:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.login.nativesso.i.c.a(android.content.Context, boolean, com.login.nativesso.a.j):void");
    }

    public static void a(Context context, com.login.nativesso.a.h hVar) {
        com.login.nativesso.e.a aVar = new com.login.nativesso.e.a();
        b a = b.a();
        aVar.b(a.a("SSECID", context));
        aVar.a(a.a("TICKETID", context));
        aVar.c(a.a("TGID", context));
        aVar.d(a.a("LAST_SESSION_SRC", context));
        aVar.e(a.a("LAST_SESSION_IDENTIFIER", context));
        if (hVar != null) {
            hVar.onSuccess(aVar);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0028 A:{Catch:{ ServerException -> 0x00c6, SecurityException -> 0x00b4, Exception -> 0x009b }} */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0085 A:{Catch:{ ServerException -> 0x00c6, SecurityException -> 0x00b4, Exception -> 0x009b }} */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0028 A:{Catch:{ ServerException -> 0x00c6, SecurityException -> 0x00b4, Exception -> 0x009b }} */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0085 A:{Catch:{ ServerException -> 0x00c6, SecurityException -> 0x00b4, Exception -> 0x009b }} */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00c6 A:{ExcHandler: ServerException (r6_12 'e' com.login.nativesso.exception.ServerException), Splitter:B:0:0x0000} */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00b4 A:{ExcHandler: SecurityException (r6_9 'e' com.login.nativesso.exception.SecurityException), Splitter:B:0:0x0000} */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00c6 A:{ExcHandler: ServerException (r6_12 'e' com.login.nativesso.exception.ServerException), Splitter:B:0:0x0000} */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00b4 A:{ExcHandler: SecurityException (r6_9 'e' com.login.nativesso.exception.SecurityException), Splitter:B:0:0x0000} */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing block: B:10:0x001b, code skipped:
            r5 = e;
     */
    /* JADX WARNING: Missing block: B:11:0x001d, code skipped:
            r5 = e;
     */
    /* JADX WARNING: Missing block: B:12:0x001e, code skipped:
            r4 = null;
     */
    /* JADX WARNING: Missing block: B:14:?, code skipped:
            com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r5);
     */
    /* JADX WARNING: Missing block: B:34:0x00b4, code skipped:
            r6 = move-exception;
     */
    /* JADX WARNING: Missing block: B:35:0x00b5, code skipped:
            if (r7 != null) goto L_0x00b7;
     */
    /* JADX WARNING: Missing block: B:36:0x00b7, code skipped:
            com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r6);
            r7.a(a(4008, "SECURITY_ISSUE"));
     */
    /* JADX WARNING: Missing block: B:37:0x00c5, code skipped:
            return;
     */
    /* JADX WARNING: Missing block: B:38:0x00c6, code skipped:
            r6 = move-exception;
     */
    /* JADX WARNING: Missing block: B:39:0x00c7, code skipped:
            if (r7 != null) goto L_0x00c9;
     */
    /* JADX WARNING: Missing block: B:40:0x00c9, code skipped:
            com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r6);
            r7.a(a((int) com.gaana.login.sso.SsoErrorCodes.SERVER_ERROR, "SERVER_ERROR"));
     */
    /* JADX WARNING: Missing block: B:41:0x00d7, code skipped:
            return;
     */
    public static void a(android.content.Context r6, com.login.nativesso.a.e r7) {
        /*
        r0 = com.login.nativesso.i.a.a(r6);	 Catch:{ ServerException -> 0x00c6, SecurityException -> 0x00b4, Exception -> 0x009b }
        r1 = com.login.nativesso.g.b.a();	 Catch:{ ServerException -> 0x00c6, SecurityException -> 0x00b4, Exception -> 0x009b }
        r2 = 4004; // 0xfa4 float:5.611E-42 double:1.978E-320;
        if (r0 == 0) goto L_0x008f;
    L_0x000c:
        r3 = 0;
        r4 = "SSECID";
        r4 = r0.getString(r4);	 Catch:{ Exception -> 0x001d, ServerException -> 0x00c6, SecurityException -> 0x00b4 }
        r5 = "TICKETID";
        r5 = r0.getString(r5);	 Catch:{ Exception -> 0x001b, ServerException -> 0x00c6, SecurityException -> 0x00b4 }
        r3 = r5;
        goto L_0x0022;
    L_0x001b:
        r5 = move-exception;
        goto L_0x001f;
    L_0x001d:
        r5 = move-exception;
        r4 = r3;
    L_0x001f:
        com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r5);	 Catch:{ ServerException -> 0x00c6, SecurityException -> 0x00b4, Exception -> 0x009b }
    L_0x0022:
        r5 = a(r3);	 Catch:{ ServerException -> 0x00c6, SecurityException -> 0x00b4, Exception -> 0x009b }
        if (r5 != 0) goto L_0x0083;
    L_0x0028:
        r5 = a(r4);	 Catch:{ ServerException -> 0x00c6, SecurityException -> 0x00b4, Exception -> 0x009b }
        if (r5 == 0) goto L_0x002f;
    L_0x002e:
        goto L_0x0083;
    L_0x002f:
        r2 = "TGID";
        r2 = r0.getString(r2);	 Catch:{ ServerException -> 0x00c6, SecurityException -> 0x00b4, Exception -> 0x009b }
        r5 = "SOCIALTYPE";
        r0 = r0.getString(r5);	 Catch:{ ServerException -> 0x00c6, SecurityException -> 0x00b4, Exception -> 0x009b }
        r5 = "TGID";
        r1.a(r6, r5, r2);	 Catch:{ ServerException -> 0x00c6, SecurityException -> 0x00b4, Exception -> 0x009b }
        r2 = "TICKETID";
        r1.a(r6, r2, r3);	 Catch:{ ServerException -> 0x00c6, SecurityException -> 0x00b4, Exception -> 0x009b }
        r2 = "SSECID";
        r1.a(r6, r2, r4);	 Catch:{ ServerException -> 0x00c6, SecurityException -> 0x00b4, Exception -> 0x009b }
        r2 = "SOCIALTYPE";
        r1.a(r6, r2, r0);	 Catch:{ ServerException -> 0x00c6, SecurityException -> 0x00b4, Exception -> 0x009b }
        r2 = "CopyGlobalSession";
        com.login.nativesso.b.a.a(r2, r7);	 Catch:{ ServerException -> 0x00c6, SecurityException -> 0x00b4, Exception -> 0x009b }
        b();	 Catch:{ ServerException -> 0x00c6, SecurityException -> 0x00b4, Exception -> 0x009b }
        r2 = "sso";
        r2 = r0.contains(r2);	 Catch:{ ServerException -> 0x00c6, SecurityException -> 0x00b4, Exception -> 0x009b }
        if (r2 == 0) goto L_0x0076;
    L_0x005f:
        r2 = "&";
        r0 = r0.split(r2);	 Catch:{ ServerException -> 0x00c6, SecurityException -> 0x00b4, Exception -> 0x009b }
        r2 = "LAST_SESSION_SRC";
        r3 = 0;
        r3 = r0[r3];	 Catch:{ ServerException -> 0x00c6, SecurityException -> 0x00b4, Exception -> 0x009b }
        r1.a(r6, r2, r3);	 Catch:{ ServerException -> 0x00c6, SecurityException -> 0x00b4, Exception -> 0x009b }
        r2 = "LAST_SESSION_IDENTIFIER";
        r3 = 1;
        r0 = r0[r3];	 Catch:{ ServerException -> 0x00c6, SecurityException -> 0x00b4, Exception -> 0x009b }
        r1.a(r6, r2, r0);	 Catch:{ ServerException -> 0x00c6, SecurityException -> 0x00b4, Exception -> 0x009b }
        goto L_0x00d8;
    L_0x0076:
        r2 = "LAST_SESSION_SRC";
        r1.a(r6, r2, r0);	 Catch:{ ServerException -> 0x00c6, SecurityException -> 0x00b4, Exception -> 0x009b }
        r0 = "LAST_SESSION_IDENTIFIER";
        r2 = "";
        r1.a(r6, r0, r2);	 Catch:{ ServerException -> 0x00c6, SecurityException -> 0x00b4, Exception -> 0x009b }
        goto L_0x00d8;
    L_0x0083:
        if (r7 == 0) goto L_0x008e;
    L_0x0085:
        r6 = "GLOBAL_SESSION_NOT_EXIST";
        r6 = a(r2, r6);	 Catch:{ ServerException -> 0x00c6, SecurityException -> 0x00b4, Exception -> 0x009b }
        r7.a(r6);	 Catch:{ ServerException -> 0x00c6, SecurityException -> 0x00b4, Exception -> 0x009b }
    L_0x008e:
        return;
    L_0x008f:
        if (r7 == 0) goto L_0x00d8;
    L_0x0091:
        r6 = "GLOBAL_SESSION_NOT_EXIST";
        r6 = a(r2, r6);	 Catch:{ ServerException -> 0x00c6, SecurityException -> 0x00b4, Exception -> 0x009b }
        r7.a(r6);	 Catch:{ ServerException -> 0x00c6, SecurityException -> 0x00b4, Exception -> 0x009b }
        goto L_0x00d8;
    L_0x009b:
        r6 = move-exception;
        com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r6);
        if (r7 == 0) goto L_0x00ac;
    L_0x00a1:
        r6 = 4002; // 0xfa2 float:5.608E-42 double:1.9773E-320;
        r0 = "REQUEST_FAILED";
        r6 = a(r6, r0);
        r7.a(r6);
    L_0x00ac:
        r6 = "NATIVESSO";
        r7 = "Json Error in copyGlobalSessionToApp";
        android.util.Log.e(r6, r7);
        goto L_0x00d8;
    L_0x00b4:
        r6 = move-exception;
        if (r7 == 0) goto L_0x00d8;
    L_0x00b7:
        com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r6);
        r6 = 4008; // 0xfa8 float:5.616E-42 double:1.98E-320;
        r0 = "SECURITY_ISSUE";
        r6 = a(r6, r0);
        r7.a(r6);
        return;
    L_0x00c6:
        r6 = move-exception;
        if (r7 == 0) goto L_0x00d8;
    L_0x00c9:
        com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r6);
        r6 = 4007; // 0xfa7 float:5.615E-42 double:1.9797E-320;
        r0 = "SERVER_ERROR";
        r6 = a(r6, r0);
        r7.a(r6);
        return;
    L_0x00d8:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.login.nativesso.i.c.a(android.content.Context, com.login.nativesso.a.e):void");
    }

    public static void a(String str, Context context, com.login.nativesso.a.n nVar) {
        String a = b.a().a("channel", context);
        com.login.nativesso.b.a.a("MigrateSessionCb", nVar);
        JSONObject jSONObject = new JSONObject();
        HashMap hashMap = new HashMap();
        hashMap.put("channel", a);
        hashMap.put("ticketId", str);
        com.login.nativesso.c.m mVar = new com.login.nativesso.c.m();
        com.login.nativesso.f.a.a().a(new com.login.nativesso.h.l(1, jSONObject, mVar, mVar, hashMap, b.w));
    }

    public static void a(String str, Context context, com.login.nativesso.a.f fVar) {
        String a = b.a().a("channel", context);
        com.login.nativesso.b.a.a("CreateUnverfiedSessCb", fVar);
        JSONObject jSONObject = new JSONObject();
        HashMap hashMap = new HashMap();
        hashMap.put("channel", a);
        hashMap.put("ticketId", str);
        com.login.nativesso.c.e eVar = new com.login.nativesso.c.e();
        com.login.nativesso.f.a.a().a(new com.login.nativesso.h.l(1, jSONObject, eVar, eVar, hashMap, b.w));
    }

    public static void a(String str, aa aaVar) {
        com.login.nativesso.b.a.a("UpdateEmailAndMobileCb", aaVar);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("mobile", str);
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
        }
        com.login.nativesso.c.x xVar = new com.login.nativesso.c.x();
        com.login.nativesso.f.a.a().a(new com.login.nativesso.h.u(1, jSONObject, xVar, xVar, null, b.x));
    }

    public static void a(String str, String str2, af afVar) {
        com.login.nativesso.b.a.a("VerifyEmailAndMobileCb", afVar);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("mobile", str);
            jSONObject.put("otp", str2);
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
        }
        ab abVar = new ab(str);
        com.login.nativesso.f.a.a().a(new com.login.nativesso.h.u(1, jSONObject, abVar, abVar, null, b.z));
    }

    public static void b(String str, aa aaVar) {
        com.login.nativesso.b.a.a("UpdateEmailAndMobileCb", aaVar);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("email", str);
        } catch (Exception unused) {
        }
        com.login.nativesso.c.x xVar = new com.login.nativesso.c.x();
        com.login.nativesso.f.a.a().a(new com.login.nativesso.h.u(1, jSONObject, xVar, xVar, null, b.y));
    }

    public static void b(String str, String str2, af afVar) {
        com.login.nativesso.b.a.a("VerifyEmailAndMobileCb", afVar);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("email", str);
            jSONObject.put("otp", str2);
        } catch (Exception unused) {
        }
        ab abVar = new ab(str);
        com.login.nativesso.f.a.a().a(new com.login.nativesso.h.u(1, jSONObject, abVar, abVar, null, b.A));
    }

    public static void a(String str, String str2, String str3, String str4, String str5, com.login.nativesso.a.ab abVar) {
        JSONObject jSONObject = new JSONObject();
        try {
            if (!a(str)) {
                jSONObject.put("firstName", str);
            }
            if (!a(str2)) {
                jSONObject.put("lastName", str2);
            }
            if (!a(str3)) {
                jSONObject.put(LoginManager.TAG_DOB, str3);
            }
            if (!a(str4)) {
                jSONObject.put("gender", str4);
            }
            if (!a(str5)) {
                jSONObject.put("city", str5);
            }
            com.login.nativesso.b.a.a("UpdateUserCb", abVar);
            com.login.nativesso.c.y yVar = new com.login.nativesso.c.y();
            com.login.nativesso.f.a.a().a(new com.login.nativesso.h.v(1, jSONObject, yVar, yVar, null, b.B));
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            if (abVar != null) {
                abVar.a(a((int) SsoErrorCodes.REQUEST_FAILED, "REQUEST_FAILED"));
            }
        }
    }

    public static void a(String str, String str2, String str3, com.login.nativesso.a.ac acVar) {
        JSONObject jSONObject = new JSONObject();
        try {
            if (!a(str)) {
                jSONObject.put("termsAccepted", str);
            }
            if (!a(str2)) {
                jSONObject.put("shareDataAllowed", str2);
            }
            if (!a(str3)) {
                jSONObject.put("timespointsPolicy", str3);
            }
            com.login.nativesso.b.a.a("UpdateUserPermissionsCb", acVar);
            com.login.nativesso.c.z zVar = new com.login.nativesso.c.z();
            com.login.nativesso.f.a.a().a(new w(1, jSONObject, zVar, zVar, null, b.C));
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            if (acVar != null) {
                acVar.a(a((int) SsoErrorCodes.REQUEST_FAILED, "REQUEST_FAILED"));
            }
        }
    }

    public static void a(String str, Context context, com.login.nativesso.a.ad adVar) {
        com.login.nativesso.b.a.a("UpdateUserProfilePicCb", adVar);
        Intent intent = new Intent();
        if (a(str)) {
            intent.putExtra("BY_CUSTOM_DIALOG", false);
        } else {
            intent.putExtra("GALLERY_CAMERA", str.toLowerCase());
            intent.putExtra("BY_CUSTOM_DIALOG", true);
        }
        intent.setClass(context, UploadProfilePicActivity.class);
        intent.setFlags(C.ENCODING_PCM_MU_LAW);
        context.startActivity(intent);
    }

    public static boolean a(Context context, String str) {
        Set stringSet = b.a().d(context).getStringSet("PACKAGE_LIST", null);
        return (stringSet == null || stringSet.isEmpty()) ? false : stringSet.contains(str);
    }

    public static void a(String str, SharedPreferences sharedPreferences) {
        Editor edit = sharedPreferences.edit();
        Set stringSet = sharedPreferences.getStringSet("PACKAGE_LIST", null);
        if (stringSet == null) {
            stringSet = new HashSet();
        }
        stringSet.add(str);
        edit.putStringSet("PACKAGE_LIST", stringSet);
        edit.apply();
    }

    public static String b(String str) {
        try {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(b.h);
            stringBuilder.append("?package=");
            stringBuilder.append(str);
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(stringBuilder.toString()).openConnection();
            httpURLConnection.setRequestMethod(HttpMethods.GET);
            httpURLConnection.setConnectTimeout(SsoErrorCodes.SDK_NOT_INITIALIZED);
            httpURLConnection.setReadTimeout(SsoErrorCodes.SDK_NOT_INITIALIZED);
            if (httpURLConnection.getResponseCode() == 200) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                StringBuffer stringBuffer = new StringBuffer();
                while (true) {
                    String readLine = bufferedReader.readLine();
                    if (readLine != null) {
                        stringBuffer.append(readLine);
                    } else {
                        bufferedReader.close();
                        return stringBuffer.toString();
                    }
                }
            }
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
        }
        return null;
    }

    public static boolean b(Context context, final String str) {
        if (a(context, str)) {
            return true;
        }
        SharedPreferences d = b.a().d(context);
        final String[] strArr = new String[]{null};
        try {
            AnonymousClass4 anonymousClass4 = new UncaughtExceptionHandler() {
                public void uncaughtException(Thread thread, Throwable th) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("Uncaught exception: ");
                    stringBuilder.append(th);
                    Log.e("NATIVESSO", stringBuilder.toString());
                }
            };
            Thread thread = new Thread(new Runnable() {
                public void run() {
                    strArr[0] = c.b(str);
                }
            });
            thread.setUncaughtExceptionHandler(anonymousClass4);
            thread.start();
            thread.join();
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
        }
        if (strArr[0] != null) {
            String string;
            try {
                string = new JSONObject(strArr[0]).getString("isValid");
            } catch (JSONException e2) {
                ThrowableExtension.printStackTrace(e2);
                string = null;
            }
            if (!Boolean.TRUE.toString().equalsIgnoreCase(string)) {
                return false;
            }
            a(str, d);
            return true;
        }
        throw new ServerException("SERVER_ERROR");
    }

    public static boolean c(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null ? activeNetworkInfo.isConnected() : false;
    }

    public static void a(String str, com.login.nativesso.a.g gVar) {
        if (LoginManager.SSO_SOCIAL_LOGIN_TYPE_FACEBOOK.equalsIgnoreCase(str) || LoginManager.SSO_SOCIAL_LOGIN_TYPE_GOOGLE.equalsIgnoreCase(str)) {
            com.login.nativesso.b.a.a("DelinkCb", gVar);
            com.login.nativesso.c.t tVar = new com.login.nativesso.c.t();
            com.login.nativesso.c.t.a(str);
            com.login.nativesso.f.a.a().a(new com.login.nativesso.h.s(1, com.login.nativesso.h.s.a(str), tVar, tVar));
            return;
        }
        gVar.a(a(413, "INVALID_REQUEST"));
    }

    public static void a(String str, String str2, String str3) {
        com.login.nativesso.c.u uVar = new com.login.nativesso.c.u();
        com.login.nativesso.c.u.a(str3);
        com.login.nativesso.f.a.a().a(new com.login.nativesso.h.t(1, com.login.nativesso.h.t.a(str, str2, str3), uVar, uVar));
    }

    public static void b(String str, String str2, String str3) {
        com.login.nativesso.c.w wVar = new com.login.nativesso.c.w();
        com.login.nativesso.f.a.a().a(new com.login.nativesso.h.i(1, com.login.nativesso.h.t.a(str, str2, str3), wVar, wVar));
    }

    public static void a(Context context, String str, String str2, String str3, String str4, boolean z, x xVar) {
        Context context2 = context;
        String str5 = str3;
        x xVar2 = xVar;
        if (a(str5)) {
            xVar2.onLoginFailure(a(4005, "SOCIAL_TYPE_MISSING"));
            return;
        }
        b a = b.a();
        String a2 = a.a("channel", context2);
        String a3 = a.a("siteId", context2);
        String a4 = a.a("channel", context2);
        String a5 = a.a("TGID", context2);
        if (str5.equalsIgnoreCase(LoginManager.SSO_SOCIAL_LOGIN_TYPE_GOOGLE)) {
            a(a2, a3, str, str2, true, a5, a4, str4, xVar2);
        } else if (str5.equalsIgnoreCase(LoginManager.SSO_SOCIAL_LOGIN_TYPE_FACEBOOK)) {
            String str6 = InternalLogger.EVENT_PARAM_EXTRAS_FALSE;
            if (z) {
                str6 = "true";
            }
            a(a2, a3, str, str2, true, a5, a4, str6, str4, xVar2);
        } else if (str5.equalsIgnoreCase("linkedin")) {
            b(a2, a3, str, str2, true, a5, a4, str4, xVar2);
        } else {
            xVar2.onLoginFailure(a(4006, "SOCIAL_TYPE_INVALID"));
        }
    }

    public static com.login.nativesso.e.c a(int i, String str) {
        return new com.login.nativesso.e.c(i, str);
    }

    /* JADX WARNING: Removed duplicated region for block: B:33:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x006b  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0052  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0039  */
    /* JADX WARNING: Removed duplicated region for block: B:33:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x006b  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0052  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0039  */
    /* JADX WARNING: Removed duplicated region for block: B:33:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x006b  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0052  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0039  */
    public static void c(java.lang.String r2) {
        /*
        r0 = r2.hashCode();
        r1 = 110986; // 0x1b18a float:1.55525E-40 double:5.48344E-319;
        if (r0 == r1) goto L_0x0028;
    L_0x0009:
        r1 = 3321850; // 0x32affa float:4.654903E-39 double:1.641212E-317;
        if (r0 == r1) goto L_0x001e;
    L_0x000e:
        r1 = 103149417; // 0x625ef69 float:3.1208942E-35 double:5.09625833E-316;
        if (r0 == r1) goto L_0x0014;
    L_0x0013:
        goto L_0x0032;
    L_0x0014:
        r0 = "login";
        r2 = r2.equals(r0);
        if (r2 == 0) goto L_0x0032;
    L_0x001c:
        r2 = 0;
        goto L_0x0033;
    L_0x001e:
        r0 = "link";
        r2 = r2.equals(r0);
        if (r2 == 0) goto L_0x0032;
    L_0x0026:
        r2 = 1;
        goto L_0x0033;
    L_0x0028:
        r0 = "pic";
        r2 = r2.equals(r0);
        if (r2 == 0) goto L_0x0032;
    L_0x0030:
        r2 = 2;
        goto L_0x0033;
    L_0x0032:
        r2 = -1;
    L_0x0033:
        r0 = 4002; // 0xfa2 float:5.608E-42 double:1.9773E-320;
        switch(r2) {
            case 0: goto L_0x006b;
            case 1: goto L_0x0052;
            case 2: goto L_0x0039;
            default: goto L_0x0038;
        };
    L_0x0038:
        goto L_0x0083;
    L_0x0039:
        r2 = "SocialPicUploadCb";
        r2 = com.login.nativesso.b.a.a(r2);
        r2 = (com.login.nativesso.a.y) r2;
        if (r2 == 0) goto L_0x0083;
    L_0x0043:
        r1 = "REQUEST_FAILED";
        r0 = a(r0, r1);
        r2.a(r0);
        r2 = "SocialPicUploadCb";
        com.login.nativesso.b.a.b(r2);
        goto L_0x0083;
    L_0x0052:
        r2 = "SocialLinkCb";
        r2 = com.login.nativesso.b.a.a(r2);
        r2 = (com.login.nativesso.a.w) r2;
        if (r2 == 0) goto L_0x0083;
    L_0x005c:
        r1 = "REQUEST_FAILED";
        r0 = a(r0, r1);
        r2.a(r0);
        r2 = "SocialLinkCb";
        com.login.nativesso.b.a.b(r2);
        goto L_0x0083;
    L_0x006b:
        r2 = "SocialLoginCb";
        r2 = com.login.nativesso.b.a.a(r2);
        r2 = (com.login.nativesso.a.x) r2;
        if (r2 == 0) goto L_0x0083;
    L_0x0075:
        r1 = "REQUEST_FAILED";
        r0 = a(r0, r1);
        r2.onLoginFailure(r0);
        r2 = "SocialLoginCb";
        com.login.nativesso.b.a.b(r2);
    L_0x0083:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.login.nativesso.i.c.c(java.lang.String):void");
    }

    public static boolean d(Context context) {
        b a = b.a();
        return (a(a.a("TICKETID", context)) || a(a.b(context))) ? false : true;
    }

    public static void a(String str, d dVar) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("identifier", str);
            com.login.nativesso.b.a.a("CheckUserExistCb", dVar);
            com.login.nativesso.c.d dVar2 = new com.login.nativesso.c.d();
            com.login.nativesso.f.a.a().a(new com.login.nativesso.h.d(1, jSONObject, dVar2, dVar2, null, b.E));
        } catch (JSONException e) {
            ThrowableExtension.printStackTrace(e);
            if (dVar != null) {
                dVar.a(a((int) SsoErrorCodes.REQUEST_FAILED, "REQUEST_FAILED"));
            }
        }
    }

    public static void a(String str, String str2, String str3, ae aeVar) {
        JSONObject jSONObject = new JSONObject();
        try {
            if (!a(str)) {
                jSONObject.put("email", str);
            }
            if (!a(str2)) {
                jSONObject.put(LoginManager.TAG_PASSWORD, str2);
            }
            if (!a(str3)) {
                jSONObject.put("confirmPassword", str3);
            }
            com.login.nativesso.b.a.a("ValidatePasswordCb", aeVar);
            com.login.nativesso.c.aa aaVar = new com.login.nativesso.c.aa();
            com.login.nativesso.f.a.a().a(new com.login.nativesso.h.x(1, jSONObject, aaVar, aaVar, null, b.F));
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            if (aeVar != null) {
                aeVar.a(a((int) SsoErrorCodes.REQUEST_FAILED, "REQUEST_FAILED"));
            }
        }
    }

    public static void a(String str, String str2, String str3, String str4, String str5, String str6, com.login.nativesso.a.o oVar) {
        if (a(str)) {
            oVar.a(a(413, "INVALID_REQUEST"));
            return;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("mobile", str);
            if (!a(str2)) {
                jSONObject.put("name", str2);
            }
            if (!a(str3)) {
                jSONObject.put("gender", str3);
            }
            if (!a(str4)) {
                jSONObject.put("termsAccepted", str4);
            }
            if (!a(str5)) {
                jSONObject.put("shareDataAllowed", str5);
            }
            if (!a(str6)) {
                jSONObject.put("timespointsPolicy", str6);
            }
            com.login.nativesso.b.a.a("RegisterMobileCb", oVar);
            com.login.nativesso.c.n nVar = new com.login.nativesso.c.n();
            com.login.nativesso.f.a.a().a(new com.login.nativesso.h.m(1, jSONObject, nVar, nVar, null, b.D));
        } catch (JSONException e) {
            ThrowableExtension.printStackTrace(e);
            if (oVar != null) {
                oVar.a(a((int) SsoErrorCodes.REQUEST_FAILED, "REQUEST_FAILED"));
            }
        }
    }
}
