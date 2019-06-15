package com.til.colombia.dmp.android;

import android.content.Context;
import android.net.Uri.Builder;
import android.os.AsyncTask;
import com.til.colombia.android.internal.e;

final class a {
    Context a;

    private class a extends AsyncTask<Void, Void, Boolean> {
        private a() {
        }

        /* synthetic */ a(a aVar, byte b) {
            this();
        }

        /* Access modifiers changed, original: protected|final|synthetic */
        public final /* synthetic */ Object doInBackground(Object[] objArr) {
            return a();
        }

        /* JADX WARNING: Removed duplicated region for block: B:45:0x0133  */
        /* JADX WARNING: Missing block: B:31:0x0116, code skipped:
            if (r2 != null) goto L_0x0128;
     */
        /* JADX WARNING: Missing block: B:39:0x0126, code skipped:
            if (r2 != null) goto L_0x0128;
     */
        /* JADX WARNING: Missing block: B:40:0x0128, code skipped:
            r2.disconnect();
     */
        /* JADX WARNING: Missing block: B:42:0x012f, code skipped:
            return java.lang.Boolean.valueOf(false);
     */
        private java.lang.Boolean a() {
            /*
            r10 = this;
            r0 = com.til.colombia.dmp.android.a.this;
            r0 = r0.a;
            r0 = com.til.colombia.dmp.android.Utils.checkNetworkAvailibility(r0);
            r1 = 0;
            if (r0 != 0) goto L_0x0010;
        L_0x000b:
            r0 = java.lang.Boolean.valueOf(r1);
            return r0;
        L_0x0010:
            r0 = 0;
            r2 = new java.net.URL;	 Catch:{ Exception -> 0x011f, all -> 0x011b }
            r3 = com.til.colombia.dmp.android.a.this;	 Catch:{ Exception -> 0x011f, all -> 0x011b }
            r4 = new android.net.Uri$Builder;	 Catch:{ Exception -> 0x011f, all -> 0x011b }
            r4.<init>();	 Catch:{ Exception -> 0x011f, all -> 0x011b }
            r5 = com.til.colombia.dmp.android.Utils.getAudUrl();	 Catch:{ Exception -> 0x011f, all -> 0x011b }
            r4 = r4.encodedPath(r5);	 Catch:{ Exception -> 0x011f, all -> 0x011b }
            r5 = "mid";
            r3 = r3.a;	 Catch:{ Exception -> 0x011f, all -> 0x011b }
            r3 = com.til.colombia.dmp.android.Utils.getAAID(r3);	 Catch:{ Exception -> 0x011f, all -> 0x011b }
            r3 = r4.appendQueryParameter(r5, r3);	 Catch:{ Exception -> 0x011f, all -> 0x011b }
            r4 = "lite";
            r5 = com.til.colombia.dmp.android.Utils.getLite();	 Catch:{ Exception -> 0x011f, all -> 0x011b }
            r5 = java.lang.String.valueOf(r5);	 Catch:{ Exception -> 0x011f, all -> 0x011b }
            r3 = r3.appendQueryParameter(r4, r5);	 Catch:{ Exception -> 0x011f, all -> 0x011b }
            r3 = r3.build();	 Catch:{ Exception -> 0x011f, all -> 0x011b }
            r3 = r3.toString();	 Catch:{ Exception -> 0x011f, all -> 0x011b }
            r2.<init>(r3);	 Catch:{ Exception -> 0x011f, all -> 0x011b }
            r2 = r2.openConnection();	 Catch:{ Exception -> 0x011f, all -> 0x011b }
            r2 = (java.net.HttpURLConnection) r2;	 Catch:{ Exception -> 0x011f, all -> 0x011b }
            r0 = 10000; // 0x2710 float:1.4013E-41 double:4.9407E-320;
            r2.setConnectTimeout(r0);	 Catch:{ Exception -> 0x0119 }
            r2.setReadTimeout(r0);	 Catch:{ Exception -> 0x0119 }
            r0 = "User-Agent";
            r3 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0119 }
            r3.<init>();	 Catch:{ Exception -> 0x0119 }
            r4 = "http.agent";
            r4 = java.lang.System.getProperty(r4);	 Catch:{ Exception -> 0x0119 }
            r3.append(r4);	 Catch:{ Exception -> 0x0119 }
            r4 = "dmp-aos:1.6.0";
            r3.append(r4);	 Catch:{ Exception -> 0x0119 }
            r3 = r3.toString();	 Catch:{ Exception -> 0x0119 }
            r2.setRequestProperty(r0, r3);	 Catch:{ Exception -> 0x0119 }
            r0 = "Content-Type";
            r3 = "application/json; charset=UTF-8";
            r2.setRequestProperty(r0, r3);	 Catch:{ Exception -> 0x0119 }
            r0 = "GET";
            r2.setRequestMethod(r0);	 Catch:{ Exception -> 0x0119 }
            r2.setUseCaches(r1);	 Catch:{ Exception -> 0x0119 }
            r2.connect();	 Catch:{ Exception -> 0x0119 }
            r0 = r2.getResponseCode();	 Catch:{ Exception -> 0x0119 }
            r0 = r0 / 10;
            r3 = 20;
            if (r0 != r3) goto L_0x0116;
        L_0x008d:
            r0 = r2.getInputStream();	 Catch:{ Exception -> 0x0119 }
            r0 = com.til.colombia.dmp.android.Utils.getStringFromInputStream(r0);	 Catch:{ Exception -> 0x0119 }
            r3 = android.text.TextUtils.isEmpty(r0);	 Catch:{ Exception -> 0x0119 }
            if (r3 != 0) goto L_0x00f9;
        L_0x009b:
            r3 = new java.util.LinkedList;	 Catch:{ Exception -> 0x0119 }
            r3.<init>();	 Catch:{ Exception -> 0x0119 }
            r4 = new org.json.JSONObject;	 Catch:{ Exception -> 0x0119 }
            r4.<init>(r0);	 Catch:{ Exception -> 0x0119 }
            r0 = "Profile";
            r0 = r4.getJSONObject(r0);	 Catch:{ Exception -> 0x0119 }
            r4 = "Audiences";
            r0 = r0.getJSONObject(r4);	 Catch:{ Exception -> 0x0119 }
            r4 = "Audience";
            r0 = r0.getJSONArray(r4);	 Catch:{ Exception -> 0x0119 }
            if (r0 == 0) goto L_0x00e8;
        L_0x00b9:
            r4 = r0.length();	 Catch:{ Exception -> 0x0119 }
            if (r4 <= 0) goto L_0x00e8;
        L_0x00bf:
            r4 = r1;
        L_0x00c0:
            r5 = r0.length();	 Catch:{ Exception -> 0x0119 }
            if (r4 >= r5) goto L_0x00e8;
        L_0x00c6:
            r5 = r0.getJSONObject(r4);	 Catch:{ Exception -> 0x0119 }
            if (r5 == 0) goto L_0x00e5;
        L_0x00cc:
            r5 = r0.getJSONObject(r4);	 Catch:{ Exception -> 0x0119 }
            r6 = "abbr";
            r5 = r5.has(r6);	 Catch:{ Exception -> 0x0119 }
            if (r5 == 0) goto L_0x00e5;
        L_0x00d8:
            r5 = r0.getJSONObject(r4);	 Catch:{ Exception -> 0x0119 }
            r6 = "abbr";
            r5 = r5.getString(r6);	 Catch:{ Exception -> 0x0119 }
            r3.add(r5);	 Catch:{ Exception -> 0x0119 }
        L_0x00e5:
            r4 = r4 + 1;
            goto L_0x00c0;
        L_0x00e8:
            r0 = com.til.colombia.dmp.android.a.this;	 Catch:{ Exception -> 0x0119 }
            r0 = r0.a;	 Catch:{ Exception -> 0x0119 }
            r4 = "ColombiaDMPPref";
            r5 = "audiences";
            r6 = ",";
            r3 = com.til.colombia.dmp.android.Utils.join(r3, r6);	 Catch:{ Exception -> 0x0119 }
            com.til.colombia.dmp.android.Utils.setPreferences(r0, r4, r5, r3);	 Catch:{ Exception -> 0x0119 }
        L_0x00f9:
            r0 = com.til.colombia.dmp.android.a.this;	 Catch:{ Exception -> 0x0119 }
            r0 = r0.a;	 Catch:{ Exception -> 0x0119 }
            r3 = "ColombiaDMPPref";
            r4 = "alu";
            r5 = java.lang.System.currentTimeMillis();	 Catch:{ Exception -> 0x0119 }
            r7 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
            r5 = r5 / r7;
            com.til.colombia.dmp.android.Utils.setPreferences(r0, r3, r4, r5);	 Catch:{ Exception -> 0x0119 }
            r0 = 1;
            r0 = java.lang.Boolean.valueOf(r0);	 Catch:{ Exception -> 0x0119 }
            if (r2 == 0) goto L_0x0115;
        L_0x0112:
            r2.disconnect();
        L_0x0115:
            return r0;
        L_0x0116:
            if (r2 == 0) goto L_0x012b;
        L_0x0118:
            goto L_0x0128;
        L_0x0119:
            r0 = move-exception;
            goto L_0x0123;
        L_0x011b:
            r1 = move-exception;
            r2 = r0;
            r0 = r1;
            goto L_0x0131;
        L_0x011f:
            r2 = move-exception;
            r9 = r2;
            r2 = r0;
            r0 = r9;
        L_0x0123:
            com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r0);	 Catch:{ all -> 0x0130 }
            if (r2 == 0) goto L_0x012b;
        L_0x0128:
            r2.disconnect();
        L_0x012b:
            r0 = java.lang.Boolean.valueOf(r1);
            return r0;
        L_0x0130:
            r0 = move-exception;
        L_0x0131:
            if (r2 == 0) goto L_0x0136;
        L_0x0133:
            r2.disconnect();
        L_0x0136:
            throw r0;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.til.colombia.dmp.android.a$a.a():java.lang.Boolean");
        }
    }

    public a(Context context) {
        this.a = context;
    }

    public final void a() {
        new a(this, (byte) 0).execute(new Void[0]);
    }

    public final String b() {
        return Utils.getPreferences(this.a, Utils.DMP_PREF, Utils.DMP_AUDS);
    }

    public final String[] c() {
        if (b() == null) {
            return new String[0];
        }
        return b().split(",");
    }

    private String d() {
        return new Builder().encodedPath(Utils.getAudUrl()).appendQueryParameter("mid", Utils.getAAID(this.a)).appendQueryParameter(e.v, String.valueOf(Utils.getLite())).build().toString();
    }
}
