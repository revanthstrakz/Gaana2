package com.fasterxml.jackson.core.b;

import com.fasterxml.jackson.core.a;

public class d extends a {
    protected final d c;
    protected String d;
    protected d e = null;

    protected d(int i, d dVar) {
        this.a = i;
        this.c = dVar;
        this.b = -1;
    }

    public static d g() {
        return new d(0, null);
    }

    private d a(int i) {
        this.a = i;
        this.b = -1;
        this.d = null;
        return this;
    }

    public final d h() {
        d dVar = this.e;
        if (dVar != null) {
            return dVar.a(1);
        }
        dVar = new d(1, this);
        this.e = dVar;
        return dVar;
    }

    public final d i() {
        d dVar = this.e;
        if (dVar != null) {
            return dVar.a(2);
        }
        dVar = new d(2, this);
        this.e = dVar;
        return dVar;
    }

    public final d j() {
        return this.c;
    }

    public final int a(String str) {
        if (this.a != 2 || this.d != null) {
            return 4;
        }
        this.d = str;
        return this.b < 0 ? 0 : 1;
    }

    public final int k() {
        if (this.a != 2) {
            int i = 0;
            if (this.a == 1) {
                int i2 = this.b;
                this.b++;
                if (i2 >= 0) {
                    i = 1;
                }
                return i;
            }
            this.b++;
            if (this.b != 0) {
                i = 3;
            }
            return i;
        } else if (this.d == null) {
            return 5;
        } else {
            this.d = null;
            this.b++;
            return 2;
        }
    }

    /* Access modifiers changed, original: protected|final */
    public final void a(StringBuilder stringBuilder) {
        if (this.a == 2) {
            stringBuilder.append('{');
            if (this.d != null) {
                stringBuilder.append('\"');
                stringBuilder.append(this.d);
                stringBuilder.append('\"');
            } else {
                stringBuilder.append('?');
            }
            stringBuilder.append('}');
        } else if (this.a == 1) {
            stringBuilder.append('[');
            stringBuilder.append(f());
            stringBuilder.append(']');
        } else {
            stringBuilder.append("/");
        }
    }

    public final String toString() {
        StringBuilder stringBuilder = new StringBuilder(64);
        a(stringBuilder);
        return stringBuilder.toString();
    }
}
