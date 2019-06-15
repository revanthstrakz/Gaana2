package androidx.work;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.annotation.VisibleForTesting;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public final class d {
    public static final d a = new a().a();
    private static final String c = f.a("Data");
    Map<String, Object> b;

    public static final class a {
        private Map<String, Object> a = new HashMap();

        @NonNull
        public a a(@NonNull String str, @Nullable String str2) {
            this.a.put(str, str2);
            return this;
        }

        @NonNull
        public a a(@NonNull d dVar) {
            a(dVar.b);
            return this;
        }

        @NonNull
        public a a(@NonNull Map<String, Object> map) {
            for (Entry entry : map.entrySet()) {
                a((String) entry.getKey(), entry.getValue());
            }
            return this;
        }

        @RestrictTo({Scope.LIBRARY_GROUP})
        @NonNull
        public a a(@NonNull String str, @Nullable Object obj) {
            if (obj == null) {
                this.a.put(str, null);
            } else {
                Class cls = obj.getClass();
                if (cls == Boolean.class || cls == Integer.class || cls == Long.class || cls == Float.class || cls == Double.class || cls == String.class || cls == Boolean[].class || cls == Integer[].class || cls == Long[].class || cls == Float[].class || cls == Double[].class || cls == String[].class) {
                    this.a.put(str, obj);
                } else if (cls == boolean[].class) {
                    this.a.put(str, d.a((boolean[]) obj));
                } else if (cls == int[].class) {
                    this.a.put(str, d.a((int[]) obj));
                } else if (cls == long[].class) {
                    this.a.put(str, d.a((long[]) obj));
                } else if (cls == float[].class) {
                    this.a.put(str, d.a((float[]) obj));
                } else if (cls == double[].class) {
                    this.a.put(str, d.a((double[]) obj));
                } else {
                    throw new IllegalArgumentException(String.format("Key %s has invalid type %s", new Object[]{str, cls}));
                }
            }
            return this;
        }

        @NonNull
        public d a() {
            d dVar = new d(this.a);
            d.a(dVar);
            return dVar;
        }
    }

    d() {
    }

    public d(@NonNull d dVar) {
        this.b = new HashMap(dVar.b);
    }

    d(@NonNull Map<String, ?> map) {
        this.b = new HashMap(map);
    }

    @Nullable
    public String a(@NonNull String str) {
        Object obj = this.b.get(str);
        return obj instanceof String ? (String) obj : null;
    }

    @NonNull
    public Map<String, Object> a() {
        return Collections.unmodifiableMap(this.b);
    }

    @VisibleForTesting
    @RestrictTo({Scope.LIBRARY_GROUP})
    public int b() {
        return this.b.size();
    }

    /* JADX WARNING: Removed duplicated region for block: B:33:0x007e A:{SYNTHETIC, Splitter:B:33:0x007e} */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x0099 A:{SYNTHETIC, Splitter:B:43:0x0099} */
    @android.support.annotation.RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
    @android.support.annotation.NonNull
    public static byte[] a(@android.support.annotation.NonNull androidx.work.d r4) throws java.lang.IllegalStateException {
        /*
        r0 = new java.io.ByteArrayOutputStream;
        r0.<init>();
        r1 = 0;
        r2 = new java.io.ObjectOutputStream;	 Catch:{ IOException -> 0x0070 }
        r2.<init>(r0);	 Catch:{ IOException -> 0x0070 }
        r1 = r4.b();	 Catch:{ IOException -> 0x006a, all -> 0x0068 }
        r2.writeInt(r1);	 Catch:{ IOException -> 0x006a, all -> 0x0068 }
        r4 = r4.b;	 Catch:{ IOException -> 0x006a, all -> 0x0068 }
        r4 = r4.entrySet();	 Catch:{ IOException -> 0x006a, all -> 0x0068 }
        r4 = r4.iterator();	 Catch:{ IOException -> 0x006a, all -> 0x0068 }
    L_0x001c:
        r1 = r4.hasNext();	 Catch:{ IOException -> 0x006a, all -> 0x0068 }
        if (r1 == 0) goto L_0x0039;
    L_0x0022:
        r1 = r4.next();	 Catch:{ IOException -> 0x006a, all -> 0x0068 }
        r1 = (java.util.Map.Entry) r1;	 Catch:{ IOException -> 0x006a, all -> 0x0068 }
        r3 = r1.getKey();	 Catch:{ IOException -> 0x006a, all -> 0x0068 }
        r3 = (java.lang.String) r3;	 Catch:{ IOException -> 0x006a, all -> 0x0068 }
        r2.writeUTF(r3);	 Catch:{ IOException -> 0x006a, all -> 0x0068 }
        r1 = r1.getValue();	 Catch:{ IOException -> 0x006a, all -> 0x0068 }
        r2.writeObject(r1);	 Catch:{ IOException -> 0x006a, all -> 0x0068 }
        goto L_0x001c;
    L_0x0039:
        if (r2 == 0) goto L_0x0047;
    L_0x003b:
        r2.close();	 Catch:{ IOException -> 0x003f }
        goto L_0x0047;
    L_0x003f:
        r4 = move-exception;
        r1 = c;
        r2 = "Error in Data#toByteArray: ";
        android.util.Log.e(r1, r2, r4);
    L_0x0047:
        r0.close();	 Catch:{ IOException -> 0x004b }
        goto L_0x0053;
    L_0x004b:
        r4 = move-exception;
        r1 = c;
        r2 = "Error in Data#toByteArray: ";
        android.util.Log.e(r1, r2, r4);
    L_0x0053:
        r4 = r0.size();
        r1 = 10240; // 0x2800 float:1.4349E-41 double:5.059E-320;
        if (r4 <= r1) goto L_0x0063;
    L_0x005b:
        r4 = new java.lang.IllegalStateException;
        r0 = "Data cannot occupy more than 10240 bytes when serialized";
        r4.<init>(r0);
        throw r4;
    L_0x0063:
        r4 = r0.toByteArray();
        return r4;
    L_0x0068:
        r4 = move-exception;
        goto L_0x0097;
    L_0x006a:
        r4 = move-exception;
        r1 = r2;
        goto L_0x0071;
    L_0x006d:
        r4 = move-exception;
        r2 = r1;
        goto L_0x0097;
    L_0x0070:
        r4 = move-exception;
    L_0x0071:
        r2 = c;	 Catch:{ all -> 0x006d }
        r3 = "Error in Data#toByteArray: ";
        android.util.Log.e(r2, r3, r4);	 Catch:{ all -> 0x006d }
        r4 = r0.toByteArray();	 Catch:{ all -> 0x006d }
        if (r1 == 0) goto L_0x008a;
    L_0x007e:
        r1.close();	 Catch:{ IOException -> 0x0082 }
        goto L_0x008a;
    L_0x0082:
        r1 = move-exception;
        r2 = c;
        r3 = "Error in Data#toByteArray: ";
        android.util.Log.e(r2, r3, r1);
    L_0x008a:
        r0.close();	 Catch:{ IOException -> 0x008e }
        goto L_0x0096;
    L_0x008e:
        r0 = move-exception;
        r1 = c;
        r2 = "Error in Data#toByteArray: ";
        android.util.Log.e(r1, r2, r0);
    L_0x0096:
        return r4;
    L_0x0097:
        if (r2 == 0) goto L_0x00a5;
    L_0x0099:
        r2.close();	 Catch:{ IOException -> 0x009d }
        goto L_0x00a5;
    L_0x009d:
        r1 = move-exception;
        r2 = c;
        r3 = "Error in Data#toByteArray: ";
        android.util.Log.e(r2, r3, r1);
    L_0x00a5:
        r0.close();	 Catch:{ IOException -> 0x00a9 }
        goto L_0x00b1;
    L_0x00a9:
        r0 = move-exception;
        r1 = c;
        r2 = "Error in Data#toByteArray: ";
        android.util.Log.e(r1, r2, r0);
    L_0x00b1:
        throw r4;
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.work.d.a(androidx.work.d):byte[]");
    }

    /* JADX WARNING: Unknown top exception splitter block from list: {B:31:0x0062=Splitter:B:31:0x0062, B:17:0x003f=Splitter:B:17:0x003f} */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0056 A:{SYNTHETIC, Splitter:B:27:0x0056} */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0077 A:{SYNTHETIC, Splitter:B:39:0x0077} */
    @android.support.annotation.RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
    @android.support.annotation.NonNull
    public static androidx.work.d a(@android.support.annotation.NonNull byte[] r6) throws java.lang.IllegalStateException {
        /*
        r0 = 10240; // 0x2800 float:1.4349E-41 double:5.059E-320;
        r1 = r6.length;
        if (r1 <= r0) goto L_0x000d;
    L_0x0005:
        r6 = new java.lang.IllegalStateException;
        r0 = "Data cannot occupy more than 10240 bytes when serialized";
        r6.<init>(r0);
        throw r6;
    L_0x000d:
        r0 = new java.util.HashMap;
        r0.<init>();
        r1 = new java.io.ByteArrayInputStream;
        r1.<init>(r6);
        r6 = 0;
        r2 = new java.io.ObjectInputStream;	 Catch:{ IOException | ClassNotFoundException -> 0x0049, IOException | ClassNotFoundException -> 0x0049, all -> 0x0045 }
        r2.<init>(r1);	 Catch:{ IOException | ClassNotFoundException -> 0x0049, IOException | ClassNotFoundException -> 0x0049, all -> 0x0045 }
        r6 = r2.readInt();	 Catch:{ IOException | ClassNotFoundException -> 0x0043, IOException | ClassNotFoundException -> 0x0043 }
    L_0x0021:
        if (r6 <= 0) goto L_0x0031;
    L_0x0023:
        r3 = r2.readUTF();	 Catch:{ IOException | ClassNotFoundException -> 0x0043, IOException | ClassNotFoundException -> 0x0043 }
        r4 = r2.readObject();	 Catch:{ IOException | ClassNotFoundException -> 0x0043, IOException | ClassNotFoundException -> 0x0043 }
        r0.put(r3, r4);	 Catch:{ IOException | ClassNotFoundException -> 0x0043, IOException | ClassNotFoundException -> 0x0043 }
        r6 = r6 + -1;
        goto L_0x0021;
    L_0x0031:
        if (r2 == 0) goto L_0x003f;
    L_0x0033:
        r2.close();	 Catch:{ IOException -> 0x0037 }
        goto L_0x003f;
    L_0x0037:
        r6 = move-exception;
        r2 = c;
        r3 = "Error in Data#fromByteArray: ";
        android.util.Log.e(r2, r3, r6);
    L_0x003f:
        r1.close();	 Catch:{ IOException -> 0x0066 }
        goto L_0x006e;
    L_0x0043:
        r6 = move-exception;
        goto L_0x004d;
    L_0x0045:
        r0 = move-exception;
        r2 = r6;
        r6 = r0;
        goto L_0x0075;
    L_0x0049:
        r2 = move-exception;
        r5 = r2;
        r2 = r6;
        r6 = r5;
    L_0x004d:
        r3 = c;	 Catch:{ all -> 0x0074 }
        r4 = "Error in Data#fromByteArray: ";
        android.util.Log.e(r3, r4, r6);	 Catch:{ all -> 0x0074 }
        if (r2 == 0) goto L_0x0062;
    L_0x0056:
        r2.close();	 Catch:{ IOException -> 0x005a }
        goto L_0x0062;
    L_0x005a:
        r6 = move-exception;
        r2 = c;
        r3 = "Error in Data#fromByteArray: ";
        android.util.Log.e(r2, r3, r6);
    L_0x0062:
        r1.close();	 Catch:{ IOException -> 0x0066 }
        goto L_0x006e;
    L_0x0066:
        r6 = move-exception;
        r1 = c;
        r2 = "Error in Data#fromByteArray: ";
        android.util.Log.e(r1, r2, r6);
    L_0x006e:
        r6 = new androidx.work.d;
        r6.<init>(r0);
        return r6;
    L_0x0074:
        r6 = move-exception;
    L_0x0075:
        if (r2 == 0) goto L_0x0083;
    L_0x0077:
        r2.close();	 Catch:{ IOException -> 0x007b }
        goto L_0x0083;
    L_0x007b:
        r0 = move-exception;
        r2 = c;
        r3 = "Error in Data#fromByteArray: ";
        android.util.Log.e(r2, r3, r0);
    L_0x0083:
        r1.close();	 Catch:{ IOException -> 0x0087 }
        goto L_0x008f;
    L_0x0087:
        r0 = move-exception;
        r1 = c;
        r2 = "Error in Data#fromByteArray: ";
        android.util.Log.e(r1, r2, r0);
    L_0x008f:
        throw r6;
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.work.d.a(byte[]):androidx.work.d");
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.b.equals(((d) obj).b);
    }

    public int hashCode() {
        return 31 * this.b.hashCode();
    }

    @NonNull
    static Boolean[] a(@NonNull boolean[] zArr) {
        Boolean[] boolArr = new Boolean[zArr.length];
        for (int i = 0; i < zArr.length; i++) {
            boolArr[i] = Boolean.valueOf(zArr[i]);
        }
        return boolArr;
    }

    @NonNull
    static Integer[] a(@NonNull int[] iArr) {
        Integer[] numArr = new Integer[iArr.length];
        for (int i = 0; i < iArr.length; i++) {
            numArr[i] = Integer.valueOf(iArr[i]);
        }
        return numArr;
    }

    @NonNull
    static Long[] a(@NonNull long[] jArr) {
        Long[] lArr = new Long[jArr.length];
        for (int i = 0; i < jArr.length; i++) {
            lArr[i] = Long.valueOf(jArr[i]);
        }
        return lArr;
    }

    @NonNull
    static Float[] a(@NonNull float[] fArr) {
        Float[] fArr2 = new Float[fArr.length];
        for (int i = 0; i < fArr.length; i++) {
            fArr2[i] = Float.valueOf(fArr[i]);
        }
        return fArr2;
    }

    @NonNull
    static Double[] a(@NonNull double[] dArr) {
        Double[] dArr2 = new Double[dArr.length];
        for (int i = 0; i < dArr.length; i++) {
            dArr2[i] = Double.valueOf(dArr[i]);
        }
        return dArr2;
    }
}
