package com.i;

import android.text.TextUtils;
import com.android.volley.AuthFailureError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Request.Priority;
import com.android.volley.g;
import com.android.volley.i;
import com.android.volley.i.a;
import com.android.volley.i.b;
import com.android.volley.k;
import com.android.volley.toolbox.d;
import com.gaana.models.BusinessObject;
import com.gaana.models.FlatBufferHelper;
import com.gaana.models.Item;
import com.google.android.exoplayer2.extractor.ts.PsExtractor;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.managers.URLManager;
import com.utilities.Util;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.zip.GZIPInputStream;
import net.jpountz.lz4.LZ4Factory;
import net.jpountz.lz4.LZ4FastDecompressor;
import org.apache.http.entity.mime.MIME;

public class c extends Request<Object> {
    public boolean a = true;
    private Class<?> b;
    private b<Object> c;
    private com.android.volley.i.c<Object> d;
    private Priority e = Priority.NORMAL;
    private String f;
    private int g = PsExtractor.VIDEO_STREAM_MASK;
    private int h = 1440;
    private String i;
    private URLManager j;
    private boolean k = false;
    private boolean l = false;
    private String m;
    private boolean n = false;
    private int o = 0;

    public void a(boolean z) {
        this.k = z;
    }

    public void a(URLManager uRLManager) {
        this.j = uRLManager;
    }

    public c(int i, String str, Class<?> cls, b<Object> bVar, a aVar, com.android.volley.i.c cVar) {
        super(i, str, aVar);
        this.m = str;
        this.b = cls;
        this.c = bVar;
        this.d = cVar;
    }

    public void a(int i) {
        this.g = i;
    }

    public k getRetryPolicy() {
        return super.getRetryPolicy();
    }

    public void a(String str) {
        this.i = str;
    }

    public String getCacheKey() {
        return this.i;
    }

    private Object b(g gVar) throws IOException {
        return FlatBufferHelper.getModelData(c(gVar), this.b);
    }

    /* Access modifiers changed, original: protected */
    public i<Object> parseNetworkResponse(g gVar) {
        boolean z = false;
        try {
            if (!(gVar.c == null || gVar.c.get(MIME.CONTENT_TYPE) == null)) {
                z = ((String) gVar.c.get(MIME.CONTENT_TYPE)).equalsIgnoreCase("application/x-fb");
                ((String) gVar.c.get(MIME.CONTENT_TYPE)).equalsIgnoreCase("text/plain");
            }
            Object b;
            if (z) {
                b = b(gVar);
                if (b == null) {
                    return i.a(new ParseError());
                }
                if (this.k) {
                    return i.a(a(b), a(gVar));
                }
                return i.a(b(b), a(gVar));
            }
            String d = d(gVar);
            if (this.b == null || this.b == String.class) {
                return i.a(d, a(gVar));
            }
            b = new GsonBuilder().excludeFieldsWithModifiers(8, 4).create().fromJson(d, this.b);
            if (this.k) {
                return i.a(a(b), a(gVar));
            }
            return i.a(b(b), a(gVar));
        } catch (JsonSyntaxException e) {
            return i.a(new ParseError(e));
        } catch (IOException e2) {
            return i.a(new ParseError(e2));
        }
    }

    public com.android.volley.a.a a(g gVar) {
        if (!shouldCache()) {
            return d.a(gVar);
        }
        long currentTimeMillis = System.currentTimeMillis();
        Map map = gVar.c;
        long j = 0;
        String str = (String) map.get("Date");
        if (str != null) {
            j = d.a(str);
        }
        str = (String) map.get("ETag");
        long j2 = currentTimeMillis + ((long) ((this.g * 60) * 1000));
        long j3 = currentTimeMillis + ((long) ((this.h * 60) * 1000));
        com.android.volley.a.a aVar = new com.android.volley.a.a();
        aVar.a = gVar.b;
        aVar.b = str;
        aVar.f = j2;
        aVar.e = j3;
        aVar.c = j;
        aVar.g = map;
        return aVar;
    }

    /* JADX WARNING: Removed duplicated region for block: B:30:0x00a1 A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x00a0 A:{RETURN} */
    private java.lang.Object b(java.lang.Object r5) {
        /*
        r4 = this;
        r0 = r4.j;
        if (r0 == 0) goto L_0x009d;
    L_0x0004:
        r0 = r5 instanceof com.gaana.models.BusinessObject;
        if (r0 == 0) goto L_0x009d;
    L_0x0008:
        r0 = r5;
        r0 = (com.gaana.models.BusinessObject) r0;
        r1 = r0.getVolleyError();
        if (r1 != 0) goto L_0x009d;
    L_0x0011:
        r1 = r4.j;
        r1 = r1.i();
        r0.setBusinessObjType(r1);
        r1 = r0.getArrListBusinessObj();
        if (r1 == 0) goto L_0x004f;
    L_0x0020:
        r1 = r0.getArrListBusinessObj();
        r1 = r1.iterator();
    L_0x0028:
        r2 = r1.hasNext();
        if (r2 == 0) goto L_0x008d;
    L_0x002e:
        r2 = r1.next();
        r2 = (com.gaana.models.BusinessObject) r2;
        r3 = r4.j;
        r3 = r3.i();
        r2.setBusinessObjType(r3);
        r3 = r4.j;
        r3 = r3.l();
        if (r3 == 0) goto L_0x0028;
    L_0x0045:
        r3 = r4.j;
        r3 = r3.l();
        r2.setParentBusinessObjType(r3);
        goto L_0x0028;
    L_0x004f:
        r1 = r0 instanceof com.gaana.revampeddetail.model.RevampedDetailObject;
        if (r1 == 0) goto L_0x008d;
    L_0x0053:
        r1 = r0;
        r1 = (com.gaana.revampeddetail.model.RevampedDetailObject) r1;
        r1 = r1.getTrackListifAvailable();
        if (r1 == 0) goto L_0x008d;
    L_0x005c:
        r2 = r1.size();
        if (r2 <= 0) goto L_0x008d;
    L_0x0062:
        r1 = r1.iterator();
    L_0x0066:
        r2 = r1.hasNext();
        if (r2 == 0) goto L_0x008d;
    L_0x006c:
        r2 = r1.next();
        r2 = (com.gaana.models.BusinessObject) r2;
        r3 = r4.j;
        r3 = r3.i();
        r2.setBusinessObjType(r3);
        r3 = r4.j;
        r3 = r3.l();
        if (r3 == 0) goto L_0x0066;
    L_0x0083:
        r3 = r4.j;
        r3 = r3.l();
        r2.setParentBusinessObjType(r3);
        goto L_0x0066;
    L_0x008d:
        r1 = r4.j;
        r2 = 0;
        r2 = java.lang.Boolean.valueOf(r2);
        r1.c(r2);
        r1 = r4.j;
        r0.setUrlManager(r1);
        goto L_0x009e;
    L_0x009d:
        r0 = 0;
    L_0x009e:
        if (r0 == 0) goto L_0x00a1;
    L_0x00a0:
        return r0;
    L_0x00a1:
        return r5;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.i.c.b(java.lang.Object):java.lang.Object");
    }

    private byte[] c(g gVar) throws IOException {
        String str = (String) gVar.c.get("Content-Encoding");
        if (TextUtils.isEmpty(str)) {
            str = (String) gVar.c.get("content-encoding");
        }
        if (str != null && str.equalsIgnoreCase("lz4")) {
            LZ4Factory fastestInstance = LZ4Factory.fastestInstance();
            int intValue = Integer.valueOf((String) gVar.c.get("orig-length")).intValue();
            LZ4FastDecompressor fastDecompressor = fastestInstance.fastDecompressor();
            byte[] bArr = new byte[intValue];
            fastDecompressor.decompress(gVar.b, 0, bArr, 0, intValue);
            return bArr;
        } else if (str == null || !str.equals("gzip")) {
            return gVar.b;
        } else {
            GZIPInputStream gZIPInputStream = new GZIPInputStream(new ByteArrayInputStream(gVar.b));
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr2 = new byte[1024];
            int i = 0;
            while (i >= 0) {
                i = gZIPInputStream.read(bArr2, 0, bArr2.length);
                if (i > 0) {
                    byteArrayOutputStream.write(bArr2, 0, i);
                }
            }
            return byteArrayOutputStream.toByteArray();
        }
    }

    private String d(g gVar) throws JsonSyntaxException, IOException {
        String str = (String) gVar.c.get("Content-Encoding");
        if (str == null || !str.equalsIgnoreCase("gzip")) {
            return new String(gVar.b, d.a(gVar.c));
        }
        GZIPInputStream gZIPInputStream = new GZIPInputStream(new ByteArrayInputStream(gVar.b));
        InputStreamReader inputStreamReader = new InputStreamReader(gZIPInputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        StringBuilder stringBuilder = new StringBuilder();
        while (true) {
            String readLine = bufferedReader.readLine();
            if (readLine != null) {
                stringBuilder.append(readLine);
            } else {
                String stringBuilder2 = stringBuilder.toString();
                inputStreamReader.close();
                bufferedReader.close();
                gZIPInputStream.close();
                return stringBuilder2;
            }
        }
    }

    public Map<String, String> getHeaders() throws AuthFailureError {
        return j.a().a(this);
    }

    /* Access modifiers changed, original: protected */
    public void deliverResponse(Object obj, boolean z) {
        if (this.c != null) {
            this.c.onResponse(obj);
        }
        if (this.d != null) {
            this.d.a(obj, z);
        }
    }

    /* Access modifiers changed, original: protected */
    public void deliverResponse(Object obj) {
        if (this.c != null) {
            this.c.onResponse(obj);
        }
    }

    public Priority getPriority() {
        return this.e;
    }

    public void a(Priority priority) {
        this.e = priority;
    }

    public void b(String str) {
        this.f = str;
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x005a A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0059 A:{RETURN} */
    public java.lang.Object a(java.lang.Object r5) {
        /*
        r4 = this;
        r0 = r5 instanceof com.gaana.models.BusinessObject;
        if (r0 == 0) goto L_0x0056;
    L_0x0004:
        r0 = r5;
        r0 = (com.gaana.models.BusinessObject) r0;
        r1 = r0.getVolleyError();
        if (r1 != 0) goto L_0x0056;
    L_0x000d:
        r1 = new com.gaana.models.Tracks;
        r1.<init>();
        r2 = r5 instanceof com.gaana.models.Items;
        if (r2 == 0) goto L_0x002a;
    L_0x0016:
        r2 = com.gaana.application.GaanaApplication.getInstance();
        r2 = r2.isAppLaucnhedFromDeeplinking();
        if (r2 == 0) goto L_0x002a;
    L_0x0020:
        r2 = r5;
        r2 = (com.gaana.models.Items) r2;
        r2 = r2.getRawTagDescription();
        r1.setName(r2);
    L_0x002a:
        r2 = com.managers.URLManager.BusinessObjectType.Tracks;
        r1.setBusinessObjType(r2);
        r0 = r0.getArrListBusinessObj();
        if (r0 == 0) goto L_0x0057;
    L_0x0035:
        r2 = new java.util.ArrayList;
        r2.<init>();
        r0 = r0.iterator();
    L_0x003e:
        r3 = r0.hasNext();
        if (r3 == 0) goto L_0x0052;
    L_0x0044:
        r3 = r0.next();
        r3 = (com.gaana.models.Item) r3;
        r3 = r4.a(r3);
        r2.add(r3);
        goto L_0x003e;
    L_0x0052:
        r1.setArrListBusinessObj(r2);
        goto L_0x0057;
    L_0x0056:
        r1 = 0;
    L_0x0057:
        if (r1 == 0) goto L_0x005a;
    L_0x0059:
        return r1;
    L_0x005a:
        return r5;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.i.c.a(java.lang.Object):java.lang.Object");
    }

    /* Access modifiers changed, original: protected */
    public BusinessObject a(Item item) {
        return Util.g(item);
    }

    public boolean a() {
        return this.a;
    }

    public void b(boolean z) {
        this.a = z;
    }

    public void c(boolean z) {
        this.l = z;
    }

    public void d(boolean z) {
        this.n = z;
    }

    public boolean b() {
        return this.n;
    }
}
