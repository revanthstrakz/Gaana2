package com.payu.custombrowser.a;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.payu.custombrowser.util.CBUtil;
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

public class b {
    private long a = 0;
    private final Context b;
    private boolean c = false;
    private ArrayList<String> d;
    private Timer e;
    private String f = "cb_local_cache_device";
    private CBUtil g;
    private boolean h;

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
        /* JADX WARNING: Missing exception handler attribute for start block: B:29:0x0107 */
        /* JADX WARNING: Removed duplicated region for block: B:37:0x0133 A:{ExcHandler: MalformedURLException | ProtocolException (r8_25 'e' java.lang.Throwable A:{Catch:{ Exception -> 0x0127 }}), Splitter:B:0:0x0000, Catch:{ Exception -> 0x0127 }} */
        /* JADX WARNING: Removed duplicated region for block: B:37:0x0133 A:{ExcHandler: MalformedURLException | ProtocolException (r8_25 'e' java.lang.Throwable A:{Catch:{ Exception -> 0x0127 }}), Splitter:B:0:0x0000, Catch:{ Exception -> 0x0127 }} */
        /* JADX WARNING: Removed duplicated region for block: B:34:0x0129 A:{ExcHandler: IOException (r8_24 'e' java.io.IOException), Splitter:B:0:0x0000} */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing block: B:30:?, code skipped:
            com.payu.custombrowser.a.b.a(r7.a, r7.b);
     */
        /* JADX WARNING: Missing block: B:33:0x0127, code skipped:
            r8 = move-exception;
     */
        /* JADX WARNING: Missing block: B:34:0x0129, code skipped:
            r8 = move-exception;
     */
        /* JADX WARNING: Missing block: B:36:?, code skipped:
            com.payu.custombrowser.a.b.h(r7.a);
            com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r8);
     */
        /* JADX WARNING: Missing block: B:37:0x0133, code skipped:
            r8 = move-exception;
     */
        /* JADX WARNING: Missing block: B:38:0x0134, code skipped:
            com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r8);
     */
        /* JADX WARNING: Missing block: B:39:0x0138, code skipped:
            com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r8);
     */
        /* renamed from: a */
        public java.lang.String doInBackground(java.lang.String... r8) {
            /*
            r7 = this;
            r0 = com.payu.custombrowser.a.b.this;	 Catch:{ MalformedURLException | ProtocolException -> 0x0133, MalformedURLException | ProtocolException -> 0x0133, IOException -> 0x0129 }
            r0 = r0.b;	 Catch:{ MalformedURLException | ProtocolException -> 0x0133, MalformedURLException | ProtocolException -> 0x0133, IOException -> 0x0129 }
            if (r0 == 0) goto L_0x013b;
        L_0x0008:
            r0 = com.payu.custombrowser.a.b.this;	 Catch:{ MalformedURLException | ProtocolException -> 0x0133, MalformedURLException | ProtocolException -> 0x0133, IOException -> 0x0129 }
            r0 = r0.h;	 Catch:{ MalformedURLException | ProtocolException -> 0x0133, MalformedURLException | ProtocolException -> 0x0133, IOException -> 0x0129 }
            if (r0 != 0) goto L_0x013b;
        L_0x0010:
            r0 = new org.json.JSONArray;	 Catch:{ MalformedURLException | ProtocolException -> 0x0133, MalformedURLException | ProtocolException -> 0x0133, IOException -> 0x0129 }
            r1 = 0;
            r8 = r8[r1];	 Catch:{ MalformedURLException | ProtocolException -> 0x0133, MalformedURLException | ProtocolException -> 0x0133, IOException -> 0x0129 }
            r0.<init>(r8);	 Catch:{ MalformedURLException | ProtocolException -> 0x0133, MalformedURLException | ProtocolException -> 0x0133, IOException -> 0x0129 }
            r2 = r0;
            r8 = r1;
        L_0x001a:
            r3 = r0.length();	 Catch:{ MalformedURLException | ProtocolException -> 0x0133, MalformedURLException | ProtocolException -> 0x0133, IOException -> 0x0129 }
            if (r8 >= r3) goto L_0x0063;
        L_0x0020:
            r3 = com.payu.custombrowser.a.b.this;	 Catch:{ MalformedURLException | ProtocolException -> 0x0133, MalformedURLException | ProtocolException -> 0x0133, IOException -> 0x0129 }
            r4 = new java.lang.StringBuilder;	 Catch:{ MalformedURLException | ProtocolException -> 0x0133, MalformedURLException | ProtocolException -> 0x0133, IOException -> 0x0129 }
            r4.<init>();	 Catch:{ MalformedURLException | ProtocolException -> 0x0133, MalformedURLException | ProtocolException -> 0x0133, IOException -> 0x0129 }
            r5 = r0.get(r8);	 Catch:{ MalformedURLException | ProtocolException -> 0x0133, MalformedURLException | ProtocolException -> 0x0133, IOException -> 0x0129 }
            r5 = (org.json.JSONObject) r5;	 Catch:{ MalformedURLException | ProtocolException -> 0x0133, MalformedURLException | ProtocolException -> 0x0133, IOException -> 0x0129 }
            r6 = "merchant_key";
            r5 = r5.getString(r6);	 Catch:{ MalformedURLException | ProtocolException -> 0x0133, MalformedURLException | ProtocolException -> 0x0133, IOException -> 0x0129 }
            r4.append(r5);	 Catch:{ MalformedURLException | ProtocolException -> 0x0133, MalformedURLException | ProtocolException -> 0x0133, IOException -> 0x0129 }
            r5 = "|";
            r4.append(r5);	 Catch:{ MalformedURLException | ProtocolException -> 0x0133, MalformedURLException | ProtocolException -> 0x0133, IOException -> 0x0129 }
            r5 = r0.get(r8);	 Catch:{ MalformedURLException | ProtocolException -> 0x0133, MalformedURLException | ProtocolException -> 0x0133, IOException -> 0x0129 }
            r5 = (org.json.JSONObject) r5;	 Catch:{ MalformedURLException | ProtocolException -> 0x0133, MalformedURLException | ProtocolException -> 0x0133, IOException -> 0x0129 }
            r6 = "txnid";
            r5 = r5.getString(r6);	 Catch:{ MalformedURLException | ProtocolException -> 0x0133, MalformedURLException | ProtocolException -> 0x0133, IOException -> 0x0129 }
            r4.append(r5);	 Catch:{ MalformedURLException | ProtocolException -> 0x0133, MalformedURLException | ProtocolException -> 0x0133, IOException -> 0x0129 }
            r4 = r4.toString();	 Catch:{ MalformedURLException | ProtocolException -> 0x0133, MalformedURLException | ProtocolException -> 0x0133, IOException -> 0x0129 }
            r5 = com.payu.custombrowser.a.b.this;	 Catch:{ MalformedURLException | ProtocolException -> 0x0133, MalformedURLException | ProtocolException -> 0x0133, IOException -> 0x0129 }
            r5 = r5.b;	 Catch:{ MalformedURLException | ProtocolException -> 0x0133, MalformedURLException | ProtocolException -> 0x0133, IOException -> 0x0129 }
            r3 = r3.a(r4, r5);	 Catch:{ MalformedURLException | ProtocolException -> 0x0133, MalformedURLException | ProtocolException -> 0x0133, IOException -> 0x0129 }
            if (r3 == 0) goto L_0x0060;
        L_0x005a:
            r2 = com.payu.custombrowser.a.b.this;	 Catch:{ MalformedURLException | ProtocolException -> 0x0133, MalformedURLException | ProtocolException -> 0x0133, IOException -> 0x0129 }
            r2 = r2.a(r0, r8);	 Catch:{ MalformedURLException | ProtocolException -> 0x0133, MalformedURLException | ProtocolException -> 0x0133, IOException -> 0x0129 }
        L_0x0060:
            r8 = r8 + 1;
            goto L_0x001a;
        L_0x0063:
            r8 = r2.length();	 Catch:{ MalformedURLException | ProtocolException -> 0x0133, MalformedURLException | ProtocolException -> 0x0133, IOException -> 0x0129 }
            if (r8 <= 0) goto L_0x013b;
        L_0x0069:
            r8 = new java.lang.StringBuilder;	 Catch:{ MalformedURLException | ProtocolException -> 0x0133, MalformedURLException | ProtocolException -> 0x0133, IOException -> 0x0129 }
            r8.<init>();	 Catch:{ MalformedURLException | ProtocolException -> 0x0133, MalformedURLException | ProtocolException -> 0x0133, IOException -> 0x0129 }
            r0 = "command=DeviceAnalytics&data=";
            r8.append(r0);	 Catch:{ MalformedURLException | ProtocolException -> 0x0133, MalformedURLException | ProtocolException -> 0x0133, IOException -> 0x0129 }
            r0 = r2.toString();	 Catch:{ MalformedURLException | ProtocolException -> 0x0133, MalformedURLException | ProtocolException -> 0x0133, IOException -> 0x0129 }
            r8.append(r0);	 Catch:{ MalformedURLException | ProtocolException -> 0x0133, MalformedURLException | ProtocolException -> 0x0133, IOException -> 0x0129 }
            r8 = r8.toString();	 Catch:{ MalformedURLException | ProtocolException -> 0x0133, MalformedURLException | ProtocolException -> 0x0133, IOException -> 0x0129 }
            r0 = "https://info.payu.in/merchant/MobileAnalytics";
            r3 = com.payu.custombrowser.a.b.this;	 Catch:{ MalformedURLException | ProtocolException -> 0x0133, MalformedURLException | ProtocolException -> 0x0133, IOException -> 0x0129 }
            r3 = r3.g;	 Catch:{ MalformedURLException | ProtocolException -> 0x0133, MalformedURLException | ProtocolException -> 0x0133, IOException -> 0x0129 }
            r8 = r3.getHttpsConn(r0, r8);	 Catch:{ MalformedURLException | ProtocolException -> 0x0133, MalformedURLException | ProtocolException -> 0x0133, IOException -> 0x0129 }
            if (r8 == 0) goto L_0x0117;
        L_0x008c:
            r0 = r8.getResponseCode();	 Catch:{ MalformedURLException | ProtocolException -> 0x0133, MalformedURLException | ProtocolException -> 0x0133, IOException -> 0x0129 }
            r3 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
            if (r0 != r3) goto L_0x010f;
        L_0x0094:
            r8 = r8.getInputStream();	 Catch:{ Exception -> 0x0107, MalformedURLException | ProtocolException -> 0x0133, MalformedURLException | ProtocolException -> 0x0133, IOException -> 0x0129 }
            r8 = com.payu.custombrowser.util.CBUtil.getStringBufferFromInputStream(r8);	 Catch:{ Exception -> 0x0107, MalformedURLException | ProtocolException -> 0x0133, MalformedURLException | ProtocolException -> 0x0133, IOException -> 0x0129 }
            if (r8 == 0) goto L_0x013b;
        L_0x009e:
            r0 = new org.json.JSONObject;	 Catch:{ Exception -> 0x0107, MalformedURLException | ProtocolException -> 0x0133, MalformedURLException | ProtocolException -> 0x0133, IOException -> 0x0129 }
            r8 = r8.toString();	 Catch:{ Exception -> 0x0107, MalformedURLException | ProtocolException -> 0x0133, MalformedURLException | ProtocolException -> 0x0133, IOException -> 0x0129 }
            r0.<init>(r8);	 Catch:{ Exception -> 0x0107, MalformedURLException | ProtocolException -> 0x0133, MalformedURLException | ProtocolException -> 0x0133, IOException -> 0x0129 }
            r8 = "status";
            r8 = r0.has(r8);	 Catch:{ Exception -> 0x0107, MalformedURLException | ProtocolException -> 0x0133, MalformedURLException | ProtocolException -> 0x0133, IOException -> 0x0129 }
            if (r8 == 0) goto L_0x00ff;
        L_0x00af:
            r8 = com.payu.custombrowser.a.b.this;	 Catch:{ Exception -> 0x0107, MalformedURLException | ProtocolException -> 0x0133, MalformedURLException | ProtocolException -> 0x0133, IOException -> 0x0129 }
            r8 = r8.b;	 Catch:{ Exception -> 0x0107, MalformedURLException | ProtocolException -> 0x0133, MalformedURLException | ProtocolException -> 0x0133, IOException -> 0x0129 }
            r0 = com.payu.custombrowser.a.b.this;	 Catch:{ Exception -> 0x0107, MalformedURLException | ProtocolException -> 0x0133, MalformedURLException | ProtocolException -> 0x0133, IOException -> 0x0129 }
            r0 = r0.f;	 Catch:{ Exception -> 0x0107, MalformedURLException | ProtocolException -> 0x0133, MalformedURLException | ProtocolException -> 0x0133, IOException -> 0x0129 }
            r8.deleteFile(r0);	 Catch:{ Exception -> 0x0107, MalformedURLException | ProtocolException -> 0x0133, MalformedURLException | ProtocolException -> 0x0133, IOException -> 0x0129 }
        L_0x00be:
            r8 = r2.length();	 Catch:{ Exception -> 0x0107, MalformedURLException | ProtocolException -> 0x0133, MalformedURLException | ProtocolException -> 0x0133, IOException -> 0x0129 }
            if (r1 >= r8) goto L_0x013b;
        L_0x00c4:
            r8 = com.payu.custombrowser.a.b.this;	 Catch:{ Exception -> 0x0107, MalformedURLException | ProtocolException -> 0x0133, MalformedURLException | ProtocolException -> 0x0133, IOException -> 0x0129 }
            r0 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0107, MalformedURLException | ProtocolException -> 0x0133, MalformedURLException | ProtocolException -> 0x0133, IOException -> 0x0129 }
            r0.<init>();	 Catch:{ Exception -> 0x0107, MalformedURLException | ProtocolException -> 0x0133, MalformedURLException | ProtocolException -> 0x0133, IOException -> 0x0129 }
            r3 = r2.get(r1);	 Catch:{ Exception -> 0x0107, MalformedURLException | ProtocolException -> 0x0133, MalformedURLException | ProtocolException -> 0x0133, IOException -> 0x0129 }
            r3 = (org.json.JSONObject) r3;	 Catch:{ Exception -> 0x0107, MalformedURLException | ProtocolException -> 0x0133, MalformedURLException | ProtocolException -> 0x0133, IOException -> 0x0129 }
            r4 = "merchant_key";
            r3 = r3.getString(r4);	 Catch:{ Exception -> 0x0107, MalformedURLException | ProtocolException -> 0x0133, MalformedURLException | ProtocolException -> 0x0133, IOException -> 0x0129 }
            r0.append(r3);	 Catch:{ Exception -> 0x0107, MalformedURLException | ProtocolException -> 0x0133, MalformedURLException | ProtocolException -> 0x0133, IOException -> 0x0129 }
            r3 = "|";
            r0.append(r3);	 Catch:{ Exception -> 0x0107, MalformedURLException | ProtocolException -> 0x0133, MalformedURLException | ProtocolException -> 0x0133, IOException -> 0x0129 }
            r3 = r2.get(r1);	 Catch:{ Exception -> 0x0107, MalformedURLException | ProtocolException -> 0x0133, MalformedURLException | ProtocolException -> 0x0133, IOException -> 0x0129 }
            r3 = (org.json.JSONObject) r3;	 Catch:{ Exception -> 0x0107, MalformedURLException | ProtocolException -> 0x0133, MalformedURLException | ProtocolException -> 0x0133, IOException -> 0x0129 }
            r4 = "txnid";
            r3 = r3.getString(r4);	 Catch:{ Exception -> 0x0107, MalformedURLException | ProtocolException -> 0x0133, MalformedURLException | ProtocolException -> 0x0133, IOException -> 0x0129 }
            r0.append(r3);	 Catch:{ Exception -> 0x0107, MalformedURLException | ProtocolException -> 0x0133, MalformedURLException | ProtocolException -> 0x0133, IOException -> 0x0129 }
            r0 = r0.toString();	 Catch:{ Exception -> 0x0107, MalformedURLException | ProtocolException -> 0x0133, MalformedURLException | ProtocolException -> 0x0133, IOException -> 0x0129 }
            r3 = com.payu.custombrowser.a.b.this;	 Catch:{ Exception -> 0x0107, MalformedURLException | ProtocolException -> 0x0133, MalformedURLException | ProtocolException -> 0x0133, IOException -> 0x0129 }
            r3 = r3.b;	 Catch:{ Exception -> 0x0107, MalformedURLException | ProtocolException -> 0x0133, MalformedURLException | ProtocolException -> 0x0133, IOException -> 0x0129 }
            r4 = 1;
            r8.a(r0, r4, r3);	 Catch:{ Exception -> 0x0107, MalformedURLException | ProtocolException -> 0x0133, MalformedURLException | ProtocolException -> 0x0133, IOException -> 0x0129 }
            r1 = r1 + 1;
            goto L_0x00be;
        L_0x00ff:
            r8 = com.payu.custombrowser.a.b.this;	 Catch:{ Exception -> 0x0107, MalformedURLException | ProtocolException -> 0x0133, MalformedURLException | ProtocolException -> 0x0133, IOException -> 0x0129 }
            r0 = r7.b;	 Catch:{ Exception -> 0x0107, MalformedURLException | ProtocolException -> 0x0133, MalformedURLException | ProtocolException -> 0x0133, IOException -> 0x0129 }
            r8.b(r0);	 Catch:{ Exception -> 0x0107, MalformedURLException | ProtocolException -> 0x0133, MalformedURLException | ProtocolException -> 0x0133, IOException -> 0x0129 }
            goto L_0x013b;
        L_0x0107:
            r8 = com.payu.custombrowser.a.b.this;	 Catch:{ MalformedURLException | ProtocolException -> 0x0133, MalformedURLException | ProtocolException -> 0x0133, IOException -> 0x0129 }
            r0 = r7.b;	 Catch:{ MalformedURLException | ProtocolException -> 0x0133, MalformedURLException | ProtocolException -> 0x0133, IOException -> 0x0129 }
            r8.b(r0);	 Catch:{ MalformedURLException | ProtocolException -> 0x0133, MalformedURLException | ProtocolException -> 0x0133, IOException -> 0x0129 }
            goto L_0x013b;
        L_0x010f:
            r8 = com.payu.custombrowser.a.b.this;	 Catch:{ MalformedURLException | ProtocolException -> 0x0133, MalformedURLException | ProtocolException -> 0x0133, IOException -> 0x0129 }
            r0 = r7.b;	 Catch:{ MalformedURLException | ProtocolException -> 0x0133, MalformedURLException | ProtocolException -> 0x0133, IOException -> 0x0129 }
            r8.b(r0);	 Catch:{ MalformedURLException | ProtocolException -> 0x0133, MalformedURLException | ProtocolException -> 0x0133, IOException -> 0x0129 }
            goto L_0x013b;
        L_0x0117:
            r8 = com.payu.custombrowser.a.b.this;	 Catch:{ MalformedURLException | ProtocolException -> 0x0133, MalformedURLException | ProtocolException -> 0x0133, IOException -> 0x0129 }
            r8 = r8.b;	 Catch:{ MalformedURLException | ProtocolException -> 0x0133, MalformedURLException | ProtocolException -> 0x0133, IOException -> 0x0129 }
            r0 = com.payu.custombrowser.a.b.this;	 Catch:{ MalformedURLException | ProtocolException -> 0x0133, MalformedURLException | ProtocolException -> 0x0133, IOException -> 0x0129 }
            r0 = r0.f;	 Catch:{ MalformedURLException | ProtocolException -> 0x0133, MalformedURLException | ProtocolException -> 0x0133, IOException -> 0x0129 }
            r8.deleteFile(r0);	 Catch:{ MalformedURLException | ProtocolException -> 0x0133, MalformedURLException | ProtocolException -> 0x0133, IOException -> 0x0129 }
            goto L_0x013b;
        L_0x0127:
            r8 = move-exception;
            goto L_0x0138;
        L_0x0129:
            r8 = move-exception;
            r0 = com.payu.custombrowser.a.b.this;	 Catch:{ Exception -> 0x0127 }
            r0.b();	 Catch:{ Exception -> 0x0127 }
            com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r8);	 Catch:{ Exception -> 0x0127 }
            goto L_0x013b;
        L_0x0133:
            r8 = move-exception;
            com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r8);	 Catch:{ Exception -> 0x0127 }
            goto L_0x013b;
        L_0x0138:
            com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r8);
        L_0x013b:
            r8 = 0;
            return r8;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.payu.custombrowser.a.b$a.doInBackground(java.lang.String[]):java.lang.String");
        }
    }

    public b(Context context, final String str) {
        this.b = context;
        this.f = str;
        this.d = new ArrayList();
        this.g = new CBUtil();
        final UncaughtExceptionHandler defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(new UncaughtExceptionHandler() {
            public void uncaughtException(Thread thread, Throwable th) {
                while (b.this.c) {
                }
                b.this.c();
                try {
                    int i = 0;
                    FileOutputStream openFileOutput = b.this.b.openFileOutput(str, 0);
                    int size = b.this.d.size();
                    while (i < size) {
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append((String) b.this.d.get(i));
                        stringBuilder.append("\r\n");
                        openFileOutput.write(stringBuilder.toString().getBytes());
                        i++;
                    }
                    openFileOutput.close();
                } catch (IOException e) {
                    ThrowableExtension.printStackTrace(e);
                }
                b.this.d();
                defaultUncaughtExceptionHandler.uncaughtException(thread, th);
            }
        });
    }

    public void a(String str) {
        if (this.c) {
            this.d.add(str);
        } else {
            c();
            try {
                JSONArray jSONArray;
                JSONObject jSONObject = new JSONObject(str);
                String str2 = "";
                if (!new File(this.b.getFilesDir(), this.f).exists()) {
                    this.b.openFileOutput(this.f, 0);
                }
                FileInputStream openFileInput = this.b.openFileInput(this.f);
                while (true) {
                    int read = openFileInput.read();
                    if (read == -1) {
                        break;
                    }
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(str2);
                    stringBuilder.append(Character.toString((char) read));
                    str2 = stringBuilder.toString();
                }
                if (str2.equalsIgnoreCase("")) {
                    jSONArray = new JSONArray();
                } else {
                    jSONArray = new JSONArray(str2);
                }
                openFileInput.close();
                FileOutputStream openFileOutput = this.b.openFileOutput(this.f, 0);
                jSONArray.put(jSONArray.length(), jSONObject);
                openFileOutput.write(jSONArray.toString().getBytes());
                openFileOutput.close();
            } catch (IOException e) {
                ThrowableExtension.printStackTrace(e);
                this.d.add(str);
            } catch (JSONException e2) {
                ThrowableExtension.printStackTrace(e2);
            }
            d();
        }
        b();
    }

    private void b() {
        if (this.e != null) {
            this.e.cancel();
        }
        this.e = new Timer();
        this.e.schedule(new TimerTask() {
            /* JADX WARNING: Removed duplicated region for block: B:41:0x014a  */
            public void run() {
                /*
                r6 = this;
            L_0x0000:
                r0 = com.payu.custombrowser.a.b.this;
                r0 = r0.c;
                if (r0 == 0) goto L_0x0009;
            L_0x0008:
                goto L_0x0000;
            L_0x0009:
                r0 = com.payu.custombrowser.a.b.this;
                r1 = 5000; // 0x1388 float:7.006E-42 double:2.4703E-320;
                r0.a = r1;
                r0 = com.payu.custombrowser.a.b.this;
                r0.c();
                r0 = "";
                r1 = 1;
                r2 = 0;
                r3 = new java.io.File;	 Catch:{ IOException -> 0x00d2 }
                r4 = com.payu.custombrowser.a.b.this;	 Catch:{ IOException -> 0x00d2 }
                r4 = r4.b;	 Catch:{ IOException -> 0x00d2 }
                r4 = r4.getFilesDir();	 Catch:{ IOException -> 0x00d2 }
                r5 = com.payu.custombrowser.a.b.this;	 Catch:{ IOException -> 0x00d2 }
                r5 = r5.f;	 Catch:{ IOException -> 0x00d2 }
                r3.<init>(r4, r5);	 Catch:{ IOException -> 0x00d2 }
                r3 = r3.exists();	 Catch:{ IOException -> 0x00d2 }
                if (r3 != 0) goto L_0x0043;
            L_0x0034:
                r3 = com.payu.custombrowser.a.b.this;	 Catch:{ IOException -> 0x00d2 }
                r3 = r3.b;	 Catch:{ IOException -> 0x00d2 }
                r4 = com.payu.custombrowser.a.b.this;	 Catch:{ IOException -> 0x00d2 }
                r4 = r4.f;	 Catch:{ IOException -> 0x00d2 }
                r3.openFileOutput(r4, r2);	 Catch:{ IOException -> 0x00d2 }
            L_0x0043:
                r3 = com.payu.custombrowser.a.b.this;	 Catch:{ IOException -> 0x00d2 }
                r3 = r3.b;	 Catch:{ IOException -> 0x00d2 }
                r4 = com.payu.custombrowser.a.b.this;	 Catch:{ IOException -> 0x00d2 }
                r4 = r4.f;	 Catch:{ IOException -> 0x00d2 }
                r3 = r3.openFileInput(r4);	 Catch:{ IOException -> 0x00d2 }
            L_0x0053:
                r4 = r3.read();	 Catch:{ IOException -> 0x00d2 }
                r5 = -1;
                if (r4 == r5) goto L_0x0070;
            L_0x005a:
                r5 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x00d2 }
                r5.<init>();	 Catch:{ IOException -> 0x00d2 }
                r5.append(r0);	 Catch:{ IOException -> 0x00d2 }
                r4 = (char) r4;	 Catch:{ IOException -> 0x00d2 }
                r4 = java.lang.Character.toString(r4);	 Catch:{ IOException -> 0x00d2 }
                r5.append(r4);	 Catch:{ IOException -> 0x00d2 }
                r4 = r5.toString();	 Catch:{ IOException -> 0x00d2 }
                r0 = r4;
                goto L_0x0053;
            L_0x0070:
                r3.close();	 Catch:{ IOException -> 0x00d2 }
                r3 = com.payu.custombrowser.a.b.this;
                r3 = r3.d;
                r3 = r3.size();
            L_0x007d:
                if (r3 <= 0) goto L_0x00b9;
            L_0x007f:
                r3 = r3 + -1;
                r4 = new java.lang.StringBuilder;
                r4.<init>();
                r4.append(r0);
                r0 = com.payu.custombrowser.a.b.this;
                r0 = r0.d;
                r0 = r0.get(r3);
                r0 = (java.lang.String) r0;
                r4.append(r0);
                r0 = "\r\n";
                r4.append(r0);
                r0 = r4.toString();
                if (r3 < 0) goto L_0x007d;
            L_0x00a3:
                r4 = com.payu.custombrowser.a.b.this;
                r4 = r4.d;
                r4 = r4.size();
                if (r4 <= r3) goto L_0x007d;
            L_0x00af:
                r4 = com.payu.custombrowser.a.b.this;
                r4 = r4.d;
                r4.remove(r3);
                goto L_0x007d;
            L_0x00b9:
                r0 = r0.trim();
                r3 = r0.length();
                if (r3 <= 0) goto L_0x0135;
            L_0x00c3:
                r3 = new com.payu.custombrowser.a.b$a;
                r4 = com.payu.custombrowser.a.b.this;
                r3.<init>(r0);
                r1 = new java.lang.String[r1];
                r1[r2] = r0;
                goto L_0x0131;
            L_0x00cf:
                r3 = move-exception;
                goto L_0x0155;
            L_0x00d2:
                r3 = move-exception;
                com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r3);	 Catch:{ all -> 0x00cf }
                r3 = com.payu.custombrowser.a.b.this;
                r3 = r3.d;
                r3 = r3.size();
            L_0x00e0:
                if (r3 <= 0) goto L_0x011c;
            L_0x00e2:
                r3 = r3 + -1;
                r4 = new java.lang.StringBuilder;
                r4.<init>();
                r4.append(r0);
                r0 = com.payu.custombrowser.a.b.this;
                r0 = r0.d;
                r0 = r0.get(r3);
                r0 = (java.lang.String) r0;
                r4.append(r0);
                r0 = "\r\n";
                r4.append(r0);
                r0 = r4.toString();
                if (r3 < 0) goto L_0x00e0;
            L_0x0106:
                r4 = com.payu.custombrowser.a.b.this;
                r4 = r4.d;
                r4 = r4.size();
                if (r4 <= r3) goto L_0x00e0;
            L_0x0112:
                r4 = com.payu.custombrowser.a.b.this;
                r4 = r4.d;
                r4.remove(r3);
                goto L_0x00e0;
            L_0x011c:
                r0 = r0.trim();
                r3 = r0.length();
                if (r3 <= 0) goto L_0x0135;
            L_0x0126:
                r3 = new com.payu.custombrowser.a.b$a;
                r4 = com.payu.custombrowser.a.b.this;
                r3.<init>(r0);
                r1 = new java.lang.String[r1];
                r1[r2] = r0;
            L_0x0131:
                r3.execute(r1);
                goto L_0x013e;
            L_0x0135:
                r0 = com.payu.custombrowser.a.b.this;
                r0 = r0.e;
                r0.cancel();
            L_0x013e:
                r0 = com.payu.custombrowser.a.b.this;
                r0 = r0.d;
                r0 = r0.size();
                if (r0 <= 0) goto L_0x014f;
            L_0x014a:
                r0 = com.payu.custombrowser.a.b.this;
                r0.b();
            L_0x014f:
                r0 = com.payu.custombrowser.a.b.this;
                r0.d();
                return;
            L_0x0155:
                r4 = com.payu.custombrowser.a.b.this;
                r4 = r4.d;
                r4 = r4.size();
            L_0x015f:
                if (r4 <= 0) goto L_0x019b;
            L_0x0161:
                r4 = r4 + -1;
                r5 = new java.lang.StringBuilder;
                r5.<init>();
                r5.append(r0);
                r0 = com.payu.custombrowser.a.b.this;
                r0 = r0.d;
                r0 = r0.get(r4);
                r0 = (java.lang.String) r0;
                r5.append(r0);
                r0 = "\r\n";
                r5.append(r0);
                r0 = r5.toString();
                if (r4 < 0) goto L_0x015f;
            L_0x0185:
                r5 = com.payu.custombrowser.a.b.this;
                r5 = r5.d;
                r5 = r5.size();
                if (r5 <= r4) goto L_0x015f;
            L_0x0191:
                r5 = com.payu.custombrowser.a.b.this;
                r5 = r5.d;
                r5.remove(r4);
                goto L_0x015f;
            L_0x019b:
                r0 = r0.trim();
                r4 = r0.length();
                if (r4 <= 0) goto L_0x01b4;
            L_0x01a5:
                r4 = new com.payu.custombrowser.a.b$a;
                r5 = com.payu.custombrowser.a.b.this;
                r4.<init>(r0);
                r1 = new java.lang.String[r1];
                r1[r2] = r0;
                r4.execute(r1);
                goto L_0x01bd;
            L_0x01b4:
                r0 = com.payu.custombrowser.a.b.this;
                r0 = r0.e;
                r0.cancel();
            L_0x01bd:
                throw r3;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.payu.custombrowser.a.b$AnonymousClass2.run():void");
            }
        }, this.a);
    }

    private synchronized void c() {
        this.c = true;
    }

    private synchronized void d() {
        this.c = false;
    }

    private JSONArray a(JSONArray jSONArray, int i) throws JSONException {
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

    private void b(String str) {
        try {
            FileOutputStream openFileOutput = this.b.openFileOutput(this.f, 0);
            openFileOutput.write(str.getBytes());
            openFileOutput.close();
        } catch (IOException e) {
            ThrowableExtension.printStackTrace(e);
        }
    }

    public Timer a() {
        this.h = true;
        return this.e;
    }

    private boolean a(String str, Context context) {
        return context.getSharedPreferences("com.payu", 0).getBoolean(str, false);
    }

    private void a(String str, boolean z, Context context) {
        Editor edit = context.getSharedPreferences("com.payu", 0).edit();
        edit.putBoolean(str, z);
        edit.apply();
    }
}
