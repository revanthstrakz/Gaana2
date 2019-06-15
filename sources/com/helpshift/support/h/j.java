package com.helpshift.support.h;

import android.provider.Settings.Secure;
import com.gaana.login.LoginManager;
import com.helpshift.account.dao.ProfileDTO;
import com.helpshift.account.dao.c;
import com.helpshift.common.platform.network.d;
import com.helpshift.common.platform.p;
import com.helpshift.configuration.a.a;
import com.helpshift.support.g;
import com.helpshift.support.util.b;
import com.helpshift.util.l;
import com.helpshift.util.o;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONObject;

public class j {
    private g a;
    private a b = o.d().m();
    private d c;
    private com.helpshift.account.dao.a d;
    private c e;
    private com.helpshift.meta.a.a f;
    private String g;
    private String h;
    private ProfileDTO i;
    private Boolean j;
    private Boolean k;
    private Boolean l;
    private Boolean m;
    private Boolean n;
    private Boolean o;
    private Boolean p;
    private Boolean q;
    private float r;
    private HashMap<String, Serializable> s;

    public j(g gVar) {
        this.a = gVar;
        p c = o.c();
        this.c = c.q();
        this.d = c.o();
        this.e = c.p();
        this.f = c.g();
    }

    public void a() {
        if (this.a.b("requireEmail")) {
            this.j = this.a.c("requireEmail");
        } else {
            this.j = Boolean.valueOf(this.b.a("requireEmail"));
        }
        if (this.a.b("fullPrivacy")) {
            this.k = this.a.c("fullPrivacy");
        } else {
            this.k = Boolean.valueOf(this.b.a("fullPrivacy"));
        }
        if (this.a.b("hideNameAndEmail")) {
            this.l = this.a.c("hideNameAndEmail");
        } else {
            this.l = Boolean.valueOf(this.b.a("hideNameAndEmail"));
        }
        if (this.a.b("showSearchOnNewConversation")) {
            this.m = this.a.c("showSearchOnNewConversation");
        } else {
            this.m = Boolean.valueOf(this.b.a("showSearchOnNewConversation"));
        }
        if (this.a.b("gotoConversationAfterContactUs")) {
            this.n = this.a.c("gotoConversationAfterContactUs");
        } else {
            this.n = Boolean.valueOf(this.b.a("gotoConversationAfterContactUs"));
        }
        if (this.a.b("showConversationResolutionQuestion")) {
            this.o = this.a.c("showConversationResolutionQuestion");
        } else {
            this.o = Boolean.valueOf(this.b.a("showConversationResolutionQuestion"));
        }
        if (this.a.b("showConversationInfoScreen")) {
            this.p = this.a.c("showConversationInfoScreen");
        } else {
            this.p = Boolean.valueOf(this.b.a("showConversationInfoScreen"));
        }
        if (this.a.b("enableTypingIndicator")) {
            this.q = this.a.c("enableTypingIndicator");
        } else {
            this.q = Boolean.valueOf(this.b.a("enableTypingIndicator"));
        }
        if (this.a.b("serverTimeDelta")) {
            this.r = this.a.a("serverTimeDelta").floatValue();
        } else {
            this.r = this.c.a();
        }
        if (this.a.b("loginIdentifier")) {
            this.g = this.a.i("loginIdentifier");
        } else {
            this.g = this.d.a();
        }
        String str = null;
        if (this.a.b("identity")) {
            str = this.a.i("identity");
        }
        String str2 = str;
        if (com.helpshift.common.c.a(str2)) {
            this.h = this.d.d();
            if (!com.helpshift.common.c.a(this.h)) {
                this.i = this.d.d(this.h);
            }
        } else {
            this.h = this.a.i("uuid");
            if (com.helpshift.common.c.a(this.h)) {
                this.h = Secure.getString(o.b().getContentResolver(), "android_id");
            }
            this.i = new ProfileDTO(null, this.h, str2, this.a.i(LoginManager.TAG_USER_NAME), this.a.i("email"), this.h, this.a.i("campaignsUid"), this.a.i("campaignsDid"), false);
        }
        if (this.a.b("customMetaData")) {
            str = this.a.i("customMetaData");
            try {
                if (!com.helpshift.common.c.a(str)) {
                    JSONObject jSONObject = new JSONObject(str);
                    Iterator keys = jSONObject.keys();
                    this.s = new HashMap();
                    while (keys.hasNext()) {
                        String str3 = (String) keys.next();
                        Object obj = jSONObject.get(str3);
                        if (obj instanceof Serializable) {
                            this.s.put(str3, (Serializable) obj);
                        }
                    }
                    return;
                }
                return;
            } catch (Exception e) {
                l.a("Helpshift_KVStoreMigratorr", "Exception converting meta from storage", e);
                return;
            }
        }
        this.s = this.f.b();
    }

    public void b() {
        HashMap hashMap = new HashMap();
        hashMap.put("requireEmail", this.j);
        hashMap.put("fullPrivacy", this.k);
        hashMap.put("hideNameAndEmail", this.l);
        hashMap.put("showSearchOnNewConversation", this.m);
        hashMap.put("gotoConversationAfterContactUs", this.n);
        hashMap.put("showConversationResolutionQuestion", this.o);
        hashMap.put("showConversationInfoScreen", this.p);
        hashMap.put("enableTypingIndicator", this.q);
        Map hashMap2 = new HashMap(b.a);
        hashMap2.putAll(hashMap);
        o.d().b(hashMap2);
        this.c.a(this.r);
        this.d.a(this.g);
        if (!com.helpshift.common.c.a(this.h)) {
            this.d.e(this.h);
            if (this.i != null) {
                this.e.a(this.i);
            }
        }
        this.f.a(this.s);
    }
}
