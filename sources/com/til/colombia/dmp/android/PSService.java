package com.til.colombia.dmp.android;

import android.app.IntentService;
import android.net.Uri;
import android.net.Uri.Builder;

public class PSService extends IntentService {
    private String a = null;

    public PSService() {
        super(PSService.class.getName());
    }

    /* Access modifiers changed, original: protected */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x009b  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0093  */
    public void onHandleIntent(android.content.Intent r5) {
        /*
        r4 = this;
        r0 = 0;
        r1 = "sid";
        r5 = r5.getStringExtra(r1);	 Catch:{ Exception -> 0x008a, all -> 0x0085 }
        r4.a = r5;	 Catch:{ Exception -> 0x008a, all -> 0x0085 }
        r5 = new android.net.Uri$Builder;	 Catch:{ Exception -> 0x008a, all -> 0x0085 }
        r5.<init>();	 Catch:{ Exception -> 0x008a, all -> 0x0085 }
        r1 = com.til.colombia.dmp.android.Utils.getPsUrl();	 Catch:{ Exception -> 0x008a, all -> 0x0085 }
        r5 = r5.encodedPath(r1);	 Catch:{ Exception -> 0x008a, all -> 0x0085 }
        r1 = "pid";
        r2 = com.til.colombia.dmp.android.Utils.getAAID(r4);	 Catch:{ Exception -> 0x008a, all -> 0x0085 }
        r5 = r5.appendQueryParameter(r1, r2);	 Catch:{ Exception -> 0x008a, all -> 0x0085 }
        r1 = "sid";
        r2 = r4.a;	 Catch:{ Exception -> 0x008a, all -> 0x0085 }
        r5 = r5.appendQueryParameter(r1, r2);	 Catch:{ Exception -> 0x008a, all -> 0x0085 }
        r5 = r5.build();	 Catch:{ Exception -> 0x008a, all -> 0x0085 }
        r1 = new java.net.URL;	 Catch:{ Exception -> 0x008a, all -> 0x0085 }
        r5 = r5.toString();	 Catch:{ Exception -> 0x008a, all -> 0x0085 }
        r1.<init>(r5);	 Catch:{ Exception -> 0x008a, all -> 0x0085 }
        r5 = r1.openConnection();	 Catch:{ Exception -> 0x008a, all -> 0x0085 }
        r5 = (java.net.HttpURLConnection) r5;	 Catch:{ Exception -> 0x008a, all -> 0x0085 }
        r0 = 10000; // 0x2710 float:1.4013E-41 double:4.9407E-320;
        r5.setConnectTimeout(r0);	 Catch:{ Exception -> 0x0083 }
        r5.setReadTimeout(r0);	 Catch:{ Exception -> 0x0083 }
        r0 = "User-Agent";
        r1 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0083 }
        r1.<init>();	 Catch:{ Exception -> 0x0083 }
        r2 = "http.agent";
        r2 = java.lang.System.getProperty(r2);	 Catch:{ Exception -> 0x0083 }
        r1.append(r2);	 Catch:{ Exception -> 0x0083 }
        r2 = "dmp-aos:1.6.0";
        r1.append(r2);	 Catch:{ Exception -> 0x0083 }
        r1 = r1.toString();	 Catch:{ Exception -> 0x0083 }
        r5.setRequestProperty(r0, r1);	 Catch:{ Exception -> 0x0083 }
        r0 = 0;
        r5.setUseCaches(r0);	 Catch:{ Exception -> 0x0083 }
        r5.connect();	 Catch:{ Exception -> 0x0083 }
        r0 = "cps";
        r1 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0083 }
        r2 = "sent to server ";
        r1.<init>(r2);	 Catch:{ Exception -> 0x0083 }
        r2 = r5.getResponseCode();	 Catch:{ Exception -> 0x0083 }
        r1.append(r2);	 Catch:{ Exception -> 0x0083 }
        r1 = r1.toString();	 Catch:{ Exception -> 0x0083 }
        android.util.Log.i(r0, r1);	 Catch:{ Exception -> 0x0083 }
        if (r5 == 0) goto L_0x0097;
    L_0x007f:
        r5.disconnect();
        return;
    L_0x0083:
        r0 = move-exception;
        goto L_0x008e;
    L_0x0085:
        r5 = move-exception;
        r3 = r0;
        r0 = r5;
        r5 = r3;
        goto L_0x0099;
    L_0x008a:
        r5 = move-exception;
        r3 = r0;
        r0 = r5;
        r5 = r3;
    L_0x008e:
        com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r0);	 Catch:{ all -> 0x0098 }
        if (r5 == 0) goto L_0x0097;
    L_0x0093:
        r5.disconnect();
        return;
    L_0x0097:
        return;
    L_0x0098:
        r0 = move-exception;
    L_0x0099:
        if (r5 == 0) goto L_0x009e;
    L_0x009b:
        r5.disconnect();
    L_0x009e:
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.til.colombia.dmp.android.PSService.onHandleIntent(android.content.Intent):void");
    }

    private Uri a() {
        return new Builder().encodedPath(Utils.getPsUrl()).appendQueryParameter("pid", Utils.getAAID(this)).appendQueryParameter("sid", this.a).build();
    }
}
