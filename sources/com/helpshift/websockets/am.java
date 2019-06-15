package com.helpshift.websockets;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

class am extends al {
    private final LinkedList<ah> b = new LinkedList();
    private final s c;
    private boolean d;
    private ah e;
    private boolean f;
    private boolean g;

    public am(ae aeVar) {
        super("WritingThread", aeVar, ThreadType.WRITING_THREAD);
        this.c = aeVar.q();
    }

    public void a() {
        try {
            d();
        } catch (Throwable th) {
            WebSocketError webSocketError = WebSocketError.UNEXPECTED_ERROR_IN_WRITING_THREAD;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("An uncaught throwable was detected in the writing thread: ");
            stringBuilder.append(th.getMessage());
            WebSocketException webSocketException = new WebSocketException(webSocketError, stringBuilder.toString(), th);
            o l = this.a.l();
            l.a(webSocketException);
            l.b(webSocketException);
        }
        synchronized (this) {
            this.g = true;
            notifyAll();
        }
        j();
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x001d */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Can't wrap try/catch for region: R(5:1|(3:3|(2:5|17)(2:6|(2:8|18)(5:9|10|11|12|19))|16)|13|14|20) */
    /* JADX WARNING: Missing block: B:21:?, code skipped:
            return;
     */
    private void d() {
        /*
        r3 = this;
        r0 = r3.a;
        r0.o();
    L_0x0005:
        r0 = r3.g();
        r1 = 1;
        if (r0 != r1) goto L_0x000d;
    L_0x000c:
        goto L_0x001d;
    L_0x000d:
        r2 = 3;
        if (r0 != r2) goto L_0x0014;
    L_0x0010:
        r3.e();
        goto L_0x0005;
    L_0x0014:
        r2 = 2;
        if (r0 != r2) goto L_0x0018;
    L_0x0017:
        goto L_0x0005;
    L_0x0018:
        r0 = 0;
        r3.a(r0);	 Catch:{ WebSocketException -> 0x001d }
        goto L_0x0005;
    L_0x001d:
        r3.a(r1);	 Catch:{ WebSocketException -> 0x0020 }
    L_0x0020:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.helpshift.websockets.am.d():void");
    }

    public void c() {
        synchronized (this) {
            this.d = true;
            notifyAll();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:1:0x0001 A:{LOOP_START, SYNTHETIC, LOOP:0: B:1:0x0001->B:35:0x0001} */
    /* JADX WARNING: Missing exception handler attribute for start block: B:1:0x0001 */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    public boolean a(com.helpshift.websockets.ah r3) {
        /*
        r2 = this;
        monitor-enter(r2);
    L_0x0001:
        r0 = r2.g;	 Catch:{ all -> 0x0043 }
        if (r0 == 0) goto L_0x0008;
    L_0x0005:
        r3 = 0;
        monitor-exit(r2);	 Catch:{ all -> 0x0043 }
        return r3;
    L_0x0008:
        r0 = r2.d;	 Catch:{ all -> 0x0043 }
        if (r0 != 0) goto L_0x002e;
    L_0x000c:
        r0 = r2.e;	 Catch:{ all -> 0x0043 }
        if (r0 == 0) goto L_0x0011;
    L_0x0010:
        goto L_0x002e;
    L_0x0011:
        r0 = r3.l();	 Catch:{ all -> 0x0043 }
        if (r0 == 0) goto L_0x0018;
    L_0x0017:
        goto L_0x002e;
    L_0x0018:
        r0 = r2.a;	 Catch:{ all -> 0x0043 }
        r0 = r0.e();	 Catch:{ all -> 0x0043 }
        if (r0 != 0) goto L_0x0021;
    L_0x0020:
        goto L_0x002e;
    L_0x0021:
        r1 = r2.b;	 Catch:{ all -> 0x0043 }
        r1 = r1.size();	 Catch:{ all -> 0x0043 }
        if (r1 >= r0) goto L_0x002a;
    L_0x0029:
        goto L_0x002e;
    L_0x002a:
        r2.wait();	 Catch:{ InterruptedException -> 0x0001 }
        goto L_0x0001;
    L_0x002e:
        r0 = b(r3);	 Catch:{ all -> 0x0043 }
        if (r0 == 0) goto L_0x0038;
    L_0x0034:
        r2.c(r3);	 Catch:{ all -> 0x0043 }
        goto L_0x003d;
    L_0x0038:
        r0 = r2.b;	 Catch:{ all -> 0x0043 }
        r0.addLast(r3);	 Catch:{ all -> 0x0043 }
    L_0x003d:
        r2.notifyAll();	 Catch:{ all -> 0x0043 }
        monitor-exit(r2);	 Catch:{ all -> 0x0043 }
        r3 = 1;
        return r3;
    L_0x0043:
        r3 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x0043 }
        throw r3;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.helpshift.websockets.am.a(com.helpshift.websockets.ah):boolean");
    }

    private static boolean b(ah ahVar) {
        return ahVar.j() || ahVar.k();
    }

    private void c(ah ahVar) {
        Iterator it = this.b.iterator();
        int i = 0;
        while (it.hasNext() && b((ah) it.next())) {
            i++;
        }
        this.b.add(i, ahVar);
    }

    private void e() {
        try {
            f();
        } catch (IOException unused) {
        }
    }

    private void f() throws IOException {
        this.a.j().flush();
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x0029  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0027 A:{SKIP} */
    /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x0023 */
    /* JADX WARNING: Can't wrap try/catch for region: R(5:10|(2:12|(3:14|15|16)(2:17|18))|19|20|(2:22|23)(2:24|(2:26|(3:28|29|30)(3:31|32|33))(2:34|35))) */
    private int g() {
        /*
        r4 = this;
        monitor-enter(r4);
        r0 = r4.d;	 Catch:{ all -> 0x003e }
        r1 = 1;
        if (r0 == 0) goto L_0x0008;
    L_0x0006:
        monitor-exit(r4);	 Catch:{ all -> 0x003e }
        return r1;
    L_0x0008:
        r0 = r4.e;	 Catch:{ all -> 0x003e }
        if (r0 == 0) goto L_0x000e;
    L_0x000c:
        monitor-exit(r4);	 Catch:{ all -> 0x003e }
        return r1;
    L_0x000e:
        r0 = r4.b;	 Catch:{ all -> 0x003e }
        r0 = r0.size();	 Catch:{ all -> 0x003e }
        r2 = 3;
        r3 = 0;
        if (r0 != 0) goto L_0x0023;
    L_0x0018:
        r0 = r4.f;	 Catch:{ all -> 0x003e }
        if (r0 == 0) goto L_0x0020;
    L_0x001c:
        r4.f = r3;	 Catch:{ all -> 0x003e }
        monitor-exit(r4);	 Catch:{ all -> 0x003e }
        return r2;
    L_0x0020:
        r4.wait();	 Catch:{ InterruptedException -> 0x0023 }
    L_0x0023:
        r0 = r4.d;	 Catch:{ all -> 0x003e }
        if (r0 == 0) goto L_0x0029;
    L_0x0027:
        monitor-exit(r4);	 Catch:{ all -> 0x003e }
        return r1;
    L_0x0029:
        r0 = r4.b;	 Catch:{ all -> 0x003e }
        r0 = r0.size();	 Catch:{ all -> 0x003e }
        if (r0 != 0) goto L_0x003c;
    L_0x0031:
        r0 = r4.f;	 Catch:{ all -> 0x003e }
        if (r0 == 0) goto L_0x0039;
    L_0x0035:
        r4.f = r3;	 Catch:{ all -> 0x003e }
        monitor-exit(r4);	 Catch:{ all -> 0x003e }
        return r2;
    L_0x0039:
        r0 = 2;
        monitor-exit(r4);	 Catch:{ all -> 0x003e }
        return r0;
    L_0x003c:
        monitor-exit(r4);	 Catch:{ all -> 0x003e }
        return r3;
    L_0x003e:
        r0 = move-exception;
        monitor-exit(r4);	 Catch:{ all -> 0x003e }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.helpshift.websockets.am.g():int");
    }

    /* JADX WARNING: Missing block: B:7:0x0017, code skipped:
            if (b(r5) == false) goto L_?;
     */
    /* JADX WARNING: Missing block: B:8:0x0019, code skipped:
            h();
     */
    /* JADX WARNING: Missing block: B:11:0x001e, code skipped:
            d(r2);
     */
    /* JADX WARNING: Missing block: B:12:0x0025, code skipped:
            if (r2.j() != false) goto L_0x003a;
     */
    /* JADX WARNING: Missing block: B:14:0x002b, code skipped:
            if (r2.k() == false) goto L_0x002e;
     */
    /* JADX WARNING: Missing block: B:16:0x0032, code skipped:
            if (b(r5) != false) goto L_0x0035;
     */
    /* JADX WARNING: Missing block: B:18:0x0035, code skipped:
            r0 = a(r0);
     */
    /* JADX WARNING: Missing block: B:19:0x003a, code skipped:
            h();
            r0 = java.lang.System.currentTimeMillis();
     */
    /* JADX WARNING: Missing block: B:32:?, code skipped:
            return;
     */
    /* JADX WARNING: Missing block: B:33:?, code skipped:
            return;
     */
    private void a(boolean r5) throws com.helpshift.websockets.WebSocketException {
        /*
        r4 = this;
        r0 = java.lang.System.currentTimeMillis();
    L_0x0004:
        monitor-enter(r4);
        r2 = r4.b;	 Catch:{ all -> 0x0042 }
        r2 = r2.poll();	 Catch:{ all -> 0x0042 }
        r2 = (com.helpshift.websockets.ah) r2;	 Catch:{ all -> 0x0042 }
        r4.notifyAll();	 Catch:{ all -> 0x0042 }
        if (r2 != 0) goto L_0x001d;
    L_0x0012:
        monitor-exit(r4);	 Catch:{ all -> 0x0042 }
        r5 = r4.b(r5);
        if (r5 == 0) goto L_0x001c;
    L_0x0019:
        r4.h();
    L_0x001c:
        return;
    L_0x001d:
        monitor-exit(r4);	 Catch:{ all -> 0x0042 }
        r4.d(r2);
        r3 = r2.j();
        if (r3 != 0) goto L_0x003a;
    L_0x0027:
        r2 = r2.k();
        if (r2 == 0) goto L_0x002e;
    L_0x002d:
        goto L_0x003a;
    L_0x002e:
        r2 = r4.b(r5);
        if (r2 != 0) goto L_0x0035;
    L_0x0034:
        goto L_0x0004;
    L_0x0035:
        r0 = r4.a(r0);
        goto L_0x0004;
    L_0x003a:
        r4.h();
        r0 = java.lang.System.currentTimeMillis();
        goto L_0x0004;
    L_0x0042:
        r5 = move-exception;
        monitor-exit(r4);	 Catch:{ all -> 0x0042 }
        throw r5;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.helpshift.websockets.am.a(boolean):void");
    }

    private boolean b(boolean z) {
        return z || this.a.c() || this.f || this.e != null;
    }

    private long a(long j) throws WebSocketException {
        long currentTimeMillis = System.currentTimeMillis();
        if (1000 >= currentTimeMillis - j) {
            return j;
        }
        h();
        return currentTimeMillis;
    }

    private void h() throws WebSocketException {
        try {
            f();
            synchronized (this) {
                this.f = false;
            }
        } catch (IOException e) {
            WebSocketError webSocketError = WebSocketError.FLUSH_ERROR;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Flushing frames to the server failed: ");
            stringBuilder.append(e.getMessage());
            WebSocketException webSocketException = new WebSocketException(webSocketError, stringBuilder.toString(), e);
            o l = this.a.l();
            l.a(webSocketException);
            l.b(webSocketException, null);
            throw webSocketException;
        }
    }

    private void d(ah ahVar) throws WebSocketException {
        Object obj;
        ahVar = ah.a(ahVar, this.c);
        this.a.l().h(ahVar);
        if (this.e != null) {
            obj = 1;
        } else {
            if (ahVar.i()) {
                this.e = ahVar;
            }
            obj = null;
        }
        if (obj != null) {
            this.a.l().j(ahVar);
            return;
        }
        if (ahVar.i()) {
            i();
        }
        try {
            this.a.j().a(ahVar);
            this.a.l().i(ahVar);
        } catch (IOException e) {
            WebSocketError webSocketError = WebSocketError.IO_ERROR_IN_WRITING;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("An I/O error occurred when a frame was tried to be sent: ");
            stringBuilder.append(e.getMessage());
            WebSocketException webSocketException = new WebSocketException(webSocketError, stringBuilder.toString(), e);
            o l = this.a.l();
            l.a(webSocketException);
            l.b(webSocketException, ahVar);
            throw webSocketException;
        }
    }

    private void i() {
        Object obj;
        StateManager k = this.a.k();
        synchronized (k) {
            WebSocketState a = k.a();
            if (a == WebSocketState.CLOSING || a == WebSocketState.CLOSED) {
                obj = null;
            } else {
                k.a(CloseInitiator.CLIENT);
                obj = 1;
            }
        }
        if (obj != null) {
            this.a.l().a(WebSocketState.CLOSING);
        }
    }

    private void j() {
        this.a.c(this.e);
    }
}
