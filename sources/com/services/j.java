package com.services;

import android.os.Build.VERSION;
import android.text.TextUtils;
import android.util.Log;
import com.constants.Constants;
import com.gaana.application.GaanaApplication;
import com.google.ads.mediation.inmobi.InMobiNetworkKeys;
import com.google.api.client.http.HttpMethods;
import com.google.api.client.http.HttpStatusCodes;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.i.g;
import com.payu.custombrowser.util.CBConstant;
import com.til.colombia.android.internal.e;
import com.utilities.Util;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.zip.GZIPInputStream;
import javax.net.ssl.HttpsURLConnection;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.AbstractHttpMessage;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;

public class j {
    private boolean a = false;

    public void a(AbstractHttpMessage abstractHttpMessage) {
        if (TextUtils.isEmpty(Constants.ct)) {
            Constants.ct = "IN";
        }
        abstractHttpMessage.addHeader("Accept-Encoding", "gzip");
        abstractHttpMessage.addHeader("appId", Constants.bt);
        abstractHttpMessage.addHeader(InMobiNetworkKeys.COUNTRY, Constants.ct);
        abstractHttpMessage.addHeader("gps_city", Constants.cC);
        abstractHttpMessage.addHeader("gps_state", Constants.cB);
        abstractHttpMessage.addHeader("gps_enable", Constants.cD);
        abstractHttpMessage.addHeader("deviceType", Constants.bU);
        abstractHttpMessage.addHeader("appVersion", "V7");
        abstractHttpMessage.addHeader("deviceTimeInSec", String.valueOf(System.currentTimeMillis() / 1000));
        abstractHttpMessage.addHeader("deviceTime", Util.u());
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("PHPSESSID=");
        stringBuilder.append(GaanaApplication.getCurrentSessionId());
        abstractHttpMessage.addHeader("Cookie", stringBuilder.toString());
        abstractHttpMessage.addHeader("deviceId", Util.l(GaanaApplication.getContext()));
        abstractHttpMessage.addHeader("deviceOsVersion", VERSION.RELEASE);
        stringBuilder = new StringBuilder();
        stringBuilder.append("gaanaAndroid-");
        stringBuilder.append(Constants.cz);
        abstractHttpMessage.addHeader("gaanaAppVersion", stringBuilder.toString());
        abstractHttpMessage.addHeader(e.c, Util.R());
    }

    private void a(HttpURLConnection httpURLConnection) {
        if (TextUtils.isEmpty(Constants.ct)) {
            Constants.ct = "IN";
        }
        httpURLConnection.addRequestProperty("Accept-Encoding", "gzip");
        httpURLConnection.addRequestProperty("appId", Constants.bt);
        httpURLConnection.addRequestProperty(InMobiNetworkKeys.COUNTRY, Constants.ct);
        httpURLConnection.addRequestProperty("gps_city", Constants.cC);
        httpURLConnection.addRequestProperty("gps_state", Constants.cB);
        httpURLConnection.addRequestProperty("gps_enable", Constants.cD);
        httpURLConnection.addRequestProperty("deviceType", Constants.bU);
        httpURLConnection.addRequestProperty("appVersion", "V7");
        httpURLConnection.addRequestProperty("deviceTimeInSec", String.valueOf(System.currentTimeMillis() / 1000));
        httpURLConnection.addRequestProperty("deviceTime", Util.u());
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("PHPSESSID=");
        stringBuilder.append(GaanaApplication.getCurrentSessionId());
        httpURLConnection.addRequestProperty("Cookie", stringBuilder.toString());
        httpURLConnection.addRequestProperty("deviceId", Util.l(GaanaApplication.getContext()));
        httpURLConnection.addRequestProperty("deviceOsVersion", VERSION.RELEASE);
        stringBuilder = new StringBuilder();
        stringBuilder.append("gaanaAndroid-");
        stringBuilder.append(Constants.cz);
        httpURLConnection.addRequestProperty("gaanaAppVersion", stringBuilder.toString());
        httpURLConnection.addRequestProperty(e.c, Util.R());
        if (!Constants.bV.equalsIgnoreCase("English") && this.a) {
            httpURLConnection.addRequestProperty("display_languageV3", Constants.bV);
        }
        this.a = false;
    }

    /* Access modifiers changed, original: protected */
    public HttpsURLConnection a(URL url) throws IOException {
        HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
        httpsURLConnection.setSSLSocketFactory(g.a());
        return httpsURLConnection;
    }

    public String a(HttpResponse httpResponse, String str) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            InputStream content = httpResponse.getEntity().getContent();
            Header firstHeader = httpResponse.getFirstHeader("Content-Encoding");
            InputStream gZIPInputStream = (firstHeader == null || !firstHeader.getValue().equalsIgnoreCase("gzip")) ? content : new GZIPInputStream(content);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(gZIPInputStream));
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    StringBuilder stringBuilder2 = new StringBuilder();
                    stringBuilder2.append(readLine);
                    stringBuilder2.append("\n");
                    stringBuilder.append(stringBuilder2.toString());
                } else {
                    try {
                        break;
                    } catch (IOException e) {
                        ThrowableExtension.printStackTrace(e);
                        return null;
                    }
                }
            }
            gZIPInputStream.close();
            if (Constants.c) {
                StringBuilder stringBuilder3;
                if (TextUtils.isEmpty(stringBuilder)) {
                    stringBuilder3 = new StringBuilder();
                    stringBuilder3.append(str);
                    stringBuilder3.append(" ======= Response is null");
                    Log.v("HttpResponse", stringBuilder3.toString());
                } else {
                    stringBuilder3 = new StringBuilder();
                    stringBuilder3.append(str);
                    stringBuilder3.append(" ======= ");
                    stringBuilder3.append(stringBuilder.toString());
                    Log.v("HttpResponse", stringBuilder3.toString());
                }
            }
            return stringBuilder.toString();
        } catch (IOException e2) {
            ThrowableExtension.printStackTrace(e2);
            return null;
        } catch (OutOfMemoryError unused) {
            System.gc();
            return null;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:39:0x0045 A:{SYNTHETIC, EDGE_INSN: B:39:0x0045->B:16:0x0045 ?: BREAK  , EDGE_INSN: B:39:0x0045->B:16:0x0045 ?: BREAK  } */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0030 A:{LOOP_END, LOOP:0: B:13:0x002a->B:15:0x0030, Catch:{ Exception -> 0x0082, all -> 0x007b }} */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0049 A:{Catch:{ Exception -> 0x0082, all -> 0x007b }} */
    /* JADX WARNING: Removed duplicated region for block: B:42:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0077 A:{SYNTHETIC, Splitter:B:24:0x0077} */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0084 A:{SYNTHETIC, Splitter:B:34:0x0084} */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x007e A:{SYNTHETIC, Splitter:B:28:0x007e} */
    private java.lang.String a(java.io.InputStream r5, java.lang.String r6) {
        /*
        r4 = this;
        r0 = new java.lang.StringBuffer;
        r0.<init>();
        r1 = 0;
        if (r6 == 0) goto L_0x001b;
    L_0x0008:
        r2 = "gzip";
        r6 = r6.equalsIgnoreCase(r2);	 Catch:{ Exception -> 0x0019, all -> 0x0016 }
        if (r6 == 0) goto L_0x001b;
    L_0x0010:
        r6 = new java.util.zip.GZIPInputStream;	 Catch:{ Exception -> 0x0019, all -> 0x0016 }
        r6.<init>(r5);	 Catch:{ Exception -> 0x0019, all -> 0x0016 }
        goto L_0x0020;
    L_0x0016:
        r5 = move-exception;
        r6 = r1;
        goto L_0x007c;
    L_0x0019:
        r6 = r1;
        goto L_0x0082;
    L_0x001b:
        r6 = new java.io.BufferedInputStream;	 Catch:{ Exception -> 0x0019, all -> 0x0016 }
        r6.<init>(r5);	 Catch:{ Exception -> 0x0019, all -> 0x0016 }
    L_0x0020:
        r5 = new java.io.InputStreamReader;	 Catch:{ Exception -> 0x0082, all -> 0x007b }
        r5.<init>(r6);	 Catch:{ Exception -> 0x0082, all -> 0x007b }
        r2 = new java.io.BufferedReader;	 Catch:{ Exception -> 0x0082, all -> 0x007b }
        r2.<init>(r5);	 Catch:{ Exception -> 0x0082, all -> 0x007b }
    L_0x002a:
        r5 = r2.readLine();	 Catch:{ Exception -> 0x0082, all -> 0x007b }
        if (r5 == 0) goto L_0x0045;
    L_0x0030:
        r3 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0082, all -> 0x007b }
        r3.<init>();	 Catch:{ Exception -> 0x0082, all -> 0x007b }
        r3.append(r5);	 Catch:{ Exception -> 0x0082, all -> 0x007b }
        r5 = "\n";
        r3.append(r5);	 Catch:{ Exception -> 0x0082, all -> 0x007b }
        r5 = r3.toString();	 Catch:{ Exception -> 0x0082, all -> 0x007b }
        r0.append(r5);	 Catch:{ Exception -> 0x0082, all -> 0x007b }
        goto L_0x002a;
    L_0x0045:
        r5 = com.constants.Constants.c;	 Catch:{ Exception -> 0x0082, all -> 0x007b }
        if (r5 == 0) goto L_0x0071;
    L_0x0049:
        r5 = android.text.TextUtils.isEmpty(r0);	 Catch:{ Exception -> 0x0082, all -> 0x007b }
        if (r5 != 0) goto L_0x006a;
    L_0x004f:
        r5 = "HttpResponse";
        r2 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0082, all -> 0x007b }
        r2.<init>();	 Catch:{ Exception -> 0x0082, all -> 0x007b }
        r3 = " ======= ";
        r2.append(r3);	 Catch:{ Exception -> 0x0082, all -> 0x007b }
        r3 = r0.toString();	 Catch:{ Exception -> 0x0082, all -> 0x007b }
        r2.append(r3);	 Catch:{ Exception -> 0x0082, all -> 0x007b }
        r2 = r2.toString();	 Catch:{ Exception -> 0x0082, all -> 0x007b }
        android.util.Log.v(r5, r2);	 Catch:{ Exception -> 0x0082, all -> 0x007b }
        goto L_0x0071;
    L_0x006a:
        r5 = "HttpResponse";
        r2 = " ======= Response is null";
        android.util.Log.v(r5, r2);	 Catch:{ Exception -> 0x0082, all -> 0x007b }
    L_0x0071:
        r5 = r0.toString();	 Catch:{ Exception -> 0x0082, all -> 0x007b }
        if (r6 == 0) goto L_0x0088;
    L_0x0077:
        r6.close();	 Catch:{ IOException -> 0x0088 }
        goto L_0x0088;
    L_0x007b:
        r5 = move-exception;
    L_0x007c:
        if (r6 == 0) goto L_0x0081;
    L_0x007e:
        r6.close();	 Catch:{ IOException -> 0x0081 }
    L_0x0081:
        throw r5;
    L_0x0082:
        if (r6 == 0) goto L_0x0087;
    L_0x0084:
        r6.close();	 Catch:{ IOException -> 0x0087 }
    L_0x0087:
        r5 = r1;
    L_0x0088:
        return r5;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.services.j.a(java.io.InputStream, java.lang.String):java.lang.String");
    }

    public String a(String str) throws IllegalArgumentException, IOException, AppException {
        HttpURLConnection a = a(new URL(str));
        a.setDoInput(true);
        a.setConnectTimeout(CBConstant.VERIFY_HTTP_TIMEOUT);
        a(a);
        a.connect();
        String a2 = a(a.getInputStream(), a.getContentEncoding());
        if (!(a.getResponseCode() == 200 || a == null)) {
            a.disconnect();
        }
        return a2;
    }

    public i a(String str, int i) {
        i iVar = new i();
        try {
            HttpURLConnection a = a(new URL(str));
            a.setDoInput(true);
            a.setRequestMethod(HttpMethods.GET);
            a.setConnectTimeout(CBConstant.VERIFY_HTTP_TIMEOUT);
            a(a);
            System.currentTimeMillis();
            a.connect();
            System.currentTimeMillis();
            InputStream inputStream = a.getInputStream();
            String contentEncoding = a.getContentEncoding();
            int responseCode = a.getResponseCode();
            iVar.a(responseCode);
            if (responseCode == 200 || responseCode == HttpStatusCodes.STATUS_CODE_CREATED) {
                str = a(inputStream, contentEncoding);
            } else {
                if (a != null) {
                    a.disconnect();
                }
                iVar.a(Boolean.valueOf(false));
                str = null;
            }
            iVar.a(str);
            if (str == null) {
                iVar.a(responseCode);
                iVar.a(Boolean.valueOf(false));
            }
            return iVar;
        } catch (Exception unused) {
            return iVar;
        }
    }

    public i b(String str) {
        return a(str, (int) CBConstant.VERIFY_HTTP_TIMEOUT);
    }

    public i a(String str, boolean z) {
        this.a = z;
        return a(str, (int) CBConstant.VERIFY_HTTP_TIMEOUT);
    }

    public i a(String str, int i, boolean z) {
        this.a = z;
        return a(str, i);
    }

    private String a(List<NameValuePair> list) throws UnsupportedEncodingException {
        StringBuilder stringBuilder = new StringBuilder();
        Object obj = 1;
        for (NameValuePair nameValuePair : list) {
            if (obj != null) {
                obj = null;
            } else {
                stringBuilder.append("&");
            }
            stringBuilder.append(URLEncoder.encode(nameValuePair.getName(), "UTF-8"));
            stringBuilder.append("=");
            stringBuilder.append(URLEncoder.encode(nameValuePair.getValue(), "UTF-8"));
        }
        return stringBuilder.toString();
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:? A:{SYNTHETIC, ExcHandler: UnsupportedEncodingException (unused java.io.UnsupportedEncodingException), Splitter:B:1:0x0001} */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0069 A:{ExcHandler: ProtocolException (r6_4 'e' java.net.ProtocolException), Splitter:B:1:0x0001} */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0065  */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing block: B:10:0x0062, code skipped:
            r6 = null;
     */
    /* JADX WARNING: Missing block: B:14:0x0065, code skipped:
            r6.disconnect();
     */
    /* JADX WARNING: Missing block: B:15:0x0069, code skipped:
            r6 = move-exception;
     */
    /* JADX WARNING: Missing block: B:16:0x006a, code skipped:
            com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r6);
     */
    public java.lang.String a(java.lang.String r6, java.util.List<org.apache.http.NameValuePair> r7) throws org.apache.http.client.ClientProtocolException {
        /*
        r5 = this;
        r0 = 0;
        r1 = new java.net.URL;	 Catch:{ UnsupportedEncodingException -> 0x006d, ProtocolException -> 0x0069, IOException -> 0x0062 }
        r1.<init>(r6);	 Catch:{ UnsupportedEncodingException -> 0x006d, ProtocolException -> 0x0069, IOException -> 0x0062 }
        r6 = r5.a(r1);	 Catch:{ UnsupportedEncodingException -> 0x006d, ProtocolException -> 0x0069, IOException -> 0x0062 }
        r1 = "POST";
        r6.setRequestMethod(r1);	 Catch:{ UnsupportedEncodingException -> 0x006d, ProtocolException -> 0x0069, IOException -> 0x0063 }
        r1 = 1;
        r6.setDoInput(r1);	 Catch:{ UnsupportedEncodingException -> 0x006d, ProtocolException -> 0x0069, IOException -> 0x0063 }
        r6.setDoOutput(r1);	 Catch:{ UnsupportedEncodingException -> 0x006d, ProtocolException -> 0x0069, IOException -> 0x0063 }
        r1 = 120000; // 0x1d4c0 float:1.68156E-40 double:5.9288E-319;
        r6.setConnectTimeout(r1);	 Catch:{ UnsupportedEncodingException -> 0x006d, ProtocolException -> 0x0069, IOException -> 0x0063 }
        r5.a(r6);	 Catch:{ UnsupportedEncodingException -> 0x006d, ProtocolException -> 0x0069, IOException -> 0x0063 }
        r1 = new java.io.BufferedOutputStream;	 Catch:{ UnsupportedEncodingException -> 0x006d, ProtocolException -> 0x0069, IOException -> 0x0063 }
        r2 = r6.getOutputStream();	 Catch:{ UnsupportedEncodingException -> 0x006d, ProtocolException -> 0x0069, IOException -> 0x0063 }
        r1.<init>(r2);	 Catch:{ UnsupportedEncodingException -> 0x006d, ProtocolException -> 0x0069, IOException -> 0x0063 }
        r2 = new java.io.BufferedWriter;	 Catch:{ UnsupportedEncodingException -> 0x006d, ProtocolException -> 0x0069, IOException -> 0x0063 }
        r3 = new java.io.OutputStreamWriter;	 Catch:{ UnsupportedEncodingException -> 0x006d, ProtocolException -> 0x0069, IOException -> 0x0063 }
        r4 = "UTF-8";
        r3.<init>(r1, r4);	 Catch:{ UnsupportedEncodingException -> 0x006d, ProtocolException -> 0x0069, IOException -> 0x0063 }
        r2.<init>(r3);	 Catch:{ UnsupportedEncodingException -> 0x006d, ProtocolException -> 0x0069, IOException -> 0x0063 }
        r7 = r5.a(r7);	 Catch:{ UnsupportedEncodingException -> 0x006d, ProtocolException -> 0x0069, IOException -> 0x0063 }
        r2.write(r7);	 Catch:{ UnsupportedEncodingException -> 0x006d, ProtocolException -> 0x0069, IOException -> 0x0063 }
        r2.flush();	 Catch:{ UnsupportedEncodingException -> 0x006d, ProtocolException -> 0x0069, IOException -> 0x0063 }
        r2.close();	 Catch:{ UnsupportedEncodingException -> 0x006d, ProtocolException -> 0x0069, IOException -> 0x0063 }
        r1.close();	 Catch:{ UnsupportedEncodingException -> 0x006d, ProtocolException -> 0x0069, IOException -> 0x0063 }
        r6.connect();	 Catch:{ UnsupportedEncodingException -> 0x006d, ProtocolException -> 0x0069, IOException -> 0x0063 }
        r7 = r6.getInputStream();	 Catch:{ UnsupportedEncodingException -> 0x006d, ProtocolException -> 0x0069, IOException -> 0x0063 }
        r1 = r6.getContentEncoding();	 Catch:{ UnsupportedEncodingException -> 0x006d, ProtocolException -> 0x0069, IOException -> 0x0063 }
        r7 = r5.a(r7, r1);	 Catch:{ UnsupportedEncodingException -> 0x006d, ProtocolException -> 0x0069, IOException -> 0x0063 }
        r1 = r6.getResponseCode();	 Catch:{ UnsupportedEncodingException -> 0x006d, ProtocolException -> 0x0069, IOException -> 0x0063 }
        r2 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        if (r1 == r2) goto L_0x0061;
    L_0x005b:
        if (r6 == 0) goto L_0x0060;
    L_0x005d:
        r6.disconnect();	 Catch:{ UnsupportedEncodingException -> 0x006d, ProtocolException -> 0x0069, IOException -> 0x0063 }
    L_0x0060:
        return r0;
    L_0x0061:
        return r7;
    L_0x0062:
        r6 = r0;
    L_0x0063:
        if (r6 == 0) goto L_0x006d;
    L_0x0065:
        r6.disconnect();
        goto L_0x006d;
    L_0x0069:
        r6 = move-exception;
        com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r6);
    L_0x006d:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.services.j.a(java.lang.String, java.util.List):java.lang.String");
    }

    public String a(String str, File file) throws ClientProtocolException {
        DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
        BasicHttpParams basicHttpParams = new BasicHttpParams();
        HttpConnectionParams.setSoTimeout(basicHttpParams, CBConstant.VERIFY_HTTP_TIMEOUT);
        defaultHttpClient.setParams(basicHttpParams);
        try {
            AbstractHttpMessage httpPost = new HttpPost(str);
            try {
                MultipartEntity multipartEntity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
                multipartEntity.addPart("file", new FileBody(file));
                httpPost.setEntity(multipartEntity);
                a(httpPost);
                HttpResponse execute = defaultHttpClient.execute(httpPost);
                if (execute.getStatusLine().getStatusCode() == 200) {
                    return a(execute, str);
                }
                if (httpPost != null) {
                    httpPost.abort();
                }
                return null;
            } catch (UnsupportedEncodingException e) {
                ThrowableExtension.printStackTrace(e);
                return null;
            } catch (IOException unused) {
                if (httpPost != null) {
                    httpPost.abort();
                }
                return null;
            }
        } catch (Exception unused2) {
            return null;
        }
    }
}
