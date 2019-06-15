package com.helpshift.websockets;

import com.comscore.streaming.Constants;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.List;
import java.util.Map;

public class ae {
    private ah A;
    private ah B;
    private s C;
    private final ag a;
    private final aa b;
    private final StateManager c;
    private l d;
    private final o e;
    private final v f;
    private final w g;
    private final Object h = new Object();
    private ai i;
    private ak j;
    private z k;
    private am l;
    private Map<String, List<String>> m;
    private List<af> n;
    private String o;
    private boolean p;
    private boolean q = true;
    private boolean r = true;
    private int s;
    private int t;
    private boolean u;
    private Object v = new Object();
    private boolean w;
    private boolean x;
    private boolean y;
    private boolean z;

    ae(ag agVar, boolean z, String str, String str2, String str3, aa aaVar) {
        this.a = agVar;
        this.b = aaVar;
        this.c = new StateManager();
        this.d = new l(z, str, str2, str3);
        this.e = new o(this);
        this.f = new v(this, new d());
        this.g = new w(this, new d());
    }

    /* Access modifiers changed, original: protected */
    public void finalize() throws Throwable {
        if (a(WebSocketState.CREATED)) {
            p();
        }
        super.finalize();
    }

    public boolean a() {
        return a(WebSocketState.OPEN);
    }

    private boolean a(WebSocketState webSocketState) {
        boolean z;
        synchronized (this.c) {
            z = this.c.a() == webSocketState;
        }
        return z;
    }

    public ae a(String str) {
        this.d.a(str);
        return this;
    }

    public ae b(String str) {
        this.d.c(str);
        return this;
    }

    public ae a(String str, String str2) {
        this.d.a(str, str2);
        return this;
    }

    public boolean b() {
        return this.p;
    }

    public boolean c() {
        return this.q;
    }

    public boolean d() {
        return this.r;
    }

    public int e() {
        return this.s;
    }

    public ae a(aj ajVar) {
        this.e.a(ajVar);
        return this;
    }

    public Socket f() {
        return this.b.a();
    }

    public ae g() throws WebSocketException {
        r();
        try {
            this.b.b();
            this.m = s();
            this.C = y();
            this.c.a(WebSocketState.OPEN);
            this.e.a(WebSocketState.OPEN);
            t();
            return this;
        } catch (WebSocketException e) {
            this.b.c();
            this.c.a(WebSocketState.CLOSED);
            this.e.a(WebSocketState.CLOSED);
            throw e;
        }
    }

    public ae h() {
        return a(1000, null);
    }

    public ae a(int i, String str) {
        return a(i, str, Constants.HEARTBEAT_STAGE_ONE_INTERVAL);
    }

    /* JADX WARNING: Missing block: B:9:0x0025, code skipped:
            r3.e.a(com.helpshift.websockets.WebSocketState.CLOSING);
     */
    /* JADX WARNING: Missing block: B:10:0x0030, code skipped:
            if (r6 >= 0) goto L_0x0034;
     */
    /* JADX WARNING: Missing block: B:11:0x0032, code skipped:
            r6 = com.comscore.streaming.Constants.HEARTBEAT_STAGE_ONE_INTERVAL;
     */
    /* JADX WARNING: Missing block: B:12:0x0034, code skipped:
            a(r6);
     */
    /* JADX WARNING: Missing block: B:13:0x0037, code skipped:
            return r3;
     */
    public com.helpshift.websockets.ae a(int r4, java.lang.String r5, long r6) {
        /*
        r3 = this;
        r0 = r3.c;
        monitor-enter(r0);
        r1 = com.helpshift.websockets.ae.AnonymousClass1.a;	 Catch:{ all -> 0x003e }
        r2 = r3.c;	 Catch:{ all -> 0x003e }
        r2 = r2.a();	 Catch:{ all -> 0x003e }
        r2 = r2.ordinal();	 Catch:{ all -> 0x003e }
        r1 = r1[r2];	 Catch:{ all -> 0x003e }
        switch(r1) {
            case 1: goto L_0x0038;
            case 2: goto L_0x0016;
            default: goto L_0x0014;
        };	 Catch:{ all -> 0x003e }
    L_0x0014:
        monitor-exit(r0);	 Catch:{ all -> 0x003e }
        goto L_0x003d;
    L_0x0016:
        r1 = r3.c;	 Catch:{ all -> 0x003e }
        r2 = com.helpshift.websockets.StateManager.CloseInitiator.CLIENT;	 Catch:{ all -> 0x003e }
        r1.a(r2);	 Catch:{ all -> 0x003e }
        r4 = com.helpshift.websockets.ah.b(r4, r5);	 Catch:{ all -> 0x003e }
        r3.a(r4);	 Catch:{ all -> 0x003e }
        monitor-exit(r0);	 Catch:{ all -> 0x003e }
        r4 = r3.e;
        r5 = com.helpshift.websockets.WebSocketState.CLOSING;
        r4.a(r5);
        r4 = 0;
        r0 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1));
        if (r0 >= 0) goto L_0x0034;
    L_0x0032:
        r6 = 10000; // 0x2710 float:1.4013E-41 double:4.9407E-320;
    L_0x0034:
        r3.a(r6);
        return r3;
    L_0x0038:
        r3.x();	 Catch:{ all -> 0x003e }
        monitor-exit(r0);	 Catch:{ all -> 0x003e }
        return r3;
    L_0x003d:
        return r3;
    L_0x003e:
        r4 = move-exception;
        monitor-exit(r0);	 Catch:{ all -> 0x003e }
        throw r4;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.helpshift.websockets.ae.a(int, java.lang.String, long):com.helpshift.websockets.ae");
    }

    /* JADX WARNING: Missing block: B:12:0x0017, code skipped:
            r0 = r3.l;
     */
    /* JADX WARNING: Missing block: B:13:0x0019, code skipped:
            if (r0 != null) goto L_0x001c;
     */
    /* JADX WARNING: Missing block: B:14:0x001b, code skipped:
            return r3;
     */
    /* JADX WARNING: Missing block: B:15:0x001c, code skipped:
            r1 = d(r4);
     */
    /* JADX WARNING: Missing block: B:16:0x0020, code skipped:
            if (r1 != null) goto L_0x0026;
     */
    /* JADX WARNING: Missing block: B:17:0x0022, code skipped:
            r0.a(r4);
     */
    /* JADX WARNING: Missing block: B:18:0x0026, code skipped:
            r4 = r1.iterator();
     */
    /* JADX WARNING: Missing block: B:20:0x002e, code skipped:
            if (r4.hasNext() == false) goto L_0x003a;
     */
    /* JADX WARNING: Missing block: B:21:0x0030, code skipped:
            r0.a((com.helpshift.websockets.ah) r4.next());
     */
    /* JADX WARNING: Missing block: B:22:0x003a, code skipped:
            return r3;
     */
    public com.helpshift.websockets.ae a(com.helpshift.websockets.ah r4) {
        /*
        r3 = this;
        if (r4 != 0) goto L_0x0003;
    L_0x0002:
        return r3;
    L_0x0003:
        r0 = r3.c;
        monitor-enter(r0);
        r1 = r3.c;	 Catch:{ all -> 0x003b }
        r1 = r1.a();	 Catch:{ all -> 0x003b }
        r2 = com.helpshift.websockets.WebSocketState.OPEN;	 Catch:{ all -> 0x003b }
        if (r1 == r2) goto L_0x0016;
    L_0x0010:
        r2 = com.helpshift.websockets.WebSocketState.CLOSING;	 Catch:{ all -> 0x003b }
        if (r1 == r2) goto L_0x0016;
    L_0x0014:
        monitor-exit(r0);	 Catch:{ all -> 0x003b }
        return r3;
    L_0x0016:
        monitor-exit(r0);	 Catch:{ all -> 0x003b }
        r0 = r3.l;
        if (r0 != 0) goto L_0x001c;
    L_0x001b:
        return r3;
    L_0x001c:
        r1 = r3.d(r4);
        if (r1 != 0) goto L_0x0026;
    L_0x0022:
        r0.a(r4);
        goto L_0x003a;
    L_0x0026:
        r4 = r1.iterator();
    L_0x002a:
        r1 = r4.hasNext();
        if (r1 == 0) goto L_0x003a;
    L_0x0030:
        r1 = r4.next();
        r1 = (com.helpshift.websockets.ah) r1;
        r0.a(r1);
        goto L_0x002a;
    L_0x003a:
        return r3;
    L_0x003b:
        r4 = move-exception;
        monitor-exit(r0);	 Catch:{ all -> 0x003b }
        throw r4;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.helpshift.websockets.ae.a(com.helpshift.websockets.ah):com.helpshift.websockets.ae");
    }

    private List<ah> d(ah ahVar) {
        return ah.a(ahVar, this.t, this.C);
    }

    public ae c(String str) {
        return a(ah.b(str));
    }

    private void r() throws WebSocketException {
        synchronized (this.c) {
            if (this.c.a() != WebSocketState.CREATED) {
                throw new WebSocketException(WebSocketError.NOT_IN_CREATED_STATE, "The current state of the WebSocket is not CREATED.");
            }
            this.c.a(WebSocketState.CONNECTING);
        }
        this.e.a(WebSocketState.CONNECTING);
    }

    private Map<String, List<String>> s() throws WebSocketException {
        Socket a = this.b.a();
        ai a2 = a(a);
        ak b = b(a);
        byte[] bArr = new byte[16];
        p.b(bArr);
        String a3 = b.a(bArr);
        a(b, a3);
        Map a4 = a(a2, a3);
        this.i = a2;
        this.j = b;
        return a4;
    }

    private ai a(Socket socket) throws WebSocketException {
        try {
            return new ai(new BufferedInputStream(socket.getInputStream()));
        } catch (IOException e) {
            WebSocketError webSocketError = WebSocketError.SOCKET_INPUT_STREAM_FAILURE;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Failed to get the input stream of the raw socket: ");
            stringBuilder.append(e.getMessage());
            throw new WebSocketException(webSocketError, stringBuilder.toString(), e);
        }
    }

    private ak b(Socket socket) throws WebSocketException {
        try {
            return new ak(new BufferedOutputStream(socket.getOutputStream()));
        } catch (IOException e) {
            WebSocketError webSocketError = WebSocketError.SOCKET_OUTPUT_STREAM_FAILURE;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Failed to get the output stream from the raw socket: ");
            stringBuilder.append(e.getMessage());
            throw new WebSocketException(webSocketError, stringBuilder.toString(), e);
        }
    }

    private void a(ak akVar, String str) throws WebSocketException {
        this.d.e(str);
        str = this.d.a();
        List b = this.d.b();
        String a = l.a(str, b);
        this.e.a(str, b);
        try {
            akVar.a(a);
            akVar.flush();
        } catch (IOException e) {
            WebSocketError webSocketError = WebSocketError.OPENING_HAHDSHAKE_REQUEST_FAILURE;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Failed to send an opening handshake request to the server: ");
            stringBuilder.append(e.getMessage());
            throw new WebSocketException(webSocketError, stringBuilder.toString(), e);
        }
    }

    private Map<String, List<String>> a(ai aiVar, String str) throws WebSocketException {
        return new m(this).a(aiVar, str);
    }

    private void t() {
        z zVar = new z(this);
        am amVar = new am(this);
        synchronized (this.h) {
            this.k = zVar;
            this.l = amVar;
        }
        zVar.b();
        amVar.b();
        zVar.start();
        amVar.start();
    }

    private void a(long j) {
        z zVar;
        am amVar;
        synchronized (this.h) {
            zVar = this.k;
            amVar = this.l;
            this.k = null;
            this.l = null;
        }
        if (zVar != null) {
            zVar.a(j);
        }
        if (amVar != null) {
            amVar.c();
        }
    }

    /* Access modifiers changed, original: 0000 */
    public ai i() {
        return this.i;
    }

    /* Access modifiers changed, original: 0000 */
    public ak j() {
        return this.j;
    }

    /* Access modifiers changed, original: 0000 */
    public StateManager k() {
        return this.c;
    }

    /* Access modifiers changed, original: 0000 */
    public o l() {
        return this.e;
    }

    /* Access modifiers changed, original: 0000 */
    public l m() {
        return this.d;
    }

    /* Access modifiers changed, original: 0000 */
    public void a(List<af> list) {
        this.n = list;
    }

    /* Access modifiers changed, original: 0000 */
    public void d(String str) {
        this.o = str;
    }

    /* Access modifiers changed, original: 0000 */
    public void n() {
        boolean z;
        synchronized (this.h) {
            this.w = true;
            z = this.x;
        }
        u();
        if (z) {
            v();
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void o() {
        boolean z;
        synchronized (this.h) {
            this.x = true;
            z = this.w;
        }
        u();
        if (z) {
            v();
        }
    }

    private void u() {
        synchronized (this.v) {
            if (this.u) {
                return;
            }
            this.u = true;
            this.e.a(this.m);
        }
    }

    private void v() {
        this.f.a();
        this.g.a();
    }

    /* Access modifiers changed, original: 0000 */
    public void b(ah ahVar) {
        synchronized (this.h) {
            this.y = true;
            this.A = ahVar;
            if (this.z) {
                w();
                return;
            }
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void c(ah ahVar) {
        synchronized (this.h) {
            this.z = true;
            this.B = ahVar;
            if (this.y) {
                w();
                return;
            }
        }
    }

    private void w() {
        p();
    }

    /* Access modifiers changed, original: 0000 */
    public void p() {
        this.f.b();
        this.g.b();
        try {
            this.b.a().close();
        } catch (Throwable unused) {
        }
        synchronized (this.c) {
            this.c.a(WebSocketState.CLOSED);
        }
        this.e.a(WebSocketState.CLOSED);
        this.e.a(this.A, this.B, this.c.b());
    }

    private void x() {
        i iVar = new i(this);
        iVar.b();
        iVar.start();
    }

    private s y() {
        if (this.n == null) {
            return null;
        }
        for (af afVar : this.n) {
            if (afVar instanceof s) {
                return (s) afVar;
            }
        }
        return null;
    }

    /* Access modifiers changed, original: 0000 */
    public s q() {
        return this.C;
    }
}
