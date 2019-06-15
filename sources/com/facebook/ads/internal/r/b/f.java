package com.facebook.ads.internal.r.b;

import android.content.Context;
import android.os.SystemClock;
import android.util.Log;
import com.facebook.ads.internal.r.b.a.g;
import com.google.api.client.http.HttpStatusCodes;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Arrays;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class f {
    private final Object a;
    private final ExecutorService b;
    private final Map<String, g> c;
    private final ServerSocket d;
    private final int e;
    private final Thread f;
    private final c g;
    private boolean h;

    public static final class a {
        private File a;
        private com.facebook.ads.internal.r.b.a.c b = new com.facebook.ads.internal.r.b.a.f();
        private com.facebook.ads.internal.r.b.a.a c = new g(67108864);

        public a(Context context) {
            this.a = o.a(context);
        }

        private c a() {
            return new c(this.a, this.b, this.c);
        }
    }

    private class b implements Callable<Boolean> {
        private b() {
        }

        /* renamed from: a */
        public Boolean call() {
            return Boolean.valueOf(f.this.c());
        }
    }

    private class c implements Callable<Boolean> {
        private final String b;

        public c(String str) {
            this.b = str;
        }

        /* renamed from: a */
        public Boolean call() {
            return Boolean.valueOf(f.this.c(this.b));
        }
    }

    private final class d implements Runnable {
        private final Socket b;

        public d(Socket socket) {
            this.b = socket;
        }

        public void run() {
            f.this.a(this.b);
        }
    }

    private final class e implements Runnable {
        private final CountDownLatch b;

        public e(CountDownLatch countDownLatch) {
            this.b = countDownLatch;
        }

        public void run() {
            this.b.countDown();
            f.this.e();
        }
    }

    public f(Context context) {
        this(new a(context).a());
    }

    private f(c cVar) {
        this.a = new Object();
        this.b = Executors.newFixedThreadPool(8);
        this.c = new ConcurrentHashMap();
        this.g = (c) j.a(cVar);
        try {
            this.d = new ServerSocket(0, 8, InetAddress.getByName("127.0.0.1"));
            this.e = this.d.getLocalPort();
            CountDownLatch countDownLatch = new CountDownLatch(1);
            this.f = new Thread(new e(countDownLatch));
            this.f.start();
            countDownLatch.await();
            Log.i("ProxyCache", "Proxy cache server started. Ping it...");
            b();
        } catch (IOException | InterruptedException e) {
            this.b.shutdown();
            throw new IllegalStateException("Error starting local proxy server", e);
        }
    }

    private void a(Throwable th) {
        Log.e("ProxyCache", "HttpProxyCacheServer error", th);
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x005a */
    private void a(java.net.Socket r5) {
        /*
        r4 = this;
        r0 = r5.getInputStream();	 Catch:{ SocketException -> 0x005a, l | IOException -> 0x0044, l | IOException -> 0x0044 }
        r0 = com.facebook.ads.internal.r.b.d.a(r0);	 Catch:{ SocketException -> 0x005a, l | IOException -> 0x0044, l | IOException -> 0x0044 }
        r1 = "ProxyCache";
        r2 = new java.lang.StringBuilder;	 Catch:{ SocketException -> 0x005a, l | IOException -> 0x0044, l | IOException -> 0x0044 }
        r2.<init>();	 Catch:{ SocketException -> 0x005a, l | IOException -> 0x0044, l | IOException -> 0x0044 }
        r3 = "Request to cache proxy:";
        r2.append(r3);	 Catch:{ SocketException -> 0x005a, l | IOException -> 0x0044, l | IOException -> 0x0044 }
        r2.append(r0);	 Catch:{ SocketException -> 0x005a, l | IOException -> 0x0044, l | IOException -> 0x0044 }
        r2 = r2.toString();	 Catch:{ SocketException -> 0x005a, l | IOException -> 0x0044, l | IOException -> 0x0044 }
        android.util.Log.i(r1, r2);	 Catch:{ SocketException -> 0x005a, l | IOException -> 0x0044, l | IOException -> 0x0044 }
        r1 = r0.a;	 Catch:{ SocketException -> 0x005a, l | IOException -> 0x0044, l | IOException -> 0x0044 }
        r1 = com.facebook.ads.internal.r.b.m.c(r1);	 Catch:{ SocketException -> 0x005a, l | IOException -> 0x0044, l | IOException -> 0x0044 }
        r2 = "ping";
        r2 = r2.equals(r1);	 Catch:{ SocketException -> 0x005a, l | IOException -> 0x0044, l | IOException -> 0x0044 }
        if (r2 == 0) goto L_0x0030;
    L_0x002c:
        r4.b(r5);	 Catch:{ SocketException -> 0x005a, l | IOException -> 0x0044, l | IOException -> 0x0044 }
        goto L_0x0037;
    L_0x0030:
        r1 = r4.e(r1);	 Catch:{ SocketException -> 0x005a, l | IOException -> 0x0044, l | IOException -> 0x0044 }
        r1.a(r0, r5);	 Catch:{ SocketException -> 0x005a, l | IOException -> 0x0044, l | IOException -> 0x0044 }
    L_0x0037:
        r4.c(r5);
        r5 = "ProxyCache";
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        goto L_0x006b;
    L_0x0042:
        r0 = move-exception;
        goto L_0x007f;
    L_0x0044:
        r0 = move-exception;
        r1 = new com.facebook.ads.internal.r.b.l;	 Catch:{ all -> 0x0042 }
        r2 = "Error processing request";
        r1.<init>(r2, r0);	 Catch:{ all -> 0x0042 }
        r4.a(r1);	 Catch:{ all -> 0x0042 }
        r4.c(r5);
        r5 = "ProxyCache";
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        goto L_0x006b;
    L_0x005a:
        r0 = "ProxyCache";
        r1 = "Closing socket... Socket is closed by client.";
        android.util.Log.d(r0, r1);	 Catch:{ all -> 0x0042 }
        r4.c(r5);
        r5 = "ProxyCache";
        r0 = new java.lang.StringBuilder;
        r0.<init>();
    L_0x006b:
        r1 = "Opened connections: ";
        r0.append(r1);
        r1 = r4.f();
        r0.append(r1);
        r0 = r0.toString();
        android.util.Log.d(r5, r0);
        return;
    L_0x007f:
        r4.c(r5);
        r5 = "ProxyCache";
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "Opened connections: ";
        r1.append(r2);
        r2 = r4.f();
        r1.append(r2);
        r1 = r1.toString();
        android.util.Log.d(r5, r1);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.ads.internal.r.b.f.a(java.net.Socket):void");
    }

    private void b() {
        int i = HttpStatusCodes.STATUS_CODE_MULTIPLE_CHOICES;
        int i2 = 0;
        while (i2 < 3) {
            try {
                Future submit = this.b.submit(new b());
                long j = (long) i;
                this.h = ((Boolean) submit.get(j, TimeUnit.MILLISECONDS)).booleanValue();
                if (!this.h) {
                    SystemClock.sleep(j);
                    i2++;
                    i *= 2;
                } else {
                    return;
                }
            } catch (InterruptedException | ExecutionException | TimeoutException e) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Error pinging server [attempt: ");
                stringBuilder.append(i2);
                stringBuilder.append(", timeout: ");
                stringBuilder.append(i);
                stringBuilder.append("]. ");
                Log.e("ProxyCache", stringBuilder.toString(), e);
            }
        }
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("Shutdown server... Error pinging server [attempts: ");
        stringBuilder2.append(i2);
        stringBuilder2.append(", max timeout: ");
        stringBuilder2.append(i / 2);
        stringBuilder2.append("].");
        Log.e("ProxyCache", stringBuilder2.toString());
        a();
    }

    private void b(Socket socket) {
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("HTTP/1.1 200 OK\n\n".getBytes());
        outputStream.write("ping ok".getBytes());
    }

    private void c(Socket socket) {
        d(socket);
        e(socket);
        f(socket);
    }

    private boolean c() {
        h hVar = new h(d("ping"));
        boolean e;
        try {
            byte[] bytes = "ping ok".getBytes();
            hVar.a(0);
            byte[] bArr = new byte[bytes.length];
            hVar.a(bArr);
            e = Arrays.equals(bytes, bArr);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Ping response: `");
            stringBuilder.append(new String(bArr));
            stringBuilder.append("`, pinged? ");
            stringBuilder.append(e);
            Log.d("ProxyCache", stringBuilder.toString());
            return e;
        } catch (l e2) {
            e = e2;
            Log.e("ProxyCache", "Error reading ping response", e);
            return false;
        } finally {
            hVar.b();
        }
    }

    private boolean c(String str) {
        h hVar = new h(d(str));
        try {
            hVar.a(0);
            while (hVar.a(new byte[8192]) != -1) {
            }
            return true;
        } catch (l e) {
            Log.e("ProxyCache", "Error reading url", e);
            return false;
        } finally {
            hVar.b();
        }
    }

    private String d(String str) {
        return String.format(Locale.US, "http://%s:%d/%s", new Object[]{"127.0.0.1", Integer.valueOf(this.e), m.b(str)});
    }

    private void d() {
        synchronized (this.a) {
            for (g a : this.c.values()) {
                a.a();
            }
            this.c.clear();
        }
    }

    private void d(Socket socket) {
        try {
            if (!socket.isInputShutdown()) {
                socket.shutdownInput();
            }
        } catch (SocketException unused) {
            Log.d("ProxyCache", "Releasing input stream... Socket is closed by client.");
        } catch (IOException e) {
            a(new l("Error closing socket input stream", e));
        }
    }

    private g e(String str) {
        g gVar;
        synchronized (this.a) {
            gVar = (g) this.c.get(str);
            if (gVar == null) {
                gVar = new g(str, this.g);
                this.c.put(str, gVar);
            }
        }
        return gVar;
    }

    private void e() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Socket accept = this.d.accept();
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Accept new socket ");
                stringBuilder.append(accept);
                Log.d("ProxyCache", stringBuilder.toString());
                this.b.submit(new d(accept));
            } catch (IOException e) {
                a(new l("Error during waiting connection", e));
                return;
            }
        }
    }

    private void e(Socket socket) {
        try {
            if (socket.isOutputShutdown()) {
                socket.shutdownOutput();
            }
        } catch (IOException e) {
            a(new l("Error closing socket output stream", e));
        }
    }

    private int f() {
        int i;
        synchronized (this.a) {
            i = 0;
            for (g b : this.c.values()) {
                i += b.b();
            }
        }
        return i;
    }

    private void f(Socket socket) {
        try {
            if (!socket.isClosed()) {
                socket.close();
            }
        } catch (IOException e) {
            a(new l("Error closing socket", e));
        }
    }

    public void a() {
        Log.i("ProxyCache", "Shutdown proxy server");
        d();
        this.f.interrupt();
        try {
            if (!this.d.isClosed()) {
                this.d.close();
            }
        } catch (IOException e) {
            a(new l("Error shutting down proxy server", e));
        }
    }

    public boolean a(String str) {
        int i = HttpStatusCodes.STATUS_CODE_MULTIPLE_CHOICES;
        int i2 = 0;
        while (i2 < 3) {
            try {
                if (((Boolean) this.b.submit(new c(str)).get()).booleanValue()) {
                    return true;
                }
                SystemClock.sleep((long) i);
                i2++;
                i *= 2;
            } catch (InterruptedException | ExecutionException e) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Error precaching url [attempt: ");
                stringBuilder.append(i2);
                stringBuilder.append(", url: ");
                stringBuilder.append(str);
                stringBuilder.append("]. ");
                Log.e("ProxyCache", stringBuilder.toString(), e);
            }
        }
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("Shutdown server... Error precaching url [attempts: ");
        stringBuilder2.append(i2);
        stringBuilder2.append(", url: ");
        stringBuilder2.append(str);
        stringBuilder2.append("].");
        Log.e("ProxyCache", stringBuilder2.toString());
        a();
        return false;
    }

    public String b(String str) {
        if (!this.h) {
            Log.e("ProxyCache", "Proxy server isn't pinged. Caching doesn't work.");
        }
        return this.h ? d(str) : str;
    }
}
