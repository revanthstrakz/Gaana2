package com.google.android.gms.internal.ads;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.ConditionVariable;
import android.support.annotation.Nullable;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.gms.common.util.VisibleForTesting;
import org.json.JSONException;
import org.json.JSONObject;

@zzark
public final class zzaak implements OnSharedPreferenceChangeListener {
    private final Object lock = new Object();
    private final ConditionVariable zzcnn = new ConditionVariable();
    @VisibleForTesting
    private volatile boolean zzcno = false;
    @Nullable
    private SharedPreferences zzcnp = null;
    private Context zzcnq;
    private JSONObject zzcnr = new JSONObject();
    private volatile boolean zztd = false;

    /* JADX WARNING: Missing block: B:29:0x003d, code skipped:
            return;
     */
    public final void initialize(android.content.Context r5) {
        /*
        r4 = this;
        r0 = r4.zztd;
        if (r0 == 0) goto L_0x0005;
    L_0x0004:
        return;
    L_0x0005:
        r0 = r4.lock;
        monitor-enter(r0);
        r1 = r4.zztd;	 Catch:{ all -> 0x0069 }
        if (r1 == 0) goto L_0x000e;
    L_0x000c:
        monitor-exit(r0);	 Catch:{ all -> 0x0069 }
        return;
    L_0x000e:
        r1 = r4.zzcno;	 Catch:{ all -> 0x0069 }
        r2 = 1;
        if (r1 != 0) goto L_0x0015;
    L_0x0013:
        r4.zzcno = r2;	 Catch:{ all -> 0x0069 }
    L_0x0015:
        r1 = r5.getApplicationContext();	 Catch:{ all -> 0x0069 }
        if (r1 != 0) goto L_0x001d;
    L_0x001b:
        r1 = r5;
        goto L_0x0021;
    L_0x001d:
        r1 = r5.getApplicationContext();	 Catch:{ all -> 0x0069 }
    L_0x0021:
        r4.zzcnq = r1;	 Catch:{ all -> 0x0069 }
        r1 = 0;
        r3 = com.google.android.gms.common.GooglePlayServicesUtilLight.getRemoteContext(r5);	 Catch:{ all -> 0x0060 }
        if (r3 != 0) goto L_0x0033;
    L_0x002a:
        if (r5 == 0) goto L_0x0033;
    L_0x002c:
        r3 = r5.getApplicationContext();	 Catch:{ all -> 0x0060 }
        if (r3 != 0) goto L_0x0033;
    L_0x0032:
        r3 = r5;
    L_0x0033:
        if (r3 != 0) goto L_0x003e;
    L_0x0035:
        r4.zzcno = r1;	 Catch:{ all -> 0x0069 }
        r5 = r4.zzcnn;	 Catch:{ all -> 0x0069 }
        r5.open();	 Catch:{ all -> 0x0069 }
        monitor-exit(r0);	 Catch:{ all -> 0x0069 }
        return;
    L_0x003e:
        com.google.android.gms.internal.ads.zzwu.zzpx();	 Catch:{ all -> 0x0060 }
        r5 = "google_ads_flags";
        r5 = r3.getSharedPreferences(r5, r1);	 Catch:{ all -> 0x0060 }
        r4.zzcnp = r5;	 Catch:{ all -> 0x0060 }
        r5 = r4.zzcnp;	 Catch:{ all -> 0x0060 }
        if (r5 == 0) goto L_0x0052;
    L_0x004d:
        r5 = r4.zzcnp;	 Catch:{ all -> 0x0060 }
        r5.registerOnSharedPreferenceChangeListener(r4);	 Catch:{ all -> 0x0060 }
    L_0x0052:
        r4.zzqy();	 Catch:{ all -> 0x0060 }
        r4.zztd = r2;	 Catch:{ all -> 0x0060 }
        r4.zzcno = r1;	 Catch:{ all -> 0x0069 }
        r5 = r4.zzcnn;	 Catch:{ all -> 0x0069 }
        r5.open();	 Catch:{ all -> 0x0069 }
        monitor-exit(r0);	 Catch:{ all -> 0x0069 }
        return;
    L_0x0060:
        r5 = move-exception;
        r4.zzcno = r1;	 Catch:{ all -> 0x0069 }
        r1 = r4.zzcnn;	 Catch:{ all -> 0x0069 }
        r1.open();	 Catch:{ all -> 0x0069 }
        throw r5;	 Catch:{ all -> 0x0069 }
    L_0x0069:
        r5 = move-exception;
        monitor-exit(r0);	 Catch:{ all -> 0x0069 }
        throw r5;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzaak.initialize(android.content.Context):void");
    }

    public final <T> T zzd(zzaac<T> zzaac) {
        if (!this.zzcnn.block(DefaultRenderersFactory.DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS)) {
            synchronized (this.lock) {
                if (this.zzcno) {
                } else {
                    throw new IllegalStateException("Flags.initialize() was not called!");
                }
            }
        }
        if (!this.zztd || this.zzcnp == null) {
            synchronized (this.lock) {
                if (this.zztd) {
                    if (this.zzcnp == null) {
                    }
                }
                Object zzqv = zzaac.zzqv();
                return zzqv;
            }
        }
        if (zzaac.getSource() == 1 && this.zzcnr.has(zzaac.getKey())) {
            return zzaac.zzb(this.zzcnr);
        }
        return zzbak.zza(this.zzcnq, new zzaam(this, zzaac));
    }

    public final void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
        if ("flag_configuration".equals(str)) {
            zzqy();
        }
    }

    private final void zzqy() {
        if (this.zzcnp != null) {
            try {
                this.zzcnr = new JSONObject((String) zzbak.zza(this.zzcnq, new zzaal(this)));
            } catch (JSONException unused) {
            }
        }
    }

    /* Access modifiers changed, original: final|synthetic */
    public final /* synthetic */ String zzqz() throws Exception {
        return this.zzcnp.getString("flag_configuration", "{}");
    }
}
