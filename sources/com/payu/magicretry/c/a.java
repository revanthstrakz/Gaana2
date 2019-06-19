package com.payu.magicretry.c;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.payu.magicretry.MagicRetryFragment;
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

public class a {
    private String a;
    private Context b;
    private String c;
    private boolean d;
    private ArrayList<String> e;
    private Timer f;

    public a(final Context context, String str) {
        this.c = MagicRetryFragment.a ? "http://10.50.23.170:6543/MobileAnalytics" : "https://a.payu.in/MobileAnalytics";
        this.d = false;
        this.b = context;
        this.a = str;
        this.e = new ArrayList();
        final UncaughtExceptionHandler defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(new UncaughtExceptionHandler() {
            public void uncaughtException(Thread thread, Throwable th) {
                while (a.this.d) {
                }
                a.this.b();
                try {
                    int i = 0;
                    FileOutputStream openFileOutput = context.openFileOutput(a.this.a, 0);
                    int size = a.this.e.size();
                    if (size > 0) {
                        JSONArray jSONArray = new JSONArray();
                        while (i < size) {
                            jSONArray.put(jSONArray.length(), new JSONObject((String) a.this.e.get(i)));
                            i++;
                        }
                        openFileOutput.write(jSONArray.toString().getBytes());
                        a.this.e = new ArrayList();
                    }
                    openFileOutput.close();
                } catch (IOException e) {
                    ThrowableExtension.printStackTrace(e);
                } catch (Exception e2) {
                    ThrowableExtension.printStackTrace(e2);
                }
                a.this.c();
                defaultUncaughtExceptionHandler.uncaughtException(thread, th);
            }
        });
    }

    public void a(final String str) {
        a();
        if (this.d) {
            try {
                this.e.add(str);
                return;
            } catch (Exception e) {
                ThrowableExtension.printStackTrace(e);
                return;
            }
        }
        new AsyncTask<Void, Void, Void>() {
            /* Access modifiers changed, original: protected|varargs */
            /* renamed from: a */
            public Void doInBackground(Void... voidArr) {
                a.this.b();
                try {
                    JSONArray jSONArray;
                    JSONObject jSONObject = new JSONObject(str);
                    String str = "";
                    if (!new File(a.this.b.getFilesDir(), a.this.a).exists()) {
                        a.this.b.openFileOutput(a.this.a, 0);
                    }
                    FileInputStream openFileInput = a.this.b.openFileInput(a.this.a);
                    while (true) {
                        int read = openFileInput.read();
                        if (read == -1) {
                            break;
                        }
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append(str);
                        stringBuilder.append(Character.toString((char) read));
                        str = stringBuilder.toString();
                    }
                    if (str.equalsIgnoreCase("")) {
                        jSONArray = new JSONArray();
                    } else {
                        jSONArray = new JSONArray(str);
                    }
                    openFileInput.close();
                    FileOutputStream openFileOutput = a.this.b.openFileOutput(a.this.a, 0);
                    jSONArray.put(jSONArray.length(), jSONObject);
                    openFileOutput.write(jSONArray.toString().getBytes());
                    openFileOutput.close();
                } catch (IOException e) {
                    ThrowableExtension.printStackTrace(e);
                    a.this.e.add(str);
                } catch (JSONException e2) {
                    ThrowableExtension.printStackTrace(e2);
                    a.this.e.add(str);
                } catch (Exception e3) {
                    ThrowableExtension.printStackTrace(e3);
                    a.this.e.add(str);
                }
                a.this.c();
                return null;
            }
        }.execute(new Void[]{null, null, null});
    }

    private void a() {
        if (this.f != null) {
            this.f.cancel();
        }
        this.f = new Timer();
        this.f.schedule(new TimerTask() {
            /* JADX WARNING: Removed duplicated region for block: B:73:0x025b A:{ExcHandler: MalformedURLException (r0_79 'e' java.net.MalformedURLException), Splitter:B:15:0x0078} */
            /* JADX WARNING: Removed duplicated region for block: B:71:0x0255 A:{ExcHandler: ProtocolException (r0_78 'e' java.net.ProtocolException), Splitter:B:15:0x0078} */
            /* JADX WARNING: Removed duplicated region for block: B:69:0x024f A:{ExcHandler: UnsupportedEncodingException (r0_77 'e' java.io.UnsupportedEncodingException), Splitter:B:15:0x0078} */
            /* JADX WARNING: Removed duplicated region for block: B:67:0x0249 A:{ExcHandler: IOException (r0_76 'e' java.io.IOException), Splitter:B:15:0x0078} */
            /* JADX WARNING: Removed duplicated region for block: B:65:0x0243 A:{ExcHandler: JSONException (r0_75 'e' org.json.JSONException), Splitter:B:15:0x0078} */
            /* JADX WARNING: Removed duplicated region for block: B:73:0x025b A:{ExcHandler: MalformedURLException (r0_79 'e' java.net.MalformedURLException), Splitter:B:15:0x0078} */
            /* JADX WARNING: Removed duplicated region for block: B:71:0x0255 A:{ExcHandler: ProtocolException (r0_78 'e' java.net.ProtocolException), Splitter:B:15:0x0078} */
            /* JADX WARNING: Removed duplicated region for block: B:69:0x024f A:{ExcHandler: UnsupportedEncodingException (r0_77 'e' java.io.UnsupportedEncodingException), Splitter:B:15:0x0078} */
            /* JADX WARNING: Removed duplicated region for block: B:67:0x0249 A:{ExcHandler: IOException (r0_76 'e' java.io.IOException), Splitter:B:15:0x0078} */
            /* JADX WARNING: Removed duplicated region for block: B:65:0x0243 A:{ExcHandler: JSONException (r0_75 'e' org.json.JSONException), Splitter:B:15:0x0078} */
            /* JADX WARNING: Removed duplicated region for block: B:107:0x0357 A:{ExcHandler: MalformedURLException (r0_53 'e' java.net.MalformedURLException), Splitter:B:75:0x0261} */
            /* JADX WARNING: Removed duplicated region for block: B:105:0x0352 A:{ExcHandler: ProtocolException (r0_52 'e' java.net.ProtocolException), Splitter:B:75:0x0261} */
            /* JADX WARNING: Removed duplicated region for block: B:103:0x034d A:{ExcHandler: UnsupportedEncodingException (r0_51 'e' java.io.UnsupportedEncodingException), Splitter:B:75:0x0261} */
            /* JADX WARNING: Removed duplicated region for block: B:101:0x0348 A:{ExcHandler: IOException (r0_50 'e' java.io.IOException), Splitter:B:75:0x0261} */
            /* JADX WARNING: Removed duplicated region for block: B:99:0x0343 A:{ExcHandler: JSONException (r0_49 'e' org.json.JSONException), Splitter:B:75:0x0261} */
            /* JADX WARNING: Removed duplicated region for block: B:65:0x0243 A:{ExcHandler: JSONException (r0_75 'e' org.json.JSONException), Splitter:B:15:0x0078} */
            /* JADX WARNING: Removed duplicated region for block: B:99:0x0343 A:{ExcHandler: JSONException (r0_49 'e' org.json.JSONException), Splitter:B:75:0x0261} */
            /* JADX WARNING: Removed duplicated region for block: B:101:0x0348 A:{ExcHandler: IOException (r0_50 'e' java.io.IOException), Splitter:B:75:0x0261} */
            /* JADX WARNING: Removed duplicated region for block: B:67:0x0249 A:{ExcHandler: IOException (r0_76 'e' java.io.IOException), Splitter:B:15:0x0078} */
            /* JADX WARNING: Removed duplicated region for block: B:103:0x034d A:{ExcHandler: UnsupportedEncodingException (r0_51 'e' java.io.UnsupportedEncodingException), Splitter:B:75:0x0261} */
            /* JADX WARNING: Removed duplicated region for block: B:69:0x024f A:{ExcHandler: UnsupportedEncodingException (r0_77 'e' java.io.UnsupportedEncodingException), Splitter:B:15:0x0078} */
            /* JADX WARNING: Removed duplicated region for block: B:105:0x0352 A:{ExcHandler: ProtocolException (r0_52 'e' java.net.ProtocolException), Splitter:B:75:0x0261} */
            /* JADX WARNING: Removed duplicated region for block: B:71:0x0255 A:{ExcHandler: ProtocolException (r0_78 'e' java.net.ProtocolException), Splitter:B:15:0x0078} */
            /* JADX WARNING: Removed duplicated region for block: B:107:0x0357 A:{ExcHandler: MalformedURLException (r0_53 'e' java.net.MalformedURLException), Splitter:B:75:0x0261} */
            /* JADX WARNING: Removed duplicated region for block: B:73:0x025b A:{ExcHandler: MalformedURLException (r0_79 'e' java.net.MalformedURLException), Splitter:B:15:0x0078} */
            /* JADX WARNING: Exception block dominator not found, dom blocks: [B:15:0x0078, B:56:0x020b] */
            /* JADX WARNING: Failed to process nested try/catch */
            /* JADX WARNING: Failed to process nested try/catch */
            /* JADX WARNING: Missing block: B:60:0x0237, code skipped:
            r0 = move-exception;
     */
            /* JADX WARNING: Missing block: B:62:?, code skipped:
            com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r0);
     */
            /* JADX WARNING: Missing block: B:63:0x023d, code skipped:
            r0 = move-exception;
     */
            /* JADX WARNING: Missing block: B:64:0x023e, code skipped:
            com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r0);
     */
            /* JADX WARNING: Missing block: B:65:0x0243, code skipped:
            r0 = move-exception;
     */
            /* JADX WARNING: Missing block: B:66:0x0244, code skipped:
            com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r0);
     */
            /* JADX WARNING: Missing block: B:67:0x0249, code skipped:
            r0 = move-exception;
     */
            /* JADX WARNING: Missing block: B:68:0x024a, code skipped:
            com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r0);
     */
            /* JADX WARNING: Missing block: B:69:0x024f, code skipped:
            r0 = move-exception;
     */
            /* JADX WARNING: Missing block: B:70:0x0250, code skipped:
            com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r0);
     */
            /* JADX WARNING: Missing block: B:71:0x0255, code skipped:
            r0 = move-exception;
     */
            /* JADX WARNING: Missing block: B:72:0x0256, code skipped:
            com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r0);
     */
            /* JADX WARNING: Missing block: B:73:0x025b, code skipped:
            r0 = move-exception;
     */
            /* JADX WARNING: Missing block: B:74:0x025c, code skipped:
            com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r0);
     */
            /* JADX WARNING: Missing block: B:97:0x033e, code skipped:
            r0 = move-exception;
     */
            /* JADX WARNING: Missing block: B:98:0x033f, code skipped:
            com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r0);
     */
            /* JADX WARNING: Missing block: B:99:0x0343, code skipped:
            r0 = move-exception;
     */
            /* JADX WARNING: Missing block: B:100:0x0344, code skipped:
            com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r0);
     */
            /* JADX WARNING: Missing block: B:101:0x0348, code skipped:
            r0 = move-exception;
     */
            /* JADX WARNING: Missing block: B:102:0x0349, code skipped:
            com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r0);
     */
            /* JADX WARNING: Missing block: B:103:0x034d, code skipped:
            r0 = move-exception;
     */
            /* JADX WARNING: Missing block: B:104:0x034e, code skipped:
            com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r0);
     */
            /* JADX WARNING: Missing block: B:105:0x0352, code skipped:
            r0 = move-exception;
     */
            /* JADX WARNING: Missing block: B:106:0x0353, code skipped:
            com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r0);
     */
            /* JADX WARNING: Missing block: B:107:0x0357, code skipped:
            r0 = move-exception;
     */
            /* JADX WARNING: Missing block: B:108:0x0358, code skipped:
            com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r0);
     */
            public void run() {
                /*
                r11 = this;
            L_0x0000:
                r0 = com.payu.magicretry.c.a.this;
                r0 = r0.d;
                if (r0 == 0) goto L_0x0009;
            L_0x0008:
                goto L_0x0000;
            L_0x0009:
                r0 = com.payu.magicretry.c.a.this;
                r0.b();
                r0 = com.payu.magicretry.c.a.this;
                r0 = r0.d();
                if (r0 == 0) goto L_0x035c;
            L_0x0016:
                r0 = "";
                r1 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
                r2 = 1024; // 0x400 float:1.435E-42 double:5.06E-321;
                r3 = -1;
                r4 = 1;
                r5 = 0;
                r6 = new java.io.File;	 Catch:{ IOException -> 0x015a }
                r7 = com.payu.magicretry.c.a.this;	 Catch:{ IOException -> 0x015a }
                r7 = r7.b;	 Catch:{ IOException -> 0x015a }
                r7 = r7.getFilesDir();	 Catch:{ IOException -> 0x015a }
                r8 = com.payu.magicretry.c.a.this;	 Catch:{ IOException -> 0x015a }
                r8 = r8.a;	 Catch:{ IOException -> 0x015a }
                r6.<init>(r7, r8);	 Catch:{ IOException -> 0x015a }
                r6 = r6.exists();	 Catch:{ IOException -> 0x015a }
                if (r6 != 0) goto L_0x0049;
            L_0x003a:
                r6 = com.payu.magicretry.c.a.this;	 Catch:{ IOException -> 0x015a }
                r6 = r6.b;	 Catch:{ IOException -> 0x015a }
                r7 = com.payu.magicretry.c.a.this;	 Catch:{ IOException -> 0x015a }
                r7 = r7.a;	 Catch:{ IOException -> 0x015a }
                r6.openFileOutput(r7, r5);	 Catch:{ IOException -> 0x015a }
            L_0x0049:
                r6 = com.payu.magicretry.c.a.this;	 Catch:{ IOException -> 0x015a }
                r6 = r6.b;	 Catch:{ IOException -> 0x015a }
                r7 = com.payu.magicretry.c.a.this;	 Catch:{ IOException -> 0x015a }
                r7 = r7.a;	 Catch:{ IOException -> 0x015a }
                r6 = r6.openFileInput(r7);	 Catch:{ IOException -> 0x015a }
            L_0x0059:
                r7 = r6.read();	 Catch:{ IOException -> 0x015a }
                if (r7 == r3) goto L_0x0075;
            L_0x005f:
                r8 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x015a }
                r8.<init>();	 Catch:{ IOException -> 0x015a }
                r8.append(r0);	 Catch:{ IOException -> 0x015a }
                r7 = (char) r7;	 Catch:{ IOException -> 0x015a }
                r7 = java.lang.Character.toString(r7);	 Catch:{ IOException -> 0x015a }
                r8.append(r7);	 Catch:{ IOException -> 0x015a }
                r7 = r8.toString();	 Catch:{ IOException -> 0x015a }
                r0 = r7;
                goto L_0x0059;
            L_0x0075:
                r6.close();	 Catch:{ IOException -> 0x015a }
                r6 = new org.json.JSONArray;	 Catch:{ MalformedURLException -> 0x025b, ProtocolException -> 0x0255, UnsupportedEncodingException -> 0x024f, IOException -> 0x0249, JSONException -> 0x0243, Exception -> 0x023d }
                r6.<init>(r0);	 Catch:{ MalformedURLException -> 0x025b, ProtocolException -> 0x0255, UnsupportedEncodingException -> 0x024f, IOException -> 0x0249, JSONException -> 0x0243, Exception -> 0x023d }
                r0 = new org.json.JSONArray;	 Catch:{ MalformedURLException -> 0x025b, ProtocolException -> 0x0255, UnsupportedEncodingException -> 0x024f, IOException -> 0x0249, JSONException -> 0x0243, Exception -> 0x023d }
                r0.<init>();	 Catch:{ MalformedURLException -> 0x025b, ProtocolException -> 0x0255, UnsupportedEncodingException -> 0x024f, IOException -> 0x0249, JSONException -> 0x0243, Exception -> 0x023d }
                r0 = com.payu.magicretry.c.a.this;	 Catch:{ MalformedURLException -> 0x025b, ProtocolException -> 0x0255, UnsupportedEncodingException -> 0x024f, IOException -> 0x0249, JSONException -> 0x0243, Exception -> 0x023d }
                r0 = r0.e;	 Catch:{ MalformedURLException -> 0x025b, ProtocolException -> 0x0255, UnsupportedEncodingException -> 0x024f, IOException -> 0x0249, JSONException -> 0x0243, Exception -> 0x023d }
                r0 = r0.size();	 Catch:{ MalformedURLException -> 0x025b, ProtocolException -> 0x0255, UnsupportedEncodingException -> 0x024f, IOException -> 0x0249, JSONException -> 0x0243, Exception -> 0x023d }
                if (r0 <= 0) goto L_0x00b2;
            L_0x008e:
                r0 = r5;
            L_0x008f:
                r7 = com.payu.magicretry.c.a.this;	 Catch:{ MalformedURLException -> 0x025b, ProtocolException -> 0x0255, UnsupportedEncodingException -> 0x024f, IOException -> 0x0249, JSONException -> 0x0243, Exception -> 0x023d }
                r7 = r7.e;	 Catch:{ MalformedURLException -> 0x025b, ProtocolException -> 0x0255, UnsupportedEncodingException -> 0x024f, IOException -> 0x0249, JSONException -> 0x0243, Exception -> 0x023d }
                r7 = r7.size();	 Catch:{ MalformedURLException -> 0x025b, ProtocolException -> 0x0255, UnsupportedEncodingException -> 0x024f, IOException -> 0x0249, JSONException -> 0x0243, Exception -> 0x023d }
                if (r0 >= r7) goto L_0x00b2;
            L_0x009b:
                r7 = new org.json.JSONObject;	 Catch:{ MalformedURLException -> 0x025b, ProtocolException -> 0x0255, UnsupportedEncodingException -> 0x024f, IOException -> 0x0249, JSONException -> 0x0243, Exception -> 0x023d }
                r8 = com.payu.magicretry.c.a.this;	 Catch:{ MalformedURLException -> 0x025b, ProtocolException -> 0x0255, UnsupportedEncodingException -> 0x024f, IOException -> 0x0249, JSONException -> 0x0243, Exception -> 0x023d }
                r8 = r8.e;	 Catch:{ MalformedURLException -> 0x025b, ProtocolException -> 0x0255, UnsupportedEncodingException -> 0x024f, IOException -> 0x0249, JSONException -> 0x0243, Exception -> 0x023d }
                r8 = r8.get(r0);	 Catch:{ MalformedURLException -> 0x025b, ProtocolException -> 0x0255, UnsupportedEncodingException -> 0x024f, IOException -> 0x0249, JSONException -> 0x0243, Exception -> 0x023d }
                r8 = (java.lang.String) r8;	 Catch:{ MalformedURLException -> 0x025b, ProtocolException -> 0x0255, UnsupportedEncodingException -> 0x024f, IOException -> 0x0249, JSONException -> 0x0243, Exception -> 0x023d }
                r7.<init>(r8);	 Catch:{ MalformedURLException -> 0x025b, ProtocolException -> 0x0255, UnsupportedEncodingException -> 0x024f, IOException -> 0x0249, JSONException -> 0x0243, Exception -> 0x023d }
                r6.put(r7);	 Catch:{ MalformedURLException -> 0x025b, ProtocolException -> 0x0255, UnsupportedEncodingException -> 0x024f, IOException -> 0x0249, JSONException -> 0x0243, Exception -> 0x023d }
                r0 = r0 + 1;
                goto L_0x008f;
            L_0x00b2:
                r0 = r6.length();	 Catch:{ MalformedURLException -> 0x025b, ProtocolException -> 0x0255, UnsupportedEncodingException -> 0x024f, IOException -> 0x0249, JSONException -> 0x0243, Exception -> 0x023d }
                if (r0 <= 0) goto L_0x035c;
            L_0x00b8:
                r0 = new java.lang.StringBuilder;	 Catch:{ MalformedURLException -> 0x025b, ProtocolException -> 0x0255, UnsupportedEncodingException -> 0x024f, IOException -> 0x0249, JSONException -> 0x0243, Exception -> 0x023d }
                r0.<init>();	 Catch:{ MalformedURLException -> 0x025b, ProtocolException -> 0x0255, UnsupportedEncodingException -> 0x024f, IOException -> 0x0249, JSONException -> 0x0243, Exception -> 0x023d }
                r7 = "command=EventAnalytics&data=";
                r0.append(r7);	 Catch:{ MalformedURLException -> 0x025b, ProtocolException -> 0x0255, UnsupportedEncodingException -> 0x024f, IOException -> 0x0249, JSONException -> 0x0243, Exception -> 0x023d }
                r6 = r6.toString();	 Catch:{ MalformedURLException -> 0x025b, ProtocolException -> 0x0255, UnsupportedEncodingException -> 0x024f, IOException -> 0x0249, JSONException -> 0x0243, Exception -> 0x023d }
                r0.append(r6);	 Catch:{ MalformedURLException -> 0x025b, ProtocolException -> 0x0255, UnsupportedEncodingException -> 0x024f, IOException -> 0x0249, JSONException -> 0x0243, Exception -> 0x023d }
                r0 = r0.toString();	 Catch:{ MalformedURLException -> 0x025b, ProtocolException -> 0x0255, UnsupportedEncodingException -> 0x024f, IOException -> 0x0249, JSONException -> 0x0243, Exception -> 0x023d }
                r6 = r0.getBytes();	 Catch:{ MalformedURLException -> 0x025b, ProtocolException -> 0x0255, UnsupportedEncodingException -> 0x024f, IOException -> 0x0249, JSONException -> 0x0243, Exception -> 0x023d }
                r7 = new java.net.URL;	 Catch:{ MalformedURLException -> 0x025b, ProtocolException -> 0x0255, UnsupportedEncodingException -> 0x024f, IOException -> 0x0249, JSONException -> 0x0243, Exception -> 0x023d }
                r8 = com.payu.magicretry.c.a.this;	 Catch:{ MalformedURLException -> 0x025b, ProtocolException -> 0x0255, UnsupportedEncodingException -> 0x024f, IOException -> 0x0249, JSONException -> 0x0243, Exception -> 0x023d }
                r8 = r8.c;	 Catch:{ MalformedURLException -> 0x025b, ProtocolException -> 0x0255, UnsupportedEncodingException -> 0x024f, IOException -> 0x0249, JSONException -> 0x0243, Exception -> 0x023d }
                r7.<init>(r8);	 Catch:{ MalformedURLException -> 0x025b, ProtocolException -> 0x0255, UnsupportedEncodingException -> 0x024f, IOException -> 0x0249, JSONException -> 0x0243, Exception -> 0x023d }
                r7 = r7.openConnection();	 Catch:{ MalformedURLException -> 0x025b, ProtocolException -> 0x0255, UnsupportedEncodingException -> 0x024f, IOException -> 0x0249, JSONException -> 0x0243, Exception -> 0x023d }
                r7 = (java.net.HttpURLConnection) r7;	 Catch:{ MalformedURLException -> 0x025b, ProtocolException -> 0x0255, UnsupportedEncodingException -> 0x024f, IOException -> 0x0249, JSONException -> 0x0243, Exception -> 0x023d }
                r8 = "POST";
                r7.setRequestMethod(r8);	 Catch:{ MalformedURLException -> 0x025b, ProtocolException -> 0x0255, UnsupportedEncodingException -> 0x024f, IOException -> 0x0249, JSONException -> 0x0243, Exception -> 0x023d }
                r8 = "Content-Type";
                r9 = "application/x-www-form-urlencoded";
                r7.setRequestProperty(r8, r9);	 Catch:{ MalformedURLException -> 0x025b, ProtocolException -> 0x0255, UnsupportedEncodingException -> 0x024f, IOException -> 0x0249, JSONException -> 0x0243, Exception -> 0x023d }
                r8 = "Content-Length";
                r0 = r0.length();	 Catch:{ MalformedURLException -> 0x025b, ProtocolException -> 0x0255, UnsupportedEncodingException -> 0x024f, IOException -> 0x0249, JSONException -> 0x0243, Exception -> 0x023d }
                r0 = java.lang.String.valueOf(r0);	 Catch:{ MalformedURLException -> 0x025b, ProtocolException -> 0x0255, UnsupportedEncodingException -> 0x024f, IOException -> 0x0249, JSONException -> 0x0243, Exception -> 0x023d }
                r7.setRequestProperty(r8, r0);	 Catch:{ MalformedURLException -> 0x025b, ProtocolException -> 0x0255, UnsupportedEncodingException -> 0x024f, IOException -> 0x0249, JSONException -> 0x0243, Exception -> 0x023d }
                r7.setDoOutput(r4);	 Catch:{ MalformedURLException -> 0x025b, ProtocolException -> 0x0255, UnsupportedEncodingException -> 0x024f, IOException -> 0x0249, JSONException -> 0x0243, Exception -> 0x023d }
                r0 = r7.getOutputStream();	 Catch:{ MalformedURLException -> 0x025b, ProtocolException -> 0x0255, UnsupportedEncodingException -> 0x024f, IOException -> 0x0249, JSONException -> 0x0243, Exception -> 0x023d }
                r0.write(r6);	 Catch:{ MalformedURLException -> 0x025b, ProtocolException -> 0x0255, UnsupportedEncodingException -> 0x024f, IOException -> 0x0249, JSONException -> 0x0243, Exception -> 0x023d }
                r0 = r7.getResponseCode();	 Catch:{ MalformedURLException -> 0x025b, ProtocolException -> 0x0255, UnsupportedEncodingException -> 0x024f, IOException -> 0x0249, JSONException -> 0x0243, Exception -> 0x023d }
                r4 = r7.getInputStream();	 Catch:{ MalformedURLException -> 0x025b, ProtocolException -> 0x0255, UnsupportedEncodingException -> 0x024f, IOException -> 0x0249, JSONException -> 0x0243, Exception -> 0x023d }
                r6 = new java.lang.StringBuffer;	 Catch:{ MalformedURLException -> 0x025b, ProtocolException -> 0x0255, UnsupportedEncodingException -> 0x024f, IOException -> 0x0249, JSONException -> 0x0243, Exception -> 0x023d }
                r6.<init>();	 Catch:{ MalformedURLException -> 0x025b, ProtocolException -> 0x0255, UnsupportedEncodingException -> 0x024f, IOException -> 0x0249, JSONException -> 0x0243, Exception -> 0x023d }
                r2 = new byte[r2];	 Catch:{ MalformedURLException -> 0x025b, ProtocolException -> 0x0255, UnsupportedEncodingException -> 0x024f, IOException -> 0x0249, JSONException -> 0x0243, Exception -> 0x023d }
            L_0x0114:
                r7 = r4.read(r2);	 Catch:{ MalformedURLException -> 0x025b, ProtocolException -> 0x0255, UnsupportedEncodingException -> 0x024f, IOException -> 0x0249, JSONException -> 0x0243, Exception -> 0x023d }
                if (r7 == r3) goto L_0x0123;
            L_0x011a:
                r8 = new java.lang.String;	 Catch:{ MalformedURLException -> 0x025b, ProtocolException -> 0x0255, UnsupportedEncodingException -> 0x024f, IOException -> 0x0249, JSONException -> 0x0243, Exception -> 0x023d }
                r8.<init>(r2, r5, r7);	 Catch:{ MalformedURLException -> 0x025b, ProtocolException -> 0x0255, UnsupportedEncodingException -> 0x024f, IOException -> 0x0249, JSONException -> 0x0243, Exception -> 0x023d }
                r6.append(r8);	 Catch:{ MalformedURLException -> 0x025b, ProtocolException -> 0x0255, UnsupportedEncodingException -> 0x024f, IOException -> 0x0249, JSONException -> 0x0243, Exception -> 0x023d }
                goto L_0x0114;
            L_0x0123:
                if (r0 != r1) goto L_0x035c;
            L_0x0125:
                r0 = new org.json.JSONObject;	 Catch:{ Exception -> 0x0151, MalformedURLException -> 0x025b, ProtocolException -> 0x0255, UnsupportedEncodingException -> 0x024f, IOException -> 0x0249, JSONException -> 0x0243 }
                r1 = r6.toString();	 Catch:{ Exception -> 0x0151, MalformedURLException -> 0x025b, ProtocolException -> 0x0255, UnsupportedEncodingException -> 0x024f, IOException -> 0x0249, JSONException -> 0x0243 }
                r0.<init>(r1);	 Catch:{ Exception -> 0x0151, MalformedURLException -> 0x025b, ProtocolException -> 0x0255, UnsupportedEncodingException -> 0x024f, IOException -> 0x0249, JSONException -> 0x0243 }
                r1 = "status";
                r0 = r0.has(r1);	 Catch:{ Exception -> 0x0151, MalformedURLException -> 0x025b, ProtocolException -> 0x0255, UnsupportedEncodingException -> 0x024f, IOException -> 0x0249, JSONException -> 0x0243 }
                if (r0 == 0) goto L_0x035c;
            L_0x0136:
                r0 = com.payu.magicretry.c.a.this;	 Catch:{ Exception -> 0x0151, MalformedURLException -> 0x025b, ProtocolException -> 0x0255, UnsupportedEncodingException -> 0x024f, IOException -> 0x0249, JSONException -> 0x0243 }
                r0 = r0.b;	 Catch:{ Exception -> 0x0151, MalformedURLException -> 0x025b, ProtocolException -> 0x0255, UnsupportedEncodingException -> 0x024f, IOException -> 0x0249, JSONException -> 0x0243 }
                r1 = com.payu.magicretry.c.a.this;	 Catch:{ Exception -> 0x0151, MalformedURLException -> 0x025b, ProtocolException -> 0x0255, UnsupportedEncodingException -> 0x024f, IOException -> 0x0249, JSONException -> 0x0243 }
                r1 = r1.a;	 Catch:{ Exception -> 0x0151, MalformedURLException -> 0x025b, ProtocolException -> 0x0255, UnsupportedEncodingException -> 0x024f, IOException -> 0x0249, JSONException -> 0x0243 }
                r0.deleteFile(r1);	 Catch:{ Exception -> 0x0151, MalformedURLException -> 0x025b, ProtocolException -> 0x0255, UnsupportedEncodingException -> 0x024f, IOException -> 0x0249, JSONException -> 0x0243 }
                r0 = com.payu.magicretry.c.a.this;	 Catch:{ Exception -> 0x0151, MalformedURLException -> 0x025b, ProtocolException -> 0x0255, UnsupportedEncodingException -> 0x024f, IOException -> 0x0249, JSONException -> 0x0243 }
                r1 = new java.util.ArrayList;	 Catch:{ Exception -> 0x0151, MalformedURLException -> 0x025b, ProtocolException -> 0x0255, UnsupportedEncodingException -> 0x024f, IOException -> 0x0249, JSONException -> 0x0243 }
                r1.<init>();	 Catch:{ Exception -> 0x0151, MalformedURLException -> 0x025b, ProtocolException -> 0x0255, UnsupportedEncodingException -> 0x024f, IOException -> 0x0249, JSONException -> 0x0243 }
                r0.e = r1;	 Catch:{ Exception -> 0x0151, MalformedURLException -> 0x025b, ProtocolException -> 0x0255, UnsupportedEncodingException -> 0x024f, IOException -> 0x0249, JSONException -> 0x0243 }
                goto L_0x035c;
            L_0x0151:
                r0 = move-exception;
                com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r0);	 Catch:{ MalformedURLException -> 0x025b, ProtocolException -> 0x0255, UnsupportedEncodingException -> 0x024f, IOException -> 0x0249, JSONException -> 0x0243, Exception -> 0x023d }
                goto L_0x035c;
            L_0x0157:
                r6 = move-exception;
                goto L_0x0261;
            L_0x015a:
                r6 = move-exception;
                com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r6);	 Catch:{ all -> 0x0157 }
                r6 = new org.json.JSONArray;	 Catch:{ MalformedURLException -> 0x025b, ProtocolException -> 0x0255, UnsupportedEncodingException -> 0x024f, IOException -> 0x0249, JSONException -> 0x0243, Exception -> 0x023d }
                r6.<init>(r0);	 Catch:{ MalformedURLException -> 0x025b, ProtocolException -> 0x0255, UnsupportedEncodingException -> 0x024f, IOException -> 0x0249, JSONException -> 0x0243, Exception -> 0x023d }
                r0 = new org.json.JSONArray;	 Catch:{ MalformedURLException -> 0x025b, ProtocolException -> 0x0255, UnsupportedEncodingException -> 0x024f, IOException -> 0x0249, JSONException -> 0x0243, Exception -> 0x023d }
                r0.<init>();	 Catch:{ MalformedURLException -> 0x025b, ProtocolException -> 0x0255, UnsupportedEncodingException -> 0x024f, IOException -> 0x0249, JSONException -> 0x0243, Exception -> 0x023d }
                r0 = com.payu.magicretry.c.a.this;	 Catch:{ MalformedURLException -> 0x025b, ProtocolException -> 0x0255, UnsupportedEncodingException -> 0x024f, IOException -> 0x0249, JSONException -> 0x0243, Exception -> 0x023d }
                r0 = r0.e;	 Catch:{ MalformedURLException -> 0x025b, ProtocolException -> 0x0255, UnsupportedEncodingException -> 0x024f, IOException -> 0x0249, JSONException -> 0x0243, Exception -> 0x023d }
                r0 = r0.size();	 Catch:{ MalformedURLException -> 0x025b, ProtocolException -> 0x0255, UnsupportedEncodingException -> 0x024f, IOException -> 0x0249, JSONException -> 0x0243, Exception -> 0x023d }
                if (r0 <= 0) goto L_0x0198;
            L_0x0174:
                r0 = r5;
            L_0x0175:
                r7 = com.payu.magicretry.c.a.this;	 Catch:{ MalformedURLException -> 0x025b, ProtocolException -> 0x0255, UnsupportedEncodingException -> 0x024f, IOException -> 0x0249, JSONException -> 0x0243, Exception -> 0x023d }
                r7 = r7.e;	 Catch:{ MalformedURLException -> 0x025b, ProtocolException -> 0x0255, UnsupportedEncodingException -> 0x024f, IOException -> 0x0249, JSONException -> 0x0243, Exception -> 0x023d }
                r7 = r7.size();	 Catch:{ MalformedURLException -> 0x025b, ProtocolException -> 0x0255, UnsupportedEncodingException -> 0x024f, IOException -> 0x0249, JSONException -> 0x0243, Exception -> 0x023d }
                if (r0 >= r7) goto L_0x0198;
            L_0x0181:
                r7 = new org.json.JSONObject;	 Catch:{ MalformedURLException -> 0x025b, ProtocolException -> 0x0255, UnsupportedEncodingException -> 0x024f, IOException -> 0x0249, JSONException -> 0x0243, Exception -> 0x023d }
                r8 = com.payu.magicretry.c.a.this;	 Catch:{ MalformedURLException -> 0x025b, ProtocolException -> 0x0255, UnsupportedEncodingException -> 0x024f, IOException -> 0x0249, JSONException -> 0x0243, Exception -> 0x023d }
                r8 = r8.e;	 Catch:{ MalformedURLException -> 0x025b, ProtocolException -> 0x0255, UnsupportedEncodingException -> 0x024f, IOException -> 0x0249, JSONException -> 0x0243, Exception -> 0x023d }
                r8 = r8.get(r0);	 Catch:{ MalformedURLException -> 0x025b, ProtocolException -> 0x0255, UnsupportedEncodingException -> 0x024f, IOException -> 0x0249, JSONException -> 0x0243, Exception -> 0x023d }
                r8 = (java.lang.String) r8;	 Catch:{ MalformedURLException -> 0x025b, ProtocolException -> 0x0255, UnsupportedEncodingException -> 0x024f, IOException -> 0x0249, JSONException -> 0x0243, Exception -> 0x023d }
                r7.<init>(r8);	 Catch:{ MalformedURLException -> 0x025b, ProtocolException -> 0x0255, UnsupportedEncodingException -> 0x024f, IOException -> 0x0249, JSONException -> 0x0243, Exception -> 0x023d }
                r6.put(r7);	 Catch:{ MalformedURLException -> 0x025b, ProtocolException -> 0x0255, UnsupportedEncodingException -> 0x024f, IOException -> 0x0249, JSONException -> 0x0243, Exception -> 0x023d }
                r0 = r0 + 1;
                goto L_0x0175;
            L_0x0198:
                r0 = r6.length();	 Catch:{ MalformedURLException -> 0x025b, ProtocolException -> 0x0255, UnsupportedEncodingException -> 0x024f, IOException -> 0x0249, JSONException -> 0x0243, Exception -> 0x023d }
                if (r0 <= 0) goto L_0x035c;
            L_0x019e:
                r0 = new java.lang.StringBuilder;	 Catch:{ MalformedURLException -> 0x025b, ProtocolException -> 0x0255, UnsupportedEncodingException -> 0x024f, IOException -> 0x0249, JSONException -> 0x0243, Exception -> 0x023d }
                r0.<init>();	 Catch:{ MalformedURLException -> 0x025b, ProtocolException -> 0x0255, UnsupportedEncodingException -> 0x024f, IOException -> 0x0249, JSONException -> 0x0243, Exception -> 0x023d }
                r7 = "command=EventAnalytics&data=";
                r0.append(r7);	 Catch:{ MalformedURLException -> 0x025b, ProtocolException -> 0x0255, UnsupportedEncodingException -> 0x024f, IOException -> 0x0249, JSONException -> 0x0243, Exception -> 0x023d }
                r6 = r6.toString();	 Catch:{ MalformedURLException -> 0x025b, ProtocolException -> 0x0255, UnsupportedEncodingException -> 0x024f, IOException -> 0x0249, JSONException -> 0x0243, Exception -> 0x023d }
                r0.append(r6);	 Catch:{ MalformedURLException -> 0x025b, ProtocolException -> 0x0255, UnsupportedEncodingException -> 0x024f, IOException -> 0x0249, JSONException -> 0x0243, Exception -> 0x023d }
                r0 = r0.toString();	 Catch:{ MalformedURLException -> 0x025b, ProtocolException -> 0x0255, UnsupportedEncodingException -> 0x024f, IOException -> 0x0249, JSONException -> 0x0243, Exception -> 0x023d }
                r6 = r0.getBytes();	 Catch:{ MalformedURLException -> 0x025b, ProtocolException -> 0x0255, UnsupportedEncodingException -> 0x024f, IOException -> 0x0249, JSONException -> 0x0243, Exception -> 0x023d }
                r7 = new java.net.URL;	 Catch:{ MalformedURLException -> 0x025b, ProtocolException -> 0x0255, UnsupportedEncodingException -> 0x024f, IOException -> 0x0249, JSONException -> 0x0243, Exception -> 0x023d }
                r8 = com.payu.magicretry.c.a.this;	 Catch:{ MalformedURLException -> 0x025b, ProtocolException -> 0x0255, UnsupportedEncodingException -> 0x024f, IOException -> 0x0249, JSONException -> 0x0243, Exception -> 0x023d }
                r8 = r8.c;	 Catch:{ MalformedURLException -> 0x025b, ProtocolException -> 0x0255, UnsupportedEncodingException -> 0x024f, IOException -> 0x0249, JSONException -> 0x0243, Exception -> 0x023d }
                r7.<init>(r8);	 Catch:{ MalformedURLException -> 0x025b, ProtocolException -> 0x0255, UnsupportedEncodingException -> 0x024f, IOException -> 0x0249, JSONException -> 0x0243, Exception -> 0x023d }
                r7 = r7.openConnection();	 Catch:{ MalformedURLException -> 0x025b, ProtocolException -> 0x0255, UnsupportedEncodingException -> 0x024f, IOException -> 0x0249, JSONException -> 0x0243, Exception -> 0x023d }
                r7 = (java.net.HttpURLConnection) r7;	 Catch:{ MalformedURLException -> 0x025b, ProtocolException -> 0x0255, UnsupportedEncodingException -> 0x024f, IOException -> 0x0249, JSONException -> 0x0243, Exception -> 0x023d }
                r8 = "POST";
                r7.setRequestMethod(r8);	 Catch:{ MalformedURLException -> 0x025b, ProtocolException -> 0x0255, UnsupportedEncodingException -> 0x024f, IOException -> 0x0249, JSONException -> 0x0243, Exception -> 0x023d }
                r8 = "Content-Type";
                r9 = "application/x-www-form-urlencoded";
                r7.setRequestProperty(r8, r9);	 Catch:{ MalformedURLException -> 0x025b, ProtocolException -> 0x0255, UnsupportedEncodingException -> 0x024f, IOException -> 0x0249, JSONException -> 0x0243, Exception -> 0x023d }
                r8 = "Content-Length";
                r0 = r0.length();	 Catch:{ MalformedURLException -> 0x025b, ProtocolException -> 0x0255, UnsupportedEncodingException -> 0x024f, IOException -> 0x0249, JSONException -> 0x0243, Exception -> 0x023d }
                r0 = java.lang.String.valueOf(r0);	 Catch:{ MalformedURLException -> 0x025b, ProtocolException -> 0x0255, UnsupportedEncodingException -> 0x024f, IOException -> 0x0249, JSONException -> 0x0243, Exception -> 0x023d }
                r7.setRequestProperty(r8, r0);	 Catch:{ MalformedURLException -> 0x025b, ProtocolException -> 0x0255, UnsupportedEncodingException -> 0x024f, IOException -> 0x0249, JSONException -> 0x0243, Exception -> 0x023d }
                r7.setDoOutput(r4);	 Catch:{ MalformedURLException -> 0x025b, ProtocolException -> 0x0255, UnsupportedEncodingException -> 0x024f, IOException -> 0x0249, JSONException -> 0x0243, Exception -> 0x023d }
                r0 = r7.getOutputStream();	 Catch:{ MalformedURLException -> 0x025b, ProtocolException -> 0x0255, UnsupportedEncodingException -> 0x024f, IOException -> 0x0249, JSONException -> 0x0243, Exception -> 0x023d }
                r0.write(r6);	 Catch:{ MalformedURLException -> 0x025b, ProtocolException -> 0x0255, UnsupportedEncodingException -> 0x024f, IOException -> 0x0249, JSONException -> 0x0243, Exception -> 0x023d }
                r0 = r7.getResponseCode();	 Catch:{ MalformedURLException -> 0x025b, ProtocolException -> 0x0255, UnsupportedEncodingException -> 0x024f, IOException -> 0x0249, JSONException -> 0x0243, Exception -> 0x023d }
                r4 = r7.getInputStream();	 Catch:{ MalformedURLException -> 0x025b, ProtocolException -> 0x0255, UnsupportedEncodingException -> 0x024f, IOException -> 0x0249, JSONException -> 0x0243, Exception -> 0x023d }
                r6 = new java.lang.StringBuffer;	 Catch:{ MalformedURLException -> 0x025b, ProtocolException -> 0x0255, UnsupportedEncodingException -> 0x024f, IOException -> 0x0249, JSONException -> 0x0243, Exception -> 0x023d }
                r6.<init>();	 Catch:{ MalformedURLException -> 0x025b, ProtocolException -> 0x0255, UnsupportedEncodingException -> 0x024f, IOException -> 0x0249, JSONException -> 0x0243, Exception -> 0x023d }
                r2 = new byte[r2];	 Catch:{ MalformedURLException -> 0x025b, ProtocolException -> 0x0255, UnsupportedEncodingException -> 0x024f, IOException -> 0x0249, JSONException -> 0x0243, Exception -> 0x023d }
            L_0x01fa:
                r7 = r4.read(r2);	 Catch:{ MalformedURLException -> 0x025b, ProtocolException -> 0x0255, UnsupportedEncodingException -> 0x024f, IOException -> 0x0249, JSONException -> 0x0243, Exception -> 0x023d }
                if (r7 == r3) goto L_0x0209;
            L_0x0200:
                r8 = new java.lang.String;	 Catch:{ MalformedURLException -> 0x025b, ProtocolException -> 0x0255, UnsupportedEncodingException -> 0x024f, IOException -> 0x0249, JSONException -> 0x0243, Exception -> 0x023d }
                r8.<init>(r2, r5, r7);	 Catch:{ MalformedURLException -> 0x025b, ProtocolException -> 0x0255, UnsupportedEncodingException -> 0x024f, IOException -> 0x0249, JSONException -> 0x0243, Exception -> 0x023d }
                r6.append(r8);	 Catch:{ MalformedURLException -> 0x025b, ProtocolException -> 0x0255, UnsupportedEncodingException -> 0x024f, IOException -> 0x0249, JSONException -> 0x0243, Exception -> 0x023d }
                goto L_0x01fa;
            L_0x0209:
                if (r0 != r1) goto L_0x035c;
            L_0x020b:
                r0 = new org.json.JSONObject;	 Catch:{ Exception -> 0x0237, MalformedURLException -> 0x025b, ProtocolException -> 0x0255, UnsupportedEncodingException -> 0x024f, IOException -> 0x0249, JSONException -> 0x0243 }
                r1 = r6.toString();	 Catch:{ Exception -> 0x0237, MalformedURLException -> 0x025b, ProtocolException -> 0x0255, UnsupportedEncodingException -> 0x024f, IOException -> 0x0249, JSONException -> 0x0243 }
                r0.<init>(r1);	 Catch:{ Exception -> 0x0237, MalformedURLException -> 0x025b, ProtocolException -> 0x0255, UnsupportedEncodingException -> 0x024f, IOException -> 0x0249, JSONException -> 0x0243 }
                r1 = "status";
                r0 = r0.has(r1);	 Catch:{ Exception -> 0x0237, MalformedURLException -> 0x025b, ProtocolException -> 0x0255, UnsupportedEncodingException -> 0x024f, IOException -> 0x0249, JSONException -> 0x0243 }
                if (r0 == 0) goto L_0x035c;
            L_0x021c:
                r0 = com.payu.magicretry.c.a.this;	 Catch:{ Exception -> 0x0237, MalformedURLException -> 0x025b, ProtocolException -> 0x0255, UnsupportedEncodingException -> 0x024f, IOException -> 0x0249, JSONException -> 0x0243 }
                r0 = r0.b;	 Catch:{ Exception -> 0x0237, MalformedURLException -> 0x025b, ProtocolException -> 0x0255, UnsupportedEncodingException -> 0x024f, IOException -> 0x0249, JSONException -> 0x0243 }
                r1 = com.payu.magicretry.c.a.this;	 Catch:{ Exception -> 0x0237, MalformedURLException -> 0x025b, ProtocolException -> 0x0255, UnsupportedEncodingException -> 0x024f, IOException -> 0x0249, JSONException -> 0x0243 }
                r1 = r1.a;	 Catch:{ Exception -> 0x0237, MalformedURLException -> 0x025b, ProtocolException -> 0x0255, UnsupportedEncodingException -> 0x024f, IOException -> 0x0249, JSONException -> 0x0243 }
                r0.deleteFile(r1);	 Catch:{ Exception -> 0x0237, MalformedURLException -> 0x025b, ProtocolException -> 0x0255, UnsupportedEncodingException -> 0x024f, IOException -> 0x0249, JSONException -> 0x0243 }
                r0 = com.payu.magicretry.c.a.this;	 Catch:{ Exception -> 0x0237, MalformedURLException -> 0x025b, ProtocolException -> 0x0255, UnsupportedEncodingException -> 0x024f, IOException -> 0x0249, JSONException -> 0x0243 }
                r1 = new java.util.ArrayList;	 Catch:{ Exception -> 0x0237, MalformedURLException -> 0x025b, ProtocolException -> 0x0255, UnsupportedEncodingException -> 0x024f, IOException -> 0x0249, JSONException -> 0x0243 }
                r1.<init>();	 Catch:{ Exception -> 0x0237, MalformedURLException -> 0x025b, ProtocolException -> 0x0255, UnsupportedEncodingException -> 0x024f, IOException -> 0x0249, JSONException -> 0x0243 }
                r0.e = r1;	 Catch:{ Exception -> 0x0237, MalformedURLException -> 0x025b, ProtocolException -> 0x0255, UnsupportedEncodingException -> 0x024f, IOException -> 0x0249, JSONException -> 0x0243 }
                goto L_0x035c;
            L_0x0237:
                r0 = move-exception;
                com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r0);	 Catch:{ MalformedURLException -> 0x025b, ProtocolException -> 0x0255, UnsupportedEncodingException -> 0x024f, IOException -> 0x0249, JSONException -> 0x0243, Exception -> 0x023d }
                goto L_0x035c;
            L_0x023d:
                r0 = move-exception;
                com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r0);
                goto L_0x035c;
            L_0x0243:
                r0 = move-exception;
                com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r0);
                goto L_0x035c;
            L_0x0249:
                r0 = move-exception;
                com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r0);
                goto L_0x035c;
            L_0x024f:
                r0 = move-exception;
                com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r0);
                goto L_0x035c;
            L_0x0255:
                r0 = move-exception;
                com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r0);
                goto L_0x035c;
            L_0x025b:
                r0 = move-exception;
                com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r0);
                goto L_0x035c;
            L_0x0261:
                r7 = new org.json.JSONArray;	 Catch:{ MalformedURLException -> 0x0357, ProtocolException -> 0x0352, UnsupportedEncodingException -> 0x034d, IOException -> 0x0348, JSONException -> 0x0343, Exception -> 0x033e }
                r7.<init>(r0);	 Catch:{ MalformedURLException -> 0x0357, ProtocolException -> 0x0352, UnsupportedEncodingException -> 0x034d, IOException -> 0x0348, JSONException -> 0x0343, Exception -> 0x033e }
                r0 = new org.json.JSONArray;	 Catch:{ MalformedURLException -> 0x0357, ProtocolException -> 0x0352, UnsupportedEncodingException -> 0x034d, IOException -> 0x0348, JSONException -> 0x0343, Exception -> 0x033e }
                r0.<init>();	 Catch:{ MalformedURLException -> 0x0357, ProtocolException -> 0x0352, UnsupportedEncodingException -> 0x034d, IOException -> 0x0348, JSONException -> 0x0343, Exception -> 0x033e }
                r0 = com.payu.magicretry.c.a.this;	 Catch:{ MalformedURLException -> 0x0357, ProtocolException -> 0x0352, UnsupportedEncodingException -> 0x034d, IOException -> 0x0348, JSONException -> 0x0343, Exception -> 0x033e }
                r0 = r0.e;	 Catch:{ MalformedURLException -> 0x0357, ProtocolException -> 0x0352, UnsupportedEncodingException -> 0x034d, IOException -> 0x0348, JSONException -> 0x0343, Exception -> 0x033e }
                r0 = r0.size();	 Catch:{ MalformedURLException -> 0x0357, ProtocolException -> 0x0352, UnsupportedEncodingException -> 0x034d, IOException -> 0x0348, JSONException -> 0x0343, Exception -> 0x033e }
                if (r0 <= 0) goto L_0x029b;
            L_0x0277:
                r0 = r5;
            L_0x0278:
                r8 = com.payu.magicretry.c.a.this;	 Catch:{ MalformedURLException -> 0x0357, ProtocolException -> 0x0352, UnsupportedEncodingException -> 0x034d, IOException -> 0x0348, JSONException -> 0x0343, Exception -> 0x033e }
                r8 = r8.e;	 Catch:{ MalformedURLException -> 0x0357, ProtocolException -> 0x0352, UnsupportedEncodingException -> 0x034d, IOException -> 0x0348, JSONException -> 0x0343, Exception -> 0x033e }
                r8 = r8.size();	 Catch:{ MalformedURLException -> 0x0357, ProtocolException -> 0x0352, UnsupportedEncodingException -> 0x034d, IOException -> 0x0348, JSONException -> 0x0343, Exception -> 0x033e }
                if (r0 >= r8) goto L_0x029b;
            L_0x0284:
                r8 = new org.json.JSONObject;	 Catch:{ MalformedURLException -> 0x0357, ProtocolException -> 0x0352, UnsupportedEncodingException -> 0x034d, IOException -> 0x0348, JSONException -> 0x0343, Exception -> 0x033e }
                r9 = com.payu.magicretry.c.a.this;	 Catch:{ MalformedURLException -> 0x0357, ProtocolException -> 0x0352, UnsupportedEncodingException -> 0x034d, IOException -> 0x0348, JSONException -> 0x0343, Exception -> 0x033e }
                r9 = r9.e;	 Catch:{ MalformedURLException -> 0x0357, ProtocolException -> 0x0352, UnsupportedEncodingException -> 0x034d, IOException -> 0x0348, JSONException -> 0x0343, Exception -> 0x033e }
                r9 = r9.get(r0);	 Catch:{ MalformedURLException -> 0x0357, ProtocolException -> 0x0352, UnsupportedEncodingException -> 0x034d, IOException -> 0x0348, JSONException -> 0x0343, Exception -> 0x033e }
                r9 = (java.lang.String) r9;	 Catch:{ MalformedURLException -> 0x0357, ProtocolException -> 0x0352, UnsupportedEncodingException -> 0x034d, IOException -> 0x0348, JSONException -> 0x0343, Exception -> 0x033e }
                r8.<init>(r9);	 Catch:{ MalformedURLException -> 0x0357, ProtocolException -> 0x0352, UnsupportedEncodingException -> 0x034d, IOException -> 0x0348, JSONException -> 0x0343, Exception -> 0x033e }
                r7.put(r8);	 Catch:{ MalformedURLException -> 0x0357, ProtocolException -> 0x0352, UnsupportedEncodingException -> 0x034d, IOException -> 0x0348, JSONException -> 0x0343, Exception -> 0x033e }
                r0 = r0 + 1;
                goto L_0x0278;
            L_0x029b:
                r0 = r7.length();	 Catch:{ MalformedURLException -> 0x0357, ProtocolException -> 0x0352, UnsupportedEncodingException -> 0x034d, IOException -> 0x0348, JSONException -> 0x0343, Exception -> 0x033e }
                if (r0 <= 0) goto L_0x035b;
            L_0x02a1:
                r0 = new java.lang.StringBuilder;	 Catch:{ MalformedURLException -> 0x0357, ProtocolException -> 0x0352, UnsupportedEncodingException -> 0x034d, IOException -> 0x0348, JSONException -> 0x0343, Exception -> 0x033e }
                r0.<init>();	 Catch:{ MalformedURLException -> 0x0357, ProtocolException -> 0x0352, UnsupportedEncodingException -> 0x034d, IOException -> 0x0348, JSONException -> 0x0343, Exception -> 0x033e }
                r8 = "command=EventAnalytics&data=";
                r0.append(r8);	 Catch:{ MalformedURLException -> 0x0357, ProtocolException -> 0x0352, UnsupportedEncodingException -> 0x034d, IOException -> 0x0348, JSONException -> 0x0343, Exception -> 0x033e }
                r7 = r7.toString();	 Catch:{ MalformedURLException -> 0x0357, ProtocolException -> 0x0352, UnsupportedEncodingException -> 0x034d, IOException -> 0x0348, JSONException -> 0x0343, Exception -> 0x033e }
                r0.append(r7);	 Catch:{ MalformedURLException -> 0x0357, ProtocolException -> 0x0352, UnsupportedEncodingException -> 0x034d, IOException -> 0x0348, JSONException -> 0x0343, Exception -> 0x033e }
                r0 = r0.toString();	 Catch:{ MalformedURLException -> 0x0357, ProtocolException -> 0x0352, UnsupportedEncodingException -> 0x034d, IOException -> 0x0348, JSONException -> 0x0343, Exception -> 0x033e }
                r7 = r0.getBytes();	 Catch:{ MalformedURLException -> 0x0357, ProtocolException -> 0x0352, UnsupportedEncodingException -> 0x034d, IOException -> 0x0348, JSONException -> 0x0343, Exception -> 0x033e }
                r8 = new java.net.URL;	 Catch:{ MalformedURLException -> 0x0357, ProtocolException -> 0x0352, UnsupportedEncodingException -> 0x034d, IOException -> 0x0348, JSONException -> 0x0343, Exception -> 0x033e }
                r9 = com.payu.magicretry.c.a.this;	 Catch:{ MalformedURLException -> 0x0357, ProtocolException -> 0x0352, UnsupportedEncodingException -> 0x034d, IOException -> 0x0348, JSONException -> 0x0343, Exception -> 0x033e }
                r9 = r9.c;	 Catch:{ MalformedURLException -> 0x0357, ProtocolException -> 0x0352, UnsupportedEncodingException -> 0x034d, IOException -> 0x0348, JSONException -> 0x0343, Exception -> 0x033e }
                r8.<init>(r9);	 Catch:{ MalformedURLException -> 0x0357, ProtocolException -> 0x0352, UnsupportedEncodingException -> 0x034d, IOException -> 0x0348, JSONException -> 0x0343, Exception -> 0x033e }
                r8 = r8.openConnection();	 Catch:{ MalformedURLException -> 0x0357, ProtocolException -> 0x0352, UnsupportedEncodingException -> 0x034d, IOException -> 0x0348, JSONException -> 0x0343, Exception -> 0x033e }
                r8 = (java.net.HttpURLConnection) r8;	 Catch:{ MalformedURLException -> 0x0357, ProtocolException -> 0x0352, UnsupportedEncodingException -> 0x034d, IOException -> 0x0348, JSONException -> 0x0343, Exception -> 0x033e }
                r9 = "POST";
                r8.setRequestMethod(r9);	 Catch:{ MalformedURLException -> 0x0357, ProtocolException -> 0x0352, UnsupportedEncodingException -> 0x034d, IOException -> 0x0348, JSONException -> 0x0343, Exception -> 0x033e }
                r9 = "Content-Type";
                r10 = "application/x-www-form-urlencoded";
                r8.setRequestProperty(r9, r10);	 Catch:{ MalformedURLException -> 0x0357, ProtocolException -> 0x0352, UnsupportedEncodingException -> 0x034d, IOException -> 0x0348, JSONException -> 0x0343, Exception -> 0x033e }
                r9 = "Content-Length";
                r0 = r0.length();	 Catch:{ MalformedURLException -> 0x0357, ProtocolException -> 0x0352, UnsupportedEncodingException -> 0x034d, IOException -> 0x0348, JSONException -> 0x0343, Exception -> 0x033e }
                r0 = java.lang.String.valueOf(r0);	 Catch:{ MalformedURLException -> 0x0357, ProtocolException -> 0x0352, UnsupportedEncodingException -> 0x034d, IOException -> 0x0348, JSONException -> 0x0343, Exception -> 0x033e }
                r8.setRequestProperty(r9, r0);	 Catch:{ MalformedURLException -> 0x0357, ProtocolException -> 0x0352, UnsupportedEncodingException -> 0x034d, IOException -> 0x0348, JSONException -> 0x0343, Exception -> 0x033e }
                r8.setDoOutput(r4);	 Catch:{ MalformedURLException -> 0x0357, ProtocolException -> 0x0352, UnsupportedEncodingException -> 0x034d, IOException -> 0x0348, JSONException -> 0x0343, Exception -> 0x033e }
                r0 = r8.getOutputStream();	 Catch:{ MalformedURLException -> 0x0357, ProtocolException -> 0x0352, UnsupportedEncodingException -> 0x034d, IOException -> 0x0348, JSONException -> 0x0343, Exception -> 0x033e }
                r0.write(r7);	 Catch:{ MalformedURLException -> 0x0357, ProtocolException -> 0x0352, UnsupportedEncodingException -> 0x034d, IOException -> 0x0348, JSONException -> 0x0343, Exception -> 0x033e }
                r0 = r8.getResponseCode();	 Catch:{ MalformedURLException -> 0x0357, ProtocolException -> 0x0352, UnsupportedEncodingException -> 0x034d, IOException -> 0x0348, JSONException -> 0x0343, Exception -> 0x033e }
                r4 = r8.getInputStream();	 Catch:{ MalformedURLException -> 0x0357, ProtocolException -> 0x0352, UnsupportedEncodingException -> 0x034d, IOException -> 0x0348, JSONException -> 0x0343, Exception -> 0x033e }
                r7 = new java.lang.StringBuffer;	 Catch:{ MalformedURLException -> 0x0357, ProtocolException -> 0x0352, UnsupportedEncodingException -> 0x034d, IOException -> 0x0348, JSONException -> 0x0343, Exception -> 0x033e }
                r7.<init>();	 Catch:{ MalformedURLException -> 0x0357, ProtocolException -> 0x0352, UnsupportedEncodingException -> 0x034d, IOException -> 0x0348, JSONException -> 0x0343, Exception -> 0x033e }
                r2 = new byte[r2];	 Catch:{ MalformedURLException -> 0x0357, ProtocolException -> 0x0352, UnsupportedEncodingException -> 0x034d, IOException -> 0x0348, JSONException -> 0x0343, Exception -> 0x033e }
            L_0x02fd:
                r8 = r4.read(r2);	 Catch:{ MalformedURLException -> 0x0357, ProtocolException -> 0x0352, UnsupportedEncodingException -> 0x034d, IOException -> 0x0348, JSONException -> 0x0343, Exception -> 0x033e }
                if (r8 == r3) goto L_0x030c;
            L_0x0303:
                r9 = new java.lang.String;	 Catch:{ MalformedURLException -> 0x0357, ProtocolException -> 0x0352, UnsupportedEncodingException -> 0x034d, IOException -> 0x0348, JSONException -> 0x0343, Exception -> 0x033e }
                r9.<init>(r2, r5, r8);	 Catch:{ MalformedURLException -> 0x0357, ProtocolException -> 0x0352, UnsupportedEncodingException -> 0x034d, IOException -> 0x0348, JSONException -> 0x0343, Exception -> 0x033e }
                r7.append(r9);	 Catch:{ MalformedURLException -> 0x0357, ProtocolException -> 0x0352, UnsupportedEncodingException -> 0x034d, IOException -> 0x0348, JSONException -> 0x0343, Exception -> 0x033e }
                goto L_0x02fd;
            L_0x030c:
                if (r0 != r1) goto L_0x035b;
            L_0x030e:
                r0 = new org.json.JSONObject;	 Catch:{ Exception -> 0x0339, MalformedURLException -> 0x0357, ProtocolException -> 0x0352, UnsupportedEncodingException -> 0x034d, IOException -> 0x0348, JSONException -> 0x0343 }
                r1 = r7.toString();	 Catch:{ Exception -> 0x0339, MalformedURLException -> 0x0357, ProtocolException -> 0x0352, UnsupportedEncodingException -> 0x034d, IOException -> 0x0348, JSONException -> 0x0343 }
                r0.<init>(r1);	 Catch:{ Exception -> 0x0339, MalformedURLException -> 0x0357, ProtocolException -> 0x0352, UnsupportedEncodingException -> 0x034d, IOException -> 0x0348, JSONException -> 0x0343 }
                r1 = "status";
                r0 = r0.has(r1);	 Catch:{ Exception -> 0x0339, MalformedURLException -> 0x0357, ProtocolException -> 0x0352, UnsupportedEncodingException -> 0x034d, IOException -> 0x0348, JSONException -> 0x0343 }
                if (r0 == 0) goto L_0x035b;
            L_0x031f:
                r0 = com.payu.magicretry.c.a.this;	 Catch:{ Exception -> 0x0339, MalformedURLException -> 0x0357, ProtocolException -> 0x0352, UnsupportedEncodingException -> 0x034d, IOException -> 0x0348, JSONException -> 0x0343 }
                r0 = r0.b;	 Catch:{ Exception -> 0x0339, MalformedURLException -> 0x0357, ProtocolException -> 0x0352, UnsupportedEncodingException -> 0x034d, IOException -> 0x0348, JSONException -> 0x0343 }
                r1 = com.payu.magicretry.c.a.this;	 Catch:{ Exception -> 0x0339, MalformedURLException -> 0x0357, ProtocolException -> 0x0352, UnsupportedEncodingException -> 0x034d, IOException -> 0x0348, JSONException -> 0x0343 }
                r1 = r1.a;	 Catch:{ Exception -> 0x0339, MalformedURLException -> 0x0357, ProtocolException -> 0x0352, UnsupportedEncodingException -> 0x034d, IOException -> 0x0348, JSONException -> 0x0343 }
                r0.deleteFile(r1);	 Catch:{ Exception -> 0x0339, MalformedURLException -> 0x0357, ProtocolException -> 0x0352, UnsupportedEncodingException -> 0x034d, IOException -> 0x0348, JSONException -> 0x0343 }
                r0 = com.payu.magicretry.c.a.this;	 Catch:{ Exception -> 0x0339, MalformedURLException -> 0x0357, ProtocolException -> 0x0352, UnsupportedEncodingException -> 0x034d, IOException -> 0x0348, JSONException -> 0x0343 }
                r1 = new java.util.ArrayList;	 Catch:{ Exception -> 0x0339, MalformedURLException -> 0x0357, ProtocolException -> 0x0352, UnsupportedEncodingException -> 0x034d, IOException -> 0x0348, JSONException -> 0x0343 }
                r1.<init>();	 Catch:{ Exception -> 0x0339, MalformedURLException -> 0x0357, ProtocolException -> 0x0352, UnsupportedEncodingException -> 0x034d, IOException -> 0x0348, JSONException -> 0x0343 }
                r0.e = r1;	 Catch:{ Exception -> 0x0339, MalformedURLException -> 0x0357, ProtocolException -> 0x0352, UnsupportedEncodingException -> 0x034d, IOException -> 0x0348, JSONException -> 0x0343 }
                goto L_0x035b;
            L_0x0339:
                r0 = move-exception;
                com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r0);	 Catch:{ MalformedURLException -> 0x0357, ProtocolException -> 0x0352, UnsupportedEncodingException -> 0x034d, IOException -> 0x0348, JSONException -> 0x0343, Exception -> 0x033e }
                goto L_0x035b;
            L_0x033e:
                r0 = move-exception;
                com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r0);
                goto L_0x035b;
            L_0x0343:
                r0 = move-exception;
                com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r0);
                goto L_0x035b;
            L_0x0348:
                r0 = move-exception;
                com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r0);
                goto L_0x035b;
            L_0x034d:
                r0 = move-exception;
                com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r0);
                goto L_0x035b;
            L_0x0352:
                r0 = move-exception;
                com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r0);
                goto L_0x035b;
            L_0x0357:
                r0 = move-exception;
                com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r0);
            L_0x035b:
                throw r6;
            L_0x035c:
                r0 = com.payu.magicretry.c.a.this;
                r0 = r0.e;
                r0 = r0.size();
                if (r0 <= 0) goto L_0x036d;
            L_0x0368:
                r0 = com.payu.magicretry.c.a.this;
                r0.a();
            L_0x036d:
                r0 = com.payu.magicretry.c.a.this;
                r0.c();
                return;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.payu.magicretry.c.a$AnonymousClass3.run():void");
            }
        }, DefaultRenderersFactory.DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS);
    }

    private synchronized void b() {
        this.d = true;
    }

    private synchronized void c() {
        this.d = false;
    }

    private boolean d() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.b.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }
}
