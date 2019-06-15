package com.helpshift.support.h;

import android.content.Context;
import com.helpshift.common.platform.o;
import com.helpshift.q.d;
import java.io.Serializable;

public class k implements o {
    private d a;

    public k(Context context) {
        this.a = new m(context);
    }

    public void a(String str, Boolean bool) {
        b(str, (Serializable) bool);
    }

    public void a(String str, Integer num) {
        b(str, (Serializable) num);
    }

    public void a(String str, Float f) {
        b(str, (Serializable) f);
    }

    public void a(String str, String str2) {
        b(str, (Serializable) str2);
    }

    public void a(String str, Serializable serializable) {
        b(str, serializable);
    }

    public Boolean b(String str, Boolean bool) {
        Object a = this.a.a(str);
        if (a == null) {
            return bool;
        }
        return (Boolean) a;
    }

    public Integer b(String str, Integer num) {
        Object a = this.a.a(str);
        if (a == null) {
            return num;
        }
        return (Integer) a;
    }

    public Float b(String str, Float f) {
        Object a = this.a.a(str);
        if (a == null) {
            return f;
        }
        return (Float) a;
    }

    public String a(String str) {
        Object a = this.a.a(str);
        if (a == null) {
            return null;
        }
        return (String) a;
    }

    public String b(String str, String str2) {
        Object a = this.a.a(str);
        if (a == null) {
            return str2;
        }
        return (String) a;
    }

    public Object b(String str) {
        return this.a.a(str);
    }

    public void a() {
        this.a.a();
    }

    private void b(String str, Serializable serializable) {
        if (serializable == null) {
            this.a.b(str);
        } else {
            this.a.a(str, serializable);
        }
    }
}
