package com.helpshift.websockets;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.net.SocketFactory;

public class y {
    private final ag a;
    private final Map<String, List<String>> b = new TreeMap(String.CASE_INSENSITIVE_ORDER);
    private final ab c = new ab();
    private boolean d;
    private String e;
    private int f;
    private String g;
    private String h;

    y(ag agVar) {
        this.a = agVar;
        a();
    }

    public y a() {
        this.d = false;
        this.e = null;
        this.f = -1;
        this.g = null;
        this.h = null;
        this.b.clear();
        return this;
    }

    public boolean b() {
        return this.d;
    }

    public String c() {
        return this.e;
    }

    public int d() {
        return this.f;
    }

    public String e() {
        return this.g;
    }

    public String f() {
        return this.h;
    }

    public Map<String, List<String>> g() {
        return this.b;
    }

    /* Access modifiers changed, original: 0000 */
    public SocketFactory h() {
        return this.c.a(this.d);
    }
}
