package com.helpshift.campaigns.models;

import android.text.TextUtils;
import com.google.android.gms.cast.HlsSegmentFormat;
import com.google.api.client.http.HttpStatusCodes;
import com.helpshift.campaigns.c.b;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;

public class AnalyticsEvent implements Serializable {
    private static final long serialVersionUID = 8930869772164604416L;
    public String a;
    public String b;
    public Long c;
    public String d = b.a().d.a().a;
    public Integer e;
    public Boolean f;

    public static class a {
        public static final Integer a = Integer.valueOf(0);
        public static final Integer b = Integer.valueOf(1);
        public static final Integer c = Integer.valueOf(2);
        public static final Integer d = Integer.valueOf(5);
        public static final Integer e = Integer.valueOf(6);
        public static final Integer f = Integer.valueOf(8);
        static final Integer[] g = new Integer[]{Integer.valueOf(HttpStatusCodes.STATUS_CODE_CREATED), Integer.valueOf(202), Integer.valueOf(203), Integer.valueOf(HttpStatusCodes.STATUS_CODE_NO_CONTENT)};
    }

    public AnalyticsEvent(Integer num, String str, Boolean bool) {
        long currentTimeMillis = System.currentTimeMillis();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("__hs_ae_");
        stringBuilder.append(str);
        stringBuilder.append("_");
        stringBuilder.append(currentTimeMillis);
        this.a = stringBuilder.toString();
        this.b = str;
        this.c = Long.valueOf(currentTimeMillis);
        this.e = num;
        this.f = bool;
    }

    public HashMap a() {
        HashMap hashMap = new HashMap();
        Object e = com.helpshift.k.b.a().b.e(this.b);
        if (TextUtils.isEmpty(e)) {
            e = this.b;
        }
        hashMap.put("cid", e);
        hashMap.put("uid", this.d);
        hashMap.put(HlsSegmentFormat.TS, this.c);
        hashMap.put("t", this.e);
        hashMap.put("g", this.f);
        hashMap.put("v", Integer.valueOf(1));
        return hashMap;
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeUTF(this.a);
        objectOutputStream.writeUTF(this.b);
        objectOutputStream.writeLong(this.c.longValue());
        objectOutputStream.writeUTF(this.d);
        objectOutputStream.writeInt(this.e.intValue());
        objectOutputStream.writeBoolean(this.f.booleanValue());
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        this.a = objectInputStream.readUTF();
        this.b = objectInputStream.readUTF();
        this.c = Long.valueOf(objectInputStream.readLong());
        this.d = objectInputStream.readUTF();
        this.e = Integer.valueOf(objectInputStream.readInt());
        try {
            this.f = Boolean.valueOf(objectInputStream.readBoolean());
        } catch (EOFException unused) {
            this.f = Boolean.valueOf(false);
        }
    }
}
