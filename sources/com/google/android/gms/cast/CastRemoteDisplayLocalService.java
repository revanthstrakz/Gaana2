package com.google.android.gms.cast;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ServiceInfo;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import android.support.v4.app.NotificationCompat.Builder;
import android.support.v7.media.MediaRouter;
import android.support.v7.media.MediaRouter.Callback;
import android.text.TextUtils;
import android.view.Display;
import com.google.android.gms.cast.CastRemoteDisplay.Configuration;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.internal.cast.zzdw;
import com.google.android.gms.internal.cast.zzez;
import java.lang.ref.WeakReference;
import java.util.concurrent.atomic.AtomicBoolean;

@TargetApi(19)
public abstract class CastRemoteDisplayLocalService extends Service {
    private static final zzdw zzbf = new zzdw("CastRemoteDisplayLocalService");
    private static final int zzbp = R.id.cast_notification_id;
    private static final Object zzbq = new Object();
    private static AtomicBoolean zzbr = new AtomicBoolean(false);
    private static CastRemoteDisplayLocalService zzcg;
    private Handler handler;
    private WeakReference<Callbacks> zzbs;
    private zzb zzbt;
    private NotificationSettings zzbu;
    private Notification zzbv;
    private boolean zzbw;
    private PendingIntent zzbx;
    private CastDevice zzby;
    private Display zzbz;
    private Context zzca;
    private ServiceConnection zzcb;
    private MediaRouter zzcc;
    private boolean zzcd = false;
    private CastRemoteDisplayClient zzce;
    private final Callback zzcf = new zzu(this);
    private final IBinder zzch = new zza();
    private String zzy;

    public interface Callbacks {
        void onRemoteDisplaySessionEnded(CastRemoteDisplayLocalService castRemoteDisplayLocalService);

        void onRemoteDisplaySessionError(Status status);

        void onRemoteDisplaySessionStarted(CastRemoteDisplayLocalService castRemoteDisplayLocalService);

        void onServiceCreated(CastRemoteDisplayLocalService castRemoteDisplayLocalService);
    }

    public static final class NotificationSettings {
        private Notification zzbv;
        private PendingIntent zzcp;
        private String zzcq;
        private String zzcr;

        public static final class Builder {
            private NotificationSettings zzcs = new NotificationSettings();

            public final Builder setNotification(Notification notification) {
                this.zzcs.zzbv = notification;
                return this;
            }

            public final Builder setNotificationPendingIntent(PendingIntent pendingIntent) {
                this.zzcs.zzcp = pendingIntent;
                return this;
            }

            public final Builder setNotificationTitle(String str) {
                this.zzcs.zzcq = str;
                return this;
            }

            public final Builder setNotificationText(String str) {
                this.zzcs.zzcr = str;
                return this;
            }

            public final NotificationSettings build() {
                if (this.zzcs.zzbv != null) {
                    if (!TextUtils.isEmpty(this.zzcs.zzcq)) {
                        throw new IllegalArgumentException("notificationTitle requires using the default notification");
                    } else if (!TextUtils.isEmpty(this.zzcs.zzcr)) {
                        throw new IllegalArgumentException("notificationText requires using the default notification");
                    } else if (this.zzcs.zzcp != null) {
                        throw new IllegalArgumentException("notificationPendingIntent requires using the default notification");
                    }
                } else if (TextUtils.isEmpty(this.zzcs.zzcq) && TextUtils.isEmpty(this.zzcs.zzcr) && this.zzcs.zzcp == null) {
                    throw new IllegalArgumentException("At least an argument must be provided");
                }
                return this.zzcs;
            }
        }

        private NotificationSettings() {
        }

        private NotificationSettings(NotificationSettings notificationSettings) {
            this.zzbv = notificationSettings.zzbv;
            this.zzcp = notificationSettings.zzcp;
            this.zzcq = notificationSettings.zzcq;
            this.zzcr = notificationSettings.zzcr;
        }

        /* synthetic */ NotificationSettings(NotificationSettings notificationSettings, zzu zzu) {
            this(notificationSettings);
        }
    }

    public static class Options {
        @Configuration
        private int zzbd = 2;

        public void setConfigPreset(@Configuration int i) {
            this.zzbd = i;
        }

        @Configuration
        public int getConfigPreset() {
            return this.zzbd;
        }
    }

    @VisibleForTesting
    class zza extends Binder {
        zza() {
        }
    }

    private static final class zzb extends BroadcastReceiver {
        private zzb() {
        }

        public final void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("com.google.android.gms.cast.remote_display.ACTION_NOTIFICATION_DISCONNECT")) {
                CastRemoteDisplayLocalService.stopService();
                return;
            }
            if (intent.getAction().equals("com.google.android.gms.cast.remote_display.ACTION_SESSION_ENDED")) {
                CastRemoteDisplayLocalService.zzb(false);
            }
        }

        /* synthetic */ zzb(zzu zzu) {
            this();
        }
    }

    public abstract void onCreatePresentation(Display display);

    public abstract void onDismissPresentation();

    public IBinder onBind(Intent intent) {
        zzb("onBind");
        return this.zzch;
    }

    public void onCreate() {
        zzb("onCreate");
        super.onCreate();
        this.handler = new zzez(getMainLooper());
        this.handler.postDelayed(new zzv(this), 100);
        if (this.zzce == null) {
            this.zzce = CastRemoteDisplay.getClient(this);
        }
        if (PlatformVersion.isAtLeastO()) {
            NotificationManager notificationManager = (NotificationManager) getSystemService(NotificationManager.class);
            NotificationChannel notificationChannel = new NotificationChannel("cast_remote_display_local_service", getString(R.string.cast_notification_default_channel_name), 2);
            notificationChannel.setShowBadge(false);
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        zzb("onStartCommand");
        this.zzcd = true;
        return 2;
    }

    protected static void setDebugEnabled() {
        zzbf.zzl(true);
    }

    /* Access modifiers changed, original: protected */
    public Display getDisplay() {
        return this.zzbz;
    }

    public static CastRemoteDisplayLocalService getInstance() {
        CastRemoteDisplayLocalService castRemoteDisplayLocalService;
        synchronized (zzbq) {
            castRemoteDisplayLocalService = zzcg;
        }
        return castRemoteDisplayLocalService;
    }

    public static void startService(Context context, Class<? extends CastRemoteDisplayLocalService> cls, String str, CastDevice castDevice, NotificationSettings notificationSettings, Callbacks callbacks) {
        startServiceWithOptions(context, cls, str, castDevice, new Options(), notificationSettings, callbacks);
    }

    public static void startServiceWithOptions(@NonNull Context context, @NonNull Class<? extends CastRemoteDisplayLocalService> cls, @NonNull String str, @NonNull CastDevice castDevice, @NonNull Options options, @NonNull NotificationSettings notificationSettings, @NonNull Callbacks callbacks) {
        zzbf.d("Starting Service", new Object[0]);
        synchronized (zzbq) {
            if (zzcg != null) {
                zzbf.w("An existing service had not been stopped before starting one", new Object[0]);
                zzb(true);
            }
        }
        try {
            ServiceInfo serviceInfo = context.getPackageManager().getServiceInfo(new ComponentName(context, cls), 128);
            if (serviceInfo == null || !serviceInfo.exported) {
                Preconditions.checkNotNull(context, "activityContext is required.");
                Preconditions.checkNotNull(cls, "serviceClass is required.");
                Preconditions.checkNotNull(str, "applicationId is required.");
                Preconditions.checkNotNull(castDevice, "device is required.");
                Preconditions.checkNotNull(options, "options is required.");
                Preconditions.checkNotNull(notificationSettings, "notificationSettings is required.");
                Preconditions.checkNotNull(callbacks, "callbacks is required.");
                if (notificationSettings.zzbv == null && notificationSettings.zzcp == null) {
                    throw new IllegalArgumentException("notificationSettings: Either the notification or the notificationPendingIntent must be provided");
                } else if (zzbr.getAndSet(true)) {
                    zzbf.e("Service is already being started, startService has been called twice", new Object[0]);
                    return;
                } else {
                    Intent intent = new Intent(context, cls);
                    context.startService(intent);
                    context.bindService(intent, new zzw(str, castDevice, options, notificationSettings, context, callbacks), 64);
                    return;
                }
            }
            throw new IllegalStateException("The service must not be exported, verify the manifest configuration");
        } catch (NameNotFoundException unused) {
            throw new IllegalStateException("Service not found, did you forget to configure it in the manifest?");
        }
    }

    private final void zza(boolean z) {
        zzb("Stopping Service");
        Preconditions.checkMainThread("stopServiceInstanceInternal must be called on the main thread");
        if (!(z || this.zzcc == null)) {
            zzb("Setting default route");
            this.zzcc.selectRoute(this.zzcc.getDefaultRoute());
        }
        if (this.zzbt != null) {
            zzb("Unregistering notification receiver");
            unregisterReceiver(this.zzbt);
        }
        zzb("stopRemoteDisplaySession");
        zzb("stopRemoteDisplay");
        this.zzce.stopRemoteDisplay().addOnCompleteListener(new zzaa(this));
        if (this.zzbs.get() != null) {
            ((Callbacks) this.zzbs.get()).onRemoteDisplaySessionEnded(this);
        }
        onDismissPresentation();
        zzb("Stopping the remote display Service");
        stopForeground(true);
        stopSelf();
        if (this.zzcc != null) {
            Preconditions.checkMainThread("CastRemoteDisplayLocalService calls must be done on the main thread");
            zzb("removeMediaRouterCallback");
            this.zzcc.removeCallback(this.zzcf);
        }
        if (!(this.zzca == null || this.zzcb == null)) {
            try {
                this.zzca.unbindService(this.zzcb);
            } catch (IllegalArgumentException unused) {
                zzb("No need to unbind service, already unbound");
            }
            this.zzcb = null;
            this.zzca = null;
        }
        this.zzy = null;
        this.zzbv = null;
        this.zzbz = null;
    }

    /* JADX WARNING: Missing block: B:11:0x0029, code skipped:
            if (r1.handler == null) goto L_0x0043;
     */
    /* JADX WARNING: Missing block: B:13:0x0033, code skipped:
            if (android.os.Looper.myLooper() == android.os.Looper.getMainLooper()) goto L_0x0040;
     */
    /* JADX WARNING: Missing block: B:14:0x0035, code skipped:
            r1.handler.post(new com.google.android.gms.cast.zzx(r1, r4));
     */
    /* JADX WARNING: Missing block: B:15:0x003f, code skipped:
            return;
     */
    /* JADX WARNING: Missing block: B:16:0x0040, code skipped:
            r1.zza(r4);
     */
    /* JADX WARNING: Missing block: B:17:0x0043, code skipped:
            return;
     */
    private static void zzb(boolean r4) {
        /*
        r0 = zzbf;
        r1 = "Stopping Service";
        r2 = 0;
        r3 = new java.lang.Object[r2];
        r0.d(r1, r3);
        r0 = zzbr;
        r0.set(r2);
        r0 = zzbq;
        monitor-enter(r0);
        r1 = zzcg;	 Catch:{ all -> 0x0044 }
        if (r1 != 0) goto L_0x0021;
    L_0x0016:
        r4 = zzbf;	 Catch:{ all -> 0x0044 }
        r1 = "Service is already being stopped";
        r2 = new java.lang.Object[r2];	 Catch:{ all -> 0x0044 }
        r4.e(r1, r2);	 Catch:{ all -> 0x0044 }
        monitor-exit(r0);	 Catch:{ all -> 0x0044 }
        return;
    L_0x0021:
        r1 = zzcg;	 Catch:{ all -> 0x0044 }
        r2 = 0;
        zzcg = r2;	 Catch:{ all -> 0x0044 }
        monitor-exit(r0);	 Catch:{ all -> 0x0044 }
        r0 = r1.handler;
        if (r0 == 0) goto L_0x0043;
    L_0x002b:
        r0 = android.os.Looper.myLooper();
        r2 = android.os.Looper.getMainLooper();
        if (r0 == r2) goto L_0x0040;
    L_0x0035:
        r0 = r1.handler;
        r2 = new com.google.android.gms.cast.zzx;
        r2.<init>(r1, r4);
        r0.post(r2);
        return;
    L_0x0040:
        r1.zza(r4);
    L_0x0043:
        return;
    L_0x0044:
        r4 = move-exception;
        monitor-exit(r0);	 Catch:{ all -> 0x0044 }
        throw r4;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.cast.CastRemoteDisplayLocalService.zzb(boolean):void");
    }

    public static void stopService() {
        zzb(false);
    }

    public void updateNotificationSettings(NotificationSettings notificationSettings) {
        Preconditions.checkNotNull(notificationSettings, "notificationSettings is required.");
        Preconditions.checkNotNull(this.handler, "Service is not ready yet.");
        this.handler.post(new zzy(this, notificationSettings));
    }

    private final void zza(NotificationSettings notificationSettings) {
        Preconditions.checkMainThread("updateNotificationSettingsInternal must be called on the main thread");
        if (this.zzbu == null) {
            throw new IllegalStateException("No current notification settings to update");
        }
        if (!this.zzbw) {
            Preconditions.checkNotNull(notificationSettings.zzbv, "notification is required.");
            this.zzbv = notificationSettings.zzbv;
            this.zzbu.zzbv = this.zzbv;
        } else if (notificationSettings.zzbv != null) {
            throw new IllegalStateException("Current mode is default notification, notification attribute must not be provided");
        } else {
            if (notificationSettings.zzcp != null) {
                this.zzbu.zzcp = notificationSettings.zzcp;
            }
            if (!TextUtils.isEmpty(notificationSettings.zzcq)) {
                this.zzbu.zzcq = notificationSettings.zzcq;
            }
            if (!TextUtils.isEmpty(notificationSettings.zzcr)) {
                this.zzbu.zzcr = notificationSettings.zzcr;
            }
            this.zzbv = zzc(true);
        }
        startForeground(zzbp, this.zzbv);
    }

    private final void zza(Display display) {
        this.zzbz = display;
        if (this.zzbw) {
            this.zzbv = zzc(true);
            startForeground(zzbp, this.zzbv);
        }
        if (this.zzbs.get() != null) {
            ((Callbacks) this.zzbs.get()).onRemoteDisplaySessionStarted(this);
        }
        onCreatePresentation(this.zzbz);
    }

    /* JADX WARNING: Missing block: B:10:0x0020, code skipped:
            r3.zzbs = new java.lang.ref.WeakReference(r10);
            r3.zzy = r4;
            r3.zzby = r5;
            r3.zzca = r8;
            r3.zzcb = r9;
     */
    /* JADX WARNING: Missing block: B:11:0x0031, code skipped:
            if (r3.zzcc != null) goto L_0x003d;
     */
    /* JADX WARNING: Missing block: B:12:0x0033, code skipped:
            r3.zzcc = android.support.v7.media.MediaRouter.getInstance(getApplicationContext());
     */
    /* JADX WARNING: Missing block: B:13:0x003d, code skipped:
            r4 = new android.support.v7.media.MediaRouteSelector.Builder().addControlCategory(com.google.android.gms.cast.CastMediaControlIntent.categoryForCast(r3.zzy)).build();
            zzb("addMediaRouterCallback");
            r3.zzcc.addCallback(r4, r3.zzcf, 4);
            r3.zzbv = com.google.android.gms.cast.CastRemoteDisplayLocalService.NotificationSettings.zzb(r7);
            r3.zzbt = new com.google.android.gms.cast.CastRemoteDisplayLocalService.zzb();
            r4 = new android.content.IntentFilter();
            r4.addAction("com.google.android.gms.cast.remote_display.ACTION_NOTIFICATION_DISCONNECT");
            r4.addAction("com.google.android.gms.cast.remote_display.ACTION_SESSION_ENDED");
            registerReceiver(r3.zzbt, r4);
            r3.zzbu = new com.google.android.gms.cast.CastRemoteDisplayLocalService.NotificationSettings(r7, null);
     */
    /* JADX WARNING: Missing block: B:14:0x008d, code skipped:
            if (com.google.android.gms.cast.CastRemoteDisplayLocalService.NotificationSettings.zzb(r3.zzbu) != null) goto L_0x0098;
     */
    /* JADX WARNING: Missing block: B:15:0x008f, code skipped:
            r3.zzbw = true;
            r3.zzbv = zzc(false);
     */
    /* JADX WARNING: Missing block: B:16:0x0098, code skipped:
            r3.zzbw = false;
            r3.zzbv = com.google.android.gms.cast.CastRemoteDisplayLocalService.NotificationSettings.zzb(r3.zzbu);
     */
    /* JADX WARNING: Missing block: B:17:0x00a2, code skipped:
            startForeground(zzbp, r3.zzbv);
            zzb("startRemoteDisplay");
            r4 = new android.content.Intent("com.google.android.gms.cast.remote_display.ACTION_SESSION_ENDED");
            r4.setPackage(r3.zzca.getPackageName());
            r3.zzce.startRemoteDisplay(r5, r3.zzy, r6.getConfigPreset(), android.app.PendingIntent.getBroadcast(r3, 0, r4, 0)).addOnCompleteListener(new com.google.android.gms.cast.zzz(r3));
     */
    /* JADX WARNING: Missing block: B:18:0x00dc, code skipped:
            if (r3.zzbs.get() == null) goto L_0x00e9;
     */
    /* JADX WARNING: Missing block: B:19:0x00de, code skipped:
            ((com.google.android.gms.cast.CastRemoteDisplayLocalService.Callbacks) r3.zzbs.get()).onServiceCreated(r3);
     */
    /* JADX WARNING: Missing block: B:20:0x00e9, code skipped:
            return true;
     */
    private final boolean zza(java.lang.String r4, com.google.android.gms.cast.CastDevice r5, com.google.android.gms.cast.CastRemoteDisplayLocalService.Options r6, com.google.android.gms.cast.CastRemoteDisplayLocalService.NotificationSettings r7, android.content.Context r8, android.content.ServiceConnection r9, com.google.android.gms.cast.CastRemoteDisplayLocalService.Callbacks r10) {
        /*
        r3 = this;
        r0 = "startRemoteDisplaySession";
        r3.zzb(r0);
        r0 = "Starting the Cast Remote Display must be done on the main thread";
        com.google.android.gms.common.internal.Preconditions.checkMainThread(r0);
        r0 = zzbq;
        monitor-enter(r0);
        r1 = zzcg;	 Catch:{ all -> 0x00ea }
        r2 = 0;
        if (r1 == 0) goto L_0x001d;
    L_0x0012:
        r4 = zzbf;	 Catch:{ all -> 0x00ea }
        r5 = "An existing service had not been stopped before starting one";
        r6 = new java.lang.Object[r2];	 Catch:{ all -> 0x00ea }
        r4.w(r5, r6);	 Catch:{ all -> 0x00ea }
        monitor-exit(r0);	 Catch:{ all -> 0x00ea }
        return r2;
    L_0x001d:
        zzcg = r3;	 Catch:{ all -> 0x00ea }
        monitor-exit(r0);	 Catch:{ all -> 0x00ea }
        r0 = new java.lang.ref.WeakReference;
        r0.<init>(r10);
        r3.zzbs = r0;
        r3.zzy = r4;
        r3.zzby = r5;
        r3.zzca = r8;
        r3.zzcb = r9;
        r4 = r3.zzcc;
        if (r4 != 0) goto L_0x003d;
    L_0x0033:
        r4 = r3.getApplicationContext();
        r4 = android.support.v7.media.MediaRouter.getInstance(r4);
        r3.zzcc = r4;
    L_0x003d:
        r4 = new android.support.v7.media.MediaRouteSelector$Builder;
        r4.<init>();
        r8 = r3.zzy;
        r8 = com.google.android.gms.cast.CastMediaControlIntent.categoryForCast(r8);
        r4 = r4.addControlCategory(r8);
        r4 = r4.build();
        r8 = "addMediaRouterCallback";
        r3.zzb(r8);
        r8 = r3.zzcc;
        r9 = r3.zzcf;
        r10 = 4;
        r8.addCallback(r4, r9, r10);
        r4 = r7.zzbv;
        r3.zzbv = r4;
        r4 = new com.google.android.gms.cast.CastRemoteDisplayLocalService$zzb;
        r8 = 0;
        r4.<init>(r8);
        r3.zzbt = r4;
        r4 = new android.content.IntentFilter;
        r4.<init>();
        r9 = "com.google.android.gms.cast.remote_display.ACTION_NOTIFICATION_DISCONNECT";
        r4.addAction(r9);
        r9 = "com.google.android.gms.cast.remote_display.ACTION_SESSION_ENDED";
        r4.addAction(r9);
        r9 = r3.zzbt;
        r3.registerReceiver(r9, r4);
        r4 = new com.google.android.gms.cast.CastRemoteDisplayLocalService$NotificationSettings;
        r4.<init>(r7, r8);
        r3.zzbu = r4;
        r4 = r3.zzbu;
        r4 = r4.zzbv;
        r7 = 1;
        if (r4 != 0) goto L_0x0098;
    L_0x008f:
        r3.zzbw = r7;
        r4 = r3.zzc(r2);
        r3.zzbv = r4;
        goto L_0x00a2;
    L_0x0098:
        r3.zzbw = r2;
        r4 = r3.zzbu;
        r4 = r4.zzbv;
        r3.zzbv = r4;
    L_0x00a2:
        r4 = zzbp;
        r8 = r3.zzbv;
        r3.startForeground(r4, r8);
        r4 = "startRemoteDisplay";
        r3.zzb(r4);
        r4 = new android.content.Intent;
        r8 = "com.google.android.gms.cast.remote_display.ACTION_SESSION_ENDED";
        r4.<init>(r8);
        r8 = r3.zzca;
        r8 = r8.getPackageName();
        r4.setPackage(r8);
        r4 = android.app.PendingIntent.getBroadcast(r3, r2, r4, r2);
        r8 = r3.zzce;
        r9 = r3.zzy;
        r6 = r6.getConfigPreset();
        r4 = r8.startRemoteDisplay(r5, r9, r6, r4);
        r5 = new com.google.android.gms.cast.zzz;
        r5.<init>(r3);
        r4.addOnCompleteListener(r5);
        r4 = r3.zzbs;
        r4 = r4.get();
        if (r4 == 0) goto L_0x00e9;
    L_0x00de:
        r4 = r3.zzbs;
        r4 = r4.get();
        r4 = (com.google.android.gms.cast.CastRemoteDisplayLocalService.Callbacks) r4;
        r4.onServiceCreated(r3);
    L_0x00e9:
        return r7;
    L_0x00ea:
        r4 = move-exception;
        monitor-exit(r0);	 Catch:{ all -> 0x00ea }
        throw r4;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.cast.CastRemoteDisplayLocalService.zza(java.lang.String, com.google.android.gms.cast.CastDevice, com.google.android.gms.cast.CastRemoteDisplayLocalService$Options, com.google.android.gms.cast.CastRemoteDisplayLocalService$NotificationSettings, android.content.Context, android.content.ServiceConnection, com.google.android.gms.cast.CastRemoteDisplayLocalService$Callbacks):boolean");
    }

    private final void zze() {
        if (this.zzbs.get() != null) {
            ((Callbacks) this.zzbs.get()).onRemoteDisplaySessionError(new Status(CastStatusCodes.ERROR_SERVICE_CREATION_FAILED));
        }
        stopService();
    }

    private final Notification zzc(boolean z) {
        int i;
        int i2;
        zzb("createDefaultNotification");
        CharSequence zzc = this.zzbu.zzcq;
        CharSequence zzd = this.zzbu.zzcr;
        if (z) {
            i = R.string.cast_notification_connected_message;
            i2 = R.drawable.cast_ic_notification_on;
        } else {
            i = R.string.cast_notification_connecting_message;
            i2 = R.drawable.cast_ic_notification_connecting;
        }
        if (TextUtils.isEmpty(zzc)) {
            zzc = (String) getPackageManager().getApplicationLabel(getApplicationInfo());
        }
        if (TextUtils.isEmpty(zzd)) {
            zzd = getString(i, new Object[]{this.zzby.getFriendlyName()});
        }
        Builder ongoing = new Builder(this, "cast_remote_display_local_service").setContentTitle(zzc).setContentText(zzd).setContentIntent(this.zzbu.zzcp).setSmallIcon(i2).setOngoing(true);
        String string = getString(R.string.cast_notification_disconnect);
        if (this.zzbx == null) {
            Intent intent = new Intent("com.google.android.gms.cast.remote_display.ACTION_NOTIFICATION_DISCONNECT");
            intent.setPackage(this.zzca.getPackageName());
            this.zzbx = PendingIntent.getBroadcast(this, 0, intent, 134217728);
        }
        return ongoing.addAction(17301560, string, this.zzbx).build();
    }

    private final void zzb(String str) {
        zzbf.d("[Instance: %s] %s", this, str);
    }

    private final void zzc(String str) {
        zzbf.e("[Instance: %s] %s", this, str);
    }
}
