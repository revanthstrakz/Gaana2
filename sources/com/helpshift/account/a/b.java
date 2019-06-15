package com.helpshift.account.a;

import com.helpshift.account.dao.ProfileDTO;
import com.helpshift.account.dao.c;
import com.helpshift.common.domain.e;
import com.helpshift.common.domain.network.f;
import com.helpshift.common.domain.network.k;
import com.helpshift.common.domain.network.l;
import com.helpshift.common.exception.NetworkException;
import com.helpshift.common.exception.RootAPIException;
import com.helpshift.common.platform.network.h;
import com.helpshift.common.platform.p;
import java.util.HashMap;
import java.util.Observable;

public class b extends Observable {
    public Long a;
    public String b;
    public String c;
    public String d;
    public String e;
    public String f;
    public String g;
    public String h;
    public String i;
    public boolean j;
    private final e k;
    private final p l;
    private final c m;
    private final h n;
    private String o;

    b(p pVar, e eVar, ProfileDTO profileDTO) {
        this.l = pVar;
        this.k = eVar;
        this.m = pVar.p();
        this.n = pVar.j();
        a(profileDTO);
    }

    /* Access modifiers changed, original: 0000 */
    public void a(String str) {
        this.o = str;
    }

    /* Access modifiers changed, original: 0000 */
    public void a() {
        a(this.m.a(g()));
    }

    private ProfileDTO g() {
        return new ProfileDTO(this.a, this.c, this.b, this.d, this.e, this.f, this.h, this.i, this.j);
    }

    private void a(ProfileDTO profileDTO) {
        this.a = profileDTO.a;
        this.b = profileDTO.b;
        this.c = profileDTO.c;
        this.d = profileDTO.d;
        this.e = profileDTO.e;
        this.f = profileDTO.f;
        this.h = profileDTO.g;
        this.i = profileDTO.h;
        this.j = profileDTO.i;
    }

    public void b(String str) {
        this.d = str;
        this.m.a(g());
    }

    public void c(String str) {
        this.e = str;
        this.m.a(g());
    }

    public String b() {
        if (c()) {
            return this.o;
        }
        return this.c;
    }

    public boolean c() {
        return this.c.equals(this.f);
    }

    public String a(String str, String str2) {
        HashMap hashMap = new HashMap();
        hashMap.put("identifier", this.f);
        hashMap.put("displayname", str);
        hashMap.put("email", str2);
        this.b = this.n.a(h().c(hashMap).b).a;
        this.m.a(g());
        d();
        return this.b;
    }

    private com.helpshift.common.domain.network.h h() {
        return new f(new l(new k("/profiles/", this.k, this.l), this.l));
    }

    private com.helpshift.common.domain.network.h i() {
        return new f(new com.helpshift.common.domain.network.c(new l(new k("/update-ua-token/", this.k, this.l), this.l)));
    }

    /* Access modifiers changed, original: 0000 */
    public void d() {
        if (!com.helpshift.common.c.a(this.g) && !com.helpshift.common.c.a(this.b) && !this.j) {
            e();
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void e() {
        HashMap hashMap = new HashMap();
        hashMap.put("profile-id", this.b);
        hashMap.put(AccountKitGraphConstants.TOKEN_RESPONSE_TYPE, this.g);
        try {
            i().c(hashMap);
            this.j = true;
            this.m.a(g());
            setChanged();
            notifyObservers();
        } catch (RootAPIException e) {
            if (e.c != NetworkException.NON_RETRIABLE) {
                throw e;
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0042  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0042  */
    /* JADX WARNING: Removed duplicated region for block: B:18:? A:{SYNTHETIC, RETURN} */
    public void f() {
        /*
        r4 = this;
        r0 = r4.l;
        r0 = r0.w();
        r1 = 1;
        r2 = 0;
        if (r0 != 0) goto L_0x0023;
    L_0x000a:
        r0 = r4.h;
        r0 = com.helpshift.common.c.a(r0);
        r3 = 0;
        if (r0 != 0) goto L_0x0016;
    L_0x0013:
        r4.h = r3;
        r2 = r1;
    L_0x0016:
        r0 = r4.i;
        r0 = com.helpshift.common.c.a(r0);
        if (r0 != 0) goto L_0x0021;
    L_0x001e:
        r4.i = r3;
        goto L_0x0040;
    L_0x0021:
        r1 = r2;
        goto L_0x0040;
    L_0x0023:
        r3 = r4.h;
        r3 = com.helpshift.common.c.a(r3);
        if (r3 == 0) goto L_0x0032;
    L_0x002b:
        r2 = r0.a();
        r4.h = r2;
        r2 = r1;
    L_0x0032:
        r3 = r4.i;
        r3 = com.helpshift.common.c.a(r3);
        if (r3 == 0) goto L_0x0021;
    L_0x003a:
        r0 = r0.b();
        r4.i = r0;
    L_0x0040:
        if (r1 == 0) goto L_0x004b;
    L_0x0042:
        r0 = r4.m;
        r1 = r4.g();
        r0.a(r1);
    L_0x004b:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.helpshift.account.a.b.f():void");
    }

    public void d(String str) {
        if (com.helpshift.common.c.a(this.g) || !this.g.equals(str)) {
            this.g = str;
            this.j = false;
            this.m.a(g());
        }
    }
}
