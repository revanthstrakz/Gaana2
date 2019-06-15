package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.measurement.zzo;
import com.google.android.gms.internal.measurement.zzrs;

final class zzer implements Runnable {
    private final String zzazo;
    private volatile String zzbap;
    private final zzrs zzbeo;
    private final String zzbep;
    private zzdh<zzo> zzbeq;
    private volatile zzal zzber;
    private volatile String zzbes;
    private final Context zzri;

    public zzer(Context context, String str, zzal zzal) {
        this(context, str, new zzrs(), zzal);
    }

    @VisibleForTesting
    private zzer(Context context, String str, zzrs zzrs, zzal zzal) {
        this.zzri = context;
        this.zzbeo = zzrs;
        this.zzazo = str;
        this.zzber = zzal;
        String valueOf = String.valueOf("/r?id=");
        str = String.valueOf(str);
        this.zzbep = str.length() != 0 ? valueOf.concat(str) : new String(valueOf);
        this.zzbap = this.zzbep;
        this.zzbes = null;
    }

    /* JADX WARNING: Unknown top exception splitter block from list: {B:58:0x01f5=Splitter:B:58:0x01f5, B:34:0x0127=Splitter:B:34:0x0127} */
    /* JADX WARNING: Missing exception handler attribute for start block: B:58:0x01f5 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:34:0x0127 */
    public final void run() {
        /*
        r6 = this;
        r0 = r6.zzbeq;
        if (r0 != 0) goto L_0x000c;
    L_0x0004:
        r0 = new java.lang.IllegalStateException;
        r1 = "callback must be set before execute";
        r0.<init>(r1);
        throw r0;
    L_0x000c:
        r0 = r6.zzbeq;
        r0.zznx();
        r0 = r6.zzri;
        r1 = "connectivity";
        r0 = r0.getSystemService(r1);
        r0 = (android.net.ConnectivityManager) r0;
        r0 = r0.getActiveNetworkInfo();
        if (r0 == 0) goto L_0x002a;
    L_0x0021:
        r0 = r0.isConnected();
        if (r0 != 0) goto L_0x0028;
    L_0x0027:
        goto L_0x002a;
    L_0x0028:
        r0 = 1;
        goto L_0x0030;
    L_0x002a:
        r0 = "...no network connectivity";
        com.google.android.gms.tagmanager.zzdi.v(r0);
        r0 = 0;
    L_0x0030:
        if (r0 != 0) goto L_0x003a;
    L_0x0032:
        r0 = r6.zzbeq;
        r1 = com.google.android.gms.tagmanager.zzcz.zzbde;
        r0.zzu(r1);
        return;
    L_0x003a:
        r0 = "Start loading resource from network ...";
        com.google.android.gms.tagmanager.zzdi.v(r0);
        r0 = r6.zzber;
        r0 = r0.zzoe();
        r1 = r6.zzbap;
        r2 = 12;
        r3 = java.lang.String.valueOf(r0);
        r3 = r3.length();
        r2 = r2 + r3;
        r3 = java.lang.String.valueOf(r1);
        r3 = r3.length();
        r2 = r2 + r3;
        r3 = new java.lang.StringBuilder;
        r3.<init>(r2);
        r3.append(r0);
        r3.append(r1);
        r0 = "&v=a65833898";
        r3.append(r0);
        r0 = r3.toString();
        r1 = r6.zzbes;
        if (r1 == 0) goto L_0x00ae;
    L_0x0073:
        r1 = r6.zzbes;
        r1 = r1.trim();
        r2 = "";
        r1 = r1.equals(r2);
        if (r1 != 0) goto L_0x00ae;
    L_0x0081:
        r0 = java.lang.String.valueOf(r0);
        r1 = r6.zzbes;
        r2 = 4;
        r3 = java.lang.String.valueOf(r0);
        r3 = r3.length();
        r2 = r2 + r3;
        r3 = java.lang.String.valueOf(r1);
        r3 = r3.length();
        r2 = r2 + r3;
        r3 = new java.lang.StringBuilder;
        r3.<init>(r2);
        r3.append(r0);
        r0 = "&pv=";
        r3.append(r0);
        r3.append(r1);
        r0 = r3.toString();
    L_0x00ae:
        r1 = com.google.android.gms.tagmanager.zzeh.zzpm();
        r1 = r1.zzpn();
        r2 = com.google.android.gms.tagmanager.zzeh.zza.CONTAINER_DEBUG;
        r1 = r1.equals(r2);
        if (r1 == 0) goto L_0x00d9;
    L_0x00be:
        r0 = java.lang.String.valueOf(r0);
        r1 = "&gtm_debug=x";
        r1 = java.lang.String.valueOf(r1);
        r2 = r1.length();
        if (r2 == 0) goto L_0x00d3;
    L_0x00ce:
        r0 = r0.concat(r1);
        goto L_0x00d9;
    L_0x00d3:
        r1 = new java.lang.String;
        r1.<init>(r0);
        r0 = r1;
    L_0x00d9:
        r1 = com.google.android.gms.internal.measurement.zzrs.zzth();
        r2 = 0;
        r3 = r1.zzez(r0);	 Catch:{ FileNotFoundException -> 0x01f5, zzrt -> 0x0127, IOException -> 0x00e7 }
        r2 = r3;
        goto L_0x0148;
    L_0x00e4:
        r0 = move-exception;
        goto L_0x0237;
    L_0x00e7:
        r2 = move-exception;
        r3 = r2.getMessage();	 Catch:{ all -> 0x00e4 }
        r4 = 40;
        r5 = java.lang.String.valueOf(r0);	 Catch:{ all -> 0x00e4 }
        r5 = r5.length();	 Catch:{ all -> 0x00e4 }
        r4 = r4 + r5;
        r5 = java.lang.String.valueOf(r3);	 Catch:{ all -> 0x00e4 }
        r5 = r5.length();	 Catch:{ all -> 0x00e4 }
        r4 = r4 + r5;
        r5 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00e4 }
        r5.<init>(r4);	 Catch:{ all -> 0x00e4 }
        r4 = "Error when loading resources from url: ";
        r5.append(r4);	 Catch:{ all -> 0x00e4 }
        r5.append(r0);	 Catch:{ all -> 0x00e4 }
        r0 = " ";
        r5.append(r0);	 Catch:{ all -> 0x00e4 }
        r5.append(r3);	 Catch:{ all -> 0x00e4 }
        r0 = r5.toString();	 Catch:{ all -> 0x00e4 }
        com.google.android.gms.tagmanager.zzdi.zzb(r0, r2);	 Catch:{ all -> 0x00e4 }
        r0 = r6.zzbeq;	 Catch:{ all -> 0x00e4 }
        r2 = com.google.android.gms.tagmanager.zzcz.zzbdf;	 Catch:{ all -> 0x00e4 }
        r0.zzu(r2);	 Catch:{ all -> 0x00e4 }
        r1.close();
        return;
    L_0x0127:
        r3 = "Error when loading resource for url: ";
        r4 = java.lang.String.valueOf(r0);	 Catch:{ all -> 0x00e4 }
        r5 = r4.length();	 Catch:{ all -> 0x00e4 }
        if (r5 == 0) goto L_0x0138;
    L_0x0133:
        r3 = r3.concat(r4);	 Catch:{ all -> 0x00e4 }
        goto L_0x013e;
    L_0x0138:
        r4 = new java.lang.String;	 Catch:{ all -> 0x00e4 }
        r4.<init>(r3);	 Catch:{ all -> 0x00e4 }
        r3 = r4;
    L_0x013e:
        com.google.android.gms.tagmanager.zzdi.zzab(r3);	 Catch:{ all -> 0x00e4 }
        r3 = r6.zzbeq;	 Catch:{ all -> 0x00e4 }
        r4 = com.google.android.gms.tagmanager.zzcz.zzbdh;	 Catch:{ all -> 0x00e4 }
        r3.zzu(r4);	 Catch:{ all -> 0x00e4 }
    L_0x0148:
        r3 = new java.io.ByteArrayOutputStream;	 Catch:{ IOException -> 0x01b5 }
        r3.<init>();	 Catch:{ IOException -> 0x01b5 }
        com.google.android.gms.internal.measurement.zzrg.zza(r2, r3);	 Catch:{ IOException -> 0x01b5 }
        r2 = r3.toByteArray();	 Catch:{ IOException -> 0x01b5 }
        r3 = new com.google.android.gms.internal.measurement.zzo;	 Catch:{ IOException -> 0x01b5 }
        r3.<init>();	 Catch:{ IOException -> 0x01b5 }
        r2 = com.google.android.gms.internal.measurement.zzyi.zza(r3, r2);	 Catch:{ IOException -> 0x01b5 }
        r2 = (com.google.android.gms.internal.measurement.zzo) r2;	 Catch:{ IOException -> 0x01b5 }
        r3 = java.lang.String.valueOf(r2);	 Catch:{ IOException -> 0x01b5 }
        r4 = 43;
        r5 = java.lang.String.valueOf(r3);	 Catch:{ IOException -> 0x01b5 }
        r5 = r5.length();	 Catch:{ IOException -> 0x01b5 }
        r4 = r4 + r5;
        r5 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x01b5 }
        r5.<init>(r4);	 Catch:{ IOException -> 0x01b5 }
        r4 = "Successfully loaded supplemented resource: ";
        r5.append(r4);	 Catch:{ IOException -> 0x01b5 }
        r5.append(r3);	 Catch:{ IOException -> 0x01b5 }
        r3 = r5.toString();	 Catch:{ IOException -> 0x01b5 }
        com.google.android.gms.tagmanager.zzdi.v(r3);	 Catch:{ IOException -> 0x01b5 }
        r3 = r2.zzqg;	 Catch:{ IOException -> 0x01b5 }
        if (r3 != 0) goto L_0x01a7;
    L_0x0186:
        r3 = r2.zzqf;	 Catch:{ IOException -> 0x01b5 }
        r3 = r3.length;	 Catch:{ IOException -> 0x01b5 }
        if (r3 != 0) goto L_0x01a7;
    L_0x018b:
        r3 = "No change for container: ";
        r4 = r6.zzazo;	 Catch:{ IOException -> 0x01b5 }
        r4 = java.lang.String.valueOf(r4);	 Catch:{ IOException -> 0x01b5 }
        r5 = r4.length();	 Catch:{ IOException -> 0x01b5 }
        if (r5 == 0) goto L_0x019e;
    L_0x0199:
        r3 = r3.concat(r4);	 Catch:{ IOException -> 0x01b5 }
        goto L_0x01a4;
    L_0x019e:
        r4 = new java.lang.String;	 Catch:{ IOException -> 0x01b5 }
        r4.<init>(r3);	 Catch:{ IOException -> 0x01b5 }
        r3 = r4;
    L_0x01a4:
        com.google.android.gms.tagmanager.zzdi.v(r3);	 Catch:{ IOException -> 0x01b5 }
    L_0x01a7:
        r3 = r6.zzbeq;	 Catch:{ IOException -> 0x01b5 }
        r3.onSuccess(r2);	 Catch:{ IOException -> 0x01b5 }
        r1.close();
        r0 = "Load resource from network finished.";
        com.google.android.gms.tagmanager.zzdi.v(r0);
        return;
    L_0x01b5:
        r2 = move-exception;
        r3 = r2.getMessage();	 Catch:{ all -> 0x00e4 }
        r4 = 51;
        r5 = java.lang.String.valueOf(r0);	 Catch:{ all -> 0x00e4 }
        r5 = r5.length();	 Catch:{ all -> 0x00e4 }
        r4 = r4 + r5;
        r5 = java.lang.String.valueOf(r3);	 Catch:{ all -> 0x00e4 }
        r5 = r5.length();	 Catch:{ all -> 0x00e4 }
        r4 = r4 + r5;
        r5 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00e4 }
        r5.<init>(r4);	 Catch:{ all -> 0x00e4 }
        r4 = "Error when parsing downloaded resources from url: ";
        r5.append(r4);	 Catch:{ all -> 0x00e4 }
        r5.append(r0);	 Catch:{ all -> 0x00e4 }
        r0 = " ";
        r5.append(r0);	 Catch:{ all -> 0x00e4 }
        r5.append(r3);	 Catch:{ all -> 0x00e4 }
        r0 = r5.toString();	 Catch:{ all -> 0x00e4 }
        com.google.android.gms.tagmanager.zzdi.zzb(r0, r2);	 Catch:{ all -> 0x00e4 }
        r0 = r6.zzbeq;	 Catch:{ all -> 0x00e4 }
        r2 = com.google.android.gms.tagmanager.zzcz.zzbdg;	 Catch:{ all -> 0x00e4 }
        r0.zzu(r2);	 Catch:{ all -> 0x00e4 }
        r1.close();
        return;
    L_0x01f5:
        r2 = r6.zzazo;	 Catch:{ all -> 0x00e4 }
        r3 = 79;
        r4 = java.lang.String.valueOf(r0);	 Catch:{ all -> 0x00e4 }
        r4 = r4.length();	 Catch:{ all -> 0x00e4 }
        r3 = r3 + r4;
        r4 = java.lang.String.valueOf(r2);	 Catch:{ all -> 0x00e4 }
        r4 = r4.length();	 Catch:{ all -> 0x00e4 }
        r3 = r3 + r4;
        r4 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00e4 }
        r4.<init>(r3);	 Catch:{ all -> 0x00e4 }
        r3 = "No data is retrieved from the given url: ";
        r4.append(r3);	 Catch:{ all -> 0x00e4 }
        r4.append(r0);	 Catch:{ all -> 0x00e4 }
        r0 = ". Make sure container_id: ";
        r4.append(r0);	 Catch:{ all -> 0x00e4 }
        r4.append(r2);	 Catch:{ all -> 0x00e4 }
        r0 = " is correct.";
        r4.append(r0);	 Catch:{ all -> 0x00e4 }
        r0 = r4.toString();	 Catch:{ all -> 0x00e4 }
        com.google.android.gms.tagmanager.zzdi.zzab(r0);	 Catch:{ all -> 0x00e4 }
        r0 = r6.zzbeq;	 Catch:{ all -> 0x00e4 }
        r2 = com.google.android.gms.tagmanager.zzcz.zzbdg;	 Catch:{ all -> 0x00e4 }
        r0.zzu(r2);	 Catch:{ all -> 0x00e4 }
        r1.close();
        return;
    L_0x0237:
        r1.close();
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zzer.run():void");
    }

    /* Access modifiers changed, original: final */
    public final void zza(zzdh<zzo> zzdh) {
        this.zzbeq = zzdh;
    }

    /* Access modifiers changed, original: final */
    @VisibleForTesting
    public final void zzdg(String str) {
        if (str == null) {
            this.zzbap = this.zzbep;
            return;
        }
        String str2 = "Setting CTFE URL path: ";
        String valueOf = String.valueOf(str);
        zzdi.zzdn(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
        this.zzbap = str;
    }

    /* Access modifiers changed, original: final */
    @VisibleForTesting
    public final void zzdy(String str) {
        String str2 = "Setting previous container version: ";
        String valueOf = String.valueOf(str);
        zzdi.zzdn(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
        this.zzbes = str;
    }
}
