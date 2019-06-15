package com.exoplayer2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.Serializable;

public class CookieSpan implements Serializable {
    private String a;
    private String b;
    private long c;

    public CookieSpan(String str, String str2, long j) {
        this.a = str;
        this.b = str2;
        this.c = j;
    }

    public CookieSpan(DataInputStream dataInputStream) throws IOException {
        this(dataInputStream.readUTF(), dataInputStream.readUTF(), dataInputStream.readLong());
    }

    public String a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    public long c() {
        return this.c;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer(" trackUrl : ");
        stringBuffer.append(this.a);
        stringBuffer.append(" trackCookieString : ");
        stringBuffer.append(this.b);
        stringBuffer.append(" lastAccessTimeStamp : ");
        stringBuffer.append(this.c);
        return stringBuffer.toString();
    }

    public void a(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeUTF(a());
        dataOutputStream.writeUTF(b());
        dataOutputStream.writeLong(c());
    }
}
