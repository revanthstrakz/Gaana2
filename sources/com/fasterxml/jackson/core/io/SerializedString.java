package com.fasterxml.jackson.core.io;

import com.fasterxml.jackson.core.d;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class SerializedString implements d, Serializable {
    protected final String a;
    protected byte[] b;
    protected transient String c;

    public SerializedString(String str) {
        if (str == null) {
            throw new IllegalStateException("Null String illegal for SerializedString");
        }
        this.a = str;
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException {
        this.c = objectInputStream.readUTF();
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.writeUTF(this.a);
    }

    /* Access modifiers changed, original: protected */
    public Object readResolve() {
        return new SerializedString(this.c);
    }

    public final String a() {
        return this.a;
    }

    public final byte[] b() {
        byte[] bArr = this.b;
        if (bArr != null) {
            return bArr;
        }
        bArr = d.a().a(this.a);
        this.b = bArr;
        return bArr;
    }

    public final String toString() {
        return this.a;
    }

    public final int hashCode() {
        return this.a.hashCode();
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        return this.a.equals(((SerializedString) obj).a);
    }
}
