package com.facebook.ads.internal.r.a;

import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import org.apache.http.entity.mime.MIME;

public abstract class f implements q {
    private final r a;

    public f() {
        this(new g());
    }

    public f(r rVar) {
        this.a = rVar;
    }

    public OutputStream a(HttpURLConnection httpURLConnection) {
        return httpURLConnection.getOutputStream();
    }

    public HttpURLConnection a(String str) {
        return (HttpURLConnection) new URL(str).openConnection();
    }

    public void a(OutputStream outputStream, byte[] bArr) {
        outputStream.write(bArr);
    }

    public void a(HttpURLConnection httpURLConnection, j jVar, String str) {
        httpURLConnection.setRequestMethod(jVar.c());
        httpURLConnection.setDoOutput(jVar.b());
        httpURLConnection.setDoInput(jVar.a());
        if (str != null) {
            httpURLConnection.setRequestProperty(MIME.CONTENT_TYPE, str);
        }
        httpURLConnection.setRequestProperty("Accept-Charset", "UTF-8");
    }

    public boolean a(m mVar) {
        n a = mVar.a();
        if (this.a.a()) {
            this.a.a("BasicRequestHandler.onError got");
            ThrowableExtension.printStackTrace(mVar);
        }
        return a != null && a.a() > 0;
    }

    public byte[] a(InputStream inputStream) {
        byte[] bArr = new byte[16384];
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        while (true) {
            int read = inputStream.read(bArr);
            if (read != -1) {
                byteArrayOutputStream.write(bArr, 0, read);
            } else {
                byteArrayOutputStream.flush();
                return byteArrayOutputStream.toByteArray();
            }
        }
    }

    public InputStream b(HttpURLConnection httpURLConnection) {
        return httpURLConnection.getInputStream();
    }
}
