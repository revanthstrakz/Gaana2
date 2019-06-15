package com.helpshift.support;

import android.content.Context;
import com.helpshift.account.dao.ProfileDTO;
import com.helpshift.support.h.g;
import com.helpshift.util.o;

public class j {
    private g a;

    private static class a {
        static final j a = new j(o.b());
    }

    public j(Context context) {
        this.a = new g(context);
    }

    public static j a() {
        return a.a;
    }

    public void a(ProfileDTO profileDTO) {
        this.a.a(profileDTO);
    }

    public ProfileDTO a(String str) {
        return this.a.a(str);
    }
}
