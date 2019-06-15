package com.helpshift.account.dao;

import com.helpshift.common.platform.o;
import com.helpshift.support.j;
import java.io.Serializable;

public class b implements a, c {
    private j a;
    private o b;

    public b(j jVar, o oVar) {
        this.a = jVar;
        this.b = oVar;
    }

    public void a(String str) {
        this.b.a("loginIdentifier", str);
    }

    public String a() {
        return this.b.b("loginIdentifier", "");
    }

    public void b(String str) {
        this.b.a("deviceId", str);
    }

    public String b() {
        return this.b.b("deviceId", "");
    }

    public void c(String str) {
        this.b.a("deviceToken", str);
    }

    public String c() {
        return this.b.b("deviceToken", "");
    }

    public ProfileDTO d(String str) {
        if (!f(str)) {
            return this.a.a(str);
        }
        Object b = this.b.b("default_user_profile");
        return b instanceof ProfileDTO ? (ProfileDTO) b : null;
    }

    public void e(String str) {
        this.b.a("default_user_login", str);
    }

    public String d() {
        return this.b.a("default_user_login");
    }

    private boolean f(String str) {
        String d = d();
        return d != null && d.equals(str);
    }

    public ProfileDTO a(ProfileDTO profileDTO) {
        if (f(profileDTO.c)) {
            Serializable serializable;
            if (profileDTO.a == null) {
                Serializable profileDTO2 = new ProfileDTO(Long.valueOf((long) (DefaultOggSeeker.MATCH_BYTE_RANGE + (Math.abs(profileDTO.c.hashCode()) % 1000))), profileDTO.c, profileDTO.b, profileDTO.d, profileDTO.e, profileDTO.f, profileDTO.g, profileDTO.h, profileDTO.i);
            } else {
                serializable = profileDTO;
            }
            this.b.a("default_user_profile", serializable);
            return serializable;
        }
        this.a.a(profileDTO);
        return this.a.a(profileDTO.c);
    }
}
