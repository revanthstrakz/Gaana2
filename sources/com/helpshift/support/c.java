package com.helpshift.support;

import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.google.api.client.http.HttpMethods;
import com.helpshift.a.a.a.d;
import com.helpshift.common.domain.network.i;
import com.helpshift.exceptions.InstallException;
import com.helpshift.j.c.a;
import com.helpshift.network.g;
import com.helpshift.util.l;
import com.helpshift.util.o;
import com.helpshift.util.x;
import com.integralads.avid.library.inmobi.session.internal.InternalAvidAdSessionContext;
import com.integralads.avid.library.inmobi.utils.AvidJSONUtil;
import com.til.colombia.android.internal.e;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class c {
    static int a;
    private final String b;
    private final String c;
    private final String d;
    private g e;

    protected c(String str, String str2, String str3, g gVar) {
        this.c = str;
        this.b = str2;
        this.d = str3;
        this.e = gVar;
    }

    static void a(HttpURLConnection httpURLConnection) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Helpshift-Android/6.4.0/");
        stringBuilder.append(VERSION.RELEASE);
        String stringBuilder2 = stringBuilder.toString();
        String format = String.format("%s;q=1.0", new Object[]{o.d().t().e()});
        httpURLConnection.setConnectTimeout(5000);
        httpURLConnection.setRequestProperty(e.c, stringBuilder2);
        httpURLConnection.setRequestProperty("Accept-Language", format);
        httpURLConnection.setRequestProperty("Accept-Encoding", "gzip");
        httpURLConnection.setRequestProperty("X-HS-V", "Helpshift-Android/6.4.0");
    }

    static String a(List<g> list) {
        StringBuilder stringBuilder = new StringBuilder();
        Object obj = 1;
        for (g gVar : list) {
            if (obj != null) {
                obj = null;
            } else {
                stringBuilder.append("&");
            }
            try {
                stringBuilder.append(URLEncoder.encode(gVar.a, "UTF-8"));
                stringBuilder.append("=");
                stringBuilder.append(URLEncoder.encode(gVar.b, "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                l.c("Helpshift_ApiClient", "Exception Unsupported Encoding", e);
            }
        }
        return stringBuilder.toString();
    }

    /* Access modifiers changed, original: 0000 */
    public String a(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("/faqs/");
        stringBuilder.append(str);
        stringBuilder.append("/");
        return stringBuilder.toString();
    }

    private String a(String str, String str2) throws GeneralSecurityException, InstallException {
        if (!TextUtils.isEmpty(str2)) {
            return o.d().s().a(str, str2);
        }
        throw new InstallException("apiKey Missing");
    }

    private String c(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("/api/lib/2");
        stringBuilder.append(str);
        return stringBuilder.toString();
    }

    /* Access modifiers changed, original: 0000 */
    public String b(String str) throws InstallException {
        if (TextUtils.isEmpty(this.c)) {
            throw new InstallException("domain Missing");
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(i.a);
        stringBuilder.append(this.c);
        stringBuilder.append(c(str));
        return stringBuilder.toString();
    }

    /* Access modifiers changed, original: 0000 */
    public String a(HashMap<String, String> hashMap) {
        ArrayList arrayList = new ArrayList();
        for (String str : new ArrayList(hashMap.keySet())) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append("=");
            stringBuilder.append(Uri.encode((String) hashMap.get(str)));
            arrayList.add(stringBuilder.toString());
        }
        return TextUtils.join("&", arrayList);
    }

    private String a(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj instanceof ArrayList) {
            return new JSONArray((ArrayList) obj).toString();
        }
        return obj.toString();
    }

    /* Access modifiers changed, original: 0000 */
    public List<g> b(HashMap<String, String> hashMap) {
        ArrayList<String> arrayList = new ArrayList(hashMap.keySet());
        ArrayList arrayList2 = new ArrayList(arrayList.size());
        for (String str : arrayList) {
            String a = a(hashMap.get(str));
            if (a != null) {
                arrayList2.add(new g(str, a));
            }
        }
        return arrayList2;
    }

    /* Access modifiers changed, original: 0000 */
    public HashMap<String, String> c(HashMap<String, String> hashMap) throws InstallException, GeneralSecurityException {
        ArrayList arrayList = new ArrayList();
        arrayList.add("platform-id=sdk");
        String uuid = UUID.randomUUID().toString();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("token=");
        stringBuilder.append(uuid);
        arrayList.add(stringBuilder.toString());
        hashMap.put(AccountKitGraphConstants.TOKEN_RESPONSE_TYPE, uuid);
        hashMap.put("sm", a());
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("sm=");
        stringBuilder2.append(a());
        arrayList.add(stringBuilder2.toString());
        hashMap.put("signature", a(TextUtils.join("&", arrayList), "sdk"));
        return hashMap;
    }

    /* Access modifiers changed, original: 0000 */
    public HashMap<String, String> a(HashMap<String, String> hashMap, String str, String str2) throws InstallException, GeneralSecurityException {
        str = c(str);
        if (TextUtils.isEmpty(this.b)) {
            throw new InstallException("appId Missing");
        }
        hashMap.put("platform-id", this.b);
        hashMap.put("method", str2);
        hashMap.put("uri", str);
        hashMap.put(AvidJSONUtil.KEY_TIMESTAMP, x.a(Float.valueOf(o.c().q().a())));
        hashMap.put("sm", a());
        ArrayList<String> arrayList = new ArrayList(hashMap.keySet());
        ArrayList arrayList2 = new ArrayList();
        Collections.sort(arrayList);
        for (String str3 : arrayList) {
            if (!(str3.equals("screenshot") || str3.equals("meta"))) {
                String a = a(hashMap.get(str3));
                if (a != null) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(str3);
                    stringBuilder.append("=");
                    stringBuilder.append(a);
                    arrayList2.add(stringBuilder.toString());
                }
            }
        }
        hashMap.put("signature", a(TextUtils.join("&", arrayList2), this.d));
        hashMap.remove("method");
        hashMap.remove("uri");
        return hashMap;
    }

    /* Access modifiers changed, original: 0000 */
    public void a(HttpsURLConnection httpsURLConnection) {
        if (VERSION.SDK_INT >= 16 && VERSION.SDK_INT <= 19) {
            ArrayList arrayList = new ArrayList();
            arrayList.add("TLSv1.2");
            ArrayList arrayList2 = new ArrayList();
            arrayList2.add("SSLv3");
            httpsURLConnection.setSSLSocketFactory(new d(httpsURLConnection.getSSLSocketFactory(), arrayList, arrayList2));
        }
    }

    private void b(HttpsURLConnection httpsURLConnection) {
        if (VERSION.SDK_INT >= 16 && VERSION.SDK_INT <= 19 && httpsURLConnection != null) {
            SSLSocketFactory sSLSocketFactory = httpsURLConnection.getSSLSocketFactory();
            if (sSLSocketFactory instanceof d) {
                ((d) sSLSocketFactory).a();
            }
        }
    }

    private String a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("ia", true);
            jSONObject.put("rs", true);
            jSONObject.put("clc", true);
            jSONObject.put("atai", true);
            jSONObject.put("fp", true);
        } catch (JSONException e) {
            l.c("Helpshift_ApiClient", "getSdkMeta : ", e);
        }
        return jSONObject.toString();
    }

    /* Access modifiers changed, original: 0000 */
    public void a(String str, Handler handler, int i, Throwable th) {
        Throwable[] thArr = new Throwable[]{th};
        a[] aVarArr = new a[2];
        aVarArr[0] = com.helpshift.j.c.d.a("route", str);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(i);
        stringBuilder.append("");
        aVarArr[1] = com.helpshift.j.c.d.a("status", stringBuilder.toString());
        l.a("Helpshift_ApiClient", "Network error", thArr, aVarArr);
        Message obtainMessage = handler.obtainMessage();
        HashMap hashMap = new HashMap();
        hashMap.put("status", Integer.valueOf(i));
        obtainMessage.obj = hashMap;
        handler.sendMessage(obtainMessage);
    }

    /* Access modifiers changed, original: 0000 */
    public void a(String str, String str2, HashMap hashMap, Handler handler, Handler handler2) {
        a(str, str2, hashMap, handler, handler2, false);
    }

    private void a(String str, String str2, HashMap hashMap, Handler handler, Handler handler2, boolean z) {
        final HashMap hashMap2 = hashMap;
        final String str3 = str2;
        final String str4 = str;
        final boolean z2 = z;
        final Handler handler3 = handler;
        final Handler handler4 = handler2;
        new Thread(new Runnable() {
            /* JADX WARNING: Removed duplicated region for block: B:110:0x0314 A:{Catch:{ JSONException -> 0x0252, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b, all -> 0x01ff, JSONException -> 0x0311, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }} */
            /* JADX WARNING: Removed duplicated region for block: B:25:0x0114 A:{SYNTHETIC, Splitter:B:25:0x0114} */
            /* JADX WARNING: Removed duplicated region for block: B:114:0x0327 A:{Catch:{ all -> 0x0308, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, SecurityException -> 0x040d, UnknownHostException -> 0x03fc, SSLPeerUnverifiedException -> 0x03eb, SSLHandshakeException -> 0x03da, IOException -> 0x03c9 }} */
            /* JADX WARNING: Removed duplicated region for block: B:181:? A:{SYNTHETIC, RETURN} */
            /* JADX WARNING: Missing exception handler attribute for start block: B:74:0x0235 */
            /* JADX WARNING: Removed duplicated region for block: B:106:0x030f A:{Splitter:B:25:0x0114, ExcHandler: UnknownHostException (r0_56 'e' java.net.UnknownHostException A:{Catch:{  }}), Catch:{ JSONException -> 0x0252, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b, all -> 0x01ff, JSONException -> 0x0311, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }} */
            /* JADX WARNING: Removed duplicated region for block: B:104:0x030d A:{Splitter:B:25:0x0114, ExcHandler: SocketException (r0_53 'e' java.net.SocketException A:{Catch:{  }}), Catch:{ JSONException -> 0x0252, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b, all -> 0x01ff, JSONException -> 0x0311, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }} */
            /* JADX WARNING: Removed duplicated region for block: B:102:0x030b A:{Splitter:B:57:0x01fb, ExcHandler: SocketTimeoutException (r0_49 'e' java.net.SocketTimeoutException A:{Catch:{  }}), Catch:{ JSONException -> 0x0252, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b, all -> 0x01ff, JSONException -> 0x0311, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }} */
            /* JADX WARNING: Removed duplicated region for block: B:106:0x030f A:{Splitter:B:25:0x0114, ExcHandler: UnknownHostException (r0_56 'e' java.net.UnknownHostException A:{Catch:{  }}), Catch:{ JSONException -> 0x0252, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b, all -> 0x01ff, JSONException -> 0x0311, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }} */
            /* JADX WARNING: Removed duplicated region for block: B:104:0x030d A:{Splitter:B:25:0x0114, ExcHandler: SocketException (r0_53 'e' java.net.SocketException A:{Catch:{  }}), Catch:{ JSONException -> 0x0252, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b, all -> 0x01ff, JSONException -> 0x0311, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }} */
            /* JADX WARNING: Removed duplicated region for block: B:102:0x030b A:{Splitter:B:57:0x01fb, ExcHandler: SocketTimeoutException (r0_49 'e' java.net.SocketTimeoutException A:{Catch:{  }}), Catch:{ JSONException -> 0x0252, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b, all -> 0x01ff, JSONException -> 0x0311, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }} */
            /* JADX WARNING: Removed duplicated region for block: B:102:0x030b A:{Splitter:B:57:0x01fb, ExcHandler: SocketTimeoutException (r0_49 'e' java.net.SocketTimeoutException A:{Catch:{  }}), Catch:{ JSONException -> 0x0252, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b, all -> 0x01ff, JSONException -> 0x0311, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }} */
            /* JADX WARNING: Removed duplicated region for block: B:104:0x030d A:{Splitter:B:25:0x0114, ExcHandler: SocketException (r0_53 'e' java.net.SocketException A:{Catch:{  }}), Catch:{ JSONException -> 0x0252, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b, all -> 0x01ff, JSONException -> 0x0311, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }} */
            /* JADX WARNING: Removed duplicated region for block: B:106:0x030f A:{Splitter:B:25:0x0114, ExcHandler: UnknownHostException (r0_56 'e' java.net.UnknownHostException A:{Catch:{  }}), Catch:{ JSONException -> 0x0252, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b, all -> 0x01ff, JSONException -> 0x0311, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }} */
            /* JADX WARNING: Failed to process nested try/catch */
            /* JADX WARNING: Missing block: B:56:0x01f9, code skipped:
            if (r10 != null) goto L_0x01fb;
     */
            /* JADX WARNING: Missing block: B:58:?, code skipped:
            r10.close();
     */
            /* JADX WARNING: Missing block: B:63:0x0209, code skipped:
            if (r10 == null) goto L_0x0212;
     */
            /* JADX WARNING: Missing block: B:102:0x030b, code skipped:
            r0 = move-exception;
     */
            /* JADX WARNING: Missing block: B:104:0x030d, code skipped:
            r0 = move-exception;
     */
            /* JADX WARNING: Missing block: B:106:0x030f, code skipped:
            r0 = move-exception;
     */
            /* JADX WARNING: Missing block: B:118:?, code skipped:
            com.helpshift.util.l.a("Helpshift_ApiClient", "Exception Socket timeout", new java.lang.Throwable[]{r0}, com.helpshift.j.c.d.a("route", r4));
     */
            /* JADX WARNING: Missing block: B:121:0x034f, code skipped:
            if ((r8 instanceof javax.net.ssl.HttpsURLConnection) != false) goto L_0x0351;
     */
            /* JADX WARNING: Missing block: B:122:0x0351, code skipped:
            com.helpshift.support.c.a(r15.g, (javax.net.ssl.HttpsURLConnection) r8);
     */
            /* JADX WARNING: Missing block: B:123:0x0359, code skipped:
            if (r8 == null) goto L_?;
     */
            /* JADX WARNING: Missing block: B:125:?, code skipped:
            r15.g.a(r4, r8, com.helpshift.common.domain.network.j.a.intValue(), r0);
     */
            /* JADX WARNING: Missing block: B:128:0x036d, code skipped:
            if ((r8 instanceof javax.net.ssl.HttpsURLConnection) != false) goto L_0x036f;
     */
            /* JADX WARNING: Missing block: B:129:0x036f, code skipped:
            com.helpshift.support.c.a(r15.g, (javax.net.ssl.HttpsURLConnection) r8);
     */
            /* JADX WARNING: Missing block: B:130:0x0377, code skipped:
            if (r8 == null) goto L_?;
     */
            /* JADX WARNING: Missing block: B:132:?, code skipped:
            r15.g.a(r4, r8, com.helpshift.common.domain.network.j.d.intValue(), r0);
     */
            /* JADX WARNING: Missing block: B:135:0x038b, code skipped:
            if ((r8 instanceof javax.net.ssl.HttpsURLConnection) != false) goto L_0x038d;
     */
            /* JADX WARNING: Missing block: B:136:0x038d, code skipped:
            com.helpshift.support.c.a(r15.g, (javax.net.ssl.HttpsURLConnection) r8);
     */
            /* JADX WARNING: Missing block: B:137:0x0395, code skipped:
            if (r8 == null) goto L_?;
     */
            /* JADX WARNING: Missing block: B:182:?, code skipped:
            return;
     */
            /* JADX WARNING: Missing block: B:183:?, code skipped:
            return;
     */
            /* JADX WARNING: Missing block: B:184:?, code skipped:
            return;
     */
            public void run() {
                /*
                r15 = this;
                r6 = new java.util.HashMap;	 Catch:{ InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, SecurityException -> 0x040d, UnknownHostException -> 0x03fc, SSLPeerUnverifiedException -> 0x03eb, SSLHandshakeException -> 0x03da, IOException -> 0x03c9 }
                r0 = r3;	 Catch:{ InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, SecurityException -> 0x040d, UnknownHostException -> 0x03fc, SSLPeerUnverifiedException -> 0x03eb, SSLHandshakeException -> 0x03da, IOException -> 0x03c9 }
                r6.<init>(r0);	 Catch:{ InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, SecurityException -> 0x040d, UnknownHostException -> 0x03fc, SSLPeerUnverifiedException -> 0x03eb, SSLHandshakeException -> 0x03da, IOException -> 0x03c9 }
                r0 = com.helpshift.support.c.this;	 Catch:{ InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, SecurityException -> 0x040d, UnknownHostException -> 0x03fc, SSLPeerUnverifiedException -> 0x03eb, SSLHandshakeException -> 0x03da, IOException -> 0x03c9 }
                r1 = r4;	 Catch:{ InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, SecurityException -> 0x040d, UnknownHostException -> 0x03fc, SSLPeerUnverifiedException -> 0x03eb, SSLHandshakeException -> 0x03da, IOException -> 0x03c9 }
                r0 = r0.b(r1);	 Catch:{ InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, SecurityException -> 0x040d, UnknownHostException -> 0x03fc, SSLPeerUnverifiedException -> 0x03eb, SSLHandshakeException -> 0x03da, IOException -> 0x03c9 }
                r1 = r5;	 Catch:{ InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, SecurityException -> 0x040d, UnknownHostException -> 0x03fc, SSLPeerUnverifiedException -> 0x03eb, SSLHandshakeException -> 0x03da, IOException -> 0x03c9 }
                r2 = "GET";
                r1 = r1.equals(r2);	 Catch:{ InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, SecurityException -> 0x040d, UnknownHostException -> 0x03fc, SSLPeerUnverifiedException -> 0x03eb, SSLHandshakeException -> 0x03da, IOException -> 0x03c9 }
                r2 = 0;
                r7 = 1;
                if (r1 == 0) goto L_0x0089;
            L_0x001b:
                r1 = com.helpshift.support.c.this;	 Catch:{ InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, SecurityException -> 0x040d, UnknownHostException -> 0x03fc, SSLPeerUnverifiedException -> 0x03eb, SSLHandshakeException -> 0x03da, IOException -> 0x03c9 }
                r3 = com.helpshift.support.c.this;	 Catch:{ InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, SecurityException -> 0x040d, UnknownHostException -> 0x03fc, SSLPeerUnverifiedException -> 0x03eb, SSLHandshakeException -> 0x03da, IOException -> 0x03c9 }
                r4 = r3;	 Catch:{ InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, SecurityException -> 0x040d, UnknownHostException -> 0x03fc, SSLPeerUnverifiedException -> 0x03eb, SSLHandshakeException -> 0x03da, IOException -> 0x03c9 }
                r5 = r4;	 Catch:{ InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, SecurityException -> 0x040d, UnknownHostException -> 0x03fc, SSLPeerUnverifiedException -> 0x03eb, SSLHandshakeException -> 0x03da, IOException -> 0x03c9 }
                r8 = r5;	 Catch:{ InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, SecurityException -> 0x040d, UnknownHostException -> 0x03fc, SSLPeerUnverifiedException -> 0x03eb, SSLHandshakeException -> 0x03da, IOException -> 0x03c9 }
                r3 = r3.a(r4, r5, r8);	 Catch:{ InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, SecurityException -> 0x040d, UnknownHostException -> 0x03fc, SSLPeerUnverifiedException -> 0x03eb, SSLHandshakeException -> 0x03da, IOException -> 0x03c9 }
                r1 = r1.a(r3);	 Catch:{ InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, SecurityException -> 0x040d, UnknownHostException -> 0x03fc, SSLPeerUnverifiedException -> 0x03eb, SSLHandshakeException -> 0x03da, IOException -> 0x03c9 }
                r3 = new java.net.URL;	 Catch:{ InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, SecurityException -> 0x040d, UnknownHostException -> 0x03fc, SSLPeerUnverifiedException -> 0x03eb, SSLHandshakeException -> 0x03da, IOException -> 0x03c9 }
                r4 = new java.lang.StringBuilder;	 Catch:{ InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, SecurityException -> 0x040d, UnknownHostException -> 0x03fc, SSLPeerUnverifiedException -> 0x03eb, SSLHandshakeException -> 0x03da, IOException -> 0x03c9 }
                r4.<init>();	 Catch:{ InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, SecurityException -> 0x040d, UnknownHostException -> 0x03fc, SSLPeerUnverifiedException -> 0x03eb, SSLHandshakeException -> 0x03da, IOException -> 0x03c9 }
                r4.append(r0);	 Catch:{ InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, SecurityException -> 0x040d, UnknownHostException -> 0x03fc, SSLPeerUnverifiedException -> 0x03eb, SSLHandshakeException -> 0x03da, IOException -> 0x03c9 }
                r0 = "?";
                r4.append(r0);	 Catch:{ InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, SecurityException -> 0x040d, UnknownHostException -> 0x03fc, SSLPeerUnverifiedException -> 0x03eb, SSLHandshakeException -> 0x03da, IOException -> 0x03c9 }
                r4.append(r1);	 Catch:{ InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, SecurityException -> 0x040d, UnknownHostException -> 0x03fc, SSLPeerUnverifiedException -> 0x03eb, SSLHandshakeException -> 0x03da, IOException -> 0x03c9 }
                r0 = r4.toString();	 Catch:{ InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, SecurityException -> 0x040d, UnknownHostException -> 0x03fc, SSLPeerUnverifiedException -> 0x03eb, SSLHandshakeException -> 0x03da, IOException -> 0x03c9 }
                r3.<init>(r0);	 Catch:{ InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, SecurityException -> 0x040d, UnknownHostException -> 0x03fc, SSLPeerUnverifiedException -> 0x03eb, SSLHandshakeException -> 0x03da, IOException -> 0x03c9 }
                r0 = "https://";
                r1 = com.helpshift.common.domain.network.i.a;	 Catch:{ InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, SecurityException -> 0x040d, UnknownHostException -> 0x03fc, SSLPeerUnverifiedException -> 0x03eb, SSLHandshakeException -> 0x03da, IOException -> 0x03c9 }
                r0 = r0.equals(r1);	 Catch:{ InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, SecurityException -> 0x040d, UnknownHostException -> 0x03fc, SSLPeerUnverifiedException -> 0x03eb, SSLHandshakeException -> 0x03da, IOException -> 0x03c9 }
                if (r0 == 0) goto L_0x005f;
            L_0x0050:
                r0 = r3.openConnection();	 Catch:{ InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, SecurityException -> 0x040d, UnknownHostException -> 0x03fc, SSLPeerUnverifiedException -> 0x03eb, SSLHandshakeException -> 0x03da, IOException -> 0x03c9 }
                r0 = (javax.net.ssl.HttpsURLConnection) r0;	 Catch:{ InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, SecurityException -> 0x040d, UnknownHostException -> 0x03fc, SSLPeerUnverifiedException -> 0x03eb, SSLHandshakeException -> 0x03da, IOException -> 0x03c9 }
                r1 = com.helpshift.support.c.this;	 Catch:{ InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, SecurityException -> 0x040d, UnknownHostException -> 0x03fc, SSLPeerUnverifiedException -> 0x03eb, SSLHandshakeException -> 0x03da, IOException -> 0x03c9 }
                r3 = r0;
                r3 = (javax.net.ssl.HttpsURLConnection) r3;	 Catch:{ InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, SecurityException -> 0x040d, UnknownHostException -> 0x03fc, SSLPeerUnverifiedException -> 0x03eb, SSLHandshakeException -> 0x03da, IOException -> 0x03c9 }
                r1.a(r3);	 Catch:{ InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, SecurityException -> 0x040d, UnknownHostException -> 0x03fc, SSLPeerUnverifiedException -> 0x03eb, SSLHandshakeException -> 0x03da, IOException -> 0x03c9 }
                goto L_0x0065;
            L_0x005f:
                r0 = r3.openConnection();	 Catch:{ InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, SecurityException -> 0x040d, UnknownHostException -> 0x03fc, SSLPeerUnverifiedException -> 0x03eb, SSLHandshakeException -> 0x03da, IOException -> 0x03c9 }
                r0 = (java.net.HttpURLConnection) r0;	 Catch:{ InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, SecurityException -> 0x040d, UnknownHostException -> 0x03fc, SSLPeerUnverifiedException -> 0x03eb, SSLHandshakeException -> 0x03da, IOException -> 0x03c9 }
            L_0x0065:
                r1 = "GET";
                r0.setRequestMethod(r1);	 Catch:{ InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, SecurityException -> 0x040d, UnknownHostException -> 0x03fc, SSLPeerUnverifiedException -> 0x03eb, SSLHandshakeException -> 0x03da, IOException -> 0x03c9 }
                com.helpshift.support.c.a(r0);	 Catch:{ InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, SecurityException -> 0x040d, UnknownHostException -> 0x03fc, SSLPeerUnverifiedException -> 0x03eb, SSLHandshakeException -> 0x03da, IOException -> 0x03c9 }
                r1 = com.helpshift.util.o.c();	 Catch:{ InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, SecurityException -> 0x040d, UnknownHostException -> 0x03fc, SSLPeerUnverifiedException -> 0x03eb, SSLHandshakeException -> 0x03da, IOException -> 0x03c9 }
                r1 = r1.q();	 Catch:{ InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, SecurityException -> 0x040d, UnknownHostException -> 0x03fc, SSLPeerUnverifiedException -> 0x03eb, SSLHandshakeException -> 0x03da, IOException -> 0x03c9 }
                r3 = r4;	 Catch:{ InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, SecurityException -> 0x040d, UnknownHostException -> 0x03fc, SSLPeerUnverifiedException -> 0x03eb, SSLHandshakeException -> 0x03da, IOException -> 0x03c9 }
                r1 = r1.a(r3);	 Catch:{ InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, SecurityException -> 0x040d, UnknownHostException -> 0x03fc, SSLPeerUnverifiedException -> 0x03eb, SSLHandshakeException -> 0x03da, IOException -> 0x03c9 }
                r3 = android.text.TextUtils.isEmpty(r1);	 Catch:{ InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, SecurityException -> 0x040d, UnknownHostException -> 0x03fc, SSLPeerUnverifiedException -> 0x03eb, SSLHandshakeException -> 0x03da, IOException -> 0x03c9 }
                if (r3 != 0) goto L_0x0086;
            L_0x0081:
                r3 = "If-None-Match";
                r0.setRequestProperty(r3, r1);	 Catch:{ InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, SecurityException -> 0x040d, UnknownHostException -> 0x03fc, SSLPeerUnverifiedException -> 0x03eb, SSLHandshakeException -> 0x03da, IOException -> 0x03c9 }
            L_0x0086:
                r8 = r0;
                goto L_0x0111;
            L_0x0089:
                r1 = r5;	 Catch:{ InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, SecurityException -> 0x040d, UnknownHostException -> 0x03fc, SSLPeerUnverifiedException -> 0x03eb, SSLHandshakeException -> 0x03da, IOException -> 0x03c9 }
                r3 = "POST";
                r1 = r1.equals(r3);	 Catch:{ InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, SecurityException -> 0x040d, UnknownHostException -> 0x03fc, SSLPeerUnverifiedException -> 0x03eb, SSLHandshakeException -> 0x03da, IOException -> 0x03c9 }
                if (r1 == 0) goto L_0x0110;
            L_0x0093:
                r1 = r6;	 Catch:{ InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, SecurityException -> 0x040d, UnknownHostException -> 0x03fc, SSLPeerUnverifiedException -> 0x03eb, SSLHandshakeException -> 0x03da, IOException -> 0x03c9 }
                if (r1 == 0) goto L_0x00a6;
            L_0x0097:
                r1 = com.helpshift.support.c.this;	 Catch:{ InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, SecurityException -> 0x040d, UnknownHostException -> 0x03fc, SSLPeerUnverifiedException -> 0x03eb, SSLHandshakeException -> 0x03da, IOException -> 0x03c9 }
                r3 = com.helpshift.support.c.this;	 Catch:{ InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, SecurityException -> 0x040d, UnknownHostException -> 0x03fc, SSLPeerUnverifiedException -> 0x03eb, SSLHandshakeException -> 0x03da, IOException -> 0x03c9 }
                r4 = r3;	 Catch:{ InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, SecurityException -> 0x040d, UnknownHostException -> 0x03fc, SSLPeerUnverifiedException -> 0x03eb, SSLHandshakeException -> 0x03da, IOException -> 0x03c9 }
                r3 = r3.c(r4);	 Catch:{ InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, SecurityException -> 0x040d, UnknownHostException -> 0x03fc, SSLPeerUnverifiedException -> 0x03eb, SSLHandshakeException -> 0x03da, IOException -> 0x03c9 }
                r1 = r1.b(r3);	 Catch:{ InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, SecurityException -> 0x040d, UnknownHostException -> 0x03fc, SSLPeerUnverifiedException -> 0x03eb, SSLHandshakeException -> 0x03da, IOException -> 0x03c9 }
                goto L_0x00b8;
            L_0x00a6:
                r1 = com.helpshift.support.c.this;	 Catch:{ InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, SecurityException -> 0x040d, UnknownHostException -> 0x03fc, SSLPeerUnverifiedException -> 0x03eb, SSLHandshakeException -> 0x03da, IOException -> 0x03c9 }
                r3 = com.helpshift.support.c.this;	 Catch:{ InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, SecurityException -> 0x040d, UnknownHostException -> 0x03fc, SSLPeerUnverifiedException -> 0x03eb, SSLHandshakeException -> 0x03da, IOException -> 0x03c9 }
                r4 = r3;	 Catch:{ InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, SecurityException -> 0x040d, UnknownHostException -> 0x03fc, SSLPeerUnverifiedException -> 0x03eb, SSLHandshakeException -> 0x03da, IOException -> 0x03c9 }
                r5 = r4;	 Catch:{ InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, SecurityException -> 0x040d, UnknownHostException -> 0x03fc, SSLPeerUnverifiedException -> 0x03eb, SSLHandshakeException -> 0x03da, IOException -> 0x03c9 }
                r8 = r5;	 Catch:{ InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, SecurityException -> 0x040d, UnknownHostException -> 0x03fc, SSLPeerUnverifiedException -> 0x03eb, SSLHandshakeException -> 0x03da, IOException -> 0x03c9 }
                r3 = r3.a(r4, r5, r8);	 Catch:{ InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, SecurityException -> 0x040d, UnknownHostException -> 0x03fc, SSLPeerUnverifiedException -> 0x03eb, SSLHandshakeException -> 0x03da, IOException -> 0x03c9 }
                r1 = r1.b(r3);	 Catch:{ InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, SecurityException -> 0x040d, UnknownHostException -> 0x03fc, SSLPeerUnverifiedException -> 0x03eb, SSLHandshakeException -> 0x03da, IOException -> 0x03c9 }
            L_0x00b8:
                r3 = new java.net.URL;	 Catch:{ InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, SecurityException -> 0x040d, UnknownHostException -> 0x03fc, SSLPeerUnverifiedException -> 0x03eb, SSLHandshakeException -> 0x03da, IOException -> 0x03c9 }
                r3.<init>(r0);	 Catch:{ InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, SecurityException -> 0x040d, UnknownHostException -> 0x03fc, SSLPeerUnverifiedException -> 0x03eb, SSLHandshakeException -> 0x03da, IOException -> 0x03c9 }
                r0 = "https://";
                r4 = com.helpshift.common.domain.network.i.a;	 Catch:{ InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, SecurityException -> 0x040d, UnknownHostException -> 0x03fc, SSLPeerUnverifiedException -> 0x03eb, SSLHandshakeException -> 0x03da, IOException -> 0x03c9 }
                r0 = r0.equals(r4);	 Catch:{ InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, SecurityException -> 0x040d, UnknownHostException -> 0x03fc, SSLPeerUnverifiedException -> 0x03eb, SSLHandshakeException -> 0x03da, IOException -> 0x03c9 }
                if (r0 == 0) goto L_0x00d6;
            L_0x00c7:
                r0 = r3.openConnection();	 Catch:{ InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, SecurityException -> 0x040d, UnknownHostException -> 0x03fc, SSLPeerUnverifiedException -> 0x03eb, SSLHandshakeException -> 0x03da, IOException -> 0x03c9 }
                r0 = (javax.net.ssl.HttpsURLConnection) r0;	 Catch:{ InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, SecurityException -> 0x040d, UnknownHostException -> 0x03fc, SSLPeerUnverifiedException -> 0x03eb, SSLHandshakeException -> 0x03da, IOException -> 0x03c9 }
                r3 = com.helpshift.support.c.this;	 Catch:{ InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, SecurityException -> 0x040d, UnknownHostException -> 0x03fc, SSLPeerUnverifiedException -> 0x03eb, SSLHandshakeException -> 0x03da, IOException -> 0x03c9 }
                r4 = r0;
                r4 = (javax.net.ssl.HttpsURLConnection) r4;	 Catch:{ InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, SecurityException -> 0x040d, UnknownHostException -> 0x03fc, SSLPeerUnverifiedException -> 0x03eb, SSLHandshakeException -> 0x03da, IOException -> 0x03c9 }
                r3.a(r4);	 Catch:{ InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, SecurityException -> 0x040d, UnknownHostException -> 0x03fc, SSLPeerUnverifiedException -> 0x03eb, SSLHandshakeException -> 0x03da, IOException -> 0x03c9 }
                goto L_0x00dc;
            L_0x00d6:
                r0 = r3.openConnection();	 Catch:{ InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, SecurityException -> 0x040d, UnknownHostException -> 0x03fc, SSLPeerUnverifiedException -> 0x03eb, SSLHandshakeException -> 0x03da, IOException -> 0x03c9 }
                r0 = (java.net.HttpURLConnection) r0;	 Catch:{ InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, SecurityException -> 0x040d, UnknownHostException -> 0x03fc, SSLPeerUnverifiedException -> 0x03eb, SSLHandshakeException -> 0x03da, IOException -> 0x03c9 }
            L_0x00dc:
                r3 = "POST";
                r0.setRequestMethod(r3);	 Catch:{ InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, SecurityException -> 0x040d, UnknownHostException -> 0x03fc, SSLPeerUnverifiedException -> 0x03eb, SSLHandshakeException -> 0x03da, IOException -> 0x03c9 }
                r0.setDoOutput(r7);	 Catch:{ InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, SecurityException -> 0x040d, UnknownHostException -> 0x03fc, SSLPeerUnverifiedException -> 0x03eb, SSLHandshakeException -> 0x03da, IOException -> 0x03c9 }
                r3 = "Content-type";
                r4 = "application/x-www-form-urlencoded";
                r0.setRequestProperty(r3, r4);	 Catch:{ InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, SecurityException -> 0x040d, UnknownHostException -> 0x03fc, SSLPeerUnverifiedException -> 0x03eb, SSLHandshakeException -> 0x03da, IOException -> 0x03c9 }
                com.helpshift.support.c.a(r0);	 Catch:{ InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, SecurityException -> 0x040d, UnknownHostException -> 0x03fc, SSLPeerUnverifiedException -> 0x03eb, SSLHandshakeException -> 0x03da, IOException -> 0x03c9 }
                r3 = r0.getOutputStream();	 Catch:{ InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, SecurityException -> 0x040d, UnknownHostException -> 0x03fc, SSLPeerUnverifiedException -> 0x03eb, SSLHandshakeException -> 0x03da, IOException -> 0x03c9 }
                r4 = new java.io.BufferedWriter;	 Catch:{ InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, SecurityException -> 0x040d, UnknownHostException -> 0x03fc, SSLPeerUnverifiedException -> 0x03eb, SSLHandshakeException -> 0x03da, IOException -> 0x03c9 }
                r5 = new java.io.OutputStreamWriter;	 Catch:{ InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, SecurityException -> 0x040d, UnknownHostException -> 0x03fc, SSLPeerUnverifiedException -> 0x03eb, SSLHandshakeException -> 0x03da, IOException -> 0x03c9 }
                r8 = "UTF-8";
                r5.<init>(r3, r8);	 Catch:{ InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, SecurityException -> 0x040d, UnknownHostException -> 0x03fc, SSLPeerUnverifiedException -> 0x03eb, SSLHandshakeException -> 0x03da, IOException -> 0x03c9 }
                r4.<init>(r5);	 Catch:{ InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, SecurityException -> 0x040d, UnknownHostException -> 0x03fc, SSLPeerUnverifiedException -> 0x03eb, SSLHandshakeException -> 0x03da, IOException -> 0x03c9 }
                r1 = com.helpshift.support.c.a(r1);	 Catch:{ InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, SecurityException -> 0x040d, UnknownHostException -> 0x03fc, SSLPeerUnverifiedException -> 0x03eb, SSLHandshakeException -> 0x03da, IOException -> 0x03c9 }
                r4.write(r1);	 Catch:{ InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, SecurityException -> 0x040d, UnknownHostException -> 0x03fc, SSLPeerUnverifiedException -> 0x03eb, SSLHandshakeException -> 0x03da, IOException -> 0x03c9 }
                r4.flush();	 Catch:{ InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, SecurityException -> 0x040d, UnknownHostException -> 0x03fc, SSLPeerUnverifiedException -> 0x03eb, SSLHandshakeException -> 0x03da, IOException -> 0x03c9 }
                r4.close();	 Catch:{ InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, SecurityException -> 0x040d, UnknownHostException -> 0x03fc, SSLPeerUnverifiedException -> 0x03eb, SSLHandshakeException -> 0x03da, IOException -> 0x03c9 }
                r3.close();	 Catch:{ InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, SecurityException -> 0x040d, UnknownHostException -> 0x03fc, SSLPeerUnverifiedException -> 0x03eb, SSLHandshakeException -> 0x03da, IOException -> 0x03c9 }
                goto L_0x0086;
            L_0x0110:
                r8 = r2;
            L_0x0111:
                r9 = 0;
                if (r8 == 0) goto L_0x0314;
            L_0x0114:
                r0 = r8.getResponseCode();	 Catch:{ JSONException -> 0x0311, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }
                r1 = 300; // 0x12c float:4.2E-43 double:1.48E-321;
                if (r0 < r1) goto L_0x014c;
            L_0x011c:
                r3 = "Helpshift_ApiClient";
                r4 = new java.lang.StringBuilder;	 Catch:{ JSONException -> 0x0311, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }
                r4.<init>();	 Catch:{ JSONException -> 0x0311, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }
                r5 = "Api : ";
                r4.append(r5);	 Catch:{ JSONException -> 0x0311, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }
                r5 = r4;	 Catch:{ JSONException -> 0x0311, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }
                r4.append(r5);	 Catch:{ JSONException -> 0x0311, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }
                r5 = " \t Status : ";
                r4.append(r5);	 Catch:{ JSONException -> 0x0311, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }
                r4.append(r0);	 Catch:{ JSONException -> 0x0311, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }
                r5 = "\t Thread Id : ";
                r4.append(r5);	 Catch:{ JSONException -> 0x0311, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }
                r5 = java.lang.Thread.currentThread();	 Catch:{ JSONException -> 0x0311, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }
                r10 = r5.getId();	 Catch:{ JSONException -> 0x0311, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }
                r4.append(r10);	 Catch:{ JSONException -> 0x0311, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }
                r4 = r4.toString();	 Catch:{ JSONException -> 0x0311, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }
                com.helpshift.util.l.a(r3, r4);	 Catch:{ JSONException -> 0x0311, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }
            L_0x014c:
                r3 = r8.getHeaderFields();	 Catch:{ JSONException -> 0x0311, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }
                r3 = r3.entrySet();	 Catch:{ JSONException -> 0x0311, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }
                r4 = new java.lang.StringBuilder;	 Catch:{ JSONException -> 0x0311, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }
                r4.<init>();	 Catch:{ JSONException -> 0x0311, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }
                r5 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
                if (r0 < r5) goto L_0x0212;
            L_0x015d:
                if (r0 >= r1) goto L_0x0212;
            L_0x015f:
                r10 = new java.io.BufferedInputStream;	 Catch:{ JSONException -> 0x0311, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }
                r11 = r8.getInputStream();	 Catch:{ JSONException -> 0x0311, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }
                r10.<init>(r11);	 Catch:{ JSONException -> 0x0311, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }
                r11 = r3.iterator();	 Catch:{ JSONException -> 0x0311, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }
            L_0x016c:
                r12 = r11.hasNext();	 Catch:{ JSONException -> 0x0311, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }
                if (r12 == 0) goto L_0x01a6;
            L_0x0172:
                r12 = r11.next();	 Catch:{ JSONException -> 0x0311, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }
                r12 = (java.util.Map.Entry) r12;	 Catch:{ JSONException -> 0x0311, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }
                r13 = r12.getKey();	 Catch:{ JSONException -> 0x0311, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }
                if (r13 == 0) goto L_0x016c;
            L_0x017e:
                r13 = r12.getKey();	 Catch:{ JSONException -> 0x0311, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }
                r13 = (java.lang.String) r13;	 Catch:{ JSONException -> 0x0311, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }
                r14 = "ETag";
                r13 = r13.equals(r14);	 Catch:{ JSONException -> 0x0311, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }
                if (r13 == 0) goto L_0x016c;
            L_0x018c:
                r13 = com.helpshift.util.o.c();	 Catch:{ JSONException -> 0x0311, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }
                r13 = r13.q();	 Catch:{ JSONException -> 0x0311, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }
                r14 = r4;	 Catch:{ JSONException -> 0x0311, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }
                r12 = r12.getValue();	 Catch:{ JSONException -> 0x0311, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }
                r12 = (java.util.List) r12;	 Catch:{ JSONException -> 0x0311, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }
                r12 = r12.get(r9);	 Catch:{ JSONException -> 0x0311, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }
                r12 = (java.lang.String) r12;	 Catch:{ JSONException -> 0x0311, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }
                r13.a(r14, r12);	 Catch:{ JSONException -> 0x0311, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }
                goto L_0x016c;
            L_0x01a6:
                r11 = r3.iterator();	 Catch:{ JSONException -> 0x0311, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }
            L_0x01aa:
                r12 = r11.hasNext();	 Catch:{ JSONException -> 0x0311, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }
                if (r12 == 0) goto L_0x01e5;
            L_0x01b0:
                r12 = r11.next();	 Catch:{ JSONException -> 0x0311, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }
                r12 = (java.util.Map.Entry) r12;	 Catch:{ JSONException -> 0x0311, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }
                r13 = r12.getKey();	 Catch:{ JSONException -> 0x0311, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }
                if (r13 == 0) goto L_0x01aa;
            L_0x01bc:
                r13 = r12.getKey();	 Catch:{ JSONException -> 0x0311, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }
                r13 = (java.lang.String) r13;	 Catch:{ JSONException -> 0x0311, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }
                r14 = "Content-Encoding";
                r13 = r13.equals(r14);	 Catch:{ JSONException -> 0x0311, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }
                if (r13 == 0) goto L_0x01aa;
            L_0x01ca:
                r12 = r12.getValue();	 Catch:{ JSONException -> 0x0311, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }
                r12 = (java.util.List) r12;	 Catch:{ JSONException -> 0x0311, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }
                r12 = r12.get(r9);	 Catch:{ JSONException -> 0x0311, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }
                r12 = (java.lang.String) r12;	 Catch:{ JSONException -> 0x0311, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }
                r13 = "gzip";
                r12 = r12.equalsIgnoreCase(r13);	 Catch:{ JSONException -> 0x0311, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }
                if (r12 == 0) goto L_0x01aa;
            L_0x01de:
                r12 = new java.util.zip.GZIPInputStream;	 Catch:{ JSONException -> 0x0311, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }
                r12.<init>(r10);	 Catch:{ JSONException -> 0x0311, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }
                r10 = r12;
                goto L_0x01aa;
            L_0x01e5:
                r11 = new java.io.InputStreamReader;	 Catch:{ JSONException -> 0x0311, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }
                r11.<init>(r10);	 Catch:{ JSONException -> 0x0311, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }
                r10 = new java.io.BufferedReader;	 Catch:{ JSONException -> 0x0311, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }
                r10.<init>(r11);	 Catch:{ JSONException -> 0x0311, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }
            L_0x01ef:
                r11 = r10.readLine();	 Catch:{ IOException -> 0x0201 }
                if (r11 == 0) goto L_0x01f9;
            L_0x01f5:
                r4.append(r11);	 Catch:{ IOException -> 0x0201 }
                goto L_0x01ef;
            L_0x01f9:
                if (r10 == 0) goto L_0x0212;
            L_0x01fb:
                r10.close();	 Catch:{ JSONException -> 0x0311, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }
                goto L_0x0212;
            L_0x01ff:
                r0 = move-exception;
                goto L_0x020c;
            L_0x0201:
                r11 = move-exception;
                r12 = "Helpshift_ApiClient";
                r13 = "IO Exception ex";
                com.helpshift.util.l.c(r12, r13, r11);	 Catch:{ all -> 0x01ff }
                if (r10 == 0) goto L_0x0212;
            L_0x020b:
                goto L_0x01fb;
            L_0x020c:
                if (r10 == 0) goto L_0x0211;
            L_0x020e:
                r10.close();	 Catch:{ JSONException -> 0x0311, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }
            L_0x0211:
                throw r0;	 Catch:{ JSONException -> 0x0311, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }
            L_0x0212:
                r10 = new java.util.HashMap;	 Catch:{ JSONException -> 0x0311, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }
                r10.<init>();	 Catch:{ JSONException -> 0x0311, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }
                r11 = "status";
                r12 = java.lang.Integer.valueOf(r0);	 Catch:{ JSONException -> 0x0311, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }
                r10.put(r11, r12);	 Catch:{ JSONException -> 0x0311, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }
                if (r0 < r5) goto L_0x0254;
            L_0x0222:
                if (r0 >= r1) goto L_0x0254;
            L_0x0224:
                com.helpshift.support.c.a = r9;	 Catch:{ JSONException -> 0x0311, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }
                r0 = "response";
                r1 = new org.json.JSONArray;	 Catch:{ JSONException -> 0x0235, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }
                r2 = r4.toString();	 Catch:{ JSONException -> 0x0235, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }
                r1.<init>(r2);	 Catch:{ JSONException -> 0x0235, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }
                r10.put(r0, r1);	 Catch:{ JSONException -> 0x0235, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }
                goto L_0x0243;
            L_0x0235:
                r0 = "response";
                r1 = new org.json.JSONObject;	 Catch:{ JSONException -> 0x0252, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }
                r2 = r4.toString();	 Catch:{ JSONException -> 0x0252, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }
                r1.<init>(r2);	 Catch:{ JSONException -> 0x0252, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }
                r10.put(r0, r1);	 Catch:{ JSONException -> 0x0252, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }
            L_0x0243:
                r0 = r7;	 Catch:{ JSONException -> 0x0311, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }
                r0 = r0.obtainMessage();	 Catch:{ JSONException -> 0x0311, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }
                r0.obj = r10;	 Catch:{ JSONException -> 0x0311, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }
                r1 = r7;	 Catch:{ JSONException -> 0x0311, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }
                r1.sendMessage(r0);	 Catch:{ JSONException -> 0x0311, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }
                goto L_0x0323;
            L_0x0252:
                r0 = move-exception;
                throw r0;	 Catch:{ JSONException -> 0x0311, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }
            L_0x0254:
                r1 = com.helpshift.common.domain.network.j.i;	 Catch:{ JSONException -> 0x0311, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }
                r1 = r1.intValue();	 Catch:{ JSONException -> 0x0311, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }
                if (r0 != r1) goto L_0x0270;
            L_0x025c:
                r0 = com.helpshift.support.c.a;	 Catch:{ JSONException -> 0x0311, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }
                r0 = r0 + r7;
                com.helpshift.support.c.a = r0;	 Catch:{ JSONException -> 0x0311, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }
                r0 = r7;	 Catch:{ JSONException -> 0x0311, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }
                r0 = r0.obtainMessage();	 Catch:{ JSONException -> 0x0311, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }
                r0.obj = r2;	 Catch:{ JSONException -> 0x0311, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }
                r1 = r7;	 Catch:{ JSONException -> 0x0311, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }
                r1.sendMessage(r0);	 Catch:{ JSONException -> 0x0311, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }
                goto L_0x0323;
            L_0x0270:
                r1 = com.helpshift.common.domain.network.j.u;	 Catch:{ JSONException -> 0x0311, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }
                r1 = r1.intValue();	 Catch:{ JSONException -> 0x0311, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }
                if (r0 != r1) goto L_0x02e0;
            L_0x0278:
                r0 = com.helpshift.support.c.a;	 Catch:{ JSONException -> 0x0311, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }
                r0 = r0 + r7;
                com.helpshift.support.c.a = r0;	 Catch:{ JSONException -> 0x0311, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }
                r0 = com.helpshift.support.c.a;	 Catch:{ JSONException -> 0x0311, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }
                r1 = 3;
                if (r0 > r1) goto L_0x02d0;
            L_0x0282:
                r10 = r3.iterator();	 Catch:{ JSONException -> 0x0311, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }
            L_0x0286:
                r0 = r10.hasNext();	 Catch:{ JSONException -> 0x0311, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }
                if (r0 == 0) goto L_0x0323;
            L_0x028c:
                r0 = r10.next();	 Catch:{ JSONException -> 0x0311, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }
                r0 = (java.util.Map.Entry) r0;	 Catch:{ JSONException -> 0x0311, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }
                r1 = r0.getKey();	 Catch:{ JSONException -> 0x0311, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }
                if (r1 == 0) goto L_0x0286;
            L_0x0298:
                r1 = r0.getKey();	 Catch:{ JSONException -> 0x0311, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }
                r1 = (java.lang.String) r1;	 Catch:{ JSONException -> 0x0311, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }
                r2 = "HS-UEpoch";
                r1 = r1.equals(r2);	 Catch:{ JSONException -> 0x0311, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }
                if (r1 == 0) goto L_0x0286;
            L_0x02a6:
                r1 = com.helpshift.util.o.c();	 Catch:{ JSONException -> 0x0311, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }
                r1 = r1.q();	 Catch:{ JSONException -> 0x0311, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }
                r0 = r0.getValue();	 Catch:{ JSONException -> 0x0311, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }
                r0 = (java.util.List) r0;	 Catch:{ JSONException -> 0x0311, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }
                r0 = r0.get(r9);	 Catch:{ JSONException -> 0x0311, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }
                r0 = (java.lang.String) r0;	 Catch:{ JSONException -> 0x0311, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }
                r0 = com.helpshift.common.util.a.a(r0);	 Catch:{ JSONException -> 0x0311, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }
                r1.a(r0);	 Catch:{ JSONException -> 0x0311, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }
                r0 = com.helpshift.support.c.this;	 Catch:{ JSONException -> 0x0311, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }
                r1 = r5;	 Catch:{ JSONException -> 0x0311, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }
                r2 = r4;	 Catch:{ JSONException -> 0x0311, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }
                r4 = r7;	 Catch:{ JSONException -> 0x0311, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }
                r5 = r8;	 Catch:{ JSONException -> 0x0311, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }
                r3 = r6;
                r0.a(r1, r2, r3, r4, r5);	 Catch:{ JSONException -> 0x0311, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }
                goto L_0x0286;
            L_0x02d0:
                com.helpshift.support.c.a = r9;	 Catch:{ JSONException -> 0x0311, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }
                r0 = r8;	 Catch:{ JSONException -> 0x0311, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }
                r0 = r0.obtainMessage();	 Catch:{ JSONException -> 0x0311, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }
                r0.obj = r10;	 Catch:{ JSONException -> 0x0311, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }
                r1 = r8;	 Catch:{ JSONException -> 0x0311, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }
                r1.sendMessage(r0);	 Catch:{ JSONException -> 0x0311, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }
                goto L_0x0323;
            L_0x02e0:
                r1 = com.helpshift.common.domain.network.j.q;	 Catch:{ JSONException -> 0x0311, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }
                r1 = r1.intValue();	 Catch:{ JSONException -> 0x0311, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }
                if (r0 != r1) goto L_0x02f8;
            L_0x02e8:
                r0 = com.helpshift.support.c.this;	 Catch:{ JSONException -> 0x0311, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }
                r1 = r4;	 Catch:{ JSONException -> 0x0311, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }
                r3 = r8;	 Catch:{ JSONException -> 0x0311, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }
                r4 = com.helpshift.common.domain.network.j.q;	 Catch:{ JSONException -> 0x0311, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }
                r4 = r4.intValue();	 Catch:{ JSONException -> 0x0311, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }
                r0.a(r1, r3, r4, r2);	 Catch:{ JSONException -> 0x0311, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }
                goto L_0x0323;
            L_0x02f8:
                com.helpshift.support.c.a = r9;	 Catch:{ JSONException -> 0x0311, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }
                r0 = r8;	 Catch:{ JSONException -> 0x0311, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }
                r0 = r0.obtainMessage();	 Catch:{ JSONException -> 0x0311, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }
                r0.obj = r10;	 Catch:{ JSONException -> 0x0311, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }
                r1 = r8;	 Catch:{ JSONException -> 0x0311, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }
                r1.sendMessage(r0);	 Catch:{ JSONException -> 0x0311, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }
                goto L_0x0323;
            L_0x0308:
                r0 = move-exception;
                goto L_0x03b7;
            L_0x030b:
                r0 = move-exception;
                goto L_0x0336;
            L_0x030d:
                r0 = move-exception;
                goto L_0x035c;
            L_0x030f:
                r0 = move-exception;
                goto L_0x037a;
            L_0x0311:
                r0 = move-exception;
                goto L_0x0398;
            L_0x0314:
                r0 = com.helpshift.support.c.this;	 Catch:{ JSONException -> 0x0311, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }
                r1 = r4;	 Catch:{ JSONException -> 0x0311, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }
                r3 = r8;	 Catch:{ JSONException -> 0x0311, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }
                r4 = com.helpshift.common.domain.network.j.d;	 Catch:{ JSONException -> 0x0311, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }
                r4 = r4.intValue();	 Catch:{ JSONException -> 0x0311, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }
                r0.a(r1, r3, r4, r2);	 Catch:{ JSONException -> 0x0311, UnknownHostException -> 0x030f, SocketException -> 0x030d, SocketTimeoutException -> 0x030b }
            L_0x0323:
                r0 = r8 instanceof javax.net.ssl.HttpsURLConnection;	 Catch:{ InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, SecurityException -> 0x040d, UnknownHostException -> 0x03fc, SSLPeerUnverifiedException -> 0x03eb, SSLHandshakeException -> 0x03da, IOException -> 0x03c9 }
                if (r0 == 0) goto L_0x032f;
            L_0x0327:
                r0 = com.helpshift.support.c.this;	 Catch:{ InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, SecurityException -> 0x040d, UnknownHostException -> 0x03fc, SSLPeerUnverifiedException -> 0x03eb, SSLHandshakeException -> 0x03da, IOException -> 0x03c9 }
                r1 = r8;
                r1 = (javax.net.ssl.HttpsURLConnection) r1;	 Catch:{ InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, SecurityException -> 0x040d, UnknownHostException -> 0x03fc, SSLPeerUnverifiedException -> 0x03eb, SSLHandshakeException -> 0x03da, IOException -> 0x03c9 }
                r0.b(r1);	 Catch:{ InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, SecurityException -> 0x040d, UnknownHostException -> 0x03fc, SSLPeerUnverifiedException -> 0x03eb, SSLHandshakeException -> 0x03da, IOException -> 0x03c9 }
            L_0x032f:
                if (r8 == 0) goto L_0x042e;
            L_0x0331:
                r8.disconnect();	 Catch:{ InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, SecurityException -> 0x040d, UnknownHostException -> 0x03fc, SSLPeerUnverifiedException -> 0x03eb, SSLHandshakeException -> 0x03da, IOException -> 0x03c9 }
                goto L_0x042e;
            L_0x0336:
                r1 = "Helpshift_ApiClient";
                r2 = "Exception Socket timeout";
                r3 = new java.lang.Throwable[r7];	 Catch:{ all -> 0x0308 }
                r3[r9] = r0;	 Catch:{ all -> 0x0308 }
                r0 = new com.helpshift.j.c.a[r7];	 Catch:{ all -> 0x0308 }
                r4 = "route";
                r5 = r4;	 Catch:{ all -> 0x0308 }
                r4 = com.helpshift.j.c.d.a(r4, r5);	 Catch:{ all -> 0x0308 }
                r0[r9] = r4;	 Catch:{ all -> 0x0308 }
                com.helpshift.util.l.a(r1, r2, r3, r0);	 Catch:{ all -> 0x0308 }
                r0 = r8 instanceof javax.net.ssl.HttpsURLConnection;	 Catch:{ InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, SecurityException -> 0x040d, UnknownHostException -> 0x03fc, SSLPeerUnverifiedException -> 0x03eb, SSLHandshakeException -> 0x03da, IOException -> 0x03c9 }
                if (r0 == 0) goto L_0x0359;
            L_0x0351:
                r0 = com.helpshift.support.c.this;	 Catch:{ InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, SecurityException -> 0x040d, UnknownHostException -> 0x03fc, SSLPeerUnverifiedException -> 0x03eb, SSLHandshakeException -> 0x03da, IOException -> 0x03c9 }
                r1 = r8;
                r1 = (javax.net.ssl.HttpsURLConnection) r1;	 Catch:{ InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, SecurityException -> 0x040d, UnknownHostException -> 0x03fc, SSLPeerUnverifiedException -> 0x03eb, SSLHandshakeException -> 0x03da, IOException -> 0x03c9 }
                r0.b(r1);	 Catch:{ InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, SecurityException -> 0x040d, UnknownHostException -> 0x03fc, SSLPeerUnverifiedException -> 0x03eb, SSLHandshakeException -> 0x03da, IOException -> 0x03c9 }
            L_0x0359:
                if (r8 == 0) goto L_0x042e;
            L_0x035b:
                goto L_0x0331;
            L_0x035c:
                r1 = com.helpshift.support.c.this;	 Catch:{ all -> 0x0308 }
                r2 = r4;	 Catch:{ all -> 0x0308 }
                r3 = r8;	 Catch:{ all -> 0x0308 }
                r4 = com.helpshift.common.domain.network.j.a;	 Catch:{ all -> 0x0308 }
                r4 = r4.intValue();	 Catch:{ all -> 0x0308 }
                r1.a(r2, r3, r4, r0);	 Catch:{ all -> 0x0308 }
                r0 = r8 instanceof javax.net.ssl.HttpsURLConnection;	 Catch:{ InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, SecurityException -> 0x040d, UnknownHostException -> 0x03fc, SSLPeerUnverifiedException -> 0x03eb, SSLHandshakeException -> 0x03da, IOException -> 0x03c9 }
                if (r0 == 0) goto L_0x0377;
            L_0x036f:
                r0 = com.helpshift.support.c.this;	 Catch:{ InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, SecurityException -> 0x040d, UnknownHostException -> 0x03fc, SSLPeerUnverifiedException -> 0x03eb, SSLHandshakeException -> 0x03da, IOException -> 0x03c9 }
                r1 = r8;
                r1 = (javax.net.ssl.HttpsURLConnection) r1;	 Catch:{ InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, SecurityException -> 0x040d, UnknownHostException -> 0x03fc, SSLPeerUnverifiedException -> 0x03eb, SSLHandshakeException -> 0x03da, IOException -> 0x03c9 }
                r0.b(r1);	 Catch:{ InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, SecurityException -> 0x040d, UnknownHostException -> 0x03fc, SSLPeerUnverifiedException -> 0x03eb, SSLHandshakeException -> 0x03da, IOException -> 0x03c9 }
            L_0x0377:
                if (r8 == 0) goto L_0x042e;
            L_0x0379:
                goto L_0x0331;
            L_0x037a:
                r1 = com.helpshift.support.c.this;	 Catch:{ all -> 0x0308 }
                r2 = r4;	 Catch:{ all -> 0x0308 }
                r3 = r8;	 Catch:{ all -> 0x0308 }
                r4 = com.helpshift.common.domain.network.j.d;	 Catch:{ all -> 0x0308 }
                r4 = r4.intValue();	 Catch:{ all -> 0x0308 }
                r1.a(r2, r3, r4, r0);	 Catch:{ all -> 0x0308 }
                r0 = r8 instanceof javax.net.ssl.HttpsURLConnection;	 Catch:{ InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, SecurityException -> 0x040d, UnknownHostException -> 0x03fc, SSLPeerUnverifiedException -> 0x03eb, SSLHandshakeException -> 0x03da, IOException -> 0x03c9 }
                if (r0 == 0) goto L_0x0395;
            L_0x038d:
                r0 = com.helpshift.support.c.this;	 Catch:{ InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, SecurityException -> 0x040d, UnknownHostException -> 0x03fc, SSLPeerUnverifiedException -> 0x03eb, SSLHandshakeException -> 0x03da, IOException -> 0x03c9 }
                r1 = r8;
                r1 = (javax.net.ssl.HttpsURLConnection) r1;	 Catch:{ InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, SecurityException -> 0x040d, UnknownHostException -> 0x03fc, SSLPeerUnverifiedException -> 0x03eb, SSLHandshakeException -> 0x03da, IOException -> 0x03c9 }
                r0.b(r1);	 Catch:{ InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, SecurityException -> 0x040d, UnknownHostException -> 0x03fc, SSLPeerUnverifiedException -> 0x03eb, SSLHandshakeException -> 0x03da, IOException -> 0x03c9 }
            L_0x0395:
                if (r8 == 0) goto L_0x042e;
            L_0x0397:
                goto L_0x0331;
            L_0x0398:
                r1 = com.helpshift.support.c.this;	 Catch:{ all -> 0x0308 }
                r2 = r4;	 Catch:{ all -> 0x0308 }
                r3 = r8;	 Catch:{ all -> 0x0308 }
                r4 = com.helpshift.common.domain.network.j.b;	 Catch:{ all -> 0x0308 }
                r4 = r4.intValue();	 Catch:{ all -> 0x0308 }
                r1.a(r2, r3, r4, r0);	 Catch:{ all -> 0x0308 }
                r0 = r8 instanceof javax.net.ssl.HttpsURLConnection;	 Catch:{ InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, SecurityException -> 0x040d, UnknownHostException -> 0x03fc, SSLPeerUnverifiedException -> 0x03eb, SSLHandshakeException -> 0x03da, IOException -> 0x03c9 }
                if (r0 == 0) goto L_0x03b3;
            L_0x03ab:
                r0 = com.helpshift.support.c.this;	 Catch:{ InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, SecurityException -> 0x040d, UnknownHostException -> 0x03fc, SSLPeerUnverifiedException -> 0x03eb, SSLHandshakeException -> 0x03da, IOException -> 0x03c9 }
                r1 = r8;
                r1 = (javax.net.ssl.HttpsURLConnection) r1;	 Catch:{ InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, SecurityException -> 0x040d, UnknownHostException -> 0x03fc, SSLPeerUnverifiedException -> 0x03eb, SSLHandshakeException -> 0x03da, IOException -> 0x03c9 }
                r0.b(r1);	 Catch:{ InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, SecurityException -> 0x040d, UnknownHostException -> 0x03fc, SSLPeerUnverifiedException -> 0x03eb, SSLHandshakeException -> 0x03da, IOException -> 0x03c9 }
            L_0x03b3:
                if (r8 == 0) goto L_0x042e;
            L_0x03b5:
                goto L_0x0331;
            L_0x03b7:
                r1 = r8 instanceof javax.net.ssl.HttpsURLConnection;	 Catch:{ InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, SecurityException -> 0x040d, UnknownHostException -> 0x03fc, SSLPeerUnverifiedException -> 0x03eb, SSLHandshakeException -> 0x03da, IOException -> 0x03c9 }
                if (r1 == 0) goto L_0x03c3;
            L_0x03bb:
                r1 = com.helpshift.support.c.this;	 Catch:{ InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, SecurityException -> 0x040d, UnknownHostException -> 0x03fc, SSLPeerUnverifiedException -> 0x03eb, SSLHandshakeException -> 0x03da, IOException -> 0x03c9 }
                r2 = r8;
                r2 = (javax.net.ssl.HttpsURLConnection) r2;	 Catch:{ InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, SecurityException -> 0x040d, UnknownHostException -> 0x03fc, SSLPeerUnverifiedException -> 0x03eb, SSLHandshakeException -> 0x03da, IOException -> 0x03c9 }
                r1.b(r2);	 Catch:{ InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, SecurityException -> 0x040d, UnknownHostException -> 0x03fc, SSLPeerUnverifiedException -> 0x03eb, SSLHandshakeException -> 0x03da, IOException -> 0x03c9 }
            L_0x03c3:
                if (r8 == 0) goto L_0x03c8;
            L_0x03c5:
                r8.disconnect();	 Catch:{ InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, SecurityException -> 0x040d, UnknownHostException -> 0x03fc, SSLPeerUnverifiedException -> 0x03eb, SSLHandshakeException -> 0x03da, IOException -> 0x03c9 }
            L_0x03c8:
                throw r0;	 Catch:{ InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, InstallException | MalformedURLException | ProtocolException | GeneralSecurityException -> 0x041e, SecurityException -> 0x040d, UnknownHostException -> 0x03fc, SSLPeerUnverifiedException -> 0x03eb, SSLHandshakeException -> 0x03da, IOException -> 0x03c9 }
            L_0x03c9:
                r0 = move-exception;
                r1 = com.helpshift.support.c.this;
                r2 = r4;
                r3 = r8;
                r4 = com.helpshift.common.domain.network.j.b;
                r4 = r4.intValue();
                r1.a(r2, r3, r4, r0);
                goto L_0x042e;
            L_0x03da:
                r0 = move-exception;
                r1 = com.helpshift.support.c.this;
                r2 = r4;
                r3 = r8;
                r4 = com.helpshift.common.domain.network.j.f;
                r4 = r4.intValue();
                r1.a(r2, r3, r4, r0);
                goto L_0x042e;
            L_0x03eb:
                r0 = move-exception;
                r1 = com.helpshift.support.c.this;
                r2 = r4;
                r3 = r8;
                r4 = com.helpshift.common.domain.network.j.e;
                r4 = r4.intValue();
                r1.a(r2, r3, r4, r0);
                goto L_0x042e;
            L_0x03fc:
                r0 = move-exception;
                r1 = com.helpshift.support.c.this;
                r2 = r4;
                r3 = r8;
                r4 = com.helpshift.common.domain.network.j.d;
                r4 = r4.intValue();
                r1.a(r2, r3, r4, r0);
                goto L_0x042e;
            L_0x040d:
                r0 = move-exception;
                r1 = com.helpshift.support.c.this;
                r2 = r4;
                r3 = r8;
                r4 = com.helpshift.common.domain.network.j.a;
                r4 = r4.intValue();
                r1.a(r2, r3, r4, r0);
                goto L_0x042e;
            L_0x041e:
                r0 = move-exception;
                r1 = com.helpshift.support.c.this;
                r2 = r4;
                r3 = r8;
                r4 = com.helpshift.common.domain.network.j.b;
                r4 = r4.intValue();
                r1.a(r2, r3, r4, r0);
            L_0x042e:
                return;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.helpshift.support.c$AnonymousClass1.run():void");
            }
        }, "HS-ApiClient").start();
    }

    /* Access modifiers changed, original: protected */
    public void a(Handler handler, Handler handler2) {
        l.a("Helpshift_ApiClient", "Fetching FAQs");
        HashMap hashMap = new HashMap();
        hashMap.put("edfl", String.valueOf(o.d().m().a("defaultFallbackLanguageEnable")));
        a(HttpMethods.GET, "/faqs/", hashMap, handler, handler2);
    }

    /* Access modifiers changed, original: protected */
    public void a(Handler handler, Handler handler2, List<com.helpshift.j.d.a> list, String str, String str2) {
        a(HttpMethods.POST, "/events/crash-log", com.helpshift.support.g.a.a.a(this.e, list, str, str2, InternalAvidAdSessionContext.AVID_API_LEVEL), handler, handler2, true);
    }

    /* Access modifiers changed, original: protected */
    public void a(String str, Handler handler, Handler handler2) {
        HashMap hashMap = new HashMap();
        hashMap.put("edfl", String.valueOf(o.d().m().a("defaultFallbackLanguageEnable")));
        a(HttpMethods.GET, a(str), hashMap, handler, handler2);
    }
}
