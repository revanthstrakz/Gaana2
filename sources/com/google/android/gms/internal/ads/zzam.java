package com.google.android.gms.internal.ads;

import android.text.TextUtils;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public final class zzam implements zzb {
    private final Map<String, zzan> zzbw;
    private long zzbx;
    private final File zzby;
    private final int zzbz;

    public zzam(File file, int i) {
        this.zzbw = new LinkedHashMap(16, 0.75f, true);
        this.zzbx = 0;
        this.zzby = file;
        this.zzbz = i;
    }

    public zzam(File file) {
        this(file, 5242880);
    }

    public final synchronized zzc zza(String str) {
        zzan zzan = (zzan) this.zzbw.get(str);
        if (zzan == null) {
            return null;
        }
        File zze = zze(str);
        zzao zzao;
        try {
            zzao = new zzao(new BufferedInputStream(zza(zze)), zze.length());
            if (TextUtils.equals(str, zzan.zzc(zzao).zzcb)) {
                byte[] zza = zza(zzao, zzao.zzp());
                zzc zzc = new zzc();
                zzc.data = zza;
                zzc.zza = zzan.zza;
                zzc.zzb = zzan.zzb;
                zzc.zzc = zzan.zzc;
                zzc.zzd = zzan.zzd;
                zzc.zze = zzan.zze;
                List<zzl> list = zzan.zzg;
                TreeMap treeMap = new TreeMap(String.CASE_INSENSITIVE_ORDER);
                for (zzl zzl : list) {
                    treeMap.put(zzl.getName(), zzl.getValue());
                }
                zzc.zzf = treeMap;
                zzc.zzg = Collections.unmodifiableList(zzan.zzg);
                zzao.close();
                return zzc;
            }
            zzaf.d("%s: key=%s, found=%s", zze.getAbsolutePath(), str, zzan.zzc(zzao).zzcb);
            removeEntry(str);
            zzao.close();
            return null;
        } catch (IOException e) {
            zzaf.d("%s: %s", zze.getAbsolutePath(), e.toString());
            remove(str);
            return null;
        } catch (Throwable th) {
            zzao.close();
        }
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x0059 */
    /* JADX WARNING: Missing block: B:8:0x0023, code skipped:
            return;
     */
    public final synchronized void zza() {
        /*
        r9 = this;
        monitor-enter(r9);
        r0 = r9.zzby;	 Catch:{ all -> 0x0061 }
        r0 = r0.exists();	 Catch:{ all -> 0x0061 }
        r1 = 0;
        if (r0 != 0) goto L_0x0024;
    L_0x000a:
        r0 = r9.zzby;	 Catch:{ all -> 0x0061 }
        r0 = r0.mkdirs();	 Catch:{ all -> 0x0061 }
        if (r0 != 0) goto L_0x0022;
    L_0x0012:
        r0 = "Unable to create cache dir %s";
        r2 = 1;
        r2 = new java.lang.Object[r2];	 Catch:{ all -> 0x0061 }
        r3 = r9.zzby;	 Catch:{ all -> 0x0061 }
        r3 = r3.getAbsolutePath();	 Catch:{ all -> 0x0061 }
        r2[r1] = r3;	 Catch:{ all -> 0x0061 }
        com.google.android.gms.internal.ads.zzaf.e(r0, r2);	 Catch:{ all -> 0x0061 }
    L_0x0022:
        monitor-exit(r9);
        return;
    L_0x0024:
        r0 = r9.zzby;	 Catch:{ all -> 0x0061 }
        r0 = r0.listFiles();	 Catch:{ all -> 0x0061 }
        if (r0 != 0) goto L_0x002e;
    L_0x002c:
        monitor-exit(r9);
        return;
    L_0x002e:
        r2 = r0.length;	 Catch:{ all -> 0x0061 }
    L_0x002f:
        if (r1 >= r2) goto L_0x005f;
    L_0x0031:
        r3 = r0[r1];	 Catch:{ all -> 0x0061 }
        r4 = r3.length();	 Catch:{ IOException -> 0x0059 }
        r6 = new com.google.android.gms.internal.ads.zzao;	 Catch:{ IOException -> 0x0059 }
        r7 = new java.io.BufferedInputStream;	 Catch:{ IOException -> 0x0059 }
        r8 = zza(r3);	 Catch:{ IOException -> 0x0059 }
        r7.<init>(r8);	 Catch:{ IOException -> 0x0059 }
        r6.<init>(r7, r4);	 Catch:{ IOException -> 0x0059 }
        r7 = com.google.android.gms.internal.ads.zzan.zzc(r6);	 Catch:{ all -> 0x0054 }
        r7.zzca = r4;	 Catch:{ all -> 0x0054 }
        r4 = r7.zzcb;	 Catch:{ all -> 0x0054 }
        r9.zza(r4, r7);	 Catch:{ all -> 0x0054 }
        r6.close();	 Catch:{ IOException -> 0x0059 }
        goto L_0x005c;
    L_0x0054:
        r4 = move-exception;
        r6.close();	 Catch:{ IOException -> 0x0059 }
        throw r4;	 Catch:{ IOException -> 0x0059 }
    L_0x0059:
        r3.delete();	 Catch:{ all -> 0x0061 }
    L_0x005c:
        r1 = r1 + 1;
        goto L_0x002f;
    L_0x005f:
        monitor-exit(r9);
        return;
    L_0x0061:
        r0 = move-exception;
        monitor-exit(r9);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzam.zza():void");
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:33:0x0104 */
    /* JADX WARNING: Can't wrap try/catch for region: R(5:33|34|(1:36)|37|38) */
    /* JADX WARNING: Missing block: B:35:0x0108, code skipped:
            if (r3.delete() == false) goto L_0x010a;
     */
    /* JADX WARNING: Missing block: B:36:0x010a, code skipped:
            com.google.android.gms.internal.ads.zzaf.d("Could not clean up file %s", r3.getAbsolutePath());
     */
    /* JADX WARNING: Missing block: B:38:0x011a, code skipped:
            return;
     */
    public final synchronized void zza(java.lang.String r20, com.google.android.gms.internal.ads.zzc r21) {
        /*
        r19 = this;
        r1 = r19;
        r2 = r20;
        r3 = r21;
        monitor-enter(r19);
        r4 = r3.data;	 Catch:{ all -> 0x011b }
        r4 = r4.length;	 Catch:{ all -> 0x011b }
        r5 = r1.zzbx;	 Catch:{ all -> 0x011b }
        r7 = (long) r4;	 Catch:{ all -> 0x011b }
        r9 = r5 + r7;
        r4 = r1.zzbz;	 Catch:{ all -> 0x011b }
        r4 = (long) r4;	 Catch:{ all -> 0x011b }
        r6 = (r9 > r4 ? 1 : (r9 == r4 ? 0 : -1));
        r5 = 0;
        if (r6 < 0) goto L_0x00c4;
    L_0x0017:
        r6 = com.google.android.gms.internal.ads.zzaf.DEBUG;	 Catch:{ all -> 0x011b }
        if (r6 == 0) goto L_0x0022;
    L_0x001b:
        r6 = "Pruning old cache entries.";
        r9 = new java.lang.Object[r5];	 Catch:{ all -> 0x011b }
        com.google.android.gms.internal.ads.zzaf.v(r6, r9);	 Catch:{ all -> 0x011b }
    L_0x0022:
        r9 = r1.zzbx;	 Catch:{ all -> 0x011b }
        r11 = android.os.SystemClock.elapsedRealtime();	 Catch:{ all -> 0x011b }
        r6 = r1.zzbw;	 Catch:{ all -> 0x011b }
        r6 = r6.entrySet();	 Catch:{ all -> 0x011b }
        r6 = r6.iterator();	 Catch:{ all -> 0x011b }
        r13 = r5;
    L_0x0033:
        r14 = r6.hasNext();	 Catch:{ all -> 0x011b }
        r15 = 2;
        if (r14 == 0) goto L_0x0098;
    L_0x003a:
        r14 = r6.next();	 Catch:{ all -> 0x011b }
        r14 = (java.util.Map.Entry) r14;	 Catch:{ all -> 0x011b }
        r14 = r14.getValue();	 Catch:{ all -> 0x011b }
        r14 = (com.google.android.gms.internal.ads.zzan) r14;	 Catch:{ all -> 0x011b }
        r4 = r14.zzcb;	 Catch:{ all -> 0x011b }
        r4 = r1.zze(r4);	 Catch:{ all -> 0x011b }
        r4 = r4.delete();	 Catch:{ all -> 0x011b }
        if (r4 == 0) goto L_0x0061;
    L_0x0052:
        r16 = r6;
        r5 = r1.zzbx;	 Catch:{ all -> 0x011b }
        r3 = r14.zzca;	 Catch:{ all -> 0x011b }
        r17 = r11;
        r11 = r5 - r3;
        r1.zzbx = r11;	 Catch:{ all -> 0x011b }
    L_0x005e:
        r3 = r16;
        goto L_0x007b;
    L_0x0061:
        r16 = r6;
        r17 = r11;
        r3 = "Could not delete cache entry for key=%s, filename=%s";
        r4 = new java.lang.Object[r15];	 Catch:{ all -> 0x011b }
        r5 = r14.zzcb;	 Catch:{ all -> 0x011b }
        r6 = 0;
        r4[r6] = r5;	 Catch:{ all -> 0x011b }
        r5 = r14.zzcb;	 Catch:{ all -> 0x011b }
        r5 = zzd(r5);	 Catch:{ all -> 0x011b }
        r6 = 1;
        r4[r6] = r5;	 Catch:{ all -> 0x011b }
        com.google.android.gms.internal.ads.zzaf.d(r3, r4);	 Catch:{ all -> 0x011b }
        goto L_0x005e;
    L_0x007b:
        r3.remove();	 Catch:{ all -> 0x011b }
        r13 = r13 + 1;
        r4 = r1.zzbx;	 Catch:{ all -> 0x011b }
        r11 = r4 + r7;
        r4 = (float) r11;	 Catch:{ all -> 0x011b }
        r5 = r1.zzbz;	 Catch:{ all -> 0x011b }
        r5 = (float) r5;	 Catch:{ all -> 0x011b }
        r6 = 1063675494; // 0x3f666666 float:0.9 double:5.2552552E-315;
        r5 = r5 * r6;
        r4 = (r4 > r5 ? 1 : (r4 == r5 ? 0 : -1));
        if (r4 >= 0) goto L_0x0091;
    L_0x0090:
        goto L_0x009a;
    L_0x0091:
        r6 = r3;
        r11 = r17;
        r3 = r21;
        r5 = 0;
        goto L_0x0033;
    L_0x0098:
        r17 = r11;
    L_0x009a:
        r3 = com.google.android.gms.internal.ads.zzaf.DEBUG;	 Catch:{ all -> 0x011b }
        if (r3 == 0) goto L_0x00c4;
    L_0x009e:
        r3 = "pruned %d files, %d bytes, %d ms";
        r4 = 3;
        r4 = new java.lang.Object[r4];	 Catch:{ all -> 0x011b }
        r5 = java.lang.Integer.valueOf(r13);	 Catch:{ all -> 0x011b }
        r6 = 0;
        r4[r6] = r5;	 Catch:{ all -> 0x011b }
        r5 = r1.zzbx;	 Catch:{ all -> 0x011b }
        r7 = r5 - r9;
        r5 = java.lang.Long.valueOf(r7);	 Catch:{ all -> 0x011b }
        r6 = 1;
        r4[r6] = r5;	 Catch:{ all -> 0x011b }
        r5 = android.os.SystemClock.elapsedRealtime();	 Catch:{ all -> 0x011b }
        r7 = r5 - r17;
        r5 = java.lang.Long.valueOf(r7);	 Catch:{ all -> 0x011b }
        r4[r15] = r5;	 Catch:{ all -> 0x011b }
        com.google.android.gms.internal.ads.zzaf.v(r3, r4);	 Catch:{ all -> 0x011b }
    L_0x00c4:
        r3 = r19.zze(r20);	 Catch:{ all -> 0x011b }
        r4 = new java.io.BufferedOutputStream;	 Catch:{ IOException -> 0x0104 }
        r5 = new java.io.FileOutputStream;	 Catch:{ IOException -> 0x0104 }
        r5.<init>(r3);	 Catch:{ IOException -> 0x0104 }
        r4.<init>(r5);	 Catch:{ IOException -> 0x0104 }
        r5 = new com.google.android.gms.internal.ads.zzan;	 Catch:{ IOException -> 0x0104 }
        r6 = r21;
        r5.<init>(r2, r6);	 Catch:{ IOException -> 0x0104 }
        r7 = r5.zza(r4);	 Catch:{ IOException -> 0x0104 }
        if (r7 != 0) goto L_0x00f7;
    L_0x00df:
        r4.close();	 Catch:{ IOException -> 0x0104 }
        r2 = "Failed to write header for %s";
        r4 = 1;
        r5 = new java.lang.Object[r4];	 Catch:{ IOException -> 0x0104 }
        r4 = r3.getAbsolutePath();	 Catch:{ IOException -> 0x0104 }
        r6 = 0;
        r5[r6] = r4;	 Catch:{ IOException -> 0x0104 }
        com.google.android.gms.internal.ads.zzaf.d(r2, r5);	 Catch:{ IOException -> 0x0104 }
        r2 = new java.io.IOException;	 Catch:{ IOException -> 0x0104 }
        r2.<init>();	 Catch:{ IOException -> 0x0104 }
        throw r2;	 Catch:{ IOException -> 0x0104 }
    L_0x00f7:
        r6 = r6.data;	 Catch:{ IOException -> 0x0104 }
        r4.write(r6);	 Catch:{ IOException -> 0x0104 }
        r4.close();	 Catch:{ IOException -> 0x0104 }
        r1.zza(r2, r5);	 Catch:{ IOException -> 0x0104 }
        monitor-exit(r19);
        return;
    L_0x0104:
        r2 = r3.delete();	 Catch:{ all -> 0x011b }
        if (r2 != 0) goto L_0x0119;
    L_0x010a:
        r2 = "Could not clean up file %s";
        r4 = 1;
        r4 = new java.lang.Object[r4];	 Catch:{ all -> 0x011b }
        r3 = r3.getAbsolutePath();	 Catch:{ all -> 0x011b }
        r5 = 0;
        r4[r5] = r3;	 Catch:{ all -> 0x011b }
        com.google.android.gms.internal.ads.zzaf.d(r2, r4);	 Catch:{ all -> 0x011b }
    L_0x0119:
        monitor-exit(r19);
        return;
    L_0x011b:
        r0 = move-exception;
        r2 = r0;
        monitor-exit(r19);
        throw r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzam.zza(java.lang.String, com.google.android.gms.internal.ads.zzc):void");
    }

    private final synchronized void remove(String str) {
        boolean delete = zze(str).delete();
        removeEntry(str);
        if (!delete) {
            zzaf.d("Could not delete cache entry for key=%s, filename=%s", str, zzd(str));
        }
    }

    private static String zzd(String str) {
        int length = str.length() / 2;
        String valueOf = String.valueOf(String.valueOf(str.substring(0, length).hashCode()));
        str = String.valueOf(String.valueOf(str.substring(length).hashCode()));
        return str.length() != 0 ? valueOf.concat(str) : new String(valueOf);
    }

    private final File zze(String str) {
        return new File(this.zzby, zzd(str));
    }

    private final void zza(String str, zzan zzan) {
        if (this.zzbw.containsKey(str)) {
            this.zzbx += zzan.zzca - ((zzan) this.zzbw.get(str)).zzca;
        } else {
            this.zzbx += zzan.zzca;
        }
        this.zzbw.put(str, zzan);
    }

    private final void removeEntry(String str) {
        zzan zzan = (zzan) this.zzbw.remove(str);
        if (zzan != null) {
            this.zzbx -= zzan.zzca;
        }
    }

    private static byte[] zza(zzao zzao, long j) throws IOException {
        long zzp = zzao.zzp();
        if (j >= 0 && j <= zzp) {
            int i = (int) j;
            if (((long) i) == j) {
                byte[] bArr = new byte[i];
                new DataInputStream(zzao).readFully(bArr);
                return bArr;
            }
        }
        StringBuilder stringBuilder = new StringBuilder(73);
        stringBuilder.append("streamToBytes length=");
        stringBuilder.append(j);
        stringBuilder.append(", maxLength=");
        stringBuilder.append(zzp);
        throw new IOException(stringBuilder.toString());
    }

    private static InputStream zza(File file) throws FileNotFoundException {
        return new FileInputStream(file);
    }

    private static int zza(InputStream inputStream) throws IOException {
        int read = inputStream.read();
        if (read != -1) {
            return read;
        }
        throw new EOFException();
    }

    static void zza(OutputStream outputStream, int i) throws IOException {
        outputStream.write(i & 255);
        outputStream.write((i >> 8) & 255);
        outputStream.write((i >> 16) & 255);
        outputStream.write(i >>> 24);
    }

    static int zzb(InputStream inputStream) throws IOException {
        return (zza(inputStream) << 24) | (((zza(inputStream) | 0) | (zza(inputStream) << 8)) | (zza(inputStream) << 16));
    }

    static void zza(OutputStream outputStream, long j) throws IOException {
        outputStream.write((byte) ((int) j));
        outputStream.write((byte) ((int) (j >>> 8)));
        outputStream.write((byte) ((int) (j >>> 16)));
        outputStream.write((byte) ((int) (j >>> 24)));
        outputStream.write((byte) ((int) (j >>> 32)));
        outputStream.write((byte) ((int) (j >>> 40)));
        outputStream.write((byte) ((int) (j >>> 48)));
        outputStream.write((byte) ((int) (j >>> 56)));
    }

    static long zzc(InputStream inputStream) throws IOException {
        return (((((((0 | (((long) zza(inputStream)) & 255)) | ((((long) zza(inputStream)) & 255) << 8)) | ((((long) zza(inputStream)) & 255) << 16)) | ((((long) zza(inputStream)) & 255) << 24)) | ((((long) zza(inputStream)) & 255) << 32)) | ((((long) zza(inputStream)) & 255) << 40)) | ((((long) zza(inputStream)) & 255) << 48)) | ((((long) zza(inputStream)) & 255) << 56);
    }

    static void zza(OutputStream outputStream, String str) throws IOException {
        byte[] bytes = str.getBytes("UTF-8");
        zza(outputStream, (long) bytes.length);
        outputStream.write(bytes, 0, bytes.length);
    }

    static String zza(zzao zzao) throws IOException {
        return new String(zza(zzao, zzc(zzao)), "UTF-8");
    }

    static List<zzl> zzb(zzao zzao) throws IOException {
        int zzb = zzb((InputStream) zzao);
        if (zzb < 0) {
            StringBuilder stringBuilder = new StringBuilder(31);
            stringBuilder.append("readHeaderList size=");
            stringBuilder.append(zzb);
            throw new IOException(stringBuilder.toString());
        }
        List emptyList = zzb == 0 ? Collections.emptyList() : new ArrayList();
        for (int i = 0; i < zzb; i++) {
            emptyList.add(new zzl(zza(zzao).intern(), zza(zzao).intern()));
        }
        return emptyList;
    }
}
