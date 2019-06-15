package com.exoplayer2.upstream.cache;

import android.support.annotation.Nullable;
import com.google.api.client.googleapis.media.MediaHttpUploader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public final class l implements i {
    public static final l a = new l(Collections.emptyMap());
    private int b;
    private final Map<String, byte[]> c;

    public static l a(DataInputStream dataInputStream) throws IOException {
        int readInt = dataInputStream.readInt();
        HashMap hashMap = new HashMap();
        for (int i = 0; i < readInt; i++) {
            String readUTF = dataInputStream.readUTF();
            int readInt2 = dataInputStream.readInt();
            if (readInt2 < 0 || readInt2 > MediaHttpUploader.DEFAULT_CHUNK_SIZE) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Invalid value size: ");
                stringBuilder.append(readInt2);
                throw new IOException(stringBuilder.toString());
            }
            byte[] bArr = new byte[readInt2];
            dataInputStream.readFully(bArr);
            hashMap.put(readUTF, bArr);
        }
        return new l(hashMap);
    }

    private l(Map<String, byte[]> map) {
        this.c = Collections.unmodifiableMap(map);
    }

    public l a(k kVar) {
        Map a = a(this.c, kVar);
        if (a(a)) {
            return this;
        }
        return new l(a);
    }

    public void a(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeInt(this.c.size());
        for (Entry entry : this.c.entrySet()) {
            dataOutputStream.writeUTF((String) entry.getKey());
            byte[] bArr = (byte[]) entry.getValue();
            dataOutputStream.writeInt(bArr.length);
            dataOutputStream.write(bArr);
        }
    }

    public final String a(String str, String str2) {
        return this.c.containsKey(str) ? new String((byte[]) this.c.get(str), Charset.forName("UTF-8")) : str2;
    }

    public final long a(String str, long j) {
        return this.c.containsKey(str) ? ByteBuffer.wrap((byte[]) this.c.get(str)).getLong() : j;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj == null || getClass() != obj.getClass()) ? false : a(((l) obj).c);
    }

    private boolean a(Map<String, byte[]> map) {
        if (this.c.size() != map.size()) {
            return false;
        }
        for (Entry entry : this.c.entrySet()) {
            if (!Arrays.equals((byte[]) entry.getValue(), (byte[]) map.get(entry.getKey()))) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        if (this.b == 0) {
            int i = 0;
            for (Entry entry : this.c.entrySet()) {
                i += Arrays.hashCode((byte[]) entry.getValue()) ^ ((String) entry.getKey()).hashCode();
            }
            this.b = i;
        }
        return this.b;
    }

    private static Map<String, byte[]> a(Map<String, byte[]> map, k kVar) {
        HashMap hashMap = new HashMap(map);
        a(hashMap, kVar.a());
        a(hashMap, kVar.b());
        return hashMap;
    }

    private static void a(HashMap<String, byte[]> hashMap, List<String> list) {
        for (int i = 0; i < list.size(); i++) {
            hashMap.remove(list.get(i));
        }
    }

    private static void a(HashMap<String, byte[]> hashMap, Map<String, Object> map) {
        for (String str : map.keySet()) {
            byte[] a = a(map.get(str));
            if (a.length > MediaHttpUploader.DEFAULT_CHUNK_SIZE) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("The size of ");
                stringBuilder.append(str);
                stringBuilder.append(" (");
                stringBuilder.append(a.length);
                stringBuilder.append(") is greater than maximum allowed: ");
                stringBuilder.append(MediaHttpUploader.DEFAULT_CHUNK_SIZE);
                throw new IllegalArgumentException(stringBuilder.toString());
            }
            hashMap.put(str, a);
        }
    }

    private static byte[] a(Object obj) {
        if (obj instanceof Long) {
            return ByteBuffer.allocate(8).putLong(((Long) obj).longValue()).array();
        }
        if (obj instanceof String) {
            return ((String) obj).getBytes(Charset.forName("UTF-8"));
        }
        if (obj instanceof byte[]) {
            return (byte[]) obj;
        }
        throw new IllegalArgumentException();
    }
}
