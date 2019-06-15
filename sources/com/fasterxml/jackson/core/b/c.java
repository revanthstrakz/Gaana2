package com.fasterxml.jackson.core.b;

import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.a;
import com.fasterxml.jackson.core.io.b;

public final class c extends a {
    protected final c c;
    protected int d;
    protected int e;
    protected String f;
    protected c g = null;

    public c(c cVar, int i, int i2, int i3) {
        this.a = i;
        this.c = cVar;
        this.d = i2;
        this.e = i3;
        this.b = -1;
    }

    /* Access modifiers changed, original: protected */
    public void a(int i, int i2, int i3) {
        this.a = i;
        this.b = -1;
        this.d = i2;
        this.e = i3;
        this.f = null;
    }

    public static c g() {
        return new c(null, 0, 1, 0);
    }

    public c a(int i, int i2) {
        c cVar = this.g;
        if (cVar == null) {
            cVar = new c(this, 1, i, i2);
            this.g = cVar;
            return cVar;
        }
        cVar.a(1, i, i2);
        return cVar;
    }

    public c b(int i, int i2) {
        c cVar = this.g;
        if (cVar == null) {
            cVar = new c(this, 2, i, i2);
            this.g = cVar;
            return cVar;
        }
        cVar.a(2, i, i2);
        return cVar;
    }

    public String h() {
        return this.f;
    }

    public c i() {
        return this.c;
    }

    public JsonLocation a(Object obj) {
        return new JsonLocation(obj, -1, this.d, this.e);
    }

    public boolean j() {
        int i = this.b + 1;
        this.b = i;
        if (this.a == 0 || i <= 0) {
            return false;
        }
        return true;
    }

    public void a(String str) {
        this.f = str;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(64);
        switch (this.a) {
            case 0:
                stringBuilder.append("/");
                break;
            case 1:
                stringBuilder.append('[');
                stringBuilder.append(f());
                stringBuilder.append(']');
                break;
            case 2:
                stringBuilder.append('{');
                if (this.f != null) {
                    stringBuilder.append('\"');
                    b.a(stringBuilder, this.f);
                    stringBuilder.append('\"');
                } else {
                    stringBuilder.append('?');
                }
                stringBuilder.append('}');
                break;
        }
        return stringBuilder.toString();
    }
}
