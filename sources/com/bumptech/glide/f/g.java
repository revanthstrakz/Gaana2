package com.bumptech.glide.f;

public class g {
    private Class<?> a;
    private Class<?> b;
    private Class<?> c;

    public g(Class<?> cls, Class<?> cls2) {
        a(cls, cls2);
    }

    public g(Class<?> cls, Class<?> cls2, Class<?> cls3) {
        a(cls, cls2, cls3);
    }

    public void a(Class<?> cls, Class<?> cls2) {
        a(cls, cls2, null);
    }

    public void a(Class<?> cls, Class<?> cls2, Class<?> cls3) {
        this.a = cls;
        this.b = cls2;
        this.c = cls3;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("MultiClassKey{first=");
        stringBuilder.append(this.a);
        stringBuilder.append(", second=");
        stringBuilder.append(this.b);
        stringBuilder.append('}');
        return stringBuilder.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        g gVar = (g) obj;
        return this.a.equals(gVar.a) && this.b.equals(gVar.b) && i.a(this.c, gVar.c);
    }

    public int hashCode() {
        return (31 * ((this.a.hashCode() * 31) + this.b.hashCode())) + (this.c != null ? this.c.hashCode() : 0);
    }
}
