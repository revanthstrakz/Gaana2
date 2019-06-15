package net.hockeyapp.android.c;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.integralads.avid.library.inmobi.session.internal.InternalAvidAdSessionContext;
import com.payu.custombrowser.util.CBConstant;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import net.hockeyapp.android.a;
import net.hockeyapp.android.i.d;

public class h extends c<Void, Void, HashMap<String, String>> {
    private Context a;
    private Handler b;
    private String c;
    private String d;
    private String e;
    private String f;
    private String g;
    private List<Uri> h;
    private String i;
    private boolean j;
    private ProgressDialog k;
    private boolean l = true;
    private int m = -1;

    public h(Context context, String str, String str2, String str3, String str4, String str5, List<Uri> list, String str6, Handler handler, boolean z) {
        this.a = context;
        this.c = str;
        this.d = str2;
        this.e = str3;
        this.f = str4;
        this.g = str5;
        this.h = list;
        this.i = str6;
        this.b = handler;
        this.j = z;
        if (context != null) {
            a.a(context);
        }
    }

    public void a() {
        this.a = null;
        this.k = null;
    }

    /* Access modifiers changed, original: protected */
    public void onPreExecute() {
        CharSequence string = this.a.getString(d.hockeyapp_feedback_sending_feedback_text);
        if (this.j) {
            string = this.a.getString(d.hockeyapp_feedback_fetching_feedback_text);
        }
        if ((this.k == null || !this.k.isShowing()) && this.l) {
            this.k = ProgressDialog.show(this.a, "", string, true, false);
        }
    }

    /* Access modifiers changed, original: protected|varargs */
    /* renamed from: a */
    public HashMap<String, String> doInBackground(Void... voidArr) {
        if (this.j && this.i != null) {
            return d();
        }
        if (this.j) {
            return null;
        }
        if (this.h.isEmpty()) {
            return b();
        }
        HashMap c = c();
        if (c != null) {
            b(c);
        }
        return c;
    }

    private void b(HashMap<String, String> hashMap) {
        String str = (String) hashMap.get("status");
        if (str != null && str.startsWith(InternalAvidAdSessionContext.AVID_API_LEVEL) && this.a != null) {
            File file = new File(this.a.getCacheDir(), "HockeyApp");
            if (file != null && file.exists()) {
                for (File file2 : file.listFiles()) {
                    if (!(file2 == null || Boolean.valueOf(file2.delete()).booleanValue())) {
                        net.hockeyapp.android.d.d.b("SendFeedbackTask", "Error deleting file from temporary folder");
                    }
                }
            }
        }
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public void onPostExecute(HashMap<String, String> hashMap) {
        if (this.k != null) {
            try {
                this.k.dismiss();
            } catch (Exception e) {
                ThrowableExtension.printStackTrace(e);
            }
        }
        if (this.b != null) {
            Message message = new Message();
            Bundle bundle = new Bundle();
            if (hashMap != null) {
                bundle.putString("request_type", (String) hashMap.get("type"));
                bundle.putString("feedback_response", (String) hashMap.get(CBConstant.RESPONSE));
                bundle.putString("feedback_status", (String) hashMap.get("status"));
            } else {
                bundle.putString("request_type", "unknown");
            }
            message.setData(bundle);
            this.b.sendMessage(message);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x00c8  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x00ce  */
    private java.util.HashMap<java.lang.String, java.lang.String> b() {
        /*
        r6 = this;
        r0 = new java.util.HashMap;
        r0.<init>();
        r1 = "type";
        r2 = "send";
        r0.put(r1, r2);
        r1 = 0;
        r2 = new java.util.HashMap;	 Catch:{ IOException -> 0x00c2 }
        r2.<init>();	 Catch:{ IOException -> 0x00c2 }
        r3 = "name";
        r4 = r6.d;	 Catch:{ IOException -> 0x00c2 }
        r2.put(r3, r4);	 Catch:{ IOException -> 0x00c2 }
        r3 = "email";
        r4 = r6.e;	 Catch:{ IOException -> 0x00c2 }
        r2.put(r3, r4);	 Catch:{ IOException -> 0x00c2 }
        r3 = "subject";
        r4 = r6.f;	 Catch:{ IOException -> 0x00c2 }
        r2.put(r3, r4);	 Catch:{ IOException -> 0x00c2 }
        r3 = "text";
        r4 = r6.g;	 Catch:{ IOException -> 0x00c2 }
        r2.put(r3, r4);	 Catch:{ IOException -> 0x00c2 }
        r3 = "bundle_identifier";
        r4 = net.hockeyapp.android.a.d;	 Catch:{ IOException -> 0x00c2 }
        r2.put(r3, r4);	 Catch:{ IOException -> 0x00c2 }
        r3 = "bundle_short_version";
        r4 = net.hockeyapp.android.a.c;	 Catch:{ IOException -> 0x00c2 }
        r2.put(r3, r4);	 Catch:{ IOException -> 0x00c2 }
        r3 = "bundle_version";
        r4 = net.hockeyapp.android.a.b;	 Catch:{ IOException -> 0x00c2 }
        r2.put(r3, r4);	 Catch:{ IOException -> 0x00c2 }
        r3 = "os_version";
        r4 = net.hockeyapp.android.a.e;	 Catch:{ IOException -> 0x00c2 }
        r2.put(r3, r4);	 Catch:{ IOException -> 0x00c2 }
        r3 = "oem";
        r4 = net.hockeyapp.android.a.h;	 Catch:{ IOException -> 0x00c2 }
        r2.put(r3, r4);	 Catch:{ IOException -> 0x00c2 }
        r3 = "model";
        r4 = net.hockeyapp.android.a.g;	 Catch:{ IOException -> 0x00c2 }
        r2.put(r3, r4);	 Catch:{ IOException -> 0x00c2 }
        r3 = "sdk_version";
        r4 = "4.1.1";
        r2.put(r3, r4);	 Catch:{ IOException -> 0x00c2 }
        r3 = r6.i;	 Catch:{ IOException -> 0x00c2 }
        if (r3 == 0) goto L_0x007d;
    L_0x0063:
        r3 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x00c2 }
        r3.<init>();	 Catch:{ IOException -> 0x00c2 }
        r4 = r6.c;	 Catch:{ IOException -> 0x00c2 }
        r3.append(r4);	 Catch:{ IOException -> 0x00c2 }
        r4 = r6.i;	 Catch:{ IOException -> 0x00c2 }
        r3.append(r4);	 Catch:{ IOException -> 0x00c2 }
        r4 = "/";
        r3.append(r4);	 Catch:{ IOException -> 0x00c2 }
        r3 = r3.toString();	 Catch:{ IOException -> 0x00c2 }
        r6.c = r3;	 Catch:{ IOException -> 0x00c2 }
    L_0x007d:
        r3 = new net.hockeyapp.android.d.e;	 Catch:{ IOException -> 0x00c2 }
        r4 = r6.c;	 Catch:{ IOException -> 0x00c2 }
        r3.<init>(r4);	 Catch:{ IOException -> 0x00c2 }
        r4 = r6.i;	 Catch:{ IOException -> 0x00c2 }
        if (r4 == 0) goto L_0x008b;
    L_0x0088:
        r4 = "PUT";
        goto L_0x008d;
    L_0x008b:
        r4 = "POST";
    L_0x008d:
        r3 = r3.a(r4);	 Catch:{ IOException -> 0x00c2 }
        r2 = r3.a(r2);	 Catch:{ IOException -> 0x00c2 }
        r2 = r2.a();	 Catch:{ IOException -> 0x00c2 }
        r2.connect();	 Catch:{ IOException -> 0x00bb, all -> 0x00b8 }
        r1 = "status";
        r3 = r2.getResponseCode();	 Catch:{ IOException -> 0x00bb, all -> 0x00b8 }
        r3 = java.lang.String.valueOf(r3);	 Catch:{ IOException -> 0x00bb, all -> 0x00b8 }
        r0.put(r1, r3);	 Catch:{ IOException -> 0x00bb, all -> 0x00b8 }
        r1 = "response";
        r3 = net.hockeyapp.android.c.c.a(r2);	 Catch:{ IOException -> 0x00bb, all -> 0x00b8 }
        r0.put(r1, r3);	 Catch:{ IOException -> 0x00bb, all -> 0x00b8 }
        if (r2 == 0) goto L_0x00cb;
    L_0x00b4:
        r2.disconnect();
        goto L_0x00cb;
    L_0x00b8:
        r0 = move-exception;
        r1 = r2;
        goto L_0x00cc;
    L_0x00bb:
        r1 = move-exception;
        r5 = r2;
        r2 = r1;
        r1 = r5;
        goto L_0x00c3;
    L_0x00c0:
        r0 = move-exception;
        goto L_0x00cc;
    L_0x00c2:
        r2 = move-exception;
    L_0x00c3:
        com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r2);	 Catch:{ all -> 0x00c0 }
        if (r1 == 0) goto L_0x00cb;
    L_0x00c8:
        r1.disconnect();
    L_0x00cb:
        return r0;
    L_0x00cc:
        if (r1 == 0) goto L_0x00d1;
    L_0x00ce:
        r1.disconnect();
    L_0x00d1:
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: net.hockeyapp.android.c.h.b():java.util.HashMap");
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x00cc  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x00d2  */
    private java.util.HashMap<java.lang.String, java.lang.String> c() {
        /*
        r7 = this;
        r0 = new java.util.HashMap;
        r0.<init>();
        r1 = "type";
        r2 = "send";
        r0.put(r1, r2);
        r1 = 0;
        r2 = new java.util.HashMap;	 Catch:{ IOException -> 0x00c6 }
        r2.<init>();	 Catch:{ IOException -> 0x00c6 }
        r3 = "name";
        r4 = r7.d;	 Catch:{ IOException -> 0x00c6 }
        r2.put(r3, r4);	 Catch:{ IOException -> 0x00c6 }
        r3 = "email";
        r4 = r7.e;	 Catch:{ IOException -> 0x00c6 }
        r2.put(r3, r4);	 Catch:{ IOException -> 0x00c6 }
        r3 = "subject";
        r4 = r7.f;	 Catch:{ IOException -> 0x00c6 }
        r2.put(r3, r4);	 Catch:{ IOException -> 0x00c6 }
        r3 = "text";
        r4 = r7.g;	 Catch:{ IOException -> 0x00c6 }
        r2.put(r3, r4);	 Catch:{ IOException -> 0x00c6 }
        r3 = "bundle_identifier";
        r4 = net.hockeyapp.android.a.d;	 Catch:{ IOException -> 0x00c6 }
        r2.put(r3, r4);	 Catch:{ IOException -> 0x00c6 }
        r3 = "bundle_short_version";
        r4 = net.hockeyapp.android.a.c;	 Catch:{ IOException -> 0x00c6 }
        r2.put(r3, r4);	 Catch:{ IOException -> 0x00c6 }
        r3 = "bundle_version";
        r4 = net.hockeyapp.android.a.b;	 Catch:{ IOException -> 0x00c6 }
        r2.put(r3, r4);	 Catch:{ IOException -> 0x00c6 }
        r3 = "os_version";
        r4 = net.hockeyapp.android.a.e;	 Catch:{ IOException -> 0x00c6 }
        r2.put(r3, r4);	 Catch:{ IOException -> 0x00c6 }
        r3 = "oem";
        r4 = net.hockeyapp.android.a.h;	 Catch:{ IOException -> 0x00c6 }
        r2.put(r3, r4);	 Catch:{ IOException -> 0x00c6 }
        r3 = "model";
        r4 = net.hockeyapp.android.a.g;	 Catch:{ IOException -> 0x00c6 }
        r2.put(r3, r4);	 Catch:{ IOException -> 0x00c6 }
        r3 = "sdk_version";
        r4 = "4.1.1";
        r2.put(r3, r4);	 Catch:{ IOException -> 0x00c6 }
        r3 = r7.i;	 Catch:{ IOException -> 0x00c6 }
        if (r3 == 0) goto L_0x007d;
    L_0x0063:
        r3 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x00c6 }
        r3.<init>();	 Catch:{ IOException -> 0x00c6 }
        r4 = r7.c;	 Catch:{ IOException -> 0x00c6 }
        r3.append(r4);	 Catch:{ IOException -> 0x00c6 }
        r4 = r7.i;	 Catch:{ IOException -> 0x00c6 }
        r3.append(r4);	 Catch:{ IOException -> 0x00c6 }
        r4 = "/";
        r3.append(r4);	 Catch:{ IOException -> 0x00c6 }
        r3 = r3.toString();	 Catch:{ IOException -> 0x00c6 }
        r7.c = r3;	 Catch:{ IOException -> 0x00c6 }
    L_0x007d:
        r3 = new net.hockeyapp.android.d.e;	 Catch:{ IOException -> 0x00c6 }
        r4 = r7.c;	 Catch:{ IOException -> 0x00c6 }
        r3.<init>(r4);	 Catch:{ IOException -> 0x00c6 }
        r4 = r7.i;	 Catch:{ IOException -> 0x00c6 }
        if (r4 == 0) goto L_0x008b;
    L_0x0088:
        r4 = "PUT";
        goto L_0x008d;
    L_0x008b:
        r4 = "POST";
    L_0x008d:
        r3 = r3.a(r4);	 Catch:{ IOException -> 0x00c6 }
        r4 = r7.a;	 Catch:{ IOException -> 0x00c6 }
        r5 = r7.h;	 Catch:{ IOException -> 0x00c6 }
        r2 = r3.a(r2, r4, r5);	 Catch:{ IOException -> 0x00c6 }
        r2 = r2.a();	 Catch:{ IOException -> 0x00c6 }
        r2.connect();	 Catch:{ IOException -> 0x00bf, all -> 0x00bc }
        r1 = "status";
        r3 = r2.getResponseCode();	 Catch:{ IOException -> 0x00bf, all -> 0x00bc }
        r3 = java.lang.String.valueOf(r3);	 Catch:{ IOException -> 0x00bf, all -> 0x00bc }
        r0.put(r1, r3);	 Catch:{ IOException -> 0x00bf, all -> 0x00bc }
        r1 = "response";
        r3 = net.hockeyapp.android.c.c.a(r2);	 Catch:{ IOException -> 0x00bf, all -> 0x00bc }
        r0.put(r1, r3);	 Catch:{ IOException -> 0x00bf, all -> 0x00bc }
        if (r2 == 0) goto L_0x00cf;
    L_0x00b8:
        r2.disconnect();
        goto L_0x00cf;
    L_0x00bc:
        r0 = move-exception;
        r1 = r2;
        goto L_0x00d0;
    L_0x00bf:
        r1 = move-exception;
        r6 = r2;
        r2 = r1;
        r1 = r6;
        goto L_0x00c7;
    L_0x00c4:
        r0 = move-exception;
        goto L_0x00d0;
    L_0x00c6:
        r2 = move-exception;
    L_0x00c7:
        com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r2);	 Catch:{ all -> 0x00c4 }
        if (r1 == 0) goto L_0x00cf;
    L_0x00cc:
        r1.disconnect();
    L_0x00cf:
        return r0;
    L_0x00d0:
        if (r1 == 0) goto L_0x00d5;
    L_0x00d2:
        r1.disconnect();
    L_0x00d5:
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: net.hockeyapp.android.c.h.c():java.util.HashMap");
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x0085  */
    /* JADX WARNING: Missing block: B:8:0x006d, code skipped:
            if (r0 != null) goto L_0x007e;
     */
    /* JADX WARNING: Missing block: B:16:0x007c, code skipped:
            if (r0 != null) goto L_0x007e;
     */
    /* JADX WARNING: Missing block: B:17:0x007e, code skipped:
            r0.disconnect();
     */
    /* JADX WARNING: Missing block: B:18:0x0081, code skipped:
            return r1;
     */
    private java.util.HashMap<java.lang.String, java.lang.String> d() {
        /*
        r5 = this;
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = r5.c;
        r1.append(r2);
        r2 = r5.i;
        r2 = net.hockeyapp.android.d.i.a(r2);
        r1.append(r2);
        r1 = r1.toString();
        r0.append(r1);
        r1 = r5.m;
        r2 = -1;
        if (r1 == r2) goto L_0x003a;
    L_0x0024:
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "?last_message_id=";
        r1.append(r2);
        r2 = r5.m;
        r1.append(r2);
        r1 = r1.toString();
        r0.append(r1);
    L_0x003a:
        r1 = new java.util.HashMap;
        r1.<init>();
        r2 = 0;
        r3 = new net.hockeyapp.android.d.e;	 Catch:{ IOException -> 0x0075, all -> 0x0072 }
        r0 = r0.toString();	 Catch:{ IOException -> 0x0075, all -> 0x0072 }
        r3.<init>(r0);	 Catch:{ IOException -> 0x0075, all -> 0x0072 }
        r0 = r3.a();	 Catch:{ IOException -> 0x0075, all -> 0x0072 }
        r2 = "type";
        r3 = "fetch";
        r1.put(r2, r3);	 Catch:{ IOException -> 0x0070 }
        r0.connect();	 Catch:{ IOException -> 0x0070 }
        r2 = "status";
        r3 = r0.getResponseCode();	 Catch:{ IOException -> 0x0070 }
        r3 = java.lang.String.valueOf(r3);	 Catch:{ IOException -> 0x0070 }
        r1.put(r2, r3);	 Catch:{ IOException -> 0x0070 }
        r2 = "response";
        r3 = net.hockeyapp.android.c.c.a(r0);	 Catch:{ IOException -> 0x0070 }
        r1.put(r2, r3);	 Catch:{ IOException -> 0x0070 }
        if (r0 == 0) goto L_0x0081;
    L_0x006f:
        goto L_0x007e;
    L_0x0070:
        r2 = move-exception;
        goto L_0x0079;
    L_0x0072:
        r1 = move-exception;
        r0 = r2;
        goto L_0x0083;
    L_0x0075:
        r0 = move-exception;
        r4 = r2;
        r2 = r0;
        r0 = r4;
    L_0x0079:
        com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r2);	 Catch:{ all -> 0x0082 }
        if (r0 == 0) goto L_0x0081;
    L_0x007e:
        r0.disconnect();
    L_0x0081:
        return r1;
    L_0x0082:
        r1 = move-exception;
    L_0x0083:
        if (r0 == 0) goto L_0x0088;
    L_0x0085:
        r0.disconnect();
    L_0x0088:
        throw r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: net.hockeyapp.android.c.h.d():java.util.HashMap");
    }
}
