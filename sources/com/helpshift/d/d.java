package com.helpshift.d;

public class d implements c {
    private com.helpshift.q.d a;
    private b b;

    protected d(com.helpshift.q.d dVar) {
        this.a = dVar;
    }

    protected d(com.helpshift.q.d dVar, b bVar) {
        this(dVar);
        this.b = bVar;
    }

    private static boolean a(Boolean bool) {
        return bool != null && bool.booleanValue();
    }

    private boolean e(String str) {
        boolean a = a((Boolean) this.a.a("firstDeviceSyncComplete"));
        com.helpshift.q.d dVar = this.a;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("switchUserCompleteFor");
        stringBuilder.append(str);
        return a && a((Boolean) dVar.a(stringBuilder.toString()));
    }

    public boolean a(String str) {
        return e(str);
    }

    public boolean b(String str) {
        return e(str);
    }

    public void a() {
        this.a.a("firstDeviceSyncComplete", Boolean.valueOf(true));
        if (this.b != null) {
            this.b.c();
        }
    }

    public boolean b() {
        return a((Boolean) this.a.a("firstDeviceSyncComplete"));
    }

    public void c(String str) {
        com.helpshift.q.d dVar = this.a;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("switchUserCompleteFor");
        stringBuilder.append(str);
        dVar.a(stringBuilder.toString(), Boolean.valueOf(false));
    }

    public void d(String str) {
        com.helpshift.q.d dVar = this.a;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("switchUserCompleteFor");
        stringBuilder.append(str);
        dVar.a(stringBuilder.toString(), Boolean.valueOf(true));
        if (this.b != null) {
            this.b.a(str);
        }
    }
}
