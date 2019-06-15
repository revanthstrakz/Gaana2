package io.branch.referral;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import io.branch.referral.Defines.Jsonkey;
import io.branch.referral.Defines.RequestPath;
import java.util.ConcurrentModificationException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class ServerRequest {
    protected String a;
    protected m b;
    long c = 0;
    final Set<PROCESS_WAIT_LOCK> d;
    public boolean e = false;
    boolean f = false;
    private JSONObject g;
    private ag h;
    private boolean i;
    private int j = 0;

    enum PROCESS_WAIT_LOCK {
        FB_APP_LINK_WAIT_LOCK,
        GAID_FETCH_WAIT_LOCK
    }

    public abstract void a(int i, String str);

    public abstract void a(af afVar, Branch branch);

    public abstract boolean a();

    public abstract boolean a(Context context);

    public abstract void b();

    public boolean c() {
        return false;
    }

    public boolean g() {
        return false;
    }

    public ServerRequest(Context context, String str) {
        this.a = str;
        this.b = m.a(context);
        this.h = new ag(context);
        this.g = new JSONObject();
        this.i = Branch.c();
        this.d = new HashSet();
    }

    protected ServerRequest(String str, JSONObject jSONObject, Context context) {
        this.a = str;
        this.g = jSONObject;
        this.b = m.a(context);
        this.h = new ag(context);
        this.i = Branch.c();
        this.d = new HashSet();
    }

    public final String d() {
        return this.a;
    }

    public String e() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.b.a());
        stringBuilder.append(this.a);
        return stringBuilder.toString();
    }

    /* Access modifiers changed, original: protected */
    public void a(JSONObject jSONObject) {
        try {
            String str;
            JSONObject jSONObject2 = new JSONObject();
            Iterator keys = this.b.D().keys();
            while (keys.hasNext()) {
                str = (String) keys.next();
                jSONObject2.put(str, this.b.D().get(str));
            }
            if (jSONObject.has(Jsonkey.Metadata.getKey())) {
                keys = jSONObject.getJSONObject(Jsonkey.Metadata.getKey()).keys();
                while (keys.hasNext()) {
                    str = (String) keys.next();
                    jSONObject2.put(str, jSONObject.getJSONObject(Jsonkey.Metadata.getKey()).get(str));
                }
            }
            jSONObject.put(Jsonkey.Metadata.getKey(), jSONObject2);
        } catch (JSONException unused) {
            Log.e("BranchSDK", "Could not merge metadatas, ignoring user metadata.");
        }
        this.g = jSONObject;
        k.a(this.b.B(), this.h, this.i).a(this.g);
    }

    public JSONObject f() {
        return this.g;
    }

    public JSONObject a(ConcurrentHashMap<String, String> concurrentHashMap) {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONObject jSONObject2;
            Iterator keys;
            String str;
            if (this.g != null) {
                jSONObject2 = new JSONObject(this.g.toString());
                keys = jSONObject2.keys();
                while (keys.hasNext()) {
                    str = (String) keys.next();
                    jSONObject.put(str, jSONObject2.get(str));
                }
            }
            if (concurrentHashMap.size() <= 0) {
                return jSONObject;
            }
            jSONObject2 = new JSONObject();
            for (String str2 : concurrentHashMap.keySet()) {
                jSONObject2.put(str2, concurrentHashMap.get(str2));
                concurrentHashMap.remove(str2);
            }
            jSONObject.put(Jsonkey.Branch_Instrumentation.getKey(), jSONObject2);
            return jSONObject;
        } catch (JSONException unused) {
            return jSONObject;
        } catch (ConcurrentModificationException unused2) {
            return this.g;
        }
    }

    public JSONObject h() {
        return this.g;
    }

    public JSONObject i() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("REQ_POST", this.g);
            jSONObject.put("REQ_POST_PATH", this.a);
            return jSONObject;
        } catch (JSONException unused) {
            return null;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x001b A:{Catch:{ JSONException -> 0x0022 }} */
    public static io.branch.referral.ServerRequest a(org.json.JSONObject r4, android.content.Context r5) {
        /*
        r0 = "";
        r1 = 0;
        r2 = "REQ_POST";
        r2 = r4.has(r2);	 Catch:{ JSONException -> 0x0012 }
        if (r2 == 0) goto L_0x0012;
    L_0x000b:
        r2 = "REQ_POST";
        r2 = r4.getJSONObject(r2);	 Catch:{ JSONException -> 0x0012 }
        goto L_0x0013;
    L_0x0012:
        r2 = r1;
    L_0x0013:
        r3 = "REQ_POST_PATH";
        r3 = r4.has(r3);	 Catch:{ JSONException -> 0x0022 }
        if (r3 == 0) goto L_0x0022;
    L_0x001b:
        r3 = "REQ_POST_PATH";
        r4 = r4.getString(r3);	 Catch:{ JSONException -> 0x0022 }
        r0 = r4;
    L_0x0022:
        if (r0 == 0) goto L_0x002f;
    L_0x0024:
        r4 = r0.length();
        if (r4 <= 0) goto L_0x002f;
    L_0x002a:
        r4 = a(r0, r2, r5);
        return r4;
    L_0x002f:
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: io.branch.referral.ServerRequest.a(org.json.JSONObject, android.content.Context):io.branch.referral.ServerRequest");
    }

    private static ServerRequest a(String str, JSONObject jSONObject, Context context) {
        if (str.equalsIgnoreCase(RequestPath.CompletedAction.getPath())) {
            return new o(str, jSONObject, context);
        }
        if (str.equalsIgnoreCase(RequestPath.ApplyReferralCode.getPath())) {
            return new p(str, jSONObject, context);
        }
        if (str.equalsIgnoreCase(RequestPath.GetURL.getPath())) {
            return new q(str, jSONObject, context);
        }
        if (str.equalsIgnoreCase(RequestPath.GetReferralCode.getPath())) {
            return new r(str, jSONObject, context);
        }
        if (str.equalsIgnoreCase(RequestPath.Referrals.getPath())) {
            return new s(str, jSONObject, context);
        }
        if (str.equalsIgnoreCase(RequestPath.GetCreditHistory.getPath())) {
            return new t(str, jSONObject, context);
        }
        if (str.equalsIgnoreCase(RequestPath.GetCredits.getPath())) {
            return new u(str, jSONObject, context);
        }
        if (str.equalsIgnoreCase(RequestPath.IdentifyUser.getPath())) {
            return new v(str, jSONObject, context);
        }
        if (str.equalsIgnoreCase(RequestPath.Logout.getPath())) {
            return new x(str, jSONObject, context);
        }
        if (str.equalsIgnoreCase(RequestPath.RedeemRewards.getPath())) {
            return new z(str, jSONObject, context);
        }
        if (str.equalsIgnoreCase(RequestPath.RegisterClose.getPath())) {
            return new aa(str, jSONObject, context);
        }
        if (str.equalsIgnoreCase(RequestPath.RegisterInstall.getPath())) {
            return new ab(str, jSONObject, context);
        }
        if (str.equalsIgnoreCase(RequestPath.RegisterOpen.getPath())) {
            return new ac(str, jSONObject, context);
        }
        if (str.equalsIgnoreCase(RequestPath.SendAPPList.getPath())) {
            return new ad(str, jSONObject, context);
        }
        return str.equalsIgnoreCase(RequestPath.ValidateReferralCode.getPath()) ? new ae(str, jSONObject, context) : null;
    }

    public void a(ag agVar) {
        if (!TextUtils.isEmpty(agVar.a)) {
            try {
                this.g.put(Jsonkey.GoogleAdvertisingID.getKey(), agVar.a);
                this.g.put(Jsonkey.LATVal.getKey(), agVar.b);
            } catch (JSONException e) {
                ThrowableExtension.printStackTrace(e);
            }
        }
    }

    /* Access modifiers changed, original: protected */
    public boolean b(Context context) {
        return context.checkCallingOrSelfPermission("android.permission.INTERNET") == 0;
    }

    public void j() {
        this.c = System.currentTimeMillis();
    }

    public long k() {
        if (this.c > 0) {
            return System.currentTimeMillis() - this.c;
        }
        return 0;
    }

    public void a(PROCESS_WAIT_LOCK process_wait_lock) {
        if (process_wait_lock != null) {
            this.d.add(process_wait_lock);
        }
    }

    public void b(PROCESS_WAIT_LOCK process_wait_lock) {
        this.d.remove(process_wait_lock);
    }

    public boolean l() {
        return this.d.size() > 0;
    }
}
