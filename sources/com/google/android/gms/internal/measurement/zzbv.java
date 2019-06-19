package com.google.android.gms.internal.measurement;

import android.content.res.Resources.NotFoundException;

class zzbv<T extends zzbu> extends zzat {
    private zzbw<T> zzyj;

    public zzbv(zzaw zzaw, zzbw<T> zzbw) {
        super(zzaw);
        this.zzyj = zzbw;
    }

    public final T zzq(int i) {
        try {
            return zza(zzbw().zzcm().getResources().getXml(i));
        } catch (NotFoundException e) {
            zzd("inflate() called with unknown resourceId", e);
            return null;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:43:0x00d4 A:{ExcHandler: IOException | XmlPullParserException (r5_1 'e' java.lang.Throwable), Splitter:B:0:0x0000} */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00d4 A:{ExcHandler: IOException | XmlPullParserException (r5_1 'e' java.lang.Throwable), Splitter:B:0:0x0000} */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing block: B:27:0x0095, code skipped:
            r0 = move-exception;
     */
    /* JADX WARNING: Missing block: B:29:?, code skipped:
            zzc("Error parsing bool configuration value", r1, r0);
     */
    /* JADX WARNING: Missing block: B:43:0x00d4, code skipped:
            r5 = move-exception;
     */
    /* JADX WARNING: Missing block: B:44:0x00d5, code skipped:
            zze("Error parsing tracker configuration file", r5);
     */
    private final T zza(android.content.res.XmlResourceParser r5) {
        /*
        r4 = this;
        r5.next();	 Catch:{ IOException | XmlPullParserException -> 0x00d4, IOException | XmlPullParserException -> 0x00d4 }
        r0 = r5.getEventType();	 Catch:{ IOException | XmlPullParserException -> 0x00d4, IOException | XmlPullParserException -> 0x00d4 }
    L_0x0007:
        r1 = 1;
        if (r0 == r1) goto L_0x00da;
    L_0x000a:
        r0 = r5.getEventType();	 Catch:{ IOException | XmlPullParserException -> 0x00d4, IOException | XmlPullParserException -> 0x00d4 }
        r1 = 2;
        if (r0 != r1) goto L_0x00ce;
    L_0x0011:
        r0 = r5.getName();	 Catch:{ IOException | XmlPullParserException -> 0x00d4, IOException | XmlPullParserException -> 0x00d4 }
        r1 = java.util.Locale.US;	 Catch:{ IOException | XmlPullParserException -> 0x00d4, IOException | XmlPullParserException -> 0x00d4 }
        r0 = r0.toLowerCase(r1);	 Catch:{ IOException | XmlPullParserException -> 0x00d4, IOException | XmlPullParserException -> 0x00d4 }
        r1 = "screenname";
        r1 = r0.equals(r1);	 Catch:{ IOException | XmlPullParserException -> 0x00d4, IOException | XmlPullParserException -> 0x00d4 }
        r2 = 0;
        if (r1 == 0) goto L_0x0045;
    L_0x0024:
        r0 = "name";
        r0 = r5.getAttributeValue(r2, r0);	 Catch:{ IOException | XmlPullParserException -> 0x00d4, IOException | XmlPullParserException -> 0x00d4 }
        r1 = r5.nextText();	 Catch:{ IOException | XmlPullParserException -> 0x00d4, IOException | XmlPullParserException -> 0x00d4 }
        r1 = r1.trim();	 Catch:{ IOException | XmlPullParserException -> 0x00d4, IOException | XmlPullParserException -> 0x00d4 }
        r2 = android.text.TextUtils.isEmpty(r0);	 Catch:{ IOException | XmlPullParserException -> 0x00d4, IOException | XmlPullParserException -> 0x00d4 }
        if (r2 != 0) goto L_0x00ce;
    L_0x0038:
        r2 = android.text.TextUtils.isEmpty(r1);	 Catch:{ IOException | XmlPullParserException -> 0x00d4, IOException | XmlPullParserException -> 0x00d4 }
        if (r2 != 0) goto L_0x00ce;
    L_0x003e:
        r2 = r4.zzyj;	 Catch:{ IOException | XmlPullParserException -> 0x00d4, IOException | XmlPullParserException -> 0x00d4 }
        r2.zzb(r0, r1);	 Catch:{ IOException | XmlPullParserException -> 0x00d4, IOException | XmlPullParserException -> 0x00d4 }
        goto L_0x00ce;
    L_0x0045:
        r1 = "string";
        r1 = r0.equals(r1);	 Catch:{ IOException | XmlPullParserException -> 0x00d4, IOException | XmlPullParserException -> 0x00d4 }
        if (r1 == 0) goto L_0x0069;
    L_0x004d:
        r0 = "name";
        r0 = r5.getAttributeValue(r2, r0);	 Catch:{ IOException | XmlPullParserException -> 0x00d4, IOException | XmlPullParserException -> 0x00d4 }
        r1 = r5.nextText();	 Catch:{ IOException | XmlPullParserException -> 0x00d4, IOException | XmlPullParserException -> 0x00d4 }
        r1 = r1.trim();	 Catch:{ IOException | XmlPullParserException -> 0x00d4, IOException | XmlPullParserException -> 0x00d4 }
        r2 = android.text.TextUtils.isEmpty(r0);	 Catch:{ IOException | XmlPullParserException -> 0x00d4, IOException | XmlPullParserException -> 0x00d4 }
        if (r2 != 0) goto L_0x00ce;
    L_0x0061:
        if (r1 == 0) goto L_0x00ce;
    L_0x0063:
        r2 = r4.zzyj;	 Catch:{ IOException | XmlPullParserException -> 0x00d4, IOException | XmlPullParserException -> 0x00d4 }
        r2.zzc(r0, r1);	 Catch:{ IOException | XmlPullParserException -> 0x00d4, IOException | XmlPullParserException -> 0x00d4 }
        goto L_0x00ce;
    L_0x0069:
        r1 = "bool";
        r1 = r0.equals(r1);	 Catch:{ IOException | XmlPullParserException -> 0x00d4, IOException | XmlPullParserException -> 0x00d4 }
        if (r1 == 0) goto L_0x009c;
    L_0x0071:
        r0 = "name";
        r0 = r5.getAttributeValue(r2, r0);	 Catch:{ IOException | XmlPullParserException -> 0x00d4, IOException | XmlPullParserException -> 0x00d4 }
        r1 = r5.nextText();	 Catch:{ IOException | XmlPullParserException -> 0x00d4, IOException | XmlPullParserException -> 0x00d4 }
        r1 = r1.trim();	 Catch:{ IOException | XmlPullParserException -> 0x00d4, IOException | XmlPullParserException -> 0x00d4 }
        r2 = android.text.TextUtils.isEmpty(r0);	 Catch:{ IOException | XmlPullParserException -> 0x00d4, IOException | XmlPullParserException -> 0x00d4 }
        if (r2 != 0) goto L_0x00ce;
    L_0x0085:
        r2 = android.text.TextUtils.isEmpty(r1);	 Catch:{ IOException | XmlPullParserException -> 0x00d4, IOException | XmlPullParserException -> 0x00d4 }
        if (r2 != 0) goto L_0x00ce;
    L_0x008b:
        r2 = java.lang.Boolean.parseBoolean(r1);	 Catch:{ NumberFormatException -> 0x0095 }
        r3 = r4.zzyj;	 Catch:{ NumberFormatException -> 0x0095 }
        r3.zza(r0, r2);	 Catch:{ NumberFormatException -> 0x0095 }
        goto L_0x00ce;
    L_0x0095:
        r0 = move-exception;
        r2 = "Error parsing bool configuration value";
        r4.zzc(r2, r1, r0);	 Catch:{ IOException | XmlPullParserException -> 0x00d4, IOException | XmlPullParserException -> 0x00d4 }
        goto L_0x00ce;
    L_0x009c:
        r1 = "integer";
        r0 = r0.equals(r1);	 Catch:{ IOException | XmlPullParserException -> 0x00d4, IOException | XmlPullParserException -> 0x00d4 }
        if (r0 == 0) goto L_0x00ce;
    L_0x00a4:
        r0 = "name";
        r0 = r5.getAttributeValue(r2, r0);	 Catch:{ IOException | XmlPullParserException -> 0x00d4, IOException | XmlPullParserException -> 0x00d4 }
        r1 = r5.nextText();	 Catch:{ IOException | XmlPullParserException -> 0x00d4, IOException | XmlPullParserException -> 0x00d4 }
        r1 = r1.trim();	 Catch:{ IOException | XmlPullParserException -> 0x00d4, IOException | XmlPullParserException -> 0x00d4 }
        r2 = android.text.TextUtils.isEmpty(r0);	 Catch:{ IOException | XmlPullParserException -> 0x00d4, IOException | XmlPullParserException -> 0x00d4 }
        if (r2 != 0) goto L_0x00ce;
    L_0x00b8:
        r2 = android.text.TextUtils.isEmpty(r1);	 Catch:{ IOException | XmlPullParserException -> 0x00d4, IOException | XmlPullParserException -> 0x00d4 }
        if (r2 != 0) goto L_0x00ce;
    L_0x00be:
        r2 = java.lang.Integer.parseInt(r1);	 Catch:{ NumberFormatException -> 0x00c8 }
        r3 = r4.zzyj;	 Catch:{ NumberFormatException -> 0x00c8 }
        r3.zzb(r0, r2);	 Catch:{ NumberFormatException -> 0x00c8 }
        goto L_0x00ce;
    L_0x00c8:
        r0 = move-exception;
        r2 = "Error parsing int configuration value";
        r4.zzc(r2, r1, r0);	 Catch:{ IOException | XmlPullParserException -> 0x00d4, IOException | XmlPullParserException -> 0x00d4 }
    L_0x00ce:
        r0 = r5.next();	 Catch:{ IOException | XmlPullParserException -> 0x00d4, IOException | XmlPullParserException -> 0x00d4 }
        goto L_0x0007;
    L_0x00d4:
        r5 = move-exception;
        r0 = "Error parsing tracker configuration file";
        r4.zze(r0, r5);
    L_0x00da:
        r5 = r4.zzyj;
        r5 = r5.zzdv();
        return r5;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzbv.zza(android.content.res.XmlResourceParser):com.google.android.gms.internal.measurement.zzbu");
    }
}
