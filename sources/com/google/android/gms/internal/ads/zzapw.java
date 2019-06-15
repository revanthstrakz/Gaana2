package com.google.android.gms.internal.ads;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.gaana.login.sso.SsoErrorCodes;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.gms.ads.internal.zzbb;
import com.google.android.gms.common.util.VisibleForTesting;
import com.helpshift.support.webkit.b;
import com.til.colombia.android.internal.e;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@zzark
public final class zzapw implements Callable<zzaxf> {
    @VisibleForTesting
    private static long zzdtd = 10;
    private final Context mContext;
    private int mErrorCode;
    private final Object mLock = new Object();
    private final zzaba zzbln;
    private final zzaqp zzbqa;
    private final zzcu zzdcf;
    private final zzaxg zzdsk;
    private final zzazs zzdte;
    private final zzbb zzdtf;
    private boolean zzdtg;
    private List<String> zzdth;
    private JSONObject zzdti;
    private String zzdtj;
    @Nullable
    private String zzdtk;

    public zzapw(Context context, zzbb zzbb, zzazs zzazs, zzcu zzcu, zzaxg zzaxg, zzaba zzaba) {
        this.mContext = context;
        this.zzdtf = zzbb;
        this.zzdte = zzazs;
        this.zzdsk = zzaxg;
        this.zzdcf = zzcu;
        this.zzbln = zzaba;
        this.zzbqa = zzbb.zzkn();
        this.zzdtg = false;
        this.mErrorCode = -2;
        this.zzdth = null;
        this.zzdtj = null;
        this.zzdtk = null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:85:0x01ec A:{Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, TimeoutException -> 0x0210, Exception -> 0x0209 }} */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x017a A:{Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, TimeoutException -> 0x0210, Exception -> 0x0209 }} */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x0178 A:{Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, TimeoutException -> 0x0210, Exception -> 0x0209 }} */
    /* JADX WARNING: Removed duplicated region for block: B:85:0x01ec A:{Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, TimeoutException -> 0x0210, Exception -> 0x0209 }} */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0078 A:{Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, TimeoutException -> 0x0210, Exception -> 0x0209 }} */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0071 A:{Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, TimeoutException -> 0x0210, Exception -> 0x0209 }} */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0081 A:{Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, TimeoutException -> 0x0210, Exception -> 0x0209 }} */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x007b A:{Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, TimeoutException -> 0x0210, Exception -> 0x0209 }} */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x00b5 A:{Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, TimeoutException -> 0x0210, Exception -> 0x0209 }} */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x0178 A:{Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, TimeoutException -> 0x0210, Exception -> 0x0209 }} */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x017a A:{Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, TimeoutException -> 0x0210, Exception -> 0x0209 }} */
    /* JADX WARNING: Removed duplicated region for block: B:85:0x01ec A:{Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, TimeoutException -> 0x0210, Exception -> 0x0209 }} */
    /* JADX WARNING: Removed duplicated region for block: B:96:0x0221  */
    /* JADX WARNING: Removed duplicated region for block: B:96:0x0221  */
    /* JADX WARNING: Missing block: B:12:0x003e, code skipped:
            if (r3.length() != 0) goto L_0x0044;
     */
    private final com.google.android.gms.internal.ads.zzaxf zzwd() {
        /*
        r15 = this;
        r0 = 0;
        r1 = 0;
        r2 = r15.zzdtf;	 Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, TimeoutException -> 0x0210, Exception -> 0x0209 }
        r11 = r2.getUuid();	 Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, TimeoutException -> 0x0210, Exception -> 0x0209 }
        r2 = r15.zzwe();	 Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, TimeoutException -> 0x0210, Exception -> 0x0209 }
        if (r2 != 0) goto L_0x006e;
    L_0x000e:
        r2 = new org.json.JSONObject;	 Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, TimeoutException -> 0x0210, Exception -> 0x0209 }
        r3 = r15.zzdsk;	 Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, TimeoutException -> 0x0210, Exception -> 0x0209 }
        r3 = r3.zzehy;	 Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, TimeoutException -> 0x0210, Exception -> 0x0209 }
        r3 = r3.zzdyb;	 Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, TimeoutException -> 0x0210, Exception -> 0x0209 }
        r2.<init>(r3);	 Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, TimeoutException -> 0x0210, Exception -> 0x0209 }
        r3 = new org.json.JSONObject;	 Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, TimeoutException -> 0x0210, Exception -> 0x0209 }
        r4 = r15.zzdsk;	 Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, TimeoutException -> 0x0210, Exception -> 0x0209 }
        r4 = r4.zzehy;	 Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, TimeoutException -> 0x0210, Exception -> 0x0209 }
        r4 = r4.zzdyb;	 Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, TimeoutException -> 0x0210, Exception -> 0x0209 }
        r3.<init>(r4);	 Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, TimeoutException -> 0x0210, Exception -> 0x0209 }
        r4 = r3.length();	 Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, TimeoutException -> 0x0210, Exception -> 0x0209 }
        if (r4 == 0) goto L_0x0040;
    L_0x002a:
        r4 = "ads";
        r3 = r3.optJSONArray(r4);	 Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, TimeoutException -> 0x0210, Exception -> 0x0209 }
        if (r3 == 0) goto L_0x0037;
    L_0x0032:
        r3 = r3.optJSONObject(r0);	 Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, TimeoutException -> 0x0210, Exception -> 0x0209 }
        goto L_0x0038;
    L_0x0037:
        r3 = r1;
    L_0x0038:
        if (r3 == 0) goto L_0x0040;
    L_0x003a:
        r3 = r3.length();	 Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, TimeoutException -> 0x0210, Exception -> 0x0209 }
        if (r3 != 0) goto L_0x0044;
    L_0x0040:
        r3 = 3;
        r15.zzcs(r3);	 Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, TimeoutException -> 0x0210, Exception -> 0x0209 }
    L_0x0044:
        r3 = r15.zzbqa;	 Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, TimeoutException -> 0x0210, Exception -> 0x0209 }
        r2 = r3.zzh(r2);	 Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, TimeoutException -> 0x0210, Exception -> 0x0209 }
        r3 = zzdtd;	 Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, TimeoutException -> 0x0210, Exception -> 0x0209 }
        r5 = java.util.concurrent.TimeUnit.SECONDS;	 Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, TimeoutException -> 0x0210, Exception -> 0x0209 }
        r2 = r2.get(r3, r5);	 Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, TimeoutException -> 0x0210, Exception -> 0x0209 }
        r2 = (org.json.JSONObject) r2;	 Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, TimeoutException -> 0x0210, Exception -> 0x0209 }
        r3 = "success";
        r3 = r2.optBoolean(r3, r0);	 Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, TimeoutException -> 0x0210, Exception -> 0x0209 }
        if (r3 == 0) goto L_0x006e;
    L_0x005c:
        r3 = "json";
        r2 = r2.getJSONObject(r3);	 Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, TimeoutException -> 0x0210, Exception -> 0x0209 }
        r3 = "ads";
        r2 = r2.optJSONArray(r3);	 Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, TimeoutException -> 0x0210, Exception -> 0x0209 }
        r2 = r2.getJSONObject(r0);	 Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, TimeoutException -> 0x0210, Exception -> 0x0209 }
        r8 = r2;
        goto L_0x006f;
    L_0x006e:
        r8 = r1;
    L_0x006f:
        if (r8 == 0) goto L_0x0078;
    L_0x0071:
        r2 = "enable_omid";
        r2 = r8.optBoolean(r2);	 Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, TimeoutException -> 0x0210, Exception -> 0x0209 }
        goto L_0x0079;
    L_0x0078:
        r2 = r0;
    L_0x0079:
        if (r2 != 0) goto L_0x0081;
    L_0x007b:
        r3 = com.google.android.gms.internal.ads.zzbbq.zzm(r1);	 Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, TimeoutException -> 0x0210, Exception -> 0x0209 }
    L_0x007f:
        r12 = r3;
        goto L_0x00af;
    L_0x0081:
        r3 = "omid_settings";
        r3 = r8.optJSONObject(r3);	 Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, TimeoutException -> 0x0210, Exception -> 0x0209 }
        if (r3 != 0) goto L_0x008e;
    L_0x0089:
        r3 = com.google.android.gms.internal.ads.zzbbq.zzm(r1);	 Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, TimeoutException -> 0x0210, Exception -> 0x0209 }
        goto L_0x007f;
    L_0x008e:
        r4 = "omid_html";
        r3 = r3.optString(r4);	 Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, TimeoutException -> 0x0210, Exception -> 0x0209 }
        r4 = android.text.TextUtils.isEmpty(r3);	 Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, TimeoutException -> 0x0210, Exception -> 0x0209 }
        if (r4 == 0) goto L_0x009f;
    L_0x009a:
        r3 = com.google.android.gms.internal.ads.zzbbq.zzm(r1);	 Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, TimeoutException -> 0x0210, Exception -> 0x0209 }
        goto L_0x007f;
    L_0x009f:
        r4 = com.google.android.gms.internal.ads.zzbbq.zzm(r1);	 Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, TimeoutException -> 0x0210, Exception -> 0x0209 }
        r5 = new com.google.android.gms.internal.ads.zzapx;	 Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, TimeoutException -> 0x0210, Exception -> 0x0209 }
        r5.<init>(r15, r3);	 Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, TimeoutException -> 0x0210, Exception -> 0x0209 }
        r3 = com.google.android.gms.internal.ads.zzbcg.zzepo;	 Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, TimeoutException -> 0x0210, Exception -> 0x0209 }
        r3 = com.google.android.gms.internal.ads.zzbbq.zza(r4, r5, r3);	 Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, TimeoutException -> 0x0210, Exception -> 0x0209 }
        goto L_0x007f;
    L_0x00af:
        r3 = r15.zzwe();	 Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, TimeoutException -> 0x0210, Exception -> 0x0209 }
        if (r3 != 0) goto L_0x016d;
    L_0x00b5:
        if (r8 != 0) goto L_0x00b9;
    L_0x00b7:
        goto L_0x016d;
    L_0x00b9:
        r3 = "template_id";
        r3 = r8.getString(r3);	 Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, TimeoutException -> 0x0210, Exception -> 0x0209 }
        r4 = "instream";
        r5 = "type";
        r5 = r8.optString(r5);	 Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, TimeoutException -> 0x0210, Exception -> 0x0209 }
        r4 = r4.equals(r5);	 Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, TimeoutException -> 0x0210, Exception -> 0x0209 }
        r5 = r15.zzdsk;	 Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, TimeoutException -> 0x0210, Exception -> 0x0209 }
        r5 = r5.zzeag;	 Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, TimeoutException -> 0x0210, Exception -> 0x0209 }
        r5 = r5.zzbti;	 Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, TimeoutException -> 0x0210, Exception -> 0x0209 }
        if (r5 == 0) goto L_0x00dc;
    L_0x00d3:
        r5 = r15.zzdsk;	 Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, TimeoutException -> 0x0210, Exception -> 0x0209 }
        r5 = r5.zzeag;	 Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, TimeoutException -> 0x0210, Exception -> 0x0209 }
        r5 = r5.zzbti;	 Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, TimeoutException -> 0x0210, Exception -> 0x0209 }
        r5 = r5.zzdcs;	 Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, TimeoutException -> 0x0210, Exception -> 0x0209 }
        goto L_0x00dd;
    L_0x00dc:
        r5 = r0;
    L_0x00dd:
        r6 = r15.zzdsk;	 Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, TimeoutException -> 0x0210, Exception -> 0x0209 }
        r6 = r6.zzeag;	 Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, TimeoutException -> 0x0210, Exception -> 0x0209 }
        r6 = r6.zzbti;	 Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, TimeoutException -> 0x0210, Exception -> 0x0209 }
        if (r6 == 0) goto L_0x00ee;
    L_0x00e5:
        r6 = r15.zzdsk;	 Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, TimeoutException -> 0x0210, Exception -> 0x0209 }
        r6 = r6.zzeag;	 Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, TimeoutException -> 0x0210, Exception -> 0x0209 }
        r6 = r6.zzbti;	 Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, TimeoutException -> 0x0210, Exception -> 0x0209 }
        r6 = r6.zzdcu;	 Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, TimeoutException -> 0x0210, Exception -> 0x0209 }
        goto L_0x00ef;
    L_0x00ee:
        r6 = r0;
    L_0x00ef:
        if (r4 == 0) goto L_0x00f8;
    L_0x00f1:
        r3 = new com.google.android.gms.internal.ads.zzapv;	 Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, TimeoutException -> 0x0210, Exception -> 0x0209 }
        r3.<init>();	 Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, TimeoutException -> 0x0210, Exception -> 0x0209 }
        goto L_0x016e;
    L_0x00f8:
        r4 = "2";
        r4 = r4.equals(r3);	 Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, TimeoutException -> 0x0210, Exception -> 0x0209 }
        if (r4 == 0) goto L_0x010a;
    L_0x0100:
        r3 = new com.google.android.gms.internal.ads.zzaqq;	 Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, TimeoutException -> 0x0210, Exception -> 0x0209 }
        r4 = r15.zzdsk;	 Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, TimeoutException -> 0x0210, Exception -> 0x0209 }
        r4 = r4.zzehx;	 Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, TimeoutException -> 0x0210, Exception -> 0x0209 }
        r3.<init>(r5, r6, r4);	 Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, TimeoutException -> 0x0210, Exception -> 0x0209 }
        goto L_0x016e;
    L_0x010a:
        r4 = "1";
        r4 = r4.equals(r3);	 Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, TimeoutException -> 0x0210, Exception -> 0x0209 }
        if (r4 == 0) goto L_0x011c;
    L_0x0112:
        r3 = new com.google.android.gms.internal.ads.zzaqr;	 Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, TimeoutException -> 0x0210, Exception -> 0x0209 }
        r4 = r15.zzdsk;	 Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, TimeoutException -> 0x0210, Exception -> 0x0209 }
        r4 = r4.zzehx;	 Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, TimeoutException -> 0x0210, Exception -> 0x0209 }
        r3.<init>(r5, r6, r4);	 Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, TimeoutException -> 0x0210, Exception -> 0x0209 }
        goto L_0x016e;
    L_0x011c:
        r4 = "3";
        r3 = r4.equals(r3);	 Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, TimeoutException -> 0x0210, Exception -> 0x0209 }
        if (r3 == 0) goto L_0x016a;
    L_0x0124:
        r3 = "custom_template_id";
        r3 = r8.getString(r3);	 Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, TimeoutException -> 0x0210, Exception -> 0x0209 }
        r4 = new com.google.android.gms.internal.ads.zzbcl;	 Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, TimeoutException -> 0x0210, Exception -> 0x0209 }
        r4.<init>();	 Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, TimeoutException -> 0x0210, Exception -> 0x0209 }
        r6 = com.google.android.gms.internal.ads.zzayh.zzelc;	 Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, TimeoutException -> 0x0210, Exception -> 0x0209 }
        r7 = new com.google.android.gms.internal.ads.zzapz;	 Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, TimeoutException -> 0x0210, Exception -> 0x0209 }
        r7.<init>(r15, r4, r3);	 Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, TimeoutException -> 0x0210, Exception -> 0x0209 }
        r6.post(r7);	 Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, TimeoutException -> 0x0210, Exception -> 0x0209 }
        r6 = zzdtd;	 Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, TimeoutException -> 0x0210, Exception -> 0x0209 }
        r3 = java.util.concurrent.TimeUnit.SECONDS;	 Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, TimeoutException -> 0x0210, Exception -> 0x0209 }
        r3 = r4.get(r6, r3);	 Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, TimeoutException -> 0x0210, Exception -> 0x0209 }
        if (r3 == 0) goto L_0x0149;
    L_0x0143:
        r3 = new com.google.android.gms.internal.ads.zzaqs;	 Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, TimeoutException -> 0x0210, Exception -> 0x0209 }
        r3.<init>(r5);	 Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, TimeoutException -> 0x0210, Exception -> 0x0209 }
        goto L_0x016e;
    L_0x0149:
        r3 = "No handler for custom template: ";
        r4 = "custom_template_id";
        r4 = r8.getString(r4);	 Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, TimeoutException -> 0x0210, Exception -> 0x0209 }
        r4 = java.lang.String.valueOf(r4);	 Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, TimeoutException -> 0x0210, Exception -> 0x0209 }
        r5 = r4.length();	 Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, TimeoutException -> 0x0210, Exception -> 0x0209 }
        if (r5 == 0) goto L_0x0160;
    L_0x015b:
        r3 = r3.concat(r4);	 Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, TimeoutException -> 0x0210, Exception -> 0x0209 }
        goto L_0x0166;
    L_0x0160:
        r4 = new java.lang.String;	 Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, TimeoutException -> 0x0210, Exception -> 0x0209 }
        r4.<init>(r3);	 Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, TimeoutException -> 0x0210, Exception -> 0x0209 }
        r3 = r4;
    L_0x0166:
        com.google.android.gms.internal.ads.zzbbd.e(r3);	 Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, TimeoutException -> 0x0210, Exception -> 0x0209 }
        goto L_0x016d;
    L_0x016a:
        r15.zzcs(r0);	 Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, TimeoutException -> 0x0210, Exception -> 0x0209 }
    L_0x016d:
        r3 = r1;
    L_0x016e:
        r4 = r15.zzwe();	 Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, TimeoutException -> 0x0210, Exception -> 0x0209 }
        if (r4 != 0) goto L_0x01e7;
    L_0x0174:
        if (r3 == 0) goto L_0x01e7;
    L_0x0176:
        if (r8 != 0) goto L_0x017a;
    L_0x0178:
        goto L_0x01e7;
    L_0x017a:
        r4 = "tracking_urls_and_actions";
        r4 = r8.getJSONObject(r4);	 Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, TimeoutException -> 0x0210, Exception -> 0x0209 }
        r5 = "impression_tracking_urls";
        r5 = r4.optJSONArray(r5);	 Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, TimeoutException -> 0x0210, Exception -> 0x0209 }
        if (r5 != 0) goto L_0x018a;
    L_0x0188:
        r6 = r1;
        goto L_0x01a0;
    L_0x018a:
        r6 = r5.length();	 Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, TimeoutException -> 0x0210, Exception -> 0x0209 }
        r6 = new java.lang.String[r6];	 Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, TimeoutException -> 0x0210, Exception -> 0x0209 }
        r7 = r0;
    L_0x0191:
        r9 = r5.length();	 Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, TimeoutException -> 0x0210, Exception -> 0x0209 }
        if (r7 >= r9) goto L_0x01a0;
    L_0x0197:
        r9 = r5.getString(r7);	 Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, TimeoutException -> 0x0210, Exception -> 0x0209 }
        r6[r7] = r9;	 Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, TimeoutException -> 0x0210, Exception -> 0x0209 }
        r7 = r7 + 1;
        goto L_0x0191;
    L_0x01a0:
        if (r6 != 0) goto L_0x01a4;
    L_0x01a2:
        r5 = r1;
        goto L_0x01a8;
    L_0x01a4:
        r5 = java.util.Arrays.asList(r6);	 Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, TimeoutException -> 0x0210, Exception -> 0x0209 }
    L_0x01a8:
        r15.zzdth = r5;	 Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, TimeoutException -> 0x0210, Exception -> 0x0209 }
        r5 = "active_view";
        r4 = r4.optJSONObject(r5);	 Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, TimeoutException -> 0x0210, Exception -> 0x0209 }
        r15.zzdti = r4;	 Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, TimeoutException -> 0x0210, Exception -> 0x0209 }
        r4 = "debug_signals";
        r4 = r8.optString(r4);	 Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, TimeoutException -> 0x0210, Exception -> 0x0209 }
        r15.zzdtj = r4;	 Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, TimeoutException -> 0x0210, Exception -> 0x0209 }
        r4 = "omid_settings";
        r4 = r8.optString(r4);	 Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, TimeoutException -> 0x0210, Exception -> 0x0209 }
        r15.zzdtk = r4;	 Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, TimeoutException -> 0x0210, Exception -> 0x0209 }
        r13 = r3.zza(r15, r8);	 Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, TimeoutException -> 0x0210, Exception -> 0x0209 }
        if (r13 != 0) goto L_0x01ce;
    L_0x01c8:
        r3 = "Failed to retrieve ad assets.";
        com.google.android.gms.internal.ads.zzbbd.e(r3);	 Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, TimeoutException -> 0x0210, Exception -> 0x0209 }
        goto L_0x01e7;
    L_0x01ce:
        r14 = new com.google.android.gms.internal.ads.zzach;	 Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, TimeoutException -> 0x0210, Exception -> 0x0209 }
        r4 = r15.mContext;	 Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, TimeoutException -> 0x0210, Exception -> 0x0209 }
        r5 = r15.zzdtf;	 Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, TimeoutException -> 0x0210, Exception -> 0x0209 }
        r6 = r15.zzbqa;	 Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, TimeoutException -> 0x0210, Exception -> 0x0209 }
        r7 = r15.zzdcf;	 Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, TimeoutException -> 0x0210, Exception -> 0x0209 }
        r3 = r15.zzdsk;	 Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, TimeoutException -> 0x0210, Exception -> 0x0209 }
        r3 = r3.zzeag;	 Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, TimeoutException -> 0x0210, Exception -> 0x0209 }
        r10 = r3.zzbsp;	 Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, TimeoutException -> 0x0210, Exception -> 0x0209 }
        r3 = r14;
        r9 = r13;
        r3.<init>(r4, r5, r6, r7, r8, r9, r10, r11);	 Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, TimeoutException -> 0x0210, Exception -> 0x0209 }
        r13.zzb(r14);	 Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, TimeoutException -> 0x0210, Exception -> 0x0209 }
        goto L_0x01e8;
    L_0x01e7:
        r13 = r1;
    L_0x01e8:
        r3 = r13 instanceof com.google.android.gms.internal.ads.zzabw;	 Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, TimeoutException -> 0x0210, Exception -> 0x0209 }
        if (r3 == 0) goto L_0x01fb;
    L_0x01ec:
        r3 = r13;
        r3 = (com.google.android.gms.internal.ads.zzabw) r3;	 Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, TimeoutException -> 0x0210, Exception -> 0x0209 }
        r4 = new com.google.android.gms.internal.ads.zzaqa;	 Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, TimeoutException -> 0x0210, Exception -> 0x0209 }
        r4.<init>(r15, r3);	 Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, TimeoutException -> 0x0210, Exception -> 0x0209 }
        r3 = r15.zzbqa;	 Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, TimeoutException -> 0x0210, Exception -> 0x0209 }
        r5 = "/nativeAdCustomClick";
        r3.zza(r5, r4);	 Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, TimeoutException -> 0x0210, Exception -> 0x0209 }
    L_0x01fb:
        r2 = r15.zza(r13, r2);	 Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, TimeoutException -> 0x0210, Exception -> 0x0209 }
        r3 = r15.zzdtf;	 Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, TimeoutException -> 0x0210, Exception -> 0x0209 }
        r4 = zzb(r12);	 Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, TimeoutException -> 0x0210, Exception -> 0x0209 }
        r3.zzg(r4);	 Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, InterruptedException | CancellationException | ExecutionException | JSONException -> 0x0217, TimeoutException -> 0x0210, Exception -> 0x0209 }
        return r2;
    L_0x0209:
        r2 = move-exception;
        r3 = "Error occured while doing native ads initialization.";
        com.google.android.gms.internal.ads.zzbbd.zzc(r3, r2);
        goto L_0x021d;
    L_0x0210:
        r2 = move-exception;
        r3 = "Timeout when loading native ad.";
        com.google.android.gms.internal.ads.zzbbd.zzc(r3, r2);
        goto L_0x021d;
    L_0x0217:
        r2 = move-exception;
        r3 = "Malformed native JSON response.";
        com.google.android.gms.internal.ads.zzbbd.zzc(r3, r2);
    L_0x021d:
        r2 = r15.zzdtg;
        if (r2 != 0) goto L_0x0224;
    L_0x0221:
        r15.zzcs(r0);
    L_0x0224:
        r0 = r15.zza(r1, r0);
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzapw.zzwd():com.google.android.gms.internal.ads.zzaxf");
    }

    private static zzbgg zzb(zzbcb<zzbgg> zzbcb) {
        try {
            return (zzbgg) zzbcb.get((long) ((Integer) zzwu.zzpz().zzd(zzaan.zzcui)).intValue(), TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            zzbbd.zzc("", e);
            Thread.currentThread().interrupt();
            return null;
        } catch (CancellationException | ExecutionException | TimeoutException e2) {
            zzbbd.zzc("", e2);
            return null;
        }
    }

    private final void zzc(zzadx zzadx, String str) {
        try {
            zzaeh zzar = this.zzdtf.zzar(zzadx.getCustomTemplateId());
            if (zzar != null) {
                zzar.zzb(zzadx, str);
            }
        } catch (RemoteException e) {
            StringBuilder stringBuilder = new StringBuilder(40 + String.valueOf(str).length());
            stringBuilder.append("Failed to call onCustomClick for asset ");
            stringBuilder.append(str);
            stringBuilder.append(".");
            zzbbd.zzc(stringBuilder.toString(), e);
        }
    }

    private final zzaxf zza(zzacf zzacf, boolean z) {
        int i;
        synchronized (this.mLock) {
            try {
                int i2 = this.mErrorCode;
                if (zzacf == null && this.mErrorCode == -2) {
                    i2 = 0;
                }
                i = i2;
            } catch (Throwable th) {
                while (true) {
                    Throwable th2 = th;
                }
            }
        }
        zzacf zzacf2 = i != -2 ? null : zzacf;
        zzwb zzwb = this.zzdsk.zzeag.zzdwg;
        List list = this.zzdsk.zzehy.zzdlq;
        List list2 = this.zzdsk.zzehy.zzdlr;
        List list3 = this.zzdth;
        int i3 = this.zzdsk.zzehy.orientation;
        long j = this.zzdsk.zzehy.zzdlx;
        String str = this.zzdsk.zzeag.zzdwj;
        zzwf zzwf = this.zzdsk.zzbst;
        long j2 = this.zzdsk.zzehy.zzdyc;
        List list4 = list;
        long j3 = this.zzdsk.zzehn;
        long j4 = j2;
        long j5 = this.zzdsk.zzeho;
        String str2 = this.zzdsk.zzehy.zzdyi;
        JSONObject jSONObject = this.zzdti;
        boolean z2 = this.zzdsk.zzehy.zzdyu;
        zzaso zzaso = this.zzdsk.zzehy.zzdyv;
        List list5 = this.zzdsk.zzehy.zzdlu;
        long j6 = j3;
        String str3 = this.zzdtj;
        zzum zzum = this.zzdsk.zzehw;
        return new zzaxf(zzwb, null, list4, i, list2, list3, i3, j, str, false, null, null, null, null, null, 0, zzwf, j4, j6, j5, str2, jSONObject, zzacf2, null, null, null, z2, zzaso, null, list5, str3, zzum, this.zzdsk.zzehy.zzbph, this.zzdsk.zzehx, z, this.zzdsk.zzehy.zzdls, this.zzdsk.zzehy.zzbpi, this.zzdtk, this.zzdsk.zzehy.zzdzf);
    }

    public final zzbcb<zzabm> zzg(JSONObject jSONObject) throws JSONException {
        JSONObject optJSONObject = jSONObject.optJSONObject("attribution");
        if (optJSONObject == null) {
            return zzbbq.zzm(null);
        }
        String optString = optJSONObject.optString(MimeTypes.BASE_TYPE_TEXT);
        int optInt = optJSONObject.optInt("text_size", -1);
        Integer zzb = zzb(optJSONObject, "text_color");
        Integer zzb2 = zzb(optJSONObject, "bg_color");
        int optInt2 = optJSONObject.optInt("animation_ms", 1000);
        int optInt3 = optJSONObject.optInt("presentation_ms", SsoErrorCodes.SDK_NOT_INITIALIZED);
        int i = (this.zzdsk.zzeag.zzbti == null || this.zzdsk.zzeag.zzbti.versionCode < 2) ? 1 : this.zzdsk.zzeag.zzbti.zzdcv;
        int i2 = i;
        boolean optBoolean = optJSONObject.optBoolean("allow_pub_rendering");
        List arrayList = new ArrayList();
        if (optJSONObject.optJSONArray("images") != null) {
            arrayList = zza(optJSONObject, "images", false, false, true);
        } else {
            arrayList.add(zza(optJSONObject, TtmlNode.TAG_IMAGE, false, false));
        }
        zzbcl zzbcl = new zzbcl();
        int size = r0.size();
        AtomicInteger atomicInteger = new AtomicInteger(0);
        for (zzbcb zza : r0) {
            List list = arrayList;
            zza.zza(new zzaqd(atomicInteger, size, zzbcl, arrayList), zzayf.zzeky);
            arrayList = list;
        }
        return zzbbq.zza((zzbcb) zzbcl, new zzaqb(this, optString, zzb2, zzb, optInt, optInt3, optInt2, i2, optBoolean), zzayf.zzeky);
    }

    private static Integer zzb(JSONObject jSONObject, String str) {
        try {
            jSONObject = jSONObject.getJSONObject(str);
            return Integer.valueOf(Color.rgb(jSONObject.getInt(e.o), jSONObject.getInt("g"), jSONObject.getInt(b.a)));
        } catch (JSONException unused) {
            return null;
        }
    }

    public final Future<zzabr> zza(JSONObject jSONObject, String str, boolean z) throws JSONException {
        jSONObject = jSONObject.getJSONObject(str);
        boolean optBoolean = jSONObject.optBoolean("require", true);
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        return zza(jSONObject, optBoolean, z);
    }

    public final zzbcb<zzabr> zza(JSONObject jSONObject, String str, boolean z, boolean z2) throws JSONException {
        jSONObject = z ? jSONObject.getJSONObject(str) : jSONObject.optJSONObject(str);
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        return zza(jSONObject, z, z2);
    }

    public final List<zzbcb<zzabr>> zza(JSONObject jSONObject, String str, boolean z, boolean z2, boolean z3) throws JSONException {
        JSONArray optJSONArray = jSONObject.optJSONArray(str);
        ArrayList arrayList = new ArrayList();
        if (optJSONArray == null || optJSONArray.length() == 0) {
            zzh(0, false);
            return arrayList;
        }
        int length = z3 ? optJSONArray.length() : 1;
        for (int i = 0; i < length; i++) {
            JSONObject jSONObject2 = optJSONArray.getJSONObject(i);
            if (jSONObject2 == null) {
                jSONObject2 = new JSONObject();
            }
            arrayList.add(zza(jSONObject2, false, z2));
        }
        return arrayList;
    }

    private final zzbcb<zzabr> zza(JSONObject jSONObject, boolean z, boolean z2) throws JSONException {
        CharSequence string;
        if (z) {
            string = jSONObject.getString("url");
        } else {
            string = jSONObject.optString("url");
        }
        double optDouble = jSONObject.optDouble("scale", 1.0d);
        boolean optBoolean = jSONObject.optBoolean("is_transparent", true);
        if (TextUtils.isEmpty(string)) {
            zzh(0, z);
            return zzbbq.zzm(null);
        } else if (z2) {
            return zzbbq.zzm(new zzabr(null, Uri.parse(string), optDouble));
        } else {
            return this.zzdte.zza(string, new zzaqc(this, z, optDouble, optBoolean, string));
        }
    }

    public final zzbcb<zzbgg> zzc(JSONObject jSONObject, String str) throws JSONException {
        JSONObject optJSONObject = jSONObject.optJSONObject(str);
        if (optJSONObject == null) {
            return zzbbq.zzm(null);
        }
        if (TextUtils.isEmpty(optJSONObject.optString("vast_xml"))) {
            zzbbd.zzeo("Required field 'vast_xml' is missing");
            return zzbbq.zzm(null);
        }
        zzaqf zza = zza(this.mContext, this.zzdcf, this.zzdsk, this.zzbln, this.zzdtf);
        boolean equals = "instream".equals(jSONObject.optString("type"));
        zzbcl zzbcl = new zzbcl();
        zzbcg.zzepo.execute(new zzaqg(zza, equals, optJSONObject, zzbcl));
        return zzbcl;
    }

    public final zzbcb<zzbgg> zza(String str, String str2, boolean z) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return zzbbq.zzm(null);
        }
        zzaqf zza = zza(this.mContext, this.zzdcf, this.zzdsk, this.zzbln, this.zzdtf);
        zzbcl zzbcl = new zzbcl();
        zzbcg.zzepo.execute(new zzaqh(zza, true, zzbcl, str, str2));
        return zzbcl;
    }

    private final boolean zzwe() {
        boolean z;
        synchronized (this.mLock) {
            z = this.zzdtg;
        }
        return z;
    }

    private final void zzcs(int i) {
        synchronized (this.mLock) {
            this.zzdtg = true;
            this.mErrorCode = i;
        }
    }

    public final void zzh(int i, boolean z) {
        if (z) {
            zzcs(i);
        }
    }

    static zzbgg zzc(zzbcb<zzbgg> zzbcb) {
        try {
            return (zzbgg) zzbcb.get((long) ((Integer) zzwu.zzpz().zzd(zzaan.zzcuh)).intValue(), TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            zzbbd.zzc("InterruptedException occurred while waiting for video to load", e);
            Thread.currentThread().interrupt();
            return null;
        } catch (CancellationException | ExecutionException | TimeoutException e2) {
            zzbbd.zzc("Exception occurred while waiting for video to load", e2);
            return null;
        }
    }

    private static <V> List<V> zzk(List<zzbcb<V>> list) throws ExecutionException, InterruptedException {
        ArrayList arrayList = new ArrayList();
        for (zzbcb zzbcb : list) {
            Object obj = zzbcb.get();
            if (obj != null) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    @VisibleForTesting
    private static zzaqf zza(Context context, zzcu zzcu, zzaxg zzaxg, zzaba zzaba, zzbb zzbb) {
        return new zzaqf(context, zzcu, zzaxg, zzaba, zzbb);
    }

    public final /* synthetic */ Object call() throws Exception {
        return zzwd();
    }
}
