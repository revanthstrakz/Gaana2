package com.helpshift.websockets;

import android.support.v4.view.PointerIconCompat;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

class z extends al {
    private boolean b;
    private ah c;
    private List<ah> d = new ArrayList();
    private final s e;
    private Object f = new Object();
    private Timer g;
    private a h;
    private long i;
    private boolean j;

    private class a extends TimerTask {
        a() {
        }

        public void run() {
            try {
                z.this.a.f().close();
            } catch (Throwable unused) {
            }
        }
    }

    public z(ae aeVar) {
        super("ReadingThread", aeVar, ThreadType.READING_THREAD);
        this.e = aeVar.q();
    }

    public void a() {
        try {
            c();
        } catch (Throwable th) {
            WebSocketError webSocketError = WebSocketError.UNEXPECTED_ERROR_IN_READING_THREAD;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("An uncaught throwable was detected in the reading thread: ");
            stringBuilder.append(th.getMessage());
            WebSocketException webSocketException = new WebSocketException(webSocketError, stringBuilder.toString(), th);
            o l = this.a.l();
            l.a(webSocketException);
            l.b(webSocketException);
        }
        this.a.b(this.c);
    }

    private void c() {
        this.a.n();
        ah d;
        do {
            synchronized (this) {
                if (this.b) {
                    break;
                }
                d = d();
                if (d == null) {
                    break;
                }
            }
        } while (k(d));
        e();
        h();
    }

    /* Access modifiers changed, original: 0000 */
    public void a(long j) {
        synchronized (this) {
            if (this.b) {
                return;
            }
            this.b = true;
            interrupt();
            this.i = j;
            f();
        }
    }

    private void a(byte[] bArr) {
        try {
            a(p.a(bArr));
        } catch (Throwable th) {
            WebSocketError webSocketError = WebSocketError.TEXT_MESSAGE_CONSTRUCTION_ERROR;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Failed to convert payload data into a string: ");
            stringBuilder.append(th.getMessage());
            WebSocketException webSocketException = new WebSocketException(webSocketError, stringBuilder.toString(), th);
            a(webSocketException);
            this.a.l().b(webSocketException, bArr);
        }
    }

    private void a(String str) {
        this.a.l().a(str);
    }

    private void b(byte[] bArr) {
        this.a.l().a(bArr);
    }

    private void a(WebSocketException webSocketException) {
        this.a.l().a(webSocketException);
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x006b  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0078  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x006b  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0078  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x004a  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0049 A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x006b  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0078  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x006b  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0078  */
    private com.helpshift.websockets.ah d() {
        /*
        r7 = this;
        r0 = 0;
        r1 = r7.a;	 Catch:{ InterruptedIOException -> 0x0043, IOException -> 0x0019, WebSocketException -> 0x0015 }
        r1 = r1.i();	 Catch:{ InterruptedIOException -> 0x0043, IOException -> 0x0019, WebSocketException -> 0x0015 }
        r1 = r1.b();	 Catch:{ InterruptedIOException -> 0x0043, IOException -> 0x0019, WebSocketException -> 0x0015 }
        r7.a(r1);	 Catch:{ InterruptedIOException -> 0x0013, IOException -> 0x0011, WebSocketException -> 0x000f }
        return r1;
    L_0x000f:
        r2 = move-exception;
        goto L_0x0017;
    L_0x0011:
        r2 = move-exception;
        goto L_0x001b;
    L_0x0013:
        r2 = move-exception;
        goto L_0x0045;
    L_0x0015:
        r2 = move-exception;
        r1 = r0;
    L_0x0017:
        r3 = r2;
        goto L_0x0066;
    L_0x0019:
        r2 = move-exception;
        r1 = r0;
    L_0x001b:
        r3 = r7.b;
        if (r3 == 0) goto L_0x0026;
    L_0x001f:
        r3 = r7.isInterrupted();
        if (r3 == 0) goto L_0x0026;
    L_0x0025:
        return r0;
    L_0x0026:
        r3 = new com.helpshift.websockets.WebSocketException;
        r4 = com.helpshift.websockets.WebSocketError.IO_ERROR_IN_READING;
        r5 = new java.lang.StringBuilder;
        r5.<init>();
        r6 = "An I/O error occurred while a frame was being read from the web socket: ";
        r5.append(r6);
        r6 = r2.getMessage();
        r5.append(r6);
        r5 = r5.toString();
        r3.<init>(r4, r5, r2);
        goto L_0x0066;
    L_0x0043:
        r2 = move-exception;
        r1 = r0;
    L_0x0045:
        r3 = r7.b;
        if (r3 == 0) goto L_0x004a;
    L_0x0049:
        return r0;
    L_0x004a:
        r3 = new com.helpshift.websockets.WebSocketException;
        r4 = com.helpshift.websockets.WebSocketError.INTERRUPTED_IN_READING;
        r5 = new java.lang.StringBuilder;
        r5.<init>();
        r6 = "Interruption occurred while a frame was being read from the web socket: ";
        r5.append(r6);
        r6 = r2.getMessage();
        r5.append(r6);
        r5 = r5.toString();
        r3.<init>(r4, r5, r2);
    L_0x0066:
        r2 = r3 instanceof com.helpshift.websockets.NoMoreFrameException;
        r4 = 1;
        if (r2 == 0) goto L_0x0076;
    L_0x006b:
        r7.j = r4;
        r2 = r7.a;
        r2 = r2.d();
        if (r2 == 0) goto L_0x0076;
    L_0x0075:
        r4 = 0;
    L_0x0076:
        if (r4 == 0) goto L_0x0084;
    L_0x0078:
        r7.a(r3);
        r2 = r7.a;
        r2 = r2.l();
        r2.a(r3, r1);
    L_0x0084:
        r1 = r7.b(r3);
        r2 = r7.a;
        r2.a(r1);
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.helpshift.websockets.z.d():com.helpshift.websockets.ah");
    }

    private void a(ah ahVar) throws WebSocketException {
        b(ahVar);
        g(ahVar);
        h(ahVar);
        i(ahVar);
        j(ahVar);
    }

    private void b(ah ahVar) throws WebSocketException {
        if (!this.a.b()) {
            c(ahVar);
            e(ahVar);
            f(ahVar);
        }
    }

    private void c(ah ahVar) throws WebSocketException {
        if ((this.e == null || !d(ahVar)) && ahVar.b()) {
            throw new WebSocketException(WebSocketError.UNEXPECTED_RESERVED_BIT, "The RSV1 bit of a frame is set unexpectedly.");
        }
    }

    private boolean d(ah ahVar) throws WebSocketException {
        return ahVar.g() || ahVar.h();
    }

    private void e(ah ahVar) throws WebSocketException {
        if (ahVar.c()) {
            throw new WebSocketException(WebSocketError.UNEXPECTED_RESERVED_BIT, "The RSV2 bit of a frame is set unexpectedly.");
        }
    }

    private void f(ah ahVar) throws WebSocketException {
        if (ahVar.d()) {
            throw new WebSocketException(WebSocketError.UNEXPECTED_RESERVED_BIT, "The RSV3 bit of a frame is set unexpectedly.");
        }
    }

    private void g(ah ahVar) throws WebSocketException {
        int e = ahVar.e();
        switch (e) {
            case 0:
            case 1:
            case 2:
                break;
            default:
                switch (e) {
                    case 8:
                    case 9:
                    case 10:
                        break;
                    default:
                        if (!this.a.b()) {
                            WebSocketError webSocketError = WebSocketError.UNKNOWN_OPCODE;
                            StringBuilder stringBuilder = new StringBuilder();
                            stringBuilder.append("A frame has an unknown opcode: 0x");
                            stringBuilder.append(Integer.toHexString(ahVar.e()));
                            throw new WebSocketException(webSocketError, stringBuilder.toString());
                        }
                        return;
                }
        }
    }

    private void h(ah ahVar) throws WebSocketException {
        if (ahVar.m()) {
            throw new WebSocketException(WebSocketError.FRAME_MASKED, "A frame from the server is masked.");
        }
    }

    private void i(ah ahVar) throws WebSocketException {
        if (!ahVar.l()) {
            Object obj = this.d.size() != 0 ? 1 : null;
            if (ahVar.f()) {
                if (obj == null) {
                    throw new WebSocketException(WebSocketError.UNEXPECTED_CONTINUATION_FRAME, "A continuation frame was detected although a continuation had not started.");
                }
            } else if (obj != null) {
                throw new WebSocketException(WebSocketError.CONTINUATION_NOT_CLOSED, "A non-control frame was detected although the existing continuation had not been closed.");
            }
        } else if (!ahVar.a()) {
            throw new WebSocketException(WebSocketError.FRAGMENTED_CONTROL_FRAME, "A control frame is fragmented.");
        }
    }

    private void j(ah ahVar) throws WebSocketException {
        if (ahVar.l()) {
            byte[] o = ahVar.o();
            if (o != null && 125 < o.length) {
                WebSocketError webSocketError = WebSocketError.TOO_LONG_CONTROL_FRAME_PAYLOAD;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("The payload size of a control frame exceeds the maximum size (125 bytes): ");
                stringBuilder.append(o.length);
                throw new WebSocketException(webSocketError, stringBuilder.toString());
            }
        }
    }

    private ah b(WebSocketException webSocketException) {
        int i = 1002;
        switch (webSocketException.b()) {
            case INSUFFICENT_DATA:
            case INVALID_PAYLOAD_LENGTH:
            case NO_MORE_FRAME:
            case NON_ZERO_RESERVED_BITS:
            case UNEXPECTED_RESERVED_BIT:
            case UNKNOWN_OPCODE:
            case FRAME_MASKED:
            case FRAGMENTED_CONTROL_FRAME:
            case UNEXPECTED_CONTINUATION_FRAME:
            case CONTINUATION_NOT_CLOSED:
            case TOO_LONG_CONTROL_FRAME_PAYLOAD:
                break;
            case TOO_LONG_PAYLOAD:
            case INSUFFICIENT_MEMORY_FOR_PAYLOAD:
                i = PointerIconCompat.TYPE_VERTICAL_TEXT;
                break;
            default:
                i = PointerIconCompat.TYPE_TEXT;
                break;
        }
        return ah.b(i, webSocketException.getMessage());
    }

    private boolean k(ah ahVar) {
        this.a.l().a(ahVar);
        int e = ahVar.e();
        switch (e) {
            case 0:
                return l(ahVar);
            case 1:
                return n(ahVar);
            case 2:
                return o(ahVar);
            default:
                switch (e) {
                    case 8:
                        return p(ahVar);
                    case 9:
                        return q(ahVar);
                    case 10:
                        return r(ahVar);
                    default:
                        return true;
                }
        }
    }

    private boolean l(ah ahVar) {
        this.a.l().b(ahVar);
        this.d.add(ahVar);
        if (!ahVar.a()) {
            return true;
        }
        byte[] a = a(this.d);
        if (a == null) {
            return false;
        }
        if (((ah) this.d.get(0)).g()) {
            a(a);
        } else {
            b(a);
        }
        this.d.clear();
        return true;
    }

    private byte[] a(List<ah> list) {
        byte[] b = b(this.d);
        if (b == null) {
            return null;
        }
        if (this.e != null && ((ah) list.get(0)).b()) {
            b = c(b);
        }
        return b;
    }

    private byte[] b(List<ah> list) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            for (ah o : list) {
                byte[] o2 = o.o();
                if (o2 != null) {
                    if (o2.length != 0) {
                        byteArrayOutputStream.write(o2);
                    }
                }
            }
            return byteArrayOutputStream.toByteArray();
        } catch (IOException | OutOfMemoryError e) {
            WebSocketError webSocketError = WebSocketError.MESSAGE_CONSTRUCTION_ERROR;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Failed to concatenate payloads of multiple frames to construct a message: ");
            stringBuilder.append(e.getMessage());
            WebSocketException webSocketException = new WebSocketException(webSocketError, stringBuilder.toString(), e);
            a(webSocketException);
            this.a.l().a(webSocketException, (List) list);
            this.a.a(ah.b(PointerIconCompat.TYPE_VERTICAL_TEXT, webSocketException.getMessage()));
            return null;
        }
    }

    private byte[] m(ah ahVar) {
        byte[] o = ahVar.o();
        return (this.e == null || !ahVar.b()) ? o : c(o);
    }

    private byte[] c(byte[] bArr) {
        try {
            return this.e.a(bArr);
        } catch (WebSocketException e) {
            a(e);
            this.a.l().a(e, bArr);
            this.a.a(ah.b(1003, e.getMessage()));
            return null;
        }
    }

    private boolean n(ah ahVar) {
        this.a.l().c(ahVar);
        if (ahVar.a()) {
            a(m(ahVar));
            return true;
        }
        this.d.add(ahVar);
        return true;
    }

    private boolean o(ah ahVar) {
        this.a.l().d(ahVar);
        if (ahVar.a()) {
            b(m(ahVar));
            return true;
        }
        this.d.add(ahVar);
        return true;
    }

    private boolean p(ah ahVar) {
        boolean z;
        StateManager k = this.a.k();
        this.c = ahVar;
        synchronized (k) {
            WebSocketState a = k.a();
            if (a == WebSocketState.CLOSING || a == WebSocketState.CLOSED) {
                z = false;
            } else {
                k.a(CloseInitiator.SERVER);
                this.a.a(ahVar);
                z = true;
            }
        }
        if (z) {
            this.a.l().a(WebSocketState.CLOSING);
        }
        this.a.l().e(ahVar);
        return false;
    }

    private boolean q(ah ahVar) {
        this.a.l().f(ahVar);
        this.a.a(ah.d(ahVar.o()));
        return true;
    }

    private boolean r(ah ahVar) {
        this.a.l().g(ahVar);
        return true;
    }

    private void e() {
        if (!this.j && this.c == null) {
            f();
            do {
                try {
                    ah b = this.a.i().b();
                    if (b.i()) {
                        this.c = b;
                        break;
                    }
                } catch (Throwable unused) {
                }
            } while (!isInterrupted());
        }
    }

    private void f() {
        synchronized (this.f) {
            i();
            g();
        }
    }

    private void g() {
        this.h = new a();
        this.g = new Timer("ReadingThreadCloseTimer");
        this.g.schedule(this.h, this.i);
    }

    private void h() {
        synchronized (this.f) {
            i();
        }
    }

    private void i() {
        if (this.g != null) {
            this.g.cancel();
            this.g = null;
        }
        if (this.h != null) {
            this.h.cancel();
            this.h = null;
        }
    }
}
