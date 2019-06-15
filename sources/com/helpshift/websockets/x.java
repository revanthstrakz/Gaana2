package com.helpshift.websockets;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.List;
import java.util.Map.Entry;

class x {
    private final Socket a;
    private final String b;
    private final int c;
    private final y d;

    public x(Socket socket, String str, int i, y yVar) {
        this.a = socket;
        this.b = str;
        this.c = i;
        this.d = yVar;
    }

    public void a() throws IOException {
        c();
        e();
    }

    private void c() throws IOException {
        byte[] a = p.a(d());
        OutputStream outputStream = this.a.getOutputStream();
        outputStream.write(a);
        outputStream.flush();
    }

    private String d() {
        String format = String.format("%s:%d", new Object[]{this.b, Integer.valueOf(this.c)});
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("CONNECT ");
        stringBuilder.append(format);
        stringBuilder.append(" HTTP/1.1");
        stringBuilder.append("\r\n");
        stringBuilder.append("Host: ");
        stringBuilder.append(format);
        stringBuilder.append("\r\n");
        a(stringBuilder);
        b(stringBuilder);
        stringBuilder.append("\r\n");
        return stringBuilder.toString();
    }

    private void a(StringBuilder stringBuilder) {
        for (Entry entry : this.d.g().entrySet()) {
            String str = (String) entry.getKey();
            for (String str2 : (List) entry.getValue()) {
                String str22;
                if (str22 == null) {
                    str22 = "";
                }
                stringBuilder.append(str);
                stringBuilder.append(": ");
                stringBuilder.append(str22);
                stringBuilder.append("\r\n");
            }
        }
    }

    private void b(StringBuilder stringBuilder) {
        String e = this.d.e();
        if (e != null && e.length() != 0) {
            String f = this.d.f();
            if (f == null) {
                f = "";
            }
            e = String.format("%s:%s", new Object[]{e, f});
            stringBuilder.append("Proxy-Authorization: Basic ");
            stringBuilder.append(b.a(e));
            stringBuilder.append("\r\n");
        }
    }

    private void e() throws IOException {
        InputStream inputStream = this.a.getInputStream();
        a(inputStream);
        b(inputStream);
    }

    private void a(InputStream inputStream) throws IOException {
        String a = p.a(inputStream, "UTF-8");
        if (a == null || a.length() == 0) {
            throw new IOException("The response from the proxy server does not contain a status line.");
        }
        String[] split = a.split(" +", 3);
        StringBuilder stringBuilder;
        if (split.length < 2) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("The status line in the response from the proxy server is badly formatted. The status line is: ");
            stringBuilder.append(a);
            throw new IOException(stringBuilder.toString());
        } else if (!"200".equals(split[1])) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("The status code in the response from the proxy server is not '200 Connection established'. The status line is: ");
            stringBuilder.append(a);
            throw new IOException(stringBuilder.toString());
        }
    }

    private void b(InputStream inputStream) throws IOException {
        while (true) {
            int i = 0;
            while (true) {
                int read = inputStream.read();
                if (read == -1) {
                    throw new EOFException("The end of the stream from the proxy server was reached unexpectedly.");
                } else if (read == 10) {
                    if (i == 0) {
                        return;
                    }
                } else if (read != 13) {
                    i++;
                } else {
                    read = inputStream.read();
                    if (read == -1) {
                        throw new EOFException("The end of the stream from the proxy server was reached unexpectedly after a carriage return.");
                    } else if (read != 10) {
                        i += 2;
                    } else if (i == 0) {
                        return;
                    }
                }
            }
        }
    }

    /* Access modifiers changed, original: 0000 */
    public String b() {
        return this.b;
    }
}
