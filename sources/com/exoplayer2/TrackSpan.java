package com.exoplayer2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.Serializable;

public class TrackSpan implements Serializable {
    private String a;
    private long b;

    public TrackSpan(String str, long j) {
        this.a = str;
        this.b = j;
    }

    public TrackSpan(DataInputStream dataInputStream) throws IOException {
        this(dataInputStream.readUTF(), dataInputStream.readLong());
    }

    public String a() {
        return this.a;
    }

    public long b() {
        return this.b;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer(" trackID : ");
        stringBuffer.append(this.a);
        stringBuffer.append(" lastAccessTimeStamp : ");
        stringBuffer.append(this.b);
        return stringBuffer.toString();
    }

    public void a(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeUTF(a());
        dataOutputStream.writeLong(b());
    }
}
