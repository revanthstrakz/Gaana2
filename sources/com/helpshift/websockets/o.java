package com.helpshift.websockets;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class o {
    private final ae a;
    private final List<aj> b = new ArrayList();
    private boolean c = true;
    private List<aj> d;

    public o(ae aeVar) {
        this.a = aeVar;
    }

    public void a(aj ajVar) {
        if (ajVar != null) {
            synchronized (this.b) {
                this.b.add(ajVar);
                this.c = true;
            }
        }
    }

    private List<aj> a() {
        synchronized (this.b) {
            if (this.c) {
                ArrayList arrayList = new ArrayList(this.b.size());
                for (aj add : this.b) {
                    arrayList.add(add);
                }
                this.d = arrayList;
                this.c = false;
                return arrayList;
            }
            List list = this.d;
            return list;
        }
    }

    public void a(WebSocketState webSocketState) {
        for (aj ajVar : a()) {
            try {
                ajVar.a(this.a, webSocketState);
            } catch (Throwable th) {
                a(ajVar, th);
            }
        }
    }

    public void a(Map<String, List<String>> map) {
        for (aj ajVar : a()) {
            try {
                ajVar.a(this.a, (Map) map);
            } catch (Throwable th) {
                a(ajVar, th);
            }
        }
    }

    public void a(ah ahVar, ah ahVar2, boolean z) {
        for (aj ajVar : a()) {
            try {
                ajVar.a(this.a, ahVar, ahVar2, z);
            } catch (Throwable th) {
                a(ajVar, th);
            }
        }
    }

    public void a(ah ahVar) {
        for (aj ajVar : a()) {
            try {
                ajVar.a(this.a, ahVar);
            } catch (Throwable th) {
                a(ajVar, th);
            }
        }
    }

    public void b(ah ahVar) {
        for (aj ajVar : a()) {
            try {
                ajVar.b(this.a, ahVar);
            } catch (Throwable th) {
                a(ajVar, th);
            }
        }
    }

    public void c(ah ahVar) {
        for (aj ajVar : a()) {
            try {
                ajVar.c(this.a, ahVar);
            } catch (Throwable th) {
                a(ajVar, th);
            }
        }
    }

    public void d(ah ahVar) {
        for (aj ajVar : a()) {
            try {
                ajVar.d(this.a, ahVar);
            } catch (Throwable th) {
                a(ajVar, th);
            }
        }
    }

    public void e(ah ahVar) {
        for (aj ajVar : a()) {
            try {
                ajVar.e(this.a, ahVar);
            } catch (Throwable th) {
                a(ajVar, th);
            }
        }
    }

    public void f(ah ahVar) {
        for (aj ajVar : a()) {
            try {
                ajVar.f(this.a, ahVar);
            } catch (Throwable th) {
                a(ajVar, th);
            }
        }
    }

    public void g(ah ahVar) {
        for (aj ajVar : a()) {
            try {
                ajVar.g(this.a, ahVar);
            } catch (Throwable th) {
                a(ajVar, th);
            }
        }
    }

    public void a(String str) {
        for (aj ajVar : a()) {
            try {
                ajVar.a(this.a, str);
            } catch (Throwable th) {
                a(ajVar, th);
            }
        }
    }

    public void a(byte[] bArr) {
        for (aj ajVar : a()) {
            try {
                ajVar.a(this.a, bArr);
            } catch (Throwable th) {
                a(ajVar, th);
            }
        }
    }

    public void h(ah ahVar) {
        for (aj ajVar : a()) {
            try {
                ajVar.h(this.a, ahVar);
            } catch (Throwable th) {
                a(ajVar, th);
            }
        }
    }

    public void i(ah ahVar) {
        for (aj ajVar : a()) {
            try {
                ajVar.i(this.a, ahVar);
            } catch (Throwable th) {
                a(ajVar, th);
            }
        }
    }

    public void j(ah ahVar) {
        for (aj ajVar : a()) {
            try {
                ajVar.j(this.a, ahVar);
            } catch (Throwable th) {
                a(ajVar, th);
            }
        }
    }

    public void a(ThreadType threadType, Thread thread) {
        for (aj ajVar : a()) {
            try {
                ajVar.a(this.a, threadType, thread);
            } catch (Throwable th) {
                a(ajVar, th);
            }
        }
    }

    public void b(ThreadType threadType, Thread thread) {
        for (aj ajVar : a()) {
            try {
                ajVar.b(this.a, threadType, thread);
            } catch (Throwable th) {
                a(ajVar, th);
            }
        }
    }

    public void c(ThreadType threadType, Thread thread) {
        for (aj ajVar : a()) {
            try {
                ajVar.c(this.a, threadType, thread);
            } catch (Throwable th) {
                a(ajVar, th);
            }
        }
    }

    public void a(WebSocketException webSocketException) {
        for (aj ajVar : a()) {
            try {
                ajVar.a(this.a, webSocketException);
            } catch (Throwable th) {
                a(ajVar, th);
            }
        }
    }

    public void a(WebSocketException webSocketException, ah ahVar) {
        for (aj ajVar : a()) {
            try {
                ajVar.a(this.a, webSocketException, ahVar);
            } catch (Throwable th) {
                a(ajVar, th);
            }
        }
    }

    public void a(WebSocketException webSocketException, List<ah> list) {
        for (aj ajVar : a()) {
            try {
                ajVar.a(this.a, webSocketException, (List) list);
            } catch (Throwable th) {
                a(ajVar, th);
            }
        }
    }

    public void a(WebSocketException webSocketException, byte[] bArr) {
        for (aj ajVar : a()) {
            try {
                ajVar.a(this.a, webSocketException, bArr);
            } catch (Throwable th) {
                a(ajVar, th);
            }
        }
    }

    public void b(WebSocketException webSocketException, byte[] bArr) {
        for (aj ajVar : a()) {
            try {
                ajVar.b(this.a, webSocketException, bArr);
            } catch (Throwable th) {
                a(ajVar, th);
            }
        }
    }

    public void b(WebSocketException webSocketException, ah ahVar) {
        for (aj ajVar : a()) {
            try {
                ajVar.b(this.a, webSocketException, ahVar);
            } catch (Throwable th) {
                a(ajVar, th);
            }
        }
    }

    public void b(WebSocketException webSocketException) {
        for (aj ajVar : a()) {
            try {
                ajVar.b(this.a, webSocketException);
            } catch (Throwable th) {
                a(ajVar, th);
            }
        }
    }

    private void a(aj ajVar, Throwable th) {
        try {
            ajVar.a(this.a, th);
        } catch (Throwable unused) {
        }
    }

    public void a(String str, List<String[]> list) {
        for (aj ajVar : a()) {
            try {
                ajVar.a(this.a, str, (List) list);
            } catch (Throwable th) {
                a(ajVar, th);
            }
        }
    }
}
