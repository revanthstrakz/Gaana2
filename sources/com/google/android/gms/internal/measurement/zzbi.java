package com.google.android.gms.internal.measurement;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.net.Uri.Builder;
import android.text.TextUtils;
import android.util.Pair;
import com.comscore.measurement.MeasurementDispatcher;
import com.facebook.internal.NativeProtocol;
import com.google.android.gms.analytics.CampaignTrackingReceiver;
import com.google.android.gms.analytics.zza;
import com.google.android.gms.analytics.zzg;
import com.google.android.gms.analytics.zzi;
import com.google.android.gms.analytics.zzk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.wrappers.Wrappers;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

final class zzbi extends zzau {
    private boolean started;
    private final zzbf zzxl;
    private final zzcr zzxm;
    private final zzcq zzxn;
    private final zzba zzxo;
    private long zzxp = Long.MIN_VALUE;
    private final zzbz zzxq;
    private final zzbz zzxr;
    private final zzdc zzxs;
    private long zzxt;
    private boolean zzxu;

    protected zzbi(zzaw zzaw, zzay zzay) {
        super(zzaw);
        Preconditions.checkNotNull(zzay);
        this.zzxn = new zzcq(zzaw);
        this.zzxl = new zzbf(zzaw);
        this.zzxm = new zzcr(zzaw);
        this.zzxo = new zzba(zzaw);
        this.zzxs = new zzdc(zzbx());
        this.zzxq = new zzbj(this, zzaw);
        this.zzxr = new zzbk(this, zzaw);
    }

    /* Access modifiers changed, original: protected|final */
    public final void zzag() {
        this.zzxl.zzq();
        this.zzxm.zzq();
        this.zzxo.zzq();
    }

    /* Access modifiers changed, original: final */
    public final void start() {
        zzcl();
        Preconditions.checkState(this.started ^ 1, "Analytics backend already started");
        this.started = true;
        zzca().zza(new zzbl(this));
    }

    private final boolean zzx(String str) {
        return Wrappers.packageManager(getContext()).checkCallingOrSelfPermission(str) == 0;
    }

    /* Access modifiers changed, original: protected|final */
    public final void zzdg() {
        zzcl();
        zzk.zzaf();
        Context context = zzbw().getContext();
        if (!zzcw.zza(context)) {
            zzt("AnalyticsReceiver is not registered or is disabled. Register the receiver for reliable dispatching on non-Google Play devices. See http://goo.gl/8Rd3yj for instructions.");
        } else if (!zzcx.zze(context)) {
            zzu("AnalyticsService is not registered or is disabled. Analytics service at risk of not starting. See http://goo.gl/8Rd3yj for instructions.");
        }
        if (!CampaignTrackingReceiver.zza(context)) {
            zzt("CampaignTrackingReceiver is not registered, not exported or is disabled. Installation campaign tracking is not possible. See http://goo.gl/8Rd3yj for instructions.");
        }
        zzcf().zzff();
        if (!zzx("android.permission.ACCESS_NETWORK_STATE")) {
            zzu("Missing required android.permission.ACCESS_NETWORK_STATE. Google Analytics disabled. See http://goo.gl/8Rd3yj for instructions");
            zzdq();
        }
        if (!zzx("android.permission.INTERNET")) {
            zzu("Missing required android.permission.INTERNET. Google Analytics disabled. See http://goo.gl/8Rd3yj for instructions");
            zzdq();
        }
        if (zzcx.zze(getContext())) {
            zzq("AnalyticsService registered in the app manifest and enabled");
        } else {
            zzt("AnalyticsService not registered in the app manifest. Hits might not be delivered reliably. See http://goo.gl/8Rd3yj for instructions.");
        }
        if (!(this.zzxu || this.zzxl.isEmpty())) {
            zzdj();
        }
        zzdm();
    }

    private final void zzdh() {
        zzb(new zzbm(this));
    }

    /* Access modifiers changed, original: final */
    public final void zzbv() {
        zzk.zzaf();
        this.zzxt = zzbx().currentTimeMillis();
    }

    /* Access modifiers changed, original: protected|final */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0044 A:{LOOP_START, LOOP:1: B:15:0x0044->B:24:0x0069} */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0040 A:{SYNTHETIC} */
    public final void onServiceConnected() {
        /*
        r5 = this;
        com.google.android.gms.analytics.zzk.zzaf();
        com.google.android.gms.analytics.zzk.zzaf();
        r5.zzcl();
        r0 = com.google.android.gms.internal.measurement.zzbx.zzdx();
        if (r0 != 0) goto L_0x0014;
    L_0x000f:
        r0 = "Service client disabled. Can't dispatch local hits to device AnalyticsService";
        r5.zzt(r0);
    L_0x0014:
        r0 = r5.zzxo;
        r0 = r0.isConnected();
        if (r0 != 0) goto L_0x0022;
    L_0x001c:
        r0 = "Service not connected";
        r5.zzq(r0);
        return;
    L_0x0022:
        r0 = r5.zzxl;
        r0 = r0.isEmpty();
        if (r0 != 0) goto L_0x007e;
    L_0x002a:
        r0 = "Dispatching local hits to device AnalyticsService";
        r5.zzq(r0);
    L_0x002f:
        r0 = r5.zzxl;	 Catch:{ SQLiteException -> 0x0074 }
        r1 = com.google.android.gms.internal.measurement.zzbx.zzeb();	 Catch:{ SQLiteException -> 0x0074 }
        r1 = (long) r1;	 Catch:{ SQLiteException -> 0x0074 }
        r0 = r0.zzd(r1);	 Catch:{ SQLiteException -> 0x0074 }
        r1 = r0.isEmpty();	 Catch:{ SQLiteException -> 0x0074 }
        if (r1 == 0) goto L_0x0044;
    L_0x0040:
        r5.zzdm();	 Catch:{ SQLiteException -> 0x0074 }
        return;
    L_0x0044:
        r1 = r0.isEmpty();
        if (r1 != 0) goto L_0x002f;
    L_0x004a:
        r1 = 0;
        r1 = r0.get(r1);
        r1 = (com.google.android.gms.internal.measurement.zzck) r1;
        r2 = r5.zzxo;
        r2 = r2.zzb(r1);
        if (r2 != 0) goto L_0x005d;
    L_0x0059:
        r5.zzdm();
        return;
    L_0x005d:
        r0.remove(r1);
        r2 = r5.zzxl;	 Catch:{ SQLiteException -> 0x006a }
        r3 = r1.zzeq();	 Catch:{ SQLiteException -> 0x006a }
        r2.zze(r3);	 Catch:{ SQLiteException -> 0x006a }
        goto L_0x0044;
    L_0x006a:
        r0 = move-exception;
        r1 = "Failed to remove hit that was send for delivery";
        r5.zze(r1, r0);
        r5.zzdo();
        return;
    L_0x0074:
        r0 = move-exception;
        r1 = "Failed to read hits from store";
        r5.zze(r1, r0);
        r5.zzdo();
        return;
    L_0x007e:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzbi.onServiceConnected():void");
    }

    private final void zzdi() {
        try {
            this.zzxl.zzdb();
            zzdm();
        } catch (SQLiteException e) {
            zzd("Failed to delete stale hits", e);
        }
        this.zzxr.zzh(MeasurementDispatcher.MILLIS_PER_DAY);
    }

    /* Access modifiers changed, original: protected|final */
    public final void zzb(zzaz zzaz) {
        zzk.zzaf();
        zzb("Sending first hit to property", zzaz.zzct());
        if (!zzcf().zzfg().zzj(zzbx.zzeh())) {
            String zzfj = zzcf().zzfj();
            if (!TextUtils.isEmpty(zzfj)) {
                zzy zza = zzdg.zza(zzby(), zzfj);
                zzb("Found relevant installation campaign", zza);
                zza(zzaz, zza);
            }
        }
    }

    public final void zzg(long j) {
        zzk.zzaf();
        zzcl();
        if (j < 0) {
            j = 0;
        }
        this.zzxp = j;
        zzdm();
    }

    private final void zzdj() {
        if (!this.zzxu && zzbx.zzdx() && !this.zzxo.isConnected()) {
            if (this.zzxs.zzj(((Long) zzcf.zzaaj.get()).longValue())) {
                this.zzxs.start();
                zzq("Connecting to service");
                if (this.zzxo.connect()) {
                    zzq("Connected to service");
                    this.zzxs.clear();
                    onServiceConnected();
                }
            }
        }
    }

    public final long zza(zzaz zzaz, boolean z) {
        Preconditions.checkNotNull(zzaz);
        zzcl();
        zzk.zzaf();
        try {
            this.zzxl.beginTransaction();
            zzbf zzbf = this.zzxl;
            long zzcs = zzaz.zzcs();
            Preconditions.checkNotEmpty(zzaz.zzbd());
            zzbf.zzcl();
            zzk.zzaf();
            int delete = zzbf.getWritableDatabase().delete(InAppConstants.INAPP_BACKGROUND_PROPERTIES, "app_uid=? AND cid<>?", new String[]{String.valueOf(zzcs), r4});
            if (delete > 0) {
                zzbf.zza("Deleted property records", Integer.valueOf(delete));
            }
            zzcs = this.zzxl.zza(zzaz.zzcs(), zzaz.zzbd(), zzaz.zzct());
            zzaz.zzb(zzcs + 1);
            zzbf = this.zzxl;
            Preconditions.checkNotNull(zzaz);
            zzbf.zzcl();
            zzk.zzaf();
            SQLiteDatabase writableDatabase = zzbf.getWritableDatabase();
            Map zzcw = zzaz.zzcw();
            Preconditions.checkNotNull(zzcw);
            Builder builder = new Builder();
            for (Entry entry : zzcw.entrySet()) {
                builder.appendQueryParameter((String) entry.getKey(), (String) entry.getValue());
            }
            String encodedQuery = builder.build().getEncodedQuery();
            if (encodedQuery == null) {
                encodedQuery = "";
            }
            ContentValues contentValues = new ContentValues();
            contentValues.put("app_uid", Long.valueOf(zzaz.zzcs()));
            contentValues.put("cid", zzaz.zzbd());
            contentValues.put("tid", zzaz.zzct());
            contentValues.put("adid", Integer.valueOf(zzaz.zzcu()));
            contentValues.put("hits_count", Long.valueOf(zzaz.zzcv()));
            contentValues.put(NativeProtocol.WEB_DIALOG_PARAMS, encodedQuery);
            try {
                if (writableDatabase.insertWithOnConflict(InAppConstants.INAPP_BACKGROUND_PROPERTIES, null, contentValues, 5) == -1) {
                    zzbf.zzu("Failed to insert/update a property (got -1)");
                }
            } catch (SQLiteException e) {
                zzbf.zze("Error storing a property", e);
            }
            this.zzxl.setTransactionSuccessful();
            try {
                this.zzxl.endTransaction();
            } catch (SQLiteException e2) {
                zze("Failed to end transaction", e2);
            }
            return zzcs;
        } catch (SQLiteException e22) {
            zze("Failed to update Analytics property", e22);
            try {
                this.zzxl.endTransaction();
            } catch (SQLiteException e222) {
                zze("Failed to end transaction", e222);
            }
            return -1;
        } catch (Throwable th) {
            try {
                this.zzxl.endTransaction();
            } catch (SQLiteException e3) {
                zze("Failed to end transaction", e3);
            }
            throw th;
        }
    }

    public final void zza(zzck zzck) {
        Preconditions.checkNotNull(zzck);
        zzk.zzaf();
        zzcl();
        if (this.zzxu) {
            zzr("Hit delivery not possible. Missing network permissions. See http://goo.gl/8Rd3yj for instructions");
        } else {
            zza("Delivering hit", zzck);
        }
        if (TextUtils.isEmpty(zzck.zzev())) {
            Pair zzfm = zzcf().zzfk().zzfm();
            if (zzfm != null) {
                Long l = (Long) zzfm.second;
                String str = (String) zzfm.first;
                String valueOf = String.valueOf(l);
                StringBuilder stringBuilder = new StringBuilder((1 + String.valueOf(valueOf).length()) + String.valueOf(str).length());
                stringBuilder.append(valueOf);
                stringBuilder.append(":");
                stringBuilder.append(str);
                str = stringBuilder.toString();
                HashMap hashMap = new HashMap(zzck.zzcw());
                hashMap.put("_m", str);
                zzck = new zzck(this, hashMap, zzck.zzer(), zzck.zzet(), zzck.zzeq(), zzck.zzep(), zzck.zzes());
            }
        }
        zzdj();
        if (this.zzxo.zzb(zzck)) {
            zzr("Hit sent to the device AnalyticsService for delivery");
            return;
        }
        try {
            this.zzxl.zzc(zzck);
            zzdm();
        } catch (SQLiteException e) {
            zze("Delivery failed to save hit to a database", e);
            zzby().zza(zzck, "deliver: failed to insert hit to database");
        }
    }

    public final void zzbr() {
        zzk.zzaf();
        zzcl();
        zzq("Delete all hits from local store");
        try {
            zzbf zzbf = this.zzxl;
            zzk.zzaf();
            zzbf.zzcl();
            zzbf.getWritableDatabase().delete("hits2", null, null);
            zzbf = this.zzxl;
            zzk.zzaf();
            zzbf.zzcl();
            zzbf.getWritableDatabase().delete(InAppConstants.INAPP_BACKGROUND_PROPERTIES, null, null);
            zzdm();
        } catch (SQLiteException e) {
            zzd("Failed to delete hits from store", e);
        }
        zzdj();
        if (this.zzxo.zzcx()) {
            zzq("Device service unavailable. Can't clear hits stored on the device service.");
        }
    }

    /* JADX WARNING: Missing block: B:26:0x0090, code skipped:
            zzd("Database contains successfully uploaded hit", java.lang.Long.valueOf(r4), java.lang.Integer.valueOf(r6.size()));
            zzdo();
     */
    private final boolean zzdk() {
        /*
        r11 = this;
        com.google.android.gms.analytics.zzk.zzaf();
        r11.zzcl();
        r0 = "Dispatching a batch of local hits";
        r11.zzq(r0);
        r0 = r11.zzxo;
        r0 = r0.isConnected();
        r0 = r0 ^ 1;
        r1 = r11.zzxm;
        r1 = r1.zzfb();
        r1 = r1 ^ 1;
        r2 = 0;
        if (r0 == 0) goto L_0x0026;
    L_0x001e:
        if (r1 == 0) goto L_0x0026;
    L_0x0020:
        r0 = "No network or service available. Will retry later";
        r11.zzq(r0);
        return r2;
    L_0x0026:
        r0 = com.google.android.gms.internal.measurement.zzbx.zzeb();
        r1 = com.google.android.gms.internal.measurement.zzbx.zzec();
        r0 = java.lang.Math.max(r0, r1);
        r0 = (long) r0;
        r3 = new java.util.ArrayList;
        r3.<init>();
        r4 = 0;
    L_0x003a:
        r6 = r11.zzxl;	 Catch:{ all -> 0x01ba }
        r6.beginTransaction();	 Catch:{ all -> 0x01ba }
        r3.clear();	 Catch:{ all -> 0x01ba }
        r6 = r11.zzxl;	 Catch:{ SQLiteException -> 0x019c }
        r6 = r6.zzd(r0);	 Catch:{ SQLiteException -> 0x019c }
        r7 = r6.isEmpty();	 Catch:{ SQLiteException -> 0x019c }
        if (r7 == 0) goto L_0x006b;
    L_0x004e:
        r0 = "Store is empty, nothing to dispatch";
        r11.zzq(r0);	 Catch:{ SQLiteException -> 0x019c }
        r11.zzdo();	 Catch:{ SQLiteException -> 0x019c }
        r0 = r11.zzxl;	 Catch:{ SQLiteException -> 0x0061 }
        r0.setTransactionSuccessful();	 Catch:{ SQLiteException -> 0x0061 }
        r0 = r11.zzxl;	 Catch:{ SQLiteException -> 0x0061 }
        r0.endTransaction();	 Catch:{ SQLiteException -> 0x0061 }
        return r2;
    L_0x0061:
        r0 = move-exception;
        r1 = "Failed to commit local dispatch transaction";
        r11.zze(r1, r0);
        r11.zzdo();
        return r2;
    L_0x006b:
        r7 = "Hits loaded from store. count";
        r8 = r6.size();	 Catch:{ SQLiteException -> 0x019c }
        r8 = java.lang.Integer.valueOf(r8);	 Catch:{ SQLiteException -> 0x019c }
        r11.zza(r7, r8);	 Catch:{ SQLiteException -> 0x019c }
        r7 = r6.iterator();	 Catch:{ all -> 0x01ba }
    L_0x007c:
        r8 = r7.hasNext();	 Catch:{ all -> 0x01ba }
        if (r8 == 0) goto L_0x00b9;
    L_0x0082:
        r8 = r7.next();	 Catch:{ all -> 0x01ba }
        r8 = (com.google.android.gms.internal.measurement.zzck) r8;	 Catch:{ all -> 0x01ba }
        r8 = r8.zzeq();	 Catch:{ all -> 0x01ba }
        r10 = (r8 > r4 ? 1 : (r8 == r4 ? 0 : -1));
        if (r10 != 0) goto L_0x007c;
    L_0x0090:
        r0 = "Database contains successfully uploaded hit";
        r1 = java.lang.Long.valueOf(r4);	 Catch:{ all -> 0x01ba }
        r3 = r6.size();	 Catch:{ all -> 0x01ba }
        r3 = java.lang.Integer.valueOf(r3);	 Catch:{ all -> 0x01ba }
        r11.zzd(r0, r1, r3);	 Catch:{ all -> 0x01ba }
        r11.zzdo();	 Catch:{ all -> 0x01ba }
        r0 = r11.zzxl;	 Catch:{ SQLiteException -> 0x00af }
        r0.setTransactionSuccessful();	 Catch:{ SQLiteException -> 0x00af }
        r0 = r11.zzxl;	 Catch:{ SQLiteException -> 0x00af }
        r0.endTransaction();	 Catch:{ SQLiteException -> 0x00af }
        return r2;
    L_0x00af:
        r0 = move-exception;
        r1 = "Failed to commit local dispatch transaction";
        r11.zze(r1, r0);
        r11.zzdo();
        return r2;
    L_0x00b9:
        r7 = r11.zzxo;	 Catch:{ all -> 0x01ba }
        r7 = r7.isConnected();	 Catch:{ all -> 0x01ba }
        if (r7 == 0) goto L_0x011d;
    L_0x00c1:
        r7 = "Service connected, sending hits to the service";
        r11.zzq(r7);	 Catch:{ all -> 0x01ba }
    L_0x00c6:
        r7 = r6.isEmpty();	 Catch:{ all -> 0x01ba }
        if (r7 != 0) goto L_0x011d;
    L_0x00cc:
        r7 = r6.get(r2);	 Catch:{ all -> 0x01ba }
        r7 = (com.google.android.gms.internal.measurement.zzck) r7;	 Catch:{ all -> 0x01ba }
        r8 = r11.zzxo;	 Catch:{ all -> 0x01ba }
        r8 = r8.zzb(r7);	 Catch:{ all -> 0x01ba }
        if (r8 == 0) goto L_0x011d;
    L_0x00da:
        r8 = r7.zzeq();	 Catch:{ all -> 0x01ba }
        r4 = java.lang.Math.max(r4, r8);	 Catch:{ all -> 0x01ba }
        r6.remove(r7);	 Catch:{ all -> 0x01ba }
        r8 = "Hit sent do device AnalyticsService for delivery";
        r11.zzb(r8, r7);	 Catch:{ all -> 0x01ba }
        r8 = r11.zzxl;	 Catch:{ SQLiteException -> 0x00ff }
        r9 = r7.zzeq();	 Catch:{ SQLiteException -> 0x00ff }
        r8.zze(r9);	 Catch:{ SQLiteException -> 0x00ff }
        r7 = r7.zzeq();	 Catch:{ SQLiteException -> 0x00ff }
        r7 = java.lang.Long.valueOf(r7);	 Catch:{ SQLiteException -> 0x00ff }
        r3.add(r7);	 Catch:{ SQLiteException -> 0x00ff }
        goto L_0x00c6;
    L_0x00ff:
        r0 = move-exception;
        r1 = "Failed to remove hit that was send for delivery";
        r11.zze(r1, r0);	 Catch:{ all -> 0x01ba }
        r11.zzdo();	 Catch:{ all -> 0x01ba }
        r0 = r11.zzxl;	 Catch:{ SQLiteException -> 0x0113 }
        r0.setTransactionSuccessful();	 Catch:{ SQLiteException -> 0x0113 }
        r0 = r11.zzxl;	 Catch:{ SQLiteException -> 0x0113 }
        r0.endTransaction();	 Catch:{ SQLiteException -> 0x0113 }
        return r2;
    L_0x0113:
        r0 = move-exception;
        r1 = "Failed to commit local dispatch transaction";
        r11.zze(r1, r0);
        r11.zzdo();
        return r2;
    L_0x011d:
        r7 = r11.zzxm;	 Catch:{ all -> 0x01ba }
        r7 = r7.zzfb();	 Catch:{ all -> 0x01ba }
        if (r7 == 0) goto L_0x016b;
    L_0x0125:
        r7 = r11.zzxm;	 Catch:{ all -> 0x01ba }
        r6 = r7.zzb(r6);	 Catch:{ all -> 0x01ba }
        r7 = r6.iterator();	 Catch:{ all -> 0x01ba }
    L_0x012f:
        r8 = r7.hasNext();	 Catch:{ all -> 0x01ba }
        if (r8 == 0) goto L_0x0144;
    L_0x0135:
        r8 = r7.next();	 Catch:{ all -> 0x01ba }
        r8 = (java.lang.Long) r8;	 Catch:{ all -> 0x01ba }
        r8 = r8.longValue();	 Catch:{ all -> 0x01ba }
        r4 = java.lang.Math.max(r4, r8);	 Catch:{ all -> 0x01ba }
        goto L_0x012f;
    L_0x0144:
        r7 = r11.zzxl;	 Catch:{ SQLiteException -> 0x014d }
        r7.zza(r6);	 Catch:{ SQLiteException -> 0x014d }
        r3.addAll(r6);	 Catch:{ SQLiteException -> 0x014d }
        goto L_0x016b;
    L_0x014d:
        r0 = move-exception;
        r1 = "Failed to remove successfully uploaded hits";
        r11.zze(r1, r0);	 Catch:{ all -> 0x01ba }
        r11.zzdo();	 Catch:{ all -> 0x01ba }
        r0 = r11.zzxl;	 Catch:{ SQLiteException -> 0x0161 }
        r0.setTransactionSuccessful();	 Catch:{ SQLiteException -> 0x0161 }
        r0 = r11.zzxl;	 Catch:{ SQLiteException -> 0x0161 }
        r0.endTransaction();	 Catch:{ SQLiteException -> 0x0161 }
        return r2;
    L_0x0161:
        r0 = move-exception;
        r1 = "Failed to commit local dispatch transaction";
        r11.zze(r1, r0);
        r11.zzdo();
        return r2;
    L_0x016b:
        r6 = r3.isEmpty();	 Catch:{ all -> 0x01ba }
        if (r6 == 0) goto L_0x0186;
    L_0x0171:
        r0 = r11.zzxl;	 Catch:{ SQLiteException -> 0x017c }
        r0.setTransactionSuccessful();	 Catch:{ SQLiteException -> 0x017c }
        r0 = r11.zzxl;	 Catch:{ SQLiteException -> 0x017c }
        r0.endTransaction();	 Catch:{ SQLiteException -> 0x017c }
        return r2;
    L_0x017c:
        r0 = move-exception;
        r1 = "Failed to commit local dispatch transaction";
        r11.zze(r1, r0);
        r11.zzdo();
        return r2;
    L_0x0186:
        r6 = r11.zzxl;	 Catch:{ SQLiteException -> 0x0192 }
        r6.setTransactionSuccessful();	 Catch:{ SQLiteException -> 0x0192 }
        r6 = r11.zzxl;	 Catch:{ SQLiteException -> 0x0192 }
        r6.endTransaction();	 Catch:{ SQLiteException -> 0x0192 }
        goto L_0x003a;
    L_0x0192:
        r0 = move-exception;
        r1 = "Failed to commit local dispatch transaction";
        r11.zze(r1, r0);
        r11.zzdo();
        return r2;
    L_0x019c:
        r0 = move-exception;
        r1 = "Failed to read hits from persisted store";
        r11.zzd(r1, r0);	 Catch:{ all -> 0x01ba }
        r11.zzdo();	 Catch:{ all -> 0x01ba }
        r0 = r11.zzxl;	 Catch:{ SQLiteException -> 0x01b0 }
        r0.setTransactionSuccessful();	 Catch:{ SQLiteException -> 0x01b0 }
        r0 = r11.zzxl;	 Catch:{ SQLiteException -> 0x01b0 }
        r0.endTransaction();	 Catch:{ SQLiteException -> 0x01b0 }
        return r2;
    L_0x01b0:
        r0 = move-exception;
        r1 = "Failed to commit local dispatch transaction";
        r11.zze(r1, r0);
        r11.zzdo();
        return r2;
    L_0x01ba:
        r0 = move-exception;
        r1 = r11.zzxl;	 Catch:{ SQLiteException -> 0x01c6 }
        r1.setTransactionSuccessful();	 Catch:{ SQLiteException -> 0x01c6 }
        r1 = r11.zzxl;	 Catch:{ SQLiteException -> 0x01c6 }
        r1.endTransaction();	 Catch:{ SQLiteException -> 0x01c6 }
        throw r0;
    L_0x01c6:
        r0 = move-exception;
        r1 = "Failed to commit local dispatch transaction";
        r11.zze(r1, r0);
        r11.zzdo();
        return r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzbi.zzdk():boolean");
    }

    public final void zzb(zzcd zzcd) {
        long j = this.zzxt;
        zzk.zzaf();
        zzcl();
        long zzfh = zzcf().zzfh();
        zzb("Dispatching local hits. Elapsed time since last dispatch (ms)", Long.valueOf(zzfh != 0 ? Math.abs(zzbx().currentTimeMillis() - zzfh) : -1));
        zzdj();
        try {
            zzdk();
            zzcf().zzfi();
            zzdm();
            if (zzcd != null) {
                zzcd.zza(null);
            }
            if (this.zzxt != j) {
                this.zzxn.zzfa();
            }
        } catch (Exception e) {
            zze("Local dispatch failed", e);
            zzcf().zzfi();
            zzdm();
            if (zzcd != null) {
                zzcd.zza(e);
            }
        }
    }

    public final void zzdl() {
        zzk.zzaf();
        zzcl();
        zzr("Sync dispatching local hits");
        long j = this.zzxt;
        zzdj();
        try {
            zzdk();
            zzcf().zzfi();
            zzdm();
            if (this.zzxt != j) {
                this.zzxn.zzfa();
            }
        } catch (Exception e) {
            zze("Sync local dispatch failed", e);
            zzdm();
        }
    }

    private final long zzdc() {
        zzk.zzaf();
        zzcl();
        try {
            return this.zzxl.zzdc();
        } catch (SQLiteException e) {
            zze("Failed to get min/max hit times from local store", e);
            return 0;
        }
    }

    public final void zzdm() {
        zzk.zzaf();
        zzcl();
        boolean z = true;
        boolean z2 = !this.zzxu && zzdp() > 0;
        if (!z2) {
            this.zzxn.unregister();
            zzdo();
        } else if (this.zzxl.isEmpty()) {
            this.zzxn.unregister();
            zzdo();
        } else {
            if (!((Boolean) zzcf.zzaae.get()).booleanValue()) {
                this.zzxn.zzey();
                z = this.zzxn.isConnected();
            }
            if (z) {
                long abs;
                zzdn();
                long zzdp = zzdp();
                long zzfh = zzcf().zzfh();
                if (zzfh != 0) {
                    abs = zzdp - Math.abs(zzbx().currentTimeMillis() - zzfh);
                    if (abs <= 0) {
                        abs = Math.min(zzbx.zzdz(), zzdp);
                    }
                } else {
                    abs = Math.min(zzbx.zzdz(), zzdp);
                }
                zza("Dispatch scheduled (ms)", Long.valueOf(abs));
                if (this.zzxq.zzej()) {
                    this.zzxq.zzi(Math.max(1, abs + this.zzxq.zzei()));
                    return;
                } else {
                    this.zzxq.zzh(abs);
                    return;
                }
            }
            zzdo();
            zzdn();
        }
    }

    private final void zzdn() {
        zzcc zzcd = zzcd();
        if (zzcd.zzem() && !zzcd.zzej()) {
            long zzdc = zzdc();
            if (zzdc != 0 && Math.abs(zzbx().currentTimeMillis() - zzdc) <= ((Long) zzcf.zzzi.get()).longValue()) {
                zza("Dispatch alarm scheduled (ms)", Long.valueOf(zzbx.zzea()));
                zzcd.zzen();
            }
        }
    }

    private final void zzdo() {
        if (this.zzxq.zzej()) {
            zzq("All hits dispatched or no network/service. Going to power save mode");
        }
        this.zzxq.cancel();
        zzcc zzcd = zzcd();
        if (zzcd.zzej()) {
            zzcd.cancel();
        }
    }

    private final long zzdp() {
        if (this.zzxp != Long.MIN_VALUE) {
            return this.zzxp;
        }
        long longValue = ((Long) zzcf.zzzd.get()).longValue();
        zzdh zzce = zzce();
        zzce.zzcl();
        if (zzce.zzacr) {
            zzdh zzce2 = zzce();
            zzce2.zzcl();
            longValue = ((long) zzce2.zzaat) * 1000;
        }
        return longValue;
    }

    public final void zzy(String str) {
        Preconditions.checkNotEmpty(str);
        zzk.zzaf();
        zzy zza = zzdg.zza(zzby(), str);
        if (zza == null) {
            zzd("Parsing failed. Ignoring invalid campaign data", str);
            return;
        }
        String zzfj = zzcf().zzfj();
        if (str.equals(zzfj)) {
            zzt("Ignoring duplicate install campaign");
        } else if (TextUtils.isEmpty(zzfj)) {
            zzcf().zzac(str);
            if (zzcf().zzfg().zzj(zzbx.zzeh())) {
                zzd("Campaign received too late, ignoring", zza);
                return;
            }
            zzb("Received installation campaign", zza);
            for (zzaz zza2 : this.zzxl.zzf(0)) {
                zza(zza2, zza);
            }
        } else {
            zzd("Ignoring multiple install campaigns. original, new", zzfj, str);
        }
    }

    private final void zza(zzaz zzaz, zzy zzy) {
        Preconditions.checkNotNull(zzaz);
        Preconditions.checkNotNull(zzy);
        zza zza = new zza(zzbw());
        zza.zza(zzaz.zzct());
        zza.enableAdvertisingIdCollection(zzaz.zzcu());
        zzg zzm = zza.zzm();
        zzag zzag = (zzag) zzm.zzb(zzag.class);
        zzag.zzl("data");
        zzag.zzb(true);
        zzm.zza((zzi) zzy);
        zzab zzab = (zzab) zzm.zzb(zzab.class);
        zzx zzx = (zzx) zzm.zzb(zzx.class);
        for (Entry entry : zzaz.zzcw().entrySet()) {
            String str = (String) entry.getKey();
            String str2 = (String) entry.getValue();
            if ("an".equals(str)) {
                zzx.setAppName(str2);
            } else if ("av".equals(str)) {
                zzx.setAppVersion(str2);
            } else if ("aid".equals(str)) {
                zzx.setAppId(str2);
            } else if ("aiid".equals(str)) {
                zzx.setAppInstallerId(str2);
            } else if ("uid".equals(str)) {
                zzag.setUserId(str2);
            } else {
                zzab.set(str, str2);
            }
        }
        zzb("Sending installation campaign to", zzaz.zzct(), zzy);
        zzm.zza(zzcf().zzff());
        zzm.zzw();
    }

    private final void zzdq() {
        zzcl();
        zzk.zzaf();
        this.zzxu = true;
        this.zzxo.disconnect();
        zzdm();
    }
}
