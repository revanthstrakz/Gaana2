package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Calendar;
import java.util.Currency;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringTokenizer;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;

public final class hu {
    public static final gp<String> A = new gp<String>() {
        /* renamed from: a */
        public String read(hx hxVar) throws IOException {
            hy f = hxVar.f();
            if (f == hy.NULL) {
                hxVar.j();
                return null;
            } else if (f == hy.BOOLEAN) {
                return Boolean.toString(hxVar.i());
            } else {
                return hxVar.h();
            }
        }

        /* renamed from: a */
        public void write(hz hzVar, String str) throws IOException {
            hzVar.b(str);
        }
    };
    public static final gp<BigDecimal> B = new gp<BigDecimal>() {
        /* renamed from: a */
        public BigDecimal read(hx hxVar) throws IOException {
            if (hxVar.f() == hy.NULL) {
                hxVar.j();
                return null;
            }
            try {
                return new BigDecimal(hxVar.h());
            } catch (NumberFormatException e) {
                throw new gn(e);
            }
        }

        /* renamed from: a */
        public void write(hz hzVar, BigDecimal bigDecimal) throws IOException {
            hzVar.a((Number) bigDecimal);
        }
    };
    public static final gp<BigInteger> C = new gp<BigInteger>() {
        /* renamed from: a */
        public BigInteger read(hx hxVar) throws IOException {
            if (hxVar.f() == hy.NULL) {
                hxVar.j();
                return null;
            }
            try {
                return new BigInteger(hxVar.h());
            } catch (NumberFormatException e) {
                throw new gn(e);
            }
        }

        /* renamed from: a */
        public void write(hz hzVar, BigInteger bigInteger) throws IOException {
            hzVar.a((Number) bigInteger);
        }
    };
    public static final gq D = a(String.class, A);
    public static final gp<StringBuilder> E = new gp<StringBuilder>() {
        /* renamed from: a */
        public StringBuilder read(hx hxVar) throws IOException {
            if (hxVar.f() != hy.NULL) {
                return new StringBuilder(hxVar.h());
            }
            hxVar.j();
            return null;
        }

        /* renamed from: a */
        public void write(hz hzVar, StringBuilder stringBuilder) throws IOException {
            hzVar.b(stringBuilder == null ? null : stringBuilder.toString());
        }
    };
    public static final gq F = a(StringBuilder.class, E);
    public static final gp<StringBuffer> G = new gp<StringBuffer>() {
        /* renamed from: a */
        public StringBuffer read(hx hxVar) throws IOException {
            if (hxVar.f() != hy.NULL) {
                return new StringBuffer(hxVar.h());
            }
            hxVar.j();
            return null;
        }

        /* renamed from: a */
        public void write(hz hzVar, StringBuffer stringBuffer) throws IOException {
            hzVar.b(stringBuffer == null ? null : stringBuffer.toString());
        }
    };
    public static final gq H = a(StringBuffer.class, G);
    public static final gp<URL> I = new gp<URL>() {
        /* renamed from: a */
        public URL read(hx hxVar) throws IOException {
            URL url = null;
            if (hxVar.f() == hy.NULL) {
                hxVar.j();
                return null;
            }
            String h = hxVar.h();
            if (!"null".equals(h)) {
                url = new URL(h);
            }
            return url;
        }

        /* renamed from: a */
        public void write(hz hzVar, URL url) throws IOException {
            hzVar.b(url == null ? null : url.toExternalForm());
        }
    };
    public static final gq J = a(URL.class, I);
    public static final gp<URI> K = new gp<URI>() {
        /* renamed from: a */
        public URI read(hx hxVar) throws IOException {
            URI uri = null;
            if (hxVar.f() == hy.NULL) {
                hxVar.j();
                return null;
            }
            try {
                String h = hxVar.h();
                if (!"null".equals(h)) {
                    uri = new URI(h);
                }
                return uri;
            } catch (URISyntaxException e) {
                throw new gg(e);
            }
        }

        /* renamed from: a */
        public void write(hz hzVar, URI uri) throws IOException {
            hzVar.b(uri == null ? null : uri.toASCIIString());
        }
    };
    public static final gq L = a(URI.class, K);
    public static final gp<InetAddress> M = new gp<InetAddress>() {
        /* renamed from: a */
        public InetAddress read(hx hxVar) throws IOException {
            if (hxVar.f() != hy.NULL) {
                return InetAddress.getByName(hxVar.h());
            }
            hxVar.j();
            return null;
        }

        /* renamed from: a */
        public void write(hz hzVar, InetAddress inetAddress) throws IOException {
            hzVar.b(inetAddress == null ? null : inetAddress.getHostAddress());
        }
    };
    public static final gq N = b(InetAddress.class, M);
    public static final gp<UUID> O = new gp<UUID>() {
        /* renamed from: a */
        public UUID read(hx hxVar) throws IOException {
            if (hxVar.f() != hy.NULL) {
                return UUID.fromString(hxVar.h());
            }
            hxVar.j();
            return null;
        }

        /* renamed from: a */
        public void write(hz hzVar, UUID uuid) throws IOException {
            hzVar.b(uuid == null ? null : uuid.toString());
        }
    };
    public static final gq P = a(UUID.class, O);
    public static final gp<Currency> Q = new gp<Currency>() {
        /* renamed from: a */
        public Currency read(hx hxVar) throws IOException {
            return Currency.getInstance(hxVar.h());
        }

        /* renamed from: a */
        public void write(hz hzVar, Currency currency) throws IOException {
            hzVar.b(currency.getCurrencyCode());
        }
    }.nullSafe();
    public static final gq R = a(Currency.class, Q);
    public static final gq S = new gq() {
        public <T> gp<T> a(fz fzVar, hw<T> hwVar) {
            if (hwVar.a() != Timestamp.class) {
                return null;
            }
            final gp a = fzVar.a(Date.class);
            return new gp<Timestamp>() {
                /* renamed from: a */
                public Timestamp read(hx hxVar) throws IOException {
                    Date date = (Date) a.read(hxVar);
                    return date != null ? new Timestamp(date.getTime()) : null;
                }

                /* renamed from: a */
                public void write(hz hzVar, Timestamp timestamp) throws IOException {
                    a.write(hzVar, timestamp);
                }
            };
        }
    };
    public static final gp<Calendar> T = new gp<Calendar>() {
        /* renamed from: a */
        public Calendar read(hx hxVar) throws IOException {
            if (hxVar.f() == hy.NULL) {
                hxVar.j();
                return null;
            }
            hxVar.c();
            int i = 0;
            int i2 = i;
            int i3 = i2;
            int i4 = i3;
            int i5 = i4;
            int i6 = i5;
            while (hxVar.f() != hy.END_OBJECT) {
                String g = hxVar.g();
                int m = hxVar.m();
                if ("year".equals(g)) {
                    i = m;
                } else if ("month".equals(g)) {
                    i2 = m;
                } else if ("dayOfMonth".equals(g)) {
                    i3 = m;
                } else if ("hourOfDay".equals(g)) {
                    i4 = m;
                } else if ("minute".equals(g)) {
                    i5 = m;
                } else if ("second".equals(g)) {
                    i6 = m;
                }
            }
            hxVar.d();
            return new GregorianCalendar(i, i2, i3, i4, i5, i6);
        }

        /* renamed from: a */
        public void write(hz hzVar, Calendar calendar) throws IOException {
            if (calendar == null) {
                hzVar.f();
                return;
            }
            hzVar.d();
            hzVar.a("year");
            hzVar.a((long) calendar.get(1));
            hzVar.a("month");
            hzVar.a((long) calendar.get(2));
            hzVar.a("dayOfMonth");
            hzVar.a((long) calendar.get(5));
            hzVar.a("hourOfDay");
            hzVar.a((long) calendar.get(11));
            hzVar.a("minute");
            hzVar.a((long) calendar.get(12));
            hzVar.a("second");
            hzVar.a((long) calendar.get(13));
            hzVar.e();
        }
    };
    public static final gq U = b(Calendar.class, GregorianCalendar.class, T);
    public static final gp<Locale> V = new gp<Locale>() {
        /* renamed from: a */
        public Locale read(hx hxVar) throws IOException {
            String str = null;
            if (hxVar.f() == hy.NULL) {
                hxVar.j();
                return null;
            }
            StringTokenizer stringTokenizer = new StringTokenizer(hxVar.h(), "_");
            String nextToken = stringTokenizer.hasMoreElements() ? stringTokenizer.nextToken() : null;
            String nextToken2 = stringTokenizer.hasMoreElements() ? stringTokenizer.nextToken() : null;
            if (stringTokenizer.hasMoreElements()) {
                str = stringTokenizer.nextToken();
            }
            if (nextToken2 == null && str == null) {
                return new Locale(nextToken);
            }
            if (str == null) {
                return new Locale(nextToken, nextToken2);
            }
            return new Locale(nextToken, nextToken2, str);
        }

        /* renamed from: a */
        public void write(hz hzVar, Locale locale) throws IOException {
            hzVar.b(locale == null ? null : locale.toString());
        }
    };
    public static final gq W = a(Locale.class, V);
    public static final gp<gf> X = new gp<gf>() {
        /* renamed from: a */
        public gf read(hx hxVar) throws IOException {
            switch (hxVar.f()) {
                case NUMBER:
                    return new gk(new hb(hxVar.h()));
                case BOOLEAN:
                    return new gk(Boolean.valueOf(hxVar.i()));
                case STRING:
                    return new gk(hxVar.h());
                case NULL:
                    hxVar.j();
                    return gh.a;
                case BEGIN_ARRAY:
                    gc gcVar = new gc();
                    hxVar.a();
                    while (hxVar.e()) {
                        gcVar.a(read(hxVar));
                    }
                    hxVar.b();
                    return gcVar;
                case BEGIN_OBJECT:
                    gi giVar = new gi();
                    hxVar.c();
                    while (hxVar.e()) {
                        giVar.a(hxVar.g(), read(hxVar));
                    }
                    hxVar.d();
                    return giVar;
                default:
                    throw new IllegalArgumentException();
            }
        }

        /* renamed from: a */
        public void write(hz hzVar, gf gfVar) throws IOException {
            Iterator it;
            if (gfVar == null || gfVar.j()) {
                hzVar.f();
            } else if (gfVar.i()) {
                gk m = gfVar.m();
                if (m.p()) {
                    hzVar.a(m.a());
                } else if (m.o()) {
                    hzVar.a(m.f());
                } else {
                    hzVar.b(m.b());
                }
            } else if (gfVar.g()) {
                hzVar.b();
                it = gfVar.l().iterator();
                while (it.hasNext()) {
                    write(hzVar, (gf) it.next());
                }
                hzVar.c();
            } else if (gfVar.h()) {
                hzVar.d();
                for (Entry entry : gfVar.k().o()) {
                    hzVar.a((String) entry.getKey());
                    write(hzVar, (gf) entry.getValue());
                }
                hzVar.e();
            } else {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Couldn't write ");
                stringBuilder.append(gfVar.getClass());
                throw new IllegalArgumentException(stringBuilder.toString());
            }
        }
    };
    public static final gq Y = b(gf.class, X);
    public static final gq Z = new gq() {
        public <T> gp<T> a(fz fzVar, hw<T> hwVar) {
            Class a = hwVar.a();
            if (!Enum.class.isAssignableFrom(a) || a == Enum.class) {
                return null;
            }
            if (!a.isEnum()) {
                a = a.getSuperclass();
            }
            return new a(a);
        }
    };
    public static final gp<Class> a = new gp<Class>() {
        /* renamed from: a */
        public void write(hz hzVar, Class cls) throws IOException {
            if (cls == null) {
                hzVar.f();
                return;
            }
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Attempted to serialize java.lang.Class: ");
            stringBuilder.append(cls.getName());
            stringBuilder.append(". Forgot to register a type adapter?");
            throw new UnsupportedOperationException(stringBuilder.toString());
        }

        /* renamed from: a */
        public Class read(hx hxVar) throws IOException {
            if (hxVar.f() == hy.NULL) {
                hxVar.j();
                return null;
            }
            throw new UnsupportedOperationException("Attempted to deserialize a java.lang.Class. Forgot to register a type adapter?");
        }
    };
    public static final gq b = a(Class.class, a);
    public static final gp<BitSet> c = new gp<BitSet>() {
        /* JADX WARNING: Missing block: B:14:0x004a, code skipped:
            if (java.lang.Integer.parseInt(r1) != 0) goto L_0x0071;
     */
        /* JADX WARNING: Missing block: B:15:0x004d, code skipped:
            r5 = false;
     */
        /* JADX WARNING: Missing block: B:21:0x006f, code skipped:
            if (r7.m() != 0) goto L_0x0071;
     */
        /* renamed from: a */
        public java.util.BitSet read(com.google.ads.interactivemedia.v3.internal.hx r7) throws java.io.IOException {
            /*
            r6 = this;
            r0 = r7.f();
            r1 = com.google.ads.interactivemedia.v3.internal.hy.NULL;
            if (r0 != r1) goto L_0x000d;
        L_0x0008:
            r7.j();
            r7 = 0;
            return r7;
        L_0x000d:
            r0 = new java.util.BitSet;
            r0.<init>();
            r7.a();
            r1 = r7.f();
            r2 = 0;
            r3 = r2;
        L_0x001b:
            r4 = com.google.ads.interactivemedia.v3.internal.hy.END_ARRAY;
            if (r1 == r4) goto L_0x007d;
        L_0x001f:
            r4 = com.google.ads.interactivemedia.v3.internal.hu.AnonymousClass30.a;
            r5 = r1.ordinal();
            r4 = r4[r5];
            r5 = 1;
            switch(r4) {
                case 1: goto L_0x006b;
                case 2: goto L_0x0066;
                case 3: goto L_0x0042;
                default: goto L_0x002b;
            };
        L_0x002b:
            r7 = new com.google.ads.interactivemedia.v3.internal.gn;
            r0 = new java.lang.StringBuilder;
            r0.<init>();
            r2 = "Invalid bitset value type: ";
            r0.append(r2);
            r0.append(r1);
            r0 = r0.toString();
            r7.<init>(r0);
            throw r7;
        L_0x0042:
            r1 = r7.h();
            r4 = java.lang.Integer.parseInt(r1);	 Catch:{ NumberFormatException -> 0x004f }
            if (r4 == 0) goto L_0x004d;
        L_0x004c:
            goto L_0x0071;
        L_0x004d:
            r5 = r2;
            goto L_0x0071;
        L_0x004f:
            r7 = new com.google.ads.interactivemedia.v3.internal.gn;
            r0 = new java.lang.StringBuilder;
            r0.<init>();
            r2 = "Error: Expecting: bitset number value (1, 0), Found: ";
            r0.append(r2);
            r0.append(r1);
            r0 = r0.toString();
            r7.<init>(r0);
            throw r7;
        L_0x0066:
            r5 = r7.i();
            goto L_0x0071;
        L_0x006b:
            r1 = r7.m();
            if (r1 == 0) goto L_0x004d;
        L_0x0071:
            if (r5 == 0) goto L_0x0076;
        L_0x0073:
            r0.set(r3);
        L_0x0076:
            r3 = r3 + 1;
            r1 = r7.f();
            goto L_0x001b;
        L_0x007d:
            r7.b();
            return r0;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.hu$AnonymousClass12.read(com.google.ads.interactivemedia.v3.internal.hx):java.util.BitSet");
        }

        /* renamed from: a */
        public void write(hz hzVar, BitSet bitSet) throws IOException {
            if (bitSet == null) {
                hzVar.f();
                return;
            }
            hzVar.b();
            for (int i = 0; i < bitSet.length(); i++) {
                hzVar.a((long) bitSet.get(i));
            }
            hzVar.c();
        }
    };
    public static final gq d = a(BitSet.class, c);
    public static final gp<Boolean> e = new gp<Boolean>() {
        /* renamed from: a */
        public Boolean read(hx hxVar) throws IOException {
            if (hxVar.f() == hy.NULL) {
                hxVar.j();
                return null;
            } else if (hxVar.f() == hy.STRING) {
                return Boolean.valueOf(Boolean.parseBoolean(hxVar.h()));
            } else {
                return Boolean.valueOf(hxVar.i());
            }
        }

        /* renamed from: a */
        public void write(hz hzVar, Boolean bool) throws IOException {
            hzVar.a(bool);
        }
    };
    public static final gp<Boolean> f = new gp<Boolean>() {
        /* renamed from: a */
        public Boolean read(hx hxVar) throws IOException {
            if (hxVar.f() != hy.NULL) {
                return Boolean.valueOf(hxVar.h());
            }
            hxVar.j();
            return null;
        }

        /* renamed from: a */
        public void write(hz hzVar, Boolean bool) throws IOException {
            hzVar.b(bool == null ? "null" : bool.toString());
        }
    };
    public static final gq g = a(Boolean.TYPE, Boolean.class, e);
    public static final gp<Number> h = new gp<Number>() {
        /* renamed from: a */
        public Number read(hx hxVar) throws IOException {
            if (hxVar.f() == hy.NULL) {
                hxVar.j();
                return null;
            }
            try {
                return Byte.valueOf((byte) hxVar.m());
            } catch (NumberFormatException e) {
                throw new gn(e);
            }
        }

        /* renamed from: a */
        public void write(hz hzVar, Number number) throws IOException {
            hzVar.a(number);
        }
    };
    public static final gq i = a(Byte.TYPE, Byte.class, h);
    public static final gp<Number> j = new gp<Number>() {
        /* renamed from: a */
        public Number read(hx hxVar) throws IOException {
            if (hxVar.f() == hy.NULL) {
                hxVar.j();
                return null;
            }
            try {
                return Short.valueOf((short) hxVar.m());
            } catch (NumberFormatException e) {
                throw new gn(e);
            }
        }

        /* renamed from: a */
        public void write(hz hzVar, Number number) throws IOException {
            hzVar.a(number);
        }
    };
    public static final gq k = a(Short.TYPE, Short.class, j);
    public static final gp<Number> l = new gp<Number>() {
        /* renamed from: a */
        public Number read(hx hxVar) throws IOException {
            if (hxVar.f() == hy.NULL) {
                hxVar.j();
                return null;
            }
            try {
                return Integer.valueOf(hxVar.m());
            } catch (NumberFormatException e) {
                throw new gn(e);
            }
        }

        /* renamed from: a */
        public void write(hz hzVar, Number number) throws IOException {
            hzVar.a(number);
        }
    };
    public static final gq m = a(Integer.TYPE, Integer.class, l);
    public static final gp<AtomicInteger> n = new gp<AtomicInteger>() {
        /* renamed from: a */
        public AtomicInteger read(hx hxVar) throws IOException {
            try {
                return new AtomicInteger(hxVar.m());
            } catch (NumberFormatException e) {
                throw new gn(e);
            }
        }

        /* renamed from: a */
        public void write(hz hzVar, AtomicInteger atomicInteger) throws IOException {
            hzVar.a((long) atomicInteger.get());
        }
    }.nullSafe();
    public static final gq o = a(AtomicInteger.class, n);
    public static final gp<AtomicBoolean> p = new gp<AtomicBoolean>() {
        /* renamed from: a */
        public AtomicBoolean read(hx hxVar) throws IOException {
            return new AtomicBoolean(hxVar.i());
        }

        /* renamed from: a */
        public void write(hz hzVar, AtomicBoolean atomicBoolean) throws IOException {
            hzVar.a(atomicBoolean.get());
        }
    }.nullSafe();
    public static final gq q = a(AtomicBoolean.class, p);
    public static final gp<AtomicIntegerArray> r = new gp<AtomicIntegerArray>() {
        /* renamed from: a */
        public AtomicIntegerArray read(hx hxVar) throws IOException {
            ArrayList arrayList = new ArrayList();
            hxVar.a();
            while (hxVar.e()) {
                try {
                    arrayList.add(Integer.valueOf(hxVar.m()));
                } catch (NumberFormatException e) {
                    throw new gn(e);
                }
            }
            hxVar.b();
            int size = arrayList.size();
            AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(size);
            for (int i = 0; i < size; i++) {
                atomicIntegerArray.set(i, ((Integer) arrayList.get(i)).intValue());
            }
            return atomicIntegerArray;
        }

        /* renamed from: a */
        public void write(hz hzVar, AtomicIntegerArray atomicIntegerArray) throws IOException {
            hzVar.b();
            int length = atomicIntegerArray.length();
            for (int i = 0; i < length; i++) {
                hzVar.a((long) atomicIntegerArray.get(i));
            }
            hzVar.c();
        }
    }.nullSafe();
    public static final gq s = a(AtomicIntegerArray.class, r);
    public static final gp<Number> t = new gp<Number>() {
        /* renamed from: a */
        public Number read(hx hxVar) throws IOException {
            if (hxVar.f() == hy.NULL) {
                hxVar.j();
                return null;
            }
            try {
                return Long.valueOf(hxVar.l());
            } catch (NumberFormatException e) {
                throw new gn(e);
            }
        }

        /* renamed from: a */
        public void write(hz hzVar, Number number) throws IOException {
            hzVar.a(number);
        }
    };
    public static final gp<Number> u = new gp<Number>() {
        /* renamed from: a */
        public Number read(hx hxVar) throws IOException {
            if (hxVar.f() != hy.NULL) {
                return Float.valueOf((float) hxVar.k());
            }
            hxVar.j();
            return null;
        }

        /* renamed from: a */
        public void write(hz hzVar, Number number) throws IOException {
            hzVar.a(number);
        }
    };
    public static final gp<Number> v = new gp<Number>() {
        /* renamed from: a */
        public Number read(hx hxVar) throws IOException {
            if (hxVar.f() != hy.NULL) {
                return Double.valueOf(hxVar.k());
            }
            hxVar.j();
            return null;
        }

        /* renamed from: a */
        public void write(hz hzVar, Number number) throws IOException {
            hzVar.a(number);
        }
    };
    public static final gp<Number> w = new gp<Number>() {
        /* renamed from: a */
        public Number read(hx hxVar) throws IOException {
            hy f = hxVar.f();
            int i = AnonymousClass30.a[f.ordinal()];
            if (i == 1) {
                return new hb(hxVar.h());
            }
            if (i != 4) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Expecting number, got: ");
                stringBuilder.append(f);
                throw new gn(stringBuilder.toString());
            }
            hxVar.j();
            return null;
        }

        /* renamed from: a */
        public void write(hz hzVar, Number number) throws IOException {
            hzVar.a(number);
        }
    };
    public static final gq x = a(Number.class, w);
    public static final gp<Character> y = new gp<Character>() {
        /* renamed from: a */
        public Character read(hx hxVar) throws IOException {
            if (hxVar.f() == hy.NULL) {
                hxVar.j();
                return null;
            }
            String h = hxVar.h();
            if (h.length() == 1) {
                return Character.valueOf(h.charAt(0));
            }
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Expecting character, got: ");
            stringBuilder.append(h);
            throw new gn(stringBuilder.toString());
        }

        /* renamed from: a */
        public void write(hz hzVar, Character ch) throws IOException {
            hzVar.b(ch == null ? null : String.valueOf(ch));
        }
    };
    public static final gq z = a(Character.TYPE, Character.class, y);

    private static final class a<T extends Enum<T>> extends gp<T> {
        private final Map<String, T> a = new HashMap();
        private final Map<T, String> b = new HashMap();

        public a(Class<T> cls) {
            try {
                for (Enum enumR : (Enum[]) cls.getEnumConstants()) {
                    Object name = enumR.name();
                    gt gtVar = (gt) cls.getField(name).getAnnotation(gt.class);
                    if (gtVar != null) {
                        name = gtVar.a();
                        for (Object put : gtVar.b()) {
                            this.a.put(put, enumR);
                        }
                    }
                    this.a.put(name, enumR);
                    this.b.put(enumR, name);
                }
            } catch (NoSuchFieldException e) {
                throw new AssertionError(e);
            }
        }

        /* renamed from: a */
        public T read(hx hxVar) throws IOException {
            if (hxVar.f() != hy.NULL) {
                return (Enum) this.a.get(hxVar.h());
            }
            hxVar.j();
            return null;
        }

        /* renamed from: a */
        public void write(hz hzVar, T t) throws IOException {
            hzVar.b(t == null ? null : (String) this.b.get(t));
        }
    }

    public static <TT> gq a(final hw<TT> hwVar, final gp<TT> gpVar) {
        return new gq() {
            public <T> gp<T> a(fz fzVar, hw<T> hwVar) {
                return hwVar.equals(hwVar) ? gpVar : null;
            }
        };
    }

    public static <TT> gq a(final Class<TT> cls, final gp<TT> gpVar) {
        return new gq() {
            public <T> gp<T> a(fz fzVar, hw<T> hwVar) {
                return hwVar.a() == cls ? gpVar : null;
            }

            public String toString() {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Factory[type=");
                stringBuilder.append(cls.getName());
                stringBuilder.append(",adapter=");
                stringBuilder.append(gpVar);
                stringBuilder.append("]");
                return stringBuilder.toString();
            }
        };
    }

    public static <TT> gq a(final Class<TT> cls, final Class<TT> cls2, final gp<? super TT> gpVar) {
        return new gq() {
            public <T> gp<T> a(fz fzVar, hw<T> hwVar) {
                Class a = hwVar.a();
                return (a == cls || a == cls2) ? gpVar : null;
            }

            public String toString() {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Factory[type=");
                stringBuilder.append(cls2.getName());
                stringBuilder.append("+");
                stringBuilder.append(cls.getName());
                stringBuilder.append(",adapter=");
                stringBuilder.append(gpVar);
                stringBuilder.append("]");
                return stringBuilder.toString();
            }
        };
    }

    public static <TT> gq b(final Class<TT> cls, final Class<? extends TT> cls2, final gp<? super TT> gpVar) {
        return new gq() {
            public <T> gp<T> a(fz fzVar, hw<T> hwVar) {
                Class a = hwVar.a();
                return (a == cls || a == cls2) ? gpVar : null;
            }

            public String toString() {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Factory[type=");
                stringBuilder.append(cls.getName());
                stringBuilder.append("+");
                stringBuilder.append(cls2.getName());
                stringBuilder.append(",adapter=");
                stringBuilder.append(gpVar);
                stringBuilder.append("]");
                return stringBuilder.toString();
            }
        };
    }

    public static <T1> gq b(final Class<T1> cls, final gp<T1> gpVar) {
        return new gq() {
            public <T2> gp<T2> a(fz fzVar, hw<T2> hwVar) {
                final Class a = hwVar.a();
                if (cls.isAssignableFrom(a)) {
                    return new gp<T1>() {
                        public void write(hz hzVar, T1 t1) throws IOException {
                            gpVar.write(hzVar, t1);
                        }

                        public T1 read(hx hxVar) throws IOException {
                            Object read = gpVar.read(hxVar);
                            if (read == null || a.isInstance(read)) {
                                return read;
                            }
                            StringBuilder stringBuilder = new StringBuilder();
                            stringBuilder.append("Expected a ");
                            stringBuilder.append(a.getName());
                            stringBuilder.append(" but was ");
                            stringBuilder.append(read.getClass().getName());
                            throw new gn(stringBuilder.toString());
                        }
                    };
                }
                return null;
            }

            public String toString() {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Factory[typeHierarchy=");
                stringBuilder.append(cls.getName());
                stringBuilder.append(",adapter=");
                stringBuilder.append(gpVar);
                stringBuilder.append("]");
                return stringBuilder.toString();
            }
        };
    }
}
