package com.helpshift.a.a.a;

import android.content.Context;
import android.os.Build.VERSION;
import com.helpshift.a.a.a.a.a;
import com.helpshift.a.a.a.a.b;
import com.helpshift.util.l;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import javax.net.ssl.HttpsURLConnection;

public class c implements Runnable {
    private Context a;
    private a b;
    private URL c;
    private String d;
    private a e;
    private b f;
    private com.helpshift.a.a.a.a.c g;

    public c(Context context, a aVar, String str, a aVar2, b bVar, com.helpshift.a.a.a.a.c cVar) {
        try {
            this.a = context;
            this.b = aVar;
            this.d = str;
            this.e = aVar2;
            this.c = new URL(str);
            this.f = bVar;
            this.g = cVar;
        } catch (MalformedURLException e) {
            l.a("Helpshift_DownloadRun", "Malformed URL ", e);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:141:0x0266 A:{SYNTHETIC, Splitter:B:141:0x0266} */
    /* JADX WARNING: Removed duplicated region for block: B:207:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:164:0x0298 A:{SYNTHETIC, Splitter:B:164:0x0298} */
    /* JADX WARNING: Removed duplicated region for block: B:187:0x02cc A:{Catch:{ InterruptedException -> 0x029c, IOException -> 0x02d0 }} */
    /* JADX WARNING: Removed duplicated region for block: B:164:0x0298 A:{SYNTHETIC, Splitter:B:164:0x0298} */
    /* JADX WARNING: Removed duplicated region for block: B:187:0x02cc A:{Catch:{ InterruptedException -> 0x029c, IOException -> 0x02d0 }} */
    /* JADX WARNING: Removed duplicated region for block: B:141:0x0266 A:{SYNTHETIC, Splitter:B:141:0x0266} */
    /* JADX WARNING: Removed duplicated region for block: B:207:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:164:0x0298 A:{SYNTHETIC, Splitter:B:164:0x0298} */
    /* JADX WARNING: Removed duplicated region for block: B:187:0x02cc A:{Catch:{ InterruptedException -> 0x029c, IOException -> 0x02d0 }} */
    /* JADX WARNING: Missing exception handler attribute for start block: B:47:0x0129 */
    /* JADX WARNING: Removed duplicated region for block: B:166:0x029c A:{Splitter:B:95:0x01e1, ExcHandler: InterruptedException (r0_30 'e' java.lang.InterruptedException)} */
    /* JADX WARNING: Removed duplicated region for block: B:189:0x02d0 A:{Splitter:B:3:0x0025, ExcHandler: IOException (r0_32 'e' java.io.IOException)} */
    /* JADX WARNING: Removed duplicated region for block: B:189:0x02d0 A:{Splitter:B:3:0x0025, ExcHandler: IOException (r0_32 'e' java.io.IOException)} */
    /* JADX WARNING: Removed duplicated region for block: B:141:0x0266 A:{SYNTHETIC, Splitter:B:141:0x0266} */
    /* JADX WARNING: Removed duplicated region for block: B:207:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:164:0x0298 A:{SYNTHETIC, Splitter:B:164:0x0298} */
    /* JADX WARNING: Removed duplicated region for block: B:187:0x02cc A:{Catch:{ InterruptedException -> 0x029c, IOException -> 0x02d0 }} */
    /* JADX WARNING: Removed duplicated region for block: B:141:0x0266 A:{SYNTHETIC, Splitter:B:141:0x0266} */
    /* JADX WARNING: Removed duplicated region for block: B:207:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:164:0x0298 A:{SYNTHETIC, Splitter:B:164:0x0298} */
    /* JADX WARNING: Removed duplicated region for block: B:187:0x02cc A:{Catch:{ InterruptedException -> 0x029c, IOException -> 0x02d0 }} */
    /* JADX WARNING: Removed duplicated region for block: B:141:0x0266 A:{SYNTHETIC, Splitter:B:141:0x0266} */
    /* JADX WARNING: Removed duplicated region for block: B:207:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:164:0x0298 A:{SYNTHETIC, Splitter:B:164:0x0298} */
    /* JADX WARNING: Removed duplicated region for block: B:187:0x02cc A:{Catch:{ InterruptedException -> 0x029c, IOException -> 0x02d0 }} */
    /* JADX WARNING: Removed duplicated region for block: B:141:0x0266 A:{SYNTHETIC, Splitter:B:141:0x0266} */
    /* JADX WARNING: Removed duplicated region for block: B:207:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x0135 A:{Splitter:B:34:0x00f4, ExcHandler: all (th java.lang.Throwable), PHI: r12 } */
    /* JADX WARNING: Removed duplicated region for block: B:141:0x0266 A:{SYNTHETIC, Splitter:B:141:0x0266} */
    /* JADX WARNING: Removed duplicated region for block: B:207:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:164:0x0298 A:{SYNTHETIC, Splitter:B:164:0x0298} */
    /* JADX WARNING: Removed duplicated region for block: B:187:0x02cc A:{Catch:{ InterruptedException -> 0x029c, IOException -> 0x02d0 }} */
    /* JADX WARNING: Removed duplicated region for block: B:164:0x0298 A:{SYNTHETIC, Splitter:B:164:0x0298} */
    /* JADX WARNING: Removed duplicated region for block: B:187:0x02cc A:{Catch:{ InterruptedException -> 0x029c, IOException -> 0x02d0 }} */
    /* JADX WARNING: Removed duplicated region for block: B:166:0x029c A:{Splitter:B:95:0x01e1, ExcHandler: InterruptedException (r0_30 'e' java.lang.InterruptedException)} */
    /* JADX WARNING: Missing exception handler attribute for start block: B:45:0x0118 */
    /* JADX WARNING: Removed duplicated region for block: B:189:0x02d0 A:{Splitter:B:3:0x0025, ExcHandler: IOException (r0_32 'e' java.io.IOException)} */
    /* JADX WARNING: Removed duplicated region for block: B:189:0x02d0 A:{Splitter:B:3:0x0025, ExcHandler: IOException (r0_32 'e' java.io.IOException)} */
    /* JADX WARNING: Removed duplicated region for block: B:189:0x02d0 A:{Splitter:B:3:0x0025, ExcHandler: IOException (r0_32 'e' java.io.IOException)} */
    /* JADX WARNING: Removed duplicated region for block: B:189:0x02d0 A:{Splitter:B:3:0x0025, ExcHandler: IOException (r0_32 'e' java.io.IOException)} */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x0135 A:{Splitter:B:34:0x00f4, ExcHandler: all (th java.lang.Throwable), PHI: r12 } */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [B:95:0x01e1, B:164:0x0298] */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [B:95:0x01e1, B:141:0x0266] */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Can't wrap try/catch for region: R(8:(3:34|35|(2:37|38))|43|44|45|46|47|48|49) */
    /* JADX WARNING: Missing block: B:50:0x0135, code skipped:
            r0 = th;
     */
    /* JADX WARNING: Missing block: B:90:0x01ad, code skipped:
            r16 = r4;
            r17 = r12;
     */
    /* JADX WARNING: Missing block: B:92:?, code skipped:
            r5.b(r1.d);
            r2 = r7.getAbsolutePath();
            r4 = new java.lang.StringBuilder();
            r4.append("Download finished : ");
            r4.append(r1.d);
            com.helpshift.util.l.a("Helpshift_DownloadRun", r4.toString());
            r1.f.a(true, r1.d, r2);
            java.lang.Thread.interrupted();
     */
    /* JADX WARNING: Missing block: B:93:0x01dd, code skipped:
            r12 = r17;
     */
    /* JADX WARNING: Missing block: B:110:0x0204, code skipped:
            r0 = move-exception;
     */
    /* JADX WARNING: Missing block: B:111:0x0205, code skipped:
            r2 = r0;
            r5 = false;
     */
    /* JADX WARNING: Missing block: B:112:0x0209, code skipped:
            r0 = move-exception;
     */
    /* JADX WARNING: Missing block: B:113:0x020a, code skipped:
            r2 = r0;
            r5 = false;
     */
    /* JADX WARNING: Missing block: B:117:0x021a, code skipped:
            r0 = move-exception;
     */
    /* JADX WARNING: Missing block: B:118:0x021b, code skipped:
            r4 = r16;
            r2 = r0;
            r6 = r14;
     */
    /* JADX WARNING: Missing block: B:120:0x0223, code skipped:
            r0 = e;
     */
    /* JADX WARNING: Missing block: B:121:0x0224, code skipped:
            r4 = r16;
     */
    /* JADX WARNING: Missing block: B:143:0x026a, code skipped:
            r0 = move-exception;
     */
    /* JADX WARNING: Missing block: B:146:0x026f, code skipped:
            r5 = false;
     */
    /* JADX WARNING: Missing block: B:148:?, code skipped:
            r1.f.a(false, r1.d, r0);
     */
    /* JADX WARNING: Missing block: B:150:?, code skipped:
            r2 = "Helpshift_DownloadRun";
            r3 = "Exception in closing download response";
            r6 = new com.helpshift.j.c.a[1];
     */
    /* JADX WARNING: Missing block: B:151:0x0282, code skipped:
            r7 = false;
     */
    /* JADX WARNING: Missing block: B:153:?, code skipped:
            r6[0] = com.helpshift.j.c.d.a("route", r1.d);
     */
    /* JADX WARNING: Missing block: B:155:?, code skipped:
            com.helpshift.util.l.c(r2, r3, r0, r6);
     */
    /* JADX WARNING: Missing block: B:156:0x0289, code skipped:
            r0 = e;
     */
    /* JADX WARNING: Missing block: B:166:0x029c, code skipped:
            r0 = move-exception;
     */
    /* JADX WARNING: Missing block: B:167:0x029d, code skipped:
            r2 = r0;
            r5 = false;
     */
    /* JADX WARNING: Missing block: B:168:0x02a0, code skipped:
            r0 = move-exception;
     */
    /* JADX WARNING: Missing block: B:171:0x02a5, code skipped:
            r7 = false;
     */
    /* JADX WARNING: Missing block: B:173:?, code skipped:
            r1.f.a(false, r1.d, r0);
     */
    /* JADX WARNING: Missing block: B:175:?, code skipped:
            r3 = "Helpshift_DownloadRun";
            r5 = "Exception in closing download response";
            r8 = new com.helpshift.j.c.a[1];
     */
    /* JADX WARNING: Missing block: B:178:?, code skipped:
            r8[0] = com.helpshift.j.c.d.a("route", r1.d);
     */
    /* JADX WARNING: Missing block: B:180:?, code skipped:
            com.helpshift.util.l.c(r3, r5, r0, r8);
     */
    /* JADX WARNING: Missing block: B:181:0x02bf, code skipped:
            r0 = move-exception;
     */
    /* JADX WARNING: Missing block: B:182:0x02c0, code skipped:
            r2 = r0;
            r5 = false;
     */
    /* JADX WARNING: Missing block: B:183:0x02c3, code skipped:
            r0 = move-exception;
     */
    /* JADX WARNING: Missing block: B:184:0x02c4, code skipped:
            r2 = r0;
            r5 = r7;
     */
    /* JADX WARNING: Missing block: B:189:0x02d0, code skipped:
            r0 = move-exception;
     */
    /* JADX WARNING: Missing block: B:190:0x02d1, code skipped:
            r2 = r0;
            r1.f.a(false, r1.d, r2);
            com.helpshift.util.l.c("Helpshift_DownloadRun", "Exception IO", r2, com.helpshift.j.c.d.a("route", r1.d));
     */
    /* JADX WARNING: Missing block: B:191:0x02ef, code skipped:
            r0 = e;
     */
    /* JADX WARNING: Missing block: B:192:0x02f0, code skipped:
            r5 = false;
     */
    /* JADX WARNING: Missing block: B:193:0x02f1, code skipped:
            r2 = r0;
     */
    /* JADX WARNING: Missing block: B:208:?, code skipped:
            return;
     */
    public void run() {
        /*
        r19 = this;
        r1 = r19;
        r2 = "Helpshift_DownloadRun";
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = "Starting download : ";
        r3.append(r4);
        r4 = r1.d;
        r3.append(r4);
        r3 = r3.toString();
        com.helpshift.util.l.a(r2, r3);
        r2 = r1.c;
        if (r2 == 0) goto L_0x030d;
    L_0x001e:
        r2 = 10;
        android.os.Process.setThreadPriority(r2);
        r2 = 1;
        r3 = 0;
        r4 = java.lang.Thread.interrupted();	 Catch:{ InterruptedException -> 0x02ef, IOException -> 0x02d0 }
        if (r4 == 0) goto L_0x0036;
    L_0x002b:
        r4 = new java.lang.InterruptedException;	 Catch:{ InterruptedException -> 0x0031, IOException -> 0x02d0 }
        r4.<init>();	 Catch:{ InterruptedException -> 0x0031, IOException -> 0x02d0 }
        throw r4;	 Catch:{ InterruptedException -> 0x0031, IOException -> 0x02d0 }
    L_0x0031:
        r0 = move-exception;
        r2 = r0;
        r5 = r3;
        goto L_0x02f2;
    L_0x0036:
        r4 = "https";
        r5 = r1.c;	 Catch:{ InterruptedException -> 0x0031, IOException -> 0x02d0 }
        r5 = r5.getProtocol();	 Catch:{ InterruptedException -> 0x0031, IOException -> 0x02d0 }
        r4 = r4.equals(r5);	 Catch:{ InterruptedException -> 0x0031, IOException -> 0x02d0 }
        if (r4 == 0) goto L_0x0053;
    L_0x0044:
        r4 = r1.c;	 Catch:{ InterruptedException -> 0x0031, IOException -> 0x02d0 }
        r4 = r4.openConnection();	 Catch:{ InterruptedException -> 0x0031, IOException -> 0x02d0 }
        r4 = (javax.net.ssl.HttpsURLConnection) r4;	 Catch:{ InterruptedException -> 0x0031, IOException -> 0x02d0 }
        r5 = r4;
        r5 = (javax.net.ssl.HttpsURLConnection) r5;	 Catch:{ InterruptedException -> 0x0031, IOException -> 0x02d0 }
        r1.a(r5);	 Catch:{ InterruptedException -> 0x0031, IOException -> 0x02d0 }
        goto L_0x005b;
    L_0x0053:
        r4 = r1.c;	 Catch:{ InterruptedException -> 0x0031, IOException -> 0x02d0 }
        r4 = r4.openConnection();	 Catch:{ InterruptedException -> 0x0031, IOException -> 0x02d0 }
        r4 = (java.net.HttpURLConnection) r4;	 Catch:{ InterruptedException -> 0x0031, IOException -> 0x02d0 }
    L_0x005b:
        r5 = new com.helpshift.a.a.a.b.a;	 Catch:{ InterruptedException -> 0x0031, IOException -> 0x02d0 }
        r6 = r1.a;	 Catch:{ InterruptedException -> 0x0031, IOException -> 0x02d0 }
        r7 = r1.b;	 Catch:{ InterruptedException -> 0x0031, IOException -> 0x02d0 }
        r8 = r1.e;	 Catch:{ InterruptedException -> 0x0031, IOException -> 0x02d0 }
        r8 = r8.d;	 Catch:{ InterruptedException -> 0x0031, IOException -> 0x02d0 }
        r9 = r1.e;	 Catch:{ InterruptedException -> 0x0031, IOException -> 0x02d0 }
        r9 = r9.c;	 Catch:{ InterruptedException -> 0x0031, IOException -> 0x02d0 }
        r5.<init>(r6, r7, r8, r9);	 Catch:{ InterruptedException -> 0x0031, IOException -> 0x02d0 }
        r6 = 0;
        r7 = r1.d;	 Catch:{ IOException -> 0x0244, all -> 0x0240 }
        r7 = r5.a(r7);	 Catch:{ IOException -> 0x0244, all -> 0x0240 }
        r8 = r7.length();	 Catch:{ IOException -> 0x0244, all -> 0x0240 }
        r10 = "Range";
        r11 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x0244, all -> 0x0240 }
        r11.<init>();	 Catch:{ IOException -> 0x0244, all -> 0x0240 }
        r12 = "bytes=";
        r11.append(r12);	 Catch:{ IOException -> 0x0244, all -> 0x0240 }
        r11.append(r8);	 Catch:{ IOException -> 0x0244, all -> 0x0240 }
        r12 = "-";
        r11.append(r12);	 Catch:{ IOException -> 0x0244, all -> 0x0240 }
        r11 = r11.toString();	 Catch:{ IOException -> 0x0244, all -> 0x0240 }
        r4.setRequestProperty(r10, r11);	 Catch:{ IOException -> 0x0244, all -> 0x0240 }
        r10 = 0;
        r12 = r4.getInputStream();	 Catch:{ IOException -> 0x0244, all -> 0x0240 }
        r13 = r1.e;	 Catch:{ IOException -> 0x0238, all -> 0x0232 }
        r13 = r13.b;	 Catch:{ IOException -> 0x0238, all -> 0x0232 }
        if (r13 != 0) goto L_0x013e;
    L_0x009e:
        r5 = r4.getHeaderFields();	 Catch:{ IOException -> 0x0138, all -> 0x0135 }
        r5 = r5.entrySet();	 Catch:{ IOException -> 0x0138, all -> 0x0135 }
        r5 = r5.iterator();	 Catch:{ IOException -> 0x0138, all -> 0x0135 }
    L_0x00aa:
        r7 = r5.hasNext();	 Catch:{ IOException -> 0x0138, all -> 0x0135 }
        if (r7 == 0) goto L_0x00e5;
    L_0x00b0:
        r7 = r5.next();	 Catch:{ IOException -> 0x0138, all -> 0x0135 }
        r7 = (java.util.Map.Entry) r7;	 Catch:{ IOException -> 0x0138, all -> 0x0135 }
        r8 = r7.getKey();	 Catch:{ IOException -> 0x0138, all -> 0x0135 }
        if (r8 == 0) goto L_0x00aa;
    L_0x00bc:
        r8 = r7.getKey();	 Catch:{ IOException -> 0x0138, all -> 0x0135 }
        r8 = (java.lang.String) r8;	 Catch:{ IOException -> 0x0138, all -> 0x0135 }
        r9 = "Content-Encoding";
        r8 = r8.equals(r9);	 Catch:{ IOException -> 0x0138, all -> 0x0135 }
        if (r8 == 0) goto L_0x00aa;
    L_0x00ca:
        r7 = r7.getValue();	 Catch:{ IOException -> 0x0138, all -> 0x0135 }
        r7 = (java.util.List) r7;	 Catch:{ IOException -> 0x0138, all -> 0x0135 }
        r7 = r7.get(r3);	 Catch:{ IOException -> 0x0138, all -> 0x0135 }
        r7 = (java.lang.String) r7;	 Catch:{ IOException -> 0x0138, all -> 0x0135 }
        r8 = "gzip";
        r7 = r7.equalsIgnoreCase(r8);	 Catch:{ IOException -> 0x0138, all -> 0x0135 }
        if (r7 == 0) goto L_0x00aa;
    L_0x00de:
        r7 = new java.util.zip.GZIPInputStream;	 Catch:{ IOException -> 0x0138, all -> 0x0135 }
        r7.<init>(r12);	 Catch:{ IOException -> 0x0138, all -> 0x0135 }
        r12 = r7;
        goto L_0x00aa;
    L_0x00e5:
        r5 = new java.io.InputStreamReader;	 Catch:{ IOException -> 0x0138, all -> 0x0135 }
        r5.<init>(r12);	 Catch:{ IOException -> 0x0138, all -> 0x0135 }
        r7 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x0138, all -> 0x0135 }
        r7.<init>();	 Catch:{ IOException -> 0x0138, all -> 0x0135 }
        r8 = new java.io.BufferedReader;	 Catch:{ IOException -> 0x0138, all -> 0x0135 }
        r8.<init>(r5);	 Catch:{ IOException -> 0x0138, all -> 0x0135 }
    L_0x00f4:
        r5 = r8.readLine();	 Catch:{ IOException -> 0x00fe, all -> 0x0135 }
        if (r5 == 0) goto L_0x0107;
    L_0x00fa:
        r7.append(r5);	 Catch:{ IOException -> 0x00fe, all -> 0x0135 }
        goto L_0x00f4;
    L_0x00fe:
        r0 = move-exception;
        r5 = r0;
        r8 = "Helpshift_DownloadRun";
        r9 = "IO Exception while reading response";
        com.helpshift.util.l.a(r8, r9, r5);	 Catch:{ IOException -> 0x0138, all -> 0x0135 }
    L_0x0107:
        r5 = new org.json.JSONObject;	 Catch:{ JSONException -> 0x0118 }
        r8 = r7.toString();	 Catch:{ JSONException -> 0x0118 }
        r5.<init>(r8);	 Catch:{ JSONException -> 0x0118 }
        r8 = r1.f;	 Catch:{ JSONException -> 0x0118 }
        r9 = r1.d;	 Catch:{ JSONException -> 0x0118 }
        r8.a(r2, r9, r5);	 Catch:{ JSONException -> 0x0118 }
        goto L_0x0130;
    L_0x0118:
        r5 = new org.json.JSONArray;	 Catch:{ JSONException -> 0x0129 }
        r8 = r7.toString();	 Catch:{ JSONException -> 0x0129 }
        r5.<init>(r8);	 Catch:{ JSONException -> 0x0129 }
        r8 = r1.f;	 Catch:{ JSONException -> 0x0129 }
        r9 = r1.d;	 Catch:{ JSONException -> 0x0129 }
        r8.a(r2, r9, r5);	 Catch:{ JSONException -> 0x0129 }
        goto L_0x0130;
    L_0x0129:
        r5 = r1.f;	 Catch:{ IOException -> 0x0138, all -> 0x0135 }
        r8 = r1.d;	 Catch:{ IOException -> 0x0138, all -> 0x0135 }
        r5.a(r2, r8, r7);	 Catch:{ IOException -> 0x0138, all -> 0x0135 }
    L_0x0130:
        r16 = r4;
        r14 = r6;
        goto L_0x01df;
    L_0x0135:
        r0 = move-exception;
        goto L_0x0235;
    L_0x0138:
        r0 = move-exception;
        r2 = r0;
        r14 = r6;
    L_0x013b:
        r6 = r12;
        goto L_0x0247;
    L_0x013e:
        r13 = r4.getContentLength();	 Catch:{ IOException -> 0x0238, all -> 0x0232 }
        r14 = new java.io.FileOutputStream;	 Catch:{ IOException -> 0x0238, all -> 0x0232 }
        r14.<init>(r7, r2);	 Catch:{ IOException -> 0x0238, all -> 0x0232 }
        r6 = 8192; // 0x2000 float:1.14794E-41 double:4.0474E-320;
        r15 = new byte[r6];	 Catch:{ IOException -> 0x022d, all -> 0x0227 }
    L_0x014b:
        r2 = r12.read(r15, r3, r6);	 Catch:{ IOException -> 0x022d, all -> 0x0227 }
        r6 = -1;
        if (r2 == r6) goto L_0x01ad;
    L_0x0152:
        if (r2 >= 0) goto L_0x0160;
    L_0x0154:
        r2 = new java.io.EOFException;	 Catch:{ IOException -> 0x015d, all -> 0x015a }
        r2.<init>();	 Catch:{ IOException -> 0x015d, all -> 0x015a }
        throw r2;	 Catch:{ IOException -> 0x015d, all -> 0x015a }
    L_0x015a:
        r0 = move-exception;
        goto L_0x022a;
    L_0x015d:
        r0 = move-exception;
        r2 = r0;
        goto L_0x013b;
    L_0x0160:
        r14.write(r15, r3, r2);	 Catch:{ IOException -> 0x022d, all -> 0x0227 }
        r16 = r4;
        r3 = r7.length();	 Catch:{ IOException -> 0x01a5, all -> 0x019c }
        r2 = (float) r3;
        r3 = (long) r13;
        r17 = r12;
        r18 = r13;
        r12 = r3 + r8;
        r3 = (float) r12;
        r2 = r2 / r3;
        r3 = 1120403456; // 0x42c80000 float:100.0 double:5.53552857E-315;
        r2 = r2 * r3;
        r2 = (long) r2;
        r4 = (r2 > r10 ? 1 : (r2 == r10 ? 0 : -1));
        if (r4 == 0) goto L_0x0192;
    L_0x017b:
        r4 = r1.g;	 Catch:{ IOException -> 0x0190, all -> 0x0189 }
        if (r4 == 0) goto L_0x0187;
    L_0x017f:
        r4 = r1.g;	 Catch:{ IOException -> 0x0190, all -> 0x0189 }
        r6 = r1.d;	 Catch:{ IOException -> 0x0190, all -> 0x0189 }
        r10 = (int) r2;	 Catch:{ IOException -> 0x0190, all -> 0x0189 }
        r4.a(r6, r10);	 Catch:{ IOException -> 0x0190, all -> 0x0189 }
    L_0x0187:
        r10 = r2;
        goto L_0x0192;
    L_0x0189:
        r0 = move-exception;
        r2 = r0;
        r6 = r14;
        r4 = r16;
        goto L_0x021f;
    L_0x0190:
        r0 = move-exception;
        goto L_0x01a8;
    L_0x0192:
        r4 = r16;
        r12 = r17;
        r13 = r18;
        r3 = 0;
        r6 = 8192; // 0x2000 float:1.14794E-41 double:4.0474E-320;
        goto L_0x014b;
    L_0x019c:
        r0 = move-exception;
        r17 = r12;
        r2 = r0;
        r6 = r14;
        r4 = r16;
        goto L_0x0296;
    L_0x01a5:
        r0 = move-exception;
        r17 = r12;
    L_0x01a8:
        r2 = r0;
        r4 = r16;
        goto L_0x023d;
    L_0x01ad:
        r16 = r4;
        r17 = r12;
        r2 = r1.d;	 Catch:{ IOException -> 0x0223, all -> 0x021a }
        r5.b(r2);	 Catch:{ IOException -> 0x0223, all -> 0x021a }
        r2 = r7.getAbsolutePath();	 Catch:{ IOException -> 0x0223, all -> 0x021a }
        r3 = "Helpshift_DownloadRun";
        r4 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x0223, all -> 0x021a }
        r4.<init>();	 Catch:{ IOException -> 0x0223, all -> 0x021a }
        r5 = "Download finished : ";
        r4.append(r5);	 Catch:{ IOException -> 0x0223, all -> 0x021a }
        r5 = r1.d;	 Catch:{ IOException -> 0x0223, all -> 0x021a }
        r4.append(r5);	 Catch:{ IOException -> 0x0223, all -> 0x021a }
        r4 = r4.toString();	 Catch:{ IOException -> 0x0223, all -> 0x021a }
        com.helpshift.util.l.a(r3, r4);	 Catch:{ IOException -> 0x0223, all -> 0x021a }
        r3 = r1.f;	 Catch:{ IOException -> 0x0223, all -> 0x021a }
        r4 = r1.d;	 Catch:{ IOException -> 0x0223, all -> 0x021a }
        r5 = 1;
        r3.a(r5, r4, r2);	 Catch:{ IOException -> 0x0223, all -> 0x021a }
        java.lang.Thread.interrupted();	 Catch:{ IOException -> 0x0223, all -> 0x021a }
        r12 = r17;
    L_0x01df:
        if (r12 == 0) goto L_0x020e;
    L_0x01e1:
        r12.close();	 Catch:{ IOException -> 0x01e5, InterruptedException -> 0x029c }
        goto L_0x020e;
    L_0x01e5:
        r0 = move-exception;
        r2 = r1.f;	 Catch:{ InterruptedException -> 0x029c, IOException -> 0x02d0 }
        r3 = r1.d;	 Catch:{ InterruptedException -> 0x029c, IOException -> 0x02d0 }
        r4 = 0;
        r2.a(r4, r3, r0);	 Catch:{ InterruptedException -> 0x0209, IOException -> 0x02d0 }
        r2 = "Helpshift_DownloadRun";
        r3 = "Exception in closing download response";
        r4 = 1;
        r5 = new com.helpshift.j.c.a[r4];	 Catch:{ InterruptedException -> 0x029c, IOException -> 0x02d0 }
        r4 = "route";
        r6 = r1.d;	 Catch:{ InterruptedException -> 0x029c, IOException -> 0x02d0 }
        r4 = com.helpshift.j.c.d.a(r4, r6);	 Catch:{ InterruptedException -> 0x029c, IOException -> 0x02d0 }
        r6 = 0;
        r5[r6] = r4;	 Catch:{ InterruptedException -> 0x0204, IOException -> 0x02d0 }
        com.helpshift.util.l.c(r2, r3, r0, r5);	 Catch:{ InterruptedException -> 0x029c, IOException -> 0x02d0 }
        goto L_0x020e;
    L_0x0204:
        r0 = move-exception;
        r2 = r0;
        r5 = r6;
        goto L_0x02f2;
    L_0x0209:
        r0 = move-exception;
        r2 = r0;
        r5 = r4;
        goto L_0x02f2;
    L_0x020e:
        r4 = r16;
        r4.disconnect();	 Catch:{ InterruptedException -> 0x029c, IOException -> 0x02d0 }
        if (r14 == 0) goto L_0x030d;
    L_0x0215:
        r14.close();	 Catch:{ InterruptedException -> 0x029c, IOException -> 0x02d0 }
        goto L_0x030d;
    L_0x021a:
        r0 = move-exception;
        r4 = r16;
        r2 = r0;
        r6 = r14;
    L_0x021f:
        r12 = r17;
        goto L_0x0296;
    L_0x0223:
        r0 = move-exception;
        r4 = r16;
        goto L_0x0230;
    L_0x0227:
        r0 = move-exception;
        r17 = r12;
    L_0x022a:
        r2 = r0;
        goto L_0x0295;
    L_0x022d:
        r0 = move-exception;
        r17 = r12;
    L_0x0230:
        r2 = r0;
        goto L_0x023d;
    L_0x0232:
        r0 = move-exception;
        r17 = r12;
    L_0x0235:
        r2 = r0;
        goto L_0x0296;
    L_0x0238:
        r0 = move-exception;
        r17 = r12;
        r2 = r0;
        r14 = r6;
    L_0x023d:
        r6 = r17;
        goto L_0x0247;
    L_0x0240:
        r0 = move-exception;
        r2 = r0;
        r12 = r6;
        goto L_0x0296;
    L_0x0244:
        r0 = move-exception;
        r2 = r0;
        r14 = r6;
    L_0x0247:
        r3 = r1.f;	 Catch:{ all -> 0x0292 }
        r5 = r1.d;	 Catch:{ all -> 0x0292 }
        r7 = 0;
        r3.a(r7, r5, r2);	 Catch:{ all -> 0x0292 }
        r3 = "Helpshift_DownloadRun";
        r5 = "Exception in download";
        r7 = 1;
        r8 = new com.helpshift.j.c.a[r7];	 Catch:{ all -> 0x0292 }
        r7 = "route";
        r9 = r1.d;	 Catch:{ all -> 0x0292 }
        r7 = com.helpshift.j.c.d.a(r7, r9);	 Catch:{ all -> 0x0292 }
        r9 = 0;
        r8[r9] = r7;	 Catch:{ all -> 0x0292 }
        com.helpshift.util.l.c(r3, r5, r2, r8);	 Catch:{ all -> 0x0292 }
        if (r6 == 0) goto L_0x028c;
    L_0x0266:
        r6.close();	 Catch:{ IOException -> 0x026a, InterruptedException -> 0x029c }
        goto L_0x028c;
    L_0x026a:
        r0 = move-exception;
        r2 = r1.f;	 Catch:{ InterruptedException -> 0x029c, IOException -> 0x02d0 }
        r3 = r1.d;	 Catch:{ InterruptedException -> 0x029c, IOException -> 0x02d0 }
        r5 = 0;
        r2.a(r5, r3, r0);	 Catch:{ InterruptedException -> 0x0289, IOException -> 0x02d0 }
        r2 = "Helpshift_DownloadRun";
        r3 = "Exception in closing download response";
        r5 = 1;
        r6 = new com.helpshift.j.c.a[r5];	 Catch:{ InterruptedException -> 0x029c, IOException -> 0x02d0 }
        r5 = "route";
        r7 = r1.d;	 Catch:{ InterruptedException -> 0x029c, IOException -> 0x02d0 }
        r5 = com.helpshift.j.c.d.a(r5, r7);	 Catch:{ InterruptedException -> 0x029c, IOException -> 0x02d0 }
        r7 = 0;
        r6[r7] = r5;	 Catch:{ InterruptedException -> 0x02c3, IOException -> 0x02d0 }
        com.helpshift.util.l.c(r2, r3, r0, r6);	 Catch:{ InterruptedException -> 0x029c, IOException -> 0x02d0 }
        goto L_0x028c;
    L_0x0289:
        r0 = move-exception;
        goto L_0x02f1;
    L_0x028c:
        r4.disconnect();	 Catch:{ InterruptedException -> 0x029c, IOException -> 0x02d0 }
        if (r14 == 0) goto L_0x030d;
    L_0x0291:
        goto L_0x0215;
    L_0x0292:
        r0 = move-exception;
        r2 = r0;
        r12 = r6;
    L_0x0295:
        r6 = r14;
    L_0x0296:
        if (r12 == 0) goto L_0x02c7;
    L_0x0298:
        r12.close();	 Catch:{ IOException -> 0x02a0, InterruptedException -> 0x029c }
        goto L_0x02c7;
    L_0x029c:
        r0 = move-exception;
        r2 = r0;
        r5 = 0;
        goto L_0x02f2;
    L_0x02a0:
        r0 = move-exception;
        r3 = r1.f;	 Catch:{ InterruptedException -> 0x029c, IOException -> 0x02d0 }
        r5 = r1.d;	 Catch:{ InterruptedException -> 0x029c, IOException -> 0x02d0 }
        r7 = 0;
        r3.a(r7, r5, r0);	 Catch:{ InterruptedException -> 0x02c3, IOException -> 0x02d0 }
        r3 = "Helpshift_DownloadRun";
        r5 = "Exception in closing download response";
        r7 = 1;
        r8 = new com.helpshift.j.c.a[r7];	 Catch:{ InterruptedException -> 0x029c, IOException -> 0x02d0 }
        r7 = "route";
        r9 = r1.d;	 Catch:{ InterruptedException -> 0x029c, IOException -> 0x02d0 }
        r7 = com.helpshift.j.c.d.a(r7, r9);	 Catch:{ InterruptedException -> 0x029c, IOException -> 0x02d0 }
        r9 = 0;
        r8[r9] = r7;	 Catch:{ InterruptedException -> 0x02bf, IOException -> 0x02d0 }
        com.helpshift.util.l.c(r3, r5, r0, r8);	 Catch:{ InterruptedException -> 0x029c, IOException -> 0x02d0 }
        goto L_0x02c7;
    L_0x02bf:
        r0 = move-exception;
        r2 = r0;
        r5 = r9;
        goto L_0x02f2;
    L_0x02c3:
        r0 = move-exception;
        r2 = r0;
        r5 = r7;
        goto L_0x02f2;
    L_0x02c7:
        r4.disconnect();	 Catch:{ InterruptedException -> 0x029c, IOException -> 0x02d0 }
        if (r6 == 0) goto L_0x02cf;
    L_0x02cc:
        r6.close();	 Catch:{ InterruptedException -> 0x029c, IOException -> 0x02d0 }
    L_0x02cf:
        throw r2;	 Catch:{ InterruptedException -> 0x029c, IOException -> 0x02d0 }
    L_0x02d0:
        r0 = move-exception;
        r2 = r0;
        r3 = r1.f;
        r4 = r1.d;
        r5 = 0;
        r3.a(r5, r4, r2);
        r3 = "Helpshift_DownloadRun";
        r4 = "Exception IO";
        r6 = 1;
        r6 = new com.helpshift.j.c.a[r6];
        r7 = "route";
        r8 = r1.d;
        r7 = com.helpshift.j.c.d.a(r7, r8);
        r6[r5] = r7;
        com.helpshift.util.l.c(r3, r4, r2, r6);
        goto L_0x030d;
    L_0x02ef:
        r0 = move-exception;
        r5 = r3;
    L_0x02f1:
        r2 = r0;
    L_0x02f2:
        r3 = r1.f;
        r4 = r1.d;
        r3.a(r5, r4, r2);
        r3 = "Helpshift_DownloadRun";
        r4 = "Exception Interrupted";
        r6 = 1;
        r6 = new com.helpshift.j.c.a[r6];
        r7 = "route";
        r8 = r1.d;
        r7 = com.helpshift.j.c.d.a(r7, r8);
        r6[r5] = r7;
        com.helpshift.util.l.c(r3, r4, r2, r6);
    L_0x030d:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.helpshift.a.a.a.c.run():void");
    }

    private void a(HttpsURLConnection httpsURLConnection) {
        if (VERSION.SDK_INT >= 16 && VERSION.SDK_INT <= 19) {
            ArrayList arrayList = new ArrayList();
            arrayList.add("TLSv1.2");
            ArrayList arrayList2 = new ArrayList();
            arrayList2.add("SSLv3");
            httpsURLConnection.setSSLSocketFactory(new d(httpsURLConnection.getSSLSocketFactory(), arrayList, arrayList2));
        }
    }
}
