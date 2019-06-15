package com.google.android.gms.internal.measurement;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.net.Uri.Builder;
import android.text.TextUtils;
import com.comscore.measurement.MeasurementDispatcher;
import com.google.android.gms.analytics.zzk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.HttpUtils;
import com.google.android.gms.common.util.VisibleForTesting;
import java.io.Closeable;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

final class zzbf extends zzau implements Closeable {
    private static final String zzxf = String.format("CREATE TABLE IF NOT EXISTS %s ( '%s' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, '%s' INTEGER NOT NULL, '%s' TEXT NOT NULL, '%s' TEXT NOT NULL, '%s' INTEGER);", new Object[]{"hits2", "hit_id", "hit_time", "hit_url", "hit_string", "hit_app_id"});
    private static final String zzxg = String.format("SELECT MAX(%s) FROM %s WHERE 1;", new Object[]{"hit_time", "hits2"});
    private final zzbg zzxh;
    private final zzdc zzxi = new zzdc(zzbx());
    private final zzdc zzxj = new zzdc(zzbx());

    zzbf(zzaw zzaw) {
        super(zzaw);
        this.zzxh = new zzbg(this, zzaw.getContext(), "google_analytics_v4.db");
    }

    private static String zzdd() {
        return "google_analytics_v4.db";
    }

    /* Access modifiers changed, original: protected|final */
    public final void zzag() {
    }

    public final void beginTransaction() {
        zzcl();
        getWritableDatabase().beginTransaction();
    }

    public final void setTransactionSuccessful() {
        zzcl();
        getWritableDatabase().setTransactionSuccessful();
    }

    public final void endTransaction() {
        zzcl();
        getWritableDatabase().endTransaction();
    }

    public final void zzc(zzck zzck) {
        String str;
        Preconditions.checkNotNull(zzck);
        zzk.zzaf();
        zzcl();
        Preconditions.checkNotNull(zzck);
        Builder builder = new Builder();
        for (Entry entry : zzck.zzcw().entrySet()) {
            str = (String) entry.getKey();
            if (!("ht".equals(str) || "qt".equals(str) || "AppUID".equals(str))) {
                builder.appendQueryParameter(str, (String) entry.getValue());
            }
        }
        String encodedQuery = builder.build().getEncodedQuery();
        if (encodedQuery == null) {
            encodedQuery = "";
        }
        if (encodedQuery.length() > 8192) {
            zzby().zza(zzck, "Hit length exceeds the maximum allowed size");
            return;
        }
        int intValue = ((Integer) zzcf.zzza.get()).intValue();
        long zzcv = zzcv();
        if (zzcv > ((long) (intValue - 1))) {
            List zzc = zzc((zzcv - ((long) intValue)) + 1);
            zzd("Store full, deleting hits to make room, count", Integer.valueOf(zzc.size()));
            zza(zzc);
        }
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("hit_string", encodedQuery);
        contentValues.put("hit_time", Long.valueOf(zzck.zzer()));
        contentValues.put("hit_app_id", Integer.valueOf(zzck.zzep()));
        encodedQuery = "hit_url";
        if (zzck.zzet()) {
            str = zzbx.zzed();
        } else {
            str = zzbx.zzee();
        }
        contentValues.put(encodedQuery, str);
        try {
            long insert = writableDatabase.insert("hits2", null, contentValues);
            if (insert == -1) {
                zzu("Failed to insert a hit (got -1)");
            } else {
                zzb("Hit saved to database. db-id, hit", Long.valueOf(insert), zzck);
            }
        } catch (SQLiteException e) {
            zze("Error storing a hit", e);
        }
    }

    private final long zzcv() {
        zzk.zzaf();
        zzcl();
        return zza("SELECT COUNT(*) FROM hits2", null);
    }

    /* Access modifiers changed, original: final */
    public final boolean isEmpty() {
        return zzcv() == 0;
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x0071  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0077  */
    private final java.util.List<java.lang.Long> zzc(long r14) {
        /*
        r13 = this;
        com.google.android.gms.analytics.zzk.zzaf();
        r13.zzcl();
        r0 = 0;
        r2 = (r14 > r0 ? 1 : (r14 == r0 ? 0 : -1));
        if (r2 > 0) goto L_0x0011;
    L_0x000c:
        r14 = java.util.Collections.emptyList();
        return r14;
    L_0x0011:
        r0 = r13.getWritableDatabase();
        r9 = new java.util.ArrayList;
        r9.<init>();
        r10 = 0;
        r1 = "hits2";
        r2 = 1;
        r3 = new java.lang.String[r2];	 Catch:{ SQLiteException -> 0x0069 }
        r4 = "hit_id";
        r11 = 0;
        r3[r11] = r4;	 Catch:{ SQLiteException -> 0x0069 }
        r4 = 0;
        r5 = 0;
        r6 = 0;
        r7 = 0;
        r8 = "%s ASC";
        r2 = new java.lang.Object[r2];	 Catch:{ SQLiteException -> 0x0069 }
        r12 = "hit_id";
        r2[r11] = r12;	 Catch:{ SQLiteException -> 0x0069 }
        r8 = java.lang.String.format(r8, r2);	 Catch:{ SQLiteException -> 0x0069 }
        r14 = java.lang.Long.toString(r14);	 Catch:{ SQLiteException -> 0x0069 }
        r2 = r3;
        r3 = r4;
        r4 = r5;
        r5 = r6;
        r6 = r7;
        r7 = r8;
        r8 = r14;
        r14 = r0.query(r1, r2, r3, r4, r5, r6, r7, r8);	 Catch:{ SQLiteException -> 0x0069 }
        r15 = r14.moveToFirst();	 Catch:{ SQLiteException -> 0x0064, all -> 0x0061 }
        if (r15 == 0) goto L_0x005b;
    L_0x004a:
        r0 = r14.getLong(r11);	 Catch:{ SQLiteException -> 0x0064, all -> 0x0061 }
        r15 = java.lang.Long.valueOf(r0);	 Catch:{ SQLiteException -> 0x0064, all -> 0x0061 }
        r9.add(r15);	 Catch:{ SQLiteException -> 0x0064, all -> 0x0061 }
        r15 = r14.moveToNext();	 Catch:{ SQLiteException -> 0x0064, all -> 0x0061 }
        if (r15 != 0) goto L_0x004a;
    L_0x005b:
        if (r14 == 0) goto L_0x0074;
    L_0x005d:
        r14.close();
        goto L_0x0074;
    L_0x0061:
        r15 = move-exception;
        r10 = r14;
        goto L_0x0075;
    L_0x0064:
        r15 = move-exception;
        r10 = r14;
        goto L_0x006a;
    L_0x0067:
        r15 = move-exception;
        goto L_0x0075;
    L_0x0069:
        r15 = move-exception;
    L_0x006a:
        r14 = "Error selecting hit ids";
        r13.zzd(r14, r15);	 Catch:{ all -> 0x0067 }
        if (r10 == 0) goto L_0x0074;
    L_0x0071:
        r10.close();
    L_0x0074:
        return r9;
    L_0x0075:
        if (r10 == 0) goto L_0x007a;
    L_0x0077:
        r10.close();
    L_0x007a:
        throw r15;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzbf.zzc(long):java.util.List");
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x00b4  */
    public final java.util.List<com.google.android.gms.internal.measurement.zzck> zzd(long r24) {
        /*
        r23 = this;
        r10 = r23;
        r1 = 0;
        r5 = (r24 > r1 ? 1 : (r24 == r1 ? 0 : -1));
        r11 = 0;
        r12 = 1;
        if (r5 < 0) goto L_0x000c;
    L_0x000a:
        r1 = r12;
        goto L_0x000d;
    L_0x000c:
        r1 = r11;
    L_0x000d:
        com.google.android.gms.common.internal.Preconditions.checkArgument(r1);
        com.google.android.gms.analytics.zzk.zzaf();
        r23.zzcl();
        r13 = r23.getWritableDatabase();
        r1 = 0;
        r14 = "hits2";
        r2 = 5;
        r15 = new java.lang.String[r2];	 Catch:{ SQLiteException -> 0x00a7, all -> 0x00a3 }
        r2 = "hit_id";
        r15[r11] = r2;	 Catch:{ SQLiteException -> 0x00a7, all -> 0x00a3 }
        r2 = "hit_time";
        r15[r12] = r2;	 Catch:{ SQLiteException -> 0x00a7, all -> 0x00a3 }
        r2 = "hit_string";
        r9 = 2;
        r15[r9] = r2;	 Catch:{ SQLiteException -> 0x00a7, all -> 0x00a3 }
        r2 = "hit_url";
        r7 = 3;
        r15[r7] = r2;	 Catch:{ SQLiteException -> 0x00a7, all -> 0x00a3 }
        r2 = "hit_app_id";
        r8 = 4;
        r15[r8] = r2;	 Catch:{ SQLiteException -> 0x00a7, all -> 0x00a3 }
        r16 = 0;
        r17 = 0;
        r18 = 0;
        r19 = 0;
        r2 = "%s ASC";
        r5 = new java.lang.Object[r12];	 Catch:{ SQLiteException -> 0x00a7, all -> 0x00a3 }
        r6 = "hit_id";
        r5[r11] = r6;	 Catch:{ SQLiteException -> 0x00a7, all -> 0x00a3 }
        r20 = java.lang.String.format(r2, r5);	 Catch:{ SQLiteException -> 0x00a7, all -> 0x00a3 }
        r21 = java.lang.Long.toString(r24);	 Catch:{ SQLiteException -> 0x00a7, all -> 0x00a3 }
        r13 = r13.query(r14, r15, r16, r17, r18, r19, r20, r21);	 Catch:{ SQLiteException -> 0x00a7, all -> 0x00a3 }
        r14 = new java.util.ArrayList;	 Catch:{ SQLiteException -> 0x00a1 }
        r14.<init>();	 Catch:{ SQLiteException -> 0x00a1 }
        r1 = r13.moveToFirst();	 Catch:{ SQLiteException -> 0x00a1 }
        if (r1 == 0) goto L_0x009b;
    L_0x005e:
        r15 = r13.getLong(r11);	 Catch:{ SQLiteException -> 0x00a1 }
        r4 = r13.getLong(r12);	 Catch:{ SQLiteException -> 0x00a1 }
        r1 = r13.getString(r9);	 Catch:{ SQLiteException -> 0x00a1 }
        r2 = r13.getString(r7);	 Catch:{ SQLiteException -> 0x00a1 }
        r17 = r13.getInt(r8);	 Catch:{ SQLiteException -> 0x00a1 }
        r3 = r10.zzv(r1);	 Catch:{ SQLiteException -> 0x00a1 }
        r6 = com.google.android.gms.internal.measurement.zzdg.zzai(r2);	 Catch:{ SQLiteException -> 0x00a1 }
        r2 = new com.google.android.gms.internal.measurement.zzck;	 Catch:{ SQLiteException -> 0x00a1 }
        r1 = r2;
        r11 = r2;
        r2 = r10;
        r18 = r7;
        r19 = r8;
        r7 = r15;
        r15 = r9;
        r9 = r17;
        r1.<init>(r2, r3, r4, r6, r7, r9);	 Catch:{ SQLiteException -> 0x00a1 }
        r14.add(r11);	 Catch:{ SQLiteException -> 0x00a1 }
        r1 = r13.moveToNext();	 Catch:{ SQLiteException -> 0x00a1 }
        if (r1 != 0) goto L_0x0094;
    L_0x0093:
        goto L_0x009b;
    L_0x0094:
        r9 = r15;
        r7 = r18;
        r8 = r19;
        r11 = 0;
        goto L_0x005e;
    L_0x009b:
        if (r13 == 0) goto L_0x00a0;
    L_0x009d:
        r13.close();
    L_0x00a0:
        return r14;
    L_0x00a1:
        r0 = move-exception;
        goto L_0x00a9;
    L_0x00a3:
        r0 = move-exception;
        r13 = r1;
    L_0x00a5:
        r1 = r0;
        goto L_0x00b2;
    L_0x00a7:
        r0 = move-exception;
        r13 = r1;
    L_0x00a9:
        r1 = r0;
        r2 = "Error loading hits from the database";
        r10.zze(r2, r1);	 Catch:{ all -> 0x00b0 }
        throw r1;	 Catch:{ all -> 0x00b0 }
    L_0x00b0:
        r0 = move-exception;
        goto L_0x00a5;
    L_0x00b2:
        if (r13 == 0) goto L_0x00b7;
    L_0x00b4:
        r13.close();
    L_0x00b7:
        throw r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzbf.zzd(long):java.util.List");
    }

    public final void zza(List<Long> list) {
        Preconditions.checkNotNull(list);
        zzk.zzaf();
        zzcl();
        if (!list.isEmpty()) {
            int i;
            StringBuilder stringBuilder = new StringBuilder("hit_id");
            stringBuilder.append(" in (");
            for (i = 0; i < list.size(); i++) {
                Long l = (Long) list.get(i);
                if (l == null || l.longValue() == 0) {
                    throw new SQLiteException("Invalid hit id");
                }
                if (i > 0) {
                    stringBuilder.append(",");
                }
                stringBuilder.append(l);
            }
            stringBuilder.append(")");
            String stringBuilder2 = stringBuilder.toString();
            try {
                SQLiteDatabase writableDatabase = getWritableDatabase();
                zza("Deleting dispatched hits. count", Integer.valueOf(list.size()));
                i = writableDatabase.delete("hits2", stringBuilder2, null);
                if (i != list.size()) {
                    zzb("Deleted fewer hits then expected", Integer.valueOf(list.size()), Integer.valueOf(i), stringBuilder2);
                }
            } catch (SQLiteException e) {
                zze("Error deleting hits", e);
                throw e;
            }
        }
    }

    public final void zze(long j) {
        zzk.zzaf();
        zzcl();
        List arrayList = new ArrayList(1);
        arrayList.add(Long.valueOf(j));
        zza("Deleting hit, id", Long.valueOf(j));
        zza(arrayList);
    }

    public final int zzdb() {
        zzk.zzaf();
        zzcl();
        if (!this.zzxi.zzj(MeasurementDispatcher.MILLIS_PER_DAY)) {
            return 0;
        }
        this.zzxi.start();
        zzq("Deleting stale hits (if any)");
        long currentTimeMillis = zzbx().currentTimeMillis() - 2592000000L;
        int delete = getWritableDatabase().delete("hits2", "hit_time < ?", new String[]{Long.toString(currentTimeMillis)});
        zza("Deleted stale hits, count", Integer.valueOf(delete));
        return delete;
    }

    public final long zzdc() {
        zzk.zzaf();
        zzcl();
        return zza(zzxg, null, 0);
    }

    public final long zza(long j, String str, String str2) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        zzcl();
        zzk.zzaf();
        return zza("SELECT hits_count FROM properties WHERE app_uid=? AND cid=? AND tid=?", new String[]{String.valueOf(j), str, str2}, 0);
    }

    /* JADX WARNING: Removed duplicated region for block: B:39:0x00cf  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00cf  */
    public final java.util.List<com.google.android.gms.internal.measurement.zzaz> zzf(long r28) {
        /*
        r27 = this;
        r1 = r27;
        r27.zzcl();
        com.google.android.gms.analytics.zzk.zzaf();
        r2 = r27.getWritableDatabase();
        r3 = 5;
        r4 = new java.lang.String[r3];	 Catch:{ SQLiteException -> 0x00c2, all -> 0x00be }
        r3 = "cid";
        r12 = 0;
        r4[r12] = r3;	 Catch:{ SQLiteException -> 0x00c2, all -> 0x00be }
        r3 = "tid";
        r13 = 1;
        r4[r13] = r3;	 Catch:{ SQLiteException -> 0x00c2, all -> 0x00be }
        r3 = "adid";
        r14 = 2;
        r4[r14] = r3;	 Catch:{ SQLiteException -> 0x00c2, all -> 0x00be }
        r3 = "hits_count";
        r15 = 3;
        r4[r15] = r3;	 Catch:{ SQLiteException -> 0x00c2, all -> 0x00be }
        r3 = "params";
        r10 = 4;
        r4[r10] = r3;	 Catch:{ SQLiteException -> 0x00c2, all -> 0x00be }
        r3 = com.google.android.gms.internal.measurement.zzcf.zzzc;	 Catch:{ SQLiteException -> 0x00c2, all -> 0x00be }
        r3 = r3.get();	 Catch:{ SQLiteException -> 0x00c2, all -> 0x00be }
        r3 = (java.lang.Integer) r3;	 Catch:{ SQLiteException -> 0x00c2, all -> 0x00be }
        r9 = r3.intValue();	 Catch:{ SQLiteException -> 0x00c2, all -> 0x00be }
        r16 = java.lang.String.valueOf(r9);	 Catch:{ SQLiteException -> 0x00c2, all -> 0x00be }
        r5 = "app_uid=?";
        r6 = new java.lang.String[r13];	 Catch:{ SQLiteException -> 0x00c2, all -> 0x00be }
        r3 = "0";
        r6[r12] = r3;	 Catch:{ SQLiteException -> 0x00c2, all -> 0x00be }
        r3 = "properties";
        r7 = 0;
        r8 = 0;
        r17 = 0;
        r11 = r9;
        r9 = r17;
        r10 = r16;
        r2 = r2.query(r3, r4, r5, r6, r7, r8, r9, r10);	 Catch:{ SQLiteException -> 0x00c2, all -> 0x00be }
        r3 = new java.util.ArrayList;	 Catch:{ SQLiteException -> 0x00ba, all -> 0x00b7 }
        r3.<init>();	 Catch:{ SQLiteException -> 0x00ba, all -> 0x00b7 }
        r4 = r2.moveToFirst();	 Catch:{ SQLiteException -> 0x00ba, all -> 0x00b7 }
        if (r4 == 0) goto L_0x00a6;
    L_0x005a:
        r4 = r2.getString(r12);	 Catch:{ SQLiteException -> 0x00ba, all -> 0x00b7 }
        r5 = r2.getString(r13);	 Catch:{ SQLiteException -> 0x00ba, all -> 0x00b7 }
        r6 = r2.getInt(r14);	 Catch:{ SQLiteException -> 0x00ba, all -> 0x00b7 }
        if (r6 == 0) goto L_0x006b;
    L_0x0068:
        r23 = r13;
        goto L_0x006d;
    L_0x006b:
        r23 = r12;
    L_0x006d:
        r6 = r2.getInt(r15);	 Catch:{ SQLiteException -> 0x00ba, all -> 0x00b7 }
        r6 = (long) r6;	 Catch:{ SQLiteException -> 0x00ba, all -> 0x00b7 }
        r8 = 4;
        r9 = r2.getString(r8);	 Catch:{ SQLiteException -> 0x00ba, all -> 0x00b7 }
        r26 = r1.zzw(r9);	 Catch:{ SQLiteException -> 0x00ba, all -> 0x00b7 }
        r9 = android.text.TextUtils.isEmpty(r4);	 Catch:{ SQLiteException -> 0x00ba, all -> 0x00b7 }
        if (r9 != 0) goto L_0x009b;
    L_0x0081:
        r9 = android.text.TextUtils.isEmpty(r5);	 Catch:{ SQLiteException -> 0x00ba, all -> 0x00b7 }
        if (r9 == 0) goto L_0x0088;
    L_0x0087:
        goto L_0x009b;
    L_0x0088:
        r9 = new com.google.android.gms.internal.measurement.zzaz;	 Catch:{ SQLiteException -> 0x00ba, all -> 0x00b7 }
        r19 = 0;
        r18 = r9;
        r21 = r4;
        r22 = r5;
        r24 = r6;
        r18.<init>(r19, r21, r22, r23, r24, r26);	 Catch:{ SQLiteException -> 0x00ba, all -> 0x00b7 }
        r3.add(r9);	 Catch:{ SQLiteException -> 0x00ba, all -> 0x00b7 }
        goto L_0x00a0;
    L_0x009b:
        r6 = "Read property with empty client id or tracker id";
        r1.zzc(r6, r4, r5);	 Catch:{ SQLiteException -> 0x00ba, all -> 0x00b7 }
    L_0x00a0:
        r4 = r2.moveToNext();	 Catch:{ SQLiteException -> 0x00ba, all -> 0x00b7 }
        if (r4 != 0) goto L_0x005a;
    L_0x00a6:
        r4 = r3.size();	 Catch:{ SQLiteException -> 0x00ba, all -> 0x00b7 }
        if (r4 < r11) goto L_0x00b1;
    L_0x00ac:
        r4 = "Sending hits to too many properties. Campaign report might be incorrect";
        r1.zzt(r4);	 Catch:{ SQLiteException -> 0x00ba, all -> 0x00b7 }
    L_0x00b1:
        if (r2 == 0) goto L_0x00b6;
    L_0x00b3:
        r2.close();
    L_0x00b6:
        return r3;
    L_0x00b7:
        r0 = move-exception;
        r11 = r2;
        goto L_0x00cc;
    L_0x00ba:
        r0 = move-exception;
        r11 = r2;
        r2 = r0;
        goto L_0x00c5;
    L_0x00be:
        r0 = move-exception;
        r2 = r0;
        r11 = 0;
        goto L_0x00cd;
    L_0x00c2:
        r0 = move-exception;
        r2 = r0;
        r11 = 0;
    L_0x00c5:
        r3 = "Error loading hits from the database";
        r1.zze(r3, r2);	 Catch:{ all -> 0x00cb }
        throw r2;	 Catch:{ all -> 0x00cb }
    L_0x00cb:
        r0 = move-exception;
    L_0x00cc:
        r2 = r0;
    L_0x00cd:
        if (r11 == 0) goto L_0x00d2;
    L_0x00cf:
        r11.close();
    L_0x00d2:
        throw r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzbf.zzf(long):java.util.List");
    }

    public final void close() {
        try {
            this.zzxh.close();
        } catch (SQLiteException e) {
            zze("Sql error closing database", e);
        } catch (IllegalStateException e2) {
            zze("Error closing database", e2);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x0035  */
    private final long zza(java.lang.String r4, java.lang.String[] r5) {
        /*
        r3 = this;
        r5 = r3.getWritableDatabase();
        r0 = 0;
        r5 = r5.rawQuery(r4, r0);	 Catch:{ SQLiteException -> 0x0029, all -> 0x0026 }
        r0 = r5.moveToFirst();	 Catch:{ SQLiteException -> 0x0024 }
        if (r0 == 0) goto L_0x001a;
    L_0x000f:
        r0 = 0;
        r0 = r5.getLong(r0);	 Catch:{ SQLiteException -> 0x0024 }
        if (r5 == 0) goto L_0x0019;
    L_0x0016:
        r5.close();
    L_0x0019:
        return r0;
    L_0x001a:
        r0 = new android.database.sqlite.SQLiteException;	 Catch:{ SQLiteException -> 0x0024 }
        r1 = "Database returned empty set";
        r0.<init>(r1);	 Catch:{ SQLiteException -> 0x0024 }
        throw r0;	 Catch:{ SQLiteException -> 0x0024 }
    L_0x0022:
        r4 = move-exception;
        goto L_0x0033;
    L_0x0024:
        r0 = move-exception;
        goto L_0x002d;
    L_0x0026:
        r4 = move-exception;
        r5 = r0;
        goto L_0x0033;
    L_0x0029:
        r5 = move-exception;
        r2 = r0;
        r0 = r5;
        r5 = r2;
    L_0x002d:
        r1 = "Database error";
        r3.zzd(r1, r4, r0);	 Catch:{ all -> 0x0022 }
        throw r0;	 Catch:{ all -> 0x0022 }
    L_0x0033:
        if (r5 == 0) goto L_0x0038;
    L_0x0035:
        r5.close();
    L_0x0038:
        throw r4;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzbf.zza(java.lang.String, java.lang.String[]):long");
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x0033  */
    private final long zza(java.lang.String r1, java.lang.String[] r2, long r3) {
        /*
        r0 = this;
        r3 = r0.getWritableDatabase();
        r4 = 0;
        r2 = r3.rawQuery(r1, r2);	 Catch:{ SQLiteException -> 0x002a }
        r3 = r2.moveToFirst();	 Catch:{ SQLiteException -> 0x0025, all -> 0x0022 }
        if (r3 == 0) goto L_0x001a;
    L_0x000f:
        r3 = 0;
        r3 = r2.getLong(r3);	 Catch:{ SQLiteException -> 0x0025, all -> 0x0022 }
        if (r2 == 0) goto L_0x0019;
    L_0x0016:
        r2.close();
    L_0x0019:
        return r3;
    L_0x001a:
        if (r2 == 0) goto L_0x001f;
    L_0x001c:
        r2.close();
    L_0x001f:
        r1 = 0;
        return r1;
    L_0x0022:
        r1 = move-exception;
        r4 = r2;
        goto L_0x0031;
    L_0x0025:
        r3 = move-exception;
        r4 = r2;
        goto L_0x002b;
    L_0x0028:
        r1 = move-exception;
        goto L_0x0031;
    L_0x002a:
        r3 = move-exception;
    L_0x002b:
        r2 = "Database error";
        r0.zzd(r2, r1, r3);	 Catch:{ all -> 0x0028 }
        throw r3;	 Catch:{ all -> 0x0028 }
    L_0x0031:
        if (r4 == 0) goto L_0x0036;
    L_0x0033:
        r4.close();
    L_0x0036:
        throw r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzbf.zza(java.lang.String, java.lang.String[], long):long");
    }

    @VisibleForTesting
    private final Map<String, String> zzv(String str) {
        if (TextUtils.isEmpty(str)) {
            return new HashMap(0);
        }
        try {
            if (!str.startsWith("?")) {
                String str2 = "?";
                str = String.valueOf(str);
                str = str.length() != 0 ? str2.concat(str) : new String(str2);
            }
            return HttpUtils.parse(new URI(str), "UTF-8");
        } catch (URISyntaxException e) {
            zze("Error parsing hit parameters", e);
            return new HashMap(0);
        }
    }

    @VisibleForTesting
    private final Map<String, String> zzw(String str) {
        if (TextUtils.isEmpty(str)) {
            return new HashMap(0);
        }
        try {
            String str2 = "?";
            str = String.valueOf(str);
            return HttpUtils.parse(new URI(str.length() != 0 ? str2.concat(str) : new String(str2)), "UTF-8");
        } catch (URISyntaxException e) {
            zze("Error parsing property parameters", e);
            return new HashMap(0);
        }
    }

    /* Access modifiers changed, original: final */
    @VisibleForTesting
    public final SQLiteDatabase getWritableDatabase() {
        try {
            return this.zzxh.getWritableDatabase();
        } catch (SQLiteException e) {
            zzd("Error opening database", e);
            throw e;
        }
    }
}
