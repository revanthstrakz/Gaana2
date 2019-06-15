package com.google.android.gms.tagmanager;

import android.content.Context;
import android.content.res.Resources.NotFoundException;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.measurement.zzl;
import com.google.android.gms.internal.measurement.zzre;
import com.google.android.gms.internal.measurement.zzrg;
import com.google.android.gms.internal.measurement.zzrk;
import com.google.android.gms.internal.measurement.zzro;
import com.google.android.gms.internal.measurement.zzyh;
import com.google.android.gms.internal.measurement.zzyi;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONException;

final class zzex implements zzah {
    private final ExecutorService zzadl = Executors.newSingleThreadExecutor();
    private final String zzazo;
    private zzdh<zzre> zzbeq;
    private final Context zzri;

    zzex(Context context, String str) {
        this.zzri = context;
        this.zzazo = str;
    }

    public final void zza(zzdh<zzre> zzdh) {
        this.zzbeq = zzdh;
    }

    public final void zzny() {
        this.zzadl.execute(new zzey(this));
    }

    /* Access modifiers changed, original: final */
    /* JADX WARNING: Missing exception handler attribute for start block: B:30:0x009d */
    /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x0087 */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [B:27:0x0087, B:30:0x009d] */
    /* JADX WARNING: Missing block: B:28:?, code skipped:
            r3.zzbeq.zzu(com.google.android.gms.tagmanager.zzcz.zzbdf);
            com.google.android.gms.tagmanager.zzdi.zzab("Failed to read the resource from disk. The resource is inconsistent");
     */
    /* JADX WARNING: Missing block: B:31:?, code skipped:
            r3.zzbeq.zzu(com.google.android.gms.tagmanager.zzcz.zzbdf);
            com.google.android.gms.tagmanager.zzdi.zzab("Failed to read the resource from disk");
     */
    /* JADX WARNING: Missing block: B:33:?, code skipped:
            r0.close();
     */
    /* JADX WARNING: Missing block: B:34:0x00ad, code skipped:
            com.google.android.gms.tagmanager.zzdi.zzab("Error closing stream for reading resource from disk");
     */
    /* JADX WARNING: Missing block: B:39:?, code skipped:
            r0.close();
     */
    /* JADX WARNING: Missing block: B:40:0x00bc, code skipped:
            com.google.android.gms.tagmanager.zzdi.zzab("Error closing stream for reading resource from disk");
     */
    @com.google.android.gms.common.util.VisibleForTesting
    public final void zzpr() {
        /*
        r3 = this;
        r0 = r3.zzbeq;
        if (r0 != 0) goto L_0x000c;
    L_0x0004:
        r0 = new java.lang.IllegalStateException;
        r1 = "Callback must be set before execute";
        r0.<init>(r1);
        throw r0;
    L_0x000c:
        r0 = r3.zzbeq;
        r0.zznx();
        r0 = "Attempting to load resource from disk";
        com.google.android.gms.tagmanager.zzdi.v(r0);
        r0 = com.google.android.gms.tagmanager.zzeh.zzpm();
        r0 = r0.zzpn();
        r1 = com.google.android.gms.tagmanager.zzeh.zza.CONTAINER;
        if (r0 == r1) goto L_0x002e;
    L_0x0022:
        r0 = com.google.android.gms.tagmanager.zzeh.zzpm();
        r0 = r0.zzpn();
        r1 = com.google.android.gms.tagmanager.zzeh.zza.CONTAINER_DEBUG;
        if (r0 != r1) goto L_0x0046;
    L_0x002e:
        r0 = r3.zzazo;
        r1 = com.google.android.gms.tagmanager.zzeh.zzpm();
        r1 = r1.getContainerId();
        r0 = r0.equals(r1);
        if (r0 == 0) goto L_0x0046;
    L_0x003e:
        r0 = r3.zzbeq;
        r1 = com.google.android.gms.tagmanager.zzcz.zzbde;
        r0.zzu(r1);
        return;
    L_0x0046:
        r0 = new java.io.FileInputStream;	 Catch:{ FileNotFoundException -> 0x00c2 }
        r1 = r3.zzps();	 Catch:{ FileNotFoundException -> 0x00c2 }
        r0.<init>(r1);	 Catch:{ FileNotFoundException -> 0x00c2 }
        r1 = new java.io.ByteArrayOutputStream;	 Catch:{ IOException -> 0x009d, IllegalArgumentException -> 0x0087 }
        r1.<init>();	 Catch:{ IOException -> 0x009d, IllegalArgumentException -> 0x0087 }
        com.google.android.gms.internal.measurement.zzrg.zza(r0, r1);	 Catch:{ IOException -> 0x009d, IllegalArgumentException -> 0x0087 }
        r1 = r1.toByteArray();	 Catch:{ IOException -> 0x009d, IllegalArgumentException -> 0x0087 }
        r2 = new com.google.android.gms.internal.measurement.zzre;	 Catch:{ IOException -> 0x009d, IllegalArgumentException -> 0x0087 }
        r2.<init>();	 Catch:{ IOException -> 0x009d, IllegalArgumentException -> 0x0087 }
        r1 = com.google.android.gms.internal.measurement.zzyi.zza(r2, r1);	 Catch:{ IOException -> 0x009d, IllegalArgumentException -> 0x0087 }
        r1 = (com.google.android.gms.internal.measurement.zzre) r1;	 Catch:{ IOException -> 0x009d, IllegalArgumentException -> 0x0087 }
        r2 = r1.zzqg;	 Catch:{ IOException -> 0x009d, IllegalArgumentException -> 0x0087 }
        if (r2 != 0) goto L_0x0076;
    L_0x006a:
        r2 = r1.zzbqd;	 Catch:{ IOException -> 0x009d, IllegalArgumentException -> 0x0087 }
        if (r2 != 0) goto L_0x0076;
    L_0x006e:
        r1 = new java.lang.IllegalArgumentException;	 Catch:{ IOException -> 0x009d, IllegalArgumentException -> 0x0087 }
        r2 = "Resource and SupplementedResource are NULL.";
        r1.<init>(r2);	 Catch:{ IOException -> 0x009d, IllegalArgumentException -> 0x0087 }
        throw r1;	 Catch:{ IOException -> 0x009d, IllegalArgumentException -> 0x0087 }
    L_0x0076:
        r2 = r3.zzbeq;	 Catch:{ IOException -> 0x009d, IllegalArgumentException -> 0x0087 }
        r2.onSuccess(r1);	 Catch:{ IOException -> 0x009d, IllegalArgumentException -> 0x0087 }
        r0.close();	 Catch:{ IOException -> 0x007f }
        goto L_0x00b2;
    L_0x007f:
        r0 = "Error closing stream for reading resource from disk";
        com.google.android.gms.tagmanager.zzdi.zzab(r0);
        goto L_0x00b2;
    L_0x0085:
        r1 = move-exception;
        goto L_0x00b8;
    L_0x0087:
        r1 = r3.zzbeq;	 Catch:{ all -> 0x0085 }
        r2 = com.google.android.gms.tagmanager.zzcz.zzbdf;	 Catch:{ all -> 0x0085 }
        r1.zzu(r2);	 Catch:{ all -> 0x0085 }
        r1 = "Failed to read the resource from disk. The resource is inconsistent";
        com.google.android.gms.tagmanager.zzdi.zzab(r1);	 Catch:{ all -> 0x0085 }
        r0.close();	 Catch:{ IOException -> 0x0097 }
        goto L_0x00b2;
    L_0x0097:
        r0 = "Error closing stream for reading resource from disk";
        com.google.android.gms.tagmanager.zzdi.zzab(r0);
        goto L_0x00b2;
    L_0x009d:
        r1 = r3.zzbeq;	 Catch:{ all -> 0x0085 }
        r2 = com.google.android.gms.tagmanager.zzcz.zzbdf;	 Catch:{ all -> 0x0085 }
        r1.zzu(r2);	 Catch:{ all -> 0x0085 }
        r1 = "Failed to read the resource from disk";
        com.google.android.gms.tagmanager.zzdi.zzab(r1);	 Catch:{ all -> 0x0085 }
        r0.close();	 Catch:{ IOException -> 0x00ad }
        goto L_0x00b2;
    L_0x00ad:
        r0 = "Error closing stream for reading resource from disk";
        com.google.android.gms.tagmanager.zzdi.zzab(r0);
    L_0x00b2:
        r0 = "The Disk resource was successfully read.";
        com.google.android.gms.tagmanager.zzdi.v(r0);
        return;
    L_0x00b8:
        r0.close();	 Catch:{ IOException -> 0x00bc }
        goto L_0x00c1;
    L_0x00bc:
        r0 = "Error closing stream for reading resource from disk";
        com.google.android.gms.tagmanager.zzdi.zzab(r0);
    L_0x00c1:
        throw r1;
    L_0x00c2:
        r0 = "Failed to find the resource in the disk";
        com.google.android.gms.tagmanager.zzdi.zzdn(r0);
        r0 = r3.zzbeq;
        r1 = com.google.android.gms.tagmanager.zzcz.zzbde;
        r0.zzu(r1);
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zzex.zzpr():void");
    }

    public final void zza(zzre zzre) {
        this.zzadl.execute(new zzez(this, zzre));
    }

    public final zzrk zzv(int i) {
        try {
            InputStream openRawResource = this.zzri.getResources().openRawResource(i);
            String resourceName = this.zzri.getResources().getResourceName(i);
            StringBuilder stringBuilder = new StringBuilder(66 + String.valueOf(resourceName).length());
            stringBuilder.append("Attempting to load a container from the resource ID ");
            stringBuilder.append(i);
            stringBuilder.append(" (");
            stringBuilder.append(resourceName);
            stringBuilder.append(")");
            zzdi.v(stringBuilder.toString());
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                zzrg.zza(openRawResource, byteArrayOutputStream);
                zzrk zza = zza(byteArrayOutputStream);
                if (zza == null) {
                    return zze(byteArrayOutputStream.toByteArray());
                }
                zzdi.v("The container was successfully loaded from the resource (using JSON file format)");
                return zza;
            } catch (IOException unused) {
                String resourceName2 = this.zzri.getResources().getResourceName(i);
                StringBuilder stringBuilder2 = new StringBuilder(67 + String.valueOf(resourceName2).length());
                stringBuilder2.append("Error reading the default container with resource ID ");
                stringBuilder2.append(i);
                stringBuilder2.append(" (");
                stringBuilder2.append(resourceName2);
                stringBuilder2.append(")");
                zzdi.zzab(stringBuilder2.toString());
                return null;
            }
        } catch (NotFoundException unused2) {
            StringBuilder stringBuilder3 = new StringBuilder(98);
            stringBuilder3.append("Failed to load the container. No default container resource found with the resource ID ");
            stringBuilder3.append(i);
            zzdi.zzab(stringBuilder3.toString());
            return null;
        }
    }

    private static zzrk zza(ByteArrayOutputStream byteArrayOutputStream) {
        try {
            return zzda.zzdv(byteArrayOutputStream.toString("UTF-8"));
        } catch (UnsupportedEncodingException unused) {
            zzdi.zzdn("Failed to convert binary resource to string for JSON parsing; the file format is not UTF-8 format.");
            return null;
        } catch (JSONException unused2) {
            zzdi.zzab("Failed to extract the container from the resource file. Resource is a UTF-8 encoded string but doesn't contain a JSON container");
            return null;
        }
    }

    private static zzrk zze(byte[] bArr) {
        try {
            zzrk zza = zzrg.zza((zzl) zzyi.zza(new zzl(), bArr));
            if (zza != null) {
                zzdi.v("The container was successfully loaded from the resource (using binary file)");
            }
            return zza;
        } catch (zzyh unused) {
            zzdi.e("The resource file is corrupted. The container cannot be extracted from the binary file");
            return null;
        } catch (zzro unused2) {
            zzdi.zzab("The resource file is invalid. The container from the binary file is invalid");
            return null;
        }
    }

    public final synchronized void release() {
        this.zzadl.shutdown();
    }

    /* Access modifiers changed, original: final */
    /* JADX WARNING: Missing exception handler attribute for start block: B:12:0x0024 */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Can't wrap try/catch for region: R(5:12|13|14|24|15) */
    /* JADX WARNING: Missing block: B:13:?, code skipped:
            com.google.android.gms.tagmanager.zzdi.zzab("Error writing resource to disk. Removing resource from disk.");
            r0.delete();
     */
    /* JADX WARNING: Missing block: B:15:0x0035, code skipped:
            return false;
     */
    /* JADX WARNING: Missing block: B:17:?, code skipped:
            r2.close();
     */
    /* JADX WARNING: Missing block: B:18:0x003a, code skipped:
            com.google.android.gms.tagmanager.zzdi.zzab("error closing stream for writing resource to disk");
     */
    @com.google.android.gms.common.util.VisibleForTesting
    public final boolean zzb(com.google.android.gms.internal.measurement.zzre r6) {
        /*
        r5 = this;
        r0 = r5.zzps();
        r1 = 0;
        r2 = new java.io.FileOutputStream;	 Catch:{ FileNotFoundException -> 0x0040 }
        r2.<init>(r0);	 Catch:{ FileNotFoundException -> 0x0040 }
        r3 = r6.zzvx();	 Catch:{ IOException -> 0x0024 }
        r3 = new byte[r3];	 Catch:{ IOException -> 0x0024 }
        r4 = r3.length;	 Catch:{ IOException -> 0x0024 }
        com.google.android.gms.internal.measurement.zzyi.zza(r6, r3, r1, r4);	 Catch:{ IOException -> 0x0024 }
        r2.write(r3);	 Catch:{ IOException -> 0x0024 }
        r2.close();	 Catch:{ IOException -> 0x001b }
        goto L_0x0020;
    L_0x001b:
        r6 = "error closing stream for writing resource to disk";
        com.google.android.gms.tagmanager.zzdi.zzab(r6);
    L_0x0020:
        r6 = 1;
        return r6;
    L_0x0022:
        r6 = move-exception;
        goto L_0x0036;
    L_0x0024:
        r6 = "Error writing resource to disk. Removing resource from disk.";
        com.google.android.gms.tagmanager.zzdi.zzab(r6);	 Catch:{ all -> 0x0022 }
        r0.delete();	 Catch:{ all -> 0x0022 }
        r2.close();	 Catch:{ IOException -> 0x0030 }
        goto L_0x0035;
    L_0x0030:
        r6 = "error closing stream for writing resource to disk";
        com.google.android.gms.tagmanager.zzdi.zzab(r6);
    L_0x0035:
        return r1;
    L_0x0036:
        r2.close();	 Catch:{ IOException -> 0x003a }
        goto L_0x003f;
    L_0x003a:
        r0 = "error closing stream for writing resource to disk";
        com.google.android.gms.tagmanager.zzdi.zzab(r0);
    L_0x003f:
        throw r6;
    L_0x0040:
        r6 = "Error opening resource file for writing";
        com.google.android.gms.tagmanager.zzdi.e(r6);
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zzex.zzb(com.google.android.gms.internal.measurement.zzre):boolean");
    }

    @VisibleForTesting
    private final File zzps() {
        String valueOf = String.valueOf("resource_");
        String valueOf2 = String.valueOf(this.zzazo);
        return new File(this.zzri.getDir("google_tagmanager", 0), valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf));
    }
}
