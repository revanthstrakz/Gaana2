package io.branch.referral;

import android.content.Context;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class n {
    protected m a;

    public n(Context context) {
        this.a = m.a(context);
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0038 */
    /* JADX WARNING: Failed to process nested try/catch */
    private io.branch.referral.af a(java.io.InputStream r3, int r4, java.lang.String r5, boolean r6) {
        /*
        r2 = this;
        r0 = new io.branch.referral.af;
        r0.<init>(r5, r4);
        if (r3 == 0) goto L_0x0088;
    L_0x0007:
        r4 = new java.io.BufferedReader;	 Catch:{ IOException -> 0x0065 }
        r5 = new java.io.InputStreamReader;	 Catch:{ IOException -> 0x0065 }
        r5.<init>(r3);	 Catch:{ IOException -> 0x0065 }
        r4.<init>(r5);	 Catch:{ IOException -> 0x0065 }
        r3 = r4.readLine();	 Catch:{ IOException -> 0x0065 }
        if (r6 == 0) goto L_0x002d;
    L_0x0017:
        r4 = "BranchSDK";
        r5 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x0065 }
        r5.<init>();	 Catch:{ IOException -> 0x0065 }
        r1 = "returned ";
        r5.append(r1);	 Catch:{ IOException -> 0x0065 }
        r5.append(r3);	 Catch:{ IOException -> 0x0065 }
        r5 = r5.toString();	 Catch:{ IOException -> 0x0065 }
        io.branch.referral.m.c(r4, r5);	 Catch:{ IOException -> 0x0065 }
    L_0x002d:
        if (r3 == 0) goto L_0x0088;
    L_0x002f:
        r4 = new org.json.JSONObject;	 Catch:{ JSONException -> 0x0038 }
        r4.<init>(r3);	 Catch:{ JSONException -> 0x0038 }
        r0.a(r4);	 Catch:{ JSONException -> 0x0038 }
        goto L_0x0088;
    L_0x0038:
        r4 = new org.json.JSONArray;	 Catch:{ JSONException -> 0x0041 }
        r4.<init>(r3);	 Catch:{ JSONException -> 0x0041 }
        r0.a(r4);	 Catch:{ JSONException -> 0x0041 }
        goto L_0x0088;
    L_0x0041:
        r3 = move-exception;
        if (r6 == 0) goto L_0x0088;
    L_0x0044:
        r4 = r2.getClass();	 Catch:{ IOException -> 0x0065 }
        r4 = r4.getSimpleName();	 Catch:{ IOException -> 0x0065 }
        r5 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x0065 }
        r5.<init>();	 Catch:{ IOException -> 0x0065 }
        r1 = "JSON exception: ";
        r5.append(r1);	 Catch:{ IOException -> 0x0065 }
        r3 = r3.getMessage();	 Catch:{ IOException -> 0x0065 }
        r5.append(r3);	 Catch:{ IOException -> 0x0065 }
        r3 = r5.toString();	 Catch:{ IOException -> 0x0065 }
        io.branch.referral.m.c(r4, r3);	 Catch:{ IOException -> 0x0065 }
        goto L_0x0088;
    L_0x0065:
        r3 = move-exception;
        if (r6 == 0) goto L_0x0088;
    L_0x0068:
        r4 = r2.getClass();
        r4 = r4.getSimpleName();
        r5 = new java.lang.StringBuilder;
        r5.<init>();
        r6 = "IO exception: ";
        r5.append(r6);
        r3 = r3.getMessage();
        r5.append(r3);
        r3 = r5.toString();
        io.branch.referral.m.c(r4, r3);
    L_0x0088:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: io.branch.referral.n.a(java.io.InputStream, int, java.lang.String, boolean):io.branch.referral.af");
    }

    public af a(String str, JSONObject jSONObject, String str2, int i) {
        return a(str, jSONObject, str2, i, 0, true);
    }

    private boolean a(JSONObject jSONObject, int i) {
        try {
            String g = this.a.g();
            String f = this.a.f();
            jSONObject.put("sdk", "android1.14.5");
            jSONObject.put("retryNumber", i);
            if (g.equals("bnc_no_value")) {
                if (!f.equals("bnc_no_value")) {
                    jSONObject.put("app_id", this.a.f());
                    return true;
                }
                return false;
            }
            jSONObject.put("branch_key", this.a.g());
            return true;
        } catch (JSONException unused) {
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:193:0x0403 A:{SYNTHETIC, Splitter:B:193:0x0403} */
    /* JADX WARNING: Removed duplicated region for block: B:198:0x042e  */
    /* JADX WARNING: Removed duplicated region for block: B:200:0x045c  */
    /* JADX WARNING: Removed duplicated region for block: B:180:0x03ba A:{SYNTHETIC, Splitter:B:180:0x03ba} */
    /* JADX WARNING: Removed duplicated region for block: B:167:0x0364 A:{SYNTHETIC, Splitter:B:167:0x0364} */
    /* JADX WARNING: Removed duplicated region for block: B:155:0x02ff A:{SYNTHETIC, Splitter:B:155:0x02ff} */
    /* JADX WARNING: Removed duplicated region for block: B:160:0x032a  */
    /* JADX WARNING: Removed duplicated region for block: B:162:0x0358  */
    /* JADX WARNING: Removed duplicated region for block: B:143:0x029a A:{SYNTHETIC, Splitter:B:143:0x029a} */
    /* JADX WARNING: Removed duplicated region for block: B:148:0x02c7  */
    /* JADX WARNING: Removed duplicated region for block: B:150:0x02f5  */
    /* JADX WARNING: Removed duplicated region for block: B:204:0x0466  */
    /* JADX WARNING: Removed duplicated region for block: B:206:0x0494  */
    /* JADX WARNING: Removed duplicated region for block: B:193:0x0403 A:{SYNTHETIC, Splitter:B:193:0x0403} */
    /* JADX WARNING: Removed duplicated region for block: B:198:0x042e  */
    /* JADX WARNING: Removed duplicated region for block: B:200:0x045c  */
    /* JADX WARNING: Removed duplicated region for block: B:138:? A:{SYNTHETIC, Splitter:B:35:0x008f, ExcHandler: SocketTimeoutException (unused java.net.SocketTimeoutException)} */
    /* JADX WARNING: Removed duplicated region for block: B:155:0x02ff A:{SYNTHETIC, Splitter:B:155:0x02ff} */
    /* JADX WARNING: Removed duplicated region for block: B:160:0x032a  */
    /* JADX WARNING: Removed duplicated region for block: B:162:0x0358  */
    /* JADX WARNING: Removed duplicated region for block: B:143:0x029a A:{SYNTHETIC, Splitter:B:143:0x029a} */
    /* JADX WARNING: Removed duplicated region for block: B:148:0x02c7  */
    /* JADX WARNING: Removed duplicated region for block: B:150:0x02f5  */
    /* JADX WARNING: Removed duplicated region for block: B:204:0x0466  */
    /* JADX WARNING: Removed duplicated region for block: B:206:0x0494  */
    /* JADX WARNING: Removed duplicated region for block: B:125:? A:{SYNTHETIC, Splitter:B:38:0x009e, ExcHandler: SocketTimeoutException (unused java.net.SocketTimeoutException)} */
    /* JADX WARNING: Removed duplicated region for block: B:155:0x02ff A:{SYNTHETIC, Splitter:B:155:0x02ff} */
    /* JADX WARNING: Removed duplicated region for block: B:160:0x032a  */
    /* JADX WARNING: Removed duplicated region for block: B:162:0x0358  */
    /* JADX WARNING: Removed duplicated region for block: B:143:0x029a A:{SYNTHETIC, Splitter:B:143:0x029a} */
    /* JADX WARNING: Removed duplicated region for block: B:148:0x02c7  */
    /* JADX WARNING: Removed duplicated region for block: B:150:0x02f5  */
    /* JADX WARNING: Removed duplicated region for block: B:204:0x0466  */
    /* JADX WARNING: Removed duplicated region for block: B:206:0x0494  */
    /* JADX WARNING: Removed duplicated region for block: B:193:0x0403 A:{SYNTHETIC, Splitter:B:193:0x0403} */
    /* JADX WARNING: Removed duplicated region for block: B:198:0x042e  */
    /* JADX WARNING: Removed duplicated region for block: B:200:0x045c  */
    /* JADX WARNING: Removed duplicated region for block: B:167:0x0364 A:{SYNTHETIC, Splitter:B:167:0x0364} */
    /* JADX WARNING: Removed duplicated region for block: B:180:0x03ba A:{SYNTHETIC, Splitter:B:180:0x03ba} */
    /* JADX WARNING: Removed duplicated region for block: B:155:0x02ff A:{SYNTHETIC, Splitter:B:155:0x02ff} */
    /* JADX WARNING: Removed duplicated region for block: B:160:0x032a  */
    /* JADX WARNING: Removed duplicated region for block: B:162:0x0358  */
    /* JADX WARNING: Removed duplicated region for block: B:143:0x029a A:{SYNTHETIC, Splitter:B:143:0x029a} */
    /* JADX WARNING: Removed duplicated region for block: B:148:0x02c7  */
    /* JADX WARNING: Removed duplicated region for block: B:150:0x02f5  */
    /* JADX WARNING: Removed duplicated region for block: B:204:0x0466  */
    /* JADX WARNING: Removed duplicated region for block: B:206:0x0494  */
    /* JADX WARNING: Removed duplicated region for block: B:105:0x0207 A:{Splitter:B:72:0x0126, ExcHandler: SocketException (r0_15 'e' java.net.SocketException), PHI: r9 r12 } */
    /* JADX WARNING: Removed duplicated region for block: B:180:0x03ba A:{SYNTHETIC, Splitter:B:180:0x03ba} */
    /* JADX WARNING: Removed duplicated region for block: B:167:0x0364 A:{SYNTHETIC, Splitter:B:167:0x0364} */
    /* JADX WARNING: Missing exception handler attribute for start block: B:164:0x035c */
    /* JADX WARNING: Removed duplicated region for block: B:104:0x0204 A:{Splitter:B:72:0x0126, ExcHandler: UnknownHostException (e java.net.UnknownHostException), PHI: r9 r12 } */
    /* JADX WARNING: Removed duplicated region for block: B:103:0x0201 A:{Splitter:B:72:0x0126, ExcHandler: IOException (e java.io.IOException), PHI: r12 } */
    /* JADX WARNING: Removed duplicated region for block: B:204:0x0466  */
    /* JADX WARNING: Removed duplicated region for block: B:206:0x0494  */
    /* JADX WARNING: Removed duplicated region for block: B:167:0x0364 A:{SYNTHETIC, Splitter:B:167:0x0364} */
    /* JADX WARNING: Removed duplicated region for block: B:180:0x03ba A:{SYNTHETIC, Splitter:B:180:0x03ba} */
    /* JADX WARNING: Removed duplicated region for block: B:155:0x02ff A:{SYNTHETIC, Splitter:B:155:0x02ff} */
    /* JADX WARNING: Removed duplicated region for block: B:160:0x032a  */
    /* JADX WARNING: Removed duplicated region for block: B:162:0x0358  */
    /* JADX WARNING: Removed duplicated region for block: B:143:0x029a A:{SYNTHETIC, Splitter:B:143:0x029a} */
    /* JADX WARNING: Removed duplicated region for block: B:148:0x02c7  */
    /* JADX WARNING: Removed duplicated region for block: B:150:0x02f5  */
    /* JADX WARNING: Removed duplicated region for block: B:193:0x0403 A:{SYNTHETIC, Splitter:B:193:0x0403} */
    /* JADX WARNING: Removed duplicated region for block: B:198:0x042e  */
    /* JADX WARNING: Removed duplicated region for block: B:200:0x045c  */
    /* JADX WARNING: Removed duplicated region for block: B:27:? A:{SYNTHETIC, Splitter:B:17:0x0053, ExcHandler: SocketTimeoutException (unused java.net.SocketTimeoutException)} */
    /* JADX WARNING: Removed duplicated region for block: B:155:0x02ff A:{SYNTHETIC, Splitter:B:155:0x02ff} */
    /* JADX WARNING: Removed duplicated region for block: B:160:0x032a  */
    /* JADX WARNING: Removed duplicated region for block: B:162:0x0358  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x006e A:{Splitter:B:17:0x0053, ExcHandler: IOException (r0_2 'e' java.io.IOException)} */
    /* JADX WARNING: Removed duplicated region for block: B:138:? A:{SYNTHETIC, Splitter:B:35:0x008f, ExcHandler: SocketTimeoutException (unused java.net.SocketTimeoutException)} */
    /* JADX WARNING: Removed duplicated region for block: B:176:0x0388  */
    /* JADX WARNING: Removed duplicated region for block: B:178:0x03b6  */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing block: B:19:0x006a, code skipped:
            r0 = move-exception;
     */
    /* JADX WARNING: Missing block: B:20:0x006b, code skipped:
            r1 = r0;
     */
    /* JADX WARNING: Missing block: B:21:0x006e, code skipped:
            r0 = move-exception;
     */
    /* JADX WARNING: Missing block: B:22:0x006f, code skipped:
            r1 = r0;
     */
    /* JADX WARNING: Missing block: B:26:0x0078, code skipped:
            r18 = r12;
            r12 = null;
     */
    /* JADX WARNING: Missing block: B:80:0x0162, code skipped:
            r1 = r16;
     */
    /* JADX WARNING: Missing block: B:103:0x0201, code skipped:
            r0 = e;
     */
    /* JADX WARNING: Missing block: B:104:0x0204, code skipped:
            r0 = e;
     */
    /* JADX WARNING: Missing block: B:105:0x0207, code skipped:
            r0 = move-exception;
     */
    /* JADX WARNING: Missing block: B:106:0x0208, code skipped:
            r2 = r0;
            r1 = r9;
            r3 = r12;
     */
    /* JADX WARNING: Missing block: B:118:0x0265, code skipped:
            r0 = th;
     */
    /* JADX WARNING: Missing block: B:119:0x0266, code skipped:
            r12 = r17;
     */
    /* JADX WARNING: Missing block: B:120:0x026a, code skipped:
            r0 = e;
     */
    /* JADX WARNING: Missing block: B:121:0x026b, code skipped:
            r12 = r17;
     */
    /* JADX WARNING: Missing block: B:122:0x026e, code skipped:
            r0 = e;
     */
    /* JADX WARNING: Missing block: B:123:0x026f, code skipped:
            r12 = r17;
     */
    /* JADX WARNING: Missing block: B:124:0x0272, code skipped:
            r18 = r12;
            r12 = r17;
     */
    /* JADX WARNING: Missing block: B:126:0x0278, code skipped:
            r0 = e;
     */
    /* JADX WARNING: Missing block: B:127:0x0279, code skipped:
            r12 = r17;
     */
    /* JADX WARNING: Missing block: B:128:0x027c, code skipped:
            r0 = th;
     */
    /* JADX WARNING: Missing block: B:129:0x027d, code skipped:
            r12 = r7;
     */
    /* JADX WARNING: Missing block: B:130:0x0280, code skipped:
            r0 = e;
     */
    /* JADX WARNING: Missing block: B:131:0x0281, code skipped:
            r12 = r7;
     */
    /* JADX WARNING: Missing block: B:132:0x0282, code skipped:
            r1 = r0;
            r3 = r12;
     */
    /* JADX WARNING: Missing block: B:133:0x0285, code skipped:
            r0 = e;
     */
    /* JADX WARNING: Missing block: B:134:0x0286, code skipped:
            r12 = r7;
     */
    /* JADX WARNING: Missing block: B:135:0x0287, code skipped:
            r9 = -1009;
     */
    /* JADX WARNING: Missing block: B:136:0x0289, code skipped:
            r1 = r0;
            r3 = r12;
     */
    /* JADX WARNING: Missing block: B:137:0x028d, code skipped:
            r18 = r12;
            r12 = r7;
     */
    /* JADX WARNING: Missing block: B:139:0x0292, code skipped:
            r0 = e;
     */
    /* JADX WARNING: Missing block: B:140:0x0293, code skipped:
            r12 = r7;
     */
    /* JADX WARNING: Missing block: B:141:0x0294, code skipped:
            r2 = r0;
            r3 = r12;
     */
    /* JADX WARNING: Missing block: B:144:?, code skipped:
            r2 = getClass().getSimpleName();
            r4 = new java.lang.StringBuilder();
            r4.append("IO exception: ");
            r4.append(r1.getMessage());
            io.branch.referral.m.c(r2, r4.toString());
     */
    /* JADX WARNING: Missing block: B:148:0x02c7, code skipped:
            r2 = (int) (java.lang.System.currentTimeMillis() - r14);
            r4 = io.branch.referral.Branch.a();
            r5 = new java.lang.StringBuilder();
            r5.append(r10);
            r5.append("-");
            r5.append(io.branch.referral.Defines.Jsonkey.Branch_Round_Trip_Time.getKey());
            r4.a(r5.toString(), java.lang.String.valueOf(r2));
     */
    /* JADX WARNING: Missing block: B:150:0x02f5, code skipped:
            r3.disconnect();
     */
    /* JADX WARNING: Missing block: B:152:0x02f9, code skipped:
            r0 = move-exception;
     */
    /* JADX WARNING: Missing block: B:153:0x02fa, code skipped:
            r9 = -1009;
            r1 = r0;
     */
    /* JADX WARNING: Missing block: B:156:?, code skipped:
            r2 = getClass().getSimpleName();
            r4 = new java.lang.StringBuilder();
            r4.append("Http connect exception: ");
            r4.append(r1.getMessage());
            io.branch.referral.m.c(r2, r4.toString());
     */
    /* JADX WARNING: Missing block: B:160:0x032a, code skipped:
            r2 = (int) (java.lang.System.currentTimeMillis() - r14);
            r4 = io.branch.referral.Branch.a();
            r5 = new java.lang.StringBuilder();
            r5.append(r10);
            r5.append("-");
            r5.append(io.branch.referral.Defines.Jsonkey.Branch_Round_Trip_Time.getKey());
            r4.a(r5.toString(), java.lang.String.valueOf(r2));
     */
    /* JADX WARNING: Missing block: B:162:0x0358, code skipped:
            r3.disconnect();
     */
    /* JADX WARNING: Missing block: B:168:?, code skipped:
            java.lang.Thread.sleep((long) r8.a.d());
     */
    /* JADX WARNING: Missing block: B:176:0x0388, code skipped:
            r2 = (int) (java.lang.System.currentTimeMillis() - r14);
            r3 = io.branch.referral.Branch.a();
            r4 = new java.lang.StringBuilder();
            r4.append(r10);
            r4.append("-");
            r4.append(io.branch.referral.Defines.Jsonkey.Branch_Round_Trip_Time.getKey());
            r3.a(r4.toString(), java.lang.String.valueOf(r2));
     */
    /* JADX WARNING: Missing block: B:178:0x03b6, code skipped:
            r12.disconnect();
     */
    /* JADX WARNING: Missing block: B:181:?, code skipped:
            r1 = new io.branch.referral.af(r10, -111);
     */
    /* JADX WARNING: Missing block: B:183:0x03c5, code skipped:
            if (io.branch.referral.Branch.a() != null) goto L_0x03c7;
     */
    /* JADX WARNING: Missing block: B:184:0x03c7, code skipped:
            r2 = (int) (java.lang.System.currentTimeMillis() - r14);
            r3 = io.branch.referral.Branch.a();
            r4 = new java.lang.StringBuilder();
            r4.append(r10);
            r4.append("-");
            r4.append(io.branch.referral.Defines.Jsonkey.Branch_Round_Trip_Time.getKey());
            r3.a(r4.toString(), java.lang.String.valueOf(r2));
     */
    /* JADX WARNING: Missing block: B:185:0x03f3, code skipped:
            if (r12 != null) goto L_0x03f5;
     */
    /* JADX WARNING: Missing block: B:186:0x03f5, code skipped:
            r12.disconnect();
     */
    /* JADX WARNING: Missing block: B:187:0x03f8, code skipped:
            return r1;
     */
    /* JADX WARNING: Missing block: B:190:0x03fd, code skipped:
            r0 = move-exception;
     */
    /* JADX WARNING: Missing block: B:191:0x03fe, code skipped:
            r1 = -1009;
            r2 = r0;
     */
    /* JADX WARNING: Missing block: B:194:?, code skipped:
            r4 = getClass().getSimpleName();
            r5 = new java.lang.StringBuilder();
            r5.append("Http connect exception: ");
            r5.append(r2.getMessage());
            io.branch.referral.m.c(r4, r5.toString());
     */
    /* JADX WARNING: Missing block: B:198:0x042e, code skipped:
            r1 = (int) (java.lang.System.currentTimeMillis() - r14);
            r4 = io.branch.referral.Branch.a();
            r5 = new java.lang.StringBuilder();
            r5.append(r10);
            r5.append("-");
            r5.append(io.branch.referral.Defines.Jsonkey.Branch_Round_Trip_Time.getKey());
            r4.a(r5.toString(), java.lang.String.valueOf(r1));
     */
    /* JADX WARNING: Missing block: B:200:0x045c, code skipped:
            r3.disconnect();
     */
    /* JADX WARNING: Missing block: B:204:0x0466, code skipped:
            r2 = (int) (java.lang.System.currentTimeMillis() - r14);
            r4 = io.branch.referral.Branch.a();
            r5 = new java.lang.StringBuilder();
            r5.append(r10);
            r5.append("-");
            r5.append(io.branch.referral.Defines.Jsonkey.Branch_Round_Trip_Time.getKey());
            r4.a(r5.toString(), java.lang.String.valueOf(r2));
     */
    /* JADX WARNING: Missing block: B:206:0x0494, code skipped:
            r3.disconnect();
     */
    private io.branch.referral.af a(java.lang.String r21, org.json.JSONObject r22, java.lang.String r23, int r24, int r25, boolean r26) {
        /*
        r20 = this;
        r8 = r20;
        r9 = r22;
        r10 = r23;
        r1 = r25;
        r11 = r26;
        r2 = new org.json.JSONObject;
        r2.<init>();
        if (r24 > 0) goto L_0x0015;
    L_0x0011:
        r3 = 3000; // 0xbb8 float:4.204E-42 double:1.482E-320;
        r12 = r3;
        goto L_0x0017;
    L_0x0015:
        r12 = r24;
    L_0x0017:
        r3 = r8.a(r2, r1);
        if (r3 == 0) goto L_0x0498;
    L_0x001d:
        if (r9 == 0) goto L_0x0037;
    L_0x001f:
        r3 = r22.keys();
    L_0x0023:
        r4 = r3.hasNext();
        if (r4 == 0) goto L_0x0037;
    L_0x0029:
        r4 = r3.next();
        r4 = (java.lang.String) r4;
        r5 = r9.getString(r4);	 Catch:{ JSONException -> 0x0023 }
        r2.put(r4, r5);	 Catch:{ JSONException -> 0x0023 }
        goto L_0x0023;
    L_0x0037:
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r13 = r21;
        r3.append(r13);
        r2 = r8.a(r2);
        r3.append(r2);
        r2 = r3.toString();
        r14 = java.lang.System.currentTimeMillis();
        r3 = 0;
        if (r11 == 0) goto L_0x0083;
    L_0x0053:
        r4 = "BranchSDK";
        r5 = new java.lang.StringBuilder;	 Catch:{ SocketException -> 0x007d, SocketTimeoutException -> 0x0078, UnknownHostException -> 0x0072, IOException -> 0x006e }
        r5.<init>();	 Catch:{ SocketException -> 0x007d, SocketTimeoutException -> 0x0078, UnknownHostException -> 0x0072, IOException -> 0x006e }
        r7 = "getting ";
        r5.append(r7);	 Catch:{ SocketException -> 0x007d, SocketTimeoutException -> 0x0078, UnknownHostException -> 0x0072, IOException -> 0x006e }
        r5.append(r2);	 Catch:{ SocketException -> 0x007d, SocketTimeoutException -> 0x0078, UnknownHostException -> 0x0072, IOException -> 0x006e }
        r5 = r5.toString();	 Catch:{ SocketException -> 0x007d, SocketTimeoutException -> 0x0078, UnknownHostException -> 0x0072, IOException -> 0x006e }
        io.branch.referral.m.c(r4, r5);	 Catch:{ SocketException -> 0x007d, SocketTimeoutException -> 0x0078, UnknownHostException -> 0x0072, IOException -> 0x006e }
        goto L_0x0083;
    L_0x006a:
        r0 = move-exception;
        r1 = r0;
        goto L_0x0460;
    L_0x006e:
        r0 = move-exception;
        r1 = r0;
        goto L_0x0298;
    L_0x0072:
        r0 = move-exception;
        r1 = r0;
    L_0x0074:
        r9 = -1009; // 0xfffffffffffffc0f float:NaN double:NaN;
        goto L_0x02fd;
    L_0x0078:
        r18 = r12;
        r12 = r3;
        goto L_0x035c;
    L_0x007d:
        r0 = move-exception;
        r2 = r0;
    L_0x007f:
        r1 = -1009; // 0xfffffffffffffc0f float:NaN double:NaN;
        goto L_0x0401;
    L_0x0083:
        r4 = new java.net.URL;	 Catch:{ SocketException -> 0x03fd, SocketTimeoutException -> 0x0078, UnknownHostException -> 0x02f9, IOException -> 0x006e }
        r4.<init>(r2);	 Catch:{ SocketException -> 0x007d, SocketTimeoutException -> 0x0078, UnknownHostException -> 0x02f9, IOException -> 0x006e }
        r2 = r4.openConnection();	 Catch:{ SocketException -> 0x007d, SocketTimeoutException -> 0x0078, UnknownHostException -> 0x02f9, IOException -> 0x006e }
        r7 = r2;
        r7 = (javax.net.ssl.HttpsURLConnection) r7;	 Catch:{ SocketException -> 0x007d, SocketTimeoutException -> 0x0078, UnknownHostException -> 0x02f9, IOException -> 0x006e }
        r7.setConnectTimeout(r12);	 Catch:{ SocketException -> 0x0292, SocketTimeoutException -> 0x028d, UnknownHostException -> 0x0285, IOException -> 0x0280, all -> 0x027c }
        r7.setReadTimeout(r12);	 Catch:{ SocketException -> 0x0292, SocketTimeoutException -> 0x028d, UnknownHostException -> 0x0285, IOException -> 0x0280, all -> 0x027c }
        r4 = java.lang.System.currentTimeMillis();	 Catch:{ SocketException -> 0x0292, SocketTimeoutException -> 0x028d, UnknownHostException -> 0x0285, IOException -> 0x0280, all -> 0x027c }
        r17 = r7;
        r6 = r4 - r14;
        r2 = (int) r6;
        r4 = io.branch.referral.Branch.a();	 Catch:{ SocketException -> 0x0278, SocketTimeoutException -> 0x0272, UnknownHostException -> 0x026e, IOException -> 0x026a, all -> 0x0265 }
        if (r4 == 0) goto L_0x00e0;
    L_0x00a4:
        r4 = io.branch.referral.Branch.a();	 Catch:{ SocketException -> 0x00db, SocketTimeoutException -> 0x0272, UnknownHostException -> 0x00d6, IOException -> 0x00d0, all -> 0x00ca }
        r5 = new java.lang.StringBuilder;	 Catch:{ SocketException -> 0x00db, SocketTimeoutException -> 0x0272, UnknownHostException -> 0x00d6, IOException -> 0x00d0, all -> 0x00ca }
        r5.<init>();	 Catch:{ SocketException -> 0x00db, SocketTimeoutException -> 0x0272, UnknownHostException -> 0x00d6, IOException -> 0x00d0, all -> 0x00ca }
        r5.append(r10);	 Catch:{ SocketException -> 0x00db, SocketTimeoutException -> 0x0272, UnknownHostException -> 0x00d6, IOException -> 0x00d0, all -> 0x00ca }
        r6 = "-";
        r5.append(r6);	 Catch:{ SocketException -> 0x00db, SocketTimeoutException -> 0x0272, UnknownHostException -> 0x00d6, IOException -> 0x00d0, all -> 0x00ca }
        r6 = io.branch.referral.Defines.Jsonkey.Last_Round_Trip_Time;	 Catch:{ SocketException -> 0x00db, SocketTimeoutException -> 0x0272, UnknownHostException -> 0x00d6, IOException -> 0x00d0, all -> 0x00ca }
        r6 = r6.getKey();	 Catch:{ SocketException -> 0x00db, SocketTimeoutException -> 0x0272, UnknownHostException -> 0x00d6, IOException -> 0x00d0, all -> 0x00ca }
        r5.append(r6);	 Catch:{ SocketException -> 0x00db, SocketTimeoutException -> 0x0272, UnknownHostException -> 0x00d6, IOException -> 0x00d0, all -> 0x00ca }
        r5 = r5.toString();	 Catch:{ SocketException -> 0x00db, SocketTimeoutException -> 0x0272, UnknownHostException -> 0x00d6, IOException -> 0x00d0, all -> 0x00ca }
        r2 = java.lang.String.valueOf(r2);	 Catch:{ SocketException -> 0x00db, SocketTimeoutException -> 0x0272, UnknownHostException -> 0x00d6, IOException -> 0x00d0, all -> 0x00ca }
        r4.a(r5, r2);	 Catch:{ SocketException -> 0x00db, SocketTimeoutException -> 0x0272, UnknownHostException -> 0x00d6, IOException -> 0x00d0, all -> 0x00ca }
        goto L_0x00e0;
    L_0x00ca:
        r0 = move-exception;
        r1 = r0;
        r3 = r17;
        goto L_0x0460;
    L_0x00d0:
        r0 = move-exception;
        r1 = r0;
        r3 = r17;
        goto L_0x0298;
    L_0x00d6:
        r0 = move-exception;
        r1 = r0;
        r3 = r17;
        goto L_0x0074;
    L_0x00db:
        r0 = move-exception;
        r2 = r0;
        r3 = r17;
        goto L_0x007f;
    L_0x00e0:
        r7 = r17;
        r2 = r7.getResponseCode();	 Catch:{ SocketException -> 0x0292, SocketTimeoutException -> 0x028d, UnknownHostException -> 0x0285, IOException -> 0x0280, all -> 0x027c }
        r6 = 500; // 0x1f4 float:7.0E-43 double:2.47E-321;
        if (r2 < r6) goto L_0x0166;
    L_0x00ea:
        r2 = r8.a;	 Catch:{ SocketException -> 0x0292, SocketTimeoutException -> 0x028d, UnknownHostException -> 0x0285, IOException -> 0x0280, all -> 0x027c }
        r2 = r2.c();	 Catch:{ SocketException -> 0x0292, SocketTimeoutException -> 0x028d, UnknownHostException -> 0x0285, IOException -> 0x0280, all -> 0x027c }
        if (r1 >= r2) goto L_0x0166;
    L_0x00f2:
        r2 = r8.a;	 Catch:{ InterruptedException -> 0x0111, SocketException -> 0x010c, SocketTimeoutException -> 0x028d, UnknownHostException -> 0x0107, IOException -> 0x0102, all -> 0x00fd }
        r2 = r2.d();	 Catch:{ InterruptedException -> 0x0111, SocketException -> 0x010c, SocketTimeoutException -> 0x028d, UnknownHostException -> 0x0107, IOException -> 0x0102, all -> 0x00fd }
        r2 = (long) r2;	 Catch:{ InterruptedException -> 0x0111, SocketException -> 0x010c, SocketTimeoutException -> 0x028d, UnknownHostException -> 0x0107, IOException -> 0x0102, all -> 0x00fd }
        java.lang.Thread.sleep(r2);	 Catch:{ InterruptedException -> 0x0111, SocketException -> 0x010c, SocketTimeoutException -> 0x028d, UnknownHostException -> 0x0107, IOException -> 0x0102, all -> 0x00fd }
        goto L_0x0116;
    L_0x00fd:
        r0 = move-exception;
        r1 = r0;
        r3 = r7;
        goto L_0x0460;
    L_0x0102:
        r0 = move-exception;
        r1 = r0;
        r3 = r7;
        goto L_0x0298;
    L_0x0107:
        r0 = move-exception;
        r1 = r0;
        r3 = r7;
        goto L_0x0074;
    L_0x010c:
        r0 = move-exception;
        r2 = r0;
        r3 = r7;
        goto L_0x007f;
    L_0x0111:
        r0 = move-exception;
        r2 = r0;
        com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r2);	 Catch:{ SocketException -> 0x0292, SocketTimeoutException -> 0x028d, UnknownHostException -> 0x0285, IOException -> 0x0280, all -> 0x027c }
    L_0x0116:
        r16 = r1 + 1;
        r1 = r8;
        r2 = r13;
        r3 = r9;
        r4 = r10;
        r5 = r12;
        r18 = r12;
        r12 = r6;
        r6 = r16;
        r12 = r7;
        r9 = -1009; // 0xfffffffffffffc0f float:NaN double:NaN;
        r7 = r11;
        r1 = r1.a(r2, r3, r4, r5, r6, r7);	 Catch:{ SocketException -> 0x0207, SocketTimeoutException -> 0x0162, UnknownHostException -> 0x0204, IOException -> 0x0201 }
        r2 = io.branch.referral.Branch.a();
        if (r2 == 0) goto L_0x015c;
    L_0x0130:
        r2 = java.lang.System.currentTimeMillis();
        r4 = r2 - r14;
        r2 = (int) r4;
        r3 = io.branch.referral.Branch.a();
        r4 = new java.lang.StringBuilder;
        r4.<init>();
        r4.append(r10);
        r5 = "-";
        r4.append(r5);
        r5 = io.branch.referral.Defines.Jsonkey.Branch_Round_Trip_Time;
        r5 = r5.getKey();
        r4.append(r5);
        r4 = r4.toString();
        r2 = java.lang.String.valueOf(r2);
        r3.a(r4, r2);
    L_0x015c:
        if (r12 == 0) goto L_0x0161;
    L_0x015e:
        r12.disconnect();
    L_0x0161:
        return r1;
    L_0x0162:
        r1 = r16;
        goto L_0x035c;
    L_0x0166:
        r18 = r12;
        r9 = -1009; // 0xfffffffffffffc0f float:NaN double:NaN;
        r12 = r7;
        r2 = r12.getResponseCode();	 Catch:{ FileNotFoundException -> 0x020d }
        r4 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        if (r2 == r4) goto L_0x01bd;
    L_0x0173:
        r2 = r12.getErrorStream();	 Catch:{ FileNotFoundException -> 0x020d }
        if (r2 == 0) goto L_0x01bd;
    L_0x0179:
        r2 = r12.getErrorStream();	 Catch:{ FileNotFoundException -> 0x020d }
        r4 = r12.getResponseCode();	 Catch:{ FileNotFoundException -> 0x020d }
        r2 = r8.a(r2, r4, r10, r11);	 Catch:{ FileNotFoundException -> 0x020d }
        r1 = io.branch.referral.Branch.a();
        if (r1 == 0) goto L_0x01b7;
    L_0x018b:
        r3 = java.lang.System.currentTimeMillis();
        r5 = r3 - r14;
        r1 = (int) r5;
        r3 = io.branch.referral.Branch.a();
        r4 = new java.lang.StringBuilder;
        r4.<init>();
        r4.append(r10);
        r5 = "-";
        r4.append(r5);
        r5 = io.branch.referral.Defines.Jsonkey.Branch_Round_Trip_Time;
        r5 = r5.getKey();
        r4.append(r5);
        r4 = r4.toString();
        r1 = java.lang.String.valueOf(r1);
        r3.a(r4, r1);
    L_0x01b7:
        if (r12 == 0) goto L_0x01bc;
    L_0x01b9:
        r12.disconnect();
    L_0x01bc:
        return r2;
    L_0x01bd:
        r2 = r12.getInputStream();	 Catch:{ FileNotFoundException -> 0x020d }
        r4 = r12.getResponseCode();	 Catch:{ FileNotFoundException -> 0x020d }
        r2 = r8.a(r2, r4, r10, r11);	 Catch:{ FileNotFoundException -> 0x020d }
        r1 = io.branch.referral.Branch.a();
        if (r1 == 0) goto L_0x01fb;
    L_0x01cf:
        r3 = java.lang.System.currentTimeMillis();
        r5 = r3 - r14;
        r1 = (int) r5;
        r3 = io.branch.referral.Branch.a();
        r4 = new java.lang.StringBuilder;
        r4.<init>();
        r4.append(r10);
        r5 = "-";
        r4.append(r5);
        r5 = io.branch.referral.Defines.Jsonkey.Branch_Round_Trip_Time;
        r5 = r5.getKey();
        r4.append(r5);
        r4 = r4.toString();
        r1 = java.lang.String.valueOf(r1);
        r3.a(r4, r1);
    L_0x01fb:
        if (r12 == 0) goto L_0x0200;
    L_0x01fd:
        r12.disconnect();
    L_0x0200:
        return r2;
    L_0x0201:
        r0 = move-exception;
        goto L_0x0282;
    L_0x0204:
        r0 = move-exception;
        goto L_0x0289;
    L_0x0207:
        r0 = move-exception;
        r2 = r0;
        r1 = r9;
        r3 = r12;
        goto L_0x0401;
    L_0x020d:
        if (r11 == 0) goto L_0x0225;
    L_0x020f:
        r2 = "BranchSDK";
        r4 = new java.lang.StringBuilder;	 Catch:{ SocketException -> 0x0207, SocketTimeoutException -> 0x035c, UnknownHostException -> 0x0204, IOException -> 0x0201 }
        r4.<init>();	 Catch:{ SocketException -> 0x0207, SocketTimeoutException -> 0x035c, UnknownHostException -> 0x0204, IOException -> 0x0201 }
        r5 = "A resource conflict occurred with this request ";
        r4.append(r5);	 Catch:{ SocketException -> 0x0207, SocketTimeoutException -> 0x035c, UnknownHostException -> 0x0204, IOException -> 0x0201 }
        r4.append(r10);	 Catch:{ SocketException -> 0x0207, SocketTimeoutException -> 0x035c, UnknownHostException -> 0x0204, IOException -> 0x0201 }
        r4 = r4.toString();	 Catch:{ SocketException -> 0x0207, SocketTimeoutException -> 0x035c, UnknownHostException -> 0x0204, IOException -> 0x0201 }
        io.branch.referral.m.c(r2, r4);	 Catch:{ SocketException -> 0x0207, SocketTimeoutException -> 0x035c, UnknownHostException -> 0x0204, IOException -> 0x0201 }
    L_0x0225:
        r2 = r12.getResponseCode();	 Catch:{ SocketException -> 0x0207, SocketTimeoutException -> 0x035c, UnknownHostException -> 0x0204, IOException -> 0x0201 }
        r2 = r8.a(r3, r2, r10, r11);	 Catch:{ SocketException -> 0x0207, SocketTimeoutException -> 0x035c, UnknownHostException -> 0x0204, IOException -> 0x0201 }
        r1 = io.branch.referral.Branch.a();
        if (r1 == 0) goto L_0x025f;
    L_0x0233:
        r3 = java.lang.System.currentTimeMillis();
        r5 = r3 - r14;
        r1 = (int) r5;
        r3 = io.branch.referral.Branch.a();
        r4 = new java.lang.StringBuilder;
        r4.<init>();
        r4.append(r10);
        r5 = "-";
        r4.append(r5);
        r5 = io.branch.referral.Defines.Jsonkey.Branch_Round_Trip_Time;
        r5 = r5.getKey();
        r4.append(r5);
        r4 = r4.toString();
        r1 = java.lang.String.valueOf(r1);
        r3.a(r4, r1);
    L_0x025f:
        if (r12 == 0) goto L_0x0264;
    L_0x0261:
        r12.disconnect();
    L_0x0264:
        return r2;
    L_0x0265:
        r0 = move-exception;
        r12 = r17;
        goto L_0x03fa;
    L_0x026a:
        r0 = move-exception;
        r12 = r17;
        goto L_0x0282;
    L_0x026e:
        r0 = move-exception;
        r12 = r17;
        goto L_0x0287;
    L_0x0272:
        r18 = r12;
        r12 = r17;
        goto L_0x035c;
    L_0x0278:
        r0 = move-exception;
        r12 = r17;
        goto L_0x0294;
    L_0x027c:
        r0 = move-exception;
        r12 = r7;
        goto L_0x03fa;
    L_0x0280:
        r0 = move-exception;
        r12 = r7;
    L_0x0282:
        r1 = r0;
        r3 = r12;
        goto L_0x0298;
    L_0x0285:
        r0 = move-exception;
        r12 = r7;
    L_0x0287:
        r9 = -1009; // 0xfffffffffffffc0f float:NaN double:NaN;
    L_0x0289:
        r1 = r0;
        r3 = r12;
        goto L_0x02fd;
    L_0x028d:
        r18 = r12;
        r12 = r7;
        goto L_0x035c;
    L_0x0292:
        r0 = move-exception;
        r12 = r7;
    L_0x0294:
        r2 = r0;
        r3 = r12;
        goto L_0x007f;
    L_0x0298:
        if (r11 == 0) goto L_0x02ba;
    L_0x029a:
        r2 = r20.getClass();	 Catch:{ all -> 0x006a }
        r2 = r2.getSimpleName();	 Catch:{ all -> 0x006a }
        r4 = new java.lang.StringBuilder;	 Catch:{ all -> 0x006a }
        r4.<init>();	 Catch:{ all -> 0x006a }
        r5 = "IO exception: ";
        r4.append(r5);	 Catch:{ all -> 0x006a }
        r1 = r1.getMessage();	 Catch:{ all -> 0x006a }
        r4.append(r1);	 Catch:{ all -> 0x006a }
        r1 = r4.toString();	 Catch:{ all -> 0x006a }
        io.branch.referral.m.c(r2, r1);	 Catch:{ all -> 0x006a }
    L_0x02ba:
        r1 = new io.branch.referral.af;	 Catch:{ all -> 0x006a }
        r2 = 500; // 0x1f4 float:7.0E-43 double:2.47E-321;
        r1.<init>(r10, r2);	 Catch:{ all -> 0x006a }
        r2 = io.branch.referral.Branch.a();
        if (r2 == 0) goto L_0x02f3;
    L_0x02c7:
        r4 = java.lang.System.currentTimeMillis();
        r6 = r4 - r14;
        r2 = (int) r6;
        r4 = io.branch.referral.Branch.a();
        r5 = new java.lang.StringBuilder;
        r5.<init>();
        r5.append(r10);
        r6 = "-";
        r5.append(r6);
        r6 = io.branch.referral.Defines.Jsonkey.Branch_Round_Trip_Time;
        r6 = r6.getKey();
        r5.append(r6);
        r5 = r5.toString();
        r2 = java.lang.String.valueOf(r2);
        r4.a(r5, r2);
    L_0x02f3:
        if (r3 == 0) goto L_0x02f8;
    L_0x02f5:
        r3.disconnect();
    L_0x02f8:
        return r1;
    L_0x02f9:
        r0 = move-exception;
        r9 = -1009; // 0xfffffffffffffc0f float:NaN double:NaN;
        r1 = r0;
    L_0x02fd:
        if (r11 == 0) goto L_0x031f;
    L_0x02ff:
        r2 = r20.getClass();	 Catch:{ all -> 0x006a }
        r2 = r2.getSimpleName();	 Catch:{ all -> 0x006a }
        r4 = new java.lang.StringBuilder;	 Catch:{ all -> 0x006a }
        r4.<init>();	 Catch:{ all -> 0x006a }
        r5 = "Http connect exception: ";
        r4.append(r5);	 Catch:{ all -> 0x006a }
        r1 = r1.getMessage();	 Catch:{ all -> 0x006a }
        r4.append(r1);	 Catch:{ all -> 0x006a }
        r1 = r4.toString();	 Catch:{ all -> 0x006a }
        io.branch.referral.m.c(r2, r1);	 Catch:{ all -> 0x006a }
    L_0x031f:
        r1 = new io.branch.referral.af;	 Catch:{ all -> 0x006a }
        r1.<init>(r10, r9);	 Catch:{ all -> 0x006a }
        r2 = io.branch.referral.Branch.a();
        if (r2 == 0) goto L_0x0356;
    L_0x032a:
        r4 = java.lang.System.currentTimeMillis();
        r6 = r4 - r14;
        r2 = (int) r6;
        r4 = io.branch.referral.Branch.a();
        r5 = new java.lang.StringBuilder;
        r5.<init>();
        r5.append(r10);
        r6 = "-";
        r5.append(r6);
        r6 = io.branch.referral.Defines.Jsonkey.Branch_Round_Trip_Time;
        r6 = r6.getKey();
        r5.append(r6);
        r5 = r5.toString();
        r2 = java.lang.String.valueOf(r2);
        r4.a(r5, r2);
    L_0x0356:
        if (r3 == 0) goto L_0x035b;
    L_0x0358:
        r3.disconnect();
    L_0x035b:
        return r1;
    L_0x035c:
        r2 = r8.a;	 Catch:{ all -> 0x03f9 }
        r2 = r2.c();	 Catch:{ all -> 0x03f9 }
        if (r1 >= r2) goto L_0x03ba;
    L_0x0364:
        r2 = r8.a;	 Catch:{ InterruptedException -> 0x036f }
        r2 = r2.d();	 Catch:{ InterruptedException -> 0x036f }
        r2 = (long) r2;	 Catch:{ InterruptedException -> 0x036f }
        java.lang.Thread.sleep(r2);	 Catch:{ InterruptedException -> 0x036f }
        goto L_0x0374;
    L_0x036f:
        r0 = move-exception;
        r2 = r0;
        com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r2);	 Catch:{ all -> 0x03f9 }
    L_0x0374:
        r6 = r1 + 1;
        r1 = r8;
        r2 = r13;
        r3 = r22;
        r4 = r10;
        r5 = r18;
        r7 = r11;
        r1 = r1.a(r2, r3, r4, r5, r6, r7);	 Catch:{ all -> 0x03f9 }
        r2 = io.branch.referral.Branch.a();
        if (r2 == 0) goto L_0x03b4;
    L_0x0388:
        r2 = java.lang.System.currentTimeMillis();
        r4 = r2 - r14;
        r2 = (int) r4;
        r3 = io.branch.referral.Branch.a();
        r4 = new java.lang.StringBuilder;
        r4.<init>();
        r4.append(r10);
        r5 = "-";
        r4.append(r5);
        r5 = io.branch.referral.Defines.Jsonkey.Branch_Round_Trip_Time;
        r5 = r5.getKey();
        r4.append(r5);
        r4 = r4.toString();
        r2 = java.lang.String.valueOf(r2);
        r3.a(r4, r2);
    L_0x03b4:
        if (r12 == 0) goto L_0x03b9;
    L_0x03b6:
        r12.disconnect();
    L_0x03b9:
        return r1;
    L_0x03ba:
        r1 = new io.branch.referral.af;	 Catch:{ all -> 0x03f9 }
        r2 = -111; // 0xffffffffffffff91 float:NaN double:NaN;
        r1.<init>(r10, r2);	 Catch:{ all -> 0x03f9 }
        r2 = io.branch.referral.Branch.a();
        if (r2 == 0) goto L_0x03f3;
    L_0x03c7:
        r2 = java.lang.System.currentTimeMillis();
        r4 = r2 - r14;
        r2 = (int) r4;
        r3 = io.branch.referral.Branch.a();
        r4 = new java.lang.StringBuilder;
        r4.<init>();
        r4.append(r10);
        r5 = "-";
        r4.append(r5);
        r5 = io.branch.referral.Defines.Jsonkey.Branch_Round_Trip_Time;
        r5 = r5.getKey();
        r4.append(r5);
        r4 = r4.toString();
        r2 = java.lang.String.valueOf(r2);
        r3.a(r4, r2);
    L_0x03f3:
        if (r12 == 0) goto L_0x03f8;
    L_0x03f5:
        r12.disconnect();
    L_0x03f8:
        return r1;
    L_0x03f9:
        r0 = move-exception;
    L_0x03fa:
        r1 = r0;
        r3 = r12;
        goto L_0x0460;
    L_0x03fd:
        r0 = move-exception;
        r1 = -1009; // 0xfffffffffffffc0f float:NaN double:NaN;
        r2 = r0;
    L_0x0401:
        if (r11 == 0) goto L_0x0423;
    L_0x0403:
        r4 = r20.getClass();	 Catch:{ all -> 0x006a }
        r4 = r4.getSimpleName();	 Catch:{ all -> 0x006a }
        r5 = new java.lang.StringBuilder;	 Catch:{ all -> 0x006a }
        r5.<init>();	 Catch:{ all -> 0x006a }
        r6 = "Http connect exception: ";
        r5.append(r6);	 Catch:{ all -> 0x006a }
        r2 = r2.getMessage();	 Catch:{ all -> 0x006a }
        r5.append(r2);	 Catch:{ all -> 0x006a }
        r2 = r5.toString();	 Catch:{ all -> 0x006a }
        io.branch.referral.m.c(r4, r2);	 Catch:{ all -> 0x006a }
    L_0x0423:
        r2 = new io.branch.referral.af;	 Catch:{ all -> 0x006a }
        r2.<init>(r10, r1);	 Catch:{ all -> 0x006a }
        r1 = io.branch.referral.Branch.a();
        if (r1 == 0) goto L_0x045a;
    L_0x042e:
        r4 = java.lang.System.currentTimeMillis();
        r6 = r4 - r14;
        r1 = (int) r6;
        r4 = io.branch.referral.Branch.a();
        r5 = new java.lang.StringBuilder;
        r5.<init>();
        r5.append(r10);
        r6 = "-";
        r5.append(r6);
        r6 = io.branch.referral.Defines.Jsonkey.Branch_Round_Trip_Time;
        r6 = r6.getKey();
        r5.append(r6);
        r5 = r5.toString();
        r1 = java.lang.String.valueOf(r1);
        r4.a(r5, r1);
    L_0x045a:
        if (r3 == 0) goto L_0x045f;
    L_0x045c:
        r3.disconnect();
    L_0x045f:
        return r2;
    L_0x0460:
        r2 = io.branch.referral.Branch.a();
        if (r2 == 0) goto L_0x0492;
    L_0x0466:
        r4 = java.lang.System.currentTimeMillis();
        r6 = r4 - r14;
        r2 = (int) r6;
        r4 = io.branch.referral.Branch.a();
        r5 = new java.lang.StringBuilder;
        r5.<init>();
        r5.append(r10);
        r6 = "-";
        r5.append(r6);
        r6 = io.branch.referral.Defines.Jsonkey.Branch_Round_Trip_Time;
        r6 = r6.getKey();
        r5.append(r6);
        r5 = r5.toString();
        r2 = java.lang.String.valueOf(r2);
        r4.a(r5, r2);
    L_0x0492:
        if (r3 == 0) goto L_0x0497;
    L_0x0494:
        r3.disconnect();
    L_0x0497:
        throw r1;
    L_0x0498:
        r1 = new io.branch.referral.af;
        r2 = -1234; // 0xfffffffffffffb2e float:NaN double:NaN;
        r1.<init>(r10, r2);
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: io.branch.referral.n.a(java.lang.String, org.json.JSONObject, java.lang.String, int, int, boolean):io.branch.referral.af");
    }

    public af a(JSONObject jSONObject, String str, String str2, int i) {
        return a(jSONObject, str, str2, i, 0, true);
    }

    /* JADX WARNING: Removed duplicated region for block: B:212:0x04d2  */
    /* JADX WARNING: Removed duplicated region for block: B:214:0x0500  */
    /* JADX WARNING: Removed duplicated region for block: B:199:0x046a A:{SYNTHETIC, Splitter:B:199:0x046a} */
    /* JADX WARNING: Removed duplicated region for block: B:206:0x049a  */
    /* JADX WARNING: Removed duplicated region for block: B:208:0x04c8  */
    /* JADX WARNING: Removed duplicated region for block: B:187:0x0403 A:{SYNTHETIC, Splitter:B:187:0x0403} */
    /* JADX WARNING: Removed duplicated region for block: B:192:0x0430  */
    /* JADX WARNING: Removed duplicated region for block: B:194:0x045e  */
    /* JADX WARNING: Removed duplicated region for block: B:173:0x03ba A:{SYNTHETIC, Splitter:B:173:0x03ba} */
    /* JADX WARNING: Removed duplicated region for block: B:160:0x0365 A:{SYNTHETIC, Splitter:B:160:0x0365} */
    /* JADX WARNING: Missing exception handler attribute for start block: B:157:0x035d */
    /* JADX WARNING: Removed duplicated region for block: B:140:0x02e7 A:{SYNTHETIC, Splitter:B:140:0x02e7} */
    /* JADX WARNING: Removed duplicated region for block: B:150:0x0325  */
    /* JADX WARNING: Removed duplicated region for block: B:152:0x0353  */
    /* JADX WARNING: Removed duplicated region for block: B:199:0x046a A:{SYNTHETIC, Splitter:B:199:0x046a} */
    /* JADX WARNING: Removed duplicated region for block: B:206:0x049a  */
    /* JADX WARNING: Removed duplicated region for block: B:208:0x04c8  */
    /* JADX WARNING: Removed duplicated region for block: B:187:0x0403 A:{SYNTHETIC, Splitter:B:187:0x0403} */
    /* JADX WARNING: Removed duplicated region for block: B:192:0x0430  */
    /* JADX WARNING: Removed duplicated region for block: B:194:0x045e  */
    /* JADX WARNING: Removed duplicated region for block: B:160:0x0365 A:{SYNTHETIC, Splitter:B:160:0x0365} */
    /* JADX WARNING: Removed duplicated region for block: B:173:0x03ba A:{SYNTHETIC, Splitter:B:173:0x03ba} */
    /* JADX WARNING: Removed duplicated region for block: B:140:0x02e7 A:{SYNTHETIC, Splitter:B:140:0x02e7} */
    /* JADX WARNING: Removed duplicated region for block: B:150:0x0325  */
    /* JADX WARNING: Removed duplicated region for block: B:152:0x0353  */
    /* JADX WARNING: Removed duplicated region for block: B:212:0x04d2  */
    /* JADX WARNING: Removed duplicated region for block: B:214:0x0500  */
    /* JADX WARNING: Removed duplicated region for block: B:102:0x0257 A:{Splitter:B:69:0x0177, ExcHandler: SocketException (e java.net.SocketException), PHI: r12 } */
    /* JADX WARNING: Removed duplicated region for block: B:101:0x0254 A:{Splitter:B:69:0x0177, ExcHandler: UnknownHostException (e java.net.UnknownHostException), PHI: r12 } */
    /* JADX WARNING: Removed duplicated region for block: B:173:0x03ba A:{SYNTHETIC, Splitter:B:173:0x03ba} */
    /* JADX WARNING: Removed duplicated region for block: B:160:0x0365 A:{SYNTHETIC, Splitter:B:160:0x0365} */
    /* JADX WARNING: Removed duplicated region for block: B:100:0x0252 A:{Splitter:B:69:0x0177, ExcHandler: Exception (e java.lang.Exception), PHI: r12 } */
    /* JADX WARNING: Removed duplicated region for block: B:199:0x046a A:{SYNTHETIC, Splitter:B:199:0x046a} */
    /* JADX WARNING: Removed duplicated region for block: B:206:0x049a  */
    /* JADX WARNING: Removed duplicated region for block: B:208:0x04c8  */
    /* JADX WARNING: Removed duplicated region for block: B:187:0x0403 A:{SYNTHETIC, Splitter:B:187:0x0403} */
    /* JADX WARNING: Removed duplicated region for block: B:192:0x0430  */
    /* JADX WARNING: Removed duplicated region for block: B:194:0x045e  */
    /* JADX WARNING: Removed duplicated region for block: B:160:0x0365 A:{SYNTHETIC, Splitter:B:160:0x0365} */
    /* JADX WARNING: Removed duplicated region for block: B:173:0x03ba A:{SYNTHETIC, Splitter:B:173:0x03ba} */
    /* JADX WARNING: Removed duplicated region for block: B:140:0x02e7 A:{SYNTHETIC, Splitter:B:140:0x02e7} */
    /* JADX WARNING: Removed duplicated region for block: B:150:0x0325  */
    /* JADX WARNING: Removed duplicated region for block: B:152:0x0353  */
    /* JADX WARNING: Removed duplicated region for block: B:212:0x04d2  */
    /* JADX WARNING: Removed duplicated region for block: B:214:0x0500  */
    /* JADX WARNING: Removed duplicated region for block: B:187:0x0403 A:{SYNTHETIC, Splitter:B:187:0x0403} */
    /* JADX WARNING: Removed duplicated region for block: B:192:0x0430  */
    /* JADX WARNING: Removed duplicated region for block: B:194:0x045e  */
    /* JADX WARNING: Removed duplicated region for block: B:173:0x03ba A:{SYNTHETIC, Splitter:B:173:0x03ba} */
    /* JADX WARNING: Removed duplicated region for block: B:160:0x0365 A:{SYNTHETIC, Splitter:B:160:0x0365} */
    /* JADX WARNING: Removed duplicated region for block: B:140:0x02e7 A:{SYNTHETIC, Splitter:B:140:0x02e7} */
    /* JADX WARNING: Removed duplicated region for block: B:150:0x0325  */
    /* JADX WARNING: Removed duplicated region for block: B:152:0x0353  */
    /* JADX WARNING: Removed duplicated region for block: B:212:0x04d2  */
    /* JADX WARNING: Removed duplicated region for block: B:214:0x0500  */
    /* JADX WARNING: Removed duplicated region for block: B:212:0x04d2  */
    /* JADX WARNING: Removed duplicated region for block: B:214:0x0500  */
    /* JADX WARNING: Removed duplicated region for block: B:199:0x046a A:{SYNTHETIC, Splitter:B:199:0x046a} */
    /* JADX WARNING: Removed duplicated region for block: B:206:0x049a  */
    /* JADX WARNING: Removed duplicated region for block: B:208:0x04c8  */
    /* JADX WARNING: Removed duplicated region for block: B:183:0x03fd A:{Splitter:B:5:0x001c, ExcHandler: UnknownHostException (e java.net.UnknownHostException)} */
    /* JADX WARNING: Removed duplicated region for block: B:155:? A:{SYNTHETIC, Splitter:B:5:0x001c, ExcHandler: SocketTimeoutException (unused java.net.SocketTimeoutException)} */
    /* JADX WARNING: Removed duplicated region for block: B:136:0x02e1 A:{Splitter:B:5:0x001c, ExcHandler: Exception (e java.lang.Exception)} */
    /* JADX WARNING: Removed duplicated region for block: B:133:0x02db A:{Splitter:B:5:0x001c, ExcHandler: all (th java.lang.Throwable)} */
    /* JADX WARNING: Removed duplicated region for block: B:169:0x0388  */
    /* JADX WARNING: Removed duplicated region for block: B:171:0x03b6  */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing block: B:100:0x0252, code skipped:
            r0 = e;
     */
    /* JADX WARNING: Missing block: B:101:0x0254, code skipped:
            r0 = e;
     */
    /* JADX WARNING: Missing block: B:102:0x0257, code skipped:
            r0 = e;
     */
    /* JADX WARNING: Missing block: B:129:0x02d3, code skipped:
            r0 = e;
     */
    /* JADX WARNING: Missing block: B:130:0x02d4, code skipped:
            r7 = null;
     */
    /* JADX WARNING: Missing block: B:131:0x02d5, code skipped:
            r1 = r0;
            r2 = r7;
     */
    /* JADX WARNING: Missing block: B:133:0x02db, code skipped:
            r0 = th;
     */
    /* JADX WARNING: Missing block: B:134:0x02dc, code skipped:
            r7 = null;
     */
    /* JADX WARNING: Missing block: B:136:0x02e1, code skipped:
            r0 = e;
     */
    /* JADX WARNING: Missing block: B:137:0x02e2, code skipped:
            r7 = null;
     */
    /* JADX WARNING: Missing block: B:141:?, code skipped:
            r3 = getClass().getSimpleName();
            r4 = new java.lang.StringBuilder();
            r4.append("Exception: ");
            r4.append(r1.getMessage());
            io.branch.referral.m.c(r3, r4.toString());
     */
    /* JADX WARNING: Missing block: B:150:0x0325, code skipped:
            r3 = (int) (java.lang.System.currentTimeMillis() - r14);
            r4 = io.branch.referral.Branch.a();
            r5 = new java.lang.StringBuilder();
            r5.append(r10);
            r5.append("-");
            r5.append(io.branch.referral.Defines.Jsonkey.Branch_Round_Trip_Time.getKey());
            r4.a(r5.toString(), java.lang.String.valueOf(r3));
     */
    /* JADX WARNING: Missing block: B:152:0x0353, code skipped:
            r2.disconnect();
     */
    /* JADX WARNING: Missing block: B:154:0x0357, code skipped:
            r17 = true;
            r18 = r12;
            r7 = null;
     */
    /* JADX WARNING: Missing block: B:161:?, code skipped:
            java.lang.Thread.sleep((long) r8.a.d());
     */
    /* JADX WARNING: Missing block: B:169:0x0388, code skipped:
            r2 = (int) (java.lang.System.currentTimeMillis() - r14);
            r3 = io.branch.referral.Branch.a();
            r4 = new java.lang.StringBuilder();
            r4.append(r10);
            r4.append("-");
            r4.append(io.branch.referral.Defines.Jsonkey.Branch_Round_Trip_Time.getKey());
            r3.a(r4.toString(), java.lang.String.valueOf(r2));
     */
    /* JADX WARNING: Missing block: B:171:0x03b6, code skipped:
            r12.disconnect();
     */
    /* JADX WARNING: Missing block: B:174:?, code skipped:
            r1 = new io.branch.referral.af(r10, -111);
     */
    /* JADX WARNING: Missing block: B:176:0x03c5, code skipped:
            if (io.branch.referral.Branch.a() != null) goto L_0x03c7;
     */
    /* JADX WARNING: Missing block: B:177:0x03c7, code skipped:
            r2 = (int) (java.lang.System.currentTimeMillis() - r14);
            r3 = io.branch.referral.Branch.a();
            r4 = new java.lang.StringBuilder();
            r4.append(r10);
            r4.append("-");
            r4.append(io.branch.referral.Defines.Jsonkey.Branch_Round_Trip_Time.getKey());
            r3.a(r4.toString(), java.lang.String.valueOf(r2));
     */
    /* JADX WARNING: Missing block: B:178:0x03f3, code skipped:
            if (r12 != null) goto L_0x03f5;
     */
    /* JADX WARNING: Missing block: B:179:0x03f5, code skipped:
            r12.disconnect();
     */
    /* JADX WARNING: Missing block: B:180:0x03f8, code skipped:
            return r1;
     */
    /* JADX WARNING: Missing block: B:183:0x03fd, code skipped:
            r0 = e;
     */
    /* JADX WARNING: Missing block: B:184:0x03fe, code skipped:
            r7 = null;
     */
    /* JADX WARNING: Missing block: B:188:?, code skipped:
            r3 = getClass().getSimpleName();
            r4 = new java.lang.StringBuilder();
            r4.append("Http connect exception: ");
            r4.append(r1.getMessage());
            io.branch.referral.m.c(r3, r4.toString());
     */
    /* JADX WARNING: Missing block: B:192:0x0430, code skipped:
            r3 = (int) (java.lang.System.currentTimeMillis() - r14);
            r4 = io.branch.referral.Branch.a();
            r5 = new java.lang.StringBuilder();
            r5.append(r10);
            r5.append("-");
            r5.append(io.branch.referral.Defines.Jsonkey.Branch_Round_Trip_Time.getKey());
            r4.a(r5.toString(), java.lang.String.valueOf(r3));
     */
    /* JADX WARNING: Missing block: B:194:0x045e, code skipped:
            r2.disconnect();
     */
    /* JADX WARNING: Missing block: B:200:?, code skipped:
            r4 = getClass().getSimpleName();
            r5 = new java.lang.StringBuilder();
            r5.append("Http connect exception: ");
            r5.append(r1.getMessage());
            io.branch.referral.m.c(r4, r5.toString());
     */
    /* JADX WARNING: Missing block: B:201:0x048b, code skipped:
            r0 = move-exception;
     */
    /* JADX WARNING: Missing block: B:202:0x048c, code skipped:
            r1 = r0;
            r12 = r2;
     */
    /* JADX WARNING: Missing block: B:206:0x049a, code skipped:
            r3 = (int) (java.lang.System.currentTimeMillis() - r14);
            r4 = io.branch.referral.Branch.a();
            r5 = new java.lang.StringBuilder();
            r5.append(r10);
            r5.append("-");
            r5.append(io.branch.referral.Defines.Jsonkey.Branch_Round_Trip_Time.getKey());
            r4.a(r5.toString(), java.lang.String.valueOf(r3));
     */
    /* JADX WARNING: Missing block: B:208:0x04c8, code skipped:
            r2.disconnect();
     */
    private io.branch.referral.af a(org.json.JSONObject r21, java.lang.String r22, java.lang.String r23, int r24, int r25, boolean r26) {
        /*
        r20 = this;
        r8 = r20;
        r9 = r22;
        r10 = r23;
        r1 = r25;
        r11 = r26;
        if (r24 > 0) goto L_0x0010;
    L_0x000c:
        r2 = 3000; // 0xbb8 float:4.204E-42 double:1.482E-320;
        r12 = r2;
        goto L_0x0012;
    L_0x0010:
        r12 = r24;
    L_0x0012:
        r13 = new org.json.JSONObject;
        r13.<init>();
        r14 = java.lang.System.currentTimeMillis();
        r5 = 1;
        r3 = r21.keys();	 Catch:{ SocketException -> 0x0462, UnknownHostException -> 0x03fd, SocketTimeoutException -> 0x0357, Exception -> 0x02e1, all -> 0x02db }
    L_0x0020:
        r4 = r3.hasNext();	 Catch:{ SocketException -> 0x02d3, UnknownHostException -> 0x03fd, SocketTimeoutException -> 0x0357, Exception -> 0x02e1, all -> 0x02db }
        if (r4 == 0) goto L_0x0057;
    L_0x0026:
        r4 = r3.next();	 Catch:{ SocketException -> 0x0052, UnknownHostException -> 0x004d, SocketTimeoutException -> 0x0046, Exception -> 0x0041, all -> 0x003c }
        r4 = (java.lang.String) r4;	 Catch:{ SocketException -> 0x0052, UnknownHostException -> 0x004d, SocketTimeoutException -> 0x0046, Exception -> 0x0041, all -> 0x003c }
        r7 = r21;
        r2 = r7.get(r4);	 Catch:{ JSONException -> 0x0036 }
        r13.put(r4, r2);	 Catch:{ JSONException -> 0x0036 }
        goto L_0x0020;
    L_0x0036:
        r0 = move-exception;
        r2 = r0;
        com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r2);	 Catch:{ SocketException -> 0x0052, UnknownHostException -> 0x004d, SocketTimeoutException -> 0x0046, Exception -> 0x0041, all -> 0x003c }
        goto L_0x0020;
    L_0x003c:
        r0 = move-exception;
        r1 = r0;
        r12 = 0;
        goto L_0x04cc;
    L_0x0041:
        r0 = move-exception;
        r1 = r0;
        r2 = 0;
        goto L_0x02e5;
    L_0x0046:
        r17 = r5;
        r18 = r12;
        r12 = 0;
        goto L_0x035d;
    L_0x004d:
        r0 = move-exception;
        r1 = r0;
        r2 = 0;
        goto L_0x0401;
    L_0x0052:
        r0 = move-exception;
        r1 = r0;
        r2 = 0;
        goto L_0x02d7;
    L_0x0057:
        r2 = r8.a(r13, r1);	 Catch:{ SocketException -> 0x02d3, UnknownHostException -> 0x03fd, SocketTimeoutException -> 0x0357, Exception -> 0x02e1, all -> 0x02db }
        if (r2 != 0) goto L_0x0097;
    L_0x005d:
        r2 = new io.branch.referral.af;	 Catch:{ SocketException -> 0x0052, UnknownHostException -> 0x004d, SocketTimeoutException -> 0x0046, Exception -> 0x0041, all -> 0x003c }
        r3 = -1234; // 0xfffffffffffffb2e float:NaN double:NaN;
        r2.<init>(r10, r3);	 Catch:{ SocketException -> 0x0052, UnknownHostException -> 0x004d, SocketTimeoutException -> 0x0046, Exception -> 0x0041, all -> 0x003c }
        r1 = io.branch.referral.Branch.a();
        if (r1 == 0) goto L_0x0096;
    L_0x006a:
        r3 = java.lang.System.currentTimeMillis();
        r5 = r3 - r14;
        r1 = (int) r5;
        r3 = io.branch.referral.Branch.a();
        r4 = new java.lang.StringBuilder;
        r4.<init>();
        r4.append(r10);
        r5 = "-";
        r4.append(r5);
        r5 = io.branch.referral.Defines.Jsonkey.Branch_Round_Trip_Time;
        r5 = r5.getKey();
        r4.append(r5);
        r4 = r4.toString();
        r1 = java.lang.String.valueOf(r1);
        r3.a(r4, r1);
    L_0x0096:
        return r2;
    L_0x0097:
        if (r11 == 0) goto L_0x00ca;
    L_0x0099:
        r2 = "BranchSDK";
        r3 = new java.lang.StringBuilder;	 Catch:{ SocketException -> 0x0052, UnknownHostException -> 0x004d, SocketTimeoutException -> 0x0046, Exception -> 0x0041, all -> 0x003c }
        r3.<init>();	 Catch:{ SocketException -> 0x0052, UnknownHostException -> 0x004d, SocketTimeoutException -> 0x0046, Exception -> 0x0041, all -> 0x003c }
        r4 = "posting to ";
        r3.append(r4);	 Catch:{ SocketException -> 0x0052, UnknownHostException -> 0x004d, SocketTimeoutException -> 0x0046, Exception -> 0x0041, all -> 0x003c }
        r3.append(r9);	 Catch:{ SocketException -> 0x0052, UnknownHostException -> 0x004d, SocketTimeoutException -> 0x0046, Exception -> 0x0041, all -> 0x003c }
        r3 = r3.toString();	 Catch:{ SocketException -> 0x0052, UnknownHostException -> 0x004d, SocketTimeoutException -> 0x0046, Exception -> 0x0041, all -> 0x003c }
        io.branch.referral.m.c(r2, r3);	 Catch:{ SocketException -> 0x0052, UnknownHostException -> 0x004d, SocketTimeoutException -> 0x0046, Exception -> 0x0041, all -> 0x003c }
        r2 = "BranchSDK";
        r3 = new java.lang.StringBuilder;	 Catch:{ SocketException -> 0x0052, UnknownHostException -> 0x004d, SocketTimeoutException -> 0x0046, Exception -> 0x0041, all -> 0x003c }
        r3.<init>();	 Catch:{ SocketException -> 0x0052, UnknownHostException -> 0x004d, SocketTimeoutException -> 0x0046, Exception -> 0x0041, all -> 0x003c }
        r4 = "Post value = ";
        r3.append(r4);	 Catch:{ SocketException -> 0x0052, UnknownHostException -> 0x004d, SocketTimeoutException -> 0x0046, Exception -> 0x0041, all -> 0x003c }
        r4 = 4;
        r4 = r13.toString(r4);	 Catch:{ SocketException -> 0x0052, UnknownHostException -> 0x004d, SocketTimeoutException -> 0x0046, Exception -> 0x0041, all -> 0x003c }
        r3.append(r4);	 Catch:{ SocketException -> 0x0052, UnknownHostException -> 0x004d, SocketTimeoutException -> 0x0046, Exception -> 0x0041, all -> 0x003c }
        r3 = r3.toString();	 Catch:{ SocketException -> 0x0052, UnknownHostException -> 0x004d, SocketTimeoutException -> 0x0046, Exception -> 0x0041, all -> 0x003c }
        io.branch.referral.m.c(r2, r3);	 Catch:{ SocketException -> 0x0052, UnknownHostException -> 0x004d, SocketTimeoutException -> 0x0046, Exception -> 0x0041, all -> 0x003c }
    L_0x00ca:
        r2 = new java.net.URL;	 Catch:{ SocketException -> 0x02d3, UnknownHostException -> 0x03fd, SocketTimeoutException -> 0x0357, Exception -> 0x02e1, all -> 0x02db }
        r2.<init>(r9);	 Catch:{ SocketException -> 0x02d3, UnknownHostException -> 0x03fd, SocketTimeoutException -> 0x0357, Exception -> 0x02e1, all -> 0x02db }
        r2 = r2.openConnection();	 Catch:{ SocketException -> 0x02d3, UnknownHostException -> 0x03fd, SocketTimeoutException -> 0x0357, Exception -> 0x02e1, all -> 0x02db }
        r7 = r2;
        r7 = (javax.net.ssl.HttpsURLConnection) r7;	 Catch:{ SocketException -> 0x02d3, UnknownHostException -> 0x03fd, SocketTimeoutException -> 0x0357, Exception -> 0x02e1, all -> 0x02db }
        r7.setConnectTimeout(r12);	 Catch:{ SocketException -> 0x02ce, UnknownHostException -> 0x02c8, SocketTimeoutException -> 0x02c2, Exception -> 0x02bd, all -> 0x02b9 }
        r7.setReadTimeout(r12);	 Catch:{ SocketException -> 0x02ce, UnknownHostException -> 0x02c8, SocketTimeoutException -> 0x02c2, Exception -> 0x02bd, all -> 0x02b9 }
        r7.setDoInput(r5);	 Catch:{ SocketException -> 0x02ce, UnknownHostException -> 0x02c8, SocketTimeoutException -> 0x02c2, Exception -> 0x02bd, all -> 0x02b9 }
        r7.setDoOutput(r5);	 Catch:{ SocketException -> 0x02ce, UnknownHostException -> 0x02c8, SocketTimeoutException -> 0x02c2, Exception -> 0x02bd, all -> 0x02b9 }
        r2 = "Content-Type";
        r3 = "application/json";
        r7.setRequestProperty(r2, r3);	 Catch:{ SocketException -> 0x02ce, UnknownHostException -> 0x02c8, SocketTimeoutException -> 0x02c2, Exception -> 0x02bd, all -> 0x02b9 }
        r2 = "Accept";
        r3 = "application/json";
        r7.setRequestProperty(r2, r3);	 Catch:{ SocketException -> 0x02ce, UnknownHostException -> 0x02c8, SocketTimeoutException -> 0x02c2, Exception -> 0x02bd, all -> 0x02b9 }
        r2 = "POST";
        r7.setRequestMethod(r2);	 Catch:{ SocketException -> 0x02ce, UnknownHostException -> 0x02c8, SocketTimeoutException -> 0x02c2, Exception -> 0x02bd, all -> 0x02b9 }
        r2 = new java.io.OutputStreamWriter;	 Catch:{ SocketException -> 0x02ce, UnknownHostException -> 0x02c8, SocketTimeoutException -> 0x02c2, Exception -> 0x02bd, all -> 0x02b9 }
        r3 = r7.getOutputStream();	 Catch:{ SocketException -> 0x02ce, UnknownHostException -> 0x02c8, SocketTimeoutException -> 0x02c2, Exception -> 0x02bd, all -> 0x02b9 }
        r2.<init>(r3);	 Catch:{ SocketException -> 0x02ce, UnknownHostException -> 0x02c8, SocketTimeoutException -> 0x02c2, Exception -> 0x02bd, all -> 0x02b9 }
        r3 = java.lang.System.currentTimeMillis();	 Catch:{ SocketException -> 0x02ce, UnknownHostException -> 0x02c8, SocketTimeoutException -> 0x02c2, Exception -> 0x02bd, all -> 0x02b9 }
        r5 = r3 - r14;
        r3 = (int) r5;
        r4 = io.branch.referral.Branch.a();	 Catch:{ SocketException -> 0x02ce, UnknownHostException -> 0x02c8, SocketTimeoutException -> 0x02b3, Exception -> 0x02bd, all -> 0x02b9 }
        if (r4 == 0) goto L_0x013d;
    L_0x010b:
        r4 = io.branch.referral.Branch.a();	 Catch:{ SocketException -> 0x013a, UnknownHostException -> 0x0137, SocketTimeoutException -> 0x02b3, Exception -> 0x0134, all -> 0x0131 }
        r5 = new java.lang.StringBuilder;	 Catch:{ SocketException -> 0x013a, UnknownHostException -> 0x0137, SocketTimeoutException -> 0x02b3, Exception -> 0x0134, all -> 0x0131 }
        r5.<init>();	 Catch:{ SocketException -> 0x013a, UnknownHostException -> 0x0137, SocketTimeoutException -> 0x02b3, Exception -> 0x0134, all -> 0x0131 }
        r5.append(r10);	 Catch:{ SocketException -> 0x013a, UnknownHostException -> 0x0137, SocketTimeoutException -> 0x02b3, Exception -> 0x0134, all -> 0x0131 }
        r6 = "-";
        r5.append(r6);	 Catch:{ SocketException -> 0x013a, UnknownHostException -> 0x0137, SocketTimeoutException -> 0x02b3, Exception -> 0x0134, all -> 0x0131 }
        r6 = io.branch.referral.Defines.Jsonkey.Last_Round_Trip_Time;	 Catch:{ SocketException -> 0x013a, UnknownHostException -> 0x0137, SocketTimeoutException -> 0x02b3, Exception -> 0x0134, all -> 0x0131 }
        r6 = r6.getKey();	 Catch:{ SocketException -> 0x013a, UnknownHostException -> 0x0137, SocketTimeoutException -> 0x02b3, Exception -> 0x0134, all -> 0x0131 }
        r5.append(r6);	 Catch:{ SocketException -> 0x013a, UnknownHostException -> 0x0137, SocketTimeoutException -> 0x02b3, Exception -> 0x0134, all -> 0x0131 }
        r5 = r5.toString();	 Catch:{ SocketException -> 0x013a, UnknownHostException -> 0x0137, SocketTimeoutException -> 0x02b3, Exception -> 0x0134, all -> 0x0131 }
        r3 = java.lang.String.valueOf(r3);	 Catch:{ SocketException -> 0x013a, UnknownHostException -> 0x0137, SocketTimeoutException -> 0x02b3, Exception -> 0x0134, all -> 0x0131 }
        r4.a(r5, r3);	 Catch:{ SocketException -> 0x013a, UnknownHostException -> 0x0137, SocketTimeoutException -> 0x02b3, Exception -> 0x0134, all -> 0x0131 }
        goto L_0x013d;
    L_0x0131:
        r0 = move-exception;
        goto L_0x02dd;
    L_0x0134:
        r0 = move-exception;
        goto L_0x02e3;
    L_0x0137:
        r0 = move-exception;
        goto L_0x03ff;
    L_0x013a:
        r0 = move-exception;
        goto L_0x02d5;
    L_0x013d:
        r3 = r13.toString();	 Catch:{ SocketException -> 0x02ce, UnknownHostException -> 0x02c8, SocketTimeoutException -> 0x02b3, Exception -> 0x02bd, all -> 0x02b9 }
        r2.write(r3);	 Catch:{ SocketException -> 0x02ce, UnknownHostException -> 0x02c8, SocketTimeoutException -> 0x02b3, Exception -> 0x02bd, all -> 0x02b9 }
        r2.flush();	 Catch:{ SocketException -> 0x02ce, UnknownHostException -> 0x02c8, SocketTimeoutException -> 0x02b3, Exception -> 0x02bd, all -> 0x02b9 }
        r2 = r7.getResponseCode();	 Catch:{ SocketException -> 0x02ce, UnknownHostException -> 0x02c8, SocketTimeoutException -> 0x02b3, Exception -> 0x02bd, all -> 0x02b9 }
        r6 = 500; // 0x1f4 float:7.0E-43 double:2.47E-321;
        if (r2 < r6) goto L_0x01b7;
    L_0x014f:
        r2 = r8.a;	 Catch:{ SocketException -> 0x02ce, UnknownHostException -> 0x02c8, SocketTimeoutException -> 0x02b3, Exception -> 0x02bd, all -> 0x02b9 }
        r2 = r2.c();	 Catch:{ SocketException -> 0x02ce, UnknownHostException -> 0x02c8, SocketTimeoutException -> 0x02b3, Exception -> 0x02bd, all -> 0x02b9 }
        if (r1 >= r2) goto L_0x01b7;
    L_0x0157:
        r2 = r8.a;	 Catch:{ InterruptedException -> 0x0162 }
        r2 = r2.d();	 Catch:{ InterruptedException -> 0x0162 }
        r2 = (long) r2;	 Catch:{ InterruptedException -> 0x0162 }
        java.lang.Thread.sleep(r2);	 Catch:{ InterruptedException -> 0x0162 }
        goto L_0x0167;
    L_0x0162:
        r0 = move-exception;
        r2 = r0;
        com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r2);	 Catch:{ SocketException -> 0x02ce, UnknownHostException -> 0x02c8, SocketTimeoutException -> 0x02b3, Exception -> 0x02bd, all -> 0x02b9 }
    L_0x0167:
        r16 = r1 + 1;
        r1 = r8;
        r2 = r13;
        r3 = r9;
        r4 = r10;
        r17 = 1;
        r5 = r12;
        r18 = r12;
        r12 = r6;
        r6 = r16;
        r12 = r7;
        r7 = r11;
        r1 = r1.a(r2, r3, r4, r5, r6, r7);	 Catch:{ SocketException -> 0x0257, UnknownHostException -> 0x0254, SocketTimeoutException -> 0x01b3, Exception -> 0x0252 }
        r2 = io.branch.referral.Branch.a();
        if (r2 == 0) goto L_0x01ad;
    L_0x0181:
        r2 = java.lang.System.currentTimeMillis();
        r4 = r2 - r14;
        r2 = (int) r4;
        r3 = io.branch.referral.Branch.a();
        r4 = new java.lang.StringBuilder;
        r4.<init>();
        r4.append(r10);
        r5 = "-";
        r4.append(r5);
        r5 = io.branch.referral.Defines.Jsonkey.Branch_Round_Trip_Time;
        r5 = r5.getKey();
        r4.append(r5);
        r4 = r4.toString();
        r2 = java.lang.String.valueOf(r2);
        r3.a(r4, r2);
    L_0x01ad:
        if (r12 == 0) goto L_0x01b2;
    L_0x01af:
        r12.disconnect();
    L_0x01b2:
        return r1;
    L_0x01b3:
        r1 = r16;
        goto L_0x035d;
    L_0x01b7:
        r18 = r12;
        r17 = 1;
        r12 = r7;
        r2 = r12.getResponseCode();	 Catch:{ FileNotFoundException -> 0x025a }
        r3 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        if (r2 == r3) goto L_0x020e;
    L_0x01c4:
        r2 = r12.getErrorStream();	 Catch:{ FileNotFoundException -> 0x025a }
        if (r2 == 0) goto L_0x020e;
    L_0x01ca:
        r2 = r12.getErrorStream();	 Catch:{ FileNotFoundException -> 0x025a }
        r3 = r12.getResponseCode();	 Catch:{ FileNotFoundException -> 0x025a }
        r2 = r8.a(r2, r3, r10, r11);	 Catch:{ FileNotFoundException -> 0x025a }
        r1 = io.branch.referral.Branch.a();
        if (r1 == 0) goto L_0x0208;
    L_0x01dc:
        r3 = java.lang.System.currentTimeMillis();
        r5 = r3 - r14;
        r1 = (int) r5;
        r3 = io.branch.referral.Branch.a();
        r4 = new java.lang.StringBuilder;
        r4.<init>();
        r4.append(r10);
        r5 = "-";
        r4.append(r5);
        r5 = io.branch.referral.Defines.Jsonkey.Branch_Round_Trip_Time;
        r5 = r5.getKey();
        r4.append(r5);
        r4 = r4.toString();
        r1 = java.lang.String.valueOf(r1);
        r3.a(r4, r1);
    L_0x0208:
        if (r12 == 0) goto L_0x020d;
    L_0x020a:
        r12.disconnect();
    L_0x020d:
        return r2;
    L_0x020e:
        r2 = r12.getInputStream();	 Catch:{ FileNotFoundException -> 0x025a }
        r3 = r12.getResponseCode();	 Catch:{ FileNotFoundException -> 0x025a }
        r2 = r8.a(r2, r3, r10, r11);	 Catch:{ FileNotFoundException -> 0x025a }
        r1 = io.branch.referral.Branch.a();
        if (r1 == 0) goto L_0x024c;
    L_0x0220:
        r3 = java.lang.System.currentTimeMillis();
        r5 = r3 - r14;
        r1 = (int) r5;
        r3 = io.branch.referral.Branch.a();
        r4 = new java.lang.StringBuilder;
        r4.<init>();
        r4.append(r10);
        r5 = "-";
        r4.append(r5);
        r5 = io.branch.referral.Defines.Jsonkey.Branch_Round_Trip_Time;
        r5 = r5.getKey();
        r4.append(r5);
        r4 = r4.toString();
        r1 = java.lang.String.valueOf(r1);
        r3.a(r4, r1);
    L_0x024c:
        if (r12 == 0) goto L_0x0251;
    L_0x024e:
        r12.disconnect();
    L_0x0251:
        return r2;
    L_0x0252:
        r0 = move-exception;
        goto L_0x02bf;
    L_0x0254:
        r0 = move-exception;
        goto L_0x02ca;
    L_0x0257:
        r0 = move-exception;
        goto L_0x02d0;
    L_0x025a:
        if (r11 == 0) goto L_0x0272;
    L_0x025c:
        r2 = "BranchSDK";
        r3 = new java.lang.StringBuilder;	 Catch:{ SocketException -> 0x0257, UnknownHostException -> 0x0254, SocketTimeoutException -> 0x035d, Exception -> 0x0252 }
        r3.<init>();	 Catch:{ SocketException -> 0x0257, UnknownHostException -> 0x0254, SocketTimeoutException -> 0x035d, Exception -> 0x0252 }
        r4 = "A resource conflict occurred with this request ";
        r3.append(r4);	 Catch:{ SocketException -> 0x0257, UnknownHostException -> 0x0254, SocketTimeoutException -> 0x035d, Exception -> 0x0252 }
        r3.append(r10);	 Catch:{ SocketException -> 0x0257, UnknownHostException -> 0x0254, SocketTimeoutException -> 0x035d, Exception -> 0x0252 }
        r3 = r3.toString();	 Catch:{ SocketException -> 0x0257, UnknownHostException -> 0x0254, SocketTimeoutException -> 0x035d, Exception -> 0x0252 }
        io.branch.referral.m.c(r2, r3);	 Catch:{ SocketException -> 0x0257, UnknownHostException -> 0x0254, SocketTimeoutException -> 0x035d, Exception -> 0x0252 }
    L_0x0272:
        r2 = r12.getResponseCode();	 Catch:{ SocketException -> 0x0257, UnknownHostException -> 0x0254, SocketTimeoutException -> 0x035d, Exception -> 0x0252 }
        r7 = 0;
        r2 = r8.a(r7, r2, r10, r11);	 Catch:{ SocketException -> 0x0257, UnknownHostException -> 0x0254, SocketTimeoutException -> 0x035d, Exception -> 0x0252 }
        r1 = io.branch.referral.Branch.a();
        if (r1 == 0) goto L_0x02ad;
    L_0x0281:
        r3 = java.lang.System.currentTimeMillis();
        r5 = r3 - r14;
        r1 = (int) r5;
        r3 = io.branch.referral.Branch.a();
        r4 = new java.lang.StringBuilder;
        r4.<init>();
        r4.append(r10);
        r5 = "-";
        r4.append(r5);
        r5 = io.branch.referral.Defines.Jsonkey.Branch_Round_Trip_Time;
        r5 = r5.getKey();
        r4.append(r5);
        r4 = r4.toString();
        r1 = java.lang.String.valueOf(r1);
        r3.a(r4, r1);
    L_0x02ad:
        if (r12 == 0) goto L_0x02b2;
    L_0x02af:
        r12.disconnect();
    L_0x02b2:
        return r2;
    L_0x02b3:
        r18 = r12;
        r17 = 1;
        goto L_0x035c;
    L_0x02b9:
        r0 = move-exception;
        r12 = r7;
        goto L_0x03fa;
    L_0x02bd:
        r0 = move-exception;
        r12 = r7;
    L_0x02bf:
        r1 = r0;
        r2 = r12;
        goto L_0x02e5;
    L_0x02c2:
        r17 = r5;
        r18 = r12;
        goto L_0x035c;
    L_0x02c8:
        r0 = move-exception;
        r12 = r7;
    L_0x02ca:
        r1 = r0;
        r2 = r12;
        goto L_0x0401;
    L_0x02ce:
        r0 = move-exception;
        r12 = r7;
    L_0x02d0:
        r1 = r0;
        r2 = r12;
        goto L_0x02d7;
    L_0x02d3:
        r0 = move-exception;
        r7 = 0;
    L_0x02d5:
        r1 = r0;
        r2 = r7;
    L_0x02d7:
        r3 = -1009; // 0xfffffffffffffc0f float:NaN double:NaN;
        goto L_0x0468;
    L_0x02db:
        r0 = move-exception;
        r7 = 0;
    L_0x02dd:
        r1 = r0;
        r12 = r7;
        goto L_0x04cc;
    L_0x02e1:
        r0 = move-exception;
        r7 = 0;
    L_0x02e3:
        r1 = r0;
        r2 = r7;
    L_0x02e5:
        if (r11 == 0) goto L_0x0307;
    L_0x02e7:
        r3 = r20.getClass();	 Catch:{ all -> 0x048b }
        r3 = r3.getSimpleName();	 Catch:{ all -> 0x048b }
        r4 = new java.lang.StringBuilder;	 Catch:{ all -> 0x048b }
        r4.<init>();	 Catch:{ all -> 0x048b }
        r5 = "Exception: ";
        r4.append(r5);	 Catch:{ all -> 0x048b }
        r5 = r1.getMessage();	 Catch:{ all -> 0x048b }
        r4.append(r5);	 Catch:{ all -> 0x048b }
        r4 = r4.toString();	 Catch:{ all -> 0x048b }
        io.branch.referral.m.c(r3, r4);	 Catch:{ all -> 0x048b }
    L_0x0307:
        r3 = android.os.Build.VERSION.SDK_INT;	 Catch:{ all -> 0x048b }
        r4 = 11;
        if (r3 < r4) goto L_0x0318;
    L_0x030d:
        r1 = r1 instanceof android.os.NetworkOnMainThreadException;	 Catch:{ all -> 0x048b }
        if (r1 == 0) goto L_0x0318;
    L_0x0311:
        r1 = "BranchSDK";
        r3 = "Branch Error: Don't call our synchronous methods on the main thread!!!";
        android.util.Log.i(r1, r3);	 Catch:{ all -> 0x048b }
    L_0x0318:
        r1 = new io.branch.referral.af;	 Catch:{ all -> 0x048b }
        r3 = 500; // 0x1f4 float:7.0E-43 double:2.47E-321;
        r1.<init>(r10, r3);	 Catch:{ all -> 0x048b }
        r3 = io.branch.referral.Branch.a();
        if (r3 == 0) goto L_0x0351;
    L_0x0325:
        r3 = java.lang.System.currentTimeMillis();
        r5 = r3 - r14;
        r3 = (int) r5;
        r4 = io.branch.referral.Branch.a();
        r5 = new java.lang.StringBuilder;
        r5.<init>();
        r5.append(r10);
        r6 = "-";
        r5.append(r6);
        r6 = io.branch.referral.Defines.Jsonkey.Branch_Round_Trip_Time;
        r6 = r6.getKey();
        r5.append(r6);
        r5 = r5.toString();
        r3 = java.lang.String.valueOf(r3);
        r4.a(r5, r3);
    L_0x0351:
        if (r2 == 0) goto L_0x0356;
    L_0x0353:
        r2.disconnect();
    L_0x0356:
        return r1;
    L_0x0357:
        r17 = r5;
        r18 = r12;
        r7 = 0;
    L_0x035c:
        r12 = r7;
    L_0x035d:
        r2 = r8.a;	 Catch:{ all -> 0x03f9 }
        r2 = r2.c();	 Catch:{ all -> 0x03f9 }
        if (r1 >= r2) goto L_0x03ba;
    L_0x0365:
        r2 = r8.a;	 Catch:{ InterruptedException -> 0x0370 }
        r2 = r2.d();	 Catch:{ InterruptedException -> 0x0370 }
        r2 = (long) r2;	 Catch:{ InterruptedException -> 0x0370 }
        java.lang.Thread.sleep(r2);	 Catch:{ InterruptedException -> 0x0370 }
        goto L_0x0375;
    L_0x0370:
        r0 = move-exception;
        r2 = r0;
        com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r2);	 Catch:{ all -> 0x03f9 }
    L_0x0375:
        r6 = r1 + 1;
        r1 = r8;
        r2 = r13;
        r3 = r9;
        r4 = r10;
        r5 = r18;
        r7 = r11;
        r1 = r1.a(r2, r3, r4, r5, r6, r7);	 Catch:{ all -> 0x03f9 }
        r2 = io.branch.referral.Branch.a();
        if (r2 == 0) goto L_0x03b4;
    L_0x0388:
        r2 = java.lang.System.currentTimeMillis();
        r4 = r2 - r14;
        r2 = (int) r4;
        r3 = io.branch.referral.Branch.a();
        r4 = new java.lang.StringBuilder;
        r4.<init>();
        r4.append(r10);
        r5 = "-";
        r4.append(r5);
        r5 = io.branch.referral.Defines.Jsonkey.Branch_Round_Trip_Time;
        r5 = r5.getKey();
        r4.append(r5);
        r4 = r4.toString();
        r2 = java.lang.String.valueOf(r2);
        r3.a(r4, r2);
    L_0x03b4:
        if (r12 == 0) goto L_0x03b9;
    L_0x03b6:
        r12.disconnect();
    L_0x03b9:
        return r1;
    L_0x03ba:
        r1 = new io.branch.referral.af;	 Catch:{ all -> 0x03f9 }
        r2 = -111; // 0xffffffffffffff91 float:NaN double:NaN;
        r1.<init>(r10, r2);	 Catch:{ all -> 0x03f9 }
        r2 = io.branch.referral.Branch.a();
        if (r2 == 0) goto L_0x03f3;
    L_0x03c7:
        r2 = java.lang.System.currentTimeMillis();
        r4 = r2 - r14;
        r2 = (int) r4;
        r3 = io.branch.referral.Branch.a();
        r4 = new java.lang.StringBuilder;
        r4.<init>();
        r4.append(r10);
        r5 = "-";
        r4.append(r5);
        r5 = io.branch.referral.Defines.Jsonkey.Branch_Round_Trip_Time;
        r5 = r5.getKey();
        r4.append(r5);
        r4 = r4.toString();
        r2 = java.lang.String.valueOf(r2);
        r3.a(r4, r2);
    L_0x03f3:
        if (r12 == 0) goto L_0x03f8;
    L_0x03f5:
        r12.disconnect();
    L_0x03f8:
        return r1;
    L_0x03f9:
        r0 = move-exception;
    L_0x03fa:
        r1 = r0;
        goto L_0x04cc;
    L_0x03fd:
        r0 = move-exception;
        r7 = 0;
    L_0x03ff:
        r1 = r0;
        r2 = r7;
    L_0x0401:
        if (r11 == 0) goto L_0x0423;
    L_0x0403:
        r3 = r20.getClass();	 Catch:{ all -> 0x048b }
        r3 = r3.getSimpleName();	 Catch:{ all -> 0x048b }
        r4 = new java.lang.StringBuilder;	 Catch:{ all -> 0x048b }
        r4.<init>();	 Catch:{ all -> 0x048b }
        r5 = "Http connect exception: ";
        r4.append(r5);	 Catch:{ all -> 0x048b }
        r1 = r1.getMessage();	 Catch:{ all -> 0x048b }
        r4.append(r1);	 Catch:{ all -> 0x048b }
        r1 = r4.toString();	 Catch:{ all -> 0x048b }
        io.branch.referral.m.c(r3, r1);	 Catch:{ all -> 0x048b }
    L_0x0423:
        r1 = new io.branch.referral.af;	 Catch:{ all -> 0x048b }
        r3 = -1009; // 0xfffffffffffffc0f float:NaN double:NaN;
        r1.<init>(r10, r3);	 Catch:{ all -> 0x048b }
        r3 = io.branch.referral.Branch.a();
        if (r3 == 0) goto L_0x045c;
    L_0x0430:
        r3 = java.lang.System.currentTimeMillis();
        r5 = r3 - r14;
        r3 = (int) r5;
        r4 = io.branch.referral.Branch.a();
        r5 = new java.lang.StringBuilder;
        r5.<init>();
        r5.append(r10);
        r6 = "-";
        r5.append(r6);
        r6 = io.branch.referral.Defines.Jsonkey.Branch_Round_Trip_Time;
        r6 = r6.getKey();
        r5.append(r6);
        r5 = r5.toString();
        r3 = java.lang.String.valueOf(r3);
        r4.a(r5, r3);
    L_0x045c:
        if (r2 == 0) goto L_0x0461;
    L_0x045e:
        r2.disconnect();
    L_0x0461:
        return r1;
    L_0x0462:
        r0 = move-exception;
        r3 = -1009; // 0xfffffffffffffc0f float:NaN double:NaN;
        r7 = 0;
        r1 = r0;
        r2 = r7;
    L_0x0468:
        if (r11 == 0) goto L_0x048f;
    L_0x046a:
        r4 = r20.getClass();	 Catch:{ all -> 0x048b }
        r4 = r4.getSimpleName();	 Catch:{ all -> 0x048b }
        r5 = new java.lang.StringBuilder;	 Catch:{ all -> 0x048b }
        r5.<init>();	 Catch:{ all -> 0x048b }
        r6 = "Http connect exception: ";
        r5.append(r6);	 Catch:{ all -> 0x048b }
        r1 = r1.getMessage();	 Catch:{ all -> 0x048b }
        r5.append(r1);	 Catch:{ all -> 0x048b }
        r1 = r5.toString();	 Catch:{ all -> 0x048b }
        io.branch.referral.m.c(r4, r1);	 Catch:{ all -> 0x048b }
        goto L_0x048f;
    L_0x048b:
        r0 = move-exception;
        r1 = r0;
        r12 = r2;
        goto L_0x04cc;
    L_0x048f:
        r1 = new io.branch.referral.af;	 Catch:{ all -> 0x048b }
        r1.<init>(r10, r3);	 Catch:{ all -> 0x048b }
        r3 = io.branch.referral.Branch.a();
        if (r3 == 0) goto L_0x04c6;
    L_0x049a:
        r3 = java.lang.System.currentTimeMillis();
        r5 = r3 - r14;
        r3 = (int) r5;
        r4 = io.branch.referral.Branch.a();
        r5 = new java.lang.StringBuilder;
        r5.<init>();
        r5.append(r10);
        r6 = "-";
        r5.append(r6);
        r6 = io.branch.referral.Defines.Jsonkey.Branch_Round_Trip_Time;
        r6 = r6.getKey();
        r5.append(r6);
        r5 = r5.toString();
        r3 = java.lang.String.valueOf(r3);
        r4.a(r5, r3);
    L_0x04c6:
        if (r2 == 0) goto L_0x04cb;
    L_0x04c8:
        r2.disconnect();
    L_0x04cb:
        return r1;
    L_0x04cc:
        r2 = io.branch.referral.Branch.a();
        if (r2 == 0) goto L_0x04fe;
    L_0x04d2:
        r2 = java.lang.System.currentTimeMillis();
        r4 = r2 - r14;
        r2 = (int) r4;
        r3 = io.branch.referral.Branch.a();
        r4 = new java.lang.StringBuilder;
        r4.<init>();
        r4.append(r10);
        r5 = "-";
        r4.append(r5);
        r5 = io.branch.referral.Defines.Jsonkey.Branch_Round_Trip_Time;
        r5 = r5.getKey();
        r4.append(r5);
        r4 = r4.toString();
        r2 = java.lang.String.valueOf(r2);
        r3.a(r4, r2);
    L_0x04fe:
        if (r12 == 0) goto L_0x0503;
    L_0x0500:
        r12.disconnect();
    L_0x0503:
        throw r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: io.branch.referral.n.a(org.json.JSONObject, java.lang.String, java.lang.String, int, int, boolean):io.branch.referral.af");
    }

    private String a(JSONObject jSONObject) {
        StringBuilder stringBuilder = new StringBuilder();
        if (jSONObject != null) {
            JSONArray names = jSONObject.names();
            if (names != null) {
                int length = names.length();
                Object obj = 1;
                int i = 0;
                while (i < length) {
                    try {
                        String string = names.getString(i);
                        if (obj != null) {
                            stringBuilder.append("?");
                            obj = null;
                        } else {
                            stringBuilder.append("&");
                        }
                        String string2 = jSONObject.getString(string);
                        stringBuilder.append(string);
                        stringBuilder.append("=");
                        stringBuilder.append(string2);
                        i++;
                    } catch (JSONException e) {
                        ThrowableExtension.printStackTrace(e);
                        return null;
                    }
                }
            }
        }
        return stringBuilder.toString();
    }
}
