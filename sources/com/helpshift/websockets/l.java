package com.helpshift.websockets;

import java.net.URI;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

class l {
    private static final String[] a = new String[]{"Connection", "Upgrade"};
    private static final String[] b = new String[]{"Upgrade", "websocket"};
    private static final String[] c = new String[]{"Sec-WebSocket-Version", "13"};
    private boolean d;
    private String e;
    private final String f;
    private final String g;
    private final URI h;
    private String i;
    private Set<String> j;
    private List<af> k;
    private List<String[]> l;

    public l(boolean z, String str, String str2, String str3) {
        this.d = z;
        this.e = str;
        this.f = str2;
        this.g = str3;
        str = "%s://%s%s";
        Object[] objArr = new Object[3];
        objArr[0] = z ? "wss" : "ws";
        objArr[1] = str2;
        objArr[2] = str3;
        this.h = URI.create(String.format(str, objArr));
    }

    public void a(String str) {
        if (f(str)) {
            synchronized (this) {
                if (this.j == null) {
                    this.j = new LinkedHashSet();
                }
                this.j.add(str);
            }
            return;
        }
        throw new IllegalArgumentException("'protocol' must be a non-empty string with characters in the range U+0021 to U+007E not including separator characters.");
    }

    private static boolean f(String str) {
        if (str == null || str.length() == 0) {
            return false;
        }
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            if (charAt < '!' || '~' < charAt || ad.a(charAt)) {
                return false;
            }
        }
        return true;
    }

    public boolean b(String str) {
        synchronized (this) {
            if (this.j == null) {
                return false;
            }
            boolean contains = this.j.contains(str);
            return contains;
        }
    }

    public void a(af afVar) {
        if (afVar != null) {
            synchronized (this) {
                if (this.k == null) {
                    this.k = new ArrayList();
                }
                this.k.add(afVar);
            }
        }
    }

    public void c(String str) {
        a(af.a(str));
    }

    public boolean d(String str) {
        if (str == null) {
            return false;
        }
        synchronized (this) {
            if (this.k == null) {
                return false;
            }
            for (af b : this.k) {
                if (b.b().equals(str)) {
                    return true;
                }
            }
            return false;
        }
    }

    public void a(String str, String str2) {
        if (str != null && str.length() != 0) {
            if (str2 == null) {
                str2 = "";
            }
            synchronized (this) {
                if (this.l == null) {
                    this.l = new ArrayList();
                }
                this.l.add(new String[]{str, str2});
            }
        }
    }

    public void e(String str) {
        this.i = str;
    }

    public String a() {
        return String.format("GET %s HTTP/1.1", new Object[]{this.g});
    }

    public List<String[]> b() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new String[]{"Host", this.f});
        arrayList.add(a);
        arrayList.add(b);
        arrayList.add(c);
        arrayList.add(new String[]{"Sec-WebSocket-Key", this.i});
        if (!(this.j == null || this.j.size() == 0)) {
            arrayList.add(new String[]{"Sec-WebSocket-Protocol", p.a(this.j, ", ")});
        }
        if (!(this.k == null || this.k.size() == 0)) {
            arrayList.add(new String[]{"Sec-WebSocket-Extensions", p.a(this.k, ", ")});
        }
        if (!(this.e == null || this.e.length() == 0)) {
            String[] strArr = new String[2];
            strArr[0] = "Authorization";
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Basic ");
            stringBuilder.append(b.a(this.e));
            strArr[1] = stringBuilder.toString();
            arrayList.add(strArr);
        }
        if (!(this.l == null || this.l.size() == 0)) {
            arrayList.addAll(this.l);
        }
        return arrayList;
    }

    public static String a(String str, List<String[]> list) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        stringBuilder.append("\r\n");
        for (String[] strArr : list) {
            stringBuilder.append(strArr[0]);
            stringBuilder.append(": ");
            stringBuilder.append(strArr[1]);
            stringBuilder.append("\r\n");
        }
        stringBuilder.append("\r\n");
        return stringBuilder.toString();
    }
}
