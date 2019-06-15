package com.google.android.gms.ads.internal.gmsg;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.view.View;
import com.google.android.exoplayer2.C;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.ads.zzcu;
import java.util.ArrayList;
import java.util.List;

@VisibleForTesting
public final class zzad {
    private final Context mContext;
    private final View mView;
    private final zzcu zzdgf;

    public zzad(Context context, zzcu zzcu, View view) {
        this.mContext = context;
        this.zzdgf = zzcu;
        this.mView = view;
    }

    private static Intent zze(Uri uri) {
        if (uri == null) {
            return null;
        }
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addFlags(C.ENCODING_PCM_MU_LAW);
        intent.setData(uri);
        intent.setAction("android.intent.action.VIEW");
        return intent;
    }

    @VisibleForTesting
    private final ResolveInfo zzb(Intent intent) {
        return zza(intent, new ArrayList());
    }

    @VisibleForTesting
    private final ResolveInfo zza(Intent intent, ArrayList<ResolveInfo> arrayList) {
        ResolveInfo resolveInfo = null;
        try {
            PackageManager packageManager = this.mContext.getPackageManager();
            if (packageManager == null) {
                return null;
            }
            List queryIntentActivities = packageManager.queryIntentActivities(intent, 65536);
            ResolveInfo resolveActivity = packageManager.resolveActivity(intent, 65536);
            if (queryIntentActivities != null && resolveActivity != null) {
                for (int i = 0; i < queryIntentActivities.size(); i++) {
                    ResolveInfo resolveInfo2 = (ResolveInfo) queryIntentActivities.get(i);
                    if (resolveActivity != null && resolveActivity.activityInfo.name.equals(resolveInfo2.activityInfo.name)) {
                        resolveInfo = resolveActivity;
                        break;
                    }
                }
            }
            arrayList.addAll(queryIntentActivities);
            return resolveInfo;
        } catch (Throwable th) {
            zzbv.zzlj().zza(th, "OpenSystemBrowserHandler.getDefaultBrowserResolverForIntent");
        }
    }

    private static Intent zza(Intent intent, ResolveInfo resolveInfo) {
        Intent intent2 = new Intent(intent);
        intent2.setClassName(resolveInfo.activityInfo.packageName, resolveInfo.activityInfo.name);
        return intent2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0080  */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x0071  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x00a9  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x00c4  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x00bf  */
    @com.google.android.gms.common.util.VisibleForTesting
    public final android.content.Intent zzi(java.util.Map<java.lang.String, java.lang.String> r12) {
        /*
        r11 = this;
        r0 = r11.mContext;
        r1 = "activity";
        r0 = r0.getSystemService(r1);
        r0 = (android.app.ActivityManager) r0;
        r1 = "u";
        r1 = r12.get(r1);
        r1 = (java.lang.String) r1;
        r2 = android.text.TextUtils.isEmpty(r1);
        r3 = 0;
        if (r2 == 0) goto L_0x001a;
    L_0x0019:
        return r3;
    L_0x001a:
        r2 = r11.mContext;
        r4 = r11.zzdgf;
        r5 = r11.mView;
        r1 = com.google.android.gms.ads.internal.gmsg.zzac.zza(r2, r4, r1, r5, r3);
        r1 = android.net.Uri.parse(r1);
        r2 = "use_first_package";
        r2 = r12.get(r2);
        r2 = (java.lang.String) r2;
        r2 = java.lang.Boolean.parseBoolean(r2);
        r4 = "use_running_process";
        r4 = r12.get(r4);
        r4 = (java.lang.String) r4;
        r4 = java.lang.Boolean.parseBoolean(r4);
        r5 = "use_custom_tabs";
        r12 = r12.get(r5);
        r12 = (java.lang.String) r12;
        r12 = java.lang.Boolean.parseBoolean(r12);
        r5 = 0;
        if (r12 != 0) goto L_0x0064;
    L_0x004f:
        r12 = com.google.android.gms.internal.ads.zzaan.zzcwb;
        r6 = com.google.android.gms.internal.ads.zzwu.zzpz();
        r12 = r6.zzd(r12);
        r12 = (java.lang.Boolean) r12;
        r12 = r12.booleanValue();
        if (r12 == 0) goto L_0x0062;
    L_0x0061:
        goto L_0x0064;
    L_0x0062:
        r12 = r5;
        goto L_0x0065;
    L_0x0064:
        r12 = 1;
    L_0x0065:
        r6 = "http";
        r7 = r1.getScheme();
        r6 = r6.equalsIgnoreCase(r7);
        if (r6 == 0) goto L_0x0080;
    L_0x0071:
        r3 = r1.buildUpon();
        r6 = "https";
        r3 = r3.scheme(r6);
        r3 = r3.build();
        goto L_0x009a;
    L_0x0080:
        r6 = "https";
        r7 = r1.getScheme();
        r6 = r6.equalsIgnoreCase(r7);
        if (r6 == 0) goto L_0x009a;
    L_0x008c:
        r3 = r1.buildUpon();
        r6 = "http";
        r3 = r3.scheme(r6);
        r3 = r3.build();
    L_0x009a:
        r6 = new java.util.ArrayList;
        r6.<init>();
        r1 = zze(r1);
        r3 = zze(r3);
        if (r12 == 0) goto L_0x00b9;
    L_0x00a9:
        com.google.android.gms.ads.internal.zzbv.zzlf();
        r12 = r11.mContext;
        com.google.android.gms.internal.ads.zzayh.zzb(r12, r1);
        com.google.android.gms.ads.internal.zzbv.zzlf();
        r12 = r11.mContext;
        com.google.android.gms.internal.ads.zzayh.zzb(r12, r3);
    L_0x00b9:
        r12 = r11.zza(r1, r6);
        if (r12 == 0) goto L_0x00c4;
    L_0x00bf:
        r12 = zza(r1, r12);
        return r12;
    L_0x00c4:
        if (r3 == 0) goto L_0x00d7;
    L_0x00c6:
        r12 = r11.zzb(r3);
        if (r12 == 0) goto L_0x00d7;
    L_0x00cc:
        r12 = zza(r1, r12);
        r3 = r11.zzb(r12);
        if (r3 == 0) goto L_0x00d7;
    L_0x00d6:
        return r12;
    L_0x00d7:
        r12 = r6.size();
        if (r12 != 0) goto L_0x00de;
    L_0x00dd:
        return r1;
    L_0x00de:
        if (r4 == 0) goto L_0x011b;
    L_0x00e0:
        if (r0 == 0) goto L_0x011b;
    L_0x00e2:
        r12 = r0.getRunningAppProcesses();
        if (r12 == 0) goto L_0x011b;
    L_0x00e8:
        r0 = r6;
        r0 = (java.util.ArrayList) r0;
        r3 = r0.size();
        r4 = r5;
    L_0x00f0:
        if (r4 >= r3) goto L_0x011b;
    L_0x00f2:
        r7 = r0.get(r4);
        r4 = r4 + 1;
        r7 = (android.content.pm.ResolveInfo) r7;
        r8 = r12.iterator();
    L_0x00fe:
        r9 = r8.hasNext();
        if (r9 == 0) goto L_0x00f0;
    L_0x0104:
        r9 = r8.next();
        r9 = (android.app.ActivityManager.RunningAppProcessInfo) r9;
        r9 = r9.processName;
        r10 = r7.activityInfo;
        r10 = r10.packageName;
        r9 = r9.equals(r10);
        if (r9 == 0) goto L_0x00fe;
    L_0x0116:
        r12 = zza(r1, r7);
        return r12;
    L_0x011b:
        if (r2 == 0) goto L_0x0128;
    L_0x011d:
        r12 = r6.get(r5);
        r12 = (android.content.pm.ResolveInfo) r12;
        r12 = zza(r1, r12);
        return r12;
    L_0x0128:
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.ads.internal.gmsg.zzad.zzi(java.util.Map):android.content.Intent");
    }
}
