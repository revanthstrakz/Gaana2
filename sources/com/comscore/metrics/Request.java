package com.comscore.metrics;

import android.content.Context;
import android.os.Build.VERSION;
import com.comscore.analytics.Core;
import com.comscore.measurement.Measurement;
import com.comscore.utils.CSLog;
import com.comscore.utils.Connectivity;
import com.comscore.utils.Constants;
import com.comscore.utils.Storage;
import com.comscore.utils.TransmissionMode;
import com.google.api.client.http.HttpStatusCodes;
import com.til.colombia.android.internal.e;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.URL;
import java.net.URLEncoder;

public class Request {
    public static final boolean REDIRECTION_SUPPORTED;
    protected URL a = process();
    protected Proxy b;
    private Measurement c;
    private Core d;
    private Storage e;

    static {
        int i = VERSION.SDK_INT;
        boolean z = i < 11 || i > 13;
        REDIRECTION_SUPPORTED = z;
    }

    public Request(Core core, Measurement measurement) {
        this.d = core;
        this.e = core.getStorage();
        this.c = measurement;
    }

    private static Proxy a(String str) {
        int indexOf = str.indexOf(58);
        if (indexOf != -1) {
            String substring = str.substring(0, indexOf);
            indexOf = Integer.parseInt(str.substring(indexOf + 1));
            str = substring;
        } else {
            indexOf = 80;
        }
        return new Proxy(Type.HTTP, new InetSocketAddress(str, indexOf));
    }

    private boolean c() {
        d();
        boolean a = a();
        if (!a) {
            e();
        }
        return a;
    }

    private void d() {
        TransmissionMode offlineTransmissionMode = this.d.getOfflineTransmissionMode();
        if (offlineTransmissionMode == TransmissionMode.DEFAULT || ((offlineTransmissionMode == TransmissionMode.WIFIONLY && Connectivity.isConnectedWiFi(this.d.getAppContext())) || offlineTransmissionMode == TransmissionMode.PIGGYBACK)) {
            this.d.getOfflineCache().flush();
        }
    }

    private void e() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Measurement was not transmitted: ");
        stringBuilder.append(this.c.retrieveLabelsAsString(this.d.getMeasurementLabelOrder()));
        CSLog.e((Object) this, stringBuilder.toString());
        this.d.getOfflineCache().saveEvent(this.c);
    }

    /* Access modifiers changed, original: protected */
    public HttpURLConnection a(URL url) {
        HttpURLConnection httpURLConnection = (HttpURLConnection) (this.b != null ? url.openConnection(this.b) : url.openConnection());
        httpURLConnection.setRequestProperty("Connection", "Close");
        return httpURLConnection;
    }

    /* Access modifiers changed, original: protected */
    public URL a(URL url, int i, String str) {
        switch (i) {
            case HttpStatusCodes.STATUS_CODE_MULTIPLE_CHOICES /*300*/:
            case HttpStatusCodes.STATUS_CODE_MOVED_PERMANENTLY /*301*/:
            case HttpStatusCodes.STATUS_CODE_FOUND /*302*/:
            case HttpStatusCodes.STATUS_CODE_SEE_OTHER /*303*/:
            case 305:
                if (str == null) {
                    return null;
                }
                if (i == 305) {
                    i = 0;
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(url.getProtocol());
                    stringBuilder.append(':');
                    if (str.startsWith(stringBuilder.toString())) {
                        i = url.getProtocol().length() + 1;
                    }
                    if (str.startsWith("//", i)) {
                        i += 2;
                    }
                    this.b = a(str.substring(i));
                    return url;
                }
                URL url2 = new URL(url, str);
                return !url.getProtocol().equals(url2.getProtocol()) ? null : url2;
            default:
                return null;
        }
    }

    /* Access modifiers changed, original: protected */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x00c8  */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x00c1  */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x00c8  */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x00c1  */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x00c8  */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x00c1  */
    public boolean a() {
        /*
        r8 = this;
        r0 = 1;
        r1 = 0;
        r2 = 0;
        r3 = r8.a;	 Catch:{ Exception -> 0x00a3 }
        r4 = r8.b();	 Catch:{ Exception -> 0x00a3 }
        if (r4 == 0) goto L_0x001e;
    L_0x000b:
        r3 = r8.a(r3);	 Catch:{ Exception -> 0x00a3 }
        r2 = r3.getResponseCode();	 Catch:{ Exception -> 0x001a, all -> 0x0016 }
        r5 = r2;
        r2 = r3;
        goto L_0x0051;
    L_0x0016:
        r0 = move-exception;
        r2 = r3;
        goto L_0x00c6;
    L_0x001a:
        r0 = move-exception;
        r2 = r3;
        goto L_0x00a4;
    L_0x001e:
        r5 = r1;
        r4 = r2;
        r2 = r5;
    L_0x0021:
        if (r3 == 0) goto L_0x0050;
    L_0x0023:
        r6 = 5;
        if (r2 >= r6) goto L_0x0050;
    L_0x0026:
        r5 = r8.a(r3);	 Catch:{ Exception -> 0x004d, all -> 0x0049 }
        r5.setInstanceFollowRedirects(r1);	 Catch:{ Exception -> 0x0045, all -> 0x0041 }
        r4 = r5.getResponseCode();	 Catch:{ Exception -> 0x0045, all -> 0x0041 }
        r6 = "Location";
        r6 = r5.getHeaderField(r6);	 Catch:{ Exception -> 0x0045, all -> 0x0041 }
        r3 = r8.a(r3, r4, r6);	 Catch:{ Exception -> 0x0045, all -> 0x0041 }
        r2 = r2 + 1;
        r7 = r5;
        r5 = r4;
        r4 = r7;
        goto L_0x0021;
    L_0x0041:
        r0 = move-exception;
        r2 = r5;
        goto L_0x00c6;
    L_0x0045:
        r0 = move-exception;
        r2 = r5;
        goto L_0x00a4;
    L_0x0049:
        r0 = move-exception;
        r2 = r4;
        goto L_0x00c6;
    L_0x004d:
        r0 = move-exception;
        r2 = r4;
        goto L_0x00a4;
    L_0x0050:
        r2 = r4;
    L_0x0051:
        r3 = "Content-Type";
        r3 = r2.getHeaderField(r3);	 Catch:{ Exception -> 0x00a3 }
        r4 = "Content-Length";
        r4 = r2.getHeaderField(r4);	 Catch:{ Exception -> 0x00a3 }
        r6 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        if (r5 != r6) goto L_0x007b;
    L_0x0061:
        r6 = com.comscore.utils.Utils.isNotEmpty(r3);	 Catch:{ Exception -> 0x00a3 }
        if (r6 == 0) goto L_0x007b;
    L_0x0067:
        r6 = "image/";
        r3 = r3.indexOf(r6);	 Catch:{ Exception -> 0x00a3 }
        if (r3 != 0) goto L_0x007b;
    L_0x006f:
        r3 = com.comscore.utils.Utils.isNotEmpty(r4);	 Catch:{ Exception -> 0x00a3 }
        if (r3 == 0) goto L_0x007b;
    L_0x0075:
        r3 = java.lang.Integer.parseInt(r4);	 Catch:{ Exception -> 0x00a3 }
        if (r3 > 0) goto L_0x008b;
    L_0x007b:
        r3 = 204; // 0xcc float:2.86E-43 double:1.01E-321;
        if (r5 != r3) goto L_0x009a;
    L_0x007f:
        r3 = com.comscore.utils.Utils.isNotEmpty(r4);	 Catch:{ Exception -> 0x00a3 }
        if (r3 == 0) goto L_0x009a;
    L_0x0085:
        r3 = java.lang.Integer.parseInt(r4);	 Catch:{ Exception -> 0x00a3 }
        if (r3 != 0) goto L_0x009a;
    L_0x008b:
        r1 = r8.d;	 Catch:{ Exception -> 0x0095 }
        r1 = r1.getKeepAlive();	 Catch:{ Exception -> 0x0095 }
        r1.reset();	 Catch:{ Exception -> 0x0095 }
        goto L_0x009b;
    L_0x0095:
        r1 = move-exception;
        r7 = r1;
        r1 = r0;
        r0 = r7;
        goto L_0x00a4;
    L_0x009a:
        r0 = r1;
    L_0x009b:
        if (r2 == 0) goto L_0x00c5;
    L_0x009d:
        r2.disconnect();
        return r0;
    L_0x00a1:
        r0 = move-exception;
        goto L_0x00c6;
    L_0x00a3:
        r0 = move-exception;
    L_0x00a4:
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00a1 }
        r3.<init>();	 Catch:{ all -> 0x00a1 }
        r4 = "Exception sending measurement:";
        r3.append(r4);	 Catch:{ all -> 0x00a1 }
        r4 = r0.getLocalizedMessage();	 Catch:{ all -> 0x00a1 }
        r3.append(r4);	 Catch:{ all -> 0x00a1 }
        r3 = r3.toString();	 Catch:{ all -> 0x00a1 }
        com.comscore.utils.CSLog.e(r8, r3);	 Catch:{ all -> 0x00a1 }
        com.comscore.utils.CSLog.printStackTrace(r0);	 Catch:{ all -> 0x00a1 }
        if (r2 == 0) goto L_0x00c4;
    L_0x00c1:
        r2.disconnect();
    L_0x00c4:
        r0 = r1;
    L_0x00c5:
        return r0;
    L_0x00c6:
        if (r2 == 0) goto L_0x00cb;
    L_0x00c8:
        r2.disconnect();
    L_0x00cb:
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.comscore.metrics.Request.a():boolean");
    }

    public Boolean availableConnection() {
        try {
            String str;
            Context appContext = this.d.getAppContext();
            boolean z = true;
            if (Connectivity.isEmulator()) {
                str = "emu";
            } else if (Connectivity.isConnectedWiFi(appContext)) {
                str = e.ad;
            } else if (Connectivity.isConnectedMobile(appContext)) {
                str = "wwan";
            } else if (Connectivity.isConnectBluetooth(appContext)) {
                str = "bth";
            } else if (Connectivity.isConnectEthernet(appContext)) {
                str = "eth";
            } else {
                str = "unknown";
                z = false;
            }
            this.c.setLabel(Constants.NETWORK_TYPE_LABEL_NAME, str);
            return Boolean.valueOf(z);
        } catch (NullPointerException unused) {
            return Boolean.valueOf(false);
        }
    }

    /* Access modifiers changed, original: protected */
    public boolean b() {
        return REDIRECTION_SUPPORTED;
    }

    public URL process() {
        return process(this.c.getPixelURL());
    }

    public URL process(String str) {
        availableConnection();
        str = str.concat(this.c.retrieveLabelsAsString(this.d.getMeasurementLabelOrder()));
        if (str.length() > 4096 && str.indexOf("&") > 0) {
            String replace;
            int lastIndexOf = str.substring(0, 4088).lastIndexOf("&");
            try {
                replace = URLEncoder.encode(str.substring(lastIndexOf + 1), "UTF-8").replace("+", "%20");
            } catch (UnsupportedEncodingException unused) {
                replace = "0";
            }
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str.substring(0, lastIndexOf));
            stringBuilder.append("&ns_cut=");
            stringBuilder.append(replace);
            str = stringBuilder.toString();
        }
        if (str.length() > 4096) {
            str = str.substring(0, 4096);
        }
        try {
            return new URL(str);
        } catch (MalformedURLException unused2) {
            return null;
        }
    }

    /* JADX WARNING: Missing block: B:8:0x0043, code skipped:
            if (com.comscore.utils.Connectivity.isDataConnected(r0) == false) goto L_0x006c;
     */
    /* JADX WARNING: Missing block: B:15:0x0058, code skipped:
            if (com.comscore.utils.Connectivity.isConnectEthernet(r0) == false) goto L_0x006c;
     */
    /* JADX WARNING: Missing block: B:18:0x0065, code skipped:
            if (availableConnection().booleanValue() == false) goto L_0x006c;
     */
    /* JADX WARNING: Missing block: B:20:0x006b, code skipped:
            return c();
     */
    /* JADX WARNING: Missing block: B:21:0x006c, code skipped:
            e();
     */
    /* JADX WARNING: Missing block: B:23:0x0070, code skipped:
            return false;
     */
    public boolean send() {
        /*
        r7 = this;
        r0 = r7.d;
        r0 = r0.getAppContext();
        r1 = "android.permission.ACCESS_NETWORK_STATE";
        r1 = com.comscore.utils.Permissions.check(r0, r1);
        r1 = r1.booleanValue();
        r2 = r7.d;
        r2 = r2.getLiveTransmissionMode();
        r3 = r7.e;
        r4 = "lastMeasurementProcessedTimestamp";
        r5 = com.comscore.utils.Date.unixTime();
        r5 = java.lang.String.valueOf(r5);
        r3.set(r4, r5);
        r3 = com.comscore.metrics.a.a;
        r2 = r2.ordinal();
        r2 = r3[r2];
        switch(r2) {
            case 1: goto L_0x006c;
            case 2: goto L_0x006c;
            case 3: goto L_0x005b;
            case 4: goto L_0x0046;
            case 5: goto L_0x0031;
            default: goto L_0x0030;
        };
    L_0x0030:
        goto L_0x006f;
    L_0x0031:
        if (r1 == 0) goto L_0x0067;
    L_0x0033:
        r1 = com.comscore.utils.Connectivity.isEmulator();
        if (r1 != 0) goto L_0x0067;
    L_0x0039:
        r1 = com.comscore.utils.Connectivity.isConnectedWiFi(r0);
        if (r1 != 0) goto L_0x0067;
    L_0x003f:
        r0 = com.comscore.utils.Connectivity.isDataConnected(r0);
        if (r0 == 0) goto L_0x006c;
    L_0x0045:
        goto L_0x0067;
    L_0x0046:
        if (r1 == 0) goto L_0x0067;
    L_0x0048:
        r1 = com.comscore.utils.Connectivity.isEmulator();
        if (r1 != 0) goto L_0x0067;
    L_0x004e:
        r1 = com.comscore.utils.Connectivity.isConnectedWiFi(r0);
        if (r1 != 0) goto L_0x0067;
    L_0x0054:
        r0 = com.comscore.utils.Connectivity.isConnectEthernet(r0);
        if (r0 == 0) goto L_0x006c;
    L_0x005a:
        goto L_0x0067;
    L_0x005b:
        if (r1 == 0) goto L_0x0067;
    L_0x005d:
        r0 = r7.availableConnection();
        r0 = r0.booleanValue();
        if (r0 == 0) goto L_0x006c;
    L_0x0067:
        r0 = r7.c();
        return r0;
    L_0x006c:
        r7.e();
    L_0x006f:
        r0 = 0;
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.comscore.metrics.Request.send():boolean");
    }
}
