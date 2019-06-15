package com.google.android.gms.internal.ads;

@zzark
public final class zztn {
    private final int zzbyz;
    private final zztd zzbzb;
    private String zzbzj;
    private String zzbzk;
    private final boolean zzbzl = false;
    private final int zzbzm;
    private final int zzbzn;

    public zztn(int i, int i2, int i3) {
        this.zzbyz = i;
        if (i2 > 64 || i2 < 0) {
            this.zzbzm = 64;
        } else {
            this.zzbzm = i2;
        }
        if (i3 <= 0) {
            this.zzbzn = 1;
        } else {
            this.zzbzn = i3;
        }
        this.zzbzb = new zztm(this.zzbzm);
    }

    /* JADX WARNING: Removed duplicated region for block: B:82:0x010c A:{SYNTHETIC} */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00b3  */
    public final java.lang.String zza(java.util.ArrayList<java.lang.String> r18, java.util.ArrayList<com.google.android.gms.internal.ads.zztc> r19) {
        /*
        r17 = this;
        r1 = r17;
        r2 = r19;
        r3 = new com.google.android.gms.internal.ads.zzto;
        r3.<init>(r1);
        java.util.Collections.sort(r2, r3);
        r3 = new java.util.HashSet;
        r3.<init>();
        r5 = 0;
    L_0x0012:
        r6 = r19.size();
        if (r5 >= r6) goto L_0x0117;
    L_0x0018:
        r6 = r2.get(r5);
        r6 = (com.google.android.gms.internal.ads.zztc) r6;
        r6 = r6.zzob();
        r7 = r18;
        r6 = r7.get(r6);
        r6 = (java.lang.CharSequence) r6;
        r8 = java.text.Normalizer.Form.NFKC;
        r6 = java.text.Normalizer.normalize(r6, r8);
        r8 = java.util.Locale.US;
        r6 = r6.toLowerCase(r8);
        r8 = "\n";
        r6 = r6.split(r8);
        r9 = r6.length;
        if (r9 == 0) goto L_0x0110;
    L_0x003f:
        r9 = 0;
    L_0x0040:
        r10 = r6.length;
        if (r9 >= r10) goto L_0x0110;
    L_0x0043:
        r10 = r6[r9];
        r11 = "'";
        r11 = r10.indexOf(r11);
        r12 = -1;
        if (r11 == r12) goto L_0x00a8;
    L_0x004e:
        r11 = new java.lang.StringBuilder;
        r11.<init>(r10);
        r12 = 1;
        r13 = 0;
    L_0x0055:
        r14 = r12 + 2;
        r15 = r11.length();
        if (r14 > r15) goto L_0x009b;
    L_0x005d:
        r15 = r11.charAt(r12);
        r4 = 39;
        if (r15 != r4) goto L_0x0098;
    L_0x0065:
        r4 = r12 + -1;
        r4 = r11.charAt(r4);
        r13 = 32;
        if (r4 == r13) goto L_0x0092;
    L_0x006f:
        r4 = r12 + 1;
        r15 = r11.charAt(r4);
        r8 = 115; // 0x73 float:1.61E-43 double:5.7E-322;
        if (r15 == r8) goto L_0x0081;
    L_0x0079:
        r4 = r11.charAt(r4);
        r8 = 83;
        if (r4 != r8) goto L_0x0092;
    L_0x0081:
        r4 = r11.length();
        if (r14 == r4) goto L_0x008d;
    L_0x0087:
        r4 = r11.charAt(r14);
        if (r4 != r13) goto L_0x0092;
    L_0x008d:
        r11.insert(r12, r13);
        r12 = r14;
        goto L_0x0095;
    L_0x0092:
        r11.setCharAt(r12, r13);
    L_0x0095:
        r4 = 1;
        r13 = 1;
        goto L_0x0099;
    L_0x0098:
        r4 = 1;
    L_0x0099:
        r12 = r12 + r4;
        goto L_0x0055;
    L_0x009b:
        if (r13 == 0) goto L_0x00a2;
    L_0x009d:
        r4 = r11.toString();
        goto L_0x00a3;
    L_0x00a2:
        r4 = 0;
    L_0x00a3:
        if (r4 == 0) goto L_0x00a8;
    L_0x00a5:
        r1.zzbzk = r4;
        goto L_0x00a9;
    L_0x00a8:
        r4 = r10;
    L_0x00a9:
        r8 = 1;
        r4 = com.google.android.gms.internal.ads.zzth.zze(r4, r8);
        r10 = r4.length;
        r11 = r1.zzbzn;
        if (r10 < r11) goto L_0x010c;
    L_0x00b3:
        r10 = 0;
    L_0x00b4:
        r11 = r4.length;
        if (r10 >= r11) goto L_0x0102;
    L_0x00b7:
        r11 = "";
        r12 = r11;
        r11 = 0;
    L_0x00bb:
        r13 = r1.zzbzn;
        if (r11 >= r13) goto L_0x00f0;
    L_0x00bf:
        r13 = r10 + r11;
        r14 = r4.length;
        if (r13 < r14) goto L_0x00c6;
    L_0x00c4:
        r11 = 0;
        goto L_0x00f1;
    L_0x00c6:
        if (r11 <= 0) goto L_0x00d2;
    L_0x00c8:
        r12 = java.lang.String.valueOf(r12);
        r14 = " ";
        r12 = r12.concat(r14);
    L_0x00d2:
        r12 = java.lang.String.valueOf(r12);
        r13 = r4[r13];
        r13 = java.lang.String.valueOf(r13);
        r14 = r13.length();
        if (r14 == 0) goto L_0x00e7;
    L_0x00e2:
        r12 = r12.concat(r13);
        goto L_0x00ed;
    L_0x00e7:
        r13 = new java.lang.String;
        r13.<init>(r12);
        r12 = r13;
    L_0x00ed:
        r11 = r11 + 1;
        goto L_0x00bb;
    L_0x00f0:
        r11 = r8;
    L_0x00f1:
        if (r11 == 0) goto L_0x0102;
    L_0x00f3:
        r3.add(r12);
        r11 = r3.size();
        r12 = r1.zzbyz;
        if (r11 < r12) goto L_0x00ff;
    L_0x00fe:
        goto L_0x010a;
    L_0x00ff:
        r10 = r10 + 1;
        goto L_0x00b4;
    L_0x0102:
        r4 = r3.size();
        r10 = r1.zzbyz;
        if (r4 < r10) goto L_0x010c;
    L_0x010a:
        r8 = 0;
        goto L_0x0111;
    L_0x010c:
        r9 = r9 + 1;
        goto L_0x0040;
    L_0x0110:
        r8 = 1;
    L_0x0111:
        if (r8 == 0) goto L_0x0117;
    L_0x0113:
        r5 = r5 + 1;
        goto L_0x0012;
    L_0x0117:
        r2 = new com.google.android.gms.internal.ads.zztg;
        r2.<init>();
        r4 = "";
        r1.zzbzj = r4;
        r3 = r3.iterator();
    L_0x0124:
        r4 = r3.hasNext();
        if (r4 == 0) goto L_0x0141;
    L_0x012a:
        r4 = r3.next();
        r4 = (java.lang.String) r4;
        r5 = r1.zzbzb;	 Catch:{ IOException -> 0x013a }
        r4 = r5.zzay(r4);	 Catch:{ IOException -> 0x013a }
        r2.write(r4);	 Catch:{ IOException -> 0x013a }
        goto L_0x0124;
    L_0x013a:
        r0 = move-exception;
        r3 = r0;
        r4 = "Error while writing hash to byteStream";
        com.google.android.gms.internal.ads.zzbbd.zzb(r4, r3);
    L_0x0141:
        r2 = r2.toString();
        return r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zztn.zza(java.util.ArrayList, java.util.ArrayList):java.lang.String");
    }
}
