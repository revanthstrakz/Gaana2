package com.payu.custombrowser.util;

import com.comscore.utils.Constants;
import com.gaana.login.sso.SsoErrorCodes;
import com.google.api.client.http.HttpStatusCodes;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.PushbackInputStream;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.URLDecoder;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TimeZone;

public abstract class e {
    private final String a;
    private final int b;
    private ServerSocket c;
    private Set<Socket> d;
    private Thread e;
    private a f;
    private o g;

    public interface a {
        void a(Runnable runnable);
    }

    public static class b {
        private String a;
        private String b;
        private String c;

        public String a() {
            return String.format("%s=%s; expires=%s", new Object[]{this.a, this.b, this.c});
        }
    }

    public class c implements Iterable<String> {
        private HashMap<String, String> b = new HashMap();
        private ArrayList<b> c = new ArrayList();

        public c(Map<String, String> map) {
            String str = (String) map.get("cookie");
            if (str != null) {
                for (String trim : str.split(";")) {
                    String[] split = trim.trim().split("=");
                    if (split.length == 2) {
                        this.b.put(split[0], split[1]);
                    }
                }
            }
        }

        public Iterator<String> iterator() {
            return this.b.keySet().iterator();
        }

        public void a(k kVar) {
            Iterator it = this.c.iterator();
            while (it.hasNext()) {
                kVar.a(com.til.colombia.android.internal.h.i, ((b) it.next()).a());
            }
        }
    }

    public interface i {
        void a(Map<String, String> map) throws IOException, l;

        Map<String, String> b();

        String c();

        Map<String, String> d();

        String e();

        j f();
    }

    public enum j {
        GET,
        PUT,
        POST,
        DELETE,
        HEAD,
        OPTIONS;

        static j lookup(String str) {
            for (j jVar : values()) {
                if (jVar.toString().equalsIgnoreCase(str)) {
                    return jVar;
                }
            }
            return null;
        }
    }

    public static class k {
        private a a;
        private String b;
        private InputStream c;
        private Map<String, String> d;
        private j e;
        private boolean f;

        public interface a {
            String getDescription();
        }

        public enum b implements a {
            SWITCH_PROTOCOL(101, "Switching Protocols"),
            OK(200, Constants.RESPONSE_MASK),
            CREATED(HttpStatusCodes.STATUS_CODE_CREATED, "Created"),
            ACCEPTED(202, "Accepted"),
            NO_CONTENT(HttpStatusCodes.STATUS_CODE_NO_CONTENT, "No Content"),
            PARTIAL_CONTENT(206, "Partial Content"),
            REDIRECT(HttpStatusCodes.STATUS_CODE_MOVED_PERMANENTLY, "Moved Permanently"),
            NOT_MODIFIED(HttpStatusCodes.STATUS_CODE_NOT_MODIFIED, "Not Modified"),
            BAD_REQUEST(400, "Bad Request"),
            UNAUTHORIZED(401, "Unauthorized"),
            FORBIDDEN(403, "Forbidden"),
            NOT_FOUND(404, "Not Found"),
            METHOD_NOT_ALLOWED(405, "Method Not Allowed"),
            RANGE_NOT_SATISFIABLE(SsoErrorCodes.LIMIT_EXCEEDED, "Requested Range Not Satisfiable"),
            INTERNAL_ERROR(500, "Internal Server Error");
            
            private final String description;
            private final int requestStatus;

            private b(int i, String str) {
                this.requestStatus = i;
                this.description = str;
            }

            public int getRequestStatus() {
                return this.requestStatus;
            }

            public String getDescription() {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("");
                stringBuilder.append(this.requestStatus);
                stringBuilder.append(" ");
                stringBuilder.append(this.description);
                return stringBuilder.toString();
            }
        }

        public k(String str) {
            this(b.OK, "text/html", str);
        }

        public k(a aVar, String str, String str2) {
            InputStream byteArrayInputStream;
            this.d = new HashMap();
            this.a = aVar;
            this.b = str;
            if (str2 != null) {
                try {
                    byteArrayInputStream = new ByteArrayInputStream(str2.getBytes("UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    ThrowableExtension.printStackTrace(e);
                    return;
                }
            }
            byteArrayInputStream = null;
            this.c = byteArrayInputStream;
        }

        public void a(String str, String str2) {
            this.d.put(str, str2);
        }

        /* Access modifiers changed, original: protected */
        public void a(OutputStream outputStream) {
            String str = this.b;
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("E, d MMM yyyy HH:mm:ss 'GMT'", Locale.US);
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
            try {
                if (this.a == null) {
                    throw new Error("sendResponse(): Status can't be null.");
                }
                PrintWriter printWriter = new PrintWriter(outputStream);
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("HTTP/1.1 ");
                stringBuilder.append(this.a.getDescription());
                stringBuilder.append(" \r\n");
                printWriter.print(stringBuilder.toString());
                if (str != null) {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("Content-Type: ");
                    stringBuilder.append(str);
                    stringBuilder.append("\r\n");
                    printWriter.print(stringBuilder.toString());
                }
                if (this.d == null || this.d.get("Date") == null) {
                    StringBuilder stringBuilder2 = new StringBuilder();
                    stringBuilder2.append("Date: ");
                    stringBuilder2.append(simpleDateFormat.format(new Date()));
                    stringBuilder2.append("\r\n");
                    printWriter.print(stringBuilder2.toString());
                }
                if (this.d != null) {
                    for (String str2 : this.d.keySet()) {
                        String str3 = (String) this.d.get(str2);
                        StringBuilder stringBuilder3 = new StringBuilder();
                        stringBuilder3.append(str2);
                        stringBuilder3.append(": ");
                        stringBuilder3.append(str3);
                        stringBuilder3.append("\r\n");
                        printWriter.print(stringBuilder3.toString());
                    }
                }
                a(printWriter, this.d);
                if (this.e == j.HEAD || !this.f) {
                    int available = this.c != null ? this.c.available() : 0;
                    a(printWriter, this.d, available);
                    printWriter.print("\r\n");
                    printWriter.flush();
                    a(outputStream, available);
                } else {
                    a(outputStream, printWriter);
                }
                outputStream.flush();
                e.b(this.c);
            } catch (IOException unused) {
            }
        }

        /* Access modifiers changed, original: protected */
        public void a(PrintWriter printWriter, Map<String, String> map, int i) {
            if (!a((Map) map, "content-length")) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Content-Length: ");
                stringBuilder.append(i);
                stringBuilder.append("\r\n");
                printWriter.print(stringBuilder.toString());
            }
        }

        /* Access modifiers changed, original: protected */
        public void a(PrintWriter printWriter, Map<String, String> map) {
            if (!a((Map) map, "connection")) {
                printWriter.print("Connection: keep-alive\r\n");
            }
        }

        private boolean a(Map<String, String> map, String str) {
            int i = 0;
            for (String equalsIgnoreCase : map.keySet()) {
                i |= equalsIgnoreCase.equalsIgnoreCase(str);
            }
            return i;
        }

        private void a(OutputStream outputStream, PrintWriter printWriter) throws IOException {
            printWriter.print("Transfer-Encoding: chunked\r\n");
            printWriter.print("\r\n");
            printWriter.flush();
            byte[] bytes = "\r\n".getBytes();
            byte[] bArr = new byte[16384];
            while (true) {
                int read = this.c.read(bArr);
                if (read > 0) {
                    outputStream.write(String.format("%x\r\n", new Object[]{Integer.valueOf(read)}).getBytes());
                    outputStream.write(bArr, 0, read);
                    outputStream.write(bytes);
                } else {
                    outputStream.write(String.format("0\r\n\r\n", new Object[0]).getBytes());
                    return;
                }
            }
        }

        private void a(OutputStream outputStream, int i) throws IOException {
            if (this.e != j.HEAD && this.c != null) {
                byte[] bArr = new byte[16384];
                while (i > 0) {
                    int read = this.c.read(bArr, 0, i > 16384 ? 16384 : i);
                    if (read > 0) {
                        outputStream.write(bArr, 0, read);
                        i -= read;
                    } else {
                        return;
                    }
                }
            }
        }

        public void a(j jVar) {
            this.e = jVar;
        }
    }

    public static final class l extends Exception {
        private final b a;

        public l(b bVar, String str) {
            super(str);
            this.a = bVar;
        }

        public l(b bVar, String str, Exception exception) {
            super(str, exception);
            this.a = bVar;
        }

        public b a() {
            return this.a;
        }
    }

    public interface m {
        void a() throws Exception;

        String b();
    }

    public interface n {
        m a() throws Exception;

        void b();
    }

    public interface o {
        n a();
    }

    public static class d implements a {
        private long a;

        public void a(Runnable runnable) {
            this.a++;
            Thread thread = new Thread(runnable);
            thread.setDaemon(true);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("NanoHttpd Request Processor (#");
            stringBuilder.append(this.a);
            stringBuilder.append(")");
            thread.setName(stringBuilder.toString());
            thread.start();
        }
    }

    public static class e implements m {
        private File a;
        private OutputStream b = new FileOutputStream(this.a);

        public e(String str) throws IOException {
            this.a = File.createTempFile("NH-", "", new File(str));
        }

        public void a() throws Exception {
            e.b(this.b);
            this.a.delete();
        }

        public String b() {
            return this.a.getAbsolutePath();
        }
    }

    public static class f implements n {
        private final String a = System.getProperty("java.io.tmpdir");
        private final List<m> b = new ArrayList();

        public m a() throws Exception {
            e eVar = new e(this.a);
            this.b.add(eVar);
            return eVar;
        }

        public void b() {
            for (m a : this.b) {
                try {
                    a.a();
                } catch (Exception unused) {
                }
            }
            this.b.clear();
        }
    }

    private class g implements o {
        private g() {
        }

        /* synthetic */ g(e eVar, AnonymousClass1 anonymousClass1) {
            this();
        }

        public n a() {
            return new f();
        }
    }

    protected class h implements i {
        private final n b;
        private final OutputStream c;
        private PushbackInputStream d;
        private int e;
        private int f;
        private String g;
        private j h;
        private Map<String, String> i;
        private Map<String, String> j;
        private c k;
        private String l;

        public h(n nVar, InputStream inputStream, OutputStream outputStream, InetAddress inetAddress) {
            this.b = nVar;
            this.d = new PushbackInputStream(inputStream, 8192);
            this.c = outputStream;
            Object str = (inetAddress.isLoopbackAddress() || inetAddress.isAnyLocalAddress()) ? "127.0.0.1" : inetAddress.getHostAddress().toString();
            this.j = new HashMap();
            this.j.put("remote-addr", str);
            this.j.put("http-client-ip", str);
        }

        public void a() throws IOException {
            try {
                byte[] bArr = new byte[8192];
                this.e = 0;
                this.f = 0;
                int read = this.d.read(bArr, 0, 8192);
                if (read == -1) {
                    e.b(this.d);
                    e.b(this.c);
                    throw new SocketException("NanoHttpd Shutdown");
                }
                while (read > 0) {
                    this.f += read;
                    this.e = a(bArr, this.f);
                    if (this.e > 0) {
                        break;
                    }
                    read = this.d.read(bArr, this.f, 8192 - this.f);
                }
                if (this.e < this.f) {
                    this.d.unread(bArr, this.e, this.f - this.e);
                }
                this.i = new HashMap();
                if (this.j == null) {
                    this.j = new HashMap();
                }
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(bArr, 0, this.f)));
                HashMap hashMap = new HashMap();
                a(bufferedReader, hashMap, this.i, this.j);
                this.h = j.lookup((String) hashMap.get("method"));
                if (this.h == null) {
                    throw new l(b.BAD_REQUEST, "BAD REQUEST: Syntax error.");
                }
                this.g = (String) hashMap.get("uri");
                this.k = new c(this.j);
                k a = e.this.a((i) this);
                if (a == null) {
                    throw new l(b.INTERNAL_ERROR, "SERVER INTERNAL ERROR: Serve() returned a null response.");
                }
                this.k.a(a);
                a.a(this.h);
                a.a(this.c);
                this.b.b();
            } catch (Exception unused) {
                e.b(this.d);
                e.b(this.c);
                throw new SocketException("NanoHttpd Shutdown");
            } catch (SocketException e) {
                throw e;
            } catch (SocketTimeoutException e2) {
                throw e2;
            } catch (IOException e3) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("SERVER INTERNAL ERROR: IOException: ");
                stringBuilder.append(e3.getMessage());
                new k(b.INTERNAL_ERROR, "text/plain", stringBuilder.toString()).a(this.c);
                e.b(this.c);
            } catch (l e4) {
                try {
                    new k(e4.a(), "text/plain", e4.getMessage()).a(this.c);
                    e.b(this.c);
                } catch (Throwable th) {
                    this.b.b();
                }
            }
        }

        public void a(Map<String, String> map) throws IOException, l {
            Closeable bufferedReader;
            Throwable th;
            Throwable th2;
            Map<String, String> map2 = map;
            StringTokenizer stringTokenizer = null;
            Closeable g;
            try {
                ByteBuffer map3;
                g = g();
                try {
                    long parseInt = this.j.containsKey("content-length") ? (long) Integer.parseInt((String) this.j.get("content-length")) : this.e < this.f ? (long) (this.f - this.e) : 0;
                    byte[] bArr = new byte[512];
                    while (this.f >= 0 && parseInt > 0) {
                        this.f = this.d.read(bArr, 0, (int) Math.min(parseInt, 512));
                        long j = parseInt - ((long) this.f);
                        if (this.f > 0) {
                            g.write(bArr, 0, this.f);
                        }
                        parseInt = j;
                    }
                    map3 = g.getChannel().map(MapMode.READ_ONLY, 0, g.length());
                    g.seek(0);
                    bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(g.getFD())));
                } catch (Throwable th3) {
                    th = th3;
                    bufferedReader = null;
                    th2 = th;
                    e.b(g);
                    e.b(bufferedReader);
                    throw th2;
                }
                try {
                    if (j.POST.equals(this.h)) {
                        String str = "";
                        String str2 = (String) this.j.get("content-type");
                        if (str2 != null) {
                            stringTokenizer = new StringTokenizer(str2, ",; ");
                            if (stringTokenizer.hasMoreTokens()) {
                                str = stringTokenizer.nextToken();
                            }
                        }
                        String str3;
                        if (!"multipart/form-data".equalsIgnoreCase(str)) {
                            str3 = "";
                            StringBuilder stringBuilder = new StringBuilder();
                            char[] cArr = new char[512];
                            for (int read = bufferedReader.read(cArr); read >= 0 && !str3.endsWith("\r\n"); read = bufferedReader.read(cArr)) {
                                str3 = String.valueOf(cArr, 0, read);
                                stringBuilder.append(str3);
                            }
                            str3 = stringBuilder.toString().trim();
                            if ("application/x-www-form-urlencoded".equalsIgnoreCase(str)) {
                                a(str3, this.i);
                            } else if (str3.length() != 0) {
                                map2.put(CBConstant.POST_DATA, str3);
                            }
                        } else if (stringTokenizer.hasMoreTokens()) {
                            str3 = "boundary=";
                            str3 = str2.substring(str2.indexOf(str3) + str3.length(), str2.length());
                            if (str3.startsWith("\"") && str3.endsWith("\"")) {
                                str3 = str3.substring(1, str3.length() - 1);
                            }
                            a(str3, map3, bufferedReader, this.i, map2);
                        } else {
                            throw new l(b.BAD_REQUEST, "BAD REQUEST: Content type is multipart/form-data but boundary missing. Usage: GET /example/file.html");
                        }
                    } else if (j.PUT.equals(this.h)) {
                        map2.put("content", a(map3, 0, map3.limit()));
                    }
                    e.b(g);
                    e.b(bufferedReader);
                } catch (Throwable th4) {
                    th = th4;
                    th2 = th;
                    e.b(g);
                    e.b(bufferedReader);
                    throw th2;
                }
            } catch (Throwable th5) {
                th = th5;
                g = null;
                bufferedReader = g;
                th2 = th;
                e.b(g);
                e.b(bufferedReader);
                throw th2;
            }
        }

        private void a(BufferedReader bufferedReader, Map<String, String> map, Map<String, String> map2, Map<String, String> map3) throws l {
            try {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    StringTokenizer stringTokenizer = new StringTokenizer(readLine);
                    if (stringTokenizer.hasMoreTokens()) {
                        map.put("method", stringTokenizer.nextToken());
                        if (stringTokenizer.hasMoreTokens()) {
                            Object a;
                            readLine = stringTokenizer.nextToken();
                            int indexOf = readLine.indexOf(63);
                            if (indexOf >= 0) {
                                a(readLine.substring(indexOf + 1), (Map) map2);
                                a = e.this.a(readLine.substring(0, indexOf));
                            } else {
                                a = e.this.a(readLine);
                            }
                            if (stringTokenizer.hasMoreTokens()) {
                                readLine = bufferedReader.readLine();
                                while (readLine != null && readLine.trim().length() > 0) {
                                    int indexOf2 = readLine.indexOf(58);
                                    if (indexOf2 >= 0) {
                                        map3.put(readLine.substring(0, indexOf2).trim().toLowerCase(Locale.US), readLine.substring(indexOf2 + 1).trim());
                                    }
                                    readLine = bufferedReader.readLine();
                                }
                            }
                            map.put("uri", a);
                            return;
                        }
                        throw new l(b.BAD_REQUEST, "BAD REQUEST: Missing URI. Usage: GET /example/file.html");
                    }
                    throw new l(b.BAD_REQUEST, "BAD REQUEST: Syntax error. Usage: GET /example/file.html");
                }
            } catch (IOException e) {
                b bVar = b.INTERNAL_ERROR;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("SERVER INTERNAL ERROR: IOException: ");
                stringBuilder.append(e.getMessage());
                throw new l(bVar, stringBuilder.toString(), e);
            }
        }

        /* JADX WARNING: Removed duplicated region for block: B:52:0x0161  */
        /* JADX WARNING: Removed duplicated region for block: B:18:0x0068 A:{Catch:{ IOException -> 0x0169 }} */
        private void a(java.lang.String r18, java.nio.ByteBuffer r19, java.io.BufferedReader r20, java.util.Map<java.lang.String, java.lang.String> r21, java.util.Map<java.lang.String, java.lang.String> r22) throws com.payu.custombrowser.util.e.l {
            /*
            r17 = this;
            r1 = r17;
            r2 = r18;
            r3 = r19;
            r4 = r18.getBytes();	 Catch:{ IOException -> 0x0169 }
            r4 = r1.a(r3, r4);	 Catch:{ IOException -> 0x0169 }
            r5 = r20.readLine();	 Catch:{ IOException -> 0x0169 }
            r6 = 1;
            r7 = r6;
        L_0x0014:
            if (r5 == 0) goto L_0x0168;
        L_0x0016:
            r5 = r5.contains(r2);	 Catch:{ IOException -> 0x0169 }
            if (r5 != 0) goto L_0x0026;
        L_0x001c:
            r2 = new com.payu.custombrowser.util.e$l;	 Catch:{ IOException -> 0x0169 }
            r3 = com.payu.custombrowser.util.e.k.b.BAD_REQUEST;	 Catch:{ IOException -> 0x0169 }
            r4 = "BAD REQUEST: Content type is multipart/form-data but next chunk does not start with boundary. Usage: GET /example/file.html";
            r2.<init>(r3, r4);	 Catch:{ IOException -> 0x0169 }
            throw r2;	 Catch:{ IOException -> 0x0169 }
        L_0x0026:
            r7 = r7 + r6;
            r5 = new java.util.HashMap;	 Catch:{ IOException -> 0x0169 }
            r5.<init>();	 Catch:{ IOException -> 0x0169 }
            r8 = r20.readLine();	 Catch:{ IOException -> 0x0169 }
        L_0x0030:
            r9 = 0;
            r10 = -1;
            if (r8 == 0) goto L_0x0066;
        L_0x0034:
            r11 = r8.trim();	 Catch:{ IOException -> 0x0169 }
            r11 = r11.length();	 Catch:{ IOException -> 0x0169 }
            if (r11 <= 0) goto L_0x0066;
        L_0x003e:
            r11 = 58;
            r11 = r8.indexOf(r11);	 Catch:{ IOException -> 0x0169 }
            if (r11 == r10) goto L_0x0061;
        L_0x0046:
            r9 = r8.substring(r9, r11);	 Catch:{ IOException -> 0x0169 }
            r9 = r9.trim();	 Catch:{ IOException -> 0x0169 }
            r10 = java.util.Locale.US;	 Catch:{ IOException -> 0x0169 }
            r9 = r9.toLowerCase(r10);	 Catch:{ IOException -> 0x0169 }
            r11 = r11 + 1;
            r8 = r8.substring(r11);	 Catch:{ IOException -> 0x0169 }
            r8 = r8.trim();	 Catch:{ IOException -> 0x0169 }
            r5.put(r9, r8);	 Catch:{ IOException -> 0x0169 }
        L_0x0061:
            r8 = r20.readLine();	 Catch:{ IOException -> 0x0169 }
            goto L_0x0030;
        L_0x0066:
            if (r8 == 0) goto L_0x0161;
        L_0x0068:
            r11 = "content-disposition";
            r11 = r5.get(r11);	 Catch:{ IOException -> 0x0169 }
            r11 = (java.lang.String) r11;	 Catch:{ IOException -> 0x0169 }
            if (r11 != 0) goto L_0x007c;
        L_0x0072:
            r2 = new com.payu.custombrowser.util.e$l;	 Catch:{ IOException -> 0x0169 }
            r3 = com.payu.custombrowser.util.e.k.b.BAD_REQUEST;	 Catch:{ IOException -> 0x0169 }
            r4 = "BAD REQUEST: Content type is multipart/form-data but no content-disposition info found. Usage: GET /example/file.html";
            r2.<init>(r3, r4);	 Catch:{ IOException -> 0x0169 }
            throw r2;	 Catch:{ IOException -> 0x0169 }
        L_0x007c:
            r12 = new java.util.StringTokenizer;	 Catch:{ IOException -> 0x0169 }
            r13 = ";";
            r12.<init>(r11, r13);	 Catch:{ IOException -> 0x0169 }
            r11 = new java.util.HashMap;	 Catch:{ IOException -> 0x0169 }
            r11.<init>();	 Catch:{ IOException -> 0x0169 }
        L_0x0088:
            r13 = r12.hasMoreTokens();	 Catch:{ IOException -> 0x0169 }
            if (r13 == 0) goto L_0x00bb;
        L_0x008e:
            r13 = r12.nextToken();	 Catch:{ IOException -> 0x0169 }
            r13 = r13.trim();	 Catch:{ IOException -> 0x0169 }
            r14 = 61;
            r14 = r13.indexOf(r14);	 Catch:{ IOException -> 0x0169 }
            if (r14 == r10) goto L_0x00b9;
        L_0x009e:
            r15 = r13.substring(r9, r14);	 Catch:{ IOException -> 0x0169 }
            r15 = r15.trim();	 Catch:{ IOException -> 0x0169 }
            r9 = java.util.Locale.US;	 Catch:{ IOException -> 0x0169 }
            r9 = r15.toLowerCase(r9);	 Catch:{ IOException -> 0x0169 }
            r14 = r14 + 1;
            r13 = r13.substring(r14);	 Catch:{ IOException -> 0x0169 }
            r13 = r13.trim();	 Catch:{ IOException -> 0x0169 }
            r11.put(r9, r13);	 Catch:{ IOException -> 0x0169 }
        L_0x00b9:
            r9 = 0;
            goto L_0x0088;
        L_0x00bb:
            r9 = "name";
            r9 = r11.get(r9);	 Catch:{ IOException -> 0x0169 }
            r9 = (java.lang.String) r9;	 Catch:{ IOException -> 0x0169 }
            r12 = r9.length();	 Catch:{ IOException -> 0x0169 }
            r12 = r12 - r6;
            r9 = r9.substring(r6, r12);	 Catch:{ IOException -> 0x0169 }
            r12 = "";
            r13 = "content-type";
            r5 = r5.get(r13);	 Catch:{ IOException -> 0x0169 }
            if (r5 != 0) goto L_0x0119;
        L_0x00d6:
            if (r8 == 0) goto L_0x0114;
        L_0x00d8:
            r5 = r8.contains(r2);	 Catch:{ IOException -> 0x0169 }
            if (r5 != 0) goto L_0x0114;
        L_0x00de:
            r8 = r20.readLine();	 Catch:{ IOException -> 0x0169 }
            if (r8 == 0) goto L_0x00d6;
        L_0x00e4:
            r5 = r8.indexOf(r2);	 Catch:{ IOException -> 0x0169 }
            if (r5 != r10) goto L_0x00fc;
        L_0x00ea:
            r5 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x0169 }
            r5.<init>();	 Catch:{ IOException -> 0x0169 }
            r5.append(r12);	 Catch:{ IOException -> 0x0169 }
            r5.append(r8);	 Catch:{ IOException -> 0x0169 }
            r5 = r5.toString();	 Catch:{ IOException -> 0x0169 }
            r12 = r5;
            r13 = 0;
            goto L_0x00d6;
        L_0x00fc:
            r11 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x0169 }
            r11.<init>();	 Catch:{ IOException -> 0x0169 }
            r11.append(r12);	 Catch:{ IOException -> 0x0169 }
            r5 = r5 + -2;
            r13 = 0;
            r5 = r8.substring(r13, r5);	 Catch:{ IOException -> 0x0169 }
            r11.append(r5);	 Catch:{ IOException -> 0x0169 }
            r5 = r11.toString();	 Catch:{ IOException -> 0x0169 }
            r12 = r5;
            goto L_0x00d6;
        L_0x0114:
            r5 = r21;
            r10 = r22;
            goto L_0x015d;
        L_0x0119:
            r5 = r4.length;	 Catch:{ IOException -> 0x0169 }
            if (r7 <= r5) goto L_0x0126;
        L_0x011c:
            r2 = new com.payu.custombrowser.util.e$l;	 Catch:{ IOException -> 0x0169 }
            r3 = com.payu.custombrowser.util.e.k.b.INTERNAL_ERROR;	 Catch:{ IOException -> 0x0169 }
            r4 = "Error processing request";
            r2.<init>(r3, r4);	 Catch:{ IOException -> 0x0169 }
            throw r2;	 Catch:{ IOException -> 0x0169 }
        L_0x0126:
            r5 = r7 + -2;
            r5 = r4[r5];	 Catch:{ IOException -> 0x0169 }
            r5 = r1.a(r3, r5);	 Catch:{ IOException -> 0x0169 }
            r8 = r7 + -1;
            r8 = r4[r8];	 Catch:{ IOException -> 0x0169 }
            r8 = r8 - r5;
            r8 = r8 + -4;
            r5 = r1.a(r3, r5, r8);	 Catch:{ IOException -> 0x0169 }
            r10 = r22;
            r10.put(r9, r5);	 Catch:{ IOException -> 0x0169 }
            r5 = "filename";
            r5 = r11.get(r5);	 Catch:{ IOException -> 0x0169 }
            r5 = (java.lang.String) r5;	 Catch:{ IOException -> 0x0169 }
            r8 = r5.length();	 Catch:{ IOException -> 0x0169 }
            r8 = r8 - r6;
            r12 = r5.substring(r6, r8);	 Catch:{ IOException -> 0x0169 }
        L_0x014f:
            r8 = r20.readLine();	 Catch:{ IOException -> 0x0169 }
            if (r8 == 0) goto L_0x015b;
        L_0x0155:
            r5 = r8.contains(r2);	 Catch:{ IOException -> 0x0169 }
            if (r5 == 0) goto L_0x014f;
        L_0x015b:
            r5 = r21;
        L_0x015d:
            r5.put(r9, r12);	 Catch:{ IOException -> 0x0169 }
            goto L_0x0165;
        L_0x0161:
            r5 = r21;
            r10 = r22;
        L_0x0165:
            r5 = r8;
            goto L_0x0014;
        L_0x0168:
            return;
        L_0x0169:
            r0 = move-exception;
            r2 = r0;
            r3 = new com.payu.custombrowser.util.e$l;
            r4 = com.payu.custombrowser.util.e.k.b.INTERNAL_ERROR;
            r5 = new java.lang.StringBuilder;
            r5.<init>();
            r6 = "SERVER INTERNAL ERROR: IOException: ";
            r5.append(r6);
            r6 = r2.getMessage();
            r5.append(r6);
            r5 = r5.toString();
            r3.<init>(r4, r5, r2);
            throw r3;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.payu.custombrowser.util.e$h.a(java.lang.String, java.nio.ByteBuffer, java.io.BufferedReader, java.util.Map, java.util.Map):void");
        }

        private int a(byte[] bArr, int i) {
            int i2 = 0;
            while (true) {
                int i3 = i2 + 3;
                if (i3 >= i) {
                    return 0;
                }
                if (bArr[i2] == (byte) 13 && bArr[i2 + 1] == (byte) 10 && bArr[i2 + 2] == (byte) 13 && bArr[i3] == (byte) 10) {
                    return i2 + 4;
                }
                i2++;
            }
        }

        private int[] a(ByteBuffer byteBuffer, byte[] bArr) {
            ArrayList arrayList = new ArrayList();
            int i = 0;
            int i2 = -1;
            int i3 = 0;
            int i4 = i3;
            while (i3 < byteBuffer.limit()) {
                if (byteBuffer.get(i3) == bArr[i4]) {
                    if (i4 == 0) {
                        i2 = i3;
                    }
                    i4++;
                    if (i4 == bArr.length) {
                        arrayList.add(Integer.valueOf(i2));
                    } else {
                        i3++;
                    }
                } else {
                    i3 -= i4;
                }
                i2 = -1;
                i4 = 0;
                i3++;
            }
            int[] iArr = new int[arrayList.size()];
            while (i < iArr.length) {
                iArr[i] = ((Integer) arrayList.get(i)).intValue();
                i++;
            }
            return iArr;
        }

        private String a(ByteBuffer byteBuffer, int i, int i2) {
            Throwable e;
            String str = "";
            if (i2 > 0) {
                Closeable closeable = null;
                try {
                    m a = this.b.a();
                    byteBuffer = byteBuffer.duplicate();
                    Closeable fileOutputStream = new FileOutputStream(a.b());
                    try {
                        FileChannel channel = fileOutputStream.getChannel();
                        byteBuffer.position(i).limit(i + i2);
                        channel.write(byteBuffer.slice());
                        str = a.b();
                        e.b(fileOutputStream);
                    } catch (Exception e2) {
                        e = e2;
                        closeable = fileOutputStream;
                        try {
                            throw new Error(e);
                        } catch (Throwable th) {
                            e = th;
                            e.b(closeable);
                            throw e;
                        }
                    } catch (Throwable th2) {
                        e = th2;
                        closeable = fileOutputStream;
                        e.b(closeable);
                        throw e;
                    }
                } catch (Exception e3) {
                    e = e3;
                    throw new Error(e);
                }
            }
            return str;
        }

        private RandomAccessFile g() {
            try {
                return new RandomAccessFile(this.b.a().b(), "rw");
            } catch (Exception e) {
                throw new Error(e);
            }
        }

        private int a(ByteBuffer byteBuffer, int i) {
            while (i < byteBuffer.limit()) {
                if (byteBuffer.get(i) == (byte) 13) {
                    i++;
                    if (byteBuffer.get(i) == (byte) 10) {
                        i++;
                        if (byteBuffer.get(i) == (byte) 13) {
                            i++;
                            if (byteBuffer.get(i) == (byte) 10) {
                                break;
                            }
                        } else {
                            continue;
                        }
                    } else {
                        continue;
                    }
                }
                i++;
            }
            return i + 1;
        }

        private void a(String str, Map<String, String> map) {
            if (str == null) {
                this.l = "";
                return;
            }
            this.l = str;
            StringTokenizer stringTokenizer = new StringTokenizer(str, "&");
            while (stringTokenizer.hasMoreTokens()) {
                str = stringTokenizer.nextToken();
                int indexOf = str.indexOf(61);
                if (indexOf >= 0) {
                    map.put(e.this.a(str.substring(0, indexOf)).trim(), e.this.a(str.substring(indexOf + 1)));
                } else {
                    map.put(e.this.a(str).trim(), "");
                }
            }
        }

        public final Map<String, String> b() {
            return this.i;
        }

        public String c() {
            return this.l;
        }

        public final Map<String, String> d() {
            return this.j;
        }

        public final String e() {
            return this.g;
        }

        public final j f() {
            return this.h;
        }
    }

    public e(int i) {
        this(null, i);
    }

    public e(String str, int i) {
        this.d = new HashSet();
        this.a = str;
        this.b = i;
        a(new g(this, null));
        a(new d());
    }

    private static final void b(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
            }
        }
    }

    private static final void d(Socket socket) {
        if (socket != null) {
            try {
                socket.close();
            } catch (IOException unused) {
            }
        }
    }

    private static final void a(ServerSocket serverSocket) {
        if (serverSocket != null) {
            try {
                serverSocket.close();
            } catch (IOException unused) {
            }
        }
    }

    public void a() throws IOException {
        this.c = new ServerSocket();
        this.c.bind(this.a != null ? new InetSocketAddress(this.a, this.b) : new InetSocketAddress(this.b));
        this.e = new Thread(new Runnable() {
            public void run() {
                do {
                    try {
                        final Socket accept = e.this.c.accept();
                        e.this.a(accept);
                        accept.setSoTimeout(5000);
                        final InputStream inputStream = accept.getInputStream();
                        e.this.f.a(new Runnable() {
                            public void run() {
                                Closeable outputStream;
                                Exception e;
                                Throwable th;
                                try {
                                    outputStream = accept.getOutputStream();
                                    try {
                                        h hVar = new h(e.this.g.a(), inputStream, outputStream, accept.getInetAddress());
                                        while (!accept.isClosed()) {
                                            hVar.a();
                                        }
                                    } catch (Exception e2) {
                                        e = e2;
                                        try {
                                            if (!((e instanceof SocketException) && "NanoHttpd Shutdown".equals(e.getMessage()))) {
                                                ThrowableExtension.printStackTrace(e);
                                            }
                                            e.b(outputStream);
                                            e.b(inputStream);
                                            e.d(accept);
                                            e.this.b(accept);
                                        } catch (Throwable th2) {
                                            th = th2;
                                            e.b(outputStream);
                                            e.b(inputStream);
                                            e.d(accept);
                                            e.this.b(accept);
                                            throw th;
                                        }
                                    }
                                } catch (Exception e3) {
                                    Exception exception = e3;
                                    outputStream = null;
                                    e = exception;
                                    ThrowableExtension.printStackTrace(e);
                                    e.b(outputStream);
                                    e.b(inputStream);
                                    e.d(accept);
                                    e.this.b(accept);
                                } catch (Throwable th3) {
                                    Throwable th4 = th3;
                                    outputStream = null;
                                    th = th4;
                                    e.b(outputStream);
                                    e.b(inputStream);
                                    e.d(accept);
                                    e.this.b(accept);
                                    throw th;
                                }
                                e.b(outputStream);
                                e.b(inputStream);
                                e.d(accept);
                                e.this.b(accept);
                            }
                        });
                    } catch (IOException unused) {
                    }
                } while (!e.this.c.isClosed());
            }
        });
        this.e.setDaemon(true);
        this.e.setName("NH Main Listener");
        this.e.start();
    }

    public void b() {
        try {
            a(this.c);
            c();
            if (this.e != null) {
                this.e.join();
            }
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
        }
    }

    public synchronized void a(Socket socket) {
        this.d.add(socket);
    }

    public synchronized void b(Socket socket) {
        this.d.remove(socket);
    }

    public synchronized void c() {
        for (Socket d : this.d) {
            d(d);
        }
    }

    @Deprecated
    public k a(String str, j jVar, Map<String, String> map, Map<String, String> map2, Map<String, String> map3) {
        return new k(b.NOT_FOUND, "text/plain", "Not Found");
    }

    public k a(i iVar) {
        HashMap hashMap = new HashMap();
        j f = iVar.f();
        if (j.PUT.equals(f) || j.POST.equals(f)) {
            try {
                iVar.a(hashMap);
            } catch (IOException e) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("SERVER INTERNAL ERROR: IOException: ");
                stringBuilder.append(e.getMessage());
                return new k(b.INTERNAL_ERROR, "text/plain", stringBuilder.toString());
            } catch (l e2) {
                return new k(e2.a(), "text/plain", e2.getMessage());
            }
        }
        Map b = iVar.b();
        b.put("NanoHttpd.QUERY_STRING", iVar.c());
        return a(iVar.e(), f, iVar.d(), b, hashMap);
    }

    /* Access modifiers changed, original: protected */
    public String a(String str) {
        try {
            return URLDecoder.decode(str, "UTF8");
        } catch (UnsupportedEncodingException unused) {
            return null;
        }
    }

    public void a(a aVar) {
        this.f = aVar;
    }

    public void a(o oVar) {
        this.g = oVar;
    }
}
