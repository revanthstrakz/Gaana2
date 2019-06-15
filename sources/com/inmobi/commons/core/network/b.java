package com.inmobi.commons.core.network;

import com.google.api.client.http.HttpMethods;
import com.inmobi.commons.core.network.NetworkError.ErrorCode;
import com.inmobi.commons.core.utilities.d;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.entity.mime.MIME;
import org.json.JSONException;
import org.json.JSONObject;

class b {
    protected static final String a = "com.inmobi.commons.core.network.b";
    protected c b;
    protected HttpURLConnection c;

    public b(c cVar) {
        this.b = cVar;
    }

    public d a() {
        Throwable th;
        d dVar;
        this.b.a();
        d dVar2;
        if (this.b.x != 1) {
            dVar2 = new d();
            dVar2.b = new NetworkError(ErrorCode.GDPR_COMPLIANCE_ENFORCED, "Network Request dropped as current request is not GDPR compliant.");
            return dVar2;
        }
        if (d.a()) {
            try {
                HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(this.b.e()).openConnection();
                a(httpURLConnection);
                this.c = httpURLConnection;
                if (!this.b.t) {
                    this.c.setInstanceFollowRedirects(false);
                }
                if (HttpMethods.POST.equals(this.b.p)) {
                    String f = this.b.f();
                    this.c.setRequestProperty("Content-Length", Integer.toString(f.length()));
                    this.c.setRequestProperty(MIME.CONTENT_TYPE, "application/x-www-form-urlencoded");
                    Closeable closeable = null;
                    try {
                        Closeable bufferedWriter = new BufferedWriter(new OutputStreamWriter(this.c.getOutputStream()));
                        try {
                            bufferedWriter.write(f);
                            d.a(bufferedWriter);
                        } catch (Throwable th2) {
                            th = th2;
                            closeable = bufferedWriter;
                            d.a(closeable);
                            throw th;
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        d.a(closeable);
                        throw th;
                    }
                }
                dVar2 = b();
            } catch (IOException e) {
                dVar = new d();
                dVar.b = new NetworkError(ErrorCode.NETWORK_IO_ERROR, e.getLocalizedMessage());
                dVar2 = dVar;
                return dVar2;
            } catch (Exception e2) {
                dVar = new d();
                dVar.b = new NetworkError(ErrorCode.UNKNOWN_ERROR, e2.getLocalizedMessage());
                try {
                    HashMap hashMap = new HashMap();
                    hashMap.put("type", "GenericException");
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(e2.getMessage());
                    hashMap.put("message", stringBuilder.toString());
                    com.inmobi.commons.core.e.b.a();
                    com.inmobi.commons.core.e.b.a("root", "ExceptionCaught", hashMap);
                } catch (Exception unused) {
                    StringBuilder stringBuilder2 = new StringBuilder("Error in submitting telemetry event : (");
                    stringBuilder2.append(e2.getMessage());
                    stringBuilder2.append(")");
                }
                dVar2 = dVar;
                return dVar2;
            }
        }
        dVar2 = new d();
        dVar2.b = new NetworkError(ErrorCode.NETWORK_UNAVAILABLE_ERROR, "Network not reachable currently. Please try again.");
        return dVar2;
    }

    private void a(HttpURLConnection httpURLConnection) throws ProtocolException {
        httpURLConnection.setConnectTimeout(this.b.r);
        httpURLConnection.setReadTimeout(this.b.s);
        httpURLConnection.setUseCaches(false);
        Map d = this.b.d();
        if (d != null) {
            for (String str : d.keySet()) {
                httpURLConnection.setRequestProperty(str, (String) d.get(str));
            }
        }
        String str2 = this.b.p;
        httpURLConnection.setRequestMethod(str2);
        if (!HttpMethods.GET.equals(str2)) {
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
        }
    }

    /* Access modifiers changed, original: protected */
    public d b() {
        d dVar = new d();
        StringBuilder stringBuilder;
        StringBuilder stringBuilder2;
        try {
            int responseCode = this.c.getResponseCode();
            stringBuilder = new StringBuilder();
            stringBuilder.append(this.b.q);
            stringBuilder.append("Response code: ");
            stringBuilder.append(responseCode);
            if (responseCode == 200) {
                a(dVar, false);
            } else {
                ErrorCode fromValue = ErrorCode.fromValue(responseCode);
                if (fromValue == ErrorCode.BAD_REQUEST) {
                    a(dVar, true);
                    dVar.b = new NetworkError(fromValue, a(dVar.b()));
                } else {
                    if (fromValue == null) {
                        fromValue = ErrorCode.UNKNOWN_ERROR;
                    }
                    stringBuilder2 = new StringBuilder("HTTP:");
                    stringBuilder2.append(responseCode);
                    dVar.b = new NetworkError(fromValue, stringBuilder2.toString());
                    dVar.d = this.c.getHeaderFields();
                }
            }
            this.c.disconnect();
        } catch (SocketTimeoutException unused) {
            dVar.b = new NetworkError(ErrorCode.HTTP_GATEWAY_TIMEOUT, ErrorCode.HTTP_GATEWAY_TIMEOUT.toString());
        } catch (IOException unused2) {
            dVar.b = new NetworkError(ErrorCode.NETWORK_IO_ERROR, ErrorCode.NETWORK_IO_ERROR.toString());
        } catch (OutOfMemoryError unused3) {
            dVar.b = new NetworkError(ErrorCode.OUT_OF_MEMORY_ERROR, ErrorCode.OUT_OF_MEMORY_ERROR.toString());
        } catch (Exception e) {
            dVar.b = new NetworkError(ErrorCode.UNKNOWN_ERROR, ErrorCode.UNKNOWN_ERROR.toString());
            try {
                HashMap hashMap = new HashMap();
                hashMap.put("type", "GenericException");
                stringBuilder2 = new StringBuilder();
                stringBuilder2.append(e.getMessage());
                hashMap.put("message", stringBuilder2.toString());
                com.inmobi.commons.core.e.b.a();
                com.inmobi.commons.core.e.b.a("root", "ExceptionCaught", hashMap);
            } catch (Exception unused4) {
                stringBuilder = new StringBuilder("Error in submitting telemetry event : (");
                stringBuilder.append(e.getMessage());
                stringBuilder.append(")");
            }
        } catch (Throwable th) {
            this.c.disconnect();
        }
        return dVar;
    }

    private void a(d dVar, boolean z) throws IOException {
        if ((this.b.v != -1 ? 1 : null) == null || ((long) this.c.getContentLength()) <= this.b.v) {
            byte[] a = d.a(z ? this.c.getErrorStream() : this.c.getInputStream());
            if (a.length != 0) {
                if (this.b.b()) {
                    a = this.b.a(a);
                    if (a == null) {
                        dVar.b = new NetworkError(ErrorCode.INVALID_ENCRYPTED_RESPONSE_RECEIVED, "Unable to decrypt the server response.");
                    }
                }
                if (a != null && this.b.w) {
                    a = d.a(a);
                    if (a == null) {
                        dVar.b = new NetworkError(ErrorCode.GZIP_DECOMPRESSION_FAILED, "Failed to uncompress gzip response");
                    }
                }
                if (a != null) {
                    dVar.b(a);
                }
            }
            dVar.d = this.c.getHeaderFields();
            return;
        }
        dVar.b = new NetworkError(ErrorCode.RESPONSE_EXCEEDS_SPECIFIED_SIZE_LIMIT, "Response size greater than specified max response size");
    }

    private static String a(String str) {
        if (str == null) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has("errorMessage")) {
                return jSONObject.getString("errorMessage");
            }
            return null;
        } catch (JSONException unused) {
            return null;
        }
    }
}
