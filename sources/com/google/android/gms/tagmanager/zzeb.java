package com.google.android.gms.tagmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.text.TextUtils;
import com.comscore.measurement.MeasurementDispatcher;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.DefaultClock;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.Collections;
import java.util.List;

@VisibleForTesting
final class zzeb implements zzcb {
    private static final String zzxf = String.format("CREATE TABLE IF NOT EXISTS %s ( '%s' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, '%s' INTEGER NOT NULL, '%s' TEXT NOT NULL,'%s' INTEGER NOT NULL);", new Object[]{"gtm_hits", "hit_id", "hit_time", "hit_url", "hit_first_send_time"});
    private final zzed zzbdp;
    private volatile zzbe zzbdq;
    private final zzcc zzbdr;
    private final String zzbds;
    private long zzbdt;
    private final int zzbdu;
    private final Context zzri;
    private Clock zzrz;

    zzeb(zzcc zzcc, Context context) {
        this(zzcc, context, "gtm_urls.db", 2000);
    }

    @VisibleForTesting
    private zzeb(zzcc zzcc, Context context, String str, int i) {
        this.zzri = context.getApplicationContext();
        this.zzbds = str;
        this.zzbdr = zzcc;
        this.zzrz = DefaultClock.getInstance();
        this.zzbdp = new zzed(this, this.zzri, this.zzbds);
        this.zzbdq = new zzfu(this.zzri, new zzec(this));
        this.zzbdt = 0;
        this.zzbdu = 2000;
    }

    public final void zzb(long j, String str) {
        SQLiteDatabase zzdl;
        long currentTimeMillis = this.zzrz.currentTimeMillis();
        if (currentTimeMillis > this.zzbdt + MeasurementDispatcher.MILLIS_PER_DAY) {
            this.zzbdt = currentTimeMillis;
            zzdl = zzdl("Error opening database for deleteStaleHits.");
            if (zzdl != null) {
                long currentTimeMillis2 = this.zzrz.currentTimeMillis() - 2592000000L;
                zzdl.delete("gtm_hits", "HIT_TIME < ?", new String[]{Long.toString(currentTimeMillis2)});
                this.zzbdr.zzo(zzpj() == 0);
            }
        }
        int zzpj = (zzpj() - this.zzbdu) + 1;
        if (zzpj > 0) {
            List zzab = zzab(zzpj);
            int size = zzab.size();
            StringBuilder stringBuilder = new StringBuilder(51);
            stringBuilder.append("Store full, deleting ");
            stringBuilder.append(size);
            stringBuilder.append(" hits to make room.");
            zzdi.v(stringBuilder.toString());
            zza((String[]) zzab.toArray(new String[0]));
        }
        zzdl = zzdl("Error opening database for putHit");
        if (zzdl != null) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("hit_time", Long.valueOf(j));
            contentValues.put("hit_url", str);
            contentValues.put("hit_first_send_time", Integer.valueOf(0));
            try {
                zzdl.insert("gtm_hits", null, contentValues);
                this.zzbdr.zzo(false);
            } catch (SQLiteException unused) {
                zzdi.zzab("Error storing hit");
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:33:0x008d  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x007d A:{Catch:{ all -> 0x0065 }} */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0078 A:{Catch:{ all -> 0x0065 }} */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0087  */
    private final java.util.List<java.lang.String> zzab(int r15) {
        /*
        r14 = this;
        r0 = new java.util.ArrayList;
        r0.<init>();
        if (r15 > 0) goto L_0x000d;
    L_0x0007:
        r15 = "Invalid maxHits specified. Skipping";
        com.google.android.gms.tagmanager.zzdi.zzab(r15);
        return r0;
    L_0x000d:
        r1 = "Error opening database for peekHitIds.";
        r2 = r14.zzdl(r1);
        if (r2 != 0) goto L_0x0016;
    L_0x0015:
        return r0;
    L_0x0016:
        r1 = 0;
        r3 = "gtm_hits";
        r4 = 1;
        r5 = new java.lang.String[r4];	 Catch:{ SQLiteException -> 0x0067 }
        r6 = "hit_id";
        r11 = 0;
        r5[r11] = r6;	 Catch:{ SQLiteException -> 0x0067 }
        r6 = 0;
        r7 = 0;
        r8 = 0;
        r9 = 0;
        r10 = "%s ASC";
        r4 = new java.lang.Object[r4];	 Catch:{ SQLiteException -> 0x0067 }
        r12 = "hit_id";
        r4[r11] = r12;	 Catch:{ SQLiteException -> 0x0067 }
        r10 = java.lang.String.format(r10, r4);	 Catch:{ SQLiteException -> 0x0067 }
        r15 = java.lang.Integer.toString(r15);	 Catch:{ SQLiteException -> 0x0067 }
        r4 = r5;
        r5 = r6;
        r6 = r7;
        r7 = r8;
        r8 = r9;
        r9 = r10;
        r10 = r15;
        r15 = r2.query(r3, r4, r5, r6, r7, r8, r9, r10);	 Catch:{ SQLiteException -> 0x0067 }
        r1 = r15.moveToFirst();	 Catch:{ SQLiteException -> 0x0060, all -> 0x005d }
        if (r1 == 0) goto L_0x0057;
    L_0x0046:
        r1 = r15.getLong(r11);	 Catch:{ SQLiteException -> 0x0060, all -> 0x005d }
        r1 = java.lang.String.valueOf(r1);	 Catch:{ SQLiteException -> 0x0060, all -> 0x005d }
        r0.add(r1);	 Catch:{ SQLiteException -> 0x0060, all -> 0x005d }
        r1 = r15.moveToNext();	 Catch:{ SQLiteException -> 0x0060, all -> 0x005d }
        if (r1 != 0) goto L_0x0046;
    L_0x0057:
        if (r15 == 0) goto L_0x008a;
    L_0x0059:
        r15.close();
        goto L_0x008a;
    L_0x005d:
        r0 = move-exception;
        r1 = r15;
        goto L_0x008b;
    L_0x0060:
        r1 = move-exception;
        r13 = r1;
        r1 = r15;
        r15 = r13;
        goto L_0x0068;
    L_0x0065:
        r0 = move-exception;
        goto L_0x008b;
    L_0x0067:
        r15 = move-exception;
    L_0x0068:
        r2 = "Error in peekHits fetching hitIds: ";
        r15 = r15.getMessage();	 Catch:{ all -> 0x0065 }
        r15 = java.lang.String.valueOf(r15);	 Catch:{ all -> 0x0065 }
        r3 = r15.length();	 Catch:{ all -> 0x0065 }
        if (r3 == 0) goto L_0x007d;
    L_0x0078:
        r15 = r2.concat(r15);	 Catch:{ all -> 0x0065 }
        goto L_0x0082;
    L_0x007d:
        r15 = new java.lang.String;	 Catch:{ all -> 0x0065 }
        r15.<init>(r2);	 Catch:{ all -> 0x0065 }
    L_0x0082:
        com.google.android.gms.tagmanager.zzdi.zzab(r15);	 Catch:{ all -> 0x0065 }
        if (r1 == 0) goto L_0x008a;
    L_0x0087:
        r1.close();
    L_0x008a:
        return r0;
    L_0x008b:
        if (r1 == 0) goto L_0x0090;
    L_0x008d:
        r1.close();
    L_0x0090:
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zzeb.zzab(int):java.util.List");
    }

    /* JADX WARNING: Removed duplicated region for block: B:66:0x0155  */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x011b A:{Catch:{ all -> 0x0151 }} */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x0116 A:{Catch:{ all -> 0x0151 }} */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x0132 A:{Catch:{ all -> 0x0151 }} */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x014d  */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x0155  */
    /* JADX WARNING: Removed duplicated region for block: B:83:0x018c A:{Catch:{ all -> 0x019a }} */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x0187 A:{Catch:{ all -> 0x019a }} */
    /* JADX WARNING: Removed duplicated region for block: B:86:0x0196  */
    /* JADX WARNING: Removed duplicated region for block: B:91:0x019e  */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x0187 A:{Catch:{ all -> 0x019a }} */
    /* JADX WARNING: Removed duplicated region for block: B:83:0x018c A:{Catch:{ all -> 0x019a }} */
    /* JADX WARNING: Removed duplicated region for block: B:86:0x0196  */
    /* JADX WARNING: Removed duplicated region for block: B:91:0x019e  */
    /* JADX WARNING: Removed duplicated region for block: B:91:0x019e  */
    /* JADX WARNING: Removed duplicated region for block: B:83:0x018c A:{Catch:{ all -> 0x019a }} */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x0187 A:{Catch:{ all -> 0x019a }} */
    /* JADX WARNING: Removed duplicated region for block: B:86:0x0196  */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x0161 A:{Splitter:B:5:0x0043, ExcHandler: all (r0_11 'th' java.lang.Throwable)} */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x0116 A:{Catch:{ all -> 0x0151 }} */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x011b A:{Catch:{ all -> 0x0151 }} */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x0132 A:{Catch:{ all -> 0x0151 }} */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x014d  */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing block: B:70:0x0161, code skipped:
            r0 = move-exception;
     */
    /* JADX WARNING: Missing block: B:71:0x0162, code skipped:
            r1 = r0;
            r13 = r12;
     */
    /* JADX WARNING: Missing block: B:72:0x0168, code skipped:
            r0 = e;
     */
    /* JADX WARNING: Missing block: B:73:0x0169, code skipped:
            r11 = r1;
            r13 = r12;
     */
    private final java.util.List<com.google.android.gms.tagmanager.zzbw> zzac(int r19) {
        /*
        r18 = this;
        r1 = new java.util.ArrayList;
        r1.<init>();
        r2 = "Error opening database for peekHits";
        r3 = r18;
        r2 = r3.zzdl(r2);
        if (r2 != 0) goto L_0x0010;
    L_0x000f:
        return r1;
    L_0x0010:
        r5 = "gtm_hits";
        r4 = 3;
        r6 = new java.lang.String[r4];	 Catch:{ SQLiteException -> 0x0173, all -> 0x016f }
        r4 = "hit_id";
        r14 = 0;
        r6[r14] = r4;	 Catch:{ SQLiteException -> 0x0173, all -> 0x016f }
        r4 = "hit_time";
        r15 = 1;
        r6[r15] = r4;	 Catch:{ SQLiteException -> 0x0173, all -> 0x016f }
        r4 = "hit_first_send_time";
        r12 = 2;
        r6[r12] = r4;	 Catch:{ SQLiteException -> 0x0173, all -> 0x016f }
        r7 = 0;
        r8 = 0;
        r9 = 0;
        r10 = 0;
        r4 = "%s ASC";
        r11 = new java.lang.Object[r15];	 Catch:{ SQLiteException -> 0x0173, all -> 0x016f }
        r16 = "hit_id";
        r11[r14] = r16;	 Catch:{ SQLiteException -> 0x0173, all -> 0x016f }
        r11 = java.lang.String.format(r4, r11);	 Catch:{ SQLiteException -> 0x0173, all -> 0x016f }
        r4 = 40;
        r16 = java.lang.Integer.toString(r4);	 Catch:{ SQLiteException -> 0x0173, all -> 0x016f }
        r13 = r4;
        r4 = r2;
        r13 = r12;
        r12 = r16;
        r12 = r4.query(r5, r6, r7, r8, r9, r10, r11, r12);	 Catch:{ SQLiteException -> 0x0173, all -> 0x016f }
        r11 = new java.util.ArrayList;	 Catch:{ SQLiteException -> 0x0168, all -> 0x0161 }
        r11.<init>();	 Catch:{ SQLiteException -> 0x0168, all -> 0x0161 }
        r1 = r12.moveToFirst();	 Catch:{ SQLiteException -> 0x0159, all -> 0x0161 }
        if (r1 == 0) goto L_0x0074;
    L_0x004e:
        r1 = new com.google.android.gms.tagmanager.zzbw;	 Catch:{ SQLiteException -> 0x006f, all -> 0x006a }
        r5 = r12.getLong(r14);	 Catch:{ SQLiteException -> 0x006f, all -> 0x006a }
        r7 = r12.getLong(r15);	 Catch:{ SQLiteException -> 0x006f, all -> 0x006a }
        r9 = r12.getLong(r13);	 Catch:{ SQLiteException -> 0x006f, all -> 0x006a }
        r4 = r1;
        r4.<init>(r5, r7, r9);	 Catch:{ SQLiteException -> 0x006f, all -> 0x006a }
        r11.add(r1);	 Catch:{ SQLiteException -> 0x006f, all -> 0x006a }
        r1 = r12.moveToNext();	 Catch:{ SQLiteException -> 0x006f, all -> 0x006a }
        if (r1 != 0) goto L_0x004e;
    L_0x0069:
        goto L_0x0074;
    L_0x006a:
        r0 = move-exception;
        r1 = r0;
        r13 = r12;
        goto L_0x019c;
    L_0x006f:
        r0 = move-exception;
        r1 = r0;
        r13 = r12;
        goto L_0x0177;
    L_0x0074:
        if (r12 == 0) goto L_0x0079;
    L_0x0076:
        r12.close();
    L_0x0079:
        r5 = "gtm_hits";
        r6 = new java.lang.String[r13];	 Catch:{ SQLiteException -> 0x0101, all -> 0x00fc }
        r1 = "hit_id";
        r6[r14] = r1;	 Catch:{ SQLiteException -> 0x0101, all -> 0x00fc }
        r1 = "hit_url";
        r6[r15] = r1;	 Catch:{ SQLiteException -> 0x0101, all -> 0x00fc }
        r7 = 0;
        r8 = 0;
        r9 = 0;
        r10 = 0;
        r1 = "%s ASC";
        r4 = new java.lang.Object[r15];	 Catch:{ SQLiteException -> 0x0101, all -> 0x00fc }
        r13 = "hit_id";
        r4[r14] = r13;	 Catch:{ SQLiteException -> 0x0101, all -> 0x00fc }
        r1 = java.lang.String.format(r1, r4);	 Catch:{ SQLiteException -> 0x0101, all -> 0x00fc }
        r4 = 40;
        r13 = java.lang.Integer.toString(r4);	 Catch:{ SQLiteException -> 0x0101, all -> 0x00fc }
        r4 = r2;
        r2 = r11;
        r11 = r1;
        r16 = r12;
        r12 = r13;
        r12 = r4.query(r5, r6, r7, r8, r9, r10, r11, r12);	 Catch:{ SQLiteException -> 0x00f7, all -> 0x00f2 }
        r1 = r12.moveToFirst();	 Catch:{ SQLiteException -> 0x00f0 }
        if (r1 == 0) goto L_0x00ea;
    L_0x00ab:
        r1 = r14;
    L_0x00ac:
        r4 = r12;
        r4 = (android.database.sqlite.SQLiteCursor) r4;	 Catch:{ SQLiteException -> 0x00f0 }
        r4 = r4.getWindow();	 Catch:{ SQLiteException -> 0x00f0 }
        r4 = r4.getNumRows();	 Catch:{ SQLiteException -> 0x00f0 }
        if (r4 <= 0) goto L_0x00c7;
    L_0x00b9:
        r4 = r2.get(r1);	 Catch:{ SQLiteException -> 0x00f0 }
        r4 = (com.google.android.gms.tagmanager.zzbw) r4;	 Catch:{ SQLiteException -> 0x00f0 }
        r5 = r12.getString(r15);	 Catch:{ SQLiteException -> 0x00f0 }
        r4.zzds(r5);	 Catch:{ SQLiteException -> 0x00f0 }
        goto L_0x00e2;
    L_0x00c7:
        r4 = "HitString for hitId %d too large.  Hit will be deleted.";
        r5 = new java.lang.Object[r15];	 Catch:{ SQLiteException -> 0x00f0 }
        r6 = r2.get(r1);	 Catch:{ SQLiteException -> 0x00f0 }
        r6 = (com.google.android.gms.tagmanager.zzbw) r6;	 Catch:{ SQLiteException -> 0x00f0 }
        r6 = r6.zzov();	 Catch:{ SQLiteException -> 0x00f0 }
        r6 = java.lang.Long.valueOf(r6);	 Catch:{ SQLiteException -> 0x00f0 }
        r5[r14] = r6;	 Catch:{ SQLiteException -> 0x00f0 }
        r4 = java.lang.String.format(r4, r5);	 Catch:{ SQLiteException -> 0x00f0 }
        com.google.android.gms.tagmanager.zzdi.zzab(r4);	 Catch:{ SQLiteException -> 0x00f0 }
    L_0x00e2:
        r1 = r1 + 1;
        r4 = r12.moveToNext();	 Catch:{ SQLiteException -> 0x00f0 }
        if (r4 != 0) goto L_0x00ac;
    L_0x00ea:
        if (r12 == 0) goto L_0x00ef;
    L_0x00ec:
        r12.close();
    L_0x00ef:
        return r2;
    L_0x00f0:
        r0 = move-exception;
        goto L_0x0105;
    L_0x00f2:
        r0 = move-exception;
        r1 = r0;
        r12 = r16;
        goto L_0x0153;
    L_0x00f7:
        r0 = move-exception;
        r1 = r0;
        r12 = r16;
        goto L_0x0106;
    L_0x00fc:
        r0 = move-exception;
        r16 = r12;
    L_0x00ff:
        r1 = r0;
        goto L_0x0153;
    L_0x0101:
        r0 = move-exception;
        r2 = r11;
        r16 = r12;
    L_0x0105:
        r1 = r0;
    L_0x0106:
        r4 = "Error in peekHits fetching hit url: ";
        r1 = r1.getMessage();	 Catch:{ all -> 0x0151 }
        r1 = java.lang.String.valueOf(r1);	 Catch:{ all -> 0x0151 }
        r5 = r1.length();	 Catch:{ all -> 0x0151 }
        if (r5 == 0) goto L_0x011b;
    L_0x0116:
        r1 = r4.concat(r1);	 Catch:{ all -> 0x0151 }
        goto L_0x0120;
    L_0x011b:
        r1 = new java.lang.String;	 Catch:{ all -> 0x0151 }
        r1.<init>(r4);	 Catch:{ all -> 0x0151 }
    L_0x0120:
        com.google.android.gms.tagmanager.zzdi.zzab(r1);	 Catch:{ all -> 0x0151 }
        r1 = new java.util.ArrayList;	 Catch:{ all -> 0x0151 }
        r1.<init>();	 Catch:{ all -> 0x0151 }
        r11 = r2;
        r11 = (java.util.ArrayList) r11;	 Catch:{ all -> 0x0151 }
        r2 = r11.size();	 Catch:{ all -> 0x0151 }
        r4 = r14;
    L_0x0130:
        if (r14 >= r2) goto L_0x014b;
    L_0x0132:
        r5 = r11.get(r14);	 Catch:{ all -> 0x0151 }
        r14 = r14 + 1;
        r5 = (com.google.android.gms.tagmanager.zzbw) r5;	 Catch:{ all -> 0x0151 }
        r6 = r5.zzox();	 Catch:{ all -> 0x0151 }
        r6 = android.text.TextUtils.isEmpty(r6);	 Catch:{ all -> 0x0151 }
        if (r6 == 0) goto L_0x0147;
    L_0x0144:
        if (r4 != 0) goto L_0x014b;
    L_0x0146:
        r4 = r15;
    L_0x0147:
        r1.add(r5);	 Catch:{ all -> 0x0151 }
        goto L_0x0130;
    L_0x014b:
        if (r12 == 0) goto L_0x0150;
    L_0x014d:
        r12.close();
    L_0x0150:
        return r1;
    L_0x0151:
        r0 = move-exception;
        goto L_0x00ff;
    L_0x0153:
        if (r12 == 0) goto L_0x0158;
    L_0x0155:
        r12.close();
    L_0x0158:
        throw r1;
    L_0x0159:
        r0 = move-exception;
        r2 = r11;
        r16 = r12;
        r1 = r0;
        r13 = r16;
        goto L_0x0177;
    L_0x0161:
        r0 = move-exception;
        r16 = r12;
        r1 = r0;
        r13 = r16;
        goto L_0x019c;
    L_0x0168:
        r0 = move-exception;
        r16 = r12;
        r11 = r1;
        r13 = r16;
        goto L_0x0176;
    L_0x016f:
        r0 = move-exception;
        r1 = r0;
        r13 = 0;
        goto L_0x019c;
    L_0x0173:
        r0 = move-exception;
        r11 = r1;
        r13 = 0;
    L_0x0176:
        r1 = r0;
    L_0x0177:
        r2 = "Error in peekHits fetching hitIds: ";
        r1 = r1.getMessage();	 Catch:{ all -> 0x019a }
        r1 = java.lang.String.valueOf(r1);	 Catch:{ all -> 0x019a }
        r4 = r1.length();	 Catch:{ all -> 0x019a }
        if (r4 == 0) goto L_0x018c;
    L_0x0187:
        r1 = r2.concat(r1);	 Catch:{ all -> 0x019a }
        goto L_0x0191;
    L_0x018c:
        r1 = new java.lang.String;	 Catch:{ all -> 0x019a }
        r1.<init>(r2);	 Catch:{ all -> 0x019a }
    L_0x0191:
        com.google.android.gms.tagmanager.zzdi.zzab(r1);	 Catch:{ all -> 0x019a }
        if (r13 == 0) goto L_0x0199;
    L_0x0196:
        r13.close();
    L_0x0199:
        return r11;
    L_0x019a:
        r0 = move-exception;
        r1 = r0;
    L_0x019c:
        if (r13 == 0) goto L_0x01a1;
    L_0x019e:
        r13.close();
    L_0x01a1:
        throw r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zzeb.zzac(int):java.util.List");
    }

    private final void zza(String[] strArr) {
        if (strArr != null && strArr.length != 0) {
            SQLiteDatabase zzdl = zzdl("Error opening database for deleteHits.");
            if (zzdl != null) {
                boolean z = true;
                try {
                    zzdl.delete("gtm_hits", String.format("HIT_ID in (%s)", new Object[]{TextUtils.join(",", Collections.nCopies(strArr.length, "?"))}), strArr);
                    zzcc zzcc = this.zzbdr;
                    if (zzpj() != 0) {
                        z = false;
                    }
                    zzcc.zzo(z);
                } catch (SQLiteException unused) {
                    zzdi.zzab("Error deleting hits");
                }
            }
        }
    }

    private final void zze(long j) {
        zza(new String[]{String.valueOf(j)});
    }

    private final void zze(long j, long j2) {
        SQLiteDatabase zzdl = zzdl("Error opening database for getNumStoredHits.");
        if (zzdl != null) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("hit_first_send_time", Long.valueOf(j2));
            try {
                zzdl.update("gtm_hits", contentValues, "hit_id=?", new String[]{String.valueOf(j)});
            } catch (SQLiteException unused) {
                StringBuilder stringBuilder = new StringBuilder(69);
                stringBuilder.append("Error setting HIT_FIRST_DISPATCH_TIME for hitId: ");
                stringBuilder.append(j);
                zzdi.zzab(stringBuilder.toString());
                zze(j);
            }
        }
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:18:0x0029 */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0030  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0036  */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing block: B:21:0x0030, code skipped:
            r2.close();
     */
    private final int zzpj() {
        /*
        r4 = this;
        r0 = "Error opening database for getNumStoredHits.";
        r0 = r4.zzdl(r0);
        r1 = 0;
        if (r0 != 0) goto L_0x000a;
    L_0x0009:
        return r1;
    L_0x000a:
        r2 = 0;
        r3 = "SELECT COUNT(*) from gtm_hits";
        r0 = r0.rawQuery(r3, r2);	 Catch:{ SQLiteException -> 0x0029 }
        r2 = r0.moveToFirst();	 Catch:{ SQLiteException -> 0x0025, all -> 0x0022 }
        if (r2 == 0) goto L_0x001c;
    L_0x0017:
        r2 = r0.getLong(r1);	 Catch:{ SQLiteException -> 0x0025, all -> 0x0022 }
        r1 = (int) r2;
    L_0x001c:
        if (r0 == 0) goto L_0x0033;
    L_0x001e:
        r0.close();
        goto L_0x0033;
    L_0x0022:
        r1 = move-exception;
        r2 = r0;
        goto L_0x0034;
    L_0x0025:
        r2 = r0;
        goto L_0x0029;
    L_0x0027:
        r1 = move-exception;
        goto L_0x0034;
    L_0x0029:
        r0 = "Error getting numStoredHits";
        com.google.android.gms.tagmanager.zzdi.zzab(r0);	 Catch:{ all -> 0x0027 }
        if (r2 == 0) goto L_0x0033;
    L_0x0030:
        r2.close();
    L_0x0033:
        return r1;
    L_0x0034:
        if (r2 == 0) goto L_0x0039;
    L_0x0036:
        r2.close();
    L_0x0039:
        throw r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zzeb.zzpj():int");
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:16:0x0035 */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x003c  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0042  */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Can't wrap try/catch for region: R(6:14|13|16|17|(0)|20) */
    /* JADX WARNING: Missing block: B:15:0x0033, code skipped:
            r0 = th;
     */
    /* JADX WARNING: Missing block: B:19:0x003c, code skipped:
            r9.close();
     */
    /* JADX WARNING: Missing block: B:22:0x0042, code skipped:
            r9.close();
     */
    private final int zzpk() {
        /*
        r10 = this;
        r0 = "Error opening database for getNumStoredHits.";
        r1 = r10.zzdl(r0);
        r0 = 0;
        if (r1 != 0) goto L_0x000a;
    L_0x0009:
        return r0;
    L_0x000a:
        r9 = 0;
        r2 = "gtm_hits";
        r3 = 2;
        r3 = new java.lang.String[r3];	 Catch:{ SQLiteException -> 0x0035 }
        r4 = "hit_id";
        r3[r0] = r4;	 Catch:{ SQLiteException -> 0x0035 }
        r4 = 1;
        r5 = "hit_first_send_time";
        r3[r4] = r5;	 Catch:{ SQLiteException -> 0x0035 }
        r4 = "hit_first_send_time=0";
        r5 = 0;
        r6 = 0;
        r7 = 0;
        r8 = 0;
        r1 = r1.query(r2, r3, r4, r5, r6, r7, r8);	 Catch:{ SQLiteException -> 0x0035 }
        r2 = r1.getCount();	 Catch:{ SQLiteException -> 0x0031, all -> 0x002e }
        if (r1 == 0) goto L_0x002c;
    L_0x0029:
        r1.close();
    L_0x002c:
        r0 = r2;
        goto L_0x003f;
    L_0x002e:
        r0 = move-exception;
        r9 = r1;
        goto L_0x0040;
    L_0x0031:
        r9 = r1;
        goto L_0x0035;
    L_0x0033:
        r0 = move-exception;
        goto L_0x0040;
    L_0x0035:
        r1 = "Error getting num untried hits";
        com.google.android.gms.tagmanager.zzdi.zzab(r1);	 Catch:{ all -> 0x0033 }
        if (r9 == 0) goto L_0x003f;
    L_0x003c:
        r9.close();
    L_0x003f:
        return r0;
    L_0x0040:
        if (r9 == 0) goto L_0x0045;
    L_0x0042:
        r9.close();
    L_0x0045:
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zzeb.zzpk():int");
    }

    public final void dispatch() {
        zzdi.v("GTM Dispatch running...");
        if (this.zzbdq.zzom()) {
            List zzac = zzac(40);
            if (zzac.isEmpty()) {
                zzdi.v("...nothing to dispatch");
                this.zzbdr.zzo(true);
                return;
            }
            this.zzbdq.zzf(zzac);
            if (zzpk() > 0) {
                zzfn.zzqe().dispatch();
            }
        }
    }

    private final SQLiteDatabase zzdl(String str) {
        try {
            return this.zzbdp.getWritableDatabase();
        } catch (SQLiteException unused) {
            zzdi.zzab(str);
            return null;
        }
    }
}
