package com.google.firebase.iid;

final /* synthetic */ class zzag implements Runnable {
    private final zzad zzcc;

    zzag(zzad zzad) {
        this.zzcc = zzad;
    }

    /* JADX WARNING: Missing block: B:15:0x0040, code skipped:
            if (android.util.Log.isLoggable("MessengerIpcClient", 3) == false) goto L_0x0067;
     */
    /* JADX WARNING: Missing block: B:16:0x0042, code skipped:
            r4 = java.lang.String.valueOf(r1);
            r6 = new java.lang.StringBuilder(8 + java.lang.String.valueOf(r4).length());
            r6.append("Sending ");
            r6.append(r4);
            android.util.Log.d("MessengerIpcClient", r6.toString());
     */
    /* JADX WARNING: Missing block: B:17:0x0067, code skipped:
            r3 = r0.zzcb.zzx;
            r4 = r0.zzbx;
            r5 = android.os.Message.obtain();
            r5.what = r1.what;
            r5.arg1 = r1.zzcf;
            r5.replyTo = r4;
            r4 = new android.os.Bundle();
            r4.putBoolean("oneWay", r1.zzab());
            r4.putString("pkg", r3.getPackageName());
            r4.putBundle("data", r1.zzch);
            r5.setData(r4);
     */
    /* JADX WARNING: Missing block: B:19:?, code skipped:
            r0.zzby.send(r5);
     */
    /* JADX WARNING: Missing block: B:21:0x00a5, code skipped:
            r1 = move-exception;
     */
    /* JADX WARNING: Missing block: B:22:0x00a6, code skipped:
            r0.zza(2, r1.getMessage());
     */
    public final void run() {
        /*
        r8 = this;
        r0 = r8.zzcc;
    L_0x0002:
        monitor-enter(r0);
        r1 = r0.state;	 Catch:{ all -> 0x00af }
        r2 = 2;
        if (r1 == r2) goto L_0x000a;
    L_0x0008:
        monitor-exit(r0);	 Catch:{ all -> 0x00af }
        return;
    L_0x000a:
        r1 = r0.zzbz;	 Catch:{ all -> 0x00af }
        r1 = r1.isEmpty();	 Catch:{ all -> 0x00af }
        if (r1 == 0) goto L_0x0017;
    L_0x0012:
        r0.zzz();	 Catch:{ all -> 0x00af }
        monitor-exit(r0);	 Catch:{ all -> 0x00af }
        return;
    L_0x0017:
        r1 = r0.zzbz;	 Catch:{ all -> 0x00af }
        r1 = r1.poll();	 Catch:{ all -> 0x00af }
        r1 = (com.google.firebase.iid.zzak) r1;	 Catch:{ all -> 0x00af }
        r3 = r0.zzca;	 Catch:{ all -> 0x00af }
        r4 = r1.zzcf;	 Catch:{ all -> 0x00af }
        r3.put(r4, r1);	 Catch:{ all -> 0x00af }
        r3 = r0.zzcb;	 Catch:{ all -> 0x00af }
        r3 = r3.zzbu;	 Catch:{ all -> 0x00af }
        r4 = new com.google.firebase.iid.zzah;	 Catch:{ all -> 0x00af }
        r4.<init>(r0, r1);	 Catch:{ all -> 0x00af }
        r5 = 30;
        r7 = java.util.concurrent.TimeUnit.SECONDS;	 Catch:{ all -> 0x00af }
        r3.schedule(r4, r5, r7);	 Catch:{ all -> 0x00af }
        monitor-exit(r0);	 Catch:{ all -> 0x00af }
        r3 = "MessengerIpcClient";
        r4 = 3;
        r3 = android.util.Log.isLoggable(r3, r4);
        if (r3 == 0) goto L_0x0067;
    L_0x0042:
        r3 = "MessengerIpcClient";
        r4 = java.lang.String.valueOf(r1);
        r5 = 8;
        r6 = java.lang.String.valueOf(r4);
        r6 = r6.length();
        r5 = r5 + r6;
        r6 = new java.lang.StringBuilder;
        r6.<init>(r5);
        r5 = "Sending ";
        r6.append(r5);
        r6.append(r4);
        r4 = r6.toString();
        android.util.Log.d(r3, r4);
    L_0x0067:
        r3 = r0.zzcb;
        r3 = r3.zzx;
        r4 = r0.zzbx;
        r5 = android.os.Message.obtain();
        r6 = r1.what;
        r5.what = r6;
        r6 = r1.zzcf;
        r5.arg1 = r6;
        r5.replyTo = r4;
        r4 = new android.os.Bundle;
        r4.<init>();
        r6 = "oneWay";
        r7 = r1.zzab();
        r4.putBoolean(r6, r7);
        r6 = "pkg";
        r3 = r3.getPackageName();
        r4.putString(r6, r3);
        r3 = "data";
        r1 = r1.zzch;
        r4.putBundle(r3, r1);
        r5.setData(r4);
        r1 = r0.zzby;	 Catch:{ RemoteException -> 0x00a5 }
        r1.send(r5);	 Catch:{ RemoteException -> 0x00a5 }
        goto L_0x0002;
    L_0x00a5:
        r1 = move-exception;
        r1 = r1.getMessage();
        r0.zza(r2, r1);
        goto L_0x0002;
    L_0x00af:
        r1 = move-exception;
        monitor-exit(r0);	 Catch:{ all -> 0x00af }
        throw r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.iid.zzag.run():void");
    }
}
