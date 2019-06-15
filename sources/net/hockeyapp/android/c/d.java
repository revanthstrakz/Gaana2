package net.hockeyapp.android.c;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.StrictMode;
import android.os.StrictMode.VmPolicy;
import android.os.StrictMode.VmPolicy.Builder;
import com.google.android.exoplayer2.C;
import com.google.api.client.http.HttpStatusCodes;
import com.til.colombia.android.internal.e;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.UUID;
import net.hockeyapp.android.b.a;

public class d extends AsyncTask<Void, Integer, Long> {
    protected Context a;
    protected a b;
    protected String c;
    protected String d;
    protected String e;
    protected ProgressDialog f;
    private String g = null;

    public d(Context context, String str, a aVar) {
        this.a = context;
        this.c = str;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(UUID.randomUUID());
        stringBuilder.append(".apk");
        this.d = stringBuilder.toString();
        stringBuilder = new StringBuilder();
        stringBuilder.append(Environment.getExternalStorageDirectory().getAbsolutePath());
        stringBuilder.append("/Download");
        this.e = stringBuilder.toString();
        this.b = aVar;
    }

    public void a(Context context) {
        this.a = context;
    }

    public void a() {
        this.a = null;
        this.f = null;
    }

    /* Access modifiers changed, original: protected|varargs */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x00e1 A:{SYNTHETIC, Splitter:B:55:0x00e1} */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x00e9 A:{Catch:{ IOException -> 0x00e5 }} */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00ce A:{SYNTHETIC, Splitter:B:45:0x00ce} */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x00d6 A:{Catch:{ IOException -> 0x00d2 }} */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00ce A:{SYNTHETIC, Splitter:B:45:0x00ce} */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x00d6 A:{Catch:{ IOException -> 0x00d2 }} */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x00e1 A:{SYNTHETIC, Splitter:B:55:0x00e1} */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x00e9 A:{Catch:{ IOException -> 0x00e5 }} */
    /* renamed from: a */
    public java.lang.Long doInBackground(java.lang.Void... r14) {
        /*
        r13 = this;
        r14 = 0;
        r0 = 0;
        r2 = new java.net.URL;	 Catch:{ IOException -> 0x00c3, all -> 0x00c0 }
        r3 = r13.b();	 Catch:{ IOException -> 0x00c3, all -> 0x00c0 }
        r2.<init>(r3);	 Catch:{ IOException -> 0x00c3, all -> 0x00c0 }
        r3 = 6;
        r2 = r13.a(r2, r3);	 Catch:{ IOException -> 0x00c3, all -> 0x00c0 }
        r2.connect();	 Catch:{ IOException -> 0x00c3, all -> 0x00c0 }
        r3 = r2.getContentLength();	 Catch:{ IOException -> 0x00c3, all -> 0x00c0 }
        r4 = r2.getContentType();	 Catch:{ IOException -> 0x00c3, all -> 0x00c0 }
        if (r4 == 0) goto L_0x002f;
    L_0x001e:
        r5 = "text";
        r4 = r4.contains(r5);	 Catch:{ IOException -> 0x00c3, all -> 0x00c0 }
        if (r4 == 0) goto L_0x002f;
    L_0x0026:
        r2 = "The requested download does not appear to be a file.";
        r13.g = r2;	 Catch:{ IOException -> 0x00c3, all -> 0x00c0 }
        r2 = java.lang.Long.valueOf(r0);	 Catch:{ IOException -> 0x00c3, all -> 0x00c0 }
        return r2;
    L_0x002f:
        r4 = new java.io.File;	 Catch:{ IOException -> 0x00c3, all -> 0x00c0 }
        r5 = r13.e;	 Catch:{ IOException -> 0x00c3, all -> 0x00c0 }
        r4.<init>(r5);	 Catch:{ IOException -> 0x00c3, all -> 0x00c0 }
        r5 = r4.mkdirs();	 Catch:{ IOException -> 0x00c3, all -> 0x00c0 }
        if (r5 != 0) goto L_0x005d;
    L_0x003c:
        r5 = r4.exists();	 Catch:{ IOException -> 0x00c3, all -> 0x00c0 }
        if (r5 != 0) goto L_0x005d;
    L_0x0042:
        r2 = new java.io.IOException;	 Catch:{ IOException -> 0x00c3, all -> 0x00c0 }
        r3 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x00c3, all -> 0x00c0 }
        r3.<init>();	 Catch:{ IOException -> 0x00c3, all -> 0x00c0 }
        r5 = "Could not create the dir(s):";
        r3.append(r5);	 Catch:{ IOException -> 0x00c3, all -> 0x00c0 }
        r4 = r4.getAbsolutePath();	 Catch:{ IOException -> 0x00c3, all -> 0x00c0 }
        r3.append(r4);	 Catch:{ IOException -> 0x00c3, all -> 0x00c0 }
        r3 = r3.toString();	 Catch:{ IOException -> 0x00c3, all -> 0x00c0 }
        r2.<init>(r3);	 Catch:{ IOException -> 0x00c3, all -> 0x00c0 }
        throw r2;	 Catch:{ IOException -> 0x00c3, all -> 0x00c0 }
    L_0x005d:
        r5 = new java.io.File;	 Catch:{ IOException -> 0x00c3, all -> 0x00c0 }
        r6 = r13.d;	 Catch:{ IOException -> 0x00c3, all -> 0x00c0 }
        r5.<init>(r4, r6);	 Catch:{ IOException -> 0x00c3, all -> 0x00c0 }
        r4 = new java.io.BufferedInputStream;	 Catch:{ IOException -> 0x00c3, all -> 0x00c0 }
        r2 = r2.getInputStream();	 Catch:{ IOException -> 0x00c3, all -> 0x00c0 }
        r4.<init>(r2);	 Catch:{ IOException -> 0x00c3, all -> 0x00c0 }
        r2 = new java.io.FileOutputStream;	 Catch:{ IOException -> 0x00be }
        r2.<init>(r5);	 Catch:{ IOException -> 0x00be }
        r14 = 1024; // 0x400 float:1.435E-42 double:5.06E-321;
        r14 = new byte[r14];	 Catch:{ IOException -> 0x00b9, all -> 0x00b6 }
        r5 = r0;
    L_0x0077:
        r7 = r4.read(r14);	 Catch:{ IOException -> 0x00b9, all -> 0x00b6 }
        r8 = -1;
        if (r7 == r8) goto L_0x009d;
    L_0x007e:
        r8 = (long) r7;	 Catch:{ IOException -> 0x00b9, all -> 0x00b6 }
        r10 = r5 + r8;
        r5 = 1;
        r5 = new java.lang.Integer[r5];	 Catch:{ IOException -> 0x00b9, all -> 0x00b6 }
        r6 = (float) r10;	 Catch:{ IOException -> 0x00b9, all -> 0x00b6 }
        r8 = 1120403456; // 0x42c80000 float:100.0 double:5.53552857E-315;
        r6 = r6 * r8;
        r8 = (float) r3;	 Catch:{ IOException -> 0x00b9, all -> 0x00b6 }
        r6 = r6 / r8;
        r6 = java.lang.Math.round(r6);	 Catch:{ IOException -> 0x00b9, all -> 0x00b6 }
        r6 = java.lang.Integer.valueOf(r6);	 Catch:{ IOException -> 0x00b9, all -> 0x00b6 }
        r8 = 0;
        r5[r8] = r6;	 Catch:{ IOException -> 0x00b9, all -> 0x00b6 }
        r13.publishProgress(r5);	 Catch:{ IOException -> 0x00b9, all -> 0x00b6 }
        r2.write(r14, r8, r7);	 Catch:{ IOException -> 0x00b9, all -> 0x00b6 }
        r5 = r10;
        goto L_0x0077;
    L_0x009d:
        r2.flush();	 Catch:{ IOException -> 0x00b9, all -> 0x00b6 }
        r14 = java.lang.Long.valueOf(r5);	 Catch:{ IOException -> 0x00b9, all -> 0x00b6 }
        if (r2 == 0) goto L_0x00ac;
    L_0x00a6:
        r2.close();	 Catch:{ IOException -> 0x00aa }
        goto L_0x00ac;
    L_0x00aa:
        r0 = move-exception;
        goto L_0x00b2;
    L_0x00ac:
        if (r4 == 0) goto L_0x00b5;
    L_0x00ae:
        r4.close();	 Catch:{ IOException -> 0x00aa }
        goto L_0x00b5;
    L_0x00b2:
        com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r0);
    L_0x00b5:
        return r14;
    L_0x00b6:
        r0 = move-exception;
        r14 = r2;
        goto L_0x00df;
    L_0x00b9:
        r14 = move-exception;
        r12 = r2;
        r2 = r14;
        r14 = r12;
        goto L_0x00c5;
    L_0x00be:
        r2 = move-exception;
        goto L_0x00c5;
    L_0x00c0:
        r0 = move-exception;
        r4 = r14;
        goto L_0x00df;
    L_0x00c3:
        r2 = move-exception;
        r4 = r14;
    L_0x00c5:
        com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r2);	 Catch:{ all -> 0x00de }
        r0 = java.lang.Long.valueOf(r0);	 Catch:{ all -> 0x00de }
        if (r14 == 0) goto L_0x00d4;
    L_0x00ce:
        r14.close();	 Catch:{ IOException -> 0x00d2 }
        goto L_0x00d4;
    L_0x00d2:
        r14 = move-exception;
        goto L_0x00da;
    L_0x00d4:
        if (r4 == 0) goto L_0x00dd;
    L_0x00d6:
        r4.close();	 Catch:{ IOException -> 0x00d2 }
        goto L_0x00dd;
    L_0x00da:
        com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r14);
    L_0x00dd:
        return r0;
    L_0x00de:
        r0 = move-exception;
    L_0x00df:
        if (r14 == 0) goto L_0x00e7;
    L_0x00e1:
        r14.close();	 Catch:{ IOException -> 0x00e5 }
        goto L_0x00e7;
    L_0x00e5:
        r14 = move-exception;
        goto L_0x00ed;
    L_0x00e7:
        if (r4 == 0) goto L_0x00f0;
    L_0x00e9:
        r4.close();	 Catch:{ IOException -> 0x00e5 }
        goto L_0x00f0;
    L_0x00ed:
        com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r14);
    L_0x00f0:
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: net.hockeyapp.android.c.d.doInBackground(java.lang.Void[]):java.lang.Long");
    }

    /* Access modifiers changed, original: protected */
    public void a(HttpURLConnection httpURLConnection) {
        httpURLConnection.addRequestProperty(e.c, "HockeySDK/Android 4.1.1");
        httpURLConnection.setInstanceFollowRedirects(true);
        if (VERSION.SDK_INT <= 9) {
            httpURLConnection.setRequestProperty("connection", "close");
        }
    }

    /* Access modifiers changed, original: protected */
    public URLConnection a(URL url, int i) throws IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        a(httpURLConnection);
        int responseCode = httpURLConnection.getResponseCode();
        if ((responseCode != HttpStatusCodes.STATUS_CODE_MOVED_PERMANENTLY && responseCode != HttpStatusCodes.STATUS_CODE_FOUND && responseCode != HttpStatusCodes.STATUS_CODE_SEE_OTHER) || i == 0) {
            return httpURLConnection;
        }
        URL url2 = new URL(httpURLConnection.getHeaderField(e.e));
        if (!url.getProtocol().equals(url2.getProtocol())) {
            httpURLConnection.disconnect();
            return a(url2, i - 1);
        }
        return httpURLConnection;
    }

    /* Access modifiers changed, original: protected|varargs */
    /* renamed from: a */
    public void onProgressUpdate(Integer... numArr) {
        try {
            if (this.f == null) {
                this.f = new ProgressDialog(this.a);
                this.f.setProgressStyle(1);
                this.f.setMessage("Loading...");
                this.f.setCancelable(false);
                this.f.show();
            }
            this.f.setProgress(numArr[0].intValue());
        } catch (Exception unused) {
        }
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public void onPostExecute(Long l) {
        if (this.f != null) {
            try {
                this.f.dismiss();
            } catch (Exception unused) {
            }
        }
        if (l.longValue() > 0) {
            this.b.a(this);
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setDataAndType(Uri.fromFile(new File(this.e, this.d)), "application/vnd.android.package-archive");
            intent.setFlags(C.ENCODING_PCM_MU_LAW);
            VmPolicy vmPolicy = null;
            if (VERSION.SDK_INT >= 24) {
                vmPolicy = StrictMode.getVmPolicy();
                StrictMode.setVmPolicy(new Builder().penaltyLog().build());
            }
            this.a.startActivity(intent);
            if (vmPolicy != null) {
                StrictMode.setVmPolicy(vmPolicy);
                return;
            }
            return;
        }
        try {
            CharSequence string;
            AlertDialog.Builder builder = new AlertDialog.Builder(this.a);
            builder.setTitle(net.hockeyapp.android.i.d.hockeyapp_download_failed_dialog_title);
            if (this.g == null) {
                string = this.a.getString(net.hockeyapp.android.i.d.hockeyapp_download_failed_dialog_message);
            } else {
                string = this.g;
            }
            builder.setMessage(string);
            builder.setNegativeButton(net.hockeyapp.android.i.d.hockeyapp_download_failed_dialog_negative_button, new OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    d.this.b.a(d.this, Boolean.valueOf(false));
                }
            });
            builder.setPositiveButton(net.hockeyapp.android.i.d.hockeyapp_download_failed_dialog_positive_button, new OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    d.this.b.a(d.this, Boolean.valueOf(true));
                }
            });
            builder.create().show();
        } catch (Exception unused2) {
        }
    }

    /* Access modifiers changed, original: protected */
    public String b() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.c);
        stringBuilder.append("&type=apk");
        return stringBuilder.toString();
    }
}
