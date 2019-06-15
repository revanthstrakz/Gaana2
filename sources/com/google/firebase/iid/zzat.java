package com.google.firebase.iid;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcelable;
import android.support.v4.util.SimpleArrayMap;
import android.util.Log;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.iid.zzl.zza;
import com.moe.pushlibrary.utils.MoEHelperConstants;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

final class zzat {
    private static int zzcf;
    private static PendingIntent zzcr;
    private final zzan zzan;
    private final SimpleArrayMap<String, TaskCompletionSource<Bundle>> zzcs = new SimpleArrayMap();
    private Messenger zzct;
    private Messenger zzcu;
    private zzl zzcv;
    private final Context zzx;

    public zzat(Context context, zzan zzan) {
        this.zzx = context;
        this.zzan = zzan;
        this.zzct = new Messenger(new zzau(this, Looper.getMainLooper()));
    }

    private final void zzb(Message message) {
        if (message == null || !(message.obj instanceof Intent)) {
            Log.w("FirebaseInstanceId", "Dropping invalid message");
        } else {
            Intent intent = (Intent) message.obj;
            intent.setExtrasClassLoader(new zza());
            if (intent.hasExtra("google.messenger")) {
                Parcelable parcelableExtra = intent.getParcelableExtra("google.messenger");
                if (parcelableExtra instanceof zzl) {
                    this.zzcv = (zzl) parcelableExtra;
                }
                if (parcelableExtra instanceof Messenger) {
                    this.zzcu = (Messenger) parcelableExtra;
                }
            }
            Intent intent2 = (Intent) message.obj;
            String action = intent2.getAction();
            String valueOf;
            String str;
            if ("com.google.android.c2dm.intent.REGISTRATION".equals(action)) {
                CharSequence stringExtra = intent2.getStringExtra(MoEHelperConstants.EXTRA_REGISTRATION_ID);
                if (stringExtra == null) {
                    stringExtra = intent2.getStringExtra("unregistered");
                }
                if (stringExtra == null) {
                    action = intent2.getStringExtra("error");
                    if (action == null) {
                        valueOf = String.valueOf(intent2.getExtras());
                        StringBuilder stringBuilder = new StringBuilder(49 + String.valueOf(valueOf).length());
                        stringBuilder.append("Unexpected response, no error or registration id ");
                        stringBuilder.append(valueOf);
                        Log.w("FirebaseInstanceId", stringBuilder.toString());
                    } else {
                        if (Log.isLoggable("FirebaseInstanceId", 3)) {
                            String str2 = "FirebaseInstanceId";
                            String str3 = "Received InstanceID error ";
                            String valueOf2 = String.valueOf(action);
                            Log.d(str2, valueOf2.length() != 0 ? str3.concat(valueOf2) : new String(str3));
                        }
                        if (action.startsWith("|")) {
                            String[] split = action.split("\\|");
                            if (split.length <= 2 || !"ID".equals(split[1])) {
                                valueOf = "FirebaseInstanceId";
                                str = "Unexpected structured response ";
                                action = String.valueOf(action);
                                Log.w(valueOf, action.length() != 0 ? str.concat(action) : new String(str));
                            } else {
                                action = split[2];
                                str = split[3];
                                if (str.startsWith(":")) {
                                    str = str.substring(1);
                                }
                                zza(action, intent2.putExtra("error", str).getExtras());
                            }
                        } else {
                            synchronized (this.zzcs) {
                                for (int i = 0; i < this.zzcs.size(); i++) {
                                    zza((String) this.zzcs.keyAt(i), intent2.getExtras());
                                }
                            }
                        }
                    }
                } else {
                    Matcher matcher = Pattern.compile("\\|ID\\|([^|]+)\\|:?+(.*)").matcher(stringExtra);
                    if (matcher.matches()) {
                        action = matcher.group(1);
                        str = matcher.group(2);
                        Bundle extras = intent2.getExtras();
                        extras.putString(MoEHelperConstants.EXTRA_REGISTRATION_ID, str);
                        zza(action, extras);
                        return;
                    }
                    if (Log.isLoggable("FirebaseInstanceId", 3)) {
                        valueOf = "FirebaseInstanceId";
                        str = "Unexpected response string: ";
                        action = String.valueOf(stringExtra);
                        Log.d(valueOf, action.length() != 0 ? str.concat(action) : new String(str));
                    }
                    return;
                }
            }
            if (Log.isLoggable("FirebaseInstanceId", 3)) {
                valueOf = "FirebaseInstanceId";
                str = "Unexpected response action: ";
                action = String.valueOf(action);
                Log.d(valueOf, action.length() != 0 ? str.concat(action) : new String(str));
            }
        }
    }

    private static synchronized void zza(Context context, Intent intent) {
        synchronized (zzat.class) {
            if (zzcr == null) {
                Intent intent2 = new Intent();
                intent2.setPackage("com.google.example.invalidpackage");
                zzcr = PendingIntent.getBroadcast(context, 0, intent2, 0);
            }
            intent.putExtra("app", zzcr);
        }
    }

    private final void zza(String str, Bundle bundle) {
        synchronized (this.zzcs) {
            TaskCompletionSource taskCompletionSource = (TaskCompletionSource) this.zzcs.remove(str);
            if (taskCompletionSource == null) {
                String str2 = "FirebaseInstanceId";
                String str3 = "Missing callback for ";
                str = String.valueOf(str);
                Log.w(str2, str.length() != 0 ? str3.concat(str) : new String(str3));
                return;
            }
            taskCompletionSource.setResult(bundle);
        }
    }

    /* Access modifiers changed, original: final */
    public final Bundle zzc(Bundle bundle) throws IOException {
        if (this.zzan.zzaf() < 12000000) {
            return zzd(bundle);
        }
        try {
            return (Bundle) Tasks.await(zzab.zzc(this.zzx).zzb(1, bundle));
        } catch (InterruptedException | ExecutionException e) {
            if (Log.isLoggable("FirebaseInstanceId", 3)) {
                String valueOf = String.valueOf(e);
                StringBuilder stringBuilder = new StringBuilder(22 + String.valueOf(valueOf).length());
                stringBuilder.append("Error making request: ");
                stringBuilder.append(valueOf);
                Log.d("FirebaseInstanceId", stringBuilder.toString());
            }
            return ((e.getCause() instanceof zzal) && ((zzal) e.getCause()).getErrorCode() == 4) ? zzd(bundle) : null;
        }
    }

    private final Bundle zzd(Bundle bundle) throws IOException {
        Bundle zze = zze(bundle);
        if (zze == null || !zze.containsKey("google.messenger")) {
            return zze;
        }
        zze = zze(bundle);
        return (zze == null || !zze.containsKey("google.messenger")) ? zze : null;
    }

    private static synchronized String zzah() {
        String num;
        synchronized (zzat.class) {
            int i = zzcf;
            zzcf = i + 1;
            num = Integer.toString(i);
        }
        return num;
    }

    /* JADX WARNING: Removed duplicated region for block: B:38:0x00f7 A:{SYNTHETIC} */
    private final android.os.Bundle zze(android.os.Bundle r9) throws java.io.IOException {
        /*
        r8 = this;
        r0 = zzah();
        r1 = new com.google.android.gms.tasks.TaskCompletionSource;
        r1.<init>();
        r2 = r8.zzcs;
        monitor-enter(r2);
        r3 = r8.zzcs;	 Catch:{ all -> 0x0126 }
        r3.put(r0, r1);	 Catch:{ all -> 0x0126 }
        monitor-exit(r2);	 Catch:{ all -> 0x0126 }
        r2 = r8.zzan;
        r2 = r2.zzac();
        if (r2 != 0) goto L_0x0022;
    L_0x001a:
        r9 = new java.io.IOException;
        r0 = "MISSING_INSTANCEID_SERVICE";
        r9.<init>(r0);
        throw r9;
    L_0x0022:
        r2 = new android.content.Intent;
        r2.<init>();
        r3 = "com.google.android.gms";
        r2.setPackage(r3);
        r3 = r8.zzan;
        r3 = r3.zzac();
        r4 = 2;
        if (r3 != r4) goto L_0x003b;
    L_0x0035:
        r3 = "com.google.iid.TOKEN_REQUEST";
        r2.setAction(r3);
        goto L_0x0040;
    L_0x003b:
        r3 = "com.google.android.c2dm.intent.REGISTER";
        r2.setAction(r3);
    L_0x0040:
        r2.putExtras(r9);
        r9 = r8.zzx;
        zza(r9, r2);
        r9 = "kid";
        r3 = 5;
        r5 = java.lang.String.valueOf(r0);
        r5 = r5.length();
        r3 = r3 + r5;
        r5 = new java.lang.StringBuilder;
        r5.<init>(r3);
        r3 = "|ID|";
        r5.append(r3);
        r5.append(r0);
        r3 = "|";
        r5.append(r3);
        r3 = r5.toString();
        r2.putExtra(r9, r3);
        r9 = "FirebaseInstanceId";
        r3 = 3;
        r9 = android.util.Log.isLoggable(r9, r3);
        if (r9 == 0) goto L_0x009f;
    L_0x0076:
        r9 = "FirebaseInstanceId";
        r5 = r2.getExtras();
        r5 = java.lang.String.valueOf(r5);
        r6 = 8;
        r7 = java.lang.String.valueOf(r5);
        r7 = r7.length();
        r6 = r6 + r7;
        r7 = new java.lang.StringBuilder;
        r7.<init>(r6);
        r6 = "Sending ";
        r7.append(r6);
        r7.append(r5);
        r5 = r7.toString();
        android.util.Log.d(r9, r5);
    L_0x009f:
        r9 = "google.messenger";
        r5 = r8.zzct;
        r2.putExtra(r9, r5);
        r9 = r8.zzcu;
        if (r9 != 0) goto L_0x00ae;
    L_0x00aa:
        r9 = r8.zzcv;
        if (r9 == 0) goto L_0x00d3;
    L_0x00ae:
        r9 = android.os.Message.obtain();
        r9.obj = r2;
        r5 = r8.zzcu;	 Catch:{ RemoteException -> 0x00c4 }
        if (r5 == 0) goto L_0x00be;
    L_0x00b8:
        r5 = r8.zzcu;	 Catch:{ RemoteException -> 0x00c4 }
        r5.send(r9);	 Catch:{ RemoteException -> 0x00c4 }
        goto L_0x00e6;
    L_0x00be:
        r5 = r8.zzcv;	 Catch:{ RemoteException -> 0x00c4 }
        r5.send(r9);	 Catch:{ RemoteException -> 0x00c4 }
        goto L_0x00e6;
    L_0x00c4:
        r9 = "FirebaseInstanceId";
        r9 = android.util.Log.isLoggable(r9, r3);
        if (r9 == 0) goto L_0x00d3;
    L_0x00cc:
        r9 = "FirebaseInstanceId";
        r3 = "Messenger failed, fallback to startService";
        android.util.Log.d(r9, r3);
    L_0x00d3:
        r9 = r8.zzan;
        r9 = r9.zzac();
        if (r9 != r4) goto L_0x00e1;
    L_0x00db:
        r9 = r8.zzx;
        r9.sendBroadcast(r2);
        goto L_0x00e6;
    L_0x00e1:
        r9 = r8.zzx;
        r9.startService(r2);
    L_0x00e6:
        r9 = r1.getTask();	 Catch:{ InterruptedException | TimeoutException -> 0x010a, InterruptedException | TimeoutException -> 0x010a, ExecutionException -> 0x0103 }
        r1 = 30000; // 0x7530 float:4.2039E-41 double:1.4822E-319;
        r3 = java.util.concurrent.TimeUnit.MILLISECONDS;	 Catch:{ InterruptedException | TimeoutException -> 0x010a, InterruptedException | TimeoutException -> 0x010a, ExecutionException -> 0x0103 }
        r9 = com.google.android.gms.tasks.Tasks.await(r9, r1, r3);	 Catch:{ InterruptedException | TimeoutException -> 0x010a, InterruptedException | TimeoutException -> 0x010a, ExecutionException -> 0x0103 }
        r9 = (android.os.Bundle) r9;	 Catch:{ InterruptedException | TimeoutException -> 0x010a, InterruptedException | TimeoutException -> 0x010a, ExecutionException -> 0x0103 }
        r1 = r8.zzcs;
        monitor-enter(r1);
        r2 = r8.zzcs;	 Catch:{ all -> 0x00fe }
        r2.remove(r0);	 Catch:{ all -> 0x00fe }
        monitor-exit(r1);	 Catch:{ all -> 0x00fe }
        return r9;
    L_0x00fe:
        r9 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x00fe }
        throw r9;
    L_0x0101:
        r9 = move-exception;
        goto L_0x0119;
    L_0x0103:
        r9 = move-exception;
        r1 = new java.io.IOException;	 Catch:{ all -> 0x0101 }
        r1.<init>(r9);	 Catch:{ all -> 0x0101 }
        throw r1;	 Catch:{ all -> 0x0101 }
    L_0x010a:
        r9 = "FirebaseInstanceId";
        r1 = "No response";
        android.util.Log.w(r9, r1);	 Catch:{ all -> 0x0101 }
        r9 = new java.io.IOException;	 Catch:{ all -> 0x0101 }
        r1 = "TIMEOUT";
        r9.<init>(r1);	 Catch:{ all -> 0x0101 }
        throw r9;	 Catch:{ all -> 0x0101 }
    L_0x0119:
        r1 = r8.zzcs;
        monitor-enter(r1);
        r2 = r8.zzcs;	 Catch:{ all -> 0x0123 }
        r2.remove(r0);	 Catch:{ all -> 0x0123 }
        monitor-exit(r1);	 Catch:{ all -> 0x0123 }
        throw r9;
    L_0x0123:
        r9 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x0123 }
        throw r9;
    L_0x0126:
        r9 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x0126 }
        throw r9;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.iid.zzat.zze(android.os.Bundle):android.os.Bundle");
    }
}
