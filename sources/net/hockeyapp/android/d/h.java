package net.hockeyapp.android.d;

import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

public class h {
    private static final char[] a = "-_1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    private boolean b = false;
    private boolean c = false;
    private ByteArrayOutputStream d = new ByteArrayOutputStream();
    private String e;

    public h() {
        int i = 0;
        StringBuffer stringBuffer = new StringBuffer();
        Random random = new Random();
        while (i < 30) {
            stringBuffer.append(a[random.nextInt(a.length)]);
            i++;
        }
        this.e = stringBuffer.toString();
    }

    public String a() {
        return this.e;
    }

    public void b() throws IOException {
        if (!this.c) {
            ByteArrayOutputStream byteArrayOutputStream = this.d;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("--");
            stringBuilder.append(this.e);
            stringBuilder.append("\r\n");
            byteArrayOutputStream.write(stringBuilder.toString().getBytes());
        }
        this.c = true;
    }

    public void c() {
        if (!this.b) {
            try {
                ByteArrayOutputStream byteArrayOutputStream = this.d;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("\r\n--");
                stringBuilder.append(this.e);
                stringBuilder.append("--\r\n");
                byteArrayOutputStream.write(stringBuilder.toString().getBytes());
            } catch (IOException e) {
                ThrowableExtension.printStackTrace(e);
            }
            this.b = true;
        }
    }

    public void a(String str, String str2) throws IOException {
        b();
        ByteArrayOutputStream byteArrayOutputStream = this.d;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Content-Disposition: form-data; name=\"");
        stringBuilder.append(str);
        stringBuilder.append("\"\r\n");
        byteArrayOutputStream.write(stringBuilder.toString().getBytes());
        this.d.write("Content-Type: text/plain; charset=UTF-8\r\n".getBytes());
        this.d.write("Content-Transfer-Encoding: 8bit\r\n\r\n".getBytes());
        this.d.write(str2.getBytes());
        ByteArrayOutputStream byteArrayOutputStream2 = this.d;
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("\r\n--");
        stringBuilder2.append(this.e);
        stringBuilder2.append("\r\n");
        byteArrayOutputStream2.write(stringBuilder2.toString().getBytes());
    }

    public void a(String str, String str2, InputStream inputStream, boolean z) throws IOException {
        a(str, str2, inputStream, "application/octet-stream", z);
    }

    public void a(String str, String str2, InputStream inputStream, String str3, boolean z) throws IOException {
        b();
        try {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Content-Type: ");
            stringBuilder.append(str3);
            stringBuilder.append("\r\n");
            str3 = stringBuilder.toString();
            ByteArrayOutputStream byteArrayOutputStream = this.d;
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("Content-Disposition: form-data; name=\"");
            stringBuilder2.append(str);
            stringBuilder2.append("\"; filename=\"");
            stringBuilder2.append(str2);
            stringBuilder2.append("\"\r\n");
            byteArrayOutputStream.write(stringBuilder2.toString().getBytes());
            this.d.write(str3.getBytes());
            this.d.write("Content-Transfer-Encoding: binary\r\n\r\n".getBytes());
            byte[] bArr = new byte[4096];
            while (true) {
                int read = inputStream.read(bArr);
                if (read == -1) {
                    break;
                }
                this.d.write(bArr, 0, read);
            }
            this.d.flush();
            if (z) {
                c();
            } else {
                ByteArrayOutputStream byteArrayOutputStream2 = this.d;
                StringBuilder stringBuilder3 = new StringBuilder();
                stringBuilder3.append("\r\n--");
                stringBuilder3.append(this.e);
                stringBuilder3.append("\r\n");
                byteArrayOutputStream2.write(stringBuilder3.toString().getBytes());
            }
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                ThrowableExtension.printStackTrace(e);
            }
        }
    }

    public long d() {
        c();
        return (long) this.d.toByteArray().length;
    }

    public ByteArrayOutputStream e() {
        c();
        return this.d;
    }
}
