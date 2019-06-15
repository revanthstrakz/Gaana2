package com.google.ads.interactivemedia.v3.internal;

import java.math.BigInteger;

public final class gk extends gf {
    private static final Class<?>[] a = new Class[]{Integer.TYPE, Long.TYPE, Short.TYPE, Float.TYPE, Double.TYPE, Byte.TYPE, Boolean.TYPE, Character.TYPE, Integer.class, Long.class, Short.class, Float.class, Double.class, Byte.class, Boolean.class, Character.class};
    private Object b;

    public gk(Boolean bool) {
        a((Object) bool);
    }

    public gk(Number number) {
        a((Object) number);
    }

    public gk(String str) {
        a((Object) str);
    }

    /* Access modifiers changed, original: 0000 */
    public void a(Object obj) {
        if (obj instanceof Character) {
            this.b = String.valueOf(((Character) obj).charValue());
            return;
        }
        boolean z = (obj instanceof Number) || b(obj);
        gw.a(z);
        this.b = obj;
    }

    public boolean o() {
        return this.b instanceof Boolean;
    }

    /* Access modifiers changed, original: 0000 */
    public Boolean n() {
        return (Boolean) this.b;
    }

    public boolean f() {
        if (o()) {
            return n().booleanValue();
        }
        return Boolean.parseBoolean(b());
    }

    public boolean p() {
        return this.b instanceof Number;
    }

    public Number a() {
        return this.b instanceof String ? new hb((String) this.b) : (Number) this.b;
    }

    public boolean q() {
        return this.b instanceof String;
    }

    public String b() {
        if (p()) {
            return a().toString();
        }
        if (o()) {
            return n().toString();
        }
        return (String) this.b;
    }

    public double c() {
        return p() ? a().doubleValue() : Double.parseDouble(b());
    }

    public long d() {
        return p() ? a().longValue() : Long.parseLong(b());
    }

    public int e() {
        return p() ? a().intValue() : Integer.parseInt(b());
    }

    private static boolean b(Object obj) {
        if (obj instanceof String) {
            return true;
        }
        Class cls = obj.getClass();
        for (Class isAssignableFrom : a) {
            if (isAssignableFrom.isAssignableFrom(cls)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        if (this.b == null) {
            return 31;
        }
        long longValue;
        if (a(this)) {
            longValue = a().longValue();
            return (int) (longValue ^ (longValue >>> 32));
        } else if (!(this.b instanceof Number)) {
            return this.b.hashCode();
        } else {
            longValue = Double.doubleToLongBits(a().doubleValue());
            return (int) (longValue ^ (longValue >>> 32));
        }
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        gk gkVar = (gk) obj;
        if (this.b == null) {
            if (gkVar.b != null) {
                z = false;
            }
            return z;
        } else if (a(this) && a(gkVar)) {
            if (a().longValue() != gkVar.a().longValue()) {
                z = false;
            }
            return z;
        } else if (!(this.b instanceof Number) || !(gkVar.b instanceof Number)) {
            return this.b.equals(gkVar.b);
        } else {
            double doubleValue = a().doubleValue();
            double doubleValue2 = gkVar.a().doubleValue();
            if (!(doubleValue == doubleValue2 || (Double.isNaN(doubleValue) && Double.isNaN(doubleValue2)))) {
                z = false;
            }
            return z;
        }
    }

    private static boolean a(gk gkVar) {
        boolean z = false;
        if (!(gkVar.b instanceof Number)) {
            return false;
        }
        Number number = (Number) gkVar.b;
        if ((number instanceof BigInteger) || (number instanceof Long) || (number instanceof Integer) || (number instanceof Short) || (number instanceof Byte)) {
            z = true;
        }
        return z;
    }
}
