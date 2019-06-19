package com.payu.india.e;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class d {
    private static long b = 0;
    private static boolean h = true;
    private String a = "sdk_local_cache_device";
    private final Context c;
    private String d;
    private boolean e;
    private ArrayList<String> f;
    private Timer g;

    public class a extends AsyncTask<String, Void, String> {
        private String b;

        a(String str) {
            this.b = str;
        }

        /* Access modifiers changed, original: protected */
        /* renamed from: a */
        public void onPostExecute(String str) {
            super.onPostExecute(str);
        }

        /* Access modifiers changed, original: protected|varargs */
        /* JADX WARNING: Unknown top exception splitter block from list: {B:33:0x0153=Splitter:B:33:0x0153, B:38:0x0177=Splitter:B:38:0x0177} */
        /* JADX WARNING: Removed duplicated region for block: B:61:0x01fe A:{ExcHandler: MalformedURLException | ProtocolException (r9_32 'e' java.lang.Throwable A:{Catch:{ Exception -> 0x01f2 }}), Splitter:B:0:0x0000, Catch:{ Exception -> 0x01f2 }} */
        /* JADX WARNING: Removed duplicated region for block: B:61:0x01fe A:{ExcHandler: MalformedURLException | ProtocolException (r9_32 'e' java.lang.Throwable A:{Catch:{ Exception -> 0x01f2 }}), Splitter:B:0:0x0000, Catch:{ Exception -> 0x01f2 }} */
        /* JADX WARNING: Removed duplicated region for block: B:61:0x01fe A:{ExcHandler: MalformedURLException | ProtocolException (r9_32 'e' java.lang.Throwable A:{Catch:{ Exception -> 0x01f2 }}), Splitter:B:0:0x0000, Catch:{ Exception -> 0x01f2 }} */
        /* JADX WARNING: Removed duplicated region for block: B:43:0x019a A:{ExcHandler: Exception (r9_19 'e' java.lang.Exception), Splitter:B:17:0x00c6} */
        /* JADX WARNING: Removed duplicated region for block: B:61:0x01fe A:{ExcHandler: MalformedURLException | ProtocolException (r9_32 'e' java.lang.Throwable A:{Catch:{ Exception -> 0x01f2 }}), Splitter:B:0:0x0000, Catch:{ Exception -> 0x01f2 }} */
        /* JADX WARNING: Removed duplicated region for block: B:61:0x01fe A:{ExcHandler: MalformedURLException | ProtocolException (r9_32 'e' java.lang.Throwable A:{Catch:{ Exception -> 0x01f2 }}), Splitter:B:0:0x0000, Catch:{ Exception -> 0x01f2 }} */
        /* JADX WARNING: Removed duplicated region for block: B:61:0x01fe A:{ExcHandler: MalformedURLException | ProtocolException (r9_32 'e' java.lang.Throwable A:{Catch:{ Exception -> 0x01f2 }}), Splitter:B:0:0x0000, Catch:{ Exception -> 0x01f2 }} */
        /* JADX WARNING: Removed duplicated region for block: B:43:0x019a A:{ExcHandler: Exception (r9_19 'e' java.lang.Exception), Splitter:B:17:0x00c6} */
        /* JADX WARNING: Removed duplicated region for block: B:61:0x01fe A:{ExcHandler: MalformedURLException | ProtocolException (r9_32 'e' java.lang.Throwable A:{Catch:{ Exception -> 0x01f2 }}), Splitter:B:0:0x0000, Catch:{ Exception -> 0x01f2 }} */
        /* JADX WARNING: Removed duplicated region for block: B:61:0x01fe A:{ExcHandler: MalformedURLException | ProtocolException (r9_32 'e' java.lang.Throwable A:{Catch:{ Exception -> 0x01f2 }}), Splitter:B:0:0x0000, Catch:{ Exception -> 0x01f2 }} */
        /* JADX WARNING: Removed duplicated region for block: B:58:0x01f4 A:{ExcHandler: IOException (r9_31 'e' java.io.IOException), Splitter:B:0:0x0000} */
        /* JADX WARNING: Removed duplicated region for block: B:61:0x01fe A:{ExcHandler: MalformedURLException | ProtocolException (r9_32 'e' java.lang.Throwable A:{Catch:{ Exception -> 0x01f2 }}), Splitter:B:0:0x0000, Catch:{ Exception -> 0x01f2 }} */
        /* JADX WARNING: Removed duplicated region for block: B:61:0x01fe A:{ExcHandler: MalformedURLException | ProtocolException (r9_32 'e' java.lang.Throwable A:{Catch:{ Exception -> 0x01f2 }}), Splitter:B:0:0x0000, Catch:{ Exception -> 0x01f2 }} */
        /* JADX WARNING: Removed duplicated region for block: B:58:0x01f4 A:{ExcHandler: IOException (r9_31 'e' java.io.IOException), Splitter:B:0:0x0000} */
        /* JADX WARNING: Removed duplicated region for block: B:43:0x019a A:{ExcHandler: Exception (r9_19 'e' java.lang.Exception), Splitter:B:17:0x00c6} */
        /* JADX WARNING: Removed duplicated region for block: B:61:0x01fe A:{ExcHandler: MalformedURLException | ProtocolException (r9_32 'e' java.lang.Throwable A:{Catch:{ Exception -> 0x01f2 }}), Splitter:B:0:0x0000, Catch:{ Exception -> 0x01f2 }} */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing block: B:35:0x0171, code skipped:
            r9 = move-exception;
     */
        /* JADX WARNING: Missing block: B:37:?, code skipped:
            com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r9);
     */
        /* JADX WARNING: Missing block: B:40:0x0195, code skipped:
            r9 = move-exception;
     */
        /* JADX WARNING: Missing block: B:42:?, code skipped:
            com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r9);
     */
        /* JADX WARNING: Missing block: B:43:0x019a, code skipped:
            r9 = move-exception;
     */
        /* JADX WARNING: Missing block: B:45:?, code skipped:
            com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r9);
     */
        /* JADX WARNING: Missing block: B:47:?, code skipped:
            r9 = com.payu.india.e.d.c(r8.a).openFileOutput(com.payu.india.e.d.b(r8.a), 0);
            r9.write(r8.b.getBytes());
            r9.close();
     */
        /* JADX WARNING: Missing block: B:53:0x01dd, code skipped:
            r9 = move-exception;
     */
        /* JADX WARNING: Missing block: B:55:?, code skipped:
            com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r9);
     */
        /* JADX WARNING: Missing block: B:57:0x01f2, code skipped:
            r9 = move-exception;
     */
        /* JADX WARNING: Missing block: B:58:0x01f4, code skipped:
            r9 = move-exception;
     */
        /* JADX WARNING: Missing block: B:60:?, code skipped:
            com.payu.india.e.d.e(r8.a);
            com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r9);
     */
        /* JADX WARNING: Missing block: B:61:0x01fe, code skipped:
            r9 = move-exception;
     */
        /* JADX WARNING: Missing block: B:62:0x01ff, code skipped:
            com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r9);
     */
        /* JADX WARNING: Missing block: B:63:0x0203, code skipped:
            com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r9);
     */
        /* renamed from: a */
        public java.lang.String doInBackground(java.lang.String... r9) {
            /*
            r8 = this;
            r0 = new org.json.JSONArray;	 Catch:{ MalformedURLException | ProtocolException -> 0x01fe, MalformedURLException | ProtocolException -> 0x01fe, IOException -> 0x01f4 }
            r1 = 0;
            r9 = r9[r1];	 Catch:{ MalformedURLException | ProtocolException -> 0x01fe, MalformedURLException | ProtocolException -> 0x01fe, IOException -> 0x01f4 }
            r0.<init>(r9);	 Catch:{ MalformedURLException | ProtocolException -> 0x01fe, MalformedURLException | ProtocolException -> 0x01fe, IOException -> 0x01f4 }
            r2 = r0;
            r9 = r1;
        L_0x000a:
            r3 = r0.length();	 Catch:{ MalformedURLException | ProtocolException -> 0x01fe, MalformedURLException | ProtocolException -> 0x01fe, IOException -> 0x01f4 }
            if (r9 >= r3) goto L_0x004b;
        L_0x0010:
            r3 = com.payu.india.e.d.this;	 Catch:{ MalformedURLException | ProtocolException -> 0x01fe, MalformedURLException | ProtocolException -> 0x01fe, IOException -> 0x01f4 }
            r4 = new java.lang.StringBuilder;	 Catch:{ MalformedURLException | ProtocolException -> 0x01fe, MalformedURLException | ProtocolException -> 0x01fe, IOException -> 0x01f4 }
            r4.<init>();	 Catch:{ MalformedURLException | ProtocolException -> 0x01fe, MalformedURLException | ProtocolException -> 0x01fe, IOException -> 0x01f4 }
            r5 = r0.get(r9);	 Catch:{ MalformedURLException | ProtocolException -> 0x01fe, MalformedURLException | ProtocolException -> 0x01fe, IOException -> 0x01f4 }
            r5 = (org.json.JSONObject) r5;	 Catch:{ MalformedURLException | ProtocolException -> 0x01fe, MalformedURLException | ProtocolException -> 0x01fe, IOException -> 0x01f4 }
            r6 = "merchant_key";
            r5 = r5.getString(r6);	 Catch:{ MalformedURLException | ProtocolException -> 0x01fe, MalformedURLException | ProtocolException -> 0x01fe, IOException -> 0x01f4 }
            r4.append(r5);	 Catch:{ MalformedURLException | ProtocolException -> 0x01fe, MalformedURLException | ProtocolException -> 0x01fe, IOException -> 0x01f4 }
            r5 = "|";
            r4.append(r5);	 Catch:{ MalformedURLException | ProtocolException -> 0x01fe, MalformedURLException | ProtocolException -> 0x01fe, IOException -> 0x01f4 }
            r5 = r0.get(r9);	 Catch:{ MalformedURLException | ProtocolException -> 0x01fe, MalformedURLException | ProtocolException -> 0x01fe, IOException -> 0x01f4 }
            r5 = (org.json.JSONObject) r5;	 Catch:{ MalformedURLException | ProtocolException -> 0x01fe, MalformedURLException | ProtocolException -> 0x01fe, IOException -> 0x01f4 }
            r6 = "txnid";
            r5 = r5.getString(r6);	 Catch:{ MalformedURLException | ProtocolException -> 0x01fe, MalformedURLException | ProtocolException -> 0x01fe, IOException -> 0x01f4 }
            r4.append(r5);	 Catch:{ MalformedURLException | ProtocolException -> 0x01fe, MalformedURLException | ProtocolException -> 0x01fe, IOException -> 0x01f4 }
            r4 = r4.toString();	 Catch:{ MalformedURLException | ProtocolException -> 0x01fe, MalformedURLException | ProtocolException -> 0x01fe, IOException -> 0x01f4 }
            r3 = r3.b(r4);	 Catch:{ MalformedURLException | ProtocolException -> 0x01fe, MalformedURLException | ProtocolException -> 0x01fe, IOException -> 0x01f4 }
            if (r3 == 0) goto L_0x0048;
        L_0x0044:
            r2 = com.payu.india.e.d.a(r0, r9);	 Catch:{ MalformedURLException | ProtocolException -> 0x01fe, MalformedURLException | ProtocolException -> 0x01fe, IOException -> 0x01f4 }
        L_0x0048:
            r9 = r9 + 1;
            goto L_0x000a;
        L_0x004b:
            r9 = r2.length();	 Catch:{ MalformedURLException | ProtocolException -> 0x01fe, MalformedURLException | ProtocolException -> 0x01fe, IOException -> 0x01f4 }
            if (r9 <= 0) goto L_0x01e2;
        L_0x0051:
            r9 = new java.lang.StringBuilder;	 Catch:{ MalformedURLException | ProtocolException -> 0x01fe, MalformedURLException | ProtocolException -> 0x01fe, IOException -> 0x01f4 }
            r9.<init>();	 Catch:{ MalformedURLException | ProtocolException -> 0x01fe, MalformedURLException | ProtocolException -> 0x01fe, IOException -> 0x01f4 }
            r0 = "mobile_data=";
            r9.append(r0);	 Catch:{ MalformedURLException | ProtocolException -> 0x01fe, MalformedURLException | ProtocolException -> 0x01fe, IOException -> 0x01f4 }
            r0 = r2.toString();	 Catch:{ MalformedURLException | ProtocolException -> 0x01fe, MalformedURLException | ProtocolException -> 0x01fe, IOException -> 0x01f4 }
            r9.append(r0);	 Catch:{ MalformedURLException | ProtocolException -> 0x01fe, MalformedURLException | ProtocolException -> 0x01fe, IOException -> 0x01f4 }
            r9 = r9.toString();	 Catch:{ MalformedURLException | ProtocolException -> 0x01fe, MalformedURLException | ProtocolException -> 0x01fe, IOException -> 0x01f4 }
            r0 = "UTF-8";
            r0 = r9.getBytes(r0);	 Catch:{ MalformedURLException | ProtocolException -> 0x01fe, MalformedURLException | ProtocolException -> 0x01fe, IOException -> 0x01f4 }
            r3 = new java.net.URL;	 Catch:{ MalformedURLException | ProtocolException -> 0x01fe, MalformedURLException | ProtocolException -> 0x01fe, IOException -> 0x01f4 }
            r4 = com.payu.india.e.d.this;	 Catch:{ MalformedURLException | ProtocolException -> 0x01fe, MalformedURLException | ProtocolException -> 0x01fe, IOException -> 0x01f4 }
            r4 = r4.d;	 Catch:{ MalformedURLException | ProtocolException -> 0x01fe, MalformedURLException | ProtocolException -> 0x01fe, IOException -> 0x01f4 }
            r3.<init>(r4);	 Catch:{ MalformedURLException | ProtocolException -> 0x01fe, MalformedURLException | ProtocolException -> 0x01fe, IOException -> 0x01f4 }
            r3 = r3.openConnection();	 Catch:{ MalformedURLException | ProtocolException -> 0x01fe, MalformedURLException | ProtocolException -> 0x01fe, IOException -> 0x01f4 }
            r3 = (java.net.HttpURLConnection) r3;	 Catch:{ MalformedURLException | ProtocolException -> 0x01fe, MalformedURLException | ProtocolException -> 0x01fe, IOException -> 0x01f4 }
            r4 = "POST";
            r3.setRequestMethod(r4);	 Catch:{ MalformedURLException | ProtocolException -> 0x01fe, MalformedURLException | ProtocolException -> 0x01fe, IOException -> 0x01f4 }
            r4 = "Content-Type";
            r5 = "application/x-www-form-urlencoded";
            r3.setRequestProperty(r4, r5);	 Catch:{ MalformedURLException | ProtocolException -> 0x01fe, MalformedURLException | ProtocolException -> 0x01fe, IOException -> 0x01f4 }
            r4 = "Content-Length";
            r9 = r9.length();	 Catch:{ MalformedURLException | ProtocolException -> 0x01fe, MalformedURLException | ProtocolException -> 0x01fe, IOException -> 0x01f4 }
            r9 = java.lang.String.valueOf(r9);	 Catch:{ MalformedURLException | ProtocolException -> 0x01fe, MalformedURLException | ProtocolException -> 0x01fe, IOException -> 0x01f4 }
            r3.setRequestProperty(r4, r9);	 Catch:{ MalformedURLException | ProtocolException -> 0x01fe, MalformedURLException | ProtocolException -> 0x01fe, IOException -> 0x01f4 }
            r9 = 1;
            r3.setDoOutput(r9);	 Catch:{ MalformedURLException | ProtocolException -> 0x01fe, MalformedURLException | ProtocolException -> 0x01fe, IOException -> 0x01f4 }
            r4 = r3.getOutputStream();	 Catch:{ MalformedURLException | ProtocolException -> 0x01fe, MalformedURLException | ProtocolException -> 0x01fe, IOException -> 0x01f4 }
            r4.write(r0);	 Catch:{ MalformedURLException | ProtocolException -> 0x01fe, MalformedURLException | ProtocolException -> 0x01fe, IOException -> 0x01f4 }
            r0 = r3.getResponseCode();	 Catch:{ MalformedURLException | ProtocolException -> 0x01fe, MalformedURLException | ProtocolException -> 0x01fe, IOException -> 0x01f4 }
            r3 = r3.getInputStream();	 Catch:{ MalformedURLException | ProtocolException -> 0x01fe, MalformedURLException | ProtocolException -> 0x01fe, IOException -> 0x01f4 }
            r4 = new java.lang.StringBuffer;	 Catch:{ MalformedURLException | ProtocolException -> 0x01fe, MalformedURLException | ProtocolException -> 0x01fe, IOException -> 0x01f4 }
            r4.<init>();	 Catch:{ MalformedURLException | ProtocolException -> 0x01fe, MalformedURLException | ProtocolException -> 0x01fe, IOException -> 0x01f4 }
            r5 = 1024; // 0x400 float:1.435E-42 double:5.06E-321;
            r5 = new byte[r5];	 Catch:{ MalformedURLException | ProtocolException -> 0x01fe, MalformedURLException | ProtocolException -> 0x01fe, IOException -> 0x01f4 }
        L_0x00b2:
            r6 = r3.read(r5);	 Catch:{ MalformedURLException | ProtocolException -> 0x01fe, MalformedURLException | ProtocolException -> 0x01fe, IOException -> 0x01f4 }
            r7 = -1;
            if (r6 == r7) goto L_0x00c2;
        L_0x00b9:
            r7 = new java.lang.String;	 Catch:{ MalformedURLException | ProtocolException -> 0x01fe, MalformedURLException | ProtocolException -> 0x01fe, IOException -> 0x01f4 }
            r7.<init>(r5, r1, r6);	 Catch:{ MalformedURLException | ProtocolException -> 0x01fe, MalformedURLException | ProtocolException -> 0x01fe, IOException -> 0x01f4 }
            r4.append(r7);	 Catch:{ MalformedURLException | ProtocolException -> 0x01fe, MalformedURLException | ProtocolException -> 0x01fe, IOException -> 0x01f4 }
            goto L_0x00b2;
        L_0x00c2:
            r3 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
            if (r0 != r3) goto L_0x01c0;
        L_0x00c6:
            r0 = new org.json.JSONObject;	 Catch:{ Exception -> 0x019a, MalformedURLException | ProtocolException -> 0x01fe, MalformedURLException | ProtocolException -> 0x01fe, IOException -> 0x01f4 }
            r3 = r4.toString();	 Catch:{ Exception -> 0x019a, MalformedURLException | ProtocolException -> 0x01fe, MalformedURLException | ProtocolException -> 0x01fe, IOException -> 0x01f4 }
            r0.<init>(r3);	 Catch:{ Exception -> 0x019a, MalformedURLException | ProtocolException -> 0x01fe, MalformedURLException | ProtocolException -> 0x01fe, IOException -> 0x01f4 }
            r3 = "status";
            r3 = r0.has(r3);	 Catch:{ Exception -> 0x019a, MalformedURLException | ProtocolException -> 0x01fe, MalformedURLException | ProtocolException -> 0x01fe, IOException -> 0x01f4 }
            if (r3 == 0) goto L_0x0177;
        L_0x00d7:
            r3 = "status";
            r3 = r0.getString(r3);	 Catch:{ Exception -> 0x019a, MalformedURLException | ProtocolException -> 0x01fe, MalformedURLException | ProtocolException -> 0x01fe, IOException -> 0x01f4 }
            r4 = "";
            r3 = r3.equalsIgnoreCase(r4);	 Catch:{ Exception -> 0x019a, MalformedURLException | ProtocolException -> 0x01fe, MalformedURLException | ProtocolException -> 0x01fe, IOException -> 0x01f4 }
            if (r3 != 0) goto L_0x0177;
        L_0x00e5:
            r3 = com.payu.india.e.d.this;	 Catch:{ Exception -> 0x019a, MalformedURLException | ProtocolException -> 0x01fe, MalformedURLException | ProtocolException -> 0x01fe, IOException -> 0x01f4 }
            r3 = r3.c;	 Catch:{ Exception -> 0x019a, MalformedURLException | ProtocolException -> 0x01fe, MalformedURLException | ProtocolException -> 0x01fe, IOException -> 0x01f4 }
            r4 = com.payu.india.e.d.this;	 Catch:{ Exception -> 0x019a, MalformedURLException | ProtocolException -> 0x01fe, MalformedURLException | ProtocolException -> 0x01fe, IOException -> 0x01f4 }
            r4 = r4.a;	 Catch:{ Exception -> 0x019a, MalformedURLException | ProtocolException -> 0x01fe, MalformedURLException | ProtocolException -> 0x01fe, IOException -> 0x01f4 }
            r3.deleteFile(r4);	 Catch:{ Exception -> 0x019a, MalformedURLException | ProtocolException -> 0x01fe, MalformedURLException | ProtocolException -> 0x01fe, IOException -> 0x01f4 }
            r3 = "status";
            r3 = r0.has(r3);	 Catch:{ Exception -> 0x019a, MalformedURLException | ProtocolException -> 0x01fe, MalformedURLException | ProtocolException -> 0x01fe, IOException -> 0x01f4 }
            if (r3 == 0) goto L_0x0153;
        L_0x00fc:
            r3 = "status";
            r3 = r0.getString(r3);	 Catch:{ Exception -> 0x019a, MalformedURLException | ProtocolException -> 0x01fe, MalformedURLException | ProtocolException -> 0x01fe, IOException -> 0x01f4 }
            r4 = "1";
            r3 = r3.equalsIgnoreCase(r4);	 Catch:{ Exception -> 0x019a, MalformedURLException | ProtocolException -> 0x01fe, MalformedURLException | ProtocolException -> 0x01fe, IOException -> 0x01f4 }
            if (r3 != 0) goto L_0x0118;
        L_0x010a:
            r3 = "status";
            r0 = r0.getString(r3);	 Catch:{ Exception -> 0x019a, MalformedURLException | ProtocolException -> 0x01fe, MalformedURLException | ProtocolException -> 0x01fe, IOException -> 0x01f4 }
            r3 = "-1";
            r0 = r0.equalsIgnoreCase(r3);	 Catch:{ Exception -> 0x019a, MalformedURLException | ProtocolException -> 0x01fe, MalformedURLException | ProtocolException -> 0x01fe, IOException -> 0x01f4 }
            if (r0 == 0) goto L_0x0153;
        L_0x0118:
            r0 = r1;
        L_0x0119:
            r3 = r2.length();	 Catch:{ Exception -> 0x019a, MalformedURLException | ProtocolException -> 0x01fe, MalformedURLException | ProtocolException -> 0x01fe, IOException -> 0x01f4 }
            if (r0 >= r3) goto L_0x0206;
        L_0x011f:
            r3 = com.payu.india.e.d.this;	 Catch:{ Exception -> 0x019a, MalformedURLException | ProtocolException -> 0x01fe, MalformedURLException | ProtocolException -> 0x01fe, IOException -> 0x01f4 }
            r4 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x019a, MalformedURLException | ProtocolException -> 0x01fe, MalformedURLException | ProtocolException -> 0x01fe, IOException -> 0x01f4 }
            r4.<init>();	 Catch:{ Exception -> 0x019a, MalformedURLException | ProtocolException -> 0x01fe, MalformedURLException | ProtocolException -> 0x01fe, IOException -> 0x01f4 }
            r5 = r2.get(r0);	 Catch:{ Exception -> 0x019a, MalformedURLException | ProtocolException -> 0x01fe, MalformedURLException | ProtocolException -> 0x01fe, IOException -> 0x01f4 }
            r5 = (org.json.JSONObject) r5;	 Catch:{ Exception -> 0x019a, MalformedURLException | ProtocolException -> 0x01fe, MalformedURLException | ProtocolException -> 0x01fe, IOException -> 0x01f4 }
            r6 = "merchant_key";
            r5 = r5.getString(r6);	 Catch:{ Exception -> 0x019a, MalformedURLException | ProtocolException -> 0x01fe, MalformedURLException | ProtocolException -> 0x01fe, IOException -> 0x01f4 }
            r4.append(r5);	 Catch:{ Exception -> 0x019a, MalformedURLException | ProtocolException -> 0x01fe, MalformedURLException | ProtocolException -> 0x01fe, IOException -> 0x01f4 }
            r5 = "|";
            r4.append(r5);	 Catch:{ Exception -> 0x019a, MalformedURLException | ProtocolException -> 0x01fe, MalformedURLException | ProtocolException -> 0x01fe, IOException -> 0x01f4 }
            r5 = r2.get(r0);	 Catch:{ Exception -> 0x019a, MalformedURLException | ProtocolException -> 0x01fe, MalformedURLException | ProtocolException -> 0x01fe, IOException -> 0x01f4 }
            r5 = (org.json.JSONObject) r5;	 Catch:{ Exception -> 0x019a, MalformedURLException | ProtocolException -> 0x01fe, MalformedURLException | ProtocolException -> 0x01fe, IOException -> 0x01f4 }
            r6 = "txnid";
            r5 = r5.getString(r6);	 Catch:{ Exception -> 0x019a, MalformedURLException | ProtocolException -> 0x01fe, MalformedURLException | ProtocolException -> 0x01fe, IOException -> 0x01f4 }
            r4.append(r5);	 Catch:{ Exception -> 0x019a, MalformedURLException | ProtocolException -> 0x01fe, MalformedURLException | ProtocolException -> 0x01fe, IOException -> 0x01f4 }
            r4 = r4.toString();	 Catch:{ Exception -> 0x019a, MalformedURLException | ProtocolException -> 0x01fe, MalformedURLException | ProtocolException -> 0x01fe, IOException -> 0x01f4 }
            r3.a(r4, r9);	 Catch:{ Exception -> 0x019a, MalformedURLException | ProtocolException -> 0x01fe, MalformedURLException | ProtocolException -> 0x01fe, IOException -> 0x01f4 }
            r0 = r0 + 1;
            goto L_0x0119;
        L_0x0153:
            r9 = com.payu.india.e.d.this;	 Catch:{ IOException -> 0x0171, Exception -> 0x019a, MalformedURLException | ProtocolException -> 0x01fe, MalformedURLException | ProtocolException -> 0x01fe }
            r9 = r9.c;	 Catch:{ IOException -> 0x0171, Exception -> 0x019a, MalformedURLException | ProtocolException -> 0x01fe, MalformedURLException | ProtocolException -> 0x01fe }
            r0 = com.payu.india.e.d.this;	 Catch:{ IOException -> 0x0171, Exception -> 0x019a, MalformedURLException | ProtocolException -> 0x01fe, MalformedURLException | ProtocolException -> 0x01fe }
            r0 = r0.a;	 Catch:{ IOException -> 0x0171, Exception -> 0x019a, MalformedURLException | ProtocolException -> 0x01fe, MalformedURLException | ProtocolException -> 0x01fe }
            r9 = r9.openFileOutput(r0, r1);	 Catch:{ IOException -> 0x0171, Exception -> 0x019a, MalformedURLException | ProtocolException -> 0x01fe, MalformedURLException | ProtocolException -> 0x01fe }
            r0 = r8.b;	 Catch:{ IOException -> 0x0171, Exception -> 0x019a, MalformedURLException | ProtocolException -> 0x01fe, MalformedURLException | ProtocolException -> 0x01fe }
            r0 = r0.getBytes();	 Catch:{ IOException -> 0x0171, Exception -> 0x019a, MalformedURLException | ProtocolException -> 0x01fe, MalformedURLException | ProtocolException -> 0x01fe }
            r9.write(r0);	 Catch:{ IOException -> 0x0171, Exception -> 0x019a, MalformedURLException | ProtocolException -> 0x01fe, MalformedURLException | ProtocolException -> 0x01fe }
            r9.close();	 Catch:{ IOException -> 0x0171, Exception -> 0x019a, MalformedURLException | ProtocolException -> 0x01fe, MalformedURLException | ProtocolException -> 0x01fe }
            goto L_0x0206;
        L_0x0171:
            r9 = move-exception;
            com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r9);	 Catch:{ Exception -> 0x019a, MalformedURLException | ProtocolException -> 0x01fe, MalformedURLException | ProtocolException -> 0x01fe, IOException -> 0x01f4 }
            goto L_0x0206;
        L_0x0177:
            r9 = com.payu.india.e.d.this;	 Catch:{ IOException -> 0x0195, Exception -> 0x019a, MalformedURLException | ProtocolException -> 0x01fe, MalformedURLException | ProtocolException -> 0x01fe }
            r9 = r9.c;	 Catch:{ IOException -> 0x0195, Exception -> 0x019a, MalformedURLException | ProtocolException -> 0x01fe, MalformedURLException | ProtocolException -> 0x01fe }
            r0 = com.payu.india.e.d.this;	 Catch:{ IOException -> 0x0195, Exception -> 0x019a, MalformedURLException | ProtocolException -> 0x01fe, MalformedURLException | ProtocolException -> 0x01fe }
            r0 = r0.a;	 Catch:{ IOException -> 0x0195, Exception -> 0x019a, MalformedURLException | ProtocolException -> 0x01fe, MalformedURLException | ProtocolException -> 0x01fe }
            r9 = r9.openFileOutput(r0, r1);	 Catch:{ IOException -> 0x0195, Exception -> 0x019a, MalformedURLException | ProtocolException -> 0x01fe, MalformedURLException | ProtocolException -> 0x01fe }
            r0 = r8.b;	 Catch:{ IOException -> 0x0195, Exception -> 0x019a, MalformedURLException | ProtocolException -> 0x01fe, MalformedURLException | ProtocolException -> 0x01fe }
            r0 = r0.getBytes();	 Catch:{ IOException -> 0x0195, Exception -> 0x019a, MalformedURLException | ProtocolException -> 0x01fe, MalformedURLException | ProtocolException -> 0x01fe }
            r9.write(r0);	 Catch:{ IOException -> 0x0195, Exception -> 0x019a, MalformedURLException | ProtocolException -> 0x01fe, MalformedURLException | ProtocolException -> 0x01fe }
            r9.close();	 Catch:{ IOException -> 0x0195, Exception -> 0x019a, MalformedURLException | ProtocolException -> 0x01fe, MalformedURLException | ProtocolException -> 0x01fe }
            goto L_0x0206;
        L_0x0195:
            r9 = move-exception;
            com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r9);	 Catch:{ Exception -> 0x019a, MalformedURLException | ProtocolException -> 0x01fe, MalformedURLException | ProtocolException -> 0x01fe, IOException -> 0x01f4 }
            goto L_0x0206;
        L_0x019a:
            r9 = move-exception;
            com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r9);	 Catch:{ MalformedURLException | ProtocolException -> 0x01fe, MalformedURLException | ProtocolException -> 0x01fe, IOException -> 0x01f4 }
            r9 = com.payu.india.e.d.this;	 Catch:{ IOException -> 0x01bb, MalformedURLException | ProtocolException -> 0x01fe, MalformedURLException | ProtocolException -> 0x01fe }
            r9 = r9.c;	 Catch:{ IOException -> 0x01bb, MalformedURLException | ProtocolException -> 0x01fe, MalformedURLException | ProtocolException -> 0x01fe }
            r0 = com.payu.india.e.d.this;	 Catch:{ IOException -> 0x01bb, MalformedURLException | ProtocolException -> 0x01fe, MalformedURLException | ProtocolException -> 0x01fe }
            r0 = r0.a;	 Catch:{ IOException -> 0x01bb, MalformedURLException | ProtocolException -> 0x01fe, MalformedURLException | ProtocolException -> 0x01fe }
            r9 = r9.openFileOutput(r0, r1);	 Catch:{ IOException -> 0x01bb, MalformedURLException | ProtocolException -> 0x01fe, MalformedURLException | ProtocolException -> 0x01fe }
            r0 = r8.b;	 Catch:{ IOException -> 0x01bb, MalformedURLException | ProtocolException -> 0x01fe, MalformedURLException | ProtocolException -> 0x01fe }
            r0 = r0.getBytes();	 Catch:{ IOException -> 0x01bb, MalformedURLException | ProtocolException -> 0x01fe, MalformedURLException | ProtocolException -> 0x01fe }
            r9.write(r0);	 Catch:{ IOException -> 0x01bb, MalformedURLException | ProtocolException -> 0x01fe, MalformedURLException | ProtocolException -> 0x01fe }
            r9.close();	 Catch:{ IOException -> 0x01bb, MalformedURLException | ProtocolException -> 0x01fe, MalformedURLException | ProtocolException -> 0x01fe }
            goto L_0x0206;
        L_0x01bb:
            r9 = move-exception;
            com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r9);	 Catch:{ MalformedURLException | ProtocolException -> 0x01fe, MalformedURLException | ProtocolException -> 0x01fe, IOException -> 0x01f4 }
            goto L_0x0206;
        L_0x01c0:
            r9 = com.payu.india.e.d.this;	 Catch:{ IOException -> 0x01dd, MalformedURLException | ProtocolException -> 0x01fe, MalformedURLException | ProtocolException -> 0x01fe }
            r9 = r9.c;	 Catch:{ IOException -> 0x01dd, MalformedURLException | ProtocolException -> 0x01fe, MalformedURLException | ProtocolException -> 0x01fe }
            r0 = com.payu.india.e.d.this;	 Catch:{ IOException -> 0x01dd, MalformedURLException | ProtocolException -> 0x01fe, MalformedURLException | ProtocolException -> 0x01fe }
            r0 = r0.a;	 Catch:{ IOException -> 0x01dd, MalformedURLException | ProtocolException -> 0x01fe, MalformedURLException | ProtocolException -> 0x01fe }
            r9 = r9.openFileOutput(r0, r1);	 Catch:{ IOException -> 0x01dd, MalformedURLException | ProtocolException -> 0x01fe, MalformedURLException | ProtocolException -> 0x01fe }
            r0 = r8.b;	 Catch:{ IOException -> 0x01dd, MalformedURLException | ProtocolException -> 0x01fe, MalformedURLException | ProtocolException -> 0x01fe }
            r0 = r0.getBytes();	 Catch:{ IOException -> 0x01dd, MalformedURLException | ProtocolException -> 0x01fe, MalformedURLException | ProtocolException -> 0x01fe }
            r9.write(r0);	 Catch:{ IOException -> 0x01dd, MalformedURLException | ProtocolException -> 0x01fe, MalformedURLException | ProtocolException -> 0x01fe }
            r9.close();	 Catch:{ IOException -> 0x01dd, MalformedURLException | ProtocolException -> 0x01fe, MalformedURLException | ProtocolException -> 0x01fe }
            goto L_0x0206;
        L_0x01dd:
            r9 = move-exception;
            com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r9);	 Catch:{ MalformedURLException | ProtocolException -> 0x01fe, MalformedURLException | ProtocolException -> 0x01fe, IOException -> 0x01f4 }
            goto L_0x0206;
        L_0x01e2:
            r9 = com.payu.india.e.d.this;	 Catch:{ MalformedURLException | ProtocolException -> 0x01fe, MalformedURLException | ProtocolException -> 0x01fe, IOException -> 0x01f4 }
            r9 = r9.c;	 Catch:{ MalformedURLException | ProtocolException -> 0x01fe, MalformedURLException | ProtocolException -> 0x01fe, IOException -> 0x01f4 }
            r0 = com.payu.india.e.d.this;	 Catch:{ MalformedURLException | ProtocolException -> 0x01fe, MalformedURLException | ProtocolException -> 0x01fe, IOException -> 0x01f4 }
            r0 = r0.a;	 Catch:{ MalformedURLException | ProtocolException -> 0x01fe, MalformedURLException | ProtocolException -> 0x01fe, IOException -> 0x01f4 }
            r9.deleteFile(r0);	 Catch:{ MalformedURLException | ProtocolException -> 0x01fe, MalformedURLException | ProtocolException -> 0x01fe, IOException -> 0x01f4 }
            goto L_0x0206;
        L_0x01f2:
            r9 = move-exception;
            goto L_0x0203;
        L_0x01f4:
            r9 = move-exception;
            r0 = com.payu.india.e.d.this;	 Catch:{ Exception -> 0x01f2 }
            r0.c();	 Catch:{ Exception -> 0x01f2 }
            com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r9);	 Catch:{ Exception -> 0x01f2 }
            goto L_0x0206;
        L_0x01fe:
            r9 = move-exception;
            com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r9);	 Catch:{ Exception -> 0x01f2 }
            goto L_0x0206;
        L_0x0203:
            com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r9);
        L_0x0206:
            r9 = 0;
            return r9;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.payu.india.e.d$a.doInBackground(java.lang.String[]):java.lang.String");
        }
    }

    public d(Context context, String str) {
        this.d = h ? "https://info.payu.in/merchant/mobileWebService.php" : "https://mobiletest.payu.in/merchant/mobileWebService.php";
        this.e = false;
        this.f = new ArrayList();
        this.c = context;
        this.a = str;
        final UncaughtExceptionHandler defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(new UncaughtExceptionHandler() {
            public void uncaughtException(Thread thread, Throwable th) {
                while (d.this.e) {
                }
                d.this.a();
                try {
                    int i = 0;
                    FileOutputStream openFileOutput = d.this.c.openFileOutput(d.this.a, 0);
                    int size = d.this.f.size();
                    while (i < size) {
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append((String) d.this.f.get(i));
                        stringBuilder.append("\r\n");
                        openFileOutput.write(stringBuilder.toString().getBytes());
                        i++;
                    }
                    openFileOutput.close();
                } catch (IOException e) {
                    ThrowableExtension.printStackTrace(e);
                }
                d.this.b();
                defaultUncaughtExceptionHandler.uncaughtException(thread, th);
            }
        });
    }

    public void a(String str) {
        if (this.e) {
            this.f.add(str);
        } else {
            a();
            try {
                JSONObject jSONObject = new JSONObject(str);
                JSONArray jSONArray = new JSONArray();
                String str2 = "";
                if (!new File(this.c.getFilesDir(), this.a).exists()) {
                    this.c.openFileOutput(this.a, 0);
                }
                FileInputStream openFileInput = this.c.openFileInput(this.a);
                while (true) {
                    int read = openFileInput.read();
                    if (read == -1) {
                        break;
                    }
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(str2);
                    stringBuilder.append(Character.toString((char) read));
                    str2 = stringBuilder.toString();
                    jSONArray = new JSONArray(str2);
                }
                openFileInput.close();
                FileOutputStream openFileOutput = this.c.openFileOutput(this.a, 0);
                jSONArray.put(jSONArray.length(), jSONObject);
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append(str2);
                stringBuilder2.append(jSONArray.toString());
                stringBuilder2.append("\r\n");
                openFileOutput.write(stringBuilder2.toString().getBytes());
                openFileOutput.close();
            } catch (IOException e) {
                ThrowableExtension.printStackTrace(e);
                this.f.add(str);
            } catch (JSONException e2) {
                ThrowableExtension.printStackTrace(e2);
            }
            b();
        }
        c();
    }

    private void c() {
        if (this.g != null) {
            this.g.cancel();
        }
        this.g = new Timer();
        this.g.schedule(new TimerTask() {
            public void run() {
                while (d.this.e) {
                }
                d.b = DefaultRenderersFactory.DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS;
                d.this.a();
                String str = "";
                int read;
                StringBuilder stringBuilder;
                int size;
                StringBuilder stringBuilder2;
                a aVar;
                Object[] objArr;
                try {
                    if (!new File(d.this.c.getFilesDir(), d.this.a).exists()) {
                        d.this.c.openFileOutput(d.this.a, 0);
                    }
                    FileInputStream openFileInput = d.this.c.openFileInput(d.this.a);
                    while (true) {
                        read = openFileInput.read();
                        if (read == -1) {
                            break;
                        }
                        stringBuilder = new StringBuilder();
                        stringBuilder.append(str);
                        stringBuilder.append(Character.toString((char) read));
                        str = stringBuilder.toString();
                    }
                    openFileInput.close();
                    size = d.this.f.size();
                    while (size > 0) {
                        size--;
                        stringBuilder2 = new StringBuilder();
                        stringBuilder2.append(str);
                        stringBuilder2.append((String) d.this.f.get(size));
                        stringBuilder2.append("\r\n");
                        str = stringBuilder2.toString();
                        if (size >= 0 && d.this.f.size() > size) {
                            d.this.f.remove(size);
                        }
                    }
                    str = str.trim();
                    if (str.length() > 0) {
                        aVar = new a(str);
                        objArr = new String[]{str};
                        aVar.execute(objArr);
                    }
                } catch (IOException e) {
                    ThrowableExtension.printStackTrace(e);
                    size = d.this.f.size();
                    while (size > 0) {
                        size--;
                        stringBuilder2 = new StringBuilder();
                        stringBuilder2.append(str);
                        stringBuilder2.append((String) d.this.f.get(size));
                        stringBuilder2.append("\r\n");
                        str = stringBuilder2.toString();
                        if (size >= 0 && d.this.f.size() > size) {
                            d.this.f.remove(size);
                        }
                    }
                    str = str.trim();
                    if (str.length() > 0) {
                        aVar = new a(str);
                        objArr = new String[]{str};
                    }
                } catch (Throwable th) {
                    read = d.this.f.size();
                    while (read > 0) {
                        read--;
                        stringBuilder = new StringBuilder();
                        stringBuilder.append(str);
                        stringBuilder.append((String) d.this.f.get(read));
                        stringBuilder.append("\r\n");
                        str = stringBuilder.toString();
                        if (read >= 0 && d.this.f.size() > read) {
                            d.this.f.remove(read);
                        }
                    }
                    str = str.trim();
                    if (str.length() > 0) {
                        new a(str).execute(new String[]{str});
                    }
                }
                if (d.this.f.size() > 0) {
                    d.this.c();
                }
                d.this.b();
            }
        }, b);
    }

    /* Access modifiers changed, original: declared_synchronized */
    public synchronized void a() {
        this.e = true;
    }

    /* Access modifiers changed, original: declared_synchronized */
    public synchronized void b() {
        this.e = false;
    }

    public boolean b(String str) {
        return this.c.getSharedPreferences("com.payu", 0).getBoolean(str, false);
    }

    public void a(String str, boolean z) {
        Editor edit = this.c.getSharedPreferences("com.payu", 0).edit();
        edit.putBoolean(str, z);
        edit.apply();
    }

    public static JSONArray a(JSONArray jSONArray, int i) throws JSONException {
        if (i < 0 || i > jSONArray.length() - 1) {
            throw new IndexOutOfBoundsException();
        }
        JSONArray jSONArray2 = new JSONArray();
        int length = jSONArray.length();
        for (int i2 = 0; i2 < length; i2++) {
            if (i2 != i) {
                jSONArray2.put(jSONArray.get(i2));
            }
        }
        return jSONArray2;
    }
}
