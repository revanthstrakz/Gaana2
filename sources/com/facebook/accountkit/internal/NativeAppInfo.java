package com.facebook.accountkit.internal;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.os.Build;
import java.util.HashSet;
import java.util.TreeSet;

abstract class NativeAppInfo {
    private static final String FBI_HASH = "a4b7452e2ed8f5f191058ca7bbfd26b0d3214bfc";
    private static final String FBL_HASH = "5e8f16062ea3cd2c4a0d547876baa6f38cabf625";
    private static final String FBR_HASH = "8a3c4b262d721acd49a4bf97d5213199c86fa2b9";
    private static final String TAG = "NativeAppInfo";
    private static final HashSet<String> validAppSignatureHashes = buildAppSignatureHashes();
    private boolean appInstalled;
    private TreeSet<Integer> availableVersions;

    public abstract String getPackage();

    public abstract Intent getPlatformServiceIntent();

    NativeAppInfo() {
    }

    private static HashSet<String> buildAppSignatureHashes() {
        HashSet hashSet = new HashSet();
        hashSet.add(FBR_HASH);
        hashSet.add(FBI_HASH);
        hashSet.add(FBL_HASH);
        return hashSet;
    }

    public boolean validateSignature(Context context, String str) {
        String str2 = Build.BRAND;
        int i = context.getApplicationInfo().flags;
        if (str2.startsWith("generic") && (i & 2) != 0) {
            return true;
        }
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(str, 64);
            if (packageInfo.signatures == null || packageInfo.signatures.length <= 0) {
                return false;
            }
            for (Signature toByteArray : packageInfo.signatures) {
                if (!validAppSignatureHashes.contains(Utility.sha1hash(toByteArray.toByteArray()))) {
                    return false;
                }
            }
            return true;
        } catch (NameNotFoundException unused) {
            return false;
        }
    }

    public boolean isAppInstalled() {
        if (this.availableVersions == null) {
            fetchAvailableVersions(false);
        }
        return this.appInstalled;
    }

    public TreeSet<Integer> getAvailableVersions() {
        if (this.availableVersions == null) {
            fetchAvailableVersions(false);
        }
        return this.availableVersions;
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x0073 */
    /* JADX WARNING: Can't wrap try/catch for region: R(2:23|24) */
    /* JADX WARNING: Missing block: B:24:?, code skipped:
            android.util.Log.e(TAG, "Failed to query content resolver.");
     */
    public synchronized void fetchAvailableVersions(boolean r10) {
        /*
        r9 = this;
        monitor-enter(r9);
        r0 = r9.availableVersions;	 Catch:{ all -> 0x00a8 }
        if (r0 == 0) goto L_0x0009;
    L_0x0005:
        if (r10 != 0) goto L_0x0009;
    L_0x0007:
        monitor-exit(r9);
        return;
    L_0x0009:
        r10 = new java.util.TreeSet;	 Catch:{ all -> 0x00a8 }
        r10.<init>();	 Catch:{ all -> 0x00a8 }
        r0 = com.facebook.accountkit.internal.AccountKitController.getApplicationContext();	 Catch:{ all -> 0x00a8 }
        r1 = r0.getContentResolver();	 Catch:{ all -> 0x00a8 }
        r0 = 1;
        r3 = new java.lang.String[r0];	 Catch:{ all -> 0x00a8 }
        r2 = "version";
        r4 = 0;
        r3[r4] = r2;	 Catch:{ all -> 0x00a8 }
        r2 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00a8 }
        r2.<init>();	 Catch:{ all -> 0x00a8 }
        r5 = "content://";
        r2.append(r5);	 Catch:{ all -> 0x00a8 }
        r5 = r9.getPackage();	 Catch:{ all -> 0x00a8 }
        r2.append(r5);	 Catch:{ all -> 0x00a8 }
        r5 = ".provider.PlatformProvider/versions";
        r2.append(r5);	 Catch:{ all -> 0x00a8 }
        r2 = r2.toString();	 Catch:{ all -> 0x00a8 }
        r2 = android.net.Uri.parse(r2);	 Catch:{ all -> 0x00a8 }
        r7 = 0;
        r5 = com.facebook.accountkit.internal.AccountKitController.getApplicationContext();	 Catch:{ all -> 0x009f }
        r5 = r5.getPackageManager();	 Catch:{ all -> 0x009f }
        r6 = new java.lang.StringBuilder;	 Catch:{ all -> 0x009f }
        r6.<init>();	 Catch:{ all -> 0x009f }
        r8 = r9.getPackage();	 Catch:{ all -> 0x009f }
        r6.append(r8);	 Catch:{ all -> 0x009f }
        r8 = ".provider.PlatformProvider";
        r6.append(r8);	 Catch:{ all -> 0x009f }
        r6 = r6.toString();	 Catch:{ all -> 0x009f }
        r4 = r5.resolveContentProvider(r6, r4);	 Catch:{ RuntimeException -> 0x005f }
        goto L_0x0068;
    L_0x005f:
        r4 = move-exception;
        r5 = TAG;	 Catch:{ all -> 0x009f }
        r6 = "Failed to query content resolver.";
        android.util.Log.e(r5, r6, r4);	 Catch:{ all -> 0x009f }
        r4 = r7;
    L_0x0068:
        if (r4 == 0) goto L_0x0094;
    L_0x006a:
        r4 = 0;
        r5 = 0;
        r6 = 0;
        r1 = r1.query(r2, r3, r4, r5, r6);	 Catch:{ NullPointerException | SecurityException -> 0x0073, NullPointerException | SecurityException -> 0x0073 }
        r7 = r1;
        goto L_0x007a;
    L_0x0073:
        r1 = TAG;	 Catch:{ all -> 0x009f }
        r2 = "Failed to query content resolver.";
        android.util.Log.e(r1, r2);	 Catch:{ all -> 0x009f }
    L_0x007a:
        if (r7 == 0) goto L_0x0094;
    L_0x007c:
        r1 = r7.moveToNext();	 Catch:{ all -> 0x009f }
        if (r1 == 0) goto L_0x0094;
    L_0x0082:
        r1 = "version";
        r1 = r7.getColumnIndex(r1);	 Catch:{ all -> 0x009f }
        r1 = r7.getInt(r1);	 Catch:{ all -> 0x009f }
        r1 = java.lang.Integer.valueOf(r1);	 Catch:{ all -> 0x009f }
        r10.add(r1);	 Catch:{ all -> 0x009f }
        goto L_0x007c;
    L_0x0094:
        if (r7 == 0) goto L_0x009b;
    L_0x0096:
        r9.appInstalled = r0;	 Catch:{ all -> 0x00a8 }
        r7.close();	 Catch:{ all -> 0x00a8 }
    L_0x009b:
        r9.availableVersions = r10;	 Catch:{ all -> 0x00a8 }
        monitor-exit(r9);
        return;
    L_0x009f:
        r10 = move-exception;
        if (r7 == 0) goto L_0x00a7;
    L_0x00a2:
        r9.appInstalled = r0;	 Catch:{ all -> 0x00a8 }
        r7.close();	 Catch:{ all -> 0x00a8 }
    L_0x00a7:
        throw r10;	 Catch:{ all -> 0x00a8 }
    L_0x00a8:
        r10 = move-exception;
        monitor-exit(r9);
        throw r10;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.accountkit.internal.NativeAppInfo.fetchAvailableVersions(boolean):void");
    }
}
