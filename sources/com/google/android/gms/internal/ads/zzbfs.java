package com.google.android.gms.internal.ads;

import java.io.File;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@zzark
public final class zzbfs extends zzbfk {
    private static final Set<String> zzewz = Collections.synchronizedSet(new HashSet());
    private static final DecimalFormat zzexa = new DecimalFormat("#,###");
    private File zzexb;
    private boolean zzexc;

    public zzbfs(zzbdz zzbdz) {
        super(zzbdz);
        File cacheDir = this.mContext.getCacheDir();
        if (cacheDir == null) {
            zzbbd.zzeo("Context.getCacheDir() returned null");
            return;
        }
        this.zzexb = new File(cacheDir, "admobVideoStreams");
        String str;
        String valueOf;
        if (!this.zzexb.isDirectory() && !this.zzexb.mkdirs()) {
            str = "Could not create preload cache directory at ";
            valueOf = String.valueOf(this.zzexb.getAbsolutePath());
            zzbbd.zzeo(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
            this.zzexb = null;
        } else if (!this.zzexb.setReadable(true, false) || !this.zzexb.setExecutable(true, false)) {
            str = "Could not set cache file permissions at ";
            valueOf = String.valueOf(this.zzexb.getAbsolutePath());
            zzbbd.zzeo(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
            this.zzexb = null;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:274:0x008f A:{SYNTHETIC} */
    /* JADX WARNING: Removed duplicated region for block: B:250:0x0512  */
    /* JADX WARNING: Removed duplicated region for block: B:257:0x0547  */
    /* JADX WARNING: Removed duplicated region for block: B:256:0x0522  */
    /* JADX WARNING: Removed duplicated region for block: B:265:0x058c  */
    /* JADX WARNING: Removed duplicated region for block: B:264:0x0587  */
    /* JADX WARNING: Missing exception handler attribute for start block: B:224:0x04da */
    /* JADX WARNING: Removed duplicated region for block: B:250:0x0512  */
    /* JADX WARNING: Removed duplicated region for block: B:256:0x0522  */
    /* JADX WARNING: Removed duplicated region for block: B:257:0x0547  */
    /* JADX WARNING: Removed duplicated region for block: B:264:0x0587  */
    /* JADX WARNING: Removed duplicated region for block: B:265:0x058c  */
    /* JADX WARNING: Removed duplicated region for block: B:250:0x0512  */
    /* JADX WARNING: Removed duplicated region for block: B:257:0x0547  */
    /* JADX WARNING: Removed duplicated region for block: B:256:0x0522  */
    /* JADX WARNING: Removed duplicated region for block: B:265:0x058c  */
    /* JADX WARNING: Removed duplicated region for block: B:264:0x0587  */
    /* JADX WARNING: Removed duplicated region for block: B:250:0x0512  */
    /* JADX WARNING: Removed duplicated region for block: B:256:0x0522  */
    /* JADX WARNING: Removed duplicated region for block: B:257:0x0547  */
    /* JADX WARNING: Removed duplicated region for block: B:264:0x0587  */
    /* JADX WARNING: Removed duplicated region for block: B:265:0x058c  */
    /* JADX WARNING: Removed duplicated region for block: B:250:0x0512  */
    /* JADX WARNING: Removed duplicated region for block: B:257:0x0547  */
    /* JADX WARNING: Removed duplicated region for block: B:256:0x0522  */
    /* JADX WARNING: Removed duplicated region for block: B:265:0x058c  */
    /* JADX WARNING: Removed duplicated region for block: B:264:0x0587  */
    /* JADX WARNING: Removed duplicated region for block: B:250:0x0512  */
    /* JADX WARNING: Removed duplicated region for block: B:256:0x0522  */
    /* JADX WARNING: Removed duplicated region for block: B:257:0x0547  */
    /* JADX WARNING: Removed duplicated region for block: B:264:0x0587  */
    /* JADX WARNING: Removed duplicated region for block: B:265:0x058c  */
    /* JADX WARNING: Removed duplicated region for block: B:250:0x0512  */
    /* JADX WARNING: Removed duplicated region for block: B:257:0x0547  */
    /* JADX WARNING: Removed duplicated region for block: B:256:0x0522  */
    /* JADX WARNING: Removed duplicated region for block: B:265:0x058c  */
    /* JADX WARNING: Removed duplicated region for block: B:264:0x0587  */
    /* JADX WARNING: Removed duplicated region for block: B:250:0x0512  */
    /* JADX WARNING: Removed duplicated region for block: B:256:0x0522  */
    /* JADX WARNING: Removed duplicated region for block: B:257:0x0547  */
    /* JADX WARNING: Removed duplicated region for block: B:264:0x0587  */
    /* JADX WARNING: Removed duplicated region for block: B:265:0x058c  */
    /* JADX WARNING: Removed duplicated region for block: B:250:0x0512  */
    /* JADX WARNING: Removed duplicated region for block: B:257:0x0547  */
    /* JADX WARNING: Removed duplicated region for block: B:256:0x0522  */
    /* JADX WARNING: Removed duplicated region for block: B:265:0x058c  */
    /* JADX WARNING: Removed duplicated region for block: B:264:0x0587  */
    /* JADX WARNING: Removed duplicated region for block: B:250:0x0512  */
    /* JADX WARNING: Removed duplicated region for block: B:256:0x0522  */
    /* JADX WARNING: Removed duplicated region for block: B:257:0x0547  */
    /* JADX WARNING: Removed duplicated region for block: B:264:0x0587  */
    /* JADX WARNING: Removed duplicated region for block: B:265:0x058c  */
    /* JADX WARNING: Removed duplicated region for block: B:250:0x0512  */
    /* JADX WARNING: Removed duplicated region for block: B:257:0x0547  */
    /* JADX WARNING: Removed duplicated region for block: B:256:0x0522  */
    /* JADX WARNING: Removed duplicated region for block: B:265:0x058c  */
    /* JADX WARNING: Removed duplicated region for block: B:264:0x0587  */
    /* JADX WARNING: Removed duplicated region for block: B:250:0x0512  */
    /* JADX WARNING: Removed duplicated region for block: B:256:0x0522  */
    /* JADX WARNING: Removed duplicated region for block: B:257:0x0547  */
    /* JADX WARNING: Removed duplicated region for block: B:264:0x0587  */
    /* JADX WARNING: Removed duplicated region for block: B:265:0x058c  */
    /* JADX WARNING: Can't wrap try/catch for region: R(4:224|225|226|(4:227|228|229|230)) */
    /* JADX WARNING: Missing block: B:61:0x0133, code skipped:
            r16 = "error";
     */
    /* JADX WARNING: Missing block: B:63:?, code skipped:
            com.google.android.gms.ads.internal.zzbv.zzls();
            r1 = ((java.lang.Integer) com.google.android.gms.internal.ads.zzwu.zzpz().zzd(com.google.android.gms.internal.ads.zzaan.zzcoy)).intValue();
            r3 = new java.net.URL(r9);
            r2 = 0;
     */
    /* JADX WARNING: Missing block: B:64:0x014f, code skipped:
            r2 = r2 + 1;
     */
    /* JADX WARNING: Missing block: B:65:0x0152, code skipped:
            if (r2 > 20) goto L_0x04fc;
     */
    /* JADX WARNING: Missing block: B:66:0x0154, code skipped:
            r5 = r3.openConnection();
            r5.setConnectTimeout(r1);
            r5.setReadTimeout(r1);
     */
    /* JADX WARNING: Missing block: B:67:0x0160, code skipped:
            if ((r5 instanceof java.net.HttpURLConnection) != false) goto L_0x0172;
     */
    /* JADX WARNING: Missing block: B:70:0x0169, code skipped:
            throw new java.io.IOException("Invalid protocol.");
     */
    /* JADX WARNING: Missing block: B:71:0x016a, code skipped:
            r0 = move-exception;
     */
    /* JADX WARNING: Missing block: B:72:0x016b, code skipped:
            r1 = r0;
            r4 = null;
            r2 = r15;
            r3 = r16;
     */
    /* JADX WARNING: Missing block: B:74:?, code skipped:
            r5 = (java.net.HttpURLConnection) r5;
            r6 = new com.google.android.gms.internal.ads.zzbax();
            r6.zza(r5, null);
            r5.setInstanceFollowRedirects(false);
            r7 = r5.getResponseCode();
            r6.zza(r5, r7);
     */
    /* JADX WARNING: Missing block: B:76:0x0189, code skipped:
            if ((r7 / 100) != 3) goto L_0x01fb;
     */
    /* JADX WARNING: Missing block: B:78:?, code skipped:
            r4 = r5.getHeaderField(com.til.colombia.android.internal.e.e);
     */
    /* JADX WARNING: Missing block: B:79:0x0191, code skipped:
            if (r4 != null) goto L_0x019b;
     */
    /* JADX WARNING: Missing block: B:81:0x019a, code skipped:
            throw new java.io.IOException("Missing Location header in redirect");
     */
    /* JADX WARNING: Missing block: B:82:0x019b, code skipped:
            r6 = new java.net.URL(r3, r4);
            r3 = r6.getProtocol();
     */
    /* JADX WARNING: Missing block: B:83:0x01a4, code skipped:
            if (r3 != null) goto L_0x01ae;
     */
    /* JADX WARNING: Missing block: B:85:0x01ad, code skipped:
            throw new java.io.IOException("Protocol is null");
     */
    /* JADX WARNING: Missing block: B:87:0x01b4, code skipped:
            if (r3.equals("http") != false) goto L_0x01db;
     */
    /* JADX WARNING: Missing block: B:89:0x01bc, code skipped:
            if (r3.equals("https") != false) goto L_0x01db;
     */
    /* JADX WARNING: Missing block: B:90:0x01be, code skipped:
            r2 = "Unsupported scheme: ";
            r3 = java.lang.String.valueOf(r3);
     */
    /* JADX WARNING: Missing block: B:91:0x01ca, code skipped:
            if (r3.length() == 0) goto L_0x01d1;
     */
    /* JADX WARNING: Missing block: B:92:0x01cc, code skipped:
            r2 = r2.concat(r3);
     */
    /* JADX WARNING: Missing block: B:93:0x01d1, code skipped:
            r2 = new java.lang.String(r2);
     */
    /* JADX WARNING: Missing block: B:95:0x01da, code skipped:
            throw new java.io.IOException(r2);
     */
    /* JADX WARNING: Missing block: B:96:0x01db, code skipped:
            r3 = "Redirecting to ";
            r4 = java.lang.String.valueOf(r4);
     */
    /* JADX WARNING: Missing block: B:97:0x01e5, code skipped:
            if (r4.length() == 0) goto L_0x01ec;
     */
    /* JADX WARNING: Missing block: B:98:0x01e7, code skipped:
            r3 = r3.concat(r4);
     */
    /* JADX WARNING: Missing block: B:99:0x01ec, code skipped:
            r3 = new java.lang.String(r3);
     */
    /* JADX WARNING: Missing block: B:100:0x01f2, code skipped:
            com.google.android.gms.internal.ads.zzbbd.zzdn(r3);
            r5.disconnect();
     */
    /* JADX WARNING: Missing block: B:101:0x01f8, code skipped:
            r3 = r6;
     */
    /* JADX WARNING: Missing block: B:104:0x01fd, code skipped:
            if ((r5 instanceof java.net.HttpURLConnection) == false) goto L_0x025d;
     */
    /* JADX WARNING: Missing block: B:106:?, code skipped:
            r1 = r5.getResponseCode();
     */
    /* JADX WARNING: Missing block: B:107:0x0208, code skipped:
            if (r1 < 400) goto L_0x025d;
     */
    /* JADX WARNING: Missing block: B:108:0x020a, code skipped:
            r2 = "badUrl";
     */
    /* JADX WARNING: Missing block: B:110:?, code skipped:
            r3 = "HTTP request failed. Code: ";
            r4 = java.lang.String.valueOf(java.lang.Integer.toString(r1));
     */
    /* JADX WARNING: Missing block: B:111:0x021a, code skipped:
            if (r4.length() == 0) goto L_0x0221;
     */
    /* JADX WARNING: Missing block: B:112:0x021c, code skipped:
            r3 = r3.concat(r4);
     */
    /* JADX WARNING: Missing block: B:114:0x0226, code skipped:
            r3 = new java.lang.String(r3);
     */
    /* JADX WARNING: Missing block: B:116:?, code skipped:
            r6 = new java.lang.StringBuilder(32 + java.lang.String.valueOf(r34).length());
            r6.append("HTTP status code ");
            r6.append(r1);
            r6.append(" at ");
            r6.append(r9);
     */
    /* JADX WARNING: Missing block: B:117:0x0250, code skipped:
            throw new java.io.IOException(r6.toString());
     */
    /* JADX WARNING: Missing block: B:118:0x0251, code skipped:
            r0 = move-exception;
     */
    /* JADX WARNING: Missing block: B:119:0x0252, code skipped:
            r1 = r0;
            r4 = r3;
            r3 = r2;
     */
    /* JADX WARNING: Missing block: B:120:0x0256, code skipped:
            r0 = move-exception;
     */
    /* JADX WARNING: Missing block: B:121:0x0257, code skipped:
            r1 = r0;
            r3 = r2;
            r4 = null;
     */
    /* JADX WARNING: Missing block: B:122:0x025a, code skipped:
            r2 = r15;
     */
    /* JADX WARNING: Missing block: B:124:?, code skipped:
            r7 = r5.getContentLength();
     */
    /* JADX WARNING: Missing block: B:125:0x0261, code skipped:
            if (r7 >= 0) goto L_0x028c;
     */
    /* JADX WARNING: Missing block: B:127:?, code skipped:
            r1 = "Stream cache aborted, missing content-length header at ";
            r2 = java.lang.String.valueOf(r34);
     */
    /* JADX WARNING: Missing block: B:128:0x026d, code skipped:
            if (r2.length() == 0) goto L_0x0274;
     */
    /* JADX WARNING: Missing block: B:129:0x026f, code skipped:
            r1 = r1.concat(r2);
     */
    /* JADX WARNING: Missing block: B:130:0x0274, code skipped:
            r1 = new java.lang.String(r1);
     */
    /* JADX WARNING: Missing block: B:131:0x027a, code skipped:
            com.google.android.gms.internal.ads.zzbbd.zzeo(r1);
            zza(r9, r12.getAbsolutePath(), "contentLengthMissing", null);
            zzewz.remove(r15);
     */
    /* JADX WARNING: Missing block: B:132:0x028b, code skipped:
            return false;
     */
    /* JADX WARNING: Missing block: B:134:?, code skipped:
            r1 = zzexa.format((long) r7);
            r3 = ((java.lang.Integer) com.google.android.gms.internal.ads.zzwu.zzpz().zzd(com.google.android.gms.internal.ads.zzaan.zzcou)).intValue();
     */
    /* JADX WARNING: Missing block: B:135:0x02a3, code skipped:
            if (r7 <= r3) goto L_0x02fa;
     */
    /* JADX WARNING: Missing block: B:138:?, code skipped:
            r3 = new java.lang.StringBuilder((33 + java.lang.String.valueOf(r1).length()) + java.lang.String.valueOf(r34).length());
            r3.append("Content length ");
            r3.append(r1);
            r3.append(" exceeds limit at ");
            r3.append(r9);
            com.google.android.gms.internal.ads.zzbbd.zzeo(r3.toString());
            r2 = "File too big for full file cache. Size: ";
            r1 = java.lang.String.valueOf(r1);
     */
    /* JADX WARNING: Missing block: B:139:0x02df, code skipped:
            if (r1.length() == 0) goto L_0x02e6;
     */
    /* JADX WARNING: Missing block: B:140:0x02e1, code skipped:
            r1 = r2.concat(r1);
     */
    /* JADX WARNING: Missing block: B:141:0x02e6, code skipped:
            r1 = new java.lang.String(r2);
     */
    /* JADX WARNING: Missing block: B:142:0x02eb, code skipped:
            zza(r9, r12.getAbsolutePath(), "sizeExceeded", r1);
            zzewz.remove(r15);
     */
    /* JADX WARNING: Missing block: B:143:0x02f9, code skipped:
            return false;
     */
    /* JADX WARNING: Missing block: B:145:?, code skipped:
            r2 = new java.lang.StringBuilder((20 + java.lang.String.valueOf(r1).length()) + java.lang.String.valueOf(r34).length());
            r2.append("Caching ");
            r2.append(r1);
            r2.append(" bytes from ");
            r2.append(r9);
            com.google.android.gms.internal.ads.zzbbd.zzdn(r2.toString());
            r5 = java.nio.channels.Channels.newChannel(r5.getInputStream());
            r4 = new java.io.FileOutputStream(r12);
     */
    /* JADX WARNING: Missing block: B:147:?, code skipped:
            r2 = r4.getChannel();
            r1 = java.nio.ByteBuffer.allocate(1048576);
            r10 = com.google.android.gms.ads.internal.zzbv.zzlm();
            r17 = r10.currentTimeMillis();
     */
    /* JADX WARNING: Missing block: B:148:0x0353, code skipped:
            r20 = r15;
     */
    /* JADX WARNING: Missing block: B:150:?, code skipped:
            r11 = new com.google.android.gms.internal.ads.zzbai(((java.lang.Long) com.google.android.gms.internal.ads.zzwu.zzpz().zzd(com.google.android.gms.internal.ads.zzaan.zzcox)).longValue());
            r14 = ((java.lang.Long) com.google.android.gms.internal.ads.zzwu.zzpz().zzd(com.google.android.gms.internal.ads.zzaan.zzcow)).longValue();
            r6 = 0;
     */
    /* JADX WARNING: Missing block: B:151:0x036f, code skipped:
            r21 = r5.read(r1);
     */
    /* JADX WARNING: Missing block: B:152:0x0373, code skipped:
            if (r21 < 0) goto L_0x047f;
     */
    /* JADX WARNING: Missing block: B:153:0x0375, code skipped:
            r6 = r6 + r21;
     */
    /* JADX WARNING: Missing block: B:154:0x0377, code skipped:
            if (r6 <= r3) goto L_0x03bd;
     */
    /* JADX WARNING: Missing block: B:156:?, code skipped:
            r1 = "sizeExceeded";
     */
    /* JADX WARNING: Missing block: B:158:?, code skipped:
            r2 = "File too big for full file cache. Size: ";
            r3 = java.lang.String.valueOf(java.lang.Integer.toString(r6));
     */
    /* JADX WARNING: Missing block: B:159:0x0389, code skipped:
            if (r3.length() == 0) goto L_0x0391;
     */
    /* JADX WARNING: Missing block: B:160:0x038b, code skipped:
            r10 = r2.concat(r3);
     */
    /* JADX WARNING: Missing block: B:162:0x0396, code skipped:
            r10 = new java.lang.String(r2);
     */
    /* JADX WARNING: Missing block: B:165:0x039e, code skipped:
            throw new java.io.IOException("stream cache file size limit exceeded");
     */
    /* JADX WARNING: Missing block: B:166:0x039f, code skipped:
            r0 = move-exception;
     */
    /* JADX WARNING: Missing block: B:167:0x03a0, code skipped:
            r3 = r1;
            r2 = r20;
            r1 = r0;
            r32 = r10;
            r10 = r4;
            r4 = r32;
     */
    /* JADX WARNING: Missing block: B:168:0x03ab, code skipped:
            r0 = move-exception;
     */
    /* JADX WARNING: Missing block: B:169:0x03ac, code skipped:
            r3 = r1;
            r10 = r4;
            r2 = r20;
            r4 = null;
            r1 = r0;
     */
    /* JADX WARNING: Missing block: B:170:0x03b4, code skipped:
            r0 = move-exception;
     */
    /* JADX WARNING: Missing block: B:171:0x03b5, code skipped:
            r1 = r0;
            r10 = r4;
     */
    /* JADX WARNING: Missing block: B:172:0x03b7, code skipped:
            r3 = r16;
            r2 = r20;
     */
    /* JADX WARNING: Missing block: B:174:?, code skipped:
            r1.flip();
     */
    /* JADX WARNING: Missing block: B:176:0x03c4, code skipped:
            if (r2.write(r1) > 0) goto L_0x0475;
     */
    /* JADX WARNING: Missing block: B:177:0x03c6, code skipped:
            r1.clear();
     */
    /* JADX WARNING: Missing block: B:179:0x03d5, code skipped:
            if ((r10.currentTimeMillis() - r17) <= (1000 * r14)) goto L_0x0406;
     */
    /* JADX WARNING: Missing block: B:181:?, code skipped:
            r1 = "downloadTimeout";
     */
    /* JADX WARNING: Missing block: B:183:?, code skipped:
            r2 = java.lang.Long.toString(r14);
            r5 = new java.lang.StringBuilder(29 + java.lang.String.valueOf(r2).length());
            r5.append("Timeout exceeded. Limit: ");
            r5.append(r2);
            r5.append(" sec");
            r10 = r5.toString();
     */
    /* JADX WARNING: Missing block: B:186:0x0405, code skipped:
            throw new java.io.IOException("stream cache time limit exceeded");
     */
    /* JADX WARNING: Missing block: B:187:0x0406, code skipped:
            r26 = r1;
     */
    /* JADX WARNING: Missing block: B:190:0x040a, code skipped:
            if (r8.zzexc == false) goto L_0x0416;
     */
    /* JADX WARNING: Missing block: B:192:?, code skipped:
            r1 = "externalAbort";
     */
    /* JADX WARNING: Missing block: B:195:0x0415, code skipped:
            throw new java.io.IOException("abort requested");
     */
    /* JADX WARNING: Missing block: B:198:0x041a, code skipped:
            if (r11.tryAcquire() == false) goto L_0x044c;
     */
    /* JADX WARNING: Missing block: B:199:0x041c, code skipped:
            r27 = r10;
     */
    /* JADX WARNING: Missing block: B:200:0x0426, code skipped:
            r28 = r11;
            r23 = r26;
            r1 = r1;
            r24 = r2;
            r25 = r3;
            r29 = r14;
            r14 = r4;
            r19 = r6;
            r31 = r5;
            r21 = r7;
     */
    /* JADX WARNING: Missing block: B:202:?, code skipped:
            com.google.android.gms.internal.ads.zzbat.zztu.post(new com.google.android.gms.internal.ads.zzbfl(r8, r9, r12.getAbsolutePath(), r6, r7, false));
     */
    /* JADX WARNING: Missing block: B:203:0x044c, code skipped:
            r24 = r2;
            r25 = r3;
            r31 = r5;
            r19 = r6;
            r21 = r7;
            r27 = r10;
            r28 = r11;
            r29 = r14;
            r23 = r26;
            r14 = r4;
     */
    /* JADX WARNING: Missing block: B:204:0x0460, code skipped:
            r4 = r14;
            r6 = r19;
            r7 = r21;
            r1 = r23;
            r2 = r24;
            r3 = r25;
            r10 = r27;
            r11 = r28;
            r14 = r29;
            r5 = r31;
     */
    /* JADX WARNING: Missing block: B:205:0x0475, code skipped:
            r29 = r14;
     */
    /* JADX WARNING: Missing block: B:206:0x0479, code skipped:
            r0 = e;
     */
    /* JADX WARNING: Missing block: B:207:0x047a, code skipped:
            r14 = r4;
     */
    /* JADX WARNING: Missing block: B:208:0x047b, code skipped:
            r1 = r0;
            r10 = r14;
     */
    /* JADX WARNING: Missing block: B:209:0x047f, code skipped:
            r14 = r4;
     */
    /* JADX WARNING: Missing block: B:211:?, code skipped:
            r14.close();
     */
    /* JADX WARNING: Missing block: B:212:0x0488, code skipped:
            if (com.google.android.gms.internal.ads.zzbbd.isLoggable(3) == false) goto L_0x04c4;
     */
    /* JADX WARNING: Missing block: B:214:?, code skipped:
            r1 = zzexa.format((long) r6);
            r3 = new java.lang.StringBuilder((22 + java.lang.String.valueOf(r1).length()) + java.lang.String.valueOf(r34).length());
            r3.append("Preloaded ");
            r3.append(r1);
            r3.append(" bytes from ");
            r3.append(r9);
            com.google.android.gms.internal.ads.zzbbd.zzdn(r3.toString());
     */
    /* JADX WARNING: Missing block: B:215:0x04c2, code skipped:
            r0 = e;
     */
    /* JADX WARNING: Missing block: B:218:?, code skipped:
            r12.setReadable(true, false);
     */
    /* JADX WARNING: Missing block: B:219:0x04cd, code skipped:
            if (r13.isFile() == false) goto L_0x04d7;
     */
    /* JADX WARNING: Missing block: B:221:?, code skipped:
            r13.setLastModified(java.lang.System.currentTimeMillis());
     */
    /* JADX WARNING: Missing block: B:223:?, code skipped:
            r13.createNewFile();
     */
    /* JADX WARNING: Missing block: B:225:?, code skipped:
            zza(r9, r12.getAbsolutePath(), r6);
     */
    /* JADX WARNING: Missing block: B:226:0x04e3, code skipped:
            r2 = r20;
     */
    /* JADX WARNING: Missing block: B:228:?, code skipped:
            zzewz.remove(r2);
     */
    /* JADX WARNING: Missing block: B:230:0x04e9, code skipped:
            return true;
     */
    /* JADX WARNING: Missing block: B:231:0x04ea, code skipped:
            r0 = e;
     */
    /* JADX WARNING: Missing block: B:232:0x04ec, code skipped:
            r0 = e;
     */
    /* JADX WARNING: Missing block: B:233:0x04ee, code skipped:
            r0 = e;
     */
    /* JADX WARNING: Missing block: B:234:0x04ef, code skipped:
            r14 = r4;
     */
    /* JADX WARNING: Missing block: B:235:0x04f0, code skipped:
            r2 = r20;
     */
    /* JADX WARNING: Missing block: B:236:0x04f3, code skipped:
            r0 = e;
     */
    /* JADX WARNING: Missing block: B:237:0x04f4, code skipped:
            r14 = r4;
            r2 = r15;
     */
    /* JADX WARNING: Missing block: B:238:0x04f6, code skipped:
            r1 = r0;
            r10 = r14;
            r3 = r16;
     */
    /* JADX WARNING: Missing block: B:239:0x04fa, code skipped:
            r4 = null;
     */
    /* JADX WARNING: Missing block: B:240:0x04fc, code skipped:
            r2 = r15;
     */
    /* JADX WARNING: Missing block: B:243:0x0504, code skipped:
            throw new java.io.IOException("Too many redirects (20)");
     */
    /* JADX WARNING: Missing block: B:244:0x0505, code skipped:
            r0 = e;
     */
    /* JADX WARNING: Missing block: B:245:0x0507, code skipped:
            r0 = e;
     */
    /* JADX WARNING: Missing block: B:246:0x0508, code skipped:
            r2 = r15;
     */
    /* JADX WARNING: Missing block: B:247:0x0509, code skipped:
            r1 = r0;
            r3 = r16;
            r4 = null;
            r10 = null;
     */
    /* JADX WARNING: Missing block: B:249:0x0510, code skipped:
            if ((r1 instanceof java.lang.RuntimeException) != false) goto L_0x0512;
     */
    /* JADX WARNING: Missing block: B:250:0x0512, code skipped:
            com.google.android.gms.ads.internal.zzbv.zzlj().zza(r1, "VideoStreamFullFileCache.preload");
     */
    /* JADX WARNING: Missing block: B:252:?, code skipped:
            r10.close();
     */
    /* JADX WARNING: Missing block: B:255:0x0520, code skipped:
            if (r8.zzexc != false) goto L_0x0522;
     */
    /* JADX WARNING: Missing block: B:256:0x0522, code skipped:
            r5 = new java.lang.StringBuilder(26 + java.lang.String.valueOf(r34).length());
            r5.append("Preload aborted for URL \"");
            r5.append(r9);
            r5.append("\"");
            com.google.android.gms.internal.ads.zzbbd.zzen(r5.toString());
     */
    /* JADX WARNING: Missing block: B:257:0x0547, code skipped:
            r6 = new java.lang.StringBuilder(25 + java.lang.String.valueOf(r34).length());
            r6.append("Preload failed for URL \"");
            r6.append(r9);
            r6.append("\"");
            com.google.android.gms.internal.ads.zzbbd.zzc(r6.toString(), r1);
     */
    /* JADX WARNING: Missing block: B:262:0x0577, code skipped:
            r1 = "Could not delete partial cache file at ";
            r5 = java.lang.String.valueOf(r12.getAbsolutePath());
     */
    /* JADX WARNING: Missing block: B:263:0x0585, code skipped:
            if (r5.length() != 0) goto L_0x0587;
     */
    /* JADX WARNING: Missing block: B:264:0x0587, code skipped:
            r1 = r1.concat(r5);
     */
    /* JADX WARNING: Missing block: B:265:0x058c, code skipped:
            r1 = new java.lang.String(r1);
     */
    /* JADX WARNING: Missing block: B:266:0x0592, code skipped:
            com.google.android.gms.internal.ads.zzbbd.zzeo(r1);
     */
    /* JADX WARNING: Missing block: B:267:0x0595, code skipped:
            zza(r9, r12.getAbsolutePath(), r3, r4);
            zzewz.remove(r2);
     */
    /* JADX WARNING: Missing block: B:268:0x05a2, code skipped:
            return false;
     */
    public final boolean zzex(java.lang.String r34) {
        /*
        r33 = this;
        r8 = r33;
        r9 = r34;
        r1 = r8.zzexb;
        r10 = 0;
        r11 = 0;
        if (r1 != 0) goto L_0x0010;
    L_0x000a:
        r1 = "noCacheDir";
        r8.zza(r9, r10, r1, r10);
        return r11;
    L_0x0010:
        r1 = r8.zzexb;
        if (r1 != 0) goto L_0x0016;
    L_0x0014:
        r4 = r11;
        goto L_0x0034;
    L_0x0016:
        r1 = r8.zzexb;
        r1 = r1.listFiles();
        r2 = r1.length;
        r3 = r11;
        r4 = r3;
    L_0x001f:
        if (r3 >= r2) goto L_0x0034;
    L_0x0021:
        r5 = r1[r3];
        r5 = r5.getName();
        r6 = ".done";
        r5 = r5.endsWith(r6);
        if (r5 != 0) goto L_0x0031;
    L_0x002f:
        r4 = r4 + 1;
    L_0x0031:
        r3 = r3 + 1;
        goto L_0x001f;
    L_0x0034:
        r1 = com.google.android.gms.internal.ads.zzaan.zzcot;
        r2 = com.google.android.gms.internal.ads.zzwu.zzpz();
        r1 = r2.zzd(r1);
        r1 = (java.lang.Integer) r1;
        r1 = r1.intValue();
        if (r4 <= r1) goto L_0x009a;
    L_0x0046:
        r1 = r8.zzexb;
        if (r1 != 0) goto L_0x004c;
    L_0x004a:
        r1 = r11;
        goto L_0x008d;
    L_0x004c:
        r1 = 9223372036854775807; // 0x7fffffffffffffff float:NaN double:NaN;
        r3 = r8.zzexb;
        r3 = r3.listFiles();
        r4 = r3.length;
        r5 = r1;
        r2 = r10;
        r1 = r11;
    L_0x005b:
        if (r1 >= r4) goto L_0x0078;
    L_0x005d:
        r7 = r3[r1];
        r12 = r7.getName();
        r13 = ".done";
        r12 = r12.endsWith(r13);
        if (r12 != 0) goto L_0x0075;
    L_0x006b:
        r12 = r7.lastModified();
        r14 = (r12 > r5 ? 1 : (r12 == r5 ? 0 : -1));
        if (r14 >= 0) goto L_0x0075;
    L_0x0073:
        r2 = r7;
        r5 = r12;
    L_0x0075:
        r1 = r1 + 1;
        goto L_0x005b;
    L_0x0078:
        if (r2 == 0) goto L_0x004a;
    L_0x007a:
        r1 = r2.delete();
        r2 = r8.zzc(r2);
        r3 = r2.isFile();
        if (r3 == 0) goto L_0x008d;
    L_0x0088:
        r2 = r2.delete();
        r1 = r1 & r2;
    L_0x008d:
        if (r1 != 0) goto L_0x0010;
    L_0x008f:
        r1 = "Unable to expire stream cache";
        com.google.android.gms.internal.ads.zzbbd.zzeo(r1);
        r1 = "expireFailed";
        r8.zza(r9, r10, r1, r10);
        return r11;
    L_0x009a:
        r1 = r33.zzey(r34);
        r12 = new java.io.File;
        r2 = r8.zzexb;
        r12.<init>(r2, r1);
        r13 = r8.zzc(r12);
        r1 = r12.isFile();
        r14 = 1;
        if (r1 == 0) goto L_0x00dd;
    L_0x00b0:
        r1 = r13.isFile();
        if (r1 == 0) goto L_0x00dd;
    L_0x00b6:
        r1 = r12.length();
        r1 = (int) r1;
        r2 = "Stream cache hit at ";
        r3 = java.lang.String.valueOf(r34);
        r4 = r3.length();
        if (r4 == 0) goto L_0x00cc;
    L_0x00c7:
        r2 = r2.concat(r3);
        goto L_0x00d2;
    L_0x00cc:
        r3 = new java.lang.String;
        r3.<init>(r2);
        r2 = r3;
    L_0x00d2:
        com.google.android.gms.internal.ads.zzbbd.zzdn(r2);
        r2 = r12.getAbsolutePath();
        r8.zza(r9, r2, r1);
        return r14;
    L_0x00dd:
        r1 = r8.zzexb;
        r1 = r1.getAbsolutePath();
        r1 = java.lang.String.valueOf(r1);
        r2 = java.lang.String.valueOf(r34);
        r3 = r2.length();
        if (r3 == 0) goto L_0x00f7;
    L_0x00f1:
        r1 = r1.concat(r2);
        r15 = r1;
        goto L_0x00fd;
    L_0x00f7:
        r2 = new java.lang.String;
        r2.<init>(r1);
        r15 = r2;
    L_0x00fd:
        r1 = zzewz;
        monitor-enter(r1);
        r2 = zzewz;	 Catch:{ all -> 0x05a3 }
        r2 = r2.contains(r15);	 Catch:{ all -> 0x05a3 }
        if (r2 == 0) goto L_0x012d;
    L_0x0108:
        r2 = "Stream cache already in progress at ";
        r3 = java.lang.String.valueOf(r34);	 Catch:{ all -> 0x05a3 }
        r4 = r3.length();	 Catch:{ all -> 0x05a3 }
        if (r4 == 0) goto L_0x0119;
    L_0x0114:
        r2 = r2.concat(r3);	 Catch:{ all -> 0x05a3 }
        goto L_0x011f;
    L_0x0119:
        r3 = new java.lang.String;	 Catch:{ all -> 0x05a3 }
        r3.<init>(r2);	 Catch:{ all -> 0x05a3 }
        r2 = r3;
    L_0x011f:
        com.google.android.gms.internal.ads.zzbbd.zzeo(r2);	 Catch:{ all -> 0x05a3 }
        r2 = r12.getAbsolutePath();	 Catch:{ all -> 0x05a3 }
        r3 = "inProgress";
        r8.zza(r9, r2, r3, r10);	 Catch:{ all -> 0x05a3 }
        monitor-exit(r1);	 Catch:{ all -> 0x05a3 }
        return r11;
    L_0x012d:
        r2 = zzewz;	 Catch:{ all -> 0x05a3 }
        r2.add(r15);	 Catch:{ all -> 0x05a3 }
        monitor-exit(r1);	 Catch:{ all -> 0x05a3 }
        r16 = "error";
        com.google.android.gms.ads.internal.zzbv.zzls();	 Catch:{ IOException | RuntimeException -> 0x0507, IOException | RuntimeException -> 0x0507 }
        r1 = com.google.android.gms.internal.ads.zzaan.zzcoy;	 Catch:{ IOException | RuntimeException -> 0x0507, IOException | RuntimeException -> 0x0507 }
        r2 = com.google.android.gms.internal.ads.zzwu.zzpz();	 Catch:{ IOException | RuntimeException -> 0x0507, IOException | RuntimeException -> 0x0507 }
        r1 = r2.zzd(r1);	 Catch:{ IOException | RuntimeException -> 0x0507, IOException | RuntimeException -> 0x0507 }
        r1 = (java.lang.Integer) r1;	 Catch:{ IOException | RuntimeException -> 0x0507, IOException | RuntimeException -> 0x0507 }
        r1 = r1.intValue();	 Catch:{ IOException | RuntimeException -> 0x0507, IOException | RuntimeException -> 0x0507 }
        r2 = new java.net.URL;	 Catch:{ IOException | RuntimeException -> 0x0507, IOException | RuntimeException -> 0x0507 }
        r2.<init>(r9);	 Catch:{ IOException | RuntimeException -> 0x0507, IOException | RuntimeException -> 0x0507 }
        r3 = r2;
        r2 = r11;
    L_0x014f:
        r2 = r2 + r14;
        r4 = 20;
        if (r2 > r4) goto L_0x04fc;
    L_0x0154:
        r5 = r3.openConnection();	 Catch:{ IOException | RuntimeException -> 0x0507, IOException | RuntimeException -> 0x0507 }
        r5.setConnectTimeout(r1);	 Catch:{ IOException | RuntimeException -> 0x0507, IOException | RuntimeException -> 0x0507 }
        r5.setReadTimeout(r1);	 Catch:{ IOException | RuntimeException -> 0x0507, IOException | RuntimeException -> 0x0507 }
        r6 = r5 instanceof java.net.HttpURLConnection;	 Catch:{ IOException | RuntimeException -> 0x0507, IOException | RuntimeException -> 0x0507 }
        if (r6 != 0) goto L_0x0172;
    L_0x0162:
        r1 = new java.io.IOException;	 Catch:{ IOException | RuntimeException -> 0x016a, IOException | RuntimeException -> 0x016a }
        r2 = "Invalid protocol.";
        r1.<init>(r2);	 Catch:{ IOException | RuntimeException -> 0x016a, IOException | RuntimeException -> 0x016a }
        throw r1;	 Catch:{ IOException | RuntimeException -> 0x016a, IOException | RuntimeException -> 0x016a }
    L_0x016a:
        r0 = move-exception;
        r1 = r0;
        r4 = r10;
        r2 = r15;
        r3 = r16;
        goto L_0x050e;
    L_0x0172:
        r5 = (java.net.HttpURLConnection) r5;	 Catch:{ IOException | RuntimeException -> 0x0507, IOException | RuntimeException -> 0x0507 }
        r6 = new com.google.android.gms.internal.ads.zzbax;	 Catch:{ IOException | RuntimeException -> 0x0507, IOException | RuntimeException -> 0x0507 }
        r6.<init>();	 Catch:{ IOException | RuntimeException -> 0x0507, IOException | RuntimeException -> 0x0507 }
        r6.zza(r5, r10);	 Catch:{ IOException | RuntimeException -> 0x0507, IOException | RuntimeException -> 0x0507 }
        r5.setInstanceFollowRedirects(r11);	 Catch:{ IOException | RuntimeException -> 0x0507, IOException | RuntimeException -> 0x0507 }
        r7 = r5.getResponseCode();	 Catch:{ IOException | RuntimeException -> 0x0507, IOException | RuntimeException -> 0x0507 }
        r6.zza(r5, r7);	 Catch:{ IOException | RuntimeException -> 0x0507, IOException | RuntimeException -> 0x0507 }
        r7 = r7 / 100;
        r6 = 3;
        if (r7 != r6) goto L_0x01fb;
    L_0x018b:
        r4 = "Location";
        r4 = r5.getHeaderField(r4);	 Catch:{ IOException | RuntimeException -> 0x016a, IOException | RuntimeException -> 0x016a }
        if (r4 != 0) goto L_0x019b;
    L_0x0193:
        r1 = new java.io.IOException;	 Catch:{ IOException | RuntimeException -> 0x016a, IOException | RuntimeException -> 0x016a }
        r2 = "Missing Location header in redirect";
        r1.<init>(r2);	 Catch:{ IOException | RuntimeException -> 0x016a, IOException | RuntimeException -> 0x016a }
        throw r1;	 Catch:{ IOException | RuntimeException -> 0x016a, IOException | RuntimeException -> 0x016a }
    L_0x019b:
        r6 = new java.net.URL;	 Catch:{ IOException | RuntimeException -> 0x016a, IOException | RuntimeException -> 0x016a }
        r6.<init>(r3, r4);	 Catch:{ IOException | RuntimeException -> 0x016a, IOException | RuntimeException -> 0x016a }
        r3 = r6.getProtocol();	 Catch:{ IOException | RuntimeException -> 0x016a, IOException | RuntimeException -> 0x016a }
        if (r3 != 0) goto L_0x01ae;
    L_0x01a6:
        r1 = new java.io.IOException;	 Catch:{ IOException | RuntimeException -> 0x016a, IOException | RuntimeException -> 0x016a }
        r2 = "Protocol is null";
        r1.<init>(r2);	 Catch:{ IOException | RuntimeException -> 0x016a, IOException | RuntimeException -> 0x016a }
        throw r1;	 Catch:{ IOException | RuntimeException -> 0x016a, IOException | RuntimeException -> 0x016a }
    L_0x01ae:
        r7 = "http";
        r7 = r3.equals(r7);	 Catch:{ IOException | RuntimeException -> 0x016a, IOException | RuntimeException -> 0x016a }
        if (r7 != 0) goto L_0x01db;
    L_0x01b6:
        r7 = "https";
        r7 = r3.equals(r7);	 Catch:{ IOException | RuntimeException -> 0x016a, IOException | RuntimeException -> 0x016a }
        if (r7 != 0) goto L_0x01db;
    L_0x01be:
        r1 = new java.io.IOException;	 Catch:{ IOException | RuntimeException -> 0x016a, IOException | RuntimeException -> 0x016a }
        r2 = "Unsupported scheme: ";
        r3 = java.lang.String.valueOf(r3);	 Catch:{ IOException | RuntimeException -> 0x016a, IOException | RuntimeException -> 0x016a }
        r4 = r3.length();	 Catch:{ IOException | RuntimeException -> 0x016a, IOException | RuntimeException -> 0x016a }
        if (r4 == 0) goto L_0x01d1;
    L_0x01cc:
        r2 = r2.concat(r3);	 Catch:{ IOException | RuntimeException -> 0x016a, IOException | RuntimeException -> 0x016a }
        goto L_0x01d7;
    L_0x01d1:
        r3 = new java.lang.String;	 Catch:{ IOException | RuntimeException -> 0x016a, IOException | RuntimeException -> 0x016a }
        r3.<init>(r2);	 Catch:{ IOException | RuntimeException -> 0x016a, IOException | RuntimeException -> 0x016a }
        r2 = r3;
    L_0x01d7:
        r1.<init>(r2);	 Catch:{ IOException | RuntimeException -> 0x016a, IOException | RuntimeException -> 0x016a }
        throw r1;	 Catch:{ IOException | RuntimeException -> 0x016a, IOException | RuntimeException -> 0x016a }
    L_0x01db:
        r3 = "Redirecting to ";
        r4 = java.lang.String.valueOf(r4);	 Catch:{ IOException | RuntimeException -> 0x016a, IOException | RuntimeException -> 0x016a }
        r7 = r4.length();	 Catch:{ IOException | RuntimeException -> 0x016a, IOException | RuntimeException -> 0x016a }
        if (r7 == 0) goto L_0x01ec;
    L_0x01e7:
        r3 = r3.concat(r4);	 Catch:{ IOException | RuntimeException -> 0x016a, IOException | RuntimeException -> 0x016a }
        goto L_0x01f2;
    L_0x01ec:
        r4 = new java.lang.String;	 Catch:{ IOException | RuntimeException -> 0x016a, IOException | RuntimeException -> 0x016a }
        r4.<init>(r3);	 Catch:{ IOException | RuntimeException -> 0x016a, IOException | RuntimeException -> 0x016a }
        r3 = r4;
    L_0x01f2:
        com.google.android.gms.internal.ads.zzbbd.zzdn(r3);	 Catch:{ IOException | RuntimeException -> 0x016a, IOException | RuntimeException -> 0x016a }
        r5.disconnect();	 Catch:{ IOException | RuntimeException -> 0x016a, IOException | RuntimeException -> 0x016a }
        r3 = r6;
        goto L_0x014f;
    L_0x01fb:
        r1 = r5 instanceof java.net.HttpURLConnection;	 Catch:{ IOException | RuntimeException -> 0x0507, IOException | RuntimeException -> 0x0507 }
        if (r1 == 0) goto L_0x025d;
    L_0x01ff:
        r1 = r5;
        r1 = (java.net.HttpURLConnection) r1;	 Catch:{ IOException | RuntimeException -> 0x016a, IOException | RuntimeException -> 0x016a }
        r1 = r1.getResponseCode();	 Catch:{ IOException | RuntimeException -> 0x016a, IOException | RuntimeException -> 0x016a }
        r2 = 400; // 0x190 float:5.6E-43 double:1.976E-321;
        if (r1 < r2) goto L_0x025d;
    L_0x020a:
        r2 = "badUrl";
        r3 = "HTTP request failed. Code: ";
        r4 = java.lang.Integer.toString(r1);	 Catch:{ IOException | RuntimeException -> 0x0256, IOException | RuntimeException -> 0x0256 }
        r4 = java.lang.String.valueOf(r4);	 Catch:{ IOException | RuntimeException -> 0x0256, IOException | RuntimeException -> 0x0256 }
        r5 = r4.length();	 Catch:{ IOException | RuntimeException -> 0x0256, IOException | RuntimeException -> 0x0256 }
        if (r5 == 0) goto L_0x0221;
    L_0x021c:
        r3 = r3.concat(r4);	 Catch:{ IOException | RuntimeException -> 0x0256, IOException | RuntimeException -> 0x0256 }
        goto L_0x0227;
    L_0x0221:
        r4 = new java.lang.String;	 Catch:{ IOException | RuntimeException -> 0x0256, IOException | RuntimeException -> 0x0256 }
        r4.<init>(r3);	 Catch:{ IOException | RuntimeException -> 0x0256, IOException | RuntimeException -> 0x0256 }
        r3 = r4;
    L_0x0227:
        r4 = new java.io.IOException;	 Catch:{ IOException | RuntimeException -> 0x0251, IOException | RuntimeException -> 0x0251 }
        r5 = 32;
        r6 = java.lang.String.valueOf(r34);	 Catch:{ IOException | RuntimeException -> 0x0251, IOException | RuntimeException -> 0x0251 }
        r6 = r6.length();	 Catch:{ IOException | RuntimeException -> 0x0251, IOException | RuntimeException -> 0x0251 }
        r5 = r5 + r6;
        r6 = new java.lang.StringBuilder;	 Catch:{ IOException | RuntimeException -> 0x0251, IOException | RuntimeException -> 0x0251 }
        r6.<init>(r5);	 Catch:{ IOException | RuntimeException -> 0x0251, IOException | RuntimeException -> 0x0251 }
        r5 = "HTTP status code ";
        r6.append(r5);	 Catch:{ IOException | RuntimeException -> 0x0251, IOException | RuntimeException -> 0x0251 }
        r6.append(r1);	 Catch:{ IOException | RuntimeException -> 0x0251, IOException | RuntimeException -> 0x0251 }
        r1 = " at ";
        r6.append(r1);	 Catch:{ IOException | RuntimeException -> 0x0251, IOException | RuntimeException -> 0x0251 }
        r6.append(r9);	 Catch:{ IOException | RuntimeException -> 0x0251, IOException | RuntimeException -> 0x0251 }
        r1 = r6.toString();	 Catch:{ IOException | RuntimeException -> 0x0251, IOException | RuntimeException -> 0x0251 }
        r4.<init>(r1);	 Catch:{ IOException | RuntimeException -> 0x0251, IOException | RuntimeException -> 0x0251 }
        throw r4;	 Catch:{ IOException | RuntimeException -> 0x0251, IOException | RuntimeException -> 0x0251 }
    L_0x0251:
        r0 = move-exception;
        r1 = r0;
        r4 = r3;
        r3 = r2;
        goto L_0x025a;
    L_0x0256:
        r0 = move-exception;
        r1 = r0;
        r3 = r2;
        r4 = r10;
    L_0x025a:
        r2 = r15;
        goto L_0x050e;
    L_0x025d:
        r7 = r5.getContentLength();	 Catch:{ IOException | RuntimeException -> 0x0507, IOException | RuntimeException -> 0x0507 }
        if (r7 >= 0) goto L_0x028c;
    L_0x0263:
        r1 = "Stream cache aborted, missing content-length header at ";
        r2 = java.lang.String.valueOf(r34);	 Catch:{ IOException | RuntimeException -> 0x016a, IOException | RuntimeException -> 0x016a }
        r3 = r2.length();	 Catch:{ IOException | RuntimeException -> 0x016a, IOException | RuntimeException -> 0x016a }
        if (r3 == 0) goto L_0x0274;
    L_0x026f:
        r1 = r1.concat(r2);	 Catch:{ IOException | RuntimeException -> 0x016a, IOException | RuntimeException -> 0x016a }
        goto L_0x027a;
    L_0x0274:
        r2 = new java.lang.String;	 Catch:{ IOException | RuntimeException -> 0x016a, IOException | RuntimeException -> 0x016a }
        r2.<init>(r1);	 Catch:{ IOException | RuntimeException -> 0x016a, IOException | RuntimeException -> 0x016a }
        r1 = r2;
    L_0x027a:
        com.google.android.gms.internal.ads.zzbbd.zzeo(r1);	 Catch:{ IOException | RuntimeException -> 0x016a, IOException | RuntimeException -> 0x016a }
        r1 = r12.getAbsolutePath();	 Catch:{ IOException | RuntimeException -> 0x016a, IOException | RuntimeException -> 0x016a }
        r2 = "contentLengthMissing";
        r8.zza(r9, r1, r2, r10);	 Catch:{ IOException | RuntimeException -> 0x016a, IOException | RuntimeException -> 0x016a }
        r1 = zzewz;	 Catch:{ IOException | RuntimeException -> 0x016a, IOException | RuntimeException -> 0x016a }
        r1.remove(r15);	 Catch:{ IOException | RuntimeException -> 0x016a, IOException | RuntimeException -> 0x016a }
        return r11;
    L_0x028c:
        r1 = zzexa;	 Catch:{ IOException | RuntimeException -> 0x0507, IOException | RuntimeException -> 0x0507 }
        r2 = (long) r7;	 Catch:{ IOException | RuntimeException -> 0x0507, IOException | RuntimeException -> 0x0507 }
        r1 = r1.format(r2);	 Catch:{ IOException | RuntimeException -> 0x0507, IOException | RuntimeException -> 0x0507 }
        r2 = com.google.android.gms.internal.ads.zzaan.zzcou;	 Catch:{ IOException | RuntimeException -> 0x0507, IOException | RuntimeException -> 0x0507 }
        r3 = com.google.android.gms.internal.ads.zzwu.zzpz();	 Catch:{ IOException | RuntimeException -> 0x0507, IOException | RuntimeException -> 0x0507 }
        r2 = r3.zzd(r2);	 Catch:{ IOException | RuntimeException -> 0x0507, IOException | RuntimeException -> 0x0507 }
        r2 = (java.lang.Integer) r2;	 Catch:{ IOException | RuntimeException -> 0x0507, IOException | RuntimeException -> 0x0507 }
        r3 = r2.intValue();	 Catch:{ IOException | RuntimeException -> 0x0507, IOException | RuntimeException -> 0x0507 }
        if (r7 <= r3) goto L_0x02fa;
    L_0x02a5:
        r2 = 33;
        r3 = java.lang.String.valueOf(r1);	 Catch:{ IOException | RuntimeException -> 0x016a, IOException | RuntimeException -> 0x016a }
        r3 = r3.length();	 Catch:{ IOException | RuntimeException -> 0x016a, IOException | RuntimeException -> 0x016a }
        r2 = r2 + r3;
        r3 = java.lang.String.valueOf(r34);	 Catch:{ IOException | RuntimeException -> 0x016a, IOException | RuntimeException -> 0x016a }
        r3 = r3.length();	 Catch:{ IOException | RuntimeException -> 0x016a, IOException | RuntimeException -> 0x016a }
        r2 = r2 + r3;
        r3 = new java.lang.StringBuilder;	 Catch:{ IOException | RuntimeException -> 0x016a, IOException | RuntimeException -> 0x016a }
        r3.<init>(r2);	 Catch:{ IOException | RuntimeException -> 0x016a, IOException | RuntimeException -> 0x016a }
        r2 = "Content length ";
        r3.append(r2);	 Catch:{ IOException | RuntimeException -> 0x016a, IOException | RuntimeException -> 0x016a }
        r3.append(r1);	 Catch:{ IOException | RuntimeException -> 0x016a, IOException | RuntimeException -> 0x016a }
        r2 = " exceeds limit at ";
        r3.append(r2);	 Catch:{ IOException | RuntimeException -> 0x016a, IOException | RuntimeException -> 0x016a }
        r3.append(r9);	 Catch:{ IOException | RuntimeException -> 0x016a, IOException | RuntimeException -> 0x016a }
        r2 = r3.toString();	 Catch:{ IOException | RuntimeException -> 0x016a, IOException | RuntimeException -> 0x016a }
        com.google.android.gms.internal.ads.zzbbd.zzeo(r2);	 Catch:{ IOException | RuntimeException -> 0x016a, IOException | RuntimeException -> 0x016a }
        r2 = "File too big for full file cache. Size: ";
        r1 = java.lang.String.valueOf(r1);	 Catch:{ IOException | RuntimeException -> 0x016a, IOException | RuntimeException -> 0x016a }
        r3 = r1.length();	 Catch:{ IOException | RuntimeException -> 0x016a, IOException | RuntimeException -> 0x016a }
        if (r3 == 0) goto L_0x02e6;
    L_0x02e1:
        r1 = r2.concat(r1);	 Catch:{ IOException | RuntimeException -> 0x016a, IOException | RuntimeException -> 0x016a }
        goto L_0x02eb;
    L_0x02e6:
        r1 = new java.lang.String;	 Catch:{ IOException | RuntimeException -> 0x016a, IOException | RuntimeException -> 0x016a }
        r1.<init>(r2);	 Catch:{ IOException | RuntimeException -> 0x016a, IOException | RuntimeException -> 0x016a }
    L_0x02eb:
        r2 = r12.getAbsolutePath();	 Catch:{ IOException | RuntimeException -> 0x016a, IOException | RuntimeException -> 0x016a }
        r3 = "sizeExceeded";
        r8.zza(r9, r2, r3, r1);	 Catch:{ IOException | RuntimeException -> 0x016a, IOException | RuntimeException -> 0x016a }
        r1 = zzewz;	 Catch:{ IOException | RuntimeException -> 0x016a, IOException | RuntimeException -> 0x016a }
        r1.remove(r15);	 Catch:{ IOException | RuntimeException -> 0x016a, IOException | RuntimeException -> 0x016a }
        return r11;
    L_0x02fa:
        r2 = java.lang.String.valueOf(r1);	 Catch:{ IOException | RuntimeException -> 0x0507, IOException | RuntimeException -> 0x0507 }
        r2 = r2.length();	 Catch:{ IOException | RuntimeException -> 0x0507, IOException | RuntimeException -> 0x0507 }
        r4 = r4 + r2;
        r2 = java.lang.String.valueOf(r34);	 Catch:{ IOException | RuntimeException -> 0x0507, IOException | RuntimeException -> 0x0507 }
        r2 = r2.length();	 Catch:{ IOException | RuntimeException -> 0x0507, IOException | RuntimeException -> 0x0507 }
        r4 = r4 + r2;
        r2 = new java.lang.StringBuilder;	 Catch:{ IOException | RuntimeException -> 0x0507, IOException | RuntimeException -> 0x0507 }
        r2.<init>(r4);	 Catch:{ IOException | RuntimeException -> 0x0507, IOException | RuntimeException -> 0x0507 }
        r4 = "Caching ";
        r2.append(r4);	 Catch:{ IOException | RuntimeException -> 0x0507, IOException | RuntimeException -> 0x0507 }
        r2.append(r1);	 Catch:{ IOException | RuntimeException -> 0x0507, IOException | RuntimeException -> 0x0507 }
        r1 = " bytes from ";
        r2.append(r1);	 Catch:{ IOException | RuntimeException -> 0x0507, IOException | RuntimeException -> 0x0507 }
        r2.append(r9);	 Catch:{ IOException | RuntimeException -> 0x0507, IOException | RuntimeException -> 0x0507 }
        r1 = r2.toString();	 Catch:{ IOException | RuntimeException -> 0x0507, IOException | RuntimeException -> 0x0507 }
        com.google.android.gms.internal.ads.zzbbd.zzdn(r1);	 Catch:{ IOException | RuntimeException -> 0x0507, IOException | RuntimeException -> 0x0507 }
        r1 = r5.getInputStream();	 Catch:{ IOException | RuntimeException -> 0x0507, IOException | RuntimeException -> 0x0507 }
        r5 = java.nio.channels.Channels.newChannel(r1);	 Catch:{ IOException | RuntimeException -> 0x0507, IOException | RuntimeException -> 0x0507 }
        r4 = new java.io.FileOutputStream;	 Catch:{ IOException | RuntimeException -> 0x0507, IOException | RuntimeException -> 0x0507 }
        r4.<init>(r12);	 Catch:{ IOException | RuntimeException -> 0x0507, IOException | RuntimeException -> 0x0507 }
        r2 = r4.getChannel();	 Catch:{ IOException | RuntimeException -> 0x04f3, IOException | RuntimeException -> 0x04f3 }
        r1 = 1048576; // 0x100000 float:1.469368E-39 double:5.180654E-318;
        r1 = java.nio.ByteBuffer.allocate(r1);	 Catch:{ IOException | RuntimeException -> 0x04f3, IOException | RuntimeException -> 0x04f3 }
        r10 = com.google.android.gms.ads.internal.zzbv.zzlm();	 Catch:{ IOException | RuntimeException -> 0x04f3, IOException | RuntimeException -> 0x04f3 }
        r17 = r10.currentTimeMillis();	 Catch:{ IOException | RuntimeException -> 0x04f3, IOException | RuntimeException -> 0x04f3 }
        r6 = com.google.android.gms.internal.ads.zzaan.zzcox;	 Catch:{ IOException | RuntimeException -> 0x04f3, IOException | RuntimeException -> 0x04f3 }
        r11 = com.google.android.gms.internal.ads.zzwu.zzpz();	 Catch:{ IOException | RuntimeException -> 0x04f3, IOException | RuntimeException -> 0x04f3 }
        r6 = r11.zzd(r6);	 Catch:{ IOException | RuntimeException -> 0x04f3, IOException | RuntimeException -> 0x04f3 }
        r6 = (java.lang.Long) r6;	 Catch:{ IOException | RuntimeException -> 0x04f3, IOException | RuntimeException -> 0x04f3 }
        r20 = r15;
        r14 = r6.longValue();	 Catch:{ IOException | RuntimeException -> 0x04ee, IOException | RuntimeException -> 0x04ee }
        r11 = new com.google.android.gms.internal.ads.zzbai;	 Catch:{ IOException | RuntimeException -> 0x04ee, IOException | RuntimeException -> 0x04ee }
        r11.<init>(r14);	 Catch:{ IOException | RuntimeException -> 0x04ee, IOException | RuntimeException -> 0x04ee }
        r6 = com.google.android.gms.internal.ads.zzaan.zzcow;	 Catch:{ IOException | RuntimeException -> 0x04ee, IOException | RuntimeException -> 0x04ee }
        r14 = com.google.android.gms.internal.ads.zzwu.zzpz();	 Catch:{ IOException | RuntimeException -> 0x04ee, IOException | RuntimeException -> 0x04ee }
        r6 = r14.zzd(r6);	 Catch:{ IOException | RuntimeException -> 0x04ee, IOException | RuntimeException -> 0x04ee }
        r6 = (java.lang.Long) r6;	 Catch:{ IOException | RuntimeException -> 0x04ee, IOException | RuntimeException -> 0x04ee }
        r14 = r6.longValue();	 Catch:{ IOException | RuntimeException -> 0x04ee, IOException | RuntimeException -> 0x04ee }
        r6 = 0;
    L_0x036f:
        r21 = r5.read(r1);	 Catch:{ IOException | RuntimeException -> 0x04ee, IOException | RuntimeException -> 0x04ee }
        if (r21 < 0) goto L_0x047f;
    L_0x0375:
        r6 = r6 + r21;
        if (r6 <= r3) goto L_0x03bd;
    L_0x0379:
        r1 = "sizeExceeded";
        r2 = "File too big for full file cache. Size: ";
        r3 = java.lang.Integer.toString(r6);	 Catch:{ IOException | RuntimeException -> 0x03ab, IOException | RuntimeException -> 0x03ab }
        r3 = java.lang.String.valueOf(r3);	 Catch:{ IOException | RuntimeException -> 0x03ab, IOException | RuntimeException -> 0x03ab }
        r5 = r3.length();	 Catch:{ IOException | RuntimeException -> 0x03ab, IOException | RuntimeException -> 0x03ab }
        if (r5 == 0) goto L_0x0391;
    L_0x038b:
        r2 = r2.concat(r3);	 Catch:{ IOException | RuntimeException -> 0x03ab, IOException | RuntimeException -> 0x03ab }
        r10 = r2;
        goto L_0x0397;
    L_0x0391:
        r3 = new java.lang.String;	 Catch:{ IOException | RuntimeException -> 0x03ab, IOException | RuntimeException -> 0x03ab }
        r3.<init>(r2);	 Catch:{ IOException | RuntimeException -> 0x03ab, IOException | RuntimeException -> 0x03ab }
        r10 = r3;
    L_0x0397:
        r2 = new java.io.IOException;	 Catch:{ IOException | RuntimeException -> 0x039f, IOException | RuntimeException -> 0x039f }
        r3 = "stream cache file size limit exceeded";
        r2.<init>(r3);	 Catch:{ IOException | RuntimeException -> 0x039f, IOException | RuntimeException -> 0x039f }
        throw r2;	 Catch:{ IOException | RuntimeException -> 0x039f, IOException | RuntimeException -> 0x039f }
    L_0x039f:
        r0 = move-exception;
        r3 = r1;
        r2 = r20;
        r1 = r0;
        r32 = r10;
        r10 = r4;
        r4 = r32;
        goto L_0x050e;
    L_0x03ab:
        r0 = move-exception;
        r3 = r1;
        r10 = r4;
        r2 = r20;
        r4 = 0;
        r1 = r0;
        goto L_0x050e;
    L_0x03b4:
        r0 = move-exception;
        r1 = r0;
        r10 = r4;
    L_0x03b7:
        r3 = r16;
        r2 = r20;
        goto L_0x04fa;
    L_0x03bd:
        r1.flip();	 Catch:{ IOException | RuntimeException -> 0x0479, IOException | RuntimeException -> 0x0479 }
    L_0x03c0:
        r21 = r2.write(r1);	 Catch:{ IOException | RuntimeException -> 0x0479, IOException | RuntimeException -> 0x0479 }
        if (r21 > 0) goto L_0x0475;
    L_0x03c6:
        r1.clear();	 Catch:{ IOException | RuntimeException -> 0x0479, IOException | RuntimeException -> 0x0479 }
        r21 = r10.currentTimeMillis();	 Catch:{ IOException | RuntimeException -> 0x0479, IOException | RuntimeException -> 0x0479 }
        r23 = r21 - r17;
        r21 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        r21 = r21 * r14;
        r25 = (r23 > r21 ? 1 : (r23 == r21 ? 0 : -1));
        if (r25 <= 0) goto L_0x0406;
    L_0x03d7:
        r1 = "downloadTimeout";
        r2 = java.lang.Long.toString(r14);	 Catch:{ IOException | RuntimeException -> 0x03ab, IOException | RuntimeException -> 0x03ab }
        r3 = 29;
        r5 = java.lang.String.valueOf(r2);	 Catch:{ IOException | RuntimeException -> 0x03ab, IOException | RuntimeException -> 0x03ab }
        r5 = r5.length();	 Catch:{ IOException | RuntimeException -> 0x03ab, IOException | RuntimeException -> 0x03ab }
        r3 = r3 + r5;
        r5 = new java.lang.StringBuilder;	 Catch:{ IOException | RuntimeException -> 0x03ab, IOException | RuntimeException -> 0x03ab }
        r5.<init>(r3);	 Catch:{ IOException | RuntimeException -> 0x03ab, IOException | RuntimeException -> 0x03ab }
        r3 = "Timeout exceeded. Limit: ";
        r5.append(r3);	 Catch:{ IOException | RuntimeException -> 0x03ab, IOException | RuntimeException -> 0x03ab }
        r5.append(r2);	 Catch:{ IOException | RuntimeException -> 0x03ab, IOException | RuntimeException -> 0x03ab }
        r2 = " sec";
        r5.append(r2);	 Catch:{ IOException | RuntimeException -> 0x03ab, IOException | RuntimeException -> 0x03ab }
        r10 = r5.toString();	 Catch:{ IOException | RuntimeException -> 0x03ab, IOException | RuntimeException -> 0x03ab }
        r2 = new java.io.IOException;	 Catch:{ IOException | RuntimeException -> 0x039f, IOException | RuntimeException -> 0x039f }
        r3 = "stream cache time limit exceeded";
        r2.<init>(r3);	 Catch:{ IOException | RuntimeException -> 0x039f, IOException | RuntimeException -> 0x039f }
        throw r2;	 Catch:{ IOException | RuntimeException -> 0x039f, IOException | RuntimeException -> 0x039f }
    L_0x0406:
        r26 = r1;
        r1 = r8.zzexc;	 Catch:{ IOException | RuntimeException -> 0x0479, IOException | RuntimeException -> 0x0479 }
        if (r1 == 0) goto L_0x0416;
    L_0x040c:
        r1 = "externalAbort";
        r2 = new java.io.IOException;	 Catch:{ IOException | RuntimeException -> 0x03ab, IOException | RuntimeException -> 0x03ab }
        r3 = "abort requested";
        r2.<init>(r3);	 Catch:{ IOException | RuntimeException -> 0x03ab, IOException | RuntimeException -> 0x03ab }
        throw r2;	 Catch:{ IOException | RuntimeException -> 0x03ab, IOException | RuntimeException -> 0x03ab }
    L_0x0416:
        r1 = r11.tryAcquire();	 Catch:{ IOException | RuntimeException -> 0x0479, IOException | RuntimeException -> 0x0479 }
        if (r1 == 0) goto L_0x044c;
    L_0x041c:
        r21 = r12.getAbsolutePath();	 Catch:{ IOException | RuntimeException -> 0x0479, IOException | RuntimeException -> 0x0479 }
        r1 = com.google.android.gms.internal.ads.zzbat.zztu;	 Catch:{ IOException | RuntimeException -> 0x0479, IOException | RuntimeException -> 0x0479 }
        r27 = r10;
        r10 = new com.google.android.gms.internal.ads.zzbfl;	 Catch:{ IOException | RuntimeException -> 0x0479, IOException | RuntimeException -> 0x0479 }
        r22 = 0;
        r28 = r11;
        r23 = r26;
        r11 = r1;
        r1 = r10;
        r24 = r2;
        r2 = r8;
        r25 = r3;
        r3 = r9;
        r29 = r14;
        r14 = r4;
        r4 = r21;
        r15 = r5;
        r5 = r6;
        r19 = r6;
        r31 = r15;
        r15 = 3;
        r6 = r7;
        r21 = r7;
        r7 = r22;
        r1.<init>(r2, r3, r4, r5, r6, r7);	 Catch:{ IOException | RuntimeException -> 0x04c2, IOException | RuntimeException -> 0x04c2 }
        r11.post(r10);	 Catch:{ IOException | RuntimeException -> 0x04c2, IOException | RuntimeException -> 0x04c2 }
        goto L_0x0460;
    L_0x044c:
        r24 = r2;
        r25 = r3;
        r31 = r5;
        r19 = r6;
        r21 = r7;
        r27 = r10;
        r28 = r11;
        r29 = r14;
        r23 = r26;
        r15 = 3;
        r14 = r4;
    L_0x0460:
        r4 = r14;
        r6 = r19;
        r7 = r21;
        r1 = r23;
        r2 = r24;
        r3 = r25;
        r10 = r27;
        r11 = r28;
        r14 = r29;
        r5 = r31;
        goto L_0x036f;
    L_0x0475:
        r29 = r14;
        goto L_0x03c0;
    L_0x0479:
        r0 = move-exception;
        r14 = r4;
    L_0x047b:
        r1 = r0;
        r10 = r14;
        goto L_0x03b7;
    L_0x047f:
        r14 = r4;
        r15 = 3;
        r14.close();	 Catch:{ IOException | RuntimeException -> 0x04ec, IOException | RuntimeException -> 0x04ec }
        r1 = com.google.android.gms.internal.ads.zzbbd.isLoggable(r15);	 Catch:{ IOException | RuntimeException -> 0x04ec, IOException | RuntimeException -> 0x04ec }
        if (r1 == 0) goto L_0x04c4;
    L_0x048a:
        r1 = zzexa;	 Catch:{ IOException | RuntimeException -> 0x04c2, IOException | RuntimeException -> 0x04c2 }
        r2 = (long) r6;	 Catch:{ IOException | RuntimeException -> 0x04c2, IOException | RuntimeException -> 0x04c2 }
        r1 = r1.format(r2);	 Catch:{ IOException | RuntimeException -> 0x04c2, IOException | RuntimeException -> 0x04c2 }
        r2 = 22;
        r3 = java.lang.String.valueOf(r1);	 Catch:{ IOException | RuntimeException -> 0x04c2, IOException | RuntimeException -> 0x04c2 }
        r3 = r3.length();	 Catch:{ IOException | RuntimeException -> 0x04c2, IOException | RuntimeException -> 0x04c2 }
        r2 = r2 + r3;
        r3 = java.lang.String.valueOf(r34);	 Catch:{ IOException | RuntimeException -> 0x04c2, IOException | RuntimeException -> 0x04c2 }
        r3 = r3.length();	 Catch:{ IOException | RuntimeException -> 0x04c2, IOException | RuntimeException -> 0x04c2 }
        r2 = r2 + r3;
        r3 = new java.lang.StringBuilder;	 Catch:{ IOException | RuntimeException -> 0x04c2, IOException | RuntimeException -> 0x04c2 }
        r3.<init>(r2);	 Catch:{ IOException | RuntimeException -> 0x04c2, IOException | RuntimeException -> 0x04c2 }
        r2 = "Preloaded ";
        r3.append(r2);	 Catch:{ IOException | RuntimeException -> 0x04c2, IOException | RuntimeException -> 0x04c2 }
        r3.append(r1);	 Catch:{ IOException | RuntimeException -> 0x04c2, IOException | RuntimeException -> 0x04c2 }
        r1 = " bytes from ";
        r3.append(r1);	 Catch:{ IOException | RuntimeException -> 0x04c2, IOException | RuntimeException -> 0x04c2 }
        r3.append(r9);	 Catch:{ IOException | RuntimeException -> 0x04c2, IOException | RuntimeException -> 0x04c2 }
        r1 = r3.toString();	 Catch:{ IOException | RuntimeException -> 0x04c2, IOException | RuntimeException -> 0x04c2 }
        com.google.android.gms.internal.ads.zzbbd.zzdn(r1);	 Catch:{ IOException | RuntimeException -> 0x04c2, IOException | RuntimeException -> 0x04c2 }
        goto L_0x04c4;
    L_0x04c2:
        r0 = move-exception;
        goto L_0x047b;
    L_0x04c4:
        r1 = 0;
        r2 = 1;
        r12.setReadable(r2, r1);	 Catch:{ IOException | RuntimeException -> 0x04ec, IOException | RuntimeException -> 0x04ec }
        r1 = r13.isFile();	 Catch:{ IOException | RuntimeException -> 0x04ec, IOException | RuntimeException -> 0x04ec }
        if (r1 == 0) goto L_0x04d7;
    L_0x04cf:
        r1 = java.lang.System.currentTimeMillis();	 Catch:{ IOException | RuntimeException -> 0x04c2, IOException | RuntimeException -> 0x04c2 }
        r13.setLastModified(r1);	 Catch:{ IOException | RuntimeException -> 0x04c2, IOException | RuntimeException -> 0x04c2 }
        goto L_0x04da;
    L_0x04d7:
        r13.createNewFile();	 Catch:{ IOException -> 0x04da }
    L_0x04da:
        r1 = r12.getAbsolutePath();	 Catch:{ IOException | RuntimeException -> 0x04ec, IOException | RuntimeException -> 0x04ec }
        r8.zza(r9, r1, r6);	 Catch:{ IOException | RuntimeException -> 0x04ec, IOException | RuntimeException -> 0x04ec }
        r1 = zzewz;	 Catch:{ IOException | RuntimeException -> 0x04ec, IOException | RuntimeException -> 0x04ec }
        r2 = r20;
        r1.remove(r2);	 Catch:{ IOException | RuntimeException -> 0x04ea, IOException | RuntimeException -> 0x04ea }
        r1 = 1;
        return r1;
    L_0x04ea:
        r0 = move-exception;
        goto L_0x04f6;
    L_0x04ec:
        r0 = move-exception;
        goto L_0x04f0;
    L_0x04ee:
        r0 = move-exception;
        r14 = r4;
    L_0x04f0:
        r2 = r20;
        goto L_0x04f6;
    L_0x04f3:
        r0 = move-exception;
        r14 = r4;
        r2 = r15;
    L_0x04f6:
        r1 = r0;
        r10 = r14;
        r3 = r16;
    L_0x04fa:
        r4 = 0;
        goto L_0x050e;
    L_0x04fc:
        r2 = r15;
        r1 = new java.io.IOException;	 Catch:{ IOException | RuntimeException -> 0x0505, IOException | RuntimeException -> 0x0505 }
        r3 = "Too many redirects (20)";
        r1.<init>(r3);	 Catch:{ IOException | RuntimeException -> 0x0505, IOException | RuntimeException -> 0x0505 }
        throw r1;	 Catch:{ IOException | RuntimeException -> 0x0505, IOException | RuntimeException -> 0x0505 }
    L_0x0505:
        r0 = move-exception;
        goto L_0x0509;
    L_0x0507:
        r0 = move-exception;
        r2 = r15;
    L_0x0509:
        r1 = r0;
        r3 = r16;
        r4 = 0;
        r10 = 0;
    L_0x050e:
        r5 = r1 instanceof java.lang.RuntimeException;
        if (r5 == 0) goto L_0x051b;
    L_0x0512:
        r5 = com.google.android.gms.ads.internal.zzbv.zzlj();
        r6 = "VideoStreamFullFileCache.preload";
        r5.zza(r1, r6);
    L_0x051b:
        r10.close();	 Catch:{ IOException | NullPointerException -> 0x051e, IOException | NullPointerException -> 0x051e }
    L_0x051e:
        r5 = r8.zzexc;
        if (r5 == 0) goto L_0x0547;
    L_0x0522:
        r1 = 26;
        r5 = java.lang.String.valueOf(r34);
        r5 = r5.length();
        r1 = r1 + r5;
        r5 = new java.lang.StringBuilder;
        r5.<init>(r1);
        r1 = "Preload aborted for URL \"";
        r5.append(r1);
        r5.append(r9);
        r1 = "\"";
        r5.append(r1);
        r1 = r5.toString();
        com.google.android.gms.internal.ads.zzbbd.zzen(r1);
        goto L_0x056b;
    L_0x0547:
        r5 = 25;
        r6 = java.lang.String.valueOf(r34);
        r6 = r6.length();
        r5 = r5 + r6;
        r6 = new java.lang.StringBuilder;
        r6.<init>(r5);
        r5 = "Preload failed for URL \"";
        r6.append(r5);
        r6.append(r9);
        r5 = "\"";
        r6.append(r5);
        r5 = r6.toString();
        com.google.android.gms.internal.ads.zzbbd.zzc(r5, r1);
    L_0x056b:
        r1 = r12.exists();
        if (r1 == 0) goto L_0x0595;
    L_0x0571:
        r1 = r12.delete();
        if (r1 != 0) goto L_0x0595;
    L_0x0577:
        r1 = "Could not delete partial cache file at ";
        r5 = r12.getAbsolutePath();
        r5 = java.lang.String.valueOf(r5);
        r6 = r5.length();
        if (r6 == 0) goto L_0x058c;
    L_0x0587:
        r1 = r1.concat(r5);
        goto L_0x0592;
    L_0x058c:
        r5 = new java.lang.String;
        r5.<init>(r1);
        r1 = r5;
    L_0x0592:
        com.google.android.gms.internal.ads.zzbbd.zzeo(r1);
    L_0x0595:
        r1 = r12.getAbsolutePath();
        r8.zza(r9, r1, r3, r4);
        r1 = zzewz;
        r1.remove(r2);
        r1 = 0;
        return r1;
    L_0x05a3:
        r0 = move-exception;
        r2 = r0;
        monitor-exit(r1);	 Catch:{ all -> 0x05a3 }
        throw r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzbfs.zzex(java.lang.String):boolean");
    }

    public final void abort() {
        this.zzexc = true;
    }

    private final File zzc(File file) {
        return new File(this.zzexb, String.valueOf(file.getName()).concat(".done"));
    }
}
